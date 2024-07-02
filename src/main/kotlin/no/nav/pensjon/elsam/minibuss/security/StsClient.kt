package no.nav.pensjon.elsam.minibuss.security

import no.nav.pensjon.elsam.minibuss.logging.LoggingClientHttpRequestInterceptor
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.client.support.BasicAuthenticationInterceptor
import org.springframework.stereotype.Component
import org.springframework.web.client.RestClient

@Component
class StsClient(
    @Value("\${sts.url}") stsUrl: String,
    @Value("\${SERVICEUSER_USERNAME}") username: String,
    @Value("\${SERVICEUSER_PASSWORD}") password: String,
) {
    private val restClient = RestClient.builder()
        .requestInterceptor(LoggingClientHttpRequestInterceptor())
        .requestInterceptor(BasicAuthenticationInterceptor(username, password))
        .baseUrl(stsUrl)
        .build()

    fun getSystemSaml(): SAMLResponse =
        restClient.get()
            .uri("/rest/v1/sts/samltoken")
            .retrieve()
            .body(SAMLResponse::class.java)
            ?: throw RuntimeException("Failed to get STS response")
}
