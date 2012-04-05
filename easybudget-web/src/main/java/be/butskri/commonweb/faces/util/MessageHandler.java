package be.butskri.commonweb.faces.util;

import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageResolver;
import org.springframework.binding.message.Severity;
import org.springframework.webflow.execution.RequestContextHolder;

public class MessageHandler {

	static final String JSF_FORM_PREFIX = "mainForm:";

	public void addInfo(String msgKey, Object... msgParams) {
		addMessage(Severity.INFO, null, msgKey, msgParams);
	}

	public void addWarning(String msgKey, Object... msgParams) {
		addMessage(Severity.WARNING, null, msgKey, msgParams);
	}

	public void addError(String msgKey, Object... msgParams) {
		addMessage(Severity.ERROR, null, msgKey, msgParams);
	}

	public void addErrorForComponent(String uiComponentId, String msgKey, Object... msgParams) {
		addMessage(Severity.ERROR, uiComponentId, msgKey, msgParams);
	}

	private void addMessage(Severity severity, String uiComponentId, String msgKey, Object[] msgParams) {
		RequestContextHolder.getRequestContext().getMessageContext().addMessage(
				createMessageResolver(severity, uiComponentId, msgKey, msgParams));
	}

	private MessageResolver createMessageResolver(Severity severity, String uiComponentId, String msgKey,
			Object[] msgParams) {
		MessageBuilder builder = getMessageBuilder(severity);
		if (uiComponentId != null) {
			builder = builder.source(JSF_FORM_PREFIX + uiComponentId);
		}
		return builder.code(msgKey).resolvableArgs(msgParams).build();
	}

	private MessageBuilder getMessageBuilder(Severity severity) {
		MessageBuilder builder = new MessageBuilder();
		if (Severity.ERROR == severity) {
			builder = builder.error();
		} else if (Severity.WARNING == severity) {
			builder = builder.warning();
		}
		return builder;
	}

}
