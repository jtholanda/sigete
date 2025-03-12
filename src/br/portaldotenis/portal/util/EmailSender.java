package br.portaldotenis.portal.util;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import br.com.caelum.vraptor.simplemail.Mailer;
import br.portaldotenis.portal.dao.ConfirmacaoEmailDAO;
import br.portaldotenis.portal.model.ConfirmacaoEmail;
import br.portaldotenis.portal.model.Tenista;

/**
 * Classe que centraliza todas as tarefas relacionadas a envio de Email
 * 
 * @author Gabriel Soares
 * @author Thiago Holanda
 * 
 */

@RequestScoped
public class EmailSender {

	@Inject
	private HttpServletRequest request;
	@Inject
	private ConfirmacaoEmailDAO confirmacaoEmailDao;
	@Inject
	private Mailer mailer;
	
	public static final String UTF_8 = "UTF-8";
	public static final String TEXT_HTML = "text/html";
	public static final String TEXT_PLAIN = "text/plain";

	/**
	 * Envia link de confirmação de Email ao Usuário
	 * 
	 * @param tenista
	 *            que precisa ter seu Email confirmado
	 */
	public void confirmaEmail(Tenista tenista) {

		String mensagem = "Prezado(a) tenista "
				+ tenista.getNome()
				+ ",\n\n"
				+ "O SIGETE é um sistema gerenciador de competições de tênis e beach tennis que está "
				+ "sendo desenvolvido junto aos clubes e academias de tênis e BT da Paraíba. O site gerencia informações dos tenistas, torneios, rankings, pontuações, jogos, resultados, inscrições etc.\n\n"
				+ "Fique atento nas novidades disponibilizadas no site. Todas semanas teremos novas informações postadas.\n\n "
				+ "Os torneios abertos que sigam o regulamento presente no site contará pontos para os participantes, e o sistema irá classificá-lo de acordo com a pontuação dos tenistas e por nível técnico. \n\n"
				+ "As competições agora são mais emocionantes." + "Guarde os seus dados de acesso: \n" + "Login: "
				+ tenista.getEmail() + "\n" + "Senha: " + tenista.getSenhaLimpa() + "\n";

		if (!tenista.isEmailConfirmado()) {

			if (tenista.naoPossuiCpf()) {

				mensagem += "O seu cadastro foi realizado no sistema, mas precisa ser confirmado pela administração, pois o CPF informado não é do titular da conta. Aguarde que entraremos em contato.";

			} else {

				String linkDeConfirmacao = "http://" + request.getHeader("HOST") + request.getContextPath()
						+ "/tenista/confirma-email/:codigo";

				mensagem += "Para confirmar o seu cadastro, acesse: " + linkDeConfirmacao;

			}
			enviaSimpleMail(tenista, EmailAction.CONFIRMAR, "Confirmação de Cadastro", mensagem);

		} else {

			mensagem += "O seu cadastro foi confirmado. \n"
					+ "Acesse: " + Utils.SITE;

			enviaSimpleMail(tenista, EmailAction.CONFIRMADO, "Confirmação de Cadastro", mensagem);

		}
	}

	/**
	 * Envia link de recuperação de senha ao Usuário
	 * 
	 * @param tenista
	 *            que solicitou recuperação de senha
	 */

	public void recuperaSenha(Tenista tenista) {

		String linkDeRecuperacao = "http://" + request.getHeader("HOST") + request.getContextPath()
				+ "/tenista/recupera-senha/:codigo";

		String mensagem = "Para recuperar sua senha, acesse: " + linkDeRecuperacao;

		enviaSimpleMail(tenista, EmailAction.RECUPERAR_SENHA, "Recuperação de Senha", mensagem);
	}

	/**
	 * Centraliza tarefa de enviar um Email Simples
	 * 
	 * @param tenista
	 *            ao qual será enviado o Email
	 * @param emailAction
	 *            A finalidade do email
	 * @param assunto
	 *            do email
	 * @param mensagem
	 *            O corpo do email
	 */
	private void enviaSimpleMail(Tenista tenista, EmailAction emailAction, String assunto, String mensagem) {

		if (((!tenista.naoPossuiCpf() && emailAction == EmailAction.CONFIRMAR))
				|| emailAction == EmailAction.RECUPERAR_SENHA) {
			ConfirmacaoEmail confirmacaoEmail = confirmacaoEmailDao.pegaCodigoExistente(tenista, emailAction);

			if (confirmacaoEmail == null) {
				confirmacaoEmail = new ConfirmacaoEmail();
				confirmacaoEmail.setTenista(tenista);
				confirmacaoEmail.setEmailAction(emailAction);
				confirmacaoEmailDao.adiciona(confirmacaoEmail);
			}

			mensagem = mensagem.replace(":codigo", confirmacaoEmail.getCodigo());
		}

		enviaEmailPadrao(tenista, assunto, mensagem, UTF_8, TEXT_PLAIN);

	}

	/**
	 * Método que envia um e-mail com assunto e mensagem para um tenista
	 * 
	 * @param tenista
	 * @param assunto
	 * @param mensagem
	 */
	public void enviaEmailPadrao(Tenista tenista, String assunto, String mensagem, String charset, String contentType) {

			try {
				SimpleEmail email = new SimpleEmail();
				email.setSubject(assunto);
				email.setMsg(mensagem);
				
				if (tenista != null) {
					email.addTo(tenista.getEmail());
				}else{
					email.addTo(Utils.EMAIL);
				}
				
				email.setCharset(charset);
				email.updateContentType(contentType);
				mailer.send(email);
			} catch (EmailException e) {
				e.printStackTrace();
			}
		}

	public void enviaEmailPadrao(Tenista tenista, SimpleEmail email, String assunto, String mensagem, String charset, String contentType) {

		try {
			
			email.setSubject(assunto);
			email.setMsg(mensagem);
			
			if (tenista != null) {
				email.addTo(tenista.getEmail());
			}else{
				email.addTo(Utils.EMAIL);
			}
			
			email.setCharset(charset);
			email.updateContentType(contentType);
			mailer.send(email);
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}


}