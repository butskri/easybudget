package be.butskri.commonweb.faces.convert;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import be.butskri.commons.context.Locator;
import be.butskri.commons.util.EnumTranslator;

public class EnumTranslationConverter implements Converter {

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		throw new UnsupportedOperationException();
	}

	@SuppressWarnings("unchecked")
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object enumValue) {
		return Locator.locate(EnumTranslator.class).translate((Enum<?>) enumValue);
	}
}
