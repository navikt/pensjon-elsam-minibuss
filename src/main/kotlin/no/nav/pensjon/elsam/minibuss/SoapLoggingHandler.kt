package no.nav.pensjon.elsam.minibuss

import jakarta.xml.soap.SOAPException
import jakarta.xml.ws.handler.MessageContext
import jakarta.xml.ws.handler.soap.SOAPHandler
import jakarta.xml.ws.handler.soap.SOAPMessageContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.ByteArrayOutputStream
import java.io.IOException
import javax.xml.namespace.QName

class SoapLoggingHandler : SOAPHandler<SOAPMessageContext> {
    private val logger: Logger = LoggerFactory.getLogger(javaClass)

    override fun getHeaders(): Set<QName>? = null

    override fun handleMessage(context: SOAPMessageContext): Boolean {
        logSoapMessage(context)
        return true
    }

    override fun handleFault(context: SOAPMessageContext): Boolean {
        logSoapMessage(context)
        return true
    }

    override fun close(context: MessageContext) = Unit

    private fun logSoapMessage(context: SOAPMessageContext) {
        if (logger.isDebugEnabled) {
            val outboundProperty = context[MessageContext.MESSAGE_OUTBOUND_PROPERTY] as Boolean?
            val message = context.message

            if (outboundProperty != null && message != null) {
                try {
                    ByteArrayOutputStream().use { baos ->
                        message.writeTo(baos)
                        logger.debug("{} message:\n{}", if (outboundProperty) "Outbound" else "Inbound", baos)
                    }
                } catch (e: SOAPException) {
                    logger.error("Exception in handler", e)
                } catch (e: IOException) {
                    logger.error("Exception in handler", e)
                }
            }
        }
    }
}
