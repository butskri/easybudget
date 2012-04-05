package be.butskri.commonweb.faces.util;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;

import be.butskri.commons.exception.ButskriRuntimeException;
import be.butskri.commonweb.util.MessageUtil;

public class FacesMessageFactory {

	private MessageUtil messageUtil;
	private FacesUtil facesUtil;

	public void setMessageUtil(MessageUtil messageUtil) {
		this.messageUtil = messageUtil;
	}

	public void setFacesUtil(FacesUtil facesUtil) {
		this.facesUtil = facesUtil;
	}

	public FacesMessage createErrorMessage(UIComponent uiComponent, ButskriRuntimeException exception) {
		return createErrorMessage(uiComponent, exception.getErrorKey(), exception.getParameters());
	}

	public FacesMessage createErrorMessage(UIComponent uiComponent, String messageKey, Object... parameters) {
		FacesMessage message = new FacesMessage();
		String label = facesUtil.getLabel(uiComponent);
		message.setSeverity(FacesMessage.SEVERITY_ERROR);
		message.setDetail(messageUtil.getDetailMessage(messageKey, label, parameters));
		message.setSummary(messageUtil.getSummaryMessage(messageKey, label, parameters));
		return message;
	}

}
