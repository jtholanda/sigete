package br.portaldotenis.portal.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class MidiaInformativo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String url;
	private String legenda;
	@ManyToOne
	private Informativo informativo;
	
	
	
	public String getNomeArquivo(){
		String partes[] = url.split("/");
		String nomeArquivo = partes[partes.length-1];
		return nomeArquivo;
	}
	
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
	 * @return url do objeto
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url defini url do objeto
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	/**
	 * @return legenda do objeto
	 */
	public String getLegenda() {
		return legenda;
	}
	/**
	 * @param legenda defini legenda do objeto
	 */
	public void setLegenda(String legenda) {
		this.legenda = legenda;
	}
	/**
	 * @return informativo do objeto
	 */
	public Informativo getInformativo() {
		return informativo;
	}
	/**
	 * @param informativo defini informativo do objeto
	 */
	public void setInformativo(Informativo informativo) {
		this.informativo = informativo;
	}
	
	
	
		
	
}
