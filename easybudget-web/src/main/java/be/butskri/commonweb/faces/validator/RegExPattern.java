package be.butskri.commonweb.faces.validator;

import org.apache.commons.lang.StringUtils;

public enum RegExPattern {

	NAAMKARAKTERS("([' a-zA-ZéèêëçâäàïîôöùûüÉÈÊËÇÂÄÀÏÎÔÖÛÜÙ-[.]])*", "error_regex_naamkarakters"),
	DATUMFORMAAT("\\d{2}/\\d{2}/\\d{4}", "javax.faces.converter.DateTimeConverter.DATE"), 
	ONDERNEMINGSNUMMER("[01]\\d{9}", "error_regex_ondernemingsnummer"), 
	ALFANUMERIEK("[a-zA-Z0-9-]*", "error_regex_alfanumeriek"), 
	POSTCODE("[1-9]\\d{3}", "error_ongeldige_postcode");

	private final String regExPattern;
	private final String message;

	RegExPattern(String regExPattern, String message) {
		this.regExPattern = regExPattern;
		this.message = message;
	}

	public String getRegExPattern() {
		return regExPattern;
	}

	public String getMessage() {
		return message;
	}

	public boolean allows(String string) {
		if (StringUtils.isEmpty(string)) {
			return true;
		} else {
			return string.matches(regExPattern);
		}
	}

}
