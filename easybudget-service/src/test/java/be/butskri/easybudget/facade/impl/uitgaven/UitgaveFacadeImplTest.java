package be.butskri.easybudget.facade.impl.uitgaven;

import static org.junit.Assert.assertSame;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.stub;
import static org.mockito.Mockito.verify;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.internal.runners.MockitoRule;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.mockito.MockitoAnnotations.Mock;

import be.butskri.commons.types.Identifier;
import be.butskri.commons.util.RandomizerUtil;
import be.butskri.easybudget.domain.uitgaven.UitgaveCategorie;
import be.butskri.easybudget.domain.uitgaven.UitgaveCategorieBuilder;
import be.butskri.easybudget.domain.uitgaven.UitgaveCategorieRepository;
import be.butskri.easybudget.facade.algemeen.CategorieTo;
import be.butskri.easybudget.facade.impl.algemeen.CategorieToAssembler;

@RunWith(BlockJUnit4ClassRunner.class)
public class UitgaveFacadeImplTest {

	@Rule
	public MockitoRule mockitoRule = new MockitoRule();
	
	@Mock
	private UitgaveCategorieRepository uitgaveCategorieRepositoryMock;
	@Mock
	private CategorieToAssembler categorieToAssemblerMock;

	private UitgaveFacadeImpl uitgaveFacade;
	
	@Before
	public void setUp() {
		uitgaveFacade = new UitgaveFacadeImpl();
	}

	@Ignore
	@Test
	public void getAlleCategorienConverteertAlleCategorienNaarTos() {
		List<UitgaveCategorie> uitgaveCategorien = Arrays
				.asList(new UitgaveCategorieBuilder().build());
		List<CategorieTo> categorieTos = Arrays.asList(new CategorieTo());

		stub(uitgaveCategorieRepositoryMock.findAll()).toReturn(
				uitgaveCategorien);
		stub(categorieToAssemblerMock.createTos(uitgaveCategorien)).toReturn(
				categorieTos);

		assertSame(categorieTos, uitgaveFacade.getAlleCategorien());
	}

	@Ignore
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
