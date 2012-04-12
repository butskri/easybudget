package be.butskri.easybudget.domain.uitgaven;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;

import be.butskri.easybudget.domain.base.Categorie;

@Entity(name = "UITGAVE_CATEGORIE")
public class UitgaveCategorie implements Categorie {

	@Id
	@GeneratedValue(generator = "UitgaveCategorieSeq", strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name = "UitgaveCategorieSeq", sequenceName = "UITGAVE_CATEGORIE_SEQ", allocationSize = 1)
	private Long id;

	@Version
	private Long version;

	@Basic
	private String omschrijving;

	@ManyToOne(cascade = { CascadeType.PERSIST })
	@JoinColumn(name = "fk_parent")
	private UitgaveCategorie parent;

	public UitgaveCategorie(UitgaveCategorie parent, String omschrijving) {
		this.omschrijving = omschrijving;
		this.parent = parent;
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
}
