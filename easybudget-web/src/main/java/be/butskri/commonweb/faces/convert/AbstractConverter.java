package be.butskri.commonweb.faces.convert;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import org.apache.commons.lang.StringUtils;

import be.butskri.commons.context.Locator;
import be.butskri.commons.exception.ButskriRuntimeException;
import be.butskri.commonweb.faces.util.FacesMessageFactory;

public class AbstractConverter implements Converter {

	static final String IGNORE_REQUIREDNESS = "ignoreRequiredness";
	static final String REQUIRED = "fieldRequired";
	static final String REQUIRED_MESSAGE_KEY = "javax.faces.component.UIInput.REQUIRED";
	private SimpleConverter converter;

	protected void setSimpleConverter(SimpleConverter converter) {
		this.converter = converter;
	}

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String stringValue) {
		Object result = null;
		if (StringUtils.isNotBlank(stringValue)) {
			try {
				result = converter.stringToObject(stringValue);
			} catch (ButskriRuntimeException e) {
				FacesMessage errorMessage = Locator.locate(FacesMessageFactory.class)
						.createErrorMessage(uiComponent, e);
				throw new ConverterException(errorMessage, e);
			}
		}
		return result;
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object objectValue) {
		String result = null;
		if (objectValue == null) {
			result = converter.getNullString();
		} else {
			result = converter.objectToString(objectValue);
		}
		return result;
	}

	public String getNullString() {
		return "";
	}

}
