package br.portaldotenis.portal.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Classe responsável por modelar os tipos de torneios existentes
 * 
 * @author Thiago Holanda
 *
 */
@Entity
public class TipoTorneio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Column(unique = true)
	private String descricao;
	
	@NotNull
	private Float pontuacaoMaxima;

	/**
	 * @return id do objeto
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id defini id do objeto
	 */
	public void setId(Long id) {
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

	/**
	 * @return pontuacaoMaxima do objeto
	 */
	public Float getPontuacaoMaxima() {
		return pontuacaoMaxima;
	}

	/**
	 * @param pontuacaoMaxima defini pontuacaoMaxima do objeto
	 */
	public void setPontuacaoMaxima(Float pontuacaoMaxima) {
		this.pontuacaoMaxima = pontuacaoMaxima;
	}
	
	
}
