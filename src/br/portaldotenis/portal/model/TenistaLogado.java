package br.portaldotenis.portal.model;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

/**
 * Classe responsável por armazenar o Tenista logado na sessão
 * 
 * @author Gabriel Soares
 * 
 */

@SessionScoped
public class TenistaLogado implements Serializable {

	private Tenista tenista;

	//@Inject
	//HttpSession session;

	public void loga(Tenista tenista) {
		this.tenista = tenista;
		// session.setAttribute("tenistaLogado", this.tenista);

	}

	public boolean isLogado() {
		return this.tenista != null;
	}

	public Tenista getTenista() {
		return tenista;
	}

	public void desloga() {
		this.tenista = null;
	}

	public boolean isAdmin() {
		return tenista.getTipo() == TipoUsuario.ADMIN;
	}

	public boolean isOrganizador() {
		return tenista.getTipo() == TipoUsuario.ORGANIZADOR || tenista.getTipo() == TipoUsuario.ADMIN;
	}

	public boolean isAdminOrOrganizadorProprietario(Torneio torneio) {
		// TODO verificar controle de acesso
		if (tenista != null && (isAdmin() || torneio.getOrganizadorDoTorneio(tenista))) {
			return true;
		} else {
			return false;
		}

	}
	public void setTenista(Tenista tenista){
		this.tenista = tenista;
	}

}
