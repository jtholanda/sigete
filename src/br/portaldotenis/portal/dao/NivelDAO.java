package br.portaldotenis.portal.dao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.portaldotenis.portal.model.Nivel;
import br.portaldotenis.portal.model.TipoEsporte;
import br.portaldotenis.portal.model.TipoNivel;

/**
 * Responsavel por acessar dados de Niveis no banco de dados
 * 
 * @author Thiago
 *
 */
@RequestScoped
public class NivelDAO extends DAO<Nivel> {
	/**
	 * Retorna uma lista de n�veis de acordo com o tipo informado
	 * 
	 * @param tipoNivel
	 *            dos N�veis que ser�o retornados
	 * @return lista de n�veis
	 */
	public List<Nivel> getNiveisPorTipo(TipoNivel tipoNivel){
		TypedQuery<Nivel> query = getEntityManager().createQuery("SELECT n from Nivel n where n.tipoNivel like :tipoNivel order by n.id",
				Nivel.class);
		query.setParameter("tipoNivel", tipoNivel);

		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}finally{
			close();
		}

		
	}
	

	/**
	 * Retorna uma lista de n�veis de acordo com o tipo informado e o esporte
	 * 
	 * @param tipoNivel
	 *            dos N�veis que ser�o retornados
	 * @param tipoEsporte
	 *            dos Esportes que ser�o retornados	 
	 * @return lista de n�veis
	 */
	public List<Nivel> getNiveisPorTipoNivelEsporte(TipoNivel tipoNivel, TipoEsporte tipoEsporte){
		TypedQuery<Nivel> query = getEntityManager().createQuery("SELECT n from Nivel n where n.tipoNivel like :tipoNivel and n.tipoEsporte like :tipoEsporte order by n.codigo",
				Nivel.class);
		query.setParameter("tipoNivel", tipoNivel);
		query.setParameter("tipoEsporte", tipoEsporte);

		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}finally{
			close();
		}

		
	}

	
	/**
	 * Retorna um n�vwl de acordo com a descri��o
	 * 
	 * @param tipoNivel
	 *            dos N�veis que ser�o retornados
	 * @return lista de n�veis
	 */
	public Nivel getNivelPorDescricao(String descricao){
		TypedQuery<Nivel> query = getEntityManager().createQuery("SELECT n from Nivel n where n.descricao like :descricao",
				Nivel.class);
		query.setParameter("descricao", descricao);

		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}finally{
			close();
		}

		
	}
	
	/**
	 * Retorna todos os n�veis diferentes de um passado no par�metro
	 * */

	public List<Nivel> getNiveisQuePossuemRankingDeSimples() {
		
		TypedQuery<Nivel> query = getEntityManager().createQuery("SELECT n from Nivel n where n.tipoNivel = :tecnico or n.tipoNivel = :idade or n.tipoNivel = :feminino order by n.id",
				Nivel.class);
		query.setParameter("tecnico", TipoNivel.TECNICO);
		query.setParameter("idade", TipoNivel.IDADE);
		query.setParameter("feminino", TipoNivel.FEMININO);

		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}finally{
			close();
		}

		
	}


	
}
