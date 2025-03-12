<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${empresa} | Inscrição de ${esportista}s em torneio de simples e duplas</title>
   <c:import url="../comum/links.jsp"/>
</head>
<body>
<div class="body_wrapper">
  <div class="center">
	<c:import url="../menu/menu.jsp"></c:import>	

	<div class="container-fluid">


    <div class="content_area">


      
        <div class="single_left_coloum_wrapper">
            <h2 class="title">Inscrever ${esportista}(s)</h2>
        </div>

    <h2>Inscrição - ${torneio.nome }</h2>
    <hr>
					
			<c:import url="../comum/errors.jsp"/>
			
			<form action="${linkTo[InscricaoController].confirmaInscricaoDoTenistaNoTorneioDeSimplesEDuplas}" method="POST" > 

			<input type="hidden" name="inscricaoDeDupla.torneio.id" value="${torneio.id}">
			<div class="row">
			<div class="form-group col-sm-12">
			<h4>Primeiro ${esportista}</h4><br/>
						
						<select name="inscricaoDeDupla.tenista.id" id="tenistas" class="form-control">
						  <option value="0">Escolha um(a) ${esportista}</option>
							  <c:forEach items="${tenistas}" var="tenista">								
								  <option value="${tenista.id}">${tenista.exibicaoBasica}</option>
								</c:forEach>
						</select>
							
			</div>
			</div>

			<div class="row">
			<div class="form-group col-sm-12">
			<h4>Segundo ${esportista}</h4><br/>
						
						<select name="inscricaoDeDupla.tenistaParceiro.id" id="tenistasParceiros" class="form-control">
						  <option value="0">Escolha um(a) ${esportista} para parceiro na dupla</option>
							  <c:forEach items="${tenistasParceiros}" var="tenistaParceiro">								
								  <option value="${tenistaParceiro.id}">${tenistaParceiro.exibicaoBasica}</option>
								</c:forEach>
						</select>
							
			</div>
			</div>
			<small>Para inscrições de simples escolha apenas o primeiro ${esportista}.</small>
			<div class="row">
			<div class="form-group col-sm-12">
				<h4>Escolha o nível técnico que o(a) ${esportista} deseja se inscrever</h4><br/>
							
							<select name="inscricaoDeDupla.nivel.id" id="niveis" class="form-control">
								  <c:forEach items="${niveis}" var="nivel">								
									  <option value="${nivel.id}">${nivel.descricao}</option>
									</c:forEach>
							</select>
							
				</div>

			</div>
			<div class="row">
			<div class="form-group col-sm-12">
				<h4>Indique a inscrição</h4><br/>
							
							<select name="inscricaoDeDupla.indicacaoInscricao.id" id="indicacaoInscricao" class="form-control">
							<option value="0">Sem indicações</option>
								  <c:forEach items="${indicacoesInscricoes}" var="indicacaoInscricao">								
									  <option value="${indicacaoInscricao.id}">${indicacaoInscricao.descricao}</option>
									</c:forEach>
							</select>
							
				</div>

			</div>
			
			<div class="row">
			<div class="form-group col-sm-12">
			<h4>Horário especial de jogos (evite usar!)</h4><br/>
			<textarea class="form-control" name="inscricaoDeDupla.horarioEspecial"></textarea>
			</div>
			</div>

		<div class="row" align="center">
		<label class="checkbox col-sm-12">
		<input type="checkbox" id="inscricaoDeDupla.paga" name="inscricaoDeDupla.paga" />Marque se o pagamento foi efetuado.</label>
		</div>	
				
				<div class="btn-toolbar ">
				
					<button class="btn btn-success">Inscrever ${esportista}(s)</button>
					<a href="${linkTo[TorneioController].detalhaTorneio(torneio.id)}"	class="btn btn-primary">Detalhes do torneio</a>
					

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