package no.nav.pensjon.elsam.minibuss.context

import jakarta.xml.bind.annotation.XmlElement
import jakarta.xml.bind.annotation.XmlRootElement

@XmlRootElement(name = "StelvioContext", namespace = "http://www.nav.no/StelvioContextPropagation")
class StelvioContextData(
    @get:XmlElement(name = "applicationId")
    val applicationId: String? = null,
    @get:XmlElement(name = "correlationId")
    var correlationId: String? = null,
    @get:XmlElement(name = "userId")
    val userId: String? = null,
)
