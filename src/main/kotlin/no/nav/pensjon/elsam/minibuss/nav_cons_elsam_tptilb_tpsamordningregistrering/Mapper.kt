package no.nav.pensjon.elsam.minibuss.nav_cons_elsam_tptilb_tpsamordningregistrering

import nav_cons_elsam_tptilb_tpsamordningregistrering.no.nav.asbo.OpprettRefusjonskravReqInt
import nav_lib_sto.no.nav.lib.sto.fault.*
import nav_lib_sto.no.nav.lib.sto.gbo.*
import no.nav.elsam.tpsamordningregistrering.v0_5.*
import no.nav.pensjon.elsam.minibuss.misc.formatWIDString
import javax.xml.datatype.DatatypeFactory
import javax.xml.datatype.XMLGregorianCalendar

object Mapper {
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

    private fun String.toXMLGregorianCalendar() = DatatypeFactory.newInstance().newXMLGregorianCalendar(this)

    private fun XMLGregorianCalendar.toDate() = toGregorianCalendar().time
}
