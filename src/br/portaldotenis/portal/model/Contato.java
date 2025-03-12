package br.portaldotenis.portal.model;

public class Contato {

	
	private String nome;
	private String email;
	private String assunto;
	private String mensagem;
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
	/**
	 * @return email do objeto
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email defini email do objeto
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return assunto do objeto
	 */
	public String getAssunto() {
		return assunto;
	}
	/**
	 * @param assunto defini assunto do objeto
	 */
	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}
	/**
	 * @return mensagem do objeto
	 */
	public String getMensagem() {
		return mensagem;
	}
	/**
	 * @param mensagem defini mensagem do objeto
	 */
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	
}
