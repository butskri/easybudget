package be.butskri.easybudget.facade.impl.algemeen;

import java.util.ArrayList;
import java.util.List;

import be.butskri.easybudget.domain.base.Categorie;
import be.butskri.easybudget.facade.algemeen.CategorieTo;

public class CategorieToAssembler {

	public CategorieTo createTo(Categorie categorie) {
		CategorieTo result = new CategorieTo(categorie.getId());
		result.setOmschrijving(categorie.getOmschrijving());
		return result;
	}

	public List<CategorieTo> createTos(List<? extends Categorie> categorien) {
		List<CategorieTo> result = new ArrayList<CategorieTo>();
		for (Categorie categorie : categorien) {
			result.add(createTo(categorie));
		}
		return result;
	}

}
