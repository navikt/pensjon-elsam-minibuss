package no.nav.pensjon.elsam.minibuss.configuration

import no.nav.pensjon.elsam.minibuss.logging.LoggingClientHttpRequestInterceptor
import no.nav.pensjon.elsam.minibuss.security.azuread.AzureAdClientCredentialsService
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestClient

@Configuration
class RestClientConfiguration {
    @Bean
    fun azureRestClient(): RestClient = RestClient.builder()
        .requestInterceptor(LoggingClientHttpRequestInterceptor())
        .build()

    @Bean
    fun tpRestClient(
        @Value("\${tp.base.url}") baseUrl: String,
        @Value("\${tp.scope}") scope: Set<String>,
        azureAdClientCredentialsService: AzureAdClientCredentialsService,
    ): RestClient = RestClient.builder()
        .baseUrl(baseUrl)
        .requestInterceptor(LoggingClientHttpRequestInterceptor())
        .requestInterceptor { request, body, execution ->
            request.headers.setBearerAuth(azureAdClientCredentialsService.accessToken(scope))
            execution.execute(request, body)
        }
        .build()

    @Bean
    fun samRestClient(
        @Value("\${sam.base.url}") baseUrl: String,
        @Value("\${sam.scope}") scope: Set<String>,
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
