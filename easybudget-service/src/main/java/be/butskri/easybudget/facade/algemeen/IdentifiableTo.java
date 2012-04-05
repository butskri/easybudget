package be.butskri.easybudget.facade.algemeen;

import java.io.Serializable;

import be.butskri.commons.types.Identifier;

public class IdentifiableTo implements Serializable {

	private Identifier id;

	public IdentifiableTo() {
	}

	public IdentifiableTo(Long id) {
		this(new Identifier(id));
	}

	public IdentifiableTo(Identifier id) {
		this.id = id;
	}

	public Identifier getId() {
		return id;
	}

	public void setId(Identifier id) {
		this.id = id;
	}

}
