package be.butskri.easybudget.facade.impl.algemeen;

import static junit.framework.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import be.butskri.commons.test.BaseAssert;
import be.butskri.easybudget.domain.uitgaven.UitgaveCategorie;
import be.butskri.easybudget.domain.uitgaven.UitgaveCategorieBuilder;
import be.butskri.easybudget.facade.algemeen.CategorieTo;

@RunWith(BlockJUnit4ClassRunner.class)
public class CategorieToAssemblerTest {

	private static final Long ID1 = 15L;
	private static final Long ID2 = 16L;
	private static final String OMSCHRIJVING1 = "mijnOmschrijving1";
	private static final String OMSCHRIJVING2 = "mijnOmschrijving2";
	private CategorieToAssembler assembler;

	@Before
	public void setUp() {
		assembler = new CategorieToAssembler();
	}

	@Test
	public void createToMaaktEenCategorieToMetDeJuisteWaarden() {
		UitgaveCategorie categorie = createCategorie(ID1, OMSCHRIJVING1);

		CategorieTo categorieTo = assembler.createTo(categorie);
		assertCategorieTo(categorieTo, ID1, OMSCHRIJVING1);
	}

	@Test
	public void createTosMaaktEenCategorieToVoorElkeUitgaveCategorie() {
		UitgaveCategorie categorie1 = createCategorie(ID1, OMSCHRIJVING1);
		UitgaveCategorie categorie2 = createCategorie(ID2, OMSCHRIJVING2);

		List<CategorieTo> categorieTos = assembler.createTos(Arrays.asList(categorie1, categorie2));
		BaseAssert.assertSize(2, categorieTos);
		assertCategorieTo(categorieTos.get(0), ID1, OMSCHRIJVING1);
		assertCategorieTo(categorieTos.get(1), ID2, OMSCHRIJVING2);
	}

	private void assertCategorieTo(CategorieTo categorieTo, Long expectedId, String expectedOmschrijving) {
		assertEquals(expectedId, categorieTo.getId().getId());
		assertEquals(expectedOmschrijving, categorieTo.getOmschrijving());
	}

	private UitgaveCategorie createCategorie(Long id, String omschrijving) {
		return new UitgaveCategorieBuilder().withOmschrijving(omschrijving).withId(id).build();
	}
}
