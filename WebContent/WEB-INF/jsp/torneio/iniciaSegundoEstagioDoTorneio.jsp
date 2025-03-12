<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${empresa} | Iniciar segundo estágio do torneio</title>
<c:import url="../comum/links.jsp"/>
</head>
<body>
<div class="body_wrapper">
  <div class="center">
	<c:import url="../menu/menu.jsp"></c:import>	

	<div class="container-fluid">


    <div class="content_area">


      
        <div class="single_left_coloum_wrapper">
            <h2 class="title">Inicar a segunda fase do torneio</h2>
        </div>

  	<h4>${torneio.nome }</h4>
  	<hr>
	    		<c:import url="../comum/errors.jsp"/>
		
		<form action="${linkTo[TorneioController].confirmaInicioSegundoEstagio}" method="POST">
			
				
			<input type="hidden" value="${torneio.id}" name="idTorneio">
		
			
			<div class="row">
				<div class="form-group col-sm-6">
					<label for="nivel">Nível Técnico *</label> 
					<select name="idNivel" id="nivel" class="form-control">
						<c:forEach items="${niveisDoTorneio}" var="nivel">
							<option value="${nivel.id}">${nivel.descricao}</option>
						</c:forEach>
					</select>
		
				</div>
				<div class="form-group col-sm-6">
				
					<label for="tenistasPorGrupo">Quantidade de classificados no grupo *</label>
					<input type="number" id="quantidadeTenistas" name="quantidadeTenistas" class="form-control" required="required"/>
				
				
				</div>			
			
			</div>
			
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
					<h3 class="text-danger" >ATENÇÃO: Certifque que todos os jogos do nível técnico escolhido estejam com o resultado informado.</h3><br>
					<h5>A chave seguinte só vai ser gerada após ter todos os resultados do nível técnico registrados ou os classificados informados manualmente.</h5>
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