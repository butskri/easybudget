package be.butskri.commonweb.util;

import java.util.List;

import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import be.butskri.commons.util.LanguageBean;

import com.google.common.collect.Lists;

public class MessageUtil {

	private LanguageBean languageBean;
	private MessageSource messageSource;

	public void setLanguageBean(LanguageBean languageBean) {
		this.languageBean = languageBean;
	}

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	public String getDetailMessage(String messageKey, String label, Object... params) {
		return getMessageUsingSuffix(messageKey, "_detail", label, params);
	}

	public String getSummaryMessage(String messageKey, String label, Object... params) {
		return getMessageUsingSuffix(messageKey, "_summary", label, params);
	}

	public String getMessageMetLabel(String messageKey, String label, Object... params) {
		Object[] paramArray = bepaalParamArray(label, params);
		return messageSource.getMessage(messageKey, paramArray, languageBean.getLocale());
	}

	public String getMessage(String messageKey, Object... params) {
		return messageSource.getMessage(messageKey, params, languageBean.getLocale());
	}

	private boolean messageExists(String messageKey) {
		boolean result = true;
		try {
			getMessage(messageKey, (Object[]) null);
		} catch (NoSuchMessageException exception) {
			result = false;
		}
		return result;
	}

	private String getMessageUsingSuffix(String messageKey, String suffix, String label, Object... params) {
		String result = null;
		String messageKeyWithSuffix = messageKey + suffix;
		if (messageExists(messageKeyWithSuffix)) {
			result = getMessageMetLabel(messageKeyWithSuffix, label, params);
		} else {
			result = getMessageMetLabel(messageKey, label, params);
		}
		return result;
	}

	private Object[] bepaalParamArray(String label, Object... params) {
		Object[] paramArray;
		if (params != null) {
			List<Object> list = Lists.newArrayList(params);
			list.add(0, label);
			paramArray = list.toArray();
		} else {
			paramArray = new Object[] { label };
		}
		return paramArray;
	}

}
