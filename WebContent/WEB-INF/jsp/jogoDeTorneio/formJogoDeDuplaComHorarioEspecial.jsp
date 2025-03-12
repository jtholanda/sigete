<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${empresa} | Confirmação de jogo com horário especial</title>
   <c:import url="../comum/links.jsp"/>



</head>
<body>
<div class="body_wrapper">
  <div class="center">
	<c:import url="../menu/menu.jsp"></c:import>	

	<div class="container-fluid">


    <div class="content_area">


      
        <div class="single_left_coloum_wrapper">
            <h2 class="title">Confirma Jogo</h2>
        </div>
	
		<form action="${linkTo[JogoDeTorneioController].confirmaJogoDeTorneioDeDuplasComHorarioEspecial}" method="POST">
				
				<input type="hidden" value="${jogo.fase.id}" name="jogoDeTorneio.fase.id">
				<input type="hidden" value="${jogo.torneio.id}" name="jogoDeTorneio.torneio.id">
				<input type="hidden" value="${jogo.nivel.id}" name="jogoDeTorneio.nivel.id">
				<input type="hidden" value="${jogo.local.id}" name="jogoDeTorneio.local.id">
				<input type="hidden" value="${jogo.tenistaUm.id}" name="jogoDeTorneio.tenistaUm.id">
				<input type="hidden" value="${jogo.tenistaDois.id}" name="jogoDeTorneio.tenistaDois.id">
				<input type="hidden" value="${jogo.tenistaParceiroUm.id}" name="jogoDeTorneio.tenistaParceiroUm.id">
				<input type="hidden" value="${jogo.tenistaParceiroDois.id}" name="jogoDeTorneio.tenistaParceiroDois.id">
				<input type="hidden" name="jogoDeTorneio.data" value="<fmt:formatDate pattern="dd/MM/yyyy" value="${jogo.data.time}" />" />
				<input type="hidden" value="${jogo.hora}" name="jogoDeTorneio.hora">
				<input type="hidden" value="${jogo.obsTenistaUm}" name="jogoDeTorneio.obsTenistaUm">
				<input type="hidden" value="${jogo.obsTenistaDois}" name="jogoDeTorneio.obsTenistaDois">

			<h4>Analise os horários especiais 								
					<small>(Verifique a observação de horário especial dos ${esportista}s.)</small>
				</h4>
				<div class="row">

					<div class="checkbox col-sm-12">
					<label>${jogo.tenistaUm.nome} / ${jogo.tenistaParceiroUm.nome} </label>
					<input type="text" disabled="disabled" value="${inscricaoUm.horarioEspecial}" class="form-control"/>
					</div>
					
				</div>

				<div class="row">
					<div class="checkbox col-sm-12">
					<label>${jogo.tenistaDois.nome} / / ${jogo.tenistaParceiroDois.nome}</label>
					<input type="text" disabled="disabled" value="${inscricaoDois.horarioEspecial}" class="form-control"/>
					</div>
				</div>
								
				
				<c:if test="${enviaEmail}">
					<label class="checkbox col-sm-12">
					<input type="checkbox" id="enviaEmail" name="enviaEmail" checked="checked"/>Enviar notificação de e-mail para os ${esportista}s </label>
				</c:if>
				<c:if test="${!enviaEmail}">
					<label class="checkbox col-sm-12">
					<input type="checkbox" id="enviaEmail" name="enviaEmail" />Enviar notificação de e-mail para os ${esportista}s </label>
				</c:if>
				
				<div class="btn toolbar">
				<input type="submit" class="btn btn-success" value="Confirmar jogo no torneio"/>
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