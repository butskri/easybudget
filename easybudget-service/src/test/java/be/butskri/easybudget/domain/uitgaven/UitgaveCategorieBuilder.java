package be.butskri.easybudget.domain.uitgaven;

import static be.butskri.commons.util.RandomizerUtil.*;
import be.butskri.easybudget.domain.DomainObjectBuilder;

public class UitgaveCategorieBuilder extends DomainObjectBuilder<UitgaveCategorie, UitgaveCategorieBuilder> {
	
	private String omschrijving = randomLongAlphanumeric();
	
	public UitgaveCategorieBuilder withOmschrijving(String omschrijving) {
		this.omschrijving = omschrijving;
		return this;
	}
	
	@Override
	protected UitgaveCategorie buildObject() {
		return new UitgaveCategorie(null, omschrijving);
	}
}
