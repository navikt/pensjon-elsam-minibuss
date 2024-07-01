package no.nav.pensjon.elsam.minibuss.security

import org.apache.catalina.realm.RealmBase
import java.security.Principal
import java.util.UUID

class ServiceUserRealm : RealmBase() {
    override fun getPassword(password: String): String {
        return serviceUserPassword
    }

    override fun getPrincipal(username: String): Principal {
        return ServiceUserPrincipal(username)
    }

    companion object {
        val serviceUserPassword = UUID.randomUUID().toString()
    }
}
