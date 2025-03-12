<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${empresa} | Liberar chaves finais</title>
<c:import url="../comum/links.jsp"/>
</head>
<body>
<div class="body_wrapper">
  <div class="center">
	<c:import url="../menu/menu.jsp"></c:import>	

	<div class="container-fluid">


    <div class="content_area">


      
        <div class="single_left_coloum_wrapper">
            <h2 class="title">Liberar chaveamento final</h2>
        </div>

  	<h4>${torneio.nome }</h4>
  	<hr>
	    		<c:import url="../comum/errors.jsp"/>
		
		<form action="${linkTo[TorneioController].confirmaChavesFinais}" method="POST">
			
				
			<input type="hidden" value="${torneio.id}" name="idTorneio">
		
			
				<h2>${mensagemDeSucesso}</h2>
			
			<div class="row">
				<div class="form-group col-sm-6">
			
					<a href="${linkTo[TorneioController].detalhaTorneio(torneio.id)}"	class="btn btn-primary col-sm-6">Detalhes do torneio</a>
			
				 

				</div>
			</div>

			<div class="row">
				<div class="form-group col-sm-6">
			
			
					<input type="submit" class="btn btn-success col-sm-6"
				value="Confirmar" /><br>

				</div>
			</div>
			
			<div class="row">
				<div class="form-group col-sm-12">
					<h3 class="text-danger" >ATENÇÃO: Ao liberar todas as chaves finais os atletas terão acesso ao sorteio realizado.</h3><br>
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