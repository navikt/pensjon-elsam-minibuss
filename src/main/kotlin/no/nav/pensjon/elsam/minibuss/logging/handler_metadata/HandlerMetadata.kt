package no.nav.pensjon.elsam.minibuss.logging.handler_metadata

import org.slf4j.MDC
import org.springframework.web.context.request.RequestAttributes.*
import org.springframework.web.context.request.RequestContextHolder
import java.lang.reflect.Method

private const val HANDLER_TYPE = "handler_type"
private const val HANDLER_CLASS = "handler_class"
private const val HANDLER_METHOD = "handler_method"

/**
 * Adds Handler Metadata for use by the Access Log
 */
fun addHandlerMetaData(method: Method, type: String) {
    val requestAttributes = RequestContextHolder.getRequestAttributes()
    if (requestAttributes != null) {
        requestAttributes.setAttribute(HANDLER_TYPE, type, SCOPE_REQUEST)
        requestAttributes.setAttribute(HANDLER_CLASS, method.declaringClass.simpleName, SCOPE_REQUEST)
        requestAttributes.setAttribute(HANDLER_METHOD, method.name, SCOPE_REQUEST)

        MDC.put(HANDLER_TYPE, type)
        MDC.put(HANDLER_CLASS, method.declaringClass.simpleName)
        MDC.put(HANDLER_METHOD, method.name)
    }
}

fun clearHandlerMetaDataFromMDC() {
    MDC.remove(HANDLER_TYPE)
    MDC.remove(HANDLER_CLASS)
    MDC.remove(HANDLER_METHOD)
}
