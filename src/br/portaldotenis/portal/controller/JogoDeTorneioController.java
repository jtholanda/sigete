package br.portaldotenis.portal.controller;

import java.util.Calendar;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.portaldotenis.portal.dao.FaseDAO;
import br.portaldotenis.portal.dao.InscricaoDAO;
import br.portaldotenis.portal.dao.InscricaoDeDuplaDAO;
import br.portaldotenis.portal.dao.JogoDeTorneioDAO;
import br.portaldotenis.portal.dao.JogoDeTorneioDeDuplaDAO;
import br.portaldotenis.portal.dao.NivelDAO;
import br.portaldotenis.portal.dao.TenistaDAO;
import br.portaldotenis.portal.dao.TorneioDAO;
import br.portaldotenis.portal.model.Fase;
import br.portaldotenis.portal.model.Inscricao;
import br.portaldotenis.portal.model.InscricaoDeDupla;
import br.portaldotenis.portal.model.JogoDeTorneio;
import br.portaldotenis.portal.model.JogoDeTorneioDeDupla;
import br.portaldotenis.portal.model.Nivel;
import br.portaldotenis.portal.model.Pontuacao;
import br.portaldotenis.portal.model.Tenista;
import br.portaldotenis.portal.model.TenistaLogado;
import br.portaldotenis.portal.model.TipoAcaoJogo;
import br.portaldotenis.portal.model.TipoDeChave;
import br.portaldotenis.portal.model.Torneio;
import br.portaldotenis.portal.util.EmailSender;
import br.portaldotenis.portal.util.Utils;

@Controller
public class JogoDeTorneioController {

	private static final int MAXIMO_ELEMENTOS_PAGINA = 10;

	@Inject
	TorneioDAO torneioDao;

	@Inject
	Result result;

	@Inject
	TenistaLogado tenistaLogado;

	@Inject
	JogoDeTorneioDAO jogoDeTorneioDao;

	@Inject
	JogoDeTorneioDeDuplaDAO jogoDeTorneioDeDuplaDao;
	
	@Inject
	FaseDAO faseDao;

	@Inject
	TenistaDAO tenistaDao;

	@Inject
	NivelDAO nivelDao;

	@Inject
	JogoDeTorneioDAO jogoDao;

	@Inject
	InscricaoDAO inscricaoDao;
	
	@Inject
	InscricaoDeDuplaDAO inscricaoDeDuplaDao;
	
	@Inject
	EmailSender emailSender;
	
	@Inject
	Validator validator;


	/**
	 * Método que redireciona para a tela que gerencia jogos
	 * 
	 * @param idTorneio
	 *            identifica o torneio que o jogo será inserido
	 * 
	 * */

	@Transactional
	@Get("/jogoDeTorneio/gerencia-jogo/{idTorneio}")
	public void formGerenciaJogo(Long idTorneio) {

		Torneio torneio = torneioDao.busca(idTorneio);


		// TODO verificar controle de acesso
		if (tenistaLogado.isAdminOrOrganizadorProprietario(torneio)) {

			result.include("torneio", torneio);
			result.include("niveisDoTorneio", torneio.getNiveis());

		} else {

			result.redirectTo(TenistaController.class).login();
		}

	}

	/**
	 * Método que redireciona para a tela que gerencia jogos de duplas
	 * 
	 * @param idTorneio
	 *            identifica o torneio que o jogo será inserido
	 * 
	 * */

	@Transactional
	@Get("/jogoDeTorneio/gerencia-jogo-de-dupla/{idTorneio}")
	public void formGerenciaJogoDeDuplas(Long idTorneio) {

		Torneio torneio = torneioDao.busca(idTorneio);


		// TODO verificar controle de acesso
		if (tenistaLogado.isAdminOrOrganizadorProprietario(torneio)) {

			result.include("torneio", torneio);
			result.include("niveisDoTorneio", torneio.getNiveis());

		} else {

			result.redirectTo(TenistaController.class).login();
		}

	}
	/**
	 * Método que redireciona para a tela que informa resultado do jogo
	 * 
	 * @param idTorneio
	 *            identifica o torneio que o jogo será inserido
	 * 
	 * */

	@Transactional
	@Get("/jogoDeTorneio/informa-resultado-do-jogo/{idJogo}")
	public void formInformaResultadoDoJogo(Long idJogo) {

		JogoDeTorneio jogoDeTorneio = jogoDeTorneioDao.busca(idJogo);

		Torneio torneio = jogoDeTorneio.getTorneio();

		// TODO verificar controle de acesso
		if (tenistaLogado.isAdminOrOrganizadorProprietario(torneio)) {

			result.include("torneio", torneio);
			result.include("jogoDeTorneio", jogoDeTorneio);

		} else {

			result.redirectTo(TenistaController.class).login();
		}

	}
	/**
	 * Método que busca o jogo e configura para ser editado na página de edição de jogos
	 * 
	 * */
	@Transactional
	@Get("/jogoDeTorneio/edita-jogo-de-torneio/{idJogo}")
	public void formEditaJogoDeTorneio(Long idJogo) {

		JogoDeTorneio jogoDeTorneio = jogoDeTorneioDao.busca(idJogo);

		Torneio torneio = jogoDeTorneio.getTorneio();

		// TODO verificar controle de acesso
		if (tenistaLogado.isAdminOrOrganizadorProprietario(torneio)){

			result.include("tenistas", tenistaDao.getTenistasInscritosConfirmadosNoTorneioPorNivel(jogoDeTorneio.getNivel().getId(), torneio.getId()));
			result.include("fases", faseDao.listaTodos(Fase.ID));
			result.include("torneio", torneio);
			result.include("jogoDeTorneio", jogoDeTorneio);

		} else {

			result.redirectTo(TenistaController.class).login();
		}

	}
	/**
	 * Método que busca o jogo de dupla e configura para ser editado na página de edição de jogos
	 * 
	 * */
	@Transactional
	@Get("/jogoDeTorneio/edita-jogo-de-torneio-de-dupla/{idJogo}")
	public void formEditaJogoDeTorneioDeDupla(Long idJogo) {

		JogoDeTorneioDeDupla jogoDeTorneio = jogoDeTorneioDeDuplaDao.busca(idJogo);

		Torneio torneio = jogoDeTorneio.getTorneio();

		// TODO verificar controle de acesso
		if (tenistaLogado.isAdminOrOrganizadorProprietario(torneio)){
			result.include("inscricoes", inscricaoDao.getInscricoesPorNivelETorneio(jogoDeTorneio.getNivel(), torneio, null));
			result.include("fases", faseDao.listaTodos(Fase.ID));
			result.include("torneio", torneio);
			result.include("jogoDeTorneio", jogoDeTorneio);

		} else {

			result.redirectTo(TenistaController.class).login();
		}

	}
	/**
	 * Método que vai preparar o formulário de inserir jogos de acordo com o torneio e o nível
	 * 
	 * @param idTorneio identifica o torneio que o jogo será inserido
	 * @param idNivel identificao nível do jogo no torneio que será inserido 
	 * 
	 * */
	@Transactional
	@Get("{idTorneio}/{idNivel}")
	public void preparaFormularioInserirJogo(Long idTorneio, Integer idNivel) {

		//Torneio torneio = torneioDao.find(idTorneio);
		Nivel nivel = nivelDao.busca(idNivel);
		
		if(nivel.isDuplas()){

			ajustaFormularioDeInserirJogoDeTorneioDeDuplas(nivelDao.busca(idNivel), torneioDao.busca(idTorneio));
			
		}else{
		
			ajustaFormularioDeInserirJogoDeTorneio(nivelDao.busca(idNivel), idTorneio);
		}

	}
	public void formJogoComHorarioEspecial() {
		
	}
	public void formJogoDeDuplaComHorarioEspecial() {
		
	}


	// MÉTODOS DE AÇÃO
	
	/**
	 * Método responsável por inserir um jogo no sistema
	 * 
	 * @param jogoDeTorneio
	 *            é o objeto que vem da interface para ser inserido
	 * @param idNivel
	 *            identifica o nível do jogo de torneio
	 * 
	 * */
	@Transactional
	@Post("/jogoDeTorneio/gerencia-jogo")
	public void insereJogoDeTorneio(JogoDeTorneio jogoDeTorneio, boolean enviaEmail) {

		Nivel nivel = nivelDao.busca(jogoDeTorneio.getNivel().getId());
		Fase fase = faseDao.busca(jogoDeTorneio.getFase().getId());
		Torneio torneio = torneioDao.busca(jogoDeTorneio.getTorneio().getId());
		Tenista tenistaUm = tenistaDao.busca(jogoDeTorneio.getTenistaUm().getId());
		Tenista tenistaDois = tenistaDao.busca(jogoDeTorneio.getTenistaDois().getId());

		if(tenistaUm == null){
			tenistaUm = new Tenista();
			tenistaUm.setId(0L);
		}
		if(tenistaDois == null){
			tenistaDois = new Tenista();
			tenistaDois.setId(0L);
		}
		
		jogoDeTorneio.setFase(fase);
		jogoDeTorneio.setTorneio(torneio);
		jogoDeTorneio.setNivel(nivel);
		jogoDeTorneio.setLocal(torneio.getLocal());
		jogoDeTorneio.setTenistaUm(tenistaUm);
		jogoDeTorneio.setTenistaDois(tenistaDois);

		// Tratamento de horário especial
		Inscricao inscricaoUm = inscricaoDao.getInscricaoPorNivelTorneioETenista(nivel, torneio, tenistaUm);
		Inscricao inscricaoDois = inscricaoDao.getInscricaoPorNivelTorneioETenista(nivel, torneio, tenistaDois);

		if(inscricaoUm == null) {
			inscricaoUm = new Inscricao();
		}if(inscricaoDois == null){
			inscricaoDois = new Inscricao();
		}
		
		if((inscricaoUm.temHorarioEspecial()) || inscricaoDois.temHorarioEspecial()){
			
			result.include("jogo", jogoDeTorneio);
			result.include("inscricaoUm", inscricaoUm);
			result.include("inscricaoDois", inscricaoDois);
			
			result.include("enviaEmail", enviaEmail);



			result.redirectTo(JogoDeTorneioController.class).formJogoComHorarioEspecial();
			
		}else{
			
			// Verifica se existe tenista selecionado, caso contrário atribui nulo para evitar violação de restrição
			if(tenistaUm.getId() == 0){
				jogoDeTorneio.setTenistaUm(null);
			}
			if(tenistaDois.getId() == 0){
				jogoDeTorneio.setTenistaDois(null);
			}

			

			
			validaJogoTorneio(jogoDeTorneio);

			
			jogoDeTorneioDao.adiciona(jogoDeTorneio);
			result.include("mensagemDeSucesso", "Jogo inserido com sucesso.");

			if (enviaEmail) {
				enviaEmailParaTenistasDoJogo(jogoDeTorneio, TipoAcaoJogo.INSERIR);
			}

			ajustaFormularioDeInserirJogoDeTorneio(nivel, torneio.getId());
		}

		

	}

	
	/**
	 * Método responsável por inserir um jogo de duplas no sistema
	 * 
	 * @param jogoDeTorneio
	 *            é o objeto que vem da interface para ser inserido
	 * @param idNivel
	 *            identifica o nível do jogo de torneio
	 * 
	 * */
	@Transactional
	@Post("/jogoDeTorneio/insere-jogo-de-dupla")
	public void insereJogoDeTorneioDeDuplas(JogoDeTorneioDeDupla jogoDeTorneio, boolean enviaEmail, Long duplaUm, Long duplaDois) {

		Nivel nivel = nivelDao.busca(jogoDeTorneio.getNivel().getId());
		Fase fase = faseDao.busca(jogoDeTorneio.getFase().getId());
		Torneio torneio = torneioDao.busca(jogoDeTorneio.getTorneio().getId());
		InscricaoDeDupla inscricaoUm = inscricaoDeDuplaDao.busca(duplaUm);
		InscricaoDeDupla inscricaoDois = inscricaoDeDuplaDao.busca(duplaDois);

		if(inscricaoUm == null){
			inscricaoUm = new InscricaoDeDupla();
			inscricaoUm.setId(0L);
			Tenista tenistaVazio = new Tenista();
			tenistaVazio.setId(0L);
			inscricaoUm.setTenista(tenistaVazio);
			inscricaoUm.setTenistaParceiro(tenistaVazio);
			System.out.println("atribuiu novo objeto");
		}
		if(inscricaoDois == null){
			inscricaoDois = new InscricaoDeDupla();
			inscricaoDois.setId(0L);
			Tenista tenistaVazio = new Tenista();
			tenistaVazio.setId(0L);
			inscricaoDois.setTenista(tenistaVazio);
			inscricaoDois.setTenistaParceiro(tenistaVazio);
			System.out.println("atribuiu novo objeto");
		}
		
		jogoDeTorneio.setFase(fase);
		jogoDeTorneio.setTorneio(torneio);
		jogoDeTorneio.setNivel(nivel);
		jogoDeTorneio.setLocal(torneio.getLocal());
		jogoDeTorneio.setTenistaUm(inscricaoUm.getTenista());
		jogoDeTorneio.setTenistaParceiroUm(inscricaoUm.getTenistaParceiro());
		jogoDeTorneio.setTenistaDois(inscricaoDois.getTenista());
		jogoDeTorneio.setTenistaParceiroDois(inscricaoDois.getTenistaParceiro());

		// Tratamento de horário especial
		//Inscricao inscricaoUm = inscricaoDao.getInscricaoPorNivelTorneioETenista(nivel, torneio, tenistaUm);
		//Inscricao inscricaoDois = inscricaoDao.getInscricaoPorNivelTorneioETenista(nivel, torneio, tenistaDois);

	
		if((inscricaoUm.temHorarioEspecial()) || inscricaoDois.temHorarioEspecial()){
			
			result.include("jogo", jogoDeTorneio);
			result.include("inscricaoUm", inscricaoUm);
			result.include("inscricaoDois", inscricaoDois);
			result.include("enviaEmail", enviaEmail);



			result.redirectTo(JogoDeTorneioController.class).formJogoDeDuplaComHorarioEspecial();
			
		}else{
			
			// Verifica se existe tenista selecionado, caso contrário atribui nulo para evitar violação de restrição
			if(inscricaoUm.getId() == 0){
				jogoDeTorneio.setTenistaUm(null);
				jogoDeTorneio.setTenistaParceiroUm(null);
				
			}
			if(inscricaoDois.getId() == 0){
				jogoDeTorneio.setTenistaDois(null);
				jogoDeTorneio.setTenistaParceiroDois(null);
			}
			
			validaJogoTorneio(jogoDeTorneio);

			jogoDeTorneioDeDuplaDao.adiciona(jogoDeTorneio);
			result.include("mensagemDeSucesso", "Jogo inserido com sucesso.");

			if (enviaEmail) {
				enviaEmailParaTenistasDoJogoDeDuplas(jogoDeTorneio, TipoAcaoJogo.INSERIR);
			}

			ajustaFormularioDeInserirJogoDeTorneioDeDuplas(nivel, torneio);
		}

		

	}


	/**
	 * Método responsável por informar o resultado de um jogo no sistema
	 * 
	 * @param jogoDeTorneio
	 *            é o objeto que vem da interface para ser inserido
	 * 
	 * 
	 * */
	@Transactional
	@Post("/jogoDeTorneio/informa-resultado-do-jogo")
	public void informaResultadoDoJogo(JogoDeTorneio jogoDeTorneio, boolean enviaEmail) {

		Integer setT1 = 0;
		Integer setT2 = 0;
		Torneio torneio = torneioDao.busca(jogoDeTorneio.getTorneio().getId());

		if(torneio.isDuplas()){

			JogoDeTorneioDeDupla jogo = jogoDeTorneioDeDuplaDao.busca(jogoDeTorneio.getId());

			jogo.setSetTenistaUm(jogoDeTorneio.getSetTenistaUm());
			jogo.setSetTenistaDois(jogoDeTorneio.getSetTenistaDois());
			jogo.setGamesSet1T1(jogoDeTorneio.getGamesSet1T1());
			jogo.setGamesSet1T2(jogoDeTorneio.getGamesSet1T2());
			jogo.setGamesSet2T1(jogoDeTorneio.getGamesSet2T1());
			jogo.setGamesSet2T2(jogoDeTorneio.getGamesSet2T2());
			jogo.setGamesSet3T1(jogoDeTorneio.getGamesSet3T1());
			jogo.setGamesSet3T2(jogoDeTorneio.getGamesSet3T2());
			jogo.setSuperTieBreakT1(jogoDeTorneio.getSuperTieBreakT1());
			jogo.setSuperTieBreakT2(jogoDeTorneio.getSuperTieBreakT2());
			jogo.setWo(jogoDeTorneio.isWo());
			
			if(jogo.getGamesSet1T1()!=null && jogo.getGamesSet1T2() != null){
				if(jogo.getGamesSet1T1() > jogo.getGamesSet1T2()){
					setT1++;
				}else if(jogo.getGamesSet1T1() < jogo.getGamesSet1T2()){
					setT2++;
				}
			}

			if(jogo.getGamesSet2T1()!=null && jogo.getGamesSet2T2() != null){
				if(jogo.getGamesSet2T1() > jogo.getGamesSet2T2()){
					setT1++;
				}else if(jogo.getGamesSet2T1() < jogo.getGamesSet2T2()){
					setT2++;
				}
			}

			if(jogo.getGamesSet3T1()!=null && jogo.getGamesSet3T2() != null){
				if(jogo.getGamesSet3T1() > jogo.getGamesSet3T2()){
					setT1++;
				}else if(jogo.getGamesSet3T1() < jogo.getGamesSet3T2()){
					setT2++;
				}
			}

			if(jogo.getSuperTieBreakT1()!=null && jogo.getSuperTieBreakT2() != null){
				if(jogo.getSuperTieBreakT1() > jogo.getSuperTieBreakT2()){
					setT1++;
				}else if(jogo.getSuperTieBreakT1() < jogo.getSuperTieBreakT2()){
					setT2++;
				}
			}

			jogo.setSetTenistaUm(setT1);
			jogo.setSetTenistaDois(setT2);

			jogoDeTorneioDeDuplaDao.atualiza(jogo);
			result.include("mensagemDeSucesso", "Resultado do jogo foi inserido com sucesso.");

			if (enviaEmail) {
				enviaEmailParaTenistasDoJogoDeDuplas(jogo,TipoAcaoJogo.INFORMAR_RESULTADO);
			}
			result.redirectTo(JogoDeTorneioController.class).visualizaJogosDoTorneio(jogo.getTorneio().getId(), false);
			
		}else{
			
			// se for um jogo de simples
			
			JogoDeTorneio jogo = jogoDeTorneioDao.busca(jogoDeTorneio.getId());

			jogo.setSetTenistaUm(jogoDeTorneio.getSetTenistaUm());
			jogo.setSetTenistaDois(jogoDeTorneio.getSetTenistaDois());
			jogo.setGamesSet1T1(jogoDeTorneio.getGamesSet1T1());
			jogo.setGamesSet1T2(jogoDeTorneio.getGamesSet1T2());
			jogo.setGamesSet2T1(jogoDeTorneio.getGamesSet2T1());
			jogo.setGamesSet2T2(jogoDeTorneio.getGamesSet2T2());
			jogo.setGamesSet3T1(jogoDeTorneio.getGamesSet3T1());
			jogo.setGamesSet3T2(jogoDeTorneio.getGamesSet3T2());
			jogo.setSuperTieBreakT1(jogoDeTorneio.getSuperTieBreakT1());
			jogo.setSuperTieBreakT2(jogoDeTorneio.getSuperTieBreakT2());
			jogo.setWo(jogoDeTorneio.isWo());

			// calculando a quantidade de sets ganhos por cada tenista
			
			if(jogo.getGamesSet1T1()!=null && jogo.getGamesSet1T2() != null){
				if(jogo.getGamesSet1T1() > jogo.getGamesSet1T2()){
					setT1++;
				}else if(jogo.getGamesSet1T1() < jogo.getGamesSet1T2()){
					setT2++;
				}
			}

			if(jogo.getGamesSet2T1()!=null && jogo.getGamesSet2T2() != null){
				if(jogo.getGamesSet2T1() > jogo.getGamesSet2T2()){
					setT1++;
				}else if(jogo.getGamesSet2T1() < jogo.getGamesSet2T2()){
					setT2++;
				}
			}


			if(jogo.getGamesSet3T1()!=null && jogo.getGamesSet3T2() != null){
				if(jogo.getGamesSet3T1() > jogo.getGamesSet3T2()){
					setT1++;
				}else if(jogo.getGamesSet3T1() < jogo.getGamesSet3T2()){
					setT2++;
				}
			}

			if(jogo.getSuperTieBreakT1()!=null && jogo.getSuperTieBreakT2() != null){
				if(jogo.getSuperTieBreakT1() > jogo.getSuperTieBreakT2()){
					setT1++;
				}else if(jogo.getSuperTieBreakT1() < jogo.getSuperTieBreakT2()){
					setT2++;
				}
			}

			jogo.setSetTenistaUm(setT1);
			jogo.setSetTenistaDois(setT2);
			
			
			// Pega a inscrição do tenista perderdor e atribui a pontuação
			
			Inscricao inscricaoVencedor = null;
			Inscricao inscricaoPerderdor = null;
			jogoDeTorneio = jogo;
			Nivel nivel = jogoDeTorneio.getNivel();
			Tenista tenistaVencedor = jogoDeTorneio.getVencedor();
			Tenista tenistaPerderdor = jogoDeTorneio.getPerdedor();
			
			if(torneio.getTipoDeChave().equals(TipoDeChave.ELIMINATORIO) || torneio.getTipoDeChave().equals(TipoDeChave.MISTO)){
			
				inscricaoPerderdor = inscricaoDao.getInscricaoPorNivelTorneioETenista(nivel, torneio, tenistaPerderdor);

				if(jogoDeTorneio.getFase().getId() == Fase.FINAIS_64){
					inscricaoPerderdor.setPontuacao(Pontuacao.FINAIS_64);
				}
				if(jogoDeTorneio.getFase().getId() == Fase.FINAIS_32){
					inscricaoPerderdor.setPontuacao(Pontuacao.FINAIS_32);
				}
				if(jogoDeTorneio.getFase().getId() == Fase.FINAIS_16){
					inscricaoPerderdor.setPontuacao(Pontuacao.FINAIS_16);
				}
				if(jogoDeTorneio.getFase().getId() == Fase.FINAIS_8){
					inscricaoPerderdor.setPontuacao(Pontuacao.FINAIS_8);
				}
				if(jogoDeTorneio.getFase().getId() == Fase.FINAIS_4){
					inscricaoPerderdor.setPontuacao(Pontuacao.FINAIS_4);
				}
				if(jogoDeTorneio.getFase().getId() == Fase.SEMIFINAIS){
					inscricaoPerderdor.setPontuacao(Pontuacao.SEMIFINAL);
				}
				if(jogoDeTorneio.getFase().getId() == Fase.FINAL){
					inscricaoVencedor = inscricaoDao.getInscricaoPorNivelTorneioETenista(nivel, torneio, tenistaVencedor);
					
					inscricaoVencedor.setPontuacao(Pontuacao.CAMPEAO);
					inscricaoPerderdor.setPontuacao(Pontuacao.VICE_CAMPEAO);
				}
				
			}
	
			jogoDeTorneioDao.atualiza(jogo);
			inscricaoDao.atualiza(inscricaoPerderdor);
			result.include("mensagemDeSucesso", "Resultado do jogo foi inserido com sucesso.");

			if (enviaEmail) {
				enviaEmailParaTenistasDoJogo(jogo,TipoAcaoJogo.INFORMAR_RESULTADO);
			}
			result.redirectTo(JogoDeTorneioController.class).visualizaJogosDoTorneio(jogo.getTorneio().getId(), false);
			
		}

	}
	/**
	 * Método responsável por informar o resultado de um jogo no sistema
	 * 
	 * @param jogoDeTorneio
	 *            é o objeto que vem da interface para ser inserido
	 * 
	 * 
	 * */
	@Transactional
	@Post("/jogoDeTorneio/edita-jogo-de-torneio")
	public void editaJogoDeTorneio(JogoDeTorneio jogoDeTorneio, boolean enviaEmail) {

		JogoDeTorneio jogo = jogoDeTorneioDao.busca(jogoDeTorneio.getId());
		jogo.setFase(faseDao.busca(jogoDeTorneio.getFase().getId()));
		jogo.setTenistaUm(tenistaDao.busca(jogoDeTorneio.getTenistaUm().getId()));
		jogo.setTenistaDois(tenistaDao.busca(jogoDeTorneio.getTenistaDois().getId()));
		
		jogo.setData(jogoDeTorneio.getData());
		jogo.setHora(jogoDeTorneio.getHora());
		jogo.setObsTenistaUm(jogoDeTorneio.getObsTenistaUm());
		jogo.setObsTenistaDois(jogoDeTorneio.getObsTenistaDois());
		jogo.setWo(jogoDeTorneio.isWo());
		
		jogoDeTorneioDao.atualiza(jogo);
		result.include("mensagemDeSucesso", "Jogo de código " + jogoDeTorneio.getId() + " foi atualizado com sucesso.");

		if (enviaEmail) {
			enviaEmailParaTenistasDoJogo(jogo,TipoAcaoJogo.EDITAR);
		}

		ajustaFormularioDeInserirJogoDeTorneio(jogo.getNivel(), jogo.getTorneio().getId());

	}

	/**
	 * Método responsável por informar o resultado de um jogo no sistema
	 * 
	 * @param jogoDeTorneio
	 *            é o objeto que vem da interface para ser inserido
	 * 
	 * 
	 * */
	@Transactional
	@Post("/jogoDeTorneio/edita-jogo-de-torneio-de-dupla")
	public void editaJogoDeTorneioDeDuplas(JogoDeTorneioDeDupla jogoDeTorneio, boolean enviaEmail, Long duplaUm, Long duplaDois) {

		InscricaoDeDupla inscricaoUm = inscricaoDeDuplaDao.busca(duplaUm);
		InscricaoDeDupla inscricaoDois = inscricaoDeDuplaDao.busca(duplaDois);
		
		// Faz o tratamento em inscrição para evitar falha de acesso a objeto nulo de tenista
		if(inscricaoUm == null){
			inscricaoUm = new InscricaoDeDupla();
		}
		if(inscricaoDois == null){
			inscricaoDois = new InscricaoDeDupla();
		}
		
		JogoDeTorneioDeDupla jogo = jogoDeTorneioDeDuplaDao.busca(jogoDeTorneio.getId());
		jogo.setFase(faseDao.busca(jogoDeTorneio.getFase().getId()));
		jogo.setTenistaUm(inscricaoUm.getTenista());
		jogo.setTenistaParceiroUm(inscricaoUm.getTenistaParceiro());
		jogo.setTenistaDois(inscricaoDois.getTenista());
		jogo.setTenistaParceiroDois(inscricaoDois.getTenistaParceiro());
		
		jogo.setData(jogoDeTorneio.getData());
		jogo.setHora(jogoDeTorneio.getHora());
		jogo.setFase(jogoDeTorneio.getFase());
		jogo.setObsTenistaUm(jogoDeTorneio.getObsTenistaUm());
		jogo.setObsTenistaDois(jogoDeTorneio.getObsTenistaDois());
		jogo.setWo(jogoDeTorneio.isWo());
		
		jogoDeTorneioDao.atualiza(jogo);
		result.include("mensagemDeSucesso", "Jogo de código " + jogoDeTorneio.getId() + " foi atualizado com sucesso.");

		if (enviaEmail) {
			enviaEmailParaTenistasDoJogoDeDuplas(jogo,TipoAcaoJogo.EDITAR);
		}

		ajustaFormularioDeInserirJogoDeTorneioDeDuplas(jogo.getNivel(), jogo.getTorneio());

	}

	
	/**
	 * Método que apresenta os jogos do torneio
	 * 
	 */
	@Transactional
	@Get("/jogoDeTorneio/jogos/{idTorneio}")
	public void visualizaJogosDoTorneio(Long idTorneio, boolean isPorNivel) {
		
		if(!isPorNivel){
			Torneio torneio = torneioDao.busca(idTorneio);
			List<Nivel> niveisDoTorneio = torneio.getNiveis();
			List<JogoDeTorneio> jogosDoTorneio = jogoDeTorneioDao.getJogosPorTorneio(torneio);

			boolean jogosComBotoes = false;
			result.include("niveisDoTorneio", niveisDoTorneio);
			result.include("torneio",torneio);
			result.include("jogos", jogosDoTorneio);
			result.include("jogosComBotoes", jogosComBotoes);
			result.include("isPornivel", isPorNivel);

			if(tenistaLogado.isAdminOrOrganizadorProprietario(torneio)){
				result.include("adminOrganizadorTorneio", true);
			}

		}else{

			result.include("isPornivel", isPorNivel);

		}


	}

	/**
	 * Método que apresenta os jogos do torneio por nivel
	 * 
	 */
	@Transactional
	@Get("/jogoDeTorneio/jogos/{idTorneio}/{idNivel}")
	public void visualizaJogosDoTorneioPorNivel(Long idTorneio, Integer idNivel) {

		boolean jogosComBotoes = false;
		Torneio torneio = torneioDao.busca(idTorneio);
		Nivel nivel = nivelDao.busca(idNivel);
		List<JogoDeTorneio> jogosDoTorneio = jogoDeTorneioDao.getJogosNoTorneioPorNivel(idNivel, idTorneio);
		List<Integer> gruposDoNivelNoTorneio = inscricaoDao.getGruposPorNivelETorneio(nivel,torneio);

		result.include("torneio",torneio);
		result.include("nivel",nivel);
		result.include("niveisDoTorneio", torneio.getNiveis());
		result.include("jogos", jogosDoTorneio);
		result.include("jogosComBotoes", jogosComBotoes);
		result.include("gruposDoNivelNoTorneio", gruposDoNivelNoTorneio);
		
		if(tenistaLogado.isAdminOrOrganizadorProprietario(torneio)){
			result.include("adminOrganizadorTorneio", true);
		}
		result.redirectTo(JogoDeTorneioController.class).visualizaJogosDoTorneio(idTorneio, true);


		
	}
	
	/**
	 * Método que apresenta os jogos do torneio pela data e deixa pronto para impressão
	 * */
	@Post("/jogoDeTorneio")
	public void imprimiJogosDoTorneioPorData(Long idTorneio, Calendar dataDosJogos){
		
		Torneio torneio = torneioDao.busca(idTorneio);
		List<JogoDeTorneio> jogosDeTorneio = jogoDeTorneioDao.getJogosNoTorneioPorData(torneio, dataDosJogos);
		result.include("torneio",torneio);
		result.include("jogos",jogosDeTorneio);
		result.include("dataDosJogos",dataDosJogos);

		if(tenistaLogado.isAdminOrOrganizadorProprietario(torneio)){
			result.include("adminOrganizadorTorneio", true);
		}
		
	}
	/**
	 * Método que apresenta os jogos do torneio
	 * 
	 */
	@Get("/jogoDeTorneio/meus-jogos/numero/{numero}")
	public void visualizaMeusJogos(Integer numero) {
	
		int primeiro = 0;
		int anterior = 0;
		int proximo = 0;
		
		Tenista tenista = tenistaLogado.getTenista();
		//boolean jogosComBotoes = false;

		if (numero == null || numero == 0){
			numero = 1;
			anterior = 0;
			proximo = 2;			
		}else{
			anterior = numero - 1;
			proximo = numero +1;
		}
		
		primeiro = (numero - 1) * MAXIMO_ELEMENTOS_PAGINA;
		
		if (tenista != null) {

			List<JogoDeTorneio> jogosDoTenista = jogoDao.getJogosDeTorneioDeUmTenista(tenista,primeiro,MAXIMO_ELEMENTOS_PAGINA);
			result.include("jogos", jogosDoTenista);
			//result.include("jogosComBotoes", jogosComBotoes);

			result.include("numeroDeJogos", jogosDoTenista.size());
			result.include("anterior", anterior);
			result.include("proximo", proximo);
			result.include("numeroElementosMaximo", MAXIMO_ELEMENTOS_PAGINA);

		}else{
			result.redirectTo(IndexController.class).paginaInicial();
		}

	}
	
	/**
	 * Método que apresenta os jogos do torneio
	 * 
	 */
	@Transactional
	@Get("/jogoDeTorneio/{idJogo}")
	public void removeJogoDeTorneio(Long idJogo) {

		JogoDeTorneio jogo = jogoDeTorneioDao.find(idJogo);
		jogoDeTorneioDao.remove(jogo);
		result.include("mensagemDeSucesso", "Jogo de código " + jogo.getId() + " foi removido com sucesso.");

		enviaEmailParaTenistasDoJogo(jogo,TipoAcaoJogo.REMOVER);

		if(jogo.getTorneio().isDuplas()){
			ajustaFormularioDeInserirJogoDeTorneioDeDuplas(jogo.getNivel(), jogo.getTorneio());

		}else{
			ajustaFormularioDeInserirJogoDeTorneio(jogo.getNivel(), jogo.getTorneio().getId());
		}

	}
	
	
	/**
	 * Método que confirma um jogo que tem horário especial
	 * */
	@Transactional
	@Post
	public void confirmaJogoDeTorneioComHorarioEspecial(JogoDeTorneio jogoDeTorneio, boolean enviaEmail){
		
		Nivel nivel = nivelDao.busca(jogoDeTorneio.getNivel().getId());
		Fase fase = faseDao.busca(jogoDeTorneio.getFase().getId());
		Torneio torneio = torneioDao.busca(jogoDeTorneio.getTorneio().getId());
		Tenista tenistaUm = tenistaDao.busca(jogoDeTorneio.getTenistaUm().getId());
		Tenista tenistaDois = tenistaDao.busca(jogoDeTorneio.getTenistaDois().getId());
		
		
		jogoDeTorneio.setFase(fase);
		jogoDeTorneio.setTorneio(torneio);
		jogoDeTorneio.setNivel(nivel);
		jogoDeTorneio.setLocal(torneio.getLocal());
		jogoDeTorneio.setTenistaUm(tenistaUm);
		jogoDeTorneio.setTenistaDois(tenistaDois);
		
		validaJogoTorneio(jogoDeTorneio);

		jogoDeTorneioDao.adiciona(jogoDeTorneio);

		result.include("mensagemDeSucesso", "Jogo inserido com sucesso.");

			
		if (enviaEmail) {
			enviaEmailParaTenistasDoJogo(jogoDeTorneio, TipoAcaoJogo.INSERIR);
		}

		ajustaFormularioDeInserirJogoDeTorneio(nivel, torneio.getId());
		
	}
	
	/**
	 * Método que confirma um jogo de dupla que tem horário especial
	 * */
	@Transactional
	@Post
	public void confirmaJogoDeTorneioDeDuplasComHorarioEspecial(JogoDeTorneioDeDupla jogoDeTorneio, boolean enviaEmail){
		
		Nivel nivel = nivelDao.busca(jogoDeTorneio.getNivel().getId());
		Fase fase = faseDao.busca(jogoDeTorneio.getFase().getId());
		Torneio torneio = torneioDao.busca(jogoDeTorneio.getTorneio().getId());
		Tenista tenistaUm = tenistaDao.busca(jogoDeTorneio.getTenistaUm().getId());
		Tenista tenistaDois = tenistaDao.busca(jogoDeTorneio.getTenistaDois().getId());
		Tenista tenistaParceiroUm = tenistaDao.busca(jogoDeTorneio.getTenistaParceiroUm().getId());
		Tenista tenistaParceiroDois = tenistaDao.busca(jogoDeTorneio.getTenistaParceiroDois().getId());

		jogoDeTorneio.setFase(fase);
		jogoDeTorneio.setTorneio(torneio);
		jogoDeTorneio.setNivel(nivel);
		jogoDeTorneio.setLocal(torneio.getLocal());
		jogoDeTorneio.setTenistaUm(tenistaUm);
		jogoDeTorneio.setTenistaDois(tenistaDois);
		jogoDeTorneio.setTenistaParceiroUm(tenistaParceiroUm);
		jogoDeTorneio.setTenistaParceiroDois(tenistaParceiroDois);
		
		validaJogoTorneio(jogoDeTorneio);

		jogoDeTorneioDeDuplaDao.adiciona(jogoDeTorneio);

		result.include("mensagemDeSucesso", "Jogo inserido com sucesso.");

		if (enviaEmail) {
			enviaEmailParaTenistasDoJogoDeDuplas(jogoDeTorneio, TipoAcaoJogo.INSERIR);
		}

		ajustaFormularioDeInserirJogoDeTorneioDeDuplas(nivel, torneio);
		
	}
	
	
	// MÉTODOS PRIVADOS
	
	/**
	 * Método que envia um e-mail para os tenistas que irão jogar ou que já
	 * jogaram
	 * 
	 * @param jogoDeTorneio
	 * @param torneio
	 */
	private void enviaEmailParaTenistasDoJogo(JogoDeTorneio jogoDeTorneio, TipoAcaoJogo tipoAcaoJogo) {

		String assunto;
		String mensagem;

		assunto = jogoDeTorneio.getTorneio().getNome() + ": " + tipoAcaoJogo.getNome();
		mensagem = "Informações do seu jogo \n\n " + "Data do jogo: "
				+ Utils.formataDataPadrao(jogoDeTorneio.getData().getTime()) + "\n\n" + "Hora do jogo: "
				+ jogoDeTorneio.getHora() + "\n\n" 
				+ ((jogoDeTorneio.getTenistaUm()!=null)?jogoDeTorneio.getTenistaUm().getNomeExibicao():"Adversário a definir") + "\n "
				+ ((jogoDeTorneio.getTenistaDois()!=null)?jogoDeTorneio.getTenistaDois().getNomeExibicao():"Adversário a definir") + "\n\n";

		if (jogoDeTorneio.getOcorreu()) {

			mensagem += informaResultadoEmTexto(jogoDeTorneio);

		} else {

			mensagem += " Bom jogo.";

		}

		if(jogoDeTorneio.getTenistaUm() != null && jogoDeTorneio.getTenistaUm().isReceberNotificacoes()){
			emailSender.enviaEmailPadrao(jogoDeTorneio.getTenistaUm(), assunto, mensagem, EmailSender.UTF_8, EmailSender.TEXT_PLAIN);
		}
		if(jogoDeTorneio.getTenistaDois() != null && jogoDeTorneio.getTenistaDois().isReceberNotificacoes()){
			emailSender.enviaEmailPadrao(jogoDeTorneio.getTenistaDois(), assunto, mensagem, EmailSender.UTF_8, EmailSender.TEXT_PLAIN);
		}
	}

	/**
	 * Método que envia um e-mail para os tenistas de duplas que irão jogar ou que já
	 * jogaram
	 * 
	 * @param jogoDeTorneio
	 * @param torneio
	 */
	private void enviaEmailParaTenistasDoJogoDeDuplas(JogoDeTorneioDeDupla jogoDeTorneio, TipoAcaoJogo tipoAcaoJogo) {

		String assunto;
		String mensagem;

		assunto = jogoDeTorneio.getTorneio().getNome() + ": " + tipoAcaoJogo.getNome();
		mensagem = "Informações do seu jogo \n\n " + "Data do jogo: "
				+ Utils.formataDataPadrao(jogoDeTorneio.getData().getTime()) + "\n\n" + "Hora do jogo: "
				+ jogoDeTorneio.getHora() + "\n\n" 
				+ ((jogoDeTorneio.getTenistaUm()!=null)?jogoDeTorneio.getTenistaUm().getNomeCompleto():"A definir") + " / "
				+ ((jogoDeTorneio.getTenistaParceiroUm()!=null)?jogoDeTorneio.getTenistaParceiroUm().getNomeCompleto():"A definir") + "\n"
				+ ((jogoDeTorneio.getTenistaDois()!=null)?jogoDeTorneio.getTenistaDois().getNomeCompleto():"A definir") + " / "
				+ ((jogoDeTorneio.getTenistaParceiroDois()!=null)?jogoDeTorneio.getTenistaParceiroDois().getNomeCompleto():"A definir") + "\n\n";
		

		if (jogoDeTorneio.getOcorreu()) {

			mensagem += informaResultadoEmTexto(jogoDeTorneio);

		} else {

			mensagem += " Bom jogo.";

		}

		if(jogoDeTorneio.getTenistaUm().isReceberNotificacoes()){
			emailSender.enviaEmailPadrao(jogoDeTorneio.getTenistaUm(), assunto, mensagem, EmailSender.UTF_8, EmailSender.TEXT_PLAIN);
		}
		if(jogoDeTorneio.getTenistaParceiroUm().isReceberNotificacoes()){
			emailSender.enviaEmailPadrao(jogoDeTorneio.getTenistaParceiroUm(), assunto, mensagem, EmailSender.UTF_8, EmailSender.TEXT_PLAIN);
		}
		if(jogoDeTorneio.getTenistaDois().isReceberNotificacoes()){
			emailSender.enviaEmailPadrao(jogoDeTorneio.getTenistaDois(), assunto, mensagem, EmailSender.UTF_8, EmailSender.TEXT_PLAIN);
		}
		if(jogoDeTorneio.getTenistaParceiroDois().isReceberNotificacoes()){
			emailSender.enviaEmailPadrao(jogoDeTorneio.getTenistaParceiroDois(), assunto, mensagem, EmailSender.UTF_8, EmailSender.TEXT_PLAIN);
		}

	}
	/**
	 * Método que preenche o resultado do jogo para ser enviado no e-mail
	 * 
	 * */
	private String informaResultadoEmTexto(JogoDeTorneio jogoDeTorneio) {

		String mensagem = "Resultado: \n" + jogoDeTorneio.getTenistaUm().getNome() + "\n"
				+ jogoDeTorneio.getTenistaDois().getNome() + "\n" +

				"Set: " + jogoDeTorneio.getSetTenistaUm() + " - " + jogoDeTorneio.getSetTenistaDois() + "\n"
				+ (jogoDeTorneio.getGamesSet1T1() != null ? jogoDeTorneio.getGamesSet1T1() + " - " : "")
				+ (jogoDeTorneio.getGamesSet1T2() != null ? jogoDeTorneio.getGamesSet1T2() + " / " : "")
				+ (jogoDeTorneio.getGamesSet2T1() != null ? jogoDeTorneio.getGamesSet2T1() + " - " : "")
				+ (jogoDeTorneio.getGamesSet2T2() != null ? jogoDeTorneio.getGamesSet2T2() + " / " : "")
				+ (jogoDeTorneio.getGamesSet3T1() != null ? jogoDeTorneio.getGamesSet3T1() + " - " : "")
				+ (jogoDeTorneio.getGamesSet3T2() != null ? jogoDeTorneio.getGamesSet3T2() + " / " : "")
				+ (jogoDeTorneio.getSuperTieBreakT1() != null ? jogoDeTorneio.getSuperTieBreakT1() + " - " : "")
				+ (jogoDeTorneio.getSuperTieBreakT2() != null ? jogoDeTorneio.getSuperTieBreakT2() + " / " : "");

		return mensagem;
	}

	/**
	 * Método que ajusta o formulário de inserir jogos para um novo jogo
	 * 
	 * @param nivel
	 * @param torneio
	 */
	private void ajustaFormularioDeInserirJogoDeTorneio(Nivel nivel, Long idTorneio) {

		// Pega os tenistas inscritos neste nível do torneio, depois
		// redireciona para a tela de inscrição já preparada com o nível e os
		// jogos
		List<Tenista> tenistas = tenistaDao.getTenistasInscritosConfirmadosNoTorneioPorNivel(nivel.getId(), idTorneio);

		result.include("tenistasInscritos", tenistas);
		result.include("fases", faseDao.listaTodos(Fase.ID));
		result.include("nivel", nivel);
		result.include("jogos", jogoDeTorneioDao.getJogosNoTorneioPorNivel(nivel.getId(), idTorneio));

		// redireciona para a página com o torneio e o nível para que seja
		// mostrado o formulário de novo jogo já preparado
		result.redirectTo(JogoDeTorneioController.class).formGerenciaJogo(idTorneio);
	}
	
	/**
	 * Método que ajusta o formulário de inserir jogos para um novo jogo de dupla
	 * 
	 * @param nivel
	 * @param torneio
	 */
	private void ajustaFormularioDeInserirJogoDeTorneioDeDuplas(Nivel nivel, Torneio torneio) {

		// Pega os tenistas inscritos neste nível do torneio, depois
		// redireciona para a tela de inscrição já preparada com o nível e os
		// jogos
		List<Inscricao> inscricoes = inscricaoDao.getInscricoesPorNivelETorneio(nivel, torneio, null);

		result.include("inscricoes", inscricoes);
		result.include("fases", faseDao.listaTodos(Fase.ID));
		result.include("nivel", nivel);
		result.include("jogos", jogoDeTorneioDeDuplaDao.getJogosNoTorneioPorNivel(nivel.getId(), torneio.getId()));

		// redireciona para a página com o torneio e o nível para que seja
		// mostrado o formulário de novo jogo já preparado
		result.redirectTo(JogoDeTorneioController.class).formGerenciaJogoDeDuplas(torneio.getId());
	}

	// método de validação dos dados de um jogo de torneio
	private void validaJogoTorneio(JogoDeTorneio jogoDeTorneio) {
	
		validator.addIf(jogoDeTorneioDao.existeJogoSemelhante(jogoDeTorneio), new SimpleMessage("jogo.repetido",
				"O jogo já foi inserido para a mesma fase, nível e torneio. É preciso que edite ou elimine o jogo anterior"));
	
		if(jogoDeTorneio.getTenistaUm() != null && jogoDeTorneio.getTenistaDois() != null){
			validator.addIf(jogoDeTorneio.getTenistaUm().getId().equals(jogoDeTorneio.getTenistaDois().getId()), new SimpleMessage("jogo."+ Utils.ESPORTISTA,
					"Os " + Utils.ESPORTISTA + "s escolhidos são os mesmos, por favor altere."));
		}
	
		if (jogoDeTorneio.getFase().getId() == Fase.GRUPO){
		
			Inscricao inscricaoTenistaUm = inscricaoDao.getInscricaoPorNivelTorneioETenista(jogoDeTorneio.getNivel(), jogoDeTorneio.getTorneio(), jogoDeTorneio.getTenistaUm());
			Inscricao inscricaoTenistaDois = inscricaoDao.getInscricaoPorNivelTorneioETenista(jogoDeTorneio.getNivel(), jogoDeTorneio.getTorneio(), jogoDeTorneio.getTenistaDois());
			
			validator.addIf(inscricaoTenistaUm.getNumeroGrupo() != inscricaoTenistaDois.getNumeroGrupo(), new SimpleMessage("inscricao.tenista", "Os "+ Utils.ESPORTISTA + "s não estão inscritos no mesmo grupo"));
		
		}
		validator.validate(jogoDeTorneio);
	
		if(jogoDeTorneio instanceof JogoDeTorneioDeDupla){
			validator.onErrorRedirectTo(this).formGerenciaJogoDeDuplas(jogoDeTorneio.getTorneio().getId());
		}else{
			validator.onErrorRedirectTo(this).formGerenciaJogo(jogoDeTorneio.getTorneio().getId());
		}
	}
	
	
/*	private void validaJogoTorneioDeDupla(JogoDeTorneioDeDupla jogoDeTorneio) {
		
		validator.addIf(jogoDeTorneioDao.existeJogoSemelhante(jogoDeTorneio), new SimpleMessage("jogo.repetido",
				"O jogo já foi inserido para a mesma fase, nível e torneio. É preciso que edite ou elimine o jogo anterior"));
	
		validator.addIf(jogoDeTorneio.getTenistaUm().getId().equals(jogoDeTorneio.getTenistaDois().getId()), new SimpleMessage("jogo.tenista",
				"Os tenistas informados são os mesmos, por favor altere."));


		if (jogoDeTorneio.getFase().getId() == Fase.GRUPO){
		
			Inscricao inscricaoTenistaUm = inscricaoDao.getInscricaoPorNivelTorneioETenista(jogoDeTorneio.getNivel(), jogoDeTorneio.getTorneio(), jogoDeTorneio.getTenistaUm());
			Inscricao inscricaoTenistaDois = inscricaoDao.getInscricaoPorNivelTorneioETenista(jogoDeTorneio.getNivel(), jogoDeTorneio.getTorneio(), jogoDeTorneio.getTenistaDois());
			
			validator.addIf(inscricaoTenistaUm.getNumeroGrupo() != inscricaoTenistaDois.getNumeroGrupo(), new SimpleMessage("inscricao.tenista", "Os tenistas não estão inscritos no mesmo grupo"));
		
		}
		validator.validate(jogoDeTorneio);
	
		validator.onErrorRedirectTo(this).formGerenciaJogoDeDuplas(jogoDeTorneio.getTorneio().getId());
		
	}*/


}
