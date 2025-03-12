/**
 * 
 */
package br.portaldotenis.portal.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Classe responsável por modelar as premiações de um torneio
 * 
 * @author Thiago
 *
 */
@Entity
public class Premiacao{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Float valor;
	
	private boolean trofeusEMedalhas;
	private boolean camisaTorneio;
	private boolean sorteioDeBrindes;
	private boolean premiosEmProdutos;
	private boolean premiosParaTodasAsClasses;
	
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
	 * @return valor do objeto
	 */
	public Float getValor() {
		return valor;
	}
	/**
	 * @param valor defini valor do objeto
	 */
	public void setValor(Float valor) {
		this.valor = valor;
	}
	/**
	 * @return trofeusEMedalhas do objeto
	 */
	public boolean isTrofeusEMedalhas() {
		return trofeusEMedalhas;
	}
	/**
	 * @param trofeusEMedalhas defini trofeusEMedalhas do objeto
	 */
	public void setTrofeusEMedalhas(boolean trofeusEMedalhas) {
		this.trofeusEMedalhas = trofeusEMedalhas;
	}
	/**
	 * @return camisaTorneio do objeto
	 */
	public boolean isCamisaTorneio() {
		return camisaTorneio;
	}
	/**
	 * @param camisaTorneio defini camisaTorneio do objeto
	 */
	public void setCamisaTorneio(boolean camisaTorneio) {
		this.camisaTorneio = camisaTorneio;
	}
	/**
	 * @return sorteioDeBrindes do objeto
	 */
	public boolean isSorteioDeBrindes() {
		return sorteioDeBrindes;
	}
	/**
	 * @param sorteioDeBrindes defini sorteioDeBrindes do objeto
	 */
	public void setSorteioDeBrindes(boolean sorteioDeBrindes) {
		this.sorteioDeBrindes = sorteioDeBrindes;
	}
	/**
	 * @return premiosEmProdutos do objeto
	 */
	public boolean isPremiosEmProdutos() {
		return premiosEmProdutos;
	}
	/**
	 * @param premiosEmProdutos defini premiosEmProdutos do objeto
	 */
	public void setPremiosEmProdutos(boolean premiosEmProdutos) {
		this.premiosEmProdutos = premiosEmProdutos;
	}
	/**
	 * @return premiosParaTodasAsClasses do objeto
	 */
	public boolean isPremiosParaTodasAsClasses() {
		return premiosParaTodasAsClasses;
	}
	/**
	 * @param premiosParaTodasAsClasses defini premiosParaTodasAsClasses do objeto
	 */
	public void setPremiosParaTodasAsClasses(boolean premiosParaTodasAsClasses) {
		this.premiosParaTodasAsClasses = premiosParaTodasAsClasses;
	}
	
	

}
