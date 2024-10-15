package no.nav.pensjon.elsam.minibuss.sam

import no.nav.elsam.tpsamordningregistrering.v0_5.PeriodisertBelop
import no.nav.elsam.tpsamordningregistrering.v0_5.SlettTPYtelseReq
import org.springframework.http.client.ClientHttpResponse
import org.springframework.stereotype.Service
import org.springframework.web.client.ResponseErrorHandler
import org.springframework.web.client.RestClient
import org.springframework.web.client.body
import java.io.IOException
import java.util.Date

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

    fun opprettRefusjonskrav(pid: String, tpNr: String, samId: Long, refusjonskrav: Boolean, periodisertBelopListe: List<PeriodisertBelop>) =
        samRestClient.post()
            .uri("/api/refusjonskrav")
            .body(Refusjonskrav(pid, tpNr, samId, refusjonskrav, periodisertBelopListe.map { it.toRefusjonstrekk() }))
            .retrieve()
            .onStatus(NoOpResponseErrorHandler())
            .body<OpprettRefusjonskravResponse>()!!

    private fun PeriodisertBelop.toRefusjonstrekk() =
        Refusjonstrekk(
            belop = belop,
            kravstillersRef = kravstillersReferanse,
            datoFom = datoFom?.toGregorianCalendar()?.time,
            datoTom = datoTom?.toGregorianCalendar()?.time,
        )

    data class Refusjonskrav(
        val pid: String,
        val tpNr: String,
        val samId: Long,
        val refusjonskrav: Boolean,
        val periodisertBelopListe: List<Refusjonstrekk>
    )

    data class Refusjonstrekk(
        val belop: Double?,
        val kravstillersRef: String?,
        val datoFom: Date?,
        val datoTom: Date?,
    )

    enum class OpprettRefusjonskravExceptions {
        ALLEREDE_REGISTRERT_ELLER_UTENFOR_FRIST,
        ELEMENT_FINNES_IKKE,
        ULOVLIG_TREKK,
        FUNKSJONELL,
        GENERELL,
    }

    data class OpprettRefusjonskravResponse(
        val message: String? = null,
        val exceptionType: OpprettRefusjonskravExceptions? = null
    )

    class NoOpResponseErrorHandler: ResponseErrorHandler {
        @Throws(IOException::class)
        override fun hasError(response: ClientHttpResponse): Boolean {
            return response.statusCode.isError
        }

        @Throws(IOException::class)
        override fun handleError(response: ClientHttpResponse) {
            // do nothing
        }
    }
}
