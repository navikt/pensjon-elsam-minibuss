package no.nav.pensjon.elsam.minibuss.context

object RequestContextHolder {
    private val REQUEST_CONTEXT_HOLDER = ThreadLocal<RequestContext?>()

    fun currentRequestContext(): RequestContext? = REQUEST_CONTEXT_HOLDER.get()

    fun setRequestContext(
        transactionId: String? = null,
        componentId: String? = null,
        userId: String? = null,
    ) {
        REQUEST_CONTEXT_HOLDER.set(
            RequestContext(
                transactionId = transactionId,
                componentId = componentId,
                userId = userId,
            )
        )
    }

    fun resetRequestContext() {
        REQUEST_CONTEXT_HOLDER.remove()
    }
}
