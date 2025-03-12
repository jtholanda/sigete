<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>${empresa} | Login</title>
    <c:import url="../comum/links.jsp"/>
</head>
<body>
<div class="body_wrapper">
  <div class="center">
	<c:import url="../menu/menu.jsp"></c:import>	

	<div class="container-fluid">


    <div class="content_area">


      
        <div class="single_left_coloum_wrapper">
            <h2 class="title">Acessar Sistema</h2>
        </div>
    <c:import url="../comum/errors.jsp"/>
      
	<form method="post" action="${linkTo[TenistaController].logaTenista}">
	<input type="hidden" value="${urlRequisitada}" name="urlRequisitada">
		
		<div class="row">
			<div class="form-group col-sm-3">
				<label for="email">Email: </label>
				<input class="form-control" type="text" id="email" name="tenista.email" value="${tenista.email}" required="required"/>
			</div>
		</div>
		
		<div class="row">
			<div class="form-group col-sm-3">
				<label for="senha">Senha: </label>
				<input class="form-control" type="password" id="senha" name="tenista.senha" required="required" />
			</div>
		</div>
		<div class= "btn btn-toolbar">
			<button class="btn btn-success">Entrar</button>
			<a class="btn btn-danger" href="${linkTo[TenistaController].formRecuperaSenha}">Esqueci Minha Senha</a>
			<a class="btn btn-primary" href="${linkTo[TenistaController].formCadastro}">Fazer Cadastro</a>
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