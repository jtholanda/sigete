<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


	
	<h4>Resultado ${nivel.descricao}</h4><br/>
	<table class="table table-striped">
		<tr>
			<th>Nome do ${esportista}</th>
			<!--  <th>Pontos</th>-->
			<th>Jogos</th>
			<th>Vitórias Totais</th>
			<th>Derrotas</th>
			<th>Saldo de Sets</th>
			<th>Sets Pro</th>
			<th>Sets Contras</th>
			<th>Saldo de Games</th>
			<th>Games Pro</th>
			<th>Games Contra</th>			
			 
		</tr>
		
		<c:forEach var="classificacaoEmGrupo" items="${classificacoesEmGrupo}">
		 
			<tr>
				<td>${classificacaoEmGrupo.tenista.nomeRanking}</td>
				<!--  <td>${classificacaoEmGrupo.pontos}</td>-->
				<td>${classificacaoEmGrupo.jogos}</td>
				<td>${classificacaoEmGrupo.vitoriasTotais}</td>
				<td>${classificacaoEmGrupo.derrotas}</td>
				<td>${classificacaoEmGrupo.saldoDeSets}</td>
				<td>${classificacaoEmGrupo.setsPro}</td>
				<td>${classificacaoEmGrupo.setsContra}</td>
				<td>${classificacaoEmGrupo.saldoDeGames}</td>
				<td>${classificacaoEmGrupo.gamesPro}</td>
				<td>${classificacaoEmGrupo.gamesContra}</td>
			</tr>
		
		</c:forEach>
		
	</table>

	