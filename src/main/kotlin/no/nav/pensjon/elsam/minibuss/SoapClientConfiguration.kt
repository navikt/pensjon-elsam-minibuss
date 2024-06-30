package no.nav.pensjon.elsam.minibuss

import nav_cons_pen_psak_samhandler.no.nav.inf.PSAKSamhandler
import nav_cons_sto_sam_tjenestepensjon.no.nav.inf.SAMTjenestepensjon
import nav_lib_frg.no.nav.lib.frg.inf.Samhandler
import nav_lib_frg.no.nav.lib.frg.inf.tjenestepensjon.Tjenestepensjon
import nav_lib_sto.no.nav.lib.sto.inf.samordning.Samordning
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SoapClientConfiguration {
    @Bean
    fun psakSamhandler(): PSAKSamhandler = TODO()

    @Bean
    fun samhandler(): Samhandler = TODO()

    @Bean
    fun samordning(): Samordning = TODO()

    @Bean
    fun samTjenestepensjon(): SAMTjenestepensjon = TODO()

    @Bean
    fun tjenestepensjon(): Tjenestepensjon = TODO()
}
