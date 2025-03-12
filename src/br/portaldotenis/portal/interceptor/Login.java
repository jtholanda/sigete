package br.portaldotenis.portal.interceptor;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.caelum.vraptor.AroundCall;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.interceptor.SimpleInterceptorStack;
import br.portaldotenis.portal.dao.MidiaTorneioDAO;
import br.portaldotenis.portal.model.TenistaLogado;

/**
 * Interceptor que filtra todas as requisições Filter implementation class
 * Conexao
 */

// TODO implementação a ser revista, maneira de resolver o problema de pegar a
// sessão para várias ações do sistema
@Intercepts
@RequestScoped
public class Login {

	@Inject
	TenistaLogado tenistaLogado;

	@Inject
	Result result;
	
	@Inject
	MidiaTorneioDAO midiaTorneioDao;
	
	/*@Inject
	private Router router;
	
	@Inject
	private HttpServletRequest request;
	private Class<?> clazz;
	private Method invokedMethod;
	private Object[] arguments;
	*/
	@AroundCall
	public void intercept(SimpleInterceptorStack stack) {

		
		if (tenistaLogado.isLogado()) {
			result.include("tenistaLogado", tenistaLogado.getTenista());

			// Verifica se é administrador para alterar o formulário
			if (tenistaLogado.isAdmin()) {
				result.include("administrador", true);
				result.include("organizador", true);
				result.include("padrao", true);
			} else if (tenistaLogado.isOrganizador()) {
				result.include("organizador", true);
				result.include("padrao", true);
			} else {
				result.include("padrao", true);
			}

		} else {
			result.include("tenistaLogado", null);
			
		}

		// temporário
		System.gc();
		stack.next();
	}
	/*private String getURL() {
		return "http://" + request.getHeader("Host") + request.getContextPath() + getURI();
	}
	private String getURI() {
		return router.urlFor(clazz, invokedMethod, arguments);
	}*/
	
	
}
