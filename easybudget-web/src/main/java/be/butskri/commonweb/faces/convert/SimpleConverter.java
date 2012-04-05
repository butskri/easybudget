package be.butskri.commonweb.faces.convert;

interface SimpleConverter {

	Object stringToObject(String stringValue);

	String getNullString();

	String objectToString(Object objectValue);
}
