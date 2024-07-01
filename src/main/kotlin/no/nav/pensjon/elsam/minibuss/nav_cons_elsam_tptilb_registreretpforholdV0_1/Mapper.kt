package no.nav.pensjon.elsam.minibuss.nav_cons_elsam_tptilb_registreretpforholdV0_1

import nav_cons_elsam_tptilb_registreretpforhold.no.nav.asbo.HentTPForholdListeRequestInt
import nav_cons_elsam_tptilb_registreretpforhold.no.nav.asbo.OpprettTPForholdRequestInt
import nav_cons_elsam_tptilb_registreretpforhold.no.nav.asbo.SlettTPForholdFinnTjenestepensjonsforholdRequestInt
import nav_cons_elsam_tptilb_registreretpforhold.no.nav.asbo.SlettTPForholdTjenestepensjonRequestInt
import nav_lib_cons_sto_sam.no.nav.lib.sto.sam.asbo.tjenestepensjon.ASBOStoFinnTjenestepensjonsforholdRequest
import nav_lib_cons_sto_sam.no.nav.lib.sto.sam.asbo.tjenestepensjon.ASBOStoTjenestepensjon
import nav_lib_cons_sto_sam.no.nav.lib.sto.sam.asbo.tjenestepensjon.ASBOStoTjenestepensjonforhold
import nav_lib_frg.no.nav.lib.frg.fault.FaultElementetErUgyldig
import nav_lib_frg.no.nav.lib.frg.fault.FaultElementetFinnesIkke
import nav_lib_frg.no.nav.lib.frg.fault.FaultTomDatoForanFomDato
import nav_lib_frg.no.nav.lib.frg.gbo.GBOTjenestepensjonForhold
import no.nav.elsam.registreretpforhold.v0_1.FaultGenerisk
import no.nav.elsam.registreretpforhold.v0_1.FaultTjenestepensjonForholdIkkeFunnet
import no.nav.elsam.registreretpforhold.v0_1.HentTPForholdListeResp
import no.nav.elsam.registreretpforhold.v0_1.TPForhold
import javax.xml.datatype.DatatypeFactory

// FaultElementetErUgyldigTOFaultTjenestepensjonForholdIkkeFunnet
fun FaultElementetErUgyldig.toFaultTjenestepensjonForholdIkkeFunnet() =
    FaultTjenestepensjonForholdIkkeFunnet().also {
        it.errorMessage = errorMessage // move (executionOrder=1)
        it.errorSource = errorSource // move (executionOrder=2)
        it.rootCause = rootCause // move (executionOrder=3)
        it.dateTimeStamp = dateTimeStamp?.let { dts -> DatatypeFactory.newInstance().newXMLGregorianCalendar(dts) } // move (executionOrder=4)
    }

// FaultElementetFinnesIkkeTOFaultTjenestepensjonForholdIkkeFunnet
fun FaultElementetFinnesIkke.toFaultTjenestepensjonForholdIkkeFunnet() =
    FaultTjenestepensjonForholdIkkeFunnet().also {
        it.errorMessage = errorMessage // move (executionOrder=1)
        it.errorSource = errorSource // move (executionOrder=2)
        it.rootCause = rootCause // move (executionOrder=3)
        it.dateTimeStamp = dateTimeStamp?.let { dts -> DatatypeFactory.newInstance().newXMLGregorianCalendar(dts) } // move (executionOrder=4)
    }

// FaultTomDatoForanFomDatoTOFaultGenerisk
fun FaultTomDatoForanFomDato.toFaultGenerisk() =
    FaultGenerisk().also {
        it.errorDescription = errorMessage // move (executionOrder=1)
        it.errorCode = "InternalError" // set (executionOrder=2)
    }

// GBOTjenestepensjonForholdTOTPForhold
fun ASBOStoTjenestepensjonforhold.toTPForhold() =
    TPForhold().also {
        it.tpnr = tpnr // move (executionOrder=1)
        it.tpnavn = navn // move (executionOrder=2)
    }

// GBOTjenestepensjonTOHentTPForholdListeResp
fun ASBOStoTjenestepensjon.toHentTPForholdListeResp() =
    HentTPForholdListeResp().also {
        it.tjenestepensjonForholdene.addAll(tjenestepensjonsforholdListe.map(ASBOStoTjenestepensjonforhold::toTPForhold)) // submap (executionOrder=1)
    }

// HentTPForholdListeRequestIntTOGBOFinnTjenestepensjonsforholdRequest
fun HentTPForholdListeRequestInt.toGBOFinnTjenestepensjonsforholdRequest() =
    ASBOStoFinnTjenestepensjonsforholdRequest().also {
        it.fnr = extRequest?.fnr // move (executionOrder=1)
        it.hentSamhandlerInfo = true // set (executionOrder=2)
    }

// OpprettTPForholdRequestIntTOGBOTjenestepensjon
fun OpprettTPForholdRequestInt.toGBOTjenestepensjon() =
    ASBOStoTjenestepensjon().also {
        it.fnr = extRequest?.fnr // move (executionOrder=1)
        it.tjenestepensjonsforholdListe.add(this.toGBOTjenestepensjonForhold()) // submap (executionOrder=2)
    }

// OpprettTPForholdRequestIntTOGBOTjenestepensjonForhold
fun OpprettTPForholdRequestInt.toGBOTjenestepensjonForhold() =
    ASBOStoTjenestepensjonforhold().also {
        it.tssEksternId = eksternTSSId // move (executionOrder=1)
        it.harUtlandPensjon = false // set (executionOrder=2)
        it.samtykkeSimuleringKode = if (extRequest?.samtykke?.value == true) "J" else "N" // custom.output assignment (executionOrder=3)
        it.harSimulering = false // set (executionOrder=4)
    }

// SlettTPForholdRequestIntTOGBOFinnTjenestepensjonsforholdRequest
fun SlettTPForholdFinnTjenestepensjonsforholdRequestInt.toGBOFinnTjenestepensjonsforholdRequest() =
    ASBOStoFinnTjenestepensjonsforholdRequest().also {
        it.tssEksternId = eksternTSSId // move (executionOrder=1)
        it.fnr = extRequest?.fnr // move (executionOrder=2)
        it.hentSamhandlerInfo = false // set (executionOrder=3)
    }

// SlettTPForholdRequestIntTOGBOTjenestepensjonForhold
fun SlettTPForholdTjenestepensjonRequestInt.toGBOTjenestepensjonForhold() =
    GBOTjenestepensjonForhold().also {
        it.forholdId = forholdId // move (executionOrder=1)
    }
