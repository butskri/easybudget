package be.butskri.web.easybudget;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import be.butskri.commons.test.JUnit4ButskriClassRunner;
import be.butskri.commons.types.Bedrag;
import be.butskri.commons.types.Identifier;
import be.butskri.easybudget.facade.algemeen.GeldstroomTo;

@RunWith(JUnit4ButskriClassRunner.class)
public class GeldstroomBeanTest {

	private static final Bedrag BEDRAG = new Bedrag("15,63");
	private static final Identifier CATEGORIE_ID = new Identifier(17l);
	private static final Identifier SUBCATEGORIE_ID = new Identifier(18l);
	private static final Date DATUM = new Date();

	private GeldstroomBean geldstroomBean;

	@Before
	public void setUp() {
		geldstroomBean = new GeldstroomBean();
	}

	@Test
	public void getGeldstroomToGeeftGeldstroomToTerugMetIngevuldeWaarden() {
		geldstroomBean.setBedrag(BEDRAG);
		geldstroomBean.setCategorieId(CATEGORIE_ID);
		geldstroomBean.setSubcategorieId(SUBCATEGORIE_ID);
		geldstroomBean.setDatum(DATUM);

		GeldstroomTo geldstroomTo = geldstroomBean.getGeldstroomTo();
		assertEquals(BEDRAG, geldstroomTo.getBedrag());
		assertEquals(CATEGORIE_ID, geldstroomTo.getCategorie().getId());
		assertEquals(SUBCATEGORIE_ID, geldstroomTo.getSubcategorie().getId());
		assertEquals(DATUM, geldstroomTo.getDatum());

	}
}
