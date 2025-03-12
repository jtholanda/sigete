<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${empresa} | Edição de inscrição</title>
   <c:import url="../comum/links.jsp"/>

</head>
<body>
<div class="body_wrapper">
  <div class="center">
	<c:import url="../menu/menu.jsp"></c:import>	

	<div class="container-fluid">


    <div class="content_area">


      
        <div class="single_left_coloum_wrapper">
            <h2 class="title">Editar</h2>
        </div>
            
			<c:import url="../comum/errors.jsp"/>
			
			<form action="${linkTo[InscricaoController].confirmaEdicaoDeInscricao}" method="POST" > 

			<input type="hidden" name="inscricao.torneio.id" value="${inscricao.torneio.id}">
			<input type="hidden" name="inscricao.id" value="${inscricao.id}">
			<input type="hidden" name="inscricao.tenista.id" value="${inscricao.tenista.id}">
		
			
			
				<h4>Tenista: ${tenista.nomeCompleto}</h4><br/>

				<div class="row">
					<div class="form-group col-sm-6">
					<label>Escolha o nível técnico ou grupo do ${esportista}</label>	
					
						<c:if test="${inscricao.torneio.semPontuacao}">
							<select name="inscricao.nivel.id" id="nivelTecnico" class="form-control">
								  <c:forEach items="${niveisTecnicosDoTorneio}" var="nivelTecnico">								
									  <c:choose>		
											<c:when test="${inscricao.nivel.id == nivelTecnico.id}">
									 			 <option value="${nivelTecnico.id}" selected="selected">${nivelTecnico.descricao}</option>
											</c:when>
											
											<c:otherwise>
									 			 <option value="${nivelTecnico.id}">${nivelTecnico.descricao}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
							</select>
						</c:if>
						<c:if test="${!inscricao.torneio.semPontuacao }">
							<select name="inscricao.nivel.id" id="nivelTecnico" class="form-control" disabled="disabled">
								  <c:forEach items="${niveisTecnicosDoTorneio}" var="nivelTecnico">			
								   <c:choose>		
											<c:when test="${inscricao.nivel.id == nivelTecnico.id}">
									 			 <option value="${nivelTecnico.id}" selected="selected" disabled="disabled">${nivelTecnico.descricao}</option>
											</c:when>
											
											<c:otherwise>
									 			 <option value="${nivelTecnico.id}">${nivelTecnico.descricao}</option>
											</c:otherwise>
									</c:choose>					
									</c:forEach>
							</select>
						</c:if>
					</div>
				</div>				
				<div class="row">
				<div class="form-group col-sm-6">
					<label>Horário especial de jogos (evite usar!)</label>	
					<textarea class="form-control" name="inscricao.horarioEspecial">${inscricao.horarioEspecial}</textarea>
				</div>
				</div>
				
				<div class="btn-toolbar ">
				
					<button class="btn btn-success">Salvar</button>
					<input class="btn btn-primary " type="button"
							onClick="javascript:history.back(1)" value="Voltar" />	
					

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