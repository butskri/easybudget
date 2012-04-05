package be.butskri.commonweb.faces.validator;

import static org.junit.Assert.*;

import org.junit.Test;

import be.butskri.commonweb.faces.validator.RegExPattern;

public class RegExPatternTest {

	@Test
	public void naamKaraktersLaatNullValueToe() {
		assertTrue(RegExPattern.NAAMKARAKTERS.allows(null));
	}

	@Test
	public void naamKaraktersLaatLegeStringToe() {
		assertTrue(RegExPattern.NAAMKARAKTERS.allows(""));
	}

	@Test
	public void naamKaraktersLaatAlfabetischeKaraktersToe() {
		assertTrue(RegExPattern.NAAMKARAKTERS.allows("abcABC"));
	}

	@Test
	public void naamKaraktersLaatAlfabetischeKaraktersMetAccentenToe() {
		assertTrue(RegExPattern.NAAMKARAKTERS.allows("ÈËÍÎÁ‚‰‡ÔÓÙˆ˚¸˘"));
		assertTrue(RegExPattern.NAAMKARAKTERS.allows("…» À«¬ƒ¿œŒ‘÷€‹Ÿ"));
	}

	@Test
	public void naamKaraktersLaatKoppeltekenToe() {
		assertTrue(RegExPattern.NAAMKARAKTERS.allows("-"));
	}

	@Test
	public void naamKaraktersLaatEnkeleQuoteToe() {
		assertTrue(RegExPattern.NAAMKARAKTERS.allows("'"));
	}

	@Test
	public void naamKaraktersLaatSpatieToe() {
		assertTrue(RegExPattern.NAAMKARAKTERS.allows(" "));
	}

	@Test
	public void naamKaraktersLaatGeenApestaartToe() {
		assertFalse(RegExPattern.NAAMKARAKTERS.allows("@"));
	}

	@Test
	public void naamKaraktersLaatGeenEurotekenToe() {
		assertFalse(RegExPattern.NAAMKARAKTERS.allows("Ä"));
	}

	@Test
	public void naamKaraktersLaatPuntToe() {
		assertTrue(RegExPattern.NAAMKARAKTERS.allows("."));
	}

	@Test
	public void naamKaraktersLaatGeenGetallenToe() {
		assertFalse(RegExPattern.NAAMKARAKTERS.allows("123"));
	}

	@Test
	public void ondernemingsnummerLaatNullToe() {
		assertTrue(RegExPattern.ONDERNEMINGSNUMMER.allows(null));
	}

	@Test
	public void ondernemingsnummerLaatDeLegeStringToe() {
		assertTrue(RegExPattern.ONDERNEMINGSNUMMER.allows(""));
	}

	@Test
	public void ondernemingsnummerLaatEnkelCijfersToe() {
		assertFalse(RegExPattern.ONDERNEMINGSNUMMER.allows("abcdefghij"));
		assertTrue(RegExPattern.ONDERNEMINGSNUMMER.allows("0123456789"));
	}

	@Test
	public void ondernemingsnummerMoetVanLengte10Zijn() {
		assertFalse(RegExPattern.ONDERNEMINGSNUMMER.allows("012345"));
		assertTrue(RegExPattern.ONDERNEMINGSNUMMER.allows("0123456789"));
	}

	@Test
	public void ondernemingsnummerMoetBeginnenMetZeroOfEen() {
		assertTrue(RegExPattern.ONDERNEMINGSNUMMER.allows("0123456789"));
		assertTrue(RegExPattern.ONDERNEMINGSNUMMER.allows("1234567890"));
		assertFalse(RegExPattern.ONDERNEMINGSNUMMER.allows("2123456789"));
		assertFalse(RegExPattern.ONDERNEMINGSNUMMER.allows("3123456789"));
	}

	@Test
	public void alfanumeriekVeldLaatEnkelAlfanumeriekeTekensToe() {
		assertTrue(RegExPattern.ALFANUMERIEK.allows("A"));
		assertTrue(RegExPattern.ALFANUMERIEK.allows("1"));
		assertTrue(RegExPattern.ALFANUMERIEK.allows("-"));
		assertTrue(RegExPattern.ALFANUMERIEK.allows("A1"));
		assertTrue(RegExPattern.ALFANUMERIEK.allows("A313ssd"));
		assertTrue(RegExPattern.ALFANUMERIEK.allows("2008-2009"));
		assertFalse(RegExPattern.ALFANUMERIEK.allows("Èpokopk1"));
		assertFalse(RegExPattern.ALFANUMERIEK.allows("ioi 897"));
		assertFalse(RegExPattern.ALFANUMERIEK.allows(" 31sqdfds"));
	}

	@Test
	public void postcodeLigtTussen1000En9999() {
		assertTrue(RegExPattern.POSTCODE.allows("1000"));
		assertTrue(RegExPattern.POSTCODE.allows("9999"));
		assertTrue(RegExPattern.POSTCODE.allows("3000"));
		assertFalse(RegExPattern.POSTCODE.allows("300"));
		assertFalse(RegExPattern.POSTCODE.allows(" 300"));
		assertFalse(RegExPattern.POSTCODE.allows("10000"));
	}
}
