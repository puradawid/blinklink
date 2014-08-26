package pl.edu.pb.blinklink.model.logic;

import java.util.Collection;

import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import pl.edu.pb.blinklink.model.BlinkGroup;
import pl.edu.pb.blinklink.model.BlinkUser;
import pl.edu.pb.blinklink.model.GroupLink;
import pl.edu.pb.blinklink.model.Link;
import pl.edu.pb.blinklink.model.UserLink;
import pl.edu.pb.blinklink.model.beans.BlinkGroupDao;
import pl.edu.pb.blinklink.model.beans.GroupLinkDao;
import pl.edu.pb.blinklink.model.beans.LinkDao;
import pl.edu.pb.blinklink.model.beans.UserLinkDao;
import pl.edu.pb.blinklink.model.beans.mock.BlinkGroupDaoMock;
import pl.edu.pb.blinklink.model.beans.mock.GroupLinkDaoMock;
import pl.edu.pb.blinklink.model.beans.mock.LinkDaoMock;
import pl.edu.pb.blinklink.model.beans.mock.UserLinkDaoMock;
import pl.edu.pb.blinklink.model.logic.exceptions.PostingLinkException;
import pl.edu.pb.blinklink.model.logic.impl.LinkLogicHibernate;

public class LinkLogicTest {

	@InjectMocks
	LinkLogic cut = new LinkLogicHibernate();

	@Spy
	GroupLinkDao gld = new GroupLinkDaoMock();

	@Spy
	BlinkGroupDao bgd = new BlinkGroupDaoMock();

	@Spy
	LinkDao ld = new LinkDaoMock();

	@Spy
	UserLinkDao uld = new UserLinkDaoMock();

	Link l;
	BlinkUser bu;
	BlinkGroup bg;
	UserLink ul;
	GroupLink gl;

	String linkReferer = "http://google.com";
	String groupLinkReferer = "http://google.group.com";
	
	DateTime oldDateTime = new DateTime(2013, 1, 1, 0, 0); 

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		l = new Link(linkReferer);
		bu = new BlinkUser("user@gmail.com", "password");
		ul = new UserLink(bu, l);
		bg = new BlinkGroup();
		bg.getRegistered().add(bu);
		bg.setName("myAwesomeGroup");

		bgd.create(bg);//magically add group
		gl = new GroupLink();
		gl.setLink(new Link(groupLinkReferer));
		gl.setGroup(bg);
	}

	@Test
	public void testIsOkay() {
		assert cut != null;
	}

	@Test
	public void testAbstractDataStorage() {
		ld.create(new Link("http://example.host.com"));
		Assert.assertEquals(ld.findAll().size(), 1);
	}

	@Test
	public void testNullAnswer() {
		Collection<GroupLink> result = cut.getGroupLinksPast(null, null);
		assert result.isEmpty();
	}

	@Test
	public void postUserLinkTest() {
		ul.setOwner(bu);
		try {
			cut.postLink(bu, ul);
		} catch (PostingLinkException ex) {
			Assert.assertTrue(false);
		}
	}

	@Test
	public void postGroupLinkTest() {
		try {
			cut.postLink(bu, gl, gl.getGroup().getName());
		} catch (PostingLinkException e) {
			Assert.assertFalse(false);
		}
	}
	
	@Test
	public void postGroupLinkAndReturnTest() {
		postGroupLinkTest();
		Collection<GroupLink> result = cut.getGroupLinksPast(bu, oldDateTime.toDate());
		Assert.assertFalse(result.isEmpty());
	}

	@Test
	public void postLinkAndCatchTest() {
		postUserLinkTest();
		Collection<UserLink> result = cut.getUserLinksPast(bu, oldDateTime.toDate());
		Assert.assertFalse(result.isEmpty());
	}

}
