<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${empresa} | Inscrição em torneios abertos</title>
  <c:import url="../comum/links.jsp"/>
</head>
<body>
<div class="body_wrapper">
  <div class="center">
	<c:import url="../menu/menu.jsp"></c:import>	

	<div class="container-fluid">


    <div class="content_area">


      
        <div class="single_left_coloum_wrapper">
            <h2 class="title">Torneios Abertos</h2>
        </div>

    <h4>Realizar minha inscrição</h4>
    <hr>
					
			<c:import url="../comum/errors.jsp"/>
			
	<div align="right">
		<label>Total de torneios: ${numeroDeTorneiosEmAberto}</label>
	</div>
	<c:if test="${numeroDeTorneiosEmAberto > 0}">

	<table class="table table-hover">
		<tr>
			<th>Nome</th>
			<th>Local</th>
			<th>Início das inscrições</th>
			<th>Fim das inscrições</th>
			<th>Valor da inscrição</th>
			<th></th>
		</tr>
		
		<c:forEach var="torneio" items="${torneios}">
		 
			<tr>
				<td><a href="${linkTo[TorneioController].detalhaTorneio(torneio.id)}">${torneio.nome}</a></td>
				<td>${torneio.local.nome}</td>
				<td><fmt:formatDate pattern="dd/MM/yyyy" value="${torneio.dataInicioInscricao.time}"/></td>
				<td><fmt:formatDate pattern="dd/MM/yyyy" value="${torneio.dataFimInscricao.time}"/></td>
				<td><fmt:formatNumber type="currency" currencySymbol="R$" currencyCode="BRL" value="${torneio.valorInscricao}"/></td>
				
				<td>
				
					<c:if test="${!torneio.duplas}">
						<a href="${linkTo[InscricaoController].formInscreveSeEmTorneio(torneio.id)}"
						class="btn btn-success right">Inscrever-se</a>
					</c:if>
					<c:if test="${torneio.duplas}">
						<a href="${linkTo[InscricaoController].formInscreveSeEmTorneioDeDuplas(torneio.id)}"
						class="btn btn-success right">Inscrever-se</a>
					</c:if>
			
				</td>
			
			</tr>
		
		</c:forEach>
		
	</table>
	</c:if>
	<c:if test="${numeroDeTorneiosEmAberto == 0 }">
		<div align="center">
		<small class="text-info">No momento não temos torneios com inscrições abertas.</small>
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