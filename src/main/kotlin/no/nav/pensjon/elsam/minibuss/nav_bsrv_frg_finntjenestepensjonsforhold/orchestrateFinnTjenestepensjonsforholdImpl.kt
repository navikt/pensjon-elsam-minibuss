package no.nav.pensjon.elsam.minibuss.nav_bsrv_frg_finntjenestepensjonsforhold

import nav_lib_frg.no.nav.lib.frg.gbo.*
import nav_lib_frg.no.nav.lib.frg.inf.HentSamhandlerFaultSamhandlerIkkeFunnetMsg
import nav_lib_frg.no.nav.lib.frg.inf.Samhandler
import nav_lib_frg.no.nav.lib.frg.inf.tjenestepensjon.HentTjenestepensjonInfoFaultElementetFinnesIkkeMsg
import nav_lib_frg.no.nav.lib.frg.inf.tjenestepensjon.HentTjenestepensjonInfoFaultTomDatoForanFomDatoMsg
import nav_lib_frg.no.nav.lib.frg.inf.tjenestepensjon.Tjenestepensjon

class orchestrateFinnTjenestepensjonsforholdImpl(
    private val samhandlerPartner: Samhandler,
    private val tjenestepensjonPartner: Tjenestepensjon,
) {
    @Throws(
        HentTjenestepensjonInfoFaultElementetFinnesIkkeMsg::class,
        HentTjenestepensjonInfoFaultTomDatoForanFomDatoMsg::class
    )
    fun finnTjenestepensjonsforhold(finnTjenestepensjonsforholdRequest: GBOFinnTjenestepensjonsforholdRequest?): GBOTjenestepensjon? {
        var finnTjenestepensjonsforholdResponse: GBOTjenestepensjon? = null

        if (finnTjenestepensjonsforholdRequest != null) {
            //Check which search-parameter is given, and invoke the corresponding operation
            if (finnTjenestepensjonsforholdRequest.fnr != null) {
                finnTjenestepensjonsforholdResponse = tjenestepensjonPartner.hentTjenestepensjonInfo(finnTjenestepensjonsforholdRequest.toGBOHentTjenestepensjonInfoRequest())
            } else if (finnTjenestepensjonsforholdRequest.forholdId != null) {
                finnTjenestepensjonsforholdResponse = tjenestepensjonPartner.hentTjenestepensjonForholdYtelse(finnTjenestepensjonsforholdRequest.toGBOHentTjenestepensjonForholdYtelseRequest())
            } else if (finnTjenestepensjonsforholdRequest.ytelseId != null) {
                finnTjenestepensjonsforholdResponse = tjenestepensjonPartner.hentTjenestepensjonForholdYtelse(finnTjenestepensjonsforholdRequest.toGBOHentTjenestepensjonForholdYtelseRequest())
            }

            //IF hentSamhandlerInfo is true get samhandler-info for all tjenestepensjonForhold's in the response
            if ("true".equals(finnTjenestepensjonsforholdRequest.hentSamhandlerInfo, ignoreCase = true)) {
                finnTjenestepensjonsforholdResponse = fillGBOTjenestepensjonWithSamhandlerInfo(finnTjenestepensjonsforholdResponse)
            }
        }

        return finnTjenestepensjonsforholdResponse
    }


    private fun GBOFinnTjenestepensjonsforholdRequest.toGBOHentTjenestepensjonInfoRequest() =
        GBOHentTjenestepensjonInfoRequest().also {
            it.fnr = fnr
            it.datoFom = fom
            it.datoTom = tom
            it.tssEksternId = tssEksternId
        }

    private fun GBOFinnTjenestepensjonsforholdRequest.toGBOHentTjenestepensjonForholdYtelseRequest() =
        GBOHentTjenestepensjonForholdYtelseRequest().also {
            it.forholdId = forholdId
            it.ytelseId = ytelseId
        }

    private fun fillGBOTjenestepensjonWithSamhandlerInfo(tjenestepensjon: GBOTjenestepensjon?): GBOTjenestepensjon? {
        if (tjenestepensjon != null) {
            val tjenestepensjonForholdList: List<GBOTjenestepensjonForhold?> = tjenestepensjon.tjenestepensjonForholdene

            //Iterate through received list to fill in samhandlerInfo on each item in the list
            val iter: Iterator<GBOTjenestepensjonForhold?> = tjenestepensjonForholdList.iterator()
            while (iter.hasNext()) {
                val tjenestepensjonForhold: GBOTjenestepensjonForhold? = iter.next()
                if (tjenestepensjonForhold != null) {
                    //Create request-object for hentSamhandler-call
                    //Call hentSamhandler to get samhandlerInfo
                    try {
                        val hentSamhandlerResponse: GBOSamhandler? = samhandlerPartner.hentSamhandler(GBOHentSamhandlerRequest().apply {
                            idTSSEkstern = tjenestepensjonForhold.tssEksternId
                            hentDetaljert = true
                        })

                        if (hentSamhandlerResponse != null) {
                            //Get alternativId from the response's list alternativeIder where alternativeIdKode is "TPNR"

                            var altId: GBOAlternativId?
                            val altIdList: List<GBOAlternativId?>? = hentSamhandlerResponse.alternativeIder
                            if (altIdList != null) {
                                var done = false
                                val i: Iterator<GBOAlternativId?> = altIdList.iterator()
                                while (i.hasNext() && !done) {
                                    altId = i.next()
                                    if (altId != null && altId.alternativKode != null && altId.alternativKode == "TPNR") {
                                        tjenestepensjonForhold.tpnr = altId.alternativId
                                        done = true
                                    }
                                }
                            }

                            //Get avdeling from the response and read avdelingNavn from avdeling.
                            val samhandlerAvdelingList: List<GBOAvdeling>? = hentSamhandlerResponse.avdelinger

                            if (samhandlerAvdelingList != null) {
                                //The number of entries in this list will always be 1 for the call used here
                                val samhandlerAvdeling: GBOAvdeling = samhandlerAvdelingList[0]
                                tjenestepensjonForhold.navn = samhandlerAvdeling.avdelingNavn
                                tjenestepensjonForhold.avdelingType = samhandlerAvdeling.avdelingType
                            }
                        }
                    } catch (sbe: HentSamhandlerFaultSamhandlerIkkeFunnetMsg) {
                        //hentSamhandler might return FaultSamhandlerIkkeFunnet. This fault
                        //is ignored and processing continuous with next item in the list.
                    }
                }
            }
        }

        return tjenestepensjon
    }
}
