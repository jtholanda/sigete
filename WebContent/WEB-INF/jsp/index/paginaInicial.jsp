<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Bem vindo - ${empresa}</title>
   <c:import url="../comum/links.jsp"/>
</head>
<body>
<div class="body_wrapper">
  <div class="center">
	<c:import url="../menu/menu.jsp"></c:import>	

	<div class="container-fluid">


    <div class="content_area">


      
        <div class="single_left_coloum_wrapper">
            <h2 class="title">Meu Perfil</h2>
        </div>

	<h4>
	
	<c:if test="${trofeus1 > 0 }">
		<c:forEach begin="1" end="${trofeus1}">
			<img src="/${contexto}/imagens/trofeu_ouro.png" alt="Troféu de 1º lugar " width="30" height="30">
		</c:forEach>
	</c:if>
	<c:if test="${trofeus2 > 0 }">
	<c:forEach begin="1" end="${trofeus2}">
		<img src="/${contexto}/imagens/trofeu_prata.png"  alt="Troféu de 2º lugar " width="30" height="30">
	</c:forEach>
	</c:if>	
	
	</h4>
	<hr>
			<div class="row">
			
						<div class="form-group col-sm-6">
							<h4>Olá, ${tenista.nome}</h4>
							
							<label for="codigo" >Código do tenista:</label>
							<span id="codigo" class="text-info">${tenista.id} </span><br>
							
				
							<label for="nomeRanking">Minha pontuação atual:</label>
							<span id="nomeRanking" class="text-info">${tenista.pontosCategoriaPrincipal} ponto(s)</span><br>
							
							<!-- informação sobre a posição do tenista no ranking do nivel dele -->
								
							
						</div>
							
						<div class="form-group col-sm-6">
							<h4>Dados técnicos</h4>
							
							<label for="nome" >Meu nível técnico principal:</label>
							<span id="nome" class="text-info">${tenista.nivelTecnicoPrincipal.descricao} </span>
							
							<br>
							<label for="professor" >Professor:</label>
							<span id="professor" class="text-info">${tenista.professor.nome} </span><br>
							
							<label for="clube" >Clube:</label>
							<span id="clube" class="text-info">${tenista.clube.nome} </span>

								
							
						</div>

						
			</div>
							
			<div class="row">
							<div class="form-group col-sm-6">
								<h4>Dados pessoais</h4>			
								
									<label for="nome">Nome:</label>
									<span id="nome" class="text-info">${tenista.nome}</span>
							
									<br>
									<label for="sobreNome">Sobrenome:</label>
									<span id="sobreNome" class="text-info">${tenista.sobreNome}</span>
							
									<br>
									<label for="cpf">CPF:</label>
									<c:if test="${!tenista.naoPossuiCpf}">
									<span  id="cpf" class="text-info">${tenista.cpf}</span>
									</c:if>
									<c:if test="${tenista.naoPossuiCpf}">
										<span  id="cpf" class="text-info">Ajuste o seu CPF, ele não foi informado:</span>
										<a href="#">Alterar CPF</a>
									</c:if>
							</div>
								
							<div class="form-group col-sm-6">
									<h4>Contatos</h4>			
									
									<label for="email">E-mail:</label>
									<span id="email" class="text-info">${tenista.email}</span>
		
									<br>				
									<label for="telefonePrincipal">Contato telefônico 1:</label>
									<span  id="telefonePrincipal" class="text-info">${tenista.telefonePrincipal.numero}</span>
									<c:if test="${tenista.telefonePrincipal.operadora.nome != null}">
										<span  id="operadoraPrincipal" class="text-info">(${tenista.telefonePrincipal.operadora.nome})</span>
									</c:if>
									<br>				
									<label for="telefoneSecundario">Contato telefônico 2:</label>
									<span  id="telefoneSecundário" class="text-info">${tenista.telefoneSecundario.numero}</span>
									<c:if test="${tenista.telefoneSecundario.operadora.nome != null}">
										<span  id="operadoraSecundaria" class="text-info">(${tenista.telefoneSecundario.operadora.nome})</span>
									</c:if>
							</div>
							
					
			
			</div>
			
  
								
  
  		        <c:import url="../comum/botoesPaginaInicial.jsp"/>
				<div class="row">
				<div class="form-group col-sm-12" align="center">
					<c:if test="${tenistaLogado!=null }">
						<a href="${linkTo[TenistaController].formEditaTenista}" class="btn btn-info "  >Alterar meus dados</a>
						<a href="${linkTo[TenistaController].formAlteraSenha}"  class="btn btn-info " >Alterar senha</a>
						<a href="${linkTo[MaisController].formSolicitaMudancaNivel}"  class="btn btn-info " >Solicitar mudança de nível técnico</a>
			           </c:if>
			       </div>
				</div>
  		        <span style = color:green>${mensagemDeSucesso}</span>
  		        <!--  
  	<c:if test="${administrador}">
			<div class="panel panel-primary">
			<div class="panel-heading">Painel administrativo</div><br>
				<div class="row">
					<div class="form-group col-sm-3" align="center">
						<a class="btn btn-primary" href="${linkTo[AdminController].formCadastraFiliado}" >Cadastrar Filiado</a>
					</div>
					 <div class="form-group col-sm-3" align="center">					 
						<a class="btn btn-primary" href="${linkTo[AdminController].formEnviaEmails}"  >Enviar e-mails</a>
					</div> 
					<div class="form-group col-sm-3" align="center">
						<a class="btn btn-primary" href="${linkTo[AdminController].atribuiPontuacaoDosTenistas}" >Atribuir pontuação dos tenistas</a>
					</div>
					<div class="form-group col-sm-3" align="center">
						<a class="btn btn-primary" href="${linkTo[AdminController].atribuiPontuacaoDeTenistasSemPontos}" >Atribuir pontuação de tenistas sem pontos</a>
					</div>
					<div class="form-group col-sm-3" align="center">
						<a class="btn btn-primary" href="${linkTo[AdminController].atribuiPontuacaoDeDuplasDosTenistas}" >Atribuir pontuação de duplas dos tenistas</a>
					</div>
					<div class="form-group col-sm-3" align="center">
						<a class="btn btn-primary" href="${linkTo[AdminController].visualizaTenistasDesativados}" >Tenistas desativados</a>
					</div>
					<div class="form-group col-sm-3" align="center">
						<a class="btn btn-primary" href="${linkTo[AdminController].visualizaTenistasDesconfirmados}" >Tenistas não confirmados manualmente</a>
					</div>	
					</div>
			</div>
			</c:if>				
-->
		
				
</div>

    <c:import url="../menu/rodape.jsp"></c:import>

	</div>
	</div>
	</div>			
   

      <c:import url="../comum/scripts.jsp"/>
   


</body>
</html>