package be.butskri.commonweb.faces.functions;

import javax.faces.context.FacesContext;

import org.apache.commons.lang.StringUtils;

public class Functions {

	static final String IGNORE_REQUIREDNESS = "ignoreRequiredness";
	static final String REQUIRED = "fieldRequired";
	static final String REQUIRED_MESSAGE_KEY = "javax.faces.component.UIInput.REQUIRED";

	public static boolean fieldRequired(Object required) {
		return !ignoreRequiredness() && isTrue(required);
	}

	private static boolean ignoreRequiredness() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		boolean result = false;
		String stringValue = facesContext.getExternalContext().getRequestParameterMap().get(IGNORE_REQUIREDNESS);
		if (!StringUtils.isEmpty(stringValue)) {
			result = Boolean.parseBoolean(stringValue);
		}
		return result;
	}

	private static boolean isTrue(Object obj) {
		boolean result = false;

		if (obj == null) {
			result = false;
		} else if (obj instanceof Boolean) {
			result = (Boolean) obj;
		} else if (obj instanceof String) {
			String stringValue = (String) obj;
			if (!StringUtils.isEmpty(stringValue)) {
				result = Boolean.parseBoolean(stringValue);
			}
		}

		return result;
	}

}
