package br.portaldotenis.portal.dao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.TypedQuery;

import br.portaldotenis.portal.model.Filiado;
import br.portaldotenis.portal.model.Tenista;

/**
 * Classe responsável por acessar dados de Tenista no banco de dados
 * 
 * @author Gabriel Soares
 * 
 */
@RequestScoped
public class FiliadoDAO extends DAO<Filiado> {

	public boolean isFiliado(Tenista tenista, int anoReferencia) {
		TypedQuery<Filiado> query = getEntityManager().createQuery(
				"SELECT f from Filiado f where f.tenista = :tenista and f.anoReferencia = :anoReferencia",
				Filiado.class);
		query.setParameter("tenista", tenista);
		query.setParameter("anoReferencia", anoReferencia);

		try{
			return !query.getResultList().isEmpty();
		}catch(Exception e){
			return false;
		}finally{
			close();
		}
	}

	public List<Filiado> getFiliadosPorAno(int anoAtual) {
		TypedQuery<Filiado> query = getEntityManager().createQuery(
				"SELECT f from Filiado f where f.anoReferencia = :anoReferencia order by f.tenista.nome",
				Filiado.class);
		query.setParameter("anoReferencia", anoAtual);

		try{
			return query.getResultList();
		}catch(Exception e){
			return null;
		}finally{
			close();
		}
	}


}
