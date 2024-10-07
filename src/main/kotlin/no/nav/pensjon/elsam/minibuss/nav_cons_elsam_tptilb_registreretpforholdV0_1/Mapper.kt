package no.nav.pensjon.elsam.minibuss.nav_cons_elsam_tptilb_registreretpforholdV0_1

import nav_cons_elsam_tptilb_registreretpforhold.no.nav.asbo.OpprettTPForholdRequestInt
import nav_lib_cons_sto_sam.no.nav.lib.sto.sam.asbo.tjenestepensjon.ASBOStoTjenestepensjon
import nav_lib_cons_sto_sam.no.nav.lib.sto.sam.asbo.tjenestepensjon.ASBOStoTjenestepensjonforhold

// OpprettTPForholdRequestIntTOGBOTjenestepensjon.map
fun OpprettTPForholdRequestInt.toGBOTjenestepensjon() =
    ASBOStoTjenestepensjon().also {
        it.fnr = extRequest?.fnr // move (executionOrder=1)
        it.tjenestepensjonsforholdListe.add(this.toGBOTjenestepensjonForhold()) // submap (executionOrder=2)
    }

// OpprettTPForholdRequestIntTOGBOTjenestepensjonForhold.map
fun OpprettTPForholdRequestInt.toGBOTjenestepensjonForhold() =
    ASBOStoTjenestepensjonforhold().also {
        it.tssEksternId = eksternTSSId // move (executionOrder=1)
        it.harUtlandPensjon = false // set (executionOrder=2)
        it.samtykkeSimuleringKode = if (extRequest?.samtykke?.value == true) "J" else "N" // custom.output assignment (executionOrder=3)
        it.harSimulering = false // set (executionOrder=4)
    }
