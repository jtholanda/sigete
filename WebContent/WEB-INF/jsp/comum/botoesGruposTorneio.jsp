<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

		<c:forEach items="${gruposDoNivelNoTorneio}" var="numeroGrupo">								
			<a href="${linkTo[InscricaoController].visualizaInscritosNoGrupoDoNivelDoTorneio(numeroGrupo, nivel.id, torneio.id )}" class="btn btn-primary">Grupo ${numeroGrupo}</a>
		</c:forEach>    	

			