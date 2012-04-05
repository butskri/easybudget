package be.butskri.commonweb.faces.convert;

public class StringConverter extends AbstractConverter implements SimpleConverter {

	public StringConverter() {
		setSimpleConverter(this);
	}

	@Override
	public String objectToString(Object objectValue) {
		return (String) objectValue;
	}

	@Override
	public Object stringToObject(String stringValue) {
		return stringValue.trim();
	}

}
