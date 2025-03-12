package br.portaldotenis.portal.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


/**
 * Classe responsável por modelar as pontuações do tenista
 *  
 * @author Thiago Holanda
 *
 */

@Entity
@Table(indexes = {@Index(columnList = "tenista_id, nivel_id, ano")})

public class Pontuacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull (message = "Escolha um tenista")
	@ManyToOne
	private Tenista tenista;
	
	@NotNull (message = "Escolha um nível")
	@ManyToOne
	private Nivel nivel;

	private Integer pontos;
	
	private Integer ano;
	
	public static final Integer CAMPEAO = 500;
	public static final Integer VICE_CAMPEAO = 300;
	public static final Integer SEMIFINAL = 180;
	public static final Integer FINAIS_4 = 90;
	public static final Integer FINAIS_8 = 45;
	public static final Integer FINAIS_16 = 20;
	public static final Integer FINAIS_32 = 10;
	public static final Integer FINAIS_64 = 5;
	


	

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
	 * @return tenista do objeto
	 */
	public Tenista getTenista() {
		return tenista;
	}

	/**
	 * @param tenista defini tenista do objeto
	 */
	public void setTenista(Tenista tenista) {
		this.tenista = tenista;
	}

	/**
	 * @return nivel do objeto
	 */
	public Nivel getNivel() {
		return nivel;
	}

	/**
	 * @param nivel defini nivel do objeto
	 */
	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}

	/**
	 * @return pontos do objeto
	 */
	public Integer getPontos() {
		return pontos;
	}

	/**
	 * @param pontos defini pontos do objeto
	 */
	public void setPontos(Integer pontos) {
		this.pontos = pontos;
	}

	/**
	 * @return ano do objeto
	 */
	public Integer getAno() {
		return ano;
	}

	/**
	 * @param ano defini ano do objeto
	 */
	public void setAno(Integer ano) {
		this.ano = ano;
	}
	
	

	
	
}