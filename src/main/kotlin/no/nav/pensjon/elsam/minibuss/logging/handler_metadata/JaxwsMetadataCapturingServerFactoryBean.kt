package no.nav.pensjon.elsam.minibuss.logging.handler_metadata

import org.apache.cxf.jaxws.JaxWsServerFactoryBean
import org.apache.cxf.service.invoker.Invoker
import org.apache.cxf.service.invoker.SingletonFactory

class JaxwsMetadataCapturingServerFactoryBean : JaxWsServerFactoryBean() {
    override fun createInvoker(): Invoker =
        if (serviceBean == null) {
            JaxwsMetadataCapturingMethodInvoker(SingletonFactory(serviceClass))
        } else {
            JaxwsMetadataCapturingMethodInvoker(SingletonFactory(serviceBean))
        }
}
