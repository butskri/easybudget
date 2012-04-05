package be.butskri.web.easybudget;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.event.ActionEvent;

import be.butskri.commons.types.Bedrag;
import be.butskri.easybudget.domain.uitgaven.Uitgave;
import be.butskri.easybudget.domain.uitgaven.UitgaveCategorie;
import be.butskri.easybudget.facade.uitgaven.UitgaveFacade;

public class UitgaveBean implements Serializable {

	private static final long serialVersionUID = -6730578716490525771L;

	private UitgaveFacade uitgaveFacade;

	private Date datum;
	private Bedrag bedrag;
	private UitgaveCategorie categorie;
	private UitgaveCategorie hoofdcategorie;
	private String subcategorieOmschrijving;
	private boolean showNieuweCategorieDialog;

	public Bedrag getBedrag() {
		return bedrag;
	}

	public UitgaveCategorie getCategorie() {
		return categorie;
	}

	public Date getDatum() {
		return datum;
	}

	public UitgaveCategorie getHoofdcategorie() {
		return hoofdcategorie;
	}

	public String getSubcategorieOmschrijving() {
		return subcategorieOmschrijving;
	}

	public boolean isShowNieuweCategorieDialog() {
		return showNieuweCategorieDialog;
	}

	public void saveCategorie(ActionEvent actionEvent) {
		UitgaveCategorie subcategorie = new UitgaveCategorie(hoofdcategorie,
				subcategorieOmschrijving);
		categorie = uitgaveFacade.save(subcategorie);
		showNieuweCategorieDialog = false;
	}

	public void saveUitgave(ActionEvent actionEvent) {
		Uitgave uitgave = new Uitgave(datum, bedrag, categorie);
		uitgaveFacade.save(uitgave);
	}

	public void setBedrag(Bedrag bedrag) {
		this.bedrag = bedrag;
	}

	public void setCategorie(UitgaveCategorie categorie) {
		this.categorie = categorie;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public void setHoofdcategorie(UitgaveCategorie hoofdcategorie) {
		this.hoofdcategorie = hoofdcategorie;
	}

	public void setSubcategorieOmschrijving(String subcategorieOmschrijving) {
		this.subcategorieOmschrijving = subcategorieOmschrijving;
	}

	public void setUitgaveFacade(UitgaveFacade uitgaveFacade) {
		this.uitgaveFacade = uitgaveFacade;
	}

	public void showNieuweCategorie() {
		hoofdcategorie = categorie;
		showNieuweCategorieDialog = true;
	}

	public List<UitgaveCategorie> zoekCategorie(String omschrijving) {
		return uitgaveFacade.findCategorien(omschrijving);
	}

}
