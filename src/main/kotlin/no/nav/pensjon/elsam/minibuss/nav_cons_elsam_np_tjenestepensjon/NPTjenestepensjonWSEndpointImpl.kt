package no.nav.pensjon.elsam.minibuss.nav_cons_elsam_np_tjenestepensjon

import jakarta.jws.WebMethod
import jakarta.jws.WebParam
import jakarta.jws.WebResult
import jakarta.jws.WebService
import jakarta.xml.bind.annotation.XmlSeeAlso
import jakarta.xml.ws.BindingType
import jakarta.xml.ws.RequestWrapper
import jakarta.xml.ws.ResponseWrapper
import nav_cons_elsam_np_tjenestepensjon.no.nav.inf.nptjenestepensjon.HarTjenestepensjonsforholdFaultPersonIkkeFunnetMsg
import nav_cons_elsam_np_tjenestepensjon.no.nav.inf.nptjenestepensjon.NPTjenestepensjon
import nav_lib_cons_elsam_np.no.nav.lib.elsam.np.asbo.tjenestepensjon.ASBONpHarTjenestepensjonsforholdRequest
import nav_lib_cons_elsam_np.no.nav.lib.elsam.np.asbo.tjenestepensjon.ASBONpHarTjenestepensjonsforholdResponse
import nav_lib_cons_elsam_np.no.nav.lib.elsam.np.fault.ObjectFactory
import org.springframework.stereotype.Component

@Component
@WebService(
    targetNamespace = "http://nav-cons-elsam-np-tjenestepensjon/no/nav/inf/NPTjenestepensjon",
    name = "NPTjenestepensjon",
    wsdlLocation = "wsdl/nav-cons-elsam-np-tjenestepensjon_NPTjenestepensjonWSEXP.wsdl",
    serviceName = "NPTjenestepensjonWSEXP_NPTjenestepensjonHttpService",
    portName = "NPTjenestepensjonWSEXP_NPTjenestepensjonHttpPort",
)
@XmlSeeAlso(
    ObjectFactory::class,
    nav_lib_cons_elsam_np.no.nav.lib.elsam.np.asbo.tjenestepensjon.ObjectFactory::class,
    nav_cons_elsam_np_tjenestepensjon.no.nav.inf.nptjenestepensjon.ObjectFactory::class
)
@BindingType
class NPTjenestepensjonWSEndpointImpl(
    private val npTjenestepensjonToFinnTjenestepensjonsforhold: NPTjenestepensjonToFinnTjenestepensjonsforhold,
) : NPTjenestepensjon {
    @WebMethod
    @RequestWrapper(
        localName = "harTjenestepensjonsforhold",
        targetNamespace = "http://nav-cons-elsam-np-tjenestepensjon/no/nav/inf/NPTjenestepensjon",
        className = "nav_cons_elsam_np_tjenestepensjon.no.nav.inf.nptjenestepensjon.HarTjenestepensjonsforhold"
    )
    @ResponseWrapper(
        localName = "harTjenestepensjonsforholdResponse",
        targetNamespace = "http://nav-cons-elsam-np-tjenestepensjon/no/nav/inf/NPTjenestepensjon",
        className = "nav_cons_elsam_np_tjenestepensjon.no.nav.inf.nptjenestepensjon.HarTjenestepensjonsforholdResponse"
    )
    @WebResult(name = "harTjenestepensjonsResponse", targetNamespace = "")
    @Throws(
        HarTjenestepensjonsforholdFaultPersonIkkeFunnetMsg::class
    )
    override fun harTjenestepensjonsforhold(
        @WebParam(
            name = "harTjenestepensjonsRequest",
            targetNamespace = ""
        ) harTjenestepensjonsRequest: ASBONpHarTjenestepensjonsforholdRequest
    ): ASBONpHarTjenestepensjonsforholdResponse? {
        return npTjenestepensjonToFinnTjenestepensjonsforhold.harTjenestepensjonsforhold(harTjenestepensjonsRequest)
    }
}
