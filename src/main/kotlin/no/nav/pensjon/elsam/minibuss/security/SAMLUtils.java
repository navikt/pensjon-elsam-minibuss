package no.nav.pensjon.elsam.minibuss.security;

import org.opensaml.saml.saml2.core.Assertion;
import org.opensaml.saml.saml2.core.Attribute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.naming.InvalidNameException;
import javax.naming.ldap.LdapName;
import javax.naming.ldap.Rdn;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SAMLUtils {
    private static final Logger logger = LoggerFactory.getLogger(SAMLUtils.class);

    public static void samlAssertionToSubject(Assertion assertion) throws TransformerException {
        final String uid = filterDNtoCNvalue(assertion.getSubject().getNameID().getValue());
        final List<Attribute> attributes = assertion.getAttributeStatements().get(0).getAttributes();

        Map<String, String> attributeMap = new HashMap<>();
        for (Attribute attribute : attributes) {
            String attributeName = attribute.getName();
            String attributeValue = attribute.getAttributeValues().get(0)
                    .getDOM().getFirstChild().getTextContent();

            attributeMap.put(attributeName, attributeValue);
        }
        if (uid == null) {
            throw new RuntimeException("SAML assertion is missing mandatory element NameId");
        }
        getSamlAssertionAsString(assertion);
    }

    static String getSamlAssertionAsString(Assertion assertion) throws TransformerException {
        StringWriter writer = new StringWriter();
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.transform(new DOMSource(assertion.getDOM()), new StreamResult(writer));
        return writer.toString();
    }

    public static String filterDNtoCNvalue(String userid) {
        try {
            LdapName ldapname = new LdapName(userid);
            String cn = null;
            for (Rdn rdn : ldapname.getRdns()) {
                if (rdn.getType().equalsIgnoreCase("CN")) {
                    cn = rdn.getValue().toString();
                    logger.debug("uid on DN form. Filtered from {} to {}", userid, cn);
                    break;
                }
            }
            return cn;
        } catch (InvalidNameException e) {
            logger.debug("uid not on DN form. Skipping filter. {}", e.toString());
            return userid;
        }
    }

}
