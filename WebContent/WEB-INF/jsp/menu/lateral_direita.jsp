<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
                 
  <hr>               

         <div class="row">
            <c:if test="${tenistaLogado == null }">
            <!--  formulário de login -->
					<form  action="${linkTo[TenistaController].logaTenista}" method="post">
					<input type="hidden" value="${urlRequisitada}" name="urlRequisitada">
						<h6>Sistema de gestão de competições de tênis e beach tennis V2.5</h6>
						<small>${mensagemDeLogin}</small>
							
					<div class="input-group margin-bottom-sm">
						<span class="input-group-addon"><i
							class="fa fa-envelope-o fa-fw"></i></span> 
							<input class="form-control"	type="text" placeholder="E-mail" name="tenista.email" value="${tenista.email}" required data-validation-required-message="Informe o e-mail" >
					</div>
					
					<div class="input-group">
						<span class="input-group-addon"><i class="fa fa-key fa-fw"></i></span>
						<input class="form-control" type="password" placeholder="Senha" name="tenista.senha" required data-validation-required-message="Informe a senha">
					</div>
					
						<div class="btn btn-toogle">
						<button class="btn btn-success">Entrar</button>
						<a href="${linkTo[TenistaController].formRecuperaSenha}"
							class="btn btn-danger">Esqueci a senha</a>
							
						</div>
						<div class="btn btn-toogle">
							<a class="btn btn-info" href="${linkTo[TenistaController].formCadastro}">Eu ainda não tenho cadastro!</a>
						</div>
						
					</form>
            
            
            </c:if>
            <c:if test="${tenistaLogado!=null }">
            <h6>Olá ${tenistaLogado.nome }! </h6>
				<a href="${linkTo[IndexController].paginaInicial}"  >Meu Perfil</a> <br>
				<a href="${linkTo[TenistaController].formEditaTenista}"  >Alterar meus dados</a> <br>
				<a href="${linkTo[TenistaController].formAlteraSenha}"   >Alterar senha</a> <br>
				<a href="${linkTo[MaisController].formSolicitaMudancaNivel}"   >Solicitar mudança de nível técnico</a> <br>
				<a href="${linkTo[TenistaController].logout}" type="button" >Sair</a><br/> 
            </c:if>
           <c:if test="${organizador}">
            <h6>Plus para Organizadores</h6>
				<a href="${linkTo[InformativoController].formCadastraInformativo}" >Cadastro de informativos</a><br/>
				<a href="${linkTo[InformativoController].gerenciaNoticiasLocais}" >Gerencia Notícias Locais</a><br/>
				<a href="${linkTo[InformativoController].gerenciaNoticiasInternacionais}" >Gerencia Notícias Internacionais</a><br/>
				<a href="${linkTo[InformativoController].gerenciaDicas}" >Gerencia Dicas</a><br/>
				<a href="${linkTo[TorneioController].formCriaTorneio}" >Criar Torneio</a><br/>
				<a href="${linkTo[TorneioController].visualizaMeusTorneios}" >Meus Torneios</a><br/>
            </c:if>
           <c:if test="${administrador}">
            <h6>Plus para Administradores</h6>
	            <a href="${linkTo[AdminController].formCadastraFiliado}" >Cadastrar Filiado</a><br/> 
	            <a href="${linkTo[AdminController].formEnviaEmails}"  >Enviar e-mails</a><br/> 
				<a href="${linkTo[AdminController].atribuiPontuacaoDosTenistas}" >Atribuir pontuação dos ${esportista}s</a><br/>
				<a href="${linkTo[AdminController].atribuiPontuacaoDeTenistasSemPontos}" >Atribuir pontuação de ${esportista}s sem pontos</a><br/>
				<a href="${linkTo[AdminController].atribuiPontuacaoDeDuplasDosTenistas}" >Atribuir pontuação de duplas dos ${esportista}s</a><br/>
				<a href="${linkTo[AdminController].visualizaTenistasDesativados}" >${esportista}a desativados</a><br/>
				<a href="${linkTo[AdminController].visualizaTenistasDesconfirmados}" >${esportista}s não confirmados manualmente</a><br/>
				<a href="${linkTo[AdminController].visualizaTenistasSuspensos}" >${esportista}s Suspensos</a><br/>
            </c:if>
          		
		</div>
					<!--  
				<div class="row">
				<div id="fotos-torneio" class="carousel slide" data-ride="carousel" data-interval="2000">
				
				  <ol class="carousel-indicators">
				    <li data-target="#fotos-torneio" data-slide-to="0" class="active"></li>
				    <li data-target="#fotos-torneio" data-slide-to="1"></li>
   				    <li data-target="#fotos-torneio" data-slide-to="2"></li>
   				    <li data-target="#fotos-torneio" data-slide-to="3"></li>
   				    <li data-target="#fotos-torneio" data-slide-to="4"></li>
   
				  </ol>
				
				  <div class="carousel-inner" role="listbox">
				    <div class="item active">
				           <img src="/sigete/imagens/foto_torneio01.jpg" class="img-responsive" alt="" height="650" width="900">
				      <div class="carousel-caption">
				      </div>
				    </div>
				    <div class="item">
				     <img src="/sigete/imagens/foto_torneio02.jpg" class="img-responsive" alt="" height="650" width="900">
				      <div class="carousel-caption">
				      </div>
				    </div>
   				    <div class="item">
				     <img src="/sigete/imagens/foto_torneio03.jpg" class="img-responsive" alt="" height="650" width="900">
				      <div class="carousel-caption">
				      </div>
				    </div>
   				    <div class="item">
				     <img src="/sigete/imagens/foto_torneio04.jpg" class="img-responsive" alt="" height="650" width="900">
				      <div class="carousel-caption">
				      </div>
				    </div>
   				    <div class="item">
				     <img src="/sigete/imagens/foto_torneio05.jpg" class="img-responsive" alt="" height="650" width="900">
				      <div class="carousel-caption">
				      </div>
				    </div>
				    
				Fotos dos torneios. <a href="">Ver mais</a> 
				</div>

				  <a class="left carousel-control" href="#fotos-torneio" role="button" data-slide="prev">
				    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
				    <span class="sr-only">Próxima</span>
				  </a>
				  <a class="right carousel-control" href="#fotos-torneio" role="button" data-slide="next">
				    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
				    <span class="sr-only">Anterior</span>
				  </a>
				</div>
				</div>
				
				
				<div class="row">
				<br>
				<div class="container">
				<div id="fotos-torneio" class="carousel slide" data-ride="carousel" data-interval="3000">
				
				   
				  <ol class="carousel-indicators">
				  <c:forEach var="imagem" items="${imagensLaterais}" varStatus="contador">
				  <c:if test="${contador.count == 1}">
				    <li data-target="#fotos-torneio" data-slide-to="0" class="active"></li>
				   </c:if>
				  <c:if test="${contador.count > 1}">
				    <li data-target="#fotos-torneio" data-slide-to="${contador.count}"></li>
				   </c:if>
   				  </c:forEach>
				  </ol>
				  
				  <div class="carousel-inner" role="listbox">

				  <c:forEach var="imagem" items="${imagensLaterais}" varStatus="contador">

				  <c:if test="${contador.count == 1}">
				    <div class="item active">
				      <div class="carousel-caption"><h3>${imagem.titulo}</h3></div>
				           <img src="${imagem.url}" class="img-responsive" alt="${imagem.titulo}" height="325" width="450">
				     
				    </div>
				   </c:if>

				  <c:if test="${contador.count > 1}">
				    <div class="item">
				         <div class="carousel-caption"><h3>${imagem.titulo}</h3></div>
				           <img src="${imagem.url}" class="img-responsive" alt="${imagem.titulo}" height="325" width="450">
				      
				    </div>
				   </c:if>
				   
				   
				    </c:forEach>
				</div>

				  <a class="left carousel-control" href="#fotos-torneio" role="button" data-slide="prev">
				    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
				    <span class="sr-only">Próxima</span>
				  </a>
				  <a class="right carousel-control" href="#fotos-torneio" role="button" data-slide="next">
				    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
				    <span class="sr-only">Anterior</span>
				  </a>
				</div>
				</div>
			
		</div>	
		
		
		
				<br>	
								-->
				
				<div class="row">
				<!-- Carrosel de publicidade -->			
					<div id="carousel-example-generic-lateral-direita" class="carousel slide" data-ride="carousel" data-interval="3000">
					
					  
					
					  <!-- Wrapper for slides -->
					  <div class="carousel-inner" role="listbox">
					    <div class="item active">
					           <a href="${linkTo[MaisController].contato}"><img src= "/sigete/imagens/anuncie_aqui.png" alt="" height="650" width="900"></a>		           
					      <div class="carousel-caption">
					      </div>
					    </div>
					    <div class="item">
					    <a href="https://www.facebook.com/Centro-Tenistico-Paraibano-297135400304092/?fref=ts" target="_blank"> <img src="/sigete/imagens/empresasParceiras/logo_ctp.jpg" alt="" height="650" width="900"></a>
					      <div class="carousel-caption">
					      </div>
					    </div>
					Faça seu anúncio. <a href="${linkTo[MaisController].contato}">Contratar</a> 
					</div>
	
					  <!-- Controls -->
					  <a class="left carousel-control" href="#carousel-example-generic-lateral-direita" role="button" data-slide="prev">
					    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
					    <span class="sr-only">Próxima</span>
					  </a>
					  <a class="right carousel-control" href="#carousel-example-generic-lateral-direita" role="button" data-slide="next">
					    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
					    <span class="sr-only">Anterior</span>
					  </a>
					</div>
				</div>
				<!--  termino do carrosel -->
			
				<br>
				<div class="row">
				<!--  
				<div class="span8">
				<div class="embed-container">
					<iframe src="https://www.youtube.com/embed/lTr_fdBv2Wg" frameborder="0"></iframe>
				</div>
				<div class="span4"> Torneio de simples do centro tenístico
				</div> 
				</div>
				-->
				</div> 
				<br>
	 			<div class="row">
				  <div class="col-xs-12">
				    <a href="${linkTo[MaisController].contato}" class="thumbnail">
				      <img src="/sigete/imagens/espaco_publicitario.jpg" alt="Anuncie aqui!" class="img-rounded">
				    </a>
				  </div>				  
								  

				 </div>
			  				
		
		
								<!-- termino da parte lateral direita -->