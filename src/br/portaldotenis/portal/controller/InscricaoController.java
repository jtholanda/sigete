/**
 * 
 */
package br.portaldotenis.portal.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.portaldotenis.portal.controleacesso.LogadoRule;
import br.portaldotenis.portal.dao.ColocacaoDAO;
import br.portaldotenis.portal.dao.FaseDAO;
import br.portaldotenis.portal.dao.IndicacaoInscricaoDAO;
import br.portaldotenis.portal.dao.InscricaoDAO;
import br.portaldotenis.portal.dao.InscricaoDeDuplaDAO;
import br.portaldotenis.portal.dao.JogoDeTorneioDAO;
import br.portaldotenis.portal.dao.MidiaTorneioDAO;
import br.portaldotenis.portal.dao.NivelDAO;
import br.portaldotenis.portal.dao.PontuacaoDAO;
import br.portaldotenis.portal.dao.TenistaDAO;
import br.portaldotenis.portal.dao.TorneioDAO;
import br.portaldotenis.portal.model.ClassificacaoDeGrupo;
import br.portaldotenis.portal.model.Colocacao;
import br.portaldotenis.portal.model.Fase;
import br.portaldotenis.portal.model.IndicacaoInscricao;
import br.portaldotenis.portal.model.Inscricao;
import br.portaldotenis.portal.model.InscricaoDeDupla;
import br.portaldotenis.portal.model.JogoDeTorneio;
import br.portaldotenis.portal.model.MidiaTorneio;
import br.portaldotenis.portal.model.Nivel;
import br.portaldotenis.portal.model.Pontuacao;
import br.portaldotenis.portal.model.Sexo;
import br.portaldotenis.portal.model.Tenista;
import br.portaldotenis.portal.model.TenistaLogado;
import br.portaldotenis.portal.model.TipoDupla;
import br.portaldotenis.portal.model.TipoEsporte;
import br.portaldotenis.portal.model.TipoNivel;
import br.portaldotenis.portal.model.TipoUsuario;
import br.portaldotenis.portal.model.Torneio;
import br.portaldotenis.portal.util.EmailSender;
import br.portaldotenis.portal.util.Utils;

/**
 * Controller responsável por atender a todas as inscrições em Torneios
 * 
 * @author Thiago Holanda
 * 
 */
@Controller
public class InscricaoController {

	public static final Integer CONFIRMA_INSCRICAO = 1;
	public static final Integer CANCELA_INSCRICAO = 2;
	public static final Integer CONFIRMA_PAGAMENTO = 3;
	public static final Integer CANCELA_PAGAMENTO = 4;
	public static final Integer CANCELA_PAGAMENTO_BOLSA = 5;
	public static final Integer ATRIBUI_BOLSA = 6;
	public static final Integer REMOVE_INSCRICAO = 7;

	public static final String CAMPO_CONFIRMADA = "confirmada";
	// private static final int INICIO_LISTA = 0;
	private static final int FIM_LISTA = 10;

	@Inject
	private TenistaLogado tenistaLogado;

	@Inject
	private Result result;

	@Inject
	private TorneioDAO torneioDao;

	@Inject
	private InscricaoDAO inscricaoDao;

	@Inject
	private TenistaDAO tenistaDao;

	@Inject
	private NivelDAO nivelDao;

	@Inject
	private Validator validator;

	@Inject
	private EmailSender emailSender;

	@Inject
	private HttpServletRequest request;

	@Inject
	private ColocacaoDAO colocacaoDao;

	@Inject
	private InscricaoDeDuplaDAO inscricaoDeDuplaDao;

	@Inject
	private PontuacaoDAO pontuacaoDao;

	@Inject
	private LogadoRule logadoRule;

	@Inject
	private MidiaTorneioDAO midiaTorneioDao;

	@Inject
	@SessionScoped
	private HttpSession inscricaoSessaoDeSimplesDuplas;
	private String mensagem = "";

	
	@Inject
	private FaseDAO faseDao;
	
	@Inject
	private IndicacaoInscricaoDAO indicacaoInscricaoDao;

	@Inject
	JogoDeTorneioDAO jogoDeTorneioDao;
	
	// MÉTODOS DE REDIRECIONAMENTO DE PÁGINAS

	/**
	 * Método que redireciona para o formulário que permite o tenista logado se
	 * inscrever no torneio
	 * 
	 * @param id
	 *            do torneio que o tenista está se inscrevendo
	 * 
	 * */
	@Get("/inscricao/inscricao-em-torneio/{idTorneio}")
	public void formInscreveSeEmTorneio(Long idTorneio) {

		if (tenistaLogado.getTenista() != null) {
			Torneio torneio = torneioDao.busca(idTorneio);
			Tenista tenista = tenistaLogado.getTenista();
			List<IndicacaoInscricao> indicacoesInscricoes = indicacaoInscricaoDao.listaTodos("descricao");

			result.include("tenistaDetalhado", tenista);
			result.include("torneio", torneio);
			result.include("niveisTecnicosDoTorneio", torneio.getNiveis());
			result.include("indicacoesInscricoes", indicacoesInscricoes);

		} else {
			result.include("urlRequisitada", "/inscricao/inscricao-em-torneio/"
					+ idTorneio);
			result.redirectTo(TenistaController.class).login();
		}

	}

	/**
	 * Redireciona para página de inscrever-se em duplas
	 * */
	@Get("/inscricao/inscricao-em-torneio-de-duplas/{idTorneio}")
	public void formInscreveSeEmTorneioDeDuplas(Long idTorneio) {

		if (tenistaLogado.getTenista() != null) {
			Torneio torneio = torneioDao.busca(idTorneio);
			Tenista tenista = tenistaLogado.getTenista();
			List<IndicacaoInscricao> indicacoesInscricoes = indicacaoInscricaoDao.listaTodos("descricao");

			result.include("tenistaDetalhado", tenista);
			result.include("torneio", torneio);
			result.include("niveisTecnicosDoTorneio", torneio.getNiveis());
			result.include("indicacoesInscricoes", indicacoesInscricoes);

			// se for de dupla mostra a lista de parceiros na inscrição

			List<Tenista> tenistasParceiros = tenistaDao
					.getTenistasAtivos();
			result.include("tenistasParceiros", tenistasParceiros);

		} else {
			result.include("urlRequisitada", "/inscricao/inscricao-em-torneio-de-duplas/"
					+ idTorneio);
			result.redirectTo(TenistaController.class).login();
		}
	}

	/**
	 * Redireciona para página de inscrever-se em torneio de simples e duplas
	 * */
	@Get("/inscricao/inscricao-em-torneio-de-simples-e-duplas/{idTorneio}")
	public void formInscreveSeEmTorneioDeSimplesEDuplas(Long idTorneio) {

		if (tenistaLogado.getTenista() != null) {
			Torneio torneio = torneioDao.busca(idTorneio);
			Tenista tenista = tenistaLogado.getTenista();
			List<IndicacaoInscricao> indicacoesInscricoes = indicacaoInscricaoDao.listaTodos("descricao");

			
			// verifica se os níveis tecnicos do torneio estão aptos para o tenista do sexo masculino logado
			List<Nivel> niveisRemovidos = new ArrayList<Nivel>();
			List<Nivel> niveisTecnicos = torneio.getNiveis();
			if(tenista.getSexo() == Sexo.MASCULINO){
				for (Nivel nivel : niveisTecnicos) {
					if(nivel.isFeminino()){
						niveisRemovidos.add(nivel);
					}
				} 
			}

			// verifica se os níveis tecnicos do torneio estão aptos para o tenista do sexo feminino logado
			if(tenista.getSexo() == Sexo.FEMININO){
				for (Nivel nivel : niveisTecnicos) {
					if(nivel.isMasculino()){
						niveisRemovidos.add(nivel);
					}
				} 
			}

			niveisTecnicos.removeAll(niveisRemovidos);
			result.include("escolheuNivel", false);
			result.include("nivelQuantidadeTenista", 0);
			result.include("tenistaDetalhado", tenista);
			result.include("torneio", torneio);
			result.include("niveisTecnicosDoTorneio", niveisTecnicos);
			result.include("indicacoesInscricoes", indicacoesInscricoes);

			// se for de dupla mostra a lista de parceiros na inscrição

			// List<Tenista> tenistasParceiros =
			// tenistaDao.listaTodos(Tenista.CAMPO_PRINCIPAL_ORDENACAO);
			// result.include("tenistasParceiros", tenistasParceiros);

		} else {
			result.include("urlRequisitada", "/inscricao/inscricao-em-torneio-de-simples-e-duplas/"
					+ idTorneio);
			result.redirectTo(TenistaController.class).login();
		}

	}

	/*
	 * /** Redireciona para página de inscrever-se em torneio de simples e
	 * duplas
	 */

	/*
	 * @Get("/inscricao/inscricao-em-torneio-de-simples-e-duplas/") public void
	 * formInscreveSeEmTorneioDeSimplesEDuplas(Inscricao inscricao, Integer
	 * quantidadeTenistas) {
	 * 
	 * Torneio torneio = inscricao.getTorneio(); Tenista tenista =
	 * tenistaLogado.getTenista();
	 * 
	 * if (tenistaLogado.getTenista() != null) {
	 * 
	 * result.include("tenistaDetalhado", tenista); result.include("torneio",
	 * torneio); result.include("niveisTecnicosDoTorneio", torneio.getNiveis());
	 * 
	 * // se for de dupla mostra a lista de parceiros na inscrição
	 * 
	 * if(quantidadeTenistas == 2){
	 * 
	 * List<Tenista> tenistasParceiros =
	 * tenistaDao.listaTodos(Tenista.CAMPO_PRINCIPAL_ORDENACAO);
	 * result.include("tenistasParceiros", tenistasParceiros); }
	 * 
	 * result.include("escolheuNivel", true);
	 * 
	 * } else { result.include("urlRequisitada",
	 * "/inscricao/inscricao-em-torneio/" + torneio.getId());
	 * result.redirectTo(TenistaController.class).login(); } }
	 */

	/**
	 * Método que redireciona para a tela de inscrever tenista em torneio, é
	 * chamado quando um organizador quer inscrever um tenista
	 * 
	 * */
	@Get("/inscricao/inscricao-tenista-em-torneio/{idTorneio}")
	public void formInscreveTenistaEmTorneio(Long idTorneio) {

		// TODO fazer com controle de acesso
		if (tenistaLogado.getTenista() != null && tenistaLogado.isOrganizador()) {

			formInscreve(idTorneio);

		} else {

			result.redirectTo(IndexController.class).mensagemErro(
					"Você não tem permissão para acessar essa funcionalidade.");
		}
	}

	/**
	 * 
	 * redireciona para a página de inscrição em duplas
	 */
	@Get("/inscricao/inscricao-tenista-em-torneio-de-duplas/{idTorneio}")
	public void formInscreveTenistasEmTorneioDeDuplas(Long idTorneio) {

		// TODO fazer com controle de acesso
		if (tenistaLogado.getTenista() != null && tenistaLogado.isOrganizador()) {

			formInscreve(idTorneio);

			List<Tenista> tenistasParceiros = tenistaDao.getTenistasAtivos();
			result.include("tenistasParceiros", tenistasParceiros);

		} else {

			result.redirectTo(IndexController.class).mensagemErro(
					"Você não tem permissão para acessar essa funcionalidade.");
		}

	}

	/**
	 * 
	 * redireciona para a página de inscrição em simples ou duplas
	 */
	@Get("/inscricao/inscricao-tenista-em-torneio-de-simples-e-duplas/{idTorneio}")
	public void formInscreveTenistasEmTorneioDeSimplesEDuplas(Long idTorneio) {

		// TODO fazer com controle de acesso
		if (tenistaLogado.getTenista() != null && tenistaLogado.isOrganizador()) {

			formInscreve(idTorneio);

			List<Tenista> tenistasParceiros = tenistaDao.getTenistasAtivos();
			result.include("tenistasParceiros", tenistasParceiros);

		} else {

			result.redirectTo(IndexController.class).mensagemErro(
					"Você não tem permissão para acessar essa funcionalidade.");
		}

	}

	/*
	 * /** Redireciona para página de inscrever-se em torneio de simples e
	 * duplas
	 */

	/*
	 * @Get("/inscricao/inscricao-em-torneio-de-simples-e-duplas/") public void
	 * formInscreveSeEmTorneioDeSimplesEDuplas(Inscricao inscricao, Integer
	 * quantidadeTenistas) {
	 * 
	 * Torneio torneio = inscricao.getTorneio(); Tenista tenista =
	 * tenistaLogado.getTenista();
	 * 
	 * if (tenistaLogado.getTenista() != null) {
	 * 
	 * result.include("tenistaDetalhado", tenista); result.include("torneio",
	 * torneio); result.include("niveisTecnicosDoTorneio", torneio.getNiveis());
	 * 
	 * // se for de dupla mostra a lista de parceiros na inscrição
	 * 
	 * if(quantidadeTenistas == 2){
	 * 
	 * List<Tenista> tenistasParceiros =
	 * tenistaDao.listaTodos(Tenista.CAMPO_PRINCIPAL_ORDENACAO);
	 * result.include("tenistasParceiros", tenistasParceiros); }
	 * 
	 * result.include("escolheuNivel", true);
	 * 
	 * } else { result.include("urlRequisitada",
	 * "/inscricao/inscricao-em-torneio/" + torneio.getId());
	 * result.redirectTo(TenistaController.class).login(); } }
	 */

	/**
	 * Método responsável por redirecionar a solicitação de inscricao
	 * simples/duplas para a tela de confirma inscrição
	 * 
	 * */

	@Get("/inscricao/confirmacao-de-inscricao-simples-torneio-simples-duplas")
	public void formConfirmaInscricaoSimplesTorneioSimplesDuplas(
			Inscricao inscricaoSessao) {
		inscricaoSessaoDeSimplesDuplas.setAttribute("inscricaoSessao",
				inscricaoSessao);
	}

	/**
	 * Método responsável por redirecionar a solicitação de inscricao
	 * simples/duplas para a tela de confirma inscrição
	 * 
	 * */

	@Get("/inscricao/confirmacao-de-inscricao-duplas-torneio-simples-duplas")
	public void formConfirmaInscricaoDuplasTorneioSimplesDuplas(
			Inscricao inscricaoSessao, Tenista tenista) {
		List<Tenista> tenistasParceiros = null;

		if(inscricaoSessao.getNivel().isMista()){
			if(tenista.getSexo() == Sexo.MASCULINO){
				tenistasParceiros = tenistaDao.getTenistasPorSexo(Sexo.FEMININO);
			}			
			if(tenista.getSexo() == Sexo.FEMININO){
				tenistasParceiros = tenistaDao.getTenistasPorSexo(Sexo.MASCULINO);
			}			
		}

		if(inscricaoSessao.getNivel().isMasculino()){
			tenistasParceiros = tenistaDao.getTenistasPorSexo(Sexo.MASCULINO);			
		}

		if(inscricaoSessao.getNivel().isFeminino()){
			tenistasParceiros = tenistaDao.getTenistasPorSexo(Sexo.FEMININO);			
		}
		result.include("tenistasParceiros", tenistasParceiros);

		inscricaoSessaoDeSimplesDuplas.setAttribute("inscricaoSessao",
				inscricaoSessao);
	}

	/*
	 * /** Redireciona para página de inscrever-se em torneio de simples e
	 * duplas
	 */
	
	/*
	 * @Get("/inscricao/inscricao-em-torneio-de-simples-e-duplas/") public void
	 * formInscreveSeEmTorneioDeSimplesEDuplas(Inscricao inscricao, Integer
	 * quantidadeTenistas) {
	 * 
	 * Torneio torneio = inscricao.getTorneio(); Tenista tenista =
	 * tenistaLogado.getTenista();
	 * 
	 * if (tenistaLogado.getTenista() != null) {
	 * 
	 * result.include("tenistaDetalhado", tenista); result.include("torneio",
	 * torneio); result.include("niveisTecnicosDoTorneio", torneio.getNiveis());
	 * 
	 * // se for de dupla mostra a lista de parceiros na inscrição
	 * 
	 * if(quantidadeTenistas == 2){
	 * 
	 * List<Tenista> tenistasParceiros =
	 * tenistaDao.listaTodos(Tenista.CAMPO_PRINCIPAL_ORDENACAO);
	 * result.include("tenistasParceiros", tenistasParceiros); }
	 * 
	 * result.include("escolheuNivel", true);
	 * 
	 * } else { result.include("urlRequisitada",
	 * "/inscricao/inscricao-em-torneio/" + torneio.getId());
	 * result.redirectTo(TenistaController.class).login(); } }
	 */
	
	/**
	 * Método que é requisitado por um tenista quando ele tentar inscrever-se em
	 * um torneio de simples e duplas
	 * 
	 * @param inscricao
	 *            objeto da inscrição solicitada
	 * */
	@Post("/inscricao/prepara-inscricao-em-torneio-de-simples-e-duplas")
	public void preparaInscricaoTorneioSimplesDuplas(Inscricao inscricao) {
	
		Tenista tenista = tenistaLogado.getTenista();
	
		if (tenista != null && !tenista.isSuspenso()) {
			
			Nivel nivel = nivelDao.find(inscricao.getNivel().getId());
			Torneio torneio = torneioDao.find(inscricao.getTorneio().getId());
			IndicacaoInscricao indicacaoInscricao = indicacaoInscricaoDao.find(inscricao.getIndicacaoInscricao().getId());
	
			inscricao.setNivel(nivel);
			inscricao.setTorneio(torneio);
			inscricao.setIndicacaoInscricao(indicacaoInscricao);
	
			if (nivel.getQuantidadeDeTenistas() == 1) {
				result.forwardTo(this)
						.formConfirmaInscricaoSimplesTorneioSimplesDuplas(inscricao);
	
			}
			if (nivel.getQuantidadeDeTenistas() == 2) {
	
				result.forwardTo(this)
						.formConfirmaInscricaoDuplasTorneioSimplesDuplas(inscricao, tenista);
	
			}
	
		} else {
			mensagem = " Prezado (a), talvez a sua conta esteja <span style=\"color:red\"> suspensa e a inscrição não foi realizada </span>, entre em contato por e-mail " + Utils.EMAIL + " para saber o motivo da pendência.";
			result.redirectTo(IndexController.class).mensagem(mensagem);
	
		}
	
	}

	/**
	 * Método comun utilizado na inscrição de simples e de duplas
	 * 
	 * @param idTorneio
	 */
	private void formInscreve(Long idTorneio) {
		Long torneioId = idTorneio;
		Torneio torneio = torneioDao.busca(torneioId);

		Tenista tenista = tenistaLogado.getTenista();
		List<IndicacaoInscricao> indicacoesInscricoes = indicacaoInscricaoDao.listaTodos("descricao");
		
		// TODO tenistas do sistema, temporário essa forma
		result.include("tenistas", tenistaDao.getTenistasAtivos());

		// Quando ainda não tenho o torneio selecionado, é passado o valor 0
		// da
		// URL, vem do menu ou paginal inicial
		if (torneioId == 0) {

			// Tenistas padrão não devem aparecer torneios, mesmo que tenham
			if (tenista.getTipo() == TipoUsuario.PADRAO) {
				result.include("torneiosProprios", null);
			} else {
				result.include("torneiosProprios", torneioDao
						.getTorneiosComInscricoesAbertasPorOrganizador(tenista,
								Calendar.getInstance()));
				result.include("torneioSelecionado", false);
			}

		} else {
			// A ação do tenista veio da tela detalhes do torneio
			result.include("torneio", torneio);
			result.include("torneioSelecionado", true);
			result.include("niveis", torneio.getNiveis());
			result.include("indicacoesInscricoes", indicacoesInscricoes);
		}
	}

	/**
	 * Método responsável por redirecionar para a tela de inscrições do tenista
	 * logado
	 * 
	 * @param idTenista
	 *            identifica qual tenista será detalhado
	 * 
	 * */
	@Get("/inscricao/visualiza-minhas-inscricoes")
	public void visualizaMinhasInscricoes() {

		Tenista tenista;
		if (logadoRule.isAllowed()) {

			tenista = tenistaLogado.getTenista();
			result.include("inscricoes",
					inscricaoDao.getInscricoesPorTenista(tenista));

		} else {

			result.redirectTo(IndexController.class).mensagemErro(
					"Você precisa estar logado no sistema.");

		}
	}

	/**
	 * Método que redireciona para a tela de torneios com inscrições em aberto
	 * 
	 * */

	@Get("/inscricao/torneio-com-inscricoes-abertas")
	public void realizaUmaInscricao() {

		List<Torneio> torneios = torneioDao
				.getTorneiosComInscricoesAbertas(Calendar.getInstance());
		result.include("torneios", torneios);
		result.include("numeroDeTorneiosEmAberto", torneios.size());

	}

	/**
	 * Método que redireciona para a tela de mensagem inscrição confirmada
	 * 
	 * */
	@Path("/inscricao/inscricao-confirmada")
	public void msgInscricaoConfirmada(Inscricao inscricao) {
		result.include("inscricao", inscricao);
	}

	/**
	 * Método que redireciona para a tela de gerenciar as inscrições de um
	 * torneio
	 * 
	 * @param idTorneio
	 *            identifica o torneio que está tendo as suas inscrições
	 *            gerenciadas
	 * 
	 * */
	@Get("/inscricao/gerenciamento-de-inscricoes/{idTorneio}")
	public void panelGerenciaInscricoes(Long idTorneio) {

		Torneio torneio = torneioDao.busca(idTorneio);
		List<Colocacao> colocacoes = colocacaoDao.listaTodos();
		if (tenistaLogado.isAdminOrOrganizadorProprietario(torneio)) {

			result.include("torneio", torneio);
			result.include("niveis", torneio.getNiveis());
			result.include("colocacoes", colocacoes);
			result.include("inscricoes",
					inscricaoDao.getInscricoesPorTorneio(torneio));
			result.include("CONFIRMA_INSCRICAO", CONFIRMA_INSCRICAO);
			result.include("CANCELA_INSCRICAO", CANCELA_INSCRICAO);
			result.include("CONFIRMA_PAGAMENTO", CONFIRMA_PAGAMENTO);
			result.include("CANCELA_PAGAMENTO", CANCELA_PAGAMENTO);
			result.include("CANCELA_PAGAMENTO_BOLSA", CANCELA_PAGAMENTO_BOLSA);
			result.include("ATRIBUI_BOLSA", ATRIBUI_BOLSA);
			result.include("REMOVE_INSCRICAO", REMOVE_INSCRICAO);
		} else {
			result.redirectTo(IndexController.class).paginaInicial();
		}
	}

	/**
	 * Método que redireciona para a tela de gerenciar as inscrições confirmadas
	 * de um torneio
	 * 
	 * @param idTorneio
	 *            identifica o torneio que está tendo as suas inscrições
	 *            gerenciadas
	 * 
	 * */
	@Get("/inscricao/gerenciamento-de-inscricoes-confirmadas/{idTorneio}")
	public void panelGerenciaInscricoesConfirmadas(Long idTorneio) {

		Torneio torneio = torneioDao.busca(idTorneio);
		List<Colocacao> colocacoes = colocacaoDao.listaTodos();
		if (tenistaLogado.isAdminOrOrganizadorProprietario(torneio)) {

			result.include("torneio", torneio);
			result.include("colocacoes", colocacoes);
			result.include("inscricoes",
					inscricaoDao.getInscricoesConfirmadasPorTorneio(torneio));
			result.include("CONFIRMA_INSCRICAO", CONFIRMA_INSCRICAO);
			result.include("CANCELA_INSCRICAO", CANCELA_INSCRICAO);
			result.include("CONFIRMA_PAGAMENTO", CONFIRMA_PAGAMENTO);
			result.include("CANCELA_PAGAMENTO", CANCELA_PAGAMENTO);
			result.include("CANCELA_PAGAMENTO_BOLSA", CANCELA_PAGAMENTO_BOLSA);
			result.include("ATRIBUI_BOLSA", ATRIBUI_BOLSA);
			result.include("REMOVE_INSCRICAO", REMOVE_INSCRICAO);
		} else {
			result.redirectTo(IndexController.class).paginaInicial();
		}
	}

	/**
	 * Método que redireciona para a tela de edição das inscrições de um torneio
	 * 
	 * @param idInscrição
	 *            identifica a inscrição que será editada
	 * 
	 * */
	@Get("/inscricao/edita-inscricao/{idInscricao}")
	public void formEditaInscricao(Long idInscricao) {

		Inscricao inscricao = inscricaoDao.busca(idInscricao);
		if (tenistaLogado.isAdminOrOrganizadorProprietario(inscricao
				.getTorneio())) {

			// Torneio torneio =
			// torneioDao.busca(inscricao.getTorneio().getId());
			Tenista tenista = tenistaDao.busca(inscricao.getTenista().getId());
			// result.include("niveis", torneio.getNiveis());
			result.include("inscricao", inscricao);
			result.include("tenista", tenista);
			result.include("niveisTecnicosDoTorneio", inscricao.getTorneio()
					.getNiveis());
			// result.include("torneio", torneio);

		} else {
			result.redirectTo(IndexController.class).paginaInicial();
		}
	}

	// MÉTODOS DE AÇÃO

	/**
	 * Método que é requisitado por um tenista quando ele tentar inscrever-se em
	 * um torneio
	 * 
	 * @param inscricao
	 *            objeto da inscrição solicitada
	 * */
	@Post("/inscricao/inscricao-em-torneio")
	public void solicitaInscricaoTorneio(Inscricao inscricao,
			Integer idTenistaParceiro) {

		String mensagem;
		Tenista tenista = tenistaLogado.getTenista();
		if (!tenista.isSuspenso()) {

			mensagem = inserirInscricaoSimples(inscricao, tenista);

		} else {
			mensagem = tenista.getNomeRanking()
					+ ", a sua conta está <span style=\"color:red\"> suspensa e a inscrição não foi realizada </span>, entre em contato por e-mail " + Utils.EMAIL + " para saber o motivo da pendência.";
		}
		result.redirectTo(IndexController.class).mensagem(mensagem);

	}

	@Post("/inscricao/inscricao-em-torneio-de-duplas")
	public void solicitaInscricaoTorneioDeDuplas(InscricaoDeDupla inscricao) {

		Tenista tenistaParceiro = tenistaDao.find(inscricao
				.getTenistaParceiro().getId());
		Tenista tenista = tenistaLogado.getTenista();
		Torneio torneio = torneioDao.busca(inscricao.getTorneio().getId());
		Nivel nivel = nivelDao.busca(inscricao.getNivel().getId());
		IndicacaoInscricao indicacaoInscricao = indicacaoInscricaoDao.busca(inscricao.getIndicacaoInscricao().getId());

		inscricao.setTenistaParceiro(tenistaParceiro);

		inscricao.setTenista(tenista);

		inscricao.setTorneio(torneio);
		
		inscricao.setNivel(nivel);
		
		inscricao.setIndicacaoInscricao(indicacaoInscricao);

		inscricao.setData(Calendar.getInstance());
		inscricao.setAnoReferencia(Calendar.getInstance().get(Calendar.YEAR));
		inscricao.setOrdemAleatoria(new Random().nextInt(5000));

		inscricao.setPontuacaoDaDupla(obterPontosDaDuplaInscrita(inscricao,
				tenistaParceiro, tenista));

		String mensagem = inserirInscricaoDupla(inscricao);
		result.redirectTo(IndexController.class).mensagem(mensagem);

	}

	/**
	 * Método responsável por enviar o tenista solicitado para ser gravado no
	 * banco de dados e enviar um Email para o Tenista confirmar o cadastro ou
	 * Email já confirmando o cadastro;
	 * 
	 * */
	@Transactional
	@Post("/inscricao/inscricao-em-torneio-de-simples-duplas")
	public void solicitaInscricaoTorneioSimplesDuplas(Tenista tenistaParceiro) {

		String mensagem;
		Tenista tenistaUm = tenistaLogado.getTenista();
		Inscricao inscricao;
		
		if (!tenistaUm.isSuspenso()) {
			
			
			// pega a isncrição que está na sessão
			inscricao = (Inscricao)inscricaoSessaoDeSimplesDuplas.getAttribute("inscricaoSessao");

			// se for inscrição de simples, senao se for de duplas insere na inscricaoDeDupla
			if(inscricao.getNivel().getQuantidadeDeTenistas() < 2){
				
				mensagem = inserirInscricaoSimples(inscricao, tenistaUm);
			
			}else{

				InscricaoDeDupla inscricaoDeDupla = new InscricaoDeDupla();

				Tenista tenistaDois = tenistaDao.find(tenistaParceiro.getId());
				
				IndicacaoInscricao indicacaoInscricao = indicacaoInscricaoDao.busca(inscricao.getIndicacaoInscricao().getId());

				inscricaoDeDupla.setTenista(tenistaUm);
				inscricaoDeDupla.setTenistaParceiro(tenistaDois);

				inscricaoDeDupla.setTorneio(inscricao.getTorneio());
				inscricaoDeDupla.setNivel(inscricao.getNivel());

				inscricaoDeDupla.setData(Calendar.getInstance());
				inscricaoDeDupla.setAnoReferencia(Calendar.getInstance().get(Calendar.YEAR));
				inscricaoDeDupla.setOrdemAleatoria(new Random().nextInt(5000));

				inscricaoDeDupla.setPontuacaoDaDupla(obterPontosDaDuplaInscrita(inscricaoDeDupla,
						tenistaDois, tenistaUm));

				inscricaoDeDupla.setIndicacaoInscricao(indicacaoInscricao);
				
				mensagem = inserirInscricaoDupla(inscricaoDeDupla);	

			}

		} else {
			mensagem = tenistaUm.getNomeRanking()
					+ ", a sua conta está <span style=\"color:red\"> suspensa e a inscrição não foi realizada </span>, entre em contato por e-mail" + Utils.EMAIL + "para saber o motivo da pendência.";
		}
		result.redirectTo(IndexController.class).mensagem(mensagem);

	}

	private void inscreverTenistaSimplesPorAdministrador(Inscricao inscricao, Tenista tenista) {
		
		Torneio torneio;
		IndicacaoInscricao indicacaoInscricao;
		
		torneio = torneioDao.busca(inscricao.getTorneio().getId());
		indicacaoInscricao = indicacaoInscricaoDao.busca(inscricao.getIndicacaoInscricao().getId());
		

		inscricao.setTorneio(torneio);

		inscricao.setData(Calendar.getInstance());

		inscricao.setConfirmada(true);

		inscricao.setAnoReferencia(Calendar.getInstance().get(Calendar.YEAR));

		inscricao.setOrdemAleatoria(new Random().nextInt(5000));
		
		inscricao.setIndicacaoInscricao(indicacaoInscricao);


		//Tenista tenista = tenistaDao.busca(inscricao.getTenista().getId());

		if (!tenista.isSuspenso()) {
			inscricao.setTenista(tenista);

			validaInscricao(inscricao, tenista, torneio);
			if(torneio.isSimples()){
				validator.onErrorRedirectTo(this).formInscreveTenistaEmTorneio(
						inscricao.getTorneio().getId());
			}else{
				validator.onErrorRedirectTo(this).formInscreveTenistasEmTorneioDeSimplesEDuplas(
						inscricao.getTorneio().getId());
			}
			inscricaoDao.adiciona(inscricao);

			// Monta assunto e mensagem do e-mail enviado para o tenista
			// inscrito
			String assunto = "Inscrição - " + torneio.getNome();
			String mensagem = obtemMensagemDeInscricao(inscricao, torneio,
					tenista);

			emailSender.enviaEmailPadrao(tenista, assunto, mensagem,
					EmailSender.UTF_8, EmailSender.TEXT_PLAIN);

			// Obtém o nível da inscrição para mostrar na mensagem
			Nivel nivelInscrito = nivelDao.busca(inscricao.getNivel()
					.getId());
			inscricao.setNivel(nivelInscrito);
			result.redirectTo(InscricaoController.class)
					.msgInscricaoConfirmada(inscricao);
		} else {
			String mensagem = "A conta de "
					+ tenista.getNomeRanking()
					+ " está <span style=\"color:red\"> suspensa e a inscrição não foi realizada </span>, entre em contato por e-mail " + Utils.EMAIL + " para saber o motivo da pendência.";
			result.redirectTo(IndexController.class).mensagemErro(mensagem);

		}
	}

	private void inscreverDuplaTenistasPorAdministrador(InscricaoDeDupla inscricao, Tenista tenista, Tenista tenistaParceiro) {
		
		//Tenista tenista = tenistaDao.busca(inscricao.getTenista().getId());
		inscricao.setTenista(tenista);

		//Tenista tenistaParceiro = tenistaDao.busca(inscricao.getTenistaParceiro().getId());
		inscricao.setTenistaParceiro(tenistaParceiro);

		if (!inscricao.getTenista().isSuspenso()
				|| !inscricao.getTenistaParceiro().isSuspenso()) {

			Torneio torneio = torneioDao.busca(inscricao.getTorneio()
					.getId());
			
			IndicacaoInscricao indicacaoInscricao = indicacaoInscricaoDao.busca(inscricao.getIndicacaoInscricao().getId());
			inscricao.setTorneio(torneio);

			inscricao.setNivel(nivelDao.find(inscricao.getNivel().getId()));

			inscricao.setConfirmada(true);

			inscricao.setData(Calendar.getInstance());
			inscricao.setAnoReferencia(Calendar.getInstance().get(
					Calendar.YEAR));
			inscricao.setOrdemAleatoria(new Random().nextInt(5000));

			inscricao.setPontuacaoDaDupla(obterPontosDaDuplaInscrita(
					inscricao, tenista, tenistaParceiro));
			inscricao.setTenistaParceiro(tenistaParceiro);
			
			inscricao.setIndicacaoInscricao(indicacaoInscricao);

			validaInscricaoDeDuplas(inscricao, torneio, tenista,
					tenistaParceiro);
			if(torneio.isDuplas()){
				validator.onErrorRedirectTo(this)
					.formInscreveTenistasEmTorneioDeDuplas(
							inscricao.getTorneio().getId());
			}else{
				validator.onErrorRedirectTo(this)
				.formInscreveTenistasEmTorneioDeSimplesEDuplas(
						inscricao.getTorneio().getId());
				
			}
			inscricaoDeDuplaDao.adiciona(inscricao);

			// Monta assunto e mensagem do e-mail enviado para o tenista
			// inscrito
			String assunto = "Inscrição - " + torneio.getNome();
			String mensagem = obtemMensagemInscricaoDeDupla(inscricao, tenista,
					tenistaParceiro, torneio);

			emailSender.enviaEmailPadrao(tenista, assunto, mensagem,
					EmailSender.UTF_8, EmailSender.TEXT_PLAIN);
			emailSender.enviaEmailPadrao(tenistaParceiro, assunto,
					mensagem, EmailSender.UTF_8, EmailSender.TEXT_PLAIN);

			// Obtém o nível da inscrição para mostrar na mensagem
			Nivel nivelInscrito = nivelDao.busca(inscricao.getNivel()
					.getId());
			inscricao.setNivel(nivelInscrito);
			result.redirectTo(InscricaoController.class)
					.msgInscricaoConfirmada(inscricao);
		} else {
			String mensagem = "A conta de "
					+ inscricao.getTenista().getNomeRanking()
					+ " ou a de "
					+ inscricao.getTenistaParceiro().getNomeRanking()
					+ " está <span style=\"color:red\"> suspensa e a inscrição não foi realizada </span>, entre em contato por e-mail " + Utils.EMAIL + " para saber o motivo da pendência.";
			result.redirectTo(IndexController.class).mensagemErro(mensagem);

		}
	}

	private String obtemMensagemInscricaoDeDupla(InscricaoDeDupla inscricao,
			Tenista tenista, Tenista tenistaParceiro, Torneio torneio) {
		String mensagem = "Inscrição confirmada para o torneio: "
				+ torneio.getNome()
				+ "\n\n"
				+ "Fique sempre atento ao site e as informações que poderá receber por e-mail. Caso ainda não tenha realizado o pagamento da inscrição. Leia as informações de pagamento a seguir.\n"
				+ torneio.getInformacoesPagamento()
				+ "\n\n"
				+ "Número de sua inscrição:"
				+ inscricao.getId()
				+ "\n\n"
				+ "Organizador: "
				+ torneio.getTenistaOrganizador().getNome()
				+ "\n\n"
				+ "Telefone: "
				+ torneio.getTenistaOrganizador()
						.getTelefonePrincipal().getNumero()
				+ "\n\n"
				+ " Dupla: "
				+ tenista.getNome()
				+ " / "
				+ tenistaParceiro.getNome()
				+ "\n\n"
				+ "Classe ou nível técnico: "
				+ inscricao.getNivel().getDescricao()
				+ "\n\n"
				+ "Observação: "
				+ inscricao.getTorneio().getObservacoesAdicionais()
				+ "\n\n\nRealize o quanto antes o pagamento da inscrição.\n";
		return mensagem;
	}

	/**
	 * Método que confirma a inscrição de um tenista em um torneio do
	 * organizador logado
	 * 
	 * @param inscricao
	 *            refere-se ao objeto inscrição que vem do formulário
	 * 
	 * */
	@Post("/inscricao/inscricao-tenista-em-torneio")
	public void confirmaInscricaoDoTenistaNoTorneio(Inscricao inscricao) {
	
	
		if (inscricao.getTenista() != null && inscricao.getTenista().getId() != 0) {
	
			Tenista tenista = tenistaDao.busca(inscricao.getTenista().getId());
			inscreverTenistaSimplesPorAdministrador(inscricao, tenista);
	
		} else {
	
			validator.add(new SimpleMessage("inscricao.tenista",
					"Escolha um tenista."));
			validator.validate(inscricao);
			validator.onErrorRedirectTo(this).formInscreveTenistaEmTorneio(
					inscricao.getTorneio().getId());
	
		}
	}

	/**
	 * Método que confirma a inscrição de um tenista em um torneio do
	 * organizador logado
	 * 
	 * @param inscricao
	 *            refere-se ao objeto inscrição que vem do formulário
	 * 
	 * */
	@Post("/inscricao/inscricao-tenista-em-torneio-de-duplas")
	public void confirmaInscricaoDoTenistaNoTorneioDeDuplas(
			InscricaoDeDupla inscricao) {
	
		if (inscricao.getTenista().getId() != 0
				&& inscricao.getTenistaParceiro().getId() != 0) {
	
			Tenista tenista = tenistaDao.busca(inscricao.getTenista().getId());
			Tenista tenistaParceiro = tenistaDao.busca(inscricao.getTenistaParceiro().getId());
			inscreverDuplaTenistasPorAdministrador(inscricao, tenista, tenistaParceiro);
			
		} else {
	
			Torneio torneio = torneioDao.busca(inscricao.getTorneio().getId());
	
			validator.add(new SimpleMessage("inscricao.tenista",
					"Escolha a dupla de tenistas."));
			validator.validate(inscricao);
			validator.onErrorRedirectTo(this)
					.formInscreveTenistasEmTorneioDeDuplas(torneio.getId());
	
		}
	}

	/**
	 * Método que confirma a inscrição de um tenista em um torneio do
	 * organizador logado
	 * 
	 * @param inscricaoDeDupla
	 *            refere-se ao objeto inscrição que vem do formulário
	 * 
	 * */
	@Post("/inscricao/inscricao-tenista-em-torneio-de-simples-e-duplas")
	public void confirmaInscricaoDoTenistaNoTorneioDeSimplesEDuplas(
			InscricaoDeDupla inscricaoDeDupla) {
		
		// está tentando inscrever um atleta na simples
		if (inscricaoDeDupla.getTenista().getId() !=0 && inscricaoDeDupla.getTenistaParceiro().getId() == 0){
			
			
			Nivel nivel = nivelDao.busca(inscricaoDeDupla.getNivel().getId());
			Tenista tenista;
			
			// o nível escolhido é de simples
			if(nivel!= null & nivel.isSimples()){
			
				// o tenista é do mesmo sexo do nível escolhido
				tenista = tenistaDao.busca(inscricaoDeDupla.getTenista().getId());
				String sexoTenista = tenista.getSexo().getNome();
				Sexo sexoNivel = nivel.getSexo(); 
				if((sexoNivel == null) || (sexoTenista.equals(sexoNivel.getNome()))){
				
					// transforma a inscrição em simples
					Inscricao inscricao = new Inscricao();
					Torneio torneio = new Torneio();
					torneio.setId(inscricaoDeDupla.getTorneio().getId());
					inscricao.setNivel(nivel);
					inscricao.setTenista(tenista);
					inscricao.setTorneio(torneio);
					inscreverTenistaSimplesPorAdministrador(inscricao, tenista);
				
				}else{
					
					validator.add(new SimpleMessage("inscricao.tenista",
							"O sexo do " + Utils.ESPORTISTA + " deve ser adequado ao nível técnico escolhido."));					
					validator.onErrorRedirectTo(this)
					.formInscreveTenistasEmTorneioDeSimplesEDuplas(inscricaoDeDupla.getTorneio().getId());		
					
				}
			
			}else{
				
				validator.add(new SimpleMessage("inscricao.tenista",
						"Escolha um nível técnico de simples, já que só selecionou apenas um " + Utils.ESPORTISTA + "."));
				validator.onErrorRedirectTo(this)
				.formInscreveTenistasEmTorneioDeSimplesEDuplas(inscricaoDeDupla.getTorneio().getId());		
				
			}
		}
		
		// está tentando inscrever uma dupla
		else if (inscricaoDeDupla.getTenista().getId() != 0
				&& inscricaoDeDupla.getTenistaParceiro().getId() != 0) {

			Nivel nivel = nivelDao.busca(inscricaoDeDupla.getNivel().getId());
			Tenista tenista, tenistaParceiro;
			
			// o nível técnico é de duplas
			if(nivel!= null && nivel.isDuplas()){

				// o tenista é do mesmo sexo do nível escolhido
				tenista = tenistaDao.busca(inscricaoDeDupla.getTenista().getId());
				tenistaParceiro = tenistaDao.busca(inscricaoDeDupla.getTenistaParceiro().getId());
				String sexoNivel = nivel.getSexo()!=null?nivel.getSexo().getNome():null;
				String sexoTenista = tenista.getSexo().getNome();
				String sexoTenistaParceiro = tenistaParceiro.getSexo().getNome();

				// os tenistas escolhidos são diferentes
				if (tenista.getId().intValue() != tenistaParceiro.getId().intValue()){
					
					// se o nível não for de mista, só aceita inscrição de sexos iguais
					if(!nivel.isMista()){
					
						
						
						if ((sexoNivel == null) || (sexoTenista.equals(sexoNivel) && (sexoTenistaParceiro.equals(sexoNivel)))) {
	
							inscreverDuplaTenistasPorAdministrador(inscricaoDeDupla, tenista, tenistaParceiro);
	
						} else {
							validator.add(new SimpleMessage("inscricao",
									"Escolha um " + Utils.ESPORTISTA +  " adequado ao nível técnico para duplas que não são mistas."));
							validator.onErrorRedirectTo(this)
							.formInscreveTenistasEmTorneioDeSimplesEDuplas(inscricaoDeDupla.getTorneio().getId());		
						}
	
					}
					// o nível técnico é de mista
					else {
	
						if (!sexoTenista.equals(sexoTenistaParceiro)) {
	
							inscreverDuplaTenistasPorAdministrador(inscricaoDeDupla, tenista, tenistaParceiro);
	
						} else {
							validator.add(new SimpleMessage("inscricao.tenista",
									"Escolha " + Utils.ESPORTISTA + "(s) de sexo(s) opostos no nível técnico do tipo misto."));
							validator.onErrorRedirectTo(this)
							.formInscreveTenistasEmTorneioDeSimplesEDuplas(inscricaoDeDupla.getTorneio().getId());		
						}
						
					}
				}else{
					validator.add(new SimpleMessage("inscricao.tenista",
							"Escolha " + Utils.ESPORTISTA + "(s) diferente(s), do mesmo sexo não pode formar uma dupla."));
					validator.onErrorRedirectTo(this)
					.formInscreveTenistasEmTorneioDeSimplesEDuplas(inscricaoDeDupla.getTorneio().getId());		
					
				}

			}else{
				
				validator.add(new SimpleMessage("inscricao.tenista",
						"Escolha um nível técnico de duplas, já que selecionou dois " + Utils.ESPORTISTA+ "s"));
				validator.onErrorRedirectTo(this)
				.formInscreveTenistasEmTorneioDeSimplesEDuplas(inscricaoDeDupla.getTorneio().getId());		
			
				
			}
		} else {


			validator.add(new SimpleMessage("inscricao.tenista",
					"Escolha um " + Utils.ESPORTISTA + " ou a dupla de " + Utils.ESPORTISTA + "s."));
			validator.validate(inscricaoDeDupla);
			validator.onErrorRedirectTo(this)
					.formInscreveTenistasEmTorneioDeSimplesEDuplas(inscricaoDeDupla.getTorneio().getId());		
		}
		
		
		
		
	}

	/**
	 * Método que gerencia as inscrições de um torneio de acordo com o id da
	 * inscrição e a ação que deve ser efetuada
	 * 
	 * @param idInscricao
	 *            identifica a inscrição que será alterada
	 * @param acao
	 *            identifica a ação mapeada por uma constante
	 * 
	 * */
	@Get("/inscricao/{idInscricao}/acao/{acao}")
	public void gerenciaInscricaoAcao(Long idInscricao, Integer acao) {

		// Pega a inscrição

		Inscricao inscricao = inscricaoDao.busca(idInscricao);

		if (acao == CONFIRMA_INSCRICAO) {
			inscricao.setConfirmada(true);
			result.include("mensagemDeSucesso",
					"Inscrição confirmada com sucesso!");
			inscricao = inscricaoDao.atualiza(inscricao);
			result.redirectTo(InscricaoController.class)
					.panelGerenciaInscricoes(inscricao.getTorneio().getId());
		}
		if (acao == CANCELA_INSCRICAO) {
			inscricao.setConfirmada(false);
			result.include("mensagemDeSucesso",
					"Inscrição cancelada com sucesso!");
			inscricao = inscricaoDao.atualiza(inscricao);
			result.redirectTo(InscricaoController.class)
					.panelGerenciaInscricoes(inscricao.getTorneio().getId());
		}
		if (acao == CONFIRMA_PAGAMENTO) {
			inscricao.setConfirmada(true);
			inscricao.setPaga(true);
			result.include("mensagemDeSucesso",
					"Pagamento confirmado com sucesso!");
			inscricao = inscricaoDao.atualiza(inscricao);
			result.redirectTo(InscricaoController.class)
					.panelGerenciaInscricoes(inscricao.getTorneio().getId());
		}
		if (acao == CANCELA_PAGAMENTO) {
			inscricao.setPaga(false);
			inscricao.setFormaDePagamento("");
			result.include("mensagemDeSucesso",
					"Pagamento cancelado com sucesso!");
			inscricao = inscricaoDao.atualiza(inscricao);
			result.redirectTo(InscricaoController.class)
					.panelGerenciaInscricoes(inscricao.getTorneio().getId());
		}
		if (acao == CANCELA_PAGAMENTO_BOLSA) {
			inscricao.setPaga(false);
			inscricao.setBolsista(false);
			result.include("mensagemDeSucesso",
					"Cancelamento de bolsa realizado com sucesso!");
			inscricao = inscricaoDao.atualiza(inscricao);
			result.redirectTo(InscricaoController.class)
					.panelGerenciaInscricoes(inscricao.getTorneio().getId());
		}
		if (acao == ATRIBUI_BOLSA) {
			inscricao.setBolsista(true);
			inscricao.setPaga(true);
			result.include("mensagemDeSucesso", "Bolsa atribuída com sucesso!");
			inscricao = inscricaoDao.atualiza(inscricao);
			result.redirectTo(InscricaoController.class)
					.panelGerenciaInscricoes(inscricao.getTorneio().getId());
		}

		if (acao == REMOVE_INSCRICAO) {
			if (!inscricao.getTorneio().isChaveGerada()) {
				inscricao.setRemovida(true);
				inscricao.setConfirmada(false);
				result.include("mensagemDeSucesso",
						"Inscrição removida com sucesso!");
				inscricao = inscricaoDao.atualiza(inscricao);
				String mensagem = " Prezado (a) " + Utils.ESPORTISTA +  ", \n\n"
						+ "Inscrição "
						+ inscricao.getNivel().getDescricao()
						+ " do "
						+ inscricao.getTorneio().getNome()
						+ " foi removida por "
						+ tenistaLogado.getTenista().getNomeRanking()
						+ " \n\n"
						+ "Se você desconhece esse cancelamento entre em contato com " + Utils.EMAIL;

				emailSender.enviaEmailPadrao(inscricao.getTenista(),
						"INSCRIÇÃO EM TORNEIO REMOVIDA COM SUCESSO!", mensagem,
						EmailSender.UTF_8, EmailSender.TEXT_PLAIN);
				result.redirectTo(InscricaoController.class)
						.panelGerenciaInscricoes(inscricao.getTorneio().getId());
			} else {
				result.redirectTo(IndexController.class)
						.mensagemErro(
								"É impossível remover uma inscrição com a chave gerada");
			}

		}

	}

	/**
	 * Método que gerencia as inscrições de um torneio de acordo com o id da
	 * inscrição e a ação que deve ser efetuada
	 * 
	 * @param idInscricao
	 *            identifica a inscrição que será alterada
	 * @param acao
	 *            identifica a ação mapeada por uma constante
	 * 
	 * */
	@Get("/inscricao/inscritos-no-torneio/{idInscricao}/")
	public void removeInscricaoPessoal(Long idInscricao) {

		// Pega a inscrição

		Inscricao inscricao = inscricaoDao.busca(idInscricao);
		inscricao.setRemovida(true);
		inscricao.setConfirmada(false);
		result.include("mensagemDeSucesso", "Inscrição removida com sucesso!");

		inscricao = inscricaoDao.atualiza(inscricao);
		String mensagem = " Prezado (a) " + Utils.ESPORTISTA +  ", \n\n"
				+ "Inscrição "
				+ inscricao.getNivel().getDescricao()
				+ " do "
				+ inscricao.getTorneio().getNome()
				+ " foi removida por "
				+ tenistaLogado.getTenista().getNomeRanking()
				+ " \n\n"
				+ "Se você desconhece esse cancelamento entre em contato com " + Utils.EMAIL;

		emailSender.enviaEmailPadrao(inscricao.getTenista(),
				"INSCRIÇÃO EM TORNEIO REMOVIDA COM SUCESSO!", mensagem,
				EmailSender.UTF_8, EmailSender.TEXT_PLAIN);

		result.redirectTo(InscricaoController.class)
				.visualizaInscritosNoTorneio(inscricao.getTorneio().getId(),
						false);
	}

	/**
	 * Método que reinicia cabeças de chave
	 * */
	@Post("/inscricao/")
	public void reiniciaCabecasDeChave(String idTorneio, String idNivel) {

		// Pega a inscrição
		Torneio torneio = torneioDao.busca(Long.parseLong(idTorneio));
		Nivel nivel = nivelDao.find(Integer.parseInt(idNivel));
		List<Inscricao> inscricoes = inscricaoDao
				.getInscricoesPorNivelETorneio(nivel, torneio, 0);
		for (Inscricao inscricao : inscricoes) {
			inscricao.setNumeroCabecaDeChave(null);
			inscricaoDao.atualiza(inscricao);
		}

		result.include("mensagemDeSucesso", "Cabeças de chaves reiniciados");
		result.redirectTo(InscricaoController.class).panelGerenciaInscricoes(
				torneio.getId());
	}

	/**
	 * Método que carrega todos os inscritos de um determinado torneio
	 * 
	 * @param idTorneio
	 *            identifica o torneio
	 * */
	@Transactional
	@Get("/inscricao/inscritos-no-torneio/{idTorneio}/{imprimirInscritos}")
	public void visualizaInscritosNoTorneio(Long idTorneio,
			boolean imprimirInscritos) {

		Torneio torneio = torneioDao.busca(idTorneio);

		// if(!torneio.isDuplas()){
		List<Inscricao> inscricoes = inscricaoDao
				.getInscricoesPorTorneio(torneio);
		result.include("numeroDeInscricoes", inscricoes.size());
		result.include("inscricoes", inscricoes);
		/*
		 * }else{ List<InscricaoDeDupla> inscricoes =
		 * inscricaoDeDuplaDao.getInscricoesPorTorneio(torneio);
		 * result.include("numeroDeInscricoes", inscricoes.size());
		 * result.include("inscricoes", inscricoes); }
		 */

		result.include("torneio", torneio);
		result.include("imprimirInscritos", imprimirInscritos);
		result.include("niveisTecnicosDoTorneio", torneio.getNiveis());
		MidiaTorneio midiaTorneio = midiaTorneioDao
				.getPDFInscritosPorTorneio(idTorneio);
		if (midiaTorneio != null) {
			result.include("inscritosPDF", midiaTorneio.getUrl());
		}

	}

	/**
	 * Método que carrega todos os inscritos de um determinado torneio por Nível técnico
	 * 
	 * @param idTorneio
	 *            identifica o torneio
	 * */
	@Transactional
	@Post("/inscricao/inscritos-no-torneio-por-nivel-tecnico")
	public void visualizaInscritosNoTorneioPorNivel(Inscricao inscricao) {

		Torneio torneio = torneioDao.busca(inscricao.getTorneio().getId());
		Nivel nivel = nivelDao.busca(inscricao.getNivel().getId());
		// if(!torneio.isDuplas()){
		
		List<Inscricao> inscricoes = inscricaoDao
				.getInscricoesPorNivelETorneio(nivel, torneio, InscricaoDAO.NUMERO_DO_GRUPO);
		List<Integer> gruposDoNivelNoTorneio = inscricaoDao.getGruposPorNivelETorneio(nivel,torneio);
		
		result.include("numeroDeInscricoes", inscricoes.size());
		result.include("inscricoes", inscricoes);
		result.include("torneio", torneio);
		result.include("imprimirInscritos", false);
		result.include("nivel", nivel);
		result.include("niveisTecnicosDoTorneio", torneio.getNiveis());
		result.include("gruposDoNivelNoTorneio",gruposDoNivelNoTorneio);
		MidiaTorneio midiaTorneio = midiaTorneioDao
				.getPDFInscritosPorTorneio(torneio.getId());
		if (midiaTorneio != null) {
			result.include("inscritosPDF", midiaTorneio.getUrl());
		}

	}

	/**
	 * Método que carrega todos os inscritos de um determinado torneio por Nível técnico
	 * 
	 * @param idTorneio
	 *            identifica o torneio
	 * */
	@Transactional
	@Get("/inscricao/inscritos-no-torneio-por-nivel-tecnico-por-grupo/{numeroGrupo}/{idNivel}/{idTorneio}")
	public void visualizaInscritosNoGrupoDoNivelDoTorneio(Integer numeroGrupo, Integer idNivel, Long idTorneio) {

		Torneio torneio = torneioDao.busca(idTorneio);
		Nivel nivel = nivelDao.busca(idNivel);
		// if(!torneio.isDuplas()){
		
		List<Inscricao> inscricoes = inscricaoDao
				.getInscricoesDoGrupoPorNivelETorneio(numeroGrupo, nivel, torneio);
		List<Integer> gruposDoNivelNoTorneio = inscricaoDao.getGruposPorNivelETorneio(nivel,torneio);
		ClassificacaoDeGrupo classificacaoDeGrupo = new ClassificacaoDeGrupo();
		
		result.include("numeroDeInscricoes", inscricoes.size());
		result.include("inscricoes", inscricoes);
		result.include("torneio", torneio);
		result.include("imprimirInscritos", false);
		result.include("nivel", nivel);
		result.include("niveisTecnicosDoTorneio", torneio.getNiveis());
		result.include("gruposDoNivelNoTorneio",gruposDoNivelNoTorneio);
		MidiaTorneio midiaTorneio = midiaTorneioDao
				.getPDFInscritosPorTorneio(torneio.getId());
		if (midiaTorneio != null) {
			result.include("inscritosPDF", midiaTorneio.getUrl());
		}
		
		// se for um torneio de grupos incluimos o resultado classificatorio
		if (torneio.isMisto2Estagios()) {
			Fase faseGrupo = faseDao.busca(Fase.GRUPO);
			result.include("classificacoesEmGrupo", classificacaoDeGrupo.classificaGrupo(nivel, torneio, numeroGrupo, faseGrupo));
		}

		// recupera os jogos do grupo do nível do torneio
		List<JogoDeTorneio> jogosDoTorneio = jogoDeTorneioDao.getJogosDoTorneioPorNivelEFase(nivel.getId(), torneio.getId(), Fase.GRUPO);

		result.include("jogos", jogosDoTorneio);

		if(tenistaLogado.isAdminOrOrganizadorProprietario(torneio)){
			result.include("adminOrganizadorTorneio", true);
		}

		

	}

	/**
	 * Método que carrega todos os inscritos de um determinado torneio
	 * 
	 * 
	 * */
	@Transactional
	@Get("/inscricao/ultimos-inscritos")
	public void visualizaUltimosInscritos() {

		List<Inscricao> inscricoes = inscricaoDao.listaTodos();
		Integer numeroDeInscritos = inscricoes.size();
		if (numeroDeInscritos > FIM_LISTA) {
			Integer inicioDalista = inscricoes.size() - FIM_LISTA;
			Integer fimDalista = inscricoes.size() - 1;
			inscricoes = inscricoes.subList(inicioDalista, fimDalista);
		}
		result.include("numeroDeInscritos", numeroDeInscritos);
		result.include("inscricoes", inscricoes);

	}

	/**
	 * Método que atualiza os cabeças de chave do torneio
	 * 
	 * @param idInscricao
	 *            identifica a inscrição do tenista que será cabeça de chave do
	 *            torneio
	 * @numeroCabecaDeChave identifica o número do cabeça de chave do torneio
	 * */
	@Post("/inscricao/gerenciamento-de-inscricoes")
	public void salvaInscricao(Long idInscricao, Integer numeroCabecaDeChave,
			String formaDePagamento) {

		Inscricao inscricao = inscricaoDao.find(idInscricao);

		if (numeroCabecaDeChave != null && numeroCabecaDeChave < 1) {

			result.redirectTo(InscricaoController.class)
					.panelGerenciaInscricoes(inscricao.getTorneio().getId());
			inscricao.setFormaDePagamento(formaDePagamento);

		} else {

			inscricao.setNumeroCabecaDeChave(numeroCabecaDeChave);
			inscricao.setFormaDePagamento(formaDePagamento);
			if (formaDePagamento != null && formaDePagamento.length() > 1) {
				inscricao.setPaga(true);
				inscricao.setConfirmada(true);
			}
			inscricao = inscricaoDao.atualiza(inscricao);
			result.redirectTo(InscricaoController.class)
					.panelGerenciaInscricoes(inscricao.getTorneio().getId());

		}
	}

	/**
	 * Método que informa o resultado do tenista no torneio
	 * 
	 * @param inscricao
	 *            identifica a inscricao do tenista que será atualizada para a
	 *            colocação e pontuação do tenista
	 * */
	@Post("/inscricao")
	public void informaResultadoDoTenista(Inscricao inscricao) {

		Inscricao inscricaoAtualizada = inscricaoDao.busca(inscricao.getId());

		Colocacao colocacao = colocacaoDao.find(inscricao.getColocacao()
				.getId());
		inscricaoAtualizada.setColocacao(colocacao);
		inscricaoAtualizada.setPontuacao(inscricao.getPontuacao());

		inscricaoDao.atualiza(inscricaoAtualizada);
		result.redirectTo(InscricaoController.class)
				.panelGerenciaInscricoesConfirmadas(
						inscricao.getTorneio().getId());
	}

	/**
	 * Método que confirma todas as inscrições em um torneio
	 * 
	 * @param torneio
	 *            identifica o torneio
	 * */
	@Get("/inscricao/confirma-todas-inscricoes/{idTorneio}")
	public void confirmaTodasInscricoes(Long idTorneio) {

		Torneio torneio = torneioDao.busca(idTorneio);
		List<Inscricao> inscricoes = inscricaoDao
				.getInscricoesPorTorneio(torneio);

		for (Inscricao inscricao : inscricoes) {
			inscricao.setConfirmada(true);
			inscricaoDao.atualiza(inscricao);
		}

		result.redirectTo(InscricaoController.class).panelGerenciaInscricoes(
				idTorneio);
	}

	/**
	 * Método que confirma todos os pagamentos em um torneio
	 * 
	 * @param torneio
	 *            identifica o torneio
	 * */
	@Get("/inscricao/confirma-todos-pagamentos/{idTorneio}")
	public void confirmaTodosPagamentos(Long idTorneio) {

		Torneio torneio = torneioDao.busca(idTorneio);
		List<Inscricao> inscricoes = inscricaoDao
				.getInscricoesPorTorneio(torneio);

		for (Inscricao inscricao : inscricoes) {
			inscricao.setPaga(true);
			inscricao.setConfirmada(true);
			inscricaoDao.atualiza(inscricao);
		}

		result.redirectTo(InscricaoController.class).panelGerenciaInscricoes(
				idTorneio);
	}

	/**
	 * Método que reinicia chaves do torneio
	 * 
	 * @param torneio
	 *            identifica o torneio
	 * */
	@Get("/inscricao/reinicia-chaves-do-torneio/{idTorneio}/{idNivel}")
	public void reiniciaChavesDoTorneio(Long idTorneio, int idNivel) {

		Torneio torneio = torneioDao.busca(idTorneio);
		Nivel nivel = nivelDao.busca(idNivel);
		List<Inscricao> inscricoes = inscricaoDao
				.getInscricoesPorNivelETorneio(nivel, torneio, null);

		for (Inscricao inscricao : inscricoes) {
			inscricao.setNumeroCabecaDeChave(null);
			;
			inscricaoDao.atualiza(inscricao);
		}

		result.redirectTo(InscricaoController.class).panelGerenciaInscricoes(
				idTorneio);
	}

	/**
	 * Método que edita uma inscricao
	 * 
	 * @param inscricao
	 *            identifica a isncrição que será editada
	 * */
	@Post("/inscricao/form-edita-inscricao")
	public void confirmaEdicaoDeInscricao(Inscricao inscricao) {

		// Nivel nivel = nivelDao.busca(inscricao.getNivel().getId());
		// Tenista tenista = tenistaDao.busca(inscricao.getTenista().getId());
		// Torneio torneio = torneioDao.busca(inscricao.getTorneio().getId());

		Inscricao inscricaoAlterada = inscricaoDao.busca(inscricao.getId());

		// inscricaoAlterada.setNivel(nivel);
		// inscricaoAlterada.setTenista(tenista);
		// inscricaoAlterada.setTorneio(torneio);

		inscricaoAlterada.setHorarioEspecial(inscricao.getHorarioEspecial());
		// inscricaoAlterada.setData(Calendar.getInstance());
		// inscricao.setAnoReferencia(Calendar.getInstance().get(Calendar.YEAR));

		if (inscricaoAlterada.getTorneio().isRankingDeClube()) {
			Nivel nivel = nivelDao.busca(inscricao.getNivel().getId());
			inscricaoAlterada.setNivel(nivel);
		}

		inscricaoDao.atualiza(inscricaoAlterada);

		result.include("mensagemDeSucesso",
				"O horário especial foi alterado com sucesso.");
		result.redirectTo(InscricaoController.class).panelGerenciaInscricoes(
				inscricao.getTorneio().getId());
	}

	/**
	 * Método que valida os dados de uma inscrição
	 * 
	 * @param inscricao
	 * @param tenista
	 * @param torneio
	 */
	private void validaInscricao(Inscricao inscricao, Tenista tenista,
			Torneio torneio) {
		// Validação da inscrição
		if (tenista.isAtivo() == false) {
			validator
					.add(new SimpleMessage(
							"inscricao.tenista.ativo",
							"O tenista está desativado para inscrições em torneios, possivelmente com alguma suspensão. Entre em contato com o administrador."));
		}
		inscricao.setNivel(nivelDao.find(inscricao.getNivel().getId()));
		if (inscricao.getNivel().getTipoNivel() == TipoNivel.TECNICO) {
			if (inscricao.getTenista().getNivelTecnicoPrincipal().getId() < inscricao
					.getNivel().getId()) {
				validator
						.add(new SimpleMessage("inscricao.nivel",
								"O " + Utils.ESPORTISTA + " só pode se inscrever em uma categoria tecnicamente superior a dele."));
			}
		}
		if (inscricao.getNivel().getTipoNivel() == TipoNivel.MASCULINO) {
			if (inscricao.getTenista().getNivelTecnicoPrincipal().getCodigo() < inscricao
					.getNivel().getCodigo()) {
				validator
						.add(new SimpleMessage(
								"inscricao.nivel",
								"O " + Utils.ESPORTISTA + " só pode se inscrever em uma categoria igual ou tecnicamente superior a dele."));
			}
			if (tenista.getSexo() != Sexo.MASCULINO) {
				validator
						.add(new SimpleMessage(
								"inscricao.sexo",
								"Apenas " + Utils.ESPORTISTA + "s do sexo masculino podem se inscrever nesta nível de categoria do torneio."));
			}
		}

		if (!(torneio.getDataInicioInscricao().getTime()
				.before(Calendar.getInstance().getTime()) && torneio
				.getDataFimInscricao().getTime().after(Utils.obtemOntem()))) {
			validator
					.add(new SimpleMessage("inscricao.data",
							"A inscrição deve ser realizada dentro do período permitido."));
		}

		if (inscricao.getNivel().getTipoNivel() == TipoNivel.IDADE) {
			if (tenista.getDataNascimento() == null) {
				validator
						.add(new SimpleMessage(
								"inscricao.nivel",
								"O " + Utils.ESPORTISTA + " está sem uma data de nascimento, por isso não pode se inscrever em um torneio por idade"));
			}
		}
		if (inscricaoDao.existeInscricao(inscricao)) {
			validator
					.add(new SimpleMessage(
							"inscricao",
							"Uma inscricao para o " + Utils.ESPORTISTA + ": "
									+ tenista.getNome()
									+ " no(a) "
									+ torneio.getNome()
									+ " neste nível já existe ou já foi solicitada no sistema."));
		}
		if (inscricao.getNivel().getTipoNivel() == TipoNivel.FEMININO) {

			if (tenista.getSexo() != Sexo.FEMININO) {
				validator
						.add(new SimpleMessage(
								"inscricao.sexo",
								"Apenas " + Utils.ESPORTISTA + "s do sexo feminino podem se inscrever neste nível de categoria do torneio."));
			}

			if (tenista.getNivelTecnicoPrincipal().getCodigo() < inscricao
					.getNivel().getCodigo()) {
				validator
						.add(new SimpleMessage(
								"inscricao.nivel",
								"A " + Utils.ESPORTISTA + " só pode se inscrever em uma categoria igual ou tecnicamente superior a dela."));

			}

		}

		validator.validate(inscricao);

	}

	/**
	 * Método que monta a mensagem que será enviada para o tenista inscrito no
	 * torneio.
	 * 
	 * @param inscricao
	 *            possui os dados da inscrição realizada
	 * @param torneio
	 *            identifica o torneio da inscrição
	 * @param tenista
	 *            identifica o tenista administrador ou organizador que está
	 *            realizando a inscrição
	 * 
	 * */
	private String obtemMensagemDeInscricao(Inscricao inscricao,
			Torneio torneio, Tenista tenista) {
		String mensagem = " A sua inscrição no " + torneio.getNome()
				+ " foi realizada e confirmada por "
				+ tenistaLogado.getTenista().getNome() + ",";
		if (torneio.getOrganizadorDoTorneio(tenistaLogado.getTenista())) {
			mensagem += " que é o organizador(a) do torneio.\n\n";
		} else {
			mensagem += " que é administrador do sistema.\n\n";
		}
		mensagem += "Para mais informações do torneio acesse: " + "http://"
				+ request.getHeader("HOST") + request.getContextPath()
				+ "/torneio/detalhes-do-torneio/" + torneio.getId() + "\n\n";
		mensagem += "Dados de sua inscrição: \n\n "
				+ "Número de identificação: "
				+ inscricao.getId()
				+ "\n\n"
				+ "Nome: "
				+ inscricao.getTenista().getNomeCompleto()
				+ "\n\n"
				+ "Classe ou nível técnico: "
				+ inscricao.getNivel().getDescricao()
				+ "\n\n"
				+ "Informações de pagamento: "
				+ inscricao.getTorneio().getInformacoesPagamento()
				+ "\n\n"
				+ "Observação: "
				+ inscricao.getTorneio().getObservacoesAdicionais()
				+ "\n\n\n"
				+ "Realize o quanto antes o pagamento da inscrição.\n"
				+ "Acesse: " + Utils.SITE + " para mais informações.\n "
				+ "Se você desconhece essa inscrição ou tem alguma dúvida entre em contato conosco pelo e-mail " + Utils.EMAIL;
		return mensagem;
	}

	/**
	 * Método que valida a inscrição de duplas de acordo com as informações dos
	 * tenistas, torneio e da inscrição
	 */
	private void validaInscricaoDeDuplas(InscricaoDeDupla inscricao,
			Torneio torneio, Tenista tenista, Tenista tenistaParceiro) {

		// obtém o esporte e verifica qual o nível técnico deve ser comparado
		TipoEsporte tipoEsporte = inscricao.getNivel().getTipoEsporte();
		
		Nivel nivelTecnico1 = null;
		Nivel nivelTecnico2 = null;


		
		if(tipoEsporte == TipoEsporte.BEACH_TENNIS){
			nivelTecnico1 = inscricao.getTenista().getNivelTecnicoBeachTennis();
			nivelTecnico2 = inscricao.getTenistaParceiro().getNivelTecnicoBeachTennis();
		}
		if(tipoEsporte == TipoEsporte.TENNIS){
			nivelTecnico1 = inscricao.getTenista().getNivelTecnicoPrincipal();
			nivelTecnico2 = inscricao.getTenistaParceiro().getNivelTecnicoPrincipal();
		}

		// Validação da inscrição

		if ((tenista.isAtivo() == false) || (tenistaParceiro.isAtivo() == false)) {
			validator
					.add(new SimpleMessage(
							"inscricao.tenista.ativo",
							"O " + Utils.ESPORTISTA + " está desativado para inscrições em torneios, possivelmente com alguma suspensão. Entre em contato com o administrador."));
		}

		if(inscricao.getNivel().isMista()){
			if(tenista.getSexo().equals(tenistaParceiro.getSexo())){
				validator.add(new SimpleMessage("inscricao.tenista.sexo", "Inscrição na mista precisa ser com " + Utils.ESPORTISTA + "s de sexos distintos."));
			}
		}

		if(inscricao.getNivel().isMasculino()){
			if(!tenista.getSexo().equals(Sexo.MASCULINO) || !tenistaParceiro.getSexo().equals(Sexo.MASCULINO)){
				validator.add(new SimpleMessage("inscricao.tenista.sexo", "Inscrição na categoria masculino precisa ser com " + Utils.ESPORTISTA + "s do sexo masculino."));
			}
		}

		if(inscricao.getNivel().isFeminino()){
			if(!tenista.getSexo().equals(Sexo.FEMININO) || !tenistaParceiro.getSexo().equals(Sexo.FEMININO)){
				validator.add(new SimpleMessage("inscricao.tenista.sexo", "Inscrição na categoria feminino precisa ser com " + Utils.ESPORTISTA + "s do sexo feminino."));
			}
		}

		
		// verifica se a inscricao é em um nível de dupla de soma ou de dupla
		// de classe
		if (inscricao.getNivel().getTipoDeDupla() == TipoDupla.CLASSE) {
			
			
			
			if (nivelTecnico1.getCodigo() < inscricao
					.getNivel().getCodigo()) {
				validator
						.add(new SimpleMessage(
								"inscricao.nivel",
								"O nível técnico escolhido na inscrição de "
										+ inscricao.getTenista().getNome()
										+ " é tecnicamente inferior ao nível técnico do cadastro."));
			}
			if (nivelTecnico2.getCodigo() < inscricao.getNivel().getCodigo()) {
				validator
						.add(new SimpleMessage(
								"inscricao.nivel",
								"O nível técnico escolhido na inscrição de "
										+ inscricao.getTenistaParceiro()
												.getNome()
										+ " é tecnicamente inferior ao nível técnico cadastro."));
			}

		} else if (inscricao.getNivel().getTipoDeDupla() == TipoDupla.SOMA) {

			//inscricao.setNivel(inscricao.getNivel());
			int soma = nivelTecnico1.getCodigo() + nivelTecnico2.getCodigo();

			switch (inscricao.getNivel().getCodigo()) {
			case Nivel.SOMA3_DUPLAS:
				if (soma < 3) {
					validator.add(new SimpleMessage("inscricao.nivel",
							"A soma dos níveis técnicos dos " + Utils.ESPORTISTA + "s é inferior a 3."));
				}
				break;

			case Nivel.SOMA4_DUPLAS:
				if (soma < 4) {
					validator.add(new SimpleMessage("inscricao.nivel",
							"A soma dos níveis técnicos dos " + Utils.ESPORTISTA + "s é inferior a 4."));
				}
				break;

			case Nivel.SOMA5_DUPLAS:
				if (soma < 5) {
					validator.add(new SimpleMessage("inscricao.nivel",
							"A soma dos níveis técnicos dos " + Utils.ESPORTISTA + "s é inferior a 5."));
				}
				break;

			case Nivel.SOMA6_DUPLAS:
				if (soma < 6) {
					validator.add(new SimpleMessage("inscricao.nivel",
							"A soma dos níveis técnicos dos " + Utils.ESPORTISTA + "s é inferior a 6."));
				}
				break;
			case Nivel.SOMA7_DUPLAS:
				if (soma < 7) {
					validator.add(new SimpleMessage("inscricao.nivel",
							"A soma dos níveis técnicos dos " + Utils.ESPORTISTA + "s é inferior a 7."));
				}
				break;

			case Nivel.SOMA8_DUPLAS:
				if (soma < 8) {
					validator.add(new SimpleMessage("inscricao.nivel",
							"A soma dos níveis técnicos dos " + Utils.ESPORTISTA + "s é inferior a 8."));
				}
				break;

			case Nivel.SOMA9_DUPLAS:
				if (soma < 9) {
					validator.add(new SimpleMessage("inscricao.nivel",
							"A soma dos níveis técnicos dos " + Utils.ESPORTISTA + "s é inferior a 9."));
				}
				break;

			case Nivel.SOMA10_DUPLAS:
				if (soma < 10) {
					validator
							.add(new SimpleMessage("inscricao.nivel",
									"A soma dos níveis técnicos dos " + Utils.ESPORTISTA + "s é inferior a 10."));
				}
				break;

			default:
				break;
			}

		}

		if (!(torneio.getDataInicioInscricao().getTime()
				.before(Calendar.getInstance().getTime()) && torneio
				.getDataFimInscricao().getTime().after(Utils.obtemOntem()))) {
			validator
					.add(new SimpleMessage("inscricao.data",
							"A inscrição deve ser realizada dentro do período permitido."));
		}

		if (inscricaoDeDuplaDao.existeInscricao(inscricao)) {
			validator
					.add(new SimpleMessage(
							"inscricao",
							"Uma inscricao para um dos " + Utils.ESPORTISTA +  "s no(a) "
									+ torneio.getNome()
									+ " neste nível já existe ou já foi solicitada no sistema."));
		}

		if (tenista.getId() == tenistaParceiro.getId()) {
			validator.add(new SimpleMessage("inscricao",
					"Os " +  Utils.ESPORTISTA + "s  selecionados são idênticos."));

		}
		if (inscricao.getTenista() == null
				|| inscricao.getTenistaParceiro() == null) {
			validator.add(new SimpleMessage("inscricao",
					"Selecione o " + Utils.ESPORTISTA + " da dupla."));
		}

		validator.validate(inscricao);

	}

	/**
	 * Método que calcula a pontuação da dupla
	 * 
	 * @param inscricao
	 * @param tenistaParceiro
	 * @param tenista
	 */
	private Integer obterPontosDaDuplaInscrita(InscricaoDeDupla inscricao,
			Tenista tenistaParceiro, Tenista tenista) {

		Pontuacao pontuacaoTenista = pontuacaoDao.getPontuacao(tenista,
				inscricao.getNivel(), inscricao.getAnoReferencia());
		Pontuacao pontuacaoTenistaParceiro = pontuacaoDao.getPontuacao(
				tenistaParceiro, inscricao.getNivel(),
				inscricao.getAnoReferencia());

		int pontosTenista = pontuacaoTenista != null ? pontuacaoTenista
				.getPontos() : 0;
		int pontosTenistaParceiro = pontuacaoTenistaParceiro != null ? pontuacaoTenistaParceiro
				.getPontos() : 0;
		return (pontosTenista + pontosTenistaParceiro);
	}

	private String inserirInscricaoSimples(Inscricao inscricao, Tenista tenista) {
		String mensagem;
		Torneio torneio = torneioDao.busca(inscricao.getTorneio().getId());
		IndicacaoInscricao indicacaoInscricao = indicacaoInscricaoDao.busca(inscricao.getIndicacaoInscricao().getId());

		inscricao.setTenista(tenista);

		inscricao.setTorneio(torneio);
		
		inscricao.setIndicacaoInscricao(indicacaoInscricao);

		inscricao.setData(Calendar.getInstance());
		inscricao.setAnoReferencia(Calendar.getInstance().get(Calendar.YEAR));
		inscricao.setOrdemAleatoria(new Random().nextInt(5000));

		validaInscricao(inscricao, tenista, torneio);
		
		if(torneio.isSimples()){
			validator.onErrorRedirectTo(this).formInscreveSeEmTorneio(
					inscricao.getTorneio().getId());
		}else if(torneio.isSimplesEDuplas()){
			validator.onErrorRedirectTo(this).formInscreveSeEmTorneioDeSimplesEDuplas(
					inscricao.getTorneio().getId());			
		}

		inscricaoDao.adiciona(inscricao);

		emailSender
				.enviaEmailPadrao(
						tenista,
						"Inscrição solicitada para: " + torneio.getNome(),
						"Realize o pagamento da inscrição para ela ser confirmada ou entre em contato com o organizador do torneio.\n\n"
								+ "Número de sua inscrição:"
								+ inscricao.getId()
								+ "\n"
								+ "Organizador: "
								+ torneio.getTenistaOrganizador().getNome()
								+ "\n"
								+ "Telefone: "
								+ torneio.getTenistaOrganizador()
										.getTelefonePrincipal().getNumero()
								+ "\n"
								+ "Categoria / Nível Técnico / Classe: "
								+ inscricao.getNivel().getDescricao()
								+ "\n"
								+ "Informações de pagamento: "
								+ inscricao.getTorneio()
										.getInformacoesPagamento()
								+ "\n"
								+ "Observação: "
								+ inscricao.getTorneio()
										.getObservacoesAdicionais()
								+ "\n\n\nRealize o quanto antes o pagamento da inscrição.\n",
						EmailSender.UTF_8, EmailSender.TEXT_PLAIN);

		mensagem = tenista.getNomeRanking()
				+ ", a sua inscrição foi solicitada"
				+ (tenista.getNivelTecnicoPrincipal().getCodigo() > inscricao
						.getNivel().getCodigo() ? " para uma classe tecnicamente superior a sua"
						: "")
				+ ". Realize o pagamento da inscrição para ela ser confirmada ou entre em contato com o organizador do torneio.";
		return mensagem;
	}

	private String inserirInscricaoDupla(InscricaoDeDupla inscricao) {
	
		Tenista tenista = inscricao.getTenista();
		Torneio torneio = inscricao.getTorneio();
		Tenista tenistaParceiro = inscricao.getTenistaParceiro();
	
		if (inscricao.getTenista() != null
				&& inscricao.getTenistaParceiro() != null) {
			if (!inscricao.getTenista().isSuspenso()
					&& !inscricao.getTenistaParceiro().isSuspenso()) {

				validaInscricaoDeDuplas(inscricao, torneio, tenista,
						tenistaParceiro);

				if(torneio.isDuplas()){
					validator.onErrorRedirectTo(this)
					.formInscreveSeEmTorneioDeDuplas(
							inscricao.getTorneio().getId());
				}else if (torneio.isSimplesEDuplas()){
					validator.onErrorRedirectTo(this)
					.formInscreveSeEmTorneioDeSimplesEDuplas(
							inscricao.getTorneio().getId());					
				}
				
				
				
				inscricaoDeDuplaDao.adiciona(inscricao);
	
				String assunto = "Inscrição solicitada para o torneio de duplas: "
						+ torneio.getNome();
				mensagem = "Realize o pagamento da inscrição para ela ser confirmada ou entre em contato com o organizador do torneio.\n\n"
						+ "Número de sua inscrição:"
						+ inscricao.getId()
						+ "\n"
						+ "Organizador: "
						+ torneio.getTenistaOrganizador().getNome()
						+ "\n"
						+ "Telefone: "
						+ torneio.getTenistaOrganizador()
								.getTelefonePrincipal().getNumero()
						+ "\n"
						+ " Dupla: "
						+ tenista.getNome()
						+ " / "
						+ tenistaParceiro.getNome()
						+ "\n"
						+ "Categoria / Nível Técnico / Classe: "
						+ inscricao.getNivel().getDescricao()
						+ "\n"
						+ "Informações de pagamento: "
						+ inscricao.getTorneio().getInformacoesPagamento()
						+ "\n"
						+ "Observação: "
						+ inscricao.getTorneio().getObservacoesAdicionais()
						+ "\n\n\nRealize o quanto antes o pagamento da inscrição, pendência no pagamento implicará em suspensão de sua conta e você estará impossibilitado de participar de novos torneios do circuíto.\n";
				;
	
				emailSender.enviaEmailPadrao(tenista, assunto, mensagem,
						EmailSender.UTF_8, EmailSender.TEXT_PLAIN);
	
				emailSender.enviaEmailPadrao(tenistaParceiro, assunto,
						mensagem, EmailSender.UTF_8, EmailSender.TEXT_PLAIN);
	
				mensagem = tenista.getNomeRanking()
						+ ", a sua inscrição e a de "
						+ tenistaParceiro.getNome()
						+ " no torneio"
						+ " foi solicitada. Realizem o pagamento para ela ser confirmada, e entre em contato com o organizador.";

			} else {
				mensagem = tenista.getNomeRanking()
						+ " ou "
						+ tenistaParceiro.getNomeRanking()
						+ " está com a conta <span style=\"color:red\"> suspensa e a inscrição não foi realizada </span>, entre em contato por e-mail " + Utils.EMAIL + " para saber o motivo da pendência.";
				result.redirectTo(IndexController.class).mensagemErro(mensagem);
	
			}
		} else {
	
			validator.add(new SimpleMessage("inscricao.tenista",
					"Escolha a dupla de tenistas."));
			validator.validate(inscricao);
			if(torneio.isSimplesEDuplas()){
				validator.onErrorRedirectTo(this).formInscreveSeEmTorneioDeSimplesEDuplas(
						torneio.getId());
			}else if(torneio.isDuplas()){
				validator.onErrorRedirectTo(this).formInscreveSeEmTorneioDeDuplas(
						torneio.getId());
			}
	
		}
		
		return mensagem;

	}

}
