package be.butskri.commonweb.faces.convert;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.inject.annotation.TestedObject;

import be.butskri.commons.test.JUnit4ButskriClassRunner;
import be.butskri.commons.types.Identifier;

@RunWith(JUnit4ButskriClassRunner.class)
public class IdentifierConverterTest {

	@TestedObject
	private IdentifierConverter converter;

	@Test
	public void objectToStringGeeftStringWaardeVanIdTerug() {
		assertEquals("152", converter.objectToString(new Identifier(152l)));
	}

	@Test
	public void stringToObjectGeeftIdentifierMetLongWaardeTerugVoorGeldigeStringWaarde() {
		assertEquals(new Identifier(152l), converter.stringToObject("152"));
	}
}
