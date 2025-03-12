package br.portaldotenis.portal.controller;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Result;
import br.portaldotenis.portal.controleacesso.LogadoRule;
import br.portaldotenis.portal.dao.PontuacaoDAO;
import br.portaldotenis.portal.model.Tenista;
import br.portaldotenis.portal.model.TenistaLogado;

/**
 * Controller responsável por atender a todas as requisições de recursos de
 * Pontuacao
 * 
 * @author Thiago Holanda
 * 
 */

@Controller
public class PontuacaoController {


	@Inject
	private Result result;
	@Inject
	private TenistaLogado tenistaLogado;
	@Inject
	private PontuacaoDAO pontuacaoDao;
	@Inject
	private LogadoRule logadoRule;

	
	/**
	 * Método responsável por redirecionar para a tela de pontuações do tenista
	 * 
	 * @param idTenista
	 *            identifica qual tenista será detalhado
	 * 
	 * */
	@Get("/pontuacao/meus-pontos")
	public void visualizaMeusPontos() {

		Tenista tenista;
		if(logadoRule.isAllowed()){

			tenista = tenistaLogado.getTenista();
			result.include("pontuacoes", pontuacaoDao.getPontuacoesPorTenista(tenista));
			
		}else{

			result.redirectTo(IndexController.class).mensagemErro("Você precisa estar logado no sistema.");

		}
	}
}
