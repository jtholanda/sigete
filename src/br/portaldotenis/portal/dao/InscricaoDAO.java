package br.portaldotenis.portal.dao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.portaldotenis.portal.model.Inscricao;
import br.portaldotenis.portal.model.Nivel;
import br.portaldotenis.portal.model.Tenista;
import br.portaldotenis.portal.model.Torneio;

/**
 * Responsavel por acessar dados das Inscrições nos torneios
 * 
 * @author Thiago Holanda
 * 
 */
@RequestScoped
public class InscricaoDAO extends DAO<Inscricao> {

	public static final Integer NIVEL_PONTOS_CATEGORIA_PRINCIPAL = 1;
	public static final Integer PONTOS_NO_TORNEIO = 2;
	public static final Integer CABECA_DE_CHAVE_NIVEL_PONTOS_CATEGORIA_PRINCIPAL = 3;
	public static final Integer NUMERO_DO_GRUPO = 4;
	public static final Integer NOME_TENISTA = 5;
	public static final Integer NUMERO_CABECA_DE_CHAVE_2_ESTAGIO = 6;
	
	public List<Inscricao> getInscricoesPorTorneio(Torneio torneio) {

		TypedQuery<Inscricao> query = null;

		query = getEntityManager()
				.createQuery(
						"SELECT i from Inscricao i, Nivel n where i.torneio = :torneio and i.nivel.id = n.id and i.removida = false order by i.nivel, i.tenista.nome ",
						Inscricao.class);
		query.setParameter("torneio", torneio);

		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}finally{
			close();
		}

	}
	public List<Inscricao> getInscricoesConfirmadasPorTorneio(Torneio torneio) {

		TypedQuery<Inscricao> query = null;

		query = getEntityManager()
				.createQuery(
						"SELECT i from Inscricao i where i.torneio = :torneio and i.removida = false and i.confirmada = true order by i.pontuacao desc, i.nivel, i.tenista.nome ",
						Inscricao.class);
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
	 * Método que obtem as inscrições de acordo com o nível e torneio passado
	 * 
	 * @param nivel
	 *            identifica o nivel que será consultado as inscrições
	 * @param torneio
	 *            identifica o torneio que desejamos buscar as inscrições
	 * 
	 * */
	public List<Inscricao> getInscricoesPorNivelETorneio(Nivel nivel, Torneio torneio, Integer orderBy) {

		TypedQuery<Inscricao> query = null;
		StringBuilder consulta = new StringBuilder(
				"SELECT i from Inscricao i where i.torneio = :torneio and i.nivel = :nivel and i.removida = false and i.confirmada = true order by ");

		if (orderBy == CABECA_DE_CHAVE_NIVEL_PONTOS_CATEGORIA_PRINCIPAL) {
			consulta.append("i.numeroCabecaDeChave asc, i.tenista.nivelTecnicoPrincipal asc, i.tenista.pontosCategoriaPrincipal desc, i.tenista.id asc");

		} else if (orderBy == NIVEL_PONTOS_CATEGORIA_PRINCIPAL) {
			consulta.append("i.tenista.nivelTecnicoPrincipal, i.tenista.pontosCategoriaPrincipal desc");
		} else if (orderBy == PONTOS_NO_TORNEIO) {
			consulta.append("i.pontuacao desc");
		} else if (orderBy == NUMERO_DO_GRUPO){
			consulta.append("i.numeroGrupo asc");			
		} else if (orderBy == NOME_TENISTA){
			consulta.append("i.nome desc");			
		} else if (orderBy == NUMERO_CABECA_DE_CHAVE_2_ESTAGIO){
			consulta.append("i.numeroCabecaDeChave2Estagio asc");			
		}
		else {
			consulta.append("i.tenista.nome");
		}

		query = getEntityManager().createQuery(consulta.toString(), Inscricao.class);
		query.setParameter("torneio", torneio);
		query.setParameter("nivel", nivel);

		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}finally{
			close();
		}
	}

	/**
	 * Método que verifica se uma inscricao já existe no sistemaas inscrições de
	 * acordo com o nível e torneio passado
	 * 
	 * @param inscricao
	 *            identifica a inscicao para ver se já existe algum com o
	 *            torneio, tenista e nivel especificados
	 * 
	 * */
	public boolean existeInscricao(Inscricao inscricao) {
		TypedQuery<Inscricao> query = getEntityManager()
				.createQuery(
						"SELECT i from Inscricao i where i.torneio = :torneio and i.tenista = :tenista and i.nivel = :nivel and i.removida = false ",
						Inscricao.class);
		query.setParameter("torneio", inscricao.getTorneio());
		query.setParameter("tenista", inscricao.getTenista());
		query.setParameter("nivel", inscricao.getNivel());

		try {
			if (query.getSingleResult() != null) {
				return true;
			} else {
				return false;
			}

		} catch (NoResultException e) {
			return false;
		}finally{
			close();
		}

	}

	/**
	 * Método que retorna a soma dos pontos do tenista em um nível e em um certo
	 * ano
	 * */
	public List<Inscricao> getInscricoesPorTenistaNivelAno(Tenista tenista, Nivel nivel, Integer anoReferencia) {

		TypedQuery<Inscricao> query = getEntityManager()
				.createQuery(
						"select i from Inscricao i where (i.tenista = :tenista or i.tenistaParceiro = tenista) and i.nivel = :nivel and i.anoReferencia = :ano and i.confirmada = true and i.removida = false ",
						Inscricao.class);
		query.setParameter("tenista", tenista);
		query.setParameter("nivel", nivel);
		query.setParameter("ano", anoReferencia);

		try {
			return query.getResultList();

		} catch (NoResultException e) {
			return null;
		}finally{
			close();
		}

	}
	/**
	 * Método que retorna do banco de dados uma inscrição baseada no nivel, no
	 * torneio e no tenista
	 * 
	 * @param nivel
	 *            identifica o nível da inscrição
	 * @param torneio
	 *            identifica o torneio da inscrição
	 * @param tenista
	 *            identifica o tenista da inscrição
	 * 
	 * */
	public Inscricao getInscricaoPorNivelTorneioETenista(Nivel nivel, Torneio torneio, Tenista tenista) {

		TypedQuery<Inscricao> query = null;
		StringBuilder consulta = new StringBuilder(
				"SELECT i from Inscricao i where i.torneio = :torneio and i.nivel = :nivel and i.tenista = :tenista and i.removida = false and i.confirmada = true");

		query = getEntityManager().createQuery(consulta.toString(), Inscricao.class);
		query.setParameter("torneio", torneio);
		query.setParameter("nivel", nivel);
		query.setParameter("tenista", tenista);

		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}finally{
			close();
		}

	}
	/**
	 * Método que retorna a lista base para gerar as chaves do torneio
	 * 
	 * 
	 * 
	 * */
	public List<Inscricao> getInscricoesParaChaveamento(Nivel nivel, Torneio torneio, Integer ano) {

		TypedQuery<Inscricao> query = null;
		StringBuilder consulta = new StringBuilder(
				"SELECT i from Inscricao i where i.torneio = :torneio and i.nivel = :nivel and i.removida = false and i.confirmada = true order by i.numeroCabecaDeChave asc, i.ordemAleatoria asc, i.id asc");
		query = getEntityManager().createQuery(consulta.toString(), Inscricao.class);
		query.setParameter("torneio", torneio);
		query.setParameter("nivel", nivel);
		//query.setParameter("ano", ano);

		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}finally{
			close();
		}

	}
	/**
	 * Método que retorna a lista base para gerar as chaves do torneio para o segundo estágio
	 * 
	 * 
	 * 
	 * */
	public List<Inscricao> getInscricoesParaChaveamentoSegundoEstagio(Nivel nivel, Torneio torneio, Integer ano) {

		TypedQuery<Inscricao> query = null;
		StringBuilder consulta = new StringBuilder(
				"SELECT i from Inscricao i where i.torneio = :torneio and i.nivel = :nivel and i.removida = false and i.confirmada = true and i.numeroCabecaDeChave2Estagio != null order by i.numeroCabecaDeChave2Estagio asc, i.ordemAleatoria asc, i.id asc");
		query = getEntityManager().createQuery(consulta.toString(), Inscricao.class);
		query.setParameter("torneio", torneio);
		query.setParameter("nivel", nivel);
		//query.setParameter("ano", ano);

		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}finally{
			close();
		}

	}
	/**
	 * Método que retorna as inscrições de um tenista
	 * */
	public List<Inscricao> getInscricoesPorTenista(Tenista tenista) {

		TypedQuery<Inscricao> query = null;

		query = getEntityManager()
				.createQuery(
						"SELECT i from Inscricao i where (i.tenista = :tenista or i.tenistaParceiro = :tenista) and i.confirmada = true and i.removida = false order by i.id desc",
						Inscricao.class);
		query.setParameter("tenista", tenista);

		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}finally{
			close();
		}


	}

	public Integer getNumeroDe1Lugar(Tenista tenista) {
		TypedQuery<Inscricao> query = getEntityManager().createQuery(
				"SELECT i from Inscricao i where (i.tenista = :tenista or i.tenistaParceiro = :tenista) and i.colocacao.id = 1", Inscricao.class);

		query.setParameter("tenista", tenista);

		try {
			return query.getResultList().size();
		} catch (NoResultException e) {
			return 0;
		}finally{
			close();
		}

	}

	public Integer getNumeroDe2Lugar(Tenista tenista) {
		TypedQuery<Inscricao> query = getEntityManager().createQuery(
				"SELECT i from Inscricao i where (i.tenista = :tenista or i.tenistaParceiro = :tenista) and i.colocacao.id = 2", Inscricao.class);

		query.setParameter("tenista", tenista);

		try {
			return query.getResultList().size();
		} catch (NoResultException e) {
			return 0;
		}finally{
			close();
		}

	}
	public List<Inscricao> getInscricoesPorTenistaTorneio(Tenista tenistaLogado, Torneio torneio) {
		TypedQuery<Inscricao> query = null;

		query = getEntityManager()
				.createQuery(
						"SELECT i from Inscricao i where i.tenista = :tenista and i.torneio = :torneio and i.removida = false order by i.nivel, i.tenista.nome ",
						Inscricao.class);
		query.setParameter("tenista", tenistaLogado);
		query.setParameter("torneio", torneio);

		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}finally{
			close();
		}
	}
	public List<Integer> getGruposPorNivelETorneio(Nivel nivel, Torneio torneio) {
		TypedQuery<Integer> query = null;
		StringBuilder consulta = new StringBuilder(
				"SELECT distinct i.numeroGrupo from Inscricao i where i.torneio = :torneio and i.nivel = :nivel and i.removida = false and i.confirmada = true order by i.numeroGrupo");


		query = getEntityManager().createQuery(consulta.toString(), Integer.class);
		query.setParameter("torneio", torneio);
		query.setParameter("nivel", nivel);

		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}finally{
			close();
		}
	}
	public List<Inscricao> getInscricoesDoGrupoPorNivelETorneio(Integer numeroGrupo, Nivel nivel,
			Torneio torneio) {
		TypedQuery<Inscricao> query = null;
		StringBuilder consulta = new StringBuilder(
				"SELECT i from Inscricao i where i.torneio = :torneio and i.nivel = :nivel and i.numeroGrupo = :numeroGrupo and i.removida = false and i.confirmada = true");

		query = getEntityManager().createQuery(consulta.toString(), Inscricao.class);
		query.setParameter("numeroGrupo", numeroGrupo);
		query.setParameter("nivel", nivel);
		query.setParameter("torneio", torneio);

		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}finally{
			close();
		}
	}


}
