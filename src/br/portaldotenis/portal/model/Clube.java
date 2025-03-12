package br.portaldotenis.portal.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

/**
 * @author Gabriel Soares
 * @author Thiago Holanda
 * 
 */
@Entity
public class Clube {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String nome;

	@OneToOne(cascade = CascadeType.PERSIST, orphanRemoval = true)
	private Endereco endereco;

	/*@OneToMany(mappedBy = "clube", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	List<Tenista> tenistas;*/

	private String visao;
	private String meta;

	public Clube() {
	}

	public Clube(String nome) {
		this.nome = nome;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getVisao() {
		return visao;
	}

	public void setVisao(String visao) {
		this.visao = visao;
	}

	public String getMeta() {
		return meta;
	}

	public void setMeta(String meta) {
		this.meta = meta;
	}

	/*
	public List<Tenista> getTenistas() {
		return tenistas;
	}

	public void setTenistas(List<Tenista> tenistas) {
		this.tenistas = tenistas;
	}
	 */
}
