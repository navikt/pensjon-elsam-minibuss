package no.nav.pensjon.elsam.minibuss.tjenestepensjon

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient
import org.springframework.web.client.body
import java.time.LocalDate

@Service
class TjenestepensjonService(
    private val tpRestClient: RestClient,
) {
    fun harTjenestepensjon(fnr: String): Boolean {
        val tjenestepensjon: TjenestepensjonModel = tpRestClient.get()
            .uri("/api/tjenestepensjon/")
            .header("fnr", fnr)
            .retrieve()
            .body()
            ?: throw RuntimeException("Fikk tomt svar fra tp-registeret")

        return tjenestepensjon.forhold.isNotEmpty()
    }

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
}
