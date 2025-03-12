<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${empresa}| Inscrição em torneio</title>
   <c:import url="../comum/links.jsp"/>
</head>
<body>
<div class="body_wrapper">
  <div class="center">
	<c:import url="../menu/menu.jsp"></c:import>	

	<div class="container-fluid">


    <div class="content_area">


      
        <div class="single_left_coloum_wrapper">
            <h2 class="title">Inscreva-se</h2>
        </div>
        
			<c:import url="../comum/errors.jsp"/>
			
			<form action="${linkTo[InscricaoController].preparaInscricaoTorneioSimplesDuplas}" method="POST" > 
					
				<c:import url="../comum/formInscreveseInicio.jsp"></c:import>
				<c:import url="../comum/formInscreveseFim.jsp"></c:import>
				
				<br/>
				<button class="btn btn-primary col-sm-12" data-loading-text="Solicitando...">Avançar</button>

			</form>
			
		</div>

		<c:import url="../menu/rodape.jsp"/>
	 
</div>
</div>
</div>

		<c:import url="../comum/scripts.jsp"/>

</body>
</html>