package br.portaldotenis.portal.model;

/**
 * Enum responsável por definir os sexos
 *  
 * @author Thiago Holanda
 *
 */
public enum Sexo {

	MASCULINO ("Masculino"), FEMININO("Feminino");
	
	private String nome;

	private Sexo(String nome) {
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
