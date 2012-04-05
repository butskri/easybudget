package be.butskri.easybudget.domain.uitgaven;

import static be.butskri.commons.util.RandomizerUtil.*;
import static junit.framework.Assert.*;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;

import be.butskri.commons.types.Bedrag;
import be.butskri.easybudget.domain.AbstractRepositoryTest;

public class UitgaveRepositoryTest extends AbstractRepositoryTest {

	@Resource
	private UitgaveRepository repository;

	private UitgaveCategorie categorie;

	public void setRepository(UitgaveRepository repository) {
		this.repository = repository;
	}

	@Before
	public void setUp() {
		categorie = UitgaveCategorieMother.createCategorie();
	}

	@SuppressWarnings("unchecked")
	@Test
	public void persistSchrijftUitgaveWegNaarDb() {
		Date datum = new Date();
		Bedrag bedrag = randomBedrag();

		Uitgave uitgave = new Uitgave(datum, bedrag, categorie);
		uitgave = repository.persist(uitgave);
		getEntityManager().flush();

		Map<String, Object> gevondenWaarden = getJdbcTemplate().queryForMap(
				"SELECT DATUM, BEDRAG, CAT.OMSCHRIJVING as CAT_OMSCHR "
						+ "FROM UITGAVE, UITGAVE_CATEGORIE CAT "
						+ "WHERE UITGAVE.FK_UITGAVE_CATEGORIE = CAT.ID "
						+ "AND UITGAVE.ID = " + uitgave.getId());
		assertEquals(datum, gevondenWaarden.get("DATUM"));
		assertEquals(bedrag.getValue(), gevondenWaarden.get("BEDRAG"));
		assertEquals(categorie.getOmschrijving(), gevondenWaarden.get("CAT_OMSCHR"));
	}

}
