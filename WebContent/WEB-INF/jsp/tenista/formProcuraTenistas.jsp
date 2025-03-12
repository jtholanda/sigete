<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${empresa} | Procurar ${esportista}s</title>
<c:import url="../comum/links.jsp"/>
</head>
<body>
<div class="body_wrapper">
  <div class="center">

	<c:import url="../menu/menu.jsp"></c:import>

	<div class="container-fluid">
	
	   <div class="content_area">
	 
    	
       <div class="single_left_coloum_wrapper">
            <h2 class="title">Pesquisa de ${esportista}s</h2>
        </div>

	<div class = "row">
		<form action="${linkTo[TenistaController].buscaTenistas}" method="POST">

			<div class="form-group col-sm-7">
					<label for="nivel">Informe o nome ou sobrenome do tenista</label>
					<input type="text" name="nome" id="nome" class="form-control"/>
			</div>
		
		
			<div class="form-group col-sm-5">
					<label for="nivel">Escolha um nível técnico</label>
					<select name="idNivel" id="nivel" class="form-control">
					<option value="0">Escolha uma nível para pesquisa</option>
					  <c:forEach items="${niveisTecnicos}" var="nivelTecnico">								
						  <option value="${nivelTecnico.id}">${nivelTecnico.descricao}</option>
						</c:forEach>
					</select>
					<br>
			
					
			</div>
			<div class="form-group col-sm-2">
						<button class="btn btn-primary ">Pesquisar</button>
			</div>
			
			
			
		</form>
		
		
	</div>	
	
	


	<c:if test="${numeroDeTenistas > 0}">
			<c:import url="../comum/listaTenistas.jsp"/>
	</c:if>
	<c:if test="${numeroDeTenistas == 0}">
		<div align="center">
			<small class="text-info">Nenhum ${esportista} foi encontrado.</small>
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