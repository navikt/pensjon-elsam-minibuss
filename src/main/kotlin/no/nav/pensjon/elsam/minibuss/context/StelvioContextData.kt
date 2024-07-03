package no.nav.pensjon.elsam.minibuss.context

import jakarta.xml.bind.annotation.XmlElement
import jakarta.xml.bind.annotation.XmlRootElement

@XmlRootElement(name = "StelvioContext", namespace = "http://www.nav.no/StelvioContextPropagation")
@Suppress("HttpUrlsUsage")
data class StelvioContextData(
    @get:XmlElement(name = "applicationId")
    var applicationId: String? = null,
    @get:XmlElement(name = "correlationId")
    var correlationId: String? = null,
    @get:XmlElement(name = "languageId")
    var languageId: String? = null,
    @get:XmlElement(name = "userId")
    var userId: String? = null,
)
