package no.nav.pensjon.elsam.minibuss.logging

import com.fasterxml.jackson.core.JsonStreamContext
import net.logstash.logback.mask.ValueMasker
import no.nav.pensjon.elsam.minibuss.misc.redact

class PidValueMasker : ValueMasker {
    override fun mask(context: JsonStreamContext, value: Any): Any {
        if (value is CharSequence) {
            return redact(value.toString())
        }
        return value
    }
}
