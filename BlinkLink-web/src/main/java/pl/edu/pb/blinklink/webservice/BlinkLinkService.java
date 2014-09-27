package pl.edu.pb.blinklink.webservice;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.logging.Logger;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

import pl.edu.pb.blinklink.model.BlinkGroup;
import pl.edu.pb.blinklink.model.BlinkUser;
import pl.edu.pb.blinklink.model.GroupLink;
import pl.edu.pb.blinklink.model.Link;
import pl.edu.pb.blinklink.model.UserLink;
import pl.edu.pb.blinklink.model.beans.BlinkUserFacade;
import pl.edu.pb.blinklink.model.beans.BlinkUserFacade.UserDoesntExists;
import pl.edu.pb.blinklink.model.logic.GroupLogic;
import pl.edu.pb.blinklink.model.logic.LinkLogic;
import pl.edu.pb.blinklink.model.logic.UserLogic;
import pl.edu.pb.blinklink.model.logic.exceptions.PostingLinkException;
import pl.edu.pb.blinklink.model.logic.exceptions.UserAlreadyRegisteredException;
import pl.edu.pb.blinklink.webservice.model.BlinkUserWebservice;
import pl.edu.pb.blinklink.webservice.model.UserLinkWebservice;

@WebService(serviceName = "BlinkLinkService", targetNamespace = "http://webservice.blinklink.pb.edu.pl/")
@HandlerChain(file = "login-handler.xml")
public class BlinkLinkService {

	private static Logger logger = Logger.getLogger(BlinkLinkService.class
			.getName());

	@Resource
	WebServiceContext wsctx;

	@EJB
	BlinkUserFacade buf;

	@EJB(beanName = "LinkLogicHibernate")
	LinkLogic ll;

	@EJB(beanName = "GroupLogicHibernate")
	GroupLogic gl;

	@EJB(beanName = "UserLogicHibernate")
	UserLogic ul;

	@WebMethod(operationName = "postLink")
	public String postLink(
			@WebParam(name = "referer", targetNamespace = "http://webservice.blinklink.pb.edu.pl/") String referer,
			@WebParam(name = "targets", targetNamespace = "http://webservice.blinklink.pb.edu.pl/") Collection<String> targets,
			@WebParam(name = "description", targetNamespace = "http://webservice.blinklink.pb.edu.pl/") String description) {
		if (checkCredencials()) {
			for (String target : targets) {
				if (target.startsWith("@"))
					try {
						BlinkUser targetUser = buf.getUserByEmail(target
								.substring(1));
						UserLink l = new UserLink(targetUser, getLogin(),
								new Link(referer));
						l.setDescription(description);
						ll.postLink(targetUser, l);
					} catch (UserDoesntExists e) {
						continue; // skip this entry
					} catch (PostingLinkException e) {
						Logger.getLogger(getClass().getName()).info(
								"Unathorized user");
					}
				else if (target.startsWith("#")) {
					try {
						GroupLink l = new GroupLink(getLogin(), new Link(
								referer), description);
						ll.postLink(getLogin(), l, target.substring((1)));
					} catch (PostingLinkException e) {
						Logger.getLogger(getClass().getName()).info(
								"Unathorized user");
					}
				}
			}
			logger.info("User fetch data");
			return "OK!";
		} else {
			logger.warning("User not logged in!");
			return "You are not logged in!";
		}
	}

	@WebMethod(operationName = "getLinksSince")
	public Collection<UserLinkWebservice> getLinksSince(
			@WebParam(name = "since") Date since) {
		BlinkUser user = getLogin();
		if (user == null) {
			throw new RuntimeException("No logged user");
		}
		Collection<UserLink> lst = ll.getUserLinksPast(user, since);
		Collection<UserLinkWebservice> links = new LinkedList<UserLinkWebservice>();
		for (UserLink ul : lst)
			links.add(new UserLinkWebservice(ul));
		Collection<GroupLink> gls = ll.getGroupLinksPast(user, since);
		for (GroupLink gl : gls)
			links.add(new UserLinkWebservice(gl));
		return links;
	}

	private boolean checkCredencials() {
		return getLogin() != null;
	}

	private BlinkUser getLogin() {
		return (BlinkUser) wsctx.getMessageContext().get("credencials");
	}
}
