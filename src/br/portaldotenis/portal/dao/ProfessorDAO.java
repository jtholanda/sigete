package br.portaldotenis.portal.dao;

import javax.enterprise.context.RequestScoped;

import br.portaldotenis.portal.model.Professor;

/**
 * Responsavel por acessar dados de Professor no banco de dados
 * 
 * @author Gabriel Soares
 *
 */
@RequestScoped
public class ProfessorDAO extends DAO<Professor> {
}