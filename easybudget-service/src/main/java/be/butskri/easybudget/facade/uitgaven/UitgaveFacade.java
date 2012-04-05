package be.butskri.easybudget.facade.uitgaven;

import java.util.List;

import be.butskri.commons.types.Identifier;
import be.butskri.easybudget.domain.uitgaven.Uitgave;
import be.butskri.easybudget.domain.uitgaven.UitgaveCategorie;
import be.butskri.easybudget.facade.algemeen.CategorieTo;
import be.butskri.easybudget.facade.algemeen.GeldstroomCriteria;
import be.butskri.easybudget.facade.algemeen.GeldstroomTo;
import be.butskri.easybudget.facade.algemeen.SubcategorieTo;

public interface UitgaveFacade {
	
	Uitgave save(Uitgave uitgave);
	
	UitgaveCategorie save(UitgaveCategorie uitgaveCategorie);

	List<UitgaveCategorie> findCategorien(String omschrijving);
	
	
	
	CategorieTo createCategorie(CategorieTo categorie);

	void removeCategorie(CategorieTo categorie);

	SubcategorieTo createSubcategorie(SubcategorieTo subcategorie);

	GeldstroomTo createUitgave(GeldstroomTo uitgave);

	List<CategorieTo> getAlleCategorien();

	List<SubcategorieTo> getSubCategorien(Identifier categorieId);

	List<GeldstroomTo> findUitgaven(GeldstroomCriteria criteria);

}
