package be.butskri.commonweb.faces.convert;

import be.butskri.commons.types.Rekeningnummer;

public class RekeningnummerConverter extends AbstractConverter implements SimpleConverter {

	public RekeningnummerConverter() {
		setSimpleConverter(this);
	}

	@Override
	public String objectToString(Object objectValue) {
		Rekeningnummer reknr = (Rekeningnummer) objectValue;
		return reknr.format();
	}

	@Override
	public Object stringToObject(String stringValue) {
		return new Rekeningnummer(stringValue);
	}

}
