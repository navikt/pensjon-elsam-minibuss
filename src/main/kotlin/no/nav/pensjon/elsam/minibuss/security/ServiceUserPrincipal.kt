package no.nav.pensjon.elsam.minibuss.security

import java.security.Principal

data class ServiceUserPrincipal(
    val username: String,
) : Principal {
    override fun getName(): String = this.username
}
