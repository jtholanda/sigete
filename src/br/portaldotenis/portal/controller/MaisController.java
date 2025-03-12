package br.portaldotenis.portal.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.portaldotenis.portal.model.Contato;
import br.portaldotenis.portal.model.Tenista;
import br.portaldotenis.portal.model.TenistaLogado;
import br.portaldotenis.portal.util.EmailSender;
import br.portaldotenis.portal.util.Utils;

/**
 * Controller que recebe requisições a página inicial
 * 
 * @author Gabriel Soares
 * @author Thiago Holanda
 * 
 */

@Controller
public class MaisController {

	@Inject
	private EmailSender emailSender;
	
	@Inject
	private Result result;
	
	@Inject
	private TenistaLogado tenistaLogado;
	
	
	@Get("/mais/clubes")
	public void clubes() {

	
	}
	@Get("/mais/professores")
	public void professores() {

	
	}
	
	@Get("/mais/federacao")
	public void federacao() {

	
	}
	
	@Get("/mais/empresasParceiras")
	public void empresasParceiras() {

	
	}

	@Get("/mais/sobre")
	public void sobre() {

	
	}

	@Get("/mais/contato")
	public void contato() {
	}
	
	@Get("/mais/filiacao")
	public void filiacao() {
	}
	@Post("/mais/")
	public void enviarContato(Contato contato) {
		
		try{
		/*String mensagemCompleta = "De: " + contato.getNome() + "\n\nE-mail:" + contato.getEmail() + "\n\nMensagem:" + contato.getMensagem();
		emailSender.enviaEmailPadrao(null, contato.getAssunto(), mensagemCompleta, EmailSender.UTF_8, EmailSender.TEXT_PLAIN);
		
		Tenista tenista = new Tenista();
		tenista.setNome(contato.getNome());
		tenista.setEmail(contato.getEmail());
		
		emailSender.enviaEmailPadrao(tenista, Utils.EMPRESA + " - Mesangem Recebida", "Caro " + contato.getNome() + ", \n\n Recebemos seu e-mail e entraremos em contato. \n\n Obrigado.", EmailSender.UTF_8, EmailSender.TEXT_PLAIN);
		
		result.redirectTo(IndexController.class).mensagem("Mensagem enviada com sucesso!");*/
		}catch(Exception e){
			result.redirectTo(IndexController.class).mensagemErro("Houve um problema ao enviar sua mensagem, entre em contato diretamente com " + Utils.EMAIL);
		}
	}
	/**
	 * Método responsável por redirecionar para a tela de alterar senha
	 * */

	@Get
	public void formSolicitaMudancaNivel() {
		
		result.include("solicitacaoMudancaClasse",true);
		result.include("tenista", tenistaLogado.getTenista());
		result.forwardTo(MaisController.class).contato();
		
	}	



}
