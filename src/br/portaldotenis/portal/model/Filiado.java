package br.portaldotenis.portal.model;


import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import br.portaldotenis.portal.controller.TenistaController;


/**
 * Classe responsável por modelar uma inscrição no torneio do sistema
 *  
 * @author Thiago Holanda
 *
 */

@Entity
@Table(uniqueConstraints = { @UniqueConstraint( columnNames = { "tenista_id", "anoReferencia" } ) } )
public class Filiado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull (message = "Escolha um tenista")
	@ManyToOne
	protected Tenista tenista;

	@NotNull
	@Temporal(TemporalType.DATE)
	private Calendar data;

	private boolean bolsista;
	
	private Integer anoReferencia;
	private String observacao;
	private Double valorPago;
	private Double valorLiquido;
	
	/**
	 * @return id do objeto
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            define id do objeto
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
	 * @param tenista
	 *            define tenista do objeto
	 */
	public void setTenista(Tenista tenista) {
		this.tenista = tenista;
	}

	/**
	 * @return data do objeto
	 */
	public Calendar getData() {
		return data;
	}

	/**
	 * @param data
	 *            define data do objeto
	 */
	public void setData(Calendar data) {
		this.data = data;
	}


	/**
	 * @return bolsista do objeto
	 */
	public boolean isBolsista() {
		return bolsista;
	}

	/**
	 * @param bolsista
	 *            define bolsista do objeto
	 */
	public void setBolsista(boolean bolsista) {
		this.bolsista = bolsista;
	}
	
	/**
	 * @return anoReferencia do objeto
	 */
	public Integer getAnoReferencia() {
		return anoReferencia;
	}

	/**
	 * @param anoReferencia defini anoReferencia do objeto
	 */
	public void setAnoReferencia(Integer anoReferencia) {
		this.anoReferencia = anoReferencia;
	}

	/**
	 * @return observacao do objeto
	 */
	public String getObservacao() {
		return observacao;
	}

	/**
	 * @param observacao defini observacao do objeto
	 */
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	/**
	 * @return valorPago do objeto
	 */
	public Double getValorPago() {
		return valorPago;
	}

	/**
	 * @param valorPago defini valorPago do objeto
	 */
	public void setValorPago(Double valorPago) {
		this.valorPago = valorPago;
	}

	/**
	 * @return valorLiquido do objeto
	 */
	public Double getValorLiquido() {
		return valorLiquido;
	}

	/**
	 * @param valorLiquido defini valorLiquido do objeto
	 */
	public void setValorLiquido(Double valorLiquido) {
		this.valorLiquido = valorLiquido;
	}

	public boolean isFiliado(Tenista tenista, Integer anoReferencia){
		
		if(anoReferencia == null){
			anoReferencia = Calendar.getInstance().get(Calendar.YEAR);			
		}
		
		TenistaController controller = new TenistaController();

		if (controller.isFiliado(tenista,anoReferencia)){
			return true;
		}else{
			return false;
		}
		
	}
	
}