package br.portaldotenis.portal.controller;

import java.util.Calendar;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Result;
import br.portaldotenis.portal.dao.InformativoDAO;
import br.portaldotenis.portal.dao.InscricaoDAO;
import br.portaldotenis.portal.dao.InscricaoDeDuplaDAO;
import br.portaldotenis.portal.dao.MidiaTorneioDAO;
import br.portaldotenis.portal.dao.PontuacaoDAO;
import br.portaldotenis.portal.dao.TorneioDAO;
import br.portaldotenis.portal.model.Cidade;
import br.portaldotenis.portal.model.Clube;
import br.portaldotenis.portal.model.Informativo;
import br.portaldotenis.portal.model.InscricaoDeDupla;
import br.portaldotenis.portal.model.MidiaTorneio;
import br.portaldotenis.portal.model.Nivel;
import br.portaldotenis.portal.model.Pontuacao;
import br.portaldotenis.portal.model.Professor;
import br.portaldotenis.portal.model.TenistaLogado;
import br.portaldotenis.portal.model.TipoInformativo;
import br.portaldotenis.portal.model.TipoNivel;
import br.portaldotenis.portal.model.Torneio;
import br.portaldotenis.portal.util.Utils;

/**
 * Controller que recebe requisições a página inicial
 * 
 * @author Gabriel Soares
 * @author Thiago Holanda
 * 
 */

@Controller
public class IndexController {

	private static final int DIAS_MAXIMO = 180;
	public static final String CONTEXTO = "alivesports";

	@Inject
	private Result result;

	@Inject
	private TenistaLogado tenistaLogado;
	
	@Inject
	private PontuacaoDAO pontuacaoDao;
	
	@Inject
	private InscricaoDeDuplaDAO inscricaoDeDuplaDao;
	
	@Inject
	private TorneioDAO torneioDao;
	
	@Inject
	InscricaoDAO inscricaoDao;
	
	@Inject
	InformativoDAO informativoDao;
	
	@Inject
	MidiaTorneioDAO midiaTorneioDao;
	

	
	@Path("/")
	public void index() {
		
		List<Informativo> noticiasLocais1 = informativoDao.getInformativosPorTipoLimitado(TipoInformativo.LOCAL,0,3);
		List<Informativo> noticiasLocais2 = informativoDao.getInformativosPorTipoLimitado(TipoInformativo.LOCAL,3,3);
		List<Informativo> dicas = informativoDao.getInformativosPorTipoLimitado(TipoInformativo.DICA,0,5);
		List<MidiaTorneio> midias = midiaTorneioDao.getBannersLimitado(0, 3);
		MidiaTorneio midiaUm = midias.get(0);
		MidiaTorneio midiaDois = midias.get(1);
		MidiaTorneio midiaTres = midias.get(2);
		Torneio torneioUm = midias.get(0).getTorneio();
		Torneio torneioDois = midias.get(1).getTorneio();
		Torneio torneioTres = midias.get(2).getTorneio();
		
		result.include("noticiasLocais1", noticiasLocais1);
		result.include("noticiasLocais2", noticiasLocais2);
		result.include("dicas", dicas);
		result.include("torneioUm", torneioUm);
		result.include("torneioDois", torneioDois);
		result.include("torneioTres", torneioTres);
		result.include("midiaUm", midiaUm);
		result.include("midiaDois", midiaDois);
		result.include("midiaTres", midiaTres);
		

	}
	@Path("/index2")
	public void index2() {

		List<Informativo> noticiasLocais = informativoDao.getInformativosPorTipoLimitado(TipoInformativo.LOCAL,0,6);
		List<Informativo> dicas = informativoDao.getInformativosPorTipoLimitado(TipoInformativo.DICA,0,5);
		List<Torneio> torneios = torneioDao.getTorneiosLimitados(0,3);
		Torneio torneioUm = torneios.get(2);
		Torneio torneioDois = torneios.get(1);
		Torneio torneioTres = torneios.get(0);
		List<MidiaTorneio> midias = midiaTorneioDao.getBannersLimitado(0, 3);
		MidiaTorneio midiaUm = midias.get(0);
		MidiaTorneio midiaDois = midias.get(1);
		MidiaTorneio midiaTres = midias.get(2);
		
		result.include("noticiasLocais", noticiasLocais);
		result.include("dicas", dicas);
		result.include("torneioUm", torneioUm);
		result.include("torneioDois", torneioDois);
		result.include("torneioTres", torneioTres);
		result.include("midiaUm", midiaUm);
		result.include("midiaDois", midiaDois);
		result.include("midiaTres", midiaTres);


	}
	
	@Path("/index-sf")
	public void indexSf() {

	}

	@Path("/mensagem")
	public String mensagem(String mensagem) {
		if (mensagem == null) {
			mensagem = "Função não disponível. Ainda em desenvolvimento, em breve liberaremos.";
		}
		return mensagem;
	}

	@Path("/mensagemErro")
	public String mensagemErro(String mensagem) {
		if (mensagem == null) {
			mensagem = "Função não disponível. Ainda em desenvolvimento, em breve liberaremos.";
		}
		return mensagem;
	}
	@Get("/pagina-inicial")
	public void paginaInicial() {

		// TODO fazer via controle de acesso
		if (tenistaLogado.getTenista() != null) {
			result.include("trofeus1", inscricaoDao.getNumeroDe1Lugar(tenistaLogado.getTenista()));
			result.include("trofeus2", inscricaoDao.getNumeroDe2Lugar(tenistaLogado.getTenista()));
			result.include("tenista", tenistaLogado.getTenista());
			
			// verifica se a data está desatualizada		
			Calendar dataAtualizacao = tenistaLogado.getTenista().getDataAtualizacao();
			if(dataAtualizacao == null || Utils.calculaTempo(dataAtualizacao) > DIAS_MAXIMO ){
				result.include("dadosDesatualizados",true);
			}
			
		} else {
			result.redirectTo(TenistaController.class).login();
		}
	}

	@Path("/instalar")
	@Transactional
	public void instalar(EntityManager manager, Result result) {
		Cidade joaoPessoa = new Cidade("João Pessoa");
		Cidade campinaGrande = new Cidade("Campina Grande");
		Cidade cabedelo = new Cidade("Cabedelo");
		Cidade guarabira = new Cidade("Guarabira");

		manager.persist(joaoPessoa);
		manager.persist(campinaGrande);
		manager.persist(cabedelo);
		manager.persist(guarabira);

		Professor alexandre = new Professor("Alexandre");
		Professor joca = new Professor("Joca");
		Professor marinho = new Professor("Marinho");
		Professor sergio = new Professor("Sérgio");
		Professor joseMacedo = new Professor("José Macedo");
		Professor helio = new Professor("Hélio");
		Professor vavado = new Professor("Vavado");

		manager.persist(alexandre);
		manager.persist(joca);
		manager.persist(marinho);
		manager.persist(sergio);
		manager.persist(joseMacedo);
		manager.persist(helio);
		manager.persist(vavado);

		Clube centroTenistico = new Clube("Centro Tenístico");
		Clube caboBranco = new Clube("Cabo Branco");
		Clube ancef = new Clube("Ansef-PB");
		Clube campestre = new Clube("Campestre");
		Clube hotelTambau = new Clube("Hotel Tambaú");

		manager.persist(centroTenistico);
		manager.persist(caboBranco);
		manager.persist(ancef);
		manager.persist(campestre);
		manager.persist(hotelTambau);

		Nivel primeiraClasse = new Nivel("1º Classe - Masculino", TipoNivel.TECNICO);
		Nivel segundaClasse = new Nivel("2º Classe - Masculino", TipoNivel.TECNICO);
		Nivel terceiraClasse = new Nivel("3º Classe - Masculino", TipoNivel.TECNICO);
		Nivel quartaClasse = new Nivel("4º Classe - Masculino", TipoNivel.TECNICO);
		Nivel quintaClasse = new Nivel("5º Classe - Masculino", TipoNivel.TECNICO);
		Nivel estreante = new Nivel("Estreante - Masculino", TipoNivel.TECNICO);
		Nivel classeAFem = new Nivel("Classe A - Feminino", TipoNivel.TECNICO);
		Nivel classeBFem = new Nivel("Classe B - Feminino", TipoNivel.TECNICO);
		Nivel estreanteFem = new Nivel("Estreante - Feminino", TipoNivel.TECNICO);
		Nivel oitoAnos = new Nivel("8 anos", TipoNivel.IDADE);
		Nivel noveAnos = new Nivel("9 anos", TipoNivel.IDADE);
		Nivel dezAnos = new Nivel("10 anos", TipoNivel.IDADE);
		Nivel dozeAnos = new Nivel("12 anos", TipoNivel.IDADE);

		manager.persist(primeiraClasse);
		manager.persist(segundaClasse);
		manager.persist(terceiraClasse);
		manager.persist(quartaClasse);
		manager.persist(quintaClasse);
		manager.persist(estreante);
		manager.persist(classeAFem);
		manager.persist(classeBFem);
		manager.persist(estreanteFem);
		manager.persist(oitoAnos);
		manager.persist(noveAnos);
		manager.persist(dezAnos);
		manager.persist(dozeAnos);

		result.redirectTo(this).mensagem("Instalação concluida");
	}

	@Path("/atualiza-pontos")
	@Transactional
	public void atualizaPontos(EntityManager manager, Result result) {
		
		int pontuacaoCalculada;
		Torneio torneio = torneioDao.find(12L);
		List<InscricaoDeDupla> inscricoes = inscricaoDeDuplaDao.getInscricoesPorTorneio(torneio);
		
		
		for (InscricaoDeDupla inscricaoDeDupla : inscricoes) {
			pontuacaoCalculada = obterPontosDaDuplaInscrita(inscricaoDeDupla);
			inscricaoDeDupla.setPontuacaoDaDupla(pontuacaoCalculada);
			inscricaoDeDuplaDao.atualiza(inscricaoDeDupla);
		}
		
		
		result.redirectTo(this).mensagem("Pontuação atualizada");
	}
	/**
	 * Método que calcula a pontuação da dupla
	 * 
	 * @param inscricao
	 * @param tenistaParceiro
	 * @param tenista
	 */
	private Integer obterPontosDaDuplaInscrita(InscricaoDeDupla inscricao) {
	
		Pontuacao pontuacaoTenista = pontuacaoDao.getPontuacao(inscricao.getTenista(), inscricao.getNivel(), inscricao.getAnoReferencia()); 	
		Pontuacao pontuacaoTenistaParceiro = pontuacaoDao.getPontuacao(inscricao.getTenistaParceiro(), inscricao.getNivel(), inscricao.getAnoReferencia());
		
		int pontosTenista = pontuacaoTenista != null ? pontuacaoTenista.getPontos(): 0;
		int pontosTenistaParceiro = pontuacaoTenistaParceiro != null ? pontuacaoTenistaParceiro.getPontos(): 0;
		return (pontosTenista + pontosTenistaParceiro);
	}

}
