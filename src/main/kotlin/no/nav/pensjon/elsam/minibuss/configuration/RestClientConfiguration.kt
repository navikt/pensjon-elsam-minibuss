package no.nav.pensjon.elsam.minibuss.configuration

import no.nav.pensjon.elsam.minibuss.logging.LoggingClientHttpRequestInterceptor
import no.nav.pensjon.elsam.minibuss.security.azuread.AzureAdClientCredentialsService
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpRequest
import org.springframework.http.client.ClientHttpRequestExecution
import org.springframework.http.client.ClientHttpRequestInterceptor
import org.springframework.http.client.ClientHttpResponse
import org.springframework.web.client.RestClient

@Configuration
class RestClientConfiguration {
    @Bean
    fun tpRestClient(
        @Value("\${tp.base.url}") baseUrl: String,
        @Value("\${tp.scope}") scope: List<String>,
        azureAdClientCredentialsService: AzureAdClientCredentialsService,
    ): RestClient = RestClient.builder()
        .baseUrl(baseUrl)
        .requestInterceptor(LoggingClientHttpRequestInterceptor())
        .requestInterceptor { request, body, execution ->
            request.headers.setBearerAuth(azureAdClientCredentialsService.accessToken(scope))
            execution.execute(request, body)
        }
        .build()
}
