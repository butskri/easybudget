package be.butskri.easybudget.domain.uitgaven;

import java.util.Date;

import be.butskri.commons.util.RandomizerUtil;
import be.butskri.easybudget.domain.DomainObjectMother;

public class UitgaveMother extends DomainObjectMother {

	public static Uitgave createUitgave() {
		UitgaveCategorie categorie = UitgaveCategorieMother.createCategorie();

		Uitgave result = new Uitgave(new Date(), RandomizerUtil.randomBedrag(), categorie);
		return result;
	}
}
