package be.butskri.commonsweb.faces.convert;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.jsf.FacesContextUtils;

import be.butskri.commons.types.Identifier;
import be.butskri.easybudget.domain.uitgaven.UitgaveCategorie;
import be.butskri.easybudget.domain.uitgaven.UitgaveCategorieRepository;

public class UitgaveCategorieConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext facesContext,
			UIComponent uiComponent, String value) {
		if (StringUtils.isBlank(value)) {
			return null;
		}
		UitgaveCategorieRepository uitgaveCategorieRepository = (UitgaveCategorieRepository) FacesContextUtils
				.getWebApplicationContext(FacesContext.getCurrentInstance())
				.getBean("uitgaveCategorieRepository");
		return uitgaveCategorieRepository.lookUpById(new Identifier(value));
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object objectValue) {
		if (objectValue == null) {
			return "";
		}
		UitgaveCategorie uitgaveCategorie = (UitgaveCategorie) objectValue;
		return String.valueOf(uitgaveCategorie.getId());
	}

}
