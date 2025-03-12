package br.portaldotenis.portal.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.observer.download.Download;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.portaldotenis.portal.controleacesso.LogadoRule;
import br.portaldotenis.portal.dao.FaseDAO;
import br.portaldotenis.portal.dao.FiliadoDAO;
import br.portaldotenis.portal.dao.InscricaoDAO;
import br.portaldotenis.portal.dao.InscricaoDeDuplaDAO;
import br.portaldotenis.portal.dao.JogoDeTorneioDAO;
import br.portaldotenis.portal.dao.LocalDAO;
import br.portaldotenis.portal.dao.MidiaTorneioDAO;
import br.portaldotenis.portal.dao.NivelDAO;
import br.portaldotenis.portal.dao.TenistaDAO;
import br.portaldotenis.portal.dao.TorneioDAO;
import br.portaldotenis.portal.model.CategoriaTorneio;
import br.portaldotenis.portal.model.ClassificacaoDeGrupo;
import br.portaldotenis.portal.model.Dupla;
import br.portaldotenis.portal.model.Fase;
import br.portaldotenis.portal.model.Inscricao;
import br.portaldotenis.portal.model.InscricaoDeDupla;
import br.portaldotenis.portal.model.Jogo;
import br.portaldotenis.portal.model.JogoDeTorneio;
import br.portaldotenis.portal.model.JogoDeTorneioDeDupla;
import br.portaldotenis.portal.model.MidiaTorneio;
import br.portaldotenis.portal.model.Nivel;
import br.portaldotenis.portal.model.SituacaoTorneio;
import br.portaldotenis.portal.model.Tenista;
import br.portaldotenis.portal.model.TenistaLogado;
import br.portaldotenis.portal.model.TipoDeChave;
import br.portaldotenis.portal.model.TipoMidiaTorneio;
import br.portaldotenis.portal.model.TipoNivel;
import br.portaldotenis.portal.model.TipoEsporte;
import br.portaldotenis.portal.model.Torneio;
import br.portaldotenis.portal.util.Utils;

/**
 * Controller responsável por atender a todas as requisições dos Torneios
 * 
 * @author Thiago Holanda
 * 
 */
@Controller
public class TorneioController {

	public static final Integer INICIA_TORNEIO = 1;
	public static final Integer CANCELA_TORNEIO = 2;
	public static final Integer REINICIA_TORNEIO = 3;
	public static final Integer CONCLUI_TORNEIO = 4;
	private static final Integer QUANTIDADE_MINIMA_INSCRITOS = 4;

	@Inject
	private Result result;

	@Inject
	private LocalDAO localDao;

	@Inject
	private NivelDAO nivelDao;

	@Inject
	private TorneioDAO torneioDao;

	@Inject
	private TenistaLogado tenistaLogado;

	@Inject
	private TenistaDAO tenistaDao;

	@Inject
	private InscricaoDAO inscricaoDao;

	@Inject
	private InscricaoDeDuplaDAO inscricaoDeDuplaDao;

	@Inject
	private LogadoRule logadoRule;

	@Inject
	private MidiaTorneioDAO midiaTorneioDao;

	@Inject
	private FiliadoDAO filiadoDao;

	@Inject
	private JogoDeTorneioDAO jogoDeTorneioDao;

	@Inject
	private FaseDAO faseDao;
	
	@Inject
	private Validator validator;

	// MÉTODOS DE REDIRECIONAMENTO DE PÁGINAS

	/**
	 * Método responsável por redirecionar para a tela de criação de torneio
	 * 
	 * */
	@Transactional
	@Get("/torneio/criacao-de-torneio")
	public void formCriaTorneio() {

		// TODO fazer via controle de acesso
		if (tenistaLogado.getTenista() != null && tenistaLogado.isOrganizador()) {
			// Atribui uma lista para variáveis utilizadas no formulário
			result.include("locais",
					localDao.listaTodos(Torneio.CAMPO_PRINCIPAL_ORDENACAO));
			result.include("niveisTecnicosBT",
					nivelDao.getNiveisPorTipoNivelEsporte(TipoNivel.TECNICO, TipoEsporte.BEACH_TENNIS));
			result.include("niveisTecnicosMasculinoBT",
					nivelDao.getNiveisPorTipoNivelEsporte(TipoNivel.MASCULINO, TipoEsporte.BEACH_TENNIS));
			result.include("niveisTecnicosFemininoBT",
					nivelDao.getNiveisPorTipoNivelEsporte(TipoNivel.FEMININO, TipoEsporte.BEACH_TENNIS));
			result.include("niveisPorIdadeBT",
					nivelDao.getNiveisPorTipoNivelEsporte(TipoNivel.IDADE, TipoEsporte.BEACH_TENNIS));
			result.include("niveisPorGrupoBT",
					nivelDao.getNiveisPorTipoNivelEsporte(TipoNivel.GRUPO, TipoEsporte.BEACH_TENNIS));
			result.include("niveisPorDuplasBT",
					nivelDao.getNiveisPorTipoNivelEsporte(TipoNivel.DUPLAS, TipoEsporte.BEACH_TENNIS));

			result.include("niveisTecnicosTenis",
					nivelDao.getNiveisPorTipoNivelEsporte(TipoNivel.TECNICO, TipoEsporte.TENNIS));
			result.include("niveisTecnicosMasculinoTenis",
					nivelDao.getNiveisPorTipoNivelEsporte(TipoNivel.MASCULINO, TipoEsporte.TENNIS));
			result.include("niveisTecnicosFemininoTenis",
					nivelDao.getNiveisPorTipoNivelEsporte(TipoNivel.FEMININO, TipoEsporte.TENNIS));
			result.include("niveisPorIdadeTenis",
					nivelDao.getNiveisPorTipoNivelEsporte(TipoNivel.IDADE, TipoEsporte.TENNIS));
			result.include("niveisPorGrupoTenis",
					nivelDao.getNiveisPorTipoNivelEsporte(TipoNivel.GRUPO, TipoEsporte.TENNIS));
			result.include("niveisPorDuplasTenis",
					nivelDao.getNiveisPorTipoNivelEsporte(TipoNivel.DUPLAS, TipoEsporte.TENNIS));

			result.include("categorias", CategoriaTorneio.values());
			result.include("tiposDeChaves", TipoDeChave.values());
			result.include("organizadores", tenistaDao.getOrganizadores());

		} else {
			result.redirectTo(IndexController.class).mensagemErro(
					"Você não tem permissão para acessar essa funcionalidade.");
		}
	}

	/**
	 * Método que redireciona para a tela de edição de dados do torneio
	 * 
	 * @param idTorneio
	 *            identifica o torneio que será editado
	 * 
	 * 
	 * */

	@Path("torneio/edicao-de-dados-do-torneio/{idTorneio}")
	@Get
	public void formEditaDadosDoTorneio(Long idTorneio) {

		Torneio torneio = torneioDao.busca(idTorneio);

		if (tenistaLogado.isAdminOrOrganizadorProprietario(torneio)) {

			result.include("torneio", torneio);

			// Atribui uma lista para variáveis utilizadas no formulário
			result.include("locais",
					localDao.listaTodos(Torneio.CAMPO_PRINCIPAL_ORDENACAO));

			result.include("niveisTecnicosBT",
					nivelDao.getNiveisPorTipoNivelEsporte(TipoNivel.TECNICO, TipoEsporte.BEACH_TENNIS));
			result.include("niveisTecnicosMasculinoBT",
					nivelDao.getNiveisPorTipoNivelEsporte(TipoNivel.MASCULINO, TipoEsporte.BEACH_TENNIS));
			result.include("niveisTecnicosFemininoBT",
					nivelDao.getNiveisPorTipoNivelEsporte(TipoNivel.FEMININO, TipoEsporte.BEACH_TENNIS));
			result.include("niveisPorIdadeBT",
					nivelDao.getNiveisPorTipoNivelEsporte(TipoNivel.IDADE, TipoEsporte.BEACH_TENNIS));
			result.include("niveisPorGrupoBT",
					nivelDao.getNiveisPorTipoNivelEsporte(TipoNivel.GRUPO, TipoEsporte.BEACH_TENNIS));
			result.include("niveisPorDuplasBT",
					nivelDao.getNiveisPorTipoNivelEsporte(TipoNivel.DUPLAS, TipoEsporte.BEACH_TENNIS));

			result.include("niveisTecnicos",
					nivelDao.getNiveisPorTipoNivelEsporte(TipoNivel.TECNICO, TipoEsporte.TENNIS));
			result.include("niveisTecnicosMasculino",
					nivelDao.getNiveisPorTipoNivelEsporte(TipoNivel.MASCULINO, TipoEsporte.TENNIS));
			result.include("niveisPorIdade",
					nivelDao.getNiveisPorTipoNivelEsporte(TipoNivel.IDADE, TipoEsporte.TENNIS));
			result.include("niveisTecnicosFeminino",
					nivelDao.getNiveisPorTipoNivelEsporte(TipoNivel.FEMININO, TipoEsporte.TENNIS));
			result.include("niveisPorGrupo",
					nivelDao.getNiveisPorTipoNivelEsporte(TipoNivel.GRUPO, TipoEsporte.TENNIS));
			result.include("niveisPorDuplas",
					nivelDao.getNiveisPorTipoNivelEsporte(TipoNivel.DUPLAS, TipoEsporte.TENNIS));
			
			result.include("categorias", CategoriaTorneio.values());
			result.include("tiposDeChaves", TipoDeChave.values());
			result.include("organizadores", tenistaDao.getOrganizadores());

		} else {
			result.redirectTo(IndexController.class).mensagemErro(
					"Você não tem permissão de acessar essa funcionalidade.");
		}

	}

	/**
	 * Método que redireciona para a tela de reinicio do torneio
	 * 
	 * @param idTorneio
	 *            identifica o torneio que está prestes a ter sua situação
	 *            ajustada ser reiniciado
	 * 
	 * */

	@Get("/torneio/ajusta-situacao-do-torneio/idTorneio/{idTorneio}/nomeTorneio/{nomeTorneio}/acao/{acao}")
	public void ajustaSituacaoDoTorneio(Long idTorneio, String nomeTorneio,
			Integer acao) {
		result.include("idTorneio", idTorneio);
		result.include("nomeTorneio", nomeTorneio);
		result.include("acao", acao);
		result.include("INICIA_TORNEIO", INICIA_TORNEIO);
		result.include("CANCELA_TORNEIO", CANCELA_TORNEIO);
		result.include("REINICIA_TORNEIO", REINICIA_TORNEIO);
		result.include("CONCLUI_TORNEIO", CONCLUI_TORNEIO);

	}

	/**
	 * Método que redireciona para a tela que gera as chaves do torneio
	 * 
	 * */

	@Get("/torneio/gera-chaves-do-torneio/{idTorneio}")
	public void geraChavesDoTorneio(Long idTorneio) {

		Torneio torneio = torneioDao.busca(idTorneio);
		result.include("torneio", torneio);

		MidiaTorneio midiaTorneio = midiaTorneioDao
				.getPDFChaveamentoPorTorneio(idTorneio);
		if (midiaTorneio != null) {
			result.include("chaveamentoPDF", midiaTorneio.getUrl());
		}
		/*
		 * if (!torneio.isChaveGerada()) { salvaChaveamento(idTorneio); }
		 */

	}

	/**
	 * Método responsável por redirecionar para a tela de inscrições do tenista
	 * logado
	 * 
	 * @param idTenista
	 *            identifica qual tenista será detalhado
	 * 
	 * */
	@Get("/torneio/visualiza-meus-torneios")
	public void visualizaMeusTorneios() {

		Tenista tenista;
		if (logadoRule.isAllowed()) {

			tenista = tenistaLogado.getTenista();
			result.include("torneiosOrganizados",
					torneioDao.getTorneiosPorOrganizador(tenista));

		} else {

			result.redirectTo(IndexController.class).mensagemErro(
					"Você precisa estar logado no sistema.");

		}
	}

	// MÉTODOS DE AÇÃO

	@Transactional
	@Post("/torneio/criacao-de-torneio")
	public void criaTorneio(Torneio torneio) {

		torneio.setLocal(localDao.busca(torneio.getLocal().getId()));
		torneio.setSituacao(SituacaoTorneio.A_INICIAR);
		torneio.setTenistaOrganizador(tenistaLogado.getTenista());
		torneio.setTenistaAuxiliar1(tenistaDao.busca(torneio
				.getTenistaAuxiliar1().getId()));
		torneio.setTenistaAuxiliar2(tenistaDao.busca(torneio
				.getTenistaAuxiliar2().getId()));
		torneio.setDivulgarTorneio(true);

		// TODO regras de negócio

		// Atribuir false para alguns atributos

		// Tipo de torneio inicial

		// Validações

		// Tratar entrada de dados com cifrão e valores

		// Limite de inscritos não pode ser menor que 4 e maior que 0

		// Opção limite de inscritos marcada

		// Um nível tem que ser escolhido

		// Premiação e inscrição não podem ser menor que 0

		// O período entre torneios não podem coincidir

		// As datas de fim devem ser maiores que as datas de início

		// se for torneio de duplas, só permitir níveis de duplas

		// torneio com 2 estágios tem que ser misto

		// tenistas por grupo não pode ser 0 e por padrão é 1 e tem que ser de
		// grupo ou misto de 2 estágios

		// fim das validações

		torneioDao.adiciona(torneio);

		// Enviar e-mail para o organizador

		// Enviar e-mails de notificações para os tenistas

		Integer anoAtual = Calendar.getInstance().get(Calendar.YEAR);
		if (!torneio.isRankingDeClube()) {
			result.redirectTo(TorneioController.class).visualizaTorneios(0,
					anoAtual);
		} else {
			result.redirectTo(TorneioController.class).visualizaTorneios(1,
					anoAtual);
		}
	}

	@Transactional
	@Post("/torneio/edicao-de-dados-do-torneio")
	public void editaDadosDoTorneio(Torneio torneio) {

		Tenista tenistaOrganizador = null;

		if (torneio.getTenistaOrganizador() != null) {
			tenistaOrganizador = tenistaDao.busca(torneio
					.getTenistaOrganizador().getId());
		}

		if (tenistaLogado.isAdminOrOrganizadorProprietario(torneio)) {

			// A situação e a chave do torneio permanece as mesmas
			Torneio torneioOriginal = torneioDao.find(torneio.getId());
			SituacaoTorneio situacao = torneioOriginal.getSituacao();
			boolean chaveGerada = torneioOriginal.isChaveGerada();

			torneio.setSituacao(situacao);
			torneio.setChaveGerada(chaveGerada);
			torneio.setLocal(localDao.busca(torneio.getLocal().getId()));
			torneio.setTenistaOrganizador(tenistaOrganizador);
			torneio.setTenistaAuxiliar1(tenistaDao.busca(torneio
					.getTenistaAuxiliar1().getId()));
			torneio.setTenistaAuxiliar2(tenistaDao.busca(torneio
					.getTenistaAuxiliar2().getId()));

			torneio = (Torneio) torneioDao.atualiza(torneio);

			result.redirectTo(TorneioController.class).detalhaTorneio(
					torneio.getId());
		} else {
			result.redirectTo(IndexController.class).mensagemErro(
					"Você não tem permissão de acessar essa funcionalidade.");
		}
	}

	/**
	 * Método responsável por redirecionar para a tela de visualização de
	 * torneios
	 * 
	 * */
	@Get("/torneio/visualizacao-de-torneios/idTipo/{idTipo}/ano/{ano}")
	public void visualizaTorneios(Integer idTipo, Integer ano) {

		List<Torneio> proximosTorneios = null;
		List<Torneio> torneiosAnteriores = null;
		boolean isFiliado = false;

		if (ano == null || ano.equals(0)) {
			ano = Calendar.getInstance().get(Calendar.YEAR);
		}

		if (idTipo == 0) {
			proximosTorneios = torneioDao.getProximosTorneios();
			torneiosAnteriores = torneioDao.getTorneiosAnteriores(ano);
		} else if (idTipo == 1) {
			proximosTorneios = torneioDao.getProximasCompeticoesSemPontuacao();
			torneiosAnteriores = torneioDao
					.getCompeticoesSemPontuacoesAnteriores(ano);

		}

		// verifica se o tenista logado é filiado e configura o botão adequado
		if (tenistaLogado != null) {
			int anoAtual = Calendar.getInstance().get(Calendar.YEAR);
			if (filiadoDao.isFiliado(tenistaLogado.getTenista(), anoAtual)) {
				isFiliado = true;
			} else {
				isFiliado = false;
			}
		}
		Date dataLimite = Utils.obtemOntem();

		result.include("proximosTorneios", proximosTorneios);
		result.include("torneiosAnteriores", torneiosAnteriores);
		result.include("dataDeHoje", Calendar.getInstance().getTime());
		result.include("dataDeOntem", dataLimite);
		result.include("numeroDeProximosTorneios", proximosTorneios.size());
		result.include("numeroDeTorneiosAnteriores", torneiosAnteriores.size());
		result.include("ano", ano);
		result.include("isFiliado", isFiliado);

	}

	/**
	 * Método responsável por redirecionar para a tela de visualização de etapas
	 * internas de clubes
	 * 
	 * */
	@Get("/torneio/visualizacao-de-etapas-internas-de-clubes")
	public void visualizaCompeticoesSemPontuacao() {

		Integer anoAtual = Calendar.getInstance().get(Calendar.YEAR);
		List<Torneio> proximosTorneios = torneioDao
				.getProximasCompeticoesSemPontuacao();
		List<Torneio> torneiosAnteriores = torneioDao
				.getCompeticoesSemPontuacoesAnteriores(anoAtual);

		Date dataLimite = Utils.obtemOntem();

		result.include("proximosTorneios", proximosTorneios);
		result.include("torneiosAnteriores", torneiosAnteriores);
		result.include("dataDeHoje", Calendar.getInstance().getTime());
		result.include("dataDeOntem", dataLimite);
		result.include("numeroDeProximosTorneios", proximosTorneios.size());
		result.include("numeroDeTorneiosAnteriores", torneiosAnteriores.size());

	}

	/**
	 * Método responsável por redirecionar para a tela de detalhes do torneio
	 * 
	 * @param idTorneio
	 *            identifica qual torneio será detalhado
	 * 
	 * */
	@Get("/torneio/detalhes-do-torneio/{idTorneio}")
	public void detalhaTorneio(Long idTorneio) {

		Torneio torneio = torneioDao.busca(idTorneio);
		List<MidiaTorneio> midiasTorneios = midiaTorneioDao
				.getMidiasPorTorneio(idTorneio);
		String banner = null;
		String tituloBanner = null;
		String regulamento = null;
		List<String> patrocinios = new ArrayList<String>();
		String botaoPagamento = null;

		for (MidiaTorneio midiaTorneio : midiasTorneios) {
			// salva o banner do torneio
			if (midiaTorneio.getTipoMidiaTorneio().equals(
					TipoMidiaTorneio.BANNER)) {
				banner = midiaTorneio.getUrl();
				tituloBanner = midiaTorneio.getTitulo();
			}
			// salva o regulamento do torneio
			if (midiaTorneio.getTipoMidiaTorneio().equals(
					TipoMidiaTorneio.REGULAMENTO)) {
				regulamento = midiaTorneio.getUrl();
			}
			// salva o regulamento do torneio
			if (midiaTorneio.getTipoMidiaTorneio().equals(
					TipoMidiaTorneio.PATROCINIO)) {
				patrocinios.add(midiaTorneio.getUrl());
			}

			if (midiaTorneioDao.getPDFChaveamentoPorTorneio(idTorneio) != null) {
				result.include("chaveamentoPDF", midiaTorneio.getUrl());
			}

		}

		// verifica se o tenista logado é filiado e configura o botão adequado
		if (tenistaLogado != null) {
			int anoAtual = Calendar.getInstance().get(Calendar.YEAR);
			if (filiadoDao.isFiliado(tenistaLogado.getTenista(), anoAtual)) {
				botaoPagamento = torneio.getBotaoPagamentoFiliado();
			} else {
				botaoPagamento = torneio.getBotaoPagamento();
			}
		} else {
			botaoPagamento = torneio.getBotaoPagamento();
		}

		// calcula se existem inscrições pendentes
		List<Inscricao> inscricoesTenistaLogado = inscricaoDao
				.getInscricoesPorTenistaTorneio(tenistaLogado.getTenista(),
						torneio);
		int numeroDePendencias = 0;
		if (inscricoesTenistaLogado != null
				&& inscricoesTenistaLogado.size() > 0) {
			for (Inscricao inscricao : inscricoesTenistaLogado) {
				if (!inscricao.isPaga()) {
					numeroDePendencias++;
				}
			}
		} else if (inscricoesTenistaLogado.size() == 0) {
			// sem inscricoes realizadas
			numeroDePendencias = -1;
		}

		// pega as inscrições realizadas pelo tenista logado
		List<Inscricao> inscricoesDoTenista = inscricaoDao
				.getInscricoesPorTenistaTorneio(tenistaLogado.getTenista(),
						torneio);
		List<String> niveisOrdenados = new ArrayList<String>();
		for (Nivel nivel : torneio.getNiveis()) {
			niveisOrdenados.add(new String(nivel.getDescricao()));
		}

		java.util.Collections.sort(niveisOrdenados);

		result.include("torneio", torneio);
		result.include("niveisTecnicos", niveisOrdenados);
		result.include("INICIA_TORNEIO", INICIA_TORNEIO);
		result.include("CANCELA_TORNEIO", CANCELA_TORNEIO);
		result.include("REINICIA_TORNEIO", REINICIA_TORNEIO);
		result.include("CONCLUI_TORNEIO", CONCLUI_TORNEIO);
		result.include("banner", banner);
		result.include("tituloBanner", tituloBanner);
		result.include("regulamento", regulamento);
		result.include("patrocinios", patrocinios);
		result.include("botaoPagamento", botaoPagamento);
		result.include("numeroDePendencias", numeroDePendencias);
		result.include("inscricoesDoTenista", inscricoesDoTenista);
		result.include("quantidadeInscricoesTenista",
				inscricoesDoTenista.size());

		if (torneio.getDataInicioInscricao().getTime()
				.before(Calendar.getInstance().getTime())
				&& torneio.getDataFimInscricao().getTime()
						.after(Utils.obtemOntem())
				&& torneio.getSituacao() == SituacaoTorneio.A_INICIAR) {

			result.include("inscricoesAbertas", true);
		} else {
			result.include("inscricoesAbertas", false);
		}
	}

	/**
	 * Método que ajusta a situação de um torneio de acordo com a ação
	 * 
	 * @param idTorneio
	 *            identifica o torneio que será ajustado
	 * 
	 * */

	@Transactional
	@Post("/torneio/ajusta-situacao-do-torneio")
	public void confirmaAjusteDeSituacao(Long idTorneio, Integer acao) {

		Torneio torneio = torneioDao.busca(idTorneio);

		if (acao == INICIA_TORNEIO) {
			torneio.setSituacao(SituacaoTorneio.INICIADO);
			torneioDao.atualiza(torneio);

		} else if (acao == CANCELA_TORNEIO) {
			torneio.setSituacao(SituacaoTorneio.CANCELADO);
			torneioDao.atualiza(torneio);

		} else if (acao == REINICIA_TORNEIO) {
			torneio.setSituacao(SituacaoTorneio.A_INICIAR);
			torneio.setChaveGerada(false);
			/*
			 * List<Inscricao> inscricoes =
			 * inscricaoDao.getInscricoesPorTorneio(torneio); for(Inscricao
			 * inscricao:inscricoes){ inscricao.setNumeroCabecaDeChave(null);
			 * inscricaoDao.atualiza(inscricao); }
			 */
			torneioDao.atualiza(torneio);

		} else if (acao == CONCLUI_TORNEIO) {
			torneio.setSituacao(SituacaoTorneio.CONCLUIDO);
			torneioDao.atualiza(torneio);
		}

		result.redirectTo(TorneioController.class).detalhaTorneio(
				torneio.getId());

	}

	/**
	 * Método que gera a chave do torneio de simples
	 * 
	 * @param idTorneio
	 *            identifica o torneio
	 * @param idNivel
	 *            identifica o nível
	 * */
	@Transactional
	@Post("/torneio/chaveamento-do-torneio")
	public Download confirmaGeracaoDeChavesDoTorneio(String idTorneio,
			String idNivel, HttpServletRequest request) {

		try {

			// Obtém o torneio
			Torneio torneio = torneioDao.busca(Long.parseLong(idTorneio));

			if (idNivel != null) {

				// Obtém o nível selecionado no formulario
				Nivel nivel = nivelDao.busca(Integer.parseInt(idNivel));

				// Identifica a quantidade de participante no nível do torneio
				// para
				// cada
				// iteração e a quantidade geral depois de adicionado os byes
				// tenistas
				Integer quantidadeDeParticipantes, quantidadeGeral = null;

				// Inscrições de cada nível e torneio
				List<Inscricao> inscricoes = null;

				// Inscrições de cada nível e torneio
				List<InscricaoDeDupla> inscricoesDeDuplas = null;

				// Tenistas inscritos no nivel
				List<Tenista> tenistas = null;

				List<Dupla> duplas = null;

				// Jogos que serão iniciados para primeira fase
				List<Jogo> jogos = new ArrayList<Jogo>();

				if (nivel.isDuplas()) {
					// Obtém todas a inscrições do nível que está no laço

					if (torneio.isMisto2Estagios()) {

						if (tenistaLogado
								.isAdminOrOrganizadorProprietario(torneio)
								|| torneio.isIniciouSegundoEstagio()) {

							inscricoesDeDuplas = inscricaoDeDuplaDao
									.getInscricoesParaChaveamentoDeDuplaSegundoEstagio(
											nivel, torneio);
						} else {
							inscricoes = new ArrayList<Inscricao>();
						}

					} else {

						inscricoesDeDuplas = inscricaoDeDuplaDao
								.getInscricoesParaChaveamentoDeDupla(nivel,
										torneio);

					}

					quantidadeDeParticipantes = inscricoesDeDuplas.size();

				} else {
					// Obtém todas a inscrições do nível que está no laço

					if (torneio.isMisto2Estagios()) {

						if (tenistaLogado
								.isAdminOrOrganizadorProprietario(torneio)
								|| torneio.isIniciouSegundoEstagio()) {

							inscricoes = inscricaoDao
									.getInscricoesParaChaveamentoSegundoEstagio(
											nivel,
											torneio,
											Calendar.getInstance().get(
													Calendar.YEAR));
						} else {
							inscricoes = new ArrayList<Inscricao>();
						}

					} else {
						inscricoes = inscricaoDao.getInscricoesParaChaveamento(
								nivel, torneio,
								Calendar.getInstance().get(Calendar.YEAR));

					}

					quantidadeDeParticipantes = inscricoes.size();

				}

				// Ajusta os bye da quantidade de participantes
				if (quantidadeDeParticipantes >= QUANTIDADE_MINIMA_INSCRITOS) {

					// Calcula a quantidade de bye no nível para o torneio com
					// limite de 2 elevado a 7 participantes
					Integer quantidadeDeBye = 0;
					for (int n = 2; n < 7;) {

						// Verifica o intervalo em que a quantidade de
						// participantes
						// se encontra
						if (quantidadeDeParticipantes >= Math.pow(2, n)
								&& quantidadeDeParticipantes < Math.pow(2, ++n)) {
							quantidadeDeBye = (int) ((Math.pow(2, n)) - quantidadeDeParticipantes);

							// Trata a quantidade padrão 4,8,16 etc
							if (log(quantidadeDeBye, 2) == (n - 1)) {
								quantidadeDeBye = 0;
							}
							break;
						}
					}

					// verifica se o n torneio é de duplas ou de outra categoria
					if (nivel.isDuplas()) {

						Dupla dupla = null;
						duplas = new ArrayList<Dupla>();

						// Obtém os tenistas inscritos no nível corrente
						for (InscricaoDeDupla inscricao : inscricoesDeDuplas) {

							dupla = new Dupla();
							dupla.setTenista(inscricao.getTenista());
							dupla.setTenistaParceiro(inscricao
									.getTenistaParceiro());
							duplas.add(dupla);

						}

						Tenista bye = new Tenista();
						bye.setNome("bye");
						bye.setSobreNome("");
						bye.setNomeRanking("");

						for (int i = 0; i < quantidadeDeBye; i++) {
							Dupla duplaBye = new Dupla();
							duplaBye.setTenista(bye);
							duplaBye.setTenistaParceiro(bye);
							duplas.add(duplaBye);
						}

						quantidadeGeral = quantidadeDeParticipantes
								+ quantidadeDeBye;
						JogoDeTorneioDeDupla jogo;
						for (int i = 1; i <= (quantidadeGeral / 2); i++) {

							jogo = new JogoDeTorneioDeDupla();
							jogo.setTenistaUm(duplas.get(i - 1).getTenista());
							jogo.setTenistaParceiroUm(duplas.get(i - 1)
									.getTenistaParceiro());
							jogo.setTenistaDois(duplas.get(quantidadeGeral - i)
									.getTenista());
							jogo.setTenistaParceiroDois(duplas.get(
									quantidadeGeral - i).getTenistaParceiro());

							jogos.add(jogo);
						}

						return Utils.gerarChaveamentoPDF(null,
								quantidadeDeParticipantes, torneio, nivel,
								duplas);

					}
					// torneio é de simples
					else if (nivel.isSimples()) {

						tenistas = new ArrayList<Tenista>();

						// Obtém os tenistas inscritos no nível corrente
						for (Inscricao inscricao : inscricoes) {

							tenistas.add(inscricao.getTenista());

						}

						// insere os bye necessários na lista
						for (int i = 0; i < quantidadeDeBye; i++) {
							// Obtém tenista ficticio bye
							Tenista tenistaBye = new Tenista();
							tenistaBye.setNome("     ");
							tenistaBye.setSobreNome("Bye");
							tenistaBye.setNomeRanking("Bye");
							tenistas.add(tenistaBye);
						}

						// Percorre todos os tenistas, inclusive os bye e cria
						// os jogos
						quantidadeGeral = quantidadeDeParticipantes
								+ quantidadeDeBye;
						Jogo jogo;
						for (int i = 1; i <= (quantidadeGeral / 2); i++) {

							// TODO Configura os parâmetros do tenista
							jogo = new Jogo();
							jogo.setTenistaUm(tenistas.get(i - 1));
							jogo.setTenistaDois(tenistas.get(quantidadeGeral
									- i));

							jogos.add(jogo);

						}

						// gera o chaveamento em pdf para o torneio de simples
						return Utils
								.gerarChaveamentoPDF(tenistas,
										quantidadeDeParticipantes, torneio,
										nivel, null);

					}

					torneio.setChaveGerada(true);
					torneio = torneioDao.atualiza(torneio);

				} // se a quantidade de tenista for menor que o número minimo
				else {

					result.redirectTo(IndexController.class)
							.mensagemErro(
									"A quantidade de atletas inscritos para este nível no torneio é muito pequena e não é possível gerar um chaveamento.");

				}

				result.include("jogos", jogos);
				result.include("torneio", torneio);
				result.include("nivel", nivel);
				result.include("quantidadeGeral", quantidadeGeral);

			} // se o nível for nulo, está gerando os cabeças de chaves e
				// alterando o status do torneio
			else {
				salvaChaveamento(torneio);
				torneio.setChaveGerada(true);
				torneio = torneioDao.atualiza(torneio);
				result.include("torneio", torneio);
				result.redirectTo(IndexController.class).mensagem(
						"Chaveamento gerado com sucesso!");

			}

		} catch (Exception e) {
			e.printStackTrace();
			result.redirectTo(IndexController.class).mensagem(
					"Ocorreu um problema:" + e.getMessage());
		}

		return null;
	}

	@Transactional
	@Post("torneio/inicia-segundo-estagio-do-torneio")
	public void confirmaInicioSegundoEstagio(Long idTorneio, Integer idNivel, Integer quantidadeTenistas) {

		Torneio torneio = torneioDao.busca(idTorneio);
		Nivel nivelSelecionado = nivelDao.busca(idNivel);
		Fase faseGrupo = faseDao.busca(Fase.GRUPO);


		Integer n = 0;
		Integer p = 2;
		Integer quantidadeDeJogosNoGrupo = 0;
		Integer quantidadeDeJogosNoNivel = 0;
		Integer quantidadeDeJogosRealizados = 0;
		// obter a quantidade de jogos que o nível deve ter

		ClassificacaoGrupoController classificacaoGrupoController = new ClassificacaoGrupoController();
		List<ClassificacaoDeGrupo> classificados = new ArrayList<ClassificacaoDeGrupo>();
		List<ClassificacaoDeGrupo> classificacaoGeral = new ArrayList<ClassificacaoDeGrupo>();
		List<ClassificacaoDeGrupo> classificacaoNoGrupo = new ArrayList<ClassificacaoDeGrupo>();
		List<Integer> gruposDoNivel = inscricaoDao.getGruposPorNivelETorneio(
				nivelSelecionado, torneio);

		for (Integer grupo : gruposDoNivel) {

			// obter a quantidade de inscritos no grupo do nível
			n = inscricaoDao.getInscricoesDoGrupoPorNivelETorneio(grupo,
					nivelSelecionado, torneio).size();

			// obter a quantidade de jogos que o grupo deve ter
			quantidadeDeJogosNoGrupo = (Utils.obterFatorial(n)/ (Utils.obterFatorial(n - p) * (Utils.obterFatorial(p))));
			quantidadeDeJogosNoNivel += quantidadeDeJogosNoGrupo;
			
			// coloca a classificação do grupo em uma lista
			classificacaoNoGrupo = classificacaoGrupoController.classificaGrupo(nivelSelecionado, torneio, grupo, faseGrupo);
		
			// junta as listas de classificação dos grupos em uma lista apenas
			for (ClassificacaoDeGrupo classificacaoDeGrupo : classificacaoNoGrupo) {
			
				classificacaoGeral.add(classificacaoDeGrupo);
				
			}

		}

		// jogos realizados no nível do torneio
		List<JogoDeTorneio> jogosRegistrados = jogoDeTorneioDao
				.getJogosNoTorneioPorNivelFase(nivelSelecionado.getId(),
						torneio.getId(), Fase.GRUPO);
 
		// verificar para cada jogo registrado se o jogo já ocorreu e decrementar se ainda não tiver o resultado
		quantidadeDeJogosRealizados = jogosRegistrados.size();
		for (JogoDeTorneio jogoDeTorneio : jogosRegistrados) {
			
			if(jogoDeTorneio.getOcorreu()==false)
				quantidadeDeJogosRealizados--;
		}
		
		validator
				.addIf((quantidadeDeJogosNoNivel > quantidadeDeJogosRealizados),
						new SimpleMessage(
								"nivel",
								"É preciso que todos os jogos tenham sido realizados no nível técnico. São precisos "
										+ quantidadeDeJogosNoNivel
										+ " jogos e foram registrados até agora "
										+ quantidadeDeJogosRealizados
										+ " jogos."));
		
		// verifica se o valor informado de quantidade de tenistas não foi acima do permitido para o nível
		validator.addIf(quantidadeTenistas > quantidadeDeJogosNoNivel, new SimpleMessage("Quantidade de Tenistas", "A quantidade de tenistas solicitados está acima do número máximo possível, verifique o valor e tente novamente."));

		validator.validate(nivelSelecionado);

		validator.onErrorRedirectTo(this).iniciaSegundoEstagioDoTorneio(
				idTorneio);


		// ordena a classificação geral
		Collections.sort(classificacaoGeral);
		Collections.reverse(classificacaoGeral);

		// reinicia o numero de cabeça de chaves 2 estágio
		List<Inscricao> inscricoesNoNivel = inscricaoDao.getInscricoesPorNivelETorneio(nivelSelecionado, torneio, null);
		for (Inscricao inscricao : inscricoesNoNivel) {
			inscricao.setNumeroCabecaDeChave2Estagio(null);
			inscricaoDao.atualiza(inscricao);
		}

		// coloca o número de cabeça de chaves de acordo com a classificação
		for (int i = 0; i < quantidadeTenistas; i++) {
			classificados.add(classificacaoGeral.get(i));
			Inscricao inscricaoClassificado = inscricaoDao.getInscricaoPorNivelTorneioETenista(nivelSelecionado, torneio, classificados.get(i).getTenista());
			if(inscricaoClassificado != null){
				inscricaoClassificado.setNumeroCabecaDeChave2Estagio(i+1);
				inscricaoDao.atualiza(inscricaoClassificado);
			}
		}
		

		result.include("mensagemDeSucesso","Início da segunda fase do nível " + nivelSelecionado.getDescricao() + " gerado com sucesso.");
		result.redirectTo(TorneioController.class).iniciaSegundoEstagioDoTorneio(
				torneio.getId());

	}
	
	/**
	 * Método que ajusta o torneio para o início do segundo estágio
	 * 
	 * @param idTorneio
	 *            identifica o torneio que será ajustado
	 * 
	 * */

	@Transactional
	@Post("/torneio/confirma-chaves-finais")
	public void confirmaChavesFinais(Long idTorneio) {

		Torneio torneio = torneioDao.busca(idTorneio);
		torneio.setIniciouSegundoEstagio(true);

		torneioDao.atualiza(torneio);
		
		result.include("mensagemDeSucesso","Início das fases eliminatórias fina do torneio foi autorizada e gerada com sucesso.");
		result.redirectTo(TorneioController.class).liberaChavesFinais(
				torneio.getId());

	}

	

	@Get("/torneio/inicia-segundo-estagio-do-torneio/{idTorneio}")
	public void iniciaSegundoEstagioDoTorneio(Long idTorneio) {

		Torneio torneio = torneioDao.busca(idTorneio);
		result.include("torneio", torneio);
		result.include("niveisDoTorneio", torneio.getNiveis());

	}

	@Get("/torneio/libera-chaves-eliminatorio/{idTorneio}")
	public void liberaChavesFinais(Long idTorneio) {

		Torneio torneio = torneioDao.busca(idTorneio);
		result.include("torneio", torneio);
		result.include("niveisDoTorneio", torneio.getNiveis());

	}

	private void salvaChaveamento(Torneio torneio) {

		// System.out.println("chegou");
		// Torneio torneio = torneioDao.busca(idTorneio);

		// se for torneio com 2 categorias diferentes existe uma particularidade
		// para cada nível
		if (torneio.getNumeroDeEstagios() > 1) {

			int tamanhoDoGrupoPadraoDoTorneio = torneio.getTenistasPorGrupo();
			int quantidadeDeInscritosNoNivel = 0;
			int quantidadeDeGruposNoNivel = 0;

			for (Nivel nivel : torneio.getNiveis()) {

				if (nivel.isDuplas()) {
					// Obtém todas a inscrições do nível que está no laço
					List<InscricaoDeDupla> inscricoesDeDuplas = inscricaoDeDuplaDao
							.getInscricoesParaChaveamentoDeDupla(nivel, torneio);

					quantidadeDeInscritosNoNivel = inscricoesDeDuplas.size();
					quantidadeDeGruposNoNivel = quantidadeDeInscritosNoNivel
							/ tamanhoDoGrupoPadraoDoTorneio;
					if (quantidadeDeGruposNoNivel == 0) {
						quantidadeDeGruposNoNivel = 1;
					}
					InscricaoDeDupla inscricaoDeDupla;
					int numeroDoGrupo = 0;
					for (int i = 1; i <= quantidadeDeInscritosNoNivel; i++) {
						numeroDoGrupo++;
						inscricaoDeDupla = inscricoesDeDuplas.get(i - 1);
						inscricaoDeDupla.setNumeroCabecaDeChave(i);
						if (numeroDoGrupo <= quantidadeDeGruposNoNivel) {
							inscricaoDeDupla.setNumeroGrupo(numeroDoGrupo);
							if (numeroDoGrupo == quantidadeDeGruposNoNivel) {
								numeroDoGrupo = 0;
							}
						}
						inscricaoDeDupla = inscricaoDeDuplaDao
								.atualiza(inscricaoDeDupla);
					}

				} else {
					// Obtém todas a inscrições do nível que está no laço
					List<Inscricao> inscricoesDeSimples = inscricaoDao
							.getInscricoesParaChaveamento(nivel, torneio,
									Calendar.getInstance().get(Calendar.YEAR));

					quantidadeDeInscritosNoNivel = inscricoesDeSimples.size();
					quantidadeDeGruposNoNivel = quantidadeDeInscritosNoNivel
							/ tamanhoDoGrupoPadraoDoTorneio;
					if (quantidadeDeGruposNoNivel == 0) {
						quantidadeDeGruposNoNivel = 1;
					}
					Inscricao inscricao;
					int numeroDoGrupo = 0;
					for (int i = 1; i <= quantidadeDeInscritosNoNivel; i++) {
						numeroDoGrupo++;
						inscricao = inscricoesDeSimples.get(i - 1);
						inscricao.setNumeroCabecaDeChave(i);
						if (numeroDoGrupo <= quantidadeDeGruposNoNivel) {
							inscricao.setNumeroGrupo(numeroDoGrupo);
							if (numeroDoGrupo == quantidadeDeGruposNoNivel) {
								numeroDoGrupo = 0;
							}
						}
						inscricao = inscricaoDao.atualiza(inscricao);
					}

				}

			}

		} // senão for é porque todos os níveis são iguais quanto a quantidade
			// de participantes na inscrição
		else {

			for (Nivel nivel : torneio.getNiveis()) {

				if (nivel.isDuplas()) {
					// Obtém todas a inscrições do nível que está no laço
					List<InscricaoDeDupla> inscricoesDeDuplas = inscricaoDeDuplaDao
							.getInscricoesParaChaveamentoDeDupla(nivel, torneio);

					InscricaoDeDupla inscricaoDeDupla;
					for (int i = 1; i <= inscricoesDeDuplas.size(); i++) {
						inscricaoDeDupla = inscricoesDeDuplas.get(i - 1);
						inscricaoDeDupla.setNumeroCabecaDeChave(i);
						inscricaoDeDupla = inscricaoDeDuplaDao
								.atualiza(inscricaoDeDupla);
					}

				} else {
					// Obtém todas a inscrições do nível que está no laço
					List<Inscricao> inscricoesDeSimples = inscricaoDao
							.getInscricoesParaChaveamento(nivel, torneio,
									Calendar.getInstance().get(Calendar.YEAR));

					Inscricao inscricao;
					for (int i = 1; i <= inscricoesDeSimples.size(); i++) {
						inscricao = inscricoesDeSimples.get(i - 1);
						inscricao.setNumeroCabecaDeChave(i);
						inscricao = inscricaoDao.atualiza(inscricao);
					}

				}

			}

		}

	}

	/*
	 * @Transactional
	 * 
	 * @Post("/torneio/gera-chaves-do-torneio") public void
	 * confirmaGeracaoDeChavesDoTorneioDeDuplas(String idTorneio, String
	 * idNivel) {
	 * 
	 * // Obtém o torneio Torneio torneio =
	 * torneioDao.busca(Long.parseLong(idTorneio));
	 * 
	 * if (idNivel != null) {
	 * 
	 * Nivel nivel = nivelDao.busca(Integer.parseInt(idNivel));
	 * 
	 * Integer quantidadeDeParticipantes, quantidadeGeral = null;
	 * 
	 * List<InscricaoDeDupla> inscricoes;
	 * 
	 * // Tenistas inscritos no nivel List<Dupla> duplas = new
	 * ArrayList<Dupla>();
	 * 
	 * 
	 * // Jogos que serão iniciados para primeira fase List<Jogo> jogos = new
	 * ArrayList<Jogo>();
	 * 
	 * // Obtém todas a inscrições do nível que está no laço inscricoes =
	 * inscricaoDeDuplaDao.getInscricoesPorNivelETorneio(nivel, torneio);
	 * quantidadeDeParticipantes = inscricoes.size();
	 * 
	 * // Ajusta os bye da quantidade de participantes if
	 * (quantidadeDeParticipantes >= QUANTIDADE_MINIMA_INSCRITOS) {
	 * 
	 * // Calcula a quantidade de bye no nível para o torneio com // limite de 2
	 * elevado a 7 participantes Integer quantidadeDeBye = 0; for (int n = 2; n
	 * < 7;) {
	 * 
	 * // Verifica o intervalo em que a quantidade de participantes // se
	 * encontra if (quantidadeDeParticipantes >= Math.pow(2, n) &&
	 * quantidadeDeParticipantes < Math.pow(2, ++n)) { quantidadeDeBye = (int)
	 * ((Math.pow(2, n)) - quantidadeDeParticipantes);
	 * 
	 * // Trata a quantidade padrão 4,8,16 etc if (log(quantidadeDeBye, 2) == (n
	 * - 1)) { quantidadeDeBye = 0; } break; } }
	 * 
	 * // Obtém os tenistas inscritos no nível corrente for (Inscricao inscricao
	 * : inscricoes) {
	 * 
	 * tenistas.add(inscricao.getTenista());
	 * 
	 * }
	 * 
	 * // insere os bye necessários na lista for (int i = 0; i <
	 * quantidadeDeBye; i++) { // Obtém tenista ficticio bye Tenista tenistaBye
	 * = new Tenista(); tenistaBye.setNome(""); tenistaBye.setSobreNome("Bye");
	 * tenistaBye.setNomeRanking("Bye"); tenistas.add(tenistaBye); }
	 * 
	 * for (Tenista tenista : tenistas) { System.out.println(tenista.getNome());
	 * }
	 * 
	 * // Percorre todos os tenistas, inclusive os bye e cria os jogos
	 * quantidadeGeral = quantidadeDeParticipantes + quantidadeDeBye; Jogo jogo;
	 * for (int i = 1; i <= (quantidadeGeral / 2); i++) {
	 * 
	 * // TODO Configura os parâmetros do tenista jogo = new Jogo();
	 * jogo.setTenistaUm(tenistas.get(i - 1));
	 * jogo.setTenistaDois(tenistas.get(quantidadeGeral - i));
	 * 
	 * jogos.add(jogo); // jogoDao.adiciona(jogo);
	 * 
	 * // System.out.println(jogo.getTenistaUm().getNome() + " x " // + //
	 * jogo.getTenistaDois().getNome());
	 * 
	 * }
	 * 
	 * torneio.setChaveGerada(true); torneio = torneioDao.atualiza(torneio);
	 * 
	 * } else {
	 * 
	 * result.redirectTo(IndexController.class) .mensagem(
	 * "A quantidade de tenistas inscritos para este nível no torneio é muito pequena e não é possível gerar um chaveamento."
	 * );
	 * 
	 * }
	 * 
	 * result.include("jogos", jogos); result.include("torneio", torneio);
	 * result.include("nivel", nivel); result.include("quantidadeGeral",
	 * quantidadeGeral); } else { torneio.setChaveGerada(true); torneio =
	 * torneioDao.atualiza(torneio); result.include("torneio", torneio); } }
	 */
	/**
	 * Método que calcula o logaritimo
	 * 
	 * @param x
	 *            identifica o numero que deseja calcular o logaritimo
	 * @param base
	 *            identifica a base do logarítimo que será calculado
	 * 
	 * */
	static Integer log(int x, int base) {
		return (int) (Math.log(x) / Math.log(base));
	}

}
