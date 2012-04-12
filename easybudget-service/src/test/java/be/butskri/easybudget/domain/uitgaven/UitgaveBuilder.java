package be.butskri.easybudget.domain.uitgaven;

import java.util.Date;

import be.butskri.commons.util.RandomizerUtil;
import be.butskri.easybudget.domain.DomainObjectBuilder;

public class UitgaveBuilder extends DomainObjectBuilder<Uitgave, UitgaveBuilder> {

	private UitgaveCategorie categorie = new UitgaveCategorieBuilder().build();

	@Override
	protected Uitgave buildObject() {
		return new Uitgave(new Date(), RandomizerUtil.randomBedrag(), categorie);
	}
}
