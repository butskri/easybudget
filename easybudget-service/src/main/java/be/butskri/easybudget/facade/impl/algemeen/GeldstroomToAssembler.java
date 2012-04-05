package be.butskri.easybudget.facade.impl.algemeen;

import be.butskri.easybudget.domain.base.Geldstroom;
import be.butskri.easybudget.facade.algemeen.GeldstroomTo;

public class GeldstroomToAssembler {

	private CategorieToAssembler categorieToAssembler;

	public void setCategorieToAssembler(CategorieToAssembler categorieToAssembler) {
		this.categorieToAssembler = categorieToAssembler;
	}

	public GeldstroomTo createTo(Geldstroom geldstroom) {
		GeldstroomTo result = new GeldstroomTo(geldstroom.getId());

		result.setDatum(geldstroom.getDatum());
		result.setBedrag(geldstroom.getBedrag());

		result.setCategorie(categorieToAssembler.createTo(geldstroom.getCategorie()));
		return result;
	}
}
