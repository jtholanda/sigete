<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="pt_br">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Tudo do tênis paraibano em um só lugar. Competições, tenistas, torneios, inscrições, ranking. Tênis da Paraíba.  ">
    <meta name="author" content="José Thiago Holanda">
  

    <title>${empresa} | Contato</title>

   <c:import url="../comum/links.jsp"/>
    

</head>

<body>

<div class="body_wrapper">
  <div class="center">
	<c:import url="../menu/menu.jsp"></c:import>	

	<div class="container-fluid">


    <div class="content_area">


      
        <div class="single_left_coloum_wrapper">
            <h2 class="title">Contato</h2>
        </div>
        
        <div class="col-lg-12 text-center">
 
 		<div class="row">
	 		<div class="col-sm-5">
	 		<p>Sugestões, críticas ou dúvidas entrem em contato conosco.</p>
 				<img src="../imagens/logomarca.png" class="img-responsive" alt="Portal do Tênis Paraibano" class="img-responsive">
			</div>
 			
 			<div class="col-sm-7">
 						<form action="${linkTo[MaisController].enviarContato}" method="POST" name = "enviaContato">
 			
						<div class="form-group col-sm-12" align="left">
						<label for="nome">Nome *</label>
						<c:if test="${!solicitacaoMudancaClasse}">
							<input type="text" id="nome" name="contato.nome" class="form-control" value="${contato.nome}" required="required"/>
						</c:if>
						<c:if test="${solicitacaoMudancaClasse}">
							<input type="text" id="nome" name="contato.nome" class="form-control" value="${tenista.nomeCompleto}" required="required"/>
						</c:if>						
						</div>

						<div class="form-group col-sm-12" align="left">
						<label for="email">E-mail *</label>
						<c:if test="${!solicitacaoMudancaClasse}">
							<input type="email" id="email" name="contato.email" class="form-control" value="${contato.email}" required="required"/>
						</c:if>
						<c:if test="${solicitacaoMudancaClasse}">
							<input type="email" id="email" name="contato.email" class="form-control" value="${tenista.email}" required="required"/>
						</c:if>
						</div>
						<div class="form-group col-sm-12" align="left">
							<label for="assunto">Assunto</label>
							<select name="contato.assunto" id="assunto" class="form-control" >
							  <option value="Anuncios">Anúncios</option>
							  <option value="Susgestao">Sugestão</option>
							  <option value="Critica">Crítica</option>  
							  <option value="Duvida">Dúvida</option>
							  <c:if test="${solicitacaoMudancaClasse}">
								  <option value="MudancaDeClasse" selected="selected">Mudança de Classe</option>
							  </c:if>
							</select>
						</div>
						<div class="form-group col-sm-12" align="left">
						<label for="mensagem">Mensagem *</label> 
						<textarea id="mensagem" name="contato.mensagem" 
							class="form-control" rows="3" maxlength="255">${contato.mensagem}</textarea>
						</div>
							<!--  <button type="submit" class="form-group col-sm-12 btn btn-success pull-center">Enviar</button>-->
						</form>			 				
 			</div>
 			
        </div>
        
        
		</div>

		
		
</div>

    <c:import url="../menu/rodape.jsp"></c:import>

	</div>
	</div>
	</div>
   
    

      <c:import url="../comum/scripts.jsp"/>
   

    
  
</body>

</html>
