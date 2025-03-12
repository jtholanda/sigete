<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">

<title>${empresa} | Confirmação de cadastro do ${esportista}</title>
<c:import url="../comum/links.jsp"/>

</head>
<body>
<div class="body_wrapper">
  <div class="center">

<c:import url="../menu/menu.jsp"></c:import>	

	<div class="container-fluid">

		
		  
    <div class="content_area">


        <div class="single_left_coloum_wrapper">
            <h2 class="title">Confirme o cadastro</h2>
        </div>
		
		<form action="${linkTo[TenistaController].confirmaCadastroTenista}" method="POST">
				
			<div class="form-group col-sm-6">
				
				<fieldset>
					<h4>Os seus dados estão corretos?</h4>

							<label for="nome" >NOME:</label>
							<label id="nome" class="text-primary">${tenista.nome}</label><br>

							<label for="nome" >SOBRENOME:</label>
							<label id="nome" class="text-primary">${tenista.sobreNome}</label><br>
					
							<label for="nomeRanking">NOME NO RANKING:</label>
							<label id="nomeRanking" class="text-primary">${tenista.nomeRanking}</label><br>
			
							<label for="dataNascimento">DATA DE NASCIMENTO:</label>
							<label id="dataNascimento" class="text-primary">							
								<fmt:formatDate value="${tenista.dataNascimento.time}" type="both" pattern="dd/MM/yyyy" />
							</label><br>

							<label for="sexo">SEXO:</label>
							<label id="sexo" class="text-primary">${tenista.sexo.nome}</label><br>

							<label for="cpf">CPF:</label>
							<label id="cpf" class="text-primary">${tenista.cpf}</label><br>
					
							<label for="email">E-MAIL (será o seu login de acesso):</label>
							<label id="email" class="text-primary">${tenista.email}</label><br>

				
							<label for="telefonePrincipal">TELEFONE:</label>
							<label  id="telefonePrincipal" class="text-primary">${tenista.telefonePrincipal.numero} ${tenista.telefonePrincipal.operadora}</label><br>
						
							<label for="nivelTecnico">NÍVEL TÉCNICO:</label>
							<label id="nivelTecnico" class="text-primary">${tenista.nivelTecnicoPrincipal.descricao}</label><br>
					
					<!--  	
						
					<br>
					<fieldset>
					<h2>Endereço (opcional)</h2>
					<address>
					
							<label>Logradouro:</label>
							<label id = "logradouro" class="text-danger">${tenista.endereco.logradouro}</label><br>
							<label>Número:</label>
							<label id="numero" class="text-danger">${tenista.endereco.numero}</label><br>
							<label>Bairro:</label>
							<label id="bairro" class="text-danger">${tenista.endereco.bairro}</label><br>
							<label>Cidade:</label>
							<label id="cidade" class="text-danger">${tenista.endereco.cidade.nome}</label><br>
							<label>Complemento:</label>
							<label id="complemento" class="text-danger">${tenista.endereco.complemento}</label><br>
							<label>CEP:</label>
							<label id="cep" class="text-danger">${tenista.endereco.cep}</label><br>
					
					</address>
					
					
				
					</fieldset>
					-->
							
							
				</fieldset>
				</div>
				<!--  
				<div class="form-group col-sm-6">
				
				<fieldset>
				<h2>Informações técnicas</h2>
							<label for="clube">Clube:</label>
							<label id="clube" class="text-danger">${tenista.clube.nome}</label><br>

							<label for="professor">Professor:</label>
							<label id="professor" class="text-danger">${tenista.professor.nome}</label><br>

							<label for="raquete">Raquete:</label>
							<label id="raquete" class="text-danger">${tenista.raquete}</label><br>

							<label for="nivelTecnico">Nível Técnico:</label>
							<label id="nivelTecnico" class="text-danger">${tenista.nivelTecnicoPrincipal.descricao}</label><br>

							<label for="inicioNoTenis">Início no tênis:</label>
							<label  id="inicioNoTenis" class="text-danger">							
								<fmt:formatDate value="${tenista.inicioNoTenis.time}" type="both" pattern="dd/MM/yyyy" />
							</label><br>
							
					
							

				</fieldset>
				</div>
				-->
				
				<br>
					
					<input type='button' value='Voltar' class="form-group col-sm-12 btn btn-default pull-center" onclick="javascript: history.go(-1)" /> <br> 
					<button type="submit" class="form-group col-sm-12 btn btn-primary pull-center">Confirmar!</button>
				
			</form>
		
		
	</div>
		<c:import url="../menu/rodape.jsp"/>
</div>
</div>
</div>		
		<c:import url="../comum/scripts.jsp"/>
</body>
</html>