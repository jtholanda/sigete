<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html>
<head>
<meta charset="iso-8859-1">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>${empresa} | Informativo</title>
<c:import url="../comum/links.jsp"/>
</head>
<body>
<div class="body_wrapper">
  <div class="center">
	<c:import url="../menu/menu.jsp"></c:import>	

	<div class="container-fluid">


    <div class="content_area">


      
        <div class="single_left_coloum_wrapper">
            <h2 class="title">Detalhe do Informativo</h2>
        </div>

	<h2>${informativo.titulo}</h2>
  	<hr>
  	<c:if test="${informativo.imagemPrincipal==null}">
		      <img class="media-object img-rounded" src="/${contexto}/imagens/logomarca.png" >
	</c:if>
		    
  	<div class="row">
  	<c:forEach items="${midiasInformativo}" var="midiaInformativo" >
 	 <div class="col-xs-4 col-md-4">
      <img src="${midiaInformativo.url}" alt="${midiaInformativo.legenda}" class="img-responsive img-rounded"/>
		  <small>${midiaInformativo.legenda}</small>
  	</div>
	</c:forEach>
	</div>
  	<br/>
		
  	<div class="row">
		 <div class="col-xs-12 col-md-12">
			<small> por ${informativo.tenista.nome} em <fmt:formatDate value="${informativo.data.time}" type="both" pattern="dd/MM/yyyy" /></small>
		</div>
	</div>
		<br/>
  	<div class="row">
		 <div class="col-xs-12 col-md-12"><h4>
			${informativo.resumo}</h4>
		</div>
		
  	</div>
	<div class="row">
		 <div class="col-xs-12 col-md-12">
			<h3>${informativo.conteudo}</h3>
		</div>
	</div>  	
  	
	</div>
		
				<c:import url="../menu/rodape.jsp"/>
		
		</div>
		</div>
		</div>

		<c:import url="../comum/scripts.jsp"/>
</body>
</html>