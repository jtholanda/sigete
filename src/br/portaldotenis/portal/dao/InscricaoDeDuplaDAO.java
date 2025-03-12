package br.portaldotenis.portal.dao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.portaldotenis.portal.model.Inscricao;
import br.portaldotenis.portal.model.InscricaoDeDupla;
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
public class InscricaoDeDuplaDAO extends DAO<InscricaoDeDupla> {

	/**
	 * Método que verifica se uma inscricao já existe no sistemaas inscrições de
	 * acordo com o nível e torneio passado
	 * 
	 * @param inscricao
	 *            identifica a inscicao para ver se já existe algum com o
	 *            torneio, tenista e nivel especificados
	 * 
	 * */
	public boolean existeInscricao(InscricaoDeDupla inscricao) {
		TypedQuery<Inscricao> query = getEntityManager()
				.createQuery(
						"SELECT i from InscricaoDeDupla i where i.torneio = :torneio and (i.tenista = :tenista or i.tenista = :tenistaParceiro or i.tenistaParceiro = :tenistaParceiro or i.tenistaParceiro = :tenista) and i.nivel = :nivel and i.removida = false ",
						Inscricao.class);
		query.setParameter("torneio", inscricao.getTorneio());
		query.setParameter("tenista", inscricao.getTenista());
		query.setParameter("tenistaParceiro", inscricao.getTenistaParceiro());
		query.setParameter("nivel", inscricao.getNivel());

		try {
			if (query.getResultList().size() > 0) {
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

	public List<InscricaoDeDupla> getInscricoesPorTorneio(Torneio torneio) {

		TypedQuery<InscricaoDeDupla> query = null;

		query = getEntityManager()
				.createQuery(
						"SELECT i from InscricaoDeDupla i where i.torneio = :torneio and i.removida = false order by i.nivel, i.tenista.nome ",
						InscricaoDeDupla.class);
		query.setParameter("torneio", torneio);

		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}finally{
			close();
		}

	}

	/** Método que retorna do banco de dados todas as inscrições de um nível em um certo torneio, pode ser ordenado pelo cabeça de chave quando usado para gerar chaveamento ou pelo id normalmente */
	public List<InscricaoDeDupla> getInscricoesPorNivelETorneio(Nivel nivel, Torneio torneio, Integer orderBy) {
	
		TypedQuery<InscricaoDeDupla> query = null;
		StringBuilder consulta = new StringBuilder(
				"SELECT i from InscricaoDeDupla i where i.torneio = :torneio and i.nivel = :nivel and i.removida = false and i.confirmada = true order by ");
		if(orderBy == InscricaoDAO.CABECA_DE_CHAVE_NIVEL_PONTOS_CATEGORIA_PRINCIPAL){
			consulta.append("i.numeroCabecaDeChave asc");
		}else{
			consulta.append("i.id asc");
		}
		
		query = getEntityManager().createQuery(consulta.toString(), InscricaoDeDupla.class);
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
	
	/** Método que retorna do banco de dados todas as inscrições de duplas de um nível em um certo torneio e no grupo específico quando torneio tem fase de grupo anterior */
	public List<InscricaoDeDupla> getInscricoesPorNivelTorneioENumeroGrupo(
			Nivel nivel, Torneio torneio, Integer numeroGrupo) {
		
		TypedQuery<InscricaoDeDupla> query = null;
		StringBuilder consulta = new StringBuilder(
				"SELECT i from InscricaoDeDupla i where i.torneio = :torneio and i.nivel = :nivel and i.numeroGrupo = :numeroGrupo and i.removida = false and i.confirmada = true order by i.id asc ");
		
		query = getEntityManager().createQuery(consulta.toString(), InscricaoDeDupla.class);
		query.setParameter("torneio", torneio);
		query.setParameter("nivel", nivel);
		query.setParameter("numeroGrupo", numeroGrupo);

		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}finally{
			close();
		}

	}
	

	/** Método que retorna do banco de dados todas as inscrições de um nível em um certo torneio, pode ser ordenado pelo cabeça de chave quando usado para gerar chaveamento ou pelo id normalmente */
	public List<InscricaoDeDupla> getInscricoesParaChaveamentoDeDupla(Nivel nivel, Torneio torneio) {
	
		TypedQuery<InscricaoDeDupla> query = null;
		StringBuilder consulta = new StringBuilder(
				"SELECT i from InscricaoDeDupla i where i.torneio = :torneio and i.nivel = :nivel and i.removida = false and i.confirmada = true order by i.numeroCabecaDeChave asc, i.ordemAleatoria asc, i.id desc");
		
		
		query = getEntityManager().createQuery(consulta.toString(), InscricaoDeDupla.class);
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

	/** Método que retorna do banco de dados todas as inscrições de um nível em um certo torneio, pode ser ordenado pelo cabeça de chave quando usado para gerar chaveamento do segundo estágio ou pelo id normalmente */
	public List<InscricaoDeDupla> getInscricoesParaChaveamentoDeDuplaSegundoEstagio(Nivel nivel, Torneio torneio) {
	
		TypedQuery<InscricaoDeDupla> query = null;
		StringBuilder consulta = new StringBuilder(
				"SELECT i from InscricaoDeDupla i where i.torneio = :torneio and i.nivel = :nivel and i.removida = false and i.confirmada = true and i.numeroCabecaDeChave2Estagio != null order by i.numeroCabecaDeChave2Estagio asc, i.ordemAleatoria asc, i.id desc");
		
		
		query = getEntityManager().createQuery(consulta.toString(), InscricaoDeDupla.class);
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
	 * Método que retorna a soma dos pontos do tenista em um nível e em um certo
	 * ano
	 * */
	public List<InscricaoDeDupla> getInscricoesPorTenistaNivelAno(Tenista tenista, Nivel nivel, Integer anoReferencia) {

		TypedQuery<InscricaoDeDupla> query = getEntityManager()
				.createQuery(
						"select i from InscricaoDeDupla i where (i.tenista = :tenista or i.tenistaParceiro = :tenista) and i.nivel = :nivel and i.anoReferencia = :ano and i.confirmada = true and i.removida = false ",
						InscricaoDeDupla.class);
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

	

}