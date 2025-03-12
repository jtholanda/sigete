<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${empresa} | Enviar arquivo de imagem</title>
<c:import url="../comum/links.jsp"/>
</head>
<body>
<div class="body_wrapper">
  <div class="center">
	<c:import url="../menu/menu.jsp"></c:import>	

	<div class="container-fluid">


    <div class="content_area">


      
        <div class="single_left_coloum_wrapper">
            <h2 class="title">Nova Imagem para o Informativo</h2>
        </div>
        
	<c:import url="../comum/errors.jsp"/>
	
			<form action="${linkTo[InformativoController].enviaMidiaInformativo}" enctype="multipart/form-data" method="POST" >
			<input type="hidden" name="informativo.id" value="${informativo.id}"/>
			
			
			
			<div class="row">
			<div class="form-group col-sm-7">
			  <label for="arquivo">Escolha o arquivo:</label> 
			  <input type="file" name="arquivo" class="form-control" id ="arquivo"/> 
			</div>
			
			<div class="form-group col-sm-5">
			  <label for="video">Se estiver enviando um vídeo informe a url do vídeo na web:</label> 
			  <input type="text" name="midiaInformativo.url" class="form-control" id ="video"/> 
			</div>
			</div>

			<div class="row">
			<div class="form-group col-sm-7">
					<label for="legenda">Legenda</label>
					<input type="text" id="legenda" name="midiaInformativo.legenda" class="form-control" value="${midiaInformativo.legenda}"/>
			</div>
			</div>
			
			 <div class="btn btn-tollbar" align="center" >
			 	<button class="form-group btn btn-success col-sm-12 pull-center">Enviar</button>
			 </div>		
			  <a href="${linkTo[IndexController].index}" class="btn btn-danger">Cancelar</a>
			 	<br><small>${mensagem}</small>
			</form>
			
		</div>

			<c:import url="../menu/rodape.jsp"/>
		
</div>
</div>
</div>
			<c:import url="../comum/scripts.jsp"/>
			
</body>
</html>