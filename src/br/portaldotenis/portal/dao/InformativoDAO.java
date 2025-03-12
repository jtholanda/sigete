package br.portaldotenis.portal.dao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.TypedQuery;

import br.portaldotenis.portal.model.Informativo;
import br.portaldotenis.portal.model.Tenista;
import br.portaldotenis.portal.model.TipoInformativo;

/**
 * Classe responsável por acessar dados de Tenista no banco de dados
 * 
 * @author Gabriel Soares
 * 
 */
@RequestScoped
public class InformativoDAO extends DAO<Informativo> {

	
	public List<Informativo> getInformativosPorTipo(TipoInformativo tipoInformativo) {
		TypedQuery<Informativo> query = getEntityManager().createQuery(
				"SELECT i from Informativo i where i.tipoInformativo = :tipoInformativo order by i.data desc, i.id desc",
				Informativo.class);
		query.setParameter("tipoInformativo", tipoInformativo);

		try{
			return query.getResultList();
		}catch(Exception e){
			return null;
		}finally{
			close();
		}
	}

	public List<Informativo> getInformativosPorTipoEProprietario(TipoInformativo tipoInformativo, Tenista tenista) {
		TypedQuery<Informativo> query = getEntityManager().createQuery(
				"SELECT i from Informativo i where i.tipoInformativo = :tipoInformativo and i.tenista = :tenista order by i.data desc, i.id desc",
				Informativo.class);
		query.setParameter("tipoInformativo", tipoInformativo);
		query.setParameter("tenista", tenista);


		try{
			return query.getResultList();
		}catch(Exception e){
			return null;
		}finally{
			close();
		}	}

	public List<Informativo> getInformativosPorTipoLimitado(TipoInformativo tipoInformativo, int primeiro, int maximoElementosPagina ) {
		TypedQuery<Informativo> query = getEntityManager().createQuery(
				"SELECT i from Informativo i where i.tipoInformativo = :tipoInformativo order by i.data desc",
				Informativo.class);
		query.setParameter("tipoInformativo", tipoInformativo);
		query.setMaxResults(maximoElementosPagina);
		query.setFirstResult(primeiro);

		try{
			return query.getResultList();
		}catch(Exception e){
			return null;
		}finally{
			close();
		}
	}


}
