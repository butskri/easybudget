package be.butskri.web.easybudget;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import javax.faces.model.SelectItem;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations.Mock;

import be.butskri.commons.test.JUnit4ButskriClassRunner;
import be.butskri.easybudget.facade.algemeen.CategorieTo;
import be.butskri.easybudget.facade.uitgaven.UitgaveFacade;

@RunWith(JUnit4ButskriClassRunner.class)
public class SelectItemsBeanTest {

	@Mock
	private UitgaveFacade uitgaveFacadeMock;
	@Mock
	private SelectItemAssembler selectItemAssembler;

	private SelectItemsBean selectItemsBean;

	@Before
	public void setUp() {
		selectItemsBean = new SelectItemsBean();
		selectItemsBean.setUitgaveFacade(uitgaveFacadeMock);
		selectItemsBean.setSelectItemAssembler(selectItemAssembler);
	}

	@Test
	public void getUitgaveCategorienMaaktSelectItemVoorElkeUitgaveCategorie() {
		SelectItem[] selectItems = new SelectItem[2];
		List<CategorieTo> categorien = Arrays.asList(new CategorieTo());

		stub(uitgaveFacadeMock.getAlleCategorien()).toReturn(categorien);
		stub(selectItemAssembler.assemble(categorien)).toReturn(selectItems);

		assertSame(selectItems, selectItemsBean.getUitgaveCategorien());
	}

}
