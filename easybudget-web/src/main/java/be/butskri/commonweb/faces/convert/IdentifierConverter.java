package be.butskri.commonweb.faces.convert;

import be.butskri.commons.types.Identifier;

public class IdentifierConverter extends AbstractConverter implements SimpleConverter {

	public IdentifierConverter() {
		setSimpleConverter(this);
	}

	@Override
	public String objectToString(Object objectValue) {
		Identifier identifier = (Identifier) objectValue;
		return identifier.toString();
	}

	@Override
	public Object stringToObject(String stringValue) {
		return new Identifier(Long.valueOf(stringValue));
	}

}
