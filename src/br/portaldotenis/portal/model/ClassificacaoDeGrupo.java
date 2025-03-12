package br.portaldotenis.portal.model;

import java.util.List;

import br.portaldotenis.portal.controller.ClassificacaoGrupoController;

public class ClassificacaoDeGrupo implements Comparable<ClassificacaoDeGrupo> {

	private final Integer VALOR_VITORIA = 3;
	private final Integer VALOR_VITORIA_WO = 2;
	private final Integer VALOR_DERROTA = 1;

	
	private Tenista tenista;
	private Tenista tenistaParceiro;
	private Integer jogos;
	private Integer vitorias;
	private Integer vitoriasWO;
	private Integer setsPro;
	private Integer setsContra;
	private Integer gamesPro;
	private Integer gamesContra;
	private Float fatorPontuacao;
	private Float fatorSet;
	private Float fatorGame;

	
	

	public ClassificacaoDeGrupo(Tenista tenista, Integer jogos, Integer vitorias, Integer vitoriasWO, Integer setsPro,
			Integer setsContra, Integer gamesPro, Integer gamesContra) {
		super();
		this.tenista = tenista;
		this.jogos = jogos;
		this.vitorias = vitorias;
		this.vitoriasWO = vitoriasWO;
		this.setsPro = setsPro;
		this.setsContra = setsContra;
		this.gamesPro = gamesPro;
		this.gamesContra = gamesContra;
	}
	
	public ClassificacaoDeGrupo() {
		// TODO Auto-generated constructor stub
	}


	/**
	 * @return tenista do objeto
	 */
	public Tenista getTenista() {
		return tenista;
	}
	/**
	 * @param tenista
	 *            defini tenista do objeto
	 */
	public void setTenista(Tenista tenista) {
		this.tenista = tenista;
	}	
	/**
	 * @return the tenistaParceiro
	 */
	public Tenista getTenistaParceiro() {
		return tenistaParceiro;
	}

	/**
	 * @param tenistaParceiro the tenistaParceiro to set
	 */
	public void setTenistaParceiro(Tenista tenistaParceiro) {
		this.tenistaParceiro = tenistaParceiro;
	}

	/**
	 * @return jogos do objeto
	 */
	public Integer getJogos() {
		return jogos;
	}
	/**
	 * @param jogos
	 *            defini jogos do objeto
	 */
	public void setJogos(Integer jogos) {
		this.jogos = jogos;
	}
	/**
	 * @return vitorias do objeto
	 */
	public Integer getVitorias() {
		return vitorias;
	}
	/**
	 * @param vitorias
	 *            defini vitorias do objeto
	 */
	public void setVitorias(Integer vitorias) {
		this.vitorias = vitorias;
	}

	/**
	 * @return vitoriasWO do objeto
	 */
	public Integer getVitoriasWO() {
		return vitoriasWO;
	}

	/**
	 * @param vitoriasWO
	 *            defini vitoriasWO do objeto
	 */
	public void setVitoriasWO(Integer vitoriasWO) {
		this.vitoriasWO = vitoriasWO;
	}

	/**
	 * @return vitoriasTotais do objeto
	 */
	public Integer getVitoriasTotais() {
		return vitorias + vitoriasWO;
	}

	/**
	 * @return derrotas do objeto
	 */
	public Integer getDerrotas() {
		return jogos - getVitoriasTotais();
	}

	/**
	 * @return saldoDeSets do objeto
	 */
	public Integer getSaldoDeSets() {
		return setsPro - setsContra;
	}

	/**
	 * @return setsPro do objeto
	 */
	public Integer getSetsPro() {
		return setsPro;
	}
	/**
	 * @param setsPro
	 *            defini setsPro do objeto
	 */
	public void setSetsPro(Integer setsPro) {
		this.setsPro = setsPro;
	}
	/**
	 * @return setsContra do objeto
	 */
	public Integer getSetsContra() {
		return setsContra;
	}
	/**
	 * @param setsContra
	 *            defini setsContra do objeto
	 */
	public void setSetsContra(Integer setsContra) {
		this.setsContra = setsContra;
	}
	/**
	 * @return saldoDegames do objeto
	 */
	public Integer getSaldoDeGames() {
		return gamesPro - gamesContra;
	}

	/**
	 * @return gamesPro do objeto
	 */
	public Integer getGamesPro() {
		return gamesPro;
	}
	/**
	 * @param gamesPro
	 *            defini gamesPro do objeto
	 */
	public void setGamesPro(Integer gamesPro) {
		this.gamesPro = gamesPro;
	}
	/**
	 * @return gamesContra do objeto
	 */
	public Integer getGamesContra() {
		return gamesContra;
	}
	/**
	 * @param gamesContra
	 *            defini gamesContra do objeto
	 */
	public void setGamesContra(Integer gamesContra) {
		this.gamesContra = gamesContra;
	}

	/**
	 * @return pontos do objeto
	 */
	public Integer getPontos() {
		return (getVitorias() * VALOR_VITORIA) + (getVitoriasWO() * VALOR_VITORIA_WO) + (getDerrotas() * VALOR_DERROTA);
	}
	

	/**
	 * @return the fatorPontuacao
	 */
	public Float getFatorPontuacao() {
		if(getJogos() > 0){
			this.fatorPontuacao = (float) (getVitoriasTotais() / (float) getJogos());
			return this.fatorPontuacao;
		}else{
			return 0F;
		}
	}

	/**
	 * @param fatorPontuacao the fatorPontuacao to set
	 */
	public void setFatorPontuacao(Float fatorPontuacao) {
		this.fatorPontuacao = fatorPontuacao;
	}
	
	

	/**
	 * @return the fatorSet
	 */
	public Float getFatorSet() {
		if(this.setsPro+this.setsContra > 0){
			this.fatorSet = (float) ((this.setsPro)/ (float)(this.setsPro+this.setsContra));
			return this.fatorSet;
		}else{
			return 0F;
		}
	}

	/**
	 * @param fatorSet the fatorSet to set
	 */
	public void setFatorSet(Float fatorSet) {
		this.fatorSet = fatorSet;
	}
	
	

	/**
	 * @return the fatorGame
	 */
	public Float getFatorGame() {
		if(this.gamesPro+this.gamesContra > 0){
			this.fatorGame = (float) ((this.gamesPro)/(float)(this.gamesPro+gamesContra));
			return this.fatorGame;
		}else{
			return 0F;
		}
		
	}

	/**
	 * @param fatorGame the fatorGame to set
	 */
	public void setFatorGame(Float fatorGame) {
		this.fatorGame = fatorGame;
	}

	@Override
	public int compareTo(ClassificacaoDeGrupo outro) {
		// TODO Auto-generated method stub

		if (!this.getVitoriasTotais().equals(outro.getVitoriasTotais())) {
			return this.getVitoriasTotais().compareTo(outro.getVitoriasTotais());
		} else if (!this.getSaldoDeSets().equals(outro.getSaldoDeSets())) {
			return this.getSaldoDeSets().compareTo(outro.getSaldoDeSets());
		} else if (!this.getSaldoDeGames().equals(outro.getSaldoDeGames())) {
			return this.getSaldoDeGames().compareTo(outro.getSaldoDeGames());
		} else if (!this.getVitorias().equals(outro.getVitorias())) {
			return this.getVitorias().compareTo(outro.getVitorias());
		} else if (!this.setsPro.equals(outro.getSetsPro())) {
			return this.setsPro.compareTo(outro.getSetsPro());
		} else if (!this.gamesPro.equals(outro.getGamesPro())) {
			return this.gamesPro.compareTo(outro.getGamesPro());
		} else if (!this.getFatorPontuacao().equals(outro.getFatorPontuacao())) {
			return this.getFatorPontuacao().compareTo(outro.getFatorPontuacao());
		} else if (!this.getFatorSet().equals(outro.getFatorSet())) {
			return this.getFatorSet().compareTo(outro.getFatorSet());
		} else {
			return this.getFatorGame().compareTo(outro.getFatorGame());
		}
	}

	public List<ClassificacaoDeGrupo> classificaGrupo(Nivel nivel, Torneio torneio) {
		
		ClassificacaoGrupoController classificacaoGrupoController = new ClassificacaoGrupoController(); 
		return classificacaoGrupoController.classificaGrupo(nivel, torneio);
	}

	public List<ClassificacaoDeGrupo> classificaGrupo(Nivel nivel, Torneio torneio,
			Integer numeroGrupo, Fase faseGrupo) {
		ClassificacaoGrupoController classificacaoGrupoController = new ClassificacaoGrupoController(); 
		return classificacaoGrupoController.classificaGrupo(nivel, torneio, numeroGrupo, faseGrupo);
	}

}
