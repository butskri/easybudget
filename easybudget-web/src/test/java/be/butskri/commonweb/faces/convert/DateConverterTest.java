package be.butskri.commonweb.faces.convert;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.inject.annotation.TestedObject;

import be.butskri.commons.exception.ButskriRuntimeException;
import be.butskri.commons.test.JUnit4ButskriClassRunner;
import be.butskri.commons.test.junit.ExpectedThrowable;
import be.butskri.commons.util.DateUtils;

@RunWith(JUnit4ButskriClassRunner.class)
public class DateConverterTest {

	private static final String DATUM_ALS_STRING = "15/10/2008";
	private static final Date DATUM = DateUtils.parseDate(DATUM_ALS_STRING);

	@TestedObject
	private DateConverter converter;

	@Test
	public void objectToStringFormatteertDatum() {
		assertEquals(DATUM_ALS_STRING, converter.objectToString(DATUM));
	}

	@Test
	public void stringToObjectZetStringOmNaarDatum() {
		assertEquals(DATUM, converter.stringToObject(DATUM_ALS_STRING));
	}

	@Test
	@ExpectedThrowable(value = "ongeldige_datum", type = ButskriRuntimeException.class)
	public void stringToObjectGooitExceptionVoorOngeldigeString() {
		converter.stringToObject("ongeldig");
	}
}
