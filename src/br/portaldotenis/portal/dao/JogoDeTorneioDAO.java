package br.portaldotenis.portal.dao;

import java.util.Calendar;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.portaldotenis.portal.model.JogoDeTorneio;
import br.portaldotenis.portal.model.Tenista;
import br.portaldotenis.portal.model.Torneio;

/**
 * Responsavel por acessar dados dos jogos dos torneios no banco de dados
 * 
 * @author Thiago Holanda
 * 
 */
@RequestScoped
public class JogoDeTorneioDAO extends DAO<JogoDeTorneio> {

	/**
	 * Obtém a lista de jogos de um torneio em um certo nível
	 * 
	 * @param idNivel
	 *            identifica o nível que será usado como paramêtro para rankear
	 *            os tenistas
	 * @param idTorneio
	 *            identica o torneio que será buscado
	 * 
	 * @return a lista de jogos daquele torneio e naquele nível
	 *         organizado pelo nome
	 */
	public List<JogoDeTorneio> getJogosNoTorneioPorNivel(Integer idNivel, Long idTorneio) {

		TypedQuery<JogoDeTorneio> query = getEntityManager().createQuery(
				"SELECT j from JogoDeTorneio j where j.torneio.id = :idTorneio and j.nivel.id = :idNivel"
						+ " order by j.data desc, j.hora asc", JogoDeTorneio.class);

		query.setParameter("idNivel", idNivel);
		query.setParameter("idTorneio", idTorneio);

		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}finally{
			close();
		}


	}

	/**
	 * Obtém a lista de jogos de um torneio em um certo nível e fase
	 * 
	 * @param idNivel
	 *            identifica o nível que será usado como paramêtro para rankear
	 *            os tenistas
	 * @param idTorneio
	 *            identica o torneio que será buscado
	 * 
	 * @param idFase
	 *            identica a fase que será buscada
	 *	 
	 *
	 * @return a lista de jogos daquele torneio, naquele nível e na fase
	 *         organizado pelo nome
	 */
	public List<JogoDeTorneio> getJogosNoTorneioPorNivelFaseETenistas(Integer idNivel, Long idTorneio, Integer idFase, Long idTenistaUm, Long idTenistaDois) {

		TypedQuery<JogoDeTorneio> query = getEntityManager().createQuery(
				"SELECT j from JogoDeTorneio j where j.torneio.id = :idTorneio and j.nivel.id = :idNivel and j.fase.id = :idFase and "
				+ "(j.tenistaUm.id = :idTenistaUm or j.tenistaUm.id = :idTenistaDois) and (j.tenistaDois.id = :idTenistaDois or j.tenistaDois.id = :idTenistaUm) ", 
				JogoDeTorneio.class);

		query.setParameter("idNivel", idNivel);
		query.setParameter("idTorneio", idTorneio);
		query.setParameter("idFase", idFase);
		query.setParameter("idTenistaUm", idTenistaUm);
		query.setParameter("idTenistaDois", idTenistaDois);
		
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}finally{
			close();
		}


	}

	/**
	 * Obtém a lista de jogos de um torneio em um certo nível e fase
	 * 
	 * @param idNivel
	 *            identifica o nível que será usado como paramêtro para rankear
	 *            os tenistas
	 * @param idTorneio
	 *            identica o torneio que será buscado
	 * 
	 * @param idFase
	 *            identica a fase que será buscada
	 *	 
	 *
	 * @return a lista de jogos daquele torneio, naquele nível e na fase
	 *         organizado pelo nome
	 */
	public List<JogoDeTorneio> getJogosNoTorneioPorNivelFase(Integer idNivel, Long idTorneio, Integer idFase) {

		TypedQuery<JogoDeTorneio> query = getEntityManager().createQuery(
				"SELECT j from JogoDeTorneio j where j.torneio.id = :idTorneio and j.nivel.id = :idNivel and j.fase.id = :idFase) ", 
				JogoDeTorneio.class);

		query.setParameter("idNivel", idNivel);
		query.setParameter("idTorneio", idTorneio);
		query.setParameter("idFase", idFase);
		
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}finally{
			close();
		}


	}

	/**
	 * Obtém a lista de jogos do tenista
	 * 
	 * @param tenista
	 *            identifica o tenista que será usado como paramêtro para buscar
	 *            os jogos
	 * 
	 * @return a lista de jogos de uym tenista
	 */
	public List<JogoDeTorneio> getJogosDeTorneioDeUmTenista(Tenista tenista) {

		TypedQuery<JogoDeTorneio> query = getEntityManager()
				.createQuery(
						"SELECT j from JogoDeTorneio j where (j.tenistaUm = :tenista or j.tenistaDois = :tenista or j.tenistaParceiroUm = :tenista or j.tenistaParceiroDois = :tenista )"
								+ " order by j.data desc", JogoDeTorneio.class);

		query.setParameter("tenista", tenista);

		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}finally{
			close();
		}


	}
	
	/** recupera os jogos de um tenista paginado*/
	public List<JogoDeTorneio> getJogosDeTorneioDeUmTenista(Tenista tenista, int primeiro, int maximoElementosPagina) {

		TypedQuery<JogoDeTorneio> query = getEntityManager()
				.createQuery(
						"SELECT j from JogoDeTorneio j where (j.tenistaUm = :tenista or j.tenistaDois = :tenista or j.tenistaParceiroUm = :tenista or j.tenistaParceiroDois = :tenista )"
								+ " order by j.data desc", JogoDeTorneio.class);

		query.setParameter("tenista", tenista);
		query.setFirstResult(primeiro);
		query.setMaxResults(maximoElementosPagina);
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}finally{
			close();
		}
	}
	/**
	 * Obtém a lista de jogos de um torneio
	 * 
	 * @param torneio
	 *            identifica o torneio que será usado como paramêtro para buscar
	 *            os jogos
	 * 
	 * @return a lista de jogos do torneio
	 */
	public List<JogoDeTorneio> getJogosPorTorneio(Torneio torneio) {

		TypedQuery<JogoDeTorneio> query = getEntityManager().createQuery(
				"SELECT j from JogoDeTorneio j, Nivel n where j.nivel = n and j.torneio = :torneio " + " order by j.data desc, j.hora asc",
				JogoDeTorneio.class);

		query.setParameter("torneio", torneio);

		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}finally{
			close();
		}


	}

	/**
	 * Obtém a lista de jogos do banco de dados de acordo com o torneio e a data
	 * do jogo
	 * 
	 * @param torneio
	 *            identifica o objeto torneio
	 * @param dataDoJogo
	 *            identifica a data que será criterio da pesquisa
	 * 
	 *            *
	 */
	public List<JogoDeTorneio> getJogosNoTorneioPorData(Torneio torneio, Calendar dataDosJogos) {

		TypedQuery<JogoDeTorneio> query = getEntityManager().createQuery(
				"SELECT j from JogoDeTorneio j where j.torneio = :torneio "
						+ "and j.data = :dataDoJogo order by j.hora asc", JogoDeTorneio.class);

		query.setParameter("torneio", torneio);
		query.setParameter("dataDoJogo", dataDosJogos);

		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}finally{
			close();
		}


	}

	public List<JogoDeTorneio> getJogosDoTenistaNoTorneioPorNivel(Long idTenista, Integer idNivel, Long idTorneio) {

		TypedQuery<JogoDeTorneio> query = getEntityManager()
				.createQuery(
						"SELECT j from JogoDeTorneio j where (j.tenistaUm.id = :idTenista or j.tenistaDois.id = :idTenista) and j.torneio.id = :idTorneio and j.nivel.id = :idNivel"
								+ " order by j.data desc, j.hora asc", JogoDeTorneio.class);

		query.setParameter("idTenista", idTenista);
		query.setParameter("idNivel", idNivel);
		query.setParameter("idTorneio", idTorneio);

		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}finally{
			close();
		}


	}

	public List<JogoDeTorneio> getJogosDoTenistaNoTorneioPorNivelEFase(Long idTenista, Integer idNivel, Long idTorneio, Integer idFase) {

		TypedQuery<JogoDeTorneio> query = getEntityManager()
				.createQuery(
						"SELECT j from JogoDeTorneio j where (j.tenistaUm.id = :idTenista or j.tenistaDois.id = :idTenista) and j.torneio.id = :idTorneio and j.nivel.id = :idNivel and j.fase.id = :idFase"
								+ " order by j.data desc, j.hora asc", JogoDeTorneio.class);

		query.setParameter("idTenista", idTenista);
		query.setParameter("idNivel", idNivel);
		query.setParameter("idTorneio", idTorneio);
		query.setParameter("idFase", idFase);

		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}finally{
			close();
		}


	}

	/**
	 * 
	 * Verifica se já existe um jogo inserido no banco de dados
	 * 
	 * @param jogoDeTorneio identifica o jogo que está pronto para ser inserido
	 * 
	 * @return boolean para identificar a existência de um jogo semelhante
	 * 
	 * */
	public boolean existeJogoSemelhante(JogoDeTorneio jogoDeTorneio) {
		
		Integer idNivel = jogoDeTorneio.getNivel().getId();
		Long idTorneio = jogoDeTorneio.getTorneio().getId();
		Integer idFase= jogoDeTorneio.getFase().getId();
		Long idTenistaUm;
		Long idTenistaDois;
		try {

			idTenistaUm = jogoDeTorneio.getTenistaUm().getId();
			idTenistaDois = jogoDeTorneio.getTenistaDois().getId();
			
		} catch (Exception e) {
			// TODO: handle exception
			idTenistaUm = 0L;
			idTenistaDois = 0L;
		}
		
		if(getJogosNoTorneioPorNivelFaseETenistas(idNivel, idTorneio, idFase, idTenistaUm, idTenistaDois).size() >= 1){
			
			return true;
			
		}else{
		
			return false;
		}
	}

	public List<JogoDeTorneio> getJogosDoTorneioPorNivelEFase(Integer idNivel,
			Long idTorneio, Integer idFase) {
		TypedQuery<JogoDeTorneio> query = getEntityManager()
				.createQuery(
						"SELECT j from JogoDeTorneio j where j.torneio.id = :idTorneio and j.nivel.id = :idNivel and j.fase.id = :idFase"
								+ " order by j.data desc, j.hora asc", JogoDeTorneio.class);

		query.setParameter("idNivel", idNivel);
		query.setParameter("idTorneio", idTorneio);
		query.setParameter("idFase", idFase);

		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}finally{
			close();
		}
	}

	
	
	

}