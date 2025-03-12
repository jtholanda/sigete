<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="pt_br">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Tudo do tênis paraibano em um só lugar. Competições, tenistas, torneios, inscrições, ranking. Tênis da Paraíba.  ">
    <meta name="author" content="José Thiago Holanda">
  

    <title>${empresa}</title>

   <c:import url="../comum/links.jsp"/>
    

</head>

<body>

<c:import url="../menu/menu.jsp"/>


    <!-- Portfolio Grid Section -->
    <section>
    
    <div class="container-fluid">
    
    
     <!-- Lateral esquerda -->
      <div class="col-lg-2">
        <c:import url="../menu/lateral_esquerda.jsp"/>
   	  </div>
    	<!-- termino da lateral esquerda -->
    
    	<!-- Parte do conteúdo-->
       <div class="col-lg-8 ">
	  <hr>
		  <div class="row">
		  <!-- 
		<div class="col-lg-4" align="center"><img alt="" src="imagens/empresasParceiras/logo_fpt.jpg" ></div>
		<div class="col-lg-4" align="center"><img alt="" src="imagens/logomarca.png" height="250" width="250" ></div>
		 <div class="col-lg-8" align="center"><img alt="" src="imagens/anuncie_aqui_banner_horizontal_grande.jpg"></div>-->
		</div>
		
		<hr>
		
		<div class="row">				  

		<!--  banners dos torneios -->
		<div class="col-sm-6 col-md-4">
		<div id="apresentacao-pagina-inicial" class="carousel slide" data-ride="carousel">
		  <!-- Indicators -->
		
		  <!-- Wrapper for slides -->
		  <div class="carousel-inner" role="listbox">
		    <div class="item active">
		    <a href="/sigete/torneio/detalhes-do-torneio/${midiaUm.torneio.id}">
		      <img src="${midiaUm.url}" alt="${midiaUm.titulo}">
		      </a>	 
		    </div>
		    <div class="item">
		    <a href="/sigete/torneio/detalhes-do-torneio/${midiaDois.torneio.id}">
		      <img src="${midiaDois.url}" alt="${midiaDois.titulo}">
		      </a>
		    </div>
			<div class="item">
		    <a href="/sigete/torneio/detalhes-do-torneio/${midiaTres.torneio.id}">
		      <img src="${midiaTres.url}" alt="${midiaTres.titulo}">
		      </a>
		    </div>		  
		   </div>
		
		  <!-- Controls -->
		  <a class="left carousel-control" href="#apresentacao-pagina-inicial" role="button" data-slide="prev">
		    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
		    <span class="sr-only">Anterior</span>
		  </a>
		  <a class="right carousel-control" href="#apresentacao-pagina-inicial" role="button" data-slide="next">
		    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
		    <span class="sr-only">Próximo</span>
		  </a>
		</div>
		</div>
		  
		 <!--  notícias locais --> 
		<div class="panel col-sm-3 col-md-4">		 
		 <c:forEach items="${noticiasLocais}" var="noticiaLocal">
			<div class="media">
			  <div class="media-left">
			    <a href="${linkTo[InformativoController].detalhaInformativo(noticiaLocal.id)}">
				    <c:if test="${noticiaLocal.imagemPrincipal!=null}">
				      <img class="media-object" src="${noticiaLocal.imagemPrincipal}" width="64px" height="64px" alt="...">
				    </c:if>
				    <c:if test="${noticiaLocal.imagemPrincipal==null}">
				      <img class="media-object" src="/sigete/imagens/logomarca.png" width="64px" height="64px" alt="...">
				    </c:if>
			    </a>
			  </div>
			  <div class="media-body">
			    <h5 class="media-heading">${noticiaLocal.titulo}</h5>
			    ${noticiaLocal.resumo}
			  </div>
			</div>
		 </c:forEach>
		 			<div align="right"><a href="${linkTo[InformativoController].visualizaNoticiasLocais}" >Notícias locais</a></div> 
		 
		 </div>
		 
		
      <!-- dicas -->
      <div class="panel col-sm-3 col-md-4">		 
		 <c:forEach items="${dicas}" var="dica">
			<div class="media">
			  <div class="media-left">
			    <a href="${linkTo[InformativoController].detalhaInformativo(dica.id)}">
				    <c:if test="${dica.imagemPrincipal!=null}">
				      <img class="media-object" src="${dica.imagemPrincipal}" width="64px" height="64px" alt="...">
				    </c:if>
				    <c:if test="${dica.imagemPrincipal==null}">
				      <img class="media-object" src="/sigete/imagens/logomarca.png" width="64px" height="64px" alt="...">
				    </c:if>
			    </a>
			  </div>
			  <div class="media-body">
			    <h5 class="media-heading">${dica.titulo}</h5>
			    ${dica.resumo}
			  </div>
			</div>
		 </c:forEach>
		 			<div align="right"><a href="${linkTo[InformativoController].visualizaDicas}" >Dicas</a></div> 
		 
		 </div>
      
      
		  
		</div>
		
		<!--  torneios em destaque -->
		<div class="row">
		<div class="panel panel-default col-sm-3 col-md-6">
            <div class="panel-heading">
                <h5><i class="fa fa-fw fa-check"></i>${torneioUm.nome}</h5>
            </div>
            <div class="panel-body">O torneio será realizado no ${torneioUm.local.nome}. ${torneioUm.observacoesAdicionais} 
                <div align="right"><a href="${linkTo[TorneioController].detalhaTorneio(torneioUm.id)}" class="btn btn-success">Mais informações</a></div>
            </div>
        </div>
        <div class="panel panel-default col-sm-3 col-md-6">
            <div class="panel-heading">
                <h5><i class="fa fa-fw fa-check"></i>${torneioDois.nome}</h5>
            </div>
            <div class="panel-body">O torneio será realizado no ${torneioDois.local.nome}. ${torneioDois.observacoesAdicionais}
                <div align="right"><a href="${linkTo[TorneioController].detalhaTorneio(torneioDois.id)}" class="btn btn-success">Mais informações</a></div>
            </div>
        </div>
        </div>
		
		<!-- imagens em destaques -->
		<div class="row">
		  <div class="col-xs-6 col-md-3">
		    <a href="${linkTo[MaisController].filiacao}" class="thumbnail">
		      <img src="imagens/filiacao_nova.jpg" alt="Em breve" class="img-rounded">
		    </a>
		  </div>

		  <div class="col-xs-6 col-md-3">
		    <a href="${linkTo[TorneioController].visualizaTorneios(0,0)}" class="thumbnail">
		      <img src="imagens/torneios2016.jpg" alt="..." class="img-rounded">
		    </a>
		  </div>

		  <div class="col-xs-6 col-md-3">
		    <a href="${linkTo[RankingController].visualizaRankingPorNivelTecnico}" class="thumbnail">
		      <img src="imagens/ranking.jpg" alt="..." class="img-rounded">
		    </a>
		  </div>
			
		  <div class="col-xs-6 col-md-3">
		    <a href="${linkTo[InformativoController].visualizaDicas}"  class="thumbnail">
		      <img src="imagens/dicas_tenis.jpg" alt="..." class="img-rounded">
		    </a>
		  </div>
  			
		</div>	 
		
	<!-- Marketing Icons Section 
	
        <div class="row">
            <div class="col-md-4">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4><i class="fa fa-fw fa-check"></i>Cadastro</h4>
                    </div>
                    <div class="panel-body">
                        <p>Realize o seu cadastro no portal do tênis paraibano e fique por dentro de todas as competições de tênis que ocorrem no estado, além de participar do ranking paraibano de tenistas.</p>
                        <a href="${linkTo[TenistaController].formCadastro}" class="btn btn-default">Cadastre-se</a>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4><i class="fa fa-fw fa-list"></i>Inscrições abertas</h4>
                    </div>
                    <div class="panel-body">
                        <p>Verifique as competições de tênis no estado que estão com as inscrições abertas, faça a sua inscrição pelo site e entre em contato com a organização para realizar o pagamento.</p>
                        <a href="${linkTo[InscricaoController].realizaUmaInscricao}" class="btn btn-default">Inscrever-me</a>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4><i class="fa fa-fw fa-search"></i>Tenistas</h4>
                    </div>
                    <div class="panel-body">
                        <p>Pesquise os tenista cadastrados e analise os dados técnicos de cada um. Assim você também poderá buscar mais informações do seu adversário e conhecê-lo melhor.</p>
                        <a href="${linkTo[TenistaController].formProcuraTenistas}"class="btn btn-default">Pesquisar</a>
                    </div>
                </div>
            </div>
        </div>
   	
		--> 
	</div>
		<!--  termino da parte do meio -->

		<!-- lateral direita -->		
		<div class="col-lg-2 ">
        	        <c:import url="../menu/lateral_direita.jsp"/>
        	
		</div>
		</div>
		<!--  termino do container -->

 
		
</section>

   
    
    <c:import url="../menu/rodape.jsp"></c:import>

      <c:import url="../comum/scripts.jsp"/>
   

    
</body>

</html>
