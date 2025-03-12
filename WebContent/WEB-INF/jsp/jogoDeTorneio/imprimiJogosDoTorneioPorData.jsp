<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${empresa} | Jogos do torneio</title>
   <c:import url="../comum/links.jsp"/>

</head>
<body>
<div class="body_wrapper">
  <div class="center">

	<div class="container-fluid">


    <div class="content_area">


      
        <div class="single_left_coloum_wrapper">
            <h2 class="title">Jogos</h2>
        </div>
	
	<h4>Jogos do ${torneio.nome} - <fmt:formatDate pattern="dd/MM/yyyy" value="${dataDosJogos.time}"/></h4>
	
	<div class="row">
	
	<c:import url="../comum/jogosEmLista.jsp">
	
	</c:import>
	
	</div>
	
	
	<div align="center">
	<a href="${linkTo[JogoDeTorneioController].visualizaJogosDoTorneio(torneio.id,false)}" class="btn btn-primary">Nova Pesquisa</a>
	</div>
	

</div>
</div>

</div>
</div>	
	
</body>
</html>