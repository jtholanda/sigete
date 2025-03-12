package br.portaldotenis.portal.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

import br.com.caelum.stella.bean.validation.CPF;
import br.com.caelum.stella.format.CPFFormatter;
import br.portaldotenis.portal.controller.TenistaController;
import br.portaldotenis.portal.util.Utils;


/**
 * 
 * @author Gabriel Soares
 * @author Thiago Holanda
 * 
 */
@Entity
@Table(indexes = {@Index(columnList = "nome, sobrenome, nomeranking")})
public class Tenista implements Serializable {

	@Transient
	public static String CAMPO_PRINCIPAL_ORDENACAO = "nome";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "É necessário informar um Email")
	@Column(unique = true)
	@Email(message = "Email inválido")
	private String email;

	@NotNull(message = "É necessário informar uma senha")
	private String senha;

	@Transient
	private String senhaLimpa;

	@Enumerated(EnumType.STRING)
	private TipoUsuario tipo;

	@CPF(message = "CPF Inválido")
	@Column(unique = true)
	private String cpf;

	@NotNull(message = "É necessário informar o seu nome")
	private String nome;

	@NotNull(message = "É necessário informar o seu sobrenome")
	private String sobreNome;

	@NotNull(message = "É necessário informar um nome para ser conhecido no ranking")
	@Column(unique = true)
	private String nomeRanking;

	@NotNull(message = "Informe o sexo")
	@Enumerated(EnumType.STRING)
	private Sexo sexo;

	@Temporal(TemporalType.DATE)
	private Calendar dataNascimento;

	@AttributeOverrides({@AttributeOverride(name = "numero", column = @Column(name = "telefonePrincipal")),
			@AttributeOverride(name = "operadora", column = @Column(name = "operadoraPrincipal"))})
	@Embedded
	private Telefone telefonePrincipal;
	@AttributeOverrides({@AttributeOverride(name = "numero", column = @Column(name = "telefoneSecundario")),
			@AttributeOverride(name = "operadora", column = @Column(name = "operadoraSecundaria"))})
	@Embedded
	private Telefone telefoneSecundario;

	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
	private Endereco endereco;

	@ManyToOne
	private Clube clube;

	@ManyToOne
	private Professor professor;

	@ManyToMany
	private List<Local> locais;

	private String raquete;

	@ManyToOne
	private Nivel nivelTecnicoPrincipal;

	@ManyToOne
	private Nivel nivelTecnicoBeachTennis;
	
	@Temporal(TemporalType.DATE)
	private Calendar inicioNoTenis;

	private String apelido;

	private Integer pontosCategoriaPrincipal;
	
	private Integer pontosCategoriaBeachTennis;

	private boolean naoPossuiCpf; // identifica se o tenista informou o seu cpf correto
	private boolean receberNotificacoes; // identifica se o tenista deseja receber notificações por e-mail
	private boolean emailConfirmado; // identifica se o tenista confirmou o e-mail dele pelo link para poder ter acesso ao sistema
	private boolean participaDeRanking; // identifica se o tenista participa do ranking e mostra ou não o nome dele na lista
	private boolean bolsista; // identifica um boleiro
	private boolean visitante; // identifica se o tenista é visitante apenas para titulo de informação
	private boolean ativo; // identifica se um tenista está ativo no sistema para participar de torneios
	private boolean confirmado; // identifica se o tenista foi verificado manualmente ou não e confirma que ele não é repetido
	private boolean filiado; // identifica se o tenista é filiado, utilizado apenas para mostrar o icone na listagem
	private boolean suspenso; // identifica se o tenista tem alguma pendência de pagamento
	private String foto;

	
	@Temporal(TemporalType.DATE)
	private Calendar dataAtualizacao;
	
	@Transient
	private Integer posicaoEmRankingDoNivel; // pega a posição do tenista no ranking
											// que foi passado como parametro


	/**
	 * Calcula a idade do tenista
	 * */
	public String getExibicaoBasica() {

		// retorna exibição básica do tenista para uma inscrição
		String exibicaoBasica = getNomeCompleto() + " - " + TipoEsporte.TENNIS.getNome() + " - " +  nivelTecnicoPrincipal.getDescricao() + " - " + TipoEsporte.BEACH_TENNIS.getNome() + " - " + nivelTecnicoBeachTennis.getDescricao();
		return (exibicaoBasica); 

	}
	/**
	 * Calcula a idade do tenista
	 * */
	public Integer getIdade() {

		// Cria um objeto calendar com a data atual
		Calendar today = Calendar.getInstance();

		// Obtém a idade baseado no ano
		int idade = today.get(Calendar.YEAR) - dataNascimento.get(Calendar.YEAR);

		dataNascimento.add(Calendar.YEAR, idade);

		// se a data de hoje é antes da data de Nascimento, então diminui 1(um)
		if (today.before(dataNascimento)) {
			idade--;
		}

		return idade;

	}

	/**
	 * Calcula a idade do tenista
	 * */
	public Integer getTempoDeTenis() {

		// Cria um objeto calendar com a data atual
		Calendar today = Calendar.getInstance();

		// Obtém a idade baseado no ano
		int tempo = today.get(Calendar.YEAR) - inicioNoTenis.get(Calendar.YEAR);

		return tempo;

	}

	/**
	 * Retorna o nome do tenista que será exibido no ranking
	 * */
	public String getNomeExibicao() {

		String nomeExibicao = (sobreNome + ", " + nome + " (" + nomeRanking + ")");
		return Utils.getNomePadronizado(nomeExibicao);

	}

	/**
	 * Retorna o nome completo do tenista
	 * */
	public String getNomeCompleto() {
		
		return getNome() + " " + getSobreNome();

	}

	public boolean isMaiorIdade() {
		Calendar dezoitoAnosAtras = Calendar.getInstance();
		dezoitoAnosAtras.add(Calendar.YEAR, -18);
		return dataNascimento.before(dezoitoAnosAtras);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email.toLowerCase();
	}

	public void setEmail(String email) {
		this.email = email.toLowerCase();
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getSenhaLimpa() {
		return senhaLimpa;
	}

	public void setSenhaLimpa(String senhaLimpa) {
		this.senhaLimpa = senhaLimpa;
	}

	public TipoUsuario getTipo() {
		return tipo;
	}

	public void setTipo(TipoUsuario tipo) {
		this.tipo = tipo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = new CPFFormatter().unformat(cpf);
	}

	public boolean isReceberNotificacoes() {
		return receberNotificacoes;
	}

	public void setReceberNotificacoes(boolean receberNotificacoes) {
		this.receberNotificacoes = receberNotificacoes;
	}

	public boolean isEmailConfirmado() {
		return emailConfirmado;
	}

	public void setEmailConfirmado(boolean emailConfirmado) {
		this.emailConfirmado = emailConfirmado;
	}

	public String getNome() {
		
		if(nome!=null){
			nome = Utils.getNomePadronizado(nome);
		}
		return nome;
	}

	public void setNome(String nome) {
		if(nome!=null){
			nome = Utils.semAcento(nome.substring(0,1)) + nome.substring(1,nome.length());
		}
		this.nome = nome;
	}

	public String getSobreNome() {
		sobreNome = Utils.getNomePadronizado(sobreNome);
		return sobreNome;
	}

	public void setSobreNome(String sobreNome) {
		this.sobreNome = sobreNome;
	}

	public String getNomeRanking() {
		/*String[] nomes = getNomeCompleto().split(" ");
		String primeiroNome = nomes[0]!= null? nomes[0]:"";
		String segundoNome = (nomes[1]!= null && nomes[1].length()>2)? nomes[1]:"";
		nomeRanking = primeiroNome + " " +segundoNome;*/
		nomeRanking = Utils.getNomePadronizado(nomeRanking);
		return nomeRanking;
	}

	public void setNomeRanking(String nomeRanking) {
		this.nomeRanking = nomeRanking;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public Telefone getTelefonePrincipal() {
		return telefonePrincipal;
	}

	public void setTelefonePrincipal(Telefone telefonePrincipal) {
		this.telefonePrincipal = telefonePrincipal;
	}

	public Telefone getTelefoneSecundario() {
		return telefoneSecundario;
	}

	public void setTelefoneSecundario(Telefone telefoneSecundario) {
		this.telefoneSecundario = telefoneSecundario;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Clube getClube() {
		return clube;
	}

	public void setClube(Clube clube) {
		this.clube = clube;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public List<Local> getLocais() {
		return locais;
	}

	public void addLocal(Local local) {
		this.locais.add(local);
	}

	public void removeLocal(Local local) {
		this.locais.remove(local);
	}

	public String getRaquete() {
		return raquete;
	}

	public void setRaquete(String raquete) {
		this.raquete = raquete;
	}

	public Nivel getNivelTecnicoPrincipal() {
		return nivelTecnicoPrincipal;
	}

	public void setNivelTecnicoPrincipal(Nivel nivelTecnicoPrincipal) {
		this.nivelTecnicoPrincipal = nivelTecnicoPrincipal;
	}

	public Calendar getInicioNoTenis() {
		return inicioNoTenis;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}
	public Integer getPontosCategoriaPrincipal() {
		return pontosCategoriaPrincipal;
	}

	public void setPontosCategoriaPrincipal(Integer pontosCategoriaPrincipal) {
		this.pontosCategoriaPrincipal = pontosCategoriaPrincipal;
	}

	public void setInicioNoTenis(Calendar inicioNoTenis) {
		this.inicioNoTenis = inicioNoTenis;
	}

	public boolean isParticipaDeRanking() {
		return participaDeRanking;
	}

	public void setParticipaDeRanking(boolean participaDeRanking) {
		this.participaDeRanking = participaDeRanking;
	}

	public boolean isBolsista() {
		return bolsista;
	}

	public void setBolsista(boolean bolsista) {
		this.bolsista = bolsista;
	}

	public boolean isVisitante() {
		return visitante;
	}

	public void setVisitante(boolean visitante) {
		this.visitante = visitante;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public boolean isConfirmado() {
		return confirmado;
	}

	public void setConfirmado(boolean confirmado) {
		this.confirmado = confirmado;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Calendar getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Calendar dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public boolean naoPossuiCpf() {
		return naoPossuiCpf;
	}

	public void setPossuiCpf(boolean naoPossuiCpf) {
		this.naoPossuiCpf = naoPossuiCpf;
	}

	/**
	 * @return naoPossuiCpf do objeto
	 */
	public boolean isNaoPossuiCpf() {
		return naoPossuiCpf;
	}

	/**
	 * @param naoPossuiCpf
	 *            defini naoPossuiCpf do objeto
	 */
	public void setNaoPossuiCpf(boolean naoPossuiCpf) {
		this.naoPossuiCpf = naoPossuiCpf;
	}

	/**
	 * @param locais
	 *            defini locais do objeto
	 */
	public void setLocais(List<Local> locais) {
		this.locais = locais;
	}

	/**
	 * @return dataAtualizacao do objeto
	 */
	public Calendar getDataAtualizacao() {
		return dataAtualizacao;
	}
	/**
	 * @param dataAtualizacao defini dataAtualizacao do objeto
	 */
	public void setDataAtualizacao(Calendar dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}
	
	
	/**
	 * @return filiado do objeto
	 */
	public boolean isFiliado() {
		return filiado;
	}
	/**
	 * @param filiado defini filiado do objeto
	 */
	public void setFiliado(boolean filiado) {
		this.filiado = filiado;
	}
	
	/**
	 * @return suspenso do objeto
	 */
	public boolean isSuspenso() {
		return suspenso;
	}
	/**
	 * @param suspenso defini suspenso do objeto
	 */
	public void setSuspenso(boolean suspenso) {
		this.suspenso = suspenso;
	}
	
	/**
	 * @return the nivelTecnicoBeachTennis
	 */
	public Nivel getNivelTecnicoBeachTennis() {
		return nivelTecnicoBeachTennis;
	}

	/**
	 * @param nivelTecnicoBeachTennis the nivelTecnicoBeachTennis to set
	 */
	public void setNivelTecnicoBeachTennis(Nivel nivelTecnicoBeachTennis) {
		this.nivelTecnicoBeachTennis = nivelTecnicoBeachTennis;
	}

	
	/**
	 * @return the pontosCategoriaBeachTennis
	 */
	public Integer getPontosCategoriaBeachTennis() {
		return pontosCategoriaBeachTennis;
	}

	/**
	 * @param pontosCategoriaBeachTennis the pontosCategoriaBeachTennis to set
	 */
	public void setPontosCategoriaBeachTennis(Integer pontosCategoriaBeachTennis) {
		this.pontosCategoriaBeachTennis = pontosCategoriaBeachTennis;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Tenista [id=" + id + ", email=" + email + ", senha=" + senha + ", tipo=" + tipo + ", cpf=" + cpf
				+ ", nome=" + nome + ", nomeRanking=" + nomeRanking + ", sexo=" + sexo + ", dataNascimento="
				+ dataNascimento + ", telefonePrincipal=" + telefonePrincipal + ", telefoneSecundario="
				+ telefoneSecundario + ", endereco=" + endereco + ", clube=" + clube + ", professor=" + professor
				+ ", raquete=" + raquete + ", nivelTecnicoPrincipal=" + nivelTecnicoPrincipal + ", inicioNoTenis="
				+ inicioNoTenis + ", naoPossuiCpf=" + naoPossuiCpf + ", receberNotificacoes=" + receberNotificacoes
				+ ", emailConfirmado=" + emailConfirmado + ", participaDeRanking=" + participaDeRanking + ", bolsista="
				+ bolsista + ", visitante=" + visitante + ", ativo=" + ativo + ", confirmado=" + confirmado + ", foto="
				+ foto + "]";
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	

	/**
	 * @param posicaoEmRankingDoNivel defini posicaoEmRankingDoNivel do objeto
	 */
	public void setPosicaoEmRankingDoNivel(Integer posicaoEmRankingDoNivel) {
		this.posicaoEmRankingDoNivel = posicaoEmRankingDoNivel;
	}
	/**
	 * Retorna a posição do tenista em seu nível técnico principal
	 * @return posicaoEmRankingNivel do objeto
	 */
	public Integer getPosicaoEmRankingDoNivel() {
		// pega a posição no ranking do tenista selecionado
		int pontuacaoAnterior = 0;
		int posicaoControle = 0;
		TenistaController controler = new TenistaController();
		
		Integer anoAtual = Calendar.getInstance().get(Calendar.YEAR);
		posicaoEmRankingDoNivel = 0;
		for (Pontuacao pontuacao : controler.obterRankingDeTenistasPorNivel(getNivelTecnicoPrincipal().getId(), anoAtual)) {

			// encontrei o tenista
			posicaoControle++;
			if(pontuacaoAnterior != pontuacao.getPontos()){
				posicaoEmRankingDoNivel = posicaoControle;
			}
			pontuacaoAnterior = pontuacao.getPontos();
			if (this.equals(pontuacao.getTenista())) {
				return posicaoEmRankingDoNivel;
			}
		}
		return posicaoEmRankingDoNivel;

	}

	/**
	 * Retorna a posição do tenista em um nivel tecnico especificado
	 * @return posicaoEmRankingNivel do objeto
	 */
	public Integer getPosicaoEmRankingDoNivel(int idNivel, int ano) {
		// pega a posição no ranking do tenista selecionado
		int pontuacaoAnterior = 0;
		int posicaoControle = 0;
		TenistaController controler = new TenistaController();
		
		posicaoEmRankingDoNivel = 0;
		for (Pontuacao pontuacao : controler.obterRankingDeTenistasPorNivel(idNivel, ano)) {
			// encontrei o tenista
			
			
			posicaoControle++;
			if(pontuacaoAnterior != pontuacao.getPontos()){
				posicaoEmRankingDoNivel = posicaoControle;
			}
			pontuacaoAnterior = pontuacao.getPontos();
			if (this.equals(pontuacao.getTenista())) {
				return posicaoEmRankingDoNivel;
			}
		}
		return posicaoEmRankingDoNivel;

	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tenista other = (Tenista) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
