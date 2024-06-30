package no.nav.pensjon.elsam.minibuss.nav_cons_elsam_tptilb_tpsamordningregistrering

import jakarta.xml.bind.JAXBElement
import nav_cons_elsam_tptilb_tpsamordningregistrering.no.nav.asbo.HentSamordningsdataReqInt
import nav_cons_elsam_tptilb_tpsamordningregistrering.no.nav.asbo.LagreTPYtelseReqInt
import nav_cons_elsam_tptilb_tpsamordningregistrering.no.nav.asbo.OpprettRefusjonskravReqInt
import nav_cons_elsam_tptilb_tpsamordningregistrering.no.nav.asbo.SlettTPYtelseReqInt
import nav_cons_elsam_tptilb_tpsamordningregistrering.no.nav.tpsamordningregistrering.v1_0.asbo.BeregningUforetrygd
import nav_lib_frg.no.nav.lib.frg.fault.FaultPersonIkkeFunnet
import nav_lib_frg.no.nav.lib.frg.gbo.GBOPerson
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

object Mapper {
    @Throws(DatatypeConfigurationException::class)
    fun FaultKombinasjonInputTOFaultTPYtelseIkkeFunnet(
        FaultKombinasjonInput: FaultKombinasjonInput,
        FaultTPYtelseIkkeFunnet: FaultTPYtelseIkkeFunnet
    ) {
        FaultTPYtelseIkkeFunnet.errorMessage = FaultKombinasjonInput.errorMessage // move (executionOrder=1)
        FaultTPYtelseIkkeFunnet.errorSource = FaultKombinasjonInput.errorSource // move (executionOrder=2)
        FaultTPYtelseIkkeFunnet.rootCause = FaultKombinasjonInput.rootCause // move (executionOrder=3)
        FaultTPYtelseIkkeFunnet.dateTimeStamp =
            toXMLGregorianCalendar(
                FaultKombinasjonInput.dateTimeStamp
            ) // move (executionOrder=4)
    }

    fun FaultPersonIkkeFunnetTOFaultGenerisk(
        FaultPersonIkkeFunnet: FaultPersonIkkeFunnet,
        FaultGenerisk: FaultGenerisk
    ) {
        FaultGenerisk.errorCode = "UnknownId" // set (executionOrder=1)
        FaultGenerisk.errorDescription = FaultPersonIkkeFunnet.errorMessage // move (executionOrder=2)
    }

    @Throws(DatatypeConfigurationException::class)
    fun FaultRefKravAlleredeRegElUtenforFristTOFaultAlleredeMottattRefusjonskrav(
        FaultRefKravAlleredeRegElUtenforFrist: FaultRefKravAlleredeRegElUtenforFrist,
        FaultAlleredeMottattRefusjonskrav: FaultAlleredeMottattRefusjonskrav
    ) {
        FaultAlleredeMottattRefusjonskrav.errorMessage =
            FaultRefKravAlleredeRegElUtenforFrist.errorMessage // move (executionOrder=1)
        FaultAlleredeMottattRefusjonskrav.errorSource =
            FaultRefKravAlleredeRegElUtenforFrist.errorSource // move (executionOrder=2)
        FaultAlleredeMottattRefusjonskrav.rootCause =
            FaultRefKravAlleredeRegElUtenforFrist.rootCause // move (executionOrder=3)
        FaultAlleredeMottattRefusjonskrav.dateTimeStamp =
            toXMLGregorianCalendar(
                FaultRefKravAlleredeRegElUtenforFrist.dateTimeStamp
            ) // move (executionOrder=4)
    }

    @Throws(DatatypeConfigurationException::class)
    fun FaultSamIdIkkeGyldigTOFaultSamordningsIdIkkeFunnet(
        FaultSamIdIkkeGyldig: FaultSamIdIkkeGyldig,
        FaultSamordningsIdIkkeFunnet: FaultSamordningsIdIkkeFunnet
    ) {
        FaultSamordningsIdIkkeFunnet.errorMessage = FaultSamIdIkkeGyldig.errorMessage // move (executionOrder=1)
        FaultSamordningsIdIkkeFunnet.errorSource = FaultSamIdIkkeGyldig.errorSource // move (executionOrder=2)
        FaultSamordningsIdIkkeFunnet.rootCause = FaultSamIdIkkeGyldig.rootCause // move (executionOrder=3)
        FaultSamordningsIdIkkeFunnet.dateTimeStamp =
            toXMLGregorianCalendar(
                FaultSamIdIkkeGyldig.dateTimeStamp
            ) // move (executionOrder=4)
    }

    @Throws(DatatypeConfigurationException::class)
    fun FaultSvarUtenforPeriodeTOFaultRefusjonskravUtenforSamordningspliktigPeriode(
        FaultSvarUtenforPeriode: FaultSvarUtenforPeriode,
        FaultRefusjonskravUtenforSamordningspliktigPeriode: FaultRefusjonskravUtenforSamordningspliktigPeriode
    ) {
        FaultRefusjonskravUtenforSamordningspliktigPeriode.errorMessage =
            FaultSvarUtenforPeriode.errorMessage // move (executionOrder=1)
        FaultRefusjonskravUtenforSamordningspliktigPeriode.errorSource =
            FaultSvarUtenforPeriode.errorSource // move (executionOrder=2)
        FaultRefusjonskravUtenforSamordningspliktigPeriode.rootCause =
            FaultSvarUtenforPeriode.rootCause // move (executionOrder=3)
        FaultRefusjonskravUtenforSamordningspliktigPeriode.dateTimeStamp =
            toXMLGregorianCalendar(
                FaultSvarUtenforPeriode.dateTimeStamp
            ) // move (executionOrder=4)
    }

    @Throws(DatatypeConfigurationException::class)
    fun FaultYtelseAlleredeRegistrertTOFaultTPYtelseAlleredeRegistrert(
        FaultYtelseAlleredeRegistrert: FaultYtelseAlleredeRegistrert,
        FaultTPYtelseAlleredeRegistrert: FaultTPYtelseAlleredeRegistrert
    ) {
        FaultTPYtelseAlleredeRegistrert.errorMessage =
            FaultYtelseAlleredeRegistrert.errorMessage // move (executionOrder=1)
        FaultTPYtelseAlleredeRegistrert.errorSource =
            FaultYtelseAlleredeRegistrert.errorSource // move (executionOrder=2)
        FaultTPYtelseAlleredeRegistrert.rootCause = FaultYtelseAlleredeRegistrert.rootCause // move (executionOrder=3)
        FaultTPYtelseAlleredeRegistrert.dateTimeStamp =
            toXMLGregorianCalendar(
                FaultYtelseAlleredeRegistrert.dateTimeStamp
            ) // move (executionOrder=4)
    }

    @Throws(DatatypeConfigurationException::class)
    fun FaultYtelseIkkeIverksattTOFaultTPForholdIkkeIverksatt(
        FaultYtelseIkkeIverksatt: FaultYtelseIkkeIverksatt,
        FaultTPForholdIkkeIverksatt: FaultTPForholdIkkeIverksatt
    ) {
        FaultTPForholdIkkeIverksatt.errorMessage = FaultYtelseIkkeIverksatt.errorMessage // move (executionOrder=1)
        FaultTPForholdIkkeIverksatt.errorSource = FaultYtelseIkkeIverksatt.errorSource // move (executionOrder=2)
        FaultTPForholdIkkeIverksatt.rootCause = FaultYtelseIkkeIverksatt.rootCause // move (executionOrder=3)
        FaultTPForholdIkkeIverksatt.dateTimeStamp =
            toXMLGregorianCalendar(
                FaultYtelseIkkeIverksatt.dateTimeStamp
            ) // move (executionOrder=4)
    }

    @Throws(DatatypeConfigurationException::class)
    fun GBOArbeidOgAktivitetsvedtakTOArbeidOgAktivitetsvedtak(
        GBOArbeidOgAktivitetsvedtak: GBOArbeidOgAktivitetsvedtak,
        ArbeidOgAktivitetsvedtak: ArbeidOgAktivitetsvedtak
    ) {
        ArbeidOgAktivitetsvedtak.vedtakId = GBOArbeidOgAktivitetsvedtak.vedtakId // move (executionOrder=1)
        ArbeidOgAktivitetsvedtak.gjelderFnr = GBOArbeidOgAktivitetsvedtak.gjelderFnr // move (executionOrder=2)
        ArbeidOgAktivitetsvedtak.virkningFom =
            toXMLGregorianCalendar(
                DateUtil.parseWIDString(GBOArbeidOgAktivitetsvedtak.virkningFom)
            ) // custom.output assignment (executionOrder=3)
        ArbeidOgAktivitetsvedtak.virkningTom =
            toXMLGregorianCalendar(
                DateUtil.parseWIDString(GBOArbeidOgAktivitetsvedtak.virkningTom)
            ) // custom.output assignment (executionOrder=4)
        ArbeidOgAktivitetsvedtak.vedtaksKode = GBOArbeidOgAktivitetsvedtak.vedtakstypeKode // move (executionOrder=5)
        ArbeidOgAktivitetsvedtak.vedtakstatusKode =
            GBOArbeidOgAktivitetsvedtak.vedtakstatusKode // move (executionOrder=6)
        ArbeidOgAktivitetsvedtak.saksKode = GBOArbeidOgAktivitetsvedtak.sakstypeKode // move (executionOrder=7)
        ArbeidOgAktivitetsvedtak.navEnhet = GBOArbeidOgAktivitetsvedtak.navEnhet // move (executionOrder=8)
        ArbeidOgAktivitetsvedtak.utfallKode = GBOArbeidOgAktivitetsvedtak.utfallKode // move (executionOrder=9)
        ArbeidOgAktivitetsvedtak.rettighetKode = GBOArbeidOgAktivitetsvedtak.rettighetKode // move (executionOrder=10)
        ArbeidOgAktivitetsvedtak.dagsatsUBT =
            GBOArbeidOgAktivitetsvedtak.vedtaksfaktaAAP.dagsatsUBT // move (executionOrder=11)
        ArbeidOgAktivitetsvedtak.dagsatsMBT =
            GBOArbeidOgAktivitetsvedtak.vedtaksfaktaAAP.dagsatsMBT // move (executionOrder=12)
        ArbeidOgAktivitetsvedtak.dagstatsMBTFS =
            GBOArbeidOgAktivitetsvedtak.vedtaksfaktaAAP.dagsatsMBTFS // move (executionOrder=13)
        ArbeidOgAktivitetsvedtak.antallBarn = toInteger(
            GBOArbeidOgAktivitetsvedtak.vedtaksfaktaAAP.antallBarn
        ) // move (executionOrder=14)
        ArbeidOgAktivitetsvedtak.beregningsgrunnlag =
            GBOArbeidOgAktivitetsvedtak.vedtaksfaktaAAP.beregningsgrunnlag // move (executionOrder=15)
    }

    fun GBOBeregningNokkelinfoTOBeregningNokkelinfo(
        GBOBeregningNokkelinfo: GBOBeregningNokkelinfo,
        BeregningNokkelinfo: BeregningNokkelinfo
    ) {
        BeregningNokkelinfo.fnr = GBOBeregningNokkelinfo.fnr // move (executionOrder=1)
        BeregningNokkelinfo.grunnlagsrolleKode = GBOBeregningNokkelinfo.grunnlagsrolleKode // move (executionOrder=2)
        GBOSluttpoengtallTOSluttpoengtall(
            GBOBeregningNokkelinfo.spt,
            BeregningNokkelinfo.spt
        ) // submap (executionOrder=3)
        GBOSluttpoengtallTOSluttpoengtall(
            GBOBeregningNokkelinfo.ypt,
            BeregningNokkelinfo.ypt
        ) // submap (executionOrder=4)
        GBOSluttpoengtallTOSluttpoengtall(
            GBOBeregningNokkelinfo.opt,
            BeregningNokkelinfo.opt
        ) // submap (executionOrder=5)
        BeregningNokkelinfo.anvendtTrygdetid =
            toJAXBElement(
                "anvendtTrygdetid",
                Int::class.java,
                toInteger(
                    GBOBeregningNokkelinfo.ttAnv
                )
            ) // move (executionOrder=6)
        BeregningNokkelinfo.anvendtIBeregningen =
            toJAXBElement(
                "anvendtIBeregningen",
                Boolean::class.java,
                GBOBeregningNokkelinfo.anvendtIBeregningen
            ) // move (executionOrder=7)
    }

    @Throws(DatatypeConfigurationException::class)
    fun GBOBeregningTOBeregning(GBOBeregning: GBOBeregning, Beregning_1: Beregning) {
        Beregning_1.virkningFom =
            toXMLGregorianCalendar(
                if (GBOBeregning.virkDatoFom != null) DateUtil.parseWIDString(GBOBeregning.virkDatoFom) else null
            ) // custom.output assignment (executionOrder=1)
        Beregning_1.virkningTom =
            toXMLGregorianCalendar(
                if (GBOBeregning.virkDatoTom != null) DateUtil.parseWIDString(GBOBeregning.virkDatoTom) else null
            ) // custom.output assignment (executionOrder=2)
        Beregning_1.resultatKode = GBOBeregning.resultatKode // move (executionOrder=3)
        Beregning_1.setP67Berergning(GBOBeregning.p67Beregning) // move (executionOrder=4)
        Beregning_1.setAfpPensjonsgrad(GBOBeregning.afpPensjonsgrad) // move (executionOrder=5)
        Beregning_1.setBruttoGrunnpensjon(GBOBeregning.grunnpensjon.brutto) // move (executionOrder=6)
        Beregning_1.setBruttoTilleggspensjon(GBOBeregning.tilleggspensjon.brutto) // move (executionOrder=7)
        Beregning_1.setBruttoSertillegg(GBOBeregning.sertillegg.brutto) // move (executionOrder=8)
        Beregning_1.setSertilleggSats(GBOBeregning.sertillegg.sertilleggsats) // move (executionOrder=9)
        Beregning_1.setBruttoAFPtillegg(GBOBeregning.afpTillegg.brutto) // move (executionOrder=10)
        Beregning_1.setBruttoBarnetilleggFellesbarn(GBOBeregning.barnetilleggFellesBarn.brutto) // move (executionOrder=11)
        Beregning_1.setAntallBarnetilleggFellesbarn(GBOBeregning.barnetilleggFellesBarn.antallBarn) // move (executionOrder=12)
        Beregning_1.setBruttoBarnetilleggSerkullsbarn(GBOBeregning.barnetilleggSerkullsbarn.brutto) // move (executionOrder=13)
        Beregning_1.setAntallBarnetilleggSerkullsbarn(GBOBeregning.barnetilleggSerkullsbarn.antallBarn) // move (executionOrder=14)
        Beregning_1.setVentetilleggProsent(GBOBeregning.ventetillegg.ventetilleggProsent) // move (executionOrder=15)
        Beregning_1.setYrkesskadegrad(GBOBeregning.yrkesskadegrad) // move (executionOrder=16)
        Beregning_1.setUforegrad(GBOBeregning.uforegrad) // move (executionOrder=17)
        Beregning_1.krigOgGammelYrkesskade.setPensjonsgrad(GBOBeregning.krigOgGammelYrkesskade.pensjonsgrad) // move (executionOrder=18)
        Beregning_1.krigOgGammelYrkesskade.setGrunnlag(GBOBeregning.krigOgGammelYrkesskade.grunnlagForUtbetaling) // move (executionOrder=19)
        Beregning_1.krigOgGammelYrkesskade.setForholdstallYG(GBOBeregning.krigOgGammelYrkesskade.forholdstallYG) // move (executionOrder=20)
        Beregning_1.setGrunnpensjonsats(GBOBeregning.grunnpensjon.grunnpensjonsats) // move (executionOrder=21)
        Beregning_1.setBrutto(GBOBeregning.brutto) // move (executionOrder=22)
        Beregning_1.setNetto(GBOBeregning.netto) // move (executionOrder=23)
        Beregning_1.setNettoGrunnpensjon(GBOBeregning.grunnpensjon.netto) // move (executionOrder=24)
        Beregning_1.setNettoTilleggspensjon(GBOBeregning.tilleggspensjon.netto) // move (executionOrder=25)
        Beregning_1.setNettoSertillegg(GBOBeregning.sertillegg.netto) // move (executionOrder=26)
        Beregning_1.setNettoAFPtillegg(GBOBeregning.afpTillegg.netto) // move (executionOrder=27)
        Beregning_1.setEktefelletillegg(GBOBeregning.ektefelleTillegg.netto) // move (executionOrder=28)
        Beregning_1.setNettoBarnetilleggFellesbarn(GBOBeregning.barnetilleggFellesBarn.netto) // move (executionOrder=29)
        Beregning_1.setNettoBarnetilleggSerkullsbarn(GBOBeregning.barnetilleggSerkullsbarn.netto) // move (executionOrder=30)
        Beregning_1.setNettoVentetillegg(GBOBeregning.ventetillegg.netto) // move (executionOrder=31)
        Beregning_1.setParagraf851Tillegg(GBOBeregning.paragraf851Tillegg.netto) // move (executionOrder=32)
        Beregning_1.krigOgGammelYrkesskade.setBelop(GBOBeregning.krigOgGammelYrkesskade.netto) // move (executionOrder=33)
        Beregning_1.setFremtidigPensjonsgivendeInntektBruker(GBOBeregning.inntektBruktIAvkortning) // move (executionOrder=34)
        GBOBeregningNokkelinfoTOBeregningNokkelinfo(
            GBOBeregning.beregningNokkelinfoListe,
            Beregning_1.involvertePersonerNokkelinfoListe
        ) // submap (executionOrder=35)
        Beregning_1.setMinstenivatilleggPensjonistpar(GBOBeregning.minstenivatilleggPensjonistpar.netto) // move (executionOrder=36)
        Beregning_1.setMinstenivatilleggIndividuelt(GBOBeregning.minstenivatilleggIndividuelt.netto) // move (executionOrder=37)
    }

    fun GBOBeregningsResultatTOBeregning2011(
        GBOBeregningsResultat: GBOBeregningsResultat,
        Beregning2011: Beregning2011
    ) {
        run {
            val GBOBeregningsResultat_virkFom = GBOBeregningsResultat.virkFom // custom.input.forEach (executionOrder=1)
            var Beregning2011_virkningFom: String? = null // custom.output declaration (executionOrder=1)
            // The specific type of variable GBOBeregningsResultat_virkFom is java.lang.String
            // The specific type of variable Beregning2011_virkningFom is java.util.Date
            if (GBOBeregningsResultat_virkFom != null) {
                Beregning2011_virkningFom = DateUtil.parseWIDString(
                    GBOBeregningsResultat_virkFom
                )
            }
            Beregning2011.setVirkningFom(Beregning2011_virkningFom) // custom.output assignment (executionOrder=1)
        }
        run {
            val GBOBeregningsResultat_virkTom = GBOBeregningsResultat.virkTom // custom.input.forEach (executionOrder=2)
            var Beregning2011_virkningTom: String? = null // custom.output declaration (executionOrder=2)
            // The specific type of variable GBOBeregningsResultat_virkTom is java.lang.String
            // The specific type of variable Beregning2011_virkningTom is java.util.Date
            if (GBOBeregningsResultat_virkTom != null) {
                Beregning2011_virkningTom = DateUtil.parseWIDString(
                    GBOBeregningsResultat_virkTom
                )
            }
            Beregning2011.setVirkningTom(Beregning2011_virkningTom) // custom.output assignment (executionOrder=2)
        }
        Beregning2011.setUttaksgrad(GBOBeregningsResultat.uttaksgrad) // move (executionOrder=3)
        Beregning2011.setBasisgp(GBOBeregningsResultat.beregningInformasjonKapittel19.basisgp) // move (executionOrder=4)
        Beregning2011.setBasistp(GBOBeregningsResultat.beregningInformasjonKapittel19.basistp) // move (executionOrder=5)
        Beregning2011.setBasispt(GBOBeregningsResultat.beregningInformasjonKapittel19.basispt) // move (executionOrder=6)
        Beregning2011.setForholdstallUttak(GBOBeregningsResultat.beregningInformasjonKapittel19.forholdstallUttak) // move (executionOrder=7)
        Beregning2011.setForholdstall67(GBOBeregningsResultat.beregningInformasjonKapittel19.forholdstall67) // move (executionOrder=8)
        Beregning2011.setGrunnpensjon(GBOBeregningsResultat.beregningInformasjonKapittel19.gp) // move (executionOrder=9)
        Beregning2011.setGpRestpensjon(GBOBeregningsResultat.beregningInformasjonKapittel19.gpRestpensjon) // move (executionOrder=10)
        Beregning2011.setTilleggspensjon(GBOBeregningsResultat.beregningInformasjonKapittel19.tp) // move (executionOrder=11)
        Beregning2011.setTpRestpensjon(GBOBeregningsResultat.beregningInformasjonKapittel19.tpRestpensjon) // move (executionOrder=12)
        Beregning2011.setPensjonstillegg(GBOBeregningsResultat.beregningInformasjonKapittel19.pt) // move (executionOrder=13)
        Beregning2011.setPtRestpensjon(GBOBeregningsResultat.beregningInformasjonKapittel19.ptRestpensjon) // move (executionOrder=14)
        Beregning2011.setTotalBelop(GBOBeregningsResultat.pensjonUnderUtbetaling.totalBelop) // move (executionOrder=15)
        Beregning2011.resultatKode =
            GBOBeregningsResultat.beregningInformasjonKapittel19.resultatType // move (executionOrder=16)
        Beregning2011.setMndGrunnpensjon(GBOBeregningsResultat.pensjonUnderUtbetaling.grunnpensjon.netto) // move (executionOrder=17)
        Beregning2011.setMndTilleggspensjon(GBOBeregningsResultat.pensjonUnderUtbetaling.tilleggspensjon.netto) // move (executionOrder=18)
        Beregning2011.setMndPensjonstillegg(GBOBeregningsResultat.pensjonUnderUtbetaling.pensjonstillegg.netto) // move (executionOrder=19)
        GBOSluttpoengtallTOSluttpoengtall(
            GBOBeregningsResultat.beregningInformasjonKapittel19.spt,
            Beregning2011.spt
        ) // submap (executionOrder=20)
        Beregning2011.grunnpensjonsats =
            GBOBeregningsResultat.pensjonUnderUtbetaling.grunnpensjon.grunnpensjonsats // move (executionOrder=21)
        GBOSluttpoengtallTOSluttpoengtall(
            GBOBeregningsResultat.beregningInformasjonKapittel19.ypt,
            Beregning2011.ypt
        ) // submap (executionOrder=22)
        Beregning2011.setYrkesskadegrad(GBOBeregningsResultat.beregningInformasjonKapittel19.yrkesskadegrad) // move (executionOrder=23)
        Beregning2011.setAnvendtTrygdetid(GBOBeregningsResultat.beregningInformasjonKapittel19.ttAnv) // move (executionOrder=24)
        Beregning2011.setBruttoBarnetilleggFellesbarn(GBOBeregningsResultat.pensjonUnderUtbetaling.barnetilleggFellesbarn.brutto) // move (executionOrder=25)
        Beregning2011.setNettoBarnetilleggFellesbarn(GBOBeregningsResultat.pensjonUnderUtbetaling.barnetilleggFellesbarn.netto) // move (executionOrder=26)
        Beregning2011.setAntallBarnetilleggFellesbarn(GBOBeregningsResultat.pensjonUnderUtbetaling.barnetilleggFellesbarn.antallBarn) // move (executionOrder=27)
        Beregning2011.setBruttoBarnetilleggSerkullsbarn(GBOBeregningsResultat.pensjonUnderUtbetaling.barnetilleggSerkullsbarn.brutto) // move (executionOrder=28)
        Beregning2011.setNettoBarnetilleggSerkullsbarn(GBOBeregningsResultat.pensjonUnderUtbetaling.barnetilleggSerkullsbarn.netto) // move (executionOrder=29)
        Beregning2011.setAntallBarnetilleggSerkullsbarn(GBOBeregningsResultat.pensjonUnderUtbetaling.barnetilleggSerkullsbarn.antallBarn) // move (executionOrder=30)
        Beregning2011.setEktefelletillegg(GBOBeregningsResultat.pensjonUnderUtbetaling.ektefelletillegg.brutto) // move (executionOrder=31)
        Beregning2011.setMinstenivatilleggPensjonistpar(GBOBeregningsResultat.pensjonUnderUtbetaling.minstenivatilleggPensjonistpar.netto) // move (executionOrder=32)
        Beregning2011.setAfpLivsvarig(GBOBeregningsResultat.pensjonUnderUtbetaling.afpLivsvarig.brutto) // move (executionOrder=33)
        Beregning2011.setAfpKronetillegg(GBOBeregningsResultat.pensjonUnderUtbetaling.afpKronetillegg.brutto) // move (executionOrder=34)
        Beregning2011.setAfpKompensasjonstillegg(GBOBeregningsResultat.pensjonUnderUtbetaling.afpKompensasjonstillegg.brutto) // move (executionOrder=35)
        Beregning2011.setForholdstallKompensasjonstillegg(GBOBeregningsResultat.pensjonUnderUtbetaling.afpKompensasjonstillegg.forholdstallKompensasjonstillegg) // move (executionOrder=36)
        Beregning2011.setAfpOpptjeningTotalbelop(GBOBeregningsResultat.beregningInformasjonKapittel19.utbetaltForrige) // move (executionOrder=37)
        Beregning2011.fnrAvdod = GBOBeregningsResultat.beregningsinformasjonAvdod.fnr // move (executionOrder=38)
        GBOSluttpoengtallTOSluttpoengtall(
            GBOBeregningsResultat.beregningsinformasjonAvdod.spt,
            Beregning2011.sptAvdod
        ) // submap (executionOrder=39)
        GBOSluttpoengtallTOSluttpoengtall(
            GBOBeregningsResultat.beregningsinformasjonAvdod.ypt,
            Beregning2011.yptAvdod
        ) // submap (executionOrder=40)
        GBOSluttpoengtallTOSluttpoengtall(
            GBOBeregningsResultat.beregningsinformasjonAvdod.opt,
            Beregning2011.optAvdod
        ) // submap (executionOrder=41)
        Beregning2011.setYrkesskadegradAvdod(GBOBeregningsResultat.beregningsinformasjonAvdod.yrkesskadegrad) // move (executionOrder=42)
        Beregning2011.setAnvendtTrygdetidAvdod(GBOBeregningsResultat.beregningsinformasjonAvdod.ttAnv) // move (executionOrder=43)
        Beregning2011.setMinstenivatilleggIndividuelt(GBOBeregningsResultat.pensjonUnderUtbetaling.minstenivatilleggIndividuelt.netto) // move (executionOrder=44)
        Beregning2011.setSkjermingstillegg(GBOBeregningsResultat.pensjonUnderUtbetaling.skjermingstillegg.netto) // move (executionOrder=45)
        Beregning2011.setUforegrad(GBOBeregningsResultat.pensjonUnderUtbetaling.skjermingstillegg.uforegrad) // move (executionOrder=46)
    }

    fun GBOBeregningsResultatUforetrygdTOBeregningUforetrygd(
        GBOBeregningsResultatUforetrygd: GBOBeregningsResultatUforetrygd,
        BeregningUforetrygd: BeregningUforetrygd
    ) {
        BeregningUforetrygd.inntektForUforhet =
            GBOBeregningsResultatUforetrygd.inntektForUforhet // move (executionOrder=1)
        BeregningUforetrygd.nettoUforetrygdOrdinar =
            GBOBeregningsResultatUforetrygd.nettoUforetrygdOrdinar // move (executionOrder=2)
        BeregningUforetrygd.bruttoUforetrygdOrdinar =
            GBOBeregningsResultatUforetrygd.bruttoUforetrygdOrdinar // move (executionOrder=3)
        BeregningUforetrygd.uforegrad = GBOBeregningsResultatUforetrygd.uforegrad // move (executionOrder=4)
        BeregningUforetrygd.yrkesskadegrad = GBOBeregningsResultatUforetrygd.yrkesskadegrad // move (executionOrder=5)
        BeregningUforetrygd.nettoBarnetilleggSerkullsbarn =
            GBOBeregningsResultatUforetrygd.nettoBarnetilleggSerkullsbarn // move (executionOrder=6)
        BeregningUforetrygd.bruttoBarnetilleggSerkullsbarn =
            GBOBeregningsResultatUforetrygd.bruttoBarnetilleggSerkullsbarn // move (executionOrder=7)
        BeregningUforetrygd.nettoBarnetilleggFellesbarn =
            GBOBeregningsResultatUforetrygd.nettoBarnetilleggFellesbarn // move (executionOrder=8)
        BeregningUforetrygd.bruttoBarnetilleggFellesbarn =
            GBOBeregningsResultatUforetrygd.bruttoBarnetilleggFellesbarn // move (executionOrder=9)
        BeregningUforetrygd.nettoGjenlevendetillegg =
            GBOBeregningsResultatUforetrygd.nettoGjenlevendetillegg // move (executionOrder=10)
        BeregningUforetrygd.bruttoGjenlevendetillegg =
            GBOBeregningsResultatUforetrygd.bruttoGjenlevendetillegg // move (executionOrder=11)
        BeregningUforetrygd.nettoEktefelletillegg =
            GBOBeregningsResultatUforetrygd.nettoEktefelletillegg // move (executionOrder=12)
        BeregningUforetrygd.resultatKode = GBOBeregningsResultatUforetrygd.resultatKode // move (executionOrder=13)
        run {
            val GBOBeregningsResultatUforetrygd_virkFom =
                GBOBeregningsResultatUforetrygd.virkFom // custom.input.forEach (executionOrder=14)
            var BeregningUforetrygd_fom: String? = null // custom.output declaration (executionOrder=14)
            // The specific type of variable GBOBeregningsResultatUforetrygd_virkFom is java.lang.String
            // The specific type of variable BeregningUforetrygd_fom is java.util.Date
            if (GBOBeregningsResultatUforetrygd_virkFom != null) {
                BeregningUforetrygd_fom = DateUtil.parseWIDString(
                    GBOBeregningsResultatUforetrygd_virkFom
                )
            }
            BeregningUforetrygd.setFom(BeregningUforetrygd_fom) // custom.output assignment (executionOrder=14)
        }
        run {
            val GBOBeregningsResultatUforetrygd_virkTom =
                GBOBeregningsResultatUforetrygd.virkTom // custom.input.forEach (executionOrder=15)
            var BeregningUforetrygd_tom: String? = null // custom.output declaration (executionOrder=15)
            // The specific type of variable GBOBeregningsResultatUforetrygd_virkTom is java.lang.String
            // The specific type of variable BeregningUforetrygd_tom is java.util.Date
            if (GBOBeregningsResultatUforetrygd_virkTom != null) {
                BeregningUforetrygd_tom = DateUtil.parseWIDString(
                    GBOBeregningsResultatUforetrygd_virkTom
                )
            }
            BeregningUforetrygd.setTom(BeregningUforetrygd_tom) // custom.output assignment (executionOrder=15)
        }
        BeregningUforetrygd.inntektEtterUforhet =
            GBOBeregningsResultatUforetrygd.inntektEtterUforhet // move (executionOrder=16)
        BeregningUforetrygd.anvendtTrygdetid =
            GBOBeregningsResultatUforetrygd.anvendtTrygdetid // move (executionOrder=17)
        BeregningUforetrygd.benyttetSivilstand =
            GBOBeregningsResultatUforetrygd.benyttetSivilstand // move (executionOrder=18)
        BeregningUforetrygd.beregningsgrunnlagOrdiner =
            GBOBeregningsResultatUforetrygd.beregningsgrunnlagOrdiner // move (executionOrder=19)
        BeregningUforetrygd.beregningsgrunnlagYrkesskade =
            GBOBeregningsResultatUforetrygd.beregningsgrunnlagYrkesskade // move (executionOrder=20)
        BeregningUforetrygd.beregningsgrunnlagOrdinerAvdod =
            GBOBeregningsResultatUforetrygd.beregningsgrunnlagOrdinerAvdod // move (executionOrder=21)
        BeregningUforetrygd.beregningsgrunnlagYrkesskadeAvdod =
            GBOBeregningsResultatUforetrygd.beregningsgrunnlagYrkesskadeAvdod // move (executionOrder=22)
        BeregningUforetrygd.anvendtTrygdetidAvdod =
            GBOBeregningsResultatUforetrygd.anvendtTrygdetidAvdod // move (executionOrder=23)
        BeregningUforetrygd.yrkesskadegradAvdod =
            GBOBeregningsResultatUforetrygd.yrkesskadegradAvdod // move (executionOrder=24)
        BeregningUforetrygd.inntektBruktIInntektsavkorting =
            GBOBeregningsResultatUforetrygd.inntektBruktIInntektsavkorting // move (executionOrder=25)
    }

    fun GBOPersonTOPerson(GBOPerson: GBOPerson, Person: Person) {
        Person.fnr = GBOPerson.fodselsnummer // move (executionOrder=1)
        Person.fornavn = GBOPerson.fornavn // move (executionOrder=2)
        Person.mellomnavn = GBOPerson.mellomnavn // move (executionOrder=3)
        Person.etternavn = GBOPerson.etternavn // move (executionOrder=4)
        Person.sivilstand = GBOPerson.sivilstand // move (executionOrder=5)
        run {
            val GBOPerson_dodsdato = GBOPerson.dodsdato // custom.input.forEach (executionOrder=6)
            var Person_dodsdato: String? = null // custom.output declaration (executionOrder=6)
            // The specific type of variable GBOPerson_dodsdato is java.lang.String
            // The specific type of variable Person_dodsdato is java.util.Date
            if (GBOPerson_dodsdato != null) {
                Person_dodsdato = DateUtil.parseWIDString(GBOPerson_dodsdato)
            }

            Person.setDodsdato(Person_dodsdato) // custom.output assignment (executionOrder=6)
        }
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

    fun GBOSamordningsdataTOHentSamordningsdataResp(
        GBOSamordningsdata: GBOSamordningsdata,
        HentSamordningsdataResp: HentSamordningsdataResp
    ) {
        HentSamordningsdataResp.tpnr = GBOSamordningsdata.tpnr // move (executionOrder=1)
        GBOPersonTOPerson(GBOSamordningsdata.person, HentSamordningsdataResp.person) // submap (executionOrder=2)
        GBOVedtakTOVedtak(
            GBOSamordningsdata.vedtakListe.vedtakListe,
            HentSamordningsdataResp.pensjonVedtakListe
        ) // submap (executionOrder=3)
        GBOArbeidOgAktivitetsvedtakTOArbeidOgAktivitetsvedtak(
            GBOSamordningsdata.arbeidOgAktivitetsvedtakListe,
            HentSamordningsdataResp.arbeidVedtakListe
        ) // submap (executionOrder=4)
        HentSamordningsdataResp.ufullstendigeData = GBOSamordningsdata.ufullstendigeData // move (executionOrder=5)
        run {
            val GBOSamordningsdata_tjenestepensjonForholdListe: String? =
                GBOSamordningsdata.tjenestepensjonForholdListe // custom.input.forEach (executionOrder=6)
            val HentSamordningsdataResp_tjenestepensjonYtelseListe: String? =
                null // custom.output declaration (executionOrder=6)

            // The specific type of variable GBOSamordningsdata_tjenestepensjonForholdListe is java.util.List
            // The specific type of variable HentSamordningsdataResp_tjenestepensjonYtelseListe is java.util.List
            if (GBOSamordningsdata_tjenestepensjonForholdListe != null &&
                HentSamordningsdataResp_tjenestepensjonYtelseListe != null
            ) {
                // Clear list to work around WID bug until JR27952 is fixed
                (HentSamordningsdataResp_tjenestepensjonYtelseListe as MutableList<*>).clear()

                val iter: Iterator = (GBOSamordningsdata_tjenestepensjonForholdListe as List<*>).iterator()
                while (iter.hasNext()) {
                    val tpForhold: DataObject = iter.next() as DataObject
                    val iterator: Iterator = (tpForhold.getList("tjenestepensjonYtelseListe") as List<*>).iterator()
                    while (iterator.hasNext()) {
                        val gboTpYtelse: DataObject = iterator.next() as DataObject
                        val tpYtelse: DataObject =
                            DataFactory.INSTANCE.create("http://nav.no/elsam/tpsamordningregistrering/V0_5", "TPYtelse")
                        tpYtelse.setString("tpnr", tpForhold.getString("tpnr"))
                        tpYtelse.setString("tpArt", gboTpYtelse.getString("ytelseKode"))
                        if (gboTpYtelse.getString("iverksattFom") != null) {
                            tpYtelse.setDate("datoFom", DateUtil.parseWIDString(gboTpYtelse.getString("iverksattFom")))
                        }
                        if (gboTpYtelse.getString("iverksattTom") != null) {
                            tpYtelse.setDate("datoTom", DateUtil.parseWIDString(gboTpYtelse.getString("iverksattTom")))
                        }
                        (HentSamordningsdataResp_tjenestepensjonYtelseListe as MutableList<*>).add(tpYtelse)
                    }
                }
            }
            HentSamordningsdataResp.setTjenestepensjonYtelseListe(HentSamordningsdataResp_tjenestepensjonYtelseListe) // custom.output assignment (executionOrder=6)
        }
    }

    fun GBOSamordningsdataTOLagreTPYtelseResp(
        GBOSamordningsdata: GBOSamordningsdata,
        LagreTPYtelseResp: LagreTPYtelseResp
    ) {
        LagreTPYtelseResp.tpnr = GBOSamordningsdata.tpnr // move (executionOrder=1)
        GBOPersonTOPerson(GBOSamordningsdata.person, LagreTPYtelseResp.person) // submap (executionOrder=2)
        GBOVedtakTOVedtak(
            GBOSamordningsdata.vedtakListe.vedtakListe,
            LagreTPYtelseResp.pensjonVedtakListe
        ) // submap (executionOrder=3)
        GBOArbeidOgAktivitetsvedtakTOArbeidOgAktivitetsvedtak(
            GBOSamordningsdata.arbeidOgAktivitetsvedtakListe,
            LagreTPYtelseResp.arbeidOgAktivitetsvedtakListe
        ) // submap (executionOrder=4)
        run {
            val GBOSamordningsdata_tjenestepensjonForholdListe: String? =
                GBOSamordningsdata.tjenestepensjonForholdListe // custom.input.forEach (executionOrder=5)
            val LagreTPYtelseResp_tjenestepensjonYtelseListe: String? =
                null // custom.output declaration (executionOrder=5)

            // The specific type of variable GBOSamordningsdata_tjenestepensjonForholdListe is java.util.List
            // The specific type of variable LagreTPYtelseResp_tjenestepensjonYtelseListe is java.util.List

            // Clear list to work around WID bug until JR27952 is fixed
            (LagreTPYtelseResp_tjenestepensjonYtelseListe as MutableList<*>?)!!.clear()

            if (GBOSamordningsdata_tjenestepensjonForholdListe != null) {
                val iter: Iterator = (GBOSamordningsdata_tjenestepensjonForholdListe as List<*>).iterator()
                while (iter.hasNext()) {
                    val tpForhold: DataObject = iter.next() as DataObject
                    val iterator: Iterator = (tpForhold.getList("tjenestepensjonYtelseListe") as List<*>).iterator()
                    while (iterator.hasNext()) {
                        val gboTpYtelse: DataObject = iterator.next() as DataObject
                        val tpYtelse: DataObject =
                            DataFactory.INSTANCE.create("http://nav.no/elsam/tpsamordningregistrering/V0_5", "TPYtelse")
                        tpYtelse.setString("tpnr", tpForhold.getString("tpnr"))
                        tpYtelse.setString("tpArt", gboTpYtelse.getString("ytelseKode"))
                        if (gboTpYtelse.getString("iverksattFom") != null) {
                            tpYtelse.setDate("datoFom", DateUtil.parseWIDString(gboTpYtelse.getString("iverksattFom")))
                        }
                        if (gboTpYtelse.getString("iverksattTom") != null) {
                            tpYtelse.setDate("datoTom", DateUtil.parseWIDString(gboTpYtelse.getString("iverksattTom")))
                        }
                        (LagreTPYtelseResp_tjenestepensjonYtelseListe as MutableList<*>?)!!.add(tpYtelse)
                    }
                }
            }
            LagreTPYtelseResp.setTjenestepensjonYtelseListe(LagreTPYtelseResp_tjenestepensjonYtelseListe) // custom.output assignment (executionOrder=5)
        }
        LagreTPYtelseResp.ufullstendigeData = GBOSamordningsdata.ufullstendigeData // move (executionOrder=6)
    }

    fun GBOSluttpoengtallTOSluttpoengtall(GBOSluttpoengtall: GBOSluttpoengtall, Sluttpoengtall: Sluttpoengtall) {
        Sluttpoengtall.setPoengtall(GBOSluttpoengtall.poengtall) // move (executionOrder=1)
        Sluttpoengtall.poengrekke.setPa(GBOSluttpoengtall.poengrekke.poengAr) // move (executionOrder=2)
        Sluttpoengtall.poengrekke.setPaF92(GBOSluttpoengtall.poengrekke.poengArFor92) // move (executionOrder=3)
        Sluttpoengtall.poengrekke.setPaE91(GBOSluttpoengtall.poengrekke.poengArEtter91) // move (executionOrder=4)
        Sluttpoengtall.poengrekke.setTpiFaktor(GBOSluttpoengtall.poengrekke.tidligerePenInntektFaktor) // move (executionOrder=5)
    }

    fun GBOVedtakTOVedtak(GBOVedtak: GBOVedtak, Vedtak: Vedtak) {
        Vedtak.saksKode = GBOVedtak.saksKode // move (executionOrder=1)
        Vedtak.vedtakId = GBOVedtak.vedtakId // move (executionOrder=2)
        Vedtak.vedtaksKode = GBOVedtak.vedtaksKode // move (executionOrder=3)
        Vedtak.regelverkKode = GBOVedtak.kravHode.regelverkKode // move (executionOrder=4)
        GBOBeregningTOBeregning(GBOVedtak.beregningListe, Vedtak.beregningListe) // submap (executionOrder=5)
        GBOBeregningsResultatTOBeregning2011(
            GBOVedtak.beregningsResultatListe,
            Vedtak.beregning2011Liste
        ) // submap (executionOrder=6)
        GBOVilkarsvedtakTOVilkarsvedtak(GBOVedtak.vilkarsvedtakListe, Vedtak.barnListe) // submap (executionOrder=7)
        GBOBeregningsResultatUforetrygdTOBeregningUforetrygd(
            GBOVedtak.beregningsresultatUforetrygdListe,
            Vedtak.beregningUforetrygdListe
        ) // submap (executionOrder=8)
        run {
            val GBOVedtak_virkningFom = GBOVedtak.virkningFom // custom.input.forEach (executionOrder=9)
            var Vedtak_virkningFom: String? = null // custom.output declaration (executionOrder=9)
            // The specific type of variable GBOVedtak_virkningFom is java.lang.String
            // The specific type of variable Vedtak_virkningFom is java.util.Date
            if (GBOVedtak_virkningFom != null) {
                Vedtak_virkningFom = DateUtil.parseWIDString(GBOVedtak_virkningFom)
            }
            Vedtak.setVirkningFom(Vedtak_virkningFom) // custom.output assignment (executionOrder=9)
        }
        run {
            val GBOVedtak_virkningTom = GBOVedtak.virkningTom // custom.input.forEach (executionOrder=10)
            var Vedtak_virkningTom: String? = null // custom.output declaration (executionOrder=10)
            // The specific type of variable GBOVedtak_virkningTom is java.lang.String
            // The specific type of variable Vedtak_virkningTom is java.util.Date
            if (GBOVedtak_virkningTom != null) {
                Vedtak_virkningTom = DateUtil.parseWIDString(GBOVedtak_virkningTom)
            }
            Vedtak.setVirkningTom(Vedtak_virkningTom) // custom.output assignment (executionOrder=10)
        }
        run {
            val GBOVedtak_kravHode_kravVelgKode =
                GBOVedtak.kravHode.kravVelgKode // custom.input.forEach (executionOrder=11)
            var Vedtak_typeKrigspensjon: String? = null // custom.output declaration (executionOrder=11)
            // The specific type of variable GBOVedtak_kravHode_kravVelgKode is java.lang.String
            // The specific type of variable Vedtak_typeKrigspensjon is java.lang.String
            val gyldigeKoder = arrayOf(
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

            if (GBOVedtak_kravHode_kravVelgKode != null) {
                if (Arrays.asList(gyldigeKoder).contains(GBOVedtak_kravHode_kravVelgKode)) {
                    Vedtak_typeKrigspensjon = GBOVedtak_kravHode_kravVelgKode
                }
            }
            Vedtak.typeKrigspensjon = Vedtak_typeKrigspensjon // custom.output assignment (executionOrder=11)
        }
    }

    fun GBOVilkarsvedtakTOVilkarsvedtak(GBOVilkarsvedtak: GBOVilkarsvedtak, Vilkarsvedtak: Vilkarsvedtak) {
        Vilkarsvedtak.gjelderFnr = GBOVilkarsvedtak.gjelderFnr // move (executionOrder=1)
        run {
            val GBOVilkarsvedtak_virkningTom = GBOVilkarsvedtak.virkningTom // custom.input.forEach (executionOrder=2)
            var Vilkarsvedtak_tom: String? = null // custom.output declaration (executionOrder=2)

            // The specific type of variable GBOVilkarsvedtak_virkningTom is java.lang.String
            // The specific type of variable Vilkarsvedtak_tom is java.util.Date
            if (GBOVilkarsvedtak_virkningTom != null) {
                Vilkarsvedtak_tom = DateUtil.parseWIDString(GBOVilkarsvedtak_virkningTom)
            }
            Vilkarsvedtak.setTom(Vilkarsvedtak_tom) // custom.output assignment (executionOrder=2)
        }
        run {
            val GBOVilkarsvedtak_virkningFom = GBOVilkarsvedtak.virkningFom // custom.input.forEach (executionOrder=3)
            var Vilkarsvedtak_fom: String? = null // custom.output declaration (executionOrder=3)
            // The specific type of variable GBOVilkarsvedtak_virkningFom is java.lang.String
            // The specific type of variable Vilkarsvedtak_fom is java.util.Date
            if (GBOVilkarsvedtak_virkningFom != null) {
                Vilkarsvedtak_fom = DateUtil.parseWIDString(GBOVilkarsvedtak_virkningFom)
            }

            Vilkarsvedtak.setFom(Vilkarsvedtak_fom) // custom.output assignment (executionOrder=3)
        }
    }

    fun HentSamordningsdataReqIntTOGBOHentSamordningsdataRequest(
        HentSamordningsdataReqInt: HentSamordningsdataReqInt,
        GBOHentSamordningsdataRequest: GBOHentSamordningsdataRequest
    ) {
        GBOHentSamordningsdataRequest.setSvarBrev("false") // set (executionOrder=1)
        GBOHentSamordningsdataRequest.tpnr = HentSamordningsdataReqInt.extRequest.tpnr // move (executionOrder=2)
        GBOHentSamordningsdataRequest.fnr = HentSamordningsdataReqInt.extRequest.fnr // move (executionOrder=3)
        run {
            val HentSamordningsdataReqInt_extRequest_datoFom: String? =
                HentSamordningsdataReqInt.extRequest.datoFom // custom.input.forEach (executionOrder=4)
            var GBOHentSamordningsdataRequest_fom: String? = null // custom.output declaration (executionOrder=4)

            // The specific type of variable HentSamordningsdataReqInt_extRequest_datoFom is java.util.Date
            // The specific type of variable GBOHentSamordningsdataRequest_fom is java.lang.String
            if (HentSamordningsdataReqInt_extRequest_datoFom != null) {
                GBOHentSamordningsdataRequest_fom =
                    DateUtil.formatWIDString(HentSamordningsdataReqInt_extRequest_datoFom as Date?)
            }
            GBOHentSamordningsdataRequest.fom =
                GBOHentSamordningsdataRequest_fom // custom.output assignment (executionOrder=4)
        }
        GBOHentSamordningsdataRequest.tssEksternId = HentSamordningsdataReqInt.tssEksternId // move (executionOrder=5)
    }

    fun LagreTPYtelseReqIntTOGBOOpprettTPSamordningRequest(
        LagreTPYtelseReqInt: LagreTPYtelseReqInt,
        GBOOpprettTPSamordningRequest: GBOOpprettTPSamordningRequest
    ) {
        GBOOpprettTPSamordningRequest.setSvarBrev("false") // set (executionOrder=1)
        GBOOpprettTPSamordningRequest.tpnr = LagreTPYtelseReqInt.extRequest.tpnr // move (executionOrder=2)
        GBOOpprettTPSamordningRequest.fnr = LagreTPYtelseReqInt.extRequest.fnr // move (executionOrder=3)
        run {
            val LagreTPYtelseReqInt_extRequest_datoFom: String? =
                LagreTPYtelseReqInt.extRequest.datoFom // custom.input.forEach (executionOrder=4)
            var GBOOpprettTPSamordningRequest_iverksattFom: String? =
                null // custom.output declaration (executionOrder=4)

            // The specific type of variable LagreTPYtelseReqInt_extRequest_datoFom is java.util.Date
            // The specific type of variable GBOOpprettTPSamordningRequest_iverksattFom is java.lang.String
            if (LagreTPYtelseReqInt_extRequest_datoFom != null) GBOOpprettTPSamordningRequest_iverksattFom =
                DateUtil.formatWIDString(LagreTPYtelseReqInt_extRequest_datoFom as Date?)


            GBOOpprettTPSamordningRequest.iverksattFom =
                GBOOpprettTPSamordningRequest_iverksattFom // custom.output assignment (executionOrder=4)
        }
        GBOOpprettTPSamordningRequest.tssEksternId = LagreTPYtelseReqInt.tssEksternId // move (executionOrder=5)
        GBOOpprettTPSamordningRequest.ytelseKode = LagreTPYtelseReqInt.extRequest.tpArt // move (executionOrder=6)
        run {
            val LagreTPYtelseReqInt_extRequest_datoFomMedlemskap: String =
                LagreTPYtelseReqInt.extRequest.datoFomMedlemskap // custom.input.forEach (executionOrder=7)
            var GBOOpprettTPSamordningRequest_innmeldtFom: String? =
                null // custom.output declaration (executionOrder=7)
            // The specific type of variable LagreTPYtelseReqInt_extRequest_datoFomMedlemskap is java.util.Date
            // The specific type of variable GBOOpprettTPSamordningRequest_innmeldtFom is java.lang.String
            GBOOpprettTPSamordningRequest_innmeldtFom =
                DateUtil.formatWIDString(LagreTPYtelseReqInt_extRequest_datoFomMedlemskap as Date)
            GBOOpprettTPSamordningRequest.innmeldtFom =
                GBOOpprettTPSamordningRequest_innmeldtFom // custom.output assignment (executionOrder=7)
        }
    }

    fun OpprettRefusjonskravReqIntTOGBOOpprettRefusjonskravRequest(
        OpprettRefusjonskravReqInt: OpprettRefusjonskravReqInt,
        GBOOpprettRefusjonskravRequest: GBOOpprettRefusjonskravRequest
    ) {
        GBOOpprettRefusjonskravRequest.svarRefusjonskrav.tpnr =
            OpprettRefusjonskravReqInt.extRequest.tpnr // move (executionOrder=1)
        GBOOpprettRefusjonskravRequest.svarRefusjonskrav.fnr =
            OpprettRefusjonskravReqInt.extRequest.fnr // move (executionOrder=2)
        GBOOpprettRefusjonskravRequest.svarRefusjonskrav.samId =
            OpprettRefusjonskravReqInt.extRequest.samordningsmeldingId // move (executionOrder=3)
        GBOOpprettRefusjonskravRequest.svarRefusjonskrav.refusjonskrav =
            OpprettRefusjonskravReqInt.extRequest.refusjonskrav // move (executionOrder=4)
        PeriodisertBelopTOGBOPeriodisertBelop(
            OpprettRefusjonskravReqInt.extRequest.periodisertBelopListe,
            GBOOpprettRefusjonskravRequest.svarRefusjonskrav.periodisertBelopListe
        ) // submap (executionOrder=5)
        GBOOpprettRefusjonskravRequest.svarRefusjonskrav.tssEksternId =
            OpprettRefusjonskravReqInt.tssEksternId // move (executionOrder=6)
    }

    fun PeriodisertBelopTOGBOPeriodisertBelop(
        PeriodisertBelop: PeriodisertBelop,
        GBOPeriodisertBelop: GBOPeriodisertBelop
    ) {
        GBOPeriodisertBelop.setBelop(PeriodisertBelop.belop) // move (executionOrder=1)
        GBOPeriodisertBelop.kravstillersRef = PeriodisertBelop.kravstillersReferanse // move (executionOrder=2)
        run {
            val PeriodisertBelop_datoFom: String? = PeriodisertBelop.datoFom // custom.input.forEach (executionOrder=3)
            var GBOPeriodisertBelop_datoFom: String? = null // custom.output declaration (executionOrder=3)

            // The specific type of variable PeriodisertBelop_datoFom is java.util.Date
            // The specific type of variable GBOPeriodisertBelop_datoFom is java.lang.String
            if (PeriodisertBelop_datoFom != null) {
                GBOPeriodisertBelop_datoFom = DateUtil.formatWIDString(PeriodisertBelop_datoFom as Date?)
            }
            GBOPeriodisertBelop.datoFom = GBOPeriodisertBelop_datoFom // custom.output assignment (executionOrder=3)
        }
        run {
            val PeriodisertBelop_datoTom: String? = PeriodisertBelop.datoTom // custom.input.forEach (executionOrder=4)
            var GBOPeriodisertBelop_datoTom: String? = null // custom.output declaration (executionOrder=4)

            // The specific type of variable PeriodisertBelop_datoTom is java.util.Date
            // The specific type of variable GBOPeriodisertBelop_datoTom is java.lang.String
            if (PeriodisertBelop_datoTom != null) {
                GBOPeriodisertBelop_datoTom = DateUtil.formatWIDString(PeriodisertBelop_datoTom as Date?)
            }
            GBOPeriodisertBelop.datoTom = GBOPeriodisertBelop_datoTom // custom.output assignment (executionOrder=4)
        }
    }

    fun SlettTPYtelseReqIntTOGBOSlettTPSamordningRequest(
        SlettTPYtelseReqInt: SlettTPYtelseReqInt,
        GBOSlettTPSamordningRequest: GBOSlettTPSamordningRequest
    ) {
        GBOSlettTPSamordningRequest.tpnr = SlettTPYtelseReqInt.extRequest.tpnr // move (executionOrder=1)
        GBOSlettTPSamordningRequest.fnr = SlettTPYtelseReqInt.extRequest.fnr // move (executionOrder=2)
        GBOSlettTPSamordningRequest.meldingKode =
            SlettTPYtelseReqInt.extRequest.henvendelseType // move (executionOrder=3)
        run {
            val SlettTPYtelseReqInt_extRequest_datoFom: String =
                SlettTPYtelseReqInt.extRequest.datoFom // custom.input.forEach (executionOrder=4)
            var GBOSlettTPSamordningRequest_iverksattFom: String? = null // custom.output declaration (executionOrder=4)
            // The specific type of variable SlettTPYtelseReqInt_extRequest_datoFom is java.util.Date
            // The specific type of variable GBOSlettTPSamordningRequest_iverksattFom is java.lang.String
            GBOSlettTPSamordningRequest_iverksattFom =
                DateUtil.formatWIDString(SlettTPYtelseReqInt_extRequest_datoFom as Date)
            GBOSlettTPSamordningRequest.iverksattFom =
                GBOSlettTPSamordningRequest_iverksattFom // custom.output assignment (executionOrder=4)
        }
        run {
            val SlettTPYtelseReqInt_extRequest_datoTom: String =
                SlettTPYtelseReqInt.extRequest.datoTom // custom.input.forEach (executionOrder=5)
            var GBOSlettTPSamordningRequest_iverksattTom: String? = null // custom.output declaration (executionOrder=5)
            // The specific type of variable SlettTPYtelseReqInt_extRequest_datoTom is java.util.Date
            // The specific type of variable GBOSlettTPSamordningRequest_iverksattTom is java.lang.String
            GBOSlettTPSamordningRequest_iverksattTom =
                DateUtil.formatWIDString(SlettTPYtelseReqInt_extRequest_datoTom as Date)
            GBOSlettTPSamordningRequest.iverksattTom =
                GBOSlettTPSamordningRequest_iverksattTom // custom.output assignment (executionOrder=5)
        }
        GBOSlettTPSamordningRequest.tssEksternId = SlettTPYtelseReqInt.tssEksternId // move (executionOrder=6)
        GBOSlettTPSamordningRequest.ytelseKode = SlettTPYtelseReqInt.extRequest.tpArt // move (executionOrder=7)
    }

    @Throws(DatatypeConfigurationException::class)
    fun toXMLGregorianCalendar(date: Date?): XMLGregorianCalendar? {
        if (date != null) {
            val calendar = GregorianCalendar()
            calendar.time = date
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar)
        } else {
            return null
        }
    }

    @Throws(DatatypeConfigurationException::class)
    fun toXMLGregorianCalendar(date: String?): XMLGregorianCalendar? {
        return if (null != date) DatatypeFactory.newInstance().newXMLGregorianCalendar(date) else null
    }

    fun toInteger(value: String?): Int? {
        return value?.toInt()
    }

    fun <T> toJAXBElement(localPart: String?, type: Class<T>?, `object`: T?): JAXBElement<T>? {
        return if (`object` != null) JAXBElement(QName(localPart), type, `object`) else null
    }
}
