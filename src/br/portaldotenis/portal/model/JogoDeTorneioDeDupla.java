package br.portaldotenis.portal.model;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class JogoDeTorneioDeDupla extends JogoDeTorneio {

	@ManyToOne
	Tenista tenistaParceiroUm;

	@ManyToOne
	Tenista tenistaParceiroDois;

	public JogoDeTorneioDeDupla() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getPrimeiraDupla() {
		if (tenistaUm != null && tenistaParceiroUm != null) {
			return tenistaUm.getNomeRanking() + " / " + tenistaParceiroUm.getNomeRanking();
		} else {
			return "";
		}
	}

	public String getSegundaDupla() {

		if (tenistaDois != null && tenistaParceiroDois != null) {
			return tenistaDois.getNomeRanking() + " / " + tenistaParceiroDois.getNomeRanking();
		} else {
			return "";
		}
	}

	/**
	 * @return tenistaParceiroUm do objeto
	 */
	public Tenista getTenistaParceiroUm() {
		return tenistaParceiroUm;
	}

	/**
	 * @param tenistaParceiroUm
	 *            defini tenistaParceiroUm do objeto
	 */
	public void setTenistaParceiroUm(Tenista tenistaParceiroUm) {
		this.tenistaParceiroUm = tenistaParceiroUm;
	}

	/**
	 * @return tenistaParceiroDois do objeto
	 */
	public Tenista getTenistaParceiroDois() {
		return tenistaParceiroDois;
	}

	/**
	 * @param tenistaParceiroDois
	 *            defini tenistaParceiroDois do objeto
	 */
	public void setTenistaParceiroDois(Tenista tenistaParceiroDois) {
		this.tenistaParceiroDois = tenistaParceiroDois;
	}

}
