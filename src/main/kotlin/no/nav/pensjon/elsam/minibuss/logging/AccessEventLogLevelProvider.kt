package no.nav.pensjon.elsam.minibuss.logging

import ch.qos.logback.access.common.spi.IAccessEvent
import com.fasterxml.jackson.core.JsonGenerator
import net.logstash.logback.composite.AbstractFieldJsonProvider

class AccessEventLogLevelProvider : AbstractFieldJsonProvider<IAccessEvent>() {
    override fun writeTo(generator: JsonGenerator, event: IAccessEvent) {
        generator.writeObjectField(
            "level", when (event.statusCode) {
                in 100..399 -> "INFO"
                in 400..499 -> "WARN"
                in 500..599 -> "ERROR"
                else -> "UNKNOWN"
            }
        )
    }
}
