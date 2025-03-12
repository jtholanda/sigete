package br.portaldotenis.portal.model;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

@Entity
@Inheritance(strategy =InheritanceType.JOINED)  
public class JogoDeTorneio extends Jogo {

	
	@ManyToOne
	Fase fase;
	
	@ManyToOne
	Torneio torneio;
	
	@ManyToOne
	Nivel nivel;

	
	protected JogoDeTorneio() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	/**
	 * @return fase do objeto
	 */
	public Fase getFase() {
		return fase;
	}

	/**
	 * @param fase defini fase do objeto
	 */
	public void setFase(Fase fase) {
		this.fase = fase;
	}

	/**
	 * @return torneio do objeto
	 */
	public Torneio getTorneio() {
		return torneio;
	}

	/**
	 * @param torneio defini torneio do objeto
	 */
	public void setTorneio(Torneio torneio) {
		this.torneio = torneio;
	}

	/**
	 * @return nivel do objeto
	 */
	public Nivel getNivel() {
		return nivel;
	}

	/**
	 * @param nivel defini nivel do objeto
	 */
	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}



	
	
}
