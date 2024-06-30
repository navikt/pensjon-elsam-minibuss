package no.nav.pensjon.elsam.minibuss.context

import jakarta.xml.bind.JAXBContext
import jakarta.xml.bind.JAXBException
import jakarta.xml.soap.SOAPException
import jakarta.xml.ws.handler.MessageContext
import jakarta.xml.ws.handler.soap.SOAPHandler
import jakarta.xml.ws.handler.soap.SOAPMessageContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.xml.namespace.QName
import javax.xml.transform.dom.DOMResult

class StelvioContextHandlerOutbound : SOAPHandler<SOAPMessageContext> {
    private val logger: Logger = LoggerFactory.getLogger(this.javaClass)

    override fun handleMessage(context: SOAPMessageContext): Boolean {
        val outbound = context[MessageContext.MESSAGE_OUTBOUND_PROPERTY] as Boolean?
        if (outbound == true) {
            val stelvioContextData = currentStelvioContextData
            try {
                val envelope = context.message.soapPart.envelope
                var header = envelope.header
                if (header == null) {
                    header = envelope.addHeader()
                }
                val jaxbMarshaller = jaxbContext.createMarshaller()
                jaxbMarshaller.marshal(stelvioContextData, DOMResult(header))
            } catch (e: SOAPException) {
                logger.error("Error with the SOAP envelope/header: ", e)
            } catch (e: JAXBException) {
                logger.error("Error while marshalling the stelvioContextData element: ", e)
            }
        }
        return true
    }

    private val currentStelvioContextData: StelvioContextData
        get() {
            val requestContext = RequestContextHolder.currentRequestContext()
            val stelvioContextData = StelvioContextData(
                applicationId = "TPLEV",
                correlationId = requestContext?.transactionId,
                userId = requestContext?.userId
            )
            return stelvioContextData
        }

    override fun handleFault(context: SOAPMessageContext): Boolean = true

    override fun close(context: MessageContext) = Unit

    override fun getHeaders(): Set<QName> = PROCESSED_HEADERS_QNAME

    companion object {
        private val STELVIO_CONTEXT_QNAME: QName = QName(
            "http://www.nav.no/StelvioContextPropagation",
            "StelvioContext"
        )

        private val PROCESSED_HEADERS_QNAME = setOf(STELVIO_CONTEXT_QNAME)

        private val jaxbContext: JAXBContext = JAXBContext.newInstance(StelvioContextData::class.java)
    }
}
