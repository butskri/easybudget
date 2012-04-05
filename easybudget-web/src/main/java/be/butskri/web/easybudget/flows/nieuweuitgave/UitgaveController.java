package be.butskri.web.easybudget.flows.nieuweuitgave;

import java.util.List;

import be.butskri.commons.types.Identifier;
import be.butskri.commonweb.faces.util.MessageHandler;
import be.butskri.easybudget.facade.algemeen.GeldstroomTo;
import be.butskri.easybudget.facade.algemeen.SubcategorieTo;
import be.butskri.easybudget.facade.uitgaven.UitgaveFacade;
import be.butskri.web.easybudget.GeldstroomBean;
import be.butskri.web.easybudget.SelectItemAssembler;

public class UitgaveController {

	private MessageHandler messageHandler;
	private UitgaveFacade uitgaveFacade;
	private SelectItemAssembler selectItemAssembler = new SelectItemAssembler();

	public void setMessageHandler(MessageHandler messageHandler) {
		this.messageHandler = messageHandler;
	}

	public void setUitgaveFacade(UitgaveFacade uitgaveFacade) {
		this.uitgaveFacade = uitgaveFacade;
	}

	public void setSelectItemAssembler(SelectItemAssembler selectItemAssembler) {
		this.selectItemAssembler = selectItemAssembler;
	}

	public void bewaren(GeldstroomBean uitgaveBean) {
		GeldstroomTo uitgaveTo = uitgaveBean.getGeldstroomTo();
		uitgaveFacade.createUitgave(uitgaveTo);
		messageHandler.addInfo("info_nieuweUitgave_opgeslagen");
	}

	public void categorieGeselecteerd(GeldstroomBean uitgaveBean) {
		Identifier categorieId = uitgaveBean.getCategorieId();

		List<SubcategorieTo> subCategorien = uitgaveFacade.getSubCategorien(categorieId);
		uitgaveBean.setSubcategorieList(selectItemAssembler.assemble(subCategorien));
		// return null;
	}

}
