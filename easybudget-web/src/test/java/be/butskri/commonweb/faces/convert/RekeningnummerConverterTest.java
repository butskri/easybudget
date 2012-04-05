package be.butskri.commonweb.faces.convert;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.inject.annotation.TestedObject;

import be.butskri.commons.exception.ButskriRuntimeException;
import be.butskri.commons.test.JUnit4ButskriClassRunner;
import be.butskri.commons.test.junit.ExpectedThrowable;
import be.butskri.commons.types.Rekeningnummer;

@RunWith(JUnit4ButskriClassRunner.class)
public class RekeningnummerConverterTest {

	private static final String GEFORMATTEERD_GELDIG_REKENINGNUMMER = "416-1090011-16";
	private static final String GELDIG_REKENINGNUMMER = "416109001116";
	private static final String ONGELDIG_REKENINGNUMMER = "416109001117";
	private static final Rekeningnummer REKENINGNUMMER = new Rekeningnummer(GELDIG_REKENINGNUMMER);

	@TestedObject
	private RekeningnummerConverter converter;

	@Test
	public void stringToObjectGeeftRekeningnummerTerug() {
		assertEquals(REKENINGNUMMER, converter.stringToObject(GELDIG_REKENINGNUMMER));
	}

	@Test
	@ExpectedThrowable(value = "ongeldig_rekeningnummer", type = ButskriRuntimeException.class)
	public void stringToObjectGooitButskriRuntimeExceptionVoorOngeldigeWaarde() {
		converter.stringToObject(ONGELDIG_REKENINGNUMMER);
	}

	@Test
	public void objectToStringGeeftGeformatteerdRekeningnummerTerug() {
		assertEquals(GEFORMATTEERD_GELDIG_REKENINGNUMMER, converter.objectToString(REKENINGNUMMER));
	}
}
