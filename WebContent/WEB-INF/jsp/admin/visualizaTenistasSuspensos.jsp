<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${empresa} | ${esportista}s suspensos</title>
<c:import url="../comum/links.jsp"/>
</head>
<body>
<div class="body_wrapper">
  <div class="center">
	<c:import url="../menu/menu.jsp"></c:import>	

	<div class="container-fluid">


    <div class="content_area">


      
        <div class="single_left_coloum_wrapper">
            <h2 class="title">${esportista}s Suspensos</h2>
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