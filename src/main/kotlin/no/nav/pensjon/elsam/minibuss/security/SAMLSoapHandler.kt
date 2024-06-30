package no.nav.pensjon.elsam.minibuss.security

import jakarta.xml.soap.SOAPElement
import jakarta.xml.soap.SOAPException
import jakarta.xml.soap.SOAPFactory
import jakarta.xml.ws.handler.MessageContext
import jakarta.xml.ws.handler.soap.SOAPHandler
import jakarta.xml.ws.handler.soap.SOAPMessageContext
import jakarta.xml.ws.handler.soap.SOAPMessageContext.MESSAGE_OUTBOUND_PROPERTY
import org.slf4j.Logger
import org.slf4j.LoggerFactory.*
import org.w3c.dom.Document
import org.xml.sax.InputSource
import org.xml.sax.SAXException
import java.io.IOException
import java.io.StringReader
import javax.xml.namespace.QName
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.parsers.ParserConfigurationException

@Suppress("HttpUrlsUsage")
class SAMLSoapHandler(private val stsClient: StsClient) : SOAPHandler<SOAPMessageContext> {
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

                header.addChildElement(generateSecurityHeaderWithSAML(stsClient.getSystemSaml()))
            } catch (e: SOAPException) {
                logger.info("Error with the SOAP envelope/header!", e)
                throw SecurityException(e)
            }
        }

        return true
    }

    private fun generateSecurityHeaderWithSAML(samlToken: SAMLResponse): SOAPElement {
        try {
            val soapFactory = SOAPFactory.newInstance()

            val secHeader = soapFactory.createElement(soapFactory.createName("Security", "wsse", SECURITY_URL))

            secHeader.appendChild(
                secHeader.ownerDocument.importNode(
                    convertStringToDocument(samlToken.decodedToken).firstChild,
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

        private fun convertStringToDocument(xmlStr: String): Document =
            try {
                StringReader(xmlStr).use {
                    DocumentBuilderFactory.newInstance().apply {
                        isNamespaceAware = true
                    }.newDocumentBuilder().parse(InputSource(it))
                }
            } catch (e: ParserConfigurationException) {
                throw IllegalArgumentException("Error parsing SAML assertion", e)
            } catch (e: SAXException) {
                throw IllegalArgumentException("Error parsing SAML assertion", e)
            } catch (e: IOException) {
                throw IllegalArgumentException("Error parsing SAML assertion", e)
            }
    }
}
