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
 * Controller respons�vel por atender a todas as requisi��es de recursos de
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
	 * M�todo respons�vel por redirecionar para a tela de pontua��es do tenista
	 * 
	 * @param idTenista
	 *            identifica qual tenista ser� detalhado
	 * 
	 * */
	@Get("/pontuacao/meus-pontos")
	public void visualizaMeusPontos() {

		Tenista tenista;
		if(logadoRule.isAllowed()){

			tenista = tenistaLogado.getTenista();
			result.include("pontuacoes", pontuacaoDao.getPontuacoesPorTenista(tenista));
			
		}else{

			result.redirectTo(IndexController.class).mensagemErro("Voc� precisa estar logado no sistema.");

		}
	}
}
