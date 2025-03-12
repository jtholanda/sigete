<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
  <script src="//cdn.tinymce.com/4/tinymce.min.js"></script>
  <script>tinymce.init({ selector:'textarea' });</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${empresa} | E-mails para os ${esportista}s</title>
   <c:import url="../comum/links.jsp"/>



</head>
<body>
<div class="body_wrapper">
  <div class="center">
	<c:import url="../menu/menu.jsp"></c:import>	

	<div class="container-fluid">


    <div class="content_area">


      
        <div class="single_left_coloum_wrapper">
            <h2 class="title">Envio de E-mail em Massa</h2>
        </div>
					
			<c:import url="../comum/errors.jsp"/>		
		<form action="${linkTo[AdminController].enviaEmails}" method="POST">
		<div class = "row">
			
			<div class="form-group col-sm-4">
					<label for="nivel">Escolha um nível técnico</label>
					<select name="idNivel" id="nivel" class="form-control">
					<option value="0">Todos</option>
					  <c:forEach items="${niveisTecnicos}" var="nivelTecnico">								
						  <option value="${nivelTecnico.id}">${nivelTecnico.descricao}</option>
						</c:forEach>
					</select>
			</div>
			
			<div class="form-group col-sm-4">
					<label for="torneio">Escolha um torneio</label>
					<select name="idTorneio" id="torneio" class="form-control">
					<option value="0">Todos</option>
					  <c:forEach items="${torneios}" var="torneio">								
						  <option value="${torneio.id}">${torneio.nome}</option>
						</c:forEach>
					</select>
			</div>
				<div class="form-group col-sm-4">
						<label for="primeiro">Do</label>
						<input type="text" id="primeiro" name="primeiro" class="form-control" value="${primeiro}" required="required"/>
						<label for="segundo">Ao</label>
						<input type="text" id="segundo" name="segundo" class="form-control" value="${segundo}" required="required"/>
				</div>
			
		</div>
		<div class="row">
			<div class="form-group col-sm-5">
			<label>Assunto</label>
			<input name="assuntoDoEmail" type="text" class="form-control"/>
			</div>
		</div>
		<div class="row">
			<div class="form-group col-sm-12">
				<textarea name="mensagemDoEmail" rows="20" cols="100" class="form-control">
			</textarea>	
			</div>
		</div>
				
				<button class="btn btn-primary ">Enviar</button>
		
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