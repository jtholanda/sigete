<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>${empresa} | Mensagem</title>
  <c:import url="../comum/links.jsp"/>
</head>
<body>
<div class="body_wrapper">
  <div class="center">
	<c:import url="../menu/menu.jsp"></c:import>	

	<div class="container-fluid">


    <div class="content_area">


      
        <div class="single_left_coloum_wrapper">
            <h2 class="title">Sucesso</h2>
        </div>

    <h2 class="success">${string }</h2>
    <hr>
		
			<div class="btn-toolbar" align="center">
					<a class="btn btn-primary"  href="${linkTo[IndexController].paginaInicial}">Menu principal</a>
				</div>
				
				
		</div>

		<c:import url="../menu/rodape.jsp"/>
	
</div>
</div>
</div>

		<c:import url="../comum/scripts.jsp"/>
</body>
</html>