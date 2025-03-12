package br.portaldotenis.portal.dao;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.portaldotenis.portal.model.Fase;
import br.portaldotenis.portal.model.Sexo;
import br.portaldotenis.portal.model.Tenista;
import br.portaldotenis.portal.model.TipoUsuario;

/**
 * Classe responsável por acessar dados de Tenista no banco de dados
 * 
 * @author Gabriel Soares
 * 
 */
@RequestScoped
public class TenistaDAO extends DAO<Tenista> {

	/**
	 * Busca o Tenista usando o login e a senha
	 * 
	 * @param tenista
	 *            com login e senha
	 * 
	 * @return tenista, caso exista, com o login e senha
	 */
	public Tenista login(Tenista tenista) {
		TypedQuery<Tenista> query = getEntityManager()
				.createQuery(
						"SELECT t from Tenista t where t.email like :email and t.senha like :senha",
						Tenista.class);
		query.setParameter("email", tenista.getEmail());
		query.setParameter("senha", tenista.getSenha());

		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}finally{
			close();
		}

	}

	/**
	 * Busca um tenista, informando apenas o Email
	 * 
	 * @param email
	 *            do tenista que será procurado
	 * @return tenista proprietario do Email
	 */
	public Tenista getTenistaPorEmail(String email) {
		TypedQuery<Tenista> query = getEntityManager().createQuery(
				"SELECT t from Tenista t where t.email like :email",
				Tenista.class);
		query.setParameter("email", email);

		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}finally{
			close();
		}

	}

	/**
	 * Verifica se existe tenista com determinado login
	 * 
	 * @param username
	 *            login(Email) do tenista
	 * @return true caso exista o tenista com o login informado; e false caso
	 *         não exista.
	 */
	public Boolean existeTenistaComLogin(String username) {
		TypedQuery<Tenista> query = getEntityManager().createQuery(
				"SELECT t from Tenista t where t.email like :username",
				Tenista.class);
		query.setParameter("username", username);

		try{
			return !query.getResultList().isEmpty();
		}catch(Exception e){
			return null;
		}finally{
			close();
		}

	}

	/**
	 * Verifica se existe tenista com determinado CPF
	 * 
	 * @param cpf
	 *            do tenista
	 * @return true caso exista o tenista com o CPF informado; e false caso não
	 *         exista.
	 */
	public Boolean existeTenistaComCpf(String cpf) {
		TypedQuery<Tenista> query = getEntityManager().createQuery(
				"SELECT t from Tenista t where t.cpf like :cpf", Tenista.class);
		query.setParameter("cpf", cpf);

		try{
		return !query.getResultList().isEmpty();
		}catch(Exception e){
			return null;
		}finally{
			close();
		}

	}

	/**
	 * Retorna um tenista de acordo com o nome informado
	 * 
	 * @param nomeTenista
	 *            informa o paramêtro que será usado como busca para procurar o
	 *            tenista
	 * 
	 * @return o tenista encontrado
	 */
	public Tenista getTenistaPorNome(String nomeTenista) {

		TypedQuery<Tenista> query = getEntityManager().createQuery(
				"SELECT t from Tenista t where t.email like :nomeTenista",
				Tenista.class);
		query.setParameter("nomeTenista", nomeTenista);

		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}finally{
			close();
		}

	}

	/**
	 * Obtém o ranking geral de tenistas
	 * 
	 * 
	 * @return a lista de tenista organizado pelo nível e pontuação
	 */
	public List<Tenista> getRankingDeTenistas() {

		TypedQuery<Tenista> query = getEntityManager()
				.createQuery(
						"SELECT t from Tenista t where t.participaDeRanking = true and t.pontosCategoriaPrincipal != 0 order by t.nivelTecnicoPrincipal.id, t.pontosCategoriaPrincipal desc, t.id ",
						Tenista.class);

		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}finally{
			close();
		}

	}

	/**
	 * Obtém o ranking por nível de tenistas
	 * 
	 * @param idNivel
	 *            identifica o nível que será usado como paramêtro para rankear
	 *            os tenistas
	 * 
	 * @return a lista de tenista organizado pelo nível e pontuação
	 */
	public List<Tenista> getRankingDeTenistasPorNivel(Integer idNivel) {

		TypedQuery<Tenista> query = getEntityManager()
				.createQuery(
						"SELECT t from Tenista t where nivelTecnicoPrincipal.id = :idNivel and t.participaDeRanking = true order by t.pontosCategoriaPrincipal desc, t.id ",
						Tenista.class);

		query.setParameter("idNivel", idNivel);

		try {
			return query.getResultList();
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
	public List<Tenista> getRankingDeTenistasPorIdade(Integer idNivel) {

		TypedQuery<Tenista> query = getEntityManager()
				.createQuery(
						"SELECT t from Tenista t, Pontuacao p where p.tenista = t and p.nivel.id = :idNivel"
								+ " and t.participaDeRanking = true and p.pontos != 0 order by p.pontos desc ",
						Tenista.class);

		query.setParameter("idNivel", idNivel);

		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}finally{
			close();
		}


	}

	/**
	 * Obtém o ranking feminino
	 * 
	 * @param idNivel
	 *            identifica o nível que será usado como paramêtro para rankear
	 *            os tenistas
	 * 
	 * @return a lista de tenista organizado pela pontuação registrada para o
	 *         sexo feminino
	 */
	public List<Tenista> getRankingFeminino() {

		TypedQuery<Tenista> query = getEntityManager()
				.createQuery(
						"SELECT t from Tenista t where t.participaDeRanking = true and t.sexo like :sexo and t.pontosCategoriaPrincipal != 0 order by t.nivelTecnicoPrincipal.id, t.pontosCategoriaPrincipal desc ",
						Tenista.class);
		query.setParameter("sexo", Sexo.FEMININO);

		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}finally{
			close();
		}


	}

	/**
	 * Verifica se existe tenista com o nome do ranking informado
	 * 
	 * @param nomeRanking
	 *            identifica o nome do ranking que será usado como paramêtro
	 *            para encontrar um tenista
	 * 
	 * @return true se encontrar um tenista
	 */
	public Boolean existeTenistaComNomeRanking(String nomeRanking) {
		TypedQuery<Tenista> query = getEntityManager()
				.createQuery(
						"SELECT t from Tenista t where t.nomeRanking like :nomeRanking",
						Tenista.class);
		query.setParameter("nomeRanking", nomeRanking);

		try{
			return !query.getResultList().isEmpty();
		}catch(Exception e){
			return null;
		}finally{
			close();
		}

	}
	

	/**
	 * Obtém a lista de tenistas que estão participando de um torneio em um
	 * certo nível
	 * 
	 * @param idNivel
	 *            identifica o nível que será usado como paramêtro para rankear
	 *            os tenistas
	 * @param idTorneio
	 *            identica o torneio que será buscado
	 * 
	 * @return a lista de tenista participante daquele torneio e naquele nível
	 *         organizado pelo nome
	 */
	public List<Tenista> getTenistasInscritosConfirmadosNoTorneioPorNivel(Integer idNivel,
			Long idTorneio) {

		TypedQuery<Tenista> query = getEntityManager()
				.createQuery(
						"SELECT t from Tenista t, Inscricao i where (i.tenista = t or i.tenistaParceiro = t) and i.nivel.id = :idNivel"
								+ " and i.torneio.id = :idTorneio and i.removida = false and i.confirmada = true order by t.nome asc",
						Tenista.class);

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

	/**
	 * Obtém a lista de tenistas que estão participando de um torneio em um
	 * certo nível e alocados em um grupo de torneio de misto de 2 estágios
	 * 
	 * @param idNivel
	 *            identifica o nível que será usado como paramêtro para rankear
	 *            os tenistas
	 * @param idTorneio
	 *            identica o torneio que será buscado
	 * 
	 * @param faseGrupo
	 *            identica o torneio que será buscado	 
	 *      
	 * 
	 * 
	 * 
	 * @return a lista de tenista participante daquele torneio e naquele nível
	 *         organizado pelo nome
	 */

	public List<Tenista> getTenistasInscritosConfirmadosNoTorneioPorNivelNumeroGrupo(
			Integer idNivel, Long idTorneio, Integer numeroGrupo  ) {

		TypedQuery<Tenista> query = getEntityManager()
				.createQuery(
						"SELECT t from Tenista t, Inscricao i where (i.tenista = t) and i.nivel.id = :idNivel"
								+ " and i.torneio.id = :idTorneio and i.numeroGrupo = :numeroGrupo and i.removida = false and i.confirmada = true order by t.nome asc",
						Tenista.class);

		query.setParameter("idNivel", idNivel);
		query.setParameter("idTorneio", idTorneio);
		query.setParameter("numeroGrupo", numeroGrupo);

		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}finally{
			close();
		}

	}

	
	/**
	 * Obtém a lista de tenistas que estão participando de um torneio em um
	 * certo nível
	 * 
	 * @param idNivel
	 *            identifica o nível que será usado como paramêtro para rankear
	 *            os tenistas
	 * @param idTorneio
	 *            identica o torneio que será buscado
	 * 
	 * @return a lista de tenista participante daquele torneio e naquele nível
	 *         organizado pelo nome
	 */
	public List<Tenista> getTenistasInscritosNoTorneioPorNivel(Integer idNivel,
			Long idTorneio) {

		TypedQuery<Tenista> query = getEntityManager()
				.createQuery(
						"SELECT t from Tenista t, Inscricao i where (i.tenista = t or i.tenistaParceiro = t) and i.nivel.id = :idNivel"
								+ " and i.torneio.id = :idTorneio and i.removida = false order by t.nome asc",
						Tenista.class);

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

	/**
	 * Método que consulta no banco de dados uma lista de tenista por nível
	 * 
	 * @param idNivel
	 *            identifica o nível que será utilizado como critério
	 * */
	public List<Tenista> getTenistasPorNivelTenis(Integer idNivel) {

		TypedQuery<Tenista> query = getEntityManager()
				.createQuery(
						"SELECT t from Tenista t where nivelTecnicoPrincipal.id = :idNivel order by t.nome asc, t.sobreNome",
						Tenista.class);

		query.setParameter("idNivel", idNivel);

		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}finally{
			close();
		}

	}
	
	/**
	 * Método que consulta no banco de dados uma lista de tenista por nível
	 * 
	 * @param idNivel
	 *            identifica o nível que será utilizado como critério
	 * */
	public List<Tenista> getTenistasPorNivelTecnico(Integer idNivel) {

		TypedQuery<Tenista> query = getEntityManager()
				.createQuery(
						"SELECT t from Tenista t where (nivelTecnicoPrincipal.id = :idNivel or nivelTecnicoBeachTennis.id = :idNivel) order by t.nome asc, t.sobreNome",
						Tenista.class);

		query.setParameter("idNivel", idNivel);

		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}finally{
			close();
		}

	}

	/**recupera tenistas por nível paginado */
	public List<Tenista> getTenistasPorNivelTecnico(Integer idNivel, int primeiro, int maximoElementosPagina) {

		TypedQuery<Tenista> query = getEntityManager()
				.createQuery(
						"SELECT t from Tenista t where (nivelTecnicoPrincipal.id = :idNivel or nivelTecnicoBeachTennis.id = :idNivel) and t.ativo = true order by t.nome asc, t.sobreNome",
						Tenista.class);

		query.setParameter("idNivel", idNivel);

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

	/**
	 * Método que consulta no banco de dados uma lista de tenista por nível e nome
	 * 
	 * @param idNivel
	 *            identifica o nível que será utilizado como critério
	 * */
	public List<Tenista> getTenistasPorNivelTecnicoENome(Integer idNivel, String nome) {

		TypedQuery<Tenista> query = getEntityManager()
				.createQuery(
						"SELECT t from Tenista t where (nivelTecnicoPrincipal.id = :idNivel or nivelTecnicoBeachTennis.id = :idNivel) and (t.nome like :nome or t.sobreNome like :nome or t.nomeRanking like :nome) order by t.nome asc, t.sobreNome asc",
						Tenista.class);

		query.setParameter("idNivel", idNivel);
		query.setParameter("nome", "%"+nome+"%");

		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}finally{
			close();
		}

	}

	/** recupera os tenistas por nome e nível técnico paginado*/
	public List<Tenista> getTenistasPorNivelTecnicoENome(Integer idNivel, String nome, int primeiro, int maximoElementosPagina) {
		TypedQuery<Tenista> query = getEntityManager()
				.createQuery(
						"SELECT t from Tenista t where (nivelTecnicoPrincipal.id = :idNivel or nivelTecnicoBeachTennis.id = :idNivel) and (t.nome like :nome or t.sobreNome like :nome or t.nomeRanking like :nome) and t.ativo = true order by t.nome asc, t.sobreNome asc",
						Tenista.class);

		query.setParameter("idNivel", idNivel);
		query.setParameter("nome", "%"+nome+"%");
		
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

	/**
	 * Obtém a lista de tenistas que estão participando de um torneio
	 * 
	 * @param idTorneio
	 *            identica o torneio que será buscado
	 * 
	 * @return a lista de tenista participante daquele torneio e naquele nível
	 *         organizado pelo nome
	 */

	public List<Tenista> getTenistasInscritosConfirmadosNoTorneio(Long idTorneio) {
		TypedQuery<Tenista> query = getEntityManager()
				.createQuery(
						"SELECT t from Tenista t, Inscricao i where (i.tenista = t or i.tenistaParceiro = t) and i.torneio.id = :idTorneio and i.removida = false and i.confirmada = true order by t.nome asc",
						Tenista.class);

		query.setParameter("idTorneio", idTorneio);

		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}finally{
			close();
		}

	}

	/**
	 * Obtém a lista de tenistas que estão participando de um torneio
	 * 
	 * @param idTorneio
	 *            identica o torneio que será buscado
	 * 
	 * @return a lista de tenista participante daquele torneio e naquele nível
	 *         organizado pelo nome
	 */

	public List<Tenista> getTenistasInscritosNoTorneio(Long idTorneio) {
		TypedQuery<Tenista> query = getEntityManager()
				.createQuery(
						"SELECT t from Tenista t, Inscricao i where (i.tenista = t or i.tenistaParceiro = t) and i.torneio.id = :idTorneio and i.removida = false order by t.nome asc",
						Tenista.class);

		query.setParameter("idTorneio", idTorneio);

		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}finally{
			close();
		}

	}

	/**
	 * Obtém a lista de tenistas que já participaram de algum torneio
	 *
	 * */
	public List<Tenista> getTenistasComParticipacaoEmTorneios(Integer idNivel) {
		TypedQuery<Tenista> query = getEntityManager()
				.createQuery(
						"SELECT t from Tenista t, Inscricao i where i.tenista = t and i.removida = false and i.confirmada = true and i.nivel.id = :idNivel order by t.nome asc",
						Tenista.class);
		query.setParameter("idNivel", idNivel);

		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}finally{
			close();
		}


	
	
	}
	/**
	 * Obtém a lista de tenistas que já participaram de algum torneio
	 *
	 * */
	public List<Tenista> getTenistasComParticipacaoEmTorneiosDeDuplas(Integer idNivel) {
		TypedQuery<Tenista> query = getEntityManager()
				.createQuery(
						"SELECT t from Tenista t, InscricaoDeDupla i where (i.tenista = t or i.tenistaParceiro = t ) and i.removida = false and i.confirmada = true and i.nivel.id = :idNivel order by t.nome asc",
						Tenista.class);
		query.setParameter("idNivel", idNivel);

		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}finally{
			close();
		}


	
	
	}
	/**
	 * Obtém a lista de tenistas por nome
	 *
	 * */
	public List<Tenista> getTenistasPorNome(String nome) {
		TypedQuery<Tenista> query = getEntityManager()
				.createQuery(
						"SELECT t from Tenista t where (lower(t.nome) like lower(:nome) or lower(t.sobreNome) like lower(:nome) or lower(t.nomeRanking) like lower(:nome)) order by t.nome asc, t.sobreNome asc",
						Tenista.class);

		query.setParameter("nome", "%"+nome+"%");

		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}finally{
			close();
		}

	}
	
	/**Recupera tenistas por nome paginados*/
	public List<Tenista> getTenistasPorNome(String nome, int primeiro, int maximoElementosPagina) {
		TypedQuery<Tenista> query = getEntityManager()
				.createQuery(
						"SELECT t from Tenista t where (lower(t.nome) like lower(:nome) or lower(t.sobreNome) like lower(:nome) or lower(t.nomeRanking) like lower(:nome)) and t.ativo = true order by t.nome asc, t.sobreNome asc",
						Tenista.class);

		query.setParameter("nome", "%"+nome+"%");
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

	public List<Tenista> getTenistasDesativados() {
		TypedQuery<Tenista> query = getEntityManager()
				.createQuery(
						"SELECT t from Tenista t where t.ativo = false order by t.nome asc, t.sobreNome asc",
						Tenista.class);
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}finally{
			close();
		}
	}

	public List<Tenista> getTenistasDesconfirmados() {
		TypedQuery<Tenista> query = getEntityManager()
				.createQuery(
						"SELECT t from Tenista t where t.confirmado = false order by t.nome asc, t.sobreNome asc",
						Tenista.class);
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}finally{
			close();
		}
	}
	/** Recupera os tenistas ativos*/
	public List<Tenista> getTenistasAtivos() {
		TypedQuery<Tenista> query = getEntityManager()
				.createQuery(
						"SELECT t from Tenista t where t.ativo = true order by t.nome asc, t.sobreNome asc",
						Tenista.class);
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}finally{
			close();
		}
	}
	/** recupera os tenistas ativos paginado*/
	public List<Tenista> getTenistasAtivos(int primeiro, int maximoElementosPagina) {
		TypedQuery<Tenista> query = getEntityManager()
				.createQuery(
						"SELECT t from Tenista t where t.ativo = true order by t.nome asc, t.sobreNome asc",
						Tenista.class);

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

	public List<Tenista> getTenistasPorTipo(TipoUsuario tipoUsuario) {
		TypedQuery<Tenista> query = getEntityManager()
				.createQuery(
						"SELECT t from Tenista t where t.tipo = :tipo order by t.nome asc, t.sobreNome asc",
						Tenista.class);
		query.setParameter("tipo", tipoUsuario);

		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}finally{
			close();
		}
	}

	public List<Tenista> getOrganizadores() {
		List <Tenista> organizadores = getTenistasPorTipo(TipoUsuario.ORGANIZADOR);
		List <Tenista> administradores = getTenistasPorTipo(TipoUsuario.ADMIN);
		organizadores.addAll(administradores);
		return organizadores;
	}

	public List<Tenista> getTenistasFemininosComParticipacaoEmTorneios(Integer idNivel) {
		TypedQuery<Tenista> query = getEntityManager()
				.createQuery(
						"SELECT t from Tenista t, Inscricao i where i.tenista = t and i.removida = false and i.confirmada = true and i.nivel.id = :idNivel order by t.nome asc",
						Tenista.class);
		query.setParameter("idNivel", idNivel);

		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}finally{
			close();
		}
	}

	public List<Tenista> getTenistasSuspensos() {
		TypedQuery<Tenista> query = getEntityManager()
				.createQuery(
						"SELECT t from Tenista t where t.suspenso = true order by t.nome asc, t.sobreNome asc",
						Tenista.class);
		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}finally{
			close();
		}
	}

	public List<Tenista> getTenistasPorNivelESexo(Integer idNivel, Sexo sexo) {
		// TODO Auto-generated method stub
		TypedQuery<Tenista> query = getEntityManager()
				.createQuery(
						
						"SELECT t from Tenista t where nivelTecnicoPrincipal.id = :idNivel and t.sexo like :sexo and t.ativo = true order by t.nome asc, t.sobreNome asc",
						Tenista.class);

		query.setParameter("idNivel", idNivel);
		query.setParameter("sexo", sexo);

		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}finally{
			close();
		}
	}

	public List<Tenista> getTenistasPorSexo(Sexo sexo) {
		// TODO Auto-generated method stub
		TypedQuery<Tenista> query = getEntityManager()
				.createQuery(
						
						"SELECT t from Tenista t where t.sexo like :sexo and t.ativo = true order by t.nome asc, t.sobreNome asc",
						Tenista.class);

		query.setParameter("sexo", sexo);

		try {
			return query.getResultList();
		} catch (NoResultException e) {
			return null;
		}finally{
			close();
		}
	}







}
