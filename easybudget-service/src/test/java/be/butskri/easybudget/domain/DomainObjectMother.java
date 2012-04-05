package be.butskri.easybudget.domain;

import be.butskri.commons.domain.DomainObject;
import be.butskri.commons.test.reflection.ReflectionUtils;


public class DomainObjectMother {

	public static void setId(DomainObject domainObject, Long id) {
		ReflectionUtils.setFieldValue(domainObject, "id", id);
	}
}
