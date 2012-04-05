package be.butskri.web.easybudget;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import be.butskri.easybudget.facade.algemeen.CategorieTo;
import be.butskri.easybudget.facade.algemeen.SubcategorieTo;

public class SelectItemAssembler {

	public SelectItem[] assemble(List<CategorieTo> categorien) {
		SelectItem[] result = new SelectItem[categorien.size()];
		int i = 0;
		for (CategorieTo categorieTo : categorien) {
			result[i++] = new SelectItem(categorieTo.getId(), categorieTo.getOmschrijving());
		}
		return result;
	}

	public List<SelectItem> assemble(List<SubcategorieTo> subcategorien) {
		List<SelectItem> result = new ArrayList<SelectItem>();
		for (SubcategorieTo subcategorieTo : subcategorien) {
			result.add(new SelectItem(subcategorieTo.getId(), subcategorieTo.getOmschrijving()));
		}
		return result;
	}
}
