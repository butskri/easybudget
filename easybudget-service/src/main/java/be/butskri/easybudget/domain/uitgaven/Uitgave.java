package be.butskri.easybudget.domain.uitgaven;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;

import be.butskri.commons.types.Bedrag;
import be.butskri.easybudget.domain.base.Geldstroom;

@Entity(name = "UITGAVE")
public class Uitgave implements Geldstroom {

	@Id
	@GeneratedValue(generator = "UitgaveSeq")
	@SequenceGenerator(name = "UitgaveSeq", sequenceName = "UITGAVE_SEQ", allocationSize = 1)
	private Long id;
	@Version
	private Long version;
	@Basic
	private Date datum;
	@Basic
	private BigDecimal bedrag;
	@ManyToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "fk_uitgave_categorie")
	private UitgaveCategorie categorie;

	public Uitgave(Date datum, Bedrag bedrag, UitgaveCategorie categorie) {
		this.datum = datum;
		this.categorie = categorie;
		this.bedrag = Bedrag.getValue(bedrag);
	}

	Uitgave() {
	}

	@Override
	public Bedrag getBedrag() {
		return Bedrag.getBedrag(bedrag);
	}

	@Override
	public UitgaveCategorie getCategorie() {
		return categorie;
	}

	@Override
	public Date getDatum() {
		return datum;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public Long getVersion() {
		return version;
	}

}
