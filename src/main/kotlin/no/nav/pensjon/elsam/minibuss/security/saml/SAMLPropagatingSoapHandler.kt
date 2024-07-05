package no.nav.pensjon.elsam.minibuss.security.saml

import jakarta.xml.soap.SOAPElement
import jakarta.xml.soap.SOAPException
import jakarta.xml.soap.SOAPFactory
import jakarta.xml.ws.handler.MessageContext
import jakarta.xml.ws.handler.soap.SOAPHandler
import jakarta.xml.ws.handler.soap.SOAPMessageContext
import jakarta.xml.ws.handler.soap.SOAPMessageContext.MESSAGE_OUTBOUND_PROPERTY
import org.slf4j.Logger
import org.slf4j.LoggerFactory.*
import org.springframework.web.context.request.RequestAttributes.SCOPE_REQUEST
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import org.w3c.dom.Element
import javax.xml.namespace.QName

@Suppress("HttpUrlsUsage")
class SAMLPropagatingSoapHandler : SOAPHandler<SOAPMessageContext> {
    private val logger: Logger = getLogger(javaClass)

    override fun getHeaders(): Set<QName> {
        return PROCESSED_HEADERS_QNAME
    }

    override fun handleMessage(context: SOAPMessageContext): Boolean {
        val outbound = context[MESSAGE_OUTBOUND_PROPERTY] as Boolean?
        if (outbound == true) {
            try {
                val envelope = context.message.soapPart.envelope
                val header = envelope.header ?: envelope.addHeader()
                header.addChildElement(generateSecurityHeaderWithSAML())
            } catch (e: SOAPException) {
                logger.info("Error with the SOAP envelope/header!", e)
                throw SecurityException(e)
            }
        }

        return true
    }

    private fun generateSecurityHeaderWithSAML(): SOAPElement {
        try {
            val soapFactory = SOAPFactory.newInstance()

            val secHeader = soapFactory.createElement(soapFactory.createName("Security", "wsse", SECURITY_URL))

            val currentRequestAttributes = RequestContextHolder.currentRequestAttributes() as ServletRequestAttributes
            val marshall: Element = currentRequestAttributes.getAttribute("minibuss-incoming-assertion", SCOPE_REQUEST) as Element

            secHeader.appendChild(
                secHeader.ownerDocument.importNode(
                    marshall,
                    true
                )
            )

            secHeader.addAttribute(
                soapFactory.createName("mustUnderstand", "soapenv", "http://www.w3.org/2003/05/soap-envelope"), "1"
            )

            return secHeader
        } catch (e: Exception) {
            logger.info("Error generating security header", e)
            throw IllegalArgumentException("Error generating Security-element with SAML", e)
        }
    }

    override fun handleFault(soapMessageContext: SOAPMessageContext): Boolean = false

    override fun close(messageContext: MessageContext) = Unit

    companion object {
        private const val SECURITY_URL = "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd"
        private val SECURITY_QNAME = QName("http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd", "Security")
        private val PROCESSED_HEADERS_QNAME = setOf(SECURITY_QNAME)
    }
}
