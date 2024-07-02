package no.nav.pensjon.elsam.minibuss.configuration

import jakarta.jws.WebService
import jakarta.xml.ws.Endpoint
import nav_cons_elsam_np_tjenestepensjon.no.nav.inf.nptjenestepensjon.NPTjenestepensjon
import no.nav.elsam.registreretpforhold.v0_1.RegistrereTPForhold
import no.nav.elsam.tpsamordningregistrering.v1_0.TPSamordningRegistrering
import no.nav.pensjon.elsam.minibuss.context.StelvioContextHandlerInbound
import no.nav.pensjon.elsam.minibuss.security.SAMLInInterceptor
import org.apache.cxf.Bus
import org.apache.cxf.jaxws.EndpointImpl
import org.apache.wss4j.common.ConfigurationConstants.SIG_SUBJECT_CERT_CONSTRAINTS
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.Locale.*

@Configuration
class SoapEndpointConfiguration(
    private val bus: Bus,
    @Value("\${webservice.sigSubjectCertConstraints}") private val sigSubjectCertConstraints: String,
    @Value("\${npTjenestepensjon.authorizedUsers}") private val npTjenestepensjonAuthorizedUsers: Set<String>,
    @Value("\${registrereTPForhold.authorizedUsers}") private val registrereTPForholdAuthorizedUsers: Set<String>,
    @Value("\${tpSamordningRegistrering.authorizedUsers}") private val tpSamordningRegistreringAuthorizedUsers: Set<String>,
) {
    @Bean
    fun npTjenestepensjonWSEndpointExport(npTjenestepensjon: NPTjenestepensjon): Endpoint =
        createEndpoint(npTjenestepensjon, npTjenestepensjonAuthorizedUsers)

    @Bean
    fun registrereTPForholdWSEndpointExport(registrereTPForhold: RegistrereTPForhold): Endpoint =
        createEndpoint(registrereTPForhold, registrereTPForholdAuthorizedUsers)

    @Bean
    fun tpSamordningRegistreringWSEndpointExport(tpSamordningRegistrering: TPSamordningRegistrering): Endpoint =
        createEndpoint(tpSamordningRegistrering, tpSamordningRegistreringAuthorizedUsers)

    private fun createEndpoint(
        wsEndpoint: Any,
        authorizedUsers: Set<String>,
    ): EndpointImpl {
        val serviceName = wsEndpoint.javaClass.getAnnotation(WebService::class.java)?.serviceName
            ?: throw IllegalArgumentException("No @WebService found on given implementation [" + wsEndpoint.javaClass.simpleName + "], check configuration!")

        val endpoint = EndpointImpl(bus, wsEndpoint).apply {
            properties = mapOf(
                "allowNonMatchingToDefaultSoapAction" to true,
            )
        }

        endpoint.inInterceptors = listOf(
            SAMLInInterceptor(
                mapOf(SIG_SUBJECT_CERT_CONSTRAINTS to sigSubjectCertConstraints),
                authorizedUsers.map { it.lowercase(getDefault()) }.toSet(),
            )
        )

        endpoint.handlers = listOf(StelvioContextHandlerInbound())

        endpoint.publish("/$serviceName")

        return endpoint
    }
}
