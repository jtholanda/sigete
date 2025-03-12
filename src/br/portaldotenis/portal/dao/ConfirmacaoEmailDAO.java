package br.portaldotenis.portal.dao;

import javax.enterprise.context.RequestScoped;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.portaldotenis.portal.model.ConfirmacaoEmail;
import br.portaldotenis.portal.model.Tenista;
import br.portaldotenis.portal.util.EmailAction;

/**
 * Responsavel por acessar dados de ConfirmacaoEmail no banco de dados
 * 
 * @author Gabriel Soares
 *
 */
@RequestScoped
public class ConfirmacaoEmailDAO extends DAO<ConfirmacaoEmail> {

	//@Inject
	//private EntityManager manager;

	/**
	 * Pega um código de uma solicitação de um determinado Tenista, para uma determinada Ação
	 * 
	 * @param tenista
	 *            que fez a solicitação
	 * @param emailAction
	 *            a ação solicitada
	 * @return um objeto ConfirmacaoEmail, caso encontrado
	 */
	public ConfirmacaoEmail pegaCodigoExistente(Tenista tenista, EmailAction emailAction) {
		TypedQuery<ConfirmacaoEmail> query = getEntityManager()
				.createQuery(
						"SELECT ce FROM ConfirmacaoEmail ce WHERE ce.tenista.email = :email AND ce.emailAction = :action",
						ConfirmacaoEmail.class);
		query.setParameter("email", tenista.getEmail());
		query.setParameter("action", emailAction);
		try {
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}finally{
			close();
		}
	}

}
