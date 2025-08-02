package no.nav.pensjon.elsam.minibuss.logging

import io.opentelemetry.api.trace.Span
import org.apache.cxf.binding.soap.SoapMessage
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor
import org.apache.cxf.phase.Phase.PRE_INVOKE
import org.springframework.web.context.request.RequestAttributes.SCOPE_REQUEST
import org.springframework.web.context.request.RequestContextHolder


class TelemetrySoapInterceptor : AbstractSoapInterceptor(PRE_INVOKE) {
    override fun handleMessage(message: SoapMessage) {
        val context = Span.current().spanContext
        val attr = RequestContextHolder.getRequestAttributes()

        if (!context.isValid || attr == null) return

        attr.setAttribute("pensjon_elsam_minibuss_span_id", context.spanId, SCOPE_REQUEST)
        attr.setAttribute("pensjon_elsam_minibuss_trace_flags", context.traceFlags.asHex(), SCOPE_REQUEST)
        attr.setAttribute("pensjon_elsam_minibuss_trace_id", context.traceId, SCOPE_REQUEST)
    }
}
