package be.butskri.easybudget.facade.impl.uitgaven;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.stub;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations.Mock;

import be.butskri.commons.test.JUnit4ButskriClassRunner;
import be.butskri.easybudget.domain.uitgaven.Uitgave;
import be.butskri.easybudget.domain.uitgaven.UitgaveMother;
import be.butskri.easybudget.facade.algemeen.CategorieTo;
import be.butskri.easybudget.facade.algemeen.GeldstroomTo;
import be.butskri.easybudget.facade.impl.algemeen.CategorieToAssembler;
import be.butskri.easybudget.facade.impl.algemeen.GeldstroomToAssembler;

@RunWith(JUnit4ButskriClassRunner.class)
public class UitgaveToAssemblerTest {

	@Mock
	private CategorieToAssembler categorieToAssemblerMock;

	private GeldstroomToAssembler assembler;

	@Before
	public void setUp() {
		assembler = new GeldstroomToAssembler();
		assembler.setCategorieToAssembler(categorieToAssemblerMock);
	}

	@Test
	public void createToMaaktUitgaveToMetJuisteWaardenVoorPrimitieveVelden() {
		Long uitgaveId = Long.valueOf(15);
		Uitgave uitgave = UitgaveMother.createUitgave();
		UitgaveMother.setId(uitgave, uitgaveId);

		GeldstroomTo uitgaveTo = assembler.createTo(uitgave);
		assertEquals(uitgaveId, uitgaveTo.getId().getId());
		assertEquals(uitgave.getBedrag(), uitgaveTo.getBedrag());
		assertEquals(uitgave.getDatum(), uitgaveTo.getDatum());
	}

	@Test
	public void createToMaaktUitgaveToMetJuisteWaardeVoorCategorie() {
		Uitgave uitgave = UitgaveMother.createUitgave();
		CategorieTo categorieTo = new CategorieTo();
		stub(categorieToAssemblerMock.createTo(uitgave.getCategorie())).toReturn(categorieTo);

		GeldstroomTo uitgaveTo = assembler.createTo(uitgave);
		assertEquals(categorieTo, uitgaveTo.getCategorie());
	}
}
