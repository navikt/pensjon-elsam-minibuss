package no.nav.pensjon.elsam.minibuss.sam

import no.nav.elsam.tpsamordningregistrering.v0_5.LagreTPYtelseReq
import no.nav.elsam.tpsamordningregistrering.v1_0.LagreTPYtelseFaultGeneriskMsg
import no.nav.elsam.tpsamordningregistrering.v1_0.LagreTPYtelseFaultTPYtelseAlleredeRegistrertMsg
import no.nav.elsam.tpsamordningregistrering.v1_0.LagreTPYtelseResp
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient

@Service
class SamService(
    private val samRestClient: RestClient
) {
    fun lagreTPYtelse(request: LagreTPYtelseReq): LagreTPYtelseResp {

        val respWrapper = samRestClient.post()
            .uri("/api/tp/ytelse/lagre")
            .body(request)
            .retrieve()
            .body(LagreTPYtelseResponseWrapper::class.java) ?: throw RuntimeException("Fikk tomt svar fra tjenesten")

        when (respWrapper.status) {
            HttpStatus.OK -> return respWrapper.lagreTPYtelse
            HttpStatus.NOT_FOUND -> throw respWrapper.e as LagreTPYtelseFaultGeneriskMsg
            HttpStatus.BAD_REQUEST -> throw respWrapper.e as LagreTPYtelseFaultTPYtelseAlleredeRegistrertMsg
            HttpStatus.INTERNAL_SERVER_ERROR -> throw respWrapper.e as LagreTPYtelseFaultGeneriskMsg
            else -> throw RuntimeException("Ukjent feil")

        }
    }
}

data class LagreTPYtelseResponseWrapper(
    val status: HttpStatus,
    val lagreTPYtelse: LagreTPYtelseResp = LagreTPYtelseResp(),
    val e: Exception? = null,
    val exceptionName: String = ""
)
