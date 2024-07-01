package no.nav.pensjon.elsam.minibuss.security;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.security.SecurityContext;
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor;
import org.apache.wss4j.common.principal.SAMLTokenPrincipal;
import org.apache.wss4j.common.ext.WSSecurityException;
import org.apache.wss4j.common.crypto.Crypto;
import org.apache.wss4j.common.crypto.CryptoFactory;
import org.apache.wss4j.dom.handler.RequestData;
import org.opensaml.saml.saml2.core.Assertion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static no.nav.pensjon.elsam.minibuss.security.SAMLUtils.*;
import static org.apache.wss4j.common.ConfigurationConstants.ACTION;
import static org.apache.wss4j.common.ConfigurationConstants.SAML_TOKEN_SIGNED;

/**
 * CXF Soap interceptor som validerer SAML-token og logger inn caller
 */
public class SAMLInInterceptor extends WSS4JInInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(SAMLInInterceptor.class);

    public SAMLInInterceptor(Map<String, Object> properties) {
        super(new HashMap<>(properties));
        setProperty(ACTION, SAML_TOKEN_SIGNED);
    }

    @Override
    public Crypto loadSignatureCrypto(RequestData requestData) throws WSSecurityException {
        Properties signatureProperties = new Properties();
        signatureProperties.setProperty("org.apache.wss4j.crypto.merlin.truststore.file", System.getProperty("javax.net.ssl.trustStore"));
        signatureProperties.setProperty("org.apache.wss4j.crypto.merlin.truststore.password", System.getProperty("javax.net.ssl.trustStorePassword"));

        return CryptoFactory.getInstance(signatureProperties);
    }

    @Override
    public void handleMessage(SoapMessage msg) {
        super.handleMessage(msg);

        SecurityContext sc = (SecurityContext) msg.get(SecurityContext.class.getName());
        if(sc == null) {
            throw new RuntimeException("Cannot get SecurityContext from SoapMessage");
        }
        SAMLTokenPrincipal samlTokenPrincipal = (SAMLTokenPrincipal) sc.getUserPrincipal();
        if(samlTokenPrincipal == null) {
            throw new RuntimeException("Cannot get SAMLTokenPrincipal from SecurityContext");
        }
        Assertion assertion = samlTokenPrincipal.getToken().getSaml2();

        if (logger.isDebugEnabled()) {
            logger.debug("SAML Issuer: {}", assertion.getIssuer().getValue());
            logger.debug("SAML Subject: {}", assertion.getSubject().getNameID().getValue());
        }

        try {
            samlAssertionToSubject(assertion);
        } catch (Exception e) {
            logger.info("Login failed", e);
            WSSecurityException wsSecurityException = new WSSecurityException(WSSecurityException.ErrorCode.INVALID_SECURITY_TOKEN, e);
            throw new Fault(wsSecurityException, wsSecurityException.getFaultCode());
        }
    }

}
