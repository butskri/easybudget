package be.butskri.easybudget.facade.algemeen;

import be.butskri.commons.types.Identifier;

public class CategorieTo extends IdentifiableTo {

	private String omschrijving;

	public CategorieTo() {
	}

	public CategorieTo(Identifier categorieId) {
		super(categorieId);
	}

	public CategorieTo(Long categorieId) {
		super(categorieId);
	}

	public String getOmschrijving() {
		return omschrijving;
	}

	public void setOmschrijving(String omschrijving) {
		this.omschrijving = omschrijving;
	}

}
