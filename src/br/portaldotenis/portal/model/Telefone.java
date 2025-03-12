package br.portaldotenis.portal.model;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

/**
 * @author Gabriel Soares
 * 
 */
@Embeddable
public class Telefone {

	@NotNull(message = "É necessário informar um número de telefone para contato")
	private String numero;

	@Enumerated(EnumType.STRING)
	private Operadora operadora;

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Operadora getOperadora() {
		return operadora;
	}

	public void setOperadora(Operadora operadora) {
		this.operadora = operadora;
	}

	public enum Operadora {

		TIM("Tim"), VIVO("Vivo"), OI("Oi"), CLARO("Claro"), NET("Net");

		String nome;

		private Operadora(String nome) {
			this.nome = nome;
		}

		/**
		 * @return nome do objeto
		 */
		public String getNome() {
			return nome;
		}

		/**
		 * @param nome defini nome do objeto
		 */
		public void setNome(String nome) {
			this.nome = nome;
		}
		

	}
}
