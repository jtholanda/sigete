package br.portaldotenis.portal.interceptor;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import br.com.caelum.vraptor.AroundCall;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.interceptor.SimpleInterceptorStack;

/**
 * Interceptor que filtra todas as requisi��es Filter implementation class
 * Conexao
 */

// TODO implementa��o a ser revista, maneira de resolver o problema de pegar a
// sess�o para v�rias a��es do sistema
@Intercepts
@RequestScoped
public class VariaveisSessao {

	
	@Inject
	@SessionScoped
	HttpSession sessaoCliente;

	@AroundCall
	public void intercept(SimpleInterceptorStack stack) {

		
		sessaoCliente.setAttribute("empresa", "Portal do T�nis Paraibano");
		sessaoCliente.setAttribute("email", "portaldotenispb@gmail.com");
		sessaoCliente.setAttribute("site", "www.portaldotenispb.com.br");
		sessaoCliente.setAttribute("contexto", "sigete");
		sessaoCliente.setAttribute("telefone", "(83) 9.8888-8321");
		sessaoCliente.setAttribute("esportista", "tenista");


		// tempor�rio
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
