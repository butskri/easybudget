package be.butskri.commonweb.util;

import static org.junit.Assert.*;

import java.util.Locale;

import org.apache.commons.lang.LocaleUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import be.butskri.commons.test.BaseAssert;
import be.butskri.commons.test.JUnit4ButskriClassRunner;
import be.butskri.commons.util.SupportedLocales;

@RunWith(JUnit4ButskriClassRunner.class)
public class SupportedLocalesForFacesConfigFactoryTest {

	private static final String FACES_CONFIG = "/facesConfigForSupportedLocales.xml";

	private static final Locale NL = LocaleUtils.toLocale("nl");
	private static final Locale NL_BE = LocaleUtils.toLocale("nl_BE");
	private static final Locale FR_BE = LocaleUtils.toLocale("fr_BE");

	private SupportedLocales supportedLocales;

	@Before
	public void setUp() {
		supportedLocales = SupportedLocalesForFacesConfigFactory.create(FACES_CONFIG);
	}

	@Test
	public void createHaaltDefaultLocaleUitFacesConfigBestand() {
		assertEquals(NL, supportedLocales.getDefaultLocale());

	}

	@Test
	public void createHaaltLocalesUitFacesConfigBestand() {
		BaseAssert.assertContainsOnly(supportedLocales.getLocales(), NL_BE, FR_BE);
	}
}
