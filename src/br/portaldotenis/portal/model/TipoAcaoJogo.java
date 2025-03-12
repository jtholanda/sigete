package br.portaldotenis.portal.model;

public enum TipoAcaoJogo {

	INSERIR("Seu jogo foi agendado com sucesso"), EDITAR("ATENÇÃO: Houve mudança nas informações do seu jogo"), REMOVER("ATENÇÃO: Seu jogo foi removido por algum motivo"), INFORMAR_RESULTADO("Segue o resultado do seu jogo");

	private String nome;

	private TipoAcaoJogo(String nome) {
		this.nome = nome;
	}
	/**
	 * @return nome do objeto
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome
	 *            defini nome do objeto
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

}
