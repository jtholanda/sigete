package br.portaldotenis.portal.model;

public class Dupla {

	private Tenista tenista;
	private Tenista tenistaParceiro;
	
	/**
	 * @return tenista do objeto
	 */
	public Tenista getTenista() {
		return tenista;
	}
	/**
	 * @param tenista defini tenista do objeto
	 */
	public void setTenista(Tenista tenista) {
		this.tenista = tenista;
	}
	/**
	 * @return tenistaParceiro do objeto
	 */
	public Tenista getTenistaParceiro() {
		return tenistaParceiro;
	}
	/**
	 * @param tenistaParceiro defini tenistaParceiro do objeto
	 */
	public void setTenistaParceiro(Tenista tenistaParceiro) {
		this.tenistaParceiro = tenistaParceiro;
	}
	
	
}
