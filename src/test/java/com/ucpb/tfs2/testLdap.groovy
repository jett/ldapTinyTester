package com.ucpb.tfs2
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:postlc-unitTestContext.xml")
class testLdap {

    @Autowired
    LdapDao ldapDao;

    @Test
    public void testADSearch() {


        // adding custom attributes
        // http://virtualizesharepoint.com/2011/07/04/adding-custom-attributes-to-active-directory-user-profile/

        println ldapDao.getUserAttributes("mcueto");

    }

}
