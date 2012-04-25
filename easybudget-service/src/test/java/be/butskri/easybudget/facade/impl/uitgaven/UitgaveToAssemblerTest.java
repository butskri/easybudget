package be.butskri.easybudget.facade.impl.uitgaven;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.stub;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.internal.runners.MockitoRule;
import org.mockito.MockitoAnnotations.Mock;

import be.butskri.easybudget.domain.uitgaven.Uitgave;
import be.butskri.easybudget.domain.uitgaven.UitgaveBuilder;
import be.butskri.easybudget.facade.algemeen.CategorieTo;
import be.butskri.easybudget.facade.algemeen.GeldstroomTo;
import be.butskri.easybudget.facade.impl.algemeen.CategorieToAssembler;
import be.butskri.easybudget.facade.impl.algemeen.GeldstroomToAssembler;

public class UitgaveToAssemblerTest {

	@Rule
	public MockitoRule mockitoRule = new MockitoRule();
	
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
		Uitgave uitgave = new UitgaveBuilder().build();
		UitgaveBuilder.setId(uitgave, uitgaveId);

		GeldstroomTo uitgaveTo = assembler.createTo(uitgave);
		assertEquals(uitgaveId, uitgaveTo.getId().getId());
		assertEquals(uitgave.getBedrag(), uitgaveTo.getBedrag());
		assertEquals(uitgave.getDatum(), uitgaveTo.getDatum());
	}

	@Test
	public void createToMaaktUitgaveToMetJuisteWaardeVoorCategorie() {
		Uitgave uitgave = new UitgaveBuilder().build();
		CategorieTo categorieTo = new CategorieTo();
		stub(categorieToAssemblerMock.createTo(uitgave.getCategorie())).toReturn(categorieTo);

		GeldstroomTo uitgaveTo = assembler.createTo(uitgave);
		assertEquals(categorieTo, uitgaveTo.getCategorie());
	}
}
