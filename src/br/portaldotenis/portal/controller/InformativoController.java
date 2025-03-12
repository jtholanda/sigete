package br.portaldotenis.portal.controller;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.observer.upload.UploadedFile;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.portaldotenis.portal.controleacesso.LogadoRule;
import br.portaldotenis.portal.dao.InformativoDAO;
import br.portaldotenis.portal.dao.MidiaInformativoDAO;
import br.portaldotenis.portal.model.Informativo;
import br.portaldotenis.portal.model.MidiaInformativo;
import br.portaldotenis.portal.model.Tenista;
import br.portaldotenis.portal.model.TenistaLogado;
import br.portaldotenis.portal.model.TipoInformativo;
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
public class InformativoController {

	private static final int QUANTIDADE_CARACTERES_MINIMO_TITULO = 16;
	private static final int QUANTIDADE_CARACTERES_MAXIMO_TITULO = 128;
	private static final int QUANTIDADE_CARACTERES_MINIMO_RESUMO = 64;
	private static final int QUANTIDADE_CARACTERES_MAXIMO_RESUMO = 256;
	
	@Inject
	private Result result;
	@Inject
	private LogadoRule logadoRule;
	@Inject
	private LogadoRule organizadorRule;	
	@Inject
	private Validator validator;
	@Inject
	private TenistaLogado tenistaLogado;
	@Inject
	private InformativoDAO informativoDao;
	@Inject
	private MidiaInformativoDAO midiaInformativoDao;

	/**
	 * Método que apresenta o formulário de cadastro de notícias
	 * */

	@Get("/informativo/form-cadastra-informativo")
	public void formCadastraInformativo() {
		if (!(logadoRule.isAllowed() && organizadorRule.isAllowed())) {
			result.redirectTo(IndexController.class)
					.mensagemErro(
							"Você precisa ser um usuário organizador de torneios para realizar essa tarefa.");
		}
		result.include("tiposDeInformativos", TipoInformativo.values());


	}
	/**
	 * Método que apresenta o formulário de cadastro de mídias do informativo
	 * */

	@Get("/informativo/form-envia-midia-informativo")
	public void formMidiaInformativo(Informativo informativo) {
		if (!(logadoRule.isAllowed() && organizadorRule.isAllowed())) {
			result.redirectTo(IndexController.class)
					.mensagemErro(
							"Você precisa ser um usuário organizador de torneios para realizar essa tarefa.");
		}
		result.include("informativo", informativo);
	}
	/**
	 * Método responsável por redirecionar para a tela de edição do informativo
	 * */

	@Transactional
	@Get("/informativo/formulario-edita-informativo/{idInformativo}")
	public void formEditaInformativo(Long idInformativo) {

		if (logadoRule.isAllowed() && organizadorRule.isAllowed()) {
			// Atribui uma lista para variáveis utilizadas no formulário
			result.include("informativo", informativoDao.find(idInformativo));
			result.include("tiposDeInformativos", TipoInformativo.values());
		}else {
			result.redirectTo(IndexController.class).mensagemErro(
					"Você precisa ser um usuário organizador de torneios para realizar essa tarefa.");
		}

	}
	
	/** redireciona para o formulário de midias do informativo */
	@Get("/informativo/adiciona-midia-informativo/{idInformativo}")
	public void adicionaMidia(Long idInformativo){
		
		Informativo informativo = informativoDao.busca(idInformativo);
		result.redirectTo(this).formMidiaInformativo(informativo);
		
	}
	
	
	/** visualiza as notícias locais */
	@Get("/informativo/visualiza-noticias-locais")
	public void visualizaNoticiasLocais() {
			
			
			List<Informativo> noticiasLocais = informativoDao.getInformativosPorTipo(TipoInformativo.LOCAL);
			result.include("informativos", noticiasLocais);
	
	}
	/** visualiza as notícias internacionais */
	@Get("/informativo/visualiza-noticias-internacionais")
	public void visualizaNoticiasInternacionais() {
			
			
			List<Informativo> noticiasInternacionais = informativoDao.getInformativosPorTipo(TipoInformativo.INTERNACIONAL);
			result.include("informativos", noticiasInternacionais);
	
	}
	/** visualiza dicas */
	@Get("/informativo/visualiza-dicas")
	public void visualizaDicas() {
			
			
			List<Informativo> dicas = informativoDao.getInformativosPorTipo(TipoInformativo.DICA);
			result.include("informativos", dicas);
	
	}

	/** gerencia as notícias locais */
	@Get("/informativo/gerencia-noticias-locais")
	public void gerenciaNoticiasLocais() {
			
			
		List<Informativo> noticiasLocais;
		
		if(tenistaLogado.isAdmin()){
			noticiasLocais = informativoDao.getInformativosPorTipo(TipoInformativo.LOCAL);
		}else{
			noticiasLocais = informativoDao.getInformativosPorTipoEProprietario(TipoInformativo.LOCAL, tenistaLogado.getTenista());				
		}
		result.include("informativos", noticiasLocais);
	
	}
	/** gerencia as notícias internacionais */
	@Get("/informativo/gerencia-noticias-internacionais")
	public void gerenciaNoticiasInternacionais() {
			
			
		List<Informativo> noticiasInternacionais;
		
		if(tenistaLogado.isAdmin()){
			noticiasInternacionais = informativoDao.getInformativosPorTipo(TipoInformativo.INTERNACIONAL);
		}else{
			noticiasInternacionais = informativoDao.getInformativosPorTipoEProprietario(TipoInformativo.INTERNACIONAL, tenistaLogado.getTenista());				
		}
		result.include("informativos", noticiasInternacionais);
		
	}
	/** gerencia dicas */
	@Get("/informativo/gerencia-dicas")
	public void gerenciaDicas() {
			
			List<Informativo> dicas;
		
			if(tenistaLogado.isAdmin()){
				dicas = informativoDao.getInformativosPorTipo(TipoInformativo.DICA);
			}else{
				dicas = informativoDao.getInformativosPorTipoEProprietario(TipoInformativo.DICA, tenistaLogado.getTenista());				
			}
			result.include("informativos", dicas);
	
	}
	
	
	
	
	/**
	 * Método que confirma o cadastro de informativos
	 * */
	@Transactional
	@Post("/informativo/form-cadastro-informativo")
	public void confirmaCadastroInformativo(Informativo informativo) {

		if(informativo.getTitulo().length() < QUANTIDADE_CARACTERES_MINIMO_TITULO){
			validator.add(new SimpleMessage("informativo.titulo",
					"O título deve ter entre " + QUANTIDADE_CARACTERES_MINIMO_TITULO + " e " + QUANTIDADE_CARACTERES_MAXIMO_TITULO + " caracteres"));
		}

		if(informativo.getResumo().length() < QUANTIDADE_CARACTERES_MINIMO_RESUMO){
			validator.add(new SimpleMessage("informativo.resumo",
					"O resumo deve ter entre " + QUANTIDADE_CARACTERES_MINIMO_RESUMO + " e " + QUANTIDADE_CARACTERES_MAXIMO_RESUMO + " caracteres"));
		}

		validator.onErrorRedirectTo(this).formCadastraInformativo();


		if(tenistaLogado != null && tenistaLogado.isOrganizador() ){
			Calendar dataAtual = Calendar.getInstance();
			Tenista tenista = tenistaLogado.getTenista();
			informativo.setData(dataAtual);
			informativo.setTenista(tenista);
			informativoDao.adiciona(informativo);
			result.include("mensagem", "O informativo foi enviado com sucesso, adicione se desejar uma imagem");
			result.redirectTo(this).formMidiaInformativo(informativo);
		}else{
			
			validator.onErrorRedirectTo(IndexController.class).mensagemErro("Você não tem permissão para realizar essa funcionalidade.");

		}
		

	}
	@Post("/informativo/form-envia-midia-informativo")
	public void enviaMidiaInformativo(Informativo informativo, UploadedFile arquivo, MidiaInformativo midiaInformativo)  {
		
		try{
		Properties prop = new Utils().getProp();
		String contexto = prop.getProperty("prop.server.contexto");

		String path = contexto + "/upload/informativos/imagens";
		String novoNomeArquivo = null;
		String CAMINHO_IMAGENS_INFORMATIVO = "/"+ Utils.CONTEXTO + "/upload/informativos/imagens/";
		long TAMANHO_MAXIMO_IMAGEM = 512_000;
		int QUANTIDADE_MAXIMA_IMAGENS = 3;


		
		// verifica se a foto foi selecionada
		if (arquivo != null) {

			informativo = informativoDao.find(informativo.getId());
			String fileName = arquivo.getFileName();

			int start = fileName.lastIndexOf(".");
			String extensao = (start > 0) ? fileName.substring(start) : "";

			novoNomeArquivo = Utils.semAcento(informativo.getId() + fileName.replace(" ", ""));
			
			// verifica se a extensão é válida
			if (extensao.equals(".jpg") || (extensao.equals(".JPG") || extensao.equals(".png") || extensao.equals(".PNG") || extensao.equals(".jpeg") || extensao.equals(".JPEG"))) {

				midiaInformativo.setUrl(CAMINHO_IMAGENS_INFORMATIVO + novoNomeArquivo);
				midiaInformativo.setInformativo(informativo);
		
				// pega o arquivo enviado de imagem e verifica as dimensões
				/*boolean isDimensoesCorreta = Utils.verificaDimensoesImagem(arquivo, ALTURA_MAXIMA, LARGURA_MAXIMA);
				if(!isDimensoesCorreta){
					validator.add(new SimpleMessage("informativo.imagem", "Esta imagem tem dimensões fora dos padrões permitidos de " + ALTURA_MAXIMA + " altura e " + LARGURA_MAXIMA + " de largura."));
				}*/
		
			} else {
				validator.add(new SimpleMessage("informativo.imagem",
						"O tipo da imagem é inválido. É necessário escolher uma imagem .jpg, .png ou .jpeg."));
			}

			// verifica o tamanho do arquivo
			if (arquivo.getSize() > TAMANHO_MAXIMO_IMAGEM) {
				validator.add(new SimpleMessage("informativo.imagem", "A imagem ultrapassou o tamanho máximo permitido de 500 KB."));
			}

			// verifica quantas imagens já estão publicadas para o informativo
			List<MidiaInformativo> midias = midiaInformativoDao.getMidiasPorInformativos(informativo.getId());
			if (midias.size() >= QUANTIDADE_MAXIMA_IMAGENS) {
				validator.add(new SimpleMessage("informativo.imagem", "Este informativo já possui a quantidade máxima de " + QUANTIDADE_MAXIMA_IMAGENS
						+ " imagens"));
			}
			

			validator.onErrorRedirectTo(this).formMidiaInformativo(informativo);
			
			Utils.enviarArquivo(arquivo, path, novoNomeArquivo);
			midiaInformativoDao.adiciona(midiaInformativo);
			// se o informativo está sem imagem principal passa a ter uma
			if(midiaInformativo.getInformativo().getImagemPrincipal()==null){
				informativo.setImagemPrincipal(midiaInformativo.getUrl());
				informativoDao.atualiza(informativo);
			}
			result.include("mensagem", "A imagem foi enviada com sucesso!");
			result.redirectTo(this).formMidiaInformativo(informativo);
			
		} else if (midiaInformativo.getUrl() != null && !midiaInformativo.getUrl().equals("")) {
					informativo = informativoDao.find(informativo.getId());
					midiaInformativo.setInformativo(informativo);
					midiaInformativoDao.adiciona(midiaInformativo);
					result.include("mensagem", "As informações de seu vídeo foram gravadas, após avaliação ele será publicado no site.");
					result.redirectTo(this).formMidiaInformativo(informativo);
		}else{			
			validator.add(new SimpleMessage("informativo.midia", "É necessário escolher uma imagem ou uma url enconding do youtube"));
		}
		}catch(Exception e){
			e.printStackTrace();
			//result.redirectTo(IndexController.class).mensagem("Falha ao enviar o arquivo: " + e.getMessage());
		}
	}
	
	/**
	 * Método que remove um informativo do sistema, as midias relacionadas e as imagens gravadas 
	 * *
	 * @throws IOException 
	 * */
	@Get("/informativo/remove-informativo/{idInformativo}")
	public void removeInformativo(Long idInformativo) throws IOException {
		
		Properties prop = new Utils().getProp();
		String contexto = prop.getProperty("prop.server.contexto");
		String path = contexto + "/upload/informativos/imagens";
		String nomeArquivo;
		File arquivo;
		
		Informativo informativo = informativoDao.busca(idInformativo);
		List<MidiaInformativo> midiasInformativo = midiaInformativoDao.getMidiasPorInformativos(idInformativo);
		for (MidiaInformativo midia : midiasInformativo) {
			nomeArquivo = midia.getNomeArquivo();
			midiaInformativoDao.remove(midia);
			arquivo = new File(path+"/"+nomeArquivo);
			arquivo.delete();
		}
		
		informativoDao.remove(informativo);
		result.redirectTo(IndexController.class).mensagem("O seu informativo foi removido com sucesso!");

		

	}
	@Transactional
	@Post("/informativo/formulario-edita-informativo")
	public void confirmaEdicaoInformativo(Informativo informativo) {

		if(informativo.getTitulo().length() < QUANTIDADE_CARACTERES_MINIMO_TITULO){
			validator.add(new SimpleMessage("informativo.titulo",
					"O título deve ter entre " + QUANTIDADE_CARACTERES_MINIMO_TITULO + " e " + QUANTIDADE_CARACTERES_MAXIMO_TITULO + " caracteres"));
		}

		if(informativo.getResumo().length() < QUANTIDADE_CARACTERES_MINIMO_RESUMO){
			validator.add(new SimpleMessage("informativo.resumo",
					"O resumo deve ter entre " + QUANTIDADE_CARACTERES_MINIMO_RESUMO + " e " + QUANTIDADE_CARACTERES_MAXIMO_RESUMO + " caracteres"));
		}

		validator.onErrorRedirectTo(this).formEditaInformativo(informativo.getId());
		
		if (tenistaLogado != null && tenistaLogado.isOrganizador()) {
			if(!informativo.getTitulo().isEmpty() && !informativo.getResumo().isEmpty() && !informativo.getConteudo().isEmpty()){
				Informativo informativoOriginal = informativoDao.busca(informativo.getId());
				
				informativo.setImagemPrincipal(informativoOriginal.getImagemPrincipal());
				informativo.setTenista(tenistaLogado.getTenista());
				informativo.setData(informativoOriginal.getData());
				informativoDao.atualiza(informativo);
				result.redirectTo(IndexController.class).mensagem("O seu informativo foi atualizado com sucesso!");
			}else{
				result.redirectTo(IndexController.class).mensagemErro(
						"Nenhum dos campos podem estar vazios.");
			}
			
		} else {
			result.redirectTo(IndexController.class).mensagemErro(
					"Você não tem permissão de acessar essa funcionalidade.");
		}
	}
	/**
	 * Método responsável por redirecionar para a tela de detalhes do informativo
	 * 
	 * @param idTorneio
	 *            identifica qual informativo será detalhado
	 * 
	 * */
	@Get("/informativo/detalhes-do-informativo/{idInformativo}")
	public void detalhaInformativo(Long idInformativo) {

		Informativo informativo = informativoDao.busca(idInformativo);
		List<MidiaInformativo> midiasInformativo = midiaInformativoDao.getMidiasPorInformativos(idInformativo);

		result.include("informativo", informativo);
		result.include("midiasInformativo", midiasInformativo);
	}
}
