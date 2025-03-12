package br.portaldotenis.portal.dao;

import javax.enterprise.context.RequestScoped;

import br.portaldotenis.portal.model.Cidade;

/**
 * Responsavel por acessar dados de Cidade no banco de dados
 * 
 * @author Gabriel Soares
 *
 */
@RequestScoped
public class CidadeDAO extends DAO<Cidade> {
}
