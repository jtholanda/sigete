<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title> ${empresa} | Gerenciamento de jogos de duplas</title>
   <c:import url="../comum/links.jsp"/>

</head>
<body>
<div class="body_wrapper">
  <div class="center">
	<c:import url="../menu/menu.jsp"></c:import>	

	<div class="container-fluid">


    <div class="content_area">


      
        <div class="single_left_coloum_wrapper">
            <h2 class="title">Gerenciamento de Jogos</h2>
        </div>
   
<div class="bs-example bs-example-tabs" role="tabpanel" data-example-id="togglable-tabs">
   
   <ul id="myTab" class="nav nav-tabs" role="tablist">
     <li role="presentation" class="active"><a href="#novoJogo" id="novoJogo-tab" role="tab" data-toggle="tab" aria-controls="novoJogo" aria-expanded="true">Novo jogo</a></li>
     <li role="presentation"><a href="#jogosMarcados" role="tab" id="jogosMarcados-tab" data-toggle="tab" aria-controls="jogosMarcados">Jogos Marcados</a></li>
   </ul>
   
   <div id="myTabContent" class="tab-content">
     
     <!--  NOVO JOGO -->
    <div role="tabpanel" class="tab-pane fade in active" id="novoJogo" aria-labelledBy="novoJogo-tab">
	<c:import url="../comum/errors.jsp"/>
	<br>
	<h4>Escolha uma opção</h4><br/>
	<div class="row">
		<div class="form-group col-sm-12">
		
		<c:forEach items="${niveisDoTorneio}" var="nivelDoTorneio">								
			<a href="${linkTo[JogoDeTorneioController].preparaFormularioInserirJogo(torneio.id, nivelDoTorneio.id)}" class="btn btn-default col-sm-4">${nivelDoTorneio.descricao}</a>
		</c:forEach>
			<a href="${linkTo[TorneioController].detalhaTorneio(torneio.id)}" class="btn btn-default">Voltar</a>
		</div>
	</div>

	<c:if test="${inscricoes!=null}">
	
<form action="${linkTo[JogoDeTorneioController].insereJogoDeTorneioDeDuplas}" method="POST">
	
	<input type="hidden" value="${torneio.id}" name="jogoDeTorneio.torneio.id"/>
	<input type="hidden" value="${nivel.id}" name="jogoDeTorneio.nivel.id"/>
	
	
	<h4>Insira um novo jogo de ${nivel.descricao} no(a) ${torneio.nome}</h4><br/>
	
	<div class="row">
	  
	<div class="form-group col-sm-3">
			<label>Data do Jogo</label>
			<input type="text" name="jogoDeTorneio.data" class="form-control" id="dataDoJogo" required="required"
			value="<fmt:formatDate pattern="dd/MM/yyyy" value="${jogoDeTorneio.data.time}"/>" />
	</div>

	<div class="form-group col-sm-3">
			<label>Hora do Jogo</label>
			<input type="text" name="jogoDeTorneio.hora" class="form-control" id="horaDoJogo" required="required"
			value="<fmt:formatDate pattern="HH:mm:ss" value="${jogoDeTorneio.hora.time}"/>" />
	</div>

	
	<div class="form-group col-sm-3">
			<label for="fase">Fase do jogo</label>
			<select name="jogoDeTorneio.fase.id" id="fase" class="form-control">
			<option value="0">Escolha uma fase</option>
			  <c:forEach items="${fases}" var="fase">								
				  <option value="${fase.id}">${fase.descricao}</option>
				</c:forEach>
			</select>		
	</div>
	<!-- Apenas para demonstração -->
	<div class="form-group col-sm-3">
			<label for="local">Local do jogo</label>
			<select name="jogoDeTorneio.local.id" id="local" class="form-control">
			    <option value="${torneio.local.id}">${torneio.local.nome}</option>
			</select>		
	</div>
	</div>
	
	<div class="row">
		<div class="form-group col-sm-12">
			<label for="duplaUm">Primeira dupla</label>
			<select name=duplaUm id="duplaUm" class="form-control">
			<option value="0">Escolha um ${esportista} ou informe jogo dependente</option>
			  <c:forEach items="${inscricoes}" var="inscricao">		
			    <option value="${inscricao.id}">${inscricao.tenista.nomeCompleto} / ${inscricao.tenistaParceiro.nomeCompleto}</option>
				</c:forEach>
			</select>
			<input type="text" name="jogoDeTorneio.obsTenistaUm" id="obsTenistaUm" class="form-control">			
		</div>
	 	
	 	<!--  
	 	<div class="form-group col-sm-3">
			<label>Informe o resultado, se o jogo já estiver ocorrido.</label>
		</div>
		<div class="form-group col-sm-1">
			<label>Set</label>
			<input type="text" name="jogoDeTorneio.setTenistaUm" class="form-control"/>
		</div>
		<div class="form-group col-sm-1">
			<label>1º set</label>
			<input type="text" name="jogoDeTorneio.gamesSet1T1" class="form-control"/>
		</div>
		<div class="form-group col-sm-1">
			<label>2º set</label>
			<input type="text" name="jogoDeTorneio.gamesSet2T1" class="form-control"/>
		</div>
		<div class="form-group col-sm-1">
			<label>3º set</label>
			<input type="text" name="jogoDeTorneio.gamesSet3T1" class="form-control"/>
		</div>
		<div class="form-group col-sm-1">
			<label>SuperTieBreak</label>
			<input type="text" name="jogoDeTorneio.superTieBreakT1" class="form-control"/>
		</div>
	
		-->

		<div class="form-group col-sm-12">
			<label for="duplaDois">Segunda Dupla</label>
			<select name="duplaDois" id="duplaDois" class="form-control">
			  <option value="0">Escolha a dupla ou informe jogo dependente</option>
			  <c:forEach items="${inscricoes}" var="inscricao">	
				  <option value="${inscricao.id}">${inscricao.tenista.nomeCompleto} / ${inscricao.tenistaParceiro.nomeCompleto}</option>
				</c:forEach>
			</select>	
			<input type="text" name="jogoDeTorneio.obsTenistaDois" id="obsTenistaDois" class="form-control">			
					
		</div>

<!--  
	 	<div class="form-group col-sm-3">
			<label>Informe o resultado, se o jogo já estiver ocorrido.</label>
		</div>
		<div class="form-group col-sm-1">
			<label>Set</label>
			<input type="text" name="jogoDeTorneio.setTenistaDois" class="form-control"/>
		</div>
		<div class="form-group col-sm-1">
			<label>1º set</label>
			<input type="text" name="jogoDeTorneio.gamesSet1T2" class="form-control"/>
		</div>
		<div class="form-group col-sm-1">
			<label>2º set</label>
			<input type="text" name="jogoDeTorneio.gamesSet2T2" class="form-control"/>
		</div>
		<div class="form-group col-sm-1">
			<label>3º set</label>
			<input type="text" name="jogoDeTorneio.gamesSet3T2" class="form-control"/>
		</div>
		<div class="form-group col-sm-1">
			<label>SuperTieBreak</label>
			<input type="text" name="jogoDeTorneio.superTieBreakT2" class="form-control"/>
		</div>
	-->
	</div>

	<label class="checkbox col-sm-12">
		<input type="checkbox" id="wo" name="jogoDeTorneio.wo"/>Marque se a partida foi WO</label>
	
	<label class="checkbox col-sm-12">
		<input type="checkbox" id="enviaEmail" name="enviaEmail"/>Enviar notificação de e-mail para os ${esportista}s (os que tenham jogo com dependência não recebem)</label>
	
			<button class="btn btn-success col-sm-5">Inserir Jogo</button> <br>
			
	<div class="row">
		<div class="form-group col-sm-12" style="color: blue" align="center">
			<h2>${mensagemDeSucesso}</h2>
		</div>
	</div>

	</form>	
	</c:if>
	</div>
	
    <!--  JOGOS MARCADOS -->
	
	<div role="tabpanel" class="tab-pane fade" id="jogosMarcados" aria-labelledBy="jogosMarcados-tab">
	<br>
	<h3>Jogos registrados de ${nivel.descricao} no(a) ${torneio.nome}</h3><br/>
	
	
	<c:forEach var="jogo" items="${jogos}">
	<div class="row">
	
	<div class="form-group col-sm-12">
	<table class="table table-condesed">
	<tr align="center">
		<th>Cód. ${jogo.id }</th>
		<th>${jogo.torneio.nome }</th>
		<th>${jogo.nivel.descricao }</th>
	</tr>
	<tr>
		
		<td><fmt:formatDate pattern="dd/MM/yyyy" value="${jogo.data.time}"/></td>
		<td>${jogo.hora}</td>
		<td>${jogo.fase.descricao}</td>
		
	</tr>
	<tr>
	<td>${jogo.primeiraDupla} ${jogo.obsTenistaUm}</td>
	<td>${jogo.segundaDupla} ${jogo.obsTenistaDois}</td>
	<td>${jogo.resultado}</td>
	</tr>


	<tr>
	<td colspan="5"><a href="${linkTo[JogoDeTorneioController].formEditaJogoDeTorneioDeDupla(jogo.id)}" class="btn btn-primary">Editar Jogo</a>
	
	<c:if test="${jogo.ocorreu}">
		<a href="${linkTo[JogoDeTorneioController].formInformaResultadoDoJogo(jogo.id)}" class="btn btn-primary">Editar Resultado</a>
	</c:if>
	<c:if test="${!jogo.ocorreu}">
		<a href="${linkTo[JogoDeTorneioController].formInformaResultadoDoJogo(jogo.id)}" class="btn btn-success">Informar Resultado</a>
	</c:if>
			<a href="${linkTo[JogoDeTorneioController].removeJogoDeTorneio(jogo.id)}" class="btn btn-danger">Remover</a>
	</td>
	</tr>
	
	</table>
	</div>
	</div>
	
	</c:forEach>
	
	
	
	
	</div>
</div>
</div>

<br>
			<a href="${linkTo[TorneioController].detalhaTorneio(torneio.id)}"	class="btn btn-primary col-sm-5">Detalhes do torneio</a>


</div>

	<c:import url="../menu/rodape.jsp"/>

</div>
</div>
</div>
	
	<c:import url="../comum/scripts.jsp"/>
	
</body>
</html>