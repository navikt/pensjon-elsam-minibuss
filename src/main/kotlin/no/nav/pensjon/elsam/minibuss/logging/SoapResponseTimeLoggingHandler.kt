package no.nav.pensjon.elsam.minibuss.logging

import jakarta.xml.ws.BindingProvider
import jakarta.xml.ws.handler.MessageContext
import jakarta.xml.ws.handler.soap.SOAPHandler
import jakarta.xml.ws.handler.soap.SOAPMessageContext
import jakarta.xml.ws.handler.soap.SOAPMessageContext.*
import net.logstash.logback.marker.Markers
import org.slf4j.LoggerFactory.*
import java.lang.System.nanoTime
import java.net.URI
import java.util.concurrent.TimeUnit
import javax.xml.namespace.QName

class SoapResponseTimeLoggingHandler : SOAPHandler<SOAPMessageContext> {
    private val logger = getLogger(javaClass)

    override fun getHeaders(): Set<QName> {
        return emptySet()
    }

    override fun handleMessage(context: SOAPMessageContext): Boolean {
        val outbound = context[MESSAGE_OUTBOUND_PROPERTY] as Boolean?
        if (outbound == true) {
            context["pen-request-start"] = nanoTime()
        } else {
            log(context)
        }

        return true
    }

    override fun handleFault(context: SOAPMessageContext?): Boolean {
        context?.run { log(this) }
        return false
    }

    private fun log(context: SOAPMessageContext) {
        val url = URI(context[BindingProvider.ENDPOINT_ADDRESS_PROPERTY] as String).toURL()
        val wsdlInterface = context[WSDL_INTERFACE] as QName
        val operation = context[WSDL_OPERATION] as QName
        val httpResponseCode = context[HTTP_RESPONSE_CODE]
        val requestSentTime = context["pen-request-start"] as Long?
        val executionTime = requestSentTime?.let { TimeUnit.NANOSECONDS.toMillis(nanoTime() - it ) } ?: 0

        logger.info(
            Markers.appendEntries(
                mapOf(
                    "x_upstream_host" to url.host,
                    "request_uri" to "${url.path}/${operation.localPart}",
                    "request_wsdl_interface" to wsdlInterface.localPart,
                    "request_wsdl_operation" to operation.localPart,
                    "response_code" to httpResponseCode,
                    "response_time" to executionTime,
                )
            ),
            "{} {} {} {} ms",
            "POST", // it's soap...
            "$url/${operation.localPart}",
            httpResponseCode,
            executionTime
        )
    }

    override fun close(context: MessageContext) = Unit
}
