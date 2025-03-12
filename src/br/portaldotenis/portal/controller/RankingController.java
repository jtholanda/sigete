package br.portaldotenis.portal.controller;

import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.portaldotenis.portal.dao.NivelDAO;
import br.portaldotenis.portal.dao.PontuacaoDAO;
import br.portaldotenis.portal.dao.TenistaDAO;
import br.portaldotenis.portal.model.CategoriaTorneio;
import br.portaldotenis.portal.model.Nivel;
import br.portaldotenis.portal.model.Pontuacao;
import br.portaldotenis.portal.model.Tenista;
import br.portaldotenis.portal.model.TipoNivel;

/**
 * Controller responsável por gerenciar o ranking de pontuação dos tenistas
 * 
 * @author Thiago Holanda
 * 
 */
@Controller
public class RankingController {

	@Inject
	TenistaDAO tenistaDao;

	@Inject
	NivelDAO nivelDao;

	@Inject
	Tenista tenista;

	@Inject
	Nivel nivel;

	@Inject
	private Result result;

	@Inject
	private PontuacaoDAO pontuacaoDao;

	/**
	 * Método que redireciona para a tela de ranking geral de tenistas
	 * 
	 * 
	 * 
	 * */
	@Get("/ranking/ranking-geral")
	public void visualizaRankingGeral() {

		List<Tenista> tenistasRankeados = tenistaDao.getRankingDeTenistas();

		result.include("tenistasRankeados", tenistasRankeados);
		result.include("numeroDeTenistas", tenistasRankeados.size());

	}

	/**
	 * Método que recupera o ranking de tenistas do sexo feminino
	 * 
	 * @param idNivel
	 *            identifica o nível
	 * 
	 * */
	@Get("/ranking/ranking-feminino")
	public void visualizaRankingFeminino() {

		// TODO pesquisa na tabela de pontuação
		List<Tenista> tenistasRankeados = tenistaDao.getRankingFeminino();

		result.include("tenistasRankeados", tenistasRankeados);
		result.include("numeroDeTenistas", tenistasRankeados.size());

	}

	/**
	 * Método que redireciona para a página de pesquisa ranking por nível
	 * técnico
	 * 
	 * @param idNivel
	 *            identifica o nível
	 * 
	 * */

	@Get("/ranking/ranking-por-nivel-tecnico")
	public void visualizaRankingPorNivelTecnico() {

		List<Nivel> niveis = nivelDao.getNiveisPorTipo(TipoNivel.TECNICO);

		result.include("niveisTecnicos", niveis);

	}

	/**
	 * Método que redireciona para a página de pesquisa ranking por nível
	 * técnico
	 * 
	 * @param idNivel
	 *            identifica o nível
	 * 
	 * */

	@Get("/ranking/ranking-feminino-por-nivel-tecnico")
	public void visualizaRankingFemininoPorNivelTecnico() {

		List<Nivel> niveis = nivelDao.getNiveisPorTipo(TipoNivel.FEMININO);

		result.include("niveisTecnicosFeminino", niveis);

	}

	/**
	 * Método que redireciona para a página de pesquisa ranking por nível
	 * técnico
	 * 
	 * @param idNivel
	 *            identifica o nível
	 * 
	 * */

	@Get("/ranking/ranking-masculino-por-nivel-tecnico")
	public void visualizaRankingMasculinoPorNivelTecnico() {

		List<Nivel> niveis = nivelDao.getNiveisPorTipo(TipoNivel.MASCULINO);

		result.include("niveisTecnicosMasculino", niveis);

	}

	/**
	 * Método que redireciona para a página de pesquisa ranking por idade
	 * 
	 * @param idNivel
	 *            identifica o nível
	 * 
	 * */

	@Get("/ranking/ranking-por-idade")
	public void visualizaRankingPorIdade() {

		List<Nivel> niveis = nivelDao.getNiveisPorTipo(TipoNivel.IDADE);

		result.include("niveisPorIdade", niveis);

	}
	
	/**
	 * Método que redireciona para a página de pesquisa ranking por nível
	 * técnico
	 * 
	 * @param idNivel
	 *            identifica o nível
	 * 
	 * */

	@Get("/ranking/ranking-de-duplas-por-nivel-tecnico")
	public void visualizaRankingDeDuplasPorNivelTecnico() {

		List<Nivel> niveis = nivelDao.getNiveisPorTipo(TipoNivel.DUPLAS);

		result.include("niveisTecnicos", niveis);

	}

	// MÉTODOS DE AÇÃO

	/**
	 * Método que recupera o ranking de tenistas pelo nível
	 * 
	 * @param idNivel
	 *            identifica o nível
	 * @param categoria identifica se o rankign é de simples ou de duplas e qual o ano do ranking
	 * 
	 * */
	@Post("/ranking/ranking-por-nivel-tecnico")
	public void pesquisaRankingPorNivelTecnico(Integer idNivel, Integer categoria, Integer ano) {

		if (idNivel != null) {
			
			if(ano == null || ano.equals(0) ){
				ano = Calendar.getInstance().get(Calendar.YEAR);
			}

			List<Pontuacao> pontosDosTenistasRankeadosNivel = pontuacaoDao.getRankingDeTenistasPorNivel(idNivel, ano);
			Nivel nivel = nivelDao.find(idNivel);

			result.include("pontosDosTenistasRankeados", pontosDosTenistasRankeadosNivel);
			result.include("numeroDeTenistas", pontosDosTenistasRankeadosNivel.size());
			result.include("nivel", nivel);
			result.include("categoria", categoria);
			result.include("ano", ano);

			// Pra verificar a categoria no rankig
			if(categoria == CategoriaTorneio.SIMPLES.getId() ){
				result.redirectTo(RankingController.class).visualizaRankingPorNivelTecnico();
			}else if (categoria == CategoriaTorneio.DUPLAS.getId()) {
				result.redirectTo(RankingController.class).visualizaRankingDeDuplasPorNivelTecnico();
			}


		}


	}

	/**
	 * Método que recupera o ranking de tenistas por idade
	 * 
	 * @param idNivel
	 *            identifica o nível
	 * 
	 * */
	@Post("/ranking/ranking-por-idade")
	public void pesquisaRankingPorIdade(Integer idNivel, Integer ano) {

		if (idNivel != null) {

			if(ano == null || ano.equals(0) ){
				ano = Calendar.getInstance().get(Calendar.YEAR);
			}
			
			List<Pontuacao> pontosDosTenistasRankeadosIdade = pontuacaoDao.getRankingDeTenistasPorNivel(idNivel, ano);
			Nivel nivel = nivelDao.find(idNivel);

			result.include("pontosDosTenistasRankeados", pontosDosTenistasRankeadosIdade);
			result.include("numeroDeTenistas", pontosDosTenistasRankeadosIdade.size());
			result.include("nivel", nivel);
			result.include("ano", ano);

			result.redirectTo(RankingController.class).visualizaRankingPorIdade();

		}


	}

	/**
	 * Método que recupera o ranking feminino de tenistas por classe
	 * 
	 * @param idNivel
	 *            identifica o nível
	 * 
	 * */
	@Post("/ranking/ranking-feminino-por-nivel-tecnico")
	public void pesquisaRankingFemininoPorNivelTecnico(Integer idNivel, Integer ano) {

		if (idNivel != null) {

			if(ano == null || ano.equals(0) ){
				ano = Calendar.getInstance().get(Calendar.YEAR);
			}
			
			List<Pontuacao> pontosDosTenistasRankeadosIdade = pontuacaoDao.getRankingDeTenistasPorNivel(idNivel, ano);
			Nivel nivel = nivelDao.find(idNivel);

			result.include("pontosDosTenistasRankeados", pontosDosTenistasRankeadosIdade);
			result.include("numeroDeTenistas", pontosDosTenistasRankeadosIdade.size());
			result.include("nivel", nivel);
			result.include("ano", ano);

			result.redirectTo(RankingController.class).visualizaRankingFemininoPorNivelTecnico();

		}


	}

	/**
	 * Método que recupera o ranking feminino de tenistas por classe
	 * 
	 * @param idNivel
	 *            identifica o nível
	 * 
	 * */
	@Post("/ranking/ranking-masculino-por-nivel-tecnico")
	public void pesquisaRankingMasculinoPorNivelTecnico(Integer idNivel, Integer ano) {

		if (idNivel != null) {

			if(ano == null || ano.equals(0) ){
				ano = Calendar.getInstance().get(Calendar.YEAR);
			}
			
			List<Pontuacao> pontosDosTenistasRankeadosIdade = pontuacaoDao.getRankingDeTenistasPorNivel(idNivel, ano);
			Nivel nivel = nivelDao.find(idNivel);

			result.include("pontosDosTenistasRankeados", pontosDosTenistasRankeadosIdade);
			result.include("numeroDeTenistas", pontosDosTenistasRankeadosIdade.size());
			result.include("nivel", nivel);
			result.include("ano", ano);

			result.redirectTo(RankingController.class).visualizaRankingMasculinoPorNivelTecnico();

		}


	}

}
