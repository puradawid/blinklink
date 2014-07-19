package pl.edu.pb.blinklink.model.logic;

import java.util.Collection;
import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import pl.edu.pb.blinklink.model.BlinkUser;
import pl.edu.pb.blinklink.model.GroupLink;
import pl.edu.pb.blinklink.model.Link;
import pl.edu.pb.blinklink.model.UserLink;
import pl.edu.pb.blinklink.model.beans.BlinkGroupDao;
import pl.edu.pb.blinklink.model.beans.GroupLinkDao;
import pl.edu.pb.blinklink.model.beans.LinkDao;
import pl.edu.pb.blinklink.model.beans.mock.BlinkGroupDaoMock;
import pl.edu.pb.blinklink.model.beans.mock.GroupLinkDaoMock;
import pl.edu.pb.blinklink.model.beans.mock.LinkDaoMock;
import pl.edu.pb.blinklink.model.logic.exceptions.PostingLinkException;
import pl.edu.pb.blinklink.model.logic.impl.LinkLogicHibernate;

public class LinkLogicTest {

	@InjectMocks
	LinkLogic linkLogic = new LinkLogicHibernate();

	@Mock
	GroupLinkDao gld = new GroupLinkDaoMock(); 
	
	@Mock
	BlinkGroupDao bgd = new BlinkGroupDaoMock();
	
	@Mock
	LinkDao ld = new LinkDaoMock();
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testIsOkay() {
		assert linkLogic != null;
	}
	
	@Test
	public void testNullAnswer() {
		Collection<GroupLink> result = linkLogic.getGroupLinksPast(null, null);
		assert result.isEmpty();
	}
	
	Link l = new Link("http://google.com");
	BlinkUser bu = new BlinkUser("user@gmail.com", "password");
	UserLink ul = new UserLink(bu, l);
	
	@Test
	public void postLinkTest() {
		try {
			linkLogic.postLink(bu, ul);
		} catch (PostingLinkException ex)
		{
			Assert.assertTrue(false);
		}
	}
	
	//@Test
	//TODO: repiar this test and impl.
	public void postLinkAndCatchTest() {
		postLinkTest();
		Collection<GroupLink> result = linkLogic.getGroupLinksPast(bu, new Date(Date.UTC(2000, 1, 1, 0, 0, 0)));
		Assert.assertFalse(result.isEmpty());
	}
}
