package no.nav.pensjon.elsam.minibuss.tjenestepensjon

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
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
        if (tpNr.startsWith("2")) {
            ""
        } else {
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

    fun opprettTPForhold(fnr: String, ordning: String) = tpRestClient.put()
            .uri("/api/samhandler/tjenestepensjon/forhold/$ordning")
            .body(SamhandlerForholdDto(kilde = "TPLEV", tpNr = ordning))
            .header("fnr", fnr)
            .retrieve()
            .body<SamhandlerForholdDto>()
            ?: throw RuntimeException("Fikk tomt svar fra tp-registeret")

    fun slettTPForhold(fnr: String, ordning: String) = tpRestClient.delete()
            .uri("/api/samhandler/tjenestepensjon/forhold/$ordning")
            .header("fnr", fnr)
            .retrieve()
            .body<Unit>()
            ?: throw RuntimeException("Fikk tomt svar fra tp-registeret")


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

    interface TjenestepensjonForhold

    //hoved forholdDto
    class SamhandlerForholdDto(
        val kilde: String, //KildeTypeCode
        tpNr: String,
        val ytelser: List<SamhandlerYtelseDto> = emptyList(),
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        datoSistOpptjening: LocalDate? = null,
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        sistEndretDatoSistOpptjening: LocalDateTime? = null,
        @JsonProperty(access = JsonProperty.Access.READ_ONLY)
        val changeStamp: ChangeStampDateDto? = null,
        harGjenlevendeYtelse: Boolean? = null
    ) : SamhandlerForholdsinfoDto(tpNr, "", datoSistOpptjening, sistEndretDatoSistOpptjening, harGjenlevendeYtelse)

    //også kjent som forholdListe
    open class SamhandlerForholdsinfoDto(
        val tpNr: String,
        val tpOrdningNavn: String,
        val datoSistOpptjening: LocalDate? = null,
        val sistEndretDatoSistOpptjening: LocalDateTime? = null,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        val harGjenlevendeYtelse: Boolean? = false
    ): TjenestepensjonForhold

    data class ChangeStampDateDto(
        val createdBy: String,
        val createdDate: LocalDateTime,
        val updatedBy: String,
        val updatedDate: LocalDateTime
    )

    data class SamhandlerYtelseDto(
        val datoInnmeldtYtelseFom: LocalDate? = null,
        val ytelseType: String, //YtelseTypeCode
        val datoYtelseIverksattFom: LocalDate? = null,
        val datoYtelseIverksattTom: LocalDate? = null,
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        val changeStamp: ChangeStampDateDto? = null,
        @JsonInclude(JsonInclude.Include.NON_EMPTY)
        val ytelseId: Long? = null
    )

}
