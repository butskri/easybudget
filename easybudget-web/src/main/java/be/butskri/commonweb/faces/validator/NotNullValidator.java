package be.butskri.commonweb.faces.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.apache.commons.lang.StringUtils;

import be.butskri.commons.context.Locator;
import be.butskri.commonweb.faces.util.FacesMessageFactory;

public class NotNullValidator implements Validator {

	static final String IGNORE_REQUIREDNESS = "ignoreRequiredness";
	static final String REQUIRED = "fieldRequired";
	static final String REQUIRED_MESSAGE_KEY = "javax.faces.component.UIInput.REQUIRED";

	public NotNullValidator() {
		System.out.println("creating NotNullValidator...");
	}

	@Override
	public void validate(FacesContext facesContext, UIComponent uiComponent, Object obj) throws ValidatorException {
		if (obj == null && isValueRequired(facesContext, uiComponent)) {
			FacesMessage errorMessage = Locator.locate(FacesMessageFactory.class).createErrorMessage(uiComponent,
					REQUIRED_MESSAGE_KEY);
			throw new ValidatorException(errorMessage);
		}
	}

	protected boolean isValueRequired(FacesContext facesContext, UIComponent uiComponent) {
		return !ignoreRequiredness(facesContext) && isRequired(uiComponent);
	}

	private boolean ignoreRequiredness(FacesContext facesContext) {
		boolean result = false;
		String stringValue = facesContext.getExternalContext().getRequestParameterMap().get(IGNORE_REQUIREDNESS);
		if (!StringUtils.isEmpty(stringValue)) {
			result = Boolean.parseBoolean(stringValue);
		}
		return result;
	}

	private boolean isRequired(UIComponent uiComponent) {
		return isTrue(uiComponent.getAttributes().get(REQUIRED));
	}

	private boolean isTrue(Object obj) {
		boolean result = false;

		if (obj instanceof Boolean) {
			result = (Boolean) obj;
		} else {
			String stringValue = (String) obj;
			if (!StringUtils.isEmpty(stringValue)) {
				result = Boolean.parseBoolean(stringValue);
			}
		}

		return result;
	}

}
