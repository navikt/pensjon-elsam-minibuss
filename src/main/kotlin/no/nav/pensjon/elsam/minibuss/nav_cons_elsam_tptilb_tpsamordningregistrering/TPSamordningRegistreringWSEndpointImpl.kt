package no.nav.pensjon.elsam.minibuss.nav_cons_elsam_tptilb_tpsamordningregistrering

import io.getunleash.DefaultUnleash
import jakarta.jws.WebMethod
import jakarta.jws.WebParam
import jakarta.jws.WebResult
import jakarta.jws.WebService
import jakarta.xml.bind.annotation.XmlSeeAlso
import jakarta.xml.ws.RequestWrapper
import jakarta.xml.ws.ResponseWrapper
import nav_cons_elsam_tptilb_tpsamordningregistrering.no.nav.asbo.*
import no.nav.elsam.tpsamordningregistrering.v0_5.HentSamordningsdataReq
import no.nav.elsam.tpsamordningregistrering.v0_5.LagreTPYtelseReq
import no.nav.elsam.tpsamordningregistrering.v0_5.OpprettRefusjonskravReq
import no.nav.elsam.tpsamordningregistrering.v0_5.SlettTPYtelseReq
import no.nav.elsam.tpsamordningregistrering.v0_8.ObjectFactory
import no.nav.elsam.tpsamordningregistrering.v1_0.*
import no.nav.pensjon.elsam.minibuss.sam.SamService
import org.slf4j.Logger
import org.slf4j.LoggerFactory.getLogger
import org.springframework.stereotype.Component

@Component
@WebService(
    targetNamespace = "http://nav.no/elsam/tpsamordningregistrering/V1_0",
    name = "TPSamordningRegistrering",
    wsdlLocation = "wsdl/nav-cons-elsam-tptilb-tpsamordningregistrering_TPSamordningRegistreringV1_0WSEXP.wsdl",
    serviceName = "TPSamordningRegistreringV1_0WSEXP_TPSamordningRegistreringHttpService",
    portName = "TPSamordningRegistreringV1_0WSEXP_TPSamordningRegistreringHttpPort",
)
@XmlSeeAlso(
    ObjectFactory::class,
    no.nav.elsam.tpsamordningregistrering.v0_7.ObjectFactory::class,
    no.nav.elsam.tpsamordningregistrering.v0_5.ObjectFactory::class,
    nav_cons_elsam_tptilb_tpsamordningregistrering.no.nav.tpsamordningregistrering.v1_0.asbo.ObjectFactory::class,
    no.nav.elsam.tpsamordningregistrering.v1_0.ObjectFactory::class
)
@Suppress("HttpUrlsUsage")
class TPSamordningRegistreringWSEndpointImpl(
    val navConsElsamTplibTpSamordningRegistrering: NavConsElsamTplibTpSamordningRegistrering,
    val busTPSamordningRegistrering: TPSamordningRegistrering,
    val samService: SamService,
    private val unleash: DefaultUnleash
) : TPSamordningRegistrering {
    private val logger: Logger = getLogger(javaClass)

    @WebMethod
    @RequestWrapper(
        localName = "slettTPYtelse",
        targetNamespace = "http://nav.no/elsam/tpsamordningregistrering/V1_0",
        className = "no.nav.elsam.tpsamordningregistrering.v1_0.SlettTPYtelse"
    )
    @ResponseWrapper(
        localName = "slettTPYtelseResponse",
        targetNamespace = "http://nav.no/elsam/tpsamordningregistrering/V1_0",
        className = "no.nav.elsam.tpsamordningregistrering.v1_0.SlettTPYtelseResponse"
    )
    @Throws(
        SlettTPYtelseFaultGeneriskMsg::class,
        SlettTPYtelseFaultTPYtelseIkkeFunnetMsg::class
    )
    override fun slettTPYtelse(
        @WebParam(name = "slettTPYtelseReq", targetNamespace = "") slettTPYtelseReq: SlettTPYtelseReq
    ) {
        if (true) {
            return busTPSamordningRegistrering.slettTPYtelse(slettTPYtelseReq)
        }

        try {
            navConsElsamTplibTpSamordningRegistrering.slettTPYtelse(slettTPYtelseReq)
        } catch (e: Exception) {
            throw when (e) {
                is SlettTPYtelseIntFaultGeneriskMsg -> SlettTPYtelseFaultGeneriskMsg(e.message, e.faultInfo)
                is SlettTPYtelseIntFaultTPYtelseIkkeFunnetMsg -> SlettTPYtelseFaultTPYtelseIkkeFunnetMsg(e.message, e.faultInfo)
                else -> e
            }
        }
    }

    @WebMethod
    @RequestWrapper(
        localName = "hentSamordningsdata",
        targetNamespace = "http://nav.no/elsam/tpsamordningregistrering/V1_0",
        className = "no.nav.elsam.tpsamordningregistrering.v1_0.HentSamordningsdata"
    )
    @ResponseWrapper(
        localName = "hentSamordningsdataResponse",
        targetNamespace = "http://nav.no/elsam/tpsamordningregistrering/V1_0",
        className = "no.nav.elsam.tpsamordningregistrering.v1_0.HentSamordningsdataResponse"
    )
    @WebResult(name = "hentSamordningsdataResp", targetNamespace = "")
    @Throws(
        HentSamordningsdataFaultTPForholdIkkeIverksattMsg::class,
        HentSamordningsdataFaultGeneriskMsg::class
    )
    override fun hentSamordningsdata(
        @WebParam(name = "hentSamordningsdataReq", targetNamespace = "") hentSamordningsdataReq: HentSamordningsdataReq
    ): HentSamordningsdataResp? {
        return busTPSamordningRegistrering.hentSamordningsdata(hentSamordningsdataReq)
    }

    @WebMethod
    @RequestWrapper(
        localName = "opprettRefusjonskrav",
        targetNamespace = "http://nav.no/elsam/tpsamordningregistrering/V1_0",
        className = "no.nav.elsam.tpsamordningregistrering.v1_0.OpprettRefusjonskrav"
    )
    @ResponseWrapper(
        localName = "opprettRefusjonskravResponse",
        targetNamespace = "http://nav.no/elsam/tpsamordningregistrering/V1_0",
        className = "no.nav.elsam.tpsamordningregistrering.v1_0.OpprettRefusjonskravResponse"
    )
    @Throws(
        OpprettRefusjonskravFaultGeneriskMsg::class,
        OpprettRefusjonskravFaultSamordningsIdOgPersonKorrelererIkkeMsg::class,
        OpprettRefusjonskravFaultSamordningsIdIkkeFunnetMsg::class,
        OpprettRefusjonskravFaultRefusjonskravUtenforSamordningspliktigPeriodeMsg::class,
        OpprettRefusjonskravFaultAlleredeMottattRefusjonskravMsg::class,
        OpprettRefusjonskravFaultRefusjonskravUtenforTidsfristMsg::class
    )
    override fun opprettRefusjonskrav(
        @WebParam(
            name = "opprettRefusjonskravReq",
            targetNamespace = ""
        ) opprettRefusjonskravReq: OpprettRefusjonskravReq
    ) {
        if (true) {
            return busTPSamordningRegistrering.opprettRefusjonskrav(opprettRefusjonskravReq)
        }

        try {
            return navConsElsamTplibTpSamordningRegistrering.opprettRefusjonskrav(opprettRefusjonskravReq)
        } catch (e: Exception) {
            throw when (e) {
                is OpprettRefusjonskravIntFaultSamordningsIdOgPersonKorrelererIkkeMsg -> OpprettRefusjonskravFaultSamordningsIdOgPersonKorrelererIkkeMsg(e.message, e.faultInfo)
                is OpprettRefusjonskravIntFaultAlleredeMottattRefusjonskravMsg -> OpprettRefusjonskravFaultAlleredeMottattRefusjonskravMsg(e.message, e.faultInfo)
                is OpprettRefusjonskravIntFaultRefusjonskravUtenforSamordningspliktigPeriodeMsg -> OpprettRefusjonskravFaultRefusjonskravUtenforSamordningspliktigPeriodeMsg(e.message, e.faultInfo)
                is OpprettRefusjonskravIntFaultSamordningsIdIkkeFunnetMsg -> OpprettRefusjonskravFaultSamordningsIdIkkeFunnetMsg(e.message, e.faultInfo)
                is OpprettRefusjonskravIntFaultRefusjonskravUtenforTidsfristMsg -> OpprettRefusjonskravFaultRefusjonskravUtenforTidsfristMsg(e.message, e.faultInfo)
                is OpprettRefusjonskravIntFaultGeneriskMsg -> OpprettRefusjonskravFaultGeneriskMsg(e.message, e.faultInfo)
                else -> e
            }
        }
    }

    @WebMethod
    @RequestWrapper(
        localName = "lagreTPYtelse",
        targetNamespace = "http://nav.no/elsam/tpsamordningregistrering/V1_0",
        className = "no.nav.elsam.tpsamordningregistrering.v1_0.LagreTPYtelse"
    )
    @ResponseWrapper(
        localName = "lagreTPYtelseResponse",
        targetNamespace = "http://nav.no/elsam/tpsamordningregistrering/V1_0",
        className = "no.nav.elsam.tpsamordningregistrering.v1_0.LagreTPYtelseResponse"
    )
    @WebResult(name = "lagreTPYtelseResp", targetNamespace = "")
    @Throws(
        LagreTPYtelseFaultTPYtelseAlleredeRegistrertMsg::class,
        LagreTPYtelseFaultGeneriskMsg::class
    )
    override fun lagreTPYtelse(
        @WebParam(name = "lagreTPYtelseReq", targetNamespace = "") lagreTPYtelseReq: LagreTPYtelseReq
    ): LagreTPYtelseResp? {

        if (unleash.isEnabled("pensjon-elsam-minibuss.lagreTPYtelse")) {
            return samService.lagreTPYtelse(lagreTPYtelseReq).also {
                logger.debug("lagreTPYtelse, kall til SAM: {}", it)
            }
        }

        if (true) {
            return busTPSamordningRegistrering.lagreTPYtelse(lagreTPYtelseReq)
        }

        try {
            return navConsElsamTplibTpSamordningRegistrering.lagreTPYtelse(lagreTPYtelseReq)
        } catch (e: Exception) {
            throw when (e) {
                is LagreTPYtelseIntFaultGeneriskMsg -> LagreTPYtelseFaultGeneriskMsg(e.message, e.faultInfo)
                is LagreTPYtelseIntFaultTPYtelseAlleredeRegistrertMsg -> LagreTPYtelseFaultTPYtelseAlleredeRegistrertMsg(e.message, e.faultInfo)
                else -> e
            }
        }
    }
}
