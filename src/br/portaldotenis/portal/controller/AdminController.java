package br.portaldotenis.portal.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.portaldotenis.portal.controleacesso.AdministradorRule;
import br.portaldotenis.portal.controleacesso.LogadoRule;
import br.portaldotenis.portal.dao.FiliadoDAO;
import br.portaldotenis.portal.dao.InscricaoDAO;
import br.portaldotenis.portal.dao.InscricaoDeDuplaDAO;
import br.portaldotenis.portal.dao.NivelDAO;
import br.portaldotenis.portal.dao.PontuacaoDAO;
import br.portaldotenis.portal.dao.TenistaDAO;
import br.portaldotenis.portal.dao.TorneioDAO;
import br.portaldotenis.portal.model.Filiado;
import br.portaldotenis.portal.model.Inscricao;
import br.portaldotenis.portal.model.InscricaoDeDupla;
import br.portaldotenis.portal.model.Nivel;
import br.portaldotenis.portal.model.Pontuacao;
import br.portaldotenis.portal.model.Tenista;
import br.portaldotenis.portal.model.TipoNivel;
import br.portaldotenis.portal.model.Torneio;
import br.portaldotenis.portal.util.EmailSender;
import br.portaldotenis.portal.util.Utils;

/**
 * Controller responsável por atender a todas as requisições de recursos de
 * Tenista
 * 
 * @author Gabriel Soares
 * @author Thiago Holanda
 * 
 */

@Controller
public class AdminController {

	@Inject
	private Result result;
	@Inject
	private TenistaDAO tenistaDao;
	@Inject
	private EmailSender emailSender;
	@Inject
	private NivelDAO nivelDao;
	@Inject
	private TorneioDAO torneioDao;
	@Inject
	private PontuacaoDAO pontuacaoDao;
	@Inject
	private InscricaoDAO inscricaoDao;
	@Inject
	private InscricaoDeDuplaDAO inscricaoDeDuplaDao;
	@Inject
	private AdministradorRule administradorRule;
	@Inject
	private LogadoRule logadoRule;
	@Inject
	private FiliadoDAO filiadoDao;
	@Inject
	private Validator validator;




	/**
	 * Método que apresenta o formulário de envio de e-mails em massa
	 * */

	@Get("admin/form-envia-emails")
	public void formEnviaEmails() {
		if (logadoRule.isAllowed() && administradorRule.isAllowed()) {

			List<Nivel> niveis = nivelDao.getNiveisPorTipo(TipoNivel.TECNICO);
			List<Torneio> torneios = torneioDao.getTorneiosAIniciarEIniciados();

			result.include("niveisTecnicos", niveis);
			result.include("torneios", torneios);

		} else {
			result.redirectTo(IndexController.class)
					.mensagemErro(
							"Você precisa ser um administrador para realizar essa tarefa.");
		}

	}

	/**
	 * Método que envia os e-mails para um grupo de tenistas de acordo com o seu
	 * nível técnico
	 * 
	 * @param idNivel
	 *            identifica o nível dos tenistas que receberão e-mails
	 * @param assuntoDoEmail
	 *            descreve o assunto do e-mail que será enviado
	 * @param mensagemDoEmail
	 *            descreve o conteúdo da mensagem que será enviada em massa
	 * 
	 * */
	@Post("admin/form-envia-emails")
	public void enviaEmails(Integer idNivel, Long idTorneio,
			String assuntoDoEmail, String mensagemDoEmail, Integer primeiro, Integer segundo) {

		List<Tenista> tenistas = new ArrayList<Tenista>();

		if (idTorneio == 0) {
			if (idNivel == 0) {
				tenistas = tenistaDao.getTenistasAtivos();
			} else {
				tenistas = tenistaDao.getTenistasPorNivelTecnico(idNivel);
			}
		} else {
			if (idNivel == 0) {
				tenistas = tenistaDao
						.getTenistasInscritosNoTorneio(idTorneio);
			} else {
				tenistas = tenistaDao
						.getTenistasInscritosNoTorneioPorNivel(
								idNivel, idTorneio);
			}
		}
		
		int contador = 0;
		for (int i = primeiro; i <= segundo; i++) {
			emailSender.enviaEmailPadrao(tenistas.get(i-1), assuntoDoEmail,
				mensagemDoEmail, EmailSender.UTF_8, EmailSender.TEXT_HTML);
//			if (tenistas.size() >= i){
//				EmailsParalelos emailsParalelos = new EmailsParalelos(mailer, assuntoDoEmail, mensagemDoEmail, tenistas.get(i-1));
//				Thread threadEmailsParalelos = new Thread(emailsParalelos);
//				threadEmailsParalelos.run();
//				contador++;
//			}
		}
		result.redirectTo(IndexController.class).mensagem(
				"Mensagem enviada para " + contador + " tenistas.");

	}

	/**
	 * Método que mostra a tela para confirmar a atribuição dos pontos
	 * */
	@Get("/admin/atribui-pontuacao-dos-tenistas")
	public void atribuiPontuacaoDosTenistas() {
		
		List<Nivel> niveisTecnicos = nivelDao.getNiveisPorTipo(TipoNivel.TECNICO);
		List<Nivel> niveisFemininos = nivelDao.getNiveisPorTipo(TipoNivel.FEMININO); 
		List<Nivel> niveisConcatenados = new ArrayList<Nivel>(niveisTecnicos.size()+niveisFemininos.size());
		niveisConcatenados.addAll(niveisTecnicos);
		niveisConcatenados.addAll(niveisFemininos);
		
		
		result.include("niveisTecnicos", niveisConcatenados);				

	}

	/**
	 * Método que mostra a tela para confirmar a atribuição dos pontos
	 * */
	@Get("/admin/atribui-pontuacao-de-tenistas-sem-pontos")
	public void atribuiPontuacaoDeTenistasSemPontos() {

	}

	/**
	 * Método que mostra a tela para confirmar a atribuição dos pontos de duplas
	 * */
	@Get("/admin/atribui-pontuacao-de-duplas-dos-tenistas")
	public void atribuiPontuacaoDeDuplasDosTenistas() {
		
		List<Nivel> niveis = nivelDao.getNiveisPorTipo(TipoNivel.DUPLAS);
		result.include("niveisDuplas", niveis);

	}

	/**
	 * Método que apresenta o formulário de cadastro de filiados
	 * */

	@Get("admin/form-cadastra-filiado")
	public void formCadastraFiliado() {
		if (logadoRule.isAllowed() && administradorRule.isAllowed()) {

			List<Tenista> tenistas = tenistaDao.getTenistasAtivos();

			result.include("tenistas", tenistas);

		} else {
			result.redirectTo(IndexController.class)
					.mensagemErro(
							"Você precisa ser um administrador para realizar essa tarefa.");
		}

	}

	

	/**
	 * Método que confirma a atribuição dos pontos dos tenistas
	 * */
	@Transactional
	@Post("/admin/atribui-pontuacao-dos-tenistas")
	public void confirmaAtribuicaoDaPontuacaoDosTenistas(Integer idNivel) {

		// Verifica se tem algum torneio iniciado, caso exista a atribuição de
		// pontos não pode ser realizada
		/*List<Torneio> torneiosChaveado = torneioDao.getTorneiosComChaveGerada();
		if (torneiosChaveado.size() == 0) {*/

			// Obtém todos os tenistas que já participaram de algum torneio
		Nivel nivel = nivelDao.find(idNivel);
		List<Tenista> tenistasComParticipacaoEmTorneios = null;
		// faz uma consulta específica para os níveis técnicos
		if(nivel.getTipoNivel().equals(TipoNivel.TECNICO)){
			tenistasComParticipacaoEmTorneios = tenistaDao
					.getTenistasComParticipacaoEmTorneios(idNivel);
		}else if(nivel.getTipoNivel().equals(TipoNivel.FEMININO)){
			tenistasComParticipacaoEmTorneios = tenistaDao
					.getTenistasFemininosComParticipacaoEmTorneios(idNivel);
		}
			// List<Nivel> niveis =
			// nivelDao.getNiveisQuePossuemRankingDeSimples();
			
			Integer anoReferencia = Calendar.getInstance().get(Calendar.YEAR);

			for (Tenista tenista : tenistasComParticipacaoEmTorneios) {

				// for (Nivel nivel : niveis) {

				// inscricoes do tenista no nivel e no ano
				List<Inscricao> inscricoes = inscricaoDao
						.getInscricoesPorTenistaNivelAno(tenista, nivel,
								anoReferencia);

				// seleciona a soma de pontos do tenista no nivel
				Integer pontosDoTenistaNoNivel = somarPontosDoTenista(inscricoes);

				Pontuacao pontuacao = pontuacaoDao.getPontuacao(tenista, nivel,
						anoReferencia);

				if (inscricoes.size() > 0) {
					// atualiza ou adiciona os pontos do tenista nesse nivel
					// em
					// pontuacao
					if (pontuacao != null) {
						pontuacao.setPontos(pontosDoTenistaNoNivel);
						pontuacaoDao.atualiza(pontuacao);
						System.out.println("Pontuacao:" + pontuacao.getId());
						System.out.println("Tenista:"
								+ pontuacao.getTenista().getId());
						System.out.println("Nível:"
								+ pontuacao.getNivel().getId());
						System.out.println("Ano:" + anoReferencia);

					} else {
						pontuacao = new Pontuacao();
						pontuacao.setTenista(tenista);
						pontuacao.setNivel(nivel);
						pontuacao.setPontos(pontosDoTenistaNoNivel);
						pontuacao.setAno(anoReferencia);
						pontuacaoDao.adiciona(pontuacao);

					}
				}
				// verifica se a classe do tenista é a mesma e atribui
				// também
				if (tenista.getNivelTecnicoPrincipal().getId() == nivel.getId()) {
					tenista.setPontosCategoriaPrincipal(pontosDoTenistaNoNivel);
					tenistaDao.atualiza(tenista);
				}

				// }

			}

			String assunto = "Atribuição de pontos";
			String mensagem = "Todos os pontos foram atribuídos com sucesso.";

			emailSender.enviaEmailPadrao(null, assunto, mensagem,
					EmailSender.UTF_8, EmailSender.TEXT_PLAIN);
			result.redirectTo(IndexController.class).mensagem(mensagem);
		/*} else {

			result.redirectTo(IndexController.class)
					.mensagem(
							"Existe torneios em andamento e agora não é possível gerar pontuação.");

		}*/
	}

	/**
	 * Método que confirma pontos para todos os tenistas em todos os níveis do
	 * ano atual
	 * */
	@Transactional
	@Post("/admin/atribui-pontuacao-de-tenistas-sem-pontos")
	public void confirmaPontuacaoDeTenistasSemPontos() {

		// Obtém todos os tenistas que já participaram de algum torneio

		List<Nivel> niveis = nivelDao.getNiveisQuePossuemRankingDeSimples();
		Integer anoReferencia = Calendar.getInstance().get(Calendar.YEAR);
		Integer contador = 0;
		Pontuacao pontuacao = null;

		try {

			for (int i = 1; i <= 100; i++) {
				Integer idNivel = i;
				List<Tenista> tenistas = tenistaDao
						.getTenistasPorNivelTecnico(idNivel);

				for (Tenista tenista : tenistas) {

					for (Nivel nivel : niveis) {

						pontuacao = pontuacaoDao.getPontuacao(tenista, nivel,
								anoReferencia);

						// atualiza os pontos do tenista nesse nivel caso ele
						// não tenha
						if (pontuacao == null) {
							pontuacao = new Pontuacao();
							pontuacao.setTenista(tenista);
							pontuacao.setNivel(nivel);
							pontuacao.setPontos(0);
							pontuacao.setAno(anoReferencia);
							pontuacaoDao.adiciona(pontuacao);
							contador++;

						} 

					}

				}

			}
			result.redirectTo(IndexController.class)
					.mensagem(
							contador
									+ " pontos inexistentes foram atribuíidos a tenistas.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println(pontuacao.getId());
		}

	}

	/**
	 * Método que confirma a atribuição dos pontos de duplas dos tenistas
	 * */
	@Transactional
	@Post("/admin/atribui-pontuacao-de-duplas-dos-tenistas")
	public void confirmaAtribuicaoDaPontuacaoDeDuplasDosTenistas(Integer idNivel) {

		// Verifica se tem algum torneio iniciado, caso exista a atribuição de
		// pontos não pode ser realizada
		
		

			// Obtém todos os tenistas que já participaram de algum torneio de
			// duplas
			List<Tenista> tenistasComParticipacaoEmTorneios = tenistaDao
					.getTenistasComParticipacaoEmTorneiosDeDuplas(idNivel);
			List<Nivel> niveis = nivelDao.getNiveisPorTipo(TipoNivel.DUPLAS);
			Integer anoReferencia = Calendar.getInstance().get(Calendar.YEAR);
			for (Tenista tenista : tenistasComParticipacaoEmTorneios) {

				for (Nivel nivel : niveis) {

					// inscricoes do tenista no nivel e no ano
					List<InscricaoDeDupla> inscricoes = inscricaoDeDuplaDao
							.getInscricoesPorTenistaNivelAno(tenista, nivel,
									anoReferencia);

					// seleciona a soma de pontos do tenista no nivel
					Integer pontosDoTenistaNoNivel = somarPontosDeDuplasDoTenista(inscricoes);

					Pontuacao pontuacao = pontuacaoDao.getPontuacao(tenista,
							nivel, anoReferencia);

					// atualiza ou adiciona os pontos do tenista nesse nivel em
					// pontuacao
					if (inscricoes.size() > 0) {
						if (pontuacao != null) {
							pontuacao.setPontos(pontosDoTenistaNoNivel);
							pontuacaoDao.atualiza(pontuacao);

						} else {
							pontuacao = new Pontuacao();
							pontuacao.setTenista(tenista);
							pontuacao.setNivel(nivel);
							pontuacao.setPontos(pontosDoTenistaNoNivel);
							pontuacao.setAno(anoReferencia);
							pontuacaoDao.adiciona(pontuacao);

						}
					}

				}

			}

			String assunto = "Atribuição de pontos";
			String mensagem = "Todos os pontos foram atribuídos com sucesso.";
			emailSender.enviaEmailPadrao(null, assunto, mensagem,
					EmailSender.UTF_8, EmailSender.TEXT_PLAIN);
			result.redirectTo(IndexController.class).mensagem(mensagem);
		
	}

	/**
	 * Método que confirma o cadastro de filiados
	 * */
	@Transactional
	@Post("/admin/form-cadastro-filiado")
	public void confirmaCadastroFiliado(Filiado filiado) {

		if (filiado.getTenista().getId() != null) {

			Integer anoAtual = Calendar.getInstance().get(Calendar.YEAR);
			if (filiado.getAnoReferencia().equals(anoAtual)) {

				try {

					filiadoDao.adiciona(filiado);
					filiado.setTenista(tenistaDao.busca(filiado.getTenista()
							.getId()));
					filiado.getTenista().setFiliado(true);
					tenistaDao.atualiza(filiado.getTenista());
					String assunto = "Cadastro Premium Confirmado";
					String mensagem = "Prezado(a) tenista, \n\n "
							+ "O processo de seu cadastro para ser um "+  Utils.ESPORTISTA + " PREMIUM foi confirmado. Agora você poderá se beneficiar das vantagens de ser um tenista PREMIUM. \n\n"
							+ "* Ter descontos em todos as inscrições dos torneios postados nesta plataforma, as inscrições para não premium serão maiores \n"
							+ "* Participar do ranking desta plataforma \n"
							+ "* Concorrer a brindes que venham ser sorteados \n"
							+ "* Poder ser organizador e divulgador de eventos esportivos em nosso sistema \n"
							+ "* Participar de sorteios.\n \n"
							+ "Nome: "
							+ filiado.getTenista().getNomeCompleto()
							+ "\n"
							+ "Data do cadastro:"
							+ Utils.formataDataPadrao(filiado.getData()
									.getTime()) + "\n" + "Ano de Referência: "
							+ filiado.getAnoReferencia() + "\n";
					emailSender
							.enviaEmailPadrao(filiado.getTenista(), assunto,
									mensagem, EmailSender.UTF_8,
									EmailSender.TEXT_PLAIN);
					result.redirectTo(IndexController.class).mensagem(
							"Cadastro realizado com sucesso.");
				} catch (Exception e) {
					validator
							.add(new SimpleMessage("filiado",
									"Falha ao tentar cadastrar ou enviar e-mail para o atleta premium"));
					validator.validate(filiado);
					validator.onErrorRedirectTo(this).formCadastraFiliado();
				}
			} else {
	
				validator
						.add(new SimpleMessage("filiado.anoReferencia",
								"O cadastro do ano só pode ser realizada no próprio ano"));
				validator.validate(filiado);
				validator.onErrorRedirectTo(this).formCadastraFiliado();
			}

		} else {
			validator.add(new SimpleMessage("filiado.tenista",
					"Escolha um tenista."));
			validator.validate(filiado);
			validator.onErrorRedirectTo(this).formCadastraFiliado();
		}

	}

	
	


	/**
	 * Buscas os tenistas que estão desativados no sistema escolhido
	 * 
	 * */
	@Get("/admin/visualiza-tenistas-desativados")
	public void visualizaTenistasDesativados() {

		List<Tenista> tenistas = tenistaDao.getTenistasDesativados();
		result.include("tenistasEncontrados", tenistas);
		result.include("numeroDeTenistas", tenistas.size());

	}

	/**
	 * Buscas os tenistas que estão desativados no sistema escolhido
	 * 
	 * */
	@Get("/admin/visualiza-tenistas-desconfirmados")
	public void visualizaTenistasDesconfirmados() {

		List<Tenista> tenistas = tenistaDao.getTenistasDesconfirmados();
		result.include("tenistasEncontrados", tenistas);
		result.include("numeroDeTenistas", tenistas.size());

	}
	/**
	 * Buscas os tenistas que estão suspensos
	 * 
	 * */
	@Get("/admin/visualiza-tenistas-suspensos")
	public void visualizaTenistasSuspensos() {

		List<Tenista> tenistas = tenistaDao.getTenistasSuspensos();
		result.include("tenistasEncontrados", tenistas);
		result.include("numeroDeTenistas", tenistas.size());

	}
	/**
	 * Método que remove um filiado do sistema e ajusta o campo filiado
	 * *
	 * */
	@Get("/admin/remove-filiacao/{idFiliado}")
	public void removeFiliacao(Long idFiliado) {

		Filiado filiado = filiadoDao.busca(idFiliado);
		filiadoDao.remove(filiado);
		
		Tenista tenista = filiado.getTenista();
		tenista.setFiliado(false);
		tenistaDao.atualiza(tenista);


		String mensagem = "Prezado(a) "
				+ filiado.getTenista().getNomeCompleto()
				+ ", \n \n"
				+ " A sua filiação de "
				+ filiado.getAnoReferencia()
				+ " foi removida do sistema. Entre em contato através do e-mail" + Utils.EMAIL + " para obter mais detalhes.";
		emailSender.enviaEmailPadrao(filiado.getTenista(), "A sua Filiação foi removida", mensagem, EmailSender.UTF_8,
				EmailSender.TEXT_PLAIN);

		result.redirectTo(IndexController.class).mensagem("O filiado foi atualizado com sucesso!");

	}

	/**
	 * ´Método que soma os pontos do tenista no nível e no ano
	 * */
	private Integer somarPontosDoTenista(List<Inscricao> inscricoes) {

		Integer soma = 0;

		for (Inscricao inscricao : inscricoes) {
			if (inscricao.getPontuacao() != null) {
				soma += inscricao.getPontuacao();
			}
		}

		return soma;
	}

	/**
	 * ´Método que soma os pontos do tenista em duplas no nível e no ano
	 * */
	private Integer somarPontosDeDuplasDoTenista(
			List<InscricaoDeDupla> inscricoes) {

		Integer soma = 0;

		for (InscricaoDeDupla inscricao : inscricoes) {
			if (inscricao.getPontuacao() != null) {
				soma += inscricao.getPontuacao();
			}
		}

		return soma;
	}

}
