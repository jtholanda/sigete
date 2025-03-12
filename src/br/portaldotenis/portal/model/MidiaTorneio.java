package br.portaldotenis.portal.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class MidiaTorneio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String titulo;
	private String url;
	@Enumerated(EnumType.STRING)
	private TipoMidiaTorneio tipoMidiaTorneio;
	@ManyToOne
	private Torneio torneio;
	private boolean ativo;
	
	
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
	 * @return titulo do objeto
	 */
	public String getTitulo() {
		return titulo;
	}
	/**
	 * @param titulo defini titulo do objeto
	 */
	public void setTitulo(String titulo) {
		this.titulo = titulo;
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
	 * @return tipoMidiaTorneio do objeto
	 */
	public TipoMidiaTorneio getTipoMidiaTorneio() {
		return tipoMidiaTorneio;
	}
	/**
	 * @param tipoMidiaTorneio defini tipoMidiaTorneio do objeto
	 */
	public void setTipoMidiaTorneio(TipoMidiaTorneio tipoMidiaTorneio) {
		this.tipoMidiaTorneio = tipoMidiaTorneio;
	}
	/**
	 * @return torneio do objeto
	 */
	public Torneio getTorneio() {
		return torneio;
	}
	/**
	 * @param torneio defini torneio do objeto
	 */
	public void setTorneio(Torneio torneio) {
		this.torneio = torneio;
	}
	/**
	 * @return ativo do objeto
	 */
	public boolean isAtivo() {
		return ativo;
	}
	/**
	 * @param ativo defini ativo do objeto
	 */
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	
	
}
