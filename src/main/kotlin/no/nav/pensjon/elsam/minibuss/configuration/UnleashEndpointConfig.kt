package no.nav.pensjon.elsam.minibuss.configuration

import io.getunleash.DefaultUnleash
import io.getunleash.UnleashContext.builder
import io.getunleash.util.UnleashConfig
import no.nav.pensjon.elsam.minibuss.context.RequestContextHolder.currentRequestContext
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class UnleashEndpointConfig {
    @Bean
    fun unleash(
        @Value("\${UNLEASH_SERVER_API_URL}") endpoint: String,
        @Value("\${UNLEASH_SERVER_API_TOKEN}") token: String,
        @Value("\${spring.application.name}") appName: String,
        @Value("\${environment.name}") environmentName: String,
    ) = DefaultUnleash(
        UnleashConfig.builder()
            .unleashAPI("$endpoint/api")
            .appName(appName)
            .environment(environmentName)
            .apiKey(token)
            .unleashContextProvider {
                builder().apply { currentRequestContext()?.userId?.let<String, Unit> { userId(it) } }.build()
            }
            .build()
    )
}
