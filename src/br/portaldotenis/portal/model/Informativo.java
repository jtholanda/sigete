package br.portaldotenis.portal.model;


import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;


/**
 * Classe responsável por modelar uma inscrição no torneio do sistema
 *  
 * @author Thiago Holanda
 *
 */

@Entity
@Table
public class Informativo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@ManyToOne
	protected Tenista tenista;

	@NotNull
	@Temporal(TemporalType.DATE)
	private Calendar data;
	
	private String titulo;
	@Column(length = 255)
	private String resumo;	
	@Column(length = 10_000)
	private String conteudo;
	@Enumerated(EnumType.STRING)
	private TipoInformativo tipoInformativo;
	private String imagemPrincipal;
	
	@OneToMany(mappedBy="informativo")
	private List<MidiaInformativo> midiasInformativo;
	
	
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
	 * @return data do objeto
	 */
	public Calendar getData() {
		return data;
	}
	/**
	 * @param data defini data do objeto
	 */
	public void setData(Calendar data) {
		this.data = data;
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
	 * @return resumo do objeto
	 */
	public String getResumo() {
		return resumo;
	}
	/**
	 * @param resumo defini resumo do objeto
	 */
	public void setResumo(String resumo) {
		this.resumo = resumo;
	}
	/**
	 * @return conteudo do objeto
	 */
	public String getConteudo() {
		return conteudo;
	}
	/**
	 * @param conteudo defini conteudo do objeto
	 */
	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}
	/**
	 * @return tipoInformativo do objeto
	 */
	public TipoInformativo getTipoInformativo() {
		return tipoInformativo;
	}
	/**
	 * @param tipoInformativo defini tipoInformativo do objeto
	 */
	public void setTipoInformativo(TipoInformativo tipoInformativo) {
		this.tipoInformativo = tipoInformativo;
	}
	/**
	 * @return imagemPrincipal do objeto
	 */
	public String getImagemPrincipal() {
		return imagemPrincipal;
	}
	/**
	 * @param imagemPrincipal defini imagemPrincipal do objeto
	 */
	public void setImagemPrincipal(String imagemPrincipal) {
		this.imagemPrincipal = imagemPrincipal;
	}
	/**
	 * @return midiasInformativo do objeto
	 */
	public List<MidiaInformativo> getMidiasInformativo() {
		return midiasInformativo;
	}
	/**
	 * @param midiasInformativo defini midiasInformativo do objeto
	 */
	public void setMidiasInformativo(List<MidiaInformativo> midiasInformativo) {
		this.midiasInformativo = midiasInformativo;
	}
	
	
	
	
	
	
}