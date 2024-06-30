package no.nav.pensjon.elsam.minibuss.nav_cons_elsam_tptilb_tpsamordningregistrering;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import nav_cons_elsam_tptilb_tpsamordningregistrering.no.nav.asbo.HentSamordningsdataReqInt;
import nav_cons_elsam_tptilb_tpsamordningregistrering.no.nav.asbo.LagreTPYtelseReqInt;
import nav_cons_elsam_tptilb_tpsamordningregistrering.no.nav.asbo.OpprettRefusjonskravReqInt;
import nav_cons_elsam_tptilb_tpsamordningregistrering.no.nav.asbo.SlettTPYtelseReqInt;
import nav_cons_elsam_tptilb_tpsamordningregistrering.no.nav.tpsamordningregistrering.v1_0.asbo.BeregningUforetrygd;
import nav_lib_sak.no.nav.lib.sak.gbo.GBOArbeidOgAktivitetsvedtak;
import nav_lib_sto.no.nav.lib.sto.fault.*;
import nav_lib_sto.no.nav.lib.sto.gbo.*;
import no.nav.elsam.tpsamordningregistrering.v0_5.*;
import no.nav.elsam.tpsamordningregistrering.v0_7.Beregning;
import no.nav.elsam.tpsamordningregistrering.v0_8.Beregning2011;
import no.nav.elsam.tpsamordningregistrering.v1_0.HentSamordningsdataResp;
import no.nav.elsam.tpsamordningregistrering.v1_0.LagreTPYtelseResp;
import no.nav.elsam.tpsamordningregistrering.v1_0.Vedtak;
import no.nav.pensjon.elsam.minibuss.DateUtil;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


public class Mapper {
    public static void FaultKombinasjonInputTOFaultTPYtelseIkkeFunnet(FaultKombinasjonInput FaultKombinasjonInput, FaultTPYtelseIkkeFunnet FaultTPYtelseIkkeFunnet) throws DatatypeConfigurationException {
        FaultTPYtelseIkkeFunnet.setErrorMessage(FaultKombinasjonInput.getErrorMessage()); // move (executionOrder=1)
        FaultTPYtelseIkkeFunnet.setErrorSource(FaultKombinasjonInput.getErrorSource()); // move (executionOrder=2)
        FaultTPYtelseIkkeFunnet.setRootCause(FaultKombinasjonInput.getRootCause()); // move (executionOrder=3)
        FaultTPYtelseIkkeFunnet.setDateTimeStamp(toXMLGregorianCalendar(FaultKombinasjonInput.getDateTimeStamp())); // move (executionOrder=4)
    }

    public static void FaultPersonIkkeFunnetTOFaultGenerisk(nav_lib_frg.no.nav.lib.frg.fault.FaultPersonIkkeFunnet FaultPersonIkkeFunnet, FaultGenerisk FaultGenerisk) {
        FaultGenerisk.setErrorCode("UnknownId"); // set (executionOrder=1)
        FaultGenerisk.setErrorDescription(FaultPersonIkkeFunnet.getErrorMessage()); // move (executionOrder=2)
    }

    public static void FaultRefKravAlleredeRegElUtenforFristTOFaultAlleredeMottattRefusjonskrav(FaultRefKravAlleredeRegElUtenforFrist FaultRefKravAlleredeRegElUtenforFrist, FaultAlleredeMottattRefusjonskrav FaultAlleredeMottattRefusjonskrav) throws DatatypeConfigurationException {
        FaultAlleredeMottattRefusjonskrav.setErrorMessage(FaultRefKravAlleredeRegElUtenforFrist.getErrorMessage()); // move (executionOrder=1)
        FaultAlleredeMottattRefusjonskrav.setErrorSource(FaultRefKravAlleredeRegElUtenforFrist.getErrorSource()); // move (executionOrder=2)
        FaultAlleredeMottattRefusjonskrav.setRootCause(FaultRefKravAlleredeRegElUtenforFrist.getRootCause()); // move (executionOrder=3)
        FaultAlleredeMottattRefusjonskrav.setDateTimeStamp(toXMLGregorianCalendar(FaultRefKravAlleredeRegElUtenforFrist.getDateTimeStamp())); // move (executionOrder=4)
    }

    public static void FaultSamIdIkkeGyldigTOFaultSamordningsIdIkkeFunnet(FaultSamIdIkkeGyldig FaultSamIdIkkeGyldig, FaultSamordningsIdIkkeFunnet FaultSamordningsIdIkkeFunnet) throws DatatypeConfigurationException {
        FaultSamordningsIdIkkeFunnet.setErrorMessage(FaultSamIdIkkeGyldig.getErrorMessage()); // move (executionOrder=1)
        FaultSamordningsIdIkkeFunnet.setErrorSource(FaultSamIdIkkeGyldig.getErrorSource()); // move (executionOrder=2)
        FaultSamordningsIdIkkeFunnet.setRootCause(FaultSamIdIkkeGyldig.getRootCause()); // move (executionOrder=3)
        FaultSamordningsIdIkkeFunnet.setDateTimeStamp(toXMLGregorianCalendar(FaultSamIdIkkeGyldig.getDateTimeStamp())); // move (executionOrder=4)
    }

    public static void FaultSvarUtenforPeriodeTOFaultRefusjonskravUtenforSamordningspliktigPeriode(FaultSvarUtenforPeriode FaultSvarUtenforPeriode, FaultRefusjonskravUtenforSamordningspliktigPeriode FaultRefusjonskravUtenforSamordningspliktigPeriode) throws DatatypeConfigurationException {
        FaultRefusjonskravUtenforSamordningspliktigPeriode.setErrorMessage(FaultSvarUtenforPeriode.getErrorMessage()); // move (executionOrder=1)
        FaultRefusjonskravUtenforSamordningspliktigPeriode.setErrorSource(FaultSvarUtenforPeriode.getErrorSource()); // move (executionOrder=2)
        FaultRefusjonskravUtenforSamordningspliktigPeriode.setRootCause(FaultSvarUtenforPeriode.getRootCause()); // move (executionOrder=3)
        FaultRefusjonskravUtenforSamordningspliktigPeriode.setDateTimeStamp(toXMLGregorianCalendar(FaultSvarUtenforPeriode.getDateTimeStamp())); // move (executionOrder=4)
    }

    public static void FaultYtelseAlleredeRegistrertTOFaultTPYtelseAlleredeRegistrert(FaultYtelseAlleredeRegistrert FaultYtelseAlleredeRegistrert, FaultTPYtelseAlleredeRegistrert FaultTPYtelseAlleredeRegistrert) throws DatatypeConfigurationException {
        FaultTPYtelseAlleredeRegistrert.setErrorMessage(FaultYtelseAlleredeRegistrert.getErrorMessage()); // move (executionOrder=1)
        FaultTPYtelseAlleredeRegistrert.setErrorSource(FaultYtelseAlleredeRegistrert.getErrorSource()); // move (executionOrder=2)
        FaultTPYtelseAlleredeRegistrert.setRootCause(FaultYtelseAlleredeRegistrert.getRootCause()); // move (executionOrder=3)
        FaultTPYtelseAlleredeRegistrert.setDateTimeStamp(toXMLGregorianCalendar(FaultYtelseAlleredeRegistrert.getDateTimeStamp())); // move (executionOrder=4)
    }

    public static void FaultYtelseIkkeIverksattTOFaultTPForholdIkkeIverksatt(FaultYtelseIkkeIverksatt FaultYtelseIkkeIverksatt, FaultTPForholdIkkeIverksatt FaultTPForholdIkkeIverksatt) throws DatatypeConfigurationException {
        FaultTPForholdIkkeIverksatt.setErrorMessage(FaultYtelseIkkeIverksatt.getErrorMessage()); // move (executionOrder=1)
        FaultTPForholdIkkeIverksatt.setErrorSource(FaultYtelseIkkeIverksatt.getErrorSource()); // move (executionOrder=2)
        FaultTPForholdIkkeIverksatt.setRootCause(FaultYtelseIkkeIverksatt.getRootCause()); // move (executionOrder=3)
        FaultTPForholdIkkeIverksatt.setDateTimeStamp(toXMLGregorianCalendar(FaultYtelseIkkeIverksatt.getDateTimeStamp())); // move (executionOrder=4)
    }

    public static void GBOArbeidOgAktivitetsvedtakTOArbeidOgAktivitetsvedtak(GBOArbeidOgAktivitetsvedtak GBOArbeidOgAktivitetsvedtak, ArbeidOgAktivitetsvedtak ArbeidOgAktivitetsvedtak) throws DatatypeConfigurationException {
        ArbeidOgAktivitetsvedtak.setVedtakId(GBOArbeidOgAktivitetsvedtak.getVedtakId()); // move (executionOrder=1)
        ArbeidOgAktivitetsvedtak.setGjelderFnr(GBOArbeidOgAktivitetsvedtak.getGjelderFnr()); // move (executionOrder=2)
        ArbeidOgAktivitetsvedtak.setVirkningFom(toXMLGregorianCalendar(DateUtil.parseWIDString(GBOArbeidOgAktivitetsvedtak.getVirkningFom()))); // custom.output assignment (executionOrder=3)
        ArbeidOgAktivitetsvedtak.setVirkningTom(toXMLGregorianCalendar(DateUtil.parseWIDString(GBOArbeidOgAktivitetsvedtak.getVirkningTom()))); // custom.output assignment (executionOrder=4)
        ArbeidOgAktivitetsvedtak.setVedtaksKode(GBOArbeidOgAktivitetsvedtak.getVedtakstypeKode()); // move (executionOrder=5)
        ArbeidOgAktivitetsvedtak.setVedtakstatusKode(GBOArbeidOgAktivitetsvedtak.getVedtakstatusKode()); // move (executionOrder=6)
        ArbeidOgAktivitetsvedtak.setSaksKode(GBOArbeidOgAktivitetsvedtak.getSakstypeKode()); // move (executionOrder=7)
        ArbeidOgAktivitetsvedtak.setNavEnhet(GBOArbeidOgAktivitetsvedtak.getNavEnhet()); // move (executionOrder=8)
        ArbeidOgAktivitetsvedtak.setUtfallKode(GBOArbeidOgAktivitetsvedtak.getUtfallKode()); // move (executionOrder=9)
        ArbeidOgAktivitetsvedtak.setRettighetKode(GBOArbeidOgAktivitetsvedtak.getRettighetKode()); // move (executionOrder=10)
        ArbeidOgAktivitetsvedtak.setDagsatsUBT(GBOArbeidOgAktivitetsvedtak.getVedtaksfaktaAAP().getDagsatsUBT()); // move (executionOrder=11)
        ArbeidOgAktivitetsvedtak.setDagsatsMBT(GBOArbeidOgAktivitetsvedtak.getVedtaksfaktaAAP().getDagsatsMBT()); // move (executionOrder=12)
        ArbeidOgAktivitetsvedtak.setDagstatsMBTFS(GBOArbeidOgAktivitetsvedtak.getVedtaksfaktaAAP().getDagsatsMBTFS()); // move (executionOrder=13)
        ArbeidOgAktivitetsvedtak.setAntallBarn(toInteger(GBOArbeidOgAktivitetsvedtak.getVedtaksfaktaAAP().getAntallBarn())); // move (executionOrder=14)
        ArbeidOgAktivitetsvedtak.setBeregningsgrunnlag(GBOArbeidOgAktivitetsvedtak.getVedtaksfaktaAAP().getBeregningsgrunnlag()); // move (executionOrder=15)
    }

    public static void GBOBeregningNokkelinfoTOBeregningNokkelinfo(nav_lib_pen.no.nav.lib.pen.gbo.GBOBeregningNokkelinfo GBOBeregningNokkelinfo, BeregningNokkelinfo BeregningNokkelinfo) {
        BeregningNokkelinfo.setFnr(GBOBeregningNokkelinfo.getFnr()); // move (executionOrder=1)
        BeregningNokkelinfo.setGrunnlagsrolleKode(GBOBeregningNokkelinfo.getGrunnlagsrolleKode()); // move (executionOrder=2)
        GBOSluttpoengtallTOSluttpoengtall(GBOBeregningNokkelinfo.getSpt(),BeregningNokkelinfo.getSpt()); // submap (executionOrder=3)
        GBOSluttpoengtallTOSluttpoengtall(GBOBeregningNokkelinfo.getYpt(),BeregningNokkelinfo.getYpt()); // submap (executionOrder=4)
        GBOSluttpoengtallTOSluttpoengtall(GBOBeregningNokkelinfo.getOpt(),BeregningNokkelinfo.getOpt()); // submap (executionOrder=5)
        BeregningNokkelinfo.setAnvendtTrygdetid(toJAXBElement("anvendtTrygdetid", Integer.class, toInteger(GBOBeregningNokkelinfo.getTtAnv()))); // move (executionOrder=6)
        BeregningNokkelinfo.setAnvendtIBeregningen(toJAXBElement("anvendtIBeregningen", Boolean.class, GBOBeregningNokkelinfo.getAnvendtIBeregningen())); // move (executionOrder=7)
    }

    public static void GBOBeregningTOBeregning(nav_lib_pen.no.nav.lib.pen.gbo.GBOBeregning GBOBeregning, Beregning Beregning_1) throws DatatypeConfigurationException {
        Beregning_1.setVirkningFom(toXMLGregorianCalendar(GBOBeregning.getVirkDatoFom() != null ? DateUtil.parseWIDString(GBOBeregning.getVirkDatoFom()) : null)); // custom.output assignment (executionOrder=1)
        Beregning_1.setVirkningTom(toXMLGregorianCalendar(GBOBeregning.getVirkDatoTom() != null ? DateUtil.parseWIDString(GBOBeregning.getVirkDatoTom()) : null)); // custom.output assignment (executionOrder=2)
        Beregning_1.setResultatKode(GBOBeregning.getResultatKode()); // move (executionOrder=3)
        Beregning_1.setP67Berergning(GBOBeregning.getP67Beregning()); // move (executionOrder=4)
        Beregning_1.setAfpPensjonsgrad(GBOBeregning.getAfpPensjonsgrad()); // move (executionOrder=5)
        Beregning_1.setBruttoGrunnpensjon(GBOBeregning.getGrunnpensjon().getBrutto()); // move (executionOrder=6)
        Beregning_1.setBruttoTilleggspensjon(GBOBeregning.getTilleggspensjon().getBrutto()); // move (executionOrder=7)
        Beregning_1.setBruttoSertillegg(GBOBeregning.getSertillegg().getBrutto()); // move (executionOrder=8)
        Beregning_1.setSertilleggSats(GBOBeregning.getSertillegg().getSertilleggsats()); // move (executionOrder=9)
        Beregning_1.setBruttoAFPtillegg(GBOBeregning.getAfpTillegg().getBrutto()); // move (executionOrder=10)
        Beregning_1.setBruttoBarnetilleggFellesbarn(GBOBeregning.getBarnetilleggFellesBarn().getBrutto()); // move (executionOrder=11)
        Beregning_1.setAntallBarnetilleggFellesbarn(GBOBeregning.getBarnetilleggFellesBarn().getAntallBarn()); // move (executionOrder=12)
        Beregning_1.setBruttoBarnetilleggSerkullsbarn(GBOBeregning.getBarnetilleggSerkullsbarn().getBrutto()); // move (executionOrder=13)
        Beregning_1.setAntallBarnetilleggSerkullsbarn(GBOBeregning.getBarnetilleggSerkullsbarn().getAntallBarn()); // move (executionOrder=14)
        Beregning_1.setVentetilleggProsent(GBOBeregning.getVentetillegg().getVentetilleggProsent()); // move (executionOrder=15)
        Beregning_1.setYrkesskadegrad(GBOBeregning.getYrkesskadegrad()); // move (executionOrder=16)
        Beregning_1.setUforegrad(GBOBeregning.getUforegrad()); // move (executionOrder=17)
        Beregning_1.getKrigOgGammelYrkesskade().setPensjonsgrad(GBOBeregning.getKrigOgGammelYrkesskade().getPensjonsgrad()); // move (executionOrder=18)
        Beregning_1.getKrigOgGammelYrkesskade().setGrunnlag(GBOBeregning.getKrigOgGammelYrkesskade().getGrunnlagForUtbetaling()); // move (executionOrder=19)
        Beregning_1.getKrigOgGammelYrkesskade().setForholdstallYG(GBOBeregning.getKrigOgGammelYrkesskade().getForholdstallYG()); // move (executionOrder=20)
        Beregning_1.setGrunnpensjonsats(GBOBeregning.getGrunnpensjon().getGrunnpensjonsats()); // move (executionOrder=21)
        Beregning_1.setBrutto(GBOBeregning.getBrutto()); // move (executionOrder=22)
        Beregning_1.setNetto(GBOBeregning.getNetto()); // move (executionOrder=23)
        Beregning_1.setNettoGrunnpensjon(GBOBeregning.getGrunnpensjon().getNetto()); // move (executionOrder=24)
        Beregning_1.setNettoTilleggspensjon(GBOBeregning.getTilleggspensjon().getNetto()); // move (executionOrder=25)
        Beregning_1.setNettoSertillegg(GBOBeregning.getSertillegg().getNetto()); // move (executionOrder=26)
        Beregning_1.setNettoAFPtillegg(GBOBeregning.getAfpTillegg().getNetto()); // move (executionOrder=27)
        Beregning_1.setEktefelletillegg(GBOBeregning.getEktefelleTillegg().getNetto()); // move (executionOrder=28)
        Beregning_1.setNettoBarnetilleggFellesbarn(GBOBeregning.getBarnetilleggFellesBarn().getNetto()); // move (executionOrder=29)
        Beregning_1.setNettoBarnetilleggSerkullsbarn(GBOBeregning.getBarnetilleggSerkullsbarn().getNetto()); // move (executionOrder=30)
        Beregning_1.setNettoVentetillegg(GBOBeregning.getVentetillegg().getNetto()); // move (executionOrder=31)
        Beregning_1.setParagraf851Tillegg(GBOBeregning.getParagraf851Tillegg().getNetto()); // move (executionOrder=32)
        Beregning_1.getKrigOgGammelYrkesskade().setBelop(GBOBeregning.getKrigOgGammelYrkesskade().getNetto()); // move (executionOrder=33)
        Beregning_1.setFremtidigPensjonsgivendeInntektBruker(GBOBeregning.getInntektBruktIAvkortning()); // move (executionOrder=34)
        GBOBeregningNokkelinfoTOBeregningNokkelinfo(GBOBeregning.getBeregningNokkelinfoListe(),Beregning_1.getInvolvertePersonerNokkelinfoListe()); // submap (executionOrder=35)
        Beregning_1.setMinstenivatilleggPensjonistpar(GBOBeregning.getMinstenivatilleggPensjonistpar().getNetto()); // move (executionOrder=36)
        Beregning_1.setMinstenivatilleggIndividuelt(GBOBeregning.getMinstenivatilleggIndividuelt().getNetto()); // move (executionOrder=37)
    }

    public static void GBOBeregningsResultatTOBeregning2011(nav_lib_pen.no.nav.lib.pen.gbo.GBOBeregningsResultat GBOBeregningsResultat, Beregning2011 Beregning2011) {
    {
        String GBOBeregningsResultat_virkFom = GBOBeregningsResultat.getVirkFom(); // custom.input.forEach (executionOrder=1)
        String Beregning2011_virkningFom = null; // custom.output declaration (executionOrder=1)
        // The specific type of variable GBOBeregningsResultat_virkFom is java.lang.String
        // The specific type of variable Beregning2011_virkningFom is java.util.Date
        if (GBOBeregningsResultat_virkFom != null) {
        	Beregning2011_virkningFom = DateUtil.parseWIDString((String) GBOBeregningsResultat_virkFom);
        }
        Beregning2011.setVirkningFom(Beregning2011_virkningFom); // custom.output assignment (executionOrder=1)

    }
    {
        String GBOBeregningsResultat_virkTom = GBOBeregningsResultat.getVirkTom(); // custom.input.forEach (executionOrder=2)
        String Beregning2011_virkningTom = null; // custom.output declaration (executionOrder=2)
        // The specific type of variable GBOBeregningsResultat_virkTom is java.lang.String
        // The specific type of variable Beregning2011_virkningTom is java.util.Date
        if (GBOBeregningsResultat_virkTom != null) {
        	Beregning2011_virkningTom = DateUtil.parseWIDString((String) GBOBeregningsResultat_virkTom);
        }
        Beregning2011.setVirkningTom(Beregning2011_virkningTom); // custom.output assignment (executionOrder=2)

    }
        Beregning2011.setUttaksgrad(GBOBeregningsResultat.getUttaksgrad()); // move (executionOrder=3)
        Beregning2011.setBasisgp(GBOBeregningsResultat.getBeregningInformasjonKapittel19().getBasisgp()); // move (executionOrder=4)
        Beregning2011.setBasistp(GBOBeregningsResultat.getBeregningInformasjonKapittel19().getBasistp()); // move (executionOrder=5)
        Beregning2011.setBasispt(GBOBeregningsResultat.getBeregningInformasjonKapittel19().getBasispt()); // move (executionOrder=6)
        Beregning2011.setForholdstallUttak(GBOBeregningsResultat.getBeregningInformasjonKapittel19().getForholdstallUttak()); // move (executionOrder=7)
        Beregning2011.setForholdstall67(GBOBeregningsResultat.getBeregningInformasjonKapittel19().getForholdstall67()); // move (executionOrder=8)
        Beregning2011.setGrunnpensjon(GBOBeregningsResultat.getBeregningInformasjonKapittel19().getGp()); // move (executionOrder=9)
        Beregning2011.setGpRestpensjon(GBOBeregningsResultat.getBeregningInformasjonKapittel19().getGpRestpensjon()); // move (executionOrder=10)
        Beregning2011.setTilleggspensjon(GBOBeregningsResultat.getBeregningInformasjonKapittel19().getTp()); // move (executionOrder=11)
        Beregning2011.setTpRestpensjon(GBOBeregningsResultat.getBeregningInformasjonKapittel19().getTpRestpensjon()); // move (executionOrder=12)
        Beregning2011.setPensjonstillegg(GBOBeregningsResultat.getBeregningInformasjonKapittel19().getPt()); // move (executionOrder=13)
        Beregning2011.setPtRestpensjon(GBOBeregningsResultat.getBeregningInformasjonKapittel19().getPtRestpensjon()); // move (executionOrder=14)
        Beregning2011.setTotalBelop(GBOBeregningsResultat.getPensjonUnderUtbetaling().getTotalBelop()); // move (executionOrder=15)
        Beregning2011.setResultatKode(GBOBeregningsResultat.getBeregningInformasjonKapittel19().getResultatType()); // move (executionOrder=16)
        Beregning2011.setMndGrunnpensjon(GBOBeregningsResultat.getPensjonUnderUtbetaling().getGrunnpensjon().getNetto()); // move (executionOrder=17)
        Beregning2011.setMndTilleggspensjon(GBOBeregningsResultat.getPensjonUnderUtbetaling().getTilleggspensjon().getNetto()); // move (executionOrder=18)
        Beregning2011.setMndPensjonstillegg(GBOBeregningsResultat.getPensjonUnderUtbetaling().getPensjonstillegg().getNetto()); // move (executionOrder=19)
        GBOSluttpoengtallTOSluttpoengtall(GBOBeregningsResultat.getBeregningInformasjonKapittel19().getSpt(),Beregning2011.getSpt()); // submap (executionOrder=20)
        Beregning2011.setGrunnpensjonsats(GBOBeregningsResultat.getPensjonUnderUtbetaling().getGrunnpensjon().getGrunnpensjonsats()); // move (executionOrder=21)
        GBOSluttpoengtallTOSluttpoengtall(GBOBeregningsResultat.getBeregningInformasjonKapittel19().getYpt(),Beregning2011.getYpt()); // submap (executionOrder=22)
        Beregning2011.setYrkesskadegrad(GBOBeregningsResultat.getBeregningInformasjonKapittel19().getYrkesskadegrad()); // move (executionOrder=23)
        Beregning2011.setAnvendtTrygdetid(GBOBeregningsResultat.getBeregningInformasjonKapittel19().getTtAnv()); // move (executionOrder=24)
        Beregning2011.setBruttoBarnetilleggFellesbarn(GBOBeregningsResultat.getPensjonUnderUtbetaling().getBarnetilleggFellesbarn().getBrutto()); // move (executionOrder=25)
        Beregning2011.setNettoBarnetilleggFellesbarn(GBOBeregningsResultat.getPensjonUnderUtbetaling().getBarnetilleggFellesbarn().getNetto()); // move (executionOrder=26)
        Beregning2011.setAntallBarnetilleggFellesbarn(GBOBeregningsResultat.getPensjonUnderUtbetaling().getBarnetilleggFellesbarn().getAntallBarn()); // move (executionOrder=27)
        Beregning2011.setBruttoBarnetilleggSerkullsbarn(GBOBeregningsResultat.getPensjonUnderUtbetaling().getBarnetilleggSerkullsbarn().getBrutto()); // move (executionOrder=28)
        Beregning2011.setNettoBarnetilleggSerkullsbarn(GBOBeregningsResultat.getPensjonUnderUtbetaling().getBarnetilleggSerkullsbarn().getNetto()); // move (executionOrder=29)
        Beregning2011.setAntallBarnetilleggSerkullsbarn(GBOBeregningsResultat.getPensjonUnderUtbetaling().getBarnetilleggSerkullsbarn().getAntallBarn()); // move (executionOrder=30)
        Beregning2011.setEktefelletillegg(GBOBeregningsResultat.getPensjonUnderUtbetaling().getEktefelletillegg().getBrutto()); // move (executionOrder=31)
        Beregning2011.setMinstenivatilleggPensjonistpar(GBOBeregningsResultat.getPensjonUnderUtbetaling().getMinstenivatilleggPensjonistpar().getNetto()); // move (executionOrder=32)
        Beregning2011.setAfpLivsvarig(GBOBeregningsResultat.getPensjonUnderUtbetaling().getAfpLivsvarig().getBrutto()); // move (executionOrder=33)
        Beregning2011.setAfpKronetillegg(GBOBeregningsResultat.getPensjonUnderUtbetaling().getAfpKronetillegg().getBrutto()); // move (executionOrder=34)
        Beregning2011.setAfpKompensasjonstillegg(GBOBeregningsResultat.getPensjonUnderUtbetaling().getAfpKompensasjonstillegg().getBrutto()); // move (executionOrder=35)
        Beregning2011.setForholdstallKompensasjonstillegg(GBOBeregningsResultat.getPensjonUnderUtbetaling().getAfpKompensasjonstillegg().getForholdstallKompensasjonstillegg()); // move (executionOrder=36)
        Beregning2011.setAfpOpptjeningTotalbelop(GBOBeregningsResultat.getBeregningInformasjonKapittel19().getUtbetaltForrige()); // move (executionOrder=37)
        Beregning2011.setFnrAvdod(GBOBeregningsResultat.getBeregningsinformasjonAvdod().getFnr()); // move (executionOrder=38)
        GBOSluttpoengtallTOSluttpoengtall(GBOBeregningsResultat.getBeregningsinformasjonAvdod().getSpt(),Beregning2011.getSptAvdod()); // submap (executionOrder=39)
        GBOSluttpoengtallTOSluttpoengtall(GBOBeregningsResultat.getBeregningsinformasjonAvdod().getYpt(),Beregning2011.getYptAvdod()); // submap (executionOrder=40)
        GBOSluttpoengtallTOSluttpoengtall(GBOBeregningsResultat.getBeregningsinformasjonAvdod().getOpt(),Beregning2011.getOptAvdod()); // submap (executionOrder=41)
        Beregning2011.setYrkesskadegradAvdod(GBOBeregningsResultat.getBeregningsinformasjonAvdod().getYrkesskadegrad()); // move (executionOrder=42)
        Beregning2011.setAnvendtTrygdetidAvdod(GBOBeregningsResultat.getBeregningsinformasjonAvdod().getTtAnv()); // move (executionOrder=43)
        Beregning2011.setMinstenivatilleggIndividuelt(GBOBeregningsResultat.getPensjonUnderUtbetaling().getMinstenivatilleggIndividuelt().getNetto()); // move (executionOrder=44)
        Beregning2011.setSkjermingstillegg(GBOBeregningsResultat.getPensjonUnderUtbetaling().getSkjermingstillegg().getNetto()); // move (executionOrder=45)
        Beregning2011.setUforegrad(GBOBeregningsResultat.getPensjonUnderUtbetaling().getSkjermingstillegg().getUforegrad()); // move (executionOrder=46)
    }

    public static void GBOBeregningsResultatUforetrygdTOBeregningUforetrygd(nav_lib_pen.no.nav.lib.pen.gbo.GBOBeregningsResultatUforetrygd GBOBeregningsResultatUforetrygd, BeregningUforetrygd BeregningUforetrygd) {
        BeregningUforetrygd.setInntektForUforhet(GBOBeregningsResultatUforetrygd.getInntektForUforhet()); // move (executionOrder=1)
        BeregningUforetrygd.setNettoUforetrygdOrdinar(GBOBeregningsResultatUforetrygd.getNettoUforetrygdOrdinar()); // move (executionOrder=2)
        BeregningUforetrygd.setBruttoUforetrygdOrdinar(GBOBeregningsResultatUforetrygd.getBruttoUforetrygdOrdinar()); // move (executionOrder=3)
        BeregningUforetrygd.setUforegrad(GBOBeregningsResultatUforetrygd.getUforegrad()); // move (executionOrder=4)
        BeregningUforetrygd.setYrkesskadegrad(GBOBeregningsResultatUforetrygd.getYrkesskadegrad()); // move (executionOrder=5)
        BeregningUforetrygd.setNettoBarnetilleggSerkullsbarn(GBOBeregningsResultatUforetrygd.getNettoBarnetilleggSerkullsbarn()); // move (executionOrder=6)
        BeregningUforetrygd.setBruttoBarnetilleggSerkullsbarn(GBOBeregningsResultatUforetrygd.getBruttoBarnetilleggSerkullsbarn()); // move (executionOrder=7)
        BeregningUforetrygd.setNettoBarnetilleggFellesbarn(GBOBeregningsResultatUforetrygd.getNettoBarnetilleggFellesbarn()); // move (executionOrder=8)
        BeregningUforetrygd.setBruttoBarnetilleggFellesbarn(GBOBeregningsResultatUforetrygd.getBruttoBarnetilleggFellesbarn()); // move (executionOrder=9)
        BeregningUforetrygd.setNettoGjenlevendetillegg(GBOBeregningsResultatUforetrygd.getNettoGjenlevendetillegg()); // move (executionOrder=10)
        BeregningUforetrygd.setBruttoGjenlevendetillegg(GBOBeregningsResultatUforetrygd.getBruttoGjenlevendetillegg()); // move (executionOrder=11)
        BeregningUforetrygd.setNettoEktefelletillegg(GBOBeregningsResultatUforetrygd.getNettoEktefelletillegg()); // move (executionOrder=12)
        BeregningUforetrygd.setResultatKode(GBOBeregningsResultatUforetrygd.getResultatKode()); // move (executionOrder=13)
    {
        String GBOBeregningsResultatUforetrygd_virkFom = GBOBeregningsResultatUforetrygd.getVirkFom(); // custom.input.forEach (executionOrder=14)
        String BeregningUforetrygd_fom = null; // custom.output declaration (executionOrder=14)
        // The specific type of variable GBOBeregningsResultatUforetrygd_virkFom is java.lang.String
        // The specific type of variable BeregningUforetrygd_fom is java.util.Date
        if( GBOBeregningsResultatUforetrygd_virkFom != null ){
        	BeregningUforetrygd_fom = DateUtil.parseWIDString((String) GBOBeregningsResultatUforetrygd_virkFom);
        }
        BeregningUforetrygd.setFom(BeregningUforetrygd_fom); // custom.output assignment (executionOrder=14)

    }
    {
        String GBOBeregningsResultatUforetrygd_virkTom = GBOBeregningsResultatUforetrygd.getVirkTom(); // custom.input.forEach (executionOrder=15)
        String BeregningUforetrygd_tom = null; // custom.output declaration (executionOrder=15)
        // The specific type of variable GBOBeregningsResultatUforetrygd_virkTom is java.lang.String
        // The specific type of variable BeregningUforetrygd_tom is java.util.Date
        if( GBOBeregningsResultatUforetrygd_virkTom != null ){
        	BeregningUforetrygd_tom = DateUtil.parseWIDString((String) GBOBeregningsResultatUforetrygd_virkTom);
        }
        BeregningUforetrygd.setTom(BeregningUforetrygd_tom); // custom.output assignment (executionOrder=15)

    }
        BeregningUforetrygd.setInntektEtterUforhet(GBOBeregningsResultatUforetrygd.getInntektEtterUforhet()); // move (executionOrder=16)
        BeregningUforetrygd.setAnvendtTrygdetid(GBOBeregningsResultatUforetrygd.getAnvendtTrygdetid()); // move (executionOrder=17)
        BeregningUforetrygd.setBenyttetSivilstand(GBOBeregningsResultatUforetrygd.getBenyttetSivilstand()); // move (executionOrder=18)
        BeregningUforetrygd.setBeregningsgrunnlagOrdiner(GBOBeregningsResultatUforetrygd.getBeregningsgrunnlagOrdiner()); // move (executionOrder=19)
        BeregningUforetrygd.setBeregningsgrunnlagYrkesskade(GBOBeregningsResultatUforetrygd.getBeregningsgrunnlagYrkesskade()); // move (executionOrder=20)
        BeregningUforetrygd.setBeregningsgrunnlagOrdinerAvdod(GBOBeregningsResultatUforetrygd.getBeregningsgrunnlagOrdinerAvdod()); // move (executionOrder=21)
        BeregningUforetrygd.setBeregningsgrunnlagYrkesskadeAvdod(GBOBeregningsResultatUforetrygd.getBeregningsgrunnlagYrkesskadeAvdod()); // move (executionOrder=22)
        BeregningUforetrygd.setAnvendtTrygdetidAvdod(GBOBeregningsResultatUforetrygd.getAnvendtTrygdetidAvdod()); // move (executionOrder=23)
        BeregningUforetrygd.setYrkesskadegradAvdod(GBOBeregningsResultatUforetrygd.getYrkesskadegradAvdod()); // move (executionOrder=24)
        BeregningUforetrygd.setInntektBruktIInntektsavkorting(GBOBeregningsResultatUforetrygd.getInntektBruktIInntektsavkorting()); // move (executionOrder=25)
    }

    public static void GBOPersonTOPerson(nav_lib_frg.no.nav.lib.frg.gbo.GBOPerson GBOPerson, Person Person) {
        Person.setFnr(GBOPerson.getFodselsnummer()); // move (executionOrder=1)
        Person.setFornavn(GBOPerson.getFornavn()); // move (executionOrder=2)
        Person.setMellomnavn(GBOPerson.getMellomnavn()); // move (executionOrder=3)
        Person.setEtternavn(GBOPerson.getEtternavn()); // move (executionOrder=4)
        Person.setSivilstand(GBOPerson.getSivilstand()); // move (executionOrder=5)
    {
        String GBOPerson_dodsdato = GBOPerson.getDodsdato(); // custom.input.forEach (executionOrder=6)
        String Person_dodsdato = null; // custom.output declaration (executionOrder=6)
        // The specific type of variable GBOPerson_dodsdato is java.lang.String
        // The specific type of variable Person_dodsdato is java.util.Date
        if (GBOPerson_dodsdato != null) {
        	Person_dodsdato = DateUtil.parseWIDString((String) GBOPerson_dodsdato);
        }

        Person.setDodsdato(Person_dodsdato); // custom.output assignment (executionOrder=6)

    }
    {
        String GBOPerson = GBOPerson; // custom.input.forEach (executionOrder=7)
        String Person_utbetalingsadresse = null; // custom.output declaration (executionOrder=7)
        // The specific type of variable Person_utbetalingsadresse is commonj.sdo.DataObject
        if (GBOPerson != null && Person_utbetalingsadresse != null) {
        	DataObject person = (DataObject) Person_utbetalingsadresse;

        	if (GBOPerson.getDataObject("bostedsAdresse") != null) {
        		DataObject bostedsAdresse = (DataObject) GBOPerson.getDataObject("bostedsAdresse");
        		String postnr = bostedsAdresse.getString("postnr");
        		if (postnr != null && !postnr.equals("")) {
        			person.setString("adresselinje1", bostedsAdresse.getString("boadresse1"));
        			person.setString("adresselinje2", bostedsAdresse.getString("boadresse2"));
        			person.setString("postnr", bostedsAdresse.getString("postnr"));
        			person.setString("poststed", bostedsAdresse.getString("poststed"));
        		}
        	}

        	DataObject annenAdresse = null;
        	if (GBOPerson.getDataObject("postAdresse") != null) {
        		DataObject postAdresse = GBOPerson.getDataObject("postAdresse");
        		String postnr = postAdresse.getString("postnr");
        		String adresselinje1 = postAdresse.getString("adresselinje1");
        		String land = postAdresse.getString("land");
        		if ((postnr != null && !postnr.equals(""))
        				|| (adresselinje1 != null && !adresselinje1.equals("") && land != null && !land.equals(""))) {
        			annenAdresse = postAdresse;
        		}
        	}
        	if (GBOPerson.getDataObject("tilleggsAdresse") != null) {
        		DataObject tilleggsAdresse = GBOPerson.getDataObject("tilleggsAdresse");
        		String postnr = tilleggsAdresse.getString("postnr");
        		if (postnr != null && !postnr.equals("")) {
        			annenAdresse = tilleggsAdresse;
        		}
        	}
        	if (GBOPerson.getDataObject("utenlandsAdresse") != null) {
        		DataObject utenlandsAdresse = GBOPerson.getDataObject("utenlandsAdresse");
        		String adresselinje1 = utenlandsAdresse.getString("adresselinje1");
        		String land = utenlandsAdresse.getString("land");
        		if (adresselinje1 != null && !adresselinje1.equals("") && land != null && !land.equals("")) {
        			annenAdresse = utenlandsAdresse;
        		}
        	}

        	if (annenAdresse != null) {
        		person.setString("adresselinje1", annenAdresse.getString("adresselinje1"));
        		person.setString("adresselinje2", annenAdresse.getString("adresselinje2"));
        		person.setString("adresselinje3", annenAdresse.getString("adresselinje3"));
        		person.setString("postnr", annenAdresse.getString("postnr"));
        		person.setString("poststed", annenAdresse.getString("poststed"));
        		person.setString("land", annenAdresse.getString("land"));
        	}
        }

        Person.setUtbetalingsadresse(Person_utbetalingsadresse); // custom.output assignment (executionOrder=7)

    }
    }

    public static void GBOSamordningsdataTOHentSamordningsdataResp(GBOSamordningsdata GBOSamordningsdata, HentSamordningsdataResp HentSamordningsdataResp) {
        HentSamordningsdataResp.setTpnr(GBOSamordningsdata.getTpnr()); // move (executionOrder=1)
        GBOPersonTOPerson(GBOSamordningsdata.getPerson(),HentSamordningsdataResp.getPerson()); // submap (executionOrder=2)
        GBOVedtakTOVedtak(GBOSamordningsdata.getVedtakListe().getVedtakListe(),HentSamordningsdataResp.getPensjonVedtakListe()); // submap (executionOrder=3)
        GBOArbeidOgAktivitetsvedtakTOArbeidOgAktivitetsvedtak(GBOSamordningsdata.getArbeidOgAktivitetsvedtakListe(),HentSamordningsdataResp.getArbeidVedtakListe()); // submap (executionOrder=4)
        HentSamordningsdataResp.setUfullstendigeData(GBOSamordningsdata.getUfullstendigeData()); // move (executionOrder=5)
    {
        String GBOSamordningsdata_tjenestepensjonForholdListe = GBOSamordningsdata.getTjenestepensjonForholdListe(); // custom.input.forEach (executionOrder=6)
        String HentSamordningsdataResp_tjenestepensjonYtelseListe = null; // custom.output declaration (executionOrder=6)
        // The specific type of variable GBOSamordningsdata_tjenestepensjonForholdListe is java.util.List
        // The specific type of variable HentSamordningsdataResp_tjenestepensjonYtelseListe is java.util.List

        if (GBOSamordningsdata_tjenestepensjonForholdListe != null &&
        	HentSamordningsdataResp_tjenestepensjonYtelseListe != null) {
        	// Clear list to work around WID bug until JR27952 is fixed
        	((List) HentSamordningsdataResp_tjenestepensjonYtelseListe).clear();

        	for (Iterator iter = ((List) GBOSamordningsdata_tjenestepensjonForholdListe).iterator(); iter.hasNext();) {
            	DataObject tpForhold = (DataObject) iter.next();
            	for (Iterator iterator = ((List) tpForhold.getList("tjenestepensjonYtelseListe")).iterator(); iterator.hasNext();) {
        	        DataObject gboTpYtelse = (DataObject) iterator.next();
        	        DataObject tpYtelse = DataFactory.INSTANCE.create("http://nav.no/elsam/tpsamordningregistrering/V0_5", "TPYtelse");
        	        tpYtelse.setString("tpnr", tpForhold.getString("tpnr"));
        	        tpYtelse.setString("tpArt", gboTpYtelse.getString("ytelseKode"));
        	        if (gboTpYtelse.getString("iverksattFom") != null) {
        	        	tpYtelse.setDate("datoFom", DateUtil.parseWIDString(gboTpYtelse.getString("iverksattFom")));
        	        }
        	        if (gboTpYtelse.getString("iverksattTom") != null) {
        		        tpYtelse.setDate("datoTom", DateUtil.parseWIDString(gboTpYtelse.getString("iverksattTom")));
        	        }
        	       ((List) HentSamordningsdataResp_tjenestepensjonYtelseListe).add(tpYtelse);
        	    }
        	}
        }
        HentSamordningsdataResp.setTjenestepensjonYtelseListe(HentSamordningsdataResp_tjenestepensjonYtelseListe); // custom.output assignment (executionOrder=6)

    }
    }

    public static void GBOSamordningsdataTOLagreTPYtelseResp(GBOSamordningsdata GBOSamordningsdata, LagreTPYtelseResp LagreTPYtelseResp) {
        LagreTPYtelseResp.setTpnr(GBOSamordningsdata.getTpnr()); // move (executionOrder=1)
        GBOPersonTOPerson(GBOSamordningsdata.getPerson(),LagreTPYtelseResp.getPerson()); // submap (executionOrder=2)
        GBOVedtakTOVedtak(GBOSamordningsdata.getVedtakListe().getVedtakListe(),LagreTPYtelseResp.getPensjonVedtakListe()); // submap (executionOrder=3)
        GBOArbeidOgAktivitetsvedtakTOArbeidOgAktivitetsvedtak(GBOSamordningsdata.getArbeidOgAktivitetsvedtakListe(),LagreTPYtelseResp.getArbeidOgAktivitetsvedtakListe()); // submap (executionOrder=4)
    {
        String GBOSamordningsdata_tjenestepensjonForholdListe = GBOSamordningsdata.getTjenestepensjonForholdListe(); // custom.input.forEach (executionOrder=5)
        String LagreTPYtelseResp_tjenestepensjonYtelseListe = null; // custom.output declaration (executionOrder=5)
        // The specific type of variable GBOSamordningsdata_tjenestepensjonForholdListe is java.util.List
        // The specific type of variable LagreTPYtelseResp_tjenestepensjonYtelseListe is java.util.List

        // Clear list to work around WID bug until JR27952 is fixed
        ((List) LagreTPYtelseResp_tjenestepensjonYtelseListe).clear();

        if (GBOSamordningsdata_tjenestepensjonForholdListe != null) {
        	for (Iterator iter = ((List) GBOSamordningsdata_tjenestepensjonForholdListe).iterator(); iter.hasNext();) {
        	    DataObject tpForhold = (DataObject) iter.next();
        	    for (Iterator iterator = ((List) tpForhold.getList("tjenestepensjonYtelseListe")).iterator(); iterator.hasNext();) {
        	        DataObject gboTpYtelse = (DataObject) iterator.next();
        	        DataObject tpYtelse = DataFactory.INSTANCE.create("http://nav.no/elsam/tpsamordningregistrering/V0_5", "TPYtelse");
        	        tpYtelse.setString("tpnr", tpForhold.getString("tpnr"));
        	        tpYtelse.setString("tpArt", gboTpYtelse.getString("ytelseKode"));
        	        if (gboTpYtelse.getString("iverksattFom") != null) {
        	        	tpYtelse.setDate("datoFom", DateUtil.parseWIDString(gboTpYtelse.getString("iverksattFom")));
        	        }
        	        if (gboTpYtelse.getString("iverksattTom") != null) {
        		        tpYtelse.setDate("datoTom", DateUtil.parseWIDString(gboTpYtelse.getString("iverksattTom")));
        	        }
        	        ((List) LagreTPYtelseResp_tjenestepensjonYtelseListe).add(tpYtelse);
        	    }
        	}
        }
        LagreTPYtelseResp.setTjenestepensjonYtelseListe(LagreTPYtelseResp_tjenestepensjonYtelseListe); // custom.output assignment (executionOrder=5)

    }
        LagreTPYtelseResp.setUfullstendigeData(GBOSamordningsdata.getUfullstendigeData()); // move (executionOrder=6)
    }

    public static void GBOSluttpoengtallTOSluttpoengtall(nav_lib_pen.no.nav.lib.pen.gbo.GBOSluttpoengtall GBOSluttpoengtall, Sluttpoengtall Sluttpoengtall) {
        Sluttpoengtall.setPoengtall(GBOSluttpoengtall.getPoengtall()); // move (executionOrder=1)
        Sluttpoengtall.getPoengrekke().setPa(GBOSluttpoengtall.getPoengrekke().getPoengAr()); // move (executionOrder=2)
        Sluttpoengtall.getPoengrekke().setPaF92(GBOSluttpoengtall.getPoengrekke().getPoengArFor92()); // move (executionOrder=3)
        Sluttpoengtall.getPoengrekke().setPaE91(GBOSluttpoengtall.getPoengrekke().getPoengArEtter91()); // move (executionOrder=4)
        Sluttpoengtall.getPoengrekke().setTpiFaktor(GBOSluttpoengtall.getPoengrekke().getTidligerePenInntektFaktor()); // move (executionOrder=5)
    }

    public static void GBOVedtakTOVedtak(nav_lib_pen.no.nav.lib.pen.gbo.GBOVedtak GBOVedtak, Vedtak Vedtak) {
        Vedtak.setSaksKode(GBOVedtak.getSaksKode()); // move (executionOrder=1)
        Vedtak.setVedtakId(GBOVedtak.getVedtakId()); // move (executionOrder=2)
        Vedtak.setVedtaksKode(GBOVedtak.getVedtaksKode()); // move (executionOrder=3)
        Vedtak.setRegelverkKode(GBOVedtak.getKravHode().getRegelverkKode()); // move (executionOrder=4)
        GBOBeregningTOBeregning(GBOVedtak.getBeregningListe(),Vedtak.getBeregningListe()); // submap (executionOrder=5)
        GBOBeregningsResultatTOBeregning2011(GBOVedtak.getBeregningsResultatListe(),Vedtak.getBeregning2011Liste()); // submap (executionOrder=6)
        GBOVilkarsvedtakTOVilkarsvedtak(GBOVedtak.getVilkarsvedtakListe(),Vedtak.getBarnListe()); // submap (executionOrder=7)
        GBOBeregningsResultatUforetrygdTOBeregningUforetrygd(GBOVedtak.getBeregningsresultatUforetrygdListe(),Vedtak.getBeregningUforetrygdListe()); // submap (executionOrder=8)
    {
        String GBOVedtak_virkningFom = GBOVedtak.getVirkningFom(); // custom.input.forEach (executionOrder=9)
        String Vedtak_virkningFom = null; // custom.output declaration (executionOrder=9)
        // The specific type of variable GBOVedtak_virkningFom is java.lang.String
        // The specific type of variable Vedtak_virkningFom is java.util.Date
        if (GBOVedtak_virkningFom != null) {
        	Vedtak_virkningFom = DateUtil.parseWIDString((String) GBOVedtak_virkningFom);
        }
        Vedtak.setVirkningFom(Vedtak_virkningFom); // custom.output assignment (executionOrder=9)

    }
    {
        String GBOVedtak_virkningTom = GBOVedtak.getVirkningTom(); // custom.input.forEach (executionOrder=10)
        String Vedtak_virkningTom = null; // custom.output declaration (executionOrder=10)
        // The specific type of variable GBOVedtak_virkningTom is java.lang.String
        // The specific type of variable Vedtak_virkningTom is java.util.Date
        if (GBOVedtak_virkningTom != null) {
        	Vedtak_virkningTom = DateUtil.parseWIDString((String) GBOVedtak_virkningTom);
        }
        Vedtak.setVirkningTom(Vedtak_virkningTom); // custom.output assignment (executionOrder=10)

    }
    {
        String GBOVedtak_kravHode_kravVelgKode = GBOVedtak.getKravHode().getKravVelgKode(); // custom.input.forEach (executionOrder=11)
        String Vedtak_typeKrigspensjon = null; // custom.output declaration (executionOrder=11)
        // The specific type of variable GBOVedtak_kravHode_kravVelgKode is java.lang.String
        // The specific type of variable Vedtak_typeKrigspensjon is java.lang.String
        String[] gyldigeKoder = {"MIL_INV", "MIL_GJENLEV", "MIL_BARNEP", "SIVIL_INV", "SIVIL_GJENLEV", "SIVIL_BARNEP", "UP", "EP", "BP"};

        if (GBOVedtak_kravHode_kravVelgKode != null) {
        	String kravVelgKode = (String) GBOVedtak_kravHode_kravVelgKode;
        	if (Arrays.asList(gyldigeKoder).contains(kravVelgKode)) {
        		Vedtak_typeKrigspensjon = GBOVedtak_kravHode_kravVelgKode;
        	}
        }
        Vedtak.setTypeKrigspensjon(Vedtak_typeKrigspensjon); // custom.output assignment (executionOrder=11)

    }
    }

    public static void GBOVilkarsvedtakTOVilkarsvedtak(nav_lib_pen.no.nav.lib.pen.gbo.GBOVilkarsvedtak GBOVilkarsvedtak, Vilkarsvedtak Vilkarsvedtak) {
        Vilkarsvedtak.setGjelderFnr(GBOVilkarsvedtak.getGjelderFnr()); // move (executionOrder=1)
    {
        String GBOVilkarsvedtak_virkningTom = GBOVilkarsvedtak.getVirkningTom(); // custom.input.forEach (executionOrder=2)
        String Vilkarsvedtak_tom = null; // custom.output declaration (executionOrder=2)
        // The specific type of variable GBOVilkarsvedtak_virkningTom is java.lang.String
        // The specific type of variable Vilkarsvedtak_tom is java.util.Date

        if (GBOVilkarsvedtak_virkningTom != null) {
        	Vilkarsvedtak_tom = DateUtil.parseWIDString((String) GBOVilkarsvedtak_virkningTom);
        }
        Vilkarsvedtak.setTom(Vilkarsvedtak_tom); // custom.output assignment (executionOrder=2)

    }
    {
        String GBOVilkarsvedtak_virkningFom = GBOVilkarsvedtak.getVirkningFom(); // custom.input.forEach (executionOrder=3)
        String Vilkarsvedtak_fom = null; // custom.output declaration (executionOrder=3)
        // The specific type of variable GBOVilkarsvedtak_virkningFom is java.lang.String
        // The specific type of variable Vilkarsvedtak_fom is java.util.Date
        if (GBOVilkarsvedtak_virkningFom != null) {
        	Vilkarsvedtak_fom = DateUtil.parseWIDString((String) GBOVilkarsvedtak_virkningFom);
        }

        Vilkarsvedtak.setFom(Vilkarsvedtak_fom); // custom.output assignment (executionOrder=3)

    }
    }

    public static void HentSamordningsdataReqIntTOGBOHentSamordningsdataRequest(HentSamordningsdataReqInt HentSamordningsdataReqInt, GBOHentSamordningsdataRequest GBOHentSamordningsdataRequest) {
        GBOHentSamordningsdataRequest.setSvarBrev("false"); // set (executionOrder=1)
        GBOHentSamordningsdataRequest.setTpnr(HentSamordningsdataReqInt.getExtRequest().getTpnr()); // move (executionOrder=2)
        GBOHentSamordningsdataRequest.setFnr(HentSamordningsdataReqInt.getExtRequest().getFnr()); // move (executionOrder=3)
    {
        String HentSamordningsdataReqInt_extRequest_datoFom = HentSamordningsdataReqInt.getExtRequest().getDatoFom(); // custom.input.forEach (executionOrder=4)
        String GBOHentSamordningsdataRequest_fom = null; // custom.output declaration (executionOrder=4)
        // The specific type of variable HentSamordningsdataReqInt_extRequest_datoFom is java.util.Date
        // The specific type of variable GBOHentSamordningsdataRequest_fom is java.lang.String

        if (HentSamordningsdataReqInt_extRequest_datoFom != null) {
        	GBOHentSamordningsdataRequest_fom = DateUtil.formatWIDString((Date) HentSamordningsdataReqInt_extRequest_datoFom);
        }
        GBOHentSamordningsdataRequest.setFom(GBOHentSamordningsdataRequest_fom); // custom.output assignment (executionOrder=4)

    }
        GBOHentSamordningsdataRequest.setTssEksternId(HentSamordningsdataReqInt.getTssEksternId()); // move (executionOrder=5)
    }

    public static void LagreTPYtelseReqIntTOGBOOpprettTPSamordningRequest(LagreTPYtelseReqInt LagreTPYtelseReqInt, GBOOpprettTPSamordningRequest GBOOpprettTPSamordningRequest) {
        GBOOpprettTPSamordningRequest.setSvarBrev("false"); // set (executionOrder=1)
        GBOOpprettTPSamordningRequest.setTpnr(LagreTPYtelseReqInt.getExtRequest().getTpnr()); // move (executionOrder=2)
        GBOOpprettTPSamordningRequest.setFnr(LagreTPYtelseReqInt.getExtRequest().getFnr()); // move (executionOrder=3)
    {
        String LagreTPYtelseReqInt_extRequest_datoFom = LagreTPYtelseReqInt.getExtRequest().getDatoFom(); // custom.input.forEach (executionOrder=4)
        String GBOOpprettTPSamordningRequest_iverksattFom = null; // custom.output declaration (executionOrder=4)
        // The specific type of variable LagreTPYtelseReqInt_extRequest_datoFom is java.util.Date
        // The specific type of variable GBOOpprettTPSamordningRequest_iverksattFom is java.lang.String

        if (LagreTPYtelseReqInt_extRequest_datoFom != null)
        GBOOpprettTPSamordningRequest_iverksattFom = DateUtil.formatWIDString((java.util.Date)LagreTPYtelseReqInt_extRequest_datoFom);


        GBOOpprettTPSamordningRequest.setIverksattFom(GBOOpprettTPSamordningRequest_iverksattFom); // custom.output assignment (executionOrder=4)

    }
        GBOOpprettTPSamordningRequest.setTssEksternId(LagreTPYtelseReqInt.getTssEksternId()); // move (executionOrder=5)
        GBOOpprettTPSamordningRequest.setYtelseKode(LagreTPYtelseReqInt.getExtRequest().getTpArt()); // move (executionOrder=6)
    {
        String LagreTPYtelseReqInt_extRequest_datoFomMedlemskap = LagreTPYtelseReqInt.getExtRequest().getDatoFomMedlemskap(); // custom.input.forEach (executionOrder=7)
        String GBOOpprettTPSamordningRequest_innmeldtFom = null; // custom.output declaration (executionOrder=7)
        // The specific type of variable LagreTPYtelseReqInt_extRequest_datoFomMedlemskap is java.util.Date
        // The specific type of variable GBOOpprettTPSamordningRequest_innmeldtFom is java.lang.String
        GBOOpprettTPSamordningRequest_innmeldtFom = DateUtil.formatWIDString((Date)LagreTPYtelseReqInt_extRequest_datoFomMedlemskap);
        GBOOpprettTPSamordningRequest.setInnmeldtFom(GBOOpprettTPSamordningRequest_innmeldtFom); // custom.output assignment (executionOrder=7)

    }
    }

    public static void OpprettRefusjonskravReqIntTOGBOOpprettRefusjonskravRequest(OpprettRefusjonskravReqInt OpprettRefusjonskravReqInt, GBOOpprettRefusjonskravRequest GBOOpprettRefusjonskravRequest) {
        GBOOpprettRefusjonskravRequest.getSvarRefusjonskrav().setTpnr(OpprettRefusjonskravReqInt.getExtRequest().getTpnr()); // move (executionOrder=1)
        GBOOpprettRefusjonskravRequest.getSvarRefusjonskrav().setFnr(OpprettRefusjonskravReqInt.getExtRequest().getFnr()); // move (executionOrder=2)
        GBOOpprettRefusjonskravRequest.getSvarRefusjonskrav().setSamId(OpprettRefusjonskravReqInt.getExtRequest().getSamordningsmeldingId()); // move (executionOrder=3)
        GBOOpprettRefusjonskravRequest.getSvarRefusjonskrav().setRefusjonskrav(OpprettRefusjonskravReqInt.getExtRequest().getRefusjonskrav()); // move (executionOrder=4)
        PeriodisertBelopTOGBOPeriodisertBelop(OpprettRefusjonskravReqInt.getExtRequest().getPeriodisertBelopListe(),GBOOpprettRefusjonskravRequest.getSvarRefusjonskrav().getPeriodisertBelopListe()); // submap (executionOrder=5)
        GBOOpprettRefusjonskravRequest.getSvarRefusjonskrav().setTssEksternId(OpprettRefusjonskravReqInt.getTssEksternId()); // move (executionOrder=6)
    }

    public static void PeriodisertBelopTOGBOPeriodisertBelop(PeriodisertBelop PeriodisertBelop, GBOPeriodisertBelop GBOPeriodisertBelop) {
        GBOPeriodisertBelop.setBelop(PeriodisertBelop.getBelop()); // move (executionOrder=1)
        GBOPeriodisertBelop.setKravstillersRef(PeriodisertBelop.getKravstillersReferanse()); // move (executionOrder=2)
    {
        String PeriodisertBelop_datoFom = PeriodisertBelop.getDatoFom(); // custom.input.forEach (executionOrder=3)
        String GBOPeriodisertBelop_datoFom = null; // custom.output declaration (executionOrder=3)
        // The specific type of variable PeriodisertBelop_datoFom is java.util.Date
        // The specific type of variable GBOPeriodisertBelop_datoFom is java.lang.String

        if (PeriodisertBelop_datoFom != null) {
        	GBOPeriodisertBelop_datoFom = DateUtil.formatWIDString((Date) PeriodisertBelop_datoFom);
        }
        GBOPeriodisertBelop.setDatoFom(GBOPeriodisertBelop_datoFom); // custom.output assignment (executionOrder=3)

    }
    {
        String PeriodisertBelop_datoTom = PeriodisertBelop.getDatoTom(); // custom.input.forEach (executionOrder=4)
        String GBOPeriodisertBelop_datoTom = null; // custom.output declaration (executionOrder=4)
        // The specific type of variable PeriodisertBelop_datoTom is java.util.Date
        // The specific type of variable GBOPeriodisertBelop_datoTom is java.lang.String

        if (PeriodisertBelop_datoTom != null) {
        	GBOPeriodisertBelop_datoTom = DateUtil.formatWIDString((Date) PeriodisertBelop_datoTom);
        }
        GBOPeriodisertBelop.setDatoTom(GBOPeriodisertBelop_datoTom); // custom.output assignment (executionOrder=4)

    }
    }

    public static void SlettTPYtelseReqIntTOGBOSlettTPSamordningRequest(SlettTPYtelseReqInt SlettTPYtelseReqInt, GBOSlettTPSamordningRequest GBOSlettTPSamordningRequest) {
        GBOSlettTPSamordningRequest.setTpnr(SlettTPYtelseReqInt.getExtRequest().getTpnr()); // move (executionOrder=1)
        GBOSlettTPSamordningRequest.setFnr(SlettTPYtelseReqInt.getExtRequest().getFnr()); // move (executionOrder=2)
        GBOSlettTPSamordningRequest.setMeldingKode(SlettTPYtelseReqInt.getExtRequest().getHenvendelseType()); // move (executionOrder=3)
    {
        String SlettTPYtelseReqInt_extRequest_datoFom = SlettTPYtelseReqInt.getExtRequest().getDatoFom(); // custom.input.forEach (executionOrder=4)
        String GBOSlettTPSamordningRequest_iverksattFom = null; // custom.output declaration (executionOrder=4)
        // The specific type of variable SlettTPYtelseReqInt_extRequest_datoFom is java.util.Date
        // The specific type of variable GBOSlettTPSamordningRequest_iverksattFom is java.lang.String
        GBOSlettTPSamordningRequest_iverksattFom = DateUtil.formatWIDString((Date)SlettTPYtelseReqInt_extRequest_datoFom);
        GBOSlettTPSamordningRequest.setIverksattFom(GBOSlettTPSamordningRequest_iverksattFom); // custom.output assignment (executionOrder=4)

    }
    {
        String SlettTPYtelseReqInt_extRequest_datoTom = SlettTPYtelseReqInt.getExtRequest().getDatoTom(); // custom.input.forEach (executionOrder=5)
        String GBOSlettTPSamordningRequest_iverksattTom = null; // custom.output declaration (executionOrder=5)
        // The specific type of variable SlettTPYtelseReqInt_extRequest_datoTom is java.util.Date
        // The specific type of variable GBOSlettTPSamordningRequest_iverksattTom is java.lang.String
        GBOSlettTPSamordningRequest_iverksattTom = DateUtil.formatWIDString((Date)SlettTPYtelseReqInt_extRequest_datoTom);
        GBOSlettTPSamordningRequest.setIverksattTom(GBOSlettTPSamordningRequest_iverksattTom); // custom.output assignment (executionOrder=5)

    }
        GBOSlettTPSamordningRequest.setTssEksternId(SlettTPYtelseReqInt.getTssEksternId()); // move (executionOrder=6)
        GBOSlettTPSamordningRequest.setYtelseKode(SlettTPYtelseReqInt.getExtRequest().getTpArt()); // move (executionOrder=7)
    }

    public static XMLGregorianCalendar toXMLGregorianCalendar(Date date) throws DatatypeConfigurationException {
        if (date != null) {
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
        } else {
            return null;
        }
    }

    public static XMLGregorianCalendar toXMLGregorianCalendar(String date) throws DatatypeConfigurationException {
        return null != date ? DatatypeFactory.newInstance().newXMLGregorianCalendar(date) : null;
    }

    public static Integer toInteger(String value) {
        return value != null ? Integer.parseInt(value) : null;
    }

    public static <T> JAXBElement<T> toJAXBElement(String localPart, Class<T> type, T object) {
        return object != null ? new JAXBElement<>(new QName(localPart), type, object) : null;
    }
}
