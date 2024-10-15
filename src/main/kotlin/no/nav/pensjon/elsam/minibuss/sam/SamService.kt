package no.nav.pensjon.elsam.minibuss.sam

import no.nav.elsam.tpsamordningregistrering.v0_5.PeriodisertBelop
import no.nav.elsam.tpsamordningregistrering.v0_5.SlettTPYtelseReq
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient
import org.springframework.web.client.body
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

    data class OpprettRefusjonskravResponse(
        val refusjonskravAlleredeRegistrertEllerUtenforFrist: Boolean,
        val exception: Exception? = null,
        val exceptionName: String? = null
    )
}
