package com.ucpb.tfs2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.stereotype.Component;

import javax.naming.directory.Attributes;
import java.util.*;

@Component
public class LdapDao {

    LdapTemplate template;

    @Autowired
    public LdapDao(LdapContextSource contextSource) {
        template = new LdapTemplate(contextSource);
    }

    @SuppressWarnings("unchecked")
    public Map<String, String> getUserAttributes(String username) {
        Map<String, String> results = new HashMap<String, String>();

        String objectClass = "samAccountName=" + username;
        template.setIgnorePartialResultException(true);
        LinkedList<Map<String, String>> list = (LinkedList<Map<String, String>>) template.search("", objectClass, new UserAttributesMapper());


        if (!list.isEmpty()) {
            // Should only return one item
            results = list.get(0);
        }
        return results;
    }

    private class UserAttributesMapper implements AttributesMapper {

        private String getGroupName(String groupDN) {

            if(groupDN.length() > 0) {
                String hr[] = groupDN.split(",");

                if(hr.length > 0) {
                    String dn[] = hr[0].split("=");

                    return dn[1];
                }
            }

            return "";
        }

        @Override
        public Map<String, Object> mapFromAttributes(Attributes attributes) throws javax.naming.NamingException {
            Map<String, Object> map = new HashMap<String, Object>();
            String email = "";
			String displayName = "";
			String extensionAttribute1 = "";
			String extensionAttribute5 = "";		
			String sAMAccountName="";
			
            try {
                String fullname = (String) attributes.get("displayName").get();
				
                if(attributes.get("mail") != null) {
                    email = (String) attributes.get("mail").get();
                }
				if(attributes.get("displayName") != null) {
                    displayName = (String) attributes.get("displayName").get();
                }
				if(attributes.get("extensionAttribute1") != null) {
                    extensionAttribute1 = (String) attributes.get("extensionAttribute1").get();
                }
				if(attributes.get("extensionAttribute5") != null) {
                    extensionAttribute5 = (String) attributes.get("extensionAttribute5").get();
                }
				if(attributes.get("sAMAccountName") != null) {
                    sAMAccountName = (String) attributes.get("sAMAccountName").get();
                }

                List<String> memberOf = new ArrayList();

                // get user groups
                if(attributes.get("memberOf") != null) {
                    for (Enumeration vals = attributes.get("memberOf").getAll(); vals.hasMoreElements();) {
                        memberOf.add(getGroupName((String)vals.nextElement()));
                    }
                }
//                String title = (String) attributes.get("title").get();

                map.put("fullname", fullname);
				map.put("displayName", displayName);
                map.put("email", email);
				map.put("extensionAttribute1",extensionAttribute1);
				map.put("extensionAttribute5",extensionAttribute5);
				map.put("sAMAccountName",sAMAccountName);
                map.put("groups", memberOf);
//                map.put("title", title);

            }
            catch (Exception e) {

            }
            return map;
        }
    }
}