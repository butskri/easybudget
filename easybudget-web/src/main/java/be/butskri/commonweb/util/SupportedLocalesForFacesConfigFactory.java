package be.butskri.commonweb.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.LocaleUtils;

import be.butskri.commons.util.SupportedLocales;
import be.butskri.commons.xml.XmlUtils;

public class SupportedLocalesForFacesConfigFactory {

	private static final String DEFAULT_LOCALE = "default-locale";
	private static final String SUPPORTED_LOCALE = "supported-locale";

	public static SupportedLocales create(String facesConfig) {
		SupportedLocales result = new SupportedLocales();

		List<String> defaultLocaleWaarden = getWaardenVoorTag(facesConfig, DEFAULT_LOCALE);
		List<String> andereLocaleWaarden = getWaardenVoorTag(facesConfig, SUPPORTED_LOCALE);

		result.setDefaultLocale(LocaleUtils.toLocale(defaultLocaleWaarden.get(0)));
		List<Locale> locales = new ArrayList<Locale>();
		for (String locale : andereLocaleWaarden) {
			locales.add(LocaleUtils.toLocale(locale));
		}
		result.setLocales(locales);

		return result;
	}

	private static List<String> getWaardenVoorTag(String resource, String tagnaam) {
		InputStream inputStream = SupportedLocalesForFacesConfigFactory.class.getResourceAsStream(resource);
		return new XmlUtils().getTagWaarden(inputStream, tagnaam);
	}
}
