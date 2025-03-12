<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${empresa} | Atribuição de pontos do torneio</title>
   <c:import url="../comum/links.jsp"/>
</head>
<body>
<div class="body_wrapper">
  <div class="center">
	<c:import url="../menu/menu.jsp"></c:import>	

	<div class="container-fluid">


    <div class="content_area">


      
        <div class="single_left_coloum_wrapper">
            <h2 class="title">Atribuir Pontos de Duplas</h2>
        </div>
					
			<c:import url="../comum/errors.jsp"/>
	
	
		<form action="${linkTo[AdminController].confirmaAtribuicaoDaPontuacaoDeDuplasDosTenistas}" method="POST">
				<div class="row">
				<div class="form-group col-sm-5">
					<label for="nivel">Escolha um nível técnico</label>
					<select name="idNivel" id="nivel" class="form-control">
					  <c:forEach items="${niveisDuplas}" var="nivelDupla">								
						  <option value="${nivelDupla.id}">${nivelDupla.descricao}</option>
						</c:forEach>
					</select>
				</div>
				</div>
				<a href="${linkTo[IndexController].paginaInicial}"	class="btn btn-primary">Início</a>

				<input type="submit" class="btn btn-success" value="Confirmar atribuição dos pontos dos torneios de duplas"/><br>
				<small>A pontuação de todos os ${esportista}s serão ajustadas como um todo.</small>
		
		</form>
		
	</div>



		<c:import url="../menu/rodape.jsp"/>
 
</div>
</div>
</div>

		<c:import url="../comum/scripts.jsp"/>
	
</body>
</html>