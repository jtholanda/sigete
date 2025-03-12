<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="form-group col-sm-12">
	<table class="table table-striped">
	<tr align="center">
		<th>Data</th>
		<th>Hora</th>
		<th>Fase</th>
		<th>Nível</th>
		<th colspan="2"></th>
		<th colspan="1">Jogo</th>
		<th colspan="2"></th>
		<th>Resultado</th>
		<c:if test="${adminOrganizadorTorneio}">
		<th>Ação</th>
		</c:if>
	</tr>

	<c:forEach var="jogo" items="${jogos}">
	
	<tr>
		<td><fmt:formatDate pattern="dd/MM/yyyy"
				value="${jogo.data.time}" /></td>
		<td>${jogo.hora}</td>
		<td>${jogo.fase.descricao}</td>
		<td>${jogo.nivel.descricao}</td>
		<c:if test="${!(jogo.nivel.quantidadeDeTenistas == 2)}">
			<td colspan="2" align="center">${jogo.tenistaUm.nomeRanking} ${jogo.obsTenistaUm}</td>
			<td colspan="1">X</td>
			<td colspan="2" align="center">${jogo.tenistaDois.nomeRanking} ${jogo.obsTenistaDois}</td>
		</c:if>
		<c:if test="${(jogo.nivel.quantidadeDeTenistas == 2)}">
			<td colspan="2" align="center">${jogo.primeiraDupla} ${jogo.obsTenistaUm}</td>
			<td colspan="1">X</td>
			<td colspan="2" align="center">${jogo.segundaDupla} ${jogo.obsTenistaDois}</td>
		</c:if>
	
		<td>${jogo.resultado}</td>
		<c:if test="${adminOrganizadorTorneio}">
		
			<c:if test="${jogo.ocorreu}">
				<td><a class="btn btn-primary" href="${linkTo[JogoDeTorneioController].formInformaResultadoDoJogo(jogo.id)}">Editar Resultado</a></td>
			</c:if>
			<c:if test="${!jogo.ocorreu}">
			<td><a class="btn btn-success" href="${linkTo[JogoDeTorneioController].formInformaResultadoDoJogo(jogo.id)}">Informar Resultado</a></td>
			</c:if>

		</c:if>
	</tr>
	</c:forEach>
	
	</table>
	</div>