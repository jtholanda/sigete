package br.portaldotenis.portal.controller;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Patch;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.caelum.vraptor.view.Results;
import br.portaldotenis.portal.controleacesso.AdministradorRule;
import br.portaldotenis.portal.controleacesso.LogadoRule;
import br.portaldotenis.portal.dao.CidadeDAO;
import br.portaldotenis.portal.dao.ClubeDAO;
import br.portaldotenis.portal.dao.ConfirmacaoEmailDAO;
import br.portaldotenis.portal.dao.FaseDAO;
import br.portaldotenis.portal.dao.FiliadoDAO;
import br.portaldotenis.portal.dao.InscricaoDAO;
import br.portaldotenis.portal.dao.JogoDeTorneioDAO;
import br.portaldotenis.portal.dao.NivelDAO;
import br.portaldotenis.portal.dao.PontuacaoDAO;
import br.portaldotenis.portal.dao.ProfessorDAO;
import br.portaldotenis.portal.dao.TenistaDAO;
import br.portaldotenis.portal.dao.TorneioDAO;
import br.portaldotenis.portal.exception.EncryptationException;
import br.portaldotenis.portal.model.ConfirmacaoEmail;
import br.portaldotenis.portal.model.Fase;
import br.portaldotenis.portal.model.Filiado;
import br.portaldotenis.portal.model.Nivel;
import br.portaldotenis.portal.model.Pontuacao;
import br.portaldotenis.portal.model.Tenista;
import br.portaldotenis.portal.model.TenistaEmCadastro;
import br.portaldotenis.portal.model.TenistaLogado;
import br.portaldotenis.portal.model.TipoEsporte;
import br.portaldotenis.portal.model.TipoNivel;
import br.portaldotenis.portal.model.TipoUsuario;
import br.portaldotenis.portal.util.EmailAction;
import br.portaldotenis.portal.util.EmailSender;
import br.portaldotenis.portal.util.Encrypt;

/**
 * Controller responsável por atender a todas as requisições de recursos de
 * Tenista
 * 
 * @author Gabriel Soares
 * @author Thiago Holanda
 * 
 */

@Controller
public class TenistaController {

	private static final String CAMPO_NOME = "nome";

	private static final int MAXIMO_ELEMENTOS_PAGINA = 50;

	@Inject
	private Result result;
	@Inject
	private TenistaDAO tenistaDao;
	@Inject
	private Validator validator;
	@Inject
	private TenistaLogado tenistaLogado;
	@Inject
	private ConfirmacaoEmailDAO confEmailDao;
	@Inject
	private EmailSender emailSender;
	@Inject
	private CidadeDAO cidadeDao;
	@Inject
	private ProfessorDAO professorDao;
	@Inject
	private ClubeDAO clubeDao;
	@Inject
	private NivelDAO nivelDao;
	@Inject
	private TenistaEmCadastro tenistaEmCadastro;
	@Inject
	private FaseDAO faseDao;
	@Inject
	private JogoDeTorneioDAO jogoDeTorneioDao;
	@Inject
	private PontuacaoDAO pontuacaoDao;
	@Inject
	private TorneioDAO torneioDao;
	@Inject
	private InscricaoDAO inscricaoDao;	
	@Inject
	private LogadoRule logadoRule;
	@Inject
	private AdministradorRule administradorRule;
	@Inject
	private FiliadoDAO filiadoDao;
	
	
	/* MÉTODOS PRIVADOS */

	/**
	 * Método que gera um captcha
	 * 
	 * @return uma string com um código captcha
	 */
	/*
	 * private String gerarCAPTCHA() { // TODO concluir depois para gerar uma
	 * imagem String a,b,c,d,e; a = ""+(int)Math.round(Math.random()*10); b =
	 * ""+(int) Math.round(Math.random()*10); c = ""+(int)
	 * Math.round(Math.random()*10); d = ""+(int) Math.round(Math.random()*10);
	 * e = ""+(int) Math.round(Math.random()*10); String captcha = a+b+c+d+e;
	 * return captcha; }
	 */

	/* MÉTODOS DE REDIRECIONAMENTO PARA TELAS */

	/**
	 * Método responsável por redirecionar para a tela de login
	 * */
	@Get
	public void login() {
		
		if(tenistaLogado.getTenista() != null){
			result.redirectTo(IndexController.class).paginaInicial();
		}
		
	}

	
	/**
	 * Método responsável por redirecionar para a tela de cadastro de tenista
	 * */

	@Transactional
	@Get("/tenista/formulario-de-cadastro")
	public void formCadastro() {

		// String captcha = gerarCAPTCHA();

		// Atribui uma lista para variáveis utilizadas no formulário
		result.include("cidades", cidadeDao.listaTodos());
		result.include("professores", professorDao.listaTodos(CAMPO_NOME));
		result.include("clubes", clubeDao.listaTodos(CAMPO_NOME));
		result.include("niveisTecnicos", nivelDao.getNiveisPorTipoNivelEsporte(TipoNivel.TECNICO, TipoEsporte.TENNIS));
		result.include("niveisTecnicosBeachTennis", nivelDao.getNiveisPorTipoNivelEsporte(TipoNivel.TECNICO, TipoEsporte.BEACH_TENNIS));

	}

	/**
	 * Método responsável por redirecionar para a tela de edição de tenista
	 * */

	@Transactional
	@Get("/tenista/formulario-edita-tenista/{idTenista}")
	public void formEditaTenista(Long idTenista) {

		if (logadoRule.isAllowed() && administradorRule.isAllowed()) {
			// Atribui uma lista para variáveis utilizadas no formulário
			result.include("tenista", tenistaDao.find(idTenista));
			result.include("cidades", cidadeDao.listaTodos());
			result.include("professores", professorDao.listaTodos(CAMPO_NOME));
			result.include("clubes", clubeDao.listaTodos(CAMPO_NOME));
			result.include("niveisTecnicosBT", nivelDao.getNiveisPorTipoNivelEsporte(TipoNivel.TECNICO, TipoEsporte.BEACH_TENNIS));
			result.include("niveisTecnicos", nivelDao.getNiveisPorTipoNivelEsporte(TipoNivel.TECNICO, TipoEsporte.TENNIS));
		}else {
			result.redirectTo(IndexController.class).mensagemErro(
					"Você precisa ser um administrador para realizar essa tarefa.");
		}

	}
	
	/**
	 * Método responsável por redirecionar para a tela de edição de tenista logado
	 * */

	@Transactional
	@Get("/tenista/formulario-altera-meus-dados")
	public void formEditaTenista() {

	
		
		if (logadoRule.isAllowed()) {
			// Atribui uma lista para variáveis utilizadas no formulário		
			result.include("tenista", tenistaLogado.getTenista());
			result.include("cidades", cidadeDao.listaTodos());
			result.include("professores", professorDao.listaTodos(CAMPO_NOME));
			result.include("clubes", clubeDao.listaTodos(CAMPO_NOME));
			result.include("niveisTecnicosBT", nivelDao.getNiveisPorTipoNivelEsporte(TipoNivel.TECNICO, TipoEsporte.BEACH_TENNIS));
			result.include("niveisTecnicos", nivelDao.getNiveisPorTipoNivelEsporte(TipoNivel.TECNICO, TipoEsporte.TENNIS));
		}else {
			result.redirectTo(IndexController.class).mensagemErro(
					"Você precisa estar logado no sistema para realizar essa tarefa.");
		}

	}
	/**
	 * Método responsável por redirecionar para a tela de alterar senha
	 * */

	@Get("/tenista/formulario-altera-senha")
	public void formAlteraSenha() {

		if (!logadoRule.isAllowed()) {
			result.redirectTo(IndexController.class).mensagemErro(
					"Você precisa estar logado no sistema para realizar essa tarefa.");
		}

	}
	
	/**
	 * Método responsável por redirecionar a solicitação de cadastro de tenista
	 * para a tela de confirma cadastro do tenista
	 * 
	 * */

	@Get("/tenista/confirmacao-de-cadastro")
	public void confirmaCadastro(TenistaEmCadastro tenistaEmCadastro) {
		result.include("tenista", tenistaEmCadastro.getTenista());
	}
	/**
	 * Método responsável por redirecionar para a tela de detalhes do tenista
	 * 
	 * @param idTenista
	 *            identifica qual tenista será detalhado
	 * 
	 * */
	@Get("/tenista/detalhes-do-tenista/idTenista/{idTenista}/acao/{acao}")
	public void detalhaTenista(Long idTenista, Integer acao) {

		
		Tenista tenista = tenistaDao.busca(idTenista);
		result.include("filiado", new Filiado().isFiliado(tenista,null));
		result.include("trofeus1", inscricaoDao.getNumeroDe1Lugar(tenista));
		result.include("trofeus2", inscricaoDao.getNumeroDe2Lugar(tenista));
		result.include("acao", acao);


		switch (acao) {
			case 1 :
				result.include("tenistaDetalhado", tenista);
				break;
			case 2 :
				result.include("pontuacoes", pontuacaoDao.getPontuacoesPorTenista(tenista));
				result.include("tenistaDetalhado", tenista);				
				break;
			case 3 :
				result.include("inscricoes", inscricaoDao.getInscricoesPorTenista(tenista));
				result.include("tenistaDetalhado", tenista);
				break;
			case 4 :
				result.include("torneiosOrganizados", torneioDao.getTorneiosPorOrganizador(tenista));
				result.include("tenistaDetalhado", tenista);
				break;
			case 5 :
				result.include("jogos", jogoDeTorneioDao.getJogosDeTorneioDeUmTenista(tenista));
				result.include("tenistaDetalhado", tenista);
				break;
			default :
				break;
		}

	}
	/**
	 * Método responsável por redirecionar para a tela de detalhes do tenista
	 * 
	 * @param idTenista
	 *            identifica qual tenista será detalhado
	 * 
	 * */
	@Get("/tenista/detalhes-do-tenista/{idTenista}")
	public void detalhaTenistaOld(Long idTenista) {

		
		Tenista tenista = tenistaDao.busca(idTenista);
		result.include("trofeus1", inscricaoDao.getNumeroDe1Lugar(tenista));
		result.include("trofeus2", inscricaoDao.getNumeroDe2Lugar(tenista));



		result.include("tenistaDetalhado", tenista);
	
		result.include("pontuacoes", pontuacaoDao.getPontuacoesPorTenista(tenista));
	
		result.include("inscricoes", inscricaoDao.getInscricoesPorTenista(tenista));
	
		result.include("torneiosOrganizados", torneioDao.getTorneiosPorOrganizador(tenista));
	
		result.include("jogos", jogoDeTorneioDao.getJogosDeTorneioDeUmTenista(tenista));
	


	}
	/**
	 * Método responsável por redirecionar para a tela de pontuações do tenista
	 * 
	 * @param idTenista
	 *            identifica qual tenista será detalhado
	 * 
	 * */
	@Get("/tenista/pontos-do-tenista/{idTenista}")
	public void pontuacoesDoTenista(Long idTenista) {

		Tenista tenista = tenistaDao.busca(idTenista);

		result.include("pontuacoes", pontuacaoDao.getPontuacoesPorTenista(tenista));


	}
	/**
	 * Método responsável por redirecionar para a tela de pontuações do tenista
	 * 
	 * @param idTenista
	 *            identifica qual tenista será detalhado
	 * 
	 * */
	@Get("/tenista/meus-pontos")
	public void visualizaMeusPontos() {

		Tenista tenista;
		if(logadoRule.isAllowed()){

			tenista = tenistaLogado.getTenista();
			result.include("pontuacoes", pontuacaoDao.getPontuacoesPorTenista(tenista));
			
		}else{

			result.redirectTo(IndexController.class).mensagemErro("Você precisa estar logado no sistema.");

		}



	}
	/**
	 * Método responsável por redirecionar para a tela de inscrições do tenista
	 * 
	 * @param idTenista
	 *            identifica qual tenista será detalhado
	 * 
	 * */
	@Get("/tenista/inscricoes-do-tenista/{idTenista}")
	public void inscricoesDoTenista(Long idTenista) {

		Tenista tenista = tenistaDao.busca(idTenista);

		result.include("inscricoes", inscricaoDao.getInscricoesPorTenista(tenista));


	}
	/**
	 * Método responsável por redirecionar para a tela de torneios organizados pelo tenista
	 * 
	 * @param idTenista
	 *            identifica qual tenista será detalhado
	 * 
	 * */
	@Get("/tenista/inscricoes-do-tenista/{idTenista}")
	public void torneiosDoTenista(Long idTenista) {

		Tenista tenista = tenistaDao.busca(idTenista);

		result.include("torneiosOrganizados", torneioDao.getTorneiosPorOrganizador(tenista));


	}
	/**
	 * Método responsável por redirecionar para a tela de jogos do tenista
	 * 
	 * @param idTenista
	 *            identifica qual tenista será detalhado
	 * 
	 * */
	@Get("/tenista/inscricoes-do-tenista/{idTenista}")
	public void jogosDoTenista(Long idTenista) {

		Tenista tenista = tenistaDao.busca(idTenista);

		result.include("jogos", jogoDeTorneioDao.getJogosDeTorneioDeUmTenista(tenista));


	}
	/** visualiza os filiados */
	@Get("/tenista/visualiza-filiados")
	public void visualizaFiliados() {
			
			int anoAtual = Calendar.getInstance().get(Calendar.YEAR);
			double somaBruta = 0;
			double somaLiquida = 0;
			
			List<Filiado> filiados = filiadoDao.getFiliadosPorAno(anoAtual);
			for (Filiado filiado : filiados) {
				somaBruta += filiado.getValorPago()!=null?filiado.getValorPago():0;
				somaLiquida += filiado.getValorLiquido()!=null?filiado.getValorLiquido():0; 
			}
			result.include("tenistasFiliados", filiados );
			result.include("quantidade", filiados.size());
			result.include("somaBruta", somaBruta);
			result.include("somaLiquida", somaLiquida);
	
	}

	/**
	 * Verifica se o código de recuperação de senha existe no BD, se exister vai
	 * para o formulário, se não erro 404
	 * 
	 * @param codigo
	 *            de recuperação recebido pela URL
	 */

	@Get("/tenista/recupera-senha/{codigo}")
	@Transactional
	public void formRecuperaSenha(String codigo) {
		ConfirmacaoEmail confirmacaoEmail = confEmailDao.busca(codigo);
		if (confirmacaoEmail == null) {
			result.use(Results.status()).notFound();
			return;
		} else if (confirmacaoEmail.getEmailAction() != EmailAction.RECUPERAR_SENHA) {
			result.use(Results.status()).notFound();
			return;
		}
		result.include("tenista", confirmacaoEmail.getTenista());
		result.include("codigo", codigo);
	}

	@Get("/tenista/recupera-senha")
	public void formRecuperaSenha() {

	}

	/**
	 * Método que redicreciona para a tela de procurar tenistas
	 * */
	@Get("/tenista/procura-tenistas")
	public void formProcuraTenistas() {
		List<Nivel> niveis = nivelDao.getNiveisPorTipo(TipoNivel.TECNICO);

		result.include("niveisTecnicos", niveis);
		
	}
	/* MÉTODOS DE AÇÃO DAS FUNÇÕES DO SISTEMA */

	/**
	 * Recebe uma requisição POST para logar o tenista
	 * 
	 * @param tenista
	 *            com Email e Senha populados, vindo do formulário de login
	 * @throws EncryptationException
	 * 
	 */

	@Transactional
	@Post("/tenista/login")
	public void logaTenista(Tenista tenista, String urlRequisitada) throws EncryptationException {
		tenista.setEmail(tenista.getEmail().toLowerCase());
		tenista.setSenha(Encrypt.toMD5(tenista.getSenha()));
		tenista = tenistaDao.login(tenista);
		validator.addIf(tenista == null, new SimpleMessage("Erro", "Email ou senha incorretos"));

		if (tenista != null) {
			validator.addIf(tenista.isEmailConfirmado() == false, new SimpleMessage("Erro",
					"Você precisa confirmar seu cadastro, acesse seu e-mail e clique no link de confirmação "
							+ " que enviamos para você. Em caso de dúvidas, entre em contato conosco."));
		}
		validator.onErrorRedirectTo(this).login();

		tenistaLogado.loga(tenista);

		if(urlRequisitada == null){
			result.redirectTo(IndexController.class).paginaInicial();
		}else{
			result.redirectTo(urlRequisitada);
		}
	}

	/**
	 * Método que retira o tenista logado da sessão
	 * */
	@Get("/tenista/logout")
	public void logout() {
		tenistaLogado.desloga();
		result.redirectTo(IndexController.class).index();
	}

	/**
	 * Recebe dados de um tenista pelo formulário de cadastro; Valida os dados
	 * necessários; Redireciona para confirmação de cadastro
	 * 
	 * @param tenista
	 *            já populado com os atributos do formulário
	 * @param senha1
	 *            informada pelo usuario, que será validada com senha2
	 * @param senha2
	 *            informada pelo usuario, que será validada com senha1
	 * @param verificacaoEmail
	 *            informado pelo usuário, que será validada com o email já
	 *            populado em tenista
	 * @throws EncryptationException
	 */

	@Transactional
	@Post("/tenista/formulario-de-cadastro")
	public void solicitaCadastroTenista(Tenista tenista, String senha1, String senha2, String verificacaoEmail)
			throws EncryptationException {

		// Atribui alguns atributos ao tenista
		tenista.setEmail(tenista.getEmail().toLowerCase().trim());
		tenista.setSenhaLimpa(senha1);
		tenista.setSenha(senha1);
		tenista.setClube(null);
		tenista.setProfessor(null);
		tenista.setEndereco(null);
		tenista.setNivelTecnicoPrincipal(nivelDao.busca(tenista.getNivelTecnicoPrincipal().getId()));
		tenista.setNivelTecnicoBeachTennis(nivelDao.busca(tenista.getNivelTecnicoBeachTennis().getId()));
		tenista.setTipo(TipoUsuario.PADRAO);
		tenista.setPontosCategoriaPrincipal(0);
		tenista.setPontosCategoriaBeachTennis(0);
		tenista.setDataAtualizacao(Calendar.getInstance());
		

		// Verifica se é um administrador ou organizador, se for não precisa
		// confirmar cadastro, é automático
		if (tenistaLogado.getTenista() != null && tenistaLogado.getTenista().getTipo() == TipoUsuario.ADMIN) {
			tenista.setConfirmado(true);
			tenista.setEmailConfirmado(true);
		}else{
			tenista.setAtivo(true);
			tenista.setParticipaDeRanking(true);
			tenista.setConfirmado(false);
			tenista.setVisitante(false);
			tenista.setEmailConfirmado(false);
			tenista.setBolsista(false);
			tenista.setNaoPossuiCpf(false);
		}
		// Validações::
		validator.addIf(tenistaDao.existeTenistaComLogin(tenista.getEmail()), new SimpleMessage("tenista.email",
				"Já existe um Usuário com o Email informado"));

		validator.addIf(tenistaDao.existeTenistaComCpf(tenista.getCpf()), new SimpleMessage("tenista.cpf",
				"Já existe um Usuário com o CPF informado"));

		validator.addIf(tenistaDao.existeTenistaComNomeRanking(tenista.getNomeRanking()), new SimpleMessage(
				"tenista.nomeRanking", "Já existe um Usuário com o nome do ranking informado"));

		validator.addIf(tenista.getDataNascimento() == null && tenista.naoPossuiCpf(), new SimpleMessage(
				"tenista.data", "Informe a sua data de nascimento"));

		// Retirado porque não tem o sobrenome
		/*validator.addIf(tenista.getNome().split(" ").length > 2, new SimpleMessage("tenista.nome",
				"No campo nome deve ser informado apenas o seu nome sem sobrenome"));*/

		/*boolean ehNumero;
		try {
		    Double valor = (Double.parseDouble(tenista.getTelefonePrincipal().getNumero()));
	        ehNumero = true;
		} catch (NumberFormatException e) {	  
	         ehNumero = false;
		}
		
		if(!ehNumero){
			validator.add(new SimpleMessage(br.portaldotenis.portal.util.Utils.ESPORTISTA+".telefonePrincipal.numero", "O telefone só deve conter números"));
		}*/

		
		if (senha1 != null) {
			if (!senha1.equals(senha2)) {
				validator.add(new SimpleMessage(br.portaldotenis.portal.util.Utils.ESPORTISTA+".senha1", "As senhas não conferem"));
			} else if (senha1.length() < 7) {
				validator.add(new SimpleMessage(br.portaldotenis.portal.util.Utils.ESPORTISTA+".senha1", "A senha deve conter no minimo 7 caracteres"));
			}
		}

		// Encripta a senha
		tenista.setSenha(Encrypt.toMD5(senha1));

		if (tenista.getEmail() != null) {
			validator.addIf(!tenista.getEmail().equals(verificacaoEmail), new SimpleMessage(br.portaldotenis.portal.util.Utils.ESPORTISTA+".email",
					"Os emails não conferem"));
		}

		// Tratamento da data de nascimento com o CPF
		if (tenista.getDataNascimento() != null) {

			tenista.getDataNascimento().setLenient(false);
			validator.addIf(tenista.getDataNascimento().after(Calendar.getInstance()), new SimpleMessage(
					"tenista.data", "Data inválida"));
			if (tenista.naoPossuiCpf() && tenista.isMaiorIdade()) {
				validator.add(new SimpleMessage(br.portaldotenis.portal.util.Utils.ESPORTISTA+".cpf", "Você é maior de idade, precisa informar o seu CPF"));
			}

		}

		// Tratamento do nível técnico principal
		if ((tenista.getNivelTecnicoPrincipal() == null) || (tenista.getNivelTecnicoBeachTennis() == null) ) {

			validator.add(new SimpleMessage(br.portaldotenis.portal.util.Utils.ESPORTISTA+".nivel", "É necessário informar o seu nível técnico para o Beach Tennis e para o Tênis. Informe iniciante se não for praticante de algum desses esportes."));

		} else {

			Nivel nivel = nivelDao.busca(tenista.getNivelTecnicoPrincipal().getId());

			// Verifica se o tenista do nível infantil tem idade adequada

			if (nivel.getTipoNivel() == TipoNivel.TECNICO && nivel.getCodigo() == Nivel.INFANTIL) {
				validator.addIf(tenista.isMaiorIdade(), new SimpleMessage(br.portaldotenis.portal.util.Utils.ESPORTISTA+".nivel",
						"Você não tem idade para ser do nível infantil"));
			}

			// Verifica se o tenista estreante tem mais de um ano

			if (nivel.getTipoNivel() == TipoNivel.TECNICO && nivel.getCodigo() == Nivel.INICIANTE) {

				if (tenista.getInicioNoTenis() != null) {

					int tempoDeTenis = tenista.getTempoDeTenis();
					if (tempoDeTenis >= 2) {
						Nivel quintaClasse = nivelDao.getNivelPorDescricao(Nivel.QUINTA_CLASSE_DESCRICAO);
						tenista.setNivelTecnicoPrincipal(quintaClasse);
					}

				}
			}
		}

		validator.validate(tenista);

		validator.onErrorRedirectTo(this).formCadastro();
		// Fim das Validações

		// Enviar para tela de confirmação do cadastro
		tenistaEmCadastro.setTenista(tenista);
		result.forwardTo(this).confirmaCadastro(tenistaEmCadastro);

	}

	
	/**
	 * Método responsável por enviar o tenista solicitado para ser gravado no
	 * banco de dados e enviar um Email para o Tenista confirmar o cadastro ou
	 * Email já confirmando o cadastro;
	 * 
	 * */
	@Transactional
	@Post("/tenista/confirmacao-de-cadastro")
	public void confirmaCadastroTenista() {

		Tenista tenista = tenistaEmCadastro.getTenista();
		
		tenistaDao.adiciona(tenista);

		emailSender.confirmaEmail(tenista);

		if (tenista.naoPossuiCpf() && !tenista.isEmailConfirmado()) {
			result.redirectTo(IndexController.class).mensagem(
					"O seu cadastro foi realizado, mas está pendente porque ainda não possui CPF, "
							+ "entre em contato para confirmar suas informações.");
			return;
		} else {

			if (!tenista.isEmailConfirmado()) {

				result.redirectTo(IndexController.class).mensagem(
						"Enviamos um e-mail com um link para confirmar o seu cadastro,"
								+ " acesse a sua caixa de e-mail e clique no link.\n "
								+ "Caso não tenha recebido, verifique a sua caixa de spam. "
								+ "Se ainda não estiver recebido, entre em contato conosco: "
								+ br.portaldotenis.portal.util.Utils.EMPRESA + " - " + br.portaldotenis.portal.util.Utils.TELEFONE);
			} else {

				result.redirectTo(IndexController.class).mensagem(
						"Enviamos um e-mail para o tenista confirmando o cadastro dele. Qualquer dúvida entra em contato conosco por:"
								+ br.portaldotenis.portal.util.Utils.EMPRESA + " - " + br.portaldotenis.portal.util.Utils.TELEFONE);

			}
		}

	}

	@Transactional
	@Post("/tenista/edicao-de-tenista")
	public void salvarDadosTenista(Tenista tenista, String verificacaoEmail, boolean mudarNivel, boolean mudarNivelBT)
			throws EncryptationException {

		try{
		// Atribui alguns atributos ao tenista
		
		//Tenista tenistaAtualizado = tenistaDao.find(tenista.getId());
		Tenista tenistaLegado = tenistaDao.find(tenista.getId());		
		tenista.setEmail(tenista.getEmail().toLowerCase().trim());
		
		tenista.setClube(clubeDao.busca(tenista.getClube().getId()));
		tenista.setProfessor(professorDao.busca(tenista.getProfessor().getId()));
		tenista.getEndereco().setCidade(cidadeDao.busca(tenista.getEndereco().getCidade().getId()));

		//atributos sem uso
		tenista.setApelido(tenistaLegado.getApelido());
		tenista.setFoto(tenistaLegado.getFoto());
		tenista.setLocais(tenistaLegado.getLocais());
		
		// atributos que são alterados por outro processo
		tenista.setPontosCategoriaPrincipal(tenistaLegado.getPontosCategoriaPrincipal());
		tenista.setSenha(tenistaLegado.getSenha());
		tenista.setDataAtualizacao(Calendar.getInstance());

		// verifica se é um administrador logado para alterar o tipo do usuario
		if(!tenistaLogado.isAdmin()){
			tenista.setTipo(tenistaLegado.getTipo());
			tenista.setEmailConfirmado(tenistaLegado.isEmailConfirmado());
			tenista.setParticipaDeRanking(tenistaLegado.isParticipaDeRanking());
			tenista.setAtivo(tenistaLegado.isAtivo());
			tenista.setVisitante(tenista.isVisitante());
			tenista.setBolsista(tenistaLegado.isBolsista());
			tenista.setEmail(tenistaLegado.getEmail());
			tenista.setNivelTecnicoPrincipal(tenistaLegado.getNivelTecnicoPrincipal());
			tenista.setConfirmado(tenistaLegado.isConfirmado());
			tenista.setFiliado(tenistaLegado.isFiliado());
			tenista.setNome(tenistaLegado.getNome());
			tenista.setSobreNome(tenistaLegado.getSobreNome());
			tenista.setNomeRanking(tenistaLegado.getNomeRanking());
		}
		


		// Validações::
		validator.addIf(tenista.getDataNascimento() == null && tenista.naoPossuiCpf(), new SimpleMessage(
				br.portaldotenis.portal.util.Utils.ESPORTISTA+".dataNascimento", "Informe a data de nascimento"));

		validator.addIf(tenista.getNome().split(" ").length > 2, new SimpleMessage(br.portaldotenis.portal.util.Utils.ESPORTISTA+".nome",
				"No campo nome deve ser informado apenas o seu nome sem sobrenome"));

		if (tenista.getEmail() != null) {
			validator.addIf(!tenista.getEmail().equals(verificacaoEmail), new SimpleMessage(br.portaldotenis.portal.util.Utils.ESPORTISTA+".email",
					"Os emails não conferem"));
		}

	
		// Tratamento da data de nascimento com o CPF
		if (tenista.getDataNascimento() != null) {

			tenista.getDataNascimento().setLenient(false);
			validator.addIf(tenista.getDataNascimento().after(Calendar.getInstance()), new SimpleMessage(
					br.portaldotenis.portal.util.Utils.ESPORTISTA+".data", "Data inválida"));
			if (tenista.naoPossuiCpf() && tenista.isMaiorIdade()) {
				validator.add(new SimpleMessage(br.portaldotenis.portal.util.Utils.ESPORTISTA+".cpf", "O "+ br.portaldotenis.portal.util.Utils.ESPORTISTA + " é maior de idade, e precisa informar o seu CPF"));
			}

		}
		if(mudarNivel){
			
			//configura o novo nivel tecnico do tenista
			tenista.setNivelTecnicoPrincipal(nivelDao.busca(tenista.getNivelTecnicoPrincipal().getId()));
			//obtem a pontuacao dele nesse nivel tecnico
			Pontuacao pontuacao = pontuacaoDao.getPontuacao(tenista, tenista.getNivelTecnicoPrincipal(), Calendar.getInstance().get(Calendar.YEAR));
			//altera os pontos do tenista configurando o valor para o novo nível técnico definido
			if(pontuacao == null){
				pontuacao = new Pontuacao();
			}
			tenista.setPontosCategoriaPrincipal(pontuacao.getPontos());
		}else{
			tenista.setNivelTecnicoPrincipal(tenistaLegado.getNivelTecnicoPrincipal());			
		}

		if(mudarNivelBT){
			
			//configura o novo nivel tecnico de BT do tenista
			tenista.setNivelTecnicoBeachTennis(nivelDao.busca(tenista.getNivelTecnicoBeachTennis().getId()));
			//obtem a pontuacao dele nesse nivel tecnico de BT
			Pontuacao pontuacao = pontuacaoDao.getPontuacao(tenista, tenista.getNivelTecnicoBeachTennis(), Calendar.getInstance().get(Calendar.YEAR));
			//altera os pontos do tenista configurando o valor para o novo nível técnico de BT definido 
			if(pontuacao == null){
				pontuacao = new Pontuacao();
			}
			tenista.setPontosCategoriaBeachTennis(pontuacao.getPontos());
		}else{
			tenista.setNivelTecnicoBeachTennis(tenistaLegado.getNivelTecnicoBeachTennis());			
		}

		
		validator.validate(tenista);

		validator.onErrorRedirectTo(this).formEditaTenista(tenista.getId());
		
		// Fim das Validações

		
		
		tenistaDao.atualiza(tenista);
		// verifica se o tenista logado é o tenista que está sendo editado
		if(tenistaLogado.getTenista().equals(tenista)){
			// atribui o tenista atualizado na sessão
			tenistaLogado.setTenista(tenista);
		}
		result.forwardTo(IndexController.class).mensagem(" Tenista atualizado com sucesso!");
		
		}catch(Exception e){
			
			result.forwardTo(IndexController.class).mensagemErro("Ocorreu um erro inesperado. Talvez o CPF, Nome no ranking ou e-mail já exista no banco de dados:" + e.getMessage());

		}

	}
	public List<Pontuacao> obterRankingDeTenistasPorNivel(int idNivel, int ano) {
		pontuacaoDao = new PontuacaoDAO();
		return pontuacaoDao.getRankingDeTenistasPorNivel(idNivel, ano);

	}

	/**
	 * Buscas os tenistas que estão inscritos no torneio e naquele nível
	 * escolhido
	 * 
	 * @param idTorneio
	 *            identifica o torneio
	 * @param idNivel
	 *            identifica o nível
	 * 
	 * */
	@Transactional
	@Post("/tenista/insere-jogo")
	public void buscaTenistasDoNivelNoTorneio(Long idTorneio, Integer idNivel) {

		List<Tenista> tenistas = tenistaDao.getTenistasInscritosConfirmadosNoTorneioPorNivel(idNivel, idTorneio);
		result.include("tenistasInscritos", tenistas);

		// prepara as listas para serem apresentadas no formulário de jogos dos
		// torneios
		result.include("fases", faseDao.listaTodos(Fase.DESCRICAO));
		result.include("nivel", nivelDao.busca(idNivel));
		result.include("jogos", jogoDeTorneioDao.getJogosNoTorneioPorNivel(idNivel, idTorneio));

		result.redirectTo(JogoDeTorneioController.class).formGerenciaJogo(idTorneio);

	}

	/**
	 * Buscas os tenistas que estão inscritos no torneio e naquele nível
	 * escolhido
	 * 
	 * @param idTorneio
	 *            identifica o torneio
	 * @param idNivel
	 *            identifica o nível
	 * 
	 * */
	@Transactional
	@Post("/tenista/busca-tenistas")
	public void buscaTenistas(String nome, Integer idNivel, Integer numero) {

		int numeroDeTenistas = 0;
		//int numeroDePaginas = 0;
		int primeiro = 0;
		int anterior = 0;
		int proximo = 0;
		List<Tenista> tenistas;
		//List<String> paginas = new ArrayList<String>();
		
		if (numero == null || numero == 0){
			numero = 1;
			anterior = 0;
			proximo = 2;			
		}else{
			anterior = numero - 1;
			proximo = numero +1;
		}
		primeiro = (numero - 1) * MAXIMO_ELEMENTOS_PAGINA;
		
		if (nome != null && !nome.equals("") ) {
			if (idNivel != 0) {
				tenistas = tenistaDao.getTenistasPorNivelTecnicoENome(idNivel, nome, primeiro, MAXIMO_ELEMENTOS_PAGINA);
			} else {
				tenistas = tenistaDao.getTenistasPorNome(nome, primeiro, MAXIMO_ELEMENTOS_PAGINA);
			}

		} else {
			if (idNivel != 0) {
				tenistas = tenistaDao.getTenistasPorNivelTecnico(idNivel, primeiro, MAXIMO_ELEMENTOS_PAGINA);
			} else {
				tenistas = tenistaDao.getTenistasAtivos(primeiro, MAXIMO_ELEMENTOS_PAGINA);
			}
		}

		numeroDeTenistas = tenistas.size();
		//numeroDePaginas = Math.round(numeroDeTenistas / MAXIMO_ELEMENTOS_PAGINA);
		
		/*for(int i=1; i<=numeroDePaginas; i++){
			paginas.add(""+i);
		}*/
		
		result.include("tenistasEncontrados", tenistas);
		result.include("numeroDeTenistas", numeroDeTenistas);
		//result.include("numeroDePaginas", numeroDePaginas);
		//result.include("paginas", paginas);
		result.include("nomeTenista", nome);
		result.include("nivelTenista", idNivel);
		result.include("anterior", anterior);
		result.include("proximo", proximo);
		result.include("numeroElementosMaximo", MAXIMO_ELEMENTOS_PAGINA);
		result.redirectTo(TenistaController.class).formProcuraTenistas();

	}

	/**
	 * Altera a senha do tenista
	 * @throws EncryptationException 
	 * 
	 * 
	 * */
	@Transactional
	@Post("/tenista/form-altera-senha")
	public void alteraSenha(String senhaAtual, String novaSenha, String confirmacaoNovaSenha) throws EncryptationException {
		

		Tenista tenistaOnline = this.tenistaLogado.getTenista();
		Tenista tenista = tenistaDao.find(tenistaOnline.getId());

		try {
			validator.addIf(senhaAtual == null || novaSenha == null || confirmacaoNovaSenha == null, new SimpleMessage(br.portaldotenis.portal.util.Utils.ESPORTISTA+".senha",
					"Informe a senha atual, a sua nova senha e a confirmação de nova senha"));

			validator.addIf(senhaAtual.length() < 7 || novaSenha.length() < 7 || confirmacaoNovaSenha.length() < 7, new SimpleMessage(
					br.portaldotenis.portal.util.Utils.ESPORTISTA + ".senha", "A sua senha deve ter no mínimo 7 digitos"));

			validator.addIf(!Encrypt.toMD5(senhaAtual).equals(tenistaOnline.getSenha()), new SimpleMessage(br.portaldotenis.portal.util.Utils.ESPORTISTA+".senhaAtual",
					"A senha atual informada não está correta"));

			validator.addIf(!novaSenha.equals(confirmacaoNovaSenha), new SimpleMessage(br.portaldotenis.portal.util.Utils.ESPORTISTA+".senha", "A senha nova não confere"));

			validator.validate(tenista);

			validator.onErrorRedirectTo(this).formAlteraSenha();
			
			tenista.setSenha(Encrypt.toMD5(novaSenha));
			tenistaDao.atualiza(tenista);
			result.forwardTo(IndexController.class).mensagem("A sua senha foi atualizada com sucesso");
		} catch (Exception e) {
		}

	}

	/**
	 * Confirma o cadastro do Tenista, através do código enviado por Email para
	 * o mesmo; Caso o código não seja válido, é retornado o erro 404
	 * 
	 * @param codigo
	 *            que foi enviado por Email e é recebido através da URL
	 */

	@Get("/tenista/confirma-email/{codigo}")
	@Transactional
	public void confirmaEmail(String codigo) {
		ConfirmacaoEmail confirmacaoEmail = confEmailDao.busca(codigo);
		if (confirmacaoEmail == null) {
			result.use(Results.http()).sendError(404, "Código de confirmação de Email inválido.");
			return;
		} else if (confirmacaoEmail.getEmailAction() != EmailAction.CONFIRMAR) {
			result.use(Results.status()).notFound();
			return;
		}

		confirmacaoEmail.getTenista().setEmailConfirmado(true);
		tenistaDao.atualiza(confirmacaoEmail.getTenista());
		confEmailDao.remove(confirmacaoEmail);

		result.forwardTo(IndexController.class).mensagem(
				"O seu cadastro foi confirmado, " + "acesse a sua conta e fique por dentro de todas as informações");
	}

	/**
	 * Envia link de recuperação de senha ao usuario
	 * 
	 * @param email
	 *            vindo do formulario de solicitação de recuperação
	 */

	@Post("/tenista/recupera-senha")
	@Transactional
	public void processaRecuperacaoSenha(String email) {
		Tenista tenista = tenistaDao.getTenistaPorEmail(email);

		validator.addIf(tenista == null, new SimpleMessage("E-mail",
				"O e-mail informado não pertence a nenhum Usuário cadastrado. "
						+ "Digite o e-mail utilizado no seu cadastro."));

		validator.onErrorRedirectTo(this).formRecuperaSenha();

		emailSender.recuperaSenha(tenista);

		result.redirectTo(IndexController.class)
				.mensagem(
						"Pronto. Agora acesse a sua caixa de entrada, "
								+ "abra o e-mail enviado com o título \"Recuperação de Senha\" do "+ br.portaldotenis.portal.util.Utils.EMPRESA + " , e siga as instruções.  <br>"
								+ "Caso tenha certeza que o e-mail não chegou, entre em contato com a administração " + br.portaldotenis.portal.util.Utils.TELEFONE
								+ "ou " + br.portaldotenis.portal.util.Utils.EMAIL);
	}

	/**
	 * Realiza a alteração da senha do usuario, após todas as etapas necessárias
	 * para recuperação de senha.
	 * 
	 * @param senha
	 *            que o usuario preencheu no formulario, vai se tornar sua nova
	 *            senha
	 * @param confirmacaoSenha
	 *            para confirmar a senha
	 * @param codigo
	 *            de solicitação de alteração de senha
	 * @throws EncryptationException
	 */

	@Patch("/tenista/recupera-senha/{codigo}")
	@Transactional
	public void recuperaSenhaNova(String codigo, String senha, String confirmacaoSenha) throws EncryptationException {
		if (senha == null) {
			validator.add(new SimpleMessage("Senha", "Você precisa informar a senha"));
		} else if (!senha.equals(confirmacaoSenha)) {
			validator.add(new SimpleMessage("Confirmação de senha", "As senhas não correspondem"));
		} else if (senha.length() < 7) {
			validator.add(new SimpleMessage("Senha", "A senha precisa ter pelo menos 7 caracteres"));
		}
		validator.onErrorRedirectTo(this).formRecuperaSenha(codigo);

		ConfirmacaoEmail confirmacaoEmail = confEmailDao.busca(codigo);
		Tenista tenista = confirmacaoEmail.getTenista();
		confEmailDao.remove(confirmacaoEmail);

		tenista.setSenha(Encrypt.toMD5(senha));
		tenistaDao.atualiza(tenista);

		result.redirectTo(IndexController.class).mensagem("Senha alterada com sucesso.");

	}


	public boolean isFiliado(Tenista tenista, int anoReferencia) {


		boolean existeFiliacao = new FiliadoDAO().isFiliado(tenista, anoReferencia);
		
		return existeFiliacao;
	}


	public Tenista buscaTenistaPorId(int tenistaId) {
		Tenista tenista = tenistaDao.busca(tenistaId);
		return tenista;
	}

}
