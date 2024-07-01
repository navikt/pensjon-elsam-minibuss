package no.nav.pensjon.elsam.minibuss.security

import org.opensaml.saml.saml2.core.Assertion
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.StringWriter
import javax.naming.InvalidNameException
import javax.naming.ldap.LdapName
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult

object SAMLUtils {
    private val logger: Logger = LoggerFactory.getLogger(SAMLUtils::class.java)

    fun samlAssertionToSubject(assertion: Assertion) {
        val uid = filterDNtoCNvalue(assertion.subject.nameID.value)
        val attributes = assertion.attributeStatements[0].attributes

        val attributeMap: MutableMap<String, String> = HashMap()
        for (attribute in attributes) {
            val attributeName = attribute.name
            val attributeValue = attribute.attributeValues[0].dom!!.firstChild.textContent

            attributeMap[attributeName] = attributeValue
        }
        if (uid == null) {
            throw RuntimeException("SAML assertion is missing mandatory element NameId")
        }
        getSamlAssertionAsString(assertion)
    }

    fun getSamlAssertionAsString(assertion: Assertion): String {
        val writer = StringWriter()
        val transformerFactory = TransformerFactory.newInstance()
        val transformer = transformerFactory.newTransformer()
        transformer.transform(DOMSource(assertion.dom), StreamResult(writer))
        return writer.toString()
    }

    fun filterDNtoCNvalue(userid: String?): String? {
        try {
            val ldapname = LdapName(userid)
            var cn: String? = null
            for (rdn in ldapname.rdns) {
                if (rdn.type.equals("CN", ignoreCase = true)) {
                    cn = rdn.value.toString()
                    logger.debug("uid on DN form. Filtered from {} to {}", userid, cn)
                    break
                }
            }
            return cn
        } catch (e: InvalidNameException) {
            logger.debug("uid not on DN form. Skipping filter. {}", e.toString())
            return userid
        }
    }
}
