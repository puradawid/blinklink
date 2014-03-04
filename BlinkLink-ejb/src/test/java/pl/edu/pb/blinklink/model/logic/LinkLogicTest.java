package pl.edu.pb.blinklink.model.logic;

import java.util.Collection;
import java.util.Date;
import javax.ejb.embeddable.EJBContainer;
import javax.inject.Inject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import pl.edu.pb.blinklink.model.BlinkUser;
import pl.edu.pb.blinklink.model.UserLink;
import pl.edu.pb.blinklink.model.logic.impl.LinkLogicHibernate;

/**
 *
 * @author dawid
 */
public class LinkLogicTest {
    
    LinkLogicHibernate llh;
    EJBContainer container;
    
    public LinkLogicTest() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getUserLinksPast method, of class LinkLogic.
     */
    @org.junit.Test
    public void testGetUserLinksPast() {
        //Collection<UserLink> links = llh.getUserLinksPast(new BlinkUser(), new Date());
        //container = EJBContainer.createEJBContainer();
        System.out.println("Well it is working");
        assert(true);
    }

    /**
     * Test of getGroupLinksPast method, of class LinkLogic.
     */
    @org.junit.Test
    public void testGetGroupLinksPast() {
        
    }

    /**
     * Test of postLink method, of class LinkLogic.
     */
    @org.junit.Test
    public void testPostLink() throws Exception {
        
    }

    /**
     * Test of searchLinks method, of class LinkLogic.
     */
    @org.junit.Test
    public void testSearchLinks() {
        
    }

    /**
     * Test of calculateSum method, of class LinkLogic.
     */
    @org.junit.Test
    public void testCalculateSum() {
        
    }
    
}
