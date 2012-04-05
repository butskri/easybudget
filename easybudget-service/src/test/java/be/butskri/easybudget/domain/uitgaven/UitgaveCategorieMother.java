package be.butskri.easybudget.domain.uitgaven;

import static be.butskri.commons.util.RandomizerUtil.*;
import be.butskri.easybudget.domain.DomainObjectMother;

public class UitgaveCategorieMother extends DomainObjectMother {

	public static UitgaveCategorie createCategorie() {
		return createCategorie(randomLongAlphanumeric());
	}

	public static UitgaveCategorie createCategorie(String omschrijving) {
		return new UitgaveCategorie(null, omschrijving);
	}

	public static UitgaveCategorie createCategorie(Long id, String omschrijving) {
		UitgaveCategorie uitgaveCategorie = new UitgaveCategorie(null, omschrijving);
		setId(uitgaveCategorie, id);
		return uitgaveCategorie;
	}
}
