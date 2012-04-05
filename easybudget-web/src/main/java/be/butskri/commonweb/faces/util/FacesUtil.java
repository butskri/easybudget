package be.butskri.commonweb.faces.util;

import javax.faces.component.UIComponent;

public class FacesUtil {

	public String getLabel(UIComponent uiComponent) {
		return (String) uiComponent.getAttributes().get("label");
	}

}
