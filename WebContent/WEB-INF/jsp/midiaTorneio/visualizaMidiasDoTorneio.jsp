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

  	<h4>${torneio.nome}</h4>
  	<hr>
  	<div align="center">
  	 <a href="${linkTo[TorneioController].detalhaTorneio(torneio.id)}" class="btn btn-primary">Detalhes do torneio</a>
	</div>  
    
			<c:if test="${numeroDeImagens > 0}">
			
			
				<br>
			
			  	<div class="row">
				  	<c:forEach items="${imagens}" var="imagem" >
				 	 <div class="col-xs-4 col-md-4">
				      <img src="${imagem.url}" alt="${imagem.titulo}" class="img-responsive img-rounded"/>
						  <small>${imagem.titulo}</small>
				  	</div>
					</c:forEach>
				</div>
			</c:if>
			<c:if test="${numeroDeImagens == 0}">
					<div align="center">
					<small class="text-info">Os organizadores do torneio não enviaram as imagens.</small>
					</div>
				</c:if>
				
		<div class="row">
		<c:forEach var="video" items="${videos}">
			<div class="embed-container">
				<iframe src="${video.url}" frameborder="0"></iframe>
			</div>
		</c:forEach>
		
		</div>
		
		
				
	</div>
	      
    
	      
      

				<c:import url="../menu/rodape.jsp"/>

</div>
</div>
</div>
			    <c:import url="../comum/scripts.jsp"/>
	
	
</body>
</html>