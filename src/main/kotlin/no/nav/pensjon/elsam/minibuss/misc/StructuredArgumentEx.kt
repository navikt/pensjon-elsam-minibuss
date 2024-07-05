package no.nav.pensjon.elsam.minibuss.misc

import net.logstash.logback.argument.StructuredArguments

fun entries(vararg entries: Pair<String, Any?>) = StructuredArguments.e(mapOf(*entries))
