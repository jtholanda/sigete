package br.portaldotenis.portal.exception;

/**
 * Exceção que informa que houve erro ao criptografar a senha
 * 
 * @author Gabriel Soares
 *
 */

public class EncryptationException extends Exception {

	private static final long serialVersionUID = 1L;

	public EncryptationException() {
		super("Erro ao criptografar");
	}
}
