package be.butskri.commonweb.faces.util;

import java.util.Locale;

import javax.faces.context.FacesContext;

import be.butskri.commons.util.LocaleResolver;

public class FacesLocaleResolver implements LocaleResolver {

	public void setLocale(Locale locale) {
		FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
	}

	public Locale getLocale() {
		return FacesContext.getCurrentInstance().getViewRoot().getLocale();
	}

}
