package no.nav.pensjon.elsam.minibuss.nav_cons_elsam_tptilb_tpsamordningregistrering

import jakarta.xml.bind.JAXBElement
import nav_cons_elsam_tptilb_tpsamordningregistrering.no.nav.asbo.HentSamordningsdataReqInt
import nav_cons_elsam_tptilb_tpsamordningregistrering.no.nav.asbo.LagreTPYtelseReqInt
import nav_cons_elsam_tptilb_tpsamordningregistrering.no.nav.asbo.OpprettRefusjonskravReqInt
import nav_cons_elsam_tptilb_tpsamordningregistrering.no.nav.asbo.SlettTPYtelseReqInt
import nav_cons_elsam_tptilb_tpsamordningregistrering.no.nav.tpsamordningregistrering.v1_0.asbo.BeregningUforetrygd
import nav_lib_frg.no.nav.lib.frg.fault.FaultPersonIkkeFunnet
import nav_lib_frg.no.nav.lib.frg.gbo.GBOAnnenAdresse
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
import no.nav.pensjon.elsam.minibuss.misc.formatWIDString
import no.nav.pensjon.elsam.minibuss.misc.parseWIDString
import java.util.*
import javax.xml.datatype.DatatypeFactory
import javax.xml.datatype.XMLGregorianCalendar
import javax.xml.namespace.QName
import kotlin.collections.ArrayList

object Mapper {
    // FaultKombinasjonInputTOFaultTPYtelseIkkeFunnet
    fun FaultKombinasjonInput.toFaultTPYtelseIkkeFunnet() =
        FaultTPYtelseIkkeFunnet().also {
            it.errorMessage = errorMessage // move (executionOrder=1)
            it.errorSource = errorSource // move (executionOrder=2)
            it.rootCause = rootCause // move (executionOrder=3)
            it.dateTimeStamp = dateTimeStamp?.toXMLGregorianCalendar() // move (executionOrder=4)
        }

    // FaultPersonIkkeFunnetTOFaultGenerisk
    fun FaultPersonIkkeFunnet.toFaultGenerisk() =
        FaultGenerisk().also {
            it.errorCode = "UnknownId" // set (executionOrder=1)
            it.errorDescription = errorMessage // move (executionOrder=2)
        }

    // FaultRefKravAlleredeRegElUtenforFristTOFaultAlleredeMottattRefusjonskrav
    fun FaultRefKravAlleredeRegElUtenforFrist.toFaultAlleredeMottattRefusjonskrav() =
        FaultAlleredeMottattRefusjonskrav().also {
            it.errorMessage = errorMessage // move (executionOrder=1)
            it.errorSource = errorSource // move (executionOrder=2)
            it.rootCause = rootCause // move (executionOrder=3)
            it.dateTimeStamp = dateTimeStamp?.toXMLGregorianCalendar() // move (executionOrder=4)
        }

    // FaultSamIdIkkeGyldigTOFaultSamordningsIdIkkeFunnet
    fun FaultSamIdIkkeGyldig.toFaultSamordningsIdIkkeFunnet() =
        FaultSamordningsIdIkkeFunnet().also {
            it.errorMessage = errorMessage // move (executionOrder=1)
            it.errorSource = errorSource // move (executionOrder=2)
            it.rootCause = rootCause // move (executionOrder=3)
            it.dateTimeStamp = dateTimeStamp?.toXMLGregorianCalendar() // move (executionOrder=4)
        }

    // FaultSvarUtenforPeriodeTOFaultRefusjonskravUtenforSamordningspliktigPeriode
    fun FaultSvarUtenforPeriode.toFaultRefusjonskravUtenforSamordningspliktigPeriode() =
        FaultRefusjonskravUtenforSamordningspliktigPeriode().also {
            it.errorMessage = errorMessage // move (executionOrder=1)
            it.errorSource = errorSource // move (executionOrder=2)
            it.rootCause = rootCause // move (executionOrder=3)
            it.dateTimeStamp = dateTimeStamp?.toXMLGregorianCalendar() // move (executionOrder=4)
        }

    // FaultYtelseAlleredeRegistrertTOFaultTPYtelseAlleredeRegistrert
    fun FaultYtelseAlleredeRegistrert.toFaultTPYtelseAlleredeRegistrert() =
        FaultTPYtelseAlleredeRegistrert().also {
            it.errorMessage = errorMessage // move (executionOrder=1)
            it.errorSource = errorSource // move (executionOrder=2)
            it.rootCause = rootCause // move (executionOrder=3)
            it.dateTimeStamp = dateTimeStamp?.toXMLGregorianCalendar() // move (executionOrder=4)
        }

    // FaultYtelseIkkeIverksattTOFaultTPForholdIkkeIverksatt
    fun FaultYtelseIkkeIverksatt.toFaultTPForholdIkkeIverksatt() =
        FaultTPForholdIkkeIverksatt().also {
            it.errorMessage = errorMessage // move (executionOrder=1)
            it.errorSource = errorSource // move (executionOrder=2)
            it.rootCause = rootCause // move (executionOrder=3)
            it.dateTimeStamp = dateTimeStamp?.toXMLGregorianCalendar() // move (executionOrder=4)
        }

    // GBOArbeidOgAktivitetsvedtakTOArbeidOgAktivitetsvedtak
    private fun GBOArbeidOgAktivitetsvedtak.toArbeidOgAktivitetsvedtak() =
        ArbeidOgAktivitetsvedtak().also {
            it.vedtakId = vedtakId // move (executionOrder=1)
            it.gjelderFnr = gjelderFnr // move (executionOrder=2)
            it.virkningFom = virkningFom?.let { x -> parseWIDString(x) }?.toXMLGregorianCalendar() // custom.output assignment (executionOrder=3)
            it.virkningTom = virkningTom?.let { x -> parseWIDString(x) }?.toXMLGregorianCalendar() // custom.output assignment (executionOrder=4)
            it.vedtaksKode = vedtakstypeKode // move (executionOrder=5)
            it.vedtakstatusKode = vedtakstatusKode // move (executionOrder=6)
            it.saksKode = sakstypeKode // move (executionOrder=7)
            it.navEnhet = navEnhet // move (executionOrder=8)
            it.utfallKode = utfallKode // move (executionOrder=9)
            it.rettighetKode = rettighetKode // move (executionOrder=10)
            it.dagsatsUBT = vedtaksfaktaAAP?.dagsatsUBT // move (executionOrder=11)
            it.dagsatsMBT = vedtaksfaktaAAP?.dagsatsMBT // move (executionOrder=12)
            it.dagstatsMBTFS = vedtaksfaktaAAP?.dagsatsMBTFS // move (executionOrder=13)
            it.antallBarn = vedtaksfaktaAAP?.antallBarn?.toInt() // move (executionOrder=14)
            it.beregningsgrunnlag = vedtaksfaktaAAP?.beregningsgrunnlag // move (executionOrder=15)
        }

    // GBOBeregningNokkelinfoTOBeregningNokkelinfo
    private fun GBOBeregningNokkelinfo.toBeregningNokkelinfo() =
        BeregningNokkelinfo().also {
            it.anvendtIBeregningen = anvendtIBeregningen?.toJAXBElement("anvendtIBeregningen") // move (executionOrder=7)
            it.anvendtTrygdetid = ttAnv?.toInt()?.toJAXBElement("anvendtTrygdetid") // move (executionOrder=6)
            it.fnr = fnr // move (executionOrder=1)
            it.grunnlagsrolleKode = grunnlagsrolleKode // move (executionOrder=2)
            it.opt = opt?.toSluttpoengtall() // submap (executionOrder=5)
            it.spt = spt?.toSluttpoengtall() // submap (executionOrder=3)
            it.ypt = ypt?.toSluttpoengtall() // submap (executionOrder=4)
        }

    // GBOBeregningTOBeregning
    private fun GBOBeregning.toBeregning() =
        Beregning().also {
            it.afpPensjonsgrad = afpPensjonsgrad?.toInt()?.toJAXBElement("afpPensjonsgrad") // move (executionOrder=5)
            it.antallBarnetilleggFellesbarn = barnetilleggFellesBarn?.antallBarn?.toInt()?.toJAXBElement("antallBarnetilleggFellesbarn") // move (executionOrder=12)
            it.antallBarnetilleggSerkullsbarn = barnetilleggSerkullsbarn?.antallBarn?.toInt()?.toJAXBElement("antallBarnetilleggSerkullsbarn") // move (executionOrder=14)
            it.brutto = brutto?.toInt()?.toJAXBElement("") // move (executionOrder=22)
            it.bruttoAFPtillegg = afpTillegg?.brutto?.toInt()?.toJAXBElement("bruttoAFPtillegg") // move (executionOrder=10)
            it.bruttoBarnetilleggFellesbarn = barnetilleggFellesBarn?.brutto?.toInt()?.toJAXBElement("bruttoBarnetilleggFellesbarn") // move (executionOrder=11)
            it.bruttoBarnetilleggSerkullsbarn = barnetilleggSerkullsbarn?.brutto?.toInt()?.toJAXBElement("bruttoBarnetilleggSerkullsbarn") // move (executionOrder=13)
            it.bruttoGrunnpensjon = grunnpensjon?.brutto?.toInt()?.toJAXBElement("bruttoGrunnpensjon") // move (executionOrder=6)
            it.bruttoSertillegg = sertillegg?.brutto?.toInt()?.toJAXBElement("bruttoSertillegg") // move (executionOrder=8)
            it.bruttoTilleggspensjon = tilleggspensjon?.brutto?.toInt()?.toJAXBElement("bruttoTilleggspensjon") // move (executionOrder=7)
            it.ektefelletillegg = ektefelleTillegg?.netto?.toInt()?.toJAXBElement("ektefelletillegg") // move (executionOrder=28)
            it.fremtidigPensjonsgivendeInntektBruker = inntektBruktIAvkortning?.toInt()?.toJAXBElement("fremtidigPensjonsgivendeInntektBruker") // move (executionOrder=34)
            it.grunnpensjonsats = grunnpensjon?.grunnpensjonsats?.toJAXBElement("grunnpensjonsats") // move (executionOrder=21)

            it.krigOgGammelYrkesskade = krigOgGammelYrkesskade?.let { src ->
                KrigOgGammelYrkesskade().also { dst ->
                    dst.forholdstallYG = src.forholdstallYG?.toJAXBElement("forholdstallYG") // move (executionOrder=20)
                    dst.grunnlag = src.grunnlagForUtbetaling?.toJAXBElement("grunnlag") // move (executionOrder=19)
                    dst.pensjonsgrad = src.pensjonsgrad?.toJAXBElement("pensjonsgrad") // move (executionOrder=18)
                    dst.belop = src.netto?.toInt()?.toJAXBElement("krigOgGammelYrkesskade") // move (executionOrder=33)
                }
            }

            it.minstenivatilleggIndividuelt = minstenivatilleggIndividuelt?.netto?.toInt() // move (executionOrder=37)
            it.minstenivatilleggPensjonistpar = minstenivatilleggPensjonistpar?.netto?.toInt() // move (executionOrder=36)
            it.netto = netto?.toInt()?.toJAXBElement("") // move (executionOrder=23)
            it.nettoAFPtillegg = afpTillegg?.netto?.toInt()?.toJAXBElement("nettoAFPtillegg") // move (executionOrder=27)
            it.nettoBarnetilleggFellesbarn = barnetilleggFellesBarn?.netto?.toInt()?.toJAXBElement("nettoBarnetilleggFellesbarn") // move (executionOrder=29)
            it.nettoBarnetilleggSerkullsbarn = barnetilleggSerkullsbarn?.netto?.toInt()?.toJAXBElement("nettoBarnetilleggSerkullsbarn") // move (executionOrder=30)
            it.nettoGrunnpensjon = grunnpensjon?.netto?.toInt()?.toJAXBElement("nettoGrunnpensjon") // move (executionOrder=24)
            it.nettoSertillegg = sertillegg?.netto?.toInt()?.toJAXBElement("nettoSertillegg") // move (executionOrder=26)
            it.nettoTilleggspensjon = tilleggspensjon?.netto?.toInt()?.toJAXBElement("nettoTilleggspensjon") // move (executionOrder=25)
            it.nettoVentetillegg = ventetillegg?.netto?.toInt()?.toJAXBElement("nettoVentetillegg") // move (executionOrder=31)
            it.p67Berergning = p67Beregning?.toJAXBElement("p67Berergning") // move (executionOrder=4)
            it.paragraf851Tillegg = paragraf851Tillegg?.netto?.toInt()?.toJAXBElement("paragraf851Tillegg") // move (executionOrder=32)
            it.resultatKode = resultatKode // move (executionOrder=3)
            it.sertilleggSats = sertillegg?.sertilleggsats?.toJAXBElement("sertilleggSats") // move (executionOrder=9)
            it.uforegrad = uforegrad?.toInt()?.toJAXBElement("uforegrad") // move (executionOrder=17)
            it.ventetilleggProsent = ventetillegg?.ventetilleggProsent?.toJAXBElement("ventetilleggProsent") // move (executionOrder=15)
            it.virkningFom = virkDatoFom?.let { x -> parseWIDString(x) }?.toXMLGregorianCalendar() // custom.output assignment (executionOrder=1)
            it.virkningTom = virkDatoTom?.let { x -> parseWIDString(x) }?.toXMLGregorianCalendar() // custom.output assignment (executionOrder=2)
            it.yrkesskadegrad = yrkesskadegrad?.toInt()?.toJAXBElement("yrkesskadegrad") // move (executionOrder=16)
            it.involvertePersonerNokkelinfoListe += beregningNokkelinfoListe.map { x -> x.toBeregningNokkelinfo() } // submap (executionOrder=35)
        }

    // GBOBeregningsResultatTOBeregning2011
    private fun GBOBeregningsResultat.toBeregning2011() =
        Beregning2011().also {
            it.afpKompensasjonstillegg = pensjonUnderUtbetaling.afpKompensasjonstillegg.brutto?.toInt() // move (executionOrder=35)
            it.afpKronetillegg = pensjonUnderUtbetaling.afpKronetillegg.brutto?.toInt() // move (executionOrder=34)
            it.afpLivsvarig = pensjonUnderUtbetaling.afpLivsvarig.brutto?.toInt() // move (executionOrder=33)
            it.afpOpptjeningTotalbelop = beregningInformasjonKapittel19.utbetaltForrige?.toDouble() // move (executionOrder=37)
            it.antallBarnetilleggFellesbarn = pensjonUnderUtbetaling.barnetilleggFellesbarn.antallBarn?.toInt() // move (executionOrder=27)
            it.antallBarnetilleggSerkullsbarn = pensjonUnderUtbetaling.barnetilleggSerkullsbarn.antallBarn?.toInt() // move (executionOrder=30)
            it.anvendtTrygdetid = beregningInformasjonKapittel19.ttAnv?.toInt() // move (executionOrder=24)
            it.anvendtTrygdetidAvdod = beregningsinformasjonAvdod.ttAnv?.toInt() // move (executionOrder=43)
            it.basisgp = beregningInformasjonKapittel19.basisgp?.toDouble() // move (executionOrder=4)
            it.basispt = beregningInformasjonKapittel19.basispt?.toDouble() // move (executionOrder=6)
            it.basistp = beregningInformasjonKapittel19.basistp?.toDouble() // move (executionOrder=5)
            it.bruttoBarnetilleggFellesbarn = pensjonUnderUtbetaling.barnetilleggFellesbarn.brutto?.toInt() // move (executionOrder=25)
            it.bruttoBarnetilleggSerkullsbarn = pensjonUnderUtbetaling.barnetilleggSerkullsbarn.brutto?.toInt() // move (executionOrder=28)
            it.ektefelletillegg = pensjonUnderUtbetaling.ektefelletillegg.brutto?.toInt() // move (executionOrder=31)
            it.fnrAvdod = beregningsinformasjonAvdod.fnr // move (executionOrder=38)
            it.forholdstall67 = beregningInformasjonKapittel19.forholdstall67?.toDouble() // move (executionOrder=8)
            it.forholdstallKompensasjonstillegg = pensjonUnderUtbetaling.afpKompensasjonstillegg.forholdstallKompensasjonstillegg?.toDouble() // move (executionOrder=36)
            it.forholdstallUttak = beregningInformasjonKapittel19.forholdstallUttak?.toDouble() // move (executionOrder=7)
            it.gpRestpensjon = beregningInformasjonKapittel19.gpRestpensjon?.toDouble() // move (executionOrder=10)
            it.grunnpensjon = beregningInformasjonKapittel19.gp?.toDouble() // move (executionOrder=9)
            it.grunnpensjonsats = pensjonUnderUtbetaling.grunnpensjon.grunnpensjonsats // move (executionOrder=21)
            it.minstenivatilleggIndividuelt = pensjonUnderUtbetaling.minstenivatilleggIndividuelt.netto?.toInt() // move (executionOrder=44)
            it.minstenivatilleggPensjonistpar = pensjonUnderUtbetaling.minstenivatilleggPensjonistpar.netto?.toInt() // move (executionOrder=32)
            it.mndGrunnpensjon = pensjonUnderUtbetaling.grunnpensjon.netto?.toInt() // move (executionOrder=17)
            it.mndPensjonstillegg = pensjonUnderUtbetaling.pensjonstillegg.netto?.toInt() // move (executionOrder=19)
            it.mndTilleggspensjon = pensjonUnderUtbetaling.tilleggspensjon.netto?.toInt() // move (executionOrder=18)
            it.nettoBarnetilleggFellesbarn = pensjonUnderUtbetaling.barnetilleggFellesbarn.netto?.toInt() // move (executionOrder=26)
            it.nettoBarnetilleggSerkullsbarn = pensjonUnderUtbetaling.barnetilleggSerkullsbarn.netto?.toInt() // move (executionOrder=29)
            it.pensjonstillegg = beregningInformasjonKapittel19.pt?.toDouble() // move (executionOrder=13)
            it.ptRestpensjon = beregningInformasjonKapittel19.ptRestpensjon?.toDouble() // move (executionOrder=14)
            it.resultatKode = beregningInformasjonKapittel19.resultatType // move (executionOrder=16)
            it.skjermingstillegg = pensjonUnderUtbetaling.skjermingstillegg.netto?.toInt() // move (executionOrder=45)
            it.tilleggspensjon = beregningInformasjonKapittel19.tp?.toDouble() // move (executionOrder=11)
            it.totalBelop = pensjonUnderUtbetaling.totalBelop?.toInt() // move (executionOrder=15)
            it.tpRestpensjon = beregningInformasjonKapittel19.tpRestpensjon?.toDouble() // move (executionOrder=12)
            it.uforegrad = pensjonUnderUtbetaling.skjermingstillegg.uforegrad?.toInt() // move (executionOrder=46)
            it.uttaksgrad = uttaksgrad?.toInt() // move (executionOrder=3)
            it.virkningFom = virkFom?.let { x -> parseWIDString(x) }?.toXMLGregorianCalendar() // custom.output assignment (executionOrder=1)
            it.virkningTom = virkTom?.let { x -> parseWIDString(x) }?.toXMLGregorianCalendar() // custom.output assignment (executionOrder=2)
            it.yrkesskadegrad = beregningInformasjonKapittel19.yrkesskadegrad?.toDouble() // move (executionOrder=23)
            it.yrkesskadegradAvdod = beregningsinformasjonAvdod.yrkesskadegrad?.toDouble() // move (executionOrder=42)

            it.spt = beregningInformasjonKapittel19.spt?.toSluttpoengtall() // submap (executionOrder=20)
            it.ypt = beregningInformasjonKapittel19.ypt?.toSluttpoengtall() // submap (executionOrder=22)
            it.sptAvdod = beregningsinformasjonAvdod.spt?.toSluttpoengtall() // submap (executionOrder=39)
            it.yptAvdod = beregningsinformasjonAvdod.ypt?.toSluttpoengtall() // submap (executionOrder=40)
            it.optAvdod = beregningsinformasjonAvdod.opt?.toSluttpoengtall() // submap (executionOrder=41)
        }

    // GBOBeregningsResultatUforetrygdTOBeregningUforetrygd
    private fun GBOBeregningsResultatUforetrygd.toBeregningUforetrygd() =
        BeregningUforetrygd().also {
            it.anvendtTrygdetid = anvendtTrygdetid // move (executionOrder=17)
            it.anvendtTrygdetidAvdod = anvendtTrygdetidAvdod // move (executionOrder=23)
            it.benyttetSivilstand = benyttetSivilstand // move (executionOrder=18)
            it.beregningsgrunnlagOrdiner = beregningsgrunnlagOrdiner // move (executionOrder=19)
            it.beregningsgrunnlagOrdinerAvdod = beregningsgrunnlagOrdinerAvdod // move (executionOrder=21)
            it.beregningsgrunnlagYrkesskade = beregningsgrunnlagYrkesskade // move (executionOrder=20)
            it.beregningsgrunnlagYrkesskadeAvdod = beregningsgrunnlagYrkesskadeAvdod // move (executionOrder=22)
            it.bruttoBarnetilleggFellesbarn = bruttoBarnetilleggFellesbarn // move (executionOrder=9)
            it.bruttoBarnetilleggSerkullsbarn = bruttoBarnetilleggSerkullsbarn // move (executionOrder=7)
            it.bruttoGjenlevendetillegg = bruttoGjenlevendetillegg // move (executionOrder=11)
            it.bruttoUforetrygdOrdinar = bruttoUforetrygdOrdinar // move (executionOrder=3)
            it.fom = virkFom?.let { virkFom -> parseWIDString(virkFom) }?.toXMLGregorianCalendar() // custom.output assignment (executionOrder=14)
            it.inntektBruktIInntektsavkorting = inntektBruktIInntektsavkorting // move (executionOrder=25)
            it.inntektEtterUforhet = inntektEtterUforhet // move (executionOrder=16)
            it.inntektForUforhet = inntektForUforhet // move (executionOrder=1)
            it.nettoBarnetilleggFellesbarn = nettoBarnetilleggFellesbarn // move (executionOrder=8)
            it.nettoBarnetilleggSerkullsbarn = nettoBarnetilleggSerkullsbarn // move (executionOrder=6)
            it.nettoEktefelletillegg = nettoEktefelletillegg // move (executionOrder=12)
            it.nettoGjenlevendetillegg = nettoGjenlevendetillegg // move (executionOrder=10)
            it.nettoUforetrygdOrdinar = nettoUforetrygdOrdinar // move (executionOrder=2)
            it.resultatKode = resultatKode // move (executionOrder=13)
            it.tom = virkTom?.let { virkTom -> parseWIDString(virkTom) }?.toXMLGregorianCalendar() // custom.output assignment (executionOrder=15)
            it.uforegrad = uforegrad // move (executionOrder=4)
            it.yrkesskadegrad = yrkesskadegrad // move (executionOrder=5)
            it.yrkesskadegradAvdod = yrkesskadegradAvdod // move (executionOrder=24)
        }

    // GBOPersonTOPerson
    private fun GBOPerson.toPerson() =
        Person().also {
            it.fnr = fodselsnummer // move (executionOrder=1)
            it.fornavn = fornavn // move (executionOrder=2)
            it.mellomnavn = mellomnavn // move (executionOrder=3)
            it.etternavn = etternavn // move (executionOrder=4)
            it.sivilstand = sivilstand // move (executionOrder=5)
            it.dodsdato = dodsdato?.let { x -> parseWIDString(x) }?.toXMLGregorianCalendar() // custom.output assignment (executionOrder=6)

            run {
                val adresse = Adresse() // custom.output declaration (executionOrder=7)

                if (this.bostedsAdresse != null) {
                    val bostedsAdresse = this.bostedsAdresse
                    val postnr = bostedsAdresse?.postnr
                    if (postnr != null && postnr != "") {
                        adresse.adresselinje1 = bostedsAdresse.boadresse1
                        adresse.adresselinje2 = bostedsAdresse.boadresse2
                        adresse.postnr = bostedsAdresse.postnr
                        adresse.poststed = bostedsAdresse.poststed
                    }
                }

                var annenAdresse: GBOAnnenAdresse? = null
                if (this.postAdresse != null) {
                    val postAdresse = this.postAdresse
                    val postnr = postAdresse?.postnr
                    val adresselinje1 = postAdresse?.adresselinje1
                    val land = postAdresse?.land

                    if ((postnr != null && postnr != "") || (adresselinje1 != null && adresselinje1 != "" && land != null && land != "")) {
                        annenAdresse = postAdresse
                    }
                }

                if (this.tilleggsAdresse != null) {
                    val tilleggsAdresse = this.tilleggsAdresse
                    val postnr: String? = tilleggsAdresse?.postnr
                    if (postnr != null && postnr != "") {
                        annenAdresse = tilleggsAdresse
                    }
                }
                if (this.utenlandsAdresse != null) {
                    val utenlandsAdresse = this.utenlandsAdresse
                    val adresselinje1: String? = utenlandsAdresse?.adresselinje1
                    val land: String? = utenlandsAdresse?.land
                    if (adresselinje1 != null && adresselinje1 != "" && land != null && land != "") {
                        annenAdresse = utenlandsAdresse
                    }
                }

                if (annenAdresse != null) {
                    adresse.adresselinje1 = annenAdresse.adresselinje1
                    adresse.adresselinje2 = annenAdresse.adresselinje2
                    adresse.adresselinje3 = annenAdresse.adresselinje3
                    adresse.postnr = annenAdresse.postnr
                    adresse.poststed = annenAdresse.poststed
                    adresse.land = annenAdresse.land
                }

                it.utbetalingsadresse = adresse // custom.output assignment (executionOrder=7)
            }
        }

    // GBOSamordningsdataTOHentSamordningsdataResp
    fun GBOSamordningsdata.toHentSamordningsdataResp() =
        HentSamordningsdataResp().also {
            it.tpnr = tpnr // move (executionOrder=1)
            it.person = person?.toPerson() // submap (executionOrder=2)
            it.pensjonVedtakListe += vedtakListe.vedtakListe.map { x -> x.toVedtak() } // submap (executionOrder=3)
            it.arbeidVedtakListe += arbeidOgAktivitetsvedtakListe.map { x -> x.toArbeidOgAktivitetsvedtak() } // submap (executionOrder=4)
            it.ufullstendigeData = ufullstendigeData // move (executionOrder=5)

            run {
                val GBOSamordningsdata_tjenestepensjonForholdListe: List<GBOTjenestepensjonForhold> = this.tjenestepensjonForholdListe // custom.input.forEach (executionOrder=6)
                val HentSamordningsdataResp_tjenestepensjonYtelseListe: MutableList<TPYtelse> = ArrayList() // custom.output declaration (executionOrder=6)

                val iter: Iterator<GBOTjenestepensjonForhold> = GBOSamordningsdata_tjenestepensjonForholdListe.iterator()
                while (iter.hasNext()) {
                    val tpForhold: GBOTjenestepensjonForhold = iter.next()
                    val iterator: Iterator<GBOTjenestepensjonYtelse> = tpForhold.getTjenestepensjonYtelseListe().iterator()
                    while (iterator.hasNext()) {
                        val gboTpYtelse = iterator.next()
                        val tpYtelse = TPYtelse()
                        tpYtelse.tpnr = tpForhold.tpnr
                        tpYtelse.tpArt = gboTpYtelse.ytelseKode
                        if (gboTpYtelse.iverksattFom != null) {
                            tpYtelse.datoFom = parseWIDString(gboTpYtelse.iverksattFom)?.toXMLGregorianCalendar()
                        }
                        if (gboTpYtelse.iverksattTom != null) {
                            tpYtelse.datoTom = parseWIDString(gboTpYtelse.iverksattTom)?.toXMLGregorianCalendar()
                        }
                        HentSamordningsdataResp_tjenestepensjonYtelseListe.add(tpYtelse)
                    }
                }
                it.tjenestepensjonYtelseListe += HentSamordningsdataResp_tjenestepensjonYtelseListe // custom.output assignment (executionOrder=6)
            }
        }

    // GBOSamordningsdataTOLagreTPYtelseResp
    fun GBOSamordningsdata.toLagreTPYtelseResp() =
        LagreTPYtelseResp().also {
            it.tpnr = tpnr // move (executionOrder=1)
            it.person = person?.toPerson() // submap (executionOrder=2)
            it.pensjonVedtakListe += vedtakListe.vedtakListe.map { x -> x.toVedtak() } // submap (executionOrder=3)
            it.arbeidOgAktivitetsvedtakListe += arbeidOgAktivitetsvedtakListe.map { x -> x.toArbeidOgAktivitetsvedtak() } // submap (executionOrder=4)

            run {
                val GBOSamordningsdata_tjenestepensjonForholdListe: List<GBOTjenestepensjonForhold>? = this.tjenestepensjonForholdListe // custom.input.forEach (executionOrder=5)
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
                                tpYtelse.datoFom = parseWIDString(gboTpYtelse.iverksattFom)?.toXMLGregorianCalendar()
                            }
                            if (gboTpYtelse.iverksattTom != null) {
                                tpYtelse.datoTom = parseWIDString(gboTpYtelse.iverksattTom)?.toXMLGregorianCalendar()
                            }
                            LagreTPYtelseResp_tjenestepensjonYtelseListe.add(tpYtelse)
                        }
                    }
                }
                it.getTjenestepensjonYtelseListe().addAll(LagreTPYtelseResp_tjenestepensjonYtelseListe) // custom.output assignment (executionOrder=5)
            }
            it.ufullstendigeData = ufullstendigeData // move (executionOrder=6)
        }

    // GBOSluttpoengtallTOSluttpoengtall
    fun GBOSluttpoengtall.toSluttpoengtall() =
        Sluttpoengtall().also {
            it.poengtall = poengtall?.toDouble()?.toJAXBElement("poengtall") // move (executionOrder=1)

            it.poengrekke = poengrekke?.let { src ->
                Poengrekke().also { dst ->
                    dst.pa = src.poengAr?.toInt()?.toJAXBElement("pa") // move (executionOrder=2)
                    dst.paF92 = src.poengArFor92?.toInt()?.toJAXBElement("pa_f92") // move (executionOrder=3)
                    dst.paE91 = src.poengArEtter91?.toInt()?.toJAXBElement("pa_e91") // move (executionOrder=4)
                    dst.tpiFaktor = src.tidligerePenInntektFaktor?.toDouble()?.toJAXBElement("tpiFaktor") // move (executionOrder=5)
                }
            }
        }

    // GBOVedtakTOVedtak
    fun GBOVedtak.toVedtak() =
        Vedtak().also {
            it.saksKode = saksKode // move (executionOrder=1)
            it.vedtakId = vedtakId // move (executionOrder=2)
            it.vedtaksKode = vedtaksKode // move (executionOrder=3)
            it.regelverkKode = kravHode.regelverkKode // move (executionOrder=4)
            it.virkningFom = virkningFom?.let { parseWIDString(it) }?.toXMLGregorianCalendar() // custom.output assignment (executionOrder=9)
            it.virkningTom = virkningTom?.let { parseWIDString(it) }?.toXMLGregorianCalendar() // custom.output assignment (executionOrder=10)
            it.typeKrigspensjon = kravHode.kravVelgKode?.takeIf { gyldigeKrigspensjonKoder.contains(it) } // custom.output assignment (executionOrder=11)
            it.beregningListe += beregningListe.map { it.toBeregning() } // submap (executionOrder=5)
            it.beregning2011Liste += beregningsResultatListe.map { x -> x.toBeregning2011() } // submap (executionOrder=6)
            it.barnListe += vilkarsvedtakListe.map { x -> x.toVilkarsvedtak() } // submap (executionOrder=7)
            it.beregningUforetrygdListe += beregningsresultatUforetrygdListe.map { x -> x.toBeregningUforetrygd() } // submap (executionOrder=8)
        }

    // GBOVilkarsvedtakTOVilkarsvedtak
    fun GBOVilkarsvedtak.toVilkarsvedtak() =
        Vilkarsvedtak().also {
            it.gjelderFnr = gjelderFnr // move (executionOrder=1)
            it.tom = virkningTom?.let { x -> parseWIDString(x) }?.toXMLGregorianCalendar() // custom.output assignment (executionOrder=2)
            it.fom = virkningFom?.let { x -> parseWIDString(x) }?.toXMLGregorianCalendar() // custom.output assignment (executionOrder=3)
        }

    // HentSamordningsdataReqIntTOGBOHentSamordningsdataRequest
    fun HentSamordningsdataReqInt.toGBOHentSamordningsdataRequest(): GBOHentSamordningsdataRequest {
        return GBOHentSamordningsdataRequest().also {
            it.svarBrev = false // set (executionOrder=1)
            it.tpnr = extRequest.tpnr // move (executionOrder=2)
            it.fnr = extRequest.fnr // move (executionOrder=3)
            it.fom = extRequest.datoFom?.let { datoFom -> formatWIDString(datoFom.toDate()) } // custom.output assignment (executionOrder=4)
            it.tssEksternId = tssEksternId // move (executionOrder=5)
        }
    }

    // LagreTPYtelseReqIntTOGBOOpprettTPSamordningRequest
    fun LagreTPYtelseReqInt.toGBOOpprettTPSamordningRequest() =
        GBOOpprettTPSamordningRequest().also {
            it.svarBrev = false // set (executionOrder=1)
            it.tpnr = extRequest?.tpnr // move (executionOrder=2)
            it.fnr = extRequest?.fnr // move (executionOrder=3)
            it.iverksattFom = extRequest?.datoFom?.let { x -> formatWIDString(x.toDate()) } // custom.output assignment (executionOrder=4)
            it.tssEksternId = tssEksternId // move (executionOrder=5)
            it.ytelseKode = extRequest?.tpArt // move (executionOrder=6)
            it.innmeldtFom = formatWIDString(extRequest?.datoFomMedlemskap?.toDate()) // custom.output assignment (executionOrder=7)
        }

    // OpprettRefusjonskravReqIntTOGBOOpprettRefusjonskravRequest
    fun OpprettRefusjonskravReqInt.toGBOOpprettRefusjonskravRequest() =
        GBOOpprettRefusjonskravRequest().also {
            it.svarRefusjonskrav.tpnr = extRequest?.tpnr // move (executionOrder=1)
            it.svarRefusjonskrav.fnr = extRequest?.fnr // move (executionOrder=2)
            it.svarRefusjonskrav.samId = extRequest?.samordningsmeldingId // move (executionOrder=3)
            it.svarRefusjonskrav.refusjonskrav = extRequest?.refusjonskrav // move (executionOrder=4)
            it.svarRefusjonskrav.tssEksternId = tssEksternId // move (executionOrder=6)
            it.svarRefusjonskrav.periodisertBelopListe += extRequest?.periodisertBelopListe?.map { x -> x.toGBOPeriodisertBelop() }.orEmpty() // submap (executionOrder=5)
        }

    // PeriodisertBelopTOGBOPeriodisertBelop
    fun PeriodisertBelop.toGBOPeriodisertBelop() =
        GBOPeriodisertBelop().also {
            it.belop = belop.toString() // move (executionOrder=1)
            it.kravstillersRef = kravstillersReferanse // move (executionOrder=2)
            it.datoFom = datoFom?.let { x -> formatWIDString(x.toDate()) } // custom.output assignment (executionOrder=3)
            it.datoTom = datoTom?.let { x -> formatWIDString(x.toDate()) } // custom.output assignment (executionOrder=4)
        }

    // SlettTPYtelseReqIntTOGBOSlettTPSamordningRequest
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

    private inline fun <reified T> T.toJAXBElement(localPart: String): JAXBElement<T> {
        return JAXBElement(QName(localPart), T::class.java, this)
    }

    private fun Date.toXMLGregorianCalendar() = DatatypeFactory.newInstance().newXMLGregorianCalendar(GregorianCalendar().also { it.time = this })

    private fun String.toXMLGregorianCalendar() = DatatypeFactory.newInstance().newXMLGregorianCalendar(this)

    private fun XMLGregorianCalendar.toDate() = toGregorianCalendar().time

    private val gyldigeKrigspensjonKoder = setOf(
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
