<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <title>${empresa} | Altera senha</title>
    <c:import url="../comum/links.jsp"/>
</head>

<body> 
<div class="body_wrapper">
  <div class="center">
	<c:import url="../menu/menu.jsp"></c:import>	

	<div class="container-fluid">


    <div class="content_area">


      
        <div class="single_left_coloum_wrapper">
            <h2 class="title">Altera Senha</h2>
        </div>
	     
			<c:import url="../comum/errors.jsp"/>
			<form method="POST" action="${linkTo[TenistaController].alteraSenha}">
					<div class="row">
						<div class="form-group col-sm-3">
							<label for="senhaAtual">Senha atual</label>
							<input class="form-control" type="password" name="senhaAtual" id="senhaAtual" value="${senhaAtual}" required="required" />
							<span class="help-block">Mínimo de 7 caracteres</span>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-sm-3">
							<label for="novaSenha">Nova senha</label>
							<input class="form-control" type="password" name="novaSenha" id="novaSenha" value="${senha}" required="required" />
							<span class="help-block">Mínimo de 7 caracteres</span>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-sm-3">
							<label for="confirmacaoNovaSenha">Confirme a nova senha</label>
							<input class="form-control" type="password" name="confirmacaoNovaSenha" id="confirmacaoNovaSenha" value="${confirmacaoSenha}" required="required"/>
							<span class="help-block">Mínimo de 7 caracteres</span>
						</div>
					</div>
					<div class="btn-toolbar" align="center">
						<button type="submit" class="form-group col-sm-12 btn btn-success pull-center">Alterar senha</button>
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