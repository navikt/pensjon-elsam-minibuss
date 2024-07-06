package no.nav.pensjon.elsam.minibuss.security.azuread

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime
import kotlin.math.min

data class ClientCredentialsTokenResponse(
    @JsonProperty("token_type") val tokenType: String,
    @JsonProperty("expires_in") val expiresIn: Long,
    @JsonProperty("access_token") val accessToken: String,
) {
    @JsonIgnore
    private val fetchTime = LocalDateTime.now()

    @JsonIgnore
    val expireTime: LocalDateTime = expiresIn.let { fetchTime.plusSeconds(it - expiryLeeway) }

    val isValid: Boolean
        @JsonIgnore
        get() = expireTime.isAfter(LocalDateTime.now())

    companion object {
        const val expiryLeeway = 30L
    }
}
