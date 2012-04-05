package be.butskri.commonsweb.faces.advice;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.aspectj.lang.ProceedingJoinPoint;

import be.butskri.commons.exception.ButskriRuntimeException;

public class FacesExceptionHandler {

	private void addErrorMessage(String errorKey) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Locale locale = facesContext.getViewRoot().getLocale();
		ResourceBundle bundle = ResourceBundle.getBundle(
				"be.butskri.web.easybudget.messages", locale);

		String message = bundle.getString(errorKey);
		facesContext.addMessage(null, new FacesMessage(
				FacesMessage.SEVERITY_ERROR, message, message));
	}

	public Object execute(ProceedingJoinPoint joinpoint) throws Throwable {
		try {
			Object result = joinpoint.proceed();
			return result;
		} catch (ButskriRuntimeException e) {
			addErrorMessage(e.getErrorKey());
			return null;
		} catch (RuntimeException e) {
			addErrorMessage("error.general");
			return null;
		}
	}
}
