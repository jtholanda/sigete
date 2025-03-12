package br.portaldotenis.portal.model;

/**
 * @author Gabriel Soares
 * @author Thiago Holanda
 *
 */
public enum TipoUsuario {

	PADRAO("Tenista"), ORGANIZADOR("Tenista organizador de torneios"), ADMIN("Tenista administrador");

	private String nome;
	
	private TipoUsuario(String nome) {
		this.nome = nome;
	}
	
	/**
	 * @return nome do objeto
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome defini nome do objeto
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	
}

	