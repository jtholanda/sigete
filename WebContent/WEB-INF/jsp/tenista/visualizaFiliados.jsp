<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${empresa} | Premium</title>
<c:import url="../comum/links.jsp"/>
</head>
<body>
<div class="body_wrapper">
  <div class="center">
	<c:import url="../menu/menu.jsp"></c:import>	

	<div class="container-fluid">


    <div class="content_area">


      
        <div class="single_left_coloum_wrapper">
            <h2 class="title">Lista de ${esportista}s</h2>
        </div>
	
	<div align="right">
	
		<!--  <label>Total Pago: <fmt:formatNumber type="currency" currencySymbol="R$" currencyCode="BRL" value="${somaBruta}"/></label><br>
		<label>Total Líquido: <fmt:formatNumber type="currency" currencySymbol="R$" currencyCode="BRL" value="${somaLiquida}"/></label><br>
		<label>Total de filiados: ${quantidade}</label><br>-->
	</div>
	
	<table class="table table-hover">

		<tr>
			<th>Nome</th>
			<th>Data do Cadastro</th>
			<th>Ano de Referência</th>
			<c:if test="${administrador}">			
				<th>Valor Pago</th>
				<th>Valor Líquido</th>
				<th>Bolsista</th>
				<th>Observação</th>
				<th>Remover</th>
			</c:if>
			
			 
		</tr>
		<c:forEach var="tenistaFiliado" items="${tenistasFiliados}">
			<tr>
				<td><c:if test="${tenistaFiliado.tenista.filiado}"><img alt="Filiado" src="/sigete/imagens/simbolo_filiado.jpg"  width="30" height="30"/> </c:if>		
				<a href="${linkTo[TenistaController].detalhaTenista(tenistaFiliado.tenista.id,1)}">${fn:toUpperCase(tenistaFiliado.tenista.nomeCompleto)}</a></td>
				<td><fmt:formatDate value="${tenistaFiliado.data.time}" type="both" pattern="dd/MM/yyyy" /></td>
				<td>${tenistaFiliado.anoReferencia}</td>
				<c:if test="${administrador}">				
					<td><fmt:formatNumber type="currency" currencySymbol="R$" currencyCode="BRL" value="${tenistaFiliado.valorPago}"/>
					</td>
					<td><fmt:formatNumber type="currency" currencySymbol="R$" currencyCode="BRL" value="${tenistaFiliado.valorLiquido}"/></td>
					<td><c:if test="${tenistaFiliado.bolsista}">Sim</c:if>
					<c:if test="${!tenistaFiliado.bolsista}">Não</c:if></td>
					<td>${tenistaFiliado.observacao}</td>
					<td><a class="btn btn-danger" href="${linkTo[AdminController].removeFiliacao(tenistaFiliado.id)}">Remover</a></td>
				</c:if>				
				
			</tr>
		</c:forEach>
	</table>

	</div>
		<c:import url="../menu/rodape.jsp"/>
</div>
</div>
</div>
		<c:import url="../comum/scripts.jsp"/>

</body>
</html>