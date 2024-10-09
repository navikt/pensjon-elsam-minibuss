package no.nav.pensjon.elsam.minibuss.sam

import no.nav.elsam.tpsamordningregistrering.v0_5.FaultGenerisk
import no.nav.elsam.tpsamordningregistrering.v0_5.FaultTPYtelseAlleredeRegistrert
import no.nav.elsam.tpsamordningregistrering.v0_5.LagreTPYtelseReq
import no.nav.elsam.tpsamordningregistrering.v1_0.LagreTPYtelseFaultGeneriskMsg
import no.nav.elsam.tpsamordningregistrering.v1_0.LagreTPYtelseFaultTPYtelseAlleredeRegistrertMsg
import no.nav.elsam.tpsamordningregistrering.v1_0.LagreTPYtelseResp
import no.nav.pensjon.elsam.minibuss.misc.toXMLGregorianCalendar
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient
import java.util.Date

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
            HttpStatus.CONFLICT -> {
                val melding = respWrapper.e?.message
                throw LagreTPYtelseFaultTPYtelseAlleredeRegistrertMsg(
                    melding,
                    FaultTPYtelseAlleredeRegistrert().apply {
                        errorMessage = melding
                        errorSource = "MODULE: nav-ent-sto-sam / COMPONENT: SamordningTOSamordningProcessProviderRemote / IF(OP): Samordning(opprettTPSamordning) / REF: SamordningProcessProviderRemotePartner IF(OP): SamordningProcessProviderRemote(samordneVedtak)"
                        dateTimeStamp = Date().toXMLGregorianCalendar()
                    }
                )
            }
            else -> {
                val melding = respWrapper.e?.message
                throw LagreTPYtelseFaultGeneriskMsg(
                    melding,
                    FaultGenerisk().apply {
                        errorCode = "InternalError"
                        errorDescription = melding
                        errorDetails.add("Teknisk feil ved oppslag")
                    }
                )
            }
        }
    }
}

data class LagreTPYtelseResponseWrapper(
    val status: HttpStatus,
    val lagreTPYtelse: LagreTPYtelseResp = LagreTPYtelseResp(),
    val e: Exception? = null,
    val exceptionName: String = ""
)
