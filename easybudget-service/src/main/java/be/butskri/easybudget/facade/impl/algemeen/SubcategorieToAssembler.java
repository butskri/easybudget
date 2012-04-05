package be.butskri.easybudget.facade.impl.algemeen;

import java.util.ArrayList;
import java.util.List;

import be.butskri.easybudget.domain.base.Subcategorie;
import be.butskri.easybudget.facade.algemeen.SubcategorieTo;

public class SubcategorieToAssembler {

	private CategorieToAssembler categorieToAssembler;

	public void setCategorieToAssembler(CategorieToAssembler categorieToAssembler) {
		this.categorieToAssembler = categorieToAssembler;
	}

	public SubcategorieTo createTo(Subcategorie subcategorie) {
		SubcategorieTo result = new SubcategorieTo(subcategorie.getId());

		result.setOmschrijving(subcategorie.getOmschrijving());
		result.setCategorie(categorieToAssembler.createTo(subcategorie.getCategorie()));

		return result;
	}

	public <T extends Subcategorie> List<SubcategorieTo> createTos(List<T> subcategorien) {
		List<SubcategorieTo> result = new ArrayList<SubcategorieTo>();
		for (Subcategorie subcategorie : subcategorien) {
			result.add(createTo(subcategorie));
		}
		return result;
	}

}
