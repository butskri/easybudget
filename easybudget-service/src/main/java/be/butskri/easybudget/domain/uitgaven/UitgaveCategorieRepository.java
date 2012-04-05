package be.butskri.easybudget.domain.uitgaven;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import be.butskri.commons.domain.RepositoryImpl;
import be.butskri.commons.exception.ButskriRuntimeException;

@Repository
public class UitgaveCategorieRepository extends
		RepositoryImpl<UitgaveCategorie> {

	@PersistenceContext(unitName = "easybudget")
	private EntityManager entityManager;

	public UitgaveCategorieRepository() {
		super(UitgaveCategorie.class);
	}

	private Collection<UitgaveCategorie> findAlleSubcategorien(
			UitgaveCategorie uitgaveCategorie) {
		List<UitgaveCategorie> result = new ArrayList<UitgaveCategorie>();
		List<UitgaveCategorie> subcategorien = findSubcategorien(uitgaveCategorie);
		result.addAll(subcategorien);
		for (UitgaveCategorie subcategorie : subcategorien) {
			result.addAll(findAlleSubcategorien(subcategorie));
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<UitgaveCategorie> findByOmschrijving(String omschrijving) {
		List<UitgaveCategorie> result = new ArrayList<UitgaveCategorie>();
		List<UitgaveCategorie> exactGevondenCategorien = createCriteria() //
				.add(
						Restrictions.ilike("omschrijving", omschrijving,
								MatchMode.ANYWHERE)) //
				.list();
		result.addAll(exactGevondenCategorien);
		for (UitgaveCategorie uitgaveCategorie : exactGevondenCategorien) {
			result.addAll(findAlleSubcategorien(uitgaveCategorie));
		}
		return result;
	}

	private UitgaveCategorie findByParentCategorieEnOmschrijving(
			UitgaveCategorie parent, String omschrijving) {
		return (UitgaveCategorie) createCriteria() //
				.add(Restrictions.eq("parent", parent)) //
				.add(Restrictions.eq("omschrijving", omschrijving)) //
				.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	private List<UitgaveCategorie> findSubcategorien(
			UitgaveCategorie uitgaveCategorie) {
		return createCriteria()
				.add(Restrictions.eq("parent", uitgaveCategorie)).list();
	}

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	@Override
	public UitgaveCategorie persist(UitgaveCategorie domainObject) {
		if (findByParentCategorieEnOmschrijving(domainObject.getParent(),
				domainObject.getOmschrijving()) == null) {
			return super.persist(domainObject);
		}
		throw new ButskriRuntimeException("easybudget.uitgave.alreadyExists");
	}

}
