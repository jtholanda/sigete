<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
<meta charset="iso-8859-1">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>${empresa} | Meus torneios</title>
<c:import url="../comum/links.jsp"/>
</head>
<body>
<div class="body_wrapper">
  <div class="center">
	<c:import url="../menu/menu.jsp"></c:import>	

	<div class="container-fluid">


    <div class="content_area">


      
        <div class="single_left_coloum_wrapper">
            <h2 class="title">Meus Torneios</h2>
        </div>
			<c:import url="../comum/torneiosTenista.jsp"/>

		<div class="btn-toolbar " align="center">
			<c:import url="../comum/botoesPaginaInicial.jsp"/>
		</div>		
	</div>
		
	
		<c:import url="../menu/rodape.jsp"/>
	
	</div>
	</div>
	</div>

		<c:import url="../comum/scripts.jsp"/>
</body>
</html>