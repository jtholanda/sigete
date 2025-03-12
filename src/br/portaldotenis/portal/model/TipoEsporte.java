package br.portaldotenis.portal.model;

/**
 * Enum respons�vel por definir os tipos de n�veis dos tenistas e torneios
 *  
 * @author Thiago Holanda
 *
 */
public enum TipoEsporte {

	TENNIS ("T�nis de quadra"), BEACH_TENNIS("T�nis de areia");
	
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
