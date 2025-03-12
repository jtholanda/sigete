package br.portaldotenis.portal.dao;

import java.util.Calendar;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.portaldotenis.portal.model.SituacaoTorneio;
import br.portaldotenis.portal.model.Tenista;
import br.portaldotenis.portal.model.Torneio;

/**
 * Responsavel por acessar dados dos Torneios no banco de dados
 * 
 * @author Thiago Holanda
 * 
 */
@RequestScoped
public class TorneioDAO extends DAO<Torneio> {

	/**
	 * Busca os próximos torneios, aqueles com situação a iniciar ou iniciado
	 * 
	 * 
	 */

	public List<Torneio> getProximosTorneios() {
		TypedQuery<Torneio> query = null;

			query = getEntityManager()
					.createQuery("SELECT t from Torneio t where (t.situacao like :aIniciar or t.situacao like:iniciado) and (t.aberto = true and t.rankingDeClube = false and t.confraternizacao = false) ORDER BY t.dataInicioInscricao", Torneio.class);
			query.setParameter("aIniciar", SituacaoTorneio.A_INICIAR);
			query.setParameter("iniciado", SituacaoTorneio.INICIADO);

		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}finally{
			close();
		}

	}
	/**
	 * Busca os próximas etapas internas de ranking de clube, aqueles com situação a iniciar ou iniciado
	 * 
	 */

	public List<Torneio> getProximasCompeticoesSemPontuacao() {
		TypedQuery<Torneio> query = null;

			query = getEntityManager()
					.createQuery("SELECT t from Torneio t where (t.situacao like :aIniciar or t.situacao like:iniciado) and (t.aberto = false or t.rankingDeClube = true or t.confraternizacao = true) ORDER BY t.dataInicioInscricao", Torneio.class);
			query.setParameter("aIniciar", SituacaoTorneio.A_INICIAR);
			query.setParameter("iniciado", SituacaoTorneio.INICIADO);

		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}finally{
			close();
		}

	}

	/**
	 * Busca os torneios anteriores, aqueles cancelados ou concluídos
	 * 
	 */

	public List<Torneio> getTorneiosAnteriores(Integer ano) {
		TypedQuery<Torneio> query = null;

		query = getEntityManager()
				.createQuery("SELECT t from Torneio t where (t.situacao like :cancelado or t.situacao like:concluido) and (t.aberto = true and t.rankingDeClube = false and t.confraternizacao = false) and YEAR(t.dataInicio) = :ano ORDER BY t.dataInicioInscricao", Torneio.class);
		query.setParameter("cancelado", SituacaoTorneio.CANCELADO);
		query.setParameter("concluido", SituacaoTorneio.CONCLUIDO);
		query.setParameter("ano", ano);

	try {
		return query.getResultList();
	} catch (NoResultException e) {
		return null;
	}finally{
		close();
	}


	}
	/**
	 * Busca as etapas internas de ranking de clube anteriores, aqueles cancelados ou concluídos
	 * 
	 */

	public List<Torneio> getCompeticoesSemPontuacoesAnteriores(Integer ano) {
		TypedQuery<Torneio> query = null;

		query = getEntityManager()
				.createQuery("SELECT t from Torneio t where (t.situacao like :cancelado or t.situacao like:concluido) and (t.aberto = false or t.rankingDeClube = true or t.confraternizacao = true) ORDER BY t.dataInicioInscricao", Torneio.class);
		query.setParameter("cancelado", SituacaoTorneio.CANCELADO);
		query.setParameter("concluido", SituacaoTorneio.CONCLUIDO);

	try {
		return query.getResultList();
	} catch (NoResultException e) {
		return null;
	}finally{
		close();
	}


	}
	/**
	 * Busca os torneios informando apenas o organizador
	 * 
	 * @param tenista
	 *            organizador do torneio
	 * 
	 */

	public List<Torneio> getTorneiosPorOrganizador(Tenista tenistaOrganizador) {
		
		TypedQuery<Torneio> query = null;

		query = getEntityManager()
				.createQuery("SELECT t from Torneio t where t.tenistaOrganizador = :tenistaOrganizador", Torneio.class);
		query.setParameter("tenistaOrganizador", tenistaOrganizador);

		
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}finally{
			close();
		}

	}
	
	/**
	 * Busca os torneio em aberto informando apenas o organizador
	 * 
	 * @param tenista
	 *            organizador do torneio
	 * 
	 */

	public List<Torneio> getTorneiosComInscricoesAbertasPorOrganizador(Tenista tenistaOrganizador, Calendar dataDeHoje) {
		
		TypedQuery<Torneio> query = null;

		query = getEntityManager()
				.createQuery("SELECT t from Torneio t where t.tenistaOrganizador = :tenistaOrganizador and t.dataInicioInscricao <=" +
						" :dataDeHoje and t.dataFimInscricao >= :dataDeHoje", Torneio.class);
		query.setParameter("tenistaOrganizador", tenistaOrganizador);
		query.setParameter("dataDeHoje", dataDeHoje);

		
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}finally{
			close();
		}

	}

	public List<Torneio> getTorneiosComInscricoesAbertas(Calendar dataDeHoje) {

		TypedQuery<Torneio> query = null;

		query = getEntityManager()
				.createQuery("SELECT t from Torneio t where t.dataInicioInscricao <=" +
						" :dataDeHoje and t.dataFimInscricao >= :dataDeHoje and t.situacao = :situacao and t.chaveGerada = false and t.aberto = true ", Torneio.class);
		
		query.setParameter("dataDeHoje", dataDeHoje);
		query.setParameter("situacao",SituacaoTorneio.A_INICIAR);

		
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}finally{
			close();
		}

	}

	/**
	 * Busca os torneios a situação
	 * 
	 * @param situacao identifica em que fase está o torneio
	 *            organizador do torneio
	 * 
	 */

	public List<Torneio> getTorneiosComPontuacaoPorSituacao(SituacaoTorneio situacao) {
		
		TypedQuery<Torneio> query = null;

		query = getEntityManager()
				.createQuery("SELECT t from Torneio t where t.situacao = :situacao and t.rankingDeClube = false and t.confraternizacao = false and t.aberto = true ", Torneio.class);
		query.setParameter("situacao", situacao);

		
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}finally{
			close();
		}

	}

	/**
	 * Busca os torneios anteriores, aqueles cancelados ou concluídos
	 * 
	 */

	public List<Torneio> getTorneiosAIniciarEIniciados() {
		TypedQuery<Torneio> query = null;

		query = getEntityManager()
				.createQuery("SELECT t from Torneio t where t.situacao like :aIniciar or t.situacao like:iniciado ORDER BY t.dataInicioInscricao", Torneio.class);
		query.setParameter("aIniciar", SituacaoTorneio.A_INICIAR);
		query.setParameter("iniciado", SituacaoTorneio.INICIADO);

	try {
		return query.getResultList();
	} catch (NoResultException e) {
		return null;
	}finally{
		close();
	}


	}
	public List<Torneio> getTorneiosComChaveGerada() {
		TypedQuery<Torneio> query = null;

		query = getEntityManager()
				.createQuery("SELECT t from Torneio t where t.situacao like:iniciado and t.chaveGerada = :chaveGerada", Torneio.class);
		query.setParameter("chaveGerada", true);
		query.setParameter("iniciado", SituacaoTorneio.INICIADO);

	try {
		return query.getResultList();
	} catch (NoResultException e) {
		return null;
	}finally{
		close();
	}	}
	/**
	 * Busca os torneios limitados por paramentros
	 * 
	 * 
	 */

	public List<Torneio> getTorneiosLimitados(int primeiro, int maximoElementosPagina) {
		TypedQuery<Torneio> query = null;

			query = getEntityManager()
					.createQuery("SELECT t from Torneio t where (t.aberto = true and t.rankingDeClube = false and t.confraternizacao = false) ORDER BY t.dataInicioInscricao desc", Torneio.class);
			query.setMaxResults(maximoElementosPagina);
			query.setFirstResult(primeiro);

		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}finally{
			close();
		}

	}

	
}