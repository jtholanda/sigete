package br.portaldotenis.portal.model;

/**
 * Enumeração responsável por definir os estados de um torneio
 * 
 * @author Thiago Holanda
 *
 */
public enum SituacaoTorneio {

	A_INICIAR(1,"A iniciar"), INICIADO(2,"Iniciado"), CONCLUIDO(3,"Concluído"), CANCELADO(4,"Cancelado");
	
	private String nome;
	private Integer id;
	
	private SituacaoTorneio(Integer id,String nome) {
		this.id = id;
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
	
	
	
}
