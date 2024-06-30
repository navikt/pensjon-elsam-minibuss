package no.nav.pensjon.elsam.minibuss

import nav_lib_frg.no.nav.lib.frg.inf.Samhandler
import nav_lib_frg.no.nav.lib.frg.inf.tjenestepensjon.Tjenestepensjon
import nav_lib_sto.no.nav.lib.sto.inf.samordning.Samordning
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SoapClientConfiguration {
    @Bean
    fun samhandler(): Samhandler = TODO()

    @Bean
    fun samordning(): Samordning = TODO()

    @Bean
    fun tjenestepensjon(): Tjenestepensjon = TODO()
}
