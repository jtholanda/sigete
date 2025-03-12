<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${empresa} | Atribuição de pontos</title>
   <c:import url="../comum/links.jsp"/>



</head>
<body>
<div class="body_wrapper">
  <div class="center">
	<c:import url="../menu/menu.jsp"></c:import>	

	<div class="container-fluid">


    <div class="content_area">


      
        <div class="single_left_coloum_wrapper">
            <h2 class="title">Atribuir Pontos de Tenistas sem Pontos</h2>
        </div>
					
			<c:import url="../comum/errors.jsp"/>	
	
		<form action="${linkTo[AdminController].confirmaPontuacaoDeTenistasSemPontos}" method="POST">
				

				<a href="${linkTo[IndexController].paginaInicial}"	class="btn btn-primary">Início</a>

				<input type="submit" class="btn btn-success" value="Confirmar atribuição dos pontos do torneio"/><br>
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