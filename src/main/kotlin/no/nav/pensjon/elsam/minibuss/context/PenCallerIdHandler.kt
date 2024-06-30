package no.nav.pensjon.elsam.minibuss.context

import jakarta.xml.soap.SOAPException
import jakarta.xml.soap.SOAPFactory
import jakarta.xml.ws.handler.MessageContext
import jakarta.xml.ws.handler.soap.SOAPHandler
import jakarta.xml.ws.handler.soap.SOAPMessageContext
import no.nav.pensjon.elsam.minibuss.context.RequestContextHolder.currentRequestContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory.*
import java.util.*
import javax.xml.namespace.QName

class PenCallerIdHandler : SOAPHandler<SOAPMessageContext> {
    private val logger: Logger = getLogger(javaClass)

    override fun handleMessage(context: SOAPMessageContext): Boolean {
        val outbound = context[SOAPMessageContext.MESSAGE_OUTBOUND_PROPERTY] as Boolean?
        if (outbound == true) {
            try {
                val envelope = context.message.soapPart.envelope
                val header = envelope.header ?: envelope.addHeader()

                val soapFactory = SOAPFactory.newInstance()

                header.addChildElement(
                    soapFactory.createElement(
                        soapFactory.createName(
                            CALL_ID,
                            "",
                            URI_NO_NAV_APPLIKASJONSRAMMEVERK
                        )
                    )
                        .addTextNode(currentRequestContext()?.transactionId ?: UUID.randomUUID().toString())
                )
            } catch (e: SOAPException) {
                logger.error("Error with the SOAP envelope/header!", e)
                return false
            } catch (e: Exception) {
                logger.error("Error when creating CallId-header", e)
                return false
            }
        }
        return true
    }

    override fun handleFault(context: SOAPMessageContext): Boolean = true

    override fun getHeaders(): Set<QName> = PROCESSED_HEADERS_QNAME

    override fun close(context: MessageContext) = Unit

    companion object {
        private const val URI_NO_NAV_APPLIKASJONSRAMMEVERK = "uri:no.nav.applikasjonsrammeverk"
        private const val CALL_ID = "callId"

        private val header: QName = QName(URI_NO_NAV_APPLIKASJONSRAMMEVERK, CALL_ID)

        private val PROCESSED_HEADERS_QNAME = setOf(header)
    }
}
