package be.butskri.easybudget.facade.impl.uitgaven;

import static org.junit.Assert.assertSame;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations.Mock;
import org.unitils.inject.annotation.InjectIntoByType;
import org.unitils.inject.annotation.TestedObject;

import be.butskri.commons.test.JUnit4ButskriClassRunner;
import be.butskri.commons.types.Identifier;
import be.butskri.commons.util.RandomizerUtil;
import be.butskri.easybudget.domain.uitgaven.UitgaveCategorie;
import be.butskri.easybudget.domain.uitgaven.UitgaveCategorieMother;
import be.butskri.easybudget.domain.uitgaven.UitgaveCategorieRepository;
import be.butskri.easybudget.facade.algemeen.CategorieTo;
import be.butskri.easybudget.facade.impl.algemeen.CategorieToAssembler;
import be.butskri.easybudget.facade.impl.algemeen.SubcategorieToAssembler;

@RunWith(JUnit4ButskriClassRunner.class)
public class UitgaveFacadeImplTest {

	@InjectIntoByType
	@Mock
	private UitgaveCategorieRepository uitgaveCategorieRepositoryMock;
	// @InjectIntoByType
	@Mock
	private CategorieToAssembler categorieToAssemblerMock;
	// @InjectIntoByType
	@Mock
	private SubcategorieToAssembler subcategorieToAssemblerMock;

	@TestedObject
	private UitgaveFacadeImpl uitgaveFacade;

	@Ignore
	@Test
	public void getAlleCategorienConverteertAlleCategorienNaarTos() {
		List<UitgaveCategorie> uitgaveCategorien = Arrays
				.asList(UitgaveCategorieMother.createCategorie());
		List<CategorieTo> categorieTos = Arrays.asList(new CategorieTo());

		stub(uitgaveCategorieRepositoryMock.findAll()).toReturn(
				uitgaveCategorien);
		stub(categorieToAssemblerMock.createTos(uitgaveCategorien)).toReturn(
				categorieTos);

		assertSame(categorieTos, uitgaveFacade.getAlleCategorien());
	}

	@Test
	public void removeCategorieZoektCategorieOpEnVerwijdertZe() {
		Identifier id = new Identifier(RandomizerUtil.randomLong());
		CategorieTo categorieTo = new CategorieTo(id);

		UitgaveCategorie uitgaveCategorie = mock(UitgaveCategorie.class);
		stub(uitgaveCategorieRepositoryMock.lookUpById(id)).toReturn(
				uitgaveCategorie);

		uitgaveFacade.removeCategorie(categorieTo);

		verify(uitgaveCategorieRepositoryMock).remove(same(uitgaveCategorie));
	}

}
