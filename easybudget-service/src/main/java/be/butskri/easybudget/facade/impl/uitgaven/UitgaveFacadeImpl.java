package be.butskri.easybudget.facade.impl.uitgaven;

import java.util.List;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import be.butskri.commons.types.Identifier;
import be.butskri.easybudget.domain.uitgaven.Uitgave;
import be.butskri.easybudget.domain.uitgaven.UitgaveCategorie;
import be.butskri.easybudget.domain.uitgaven.UitgaveCategorieRepository;
import be.butskri.easybudget.domain.uitgaven.UitgaveRepository;
import be.butskri.easybudget.facade.algemeen.CategorieTo;
import be.butskri.easybudget.facade.algemeen.GeldstroomCriteria;
import be.butskri.easybudget.facade.algemeen.GeldstroomTo;
import be.butskri.easybudget.facade.algemeen.SubcategorieTo;
import be.butskri.easybudget.facade.uitgaven.UitgaveFacade;

public class UitgaveFacadeImpl implements UitgaveFacade {

	private UitgaveCategorieRepository uitgaveCategorieRepository;
	private UitgaveRepository uitgaveRepository;

	public void setUitgaveCategorieRepository(UitgaveCategorieRepository uitgaveCategorieRepository) {
		this.uitgaveCategorieRepository = uitgaveCategorieRepository;
	}

	public void setUitgaveRepository(UitgaveRepository uitgaveRepository) {
		this.uitgaveRepository = uitgaveRepository;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public Uitgave save(Uitgave uitgave) {
		return uitgaveRepository.persist(uitgave);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public UitgaveCategorie save(UitgaveCategorie uitgaveCategorie) {
		return uitgaveCategorieRepository.persist(uitgaveCategorie);
	}
	
	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<UitgaveCategorie> findCategorien(String omschrijving) {
		return uitgaveCategorieRepository.findByOmschrijving(omschrijving);
	}
	
	
	
	
	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public CategorieTo createCategorie(CategorieTo categorieTo) {
//		UitgaveCategorie categorie = new UitgaveCategorie(categorieTo.getOmschrijving());
//		uitgaveCategorieRepository.persist(categorie);
//		return categorieToAssembler.createTo(categorie);
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public void removeCategorie(CategorieTo categorieTo) {
		UitgaveCategorie categorie = uitgaveCategorieRepository.lookUpById(categorieTo.getId());
		uitgaveCategorieRepository.remove(categorie);
	}

	@Override
	@Deprecated
	@Transactional(propagation = Propagation.REQUIRED)
	public SubcategorieTo createSubcategorie(SubcategorieTo subcategorieTo) {
//		UitgaveCategorie categorie = uitgaveCategorieRepository.lookUpById(subcategorieTo.getCategorie().getId());
//		UitgaveSubcategorie subcategorie = new UitgaveSubcategorie(subcategorieTo.getOmschrijving(), categorie);
//		uitgaveSubcategorieRepository.persist(subcategorie);
//		return subcategorieToAssembler.createTo(subcategorie);
		return null;
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED)
	public GeldstroomTo createUitgave(GeldstroomTo uitgaveTo) {
//		UitgaveCategorie categorie = uitgaveCategorieRepository.lookUpById(uitgaveTo.getCategorie().getId());
//		Uitgave uitgave = new Uitgave(uitgaveTo.getDatum(), uitgaveTo.getBedrag(), categorie);
//		uitgaveRepository.persist(uitgave);
//		return uitgaveToAssembler.createTo(uitgave);
		return null;
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<GeldstroomTo> findUitgaven(GeldstroomCriteria criteria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<CategorieTo> getAlleCategorien() {
//		List<UitgaveCategorie> categorien = uitgaveCategorieRepository.findAll();
//		return categorieToAssembler.createTos(categorien);
		return null;
	}

	@Override
	@Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
	public List<SubcategorieTo> getSubCategorien(Identifier categorieId) {
//		List<UitgaveSubcategorie> subcategorien = uitgaveSubcategorieRepository.findByCategorieId(categorieId);
//		return subcategorieToAssembler.createTos(subcategorien);
		return null;
	}

}
