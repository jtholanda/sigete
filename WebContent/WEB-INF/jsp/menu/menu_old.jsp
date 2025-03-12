	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:import url="../menu/googleAnalitics.jsp"></c:import>
   
    <nav class="navbar navbar-default navbar-fixed-top">
    
        <div class="container">
       
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header page-scroll">
                
                <span>
                <a class="navbar-brand" href="${linkTo[IndexController].index}">Portal do T�nis Paraibano</a>
                </span>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                  
                <ul class="nav navbar-nav navbar-right" >
                 
                <li class="dropdown">
			          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Tenistas<span class="caret"></span></a>
			          <ul class="dropdown-menu" role="menu">
			            <li><a href="${linkTo[TenistaController].formCadastro}">Cadastro</a></li>
			            <li><a href="${linkTo[TenistaController].formProcuraTenistas}">Procurar tenistas</a></li>
			            <li><a href="${linkTo[TenistaController].visualizaFiliados}">Filiados</a></li>
			            <c:if test="${tenistaLogado != null }">
			            <li class="divider"></li>
			            <li><a href="${linkTo[IndexController].paginaInicial}">Meu perfil</a></li>
			            <li><a href="${linkTo[PontuacaoController].visualizaMeusPontos}">Meus pontos</a></li>
			            <li><a href="${linkTo[InscricaoController].visualizaMinhasInscricoes}">Minhas inscri��es</a></li>
			            <li><a href="${linkTo[TorneioController].visualizaMeusTorneios}">Meus Torneios</a></li>
			            <li><a href="${linkTo[JogoDeTorneioController].visualizaMeusJogos}">Meus jogos</a></li>
			            </c:if>
			          </ul>
		       	 </li>
		       	 <li class="dropdown">
			          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Competi��es<span class="caret"></span></a>
			          <ul class="dropdown-menu" role="menu">
			           													           
			         
			            <li><a href="${linkTo[TorneioController].visualizaTorneios(0,0)}" >Torneios do ranking paraibano</a></li>
			            <li><a href="${linkTo[TorneioController].visualizaTorneios(1,0)}" >Etapas de ranking interno de clubes</a></li>
			            <li class="divider"></li>
			            <li><a href="${linkTo[InscricaoController].realizaUmaInscricao}">Realizar minha inscri��o</a></li>
			            <li><a href="${linkTo[InscricaoController].visualizaUltimosInscritos}" >�ltimos inscritos</a></li>
			            <li><a href="${linkTo[MidiaTorneioController].visualizaMidiasDosTorneios}">M�dias</a></li>
			          </ul>
		       	 </li>
				<li class="dropdown">
			          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Rankings <span class="caret"></span></a>
			          <ul class="dropdown-menu" role="menu">
			            <li><a href="${linkTo[RankingController].visualizaRankingGeral}" >Ranking paraibano</a></li>
			            <li><a href="${linkTo[RankingController].visualizaRankingPorNivelTecnico}">Ranking por classes</a></li>
			            <li><a href="${linkTo[RankingController].visualizaRankingPorIdade}">Ranking por idade</a></li>
			            <li class="divider"></li>
			            <li><a href="${linkTo[RankingController].visualizaRankingFemininoPorNivelTecnico}">Ranking feminino por classes</a></li>
			            <li class="divider"></li>
			            <li><a href="${linkTo[RankingController].visualizaRankingDeDuplasPorNivelTecnico}">Ranking de duplas</a></li>
			            <li class="divider"></li>
			            <li><a href="/sigete/regulamento2015.pdf" target="_blank">Regulamento do ranking 2015</a></li>
			            <li><a href="/sigete/regulamento2016.pdf" target="_blank">Regulamento do ranking 2016</a></li>
			            
			          </ul>
		       	 </li>
		       	 <li class="dropdown">
			          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Mais<span class="caret"></span></a>
			          <ul class="dropdown-menu" role="menu">
			            <li><a href="${linkTo[MaisController].federacao}">Federa��o paraibana</a></li>
						<li><a href="${linkTo[MaisController].filiacao}">Filiar-se a FPbT</a></li>
			            <li><a href="${linkTo[MaisController].clubes}">Clubes</a></li>
			            <li><a href="${linkTo[MaisController].professores}">Professores</a></li>
			            <li><a href="${linkTo[MaisController].empresasParceiras}">Empresas parceiras</a></li>
	  	                <li class="divider"></li>
		  	            <li><a href="${linkTo[InformativoController].visualizaNoticiasLocais}">T�nis local</a></li>
			            <li><a href="${linkTo[InformativoController].visualizaNoticiasInternacionais}">T�nis no mundo</a></li>
			            <li><a href="${linkTo[InformativoController].visualizaDicas}">Dicas</a></li>
	  	                <li class="divider"></li>
			            <li><a href="${linkTo[MaisController].sobre}">Sobre</a></li>
			          </ul>
		       	 </li>
                 <li class="page-scroll">
                     <a href="${linkTo[MaisController].contato}">Contato</a>
                 </li>
                 
                </ul>
                
            </div>
            
            
            <!-- /.navbar-collapse -->
            
             
        </div>
        <!-- /.container-fluid -->
    </nav>
  	 
  