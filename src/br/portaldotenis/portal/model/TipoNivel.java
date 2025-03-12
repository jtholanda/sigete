package br.portaldotenis.portal.model;

/**
 * Enum responsável por definir os tipos de níveis dos tenistas e torneios
 *  
 * @author Thiago Holanda
 *
 */
public enum TipoNivel {

	TECNICO ("Técnico"), IDADE("Idade"), MASCULINO("Masculino"), FEMININO("Feminino"), GRUPO("Grupo"), PROFESSOR("Professor"), DUPLAS("Duplas");
	
	private String nome;

	private TipoNivel(String nome) {
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
