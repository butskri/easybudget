package be.butskri.easybudget.domain;

import be.butskri.commons.domain.DomainObject;
import be.butskri.commons.test.reflection.ReflectionUtils;


@SuppressWarnings("hiding")
public abstract class DomainObjectBuilder<DomainObject, U extends DomainObjectBuilder<DomainObject, U>> {
	
	private Long id;
	
	public DomainObject build() {
		DomainObject obj = buildObject();
		setId(obj, id);
		return obj;
	}
	
	protected abstract DomainObject buildObject();

	@SuppressWarnings("unchecked")
	public U withId(Long id) {
		this.id = id;
		return (U) this;
	}
	

	public static void setId(Object domainObject, Long id) {
		ReflectionUtils.setFieldValue(domainObject, "id", id);
	}
}
