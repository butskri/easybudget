package be.butskri.commonweb.util;

import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

public class ActionBean {

	public String getActionFromRequest() {
		HttpServletRequest req = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
				.getRequest();
		return req.getAttribute("action").toString();
	}

}
