package be.butskri.easybudget.domain.uitgaven;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import be.butskri.commons.domain.RepositoryImpl;

@Repository
public class UitgaveRepository extends RepositoryImpl<Uitgave> {

	@PersistenceContext(unitName = "easybudget")
	private EntityManager entityManager;

	protected UitgaveRepository() {
		super(Uitgave.class);
	}

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

}
