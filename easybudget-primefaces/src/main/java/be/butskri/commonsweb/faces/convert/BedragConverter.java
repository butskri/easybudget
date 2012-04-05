package be.butskri.commonsweb.faces.convert;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.apache.commons.lang.StringUtils;

import be.butskri.commons.types.Bedrag;

public class BedragConverter implements Converter {
	
	@Override
	public Bedrag getAsObject(FacesContext facesContext, UIComponent uiComponent, String stringValue) {
		Bedrag result = null;
		if (!StringUtils.isEmpty(stringValue)) {
			result = new Bedrag(stringValue);
		}
		return result;
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
		String result = "";
		if (value != null) {
			result = ((Bedrag) value).format();
		}
		return result;
	}
}
