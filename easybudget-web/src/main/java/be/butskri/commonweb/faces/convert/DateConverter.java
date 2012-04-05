package be.butskri.commonweb.faces.convert;

import java.util.Date;

import be.butskri.commons.util.DateUtils;

public class DateConverter extends AbstractConverter implements SimpleConverter {

	public DateConverter() {
		setSimpleConverter(this);
	}

	@Override
	public String objectToString(Object objectValue) {
		return DateUtils.formatDate((Date) objectValue);
	}

	@Override
	public Object stringToObject(String stringValue) {
		return DateUtils.parseDate(stringValue);
	}
}
