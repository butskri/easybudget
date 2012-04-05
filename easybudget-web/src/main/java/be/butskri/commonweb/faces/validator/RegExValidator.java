package be.butskri.commonweb.faces.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.StateHolder;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import be.butskri.commons.context.Locator;
import be.butskri.commonweb.faces.util.FacesMessageFactory;

public class RegExValidator implements Validator, StateHolder {

	private boolean transientValue;
	private RegExPattern pattern;

	public String getPattern() {
		return pattern.name();
	}

	public void setPattern(String pattern) {
		this.pattern = RegExPattern.valueOf(pattern);
	}

	public void validate(FacesContext facesContext, UIComponent component, Object value) throws ValidatorException {
		String stringValue = (String) value;
		if (!pattern.allows(stringValue)) {
			FacesMessage errorMessage = Locator.locate(FacesMessageFactory.class).createErrorMessage(component,
					pattern.getMessage(), stringValue);
			throw new ValidatorException(errorMessage);
		}

	}

	public void setTransient(boolean transientValue) {
		this.transientValue = transientValue;
	}

	public boolean isTransient() {
		return transientValue;
	}

	public void restoreState(FacesContext facesContext, Object state) {
		setPattern((String) state);
	}

	public Object saveState(FacesContext facesContext) {
		return pattern.name();
	}
}
