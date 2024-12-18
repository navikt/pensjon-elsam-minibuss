package no.nav.pensjon.elsam.minibuss.configuration

import ch.qos.logback.access.tomcat.LogbackValve
import no.nav.pensjon.elsam.minibuss.security.ServiceUserRealm
import org.springframework.boot.web.embedded.tomcat.TomcatContextCustomizer
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory
import org.springframework.boot.web.server.WebServerFactoryCustomizer
import org.springframework.stereotype.Component

@Component
class TomcatEngineValveCustomizer : WebServerFactoryCustomizer<TomcatServletWebServerFactory> {
    override fun customize(factory: TomcatServletWebServerFactory) {
        factory.addEngineValves(
            LogbackValve().apply {
                name = "Logback Access"
                filename = "logback-access.xml"
            }
        )

        factory.addContextCustomizers(TomcatContextCustomizer { it.realm = ServiceUserRealm() })
    }
}
