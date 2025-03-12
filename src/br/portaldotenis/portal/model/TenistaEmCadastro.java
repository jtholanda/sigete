package br.portaldotenis.portal.model;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;


/**
 * Classe que gerencia na sess�o o tenista que est� em processo de cadastro
 * 
 *@author Thiago Holanda
 *
 * */

@SessionScoped
public class TenistaEmCadastro implements Serializable {

	Tenista tenista;

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
	
	
	
}
