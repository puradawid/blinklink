package pl.edu.pb.blinklink.webservice.named;

import java.util.Iterator;

import javax.faces.FacesException;
import javax.faces.application.NavigationHandler;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

public class OtherExceptionsHandler extends ExceptionHandlerWrapper {

	private ExceptionHandler wrapped;

	public OtherExceptionsHandler(ExceptionHandler wrapped) {
		this.wrapped = wrapped;
	}

	@Override
	public ExceptionHandler getWrapped() {
		return wrapped;
	}

	@Override
	public void handle() throws FacesException {
		Iterator iterator = getUnhandledExceptionQueuedEvents().iterator();

		while (iterator.hasNext()) {
			ExceptionQueuedEvent event = (ExceptionQueuedEvent) iterator.next();
			ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event
					.getSource();

			Throwable throwable = context.getException();

			FacesContext fc = FacesContext.getCurrentInstance();

			try {
				Flash flash = fc.getExternalContext().getFlash();

				flash.put("errorDetails", throwable.getMessage());

				System.out.println("the error is put in the flash: "
						+ throwable.getMessage());

				NavigationHandler navigationHandler = fc.getApplication()
						.getNavigationHandler();

				fc.renderResponse();
			} finally {
				iterator.remove();
			}
		}

		// Let the parent handle the rest
		getWrapped().handle();
	}

}
