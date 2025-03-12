package br.portaldotenis.portal.dao;

import javax.enterprise.context.RequestScoped;

import br.portaldotenis.portal.model.Clube;

/**
 * Responsavel por acessar dados de Clube no banco de dados
 * 
 * @author Gabriel Soares
 *
 */
@RequestScoped
public class ClubeDAO extends DAO<Clube> {
}