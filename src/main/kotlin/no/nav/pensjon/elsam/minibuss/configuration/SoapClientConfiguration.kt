package no.nav.pensjon.elsam.minibuss.configuration

import no.nav.pensjon.elsam.minibuss.context.PenCallerIdHandler
import no.nav.pensjon.elsam.minibuss.security.saml.StsClient
import no.nav.pensjon.elsam.minibuss.context.StelvioContextHandlerOutbound
import no.nav.pensjon.elsam.minibuss.logging.SoapLoggingHandler
import no.nav.pensjon.elsam.minibuss.logging.SoapResponseTimeLoggingHandler
import no.nav.pensjon.elsam.minibuss.security.saml.SAMLPropagatingSoapHandler
import no.nav.pensjon.elsam.minibuss.security.saml.SAMLSoapHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

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
}
