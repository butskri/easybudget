package be.butskri.commonweb.faces.convert;

import javax.faces.application.FacesMessage;
import javax.faces.convert.ConverterException;

import be.butskri.commons.exception.ButskriRuntimeException;
import be.butskri.commonweb.util.MessageUtil;

// TODO weghalen??
public class ConverterExceptionFactory {

	private MessageUtil messageUtil;

	public void setMessageUtil(MessageUtil messageUtil) {
		this.messageUtil = messageUtil;
	}

	public ConverterException create(String label, ButskriRuntimeException exception) {
		String detail = messageUtil.getDetailMessage(exception.getErrorKey(), label, exception.getParameters());
		String summary = messageUtil.getSummaryMessage(exception.getErrorKey(), label, exception.getParameters());
		FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, summary, detail);

		return new ConverterException(facesMessage, exception);
	}

}
