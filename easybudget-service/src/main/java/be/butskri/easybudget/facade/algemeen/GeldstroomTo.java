package be.butskri.easybudget.facade.algemeen;

import java.util.Date;

import be.butskri.commons.types.Bedrag;

public class GeldstroomTo extends IdentifiableTo {

	private Date datum;
	private Bedrag bedrag;
	private CategorieTo categorie;
	private SubcategorieTo subcategorie;

	public GeldstroomTo() {
	}

	public GeldstroomTo(Long id) {
		super(id);
	}

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public CategorieTo getCategorie() {
		return categorie;
	}

	public void setCategorie(CategorieTo categorie) {
		this.categorie = categorie;
	}

	public SubcategorieTo getSubcategorie() {
		return subcategorie;
	}

	public void setSubcategorie(SubcategorieTo subcategorie) {
		this.subcategorie = subcategorie;
	}

	public Bedrag getBedrag() {
		return bedrag;
	}

	public void setBedrag(Bedrag bedrag) {
		this.bedrag = bedrag;
	}

}
