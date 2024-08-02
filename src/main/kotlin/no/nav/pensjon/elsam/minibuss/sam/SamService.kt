package no.nav.pensjon.elsam.minibuss.sam

import no.nav.elsam.tpsamordningregistrering.v0_5.HentSamordningsdataReq
import no.nav.elsam.tpsamordningregistrering.v0_5.LagreTPYtelseReq
import no.nav.elsam.tpsamordningregistrering.v0_5.OpprettRefusjonskravReq
import no.nav.elsam.tpsamordningregistrering.v1_0.*
import no.nav.pensjon.elsam.minibuss.misc.entries
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient
import org.springframework.web.util.UriComponentsBuilder.fromUriString


@Service
class SamService(
    private val samRestClient: RestClient
) {
    private val log = LoggerFactory.getLogger(SamService::class.java)

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

    fun hentSamordningsdata(request: HentSamordningsdataReq): HentSamordningsdataResp {

        val respWrapper = samRestClient.post()
            .uri("/api/minibuss/hentSamordningsdata")
            .body(request)
            .retrieve()
            .body(HentSamordningsdataResponseWrapper::class.java) ?: throw RuntimeException("Fikk tomt svar fra tjenesten")

        when (respWrapper.status) {
            HttpStatus.OK -> return respWrapper.samordningsdata
            HttpStatus.NOT_FOUND -> throw respWrapper.e as HentSamordningsdataFaultGeneriskMsg
            HttpStatus.BAD_REQUEST -> throw respWrapper.e as HentSamordningsdataFaultTPForholdIkkeIverksattMsg
            HttpStatus.INTERNAL_SERVER_ERROR -> throw respWrapper.e as HentSamordningsdataFaultGeneriskMsg
            else -> throw RuntimeException("Ukjent feil")

        }
    }

    fun validerHentSamordningsdata(samResponse: HentSamordningsdataResp?, busResponse: HentSamordningsdataResp?) {
        if (samResponse != null && busResponse != null &&
            samResponse.tpnr == busResponse.tpnr &&
            samResponse.person == busResponse.person &&
            samResponse.ufullstendigeData == busResponse.ufullstendigeData &&
            samResponse.arbeidVedtakListe == busResponse.arbeidVedtakListe &&
            samResponse.pensjonVedtakListe == busResponse.pensjonVedtakListe &&
            samResponse.tjenestepensjonYtelseListe == busResponse.tjenestepensjonYtelseListe) {

            log.info(
                "Likt svar fra buss og sam, {}", entries(
                    "bus" to busResponse,
                    "sam" to samResponse,
                )
            )
        } else {
            log.info(
                "Avvik mellom buss og sam, {}", entries(
                    "bus" to busResponse,
                    "sam" to samResponse,
                )
            )
        }
    }

    fun opprettRefusjonskrav(request: OpprettRefusjonskravReq) {

        val builder = fromUriString("/api/refusjonskrav")
            .queryParam("tpNr", request.tpnr)
            .queryParam("samId", request.samordningsmeldingId)
            .queryParam("refusjonskrav", request.refusjonskrav)
            .queryParam("periodisertBelopListe", request.periodisertBelopListe)

        val respWrapper = samRestClient.post()
            .uri(builder.toUriString())
            .header("pid", request.fnr)
            .retrieve()
            .body(OpprettRefusjonskravResponse::class.java) ?: throw RuntimeException("Fikk tomt svar fra tjenesten")

        if (respWrapper.exception != null) {
            log.info("opprettRefusjonskrav feilet. ${request.tpnr}, ${request.samordningsmeldingId}. ${respWrapper.exception.message}")
            throw when (respWrapper.exceptionName) {
                "SamAlreadyAnsweredOrTimeLimitExceededException" -> OpprettRefusjonskravFaultGeneriskMsg(respWrapper.exception.message, respWrapper.exception)
                "SamUlovligTrekkException" -> OpprettRefusjonskravFaultGeneriskMsg(respWrapper.exception.message, respWrapper.exception)
                "SamElementFinnesIkkeException" -> OpprettRefusjonskravFaultSamordningsIdIkkeFunnetMsg(respWrapper.exception.message, respWrapper.exception)
                else -> OpprettRefusjonskravFaultGeneriskMsg(respWrapper.exception.message, respWrapper.exception)
            }
        }
        log.info("opprettRefusjonskrav ok. ${request.tpnr}, ${request.samordningsmeldingId}")
    }
}

data class LagreTPYtelseResponseWrapper(
    val status: HttpStatus,
    val lagreTPYtelse: LagreTPYtelseResp = LagreTPYtelseResp(),
    val e: Exception? = null,
    val exceptionName: String = ""
)

data class HentSamordningsdataResponseWrapper(
    val status: HttpStatus,
    val samordningsdata: HentSamordningsdataResp = HentSamordningsdataResp(),
    val e: Exception? = null,
    val exceptionName: String = ""
)

data class OpprettRefusjonskravResponse(
    val refusjonskravAlleredeRegistrertEllerUtenforFrist: Boolean,
    val exception: Exception? = null,
    val exceptionName: String? = null
)
