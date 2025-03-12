<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="pt_br">
<head>
<title>Portal do Tênis Paraibano</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Tudo do tênis paraibano em um só lugar. Competições, tenistas, torneios, inscrições, ranking. Tênis da Paraíba.  ">
    <meta name="author" content="José Thiago Holanda">

   <c:import url="../comum/links.jsp"/>

</head>
<body>
<div class="body_wrapper">
  <div class="center">
  
     <c:import url="../menu/menu.jsp"/>
  
  
    <div class="content_area">
    
    <br/>
      <div class="main_content floatleft">
        <div class="left_coloum floatleft">
          <!--  <div class="single_left_coloum_wrapper">
            <h2 class="title">Anuncie aqui!</h2>
            	            
            <div class="single_left_coloum floatleft">
			<img src="imagens/anuncie_aqui.png"alt="" />
			</div>
			</div>
			-->
			<!-- 
          <div class="single_left_coloum_wrapper">
            <h2 class="title">Produtos</h2>
            	            
            <div class="single_left_coloum floatleft">
			<img src="imagens/bolas_wilson.jpg"alt="" />
              <h3>Bolas de Tênis Wilson Championship</h3>
              <p>Tubo com 3 Bolas</p>
              <span style="color:red">R$29,90</span>
            </div>
            <div class="single_left_coloum floatleft">
			<img src="imagens/grip_gamma.jpg"alt="" />
              <h3>Grip Gamma</h3>
              <p>Aderência excelente</p>
              <span style="color:red">R$10,90<small> (unidade) </small></span>
			</div>
            <div class="single_left_coloum floatleft"><a href="/sigete/torneio/detalhes-do-torneio/${midiaTres.torneio.id}">
			<img src="imagens/corda_sigma.jpg"alt="" />
              <h3>Set de Corda Sigma</h3>
              <p>Super Poly Spin 1.25mm</p>
              <span style="color:red">R$19,90<small> (12m) </small></span>
             </div>
          </div>
           -->
        
          <div class="single_left_coloum_wrapper">
            <h2 class="title">Torneios</h2>
            	            
            <a class="more" href="${linkTo[TorneioController].visualizaTorneios(0,0)}">mais</a>
            <div class="single_left_coloum floatleft"><a href="/sigete/torneio/detalhes-do-torneio/${midiaUm.torneio.id}">
            <img src="${midiaUm.url}" alt="${midiaUm.titulo}"></a>
              <h3>${torneioUm.nome}</h3>
              <p>O torneio será realizado no ${torneioUm.local.nome}. ${torneioUm.observacoesAdicionais}.</p>
              <a class="readmore" href="${linkTo[TorneioController].detalhaTorneio(torneioUm.id)}">mais informações</a> </div>
            <div class="single_left_coloum floatleft"><a href="/sigete/torneio/detalhes-do-torneio/${midiaDois.torneio.id}">
		      <img src="${midiaDois.url}" alt="${midiaDois.titulo}"></a>
              <h3>${torneioDois.nome}</h3>
              <p>O torneio será realizado no ${torneioDois.local.nome}. ${torneioDois.observacoesAdicionais}.</p>
              <a class="readmore" href="${linkTo[TorneioController].detalhaTorneio(torneioDois.id)}">mais informações</a> </div>
            <div class="single_left_coloum floatleft"><a href="/sigete/torneio/detalhes-do-torneio/${midiaTres.torneio.id}">
		      <img src="${midiaTres.url}" alt="${midiaTres.titulo}"></a>
              <h3>${torneioTres.nome}</h3>
              <p>O torneio será realizado no ${torneioTres.local.nome}. ${torneioTres.observacoesAdicionais}.</p>
              <a class="readmore" href="${linkTo[TorneioController].detalhaTorneio(torneioTres.id)}">mais informações</a> </div>
          </div>
          
          <div class="single_left_coloum_wrapper">
            <h2 class="title">Informativos Locais</h2>
            <a class="more" href="${linkTo[InformativoController].visualizaNoticiasLocais}">mais</a>
            
            <c:forEach items="${noticiasLocais1}" var="noticiaLocal">
	            <div class="single_left_coloum floatleft"> 
	            <a href="${linkTo[InformativoController].detalhaInformativo(noticiaLocal.id)}">
				    <c:if test="${noticiaLocal.imagemPrincipal!=null}">
				      <img class="media-object" src="${noticiaLocal.imagemPrincipal}" alt="...">
				    </c:if>
				    <c:if test="${noticiaLocal.imagemPrincipal==null}">
				      <img class="media-object" src="/sigete/imagens/logomarca.png" alt="...">
				    </c:if>
			    </a>
			    <a href="${linkTo[InformativoController].detalhaInformativo(noticiaLocal.id)}">
	              <h3>${noticiaLocal.titulo}</h3></a>
	              <p> ${noticiaLocal.resumo}</p>
	              <a class="readmore" href="${linkTo[InformativoController].detalhaInformativo(noticiaLocal.id)}">leia mais</a> 
	            </div>
              </c:forEach>
          </div>
          <div class="single_left_coloum_wrapper">
            
            <c:forEach items="${noticiasLocais2}" var="noticiaLocal">
	            <div class="single_left_coloum floatleft"> 
	            <a href="${linkTo[InformativoController].detalhaInformativo(noticiaLocal.id)}">
				    <c:if test="${noticiaLocal.imagemPrincipal!=null}">
				      <img class="media-object" src="${noticiaLocal.imagemPrincipal}" alt="...">
				    </c:if>
				    <c:if test="${noticiaLocal.imagemPrincipal==null}">
				      <img class="media-object" src="/sigete/imagens/logomarca.png" alt="...">
				    </c:if>
			    </a>
			    <a href="${linkTo[InformativoController].detalhaInformativo(noticiaLocal.id)}">
	              <h3>${noticiaLocal.titulo}</h3></a>
	              <p> ${noticiaLocal.resumo}</p>
	              <a class="readmore" href="${linkTo[InformativoController].detalhaInformativo(noticiaLocal.id)}">leia mais</a> 
	            </div>
              </c:forEach>
          </div>
          
          <div class="single_left_coloum_wrapper single_cat_left">
            <h2 class="title">Clubes e Academias</h2>
            <a class="more" href="${linkTo[MaisController].clubes}">mais</a> 
			
			<div class= "row"><div class="col-xs-12 col-md-12">
			
            <a href="https://www.facebook.com/pages/Ansef-Para%C3%ADba/108613475962832?fref=ts" target="_blank"><img src="imagens/clubes/logo_ansef.png" alt="" height="70" width="70" /></a>
            
            <a href="#"><img src="imagens/clubes/logo_atg.jpg" alt="" height="70" width="70"/></a>
             
            <a href="https://www.facebook.com/groups/412907205402188/?fref=ts" target="_blank"><img src="imagens/clubes/logo_cam.jpg" alt="" height="70" width="70"/></a>
             
            <a href="https://www.facebook.com/pages/Esporte-Clube-Cabo-Branco/197781823596749?fref=ts" target="_blank"><img src="imagens/clubes/logo_cb.jpg" alt="" height="70" width="70"/></a>

            <a href="https://www.facebook.com/pages/Centro-Tenistico-Paraibano/297135400304092?fref=ts" target="_blank"><img src="imagens/clubes/logo_ctp.jpg" alt="" height="70" width="70"/></a>

            <a href="https://www.facebook.com/pages/Hotel-Tamba%C3%BA/106851299401410?fref=ts" target="_blank"><img src="imagens/clubes/logo_tennispoint.jpg" alt="" height="70" width="70"/></a>
            </div></div> 
          </div>
            
          <div class="single_left_coloum_wrapper single_cat_left">
          
            <h2 class="title">Professores</h2>
            <a class="more" href="${linkTo[MaisController].professores}">mais</a>
            <div class="single_left_coloum floatleft"> 
     		<h3>Prof. Marinho</h3><small>Aulas no Centro Tenístico Paraibano</small></div>
            <div class="single_left_coloum floatleft"> 
     		<h3>Prof. Joca</h3><small>Aulas no Centro Tenístico Paraibano e Cabo Branco</small></div>
            <div class="single_left_coloum floatleft"> 
     		<h3>Prof. Macêdo</h3><small>Aulas no Centro Tenístico Paraibano</small></div>
            <div class="single_left_coloum floatleft"> 
     		<h3>Prof. Vavado</h3><small>Aulas em condomínios</small></div>
            <div class="single_left_coloum floatleft"> 
     		<h3>Prof. Hélio</h3><small>Aulas em condomínios</small></div>
            <div class="single_left_coloum floatleft"> 
     		<h3>Prof. Diego</h3><small>Aulas em condomínios</small></div>
            <div class="single_left_coloum floatleft"> 
     		<h3>Prof. Walmir</h3><small>Aulas em condomínios</small></div>
            <div class="single_left_coloum floatleft"> 
     		<h3>Prof. Alexandre</h3><small>Aulas no Cabo Branco</small></div>
            <div class="single_left_coloum floatleft"> 
     		<h3>Prof. Sérgio</h3><small>Aulas no Cabo Branco</small></div>
            <div class="single_left_coloum floatleft"> 
     		<h3>Prof. Mário</h3><small>Aulas no Campestre</small></div>
            <div class="single_left_coloum floatleft"> 
     		<h3>Prof. Lee</h3><small>Aulas no Campestre</small></div>
            <div class="single_left_coloum floatleft"> 
     		<h3>Prof. Helinho</h3><small>Aulas no Campestre e Condomínios</small></div>
            <div class="single_left_coloum floatleft"> 
     		<h3>Prof. Léo Dutra</h3><small>Aulas no Garden e Condomínios</small></div>
             
            </div>
      
        </div>
        
        
        <div class="right_coloum floatright">
          
          <div class="single_right_coloum">
            <h2 class="title">Dicas do Portal</h2>
            <ul><c:forEach items="${dicas}" var="dica">
            
              <li>
                <div class="single_cat_right_content">
                  <h3>${dica.titulo}</h3>
                  <p>${dica.resumo}</p>
                  <p class="single_cat_right_content_meta">
                  <a href="${linkTo[InformativoController].detalhaInformativo(dica.id)}">								
                  <fmt:formatDate value="${dica.data.time}" type="both" pattern="dd/MM/yyyy" /><span> leia mais</span></a> </p>
                </div>
              </li>
              
              </c:forEach>
            </ul>
            <a class="popular_more" href="${linkTo[InformativoController].visualizaDicas}">mais</a> 
            </div>
          
          <div class="single_right_coloum">
              <h2 class="title">Ranking</h2>
            <!--
            <div class="single_cat_right_content editorial"> <a href="${linkTo[MaisController].filiacao}" class="thumbnail">
            <img src="imagens/botao_premium.jpg"alt="" /></a>
              <h3>Seja um premium e tenha descontos nos torneios.</h3>
            </div>
            -->
            <!--  <div class="single_cat_right_content editorial"><a href="${linkTo[TorneioController].visualizaTorneios(0,0)}" class="thumbnail">
               <img src="imagens/calendario.jpg" alt="" /></a>
              <h3>Confira o calendário de torneios em 2018.</h3>
            </div>
            -->
            <div class="single_cat_right_content editorial"><a href="${linkTo[RankingController].visualizaRankingPorNivelTecnico}" class="thumbnail">
             <img src="imagens/ranking.jpg" alt="" /></a>
              <h3>Ranking de todas as classes.</h3>
            </div>
          </div>
        </div>
      </div>
      
      
      
      <div class="sidebar floatright">

        <div class="single_sidebar">
          <h2 class="title">Menu</h2>
          <div class="news-letter">
          
            <c:if test="${tenistaLogado == null }">
	            <h2>Acesse sua conta</h2>
	            <p>Sistema de Gestão de Competições de Tênis 3.0</p>
	            <form action="${linkTo[TenistaController].logaTenista}" method="post">
	              <input type="text" placeholder="E-mail" name="tenista.email" value="${tenista.email}" required data-validation-required-message="Informe o e-mail" id="name"  />
	              <input type="password" placeholder="Senha" name="tenista.senha" required data-validation-required-message="Informe a senha" id="email"/>
	              <br/><br/>
	              <input type="submit" value="Entrar" class="btn btn-success" />
	              <a href="${linkTo[TenistaController].formRecuperaSenha}"
								class="btn btn-danger">Esqueci a senha</a>
	            </form>
	            <p class="news-letter-privacy">ainda não sou cadastrado, <a href="${linkTo[TenistaController].formCadastro}" >clique aqui</a></p>
            </c:if>
            
            <c:if test="${tenistaLogado!=null }">
            <h6>Olá ${tenistaLogado.nome }, opções disponíveis para você! </h6>
				<a href="${linkTo[IndexController].paginaInicial}"  ><small>Meu Perfil</small></a> <br/>
				<a href="${linkTo[TenistaController].formEditaTenista}"  ><small>Alterar meus dados</small></a> <br/>
				<a href="${linkTo[TenistaController].formAlteraSenha}"   ><small>Alterar senha</small></a> <br/>
				<a href="${linkTo[MaisController].formSolicitaMudancaNivel}"   ><small>Solicitar mudança de nível técnico</small></a> <br/>
				<a href="${linkTo[TenistaController].logout}" type="button" ><small>Sair</small></a><br/> 
            </c:if>
           <c:if test="${organizador}">
           <br/>
            <h6>Plus para Organizadores</h6>
				<a href="${linkTo[InformativoController].formCadastraInformativo}" ><small>Cadastro de informativos</small></a><br/>
				<a href="${linkTo[InformativoController].gerenciaNoticiasLocais}" ><small>Gerencia Notícias Locais</small></a><br/>
				<a href="${linkTo[InformativoController].gerenciaNoticiasInternacionais}" ><small>Gerencia Notícias Internacionais</small></a><br/>
				<a href="${linkTo[InformativoController].gerenciaDicas}" ><small>Gerencia Dicas</small></a><br/>
				<a href="${linkTo[TorneioController].formCriaTorneio}" ><small>Criar Torneio</small></a><br/>
				<a href="${linkTo[TorneioController].visualizaMeusTorneios}" ><small>Meus Torneios</small></a><br/>
            </c:if>
           <c:if test="${administrador}">
           <br/>
            <h6>Plus para Administradores</h6>
	            <a href="${linkTo[AdminController].formCadastraFiliado}" ><small>Cadastrar Filiado</small></a><br/> 
	            <a href="${linkTo[AdminController].formEnviaEmails}"  ><small>Enviar e-mails</small></a><br/> 
				<a href="${linkTo[AdminController].atribuiPontuacaoDosTenistas}" ><small>Atribuir pontuação dos tenistas</small></a><br/>
				<a href="${linkTo[AdminController].atribuiPontuacaoDeTenistasSemPontos}" ><small>Atribuir pontuação de tenistas sem pontos</small></a><br/>
				<a href="${linkTo[AdminController].atribuiPontuacaoDeDuplasDosTenistas}" ><small>Atribuir pontuação de duplas dos tenistas</small></a><br/>
				<a href="${linkTo[AdminController].visualizaTenistasDesativados}" ><small>Tenistas desativados</small></a><br/>
				<a href="${linkTo[AdminController].visualizaTenistasDesconfirmados}" ><small>Tenistas não confirmados manualmente</small></a><br/>
				<a href="${linkTo[AdminController].visualizaTenistasSuspensos}" ><small>Tenistas Suspensos</small></a><br/>
            </c:if>
          </div>
        </div>
        <div class="single_sidebar">
          <h2 class="title"></h2>
          <div class="single_sidebar"> <img src="imagens/empresasParceiras/logo_ctp.jpg" alt="" /> </div>
          <div class="single_sidebar"> <img src="imagens/espaco_publicitario.jpg" alt="" /> </div>
          </div>
      </div>
    </div>
    
   <c:import url="../menu/rodape.jsp"/>
    
    
 
  </div>
</div>

   <c:import url="../comum/scripts.jsp"/>


</body>
</html>