package br.portaldotenis.portal.controleacesso;

import javax.inject.Inject;

import br.com.caelum.brutauth.auth.handlers.RuleHandler;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;

public class NaoAutorizadoHandler implements RuleHandler {

	@Inject
	private Result result;
	
	@Override
	public void handle() {
		result.use(Results.http()).sendError(403, "Usuário não autorizado");
	}

}
