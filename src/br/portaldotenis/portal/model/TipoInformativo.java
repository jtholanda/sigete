package br.portaldotenis.portal.model;

/**
 * Enum responsável por definir os tipos de níveis dos tenistas e torneios
 *  
 * @author Thiago Holanda
 *
 */
public enum TipoInformativo {

	LOCAL (1,"Local"), INTERNACIONAL(2,"Internacional"), DICA(3,"Dica");
	
	private Integer id;
	private String nome;

	
	
	private TipoInformativo(Integer id, String nome) {
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

	private TipoInformativo(String nome) {
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
