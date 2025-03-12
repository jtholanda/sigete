<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${empresa} | Inscritos</title>
  <c:import url="../comum/links.jsp"/>
</head>

<body>


<div class="body_wrapper">
  <div class="center">
	<c:import url="../menu/menu.jsp"></c:import>	

	<div class="container-fluid">


	

  	<c:import url="../comum/inscritosNoTorneio.jsp"/>

	<c:import url="../comum/botoesGruposTorneio.jsp"/>
		
	<c:import url="../comum/scripts.jsp"/>

	</div>
	</div>
	</div>
	
</body>
</html>