package be.butskri.easybudget.domain;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:be/butskri/easybudget/application-config.xml",
		"classpath:/test-config.xml", "classpath:be/butskri/easybudget/db/easybudget-db-testcontext.xml" })
@Transactional
public abstract class AbstractIntegrationTest {

	@PersistenceContext
	private EntityManager entityManager;

	@Resource(name = "easybudgetDataSource")
	private DataSource dataSource;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	protected JdbcTemplate getJdbcTemplate() {
		return new JdbcTemplate(dataSource);
	}
	
	protected EntityManager getEntityManager() {
		return entityManager;
	}
}
