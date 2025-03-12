<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${empresa}| Inscrição em torneio</title>
   <c:import url="../comum/links.jsp"/>
</head>
<body>
<div class="body_wrapper">
  <div class="center">
	<c:import url="../menu/menu.jsp"></c:import>	

	<div class="container-fluid">


    <div class="content_area">


      
        <div class="single_left_coloum_wrapper">
            <h2 class="title">Confirme os dados</h2>
        </div>
        
			<c:import url="../comum/errors.jsp"/>
			
			<form action="${linkTo[InscricaoController].solicitaInscricaoTorneioSimplesDuplas}" method="POST" > 
					
						<fieldset>
					<h4>Os dados referentes a inscrição estão corretos?</h4>


							<label for="nome" >Torneio:</label>
							<label id="nome" class="text-primary">${inscricaoSessao.torneio.nome}</label><br><br>
							<label for="nome" >Nível técnico da inscrição:</label>
							<label id="nome" class="text-primary">${inscricaoSessao.nivel.descricao}</label><br><br>
							<label for="nome" >Indicação de inscrição:</label>
							<label id="nome" class="text-primary">${inscricaoSessao.indicacaoInscricao.descricao}</label><br><br>
							<label for="nome" >Como ficou sabendo:</label>
							<label id="nome" class="text-primary">${inscricaoSessao.conhecimentoTorneio.nome}</label><br><br>
							<div class="row">
							<div class="form-group col-sm-12">
							<h4>Parceiro(a) na dupla:</h4><br/>
										
										<select name="tenistaParceiro.id" id="tenistasParceiros" class="form-control">
										  <option value="0">Escolha um(a) ${esportista} para ser parceiro na dupla</option>
											  <c:forEach items="${tenistasParceiros}" var="tenistaParceiro">								
												  <option value="${tenistaParceiro.id}">${tenistaParceiro.exibicaoBasica}</option>
												</c:forEach>
										</select>
											
							</div>
							</div>
				

						
						<button class="btn btn-primary" data-loading-text="Solicitando...">Confirmar!</button> 
					<input type='button' value='Voltar' class="btn btn-default" onclick="javascript: history.go(-1)" /> <br> 

							
							
				</fieldset>
		
			</form>
			
		</div>

		<c:import url="../menu/rodape.jsp"/>
	 
</div>
</div>
</div>

		<c:import url="../comum/scripts.jsp"/>

</body>
</html>