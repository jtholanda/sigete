<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${empresa} | Todos os jogos</title>
   <c:import url="../comum/links.jsp"/>

</head>
<body>
<div class="body_wrapper">
  <div class="center">
	<c:import url="../menu/menu.jsp"></c:import>	

	<div class="container-fluid">


    <div class="content_area">


      
        <div class="single_left_coloum_wrapper">
            <h2 class="title">Jogos</h2>
        </div>

   <h4>${torneio.nome}</h4>
   <hr>
	
	<!-- fieldset pode ser usado em comum -->
			
	<div class="row">
		<div class="form-group col-sm-12">
			<h4>Escolha uma opção</h4><br>


					<a href= "${linkTo[JogoDeTorneioController].visualizaJogosDoTorneio(torneio.id,false)}" class="btn btn-primary col-sm-3" >Todos os jogos</a>
						
				  <c:forEach items="${niveisDoTorneio}" var="nivelDoTorneio">
						<a href="${linkTo[JogoDeTorneioController].visualizaJogosDoTorneioPorNivel(torneio.id, nivelDoTorneio.id)}" class="btn btn-default col-sm-3">${nivelDoTorneio.descricao}</a>
				</c:forEach>
						<a href="${linkTo[TorneioController].detalhaTorneio(torneio.id)}" class="btn btn-primary col-sm-3">Voltar</a>
								
		
				
		</div>
	
		<div class="form-group col-sm-3">			
	
			<h4>Informe uma data</h4><br/>
				<form action="${linkTo[JogoDeTorneioController].imprimiJogosDoTorneioPorData}" method="POST">
					<input type="hidden" name="idTorneio" value="${torneio.id}">
					<input class="form-control" type="text" name="dataDosJogos" id="dataDoJogo"><br>
					<button class="btn btn-primary">Imprimir os jogos deste dia</button>
					
				</form>				
		
		</div>
		
	
	</div>
		<!-- fieldset pode ser usado em comum -->
	
		
		
<h4>Todos os Jogos ${nivel.descricao}</h4><br/>
	
	<div class="row">
     <c:import url="../comum/jogosEmLista.jsp">
	
	</c:import>

	</div>
	
		<div align="center">
			<a href="${linkTo[TorneioController].detalhaTorneio(torneio.id)}"	class="btn btn-primary">Detalhes do torneio</a>
		</div>
	
	<c:if test="${gruposDoNivelNoTorneio!= null && gruposDoNivelNoTorneio.size() > 0}">

		<c:import url="../comum/botoesGruposTorneio.jsp"/>
	
	
	</c:if>	
	</div>


	<c:import url="../menu/rodape.jsp"/>
	
</div>
</div>
</div>

	<c:import url="../comum/scripts.jsp"/>	
	
</body>
</html>