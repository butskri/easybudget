package be.butskri.commonweb.faces.convert;

import static org.junit.Assert.*;

import java.lang.annotation.RetentionPolicy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.unitils.inject.annotation.TestedObject;

import be.butskri.commons.test.BaseAssert;
import be.butskri.commons.test.JUnit4ButskriClassRunner;

@RunWith(JUnit4ButskriClassRunner.class)
public class EnumValueConverterTest {

	private static final Enum<?> ENUM = RetentionPolicy.RUNTIME;
	private static final String ENUM_AS_STRING = "java.lang.annotation.RetentionPolicy-RUNTIME";

	@TestedObject
	private EnumValueConverter converter;

	@Test
	public void stringToObjectConverteertStringNaarOvereenkomstigeEnumWaarde() {
		Object enumWaarde = converter.stringToObject(ENUM_AS_STRING);

		BaseAssert.assertIsInstance(RetentionPolicy.class, enumWaarde);
		assertEquals(ENUM, enumWaarde);
	}

	@Test
	public void objectToStringConverteertEnumWaardeNaarString() {
		String stringWaarde = converter.objectToString(ENUM);

		assertEquals(ENUM_AS_STRING, stringWaarde);
	}

}
