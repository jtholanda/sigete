<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>${empresa} | Mensagem de inscrição</title>
   <c:import url="../comum/links.jsp"/>


</head>
<body>
<div class="body_wrapper">
  <div class="center">
	<c:import url="../menu/menu.jsp"></c:import>	

	<div class="container-fluid">


    <div class="content_area">


      
        <div class="single_left_coloum_wrapper">
            <h2 class="title">Mensagem Importante</h2>
        </div>
			
			<p class="bg-success">${string}</p>
	
			<h4>Inscrição confirmada: </h4> <br>
			Inscrição nº: <b>${inscricao.id}</b> <br>
			Nome do ${esportista}: <b>${inscricao.tenista.nome}</b> <br>
		
			<c:if test="${inscricao.torneio.duplas}"> 
			Nome do ${esportista}: <b>${inscricao.tenistaParceiro.nome}</b> <br>
			</c:if>

			<c:if test="${inscricao.torneio.simplesEDuplas}"> 
				<c:if test="${inscricao.nivel.quantidadeDeTenistas > 1 }">
					Parceiro na dupla: <b>${inscricao.tenistaParceiro.nome}</b> <br>
				</c:if>
			</c:if>
		
			
			Nível:  <b> ${inscricao.nivel.descricao}</b> <br>
			Torneio: <b>${inscricao.torneio.nome}</b> <br>
	
		<c:if test="${inscricao.nivel.codigo < inscricao.tenista.nivelTecnicoPrincipal.codigo}">
			O ${esportista} foi inscrito em uma classe tecnicamente superior a dele.
		</c:if>		
		
		
		<div class="panel-body">
			
		
			<div class="btn-toolbar" align="center">
			<c:if test="${inscricao.torneio.simples}">
					<a class="btn btn-success"  href="${linkTo[InscricaoController].formInscreveTenistaEmTorneio(inscricao.torneio.id)}">Nova inscrição</a>
			</c:if>
			<c:if test="${inscricao.torneio.duplas}">
					<a class="btn btn-success"  href="${linkTo[InscricaoController].formInscreveTenistasEmTorneioDeDuplas(inscricao.torneio.id)}">Nova inscrição</a>
			</c:if>
			<c:if test="${inscricao.torneio.simplesEDuplas}">
					<a class="btn btn-success"  href="${linkTo[InscricaoController].formInscreveTenistasEmTorneioDeSimplesEDuplas(inscricao.torneio.id)}">Nova inscrição</a>				
			</c:if>		
					<a href="${linkTo[TorneioController].detalhaTorneio(inscricao.torneio.id)}" class="btn btn-primary">Detalhes do torneio</a>
				</div>
		</div>

</div>

		<c:import url="../menu/rodape.jsp"/>

    	</div>
    	</div>
    	</div>
    	

		<c:import url="../comum/scripts.jsp"/>

</body>
</html>