package br.portaldotenis.portal.model;

import javax.inject.Inject;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import br.portaldotenis.portal.dao.FaseDAO;

/**
 * Classe responsável por modelar as fases de um torneio
 *  
 * @author Thiago Holanda
 *
 */
@Entity
public class Fase {
	
	
	public static final String DESCRICAO = "descricao";

	public static final String ID = "id";

	public static final Integer GRUPO = 6;
	public static final Integer FINAIS_64 = 8;
	public static final Integer FINAIS_32 = 7;
	public static final Integer FINAIS_16 = 5;
	public static final Integer FINAIS_8= 4;
	public static final Integer FINAIS_4= 3;
	public static final Integer SEMIFINAIS = 2;
	public static final Integer FINAL = 1;
	public static final Integer NAO_INFORMADO = 0;

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
