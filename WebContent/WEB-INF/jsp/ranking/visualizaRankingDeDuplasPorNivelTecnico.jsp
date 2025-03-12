<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${empresa} | Ranking de duplas</title>
<c:import url="../comum/links.jsp"/>
</head>
<body>
<div class="body_wrapper">
  <div class="center">
	<c:import url="../menu/menu.jsp"></c:import>	

	<div class="container-fluid">


    <div class="content_area">


      
        <div class="single_left_coloum_wrapper">
            <h2 class="title">Ranking de Duplas Por Nível Técnico</h2>
        </div>

	<div class = "row">
		<form action="${linkTo[RankingController].pesquisaRankingPorNivelTecnico}" method="POST">
		<input type = "hidden" name="categoria" value = "2">
			<div class="form-group col-sm-5">
					<label for="nivel">Escolha um nível técnico</label>
					<select name="idNivel" id="nivel" class="form-control">
					  <c:forEach items="${niveisTecnicos}" var="nivelTecnico">								
						  <option value="${nivelTecnico.id}">${nivelTecnico.descricao}</option>
						</c:forEach>
					</select>
			</div>
			<div class="form-group col-sm-2">
					<label for="ano">Escolha o ano</label>
					<select name="ano" id="ano" class="form-control">
						  <option value="2019">2019</option>
						  <option value="2018">2018</option>
						  <option value="2017">2017</option>
						  <option value="2016">2016</option>
						  <option value="2015">2015</option>
					</select>					
			</div>
			<div class="form-group col-sm-2">
			<br/>
			<button class="btn btn-primary ">Pesquisar</button>
			</div>						
		</form>
	</div>	
	
	<c:if test="${pontosDosTenistasRankeados != null}">
	<h3>Tenistas - ${nivel.descricao} ano ${ano}</h3>

	<c:if test="${numeroDeTenistas > 0}">
			<c:import url="../comum/tabelaRankingNivel.jsp"/>
	</c:if>
	<c:if test="${numeroDeTenistas == 0}">
		<div align="center">
			<small class="text-info">Nenhum ${esportista} pontuou nesta categoria de nível técnico.</small>
		</div>
	</c:if>
		
	</c:if>

	</div>

		<c:import url="../menu/rodape.jsp"/>
	
</div>
</div>
</div>

		<c:import url="../comum/scripts.jsp"/>

</body>
</html>