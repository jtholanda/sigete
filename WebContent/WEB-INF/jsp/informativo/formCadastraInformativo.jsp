<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<script src="https://cdn.tiny.cloud/1/no-api-key/tinymce/5/tinymce.min.js" referrerpolicy="origin"></script>  <script>tinymce.init({ selector:'textarea' });</script>
  <script>tinymce.init({ selector:'textarea' });</script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${empresa} | Cadastro de informativos</title>
   <c:import url="../comum/links.jsp"/>
</head>
<body>
<div class="body_wrapper">
  <div class="center">
	<c:import url="../menu/menu.jsp"></c:import>	

	<div class="container-fluid">


    <div class="content_area">


      
        <div class="single_left_coloum_wrapper">
            <h2 class="title">Cadastro de Informativos</h2>
        </div>

					
			<c:import url="../comum/errors.jsp"/>
				
			<form action="${linkTo[InformativoController].confirmaCadastroInformativo}" method="POST" > 
			
			<div class="row">
				<div class="form-group col-sm-9">
						<label for="titulo">Título</label>
						<input type="text" id="titulo" name="informativo.titulo" class="form-control" value="${informativo.titulo}" maxlength="128"/>
						<small>O título deve ter entre 20 e 128 caracteres</small>
				</div>
				<div class="form-group col-sm-3">
					<label for="tipoInformativo">Tipo de Informativo</label>
					<select name="informativo.tipoInformativo" id="tipoInformativo" class="form-control">
					<c:forEach items="${tiposDeInformativos}" var="tipoDeInformativo">
						<option value="${tipoDeInformativo}">${tipoDeInformativo.nome}</option>
					</c:forEach>
					</select>
				</div>
			</div>

			<div class="row">
				<div class="form-group col-sm-12">
						<label for="resumo">Resumo</label>
						<input type="text" id="resumo" name="informativo.resumo" class="form-control" maxlength="255" value="${informativo.resumo}" >							
						<small>O resumo deve ter entre 128 e 255 caracteres</small>
						
				</div>
			</div>

			<div class="row">
				<div class="form-group col-sm-12">
						<label for="conteudo1">Conteúdo</label>
						<textarea id="conteudo1" name="informativo.conteudo" class="form-control" rows="20" maxlength="10000">
							${informativo.conteudo}
						</textarea>
						<small>O conteúdo deve ter no máximo 10 000 caracteres</small>
						
				</div>
			</div>			
			<br/>	
			
			
			<div class="btn-toolbar" align="center">
				<button class="form-group col-sm-12 btn btn-success pull-center">Cadastrar Informativo</button>
			</div>
		</form>
			
		</div>
		

		<c:import url="../menu/rodape.jsp"/>
		
</div>
</div>
</div>

		<c:import url="../comum/scripts.jsp"/>

<script src="https://cdn.tiny.cloud/1/no-api-key/tinymce/5/tinymce.min.js" referrerpolicy="origin"></script>  <script>tinymce.init({ selector:'textarea' });</script>


</body>
</html>