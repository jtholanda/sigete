<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${empresa} | Gerar chaves</title>
<c:import url="../comum/links.jsp"/>
</head>
<body>
<div class="body_wrapper">
  <div class="center">
	<c:import url="../menu/menu.jsp"></c:import>	

	<div class="container-fluid">


    <div class="content_area">


      
        <div class="single_left_coloum_wrapper">
            <h2 class="title">Gerar Chaveamento</h2>
        </div>

  	<h4>${torneio.nome }</h4>
  	<hr>
		
		<form action="${linkTo[TorneioController].confirmaGeracaoDeChavesDoTorneio}" method="POST">
			
				
			<input type="hidden" value="${torneio.id}" name="idTorneio">
		
			
			<c:if test="${torneio.chaveGerada}">
			<div class="row">
			<c:if test="${torneio.situacao.id <= 3}">
			<div class="form-group col-sm-7">
				<label for="nivel">Nível Técnico *</label> 
				<select name="idNivel" id="nivel" class="form-control">
					<option value="0">Escolha uma opção</option>
					<c:forEach items="${torneio.niveis}" var="nivel">
						<option value="${nivel.id}">${nivel.descricao}</option>
					</c:forEach>
				</select>
	
			</div>
			</c:if>
			<div class="form-group col-sm-5">
			
			<c:if test="${chaveamentoPDF !=null}">
				<a href="${chaveamentoPDF}"	class="btn btn-primary" target="_blank">Download do chaveamento em PDF</a>
			</c:if>
			</div>			
			
			</div>
			</c:if>
			
			
			<div class="row">
			<div class="form-group col-sm-12">
			
			<a href="${linkTo[TorneioController].detalhaTorneio(torneio.id)}"	class="btn btn-primary">Detalhes do torneio</a>
			
			<input type="submit" class="btn btn-success"
				value="Chaveamento do torneio" /><br>

				 
			<c:if test="${!torneio.chaveGerada}">
				<div class="row">
				<div class="form-group col-sm-12">
					<small class="text-danger" >ATENÇÃO: Certifque que a pontuação dos ${esportista}s está atualizada com o administrador do sistema.</small><br>
					<small>Após gerar a chave do torneio, este não deverá ser reiniciado.</small>
				</div>
				</div>
			</c:if>	 
			</div>
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