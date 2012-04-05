package be.butskri.easybudget.facade.algemeen;

import java.util.Date;

import be.butskri.commons.types.Bedrag;

public class GeldstroomCriteria {

	private String categorieId;
	private String subcategorieId;
	private Date minDatum;
	private Date maxDatum;
	private Bedrag minBedrag;
	private Bedrag maxBedrag;

	public String getCategorieId() {
		return categorieId;
	}

	public void setCategorieId(String categorieId) {
		this.categorieId = categorieId;
	}

	public String getSubcategorieId() {
		return subcategorieId;
	}

	public void setSubcategorieId(String subcategorieId) {
		this.subcategorieId = subcategorieId;
	}

	public Date getMinDatum() {
		return minDatum;
	}

	public void setMinDatum(Date minDatum) {
		this.minDatum = minDatum;
	}

	public Date getMaxDatum() {
		return maxDatum;
	}

	public void setMaxDatum(Date maxDatum) {
		this.maxDatum = maxDatum;
	}

	public Bedrag getMinBedrag() {
		return minBedrag;
	}

	public void setMinBedrag(Bedrag minBedrag) {
		this.minBedrag = minBedrag;
	}

	public Bedrag getMaxBedrag() {
		return maxBedrag;
	}

	public void setMaxBedrag(Bedrag maxBedrag) {
		this.maxBedrag = maxBedrag;
	}

}
