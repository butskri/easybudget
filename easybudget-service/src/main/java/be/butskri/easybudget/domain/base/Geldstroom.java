package be.butskri.easybudget.domain.base;

import java.util.Date;

import be.butskri.commons.domain.DomainObject;
import be.butskri.commons.types.Bedrag;

public interface Geldstroom extends DomainObject {

	Date getDatum();
	Bedrag getBedrag();
	Categorie getCategorie();

}
