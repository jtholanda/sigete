package br.portaldotenis.portal.model;


/**
 * Enum responsável por definir as formas de como o tenista ficou sabendo do torneio
 * 
 * @author Thiago Holanda
 *
 */
public enum ConhecimentoTorneio {

	NAO_INFORMADO("Não Informado"), POR_EMAIL("Por Email"), COM_PROFESSOR("Com professor"), NO_CLUBE("No clube"), COM_AMIGO(
			"Com um amigo"), PELO_PORTAL ("PELO_PORTAL"), OUTROS("Outros");

	private String nome;

	private ConhecimentoTorneio(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
