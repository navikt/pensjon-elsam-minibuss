package no.nav.pensjon.elsam.minibuss.nav_cons_elsam_tptilb_tpsamordningregistrering

import nav_cons_elsam_tptilb_tpsamordningregistrering.no.nav.asbo.OpprettRefusjonskravReqInt
import nav_cons_elsam_tptilb_tpsamordningregistrering.no.nav.asbo.SlettTPYtelseReqInt
import nav_lib_frg.no.nav.lib.frg.fault.FaultPersonIkkeFunnet
import nav_lib_sto.no.nav.lib.sto.fault.*
import nav_lib_sto.no.nav.lib.sto.gbo.*
import no.nav.elsam.tpsamordningregistrering.v0_5.*
import no.nav.pensjon.elsam.minibuss.misc.formatWIDString
import javax.xml.datatype.DatatypeFactory
import javax.xml.datatype.XMLGregorianCalendar

object Mapper {
    // FaultKombinasjonInputTOFaultTPYtelseIkkeFunnet.map
    fun FaultKombinasjonInput.toFaultTPYtelseIkkeFunnet() =
        FaultTPYtelseIkkeFunnet().also {
            it.errorMessage = errorMessage // move (executionOrder=1)
            it.errorSource = errorSource // move (executionOrder=2)
            it.rootCause = rootCause // move (executionOrder=3)
            it.dateTimeStamp = dateTimeStamp?.toXMLGregorianCalendar() // move (executionOrder=4)
        }

    // FaultPersonIkkeFunnetTOFaultGenerisk.map
    fun FaultPersonIkkeFunnet.toFaultGenerisk() =
        FaultGenerisk().also {
            it.errorCode = "UnknownId" // set (executionOrder=1)
            it.errorDescription = errorMessage // move (executionOrder=2)
        }

    // FaultRefKravAlleredeRegElUtenforFristTOFaultAlleredeMottattRefusjonskrav.map
    fun FaultRefKravAlleredeRegElUtenforFrist.toFaultAlleredeMottattRefusjonskrav() =
        FaultAlleredeMottattRefusjonskrav().also {
            it.errorMessage = errorMessage // move (executionOrder=1)
            it.errorSource = errorSource // move (executionOrder=2)
            it.rootCause = rootCause // move (executionOrder=3)
            it.dateTimeStamp = dateTimeStamp?.toXMLGregorianCalendar() // move (executionOrder=4)
        }

    // FaultSamIdIkkeGyldigTOFaultSamordningsIdIkkeFunnet.map
    fun FaultSamIdIkkeGyldig.toFaultSamordningsIdIkkeFunnet() =
        FaultSamordningsIdIkkeFunnet().also {
            it.errorMessage = errorMessage // move (executionOrder=1)
            it.errorSource = errorSource // move (executionOrder=2)
            it.rootCause = rootCause // move (executionOrder=3)
            it.dateTimeStamp = dateTimeStamp?.toXMLGregorianCalendar() // move (executionOrder=4)
        }

    // FaultSvarUtenforPeriodeTOFaultRefusjonskravUtenforSamordningspliktigPeriode.map
    fun FaultSvarUtenforPeriode.toFaultRefusjonskravUtenforSamordningspliktigPeriode() =
        FaultRefusjonskravUtenforSamordningspliktigPeriode().also {
            it.errorMessage = errorMessage // move (executionOrder=1)
            it.errorSource = errorSource // move (executionOrder=2)
            it.rootCause = rootCause // move (executionOrder=3)
            it.dateTimeStamp = dateTimeStamp?.toXMLGregorianCalendar() // move (executionOrder=4)
        }

    // OpprettRefusjonskravReqIntTOGBOOpprettRefusjonskravRequest.map
    fun OpprettRefusjonskravReqInt.toGBOOpprettRefusjonskravRequest() =
        GBOOpprettRefusjonskravRequest().also {
            it.svarRefusjonskrav.tpnr = extRequest?.tpnr // move (executionOrder=1)
            it.svarRefusjonskrav.fnr = extRequest?.fnr // move (executionOrder=2)
            it.svarRefusjonskrav.samId = extRequest?.samordningsmeldingId // move (executionOrder=3)
            it.svarRefusjonskrav.refusjonskrav = extRequest?.refusjonskrav // move (executionOrder=4)
            it.svarRefusjonskrav.tssEksternId = tssEksternId // move (executionOrder=6)
            it.svarRefusjonskrav.periodisertBelopListe += extRequest?.periodisertBelopListe?.map { x -> x.toGBOPeriodisertBelop() }.orEmpty() // submap (executionOrder=5)
        }

    // PeriodisertBelopTOGBOPeriodisertBelop.map
    private fun PeriodisertBelop.toGBOPeriodisertBelop() =
        GBOPeriodisertBelop().also {
            it.belop = belop.toString() // move (executionOrder=1)
            it.kravstillersRef = kravstillersReferanse // move (executionOrder=2)
            it.datoFom = datoFom?.let { x -> formatWIDString(x.toDate()) } // custom.output assignment (executionOrder=3)
            it.datoTom = datoTom?.let { x -> formatWIDString(x.toDate()) } // custom.output assignment (executionOrder=4)
        }

    // SlettTPYtelseReqIntTOGBOSlettTPSamordningRequest.map
    fun SlettTPYtelseReqInt.toGBOSlettTPSamordningRequest() =
        GBOSlettTPSamordningRequest().also {
            it.tpnr = extRequest.tpnr // move (executionOrder=1)
            it.fnr = extRequest.fnr // move (executionOrder=2)
            it.meldingKode = extRequest.henvendelseType // move (executionOrder=3)
            it.iverksattFom = extRequest.datoFom?.toDate()?.let { x -> formatWIDString(x) } // custom.output assignment (executionOrder=4)
            it.iverksattTom = extRequest.datoTom?.toDate()?.let { x -> formatWIDString(x) } // custom.output assignment (executionOrder=5)
            it.tssEksternId = tssEksternId // move (executionOrder=6)
            it.ytelseKode = extRequest.tpArt // move (executionOrder=7)
        }

    private fun String.toXMLGregorianCalendar() = DatatypeFactory.newInstance().newXMLGregorianCalendar(this)

    private fun XMLGregorianCalendar.toDate() = toGregorianCalendar().time
}
