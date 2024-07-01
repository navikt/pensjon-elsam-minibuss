package no.nav.pensjon.elsam.minibuss;

public class ServiceBusinessException extends Exception {
    private final Object fault;

    public ServiceBusinessException(Object fault) {
        this.fault = fault;
    }

    public Object getFault() {
        return fault;
    }
}
