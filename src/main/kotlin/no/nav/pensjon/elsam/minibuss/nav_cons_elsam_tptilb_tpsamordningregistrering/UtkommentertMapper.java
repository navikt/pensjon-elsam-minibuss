package no.nav.pensjon.elsam.minibuss.nav_cons_elsam_tptilb_tpsamordningregistrering;

/*
    public static void Beregning2011V0_6TOBeregning2011V0_5(Beregning2011 Beregning2011, Beregning2011 Beregning2011_1) {
        Beregning2011_1.setVirkningFom(Beregning2011.getVirkningFom()); // move (executionOrder=1)
        Beregning2011_1.setVirkningTom(Beregning2011.getVirkningTom()); // move (executionOrder=2)
        Beregning2011_1.setUttaksgrad(Beregning2011.getUttaksgrad()); // move (executionOrder=3)
        Beregning2011_1.setBasisgp(Beregning2011.getBasisgp()); // move (executionOrder=4)
        Beregning2011_1.setBasistp(Beregning2011.getBasistp()); // move (executionOrder=5)
        Beregning2011_1.setBasispt(Beregning2011.getBasispt()); // move (executionOrder=6)
        Beregning2011_1.setForholdstallUttak(Beregning2011.getForholdstallUttak()); // move (executionOrder=7)
        Beregning2011_1.setForholdstall67(Beregning2011.getForholdstall67()); // move (executionOrder=8)
        Beregning2011_1.setGrunnpensjon(Beregning2011.getGrunnpensjon()); // move (executionOrder=9)
        Beregning2011_1.setGpRestpensjon(Beregning2011.getGpRestpensjon()); // move (executionOrder=10)
        Beregning2011_1.setTilleggspensjon(Beregning2011.getTilleggspensjon()); // move (executionOrder=11)
        Beregning2011_1.setTpRestpensjon(Beregning2011.getTpRestpensjon()); // move (executionOrder=12)
        Beregning2011_1.setPensjonstillegg(Beregning2011.getPensjonstillegg()); // move (executionOrder=13)
        Beregning2011_1.setPtRestpensjon(Beregning2011.getPtRestpensjon()); // move (executionOrder=14)
        Beregning2011_1.setTotalBelop(Beregning2011.getTotalBelop()); // move (executionOrder=15)
        Beregning2011_1.setResultatKode(Beregning2011.getResultatKode()); // move (executionOrder=16)
        Beregning2011_1.setMndGrunnpensjon(Beregning2011.getMndGrunnpensjon()); // move (executionOrder=17)
        Beregning2011_1.setMndTilleggspensjon(Beregning2011.getMndTilleggspensjon()); // move (executionOrder=18)
        Beregning2011_1.setMndPensjonstillegg(Beregning2011.getMndPensjonstillegg()); // move (executionOrder=19)
        Beregning2011_1.setSpt(Beregning2011.getSpt()); // move (executionOrder=20)
    }

    public static void Beregning2011V0_7TOBeregning2011V0_6(Beregning2011 Beregning2011, Beregning2011 Beregning2011_1) {
        Beregning2011_1.setVirkningFom(Beregning2011.getVirkningFom()); // move (executionOrder=1)
        Beregning2011_1.setVirkningTom(Beregning2011.getVirkningTom()); // move (executionOrder=2)
        Beregning2011_1.setUttaksgrad(Beregning2011.getUttaksgrad()); // move (executionOrder=3)
        Beregning2011_1.setBasisgp(Beregning2011.getBasisgp()); // move (executionOrder=4)
        Beregning2011_1.setBasistp(Beregning2011.getBasistp()); // move (executionOrder=5)
        Beregning2011_1.setBasispt(Beregning2011.getBasispt()); // move (executionOrder=6)
        Beregning2011_1.setForholdstallUttak(Beregning2011.getForholdstallUttak()); // move (executionOrder=7)
        Beregning2011_1.setForholdstall67(Beregning2011.getForholdstall67()); // move (executionOrder=8)
        Beregning2011_1.setGrunnpensjon(Beregning2011.getGrunnpensjon()); // move (executionOrder=9)
        Beregning2011_1.setGpRestpensjon(Beregning2011.getGpRestpensjon()); // move (executionOrder=10)
        Beregning2011_1.setGrunnpensjonsats(Beregning2011.getGrunnpensjonsats()); // move (executionOrder=11)
        Beregning2011_1.setTilleggspensjon(Beregning2011.getTilleggspensjon()); // move (executionOrder=12)
        Beregning2011_1.setTpRestpensjon(Beregning2011.getTpRestpensjon()); // move (executionOrder=13)
        Beregning2011_1.setPensjonstillegg(Beregning2011.getPensjonstillegg()); // move (executionOrder=14)
        Beregning2011_1.setPtRestpensjon(Beregning2011.getPtRestpensjon()); // move (executionOrder=15)
        Beregning2011_1.setTotalBelop(Beregning2011.getTotalBelop()); // move (executionOrder=16)
        Beregning2011_1.setResultatKode(Beregning2011.getResultatKode()); // move (executionOrder=17)
        Beregning2011_1.setMndGrunnpensjon(Beregning2011.getMndGrunnpensjon()); // move (executionOrder=18)
        Beregning2011_1.setMndTilleggspensjon(Beregning2011.getMndTilleggspensjon()); // move (executionOrder=19)
        Beregning2011_1.setMndPensjonstillegg(Beregning2011.getMndPensjonstillegg()); // move (executionOrder=20)
        Beregning2011_1.setSpt(Beregning2011.getSpt()); // move (executionOrder=21)
        Beregning2011_1.setYpt(Beregning2011.getYpt()); // move (executionOrder=22)
        Beregning2011_1.setYrkesskadegrad(Beregning2011.getYrkesskadegrad()); // move (executionOrder=23)
        Beregning2011_1.setAnvendtTrygdetid(Beregning2011.getAnvendtTrygdetid()); // move (executionOrder=24)
        Beregning2011_1.setBruttoBarnetilleggFellesbarn(Beregning2011.getBruttoBarnetilleggFellesbarn()); // move (executionOrder=25)
        Beregning2011_1.setNettoBarnetilleggFellesbarn(Beregning2011.getNettoBarnetilleggFellesbarn()); // move (executionOrder=26)
        Beregning2011_1.setAntallBarnetilleggFellesbarn(Beregning2011.getAntallBarnetilleggFellesbarn()); // move (executionOrder=27)
        Beregning2011_1.setBruttoBarnetilleggSerkullsbarn(Beregning2011.getBruttoBarnetilleggSerkullsbarn()); // move (executionOrder=28)
        Beregning2011_1.setNettoBarnetilleggSerkullsbarn(Beregning2011.getNettoBarnetilleggSerkullsbarn()); // move (executionOrder=29)
        Beregning2011_1.setAntallBarnetilleggSerkullsbarn(Beregning2011.getAntallBarnetilleggSerkullsbarn()); // move (executionOrder=30)
        Beregning2011_1.setEktefelletillegg(Beregning2011.getEktefelletillegg()); // move (executionOrder=31)
        Beregning2011_1.setMinstenivatilleggPensjonistpar(Beregning2011.getMinstenivatilleggPensjonistpar()); // move (executionOrder=32)
        Beregning2011_1.setAfpLivsvarig(Beregning2011.getAfpLivsvarig()); // move (executionOrder=33)
        Beregning2011_1.setAfpKronetillegg(Beregning2011.getAfpKronetillegg()); // move (executionOrder=34)
        Beregning2011_1.setAfpKompensasjonstillegg(Beregning2011.getAfpKompensasjonstillegg()); // move (executionOrder=35)
        Beregning2011_1.setForholdstallKompensasjonstillegg(Beregning2011.getForholdstallKompensasjonstillegg()); // move (executionOrder=36)
        Beregning2011_1.setAfpOpptjeningTotalbelop(Beregning2011.getAfpOpptjeningTotalbelop()); // move (executionOrder=37)
        Beregning2011_1.setFnrAvdod(Beregning2011.getFnrAvdod()); // move (executionOrder=38)
        Beregning2011_1.setSptAvdod(Beregning2011.getSptAvdod()); // move (executionOrder=39)
        Beregning2011_1.setYptAvdod(Beregning2011.getYptAvdod()); // move (executionOrder=40)
        Beregning2011_1.setOptAvdod(Beregning2011.getOptAvdod()); // move (executionOrder=41)
        Beregning2011_1.setYrkesskadegradAvdod(Beregning2011.getYrkesskadegradAvdod()); // move (executionOrder=42)
        Beregning2011_1.setAnvendtTrygdetidAvdod(Beregning2011.getAnvendtTrygdetidAvdod()); // move (executionOrder=43)
        Beregning2011_1.getUtvidelse().setMinstenivatilleggIndividuelt(Beregning2011.getMinstenivatilleggIndividuelt()); // move (executionOrder=44)
    }

    public static void Beregning2011V0_8TOBeregning2011V0_7(Beregning2011 Beregning2011, Beregning2011 Beregning2011_1) {
        Beregning2011_1.setVirkningFom(Beregning2011.getVirkningFom()); // move (executionOrder=1)
        Beregning2011_1.setVirkningTom(Beregning2011.getVirkningTom()); // move (executionOrder=2)
        Beregning2011_1.setUttaksgrad(Beregning2011.getUttaksgrad()); // move (executionOrder=3)
        Beregning2011_1.setBasisgp(Beregning2011.getBasisgp()); // move (executionOrder=4)
        Beregning2011_1.setBasistp(Beregning2011.getBasistp()); // move (executionOrder=5)
        Beregning2011_1.setBasispt(Beregning2011.getBasispt()); // move (executionOrder=6)
        Beregning2011_1.setForholdstallUttak(Beregning2011.getForholdstallUttak()); // move (executionOrder=7)
        Beregning2011_1.setForholdstall67(Beregning2011.getForholdstall67()); // move (executionOrder=8)
        Beregning2011_1.setGrunnpensjon(Beregning2011.getGrunnpensjon()); // move (executionOrder=9)
        Beregning2011_1.setGpRestpensjon(Beregning2011.getGpRestpensjon()); // move (executionOrder=10)
        Beregning2011_1.setGrunnpensjonsats(Beregning2011.getGrunnpensjonsats()); // move (executionOrder=11)
        Beregning2011_1.setTilleggspensjon(Beregning2011.getTilleggspensjon()); // move (executionOrder=12)
        Beregning2011_1.setTpRestpensjon(Beregning2011.getTpRestpensjon()); // move (executionOrder=13)
        Beregning2011_1.setPensjonstillegg(Beregning2011.getPensjonstillegg()); // move (executionOrder=14)
        Beregning2011_1.setPtRestpensjon(Beregning2011.getPtRestpensjon()); // move (executionOrder=15)
        Beregning2011_1.setTotalBelop(Beregning2011.getTotalBelop()); // move (executionOrder=16)
        Beregning2011_1.setResultatKode(Beregning2011.getResultatKode()); // move (executionOrder=17)
        Beregning2011_1.setMndGrunnpensjon(Beregning2011.getMndGrunnpensjon()); // move (executionOrder=18)
        Beregning2011_1.setMndTilleggspensjon(Beregning2011.getMndTilleggspensjon()); // move (executionOrder=19)
        Beregning2011_1.setMndPensjonstillegg(Beregning2011.getMndPensjonstillegg()); // move (executionOrder=20)
        Beregning2011_1.setSpt(Beregning2011.getSpt()); // move (executionOrder=21)
        Beregning2011_1.setYpt(Beregning2011.getYpt()); // move (executionOrder=22)
        Beregning2011_1.setYrkesskadegrad(Beregning2011.getYrkesskadegrad()); // move (executionOrder=23)
        Beregning2011_1.setAnvendtTrygdetid(Beregning2011.getAnvendtTrygdetid()); // move (executionOrder=24)
        Beregning2011_1.setBruttoBarnetilleggFellesbarn(Beregning2011.getBruttoBarnetilleggFellesbarn()); // move (executionOrder=25)
        Beregning2011_1.setNettoBarnetilleggFellesbarn(Beregning2011.getNettoBarnetilleggFellesbarn()); // move (executionOrder=26)
        Beregning2011_1.setAntallBarnetilleggFellesbarn(Beregning2011.getAntallBarnetilleggFellesbarn()); // move (executionOrder=27)
        Beregning2011_1.setBruttoBarnetilleggSerkullsbarn(Beregning2011.getBruttoBarnetilleggSerkullsbarn()); // move (executionOrder=28)
        Beregning2011_1.setNettoBarnetilleggSerkullsbarn(Beregning2011.getNettoBarnetilleggSerkullsbarn()); // move (executionOrder=29)
        Beregning2011_1.setAntallBarnetilleggSerkullsbarn(Beregning2011.getAntallBarnetilleggSerkullsbarn()); // move (executionOrder=30)
        Beregning2011_1.setEktefelletillegg(Beregning2011.getEktefelletillegg()); // move (executionOrder=31)
        Beregning2011_1.setMinstenivatilleggPensjonistpar(Beregning2011.getMinstenivatilleggPensjonistpar()); // move (executionOrder=32)
        Beregning2011_1.setAfpLivsvarig(Beregning2011.getAfpLivsvarig()); // move (executionOrder=33)
        Beregning2011_1.setAfpKronetillegg(Beregning2011.getAfpKronetillegg()); // move (executionOrder=34)
        Beregning2011_1.setAfpKompensasjonstillegg(Beregning2011.getAfpKompensasjonstillegg()); // move (executionOrder=35)
        Beregning2011_1.setForholdstallKompensasjonstillegg(Beregning2011.getForholdstallKompensasjonstillegg()); // move (executionOrder=36)
        Beregning2011_1.setAfpOpptjeningTotalbelop(Beregning2011.getAfpOpptjeningTotalbelop()); // move (executionOrder=37)
        Beregning2011_1.setFnrAvdod(Beregning2011.getFnrAvdod()); // move (executionOrder=38)
        Beregning2011_1.setSptAvdod(Beregning2011.getSptAvdod()); // move (executionOrder=39)
        Beregning2011_1.setYptAvdod(Beregning2011.getYptAvdod()); // move (executionOrder=40)
        Beregning2011_1.setOptAvdod(Beregning2011.getOptAvdod()); // move (executionOrder=41)
        Beregning2011_1.setYrkesskadegradAvdod(Beregning2011.getYrkesskadegradAvdod()); // move (executionOrder=42)
        Beregning2011_1.setAnvendtTrygdetidAvdod(Beregning2011.getAnvendtTrygdetidAvdod()); // move (executionOrder=43)
        Beregning2011_1.setMinstenivatilleggIndividuelt(Beregning2011.getMinstenivatilleggIndividuelt()); // move (executionOrder=44)
    }

    public static void BeregningUforetrygdV1_0TOBeregningUforetrygdV0_9(BeregningUforetrygd BeregningUforetrygd, BeregningUforetrygd BeregningUforetrygd_1) {
        BeregningUforetrygd_1.setInntektForUforhet(BeregningUforetrygd.getInntektForUforhet()); // move (executionOrder=1)
        BeregningUforetrygd_1.setNettoUforetrygdOrdinar(BeregningUforetrygd.getNettoUforetrygdOrdinar()); // move (executionOrder=2)
        BeregningUforetrygd_1.setBruttoUforetrygdOrdinar(BeregningUforetrygd.getBruttoUforetrygdOrdinar()); // move (executionOrder=3)
        BeregningUforetrygd_1.setUforegrad(BeregningUforetrygd.getUforegrad()); // move (executionOrder=4)
        BeregningUforetrygd_1.setYrkesskadegrad(BeregningUforetrygd.getYrkesskadegrad()); // move (executionOrder=5)
        BeregningUforetrygd_1.setNettoBarnetilleggSerkullsbarn(BeregningUforetrygd.getNettoBarnetilleggSerkullsbarn()); // move (executionOrder=6)
        BeregningUforetrygd_1.setBruttoBarnetilleggSerkullsbarn(BeregningUforetrygd.getBruttoBarnetilleggSerkullsbarn()); // move (executionOrder=7)
        BeregningUforetrygd_1.setNettoBarnetilleggFellesbarn(BeregningUforetrygd.getNettoBarnetilleggFellesbarn()); // move (executionOrder=8)
        BeregningUforetrygd_1.setBruttoBarnetilleggFellesbarn(BeregningUforetrygd.getBruttoBarnetilleggFellesbarn()); // move (executionOrder=9)
        BeregningUforetrygd_1.setNettoGjenlevendetillegg(BeregningUforetrygd.getNettoGjenlevendetillegg()); // move (executionOrder=10)
        BeregningUforetrygd_1.setBruttoGjenlevendetillegg(BeregningUforetrygd.getBruttoGjenlevendetillegg()); // move (executionOrder=11)
        BeregningUforetrygd_1.setNettoEktefelletillegg(BeregningUforetrygd.getNettoEktefelletillegg()); // move (executionOrder=12)
        BeregningUforetrygd_1.setResultatKode(BeregningUforetrygd.getResultatKode()); // move (executionOrder=13)
        BeregningUforetrygd_1.setFom(BeregningUforetrygd.getFom()); // move (executionOrder=14)
        BeregningUforetrygd_1.setTom(BeregningUforetrygd.getTom()); // move (executionOrder=15)
    }

    public static void BeregningV0_7TOBeregningV0_6(Beregning Beregning, Beregning Beregning_1) {
        Beregning_1.setVirkningFom(Beregning.getVirkningFom()); // move (executionOrder=1)
        Beregning_1.setVirkningTom(Beregning.getVirkningTom()); // move (executionOrder=2)
        Beregning_1.setBrutto(Beregning.getBrutto()); // move (executionOrder=3)
        Beregning_1.setNetto(Beregning.getNetto()); // move (executionOrder=4)
        Beregning_1.setResultatKode(Beregning.getResultatKode()); // move (executionOrder=5)
        Beregning_1.setP67Berergning(Beregning.getP67Berergning()); // move (executionOrder=6)
        Beregning_1.setAfpPensjonsgrad(Beregning.getAfpPensjonsgrad()); // move (executionOrder=7)
        Beregning_1.setBruttoGrunnpensjon(Beregning.getBruttoGrunnpensjon()); // move (executionOrder=8)
        Beregning_1.setNettoGrunnpensjon(Beregning.getNettoGrunnpensjon()); // move (executionOrder=9)
        Beregning_1.setGrunnpensjonsats(Beregning.getGrunnpensjonsats()); // move (executionOrder=10)
        Beregning_1.setBruttoTilleggspensjon(Beregning.getBruttoTilleggspensjon()); // move (executionOrder=11)
        Beregning_1.setNettoTilleggspensjon(Beregning.getNettoTilleggspensjon()); // move (executionOrder=12)
        Beregning_1.setBruttoSertillegg(Beregning.getBruttoSertillegg()); // move (executionOrder=13)
        Beregning_1.setNettoSertillegg(Beregning.getNettoSertillegg()); // move (executionOrder=14)
        Beregning_1.setSertilleggSats(Beregning.getSertilleggSats()); // move (executionOrder=15)
        Beregning_1.setBruttoAFPtillegg(Beregning.getBruttoAFPtillegg()); // move (executionOrder=16)
        Beregning_1.setNettoAFPtillegg(Beregning.getNettoAFPtillegg()); // move (executionOrder=17)
        Beregning_1.setEktefelletillegg(Beregning.getEktefelletillegg()); // move (executionOrder=18)
        Beregning_1.setBruttoBarnetilleggFellesbarn(Beregning.getBruttoBarnetilleggFellesbarn()); // move (executionOrder=19)
        Beregning_1.setNettoBarnetilleggFellesbarn(Beregning.getNettoBarnetilleggFellesbarn()); // move (executionOrder=20)
        Beregning_1.setAntallBarnetilleggFellesbarn(Beregning.getAntallBarnetilleggFellesbarn()); // move (executionOrder=21)
        Beregning_1.setBruttoBarnetilleggSerkullsbarn(Beregning.getBruttoBarnetilleggSerkullsbarn()); // move (executionOrder=22)
        Beregning_1.setNettoBarnetilleggSerkullsbarn(Beregning.getNettoBarnetilleggSerkullsbarn()); // move (executionOrder=23)
        Beregning_1.setAntallBarnetilleggSerkullsbarn(Beregning.getAntallBarnetilleggSerkullsbarn()); // move (executionOrder=24)
        Beregning_1.setNettoVentetillegg(Beregning.getNettoVentetillegg()); // move (executionOrder=25)
        Beregning_1.setVentetilleggProsent(Beregning.getVentetilleggProsent()); // move (executionOrder=26)
        Beregning_1.setParagraf851Tillegg(Beregning.getParagraf851Tillegg()); // move (executionOrder=27)
        Beregning_1.setKrigOgGammelYrkesskade(Beregning.getKrigOgGammelYrkesskade()); // move (executionOrder=28)
        Beregning_1.setYrkesskadegrad(Beregning.getYrkesskadegrad()); // move (executionOrder=29)
        Beregning_1.setUforegrad(Beregning.getUforegrad()); // move (executionOrder=30)
        Beregning_1.setInvolvertePersonerNokkelinfoListe(Beregning.getInvolvertePersonerNokkelinfoListe()); // move (executionOrder=31)
        Beregning_1.setFremtidigPensjonsgivendeInntektBruker(Beregning.getFremtidigPensjonsgivendeInntektBruker()); // move (executionOrder=32)
    }

    public static void HentSamordningsdataRespV0_6TOHentSamordningsdataV0_5(HentSamordningsdataResp HentSamordningsdataResp, HentSamordningsdataResp HentSamordningsdataResp_1) {
        HentSamordningsdataResp_1.setTpnr(HentSamordningsdataResp.getTpnr()); // move (executionOrder=1)
        HentSamordningsdataResp_1.setPerson(HentSamordningsdataResp.getPerson()); // move (executionOrder=2)
        VedtakV0_6TOVedtakV0_5(HentSamordningsdataResp.getPensjonVedtakListe(),HentSamordningsdataResp_1.getPensjonVedtakListe()); // submap (executionOrder=3)
        HentSamordningsdataResp_1.setArbeidVedtakListe(HentSamordningsdataResp.getArbeidVedtakListe()); // move (executionOrder=4)
        HentSamordningsdataResp_1.setTjenestepensjonYtelseListe(HentSamordningsdataResp.getTjenestepensjonYtelseListe()); // move (executionOrder=5)
        HentSamordningsdataResp_1.setUfullstendigeData(HentSamordningsdataResp.getUfullstendigeData()); // move (executionOrder=6)
    }

    public static void HentSamordningsdataRespV0_7TOHentSamordningsdataRespV0_6(HentSamordningsdataResp HentSamordningsdataResp, HentSamordningsdataResp HentSamordningsdataResp_1) {
        HentSamordningsdataResp_1.setTpnr(HentSamordningsdataResp.getTpnr()); // move (executionOrder=1)
        HentSamordningsdataResp_1.setPerson(HentSamordningsdataResp.getPerson()); // move (executionOrder=2)
        VedtakV0_7TOVedtakV0_6(HentSamordningsdataResp.getPensjonVedtakListe(),HentSamordningsdataResp_1.getPensjonVedtakListe()); // submap (executionOrder=3)
        HentSamordningsdataResp_1.setArbeidVedtakListe(HentSamordningsdataResp.getArbeidVedtakListe()); // move (executionOrder=4)
        HentSamordningsdataResp_1.setTjenestepensjonYtelseListe(HentSamordningsdataResp.getTjenestepensjonYtelseListe()); // move (executionOrder=5)
        HentSamordningsdataResp_1.setUfullstendigeData(HentSamordningsdataResp.getUfullstendigeData()); // move (executionOrder=6)
    }

    public static void HentSamordningsdataRespV0_8TOHentSamordningsdataRespV0_7(HentSamordningsdataResp HentSamordningsdataResp, HentSamordningsdataResp HentSamordningsdataResp_1) {
        HentSamordningsdataResp_1.setTpnr(HentSamordningsdataResp.getTpnr()); // move (executionOrder=1)
        HentSamordningsdataResp_1.setPerson(HentSamordningsdataResp.getPerson()); // move (executionOrder=2)
        {
            String HentSamordningsdataResp_pensjonVedtakListe = HentSamordningsdataResp.getPensjonVedtakListe(); // custom.input.forEach (executionOrder=3)
            String HentSamordningsdataResp_1_pensjonVedtakListe = null; // custom.output declaration (executionOrder=3)
            // The specific type of variable HentSamordningsdataResp_pensjonVedtakListe is java.util.List
            // The specific type of variable HentSamordningsdataResp_1_pensjonVedtakListe is java.util.List

            List pensjonVedtakListe = (List) HentSamordningsdataResp_pensjonVedtakListe;
            List pensjonVedtakListe1 = (List) HentSamordningsdataResp_1_pensjonVedtakListe;

            String LOCATE_SERVICE = "com/ibm/wbiserver/map/MapService";
            MapService mapService =(MapService) new ServiceManager().locateService(LOCATE_SERVICE);
            BOFactory bof = (BOFactory) ServiceManager.INSTANCE.locateService("com/ibm/websphere/bo/BOFactory");

            for (int i = 0; i < pensjonVedtakListe.size(); i++) {
                DataObject vedtak = (DataObject) pensjonVedtakListe.get(i);
                if (vedtak!=null) {
                    DataObject vedtak1 = (DataObject) bof.create("http://nav.no/elsam/tpsamordningregistrering/V0_7", "Vedtak");
                    if (vedtak.getString("vedtakId") != null && !vedtak.getString("vedtaksKode").toUpperCase().equals("REGULERING")) {
                        try {

                            mapService.simpleTransform("http://nav-cons-elsam-tptilb-tpsamordningregistrering/no/nav/map", "VedtakV0_8TOVedtakV0_7", vedtak, vedtak1);
                            pensjonVedtakListe1.add(vedtak1);
                        } catch (WBIMapServiceException e) {
                            throw new ServiceRuntimeException(e);
                        } catch (WBIMapNotFoundException e) {
                            throw new ServiceRuntimeException(e);
                        } catch (WBIMapFailureException e) {
                            throw new ServiceRuntimeException(e);
                        }
                    }
                }
            }


            HentSamordningsdataResp_1.setPensjonVedtakListe(HentSamordningsdataResp_1_pensjonVedtakListe); // custom.output assignment (executionOrder=3)

        }
        HentSamordningsdataResp_1.setArbeidVedtakListe(HentSamordningsdataResp.getArbeidVedtakListe()); // move (executionOrder=4)
        HentSamordningsdataResp_1.setTjenestepensjonYtelseListe(HentSamordningsdataResp.getTjenestepensjonYtelseListe()); // move (executionOrder=5)
        HentSamordningsdataResp_1.setUfullstendigeData(HentSamordningsdataResp.getUfullstendigeData()); // move (executionOrder=6)
    }

    public static void HentSamordningsdataRespV0_9TOHentSamordningsdataRespV0_8(HentSamordningsdataResp HentSamordningsdataResp, HentSamordningsdataResp HentSamordningsdataResp_1) {
        HentSamordningsdataResp_1.setTpnr(HentSamordningsdataResp.getTpnr()); // move (executionOrder=1)
        HentSamordningsdataResp_1.setPerson(HentSamordningsdataResp.getPerson()); // move (executionOrder=2)
        VedtakV0_9TOVedtakV0_8(HentSamordningsdataResp.getPensjonVedtakListe(),HentSamordningsdataResp_1.getPensjonVedtakListe()); // submap (executionOrder=3)
        HentSamordningsdataResp_1.setArbeidVedtakListe(HentSamordningsdataResp.getArbeidVedtakListe()); // move (executionOrder=4)
        HentSamordningsdataResp_1.setTjenestepensjonYtelseListe(HentSamordningsdataResp.getTjenestepensjonYtelseListe()); // move (executionOrder=5)
        HentSamordningsdataResp_1.setUfullstendigeData(HentSamordningsdataResp.getUfullstendigeData()); // move (executionOrder=6)
    }

    public static void HentSamordningsdataRespV1_0TOHentSamordningsdataRespV0_9(HentSamordningsdataResp HentSamordningsdataResp, HentSamordningsdataResp HentSamordningsdataResp_1) {
        HentSamordningsdataResp_1.setTpnr(HentSamordningsdataResp.getTpnr()); // move (executionOrder=1)
        HentSamordningsdataResp_1.setPerson(HentSamordningsdataResp.getPerson()); // move (executionOrder=2)
        VedtakV1_0TOVedtakV0_9(HentSamordningsdataResp.getPensjonVedtakListe(),HentSamordningsdataResp_1.getPensjonVedtakListe()); // submap (executionOrder=3)
        HentSamordningsdataResp_1.setArbeidVedtakListe(HentSamordningsdataResp.getArbeidVedtakListe()); // move (executionOrder=4)
        HentSamordningsdataResp_1.setTjenestepensjonYtelseListe(HentSamordningsdataResp.getTjenestepensjonYtelseListe()); // move (executionOrder=5)
        HentSamordningsdataResp_1.setUfullstendigeData(HentSamordningsdataResp.getUfullstendigeData()); // move (executionOrder=6)
    }

    public static void LagreTPYtelseRespV0_6TOLagreTPYtelseRespV0_5(LagreTPYtelseResp LagreTPYtelseResp, LagreTPYtelseResp LagreTPYtelseResp_1) {
        LagreTPYtelseResp_1.setPerson(LagreTPYtelseResp.getPerson()); // move (executionOrder=1)
        LagreTPYtelseResp_1.setTpnr(LagreTPYtelseResp.getTpnr()); // move (executionOrder=2)
        LagreTPYtelseResp_1.setArbeidOgAktivitetsvedtakListe(LagreTPYtelseResp.getArbeidOgAktivitetsvedtakListe()); // move (executionOrder=3)
        LagreTPYtelseResp_1.setTjenestepensjonYtelseListe(LagreTPYtelseResp.getTjenestepensjonYtelseListe()); // move (executionOrder=4)
        LagreTPYtelseResp_1.setUfullstendigeData(LagreTPYtelseResp.getUfullstendigeData()); // move (executionOrder=5)
        VedtakV0_6TOVedtakV0_5(LagreTPYtelseResp.getPensjonVedtakListe(),LagreTPYtelseResp_1.getPensjonVedtakListe()); // submap (executionOrder=6)
    }

    public static void LagreTPYtelseRespV0_7TOLagreTPYtelseV0_6(LagreTPYtelseResp LagreTPYtelseResp, LagreTPYtelseResp LagreTPYtelseResp_1) {
        LagreTPYtelseResp_1.setTpnr(LagreTPYtelseResp.getTpnr()); // move (executionOrder=1)
        LagreTPYtelseResp_1.setPerson(LagreTPYtelseResp.getPerson()); // move (executionOrder=2)
        VedtakV0_7TOVedtakV0_6(LagreTPYtelseResp.getPensjonVedtakListe(),LagreTPYtelseResp_1.getPensjonVedtakListe()); // submap (executionOrder=3)
        LagreTPYtelseResp_1.setArbeidOgAktivitetsvedtakListe(LagreTPYtelseResp.getArbeidOgAktivitetsvedtakListe()); // move (executionOrder=4)
        LagreTPYtelseResp_1.setTjenestepensjonYtelseListe(LagreTPYtelseResp.getTjenestepensjonYtelseListe()); // move (executionOrder=5)
        LagreTPYtelseResp_1.setUfullstendigeData(LagreTPYtelseResp.getUfullstendigeData()); // move (executionOrder=6)
    }

    public static void LagreTPYtelseRespV0_8TOLagreTPYtelseV0_7(LagreTPYtelseResp LagreTPYtelseResp, LagreTPYtelseResp LagreTPYtelseResp_1) {
        LagreTPYtelseResp_1.setTpnr(LagreTPYtelseResp.getTpnr()); // move (executionOrder=1)
        LagreTPYtelseResp_1.setPerson(LagreTPYtelseResp.getPerson()); // move (executionOrder=2)
        VedtakV0_8TOVedtakV0_7(LagreTPYtelseResp.getPensjonVedtakListe(),LagreTPYtelseResp_1.getPensjonVedtakListe()); // submap (executionOrder=3)
        LagreTPYtelseResp_1.setArbeidOgAktivitetsvedtakListe(LagreTPYtelseResp.getArbeidOgAktivitetsvedtakListe()); // move (executionOrder=4)
        LagreTPYtelseResp_1.setTjenestepensjonYtelseListe(LagreTPYtelseResp.getTjenestepensjonYtelseListe()); // move (executionOrder=5)
        LagreTPYtelseResp_1.setUfullstendigeData(LagreTPYtelseResp.getUfullstendigeData()); // move (executionOrder=6)
    }

    public static void LagreTPYtelseRespV0_9TOLagreTPYtelseRespV0_8(LagreTPYtelseResp LagreTPYtelseResp, LagreTPYtelseResp LagreTPYtelseResp_1) {
        LagreTPYtelseResp_1.setTpnr(LagreTPYtelseResp.getTpnr()); // move (executionOrder=1)
        LagreTPYtelseResp_1.setPerson(LagreTPYtelseResp.getPerson()); // move (executionOrder=2)
        LagreTPYtelseResp_1.setArbeidOgAktivitetsvedtakListe(LagreTPYtelseResp.getArbeidOgAktivitetsvedtakListe()); // move (executionOrder=3)
        LagreTPYtelseResp_1.setTjenestepensjonYtelseListe(LagreTPYtelseResp.getTjenestepensjonYtelseListe()); // move (executionOrder=4)
        LagreTPYtelseResp_1.setUfullstendigeData(LagreTPYtelseResp.getUfullstendigeData()); // move (executionOrder=5)
        VedtakV0_9TOVedtakV0_8(LagreTPYtelseResp.getPensjonVedtakListe(),LagreTPYtelseResp_1.getPensjonVedtakListe()); // submap (executionOrder=6)
    }

    public static void LagreTPYtelseRespV1_0TOLagreTPYtelseRespV0_9(LagreTPYtelseResp LagreTPYtelseResp, LagreTPYtelseResp LagreTPYtelseResp_1) {
        LagreTPYtelseResp_1.setTpnr(LagreTPYtelseResp.getTpnr()); // move (executionOrder=1)
        LagreTPYtelseResp_1.setPerson(LagreTPYtelseResp.getPerson()); // move (executionOrder=2)
        VedtakV1_0TOVedtakV0_9(LagreTPYtelseResp.getPensjonVedtakListe(),LagreTPYtelseResp_1.getPensjonVedtakListe()); // submap (executionOrder=3)
        LagreTPYtelseResp_1.setArbeidOgAktivitetsvedtakListe(LagreTPYtelseResp.getArbeidOgAktivitetsvedtakListe()); // move (executionOrder=4)
        LagreTPYtelseResp_1.setTjenestepensjonYtelseListe(LagreTPYtelseResp.getTjenestepensjonYtelseListe()); // move (executionOrder=5)
        LagreTPYtelseResp_1.setUfullstendigeData(LagreTPYtelseResp.getUfullstendigeData()); // move (executionOrder=6)
    }

    public static void VedtakV0_6TOVedtakV0_5(Vedtak Vedtak, Vedtak Vedtak_1) {
        Vedtak_1.setVedtakId(Vedtak.getVedtakId()); // move (executionOrder=1)
        Vedtak_1.setVirkningFom(Vedtak.getVirkningFom()); // move (executionOrder=2)
        Vedtak_1.setVirkningTom(Vedtak.getVirkningTom()); // move (executionOrder=3)
        Vedtak_1.setSaksKode(Vedtak.getSaksKode()); // move (executionOrder=4)
        Vedtak_1.setBarnListe(Vedtak.getBarnListe()); // move (executionOrder=5)
        Vedtak_1.setTypeKrigspensjon(Vedtak.getTypeKrigspensjon()); // move (executionOrder=6)
        Vedtak_1.setBeregningListe(Vedtak.getBeregningListe()); // move (executionOrder=7)
        Vedtak_1.setVedtaksKode(Vedtak.getVedtaksKode()); // move (executionOrder=8)
        Beregning2011V0_6TOBeregning2011V0_5(Vedtak.getBeregning2011Liste(),Vedtak_1.getBeregning2011Liste()); // submap (executionOrder=9)
        Vedtak_1.setRegelverkKode(Vedtak.getRegelverkKode()); // move (executionOrder=10)
    }

    public static void VedtakV0_7TOVedtakV0_6(Vedtak Vedtak, Vedtak Vedtak_1) {
        Vedtak_1.setVedtakId(Vedtak.getVedtakId()); // move (executionOrder=1)
        Vedtak_1.setVirkningFom(Vedtak.getVirkningFom()); // move (executionOrder=2)
        Vedtak_1.setVirkningTom(Vedtak.getVirkningTom()); // move (executionOrder=3)
        Vedtak_1.setSaksKode(Vedtak.getSaksKode()); // move (executionOrder=4)
        Vedtak_1.setBarnListe(Vedtak.getBarnListe()); // move (executionOrder=5)
        Vedtak_1.setTypeKrigspensjon(Vedtak.getTypeKrigspensjon()); // move (executionOrder=6)
        BeregningV0_7TOBeregningV0_6(Vedtak.getBeregningListe(),Vedtak_1.getBeregningListe()); // submap (executionOrder=7)
        Vedtak_1.setVedtaksKode(Vedtak.getVedtaksKode()); // move (executionOrder=8)
        Beregning2011V0_7TOBeregning2011V0_6(Vedtak.getBeregning2011Liste(),Vedtak_1.getBeregning2011Liste()); // submap (executionOrder=9)
        Vedtak_1.setRegelverkKode(Vedtak.getRegelverkKode()); // move (executionOrder=10)
    }

    public static void VedtakV0_8TOVedtakV0_7(Vedtak Vedtak, Vedtak Vedtak_1) {
        Vedtak_1.setVedtakId(Vedtak.getVedtakId()); // move (executionOrder=1)
        Vedtak_1.setVirkningFom(Vedtak.getVirkningFom()); // move (executionOrder=2)
        Vedtak_1.setVirkningTom(Vedtak.getVirkningTom()); // move (executionOrder=3)
        Vedtak_1.setSaksKode(Vedtak.getSaksKode()); // move (executionOrder=4)
        Vedtak_1.setBarnListe(Vedtak.getBarnListe()); // move (executionOrder=5)
        Vedtak_1.setTypeKrigspensjon(Vedtak.getTypeKrigspensjon()); // move (executionOrder=6)
        Vedtak_1.setBeregningListe(Vedtak.getBeregningListe()); // move (executionOrder=7)
        Vedtak_1.setVedtaksKode(Vedtak.getVedtaksKode()); // move (executionOrder=8)
        Beregning2011V0_8TOBeregning2011V0_7(Vedtak.getBeregning2011Liste(),Vedtak_1.getBeregning2011Liste()); // submap (executionOrder=9)
        Vedtak_1.setRegelverkKode(Vedtak.getRegelverkKode()); // move (executionOrder=10)
        Vedtak_1.setUtvidelse(Vedtak.getUtvidelse()); // move (executionOrder=11)
    }

    public static void VedtakV0_9TOVedtakV0_8(Vedtak Vedtak, Vedtak Vedtak_1) {
        Vedtak_1.setVedtakId(Vedtak.getVedtakId()); // move (executionOrder=1)
        Vedtak_1.setVirkningFom(Vedtak.getVirkningFom()); // move (executionOrder=2)
        Vedtak_1.setVirkningTom(Vedtak.getVirkningTom()); // move (executionOrder=3)
        Vedtak_1.setSaksKode(Vedtak.getSaksKode()); // move (executionOrder=4)
        Vedtak_1.setBarnListe(Vedtak.getBarnListe()); // move (executionOrder=5)
        Vedtak_1.setTypeKrigspensjon(Vedtak.getTypeKrigspensjon()); // move (executionOrder=6)
        Vedtak_1.setBeregningListe(Vedtak.getBeregningListe()); // move (executionOrder=7)
        Vedtak_1.setVedtaksKode(Vedtak.getVedtaksKode()); // move (executionOrder=8)
        Vedtak_1.setRegelverkKode(Vedtak.getRegelverkKode()); // move (executionOrder=9)
        Vedtak_1.setUtvidelse(Vedtak.getUtvidelse()); // move (executionOrder=10)
        Vedtak_1.setBeregning2011Liste(Vedtak.getBeregning2011Liste()); // move (executionOrder=11)
    }

    public static void VedtakV1_0TOVedtakV0_9(Vedtak Vedtak, Vedtak Vedtak_1) {
        Vedtak_1.setVedtakId(Vedtak.getVedtakId()); // move (executionOrder=1)
        Vedtak_1.setVirkningFom(Vedtak.getVirkningFom()); // move (executionOrder=2)
        Vedtak_1.setVirkningTom(Vedtak.getVirkningTom()); // move (executionOrder=3)
        Vedtak_1.setSaksKode(Vedtak.getSaksKode()); // move (executionOrder=4)
        Vedtak_1.setBarnListe(Vedtak.getBarnListe()); // move (executionOrder=5)
        Vedtak_1.setTypeKrigspensjon(Vedtak.getTypeKrigspensjon()); // move (executionOrder=6)
        Vedtak_1.setBeregningListe(Vedtak.getBeregningListe()); // move (executionOrder=7)
        Vedtak_1.setVedtaksKode(Vedtak.getVedtaksKode()); // move (executionOrder=8)
        Vedtak_1.setBeregning2011Liste(Vedtak.getBeregning2011Liste()); // move (executionOrder=9)
        Vedtak_1.setRegelverkKode(Vedtak.getRegelverkKode()); // move (executionOrder=10)
        Vedtak_1.setUtvidelse(Vedtak.getUtvidelse()); // move (executionOrder=11)
        BeregningUforetrygdV1_0TOBeregningUforetrygdV0_9(Vedtak.getBeregningUforetrygdListe(),Vedtak_1.getBeregningUforetrygdListe()); // submap (executionOrder=12)
    }
*/
