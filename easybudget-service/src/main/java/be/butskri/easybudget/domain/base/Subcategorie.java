package be.butskri.easybudget.domain.base;

import be.butskri.commons.domain.DomainObject;

public interface Subcategorie extends DomainObject {

	String getOmschrijving();
	
	Categorie getCategorie();
	
}
