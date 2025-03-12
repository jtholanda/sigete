package br.portaldotenis.portal.controleacesso;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.caelum.brutauth.auth.handlers.RuleHandler;
import br.com.caelum.vraptor.Result;
import br.portaldotenis.portal.controller.TenistaController;

@RequestScoped
public class LogadoHandler implements RuleHandler {

	@Inject
	private Result result;
	
	@Override
	public void handle() {
		result.redirectTo(TenistaController.class).login();
	}
}
