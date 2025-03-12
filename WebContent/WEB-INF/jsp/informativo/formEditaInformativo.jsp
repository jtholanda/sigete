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
<title>${empresa} | Edição de informativos</title>
   <c:import url="../comum/links.jsp"/>
</head>
<body>
<div class="body_wrapper">
  <div class="center">
	<c:import url="../menu/menu.jsp"></c:import>	

	<div class="container-fluid">


    <div class="content_area">


      
        <div class="single_left_coloum_wrapper">
            <h2 class="title">Edita Informativos</h2>
        </div>
					
			<c:import url="../comum/errors.jsp"/>
				
			<form action="${linkTo[InformativoController].confirmaEdicaoInformativo}" method="POST"> 
			<input type="hidden" name="informativo.id" value="${informativo.id}">
			
			<div class="row">
				<div class="form-group col-sm-9">
						<label for="titulo">Título</label>
						<input type="text" id="titulo" name="informativo.titulo" class="form-control" value="${informativo.titulo}" maxlength="128"/>
				</div>				
				<div class="form-group col-sm-3">
						<label for="tipoInformativo">Tipos de Informativo</label>
						<select id="tipoInformativo" name="informativo.tipoInformativo" class="form-control">
							
							<c:forEach items="${tiposDeInformativos}" var="tipoDeInformativo">
								<c:choose>		
									<c:when test="${informativo.tipoInformativo.id == tipoDeInformativo.id}">
										<option value="${tipoDeInformativo}" selected="selected">${tipoDeInformativo.nome}</option>
									</c:when>
									
									<c:otherwise>
										<option value="${tipoDeInformativo}">${tipoDeInformativo.nome}</option>
									</c:otherwise>
								</c:choose>
															
							</c:forEach>
						</select> 
					</div>
			</div>

			<div class="row">
				<div class="form-group col-sm-12">
						<label for="resumo">Resumo</label>
						<input type="text" id="resumo" name="informativo.resumo" class="form-control" maxlength="255" value="${informativo.resumo}">
				</div>
			</div>

			<div class="row">
				<div class="form-group col-sm-12">
						<label for="conteudo1">Conteúdo</label>
						<textarea id="conteudo1" name="informativo.conteudo" class="form-control" rows="20" maxlength="10000">
							${informativo.conteudo}
						</textarea>
				</div>
			</div>			
			<br/>	
			
			
			<div class="btn-toolbar" align="center">
				<button class="form-group col-sm-12 btn btn-success pull-center">Salvar Informativo</button>
			</div>
		</form>
			
		</div>
		

		<c:import url="../menu/rodape.jsp"/>
		
</div>
</div>
</div>

		<c:import url="../comum/scripts.jsp"/>



</body>
</html>