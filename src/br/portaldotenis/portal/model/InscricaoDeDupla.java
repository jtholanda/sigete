package br.portaldotenis.portal.model;


import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;


/**
 * Classe responsável por modelar uma inscrição de deuplas no torneio do sistema
 *  
 * @author Thiago Holanda
 *
 */

@Entity
@Inheritance(strategy =InheritanceType.JOINED)  
public class InscricaoDeDupla extends Inscricao {

	@NotNull (message = "Escolha um tenista parceiro")
	@ManyToOne
	Tenista tenistaParceiro;
	
	Integer pontuacaoDaDupla;


	public String getDupla(){
		return tenista.getNomeCompleto() + " / " + tenistaParceiro.getNomeCompleto();
	}
	
	/**
	 * @return tenistaParceiro do objeto
	 */
	public Tenista getTenistaParceiro() {
		return tenistaParceiro;
	}

	/**
	 * @param tenistaParceiro defini tenistaParceiro do objeto
	 */
	public void setTenistaParceiro(Tenista tenistaParceiro) {
		this.tenistaParceiro = tenistaParceiro;
	}

	/**
	 * @return pontuacaoDaDupla do objeto
	 */
	public Integer getPontuacaoDaDupla() {
		return pontuacaoDaDupla;
	}

	/**
	 * @param pontuacaoDaDupla defini pontuacaoDaDupla do objeto
	 */
	public void setPontuacaoDaDupla(Integer pontuacaoDaDupla) {
		this.pontuacaoDaDupla = pontuacaoDaDupla;
	}
	
		
	
	
	
}