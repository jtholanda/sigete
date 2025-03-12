package br.portaldotenis.portal.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

@RequestScoped
public abstract class DAO<T> {
	//@Inject
	//protected EntityManager manager;

	private static EntityManagerFactory factory = null;
	
	private EntityManager em = null;

//	public static String CONEXAO;
//	public static String USUARIO;
//	public static String SENHA;

	
	
//	public DAO() {
//		
//		Properties prop;
//		
//		try {
//			
//			prop = new Utils().getProp();
//			CONEXAO = prop.getProperty("conexao");
//			USUARIO = prop.getProperty("usuario");
//			SENHA = prop.getProperty("senha");
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}
	static {

		factory = Persistence.createEntityManagerFactory("default");

	}
	public EntityManager getEntityManager() {
		if (em == null) {
			em = factory.createEntityManager();
		}
		return em;
	}

	public void adiciona(T obj) {

		//EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

		//EntityManager em = emf.createEntityManager();
		
		em = getEntityManager();

		// manager.persist(obj);

		beginTransaction();
		//em.getTransaction().begin();

		em.persist(obj);
		em.getTransaction().commit();

		close();

		//emf.close();
	}

	public void remove(T obj) {

		//manager.remove(obj);
		em = getEntityManager();

		beginTransaction();
		//em.getTransaction().begin();

		// É preciso checar se a entidade está gerenciada
		em.remove(em.contains(obj) ? obj : em.merge(obj));
		em.getTransaction().commit();
		close();

	}

	public T atualiza(T obj) {
		//EntityManagerFactory emf = Persistence.createEntityManagerFactory("default");

		//EntityManager em = emf.createEntityManager();

		em = getEntityManager();

		beginTransaction();
		//em.getTransaction().begin();

		// manager.merge(obj);

		obj = em.merge(obj);

		em.getTransaction().commit();

		close();
		
		//emf.close();

		return obj;
	}

	public T busca(Object chave) {
		@SuppressWarnings("unchecked")
		Class<T> type = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
		//return manager.find(type, chave);
		T retorno = getEntityManager().find(type, chave);
		close();
		return retorno;

	}

	@SuppressWarnings("unchecked")
	public List<T> listaTodos() {
		Class<T> type = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
		//Query query = manager.createQuery("select x from " + type.getSimpleName() + " x" + " ORDER BY x.id");
		Query query = getEntityManager().createQuery("select x from " + type.getSimpleName() + " x" + " ORDER BY x.id");
		List<T> lista = query.getResultList();
		close();
		return lista;

	}

	@SuppressWarnings("unchecked")
	public List<T> listaTodos(String campo) {
		Class<T> type = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
		//Query query = manager.createQuery("select x from " + type.getSimpleName() + " x" + " ORDER BY x." + campo);
		Query query = getEntityManager().createQuery("select x from " + type.getSimpleName() + " x" + " ORDER BY x." + campo);
		List<T> lista = query.getResultList();
		close();
		return lista;

	}

	@SuppressWarnings("unchecked")
	public T find(Object chave) {
		Class<T> type = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[0];
		//return manager.find(type, chave);
		T retorno = getEntityManager().find(type, chave);
		close();
		return retorno;
	}
	protected void beginTransaction(){
		if(!em.getTransaction().isActive()){
			em.getTransaction().begin();
		}
	}
	public void close(){
		if(em != null){
			em.close();
			em = null;
		}
	}
	
}