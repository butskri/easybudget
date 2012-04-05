package be.butskri.easybudget.domain.uitgaven;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;

import be.butskri.easybudget.domain.base.Categorie;

@Entity(name = "UITGAVE_CATEGORIE")
public class UitgaveCategorie implements Categorie {

	@Id
	@GeneratedValue(generator = "UitgaveCategorieSeq")
	@SequenceGenerator(name = "UitgaveCategorieSeq", sequenceName = "UITGAVE_CATEGORIE_SEQ", allocationSize = 1)
	private Long id;

	@Version
	private Long version;

	@Basic
	private String omschrijving;

	// @OneToMany(mappedBy = "parent")
	// private List<UitgaveCategorie> subcategorien;

	@ManyToOne(cascade = { CascadeType.PERSIST })
	@JoinColumn(name = "fk_parent")
	private UitgaveCategorie parent;

	public UitgaveCategorie(UitgaveCategorie parent, String omschrijving) {
		this.omschrijving = omschrijving;
		this.parent = parent;
		// this.subcategorien = new ArrayList<UitgaveCategorie>();
		// if (parent != null) {
		// parent.addSubcategorie(this);
		// }
	}

	UitgaveCategorie() {
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public String getOmschrijving() {
		return omschrijving;
	}

	public UitgaveCategorie getParent() {
		return parent;
	}

	@Override
	public Long getVersion() {
		return version;
	}

	public String getVolledigeOmschrijving() {
		if (parent == null) {
			return omschrijving;
		} else {
			return parent.getOmschrijving() + " - " + omschrijving;
		}
	}

	// private void addSubcategorie(UitgaveCategorie subcategorie) {
	// subcategorien.add(subcategorie);
	// }

	@Deprecated
	public void setId(Long id) {
		this.id = id;
	}

	// public List<UitgaveCategorie> getSubcategorien() {
	// return subcategorien;
	// }
	//
	// public List<UitgaveCategorie> getAlleSubcategorien() {
	// List<UitgaveCategorie> result = new ArrayList<UitgaveCategorie>();
	// result.addAll(subcategorien);
	// for (UitgaveCategorie uitgaveCategorie : subcategorien) {
	// result.addAll(uitgaveCategorie.getAlleSubcategorien());
	// }
	// return result;
	// }

}
