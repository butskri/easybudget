package be.butskri.web.easybudget;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import javax.faces.model.SelectItem;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import be.butskri.commons.test.BaseAssert;
import be.butskri.commons.test.JUnit4ButskriClassRunner;
import be.butskri.commons.types.Identifier;
import be.butskri.easybudget.facade.algemeen.CategorieTo;
import be.butskri.easybudget.facade.algemeen.SubcategorieTo;

@RunWith(JUnit4ButskriClassRunner.class)
public class SelectItemAssemblerTest {

	private static final Identifier ID1 = new Identifier(15L);
	private static final Identifier ID2 = new Identifier(16L);
	private static final String OMSCHRIJVING1 = "mijnOmschrijving1";
	private static final String OMSCHRIJVING2 = "mijnOmschrijving2";

	private SelectItemAssembler selectItemAssembler;

	@Before
	public void setUp() {
		selectItemAssembler = new SelectItemAssembler();
	}

	@Test
	public void assembleGeeftSelectItemVoorElkeCategorieTo() {
		CategorieTo categorie1 = createCategorieTo(ID1, OMSCHRIJVING1);
		CategorieTo categorie2 = createCategorieTo(ID2, OMSCHRIJVING2);

		SelectItem[] selectItems = selectItemAssembler.assemble(Arrays.asList(categorie1, categorie2));
		BaseAssert.assertSize(2, selectItems);
		assertSelectItem(selectItems[0], ID1, OMSCHRIJVING1);
		assertSelectItem(selectItems[1], ID2, OMSCHRIJVING2);
	}

	@Test
	public void assembleGeeftSelectItemVoorElkeSubcategorieTo() {
		SubcategorieTo categorie1 = createSubcategorieTo(ID1, OMSCHRIJVING1);
		SubcategorieTo categorie2 = createSubcategorieTo(ID2, OMSCHRIJVING2);

		List<SelectItem> selectItems = selectItemAssembler.assemble(Arrays.asList(categorie1, categorie2));
		BaseAssert.assertSize(2, selectItems);
		assertSelectItem(selectItems.get(0), ID1, OMSCHRIJVING1);
		assertSelectItem(selectItems.get(1), ID2, OMSCHRIJVING2);
	}

	private SubcategorieTo createSubcategorieTo(Identifier id, String omschrijving) {
		SubcategorieTo result = new SubcategorieTo();
		result.setId(id);
		result.setOmschrijving(omschrijving);
		return result;
	}

	private CategorieTo createCategorieTo(Identifier id, String omschrijving) {
		CategorieTo result = new CategorieTo();
		result.setId(id);
		result.setOmschrijving(omschrijving);
		return result;
	}

	private void assertSelectItem(SelectItem selectItem, Object value, String omschrijving) {
		assertEquals(value, selectItem.getValue());
		assertEquals(omschrijving, selectItem.getLabel());
	}

}
