package no.nav.pensjon.elsam.minibuss.configuration

import jakarta.xml.ws.handler.Handler
import jakarta.xml.ws.handler.soap.SOAPMessageContext
import nav_cons_pen_psak_samhandler.no.nav.inf.PSAKSamhandler
import nav_cons_sto_sam_tjenestepensjon.no.nav.inf.SAMTjenestepensjon
import nav_lib_frg.no.nav.lib.frg.inf.tjenestepensjon.Tjenestepensjon
import nav_lib_sto.no.nav.lib.sto.inf.samordning.Samordning
import no.nav.elsam.registreretpforhold.v0_1.RegistrereTPForhold
import no.nav.elsam.tpsamordningregistrering.v1_0.TPSamordningRegistrering
import no.nav.pensjon.elsam.minibuss.context.PenCallerIdHandler
import no.nav.pensjon.elsam.minibuss.security.saml.StsClient
import no.nav.pensjon.elsam.minibuss.context.StelvioContextHandlerOutbound
import no.nav.pensjon.elsam.minibuss.logging.SoapLoggingHandler
import no.nav.pensjon.elsam.minibuss.logging.SoapResponseTimeLoggingHandler
import no.nav.pensjon.elsam.minibuss.security.saml.SAMLPropagatingSoapHandler
import no.nav.pensjon.elsam.minibuss.security.saml.SAMLSoapHandler
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.xml.namespace.QName

@Configuration
class SoapClientConfiguration {
    @Bean
    fun srvPensjonHandlers(stsClient: StsClient) = listOf(
        PenCallerIdHandler(),
        StelvioContextHandlerOutbound(),
        SoapLoggingHandler(),  // Don't include security token in log output
        SAMLSoapHandler(stsClient),
        SoapResponseTimeLoggingHandler()
    )

    @Bean
    fun samlPropagatingHandlers(stsClient: StsClient) = listOf(
        PenCallerIdHandler(),
        StelvioContextHandlerOutbound(),
        SoapLoggingHandler(),  // Don't include security token in log output
        SAMLPropagatingSoapHandler(),
        SoapResponseTimeLoggingHandler()
    )

    @Bean
    fun busRegistrereTPForhold(
        @Value("\${tjenestebuss.url}/nav-cons-elsam-tptilb-registreretpforholdV0_1Web/sca/RegistrereTPForholdWSEXP") address: String,
        samlPropagatingHandlers: List<Handler<SOAPMessageContext>>,
    ): RegistrereTPForhold = createProxy(
        address = address,
        namespace = "http://nav.no/elsam/registreretpforhold/V0_1/Binding",
        localPart = "RegistrereTPForholdWSEXP_RegistrereTPForholdHttpService",
        handlers = samlPropagatingHandlers,
    )

    @Bean
    fun busTPSamordningRegistrering(
        @Value("\${tjenestebuss.url}/nav-cons-elsam-tptilb-tpsamordningregistreringWeb/sca/TPSamordningRegistreringV1_0WSEXP") address: String,
        samlPropagatingHandlers: List<Handler<SOAPMessageContext>>,
    ): TPSamordningRegistrering = createProxy(
        address = address,
        namespace = "http://nav.no/elsam/tpsamordningregistrering/V1_0/Binding",
        localPart = "TPSamordningRegistreringV1_0WSEXP_TPSamordningRegistreringHttpService",
        handlers = samlPropagatingHandlers,
    )

    @Bean
    fun psakSamhandler(
        @Value("\${tjenestebuss.url}/nav-cons-pen-psak-samhandlerWeb/sca/PSAKSamhandlerWSEXP") address: String,
        srvPensjonHandlers: List<Handler<SOAPMessageContext>>,
    ): PSAKSamhandler = createProxy(
        address = address,
        namespace = "http://nav-cons-pen-psak-samhandler/no/nav/inf/Binding",
        localPart = "PSAKSamhandlerWSEXP_PSAKSamhandlerHttpService",
        handlers = srvPensjonHandlers,
    )

    @Bean
    fun samordning(
        samlPropagatingHandlers: List<Handler<SOAPMessageContext>>,
    ): Samordning = createProxy(
        address = "http://localhost/samordning",
        namespace = "http://nav-cons-pen-psak-samhandler/no/nav/inf/Binding",
        localPart = "PSAKSamhandlerWSEXP_PSAKSamhandlerHttpService",
        handlers = samlPropagatingHandlers,
    )

    @Bean
    fun samTjenestepensjon(
        @Value("\${tjenestebuss.url}/nav-cons-sto-sam-tjenestepensjonWeb/sca/SAMTjenestepensjonWSEXP") address: String,
        srvPensjonHandlers: List<Handler<SOAPMessageContext>>,
    ): SAMTjenestepensjon = createProxy(
        address = address,
        namespace = "http://nav-cons-sto-sam-tjenestepensjon/no/nav/inf/Binding",
        localPart = "SAMTjenestepensjonWSEXP_SAMTjenestepensjonHttpService",
        handlers = srvPensjonHandlers,
    )

    @Bean
    fun tjenestepensjon(
        samlPropagatingHandlers: List<Handler<SOAPMessageContext>>,
    ): Tjenestepensjon = createProxy(
        address = "http://localhost/tjenestepensjon",
        namespace = "http://nav-cons-pen-psak-samhandler/no/nav/inf/Binding",
        localPart = "PSAKSamhandlerWSEXP_PSAKSamhandlerHttpService",
        handlers = samlPropagatingHandlers,
    )

    private final inline fun <reified T> createProxy(
        address: String,
        namespace: String,
        localPart: String,
        handlers: List<Handler<SOAPMessageContext>>,
    ): T {
        val factory = JaxWsProxyFactoryBean()
        factory.address = address
        factory.serviceClass = T::class.java
        factory.serviceName = QName(namespace, localPart)
        factory.handlers = handlers
        return factory.create() as T
    }
}
