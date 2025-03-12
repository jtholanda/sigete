package br.portaldotenis.portal.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Colocacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotNull
	private String descricao;

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
	 * @return descricao do objeto
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao defini descricao do objeto
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
