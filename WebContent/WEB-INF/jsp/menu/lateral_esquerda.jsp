<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
   <hr>
         <div class="row">
         
          
      
			<div class="list-group">
			
                <a class="list-group-item active " href="#"> <i class="fa fa-home fa-flash"></i>&nbsp;Acesso rápido</a>
                          
				<a class="list-group-item" href="${linkTo[IndexController].index}"> <i class="fa fa-home "></i>&nbsp;Home</a>
				<a class="list-group-item" href="${linkTo[MaisController].filiacao}"> <i class="fa fa-check"></i>&nbsp;Filiar-se</a>
				<c:if test="${tenistaLogado != null }">
					<a class="list-group-item" href="${linkTo[TenistaController].formCadastro}"> <i class="fa fa-book fa-fw"></i>&nbsp;Cadastro de ${esportista}s</a>
				</c:if>
				<c:if test="${tenistaLogado == null }">
					<a class="list-group-item" href="${linkTo[TenistaController].formCadastro}"> <i class="fa fa-book fa-fw"></i>&nbsp;Cadastre-se</a>
				</c:if> 
				 
				<a class="list-group-item" href="${linkTo[TenistaController].formProcuraTenistas}"><i class="fa fa-search"></i>&nbsp;Procurar ${esportista}s </a> 
				<a class="list-group-item" href="${linkTo[TorneioController].visualizaTorneios(0,0)}"><i class="fa fa-trophy"></i>&nbsp;Torneios do ranking paraibano</a>
				<a class="list-group-item" href="${linkTo[InscricaoController].realizaUmaInscricao}" ><i class="fa fa-check-circle"></i>&nbsp;Realizar inscrição</a>
				<a class="list-group-item" href="${linkTo[TorneioController].visualizaTorneios(1,0)}"><i class="fa fa-trophy"></i>&nbsp;Competições internas sem pontuação</a>
				<a class="list-group-item" href="${linkTo[RankingController].visualizaRankingPorNivelTecnico}"> <i class="fa fa-list-ol"></i>&nbsp;Ranking por nível técnico</a> 
				
			</div>
		</div>
		<div class="row">
			<!-- Carrosel de publicidade -->			
				<div id="carousel-example-generic" class="carousel slide" data-ride="carousel" data-interval="3000">
				
				
				  <!-- Wrapper for slides -->
				  <div class="carousel-inner" role="listbox">
				    <div class="item active">
				       <a href="https://www.facebook.com/starttenis/" target="_blank"><img src="/sigete/imagens/empresasParceiras/start_tenis.png" class="img-responsive" alt="" height="650" width="900"></a>    
				           
				      <div class="carousel-caption">
				      </div>
				    </div>
				    <div class="item">
				     <a href="/sigete/torneio/detalhes-do-torneio/41"><img src="/sigete/imagens/empresasParceiras/tennis_point_pb.jpg" class="img-responsive" alt="" height="650" width="900"></a>
				      <div class="carousel-caption">
				      </div>
				    </div>
				Faça seu anúncio. <a href="${linkTo[MaisController].contato}">Contratar</a> 
				</div>

				  <!-- Controls -->
				  <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
				    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
				    <span class="sr-only">Próxima</span>
				  </a>
				  <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
				    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
				    <span class="sr-only">Anterior</span>
				  </a>
				</div>
			</div>
			<!--  termino do carrosel -->
			<br>
			
			 <div class="row">
			  <div class="col-xs-6 col-md-6">
			    <a href="https://www.facebook.com/Centro-Tenistico-Paraibano-297135400304092/?fref=ts" target="_blank" class="thumbnail">
			      <img src="/sigete/imagens/empresasParceiras/ctp.jpg" alt="Centro Tenístico Paraibano" class="img-circle">
			    </a>
			  </div>		
			  <div class="col-xs-6 col-md-6">
			    <a href="http://www.fpbtenis.com.br/index.php" class="thumbnail" target="_blank">
			      <img src="/sigete/imagens/empresasParceiras/logo_fpt.jpg" alt="FPbT" class="img-circle">
			    </a>
			  </div>
			  </div>
			 <div class="row">
			  <div class="col-xs-6 col-md-6">
			    <a href="https://www.facebook.com/pages/Pet-Shop-Hobby-Bichos/181027502003475" target="_blank" class="thumbnail">
			      <img src="/sigete/imagens/empresasParceiras/logo_hb.jpg" alt="Hobby Bichos" class="img-circle">
			    </a>
			  </div>		
			  <div class="col-xs-6 col-md-6">
			    <a href="https://www.facebook.com/EraUmaVezBabyKids?fref=ts" class="thumbnail" target="_blank">
			      <img src="/sigete/imagens/empresasParceiras/logo_euv.jpg" alt="Era Uma Vez Baby Kids"  >
			    </a>
			  </div>
			  </div>
			 <div class="row">
			  <div class="col-xs-6 col-md-6">
			    <a href="${linkTo[MaisController].contato}" target="_blank" class="thumbnail">
			      <img src="/sigete/imagens/espaco_publicitario.jpg" alt="Centro Tenístico Paraibano" class="img-circle">
			    </a>
			  </div>		
			  </div>
			  
			  
