package br.portaldotenis.portal.model;

/**
 * Enum responsável por definir as categorias de um torneio
 *  
 * @author Thiago Holanda
 *
 */
public enum CategoriaTorneio {

	SIMPLES (1,"Simples"), DUPLAS(2,"Duplas"), EQUIPES(3,"Equipes"), HANDICAP(4,"Handicap"), SIMPLES_DUPLAS(5,"Simples/Duplas");
	
	private Integer id;
	private String nome;

	private CategoriaTorneio(Integer id,String nome) {
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
