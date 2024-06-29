package no.nav.pensjon.elsam.minibuss.nav_bsrv_frg_finntjenestepensjonsforhold

import nav_lib_frg.no.nav.lib.frg.gbo.*
import nav_lib_frg.no.nav.lib.frg.inf.HentSamhandlerFaultSamhandlerIkkeFunnetMsg
import nav_lib_frg.no.nav.lib.frg.inf.Samhandler
import nav_lib_frg.no.nav.lib.frg.inf.tjenestepensjon.HentTjenestepensjonInfoFaultElementetFinnesIkkeMsg
import nav_lib_frg.no.nav.lib.frg.inf.tjenestepensjon.HentTjenestepensjonInfoFaultTomDatoForanFomDatoMsg
import nav_lib_frg.no.nav.lib.frg.inf.tjenestepensjon.Tjenestepensjon

class orchestrateFinnTjenestepensjonsforholdImpl {
    private val samhandlerPartner: Samhandler? = null
    private val tjenestepensjonPartner: Tjenestepensjon? = null

    fun locateService_TjenestepensjonPartner(): Tjenestepensjon? {
        return tjenestepensjonPartner
    }

    fun locateService_SamhandlerPartner(): Samhandler? {
        return samhandlerPartner
    }

    @Throws(
        HentTjenestepensjonInfoFaultElementetFinnesIkkeMsg::class,
        HentTjenestepensjonInfoFaultTomDatoForanFomDatoMsg::class
    )
    fun finnTjenestepensjonsforhold(finnTjenestepensjonsforholdRequest: GBOFinnTjenestepensjonsforholdRequest?): GBOTjenestepensjon? {
        var finnTjenestepensjonsforholdResponse: GBOTjenestepensjon? = null
        if (finnTjenestepensjonsforholdRequest != null) {
            //Error handling is implicit, since the exact same "fault-BO's" (java-exceptions in the run-time)
            //is sent out from the POJO as is received in to it. The exceptions float up to the cons-layer
            //automatically.

            //Check which search-parameter is given, and invoke the corresponding operation

            if (finnTjenestepensjonsforholdRequest.fnr != null) {
                finnTjenestepensjonsforholdResponse = locateService_TjenestepensjonPartner()!!.hentTjenestepensjonInfo(
                    mapGBOFinnTjenestepensjonsforholdRequestTOGBOHentTjenestepensjonInfoRequest(
                        finnTjenestepensjonsforholdRequest
                    )
                )
            } else if (finnTjenestepensjonsforholdRequest.forholdId != null) {
                finnTjenestepensjonsforholdResponse =
                    locateService_TjenestepensjonPartner()!!.hentTjenestepensjonForholdYtelse(
                        mapGBOFinnTjenestepensjonsforholdRequestTOGBOHentTjenestepensjonForholdYtelseRequest(
                            finnTjenestepensjonsforholdRequest
                        )
                    )
            } else if (finnTjenestepensjonsforholdRequest.ytelseId != null) {
                finnTjenestepensjonsforholdResponse =
                    locateService_TjenestepensjonPartner()!!.hentTjenestepensjonForholdYtelse(
                        mapGBOFinnTjenestepensjonsforholdRequestTOGBOHentTjenestepensjonForholdYtelseRequest(
                            finnTjenestepensjonsforholdRequest
                        )
                    )
            }

            //IF hentSamhandlerInfo is true get samhandler-info for all tjenestepensjonForhold's in the response
            if ("true".equals(finnTjenestepensjonsforholdRequest.hentSamhandlerInfo, ignoreCase = true)) {
                finnTjenestepensjonsforholdResponse =
                    fillGBOTjenestepensjonWithSamhandlerInfo(finnTjenestepensjonsforholdResponse)
            }
        }
        return finnTjenestepensjonsforholdResponse
    }


    // ******************
    // UTILITY METHODS
    // ******************
    private fun mapGBOFinnTjenestepensjonsforholdRequestTOGBOHentTjenestepensjonInfoRequest(
        GBOFinnTjenestepensjonsforholdRequest: GBOFinnTjenestepensjonsforholdRequest?
    ): GBOHentTjenestepensjonInfoRequest? {
        var GBOHentTjenestepensjonInfoRequest: GBOHentTjenestepensjonInfoRequest? = null

        if (GBOFinnTjenestepensjonsforholdRequest != null) {
            GBOHentTjenestepensjonInfoRequest = GBOHentTjenestepensjonInfoRequest()
            GBOHentTjenestepensjonInfoRequest.fnr = GBOFinnTjenestepensjonsforholdRequest.fnr
            GBOHentTjenestepensjonInfoRequest.datoFom = GBOFinnTjenestepensjonsforholdRequest.fom
            GBOHentTjenestepensjonInfoRequest.datoTom = GBOFinnTjenestepensjonsforholdRequest.tom
            GBOHentTjenestepensjonInfoRequest.tssEksternId = GBOFinnTjenestepensjonsforholdRequest.tssEksternId
        }

        return GBOHentTjenestepensjonInfoRequest
    }

    private fun mapGBOFinnTjenestepensjonsforholdRequestTOGBOHentTjenestepensjonForholdYtelseRequest(
        GBOFinnTjenestepensjonsforholdRequest: GBOFinnTjenestepensjonsforholdRequest?
    ): GBOHentTjenestepensjonForholdYtelseRequest? {
        var GBOHentTjenestepensjonForholdYtelseRequest: GBOHentTjenestepensjonForholdYtelseRequest? = null

        if (GBOFinnTjenestepensjonsforholdRequest != null) {
            GBOHentTjenestepensjonForholdYtelseRequest = GBOHentTjenestepensjonForholdYtelseRequest()
            GBOHentTjenestepensjonForholdYtelseRequest.forholdId = GBOFinnTjenestepensjonsforholdRequest.forholdId
            GBOHentTjenestepensjonForholdYtelseRequest.ytelseId = GBOFinnTjenestepensjonsforholdRequest.ytelseId
        }

        return GBOHentTjenestepensjonForholdYtelseRequest
    }

    private fun fillGBOTjenestepensjonWithSamhandlerInfo(tjenestepensjon: GBOTjenestepensjon?): GBOTjenestepensjon? {
        if (tjenestepensjon != null) {
            val tjenestepensjonForholdList = tjenestepensjon.tjenestepensjonForholdene

            if (tjenestepensjonForholdList != null) {
                var tjenestepensjonForhold: GBOTjenestepensjonForhold? = null
                val hentSamhandlerRequest = GBOHentSamhandlerRequest()
                var hentSamhandlerResponse: GBOSamhandler? = null
                var samhandlerAvdelingList: List<GBOAvdeling?>? = null
                var samhandlerAvdeling: GBOAvdeling? = null

                //Iterate through received list to fill in samhandlerInfo on each item in the list
                val iter: Iterator<GBOTjenestepensjonForhold> = tjenestepensjonForholdList.iterator()
                while (iter.hasNext()) {
                    tjenestepensjonForhold = iter.next()
                    if (tjenestepensjonForhold != null) {
                        //Create request-object for hentSamhandler-call
                        hentSamhandlerRequest.idTSSEkstern = tjenestepensjonForhold.tssEksternId
                        hentSamhandlerRequest.hentDetaljert = true
                        //Call hentSamhandler to get samhandlerInfo
                        try {
                            hentSamhandlerResponse =
                                locateService_SamhandlerPartner()!!.hentSamhandler(hentSamhandlerRequest)
                            if (hentSamhandlerResponse != null) {
                                //Get alternativId from the response's list alternativeIder where alternativeIdKode is "TPNR"

                                var altId: GBOAlternativId? = null
                                val altIdList = hentSamhandlerResponse.alternativeIder
                                if (altIdList != null) {
                                    var done = false
                                    val i: Iterator<GBOAlternativId> = altIdList.iterator()
                                    while (i.hasNext() && !done) {
                                        altId = i.next()
                                        if (altId != null && altId.alternativKode != null && altId.alternativKode == "TPNR") {
                                            tjenestepensjonForhold.tpnr = altId.alternativId
                                            done = true
                                        }
                                    }
                                }

                                //Get avdeling from the response and read avdelingNavn from avdeling.
                                samhandlerAvdelingList = hentSamhandlerResponse.avdelinger
                                if (samhandlerAvdelingList != null) {
                                    //The number of entries in this list will always be 1 for the call used here
                                    samhandlerAvdeling = samhandlerAvdelingList[0]
                                    if (samhandlerAvdeling != null) tjenestepensjonForhold.navn =
                                        samhandlerAvdeling.avdelingNavn
                                    tjenestepensjonForhold.avdelingType = samhandlerAvdeling!!.avdelingType
                                }
                            }
                        } catch (sbe: HentSamhandlerFaultSamhandlerIkkeFunnetMsg) {
                            //hentSamhandler might return FaultSamhandlerIkkeFunnet. This fault
                            //is ignored and processing continuous with next item in the list.
                        }
                    }
                }
            }
        }

        return tjenestepensjon
    }
}
