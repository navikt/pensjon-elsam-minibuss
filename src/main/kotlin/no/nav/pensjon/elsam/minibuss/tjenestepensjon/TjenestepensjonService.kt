package no.nav.pensjon.elsam.minibuss.tjenestepensjon

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import org.ehcache.impl.internal.concurrent.ConcurrentHashMap
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpClientErrorException.NotFound
import org.springframework.web.client.RestClient
import org.springframework.web.client.body
import java.time.LocalDate
import java.time.LocalDateTime

@Service
class TjenestepensjonService(
    private val tpRestClient: RestClient,
) {
    private val ordningCache = ConcurrentHashMap<String, CachedValue<String?>>()

    fun hentOrdning(tpNr: String): String =
        ordningCache[tpNr]
            ?.takeIf { it.fetchTime.plusHours(1).isAfter(LocalDateTime.now()) }
            ?.value
            ?: run {
                try {
                    tpRestClient.get()
                        .uri("/api/ordning/{tpNr}", mapOf("tpNr" to tpNr))
                        .retrieve()
                        .body<OrdningDto>()?.navn ?: ""
                } catch (e: NotFound) {
                    ""
                }.also {
                    ordningCache[tpNr] = CachedValue(it)
                }
        }

    fun hentTjenestepensjon(fnr: String): List<ForholdModel> {
        val tjenestepensjon: TjenestepensjonModel = tpRestClient.get()
            .uri("/api/tjenestepensjon/")
            .header("fnr", fnr)
            .retrieve()
            .body()
            ?: throw RuntimeException("Fikk tomt svar fra tp-registeret")

        return tjenestepensjon.forhold
    }

    fun harTjenestepensjon(fnr: String): Boolean {
        val tjenestepensjon: TjenestepensjonModel = tpRestClient.get()
            .uri("/api/tjenestepensjon/")
            .header("fnr", fnr)
            .retrieve()
            .body()
            ?: throw RuntimeException("Fikk tomt svar fra tp-registeret")

        return tjenestepensjon.forhold.isNotEmpty()
    }

    fun opprettForhold(fnr: String, tpnr: String) {
        tpRestClient.put()
            .uri("/api/samhandler/tjenestepensjon/forhold/{tpnr}", mapOf("tpnr" to tpnr))
            .header("fnr", fnr)
            .body(OpprettForholdRequest(
                kilde = "TPLEV",
                tpNr = tpnr,
            ))
            .retrieve()
            .body<String>()
    }

    fun slettTjenestepensjonsforhold(fnr: String, tpNr: String) {
        tpRestClient.delete()
            .uri("/api/samhandler/tjenestepensjon/forhold/{tpNr}", mapOf("tpNr" to tpNr))
            .header("fnr", fnr)
            .retrieve()
            .body<String>()
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    data class OrdningDto(
        val navn: String,
        val tpNr: String,
        val orgNr: String,
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        val tssId: String? = null
    )

    @JsonIgnoreProperties(ignoreUnknown = true)
    data class TjenestepensjonModel(
        val forhold: List<ForholdModel> = emptyList()
    )

    @JsonIgnoreProperties(ignoreUnknown = true)
    data class ForholdModel(
        val ordning: String,
        val ytelser: List<YtelseModel>? = emptyList(),
        val createdBy: String?,
        val updatedBy: String?,
        val kilde: String?,
        val datoSistOpptjening: LocalDate?
    )

    @JsonIgnoreProperties(ignoreUnknown = true)
    data class OpprettForholdRequest(
        val kilde: String,
        val tpNr: String,
    )

    @JsonIgnoreProperties(ignoreUnknown = true)
    data class YtelseModel(
        val type: String,
        val datoInnmeldtYtelseFom: LocalDate? = null,
        val datoYtelseIverksattFom: LocalDate? = null,
        val datoYtelseIverksattTom: LocalDate? = null
    )

    data class CachedValue<T>(
        val value: T,
        val fetchTime: LocalDateTime = LocalDateTime.now(),
    )
}
