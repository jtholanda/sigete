package br.portaldotenis.portal.model;

/**
 * Enum responsável por definir as categorias de um torneio
 *  
 * @author Thiago Holanda
 *
 */
public enum TipoMidiaTorneio {

	IMAGEM (1,"Imagem"), VIDEO (2,"Vídeo"), REGULAMENTO(3,"Regulamento"), BANNER(4,"Banner"), PATROCINIO(5,"Patrocínio"), CHAVEAMENTO (6,"Chaveamento"), INSCRITOS (7,"Inscritos");
	
	private Integer id;
	private String nome;

	private TipoMidiaTorneio(Integer id,String nome) {
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
