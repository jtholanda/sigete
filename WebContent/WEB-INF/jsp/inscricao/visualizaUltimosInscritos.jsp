<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${empresa} | Últimos inscritos</title>
  <c:import url="../comum/links.jsp"/>
</head>
<body>
<div class="body_wrapper">
  <div class="center">
	<c:import url="../menu/menu.jsp"></c:import>	

	<div class="container-fluid">


    <div class="content_area">


      
        <div class="single_left_coloum_wrapper">
            <h2 class="title">Inscritos</h2>
        </div>

	<h4>Inscrições recentes</h4><br/>
	<c:if test="${numeroDeInscritos > 0}">
	
	<table class="table table-hover">
		<tr>
			<th>Nome do ${esportista}</th>
			<th>Nível técnico</th>
			<th>Inscrição no nível</th>
			<th>Data da inscrição</th>
			<th>Torneio</th>
			<th>Inscrição confirmada</th>
			<th>Foi removida?</th>
			
			
			 
		</tr>
		
		<c:forEach var="inscricao" items="${inscricoes}">
		 
			<tr>
				<td><a href="${linkTo[TenistaController].detalhaTenista(inscricao.tenista.id,1)}">${inscricao.tenista.nome}</a>
				<c:if test="${inscricao.torneio.duplas }">
					<br><a href="${linkTo[TenistaController].detalhaTenista(inscricao.tenistaParceiro.id,1)}"> ${inscricao.tenistaParceiro.nomeCompleto}</a>
				</c:if>
				</td>
				<td>${inscricao.tenista.nivelTecnicoPrincipal.descricao}</td>
				<td>${inscricao.nivel.descricao}</td>
				<td><fmt:formatDate pattern="dd/MM/yyyy" value="${inscricao.data.time}"/></td>
				<td>${inscricao.torneio.nome}</td>
				<c:if test="${inscricao.confirmada}">
					<td>Sim</td>
				</c:if>
				<c:if test="${!inscricao.confirmada}">
					<td>Não</td>
				</c:if>
				
				<c:if test="${inscricao.removida}">
					<td>Sim</td>
				</c:if>
				<c:if test="${!inscricao.removida}">
					<td>Não</td>
				</c:if>
			</tr>
		
		</c:forEach>
		
	</table>
	</c:if>
	<c:if test="${numeroDeInscritos == 0 }">
	<div align="center">
		<small class="text-info">Ainda não temos inscrições em nenhum torneio no sistema.</small>
	</div>
	</c:if>
	</div>

		<c:import url="../menu/rodape.jsp"/>
	
	
</div>
</div>
</div>

		<c:import url="../comum/scripts.jsp"/>
	
</body>
</html>