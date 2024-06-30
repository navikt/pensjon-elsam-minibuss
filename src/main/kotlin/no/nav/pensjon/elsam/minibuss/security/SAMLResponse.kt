package no.nav.pensjon.elsam.minibuss.security

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty

class SAMLResponse(
    @JsonProperty("access_token")
    val accessToken: ByteArray,
    @JsonProperty("issued_token_type")
    val issuedTokenType: String,
    @JsonProperty("token_type")
    val tokenType: String,
    @JsonProperty("expires_in")
    val expiresIn: Int,
) {
    val decodedToken: String
        @JsonIgnore
        get() = String(accessToken)
}
