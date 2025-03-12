/**
 * 
 */
package br.portaldotenis.portal.model;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

/**
 * Classe responsável por modelar um professor
 * 
 * @author Thiago Holanda
 * @author Gabriel Soares
 *
 */
@Entity
public class Professor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String nome;

	@AttributeOverrides({ @AttributeOverride(name = "numero", column = @Column(name = "telefonePrincipal")),
			@AttributeOverride(name = "operadora", column = @Column(name = "operadoraPrincipal")) })
	@Embedded
	private Telefone telefonePrincipal;

	@AttributeOverrides({ @AttributeOverride(name = "numero", column = @Column(name = "telefoneSecundario")),
			@AttributeOverride(name = "operadora", column = @Column(name = "operadoraSecundaria")) })
	@Embedded
	private Telefone telefoneSecundario;

	private Integer anoInicio;

	private String email;

	public Professor() {
	}

	public Professor(String nome) {
		this.nome = nome;
	}

	/**
	 * @return o id do objeto
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            defini o id do objeto
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return o nome do objeto
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome
	 *            defini o nome do objeto
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	public Telefone getTelefonePrincipal() {
		return telefonePrincipal;
	}

	public void setTelefonePrincipal(Telefone telefonePrincipal) {
		this.telefonePrincipal = telefonePrincipal;
	}

	public Telefone getTelefoneSecundario() {
		return telefoneSecundario;
	}

	public void setTelefoneSecundario(Telefone telefoneSecundario) {
		this.telefoneSecundario = telefoneSecundario;
	}

	/**
	 * @return o anoInicio do objeto
	 */
	public Integer getAnoInicio() {
		return anoInicio;
	}

	/**
	 * @param anoInicio
	 *            defini o anoInicio do objeto
	 */
	public void setAnoInicio(Integer anoInicio) {
		this.anoInicio = anoInicio;
	}

	/**
	 * @return o email do objeto
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            defini o email do objeto
	 */
	public void setEmail(String email) {
		this.email = email;
	}

}
