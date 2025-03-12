package br.portaldotenis.portal.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import br.com.caelum.vraptor.Controller;
import br.portaldotenis.portal.dao.InscricaoDeDuplaDAO;
import br.portaldotenis.portal.dao.JogoDeTorneioDAO;
import br.portaldotenis.portal.dao.TenistaDAO;
import br.portaldotenis.portal.model.ClassificacaoDeGrupo;
import br.portaldotenis.portal.model.Fase;
import br.portaldotenis.portal.model.InscricaoDeDupla;
import br.portaldotenis.portal.model.JogoDeTorneio;
import br.portaldotenis.portal.model.Nivel;
import br.portaldotenis.portal.model.Tenista;
import br.portaldotenis.portal.model.Torneio;

@Controller
public class ClassificacaoGrupoController {

	@Inject
	private TenistaDAO tenistaDao;
	
	@Inject
	private JogoDeTorneioDAO jogoDeTorneioDao;

	@Inject
	private InscricaoDeDuplaDAO inscricaoDeDuplaDao;
	
	/**
	 * Método que gera a classificação de um grupo em um torneio de grupos
	 * baseado nos jogos de cada tenista
	 * 
	 * */
	public List<ClassificacaoDeGrupo> classificaGrupo(Nivel nivel, Torneio torneio) {

		// recupera os tenista do grupo
		List<Tenista> tenistas = tenistaDao.getTenistasInscritosConfirmadosNoTorneioPorNivel(nivel.getId(),
				torneio.getId());
		// lista com a classificação dos tenistas do grupo
		List<ClassificacaoDeGrupo> classificacoesEmGrupo = new ArrayList<ClassificacaoDeGrupo>();

		for (Tenista tenista : tenistas) {

			// recupera todos os jogos de um tenista
			List<JogoDeTorneio> jogos = jogoDeTorneioDao.getJogosDoTenistaNoTorneioPorNivel(tenista.getId(),
					nivel.getId(), torneio.getId());
			// cria a classificação do tenista
			ClassificacaoDeGrupo classificacaoDeGrupo;

			// iniciar a classificação

			classificacaoDeGrupo = populaDadosDeClassificacaoTenista(jogos, tenista, null);

			classificacoesEmGrupo.add(classificacaoDeGrupo);
		}

		// ordena a classificação pelos critérios
		Collections.sort(classificacoesEmGrupo);
		Collections.reverse(classificacoesEmGrupo);
		return classificacoesEmGrupo;
	}


	/**
	 * Método que gera a classificação de um grupo em um torneio que também tem a fase de grupos
	 * baseado nos jogos de cada tenista
	 * 
	 * */
	public List<ClassificacaoDeGrupo> classificaGrupo(Nivel nivel, Torneio torneio, Integer numeroGrupo, Fase faseGrupo) {

		
		// recupera os tenista do grupo
		tenistaDao = new TenistaDAO();
		List<Tenista> tenistas = new ArrayList<Tenista>();
		List<Tenista> tenistasParceiros = null;
		List<InscricaoDeDupla> inscricoesDeDuplas;

		// lista com a classificação dos tenistas do grupo
		List<ClassificacaoDeGrupo> classificacoesEmGrupo = new ArrayList<ClassificacaoDeGrupo>();

		
		// se o nível for de simples pegue a lista de tenistas inscritos no torneio e nível e do grupo específico sorteado 
		if(!nivel.isDuplas()){
				tenistas = tenistaDao.getTenistasInscritosConfirmadosNoTorneioPorNivelNumeroGrupo(nivel.getId(),
				torneio.getId(), numeroGrupo);

				for (Tenista tenista : tenistas) {

					// recupera todos os jogos de um tenista
					JogoDeTorneioDAO jogoDeTorneioDao = new JogoDeTorneioDAO();
					List<JogoDeTorneio> jogos = jogoDeTorneioDao.getJogosDoTenistaNoTorneioPorNivelEFase(tenista.getId(),
							nivel.getId(), torneio.getId(), faseGrupo.getId());
					// cria a classificação do tenista
					ClassificacaoDeGrupo classificacaoDeGrupo;

					// iniciar a classificação

					classificacaoDeGrupo = populaDadosDeClassificacaoTenista(jogos, tenista, null);

					classificacoesEmGrupo.add(classificacaoDeGrupo);
				}
		}else{
			 
			Tenista tenista;
			Tenista tenistaParceiro;

			if (inscricaoDeDuplaDao == null) {
				inscricaoDeDuplaDao = new InscricaoDeDuplaDAO(); 
			}
			inscricoesDeDuplas = inscricaoDeDuplaDao.getInscricoesPorNivelTorneioENumeroGrupo(nivel,torneio,numeroGrupo);

			for (InscricaoDeDupla inscricaoDeDupla : inscricoesDeDuplas) {


				tenista = inscricaoDeDupla.getTenista();
				tenistaParceiro = inscricaoDeDupla.getTenistaParceiro();
				
				// recupera todos os jogos de um tenista
				JogoDeTorneioDAO jogoDeTorneioDao = new JogoDeTorneioDAO();
				List<JogoDeTorneio> jogos = jogoDeTorneioDao.getJogosDoTenistaNoTorneioPorNivelEFase(tenista.getId(),
						nivel.getId(), torneio.getId(), faseGrupo.getId());
				//List<JogoDeTorneioDeDupla> jogos = jogoDeTorneioDeDuplaDao.getJogosDosTenistasNoTorneioDeDuplaPorNivelEFase(tenista.getId(), tenistaParceiro.getId(),
					//	nivel.getId(), torneio.getId(), faseGrupo.getId());
				// cria a classificação do tenista
				ClassificacaoDeGrupo classificacaoDeGrupo;

				// iniciar a classificação

				classificacaoDeGrupo = populaDadosDeClassificacaoTenista(jogos, tenista, tenistaParceiro);

				classificacoesEmGrupo.add(classificacaoDeGrupo);

				
			}
		}

		
		// ordena a classificação pelos critérios
		Collections.sort(classificacoesEmGrupo);
		Collections.reverse(classificacoesEmGrupo);
		return classificacoesEmGrupo;
	}

	
	/**
	 * Método que popula os dados de classificação de um tenista do grupo
	 * 
	 * @param jogos
	 *            que o tenista participou
	 * @param tenista
	 *            identifica o tenista
	 * 
	 * */
	public ClassificacaoDeGrupo populaDadosDeClassificacaoTenista(List<JogoDeTorneio> jogos, Tenista tenista, Tenista tenistaParceiro) {
		// TODO Auto-generated method stub

		ClassificacaoDeGrupo classificacaoDeGrupo = new ClassificacaoDeGrupo(tenista, 0, 0, 0, 0, 0, 0, 0);
		int numeroDeJogos = 0;
		int vitorias = 0;
		int setsPro = 0;
		int setsContra = 0;
		int gamesPro = 0;
		int gamesContra = 0;
		int vitoriasWO = 0;
		
		float fatorPontuacao = 0, fatorSet = 0, fatorGame = 0;

		// verifica se tem algum jogo para gerasr a classificação
		if (jogos != null) {

			// varre todos os jogos do tenista
			for (JogoDeTorneio jogoDeTorneio : jogos) {

				// calcula a quantidade de jogos
				if (jogoDeTorneio.getOcorreu()) {
					
					numeroDeJogos++;

					// se o tenista analisado é o primeiro do jogo
					if (jogoDeTorneio.getTenistaUm().equals(tenista)) {

						// verifica se o primeiro venceu e atribui uma vitoria para ele
						if (jogoDeTorneio.getSetTenistaUm() > jogoDeTorneio.getSetTenistaDois()) {

							if (jogoDeTorneio.isWo()){
								vitoriasWO++;
							}else{
								vitorias++;
							}
						}
						
						setsPro += jogoDeTorneio.getSetTenistaUm(); 
						setsContra += jogoDeTorneio.getSetTenistaDois();
						gamesPro += somaDeGamesDoTenistaUm(jogoDeTorneio);
						gamesContra += somaDeGamesDoTenistaDois(jogoDeTorneio); 
						
						
						
					// se o tenista é o segundo do jogo	
					} else if (jogoDeTorneio.getTenistaDois().equals(tenista)) {

						// verifica se o segundo venceu e atribui uma vitória para ele
						if (jogoDeTorneio.getSetTenistaDois() > jogoDeTorneio.getSetTenistaUm()) {

							if(jogoDeTorneio.isWo()){
								vitoriasWO++;
							}else{
								vitorias++;
							}
						}
						
						setsPro += jogoDeTorneio.getSetTenistaDois(); 
						setsContra += jogoDeTorneio.getSetTenistaUm();
						gamesPro += somaDeGamesDoTenistaDois(jogoDeTorneio); 
						gamesContra += somaDeGamesDoTenistaUm(jogoDeTorneio); 


					}
				}
			}

			if(numeroDeJogos>0){
				fatorPontuacao = (float) vitorias/ (float) numeroDeJogos;
			}
			if((setsPro + setsContra)>0){
				fatorSet = (float) setsPro/(float)(setsPro+setsContra);
			}
			if((gamesPro+gamesContra) > 0){
				fatorGame = (float) gamesPro/ (float) (gamesPro+gamesContra);
			}
			
			classificacaoDeGrupo.setTenista(tenista);
			classificacaoDeGrupo.setTenistaParceiro(tenistaParceiro);
			classificacaoDeGrupo.setJogos(numeroDeJogos);
			classificacaoDeGrupo.setFatorPontuacao(fatorPontuacao);
			classificacaoDeGrupo.setVitorias(vitorias);
			classificacaoDeGrupo.setVitoriasWO(vitoriasWO);
			classificacaoDeGrupo.setFatorSet(fatorSet);
			classificacaoDeGrupo.setSetsPro(setsPro);
			classificacaoDeGrupo.setSetsContra(setsContra);
			classificacaoDeGrupo.setFatorGame(fatorGame);
			classificacaoDeGrupo.setGamesPro(gamesPro);
			classificacaoDeGrupo.setGamesContra(gamesContra);


		}

		return classificacaoDeGrupo;
	}

	private int somaDeGamesDoTenistaUm(JogoDeTorneio jogoDeTorneio) {
		// TODO Auto-generated method stub
		
		int soma = (jogoDeTorneio.getGamesSet1T1() !=null ? jogoDeTorneio.getGamesSet1T1() : 0)  + 
				(jogoDeTorneio.getGamesSet2T1() != null ? jogoDeTorneio.getGamesSet2T1() : 0 ) + 
				(jogoDeTorneio.getGamesSet3T1() != null ? jogoDeTorneio.getGamesSet3T1() :0);
		return soma;
	}

	private int somaDeGamesDoTenistaDois(JogoDeTorneio jogoDeTorneio) {
		// TODO Auto-generated method stub
		
		int soma = (jogoDeTorneio.getGamesSet1T2() !=null ? jogoDeTorneio.getGamesSet1T2() : 0)  + 
				(jogoDeTorneio.getGamesSet2T2() != null ? jogoDeTorneio.getGamesSet2T2() : 0 ) + 
				(jogoDeTorneio.getGamesSet3T2() != null ? jogoDeTorneio.getGamesSet3T2() :0);
		return soma;
	}

}
