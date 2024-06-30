package no.nav.pensjon.elsam.minibuss

import jakarta.xml.ws.handler.Handler
import jakarta.xml.ws.handler.soap.SOAPMessageContext
import nav_cons_pen_psak_samhandler.no.nav.inf.PSAKSamhandler
import nav_cons_sto_sam_tjenestepensjon.no.nav.inf.SAMTjenestepensjon
import nav_lib_frg.no.nav.lib.frg.inf.tjenestepensjon.Tjenestepensjon
import nav_lib_sto.no.nav.lib.sto.inf.samordning.Samordning
import no.nav.pensjon.elsam.minibuss.security.SAMLSoapHandler
import no.nav.pensjon.elsam.minibuss.security.StsClient
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.xml.namespace.QName

@Configuration
class SoapClientConfiguration {
    @Bean
    fun handlers(stsClient: StsClient) = listOf(
        SAMLSoapHandler(stsClient),
    )

    @Bean
    fun psakSamhandler(
        @Value("\${tjenestebuss.url}/nav-cons-pen-psak-samhandlerWeb/sca/PSAKSamhandlerWSEXP") address: String,
        handlers: List<Handler<SOAPMessageContext>>,
    ): PSAKSamhandler = createProxy(
        address = address,
        namespace = "http://nav-cons-pen-psak-samhandler/no/nav/inf/Binding",
        localPart = "PSAKSamhandlerWSEXP_PSAKSamhandlerHttpService",
        handlers = handlers,
    )

    @Bean
    fun samordning(
        handlers: List<Handler<SOAPMessageContext>>,
    ): Samordning = createProxy(
        address = "http://localhost/samordning",
        namespace = "http://nav-cons-pen-psak-samhandler/no/nav/inf/Binding",
        localPart = "PSAKSamhandlerWSEXP_PSAKSamhandlerHttpService",
        handlers = handlers,
    )

    @Bean
    fun samTjenestepensjon(
        @Value("\${tjenestebuss.url}/nav-cons-sto-sam-tjenestepensjonWeb/sca/SAMTjenestepensjonWSEXP") address: String,
        handlers: List<Handler<SOAPMessageContext>>,
    ): SAMTjenestepensjon = createProxy(
        address = address,
        namespace = "http://nav-cons-sto-sam-tjenestepensjon/no/nav/inf/Binding",
        localPart = "SAMTjenestepensjonWSEXP_SAMTjenestepensjonHttpService",
        handlers = handlers,
    )

    @Bean
    fun tjenestepensjon(
        handlers: List<Handler<SOAPMessageContext>>,
        ): Tjenestepensjon = createProxy(
        address = "http://localhost/tjenestepensjon",
        namespace = "http://nav-cons-pen-psak-samhandler/no/nav/inf/Binding",
        localPart = "PSAKSamhandlerWSEXP_PSAKSamhandlerHttpService",
        handlers = handlers,
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
