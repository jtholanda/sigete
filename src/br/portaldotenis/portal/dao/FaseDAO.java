package br.portaldotenis.portal.dao;

import javax.enterprise.context.RequestScoped;

import br.portaldotenis.portal.model.Fase;

/**
 * Responsavel por acessar dados das Fases do torneio no banco de dados
 * 
 * @author Thiago Holanda
 *
 */
@RequestScoped
public class FaseDAO extends DAO<Fase> {
}