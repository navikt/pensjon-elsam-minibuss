package no.nav.pensjon.elsam.minibuss

import jakarta.jws.WebService
import jakarta.xml.ws.Endpoint
import nav_cons_elsam_np_tjenestepensjon.no.nav.inf.nptjenestepensjon.NPTjenestepensjon
import no.nav.elsam.registreretpforhold.v0_1.RegistrereTPForhold
import no.nav.elsam.tpsamordningregistrering.v1_0.TPSamordningRegistrering
import org.apache.cxf.Bus
import org.apache.cxf.jaxws.EndpointImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CxfConfiguration(
    private val bus: Bus,
) {
    @Bean
    fun npTjenestepensjonWSEndpointExport(npTjenestepensjon: NPTjenestepensjon): Endpoint =
        createEndpoint(npTjenestepensjon)

    @Bean
    fun registrereTPForholdWSEndpointExport(registrereTPForhold: RegistrereTPForhold): Endpoint =
        createEndpoint(registrereTPForhold)

    @Bean
    fun tpSamordningRegistreringWSEndpointExport(tpSamordningRegistrering: TPSamordningRegistrering): Endpoint =
        createEndpoint(tpSamordningRegistrering)

    private fun createEndpoint(
        wsEndpoint: Any,
    ): EndpointImpl {
        val serviceName = wsEndpoint.javaClass.getAnnotation(WebService::class.java)?.serviceName
            ?: throw IllegalArgumentException("No @WebService found on given implementation [" + wsEndpoint.javaClass.simpleName + "], check configuration!")

        val endpoint = EndpointImpl(bus, wsEndpoint).apply {
            properties = mapOf(
                "allowNonMatchingToDefaultSoapAction" to true,
            )
        }

        endpoint.publish("/$serviceName")

        return endpoint
    }
}
