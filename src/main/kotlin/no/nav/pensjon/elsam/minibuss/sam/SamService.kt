package no.nav.pensjon.elsam.minibuss.sam

import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient

@Service
class SamService(
    private val samRestClient: RestClient
) {
}
