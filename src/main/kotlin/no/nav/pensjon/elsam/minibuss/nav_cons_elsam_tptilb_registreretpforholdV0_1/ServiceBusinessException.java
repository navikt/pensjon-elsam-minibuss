package no.nav.pensjon.elsam.minibuss.nav_cons_elsam_tptilb_registreretpforholdV0_1;

import no.nav.elsam.registreretpforhold.v0_1.FaultBase;
import no.nav.elsam.registreretpforhold.v0_1.FaultGenerisk;

public class ServiceBusinessException extends Exception {
    private final Object fault;

    public ServiceBusinessException(Object fault) {
        this.fault = fault;
    }

    public Object getFault() {
        return fault;
    }
}
