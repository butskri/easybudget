package be.butskri.commonweb.faces.convert;

import be.butskri.commons.types.Bedrag;

public class BedragConverter extends AbstractConverter implements SimpleConverter {

	public BedragConverter() {
		setSimpleConverter(this);
	}

	@Override
	public String objectToString(Object objectValue) {
		Bedrag bedrag = (Bedrag) objectValue;
		return bedrag.format();
	}

	@Override
	public Object stringToObject(String stringValue) {
		return new Bedrag(stringValue);
	}

}
