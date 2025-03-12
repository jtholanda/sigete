<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
   <div class="col-lg-12">
   
 <h5>Jogos</h5><br>
 
	<div class="row">
	
	<!-- 
	<c:forEach var="jogo" items="${jogos}">
	
	<div class="form-group col-sm-4">
<table class="table">
	<tr align="center">
		<th colspan="2">${jogo.torneio.nome }</th>
		<th colspan="1">${jogo.nivel.descricao }</th>
		
	</tr>
	<tr>
		<td><fmt:formatDate pattern="dd/MM/yyyy"
				value="${jogo.data.time}" /></td>
		<td>${jogo.hora}</td>
		<td>${jogo.fase.descricao}</td>

	</tr>
	<c:if test="${!jogo.torneio.duplas }">
		<tr class="info">
			<td colspan="2">${jogo.tenistaUm.nomeCompleto} ${jogo.obsTenistaUm}</td>
			<td colspan="1">${jogo.tenistaUm.nivelTecnicoPrincipal.descricao}</td>
		</tr>
		<tr class="info">
			<td colspan="2">${jogo.tenistaDois.nomeCompleto} ${jogo.obsTenistaDois}</td>
			<td colspan="1">${jogo.tenistaDois.nivelTecnicoPrincipal.descricao}</td>
		</tr>
	</c:if>
	<c:if test="${jogo.torneio.duplas }">
		<tr class="info">
			<td colspan="2">${jogo.primeiraDupla} ${jogo.obsTenistaUm}</td>
			<td colspan="1">${jogo.tenistaUm.nivelTecnicoPrincipal.descricao}</td>
		</tr>
		<tr class="info">
			<td colspan="2">${jogo.segundaDupla} ${jogo.obsTenistaDois}</td>
			<td colspan="1">${jogo.tenistaDois.nivelTecnicoPrincipal.descricao}</td>
		</tr>
	</c:if>
	<tr class="info">
		<td colspan="3">${jogo.resultado}</td>
	</tr>
</table>
 -->

<!--  <table class="table table-hover">
	<tr align="center">
		<th colspan="3">Jogo</th>
	</tr>
	<tr class="warning">
		<td><fmt:formatDate pattern="dd/MM/yyyy"
				value="${jogo.data.time}" /></td>
		<td>${jogo.hora}</td>
		<td>${jogo.fase.descricao}</td>

	</tr>
	<tr class="success">
		<td colspan="2">${jogo.tenistaUm.nomeCompleto}</td>
		<td>${jogo.tenistaUm.nivelTecnicoPrincipal.descricao}</td>
	</tr>
	<tr class = "success">
		<td colspan="2">${jogo.tenistaDois.nomeCompleto}</td>
		<td colspan="1">${jogo.tenistaDois.nivelTecnicoPrincipal.descricao}</td>
	</tr>
</table>
-->
<!--  
</div>
</c:forEach>
-->

<!-- tabela pode ser usado em comum -->	
	<c:forEach var="jogo" items="${jogos}">
	<table class="table table-responsive">
	<c:if test="${!jogo.torneio.duplas }">
		<tr class="primary">
			<td>${jogo.torneio.nome } - ${jogo.nivel.descricao } -
			<fmt:formatDate pattern="dd/MM/yyyy"
				value="${jogo.data.time}" /> - ${jogo.hora} - ${jogo.fase.descricao}</td>
			<td colspan="1">${jogo.tenistaUm.nomeRanking} ${jogo.obsTenistaUm}</td>
			<td colspan="1">${jogo.tenistaDois.nomeRanking} ${jogo.obsTenistaDois}</td>
			<td colspan="1">${jogo.resultado}</td>
		</tr>
	</c:if>
	<c:if test="${jogo.torneio.duplas }">
		<tr class="primary">
			<td>${jogo.torneio.nome } - ${jogo.nivel.descricao } -
			<fmt:formatDate pattern="dd/MM/yyyy"
				value="${jogo.data.time}" /> - ${jogo.hora} - ${jogo.fase.descricao}</td>
			<td colspan="1">${jogo.primeiraDupla} ${jogo.obsTenistaUm}</td>
			<td colspan="1">${jogo.segundaDupla} ${jogo.obsTenistaDois}</td>
			<td colspan="1">${jogo.resultado}</td>
		</tr>
	</c:if>
	
	</table>
	</c:forEach>

</div>
</div>

<c:if test="${jogosComBotoes}">
<table>
	<tr>
		<td><a href="#" class="btn btn-primary">Editar</a> <c:if
		test="${jogo.ocorreu}">
		<a href="#" class="btn btn-primary">Editar Resultado</a>
	</c:if> <c:if test="${!jogo.ocorreu}">
		<a
			href="${linkTo[JogoDeTorneioController].formInformaResultadoDoJogo(jogo.id)}"
			class="btn btn-success">Informar Resultado</a>
	</c:if> <a href="#" class="btn btn-danger">Remover</a></td>
	</tr>
</table>
</c:if>