package be.butskri.easybudget.domain.uitgaven;

import static be.butskri.commons.util.RandomizerUtil.*;
import static junit.framework.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;

import be.butskri.easybudget.domain.AbstractRepositoryTest;

public class UitgaveCategorieRepositoryTest extends AbstractRepositoryTest {

	@Resource
	private UitgaveCategorieRepository repository;
	
	@Test
	public void persistSchrijftUitgaveCategorieWegNaarDb() {
		String omschrijving = randomShortAlphanumeric();

		UitgaveCategorie uitgaveCategorie = new UitgaveCategorie(null, omschrijving);
		uitgaveCategorie = repository.persist(uitgaveCategorie);
		getEntityManager().flush();

		String foundOmschrijving = (String) getJdbcTemplate().queryForObject(
				"SELECT OMSCHRIJVING FROM UITGAVE_CATEGORIE WHERE ID = " + uitgaveCategorie.getId(), String.class);
		assertEquals(omschrijving, foundOmschrijving);
	}
}
