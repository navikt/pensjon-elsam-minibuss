package no.nav.pensjon.elsam.minibuss.sam

import no.nav.elsam.tpsamordningregistrering.v0_5.SlettTPYtelseReq
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient
import org.springframework.web.client.body

@Service
class SamService(
    private val samRestClient: RestClient
) {
    fun slettTPYtelse(request: SlettTPYtelseReq) {
        samRestClient.post()
            .uri("/api/tp/ytelse/slett")
            .body(request)
            .retrieve()
            .body<String>()
    }
}
