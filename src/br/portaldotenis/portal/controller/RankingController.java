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
 * Controller respons�vel por gerenciar o ranking de pontua��o dos tenistas
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
	 * M�todo que redireciona para a tela de ranking geral de tenistas
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
	 * M�todo que recupera o ranking de tenistas do sexo feminino
	 * 
	 * @param idNivel
	 *            identifica o n�vel
	 * 
	 * */
	@Get("/ranking/ranking-feminino")
	public void visualizaRankingFeminino() {

		// TODO pesquisa na tabela de pontua��o
		List<Tenista> tenistasRankeados = tenistaDao.getRankingFeminino();

		result.include("tenistasRankeados", tenistasRankeados);
		result.include("numeroDeTenistas", tenistasRankeados.size());

	}

	/**
	 * M�todo que redireciona para a p�gina de pesquisa ranking por n�vel
	 * t�cnico
	 * 
	 * @param idNivel
	 *            identifica o n�vel
	 * 
	 * */

	@Get("/ranking/ranking-por-nivel-tecnico")
	public void visualizaRankingPorNivelTecnico() {

		List<Nivel> niveis = nivelDao.getNiveisPorTipo(TipoNivel.TECNICO);

		result.include("niveisTecnicos", niveis);

	}

	/**
	 * M�todo que redireciona para a p�gina de pesquisa ranking por n�vel
	 * t�cnico
	 * 
	 * @param idNivel
	 *            identifica o n�vel
	 * 
	 * */

	@Get("/ranking/ranking-feminino-por-nivel-tecnico")
	public void visualizaRankingFemininoPorNivelTecnico() {

		List<Nivel> niveis = nivelDao.getNiveisPorTipo(TipoNivel.FEMININO);

		result.include("niveisTecnicosFeminino", niveis);

	}

	/**
	 * M�todo que redireciona para a p�gina de pesquisa ranking por n�vel
	 * t�cnico
	 * 
	 * @param idNivel
	 *            identifica o n�vel
	 * 
	 * */

	@Get("/ranking/ranking-masculino-por-nivel-tecnico")
	public void visualizaRankingMasculinoPorNivelTecnico() {

		List<Nivel> niveis = nivelDao.getNiveisPorTipo(TipoNivel.MASCULINO);

		result.include("niveisTecnicosMasculino", niveis);

	}

	/**
	 * M�todo que redireciona para a p�gina de pesquisa ranking por idade
	 * 
	 * @param idNivel
	 *            identifica o n�vel
	 * 
	 * */

	@Get("/ranking/ranking-por-idade")
	public void visualizaRankingPorIdade() {

		List<Nivel> niveis = nivelDao.getNiveisPorTipo(TipoNivel.IDADE);

		result.include("niveisPorIdade", niveis);

	}
	
	/**
	 * M�todo que redireciona para a p�gina de pesquisa ranking por n�vel
	 * t�cnico
	 * 
	 * @param idNivel
	 *            identifica o n�vel
	 * 
	 * */

	@Get("/ranking/ranking-de-duplas-por-nivel-tecnico")
	public void visualizaRankingDeDuplasPorNivelTecnico() {

		List<Nivel> niveis = nivelDao.getNiveisPorTipo(TipoNivel.DUPLAS);

		result.include("niveisTecnicos", niveis);

	}

	// M�TODOS DE A��O

	/**
	 * M�todo que recupera o ranking de tenistas pelo n�vel
	 * 
	 * @param idNivel
	 *            identifica o n�vel
	 * @param categoria identifica se o rankign � de simples ou de duplas e qual o ano do ranking
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
	 * M�todo que recupera o ranking de tenistas por idade
	 * 
	 * @param idNivel
	 *            identifica o n�vel
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
	 * M�todo que recupera o ranking feminino de tenistas por classe
	 * 
	 * @param idNivel
	 *            identifica o n�vel
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
	 * M�todo que recupera o ranking feminino de tenistas por classe
	 * 
	 * @param idNivel
	 *            identifica o n�vel
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
