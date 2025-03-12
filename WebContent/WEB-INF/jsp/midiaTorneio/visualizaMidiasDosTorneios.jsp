<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${empresa} | Imagens do Torneio</title>
<c:import url="../comum/links.jsp"/>
</head>
<body>

<div class="body_wrapper">
  <div class="center">
	<c:import url="../menu/menu.jsp"></c:import>	

	<div class="container-fluid">


    <div class="content_area">


      
        <div class="single_left_coloum_wrapper">
            <h2 class="title">Imagens dos Torneios</h2>
        </div>
    
       	<div class="container">
       	<div class="row">
				<br>
				<div class="container">
			<!--  carrosel de imagens de torneios -->
				<div id="imagens-torneio" class="carousel slide" data-ride="carousel" data-interval="3000">
				
				  <!-- Indicators -->
				   
				  <ol class="carousel-indicators">
				  <c:forEach var="imagem" items="${imagens}" varStatus="contador">
				  <c:if test="${contador.count == 1}">
				    <li data-target="#imagens-torneio" data-slide-to="0" class="active"></li>
				   </c:if>
				  <c:if test="${contador.count > 1}">
				    <li data-target="#imagens-torneio" data-slide-to="${contador.count}"></li>
				   </c:if>
   				  </c:forEach>
				  </ol>
				  <!-- Wrapper for slides -->
				  
				  <div class="carousel-inner" role="listbox">

				  <c:forEach var="imagem" items="${imagens}" varStatus="contador">

				  <c:if test="${contador.count == 1}">
				    <div class="item active">
				      <div class="carousel-caption"><h3>${imagem.titulo}</h3></div>
				           <img src="${imagem.url}" class="img-responsive" alt="${imagem.titulo}">
				     
				    </div>
				   </c:if>

				  <c:if test="${contador.count > 1}">
				    <div class="item">
				         <div class="carousel-caption"><h3>${imagem.titulo}</h3></div>
				           <img src="${imagem.url}" class="img-responsive" alt="${imagem.titulo}">
				      
				    </div>
				   </c:if>
				   
				   
				    </c:forEach>
				</div>

				  <!-- Controls -->
				  <a class="left carousel-control" href="#imagens-torneio" role="button" data-slide="prev">
				    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
				    <span class="sr-only">Próxima</span>
				  </a>
				  <a class="right carousel-control" href="#imagens-torneio" role="button" data-slide="next">
				    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
				    <span class="sr-only">Anterior</span>
				  </a>
				</div>
				</div>
			
		</div>	
		<div class="row">
		<c:forEach var="video" items="${videos}">
			<div class="embed-container">
				<iframe src="${video.url}" frameborder="0"></iframe>
			</div>
		</c:forEach>
		
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