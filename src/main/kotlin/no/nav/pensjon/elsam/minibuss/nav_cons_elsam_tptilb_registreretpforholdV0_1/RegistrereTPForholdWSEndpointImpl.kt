package no.nav.pensjon.elsam.minibuss.nav_cons_elsam_tptilb_registreretpforholdV0_1

import jakarta.jws.WebMethod
import jakarta.jws.WebParam
import jakarta.jws.WebResult
import jakarta.jws.WebService
import jakarta.xml.bind.annotation.XmlSeeAlso
import jakarta.xml.ws.RequestWrapper
import jakarta.xml.ws.ResponseWrapper
import no.nav.elsam.registreretpforhold.v0_1.*
import no.nav.elsam.registreretpforhold.v0_1.ObjectFactory
import org.springframework.stereotype.Component

@Component
@WebService(
    targetNamespace = "http://nav.no/elsam/registreretpforhold/V0_1",
    name = "RegistrereTPForhold",
    wsdlLocation = "wsdl/RegistrereTPForholdWSEXP_RegistrereTPForholdHttp_Service.wsdl",
    serviceName = "RegistrereTPForholdWSEXP_RegistrereTPForholdHttpService",
    portName = "RegistrereTPForholdWSEXP_RegistrereTPForholdHttpPort",
)
@XmlSeeAlso(ObjectFactory::class)
@Suppress("HttpUrlsUsage")
class RegistrereTPForholdWSEndpointImpl(
    private val navConsElsamTptilbRegisrereTpForhold: NavConsElsamTptilbRegisrereTpForhold,
) : RegistrereTPForhold {
    @WebMethod
    @RequestWrapper(
        localName = "opprettTPForhold",
        targetNamespace = "http://nav.no/elsam/registreretpforhold/V0_1",
        className = "no.nav.elsam.registreretpforhold.v0_1.OpprettTPForhold"
    )
    @ResponseWrapper(
        localName = "opprettTPForholdResponse",
        targetNamespace = "http://nav.no/elsam/registreretpforhold/V0_1",
        className = "no.nav.elsam.registreretpforhold.v0_1.OpprettTPForholdResponse"
    )
    @Throws(
        OpprettTPForholdFaultPersonIkkeFunnetMsg::class,
        OpprettTPForholdFaultGeneriskMsg::class
    )
    override fun opprettTPForhold(
        @WebParam(name = "opprettTPForholdReq", targetNamespace = "") opprettTPForholdReq: OpprettTPForholdReq
    ) {
        return navConsElsamTptilbRegisrereTpForhold.opprettTPForhold(opprettTPForholdReq)
    }

    @WebMethod
    @RequestWrapper(
        localName = "hentTPForholdListe",
        targetNamespace = "http://nav.no/elsam/registreretpforhold/V0_1",
        className = "no.nav.elsam.registreretpforhold.v0_1.HentTPForholdListe"
    )
    @ResponseWrapper(
        localName = "hentTPForholdListeResponse",
        targetNamespace = "http://nav.no/elsam/registreretpforhold/V0_1",
        className = "no.nav.elsam.registreretpforhold.v0_1.HentTPForholdListeResponse"
    )
    @WebResult(name = "hentTPForholdListeResp", targetNamespace = "")
    @Throws(
        HentTPForholdListeFaultTjenestepensjonForholdIkkeFunnetMsg::class,
        HentTPForholdListeFaultGeneriskMsg::class
    )
    override fun hentTPForholdListe(
        @WebParam(name = "hentTPForholdListeReq", targetNamespace = "") hentTPForholdListeReq: HentTPForholdListeReq
    ): HentTPForholdListeResp? {
        return navConsElsamTptilbRegisrereTpForhold.hentTPForholdListe(hentTPForholdListeReq)
    }

    @WebMethod
    @RequestWrapper(
        localName = "slettTPForhold",
        targetNamespace = "http://nav.no/elsam/registreretpforhold/V0_1",
        className = "no.nav.elsam.registreretpforhold.v0_1.SlettTPForhold"
    )
    @ResponseWrapper(
        localName = "slettTPForholdResponse",
        targetNamespace = "http://nav.no/elsam/registreretpforhold/V0_1",
        className = "no.nav.elsam.registreretpforhold.v0_1.SlettTPForholdResponse"
    )
    @Throws(
        SlettTPForholdFaultKanIkkeSlettesMsg::class,
        SlettTPForholdFaultTjenestepensjonForholdIkkeFunnetMsg::class,
        SlettTPForholdFaultGeneriskMsg::class
    )
    override fun slettTPForhold(
        @WebParam(name = "slettTPForholdReq", targetNamespace = "") slettTPForholdReq: SlettTPForholdReq
    ) {
        navConsElsamTptilbRegisrereTpForhold.slettTPForhold(slettTPForholdReq)
    }
}
