package br.portaldotenis.portal.dao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.portaldotenis.portal.model.JogoDeTorneio;
import br.portaldotenis.portal.model.JogoDeTorneioDeDupla;

/**
 * Responsavel por acessar dados dos jogos dos torneios no banco de dados
 * 
 * @author Thiago Holanda
 *
 */
@RequestScoped
public class JogoDeTorneioDeDuplaDAO extends DAO<JogoDeTorneioDeDupla> {
	
	/**
	 * Obt�m a lista de tenistas que est�o participando de um torneio de duplas em um certo n�vel
	 * 
	 * @param idNivel identifica o n�vel que ser� usado como param�tro para rankear os tenistas
	 * @param idTorneio identica o torneio que ser� buscado
	 *            
	 * @return a lista de tenista participante daquele torneio e naquele n�vel organizado pelo nome
	 */
	public List<JogoDeTorneio> getJogosNoTorneioPorNivel(Integer idNivel, Long idTorneio) {

		TypedQuery<JogoDeTorneio> query = getEntityManager().createQuery("SELECT j from JogoDeTorneioDeDupla j where j.torneio.id = :idTorneio and j.nivel.id = :idNivel" +
				" order by j.data desc, j.hora asc",
				JogoDeTorneio.class);
		
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

	public List<JogoDeTorneioDeDupla> getJogosDosTenistasNoTorneioDeDuplaPorNivelEFase(
			Long idTenista, Long idTenistaParceiro, Integer idNivel, Long idTorneio, Integer idFase) {
		
		TypedQuery<JogoDeTorneioDeDupla> query = getEntityManager()
				.createQuery(
						"SELECT j from JogoDeTorneioDeDupla j where (j.tenistaUm.id = :idTenista or j.tenistaDois.id = :idTenista) and j.torneio.id = :idTorneio and j.nivel.id = :idNivel and j.fase.id = :idFase"
								+ " order by j.data desc, j.hora asc", JogoDeTorneioDeDupla.class);

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
	
}