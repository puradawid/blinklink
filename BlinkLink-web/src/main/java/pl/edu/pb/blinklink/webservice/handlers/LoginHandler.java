package pl.edu.pb.blinklink.webservice.handlers;

import java.util.Iterator;
import java.util.Set;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPHeaderElement;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

import pl.edu.pb.blinklink.model.logic.UserLogic;

/**
 *
 * @author dawid
 */
public class LoginHandler implements SOAPHandler<SOAPMessageContext> {

    @Override
    public Set<QName> getHeaders() {
        return null;
    }
    
    @Resource
    WebServiceContext wsctx;
    
    @EJB(beanName = "UserLogicHibernate")
    UserLogic ul;

    @Override
    public boolean handleMessage(SOAPMessageContext c) {
        if((Boolean)c.get(SOAPMessageContext.MESSAGE_OUTBOUND_PROPERTY))
            return true;
        SOAPMessage m = c.getMessage();
        SOAPHeader h = null;
        String username = null, password = null;
        try {
            h = m.getSOAPHeader();
            SOAPHeaderElement he = null;
            for(Iterator i = h.examineAllHeaderElements(); i.hasNext();)
            {
                he = (SOAPHeaderElement)i.next();
                if(he.getElementName().getLocalName().equals("username"))
                    username = he.getTextContent();
                if(he.getElementName().getLocalName().equals("password"))
                    password = he.getTextContent();
            }
            if(username != null && password != null)
            {
                c.put("credencials", ul.login(username, password));
                c.setScope("credencials", MessageContext.Scope.APPLICATION);
            }
            
        } catch (SOAPException e)
        {
            
        }
        return true;
    }

    @Override
    public boolean handleFault(SOAPMessageContext c) {
        return false;
    }

    @Override
    public void close(MessageContext mc) {
    }

    
}
