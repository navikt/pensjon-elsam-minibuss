package no.nav.pensjon.elsam.minibuss.nav_cons_elsam_tptilb_registreretpforholdV0_1

import nav_cons_elsam_tptilb_registreretpforhold.no.nav.asbo.HentTPForholdListeRequestInt
import nav_cons_elsam_tptilb_registreretpforhold.no.nav.asbo.OpprettTPForholdRequestInt
import nav_cons_elsam_tptilb_registreretpforhold.no.nav.asbo.SlettTPForholdFinnTjenestepensjonsforholdRequestInt
import nav_cons_elsam_tptilb_registreretpforhold.no.nav.asbo.SlettTPForholdTjenestepensjonRequestInt
import nav_lib_frg.no.nav.lib.frg.fault.FaultElementetErUgyldig
import nav_lib_frg.no.nav.lib.frg.fault.FaultElementetFinnesIkke
import nav_lib_frg.no.nav.lib.frg.fault.FaultTomDatoForanFomDato
import nav_lib_frg.no.nav.lib.frg.gbo.GBOFinnTjenestepensjonsforholdRequest
import nav_lib_frg.no.nav.lib.frg.gbo.GBOTjenestepensjon
import nav_lib_frg.no.nav.lib.frg.gbo.GBOTjenestepensjonForhold
import no.nav.elsam.registreretpforhold.v0_1.FaultGenerisk
import no.nav.elsam.registreretpforhold.v0_1.FaultTjenestepensjonForholdIkkeFunnet
import no.nav.elsam.registreretpforhold.v0_1.HentTPForholdListeResp
import no.nav.elsam.registreretpforhold.v0_1.TPForhold
import javax.xml.datatype.DatatypeConfigurationException
import javax.xml.datatype.DatatypeFactory

object Mapper {
    @Throws(DatatypeConfigurationException::class)
    fun FaultElementetErUgyldigTOFaultTjenestepensjonForholdIkkeFunnet(
        FaultElementetErUgyldig: FaultElementetErUgyldig,
        FaultTjenestepensjonForholdIkkeFunnet: FaultTjenestepensjonForholdIkkeFunnet
    ) {
        FaultTjenestepensjonForholdIkkeFunnet.errorMessage =
            FaultElementetErUgyldig.errorMessage // move (executionOrder=1)
        FaultTjenestepensjonForholdIkkeFunnet.errorSource =
            FaultElementetErUgyldig.errorSource // move (executionOrder=2)
        FaultTjenestepensjonForholdIkkeFunnet.rootCause = FaultElementetErUgyldig.rootCause // move (executionOrder=3)
        FaultTjenestepensjonForholdIkkeFunnet.dateTimeStamp = DatatypeFactory.newInstance()
            .newXMLGregorianCalendar(FaultElementetErUgyldig.dateTimeStamp) // move (executionOrder=4)
    }

    @Throws(DatatypeConfigurationException::class)
    fun FaultElementetFinnesIkkeTOFaultTjenestepensjonForholdIkkeFunnet(
        FaultElementetFinnesIkke: FaultElementetFinnesIkke,
        FaultTjenestepensjonForholdIkkeFunnet: FaultTjenestepensjonForholdIkkeFunnet
    ) {
        FaultTjenestepensjonForholdIkkeFunnet.errorMessage =
            FaultElementetFinnesIkke.errorMessage // move (executionOrder=1)
        FaultTjenestepensjonForholdIkkeFunnet.errorSource =
            FaultElementetFinnesIkke.errorSource // move (executionOrder=2)
        FaultTjenestepensjonForholdIkkeFunnet.rootCause = FaultElementetFinnesIkke.rootCause // move (executionOrder=3)
        FaultTjenestepensjonForholdIkkeFunnet.dateTimeStamp = DatatypeFactory.newInstance()
            .newXMLGregorianCalendar(FaultElementetFinnesIkke.dateTimeStamp) // move (executionOrder=4)
    }

    fun FaultTomDatoForanFomDatoTOFaultGenerisk(
        FaultTomDatoForanFomDato: FaultTomDatoForanFomDato, FaultGenerisk: FaultGenerisk
    ) {
        FaultGenerisk.errorDescription = FaultTomDatoForanFomDato.errorMessage // move (executionOrder=1)
        FaultGenerisk.errorCode = "InternalError" // set (executionOrder=2)
    }

    fun GBOTjenestepensjonForholdTOTPForhold(
        GBOTjenestepensjonForhold: GBOTjenestepensjonForhold, TPForhold: TPForhold
    ) {
        TPForhold.tpnr = GBOTjenestepensjonForhold.tpnr // move (executionOrder=1)
        TPForhold.tpnavn = GBOTjenestepensjonForhold.navn // move (executionOrder=2)
    }

    fun GBOTjenestepensjonTOHentTPForholdListeResp(
        GBOTjenestepensjon: GBOTjenestepensjon, HentTPForholdListeResponse: HentTPForholdListeResp
    ) {
        HentTPForholdListeResponse.tjenestepensjonForholdene.addAll(
            GBOTjenestepensjon.tjenestepensjonForholdene.stream().map { it: GBOTjenestepensjonForhold ->
                val tpForhold = TPForhold()
                GBOTjenestepensjonForholdTOTPForhold(it, tpForhold)
                tpForhold
            }.toList()
        ) // submap (executionOrder=1)
    }

    fun HentTPForholdListeRequestIntTOGBOFinnTjenestepensjonsforholdRequest(
        HentTPForholdListeRequestInt: HentTPForholdListeRequestInt,
        GBOFinnTjenestepensjonsforholdRequest: GBOFinnTjenestepensjonsforholdRequest
    ) {
        GBOFinnTjenestepensjonsforholdRequest.fnr =
            HentTPForholdListeRequestInt.extRequest.fnr // move (executionOrder=1)
        GBOFinnTjenestepensjonsforholdRequest.hentSamhandlerInfo = "true" // set (executionOrder=2)
    }

    fun OpprettTPForholdRequestIntTOGBOTjenestepensjon(
        OpprettTPForholdRequestInt: OpprettTPForholdRequestInt, GBOTjenestepensjon: GBOTjenestepensjon
    ) {
        GBOTjenestepensjon.fnr = OpprettTPForholdRequestInt.extRequest.fnr // move (executionOrder=1)
        val gboTjenestepensjonForhold = GBOTjenestepensjonForhold()
        OpprettTPForholdRequestIntTOGBOTjenestepensjonForhold(OpprettTPForholdRequestInt, gboTjenestepensjonForhold)
        GBOTjenestepensjon.tjenestepensjonForholdene.add(gboTjenestepensjonForhold) // submap (executionOrder=2)
    }

    fun OpprettTPForholdRequestIntTOGBOTjenestepensjonForhold(
        OpprettTPForholdRequestInt: OpprettTPForholdRequestInt, GBOTjenestepensjonForhold: GBOTjenestepensjonForhold
    ) {
        GBOTjenestepensjonForhold.tssEksternId = OpprettTPForholdRequestInt.eksternTSSId // move (executionOrder=1)
        GBOTjenestepensjonForhold.harUtlandPensjon = "false" // set (executionOrder=2)
        val OpprettTPForholdRequestInt_extRequest_samtykke =
            OpprettTPForholdRequestInt.extRequest.samtykke // custom.input.forEach (executionOrder=3)
        GBOTjenestepensjonForhold.samtykkeSimuleringKode =
            if ((OpprettTPForholdRequestInt_extRequest_samtykke != null && OpprettTPForholdRequestInt_extRequest_samtykke.value != null && OpprettTPForholdRequestInt_extRequest_samtykke.value!!)) "J" else "N" // custom.output assignment (executionOrder=3)
        GBOTjenestepensjonForhold.harSimulering = "false" // set (executionOrder=4)
    }

    fun SlettTPForholdRequestIntTOGBOFinnTjenestepensjonsforholdRequest(
        SlettTPForholdRequestInt: SlettTPForholdFinnTjenestepensjonsforholdRequestInt,
        GBOFinnTjenestepensjonsforholdRequest: GBOFinnTjenestepensjonsforholdRequest
    ) {
        GBOFinnTjenestepensjonsforholdRequest.tssEksternId =
            SlettTPForholdRequestInt.eksternTSSId // move (executionOrder=1)
        GBOFinnTjenestepensjonsforholdRequest.fnr = SlettTPForholdRequestInt.extRequest.fnr // move (executionOrder=2)
        GBOFinnTjenestepensjonsforholdRequest.hentSamhandlerInfo = "false" // set (executionOrder=3)
    }

    fun SlettTPForholdRequestIntTOGBOTjenestepensjonForhold(
        SlettTPForholdTjenestepensjonRequestInt: SlettTPForholdTjenestepensjonRequestInt,
        GBOTjenestepensjonForhold: GBOTjenestepensjonForhold
    ) {
        GBOTjenestepensjonForhold.forholdId =
            SlettTPForholdTjenestepensjonRequestInt.forholdId // move (executionOrder=1)
    }
}
