package no.nav.pensjon.elsam.minibuss.security.azuread

import net.logstash.logback.marker.RawJsonAppendingMarker
import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED
import org.springframework.stereotype.Component
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.client.*

@Component
class AzureAdClientCredentialsService(
    @Value("\${AZURE_APP_CLIENT_ID}") private val clientId: String,
    @Value("\${AZURE_APP_CLIENT_SECRET}") private val clientSecret: String,
    @Value("\${AZURE_OPENID_CONFIG_TOKEN_ENDPOINT}") private val tokenEndpoint: String,
) {
    private val logger: Logger = getLogger(AzureAdClientCredentialsService::class.java)
    private val restClient: RestClient = RestClient.builder().build()

    fun accessToken(scope: List<String>) = fetch(scope).accessToken

    fun fetch(scope: List<String>): ClientCredentialsTokenResponse = try {
        restClient.post()
            .uri(tokenEndpoint)
            .contentType(APPLICATION_FORM_URLENCODED)
            .body(
                    LinkedMultiValueMap<String, String>().apply {
                        add("grant_type", "client_credentials")
                        add("client_id", clientId)
                        add("client_secret", clientSecret)
                        add("scope", scope.joinToString(" "))
                    }
            )
            .retrieve()
            .body()
    } catch (e: HttpClientErrorException) {
        if (e.statusCode == NOT_FOUND) {
            logger.error(
                RawJsonAppendingMarker("error_response", e.responseBodyAsString),
                "Got 404 when trying to fetch token using endpoint $tokenEndpoint"
            )
            throw ClientCredentialsException("Unable to fetch token, wrong URL", e)
        } else {
            logger.error(
                RawJsonAppendingMarker("error_response", e.responseBodyAsString),
                "Failed to fetch token for scope=${scope.joinToString(" ")}, got status=${e.statusText}, message=${e.message}"
            )
            throw ClientCredentialsException("Unable to fetch token", e)
        }
    } catch (e: HttpServerErrorException) {
        logger.error(
            RawJsonAppendingMarker("error_response", e.responseBodyAsString),
            "Failed to fetch token, got status=${e.statusText}, message=${e.message}"
        )
        throw ClientCredentialsException("Unable to fetch token", e)
    } ?: throw ClientCredentialsException("Received empty body in response")
}
