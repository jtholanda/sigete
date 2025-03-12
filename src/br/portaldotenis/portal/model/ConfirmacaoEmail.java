package br.portaldotenis.portal.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.RandomStringUtils;

import br.portaldotenis.portal.util.EmailAction;

/**
 * @author Gabriel Soares
 *
 */
@Entity
public class ConfirmacaoEmail {

	public ConfirmacaoEmail() {
		dataPedido = new Date();
		this.codigo = RandomStringUtils.randomAlphanumeric(30);
	}

	@Id
	private String codigo;
	
	@Temporal(TemporalType.DATE)
	private Date dataPedido;
	
	@OneToOne(optional = false)
	@NotNull
	private Tenista tenista;
	
	@Enumerated(EnumType.STRING)
	private EmailAction emailAction;

	public String getCodigo() {
		return codigo;
	}

	public Date getDataPedido() {
		return dataPedido;
	}

	public Tenista getTenista() {
		return tenista;
	}

	public void setTenista(Tenista tenista) {
		this.tenista = tenista;
	}

	public EmailAction getEmailAction() {
		return emailAction;
	}

	public void setEmailAction(EmailAction emailAction) {
		this.emailAction = emailAction;
	}
}
