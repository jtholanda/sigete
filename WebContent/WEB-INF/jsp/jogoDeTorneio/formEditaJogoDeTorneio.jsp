<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${empresa}| Edição do jogo</title>
   <c:import url="../comum/links.jsp"/>
</head>
<body>
<div class="body_wrapper">
  <div class="center">
	<c:import url="../menu/menu.jsp"></c:import>	

	<div class="container-fluid">


    <div class="content_area">


      
        <div class="single_left_coloum_wrapper">
            <h2 class="title">Editar Jogo</h2>
        </div>
	     
	<c:import url="../comum/errors.jsp"/>
	<form action="${linkTo[JogoDeTorneioController].editaJogoDeTorneio}" method="POST">
	
	<input type="hidden" value="${jogoDeTorneio.id }" name= "jogoDeTorneio.id" />


	

	<div class="panel-body">
	

	<label>Código do jogo: ${jogoDeTorneio.id}</label> <br><br>
	
	<div class="row">
		<div class="form-group col-sm-5">
			<label>Torneio</label> 
			<label id="torneio" class="form-control"> 
			${torneio.nome} </label>
		</div>
		<div class="form-group col-sm-5">
			
			<label>Nível</label> 
			<label id="torneio" class="form-control"> 
			${jogoDeTorneio.nivel.descricao}</label>
		</div>
		
	</div>

	<div class="row">
		
		<div class="form-group col-sm-6">
			<label for="tenistaUm">Primeiro ${esportista}</label>
			<select name="jogoDeTorneio.tenistaUm.id" id="tenistaUm" class="form-control">
			<option value="0">Escolha um ${esportista}</option>
			  <c:forEach items="${tenistas}" var="tenista">
				<c:choose>		
					<c:when test="${jogoDeTorneio.tenistaUm.id == tenista.id}">
						<option value="${tenista.id}" selected="selected">${tenista.nomeCompleto}</option>
					</c:when>
					
					<c:otherwise>
						<option value="${tenista.id}">${tenista.nomeCompleto}</option>
					</c:otherwise>
				</c:choose>				  
			</c:forEach>
			</select>		
		</div>
		
		<div class="form-group col-sm-6">
			<label for="tenistaDois">Segundo ${esportista}</label>
			<select name="jogoDeTorneio.tenistaDois.id" id="tenistaDois" class="form-control">
			<option value="0">Escolha um ${esportista}</option>
			  <c:forEach items="${tenistas}" var="tenista">
				<c:choose>		
					<c:when test="${jogoDeTorneio.tenistaDois.id == tenista.id}">
						<option value="${tenista.id}" selected="selected">${tenista.nomeCompleto}</option>
					</c:when>
					
					<c:otherwise>
						<option value="${tenista.id}">${tenista.nomeCompleto}</option>
					</c:otherwise>
				</c:choose>				  
			</c:forEach>
			</select>		
		</div>
	</div>

	<div class="row">
		<div class="form-group col-sm-6">
			<label>Observação do primeiro ${esportista}</label>
			<input type="text" name="jogoDeTorneio.obsTenistaUm" id="obsTenistaUm" class="form-control" placeholder="Informações adicionais, escreva aqui" value="${jogoDeTorneio.obsTenistaUm}">			
		</div>
		
		<div class="form-group col-sm-6">
			<label>Observação do segundo ${esportista}</label>
			<input type="text" name="jogoDeTorneio.obsTenistaDois" id="obsTenistaDois" class="form-control" placeholder="Informações adicionais, escreva aqui" value="${jogoDeTorneio.obsTenistaDois}">			
		</div>
	</div>
	
	<div class="row">
		<div class="form-group col-sm-3">
			<label for="tenista">Local</label>
			<label id="local" class="form-control">
			${jogoDeTorneio.local.nome}
			</label>			
		</div>
		<div class="form-group col-sm-3">
			<label>Data do Jogo</label>
			<input type="text" name="jogoDeTorneio.data" class="form-control" id="dataDoJogo" required="required"
			value="<fmt:formatDate pattern="dd/MM/yyyy" value="${jogoDeTorneio.data.time}"/>" />
		</div>

		<div class="form-group col-sm-3">
			<label>Hora do Jogo</label>
			<input type="text" name="jogoDeTorneio.hora" class="form-control" id="horaDoJogo" required="required"
			value="${jogoDeTorneio.hora}" />
		</div>
		<div class="form-group col-sm-3">
			<label for="fase">Fase do jogo</label>
			<select name="jogoDeTorneio.fase.id" id="fase" class="form-control">
			<option value="0">Escolha uma fase</option>
			  <c:forEach items="${fases}" var="fase">
				<c:choose>		
					<c:when test="${jogoDeTorneio.fase.id == fase.id}">
						<option value="${fase.id}" selected="selected">${fase.descricao}</option>
					</c:when>
					
					<c:otherwise>
						<option value="${fase.id}">${fase.descricao}</option>
					</c:otherwise>
				</c:choose>				  
			</c:forEach>
			</select>		
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
		<input type="checkbox" id="enviaEmail" name="enviaEmail" checked="checked"/>Enviar notificação de e-mail para os ${esportista}s (os que tenham jogo com dependência não recebem)</label>
	
			<button class="btn btn-success">Salvar alterações do jogo</button>
			<input class="btn btn-primary " type="button"
							onClick="javascript:history.back(1)" value="Voltar" />			
			
 <br>
	
	
	</div>
	
	</form>

</div>
	

		<c:import url="../menu/rodape.jsp"/>
		
</div>
</div>
</div>
		<c:import url="../comum/scripts.jsp"/>
		
	
</body>
</html>