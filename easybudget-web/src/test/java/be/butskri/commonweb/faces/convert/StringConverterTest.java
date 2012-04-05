package be.butskri.commonweb.faces.convert;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.inject.annotation.TestedObject;

import be.butskri.commons.test.JUnit4ButskriClassRunner;

@RunWith(JUnit4ButskriClassRunner.class)
public class StringConverterTest {

	@TestedObject
	private StringConverter converter;

	@Test
	public void objectToStringGeeftStringWaardeTerug() {
		String stringWaarde = "mijnWaarde";

		assertEquals(stringWaarde, converter.objectToString(stringWaarde));
	}

	@Test
	public void stringToObjectGeeftStringWaardeTerug() {
		String stringWaarde = "mijnWaarde";

		assertEquals(stringWaarde, converter.stringToObject(stringWaarde));
	}

	@Test
	public void stringToObjectGeeftGetrimdeStringWaardeTerug() {
		String stringWaarde = "mijnWaarde";

		assertEquals(stringWaarde, converter.stringToObject("   " + stringWaarde + "   "));
	}
}
