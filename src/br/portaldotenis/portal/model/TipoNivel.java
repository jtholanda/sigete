package br.portaldotenis.portal.model;

/**
 * Enum respons�vel por definir os tipos de n�veis dos tenistas e torneios
 *  
 * @author Thiago Holanda
 *
 */
public enum TipoNivel {

	TECNICO ("T�cnico"), IDADE("Idade"), MASCULINO("Masculino"), FEMININO("Feminino"), GRUPO("Grupo"), PROFESSOR("Professor"), DUPLAS("Duplas");
	
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
