package no.nav.pensjon.elsam.minibuss.security

import no.nav.pensjon.elsam.minibuss.security.SAMLUtils.samlAssertionToSubject
import org.apache.cxf.binding.soap.SoapMessage
import org.apache.cxf.interceptor.Fault
import org.apache.cxf.security.SecurityContext
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor
import org.apache.wss4j.common.ConfigurationConstants
import org.apache.wss4j.common.ConfigurationConstants.ACTION
import org.apache.wss4j.common.ConfigurationConstants.SAML_TOKEN_SIGNED
import org.apache.wss4j.common.crypto.Crypto
import org.apache.wss4j.common.crypto.CryptoFactory
import org.apache.wss4j.common.ext.WSSecurityException
import org.apache.wss4j.common.ext.WSSecurityException.ErrorCode.INVALID_SECURITY_TOKEN
import org.apache.wss4j.common.principal.SAMLTokenPrincipal
import org.apache.wss4j.dom.handler.RequestData
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.*

/**
 * CXF Soap interceptor som validerer SAML-token og logger inn caller
 */
class SAMLInInterceptor(properties: Map<String, Any>?) : WSS4JInInterceptor(HashMap(properties)) {
    init {
        setProperty(ACTION, SAML_TOKEN_SIGNED)
    }

    override fun loadSignatureCrypto(requestData: RequestData): Crypto {
        val signatureProperties = Properties()
        signatureProperties.setProperty(
            "org.apache.wss4j.crypto.merlin.truststore.file", System.getProperty("javax.net.ssl.trustStore")
        )
        signatureProperties.setProperty(
            "org.apache.wss4j.crypto.merlin.truststore.password", System.getProperty("javax.net.ssl.trustStorePassword")
        )

        return CryptoFactory.getInstance(signatureProperties)
    }

    override fun handleMessage(msg: SoapMessage) {
        super.handleMessage(msg)

        val sc = msg[SecurityContext::class.java.name] as SecurityContext?
            ?: throw RuntimeException("Cannot get SecurityContext from SoapMessage")
        val samlTokenPrincipal = sc.userPrincipal as SAMLTokenPrincipal?
            ?: throw RuntimeException("Cannot get SAMLTokenPrincipal from SecurityContext")
        val assertion = samlTokenPrincipal.token.saml2

        if (logger.isDebugEnabled) {
            logger.debug("SAML Issuer: {}", assertion.issuer.value)
            logger.debug("SAML Subject: {}", assertion.subject.nameID.value)
        }

        try {
            samlAssertionToSubject(assertion)
        } catch (e: Exception) {
            logger.info("Login failed", e)
            val wsSecurityException = WSSecurityException(INVALID_SECURITY_TOKEN, e)
            throw Fault(wsSecurityException, wsSecurityException.faultCode)
        }
    }

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(SAMLInInterceptor::class.java)
    }
}
