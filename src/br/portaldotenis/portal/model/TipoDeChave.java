package br.portaldotenis.portal.model;

/**
 * Enum responsável por definir as categorias de um torneio
 *  
 * @author Thiago Holanda
 *
 */
public enum TipoDeChave {

	ELIMINATORIO (1,"Eliminatório"), GRUPO(2,"Grupos"), MISTO(3,"Misto");
	
	private Integer id;
	private String nome;

	private TipoDeChave(Integer id,String nome) {
		this.id = id;
		this.nome = nome;
	}

	
	/**
	 * @return id do objeto
	 */
	public Integer getId() {
		return id;
	}


	/**
	 * @param id defini id do objeto
	 */
	public void setId(Integer id) {
		this.id = id;
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
