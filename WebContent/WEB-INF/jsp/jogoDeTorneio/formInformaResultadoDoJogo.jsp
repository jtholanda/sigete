<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${empresa} | Resultado do Jogo</title>
<c:import url="../comum/links.jsp"/>

</head>
<body>
<div class="body_wrapper">
  <div class="center">
	<c:import url="../menu/menu.jsp"></c:import>	

	<div class="container-fluid">


    <div class="content_area">


      
        <div class="single_left_coloum_wrapper">
            <h2 class="title">Informa Resultado do Jogo</h2>
        </div>
   
	<form action="${linkTo[JogoDeTorneioController].informaResultadoDoJogo}" method="POST">
	
	<input type="hidden" value="${jogoDeTorneio.id }" name= "jogoDeTorneio.id" />
	<input type="hidden" value="${torneio.id }" name= "jogoDeTorneio.torneio.id" />

	
	<h4>Insira o resultado do jogo de ${jogoDeTorneio.nivel.descricao} no ${torneio.nome}</h4><br/>
	
	<label>Código do jogo: ${jogoDeTorneio.id}</label> <br><br>

	
	<div class="row">
	<c:if test="${!(jogoDeTorneio.nivel.quantidadeDeTenistas == 2)}">
		<div class="form-group col-sm-8">
			<label for="tenista">Primeiro tenista</label>
			<label  id="tenistaUm" class="form-control">
			${jogoDeTorneio.tenistaUm.nomeCompleto}
			</label>			
		</div>
	</c:if>
	<c:if test="${jogoDeTorneio.nivel.quantidadeDeTenistas == 2}">
		<div class="form-group col-sm-8">
			<label for="tenista">Primeira dupla</label>
			<label  id="tenistaUm" class="form-control">
			${jogoDeTorneio.primeiraDupla}
			</label>			
		</div>
	</c:if>
	 
		<!--  <div class="form-group col-sm-1">
			<label>Set</label>
			<input type="text" name="jogoDeTorneio.setTenistaUm" class="form-control" value="${jogoDeTorneio.setTenistaUm}"/>
		</div>
		-->
		<div class="form-group col-sm-1">
			<label>1ºset</label>
			<input type="text" name="jogoDeTorneio.gamesSet1T1" class="form-control" value="${jogoDeTorneio.gamesSet1T1}"/>
		</div>
		<div class="form-group col-sm-1">
			<label>2ºset</label>
			<input type="text" name="jogoDeTorneio.gamesSet2T1" class="form-control" value="${jogoDeTorneio.gamesSet2T1}" />
		</div>
		<div class="form-group col-sm-1">
			<label>3ºset</label>
			<input type="text" name="jogoDeTorneio.gamesSet3T1" class="form-control" value="${jogoDeTorneio.gamesSet3T1}" />
		</div>
		<div class="form-group col-sm-1">
			<label>SuperTB</label>
			<input type="text" name="jogoDeTorneio.superTieBreakT1" class="form-control" value="${jogoDeTorneio.superTieBreakT1}" />
		</div>
	
		
	</div>

	<div class="row">

	<c:if test="${!(jogoDeTorneio.nivel.quantidadeDeTenistas == 2)}">
		<div class="form-group col-sm-8">
			<label for="tenista">Segundo Tenista</label>
			<label id="tenistaDois" class="form-control">
			${jogoDeTorneio.tenistaDois.nomeCompleto}
			</label>			
		</div>
	</c:if>
	<c:if test="${jogoDeTorneio.nivel.quantidadeDeTenistas == 2}">
		<div class="form-group col-sm-8">
			<label for="tenista">Segunda Dupla</label>
			<label id="tenistaDois" class="form-control">
			${jogoDeTorneio.segundaDupla}
			</label>			
		</div>
	</c:if>

		<!--  <div class="form-group col-sm-1">
			<label>Set</label>
			<input type="text" name="jogoDeTorneio.setTenistaDois" class="form-control" value="${jogoDeTorneio.setTenistaDois}"/>
		</div>
		-->
		<div class="form-group col-sm-1">
			<label>1ºset</label>
			<input type="text" name="jogoDeTorneio.gamesSet1T2" class="form-control" value="${jogoDeTorneio.gamesSet1T2}"/>
		</div>
		<div class="form-group col-sm-1">
			<label>2ºset</label>
			<input type="text" name="jogoDeTorneio.gamesSet2T2" class="form-control" value="${jogoDeTorneio.gamesSet2T2}"/>
		</div>
		<div class="form-group col-sm-1">
			<label>3ºset</label>
			<input type="text" name="jogoDeTorneio.gamesSet3T2" class="form-control" value="${jogoDeTorneio.gamesSet3T2}"/>
		</div>
		<div class="form-group col-sm-1">
			<label>SuperTB</label>
			<input type="text" name="jogoDeTorneio.superTieBreakT2" class="form-control" value="${jogoDeTorneio.superTieBreakT2}"/>
		</div>
	
	</div>
	
	<c:if test="${jogoDeTorneio.wo}">
		<label class="checkbox col-sm-12">
			<input type="checkbox" id="wo" name="jogoDeTorneio.wo" checked="checked"/>Marque se a partida foi WO</label>
	</c:if>

	<c:if test="${!jogoDeTorneio.wo}">
		<label class="checkbox col-sm-12">
			<input type="checkbox" id="wo" name="jogoDeTorneio.wo"/>Marque se a partida foi WO</label>
	</c:if>
		
		<label class="checkbox col-sm-12">
		<input type="checkbox" id="enviaEmail" name="enviaEmail"/>Enviar notificação de e-mail para os tenistas</label>
	
			<button class="btn btn-success">Salvar resultado</button>
						<input class="btn btn-primary " type="button"
							onClick="javascript:history.back(1)" value="Voltar" />	
			
		<br>

	
	
	
	</form>
</div>

	<c:import url="../menu/rodape.jsp"/>

</div>
</div>
</div>

	

</body>
	<c:import url="../comum/scripts.jsp"/>
	
</html>