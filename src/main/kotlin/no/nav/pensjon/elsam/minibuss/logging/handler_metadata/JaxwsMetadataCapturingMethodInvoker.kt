package no.nav.pensjon.elsam.minibuss.logging.handler_metadata

import org.apache.cxf.jaxws.JAXWSMethodInvoker
import org.apache.cxf.message.Exchange
import org.apache.cxf.service.invoker.Factory
import java.lang.reflect.Method

class JaxwsMetadataCapturingMethodInvoker(factory: Factory) : JAXWSMethodInvoker(factory) {
    override fun invoke(exchange: Exchange, serviceObject: Any, method: Method, params: List<Any>): Any {
        addHandlerMetaData(method, "JAX-WS")
        try {
            return super.invoke(exchange, serviceObject, method, params)
        } finally {
            clearHandlerMetaDataFromMDC()
        }
    }
}
