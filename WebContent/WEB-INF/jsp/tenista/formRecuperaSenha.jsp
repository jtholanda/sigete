<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>${empresa} | Recuperação de Senha</title>
<c:import url="../comum/links.jsp"/>
</head>

<body>
<div class="body_wrapper">
  <div class="center">
	<c:import url="../menu/menu.jsp"></c:import>	

	<div class="container-fluid">


    <div class="content_area">


      
        <div class="single_left_coloum_wrapper">
            <h2 class="title">Recuperar Senha</h2>
        </div>

		<c:import url="../comum/errors.jsp"/>
	     
	<c:if test="${codigo == null}">
		<form method="post" action="${linkTo[TenistaController].processaRecuperacaoSenha}">
			<div class="row">
			<div class="form-group col-sm-7">
				<label for="email">Informe o seu <em>e-mail</em> cadastrado</label>
				<input class="form-control" type="email" name="email" id="email" value="${email}" placeholder="example@example.com" /><br>
				<button class="btn btn-success">Enviar</button>
			</div>
			</div>
		</form>
		
	</c:if>
	<c:if test="${codigo != null}">
		<form method="POST" action="${linkTo[TenistaController].recuperaSenhaNova(codigo)}">
		<div class="row">
			<div class="form-group col-sm-3">
				<label for="senha">Nova senha</label>
				<input class="form-control" type="password" name="senha" id="senha" value="${senha}" />
				<span class="help-block">Mínimo de 7 caracteres</span>
			</div>
		</div>
		<div class="row">
			<div class="form-group col-sm-3">
				<label for="confirmacaoSenha">Confirme a nova senha</label>
				<input class="form-control" type="password" name="confirmacaoSenha" id="confirmacaoSenha" value="${confirmacaoSenha}"/>
				<span class="help-block">Mínimo de 7 caracteres</span>
				
				<input type="hidden" name="_method" value="PATCH" />
				<button class="btn btn-success">Alterar</button>
			</div>
		</div>
		</form>
	</c:if>	
	</div>
 		
		<c:import url="../menu/rodape.jsp"/>
		
</div>
</div>
</div>

		<c:import url="../comum/scripts.jsp"/>

</body>
</html>