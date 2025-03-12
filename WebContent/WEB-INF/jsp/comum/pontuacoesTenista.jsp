<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h5>Pontuações</h5><br>

	<table class="table table-hover">
		<tr>
			<th>Nível técnico</th>
			<th>Ano</th>
			<th>Pontuação</th>
			<th>Posição</th>
		</tr>
		
		<c:forEach var="pontuacao" items="${pontuacoes}">
		 
			<tr>
				<td>${pontuacao.nivel.descricao}</td>
				<td>${pontuacao.ano}</td>
				<td>${pontuacao.pontos}</td>
				<td>${pontuacao.tenista.getPosicaoEmRankingDoNivel(pontuacao.nivel.id, pontuacao.ano)}º lugar</td>
			</tr>
		
		</c:forEach>
		
	</table>
	