package br.portaldotenis.portal.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.observer.upload.UploadedFile;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.portaldotenis.portal.dao.MidiaTorneioDAO;
import br.portaldotenis.portal.dao.TorneioDAO;
import br.portaldotenis.portal.model.MidiaTorneio;
import br.portaldotenis.portal.model.TipoMidiaTorneio;
import br.portaldotenis.portal.model.Torneio;
import br.portaldotenis.portal.util.Utils;

@Controller
public class MidiaTorneioController {

	@Inject
	MidiaTorneio midiaTorneio;

	@Inject
	MidiaTorneioDAO midiaTorneioDao;

	@Inject
	TorneioDAO torneioDao;

	@Inject
	private Result result;

	@Inject
	private Validator validator;
	

	private static final String CONTEXTO = Utils.CONTEXTO;
	private static final String CAMINHO_IMAGENS_TORNEIO = "/"+ CONTEXTO +"/upload/torneios/imagens/";
	//private static final String CAMINHO_IMAGENS_TORNEIO = "/sigete_alivesports/WebContent/upload/torneios/imagens/";
	private static final long TAMANHO_MAXIMO_IMAGEM = 512_000;
	private static final int QUANTIDADE_MAXIMA_IMAGENS = 15;

	private static final String CAMINHO_REGULAMENTO_TORNEIO = "/"+ CONTEXTO +"/upload/torneios/regulamentos/";
	private static final long TAMANHO_MAXIMO_REGULAMENTO = 512_000;

	private static final String CAMINHO_BANNER_TORNEIO = "/"+ CONTEXTO +"/upload/torneios/banners/";
	private static final long TAMANHO_MAXIMO_BANNER = 716_800;

	private static final String CAMINHO_PATROCINIOS_TORNEIO = "/"+ CONTEXTO +"/upload/torneios/patrocinios/";

	private static final String CAMINHO_ADMINISTRATIVO_TORNEIO ="/"+ CONTEXTO +"/upload/torneios/administrativos/";

	private static final int LARGURA_MAXIMA = 520;
	private static final int LARGURA_MINIMA = 500;

	private static final int ALTURA_MAXIMA = 710;
	private static final int ALTURA_MINIMA = 680;

	@Get("/midiaTorneio/formulario-de-envio-de-midias-do-torneio/{idTorneio}")
	public void formEnviaMidiaTorneio(Long idTorneio) {

		Torneio torneio = torneioDao.busca(idTorneio);
		result.include("torneio", torneio);

	}

	@Get("/midiaTorneio/visualiza-midias-do-torneio/{idTorneio}")
	public void visualizaMidiasDoTorneio(Long idTorneio) {

		List<MidiaTorneio> imagens = midiaTorneioDao.getImagensPorTorneio(idTorneio);
		result.include("imagens", imagens);
		result.include("numeroDeImagens", imagens.size());
		if(imagens.size() > 0){
			result.include("torneio", imagens.get(0).getTorneio());
		}else{
			result.include("torneio", torneioDao.busca(idTorneio));
		}

		List<MidiaTorneio> videos = midiaTorneioDao.getVideosAtivosPorTorneio(idTorneio);
		result.include("videos", videos);
		
	}
	@Get("/midiaTorneio/visualiza-midias-dos-torneios/")
	public void visualizaMidiasDosTorneios() {

		List<MidiaTorneio> imagens = midiaTorneioDao.getImagens();
		result.include("imagens", imagens);

		List<MidiaTorneio> videos = midiaTorneioDao.getVideosAtivos();
		result.include("videos", videos);
		
	}
	@Post("/midiaTorneio/formulario-de-envio-de-arquivos-do-torneio")
	public void enviaMidiaTorneio(MidiaTorneio midiaTorneio, UploadedFile arquivo) throws IOException {
		
		

		if (midiaTorneio.getTipoMidiaTorneio().equals(TipoMidiaTorneio.IMAGEM)){
			enviarImagem(midiaTorneio, arquivo);
		}
		
		if (midiaTorneio.getTipoMidiaTorneio().equals(TipoMidiaTorneio.VIDEO)){
			enviarVideo(midiaTorneio, arquivo);
		}
		
		if(midiaTorneio.getTipoMidiaTorneio().equals(TipoMidiaTorneio.REGULAMENTO) ||
				midiaTorneio.getTipoMidiaTorneio().equals(TipoMidiaTorneio.CHAVEAMENTO) ||
				midiaTorneio.getTipoMidiaTorneio().equals(TipoMidiaTorneio.INSCRITOS)){
			enviarPDF(midiaTorneio, arquivo, midiaTorneio.getTipoMidiaTorneio());
		}
		if (midiaTorneio.getTipoMidiaTorneio().equals(TipoMidiaTorneio.BANNER)){
			enviarBanner(midiaTorneio, arquivo);
		}
		
		if(midiaTorneio.getTipoMidiaTorneio().equals(TipoMidiaTorneio.PATROCINIO)){
			enviarPatrocinio(midiaTorneio, arquivo);
		}
			
	}

	

	
	

	/**
	 * @param torneio
	 * @param foto
	 * @param titulo
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	private void enviarImagem(MidiaTorneio midiaTorneio, UploadedFile imagem) throws IOException, FileNotFoundException {
		Properties prop = new Utils().getProp();
		String contexto = prop.getProperty("prop.server.contexto");

		String path = contexto + "/upload/torneios/imagens";
		String novoNomeArquivo = null;
		Torneio torneio = null;
		// verifica se a foto foi selecionada
		if (imagem != null) {

			torneio = torneioDao.find(midiaTorneio.getTorneio().getId());
			String fileName = imagem.getFileName();

			int start = fileName.lastIndexOf(".");
			String extensao = (start > 0) ? fileName.substring(start) : "";

			novoNomeArquivo = Utils.semAcento(torneio.getId() + fileName.replace(" ", ""));
			

			
			// verifica se a extensão é válida
			if (extensao.equals(".jpg") || (extensao.equals(".JPG") || extensao.equals(".png") || extensao.equals(".PNG") || extensao.equals(".jpeg") || extensao.equals(".JPEG"))) {

				midiaTorneio.setUrl(CAMINHO_IMAGENS_TORNEIO + novoNomeArquivo);
				midiaTorneio.setTipoMidiaTorneio(TipoMidiaTorneio.IMAGEM);
				midiaTorneio.setAtivo(true);
				midiaTorneio.setTorneio(torneio);

			} else {
				validator.add(new SimpleMessage("torneio.imagem",
						"O tipo da imagem é inválido. É necessário escolher uma imagem .jpg, .png ou .jpeg."));
			}

			// verifica o tamanho do arquivo
			if (imagem.getSize() > TAMANHO_MAXIMO_IMAGEM) {
				validator.add(new SimpleMessage("torneio.imagem", "A imagem ultrapassou o tamanho máximo permitido de 500 KB."));
			}

			// verifica quantas imagens já estão publicadas para o torneio
			List<MidiaTorneio> midias = midiaTorneioDao.getImagensPorTorneio(torneio.getId());
			if (midias.size() >= QUANTIDADE_MAXIMA_IMAGENS) {
				validator.add(new SimpleMessage("torneio.imagem", "Este torneio já possui a quantidade máxima de " + QUANTIDADE_MAXIMA_IMAGENS
						+ " imagens"));
			}
		} else {
			validator.add(new SimpleMessage("torneio.imagem", "É necessário escolher uma imagem"));
		}

		validator.onErrorRedirectTo(this).formEnviaMidiaTorneio(midiaTorneio.getTorneio().getId());

		
		Utils.enviarArquivo(imagem, path, novoNomeArquivo);
		midiaTorneioDao.adiciona(midiaTorneio);
		result.include("mensagem", "A imagem foi enviada com sucesso!");
		result.redirectTo(this).formEnviaMidiaTorneio(midiaTorneio.getTorneio().getId());
	}
	
	private void enviarVideo(MidiaTorneio midiaTorneio, UploadedFile arquivo) throws IOException {

		if(arquivo == null){
			if(midiaTorneio.getUrl() != null && !midiaTorneio.getUrl().equals("")){
				Torneio torneio = torneioDao.find(midiaTorneio.getTorneio().getId());
				midiaTorneio.setTorneio(torneio);
				midiaTorneio.setAtivo(false);
				midiaTorneio.setTipoMidiaTorneio(TipoMidiaTorneio.VIDEO);
				midiaTorneioDao.adiciona(midiaTorneio);
				result.include("mensagem", "As informações de seu vídeo foram gravadas, após avaliação ele será publicado no site.");
				result.redirectTo(this).formEnviaMidiaTorneio(midiaTorneio.getTorneio().getId());
			}else{
				validator.add(new SimpleMessage("torneio.vídeo", "É necessário informar a URL do vídeo"));
			}
		}else{
			validator.add(new SimpleMessage("torneio.vídeo", "Só é permitido enviar vídeos da WEB, informe a URL do vídeo"));
		}
		
		validator.onErrorRedirectTo(this).formEnviaMidiaTorneio(midiaTorneio.getTorneio().getId());


	}
	private void enviarPDF(MidiaTorneio midiaTorneio, UploadedFile arquivo, TipoMidiaTorneio tipoMidiaTorneio) throws IOException {
		
		Properties prop = new Utils().getProp();
		String contexto = prop.getProperty("prop.server.contexto");

		String path = null;
		
		

		String novoNomeArquivo = null;
		Torneio torneio = null;
		
		// verifica se a foto foi selecionada
		if (arquivo != null) {

			torneio = torneioDao.find(midiaTorneio.getTorneio().getId());
			String fileName = arquivo.getFileName();

			int start = fileName.lastIndexOf(".");
			String extensao = (start > 0) ? fileName.substring(start) : "";

			novoNomeArquivo = midiaTorneio.getTitulo() + "_" + torneio.getId() + tipoMidiaTorneio.getId() + new Random().nextInt() + extensao;

			// verifica se a extensão é válida
			if (extensao.equals(".pdf") || extensao.equals(".PDF")) {
				
				
				midiaTorneio.setTipoMidiaTorneio(tipoMidiaTorneio);
				midiaTorneio.setAtivo(true);
				midiaTorneio.setTorneio(torneio);

			} else {
				validator.add(new SimpleMessage("torneio.regulamento",
						"O tipo do arquivo é inválido. É necessário escolher um arquivo no formato PDF"));
			}

			// verifica o tamanho do arquivo
			if (arquivo.getSize() > TAMANHO_MAXIMO_REGULAMENTO) {
				validator.add(new SimpleMessage("torneio.arquivo", "O arquivo do regulamento ultrapassou o tamanho máximo permitido de 500 KB."));
			}

		} else {
			validator.add(new SimpleMessage("torneio.arquivo", "É necessário escolher um arquivo"));
		}

		validator.onErrorRedirectTo(this).formEnviaMidiaTorneio(torneio.getId());

		// verifica se já existe regulamento em caso positivo atualiza
		
		if(tipoMidiaTorneio.equals(TipoMidiaTorneio.REGULAMENTO)){
			path = contexto + "/upload/torneios/regulamentos";
			midiaTorneio.setUrl(CAMINHO_REGULAMENTO_TORNEIO + novoNomeArquivo);
			Utils.enviarArquivo(arquivo, path, novoNomeArquivo);

			MidiaTorneio midia = midiaTorneioDao.getRegulamentoPorTorneio(torneio.getId());
			if (midia == null) {
				midiaTorneioDao.adiciona(midiaTorneio);	
				result.include("mensagem", "O regulamento foi enviado com sucesso!");
			}else{
				midiaTorneio.setId(midia.getId());
				midiaTorneioDao.atualiza(midiaTorneio);
				result.include("mensagem", "Já existia um arquivo desse tipo no sistema, o arquivo atual atualizou o anterior com sucesso!");
			}
		}
		if(tipoMidiaTorneio.equals(TipoMidiaTorneio.CHAVEAMENTO)){
			path = contexto + "/upload/torneios/administrativos";
			midiaTorneio.setUrl(CAMINHO_ADMINISTRATIVO_TORNEIO + novoNomeArquivo);
			Utils.enviarArquivo(arquivo, path, novoNomeArquivo);

			MidiaTorneio midia = midiaTorneioDao.getPDFChaveamentoPorTorneio(torneio.getId());
			if (midia == null) {
				midiaTorneioDao.adiciona(midiaTorneio);	
				result.include("mensagem", "O chaveamento foi enviado com sucesso!");
			}else{
				midiaTorneio.setId(midia.getId());
				midiaTorneioDao.atualiza(midiaTorneio);
				result.include("mensagem", "Já existia um arquivo desse tipo no sistema, o arquivo atual atualizou o anterior com sucesso!");
			}
		}
		if(tipoMidiaTorneio.equals(TipoMidiaTorneio.INSCRITOS)){
			path = contexto + "/upload/torneios/administrativos";
			midiaTorneio.setUrl(CAMINHO_ADMINISTRATIVO_TORNEIO + novoNomeArquivo);
			Utils.enviarArquivo(arquivo, path, novoNomeArquivo);

			MidiaTorneio midia = midiaTorneioDao.getPDFInscritosPorTorneio(torneio.getId());
			if (midia == null) {
				midiaTorneioDao.adiciona(midiaTorneio);	
				result.include("mensagem", "O arquivo foi enviado com sucesso!");
			}else{
				midiaTorneio.setId(midia.getId());
				midiaTorneioDao.atualiza(midiaTorneio);
				result.include("mensagem", "Já existia um arquivo desse tipo no sistema, o arquivo atual atualizou o anterior com sucesso!");
			}
		}
		
		
		
		result.redirectTo(this).formEnviaMidiaTorneio(midiaTorneio.getTorneio().getId());
	}
	private void enviarBanner(MidiaTorneio midiaTorneio, UploadedFile arquivo) throws IOException {
		
		String novoNomeArquivo = null;
		Torneio torneio = null;
		
	
			
		Properties prop = new Utils().getProp();
		String contexto = prop.getProperty("prop.server.contexto");
		String path = contexto + "/upload/torneios/banners";


	
	
		// verifica se a foto foi selecionada
		if (arquivo != null) {

			torneio = torneioDao.find(midiaTorneio.getTorneio().getId());
			String fileName = arquivo.getFileName();

			int start = fileName.lastIndexOf(".");
			String extensao = (start > 0) ? fileName.substring(start) : "";

			novoNomeArquivo = torneio.getId() + extensao;

			
			
			// verifica se a extensão é válida
			if (extensao.equals(".jpg") || (extensao.equals(".JPG") || extensao.equals(".png") || extensao.equals(".PNG") || extensao.equals(".jpeg") || extensao.equals(".JPEG"))) {

				midiaTorneio.setUrl(CAMINHO_BANNER_TORNEIO + novoNomeArquivo);
				midiaTorneio.setTipoMidiaTorneio(TipoMidiaTorneio.BANNER);
				midiaTorneio.setAtivo(true);
				midiaTorneio.setTorneio(torneio);
				
				/*UploadedFile arquivoCopiado = arquivo;
				// pega o arquivo enviado de imagem e verifica as dimensões
				boolean isDimensoesCorreta = Utils.verificaDimensoesImagem(arquivoCopiado, ALTURA_MAXIMA, LARGURA_MAXIMA, ALTURA_MINIMA, LARGURA_MINIMA);
				if(!isDimensoesCorreta){
					validator.add(new SimpleMessage("informativo.imagem", "Esta imagem tem dimensões fora dos padrões permitidos de " + ALTURA_MAXIMA + " altura e " + LARGURA_MAXIMA + " de largura, ou existe alguma falha no arquivo que implicou na impossibilidade de leitura. "));
				}*/

			} else {
				validator.add(new SimpleMessage("torneio.banner",
						"O tipo do arquivo é inválido. É necessário escolher um arquivo no formato JPG"));
			}

			// verifica o tamanho do arquivo
			if (arquivo.getSize() > TAMANHO_MAXIMO_BANNER) {
				validator.add(new SimpleMessage("torneio.banner", "O arquivo do banner ultrapassou o tamanho máximo permitido de 700 KB."));
			}

		} else {
			validator.add(new SimpleMessage("torneio.banner", "É necessário escolher um arquivo."));
		}

		validator.onErrorRedirectTo(this).formEnviaMidiaTorneio(torneio.getId());

		Utils.enviarArquivo(arquivo, path, novoNomeArquivo);
		// verifica se já existe regulamento em caso positivo atualiza
		MidiaTorneio midia = midiaTorneioDao.getBannerPorTorneio(torneio.getId());
		if (midia == null) {
			midiaTorneioDao.adiciona(midiaTorneio);	
			result.include("mensagem", "O banner foi enviado com sucesso!");
		}else{
			midiaTorneio.setId(midia.getId());
			midiaTorneioDao.atualiza(midiaTorneio);
			result.include("mensagem", "Já existia um banner no sistema, o arquivo atual atualizou o anterior com sucesso!");
		}
		
		result.redirectTo(this).formEnviaMidiaTorneio(midiaTorneio.getTorneio().getId());
		
	}
	
	private void enviarPatrocinio(MidiaTorneio midiaTorneio, UploadedFile logomarca) throws FileNotFoundException, IOException {
		Properties prop = new Utils().getProp();
		String contexto = prop.getProperty("prop.server.contexto");

		String path = contexto + "/upload/torneios/patrocinios";
		String novoNomeArquivo = null;
		Torneio torneio = null;
		// verifica se a foto foi selecionada
		if (logomarca != null) {

			torneio = torneioDao.find(midiaTorneio.getTorneio().getId());
			String fileName = logomarca.getFileName();

			int start = fileName.lastIndexOf(".");
			String extensao = (start > 0) ? fileName.substring(start) : "";

			novoNomeArquivo = Utils.semAcento(torneio.getId() + fileName) ;

			// verifica se a extensão é válida
			if (extensao.equals(".jpg") || (extensao.equals(".JPG") || extensao.equals(".png") || extensao.equals(".PNG") || extensao.equals(".jpeg") || extensao.equals(".JPEG"))) {

				midiaTorneio.setUrl(CAMINHO_PATROCINIOS_TORNEIO + novoNomeArquivo);
				midiaTorneio.setAtivo(true);
				midiaTorneio.setTipoMidiaTorneio(TipoMidiaTorneio.PATROCINIO);
				midiaTorneio.setTorneio(torneio);

			} else {
				validator.add(new SimpleMessage("torneio.patrocinio",
						"O tipo da logomarca é inválido. É necessário escolher uma imagem .jpg, .png ou .jpeg."));
			}

			// verifica o tamanho do arquivo
			if (logomarca.getSize() > TAMANHO_MAXIMO_IMAGEM) {
				validator.add(new SimpleMessage("torneio.patrocinio", "A imagem ultrapassou o tamanho máximo permitido de 300 KB."));
			}

			// verifica quantas imagens já estão publicadas para o torneio
			List<MidiaTorneio> midias = midiaTorneioDao.getPatrociniosPorTorneio(torneio.getId());
			if (midias.size() >= QUANTIDADE_MAXIMA_IMAGENS) {
				validator.add(new SimpleMessage("torneio.patrocínio", "Este torneio já possui a quantidade máxima de " + QUANTIDADE_MAXIMA_IMAGENS
						+ " patrocínios"));
			}
		} else {
			validator.add(new SimpleMessage("torneio.patrocínio", "É necessário escolher uma logomarca do patrocinador."));
		}

		validator.onErrorRedirectTo(this).formEnviaMidiaTorneio(midiaTorneio.getTorneio().getId());

		
		Utils.enviarArquivo(logomarca, path, novoNomeArquivo);
		midiaTorneioDao.adiciona(midiaTorneio);
		result.include("mensagem", "A imagem foi enviada com sucesso!");
		result.redirectTo(this).formEnviaMidiaTorneio(midiaTorneio.getTorneio().getId());
		
	}


	
}
