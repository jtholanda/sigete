<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${empresa} | Resultado do Torneio</title>
<c:import url="../comum/links.jsp"/>
</head>
<body>
<div class="body_wrapper">
  <div class="center">
	<c:import url="../menu/menu.jsp"></c:import>	

	<div class="container-fluid">


    <div class="content_area">


      
        <div class="single_left_coloum_wrapper">
            <h2 class="title">Resultado do Torneio</h2>
        </div>
    
    
  	<h4>${torneio.nome} - Escolha o nível técnico</h4>
  	<hr>	

	
		<div class = "row">
				<div class="form-group col-sm-12">
		    		<c:forEach items="${torneio.niveis}" var="nivel">								
						<a href="${linkTo[ResultadoController].visualizaResultadoDoTorneio(torneio.id, nivel.id)}" class="btn btn-default col-sm-3">${nivel.descricao}</a>
					</c:forEach>
					<a href="${linkTo[TorneioController].detalhaTorneio(torneio.id)}"	class="btn btn-primary">Voltar</a>
				</div>
		</div>
			
	
	
		<c:if test="${torneio.tipoDeChave.id == 3 || torneio.tipoDeChave.id == 1}">
				<c:import url="visualizaResultadoDoTorneioEmChave.jsp"/>
		</c:if>
		
		<c:if test="${torneio.tipoDeChave.id == 2 }">
				<c:import url="visualizaResultadoDoTorneioEmGrupo.jsp"/>
		</c:if>
	
	</div>
			<c:import url="../menu/rodape.jsp"/>
		
</div>
</div>
</div>
	
</body>
			<c:import url="../comum/scripts.jsp"/>
</html>