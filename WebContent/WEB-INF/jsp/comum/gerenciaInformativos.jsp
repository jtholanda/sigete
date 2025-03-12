<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table class="table table-hover">

		<tr>
			<th>Data</th>
			<th>Título</th>
			<th>Adicionar Imagem</th>
			<th>Editar</th>
			<th>Remover</th>
			
			 
		</tr>
		<c:forEach var="informativo" items="${informativos}">
			<tr>
				<td><fmt:formatDate value="${informativo.data.time}" type="both" pattern="dd/MM/yyyy" /></td>
				<td>${informativo.titulo}</td>
				<td><a class="btn btn-success" href="${linkTo[InformativoController].adicionaMidia(informativo.id)}">Adicionar</a></td>
				<td><a class="btn btn-info" href="${linkTo[InformativoController].formEditaInformativo(informativo.id)}">Editar</a></td>
				<td><a class="btn btn-danger" href="${linkTo[InformativoController].removeInformativo(informativo.id)}">Remover</a></td>				
			</tr>
		</c:forEach>
	</table>