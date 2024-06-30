package no.nav.pensjon.elsam.minibuss.nav_cons_elsam_tptilb_tpsamordningregistrering

import jakarta.xml.bind.JAXBElement
import nav_cons_elsam_tptilb_tpsamordningregistrering.no.nav.asbo.HentSamordningsdataReqInt
import nav_cons_elsam_tptilb_tpsamordningregistrering.no.nav.asbo.LagreTPYtelseReqInt
import nav_cons_elsam_tptilb_tpsamordningregistrering.no.nav.asbo.OpprettRefusjonskravReqInt
import nav_cons_elsam_tptilb_tpsamordningregistrering.no.nav.asbo.SlettTPYtelseReqInt
import nav_cons_elsam_tptilb_tpsamordningregistrering.no.nav.tpsamordningregistrering.v1_0.asbo.BeregningUforetrygd
import nav_lib_frg.no.nav.lib.frg.fault.FaultPersonIkkeFunnet
import nav_lib_frg.no.nav.lib.frg.gbo.GBOPerson
import nav_lib_frg.no.nav.lib.frg.gbo.GBOTjenestepensjonForhold
import nav_lib_frg.no.nav.lib.frg.gbo.GBOTjenestepensjonYtelse
import nav_lib_pen.no.nav.lib.pen.gbo.*
import nav_lib_sak.no.nav.lib.sak.gbo.GBOArbeidOgAktivitetsvedtak
import nav_lib_sto.no.nav.lib.sto.fault.*
import nav_lib_sto.no.nav.lib.sto.gbo.*
import no.nav.elsam.tpsamordningregistrering.v0_5.*
import no.nav.elsam.tpsamordningregistrering.v0_7.Beregning
import no.nav.elsam.tpsamordningregistrering.v0_8.Beregning2011
import no.nav.elsam.tpsamordningregistrering.v1_0.HentSamordningsdataResp
import no.nav.elsam.tpsamordningregistrering.v1_0.LagreTPYtelseResp
import no.nav.elsam.tpsamordningregistrering.v1_0.Vedtak
import no.nav.pensjon.elsam.minibuss.DateUtil
import java.util.*
import javax.xml.datatype.DatatypeConfigurationException
import javax.xml.datatype.DatatypeFactory
import javax.xml.datatype.XMLGregorianCalendar
import javax.xml.namespace.QName
import kotlin.collections.ArrayList

object Mapper {
    @Throws(DatatypeConfigurationException::class)
    fun FaultKombinasjonInputTOFaultTPYtelseIkkeFunnet(FaultKombinasjonInput: FaultKombinasjonInput, FaultTPYtelseIkkeFunnet: FaultTPYtelseIkkeFunnet) {
        FaultTPYtelseIkkeFunnet.errorMessage = FaultKombinasjonInput.errorMessage // move (executionOrder=1)
        FaultTPYtelseIkkeFunnet.errorSource = FaultKombinasjonInput.errorSource // move (executionOrder=2)
        FaultTPYtelseIkkeFunnet.rootCause = FaultKombinasjonInput.rootCause // move (executionOrder=3)
        FaultTPYtelseIkkeFunnet.dateTimeStamp = FaultKombinasjonInput.dateTimeStamp?.toXMLGregorianCalendar() // move (executionOrder=4)
    }

    fun FaultPersonIkkeFunnetTOFaultGenerisk(FaultPersonIkkeFunnet: FaultPersonIkkeFunnet, FaultGenerisk: FaultGenerisk) {
        FaultGenerisk.errorCode = "UnknownId" // set (executionOrder=1)
        FaultGenerisk.errorDescription = FaultPersonIkkeFunnet.errorMessage // move (executionOrder=2)
    }

    @Throws(DatatypeConfigurationException::class)
    fun FaultRefKravAlleredeRegElUtenforFristTOFaultAlleredeMottattRefusjonskrav(FaultRefKravAlleredeRegElUtenforFrist: FaultRefKravAlleredeRegElUtenforFrist, FaultAlleredeMottattRefusjonskrav: FaultAlleredeMottattRefusjonskrav) {
        FaultAlleredeMottattRefusjonskrav.errorMessage = FaultRefKravAlleredeRegElUtenforFrist.errorMessage // move (executionOrder=1)
        FaultAlleredeMottattRefusjonskrav.errorSource = FaultRefKravAlleredeRegElUtenforFrist.errorSource // move (executionOrder=2)
        FaultAlleredeMottattRefusjonskrav.rootCause = FaultRefKravAlleredeRegElUtenforFrist.rootCause // move (executionOrder=3)
        FaultAlleredeMottattRefusjonskrav.dateTimeStamp = FaultRefKravAlleredeRegElUtenforFrist.dateTimeStamp?.toXMLGregorianCalendar() // move (executionOrder=4)
    }

    @Throws(DatatypeConfigurationException::class)
    fun FaultSamIdIkkeGyldigTOFaultSamordningsIdIkkeFunnet(FaultSamIdIkkeGyldig: FaultSamIdIkkeGyldig, FaultSamordningsIdIkkeFunnet: FaultSamordningsIdIkkeFunnet) {
        FaultSamordningsIdIkkeFunnet.errorMessage = FaultSamIdIkkeGyldig.errorMessage // move (executionOrder=1)
        FaultSamordningsIdIkkeFunnet.errorSource = FaultSamIdIkkeGyldig.errorSource // move (executionOrder=2)
        FaultSamordningsIdIkkeFunnet.rootCause = FaultSamIdIkkeGyldig.rootCause // move (executionOrder=3)
        FaultSamordningsIdIkkeFunnet.dateTimeStamp = FaultSamIdIkkeGyldig.dateTimeStamp?.toXMLGregorianCalendar() // move (executionOrder=4)
    }

    @Throws(DatatypeConfigurationException::class)
    fun FaultSvarUtenforPeriodeTOFaultRefusjonskravUtenforSamordningspliktigPeriode(FaultSvarUtenforPeriode: FaultSvarUtenforPeriode, FaultRefusjonskravUtenforSamordningspliktigPeriode: FaultRefusjonskravUtenforSamordningspliktigPeriode) {
        FaultRefusjonskravUtenforSamordningspliktigPeriode.errorMessage = FaultSvarUtenforPeriode.errorMessage // move (executionOrder=1)
        FaultRefusjonskravUtenforSamordningspliktigPeriode.errorSource = FaultSvarUtenforPeriode.errorSource // move (executionOrder=2)
        FaultRefusjonskravUtenforSamordningspliktigPeriode.rootCause = FaultSvarUtenforPeriode.rootCause // move (executionOrder=3)
        FaultRefusjonskravUtenforSamordningspliktigPeriode.dateTimeStamp = FaultSvarUtenforPeriode.dateTimeStamp?.toXMLGregorianCalendar() // move (executionOrder=4)
    }

    @Throws(DatatypeConfigurationException::class)
    fun FaultYtelseAlleredeRegistrertTOFaultTPYtelseAlleredeRegistrert(FaultYtelseAlleredeRegistrert: FaultYtelseAlleredeRegistrert, FaultTPYtelseAlleredeRegistrert: FaultTPYtelseAlleredeRegistrert) {
        FaultTPYtelseAlleredeRegistrert.errorMessage = FaultYtelseAlleredeRegistrert.errorMessage // move (executionOrder=1)
        FaultTPYtelseAlleredeRegistrert.errorSource = FaultYtelseAlleredeRegistrert.errorSource // move (executionOrder=2)
        FaultTPYtelseAlleredeRegistrert.rootCause = FaultYtelseAlleredeRegistrert.rootCause // move (executionOrder=3)
        FaultTPYtelseAlleredeRegistrert.dateTimeStamp = FaultYtelseAlleredeRegistrert.dateTimeStamp?.toXMLGregorianCalendar() // move (executionOrder=4)
    }

    @Throws(DatatypeConfigurationException::class)
    fun FaultYtelseIkkeIverksattTOFaultTPForholdIkkeIverksatt(FaultYtelseIkkeIverksatt: FaultYtelseIkkeIverksatt, FaultTPForholdIkkeIverksatt: FaultTPForholdIkkeIverksatt) {
        FaultTPForholdIkkeIverksatt.errorMessage = FaultYtelseIkkeIverksatt.errorMessage // move (executionOrder=1)
        FaultTPForholdIkkeIverksatt.errorSource = FaultYtelseIkkeIverksatt.errorSource // move (executionOrder=2)
        FaultTPForholdIkkeIverksatt.rootCause = FaultYtelseIkkeIverksatt.rootCause // move (executionOrder=3)
        FaultTPForholdIkkeIverksatt.dateTimeStamp = FaultYtelseIkkeIverksatt.dateTimeStamp?.toXMLGregorianCalendar() // move (executionOrder=4)
    }

    @Throws(DatatypeConfigurationException::class)
    fun GBOArbeidOgAktivitetsvedtakTOArbeidOgAktivitetsvedtak(GBOArbeidOgAktivitetsvedtak: GBOArbeidOgAktivitetsvedtak, ArbeidOgAktivitetsvedtak: ArbeidOgAktivitetsvedtak) {
        ArbeidOgAktivitetsvedtak.vedtakId = GBOArbeidOgAktivitetsvedtak.vedtakId // move (executionOrder=1)
        ArbeidOgAktivitetsvedtak.gjelderFnr = GBOArbeidOgAktivitetsvedtak.gjelderFnr // move (executionOrder=2)
        ArbeidOgAktivitetsvedtak.virkningFom = DateUtil.parseWIDString(GBOArbeidOgAktivitetsvedtak.virkningFom)?.toXMLGregorianCalendar() // custom.output assignment (executionOrder=3)
        ArbeidOgAktivitetsvedtak.virkningTom = DateUtil.parseWIDString(GBOArbeidOgAktivitetsvedtak.virkningTom)?.toXMLGregorianCalendar() // custom.output assignment (executionOrder=4)
        ArbeidOgAktivitetsvedtak.vedtaksKode = GBOArbeidOgAktivitetsvedtak.vedtakstypeKode // move (executionOrder=5)
        ArbeidOgAktivitetsvedtak.vedtakstatusKode = GBOArbeidOgAktivitetsvedtak.vedtakstatusKode // move (executionOrder=6)
        ArbeidOgAktivitetsvedtak.saksKode = GBOArbeidOgAktivitetsvedtak.sakstypeKode // move (executionOrder=7)
        ArbeidOgAktivitetsvedtak.navEnhet = GBOArbeidOgAktivitetsvedtak.navEnhet // move (executionOrder=8)
        ArbeidOgAktivitetsvedtak.utfallKode = GBOArbeidOgAktivitetsvedtak.utfallKode // move (executionOrder=9)
        ArbeidOgAktivitetsvedtak.rettighetKode = GBOArbeidOgAktivitetsvedtak.rettighetKode // move (executionOrder=10)
        ArbeidOgAktivitetsvedtak.dagsatsUBT = GBOArbeidOgAktivitetsvedtak.vedtaksfaktaAAP?.dagsatsUBT // move (executionOrder=11)
        ArbeidOgAktivitetsvedtak.dagsatsMBT = GBOArbeidOgAktivitetsvedtak.vedtaksfaktaAAP?.dagsatsMBT // move (executionOrder=12)
        ArbeidOgAktivitetsvedtak.dagstatsMBTFS = GBOArbeidOgAktivitetsvedtak.vedtaksfaktaAAP?.dagsatsMBTFS // move (executionOrder=13)
        ArbeidOgAktivitetsvedtak.antallBarn = GBOArbeidOgAktivitetsvedtak.vedtaksfaktaAAP?.antallBarn?.toInt() // move (executionOrder=14)
        ArbeidOgAktivitetsvedtak.beregningsgrunnlag = GBOArbeidOgAktivitetsvedtak.vedtaksfaktaAAP?.beregningsgrunnlag // move (executionOrder=15)
    }

    fun GBOBeregningNokkelinfoTOBeregningNokkelinfo(GBOBeregningNokkelinfo: GBOBeregningNokkelinfo, BeregningNokkelinfo: BeregningNokkelinfo) {
        BeregningNokkelinfo.fnr = GBOBeregningNokkelinfo.fnr // move (executionOrder=1)
        BeregningNokkelinfo.grunnlagsrolleKode = GBOBeregningNokkelinfo.grunnlagsrolleKode // move (executionOrder=2)
        GBOSluttpoengtallTOSluttpoengtall(GBOBeregningNokkelinfo.spt, BeregningNokkelinfo.spt) // submap (executionOrder=3)
        GBOSluttpoengtallTOSluttpoengtall(GBOBeregningNokkelinfo.ypt, BeregningNokkelinfo.ypt) // submap (executionOrder=4)
        GBOSluttpoengtallTOSluttpoengtall(GBOBeregningNokkelinfo.opt, BeregningNokkelinfo.opt) // submap (executionOrder=5)
        BeregningNokkelinfo.anvendtTrygdetid = GBOBeregningNokkelinfo.ttAnv?.toInt()?.toJAXBElement("anvendtTrygdetid") // move (executionOrder=6)
        BeregningNokkelinfo.anvendtIBeregningen = GBOBeregningNokkelinfo.anvendtIBeregningen?.toJAXBElement("anvendtIBeregningen") // move (executionOrder=7)
    }

    @Throws(DatatypeConfigurationException::class)
    fun GBOBeregningTOBeregning(GBOBeregning: GBOBeregning, Beregning_1: Beregning) {
        Beregning_1.afpPensjonsgrad = GBOBeregning.afpPensjonsgrad?.toInt()?.toJAXBElement("afpPensjonsgrad") // move (executionOrder=5)
        Beregning_1.antallBarnetilleggFellesbarn = GBOBeregning.barnetilleggFellesBarn?.antallBarn?.toInt()?.toJAXBElement("antallBarnetilleggFellesbarn") // move (executionOrder=12)
        Beregning_1.antallBarnetilleggSerkullsbarn = GBOBeregning.barnetilleggSerkullsbarn?.antallBarn?.toInt()?.toJAXBElement("antallBarnetilleggSerkullsbarn") // move (executionOrder=14)
        Beregning_1.brutto = GBOBeregning.brutto?.toInt()?.toJAXBElement("") // move (executionOrder=22)
        Beregning_1.bruttoAFPtillegg = GBOBeregning.afpTillegg?.brutto?.toInt()?.toJAXBElement("bruttoAFPtillegg") // move (executionOrder=10)
        Beregning_1.bruttoBarnetilleggFellesbarn = GBOBeregning.barnetilleggFellesBarn?.brutto?.toInt()?.toJAXBElement("bruttoBarnetilleggFellesbarn") // move (executionOrder=11)
        Beregning_1.bruttoBarnetilleggSerkullsbarn = GBOBeregning.barnetilleggSerkullsbarn?.brutto?.toInt()?.toJAXBElement("bruttoBarnetilleggSerkullsbarn") // move (executionOrder=13)
        Beregning_1.bruttoGrunnpensjon = GBOBeregning.grunnpensjon?.brutto?.toInt()?.toJAXBElement("bruttoGrunnpensjon") // move (executionOrder=6)
        Beregning_1.bruttoSertillegg = GBOBeregning.sertillegg?.brutto?.toInt()?.toJAXBElement("bruttoSertillegg") // move (executionOrder=8)
        Beregning_1.bruttoTilleggspensjon = GBOBeregning.tilleggspensjon?.brutto?.toInt()?.toJAXBElement("bruttoTilleggspensjon") // move (executionOrder=7)
        Beregning_1.ektefelletillegg = GBOBeregning.ektefelleTillegg?.netto?.toInt()?.toJAXBElement("ektefelletillegg") // move (executionOrder=28)
        Beregning_1.fremtidigPensjonsgivendeInntektBruker = GBOBeregning.inntektBruktIAvkortning?.toInt()?.toJAXBElement("fremtidigPensjonsgivendeInntektBruker") // move (executionOrder=34)
        Beregning_1.grunnpensjonsats = GBOBeregning.grunnpensjon?.grunnpensjonsats?.toJAXBElement("grunnpensjonsats") // move (executionOrder=21)

        Beregning_1.krigOgGammelYrkesskade = GBOBeregning.krigOgGammelYrkesskade?.let { src ->
            KrigOgGammelYrkesskade().also { dst ->
                dst.forholdstallYG = src.forholdstallYG?.toJAXBElement("forholdstallYG") // move (executionOrder=20)
                dst.grunnlag = src.grunnlagForUtbetaling?.toJAXBElement("grunnlag") // move (executionOrder=19)
                dst.pensjonsgrad = src.pensjonsgrad?.toJAXBElement("pensjonsgrad") // move (executionOrder=18)
                dst.belop = src.netto?.toInt()?.toJAXBElement("krigOgGammelYrkesskade") // move (executionOrder=33)
            }
        }

        Beregning_1.minstenivatilleggIndividuelt = GBOBeregning.minstenivatilleggIndividuelt?.netto?.toInt() // move (executionOrder=37)
        Beregning_1.minstenivatilleggPensjonistpar = GBOBeregning.minstenivatilleggPensjonistpar?.netto?.toInt() // move (executionOrder=36)
        Beregning_1.netto = GBOBeregning.netto?.toInt()?.toJAXBElement("") // move (executionOrder=23)
        Beregning_1.nettoAFPtillegg = GBOBeregning.afpTillegg?.netto?.toInt()?.toJAXBElement("nettoAFPtillegg") // move (executionOrder=27)
        Beregning_1.nettoBarnetilleggFellesbarn = GBOBeregning.barnetilleggFellesBarn?.netto?.toInt()?.toJAXBElement("nettoBarnetilleggFellesbarn") // move (executionOrder=29)
        Beregning_1.nettoBarnetilleggSerkullsbarn = GBOBeregning.barnetilleggSerkullsbarn?.netto?.toInt()?.toJAXBElement("nettoBarnetilleggSerkullsbarn") // move (executionOrder=30)
        Beregning_1.nettoGrunnpensjon = GBOBeregning.grunnpensjon?.netto?.toInt()?.toJAXBElement("nettoGrunnpensjon") // move (executionOrder=24)
        Beregning_1.nettoSertillegg = GBOBeregning.sertillegg?.netto?.toInt()?.toJAXBElement("nettoSertillegg") // move (executionOrder=26)
        Beregning_1.nettoTilleggspensjon = GBOBeregning.tilleggspensjon?.netto?.toInt()?.toJAXBElement("nettoTilleggspensjon") // move (executionOrder=25)
        Beregning_1.nettoVentetillegg = GBOBeregning.ventetillegg?.netto?.toInt()?.toJAXBElement("nettoVentetillegg") // move (executionOrder=31)
        Beregning_1.p67Berergning = GBOBeregning.p67Beregning?.toJAXBElement("p67Berergning") // move (executionOrder=4)
        Beregning_1.paragraf851Tillegg = GBOBeregning.paragraf851Tillegg?.netto?.toInt()?.toJAXBElement("paragraf851Tillegg") // move (executionOrder=32)
        Beregning_1.resultatKode = GBOBeregning.resultatKode // move (executionOrder=3)
        Beregning_1.sertilleggSats = GBOBeregning.sertillegg?.sertilleggsats?.toJAXBElement("sertilleggSats") // move (executionOrder=9)
        Beregning_1.uforegrad = GBOBeregning.uforegrad?.toInt()?.toJAXBElement("uforegrad") // move (executionOrder=17)
        Beregning_1.ventetilleggProsent = GBOBeregning.ventetillegg?.ventetilleggProsent?.toJAXBElement("ventetilleggProsent") // move (executionOrder=15)
        Beregning_1.virkningFom = GBOBeregning.virkDatoFom?.let { DateUtil.parseWIDString(it) }?.toXMLGregorianCalendar() // custom.output assignment (executionOrder=1)
        Beregning_1.virkningTom = GBOBeregning.virkDatoTom?.let { DateUtil.parseWIDString(it) }?.toXMLGregorianCalendar() // custom.output assignment (executionOrder=2)
        Beregning_1.yrkesskadegrad = GBOBeregning.yrkesskadegrad?.toInt()?.toJAXBElement("yrkesskadegrad") // move (executionOrder=16)

        Beregning_1.involvertePersonerNokkelinfoListe.addAll(GBOBeregning.beregningNokkelinfoListe.map { BeregningNokkelinfo().apply { GBOBeregningNokkelinfoTOBeregningNokkelinfo(it, this) } }) // submap (executionOrder=35)
    }

    fun GBOBeregningsResultatTOBeregning2011(GBOBeregningsResultat: GBOBeregningsResultat, Beregning2011: Beregning2011) {
        Beregning2011.afpKompensasjonstillegg = GBOBeregningsResultat.pensjonUnderUtbetaling.afpKompensasjonstillegg.brutto?.toInt() // move (executionOrder=35)
        Beregning2011.afpKronetillegg = GBOBeregningsResultat.pensjonUnderUtbetaling.afpKronetillegg.brutto?.toInt() // move (executionOrder=34)
        Beregning2011.afpLivsvarig = GBOBeregningsResultat.pensjonUnderUtbetaling.afpLivsvarig.brutto?.toInt() // move (executionOrder=33)
        Beregning2011.afpOpptjeningTotalbelop = GBOBeregningsResultat.beregningInformasjonKapittel19.utbetaltForrige?.toDouble() // move (executionOrder=37)
        Beregning2011.antallBarnetilleggFellesbarn = GBOBeregningsResultat.pensjonUnderUtbetaling.barnetilleggFellesbarn.antallBarn?.toInt() // move (executionOrder=27)
        Beregning2011.antallBarnetilleggSerkullsbarn = GBOBeregningsResultat.pensjonUnderUtbetaling.barnetilleggSerkullsbarn.antallBarn?.toInt() // move (executionOrder=30)
        Beregning2011.anvendtTrygdetid = GBOBeregningsResultat.beregningInformasjonKapittel19.ttAnv?.toInt() // move (executionOrder=24)
        Beregning2011.anvendtTrygdetidAvdod = GBOBeregningsResultat.beregningsinformasjonAvdod.ttAnv?.toInt() // move (executionOrder=43)
        Beregning2011.basisgp = GBOBeregningsResultat.beregningInformasjonKapittel19.basisgp?.toDouble() // move (executionOrder=4)
        Beregning2011.basispt = GBOBeregningsResultat.beregningInformasjonKapittel19.basispt?.toDouble() // move (executionOrder=6)
        Beregning2011.basistp = GBOBeregningsResultat.beregningInformasjonKapittel19.basistp?.toDouble() // move (executionOrder=5)
        Beregning2011.bruttoBarnetilleggFellesbarn = GBOBeregningsResultat.pensjonUnderUtbetaling.barnetilleggFellesbarn.brutto?.toInt() // move (executionOrder=25)
        Beregning2011.bruttoBarnetilleggSerkullsbarn = GBOBeregningsResultat.pensjonUnderUtbetaling.barnetilleggSerkullsbarn.brutto?.toInt() // move (executionOrder=28)
        Beregning2011.ektefelletillegg = GBOBeregningsResultat.pensjonUnderUtbetaling.ektefelletillegg.brutto?.toInt() // move (executionOrder=31)
        Beregning2011.fnrAvdod = GBOBeregningsResultat.beregningsinformasjonAvdod.fnr // move (executionOrder=38)
        Beregning2011.forholdstall67 = GBOBeregningsResultat.beregningInformasjonKapittel19.forholdstall67?.toDouble() // move (executionOrder=8)
        Beregning2011.forholdstallKompensasjonstillegg = GBOBeregningsResultat.pensjonUnderUtbetaling.afpKompensasjonstillegg.forholdstallKompensasjonstillegg?.toDouble() // move (executionOrder=36)
        Beregning2011.forholdstallUttak = GBOBeregningsResultat.beregningInformasjonKapittel19.forholdstallUttak?.toDouble() // move (executionOrder=7)
        Beregning2011.gpRestpensjon = GBOBeregningsResultat.beregningInformasjonKapittel19.gpRestpensjon?.toDouble() // move (executionOrder=10)
        Beregning2011.grunnpensjon = GBOBeregningsResultat.beregningInformasjonKapittel19.gp?.toDouble() // move (executionOrder=9)
        Beregning2011.grunnpensjonsats = GBOBeregningsResultat.pensjonUnderUtbetaling.grunnpensjon.grunnpensjonsats // move (executionOrder=21)
        Beregning2011.minstenivatilleggIndividuelt = GBOBeregningsResultat.pensjonUnderUtbetaling.minstenivatilleggIndividuelt.netto?.toInt() // move (executionOrder=44)
        Beregning2011.minstenivatilleggPensjonistpar = GBOBeregningsResultat.pensjonUnderUtbetaling.minstenivatilleggPensjonistpar.netto?.toInt() // move (executionOrder=32)
        Beregning2011.mndGrunnpensjon = GBOBeregningsResultat.pensjonUnderUtbetaling.grunnpensjon.netto?.toInt() // move (executionOrder=17)
        Beregning2011.mndPensjonstillegg = GBOBeregningsResultat.pensjonUnderUtbetaling.pensjonstillegg.netto?.toInt() // move (executionOrder=19)
        Beregning2011.mndTilleggspensjon = GBOBeregningsResultat.pensjonUnderUtbetaling.tilleggspensjon.netto?.toInt() // move (executionOrder=18)
        Beregning2011.nettoBarnetilleggFellesbarn = GBOBeregningsResultat.pensjonUnderUtbetaling.barnetilleggFellesbarn.netto?.toInt() // move (executionOrder=26)
        Beregning2011.nettoBarnetilleggSerkullsbarn = GBOBeregningsResultat.pensjonUnderUtbetaling.barnetilleggSerkullsbarn.netto?.toInt() // move (executionOrder=29)
        Beregning2011.pensjonstillegg = GBOBeregningsResultat.beregningInformasjonKapittel19.pt?.toDouble() // move (executionOrder=13)
        Beregning2011.ptRestpensjon = GBOBeregningsResultat.beregningInformasjonKapittel19.ptRestpensjon?.toDouble() // move (executionOrder=14)
        Beregning2011.resultatKode = GBOBeregningsResultat.beregningInformasjonKapittel19.resultatType // move (executionOrder=16)
        Beregning2011.skjermingstillegg = GBOBeregningsResultat.pensjonUnderUtbetaling.skjermingstillegg.netto?.toInt() // move (executionOrder=45)
        Beregning2011.tilleggspensjon = GBOBeregningsResultat.beregningInformasjonKapittel19.tp?.toDouble() // move (executionOrder=11)
        Beregning2011.totalBelop = GBOBeregningsResultat.pensjonUnderUtbetaling.totalBelop?.toInt() // move (executionOrder=15)
        Beregning2011.tpRestpensjon = GBOBeregningsResultat.beregningInformasjonKapittel19.tpRestpensjon?.toDouble() // move (executionOrder=12)
        Beregning2011.uforegrad = GBOBeregningsResultat.pensjonUnderUtbetaling.skjermingstillegg.uforegrad?.toInt() // move (executionOrder=46)
        Beregning2011.uttaksgrad = GBOBeregningsResultat.uttaksgrad?.toInt() // move (executionOrder=3)
        Beregning2011.virkningFom = GBOBeregningsResultat.virkFom?.let { DateUtil.parseWIDString(it) }?.toXMLGregorianCalendar() // custom.output assignment (executionOrder=1)
        Beregning2011.virkningTom = GBOBeregningsResultat.virkTom?.let { DateUtil.parseWIDString(it) }?.toXMLGregorianCalendar() // custom.output assignment (executionOrder=2)
        Beregning2011.yrkesskadegrad = GBOBeregningsResultat.beregningInformasjonKapittel19.yrkesskadegrad?.toDouble() // move (executionOrder=23)
        Beregning2011.yrkesskadegradAvdod = GBOBeregningsResultat.beregningsinformasjonAvdod.yrkesskadegrad?.toDouble() // move (executionOrder=42)

        GBOSluttpoengtallTOSluttpoengtall(GBOBeregningsResultat.beregningInformasjonKapittel19.spt, Beregning2011.spt) // submap (executionOrder=20)
        GBOSluttpoengtallTOSluttpoengtall(GBOBeregningsResultat.beregningInformasjonKapittel19.ypt, Beregning2011.ypt) // submap (executionOrder=22)
        GBOSluttpoengtallTOSluttpoengtall(GBOBeregningsResultat.beregningsinformasjonAvdod.spt, Beregning2011.sptAvdod) // submap (executionOrder=39)
        GBOSluttpoengtallTOSluttpoengtall(GBOBeregningsResultat.beregningsinformasjonAvdod.ypt, Beregning2011.yptAvdod) // submap (executionOrder=40)
        GBOSluttpoengtallTOSluttpoengtall(GBOBeregningsResultat.beregningsinformasjonAvdod.opt, Beregning2011.optAvdod) // submap (executionOrder=41)
    }

    fun GBOBeregningsResultatUforetrygdTOBeregningUforetrygd(GBOBeregningsResultatUforetrygd: GBOBeregningsResultatUforetrygd, BeregningUforetrygd: BeregningUforetrygd) {
        BeregningUforetrygd.anvendtTrygdetid = GBOBeregningsResultatUforetrygd.anvendtTrygdetid // move (executionOrder=17)
        BeregningUforetrygd.anvendtTrygdetidAvdod = GBOBeregningsResultatUforetrygd.anvendtTrygdetidAvdod // move (executionOrder=23)
        BeregningUforetrygd.benyttetSivilstand = GBOBeregningsResultatUforetrygd.benyttetSivilstand // move (executionOrder=18)
        BeregningUforetrygd.beregningsgrunnlagOrdiner = GBOBeregningsResultatUforetrygd.beregningsgrunnlagOrdiner // move (executionOrder=19)
        BeregningUforetrygd.beregningsgrunnlagOrdinerAvdod = GBOBeregningsResultatUforetrygd.beregningsgrunnlagOrdinerAvdod // move (executionOrder=21)
        BeregningUforetrygd.beregningsgrunnlagYrkesskade = GBOBeregningsResultatUforetrygd.beregningsgrunnlagYrkesskade // move (executionOrder=20)
        BeregningUforetrygd.beregningsgrunnlagYrkesskadeAvdod = GBOBeregningsResultatUforetrygd.beregningsgrunnlagYrkesskadeAvdod // move (executionOrder=22)
        BeregningUforetrygd.bruttoBarnetilleggFellesbarn = GBOBeregningsResultatUforetrygd.bruttoBarnetilleggFellesbarn // move (executionOrder=9)
        BeregningUforetrygd.bruttoBarnetilleggSerkullsbarn = GBOBeregningsResultatUforetrygd.bruttoBarnetilleggSerkullsbarn // move (executionOrder=7)
        BeregningUforetrygd.bruttoGjenlevendetillegg = GBOBeregningsResultatUforetrygd.bruttoGjenlevendetillegg // move (executionOrder=11)
        BeregningUforetrygd.bruttoUforetrygdOrdinar = GBOBeregningsResultatUforetrygd.bruttoUforetrygdOrdinar // move (executionOrder=3)
        BeregningUforetrygd.fom = GBOBeregningsResultatUforetrygd.virkFom?.let { DateUtil.parseWIDString(it) }?.toXMLGregorianCalendar() // custom.output assignment (executionOrder=14)
        BeregningUforetrygd.inntektBruktIInntektsavkorting = GBOBeregningsResultatUforetrygd.inntektBruktIInntektsavkorting // move (executionOrder=25)
        BeregningUforetrygd.inntektEtterUforhet = GBOBeregningsResultatUforetrygd.inntektEtterUforhet // move (executionOrder=16)
        BeregningUforetrygd.inntektForUforhet = GBOBeregningsResultatUforetrygd.inntektForUforhet // move (executionOrder=1)
        BeregningUforetrygd.nettoBarnetilleggFellesbarn = GBOBeregningsResultatUforetrygd.nettoBarnetilleggFellesbarn // move (executionOrder=8)
        BeregningUforetrygd.nettoBarnetilleggSerkullsbarn = GBOBeregningsResultatUforetrygd.nettoBarnetilleggSerkullsbarn // move (executionOrder=6)
        BeregningUforetrygd.nettoEktefelletillegg = GBOBeregningsResultatUforetrygd.nettoEktefelletillegg // move (executionOrder=12)
        BeregningUforetrygd.nettoGjenlevendetillegg = GBOBeregningsResultatUforetrygd.nettoGjenlevendetillegg // move (executionOrder=10)
        BeregningUforetrygd.nettoUforetrygdOrdinar = GBOBeregningsResultatUforetrygd.nettoUforetrygdOrdinar // move (executionOrder=2)
        BeregningUforetrygd.resultatKode = GBOBeregningsResultatUforetrygd.resultatKode // move (executionOrder=13)
        BeregningUforetrygd.tom = GBOBeregningsResultatUforetrygd.virkTom?.let { DateUtil.parseWIDString(it) }?.toXMLGregorianCalendar() // custom.output assignment (executionOrder=15)
        BeregningUforetrygd.uforegrad = GBOBeregningsResultatUforetrygd.uforegrad // move (executionOrder=4)
        BeregningUforetrygd.yrkesskadegrad = GBOBeregningsResultatUforetrygd.yrkesskadegrad // move (executionOrder=5)
        BeregningUforetrygd.yrkesskadegradAvdod = GBOBeregningsResultatUforetrygd.yrkesskadegradAvdod // move (executionOrder=24)
    }

    fun GBOPersonTOPerson(GBOPerson: GBOPerson, Person: Person) {
        Person.fnr = GBOPerson.fodselsnummer // move (executionOrder=1)
        Person.fornavn = GBOPerson.fornavn // move (executionOrder=2)
        Person.mellomnavn = GBOPerson.mellomnavn // move (executionOrder=3)
        Person.etternavn = GBOPerson.etternavn // move (executionOrder=4)
        Person.sivilstand = GBOPerson.sivilstand // move (executionOrder=5)
        Person.dodsdato = GBOPerson.dodsdato?.let { DateUtil.parseWIDString(it) }?.toXMLGregorianCalendar() // custom.output assignment (executionOrder=6)

        run {
            val GBOPerson: String? = GBOPerson // custom.input.forEach (executionOrder=7)
            val Person_utbetalingsadresse: String? = null // custom.output declaration (executionOrder=7)
            // The specific type of variable Person_utbetalingsadresse is commonj.sdo.DataObject
            if (GBOPerson != null && Person_utbetalingsadresse != null) {
                val person: DataObject = Person_utbetalingsadresse as DataObject

                if (GBOPerson.getDataObject("bostedsAdresse") != null) {
                    val bostedsAdresse: DataObject = GBOPerson.getDataObject("bostedsAdresse") as DataObject
                    val postnr: String = bostedsAdresse.getString("postnr")
                    if (postnr != null && postnr != "") {
                        person.setString("adresselinje1", bostedsAdresse.getString("boadresse1"))
                        person.setString("adresselinje2", bostedsAdresse.getString("boadresse2"))
                        person.setString("postnr", bostedsAdresse.getString("postnr"))
                        person.setString("poststed", bostedsAdresse.getString("poststed"))
                    }
                }

                var annenAdresse: DataObject? = null
                if (GBOPerson.getDataObject("postAdresse") != null) {
                    val postAdresse: DataObject = GBOPerson.getDataObject("postAdresse")
                    val postnr: String = postAdresse.getString("postnr")
                    val adresselinje1: String = postAdresse.getString("adresselinje1")
                    val land: String = postAdresse.getString("land")
                    if ((postnr != null && postnr != "")
                        || (adresselinje1 != null && adresselinje1 != "" && land != null && land != "")
                    ) {
                        annenAdresse = postAdresse
                    }
                }
                if (GBOPerson.getDataObject("tilleggsAdresse") != null) {
                    val tilleggsAdresse: DataObject = GBOPerson.getDataObject("tilleggsAdresse")
                    val postnr: String = tilleggsAdresse.getString("postnr")
                    if (postnr != null && postnr != "") {
                        annenAdresse = tilleggsAdresse
                    }
                }
                if (GBOPerson.getDataObject("utenlandsAdresse") != null) {
                    val utenlandsAdresse: DataObject = GBOPerson.getDataObject("utenlandsAdresse")
                    val adresselinje1: String = utenlandsAdresse.getString("adresselinje1")
                    val land: String = utenlandsAdresse.getString("land")
                    if (adresselinje1 != null && adresselinje1 != "" && land != null && land != "") {
                        annenAdresse = utenlandsAdresse
                    }
                }

                if (annenAdresse != null) {
                    person.setString("adresselinje1", annenAdresse.getString("adresselinje1"))
                    person.setString("adresselinje2", annenAdresse.getString("adresselinje2"))
                    person.setString("adresselinje3", annenAdresse.getString("adresselinje3"))
                    person.setString("postnr", annenAdresse.getString("postnr"))
                    person.setString("poststed", annenAdresse.getString("poststed"))
                    person.setString("land", annenAdresse.getString("land"))
                }
            }

            Person.setUtbetalingsadresse(Person_utbetalingsadresse) // custom.output assignment (executionOrder=7)
        }
    }

    fun GBOSamordningsdataTOHentSamordningsdataResp(GBOSamordningsdata: GBOSamordningsdata, HentSamordningsdataResp: HentSamordningsdataResp) {
        HentSamordningsdataResp.tpnr = GBOSamordningsdata.tpnr // move (executionOrder=1)
        GBOPersonTOPerson(GBOSamordningsdata.person, HentSamordningsdataResp.person) // submap (executionOrder=2)
        HentSamordningsdataResp.pensjonVedtakListe.addAll(GBOSamordningsdata.vedtakListe.vedtakListe.map { Vedtak().apply { GBOVedtakTOVedtak(it, this) } } ) // submap (executionOrder=3)
        HentSamordningsdataResp.arbeidVedtakListe.addAll(GBOSamordningsdata.arbeidOgAktivitetsvedtakListe.map { ArbeidOgAktivitetsvedtak().apply { GBOArbeidOgAktivitetsvedtakTOArbeidOgAktivitetsvedtak(it, this) } } ) // submap (executionOrder=4)
        HentSamordningsdataResp.ufullstendigeData = GBOSamordningsdata.ufullstendigeData // move (executionOrder=5)
        run {
            val GBOSamordningsdata_tjenestepensjonForholdListe: List<GBOTjenestepensjonForhold>? = GBOSamordningsdata.tjenestepensjonForholdListe // custom.input.forEach (executionOrder=6)
            val HentSamordningsdataResp_tjenestepensjonYtelseListe: MutableList<TPYtelse> = ArrayList() // custom.output declaration (executionOrder=6)

            // The specific type of variable GBOSamordningsdata_tjenestepensjonForholdListe is java.util.List
            // The specific type of variable HentSamordningsdataResp_tjenestepensjonYtelseListe is java.util.List
            if (GBOSamordningsdata_tjenestepensjonForholdListe != null && HentSamordningsdataResp_tjenestepensjonYtelseListe != null
            ) {
                // Clear list to work around WID bug until JR27952 is fixed
                (HentSamordningsdataResp_tjenestepensjonYtelseListe as MutableList<*>).clear()

                val iter: Iterator<GBOTjenestepensjonForhold> = GBOSamordningsdata_tjenestepensjonForholdListe.iterator()
                while (iter.hasNext()) {
                    val tpForhold: GBOTjenestepensjonForhold = iter.next()
                    val iterator: Iterator<GBOTjenestepensjonYtelse> = tpForhold.getTjenestepensjonYtelseListe().iterator()
                    while (iterator.hasNext()) {
                        val gboTpYtelse: GBOTjenestepensjonYtelse = iterator.next()
                        val tpYtelse = TPYtelse()
                        tpYtelse.tpnr = tpForhold.tpnr
                        tpYtelse.tpArt = gboTpYtelse.ytelseKode
                        if (gboTpYtelse.iverksattFom != null) {
                            tpYtelse.datoFom = DateUtil.parseWIDString(gboTpYtelse.iverksattFom)?.toXMLGregorianCalendar()
                        }
                        if (gboTpYtelse.iverksattTom != null) {
                            tpYtelse.datoTom = DateUtil.parseWIDString(gboTpYtelse.iverksattTom)?.toXMLGregorianCalendar()
                        }
                        HentSamordningsdataResp_tjenestepensjonYtelseListe.add(tpYtelse)
                    }
                }
            }
            HentSamordningsdataResp.getTjenestepensjonYtelseListe().addAll(HentSamordningsdataResp_tjenestepensjonYtelseListe) // custom.output assignment (executionOrder=6)
        }
    }

    fun GBOSamordningsdataTOLagreTPYtelseResp(GBOSamordningsdata: GBOSamordningsdata, LagreTPYtelseResp: LagreTPYtelseResp) {
        LagreTPYtelseResp.tpnr = GBOSamordningsdata.tpnr // move (executionOrder=1)
        GBOPersonTOPerson(GBOSamordningsdata.person, LagreTPYtelseResp.person) // submap (executionOrder=2)
        LagreTPYtelseResp.pensjonVedtakListe.addAll(GBOSamordningsdata.vedtakListe.vedtakListe.map { Vedtak().apply { GBOVedtakTOVedtak(it, this) } } ) // submap (executionOrder=3)
        LagreTPYtelseResp.arbeidOgAktivitetsvedtakListe.addAll(GBOSamordningsdata.arbeidOgAktivitetsvedtakListe.map { ArbeidOgAktivitetsvedtak().apply { GBOArbeidOgAktivitetsvedtakTOArbeidOgAktivitetsvedtak(it, this) } } ) // submap (executionOrder=4)
        run {
            val GBOSamordningsdata_tjenestepensjonForholdListe: List<GBOTjenestepensjonForhold>? = GBOSamordningsdata.tjenestepensjonForholdListe // custom.input.forEach (executionOrder=5)
            val LagreTPYtelseResp_tjenestepensjonYtelseListe: MutableList<TPYtelse> = ArrayList() // custom.output declaration (executionOrder=5)

            if (GBOSamordningsdata_tjenestepensjonForholdListe != null) {
                val iter: Iterator<GBOTjenestepensjonForhold> = GBOSamordningsdata_tjenestepensjonForholdListe.iterator()
                while (iter.hasNext()) {
                    val tpForhold: GBOTjenestepensjonForhold = iter.next()
                    val iterator: Iterator<GBOTjenestepensjonYtelse> = tpForhold.getTjenestepensjonYtelseListe().iterator()
                    while (iterator.hasNext()) {
                        val gboTpYtelse: GBOTjenestepensjonYtelse = iterator.next()
                        val tpYtelse = TPYtelse()
                        tpYtelse.tpnr = tpForhold.tpnr
                        tpYtelse.tpArt = gboTpYtelse.ytelseKode
                        if (gboTpYtelse.iverksattFom != null) {
                            tpYtelse.datoFom = DateUtil.parseWIDString(gboTpYtelse.iverksattFom)?.toXMLGregorianCalendar()
                        }
                        if (gboTpYtelse.iverksattTom != null) {
                            tpYtelse.datoTom = DateUtil.parseWIDString(gboTpYtelse.iverksattTom)?.toXMLGregorianCalendar()
                        }
                        LagreTPYtelseResp_tjenestepensjonYtelseListe.add(tpYtelse)
                    }
                }
            }
            LagreTPYtelseResp.getTjenestepensjonYtelseListe().addAll(LagreTPYtelseResp_tjenestepensjonYtelseListe) // custom.output assignment (executionOrder=5)
        }
        LagreTPYtelseResp.ufullstendigeData = GBOSamordningsdata.ufullstendigeData // move (executionOrder=6)
    }

    fun GBOSluttpoengtallTOSluttpoengtall(GBOSluttpoengtall: GBOSluttpoengtall, Sluttpoengtall: Sluttpoengtall) {
        Sluttpoengtall.poengtall = GBOSluttpoengtall.poengtall?.toDouble()?.toJAXBElement("poengtall") // move (executionOrder=1)

        Sluttpoengtall.poengrekke = GBOSluttpoengtall.poengrekke?.let { src ->
            Poengrekke().also { dst ->
                dst.pa = src.poengAr?.toInt()?.toJAXBElement("pa") // move (executionOrder=2)
                dst.paF92 = src.poengArFor92?.toInt()?.toJAXBElement("pa_f92") // move (executionOrder=3)
                dst.paE91 = src.poengArEtter91?.toInt()?.toJAXBElement("pa_e91") // move (executionOrder=4)
                dst.tpiFaktor = src.tidligerePenInntektFaktor?.toDouble()?.toJAXBElement("tpiFaktor") // move (executionOrder=5)
            }
        }
    }

    fun GBOVedtakTOVedtak(GBOVedtak: GBOVedtak, Vedtak: Vedtak) {
        Vedtak.saksKode = GBOVedtak.saksKode // move (executionOrder=1)
        Vedtak.vedtakId = GBOVedtak.vedtakId // move (executionOrder=2)
        Vedtak.vedtaksKode = GBOVedtak.vedtaksKode // move (executionOrder=3)
        Vedtak.regelverkKode = GBOVedtak.kravHode.regelverkKode // move (executionOrder=4)
        Vedtak.virkningFom = GBOVedtak.virkningFom?.let { DateUtil.parseWIDString(it) }?.toXMLGregorianCalendar() // custom.output assignment (executionOrder=9)
        Vedtak.virkningTom = GBOVedtak.virkningTom?.let { DateUtil.parseWIDString(it) }?.toXMLGregorianCalendar() // custom.output assignment (executionOrder=10)
        Vedtak.typeKrigspensjon = GBOVedtak.kravHode.kravVelgKode?.takeIf { gyldigeKrigspensjonKoder.contains(it) } // custom.output assignment (executionOrder=11)

        Vedtak.beregningListe.addAll(GBOVedtak.beregningListe.map { Beregning().apply { GBOBeregningTOBeregning(it, this) } }) // submap (executionOrder=5)
        Vedtak.beregning2011Liste.addAll(GBOVedtak.beregningsResultatListe.map { Beregning2011().apply { GBOBeregningsResultatTOBeregning2011(it, this) } } ) // submap (executionOrder=6)
        Vedtak.barnListe.addAll(GBOVedtak.vilkarsvedtakListe.map { Vilkarsvedtak().apply { GBOVilkarsvedtakTOVilkarsvedtak(it, this) } } ) // submap (executionOrder=7)
        Vedtak.beregningUforetrygdListe.addAll(GBOVedtak.beregningsresultatUforetrygdListe.map { BeregningUforetrygd().apply { GBOBeregningsResultatUforetrygdTOBeregningUforetrygd(it, this) } }) // submap (executionOrder=8)
    }

    fun GBOVilkarsvedtakTOVilkarsvedtak(GBOVilkarsvedtak: GBOVilkarsvedtak, Vilkarsvedtak: Vilkarsvedtak) {
        Vilkarsvedtak.gjelderFnr = GBOVilkarsvedtak.gjelderFnr // move (executionOrder=1)
        Vilkarsvedtak.tom = GBOVilkarsvedtak.virkningTom?.let { DateUtil.parseWIDString(it) }?.toXMLGregorianCalendar() // custom.output assignment (executionOrder=2)
        Vilkarsvedtak.fom = GBOVilkarsvedtak.virkningFom?.let { DateUtil.parseWIDString(it) }?.toXMLGregorianCalendar() // custom.output assignment (executionOrder=3)
    }

    fun HentSamordningsdataReqIntTOGBOHentSamordningsdataRequest(HentSamordningsdataReqInt: HentSamordningsdataReqInt, GBOHentSamordningsdataRequest: GBOHentSamordningsdataRequest) {
        GBOHentSamordningsdataRequest.svarBrev = false // set (executionOrder=1)
        GBOHentSamordningsdataRequest.tpnr = HentSamordningsdataReqInt.extRequest.tpnr // move (executionOrder=2)
        GBOHentSamordningsdataRequest.fnr = HentSamordningsdataReqInt.extRequest.fnr // move (executionOrder=3)
        GBOHentSamordningsdataRequest.fom = HentSamordningsdataReqInt.extRequest.datoFom?.let { DateUtil.formatWIDString(it.toDate()) } // custom.output assignment (executionOrder=4)
        GBOHentSamordningsdataRequest.tssEksternId = HentSamordningsdataReqInt.tssEksternId // move (executionOrder=5)
    }

    fun LagreTPYtelseReqIntTOGBOOpprettTPSamordningRequest(LagreTPYtelseReqInt: LagreTPYtelseReqInt, GBOOpprettTPSamordningRequest: GBOOpprettTPSamordningRequest) {
        GBOOpprettTPSamordningRequest.svarBrev = false // set (executionOrder=1)
        GBOOpprettTPSamordningRequest.tpnr = LagreTPYtelseReqInt.extRequest.tpnr // move (executionOrder=2)
        GBOOpprettTPSamordningRequest.fnr = LagreTPYtelseReqInt.extRequest.fnr // move (executionOrder=3)
        GBOOpprettTPSamordningRequest.iverksattFom = LagreTPYtelseReqInt.extRequest.datoFom?.let { DateUtil.formatWIDString(it.toDate()) } // custom.output assignment (executionOrder=4)
        GBOOpprettTPSamordningRequest.tssEksternId = LagreTPYtelseReqInt.tssEksternId // move (executionOrder=5)
        GBOOpprettTPSamordningRequest.ytelseKode = LagreTPYtelseReqInt.extRequest.tpArt // move (executionOrder=6)
        GBOOpprettTPSamordningRequest.innmeldtFom = DateUtil.formatWIDString(LagreTPYtelseReqInt.extRequest.datoFomMedlemskap?.toDate()) // custom.output assignment (executionOrder=7)
    }

    fun OpprettRefusjonskravReqIntTOGBOOpprettRefusjonskravRequest(OpprettRefusjonskravReqInt: OpprettRefusjonskravReqInt, GBOOpprettRefusjonskravRequest: GBOOpprettRefusjonskravRequest) {
        GBOOpprettRefusjonskravRequest.svarRefusjonskrav.tpnr = OpprettRefusjonskravReqInt.extRequest.tpnr // move (executionOrder=1)
        GBOOpprettRefusjonskravRequest.svarRefusjonskrav.fnr = OpprettRefusjonskravReqInt.extRequest.fnr // move (executionOrder=2)
        GBOOpprettRefusjonskravRequest.svarRefusjonskrav.samId = OpprettRefusjonskravReqInt.extRequest.samordningsmeldingId // move (executionOrder=3)
        GBOOpprettRefusjonskravRequest.svarRefusjonskrav.refusjonskrav = OpprettRefusjonskravReqInt.extRequest.refusjonskrav // move (executionOrder=4)
        GBOOpprettRefusjonskravRequest.svarRefusjonskrav.tssEksternId = OpprettRefusjonskravReqInt.tssEksternId // move (executionOrder=6)
        GBOOpprettRefusjonskravRequest.svarRefusjonskrav.periodisertBelopListe.addAll(OpprettRefusjonskravReqInt.extRequest.periodisertBelopListe.map { GBOPeriodisertBelop().apply { PeriodisertBelopTOGBOPeriodisertBelop(it, this) } }) // submap (executionOrder=5)
    }

    fun PeriodisertBelopTOGBOPeriodisertBelop(PeriodisertBelop: PeriodisertBelop, GBOPeriodisertBelop: GBOPeriodisertBelop) {
        GBOPeriodisertBelop.belop = PeriodisertBelop.belop.toString() // move (executionOrder=1)
        GBOPeriodisertBelop.kravstillersRef = PeriodisertBelop.kravstillersReferanse // move (executionOrder=2)
        GBOPeriodisertBelop.datoFom = PeriodisertBelop.datoFom?.let { DateUtil.formatWIDString(it.toDate()) } // custom.output assignment (executionOrder=3)
        GBOPeriodisertBelop.datoTom = PeriodisertBelop.datoTom?.let { DateUtil.formatWIDString(it.toDate()) } // custom.output assignment (executionOrder=4)
    }

    fun SlettTPYtelseReqIntTOGBOSlettTPSamordningRequest(SlettTPYtelseReqInt: SlettTPYtelseReqInt, GBOSlettTPSamordningRequest: GBOSlettTPSamordningRequest) {
        GBOSlettTPSamordningRequest.tpnr = SlettTPYtelseReqInt.extRequest.tpnr // move (executionOrder=1)
        GBOSlettTPSamordningRequest.fnr = SlettTPYtelseReqInt.extRequest.fnr // move (executionOrder=2)
        GBOSlettTPSamordningRequest.meldingKode = SlettTPYtelseReqInt.extRequest.henvendelseType // move (executionOrder=3)
        GBOSlettTPSamordningRequest.iverksattFom = DateUtil.formatWIDString(SlettTPYtelseReqInt.extRequest.datoFom?.toDate()) // custom.output assignment (executionOrder=4)
        GBOSlettTPSamordningRequest.iverksattTom = DateUtil.formatWIDString(SlettTPYtelseReqInt.extRequest.datoTom?.toDate()) // custom.output assignment (executionOrder=5)
        GBOSlettTPSamordningRequest.tssEksternId = SlettTPYtelseReqInt.tssEksternId // move (executionOrder=6)
        GBOSlettTPSamordningRequest.ytelseKode = SlettTPYtelseReqInt.extRequest.tpArt // move (executionOrder=7)
    }

    private inline fun <reified T> T.toJAXBElement(localPart: String): JAXBElement<T> {
        return JAXBElement(QName(localPart), T::class.java, this)
    }

    private fun Date.toXMLGregorianCalendar() = DatatypeFactory.newInstance().newXMLGregorianCalendar(GregorianCalendar().also { it.time = this })

    private fun String.toXMLGregorianCalendar() = DatatypeFactory.newInstance().newXMLGregorianCalendar(this)

    private fun XMLGregorianCalendar.toDate() = toGregorianCalendar().time

    val gyldigeKrigspensjonKoder = setOf(
        "MIL_INV",
        "MIL_GJENLEV",
        "MIL_BARNEP",
        "SIVIL_INV",
        "SIVIL_GJENLEV",
        "SIVIL_BARNEP",
        "UP",
        "EP",
        "BP"
    )
}
