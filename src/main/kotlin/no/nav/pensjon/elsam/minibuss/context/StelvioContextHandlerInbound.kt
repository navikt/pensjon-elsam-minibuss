package no.nav.pensjon.elsam.minibuss.context

import jakarta.xml.bind.JAXBContext
import jakarta.xml.ws.handler.MessageContext
import jakarta.xml.ws.handler.soap.SOAPHandler
import jakarta.xml.ws.handler.soap.SOAPMessageContext
import jakarta.xml.ws.handler.soap.SOAPMessageContext.*
import no.nav.pensjon.elsam.minibuss.context.RequestContextHolder.resetRequestContext
import no.nav.pensjon.elsam.minibuss.context.RequestContextHolder.setRequestContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.*
import javax.xml.namespace.QName

class StelvioContextHandlerInbound : SOAPHandler<SOAPMessageContext> {
    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    override fun getHeaders(): Set<QName> = PROCESSED_HEADERS_QNAME

    override fun close(context: MessageContext) = resetRequestContext()

    override fun handleFault(context: SOAPMessageContext): Boolean = true

    override fun handleMessage(context: SOAPMessageContext): Boolean {
        try {
            val outbound = context[MESSAGE_OUTBOUND_PROPERTY] as Boolean?
            if (outbound == false) {
                val headers = context.getHeaders(STELVIO_CONTEXT_QNAME, jaxbContext, true)
                val transactionId: String
                val userId: String
                val componentId: String

                if (headers.isNotEmpty()) {
                    val contextData = headers[0] as StelvioContextData

                    transactionId = contextData.correlationId?.takeIf { it.isNotBlank() } ?: UUID.randomUUID().toString()
                    userId = contextData.userId ?: "UNKNOWN_USER"
                    componentId = contextData.applicationId ?: "UNKNOWN_COMPONENT"
                } else {
                    transactionId = UUID.randomUUID().toString()
                    userId = "UNKNOWN_USER"
                    componentId = "UNKNOWN_COMPONENT"
                }

                setRequestContext(
                    transactionId = transactionId,
                    componentId = componentId,
                    userId = userId,
                )
            }
        } catch (e: Exception) {
            logger.error("Error propagating StelvioContext", e)
        }

        return true
    }

    companion object {
        private val STELVIO_CONTEXT_QNAME = QName("http://www.nav.no/StelvioContextPropagation", "StelvioContext")
        private val PROCESSED_HEADERS_QNAME = setOf(STELVIO_CONTEXT_QNAME)

        private var jaxbContext: JAXBContext = JAXBContext.newInstance(StelvioContextData::class.java)
    }
}
