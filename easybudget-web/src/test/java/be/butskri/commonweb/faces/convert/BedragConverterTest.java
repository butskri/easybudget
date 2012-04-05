package be.butskri.commonweb.faces.convert;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.inject.annotation.TestedObject;

import be.butskri.commons.exception.ButskriRuntimeException;
import be.butskri.commons.test.JUnit4ButskriClassRunner;
import be.butskri.commons.test.junit.ExpectedThrowable;
import be.butskri.commons.types.Bedrag;

@RunWith(JUnit4ButskriClassRunner.class)
public class BedragConverterTest {

	@TestedObject
	private BedragConverter converter;

	@Test
	public void objectToStringGeeftGeformatteerdeWaardeTerug() {
		Bedrag bedrag = new Bedrag("15.23");

		assertEquals("15,23", converter.objectToString(bedrag));
	}

	@Test
	public void stringToObjectGeeftBedragTerugVoorGetalMetKomma() {
		Bedrag bedrag = new Bedrag("15.23");

		assertEquals(bedrag, converter.stringToObject("15,23"));
	}

	@Test
	@ExpectedThrowable(value = "ongeldig_bedrag", type = ButskriRuntimeException.class)
	public void getAsObjectGooitConverterExceptionVoorOngeldigeString() {
		converter.stringToObject("ongeldig");
	}
}
