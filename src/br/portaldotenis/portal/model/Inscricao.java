package br.portaldotenis.portal.model;


import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;


/**
 * Classe responsável por modelar uma inscrição no torneio do sistema
 *  
 * @author Thiago Holanda
 *
 */

@Entity
@Table(indexes = {@Index(columnList = "torneio_id, tenista_id, nivel_id")})
@Inheritance(strategy =InheritanceType.JOINED)  

public class Inscricao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull (message = "Escolha um torneio")
	@ManyToOne
	private Torneio torneio;
	
	@NotNull (message = "Escolha um tenista")
	@ManyToOne
	protected Tenista tenista;
	
	@ManyToOne
	private Nivel nivel;
	
	@ManyToOne
	private IndicacaoInscricao indicacaoInscricao;

	@NotNull
	@Temporal(TemporalType.DATE)
	private Calendar data;

	@Enumerated(EnumType.STRING)
	private ConhecimentoTorneio conhecimentoTorneio;
	
	private boolean confirmada;
	private boolean paga;
	private boolean bolsista;
	private boolean removida;
	
	@ManyToOne
	private Colocacao colocacao;
	
	private Integer pontuacao;
	@Column(nullable=true)
	private Integer numeroCabecaDeChave;
	private Integer anoReferencia;
	private String horarioEspecial;
	private String formaDePagamento;
	private Integer ordemAleatoria;
	private Integer numeroGrupo;
	private Integer numeroCabecaDeChave2Estagio;
	
	public boolean temHorarioEspecial(){
		if(horarioEspecial != null){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * @return id do objeto
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            define id do objeto
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return torneio do objeto
	 */
	public Torneio getTorneio() {
		return torneio;
	}

	/**
	 * @param torneio
	 *            define torneio do objeto
	 */
	public void setTorneio(Torneio torneio) {
		this.torneio = torneio;
	}

	/**
	 * @return tenista do objeto
	 */
	public Tenista getTenista() {
		return tenista;
	}

	/**
	 * @param tenista
	 *            define tenista do objeto
	 */
	public void setTenista(Tenista tenista) {
		this.tenista = tenista;
	}

	/**
	 * @return nivel do objeto
	 */
	public Nivel getNivel() {
		return nivel;
	}

	/**
	 * @param nivel
	 *            define nivel do objeto
	 */
	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}

	/**
	 * @return data do objeto
	 */
	public Calendar getData() {
		return data;
	}

	/**
	 * @param data
	 *            define data do objeto
	 */
	public void setData(Calendar data) {
		this.data = data;
	}

	/**
	 * @return conhecimentoTorneio do objeto
	 */
	public ConhecimentoTorneio getConhecimentoTorneio() {
		return conhecimentoTorneio;
	}

	/**
	 * @param conhecimentoTorneio
	 *            define conhecimentoTorneio do objeto
	 */
	public void setConhecimentoTorneio(ConhecimentoTorneio conhecimentoTorneio) {
		this.conhecimentoTorneio = conhecimentoTorneio;
	}

	/**
	 * @return confirmada do objeto
	 */
	public boolean isConfirmada() {
		return confirmada;
	}

	/**
	 * @param confirmada
	 *            define confirmada do objeto
	 */
	public void setConfirmada(boolean confirmada) {
		this.confirmada = confirmada;
	}

	/**
	 * @return paga do objeto
	 */
	public boolean isPaga() {
		return paga;
	}

	/**
	 * @param paga
	 *            define paga do objeto
	 */
	public void setPaga(boolean paga) {
		this.paga = paga;
	}

	/**
	 * @return bolsista do objeto
	 */
	public boolean isBolsista() {
		return bolsista;
	}

	/**
	 * @param bolsista
	 *            define bolsista do objeto
	 */
	public void setBolsista(boolean bolsista) {
		this.bolsista = bolsista;
	}
	
	/**
	 * @return removida do objeto
	 */
	public boolean isRemovida() {
		return removida;
	}

	/**
	 * @param removida defini removida do objeto
	 */
	public void setRemovida(boolean removida) {
		this.removida = removida;
	}

	/**
	 * @return colocacao do objeto
	 */
	public Colocacao getColocacao() {
		return colocacao;
	}

	/**
	 * @param colocacao
	 *            define colocacao do objeto
	 */
	public void setColocacao(Colocacao colocacao) {
		this.colocacao = colocacao;
	}

	/**
	 * @return pontuacao do objeto
	 */
	public Integer getPontuacao() {
		return pontuacao;
	}

	/**
	 * @param pontuacao
	 *            define pontuacao do objeto
	 */
	public void setPontuacao(Integer pontuacao) {
		this.pontuacao = pontuacao;
	}
	/**
	 * @return numeroCabecaDeChave do objeto
	 */
	public Integer getNumeroCabecaDeChave() {
		return numeroCabecaDeChave;
	}
	/**
	 * @param numeroCabecaDeChave defini numeroCabecaDeChave do objeto
	 */
	public void setNumeroCabecaDeChave(Integer numeroCabecaDeChave) {
		this.numeroCabecaDeChave = numeroCabecaDeChave;
	}

	/**
	 * @return anoReferencia do objeto
	 */
	public Integer getAnoReferencia() {
		return anoReferencia;
	}

	/**
	 * @param anoReferencia defini anoReferencia do objeto
	 */
	public void setAnoReferencia(Integer anoReferencia) {
		this.anoReferencia = anoReferencia;
	}

	/**
	 * @return horarioEspecial do objeto
	 */
	public String getHorarioEspecial() {
		return horarioEspecial;
	}

	/**
	 * @param horarioEspecial defini horarioEspecial do objeto
	 */
	public void setHorarioEspecial(String horarioEspecial) {
		this.horarioEspecial = horarioEspecial;
	}
	/**
	 * @return formaDePagamento do objeto
	 */
	public String getFormaDePagamento() {
		return formaDePagamento;
	}
	/**
	 * @param formaDePagamento defini formaDePagamento do objeto
	 */
	public void setFormaDePagamento(String formaDePagamento) {
		this.formaDePagamento = formaDePagamento;
	}
	/**
	 * @return ordemAleatoria do objeto
	 */
	public Integer getOrdemAleatoria() {
		return ordemAleatoria;
	}
	/**
	 * @param ordemAleatoria defini ordemAleatoria do objeto
	 */
	public void setOrdemAleatoria(Integer ordemAleatoria) {
		this.ordemAleatoria = ordemAleatoria;
	}
	/**
	 * @return the numeroGrupo
	 */
	public Integer getNumeroGrupo() {
		return numeroGrupo;
	}
	/**
	 * @param numeroGrupo the numeroGrupo to set
	 */
	public void setNumeroGrupo(Integer numeroGrupo) {
		this.numeroGrupo = numeroGrupo;
	}
	/**
	 * @return the numeroCabecaDeChave2Estagio
	 */
	public Integer getNumeroCabecaDeChave2Estagio() {
		return numeroCabecaDeChave2Estagio;
	}
	/**
	 * @param numeroCabecaDeChave2Estagio the numeroCabecaDeChave2Estagio to set
	 */
	public void setNumeroCabecaDeChave2Estagio(Integer numeroCabecaDeChave2Estagio) {
		this.numeroCabecaDeChave2Estagio = numeroCabecaDeChave2Estagio;
	}
	/**
	 * @return the indicacaoInscricao
	 */
	public IndicacaoInscricao getIndicacaoInscricao() {
		return indicacaoInscricao;
	}
	/**
	 * @param indicacaoInscricao the indicacaoInscricao to set
	 */
	public void setIndicacaoInscricao(IndicacaoInscricao indicacaoInscricao) {
		this.indicacaoInscricao = indicacaoInscricao;
	}
	
	
	
	
	
	
}