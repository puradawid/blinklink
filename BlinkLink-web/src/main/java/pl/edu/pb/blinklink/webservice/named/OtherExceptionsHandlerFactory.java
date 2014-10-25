package pl.edu.pb.blinklink.webservice.named;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

public class OtherExceptionsHandlerFactory extends ExceptionHandlerFactory {

	private ExceptionHandlerFactory parent;
	
	public OtherExceptionsHandlerFactory(ExceptionHandlerFactory parent) {
		this.parent = parent;
	}
	
	@Override
	public ExceptionHandler getExceptionHandler() {
		ExceptionHandler result = new OtherExceptionsHandler(parent.getExceptionHandler());
	    return result;
	}

	
}
