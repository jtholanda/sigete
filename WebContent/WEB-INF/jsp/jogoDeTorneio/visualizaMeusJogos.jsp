<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${empresa} | Meus jogos</title>
   <c:import url="../comum/links.jsp"/>

</head>
<body>
<div class="body_wrapper">
  <div class="center">
	<c:import url="../menu/menu.jsp"></c:import>	

	<div class="container-fluid">


    <div class="content_area">


      
        <div class="single_left_coloum_wrapper">
            <h2 class="title">Meus Jogos</h2>
        </div>
        
	<div class="row">
	
	<!--  botões de paginação -->
	<div class="row" align="center">
		<div class="col-lg-6" align="right">	
			<c:if test="${anterior > 0}">
			    	<a href="${linkTo[JogoDeTorneioController].visualizaMeusJogos(anterior)}" class="btn btn-primary">&laquo; Página Anterior</a>
			</c:if>
		</div>
		<div class="col-lg-6" align="left">	
			<c:if test="${numeroDeJogos >= numeroElementosMaximo}">
			    	<a href="${linkTo[JogoDeTorneioController].visualizaMeusJogos(proximo)}" class="btn btn-primary">Próxima Página &raquo;</a>
	  	    </c:if>
	    </div>
		<div class="col-lg-12">
	    <br/>
			<label>${numeroDeJogos} Jogo(s) na página - Máximo por página: ${numeroElementosMaximo}</label>
		</div>	
	</div>
	<br/>
	
	<div class="form-group col-sm-12" align="center">
			
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
	
		  		        <c:import url="../comum/botoesPaginaInicial.jsp"/>
	
	
	</div>	

	<c:import url="../menu/rodape.jsp"/>
	
	</div>
	</div>
	</div>
	
	
	
</body>
	<c:import url="../comum/scripts.jsp"/>	

</html>