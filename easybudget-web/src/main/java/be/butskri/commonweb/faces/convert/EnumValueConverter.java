package be.butskri.commonweb.faces.convert;

public class EnumValueConverter extends AbstractConverter implements SimpleConverter {

	public EnumValueConverter() {
		setSimpleConverter(this);
	}

	@Override
	public String objectToString(Object objectValue) {
		Enum<?> e = (Enum<?>) objectValue;
		return e.getClass().getName() + "-" + e.name();
	}

	@Override
	@SuppressWarnings("unchecked")
	public Object stringToObject(String stringValue) {
		String[] splitParts = stringValue.split("-");
		try {
			Class<Enum> clazz = (Class<Enum>) Class.forName(splitParts[0]);
			return Enum.valueOf(clazz, splitParts[1]);
		} catch (ClassNotFoundException ex) {
			throw new RuntimeException("Conversion of " + stringValue + " failed", ex);
		}
	}

}
