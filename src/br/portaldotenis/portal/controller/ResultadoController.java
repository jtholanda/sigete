package br.portaldotenis.portal.controller;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Result;
import br.portaldotenis.portal.dao.InscricaoDAO;
import br.portaldotenis.portal.dao.NivelDAO;
import br.portaldotenis.portal.dao.TorneioDAO;
import br.portaldotenis.portal.model.ClassificacaoDeGrupo;
import br.portaldotenis.portal.model.Inscricao;
import br.portaldotenis.portal.model.Nivel;
import br.portaldotenis.portal.model.TipoDeChave;
import br.portaldotenis.portal.model.Torneio;

/**
 * Controller responsável por atender os resultados do torneio
 * 
 * @author Thiago Holanda
 * 
 */
@Controller
public class ResultadoController {

	@Inject
	TorneioDAO torneioDao;

	@Inject
	private InscricaoDAO inscricaoDao;

	@Inject
	private Result result;

	@Inject
	private NivelDAO nivelDao;


	/**
	 * Método que redireciona para a tela de resultado do torneio
	 * 
	 * @param idTorneio
	 *            identifica o torneio que está a apresentar o seu resultado
	 * 
	 * */
	@Get("/resultado/resultado-do-torneio/{idTorneio}")
	public void panelResultadoDoTorneio(Long idTorneio) {

		Torneio torneio = torneioDao.busca(idTorneio);

		result.include("torneio", torneio);

	}

	/**
	 * Método que redireciona para a tela de classificados na fase de grupo
	 * 
	 * @param idTorneio
	 *            identifica o torneio que está a apresentar o seu resultado
	 * 
	 * */
	@Get("/resultado/classificados-fase-de-grupo/{idTorneio}")
	public void panelClassificadosFaseDeGrupo(Long idTorneio) {

		Torneio torneio = torneioDao.busca(idTorneio);

		result.include("torneio", torneio);

	}

	/**
	 * Método que carrega o resultado de um determinado torneio
	 * 
	 * @param idTorneio
	 *            identifica o torneio
	 * @param idNivel
	 *            identifica o nível que será utilizado para mostrar o resultado
	 *            do torneio
	 * 
	 * */
	@Transactional
	@Get("/resultado/resultado-do-torneio/{idTorneio}/{idNivel}")
	public void visualizaResultadoDoTorneio(String idTorneio, String idNivel) {

		ClassificacaoDeGrupo classificacaoDeGrupo = new ClassificacaoDeGrupo();
		Nivel nivel = nivelDao.busca(Integer.parseInt(idNivel));
		Torneio torneio = torneioDao.busca(Long.parseLong(idTorneio));
		List<Inscricao> inscricoes = inscricaoDao.getInscricoesPorNivelETorneio(nivel, torneio,
				InscricaoDAO.PONTOS_NO_TORNEIO);
		result.include("inscricoes", inscricoes);
		result.include("torneio", torneio);
		result.include("nivel", nivel);

		// se for um torneio de grupos incluimos o resultado classificatorio
		if (torneio.getTipoDeChave() == TipoDeChave.GRUPO) {
			result.include("classificacoesEmGrupo", classificacaoDeGrupo.classificaGrupo(nivel, torneio));
		}

		result.redirectTo(ResultadoController.class).panelResultadoDoTorneio(torneio.getId());

	}

	/**
	 * Método que carrega o resultado da fase de grupo
	 * 
	 * @param idTorneio
	 *            identifica o torneio
	 * @param idNivel
	 *            identifica o nível que será utilizado para mostrar o resultado
	 *            do torneio
	 * 
	 * */
	@Transactional
	@Get("/resultado/classificados-fase-de-grupo/{idTorneio}/{idNivel}")
	public void visualizaClassificadosFaseDeGrupo(String idTorneio, String idNivel) {

		Nivel nivel = nivelDao.busca(Integer.parseInt(idNivel));
		Torneio torneio = torneioDao.busca(Long.parseLong(idTorneio));
		List<Inscricao> inscricoes = inscricaoDao.getInscricoesPorNivelETorneio(nivel, torneio, InscricaoDAO.NUMERO_CABECA_DE_CHAVE_2_ESTAGIO);
		List<Integer> gruposDoNivelNoTorneio = inscricaoDao.getGruposPorNivelETorneio(nivel,torneio);
		result.include("inscricoes", inscricoes);
		result.include("torneio", torneio);
		result.include("nivel", nivel);
		result.include("gruposDoNivelNoTorneio",gruposDoNivelNoTorneio);


		result.redirectTo(ResultadoController.class).panelClassificadosFaseDeGrupo(torneio.getId());

	}

}
