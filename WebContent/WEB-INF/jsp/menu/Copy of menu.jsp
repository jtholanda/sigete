	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="../menu/googleAnalitics.jsp"></c:import>
   
   <!-- cabeçalho -->
    <div class="header_area">
      <div class="logo floatleft"><a href="${linkTo[IndexController].index}"><img src="/sigete/imagens/logomarca_menor.png" alt="" height="200" width="200"/></a></div>
      <br/><br/>
     
      <div class="top_menu floatleft">
        <ul>
          <li><a href="${linkTo[TenistaController].formCadastro}">Cadastro</a></li>
          <li><a href="${linkTo[TorneioController].visualizaTorneios(0,0)}">Torneios</a></li>
          <li><a href="${linkTo[RankingController].visualizaRankingPorNivelTecnico}">Ranking</a></li>
          <li><a href="${linkTo[TenistaController].login}">Login</a></li>
        </ul>
        
      </div>
      
      <div class="social_plus_search floatright">
		<div class="social">
			
          <ul>
            <li><a href="https://www.facebook.com/portaldotenispb?fref=ts" class="facebook"></a></li>
            <a href="https://www.instagram.com/portaldotenispb/">			
            	<img src="/sigete/imagens/instagram.png" alt="" height="25" width="25"/>
            </a>
          </ul>
          
	
		
			
        </div>
         <div class="search">
          <form action="${linkTo[TenistaController].buscaTenistas}" method="post" id="search_form">
            <input type="text" id="s"  placeholder="Procurar Tenistas" name="nome"/>
            <input type="submit" id="searchform" value="search" />
            <input type="hidden" value="0" name="idNivel" />
          </form>
        </div>
        
      </div>
    </div>
    
    
    <div class="main_menu_area">
      <ul id="nav">
        <li><a href="${linkTo[IndexController].index}">Home</a></li>
        <li><a href="#">Tenista</a>
          <ul>
            <li><a href="${linkTo[TenistaController].formCadastro}">Cadastro</a></li>
            <li><a href="${linkTo[TenistaController].formProcuraTenistas}">Procurar tenistas</a></li>
            <li><a href="${linkTo[TenistaController].visualizaFiliados}">Premium</a></li>
          </ul>
        </li>
        <li><a href="#">Torneios</a>
          <ul>
            <li><a href="${linkTo[TorneioController].visualizaTorneios(0,0)}">Torneios do ranking paraibano</a></li>
            <li><a href="${linkTo[TorneioController].visualizaTorneios(1,0)}">Competições sem pontuação</a></li>
		     <li><a href="${linkTo[InscricaoController].realizaUmaInscricao}">Realizar minha inscrição</a></li>
		     <li><a href="${linkTo[InscricaoController].visualizaUltimosInscritos}" >Últimos inscritos</a></li>
		     <li><a href="${linkTo[MidiaTorneioController].visualizaMidiasDosTorneios}">Mídias</a></li>
          </ul>
        </li>
        <li><a href="#">Ranking</a>
          <ul>
            <li><a href="${linkTo[RankingController].visualizaRankingPorNivelTecnico}">Ranking por classes</a></li>
            <li><a href="${linkTo[RankingController].visualizaRankingPorIdade}">Ranking por idade</a></li>
            <li><a href="${linkTo[RankingController].visualizaRankingFemininoPorNivelTecnico}">Ranking feminino</a></li>
            <li><a href="${linkTo[RankingController].visualizaRankingDeDuplasPorNivelTecnico}">Ranking de duplas</a></li>
            <li><a href="/sigete/regulamento2015.pdf">Regulamento 2015</a></li>
            <li><a href="/sigete/regulamento2016.pdf">Regulamento 2016</a></li>
            <li><a href="/sigete/regulamento2017.pdf">Regulamento 2017</a></li>
            
          </ul>
        </li>
        <li><a href="#">Informativos</a>
          <ul>
            <li><a href="${linkTo[InformativoController].visualizaNoticiasLocais}">Locais</a></li>
            <li><a href="${linkTo[InformativoController].visualizaDicas}">Dicas</a></li>
          </ul>
        </li>
        <li><a href="#">Parceiros</a>
          <ul>
            <li><a href="${linkTo[MaisController].filiacao}">Ser Premium</a></li>
            <li><a href="${linkTo[MaisController].federacao}">Federação</a></li>
            <li><a href="${linkTo[MaisController].clubes}">Clubes e Academias</a></li>
            <li><a href="${linkTo[MaisController].professores}">Professores</a></li>
            <li><a href="${linkTo[MaisController].empresasParceiras}">Empresas</a></li>
          </ul>
        </li>
        <li><a href="${linkTo[MaisController].sobre}">Sobre</a></li>
        <li><a href="${linkTo[MaisController].contato}">Contato</a></li>
        <li><a href="#">Conta</a>
          <ul>
            <li><a href="${linkTo[IndexController].paginaInicial}">Meu perfil</a></li>
            <li><a href="${linkTo[PontuacaoController].visualizaMeusPontos}">Meus pontos</a></li>
            <li><a href="${linkTo[InscricaoController].visualizaMinhasInscricoes}">Minhas inscrições</a></li>
            <li><a href="${linkTo[TorneioController].visualizaMeusTorneios}">Meus torneios</a></li>
            <li><a href="${linkTo[JogoDeTorneioController].visualizaMeusJogos(0)}">Meus jogos</a></li>
          </ul>
        </li>
      </ul>
    </div>
     <!-- <div class="content_area" align="center">
     			<img src="/sigete/imagens/anuncie_aqui.png"alt="" width="70" height="30"/>
    			<img src="/sigete/imagens/anuncie_aqui.png"alt="" width="70" height="30"/>
    			<img src="/sigete/imagens/anuncie_aqui.png"alt="" width="70" height="30"/>
    			<img src="/sigete/imagens/anuncie_aqui.png"alt="" width="70" height="30"/>
    			<img src="/sigete/imagens/anuncie_aqui.png"alt="" width="70" height="30"/>
    	</div>
    	 -->
    
    <!-- cabeçalho -->