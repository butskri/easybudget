package be.butskri.web.easybudget;

import java.util.List;

import javax.faces.model.SelectItem;

import be.butskri.easybudget.facade.algemeen.CategorieTo;
import be.butskri.easybudget.facade.uitgaven.UitgaveFacade;

public class SelectItemsBean {

	private UitgaveFacade uitgaveFacade;
	private SelectItemAssembler selectItemAssembler;

	public void setUitgaveFacade(UitgaveFacade uitgaveFacade) {
		this.uitgaveFacade = uitgaveFacade;
	}

	public void setSelectItemAssembler(SelectItemAssembler selectItemAssembler) {
		this.selectItemAssembler = selectItemAssembler;
	}

	public SelectItem[] getUitgaveCategorien() {
		List<CategorieTo> categorien = uitgaveFacade.getAlleCategorien();
		return selectItemAssembler.assemble(categorien);
	}

}
