package be.butskri.web.easybudget;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.faces.model.SelectItem;

import be.butskri.commons.types.Bedrag;
import be.butskri.commons.types.Identifier;
import be.butskri.easybudget.facade.algemeen.CategorieTo;
import be.butskri.easybudget.facade.algemeen.GeldstroomTo;
import be.butskri.easybudget.facade.algemeen.SubcategorieTo;

public class GeldstroomBean implements Serializable {

	private Date datum;
	private Bedrag bedrag;
	private Identifier categorieId;
	private Identifier subcategorieId;
	private List<SelectItem> categorieList = Arrays.asList(new SelectItem(new Identifier(1l), "eersteCat"),
			new SelectItem(new Identifier(2l), "tweedeCat"));
	private List<SelectItem> subcategorieList = Arrays.asList(new SelectItem(new Identifier(5l), "subCat1"),
			new SelectItem(new Identifier(6l), "subCat2"));

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public Bedrag getBedrag() {
		return bedrag;
	}

	public void setBedrag(Bedrag bedrag) {
		this.bedrag = bedrag;
	}

	public Identifier getCategorieId() {
		return categorieId;
	}

	public void setCategorieId(Identifier categorieId) {
		this.categorieId = categorieId;
	}

	public Identifier getSubcategorieId() {
		return subcategorieId;
	}

	public void setSubcategorieId(Identifier subcategorieId) {
		this.subcategorieId = subcategorieId;
	}

	public List<SelectItem> getCategorieList() {
		return categorieList;
	}

	public void setCategorieList(List<SelectItem> categorieList) {
		this.categorieList = categorieList;
	}

	public List<SelectItem> getSubcategorieList() {
		return subcategorieList;
	}

	public void setSubcategorieList(List<SelectItem> subcategorieList) {
		this.subcategorieList = subcategorieList;
	}

	public GeldstroomTo getGeldstroomTo() {
		GeldstroomTo result = new GeldstroomTo();
		result.setBedrag(getBedrag());
		result.setCategorie(new CategorieTo(categorieId));
		result.setSubcategorie(new SubcategorieTo(subcategorieId));
		result.setDatum(datum);

		return result;
	}

}
