package br.portaldotenis.portal.dao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.portaldotenis.portal.model.MidiaTorneio;
import br.portaldotenis.portal.model.TipoMidiaTorneio;
import br.portaldotenis.portal.model.Torneio;

/**
 * Responsavel por acessar dados de Clube no banco de dados
 * 
 * @author Thiago Holanda
 *
 */
@RequestScoped
public class MidiaTorneioDAO extends DAO<MidiaTorneio> {
	
	/**
	 * Busca as imagens do torneio 
	 * 
	 * @param torneio
	 *            
	 * 
	 */

	public List<MidiaTorneio> getImagensPorTorneio(Long idTorneio) {
		
		TypedQuery<MidiaTorneio> query = null;

		query = getEntityManager()
				.createQuery("SELECT m from MidiaTorneio m where m.torneio.id = :idTorneio and m.tipoMidiaTorneio =:tipoMidiaTorneio", MidiaTorneio.class);
		query.setParameter("idTorneio", idTorneio);
		query.setParameter("tipoMidiaTorneio", TipoMidiaTorneio.IMAGEM);

		
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}finally{
			close();
		}

	}

	public MidiaTorneio getRegulamentoPorTorneio(Long idTorneio) {
		TypedQuery<MidiaTorneio> query = null;

		query = getEntityManager()
				.createQuery("SELECT m from MidiaTorneio m where m.torneio.id = :idTorneio and m.tipoMidiaTorneio =:tipoMidiaTorneio", MidiaTorneio.class);
		query.setParameter("idTorneio", idTorneio);
		query.setParameter("tipoMidiaTorneio", TipoMidiaTorneio.REGULAMENTO);

		
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}finally{
			close();
		}
	}

	public MidiaTorneio getBannerPorTorneio(Long idTorneio) {
		TypedQuery<MidiaTorneio> query = null;

		query = getEntityManager()
				.createQuery("SELECT m from MidiaTorneio m where m.torneio.id = :idTorneio and m.tipoMidiaTorneio =:tipoMidiaTorneio", MidiaTorneio.class);
		query.setParameter("idTorneio", idTorneio);
		query.setParameter("tipoMidiaTorneio", TipoMidiaTorneio.BANNER);

		
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}finally{
			close();
		}
	}

	public List<MidiaTorneio> getPatrociniosPorTorneio(Long idTorneio) {
		TypedQuery<MidiaTorneio> query = null;

		query = getEntityManager()
				.createQuery("SELECT m from MidiaTorneio m where m.torneio.id = :idTorneio and m.tipoMidiaTorneio =:tipoMidiaTorneio", MidiaTorneio.class);
		query.setParameter("idTorneio", idTorneio);
		query.setParameter("tipoMidiaTorneio", TipoMidiaTorneio.PATROCINIO);

		
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}finally{
			close();
		}
	}

	public List<MidiaTorneio> getVideosAtivosPorTorneio(Long idTorneio) {
		TypedQuery<MidiaTorneio> query = null;

		query = getEntityManager()
				.createQuery("SELECT m from MidiaTorneio m where m.torneio.id = :idTorneio and m.tipoMidiaTorneio = :video and m.ativo = :ativo", MidiaTorneio.class);
		
		query.setParameter("idTorneio", idTorneio);
		query.setParameter("video", TipoMidiaTorneio.VIDEO);
		query.setParameter("ativo", true);

		
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}finally{
			close();
		}

	}

	public List<MidiaTorneio> getMidiasPorTorneio(Long idTorneio) {
		
		TypedQuery<MidiaTorneio> query = null;

		query = getEntityManager()
				.createQuery("SELECT m from MidiaTorneio m where m.torneio.id = :idTorneio", MidiaTorneio.class);
		query.setParameter("idTorneio", idTorneio);

		
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}finally{
			close();
		}

	}
	
	public MidiaTorneio getPDFChaveamentoPorTorneio(Long idTorneio) {
		
		TypedQuery<MidiaTorneio> query = null;

		query = getEntityManager()
				.createQuery("SELECT m from MidiaTorneio m where m.torneio.id = :idTorneio and m.tipoMidiaTorneio =:tipoMidiaTorneio", MidiaTorneio.class);
		query.setParameter("idTorneio", idTorneio);
		query.setParameter("tipoMidiaTorneio", TipoMidiaTorneio.CHAVEAMENTO);

		
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}finally{
			close();
		}

	}
	
	public MidiaTorneio getPDFInscritosPorTorneio(Long idTorneio) {
		
		TypedQuery<MidiaTorneio> query = null;

		query = getEntityManager()
				.createQuery("SELECT m from MidiaTorneio m where m.torneio.id = :idTorneio and m.tipoMidiaTorneio =:tipoMidiaTorneio", MidiaTorneio.class);
		query.setParameter("idTorneio", idTorneio);
		query.setParameter("tipoMidiaTorneio", TipoMidiaTorneio.INSCRITOS);

		
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}finally{
			close();
		}

	}

	public List<MidiaTorneio> getImagens() {
		TypedQuery<MidiaTorneio> query = null;

		query = getEntityManager()
				.createQuery("SELECT m from MidiaTorneio m where m.tipoMidiaTorneio =:tipoMidiaTorneio order by m.id desc", MidiaTorneio.class);
		query.setParameter("tipoMidiaTorneio", TipoMidiaTorneio.IMAGEM);

		
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}finally{
			close();
		}


	}
	public List<MidiaTorneio> getVideosAtivos() {
		TypedQuery<MidiaTorneio> query = null;

		query = getEntityManager()
				.createQuery("SELECT m from MidiaTorneio m where m.tipoMidiaTorneio =:tipoMidiaTorneio and m.ativo = :ativo", MidiaTorneio.class);
		query.setParameter("tipoMidiaTorneio", TipoMidiaTorneio.VIDEO);
		query.setParameter("ativo", true);


		
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}finally{
			close();
		}


	}
	public List<MidiaTorneio> getBannersLimitado(int primeiro, int maximoElementosPagina) {
		TypedQuery<MidiaTorneio> query = null;


		query = getEntityManager()
				.createQuery("SELECT m from MidiaTorneio m where m.tipoMidiaTorneio =:tipoMidiaTorneio order by m.torneio.dataInicioInscricao desc", MidiaTorneio.class);
		query.setParameter("tipoMidiaTorneio", TipoMidiaTorneio.BANNER);
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