package be.butskri.web.easybudget.flows.nieuweuitgave;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import javax.faces.model.SelectItem;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations.Mock;

import be.butskri.commons.test.JUnit4ButskriClassRunner;
import be.butskri.commons.types.Identifier;
import be.butskri.commonweb.faces.util.MessageHandler;
import be.butskri.easybudget.facade.algemeen.GeldstroomTo;
import be.butskri.easybudget.facade.algemeen.SubcategorieTo;
import be.butskri.easybudget.facade.uitgaven.UitgaveFacade;
import be.butskri.web.easybudget.GeldstroomBean;
import be.butskri.web.easybudget.SelectItemAssembler;

@RunWith(JUnit4ButskriClassRunner.class)
public class UitgaveControllerTest {

	private UitgaveController uitgaveController;

	@Mock
	private GeldstroomBean uitgaveBeanMock;
	@Mock
	private MessageHandler messageHandlerMock;
	@Mock
	private UitgaveFacade uitgaveFacadeMock;
	@Mock
	private SelectItemAssembler selectItemAssemblerMock;

	@Before
	public void setUp() {
		uitgaveController = new UitgaveController();
		uitgaveController.setMessageHandler(messageHandlerMock);
		uitgaveController.setUitgaveFacade(uitgaveFacadeMock);
		uitgaveController.setSelectItemAssembler(selectItemAssemblerMock);
	}

	@Test
	public void bewarenSlaatUitgavenOp() {
		GeldstroomTo geldstroomTo = new GeldstroomTo();
		stub(uitgaveBeanMock.getGeldstroomTo()).toReturn(geldstroomTo);

		uitgaveController.bewaren(uitgaveBeanMock);

		verify(uitgaveFacadeMock).createUitgave(geldstroomTo);
		verify(messageHandlerMock).addInfo("info_nieuweUitgave_opgeslagen");
	}

	@Test
	public void categorieGeselecteerdHaaltSubcategorienOp() {
		Identifier categorieId = new Identifier(15l);
		List<SubcategorieTo> subcategorieToList = Arrays.asList(new SubcategorieTo());
		List<SelectItem> subcategorieList = Arrays.asList(new SelectItem(new Object()));
		stub(uitgaveBeanMock.getCategorieId()).toReturn(categorieId);
		stub(uitgaveFacadeMock.getSubCategorien(categorieId)).toReturn(subcategorieToList);
		stub(selectItemAssemblerMock.assemble(subcategorieToList)).toReturn(subcategorieList);

		uitgaveController.categorieGeselecteerd(uitgaveBeanMock);

		verify(uitgaveBeanMock).setSubcategorieList(subcategorieList);
	}
}
