<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f"%>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${empresa} | Inscritos</title>
  <c:import url="../comum/links.jsp"/>
</head>

<body>


<div class="body_wrapper">
  <div class="center">
	<c:import url="../menu/menu.jsp"></c:import>	

	<div class="container-fluid">


	

  	<c:import url="../comum/inscritosNoTorneio.jsp"/>

	<h4>Resultado ${nivel.descricao}</h4><br/>
	<table class="table table-striped">
		<tr>
			<th>Nome</th>
			<!--  <th>Pontos</th>-->
			<th>Jogos</th>
			<!--  <th>Fator de vitórias</th>-->
			<th>Vitórias Totais</th>
			<th>Derrotas</th>
			<!--<th>Fator de Sets</th>-->
			<th>Saldo de Sets</th>
			<th>Sets Pro</th>
			<th>Sets Contras</th>
			<!--  <th>Fator de Games</th>-->
			<th>Saldo de Games</th>
			<th>Games Pro</th>
			<th>Games Contra</th>			
			 
		</tr>

		<c:forEach var="classificacaoEmGrupo" items="${classificacoesEmGrupo}">
		 
			<tr>
				<td>${classificacaoEmGrupo.tenista.nomeRanking}
				<c:if test="${classificacaoEmGrupo.tenistaParceiro!=null}">
					/ ${classificacaoEmGrupo.tenistaParceiro.nomeRanking}
				</c:if>
				</td>
				<!--  <td>${classificacaoEmGrupo.pontos}</td>-->
				<td>${classificacaoEmGrupo.jogos}</td>
				<!--  <td><f:formatNumber type="percent" value="${classificacaoEmGrupo.fatorPontuacao}"/></td>-->
				<td>${classificacaoEmGrupo.vitoriasTotais}</td>
				<td>${classificacaoEmGrupo.derrotas}</td>
				<!--  <td><f:formatNumber type="percent" value="${classificacaoEmGrupo.fatorSet}"/></td>-->
				<td>${classificacaoEmGrupo.saldoDeSets}</td>
				<td>${classificacaoEmGrupo.setsPro}</td>
				<td>${classificacaoEmGrupo.setsContra}</td>
				<!--  <td><f:formatNumber type="percent" value="${classificacaoEmGrupo.fatorGame}"/></td>-->
				<td>${classificacaoEmGrupo.saldoDeGames}</td>
				<td>${classificacaoEmGrupo.gamesPro}</td>
				<td>${classificacaoEmGrupo.gamesContra}</td>
			</tr>
		
		</c:forEach>

	</table>
	
	
	<c:import url="../comum/botoesGruposTorneio.jsp"/>

	<br/>
	<br/>

	<div class="row">

     <c:import url="../comum/jogosEmLista.jsp">
	
	</c:import>

	</div>
		
	<c:import url="../comum/scripts.jsp"/>

	</div>
	</div>
	</div>
	
</body>
</html>