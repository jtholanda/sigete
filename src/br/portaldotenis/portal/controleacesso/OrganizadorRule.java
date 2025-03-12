package br.portaldotenis.portal.controleacesso;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import br.com.caelum.brutauth.auth.annotations.HandledBy;
import br.com.caelum.brutauth.auth.rules.CustomBrutauthRule;
import br.portaldotenis.portal.model.TenistaLogado;

@RequestScoped
@HandledBy(NaoAutorizadoHandler.class)
public class OrganizadorRule implements CustomBrutauthRule {

	@Inject
	private TenistaLogado tenistaLogado;

	public boolean isAllowed() {
		return tenistaLogado.isOrganizador() || tenistaLogado.isAdmin();
	}
}
