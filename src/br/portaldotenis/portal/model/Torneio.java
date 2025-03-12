/**
 * 
 */
package br.portaldotenis.portal.model;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

/**
 * Classe responsável por modelar um torneio do sistema
 * 
 * @author Thiago
 *
 */
@Entity
public class Torneio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "Informe o nome do torneio")
	private String nome;

	@NotNull(message = "Informe a data de início do torneio")
	@Temporal(TemporalType.DATE)
	private Calendar dataInicio;

	@NotNull(message = "Informe a data de fim do torneio")
	@Temporal(TemporalType.DATE)
	private Calendar dataFim;

	@ManyToOne
	private Local local;

	@NotNull(message = "Informe a data de início das inscrições do torneio")
	@Temporal(TemporalType.DATE)
	private Calendar dataInicioInscricao;

	@NotNull(message = "Informe a data de fim das inscrições do torneio ")
	@Temporal(TemporalType.DATE)
	private Calendar dataFimInscricao;

	@NotNull(message = "Informe o valor da primeira inscrição")
	private Float valorInscricao;

	private Float valorSegundaInscricao;

	private Float valorTerceiraInscricao;

	
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private Premiacao premiacao;

	private String observacoesAdicionais;

	@NotNull(message = "Descreva as informações de pagamento do torneio")
	private String informacoesPagamento;

	@NotNull
	private Integer limiteDeInscritos;

	@Enumerated(EnumType.STRING)
	private SituacaoTorneio situacao;

	private String motivoDeReabertura;

	private boolean chaveGerada;
	private boolean divulgarTorneio;
	private boolean aberto;
	private boolean rankingDeClube;
	private boolean confraternizacao;
	private boolean iniciouSegundoEstagio;

	@NotNull(message = "Informe o tipo de chaveamento")
	@Enumerated(EnumType.STRING)
	private TipoDeChave tipoDeChave;

	@NotNull(message = "Informe a categoria")
	@Enumerated(EnumType.STRING)
	private CategoriaTorneio categoria;

	@ManyToMany(fetch = FetchType.EAGER)
	private List<Nivel> niveis;

	@ManyToOne
	private Tenista tenistaOrganizador;

	private BigDecimal valorInscricaoFiliado;

	private String botaoPagamentoFiliado;

	private String botaoPagamento;

	private String botaoSegundoPagamento;

	private String botaoTerceiroPagamento;

	@OneToMany(mappedBy = "torneio")
	private List<Inscricao> inscricoes;

	private String horario;

	@ManyToOne
	private Tenista tenistaAuxiliar1;

	@ManyToOne
	private Tenista tenistaAuxiliar2;
	
	private Integer numeroDeEstagios;
	
	private Integer tenistasPorGrupo;

	@Transient
	public static String CAMPO_PRINCIPAL_ORDENACAO = "nome";

	public boolean isSemPontuacao() {
		if (!aberto || rankingDeClube || confraternizacao) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isSimplesEDuplas() {

		if (categoria == CategoriaTorneio.SIMPLES_DUPLAS ) {
			return true;
		} else {
			return false;
		}
	}
	public boolean isDuplas() {

		if (categoria == CategoriaTorneio.DUPLAS) {
			return true;
		} else {
			return false;
		}
	}
	public boolean isSimples() {

		if (categoria == CategoriaTorneio.SIMPLES) {
			return true;
		} else {
			return false;
		}
	}
	public boolean isMisto2Estagios() {

		if (tipoDeChave == TipoDeChave.MISTO && numeroDeEstagios == 2) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * Método que verifica se um tenista é organizador do torneio
	 * 
	 * @param tenista
	 *            identifica o tenista que será testado
	 * 
	 * */
	public boolean getOrganizadorDoTorneio(Tenista tenista) {
		if (tenista.equals(this.getTenistaOrganizador()) || tenista.equals(this.getTenistaAuxiliar1())
				|| tenista.equals(this.getTenistaAuxiliar2())) {
			return true;
		}else {
			return false;
		}

	}

	public boolean isContaPontos() {
		if (aberto == true && rankingDeClube == false && confraternizacao == false) {
			return true;
		} else {
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
	 *            defini id do objeto
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return nome do objeto
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * @param nome
	 *            defini nome do objeto
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * @return dataInicio do objeto
	 */
	public Calendar getDataInicio() {
		return dataInicio;
	}
	/**
	 * @param dataInicio
	 *            defini dataInicio do objeto
	 */
	public void setDataInicio(Calendar dataInicio) {
		this.dataInicio = dataInicio;
	}
	/**
	 * @return dataFim do objeto
	 */
	public Calendar getDataFim() {
		return dataFim;
	}
	/**
	 * @param dataFim
	 *            defini dataFim do objeto
	 */
	public void setDataFim(Calendar dataFim) {
		this.dataFim = dataFim;
	}
	/**
	 * @return local do objeto
	 */
	public Local getLocal() {
		return local;
	}
	/**
	 * @param local
	 *            defini local do objeto
	 */
	public void setLocal(Local local) {
		this.local = local;
	}
	/**
	 * @return dataInicioInscricao do objeto
	 */
	public Calendar getDataInicioInscricao() {
		return dataInicioInscricao;
	}
	/**
	 * @param dataInicioInscricao
	 *            defini dataInicioInscricao do objeto
	 */
	public void setDataInicioInscricao(Calendar dataInicioInscricao) {
		this.dataInicioInscricao = dataInicioInscricao;
	}
	/**
	 * @return dataFimInscricao do objeto
	 */
	public Calendar getDataFimInscricao() {
		return dataFimInscricao;
	}
	/**
	 * @param dataFimInscricao
	 *            defini dataFimInscricao do objeto
	 */
	public void setDataFimInscricao(Calendar dataFimInscricao) {
		this.dataFimInscricao = dataFimInscricao;
	}
	/**
	 * @return valorInscricao do objeto
	 */
	public Float getValorInscricao() {
		return valorInscricao;
	}
	/**
	 * @param valorInscricao
	 *            defini valorInscricao do objeto
	 */
	public void setValorInscricao(Float valorInscricao) {
		this.valorInscricao = valorInscricao;
	}
	/**
	 * @return premiacao do objeto
	 */
	public Premiacao getPremiacao() {
		return premiacao;
	}
	/**
	 * @param premiacao
	 *            defini premiacao do objeto
	 */
	public void setPremiacao(Premiacao premiacao) {
		this.premiacao = premiacao;
	}
	/**
	 * @return observacoesAdicionais do objeto
	 */
	public String getObservacoesAdicionais() {
		return observacoesAdicionais;
	}
	/**
	 * @param observacoesAdicionais
	 *            defini observacoesAdicionais do objeto
	 */
	public void setObservacoesAdicionais(String observacoesAdicionais) {
		this.observacoesAdicionais = observacoesAdicionais;
	}
	/**
	 * @return informacoesPagamento do objeto
	 */
	public String getInformacoesPagamento() {
		return informacoesPagamento;
	}
	/**
	 * @param informacoesPagamento
	 *            defini informacoesPagamento do objeto
	 */
	public void setInformacoesPagamento(String informacoesPagamento) {
		this.informacoesPagamento = informacoesPagamento;
	}
	/**
	 * @return limiteDeInscritos do objeto
	 */
	public Integer getLimiteDeInscritos() {
		return limiteDeInscritos;
	}
	/**
	 * @param limiteDeInscritos
	 *            defini limiteDeInscritos do objeto
	 */
	public void setLimiteDeInscritos(Integer limiteDeInscritos) {
		this.limiteDeInscritos = limiteDeInscritos;
	}
	/**
	 * @return situacao do objeto
	 */
	public SituacaoTorneio getSituacao() {
		return situacao;
	}
	/**
	 * @param situacao
	 *            defini situacao do objeto
	 */
	public void setSituacao(SituacaoTorneio situacao) {
		this.situacao = situacao;
	}
	/**
	 * @return motivoDeReabertura do objeto
	 */
	public String getMotivoDeReabertura() {
		return motivoDeReabertura;
	}
	/**
	 * @param motivoDeReabertura
	 *            defini motivoDeReabertura do objeto
	 */
	public void setMotivoDeReabertura(String motivoDeReabertura) {
		this.motivoDeReabertura = motivoDeReabertura;
	}
	/**
	 * @return chaveGerada do objeto
	 */
	public boolean isChaveGerada() {
		return chaveGerada;
	}
	/**
	 * @param chaveGerada
	 *            defini chaveGerada do objeto
	 */
	public void setChaveGerada(boolean chaveGerada) {
		this.chaveGerada = chaveGerada;
	}
	/**
	 * @return divulgarTorneio do objeto
	 */
	public boolean isDivulgarTorneio() {
		return divulgarTorneio;
	}
	/**
	 * @param divulgarTorneio
	 *            defini divulgarTorneio do objeto
	 */
	public void setDivulgarTorneio(boolean divulgarTorneio) {
		this.divulgarTorneio = divulgarTorneio;
	}

	/**
	 * @return aberto do objeto
	 */
	public boolean isAberto() {
		return aberto;
	}
	/**
	 * @param aberto
	 *            defini aberto do objeto
	 */
	public void setAberto(boolean aberto) {
		this.aberto = aberto;
	}

	/**
	 * @return rankingDeClube do objeto
	 */
	public boolean isRankingDeClube() {
		return rankingDeClube;
	}
	/**
	 * @param rankingDeClube
	 *            defini rankingDeClube do objeto
	 */
	public void setRankingDeClube(boolean rankingDeClube) {
		this.rankingDeClube = rankingDeClube;
	}

	/**
	 * @return confraternizacao do objeto
	 */
	public boolean isConfraternizacao() {
		return confraternizacao;
	}

	/**
	 * @param confraternizacao
	 *            defini confraternizacao do objeto
	 */
	public void setConfraternizacao(boolean confraternizacao) {
		this.confraternizacao = confraternizacao;
	}

	/**
	 * @return tipoDechave do objeto
	 */
	public TipoDeChave getTipoDeChave() {
		return tipoDeChave;
	}

	/**
	 * @param tipoDechave
	 *            defini tipoDechave do objeto
	 */
	public void setTipoDeChave(TipoDeChave tipoDeChave) {
		this.tipoDeChave = tipoDeChave;
	}

	/**
	 * @return categoria do objeto
	 */
	public CategoriaTorneio getCategoria() {
		return categoria;
	}
	/**
	 * @param categoria
	 *            defini categoria do objeto
	 */
	public void setCategoria(CategoriaTorneio categoria) {
		this.categoria = categoria;
	}
	/**
	 * @return niveis do objeto
	 */
	public List<Nivel> getNiveis() {
		return niveis;
	}
	/**
	 * @param niveis
	 *            defini niveis do objeto
	 */
	public void setNiveis(List<Nivel> niveis) {
		this.niveis = niveis;
	}
	/**
	 * @return tenistaOrganizador do objeto
	 */
	public Tenista getTenistaOrganizador() {
		return tenistaOrganizador;
	}
	/**
	 * @param tenistaOrganizador
	 *            defini tenistaOrganizador do objeto
	 */
	public void setTenistaOrganizador(Tenista tenistaOrganizador) {
		this.tenistaOrganizador = tenistaOrganizador;
	}

	/**
	 * @return inscricoes do objeto
	 */
	public List<Inscricao> getInscricoes() {
		return inscricoes;
	}

	/**
	 * @param inscricoes
	 *            defini inscricoes do objeto
	 */
	public void setInscricoes(List<Inscricao> inscricoes) {
		this.inscricoes = inscricoes;
	}

	/**
	 * @return valorInscricaoFiliado do objeto
	 */
	public BigDecimal getValorInscricaoFiliado() {
		return valorInscricaoFiliado;
	}

	/**
	 * @param valorInscricaoFiliado
	 *            defini valorInscricaoFiliado do objeto
	 */
	public void setValorInscricaoFiliado(BigDecimal valorInscricaoFiliado) {
		this.valorInscricaoFiliado = valorInscricaoFiliado;
	}

	/**
	 * @return botaoPagamentoFiliado do objeto
	 */
	public String getBotaoPagamentoFiliado() {
		return botaoPagamentoFiliado;
	}

	/**
	 * @param botaoPagamentoFiliado
	 *            defini botaoPagamentoFiliado do objeto
	 */
	public void setBotaoPagamentoFiliado(String botaoPagamentoFiliado) {
		this.botaoPagamentoFiliado = botaoPagamentoFiliado;
	}

	/**
	 * @return botaoPagamento do objeto
	 */
	public String getBotaoPagamento() {
		return botaoPagamento;
	}

	/**
	 * @param botaoPagamento
	 *            defini botaoPagamento do objeto
	 */
	public void setBotaoPagamento(String botaoPagamento) {
		this.botaoPagamento = botaoPagamento;
	}

	/**
	 * @return horario do objeto
	 */
	public String getHorario() {
		return horario;
	}

	/**
	 * @param horario
	 *            defini horario do objeto
	 */
	public void setHorario(String horario) {
		this.horario = horario;
	}

	/**
	 * @return tenistaAuxiliar1 do objeto
	 */
	public Tenista getTenistaAuxiliar1() {
		return tenistaAuxiliar1;
	}

	/**
	 * @param tenistaAuxiliar1
	 *            defini tenistaAuxiliar1 do objeto
	 */
	public void setTenistaAuxiliar1(Tenista tenistaAuxiliar1) {
		this.tenistaAuxiliar1 = tenistaAuxiliar1;
	}

	/**
	 * @return tenistaAuxiliar2 do objeto
	 */
	public Tenista getTenistaAuxiliar2() {
		return tenistaAuxiliar2;
	}

	/**
	 * @param tenistaAuxiliar2
	 *            defini tenistaAuxiliar2 do objeto
	 */
	public void setTenistaAuxiliar2(Tenista tenistaAuxiliar2) {
		this.tenistaAuxiliar2 = tenistaAuxiliar2;
	}

	/**
	 * @return the valorSegundaInscricao
	 */
	public Float getValorSegundaInscricao() {
		return valorSegundaInscricao;
	}

	/**
	 * @param valorSegundaInscricao the valorSegundaInscricao to set
	 */
	public void setValorSegundaInscricao(Float valorSegundaInscricao) {
		this.valorSegundaInscricao = valorSegundaInscricao;
	}

	/**
	 * @return the valorTerceiraInscricao
	 */
	public Float getValorTerceiraInscricao() {
		return valorTerceiraInscricao;
	}

	/**
	 * @param valorTerceiraInscricao the valorTerceiraInscricao to set
	 */
	public void setValorTerceiraInscricao(Float valorTerceiraInscricao) {
		this.valorTerceiraInscricao = valorTerceiraInscricao;
	}

	/**
	 * @return the botaoSegundoPagamento
	 */
	public String getBotaoSegundoPagamento() {
		return botaoSegundoPagamento;
	}

	/**
	 * @param botaoSegundoPagamento the botaoSegundoPagamento to set
	 */
	public void setBotaoSegundoPagamento(String botaoSegundoPagamento) {
		this.botaoSegundoPagamento = botaoSegundoPagamento;
	}

	/**
	 * @return the botaoTerceiroPagamento
	 */
	public String getBotaoTerceiroPagamento() {
		return botaoTerceiroPagamento;
	}

	/**
	 * @param botaoTerceiroPagamento the botaoTerceiroPagamento to set
	 */
	public void setBotaoTerceiroPagamento(String botaoTerceiroPagamento) {
		this.botaoTerceiroPagamento = botaoTerceiroPagamento;
	}

	/**
	 * @return the numeroDeEstagios
	 */
	public Integer getNumeroDeEstagios() {
		return numeroDeEstagios;
	}

	/**
	 * @param numeroDeEstagios the numeroDeEstagios to set
	 */
	public void setNumeroDeEstagios(Integer numeroDeEstagios) {
		this.numeroDeEstagios = numeroDeEstagios;
	}

	/**
	 * @return the tenistasPorGrupo
	 */
	public Integer getTenistasPorGrupo() {
		return tenistasPorGrupo;
	}

	/**
	 * @param tenistasPorGrupo the tenistasPorGrupo to set
	 */
	public void setTenistasPorGrupo(Integer tenistasPorGrupo) {
		this.tenistasPorGrupo = tenistasPorGrupo;
	}

	/**
	 * @return the iniciouSegundoEstagio
	 */
	public boolean isIniciouSegundoEstagio() {
		return iniciouSegundoEstagio;
	}

	/**
	 * @param iniciouSegundoEstagio the iniciouSegundoEstagio to set
	 */
	public void setIniciouSegundoEstagio(boolean iniciouSegundoEstagio) {
		this.iniciouSegundoEstagio = iniciouSegundoEstagio;
	}

	
	
	
	
	

}
