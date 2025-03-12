package br.portaldotenis.portal.dao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.portaldotenis.portal.model.Nivel;
import br.portaldotenis.portal.model.Pontuacao;
import br.portaldotenis.portal.model.Tenista;

/**
 * Responsavel por acessar dados das Fases do torneio no banco de dados
 * 
 * @author Thiago Holanda
 * 
 */
@RequestScoped
public class PontuacaoDAO extends DAO<Pontuacao> {

	/**
	 * Método retorna a pontuacao do tenista, no nivel e no ano
	 * */
	public Pontuacao getPontuacao(Tenista tenista, Nivel nivel, Integer ano) {

		TypedQuery<Pontuacao> query = getEntityManager().createQuery(
				"SELECT p from Pontuacao p where p.tenista = :tenista and p.nivel = :nivel and p.ano = :ano",
				Pontuacao.class);

		query.setParameter("tenista", tenista);
		query.setParameter("nivel", nivel);
		query.setParameter("ano", ano);

		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}finally{
			close();
		}

	}

	/**
	 * Obtém o ranking por idade de tenistas
	 * 
	 * @param idNivel
	 *            identifica o nível que será usado como paramêtro para rankear
	 *            os tenistas
	 * 
	 * @return a lista de tenista organizado pela pontuação registrada
	 */
	public List<Pontuacao> getRankingDeTenistasPorNivel(Integer idNivel, Integer ano) {

		String sql;
		if(ano != 2016 && ano != 2017){
			sql = "SELECT p from Pontuacao p, Tenista t where p.tenista = t and p.nivel.id = :idNivel and p.ano = :ano"
					+ " and t.participaDeRanking = true and p.pontos != 0 order by p.pontos desc, p.tenista.nivelTecnicoPrincipal "; 
		}else{
			sql = "SELECT p from Pontuacao p, Tenista t, Filiado f where p.tenista = t and f.tenista = t and p.nivel.id = :idNivel and p.ano = :ano and f.anoReferencia = :ano"
					+ " and t.participaDeRanking = true and p.pontos != 0 order by p.pontos desc, p.tenista.nivelTecnicoPrincipal "; 
		}
		
		TypedQuery<Pontuacao> query = getEntityManager()
				.createQuery(sql,Pontuacao.class);

		query.setParameter("idNivel", idNivel);
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
	 * Método retorna as pontuacões do tenista incluindo os niveis e em todos os
	 * anos
	 * */
	public List<Pontuacao> getPontuacoesPorTenista(Tenista tenista) {

		TypedQuery<Pontuacao> query = getEntityManager()
				.createQuery(
						"SELECT p from Pontuacao p where p.tenista = :tenista and p.pontos != 0 order by p.ano desc, p.nivel.id desc",
						Pontuacao.class);

		query.setParameter("tenista", tenista);

		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}finally{
			close();
		}

	}
	


}
