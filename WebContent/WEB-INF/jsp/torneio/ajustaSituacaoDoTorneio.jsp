<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${empresa} | Ajustes de torneio</title>
<c:import url="../comum/links.jsp"/>
</head>
<body>
<div class="body_wrapper">
  <div class="center">
	<c:import url="../menu/menu.jsp"></c:import>	

	<div class="container-fluid">


    <div class="content_area">


      
        <div class="single_left_coloum_wrapper">
            <h2 class="title">Torneio</h2>
        </div>
			<h3>${nomeTorneio}</h3><br/>
			
		<form action="${linkTo[TorneioController].confirmaAjusteDeSituacao}" method="POST">
				
				<input type="hidden" value="${idTorneio}" name="idTorneio">
				<input type="hidden" value="${acao}" name="acao">

				<a href="${linkTo[TorneioController].detalhaTorneio(idTorneio)}"	class="btn btn-primary">Detalhes do torneio</a>

				<c:if test = "${acao == INICIA_TORNEIO }">
					<input type="submit" class="btn btn-success" value="Confirmar in�cio do torneio"/><br>
					<small>Ap�s iniciar o torneio as inscri��es n�o poder�o ser mais confirmadas.</small>
				</c:if>

				<c:if test = "${acao == CANCELA_TORNEIO }">
					<input type="submit" class="btn btn-success" value="Cancelar torneio"/><br>
					<small>Um torneio cancelado impede novas inscri��es.</small>
				</c:if>
				
				<c:if test = "${acao == REINICIA_TORNEIO }">
					<input type="submit" class="btn btn-success" value="Reiniciar torneio"/><br>
					<small>O rein�cio do torneio n�o reabre as inscri��es, apenas permiti gerenciar as existentes.</small>
				</c:if>
				
				<c:if test = "${acao == CONCLUI_TORNEIO }">
					<input type="submit" class="btn btn-success" value="Concluir torneio"/><br>
					<small>O torneio s� poder� ser conclu�do se todos os jogos e resultados estiverem sido inseridos, e depois s� poder� ser alterado via administra��o.</small>
				</c:if>
				
		</form>
		</div>

				<c:import url="../menu/rodape.jsp"/>
	
</div>
</div>
</div>
			    <c:import url="../comum/scripts.jsp"/>
</body>
</html>