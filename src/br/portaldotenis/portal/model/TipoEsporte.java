package br.portaldotenis.portal.model;

/**
 * Enum responsável por definir os tipos de níveis dos tenistas e torneios
 *  
 * @author Thiago Holanda
 *
 */
public enum TipoEsporte {

	TENNIS ("Tênis de quadra"), BEACH_TENNIS("Tênis de areia");
	
	private String nome;

	private TipoEsporte(String nome) {
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
