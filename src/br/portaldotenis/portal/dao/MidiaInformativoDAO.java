package br.portaldotenis.portal.dao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.portaldotenis.portal.model.MidiaInformativo;
import br.portaldotenis.portal.model.MidiaTorneio;
import br.portaldotenis.portal.model.TipoMidiaTorneio;

/**
 * Responsavel por acessar dados de Clube no banco de dados
 * 
 * @author Thiago Holanda
 *
 */
@RequestScoped
public class MidiaInformativoDAO extends DAO<MidiaInformativo> {
	
	/**
	 * Busca as imagens do torneio 
	 * 
	 * @param torneio
	 *            
	 * 
	 */

	public List<MidiaInformativo> getMidiasPorInformativos(Long idInformativo) {
		
		TypedQuery<MidiaInformativo> query = null;

		query = getEntityManager()
				.createQuery("SELECT m from MidiaInformativo m where m.informativo.id = :idInformativo", MidiaInformativo.class);
		query.setParameter("idInformativo", idInformativo);

		
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}finally{
			close();
		}

	}

	}