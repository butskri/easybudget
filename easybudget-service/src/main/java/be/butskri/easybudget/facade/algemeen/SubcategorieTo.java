package be.butskri.easybudget.facade.algemeen;

import be.butskri.commons.types.Identifier;

public class SubcategorieTo extends IdentifiableTo {

	private String omschrijving;
	private CategorieTo categorie = new CategorieTo();

	public SubcategorieTo() {
	}

	public SubcategorieTo(Long id) {
		super(id);
	}

	public SubcategorieTo(Identifier id) {
		super(id);
	}

	public String getOmschrijving() {
		return omschrijving;
	}

	public void setOmschrijving(String omschrijving) {
		this.omschrijving = omschrijving;
	}

	public CategorieTo getCategorie() {
		return categorie;
	}

	public void setCategorie(CategorieTo categorie) {
		this.categorie = categorie;
	}

}
