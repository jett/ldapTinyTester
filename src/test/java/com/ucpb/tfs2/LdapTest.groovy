package com.ucpb.tfs2
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:postlc-unitTestContext.xml")
class LdapTest {

    @Autowired
    LdapDao ldapDao;

    @Test
    public void testADSearch() {


        // adding custom attributes
        // http://virtualizesharepoint.com/2011/07/04/adding-custom-attributes-to-active-directory-user-profile/

        // this and auth all in one
        // http://blog.javachap.com/index.php/ldap-user-management-with-spring-ldap/
//        http://forum.springsource.org/showthread.php?81399-Spring-Security-Pojo-Example

        println ldapDao.getUserAttributes("ibdmuc");
		println ldapDao.getUserAttributes("tsdtst3");
		println ldapDao.getUserAttributes("tsdtst4");
		println ldapDao.getUserAttributes("tsdtst5");
		println ldapDao.getUserAttributes("tsdtst6");
		println ldapDao.getUserAttributes("tsdtst7");
		println ldapDao.getUserAttributes("tsdtst8");
		println ldapDao.getUserAttributes("tsdtst9");
//        println ldapDao.getUserAttributes("itdipc6");

    }

}
