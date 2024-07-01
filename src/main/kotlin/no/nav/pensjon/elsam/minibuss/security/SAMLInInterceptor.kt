package no.nav.pensjon.elsam.minibuss.security

import org.apache.cxf.binding.soap.SoapMessage
import org.apache.cxf.interceptor.Fault
import org.apache.cxf.security.SecurityContext
import org.apache.cxf.ws.security.wss4j.WSS4JInInterceptor
import org.apache.wss4j.common.ConfigurationConstants.ACTION
import org.apache.wss4j.common.ConfigurationConstants.SAML_TOKEN_SIGNED
import org.apache.wss4j.common.crypto.Crypto
import org.apache.wss4j.common.crypto.CryptoFactory
import org.apache.wss4j.common.ext.WSSecurityException
import org.apache.wss4j.common.ext.WSSecurityException.ErrorCode.INVALID_SECURITY
import org.apache.wss4j.common.ext.WSSecurityException.ErrorCode.INVALID_SECURITY_TOKEN
import org.apache.wss4j.common.principal.SAMLTokenPrincipal
import org.apache.wss4j.dom.handler.RequestData
import org.slf4j.Logger
import org.slf4j.LoggerFactory.*
import java.lang.System.getProperty
import java.util.*

class SAMLInInterceptor(properties: Map<String, Any>, private val authorizedUsers: Set<String>) : WSS4JInInterceptor(HashMap(properties)) {
    private val logger: Logger = getLogger(javaClass)

    init {
        setProperty(ACTION, SAML_TOKEN_SIGNED)
    }

    override fun loadSignatureCrypto(requestData: RequestData): Crypto {
        return CryptoFactory.getInstance(
            mapOf(
                "org.apache.wss4j.crypto.merlin.truststore.file" to getProperty("javax.net.ssl.trustStore"),
                "org.apache.wss4j.crypto.merlin.truststore.password" to getProperty("javax.net.ssl.trustStorePassword")
            ).toProperties()
        )
    }

    override fun handleMessage(msg: SoapMessage) {
        super.handleMessage(msg)

        val sc = msg[SecurityContext::class.java.name] as SecurityContext?
            ?: throw RuntimeException("Cannot get SecurityContext from SoapMessage")
        val samlTokenPrincipal = sc.userPrincipal as SAMLTokenPrincipal?
            ?: throw RuntimeException("Cannot get SAMLTokenPrincipal from SecurityContext")
        val assertion = samlTokenPrincipal.token.saml2

        val nameID = assertion.subject.nameID.value

        if (nameID == null) {
            logger.warn("SAML Token mangler nameID")
            val wsSecurityException = WSSecurityException(INVALID_SECURITY_TOKEN)
            throw Fault(wsSecurityException, wsSecurityException.faultCode)
        }

        if (!authorizedUsers.contains(nameID)) {
            logger.warn("Bruker '{}' har ikke tilgang", nameID)
            val wsSecurityException = WSSecurityException(INVALID_SECURITY)
            throw Fault(wsSecurityException, wsSecurityException.faultCode)
        }
    }
}
