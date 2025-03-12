<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${empresa} | Confirmação do sorteio do chaveamento</title>
<c:import url="../comum/links.jsp"/>
</head>
<body>
<div class="body_wrapper">
  <div class="center">
	<c:import url="../menu/menu.jsp"></c:import>	

	<div class="container-fluid">


    <div class="content_area">


      
        <div class="single_left_coloum_wrapper">
            <h2 class="title">Chaveamento</h2>
        </div>
        <h3>{torneio.nome} - {nivel.descricao}</h3>
	
	<c:if test="${quantidadeGeral == 4}">
	<c:import url="../comum/chaveDeSemi.jsp"></c:import>
	</c:if>
	
	
	<c:if test="${quantidadeGeral == 8}">
	<c:import url="../comum/chaveDeQuartas.jsp"></c:import>
	</c:if>
	
	<c:if test="${quantidadeGeral == 16}">
	<c:import url="../comum/chaveDeOitavas.jsp"></c:import>
	</c:if>
	
	<c:if test="${quantidadeGeral == 32}">
	<c:import url="../comum/chaveDeDecimasSextas.jsp"></c:import>
	</c:if>

	<c:if test="${quantidadeGeral == 64}">
	<c:import url="../comum/chaveDe32.jsp"></c:import>
	</c:if>
	
	<div align="center">
		<a href="${linkTo[TorneioController].detalhaTorneio(torneio.id)}"	class="btn btn-primary">${torneio.nome }</a>
	</div>
	
	<div align="center">
	<b>
		PORTAL DO TÊNIS PARAIBANO <br> www.portaldotenispb.com.br
	</b>
		
	</div>
	</div>

</div>
</div>
</div>
</body>
</html>