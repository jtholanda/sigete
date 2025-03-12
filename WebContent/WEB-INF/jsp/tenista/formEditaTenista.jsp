	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <title>${empresa} | Edição dos dados do ${esportista}</title>
    <c:import url="../comum/links.jsp"/>
</head>

<body> 
<div class="body_wrapper">
  <div class="center">

	<c:import url="../menu/menu.jsp"></c:import>	

	<div class="container-fluid">
	  <div class="content_area">
        <div class="single_left_coloum_wrapper">
            <h2 class="title">Editar Dados</h2>
        </div>
    
     
			<c:import url="../comum/errors.jsp"/>

			<form action="${linkTo[TenistaController].salvarDadosTenista}" method="POST" name = "editaTenista">
			<input type="hidden" id="id" name="tenista.id" class="form-control" value="${tenista.id}"/>
				
							<hr>
	                		<h4>DADOS PESSOAIS</h4>
	                		<hr>
					<div class="row">
						<div class="form-group col-sm-2">
						<label for="nome">Id</label>
						<input type="text" id="id" name="tenista.id" class="form-control" value="${tenista.id}" disabled="disabled"/>
						</div>

						<div class="form-group col-sm-5">
						<label for="pontos">Pontos na categoria principal (${tenista.nivelTecnicoPrincipal.descricao})</label>
						<input type="text" id="pontos" name="tenista.pontosCategoriaPrincipal" class="form-control" value="${tenista.pontosCategoriaPrincipal}" disabled="disabled"/>
						</div>					
					</div>
					<div class="row">
						
						
						<div class="form-group col-sm-5">
						<label for="nome">Nome principal *</label>
						<c:if test="${administrador}">
							<input type="text" id="nome" name="tenista.nome" class="form-control" value="${tenista.nome}" required="required"/>						
						</c:if>
						<c:if test="${!administrador}">
							<input type="text" id="nome" name="tenista.nome" class="form-control" value="${tenista.nome}" disabled="disabled"/>
						</c:if>
						</div>

						  
						<div class="form-group col-sm-4">
						<label for="sobrenome">Sobrenome(s) preferido(s) *</label>
						<c:if test="${administrador}">
							<input type="text" id="sobrenome" name="tenista.sobreNome" class="form-control" value="${tenista.sobreNome}" required="required"/>
						</c:if>
						<c:if test="${!administrador}">
							<input type="text" id="sobrenome" name="tenista.sobreNome" class="form-control" value="${tenista.sobreNome}" disabled="disabled"/>
						</c:if>
						</div>
						
					
						<div class="form-group col-sm-3">
							<label for="nomeRanking">Nome no Ranking *</label>
							<c:if test="${administrador}">
								<input type="text" id="nomeRanking" name="tenista.nomeRanking" class="form-control" value="${tenista.nomeRanking}" required="required"/>
							</c:if> 
							<c:if test="${!administrador}">
								<input type="text" id="nomeRanking" name="tenista.nomeRanking" class="form-control" value="${tenista.nomeRanking}" disabled="disabled"/>
							</c:if> 
						</div>
						
					</div>
					
					<div class="row">

						<div class="form-group col-sm-4">
							<label for="dataNascimento">Data de Nascimento *</label>
							<input type="text" id="dataNascimento" name="tenista.dataNascimento"
							 class="form-control" value="<fmt:formatDate pattern="dd/MM/yyyy" value="${tenista.dataNascimento.time}"/>" required="required" />
						</div>

						<div class="form-group col-sm-4">
							<label for="sexo">Sexo *</label>
							<select name="tenista.sexo" id="sexo" class="form-control" >
							  <c:if test="${tenista.sexo eq('MASCULINO')}">
								  <option value="MASCULINO" selected="selected">Masculino</option>
							  </c:if>							  
							  <c:if test="${tenista.sexo ne('MASCULINO')}">
								  <option value="MASCULINO">Masculino</option>
							  </c:if>							  
							  <c:if test="${tenista.sexo eq('FEMININO')}">
								  <option value="FEMININO" selected="selected">Feminino</option>
							  </c:if>							  
							  <c:if test="${tenista.sexo ne('FEMININO')}">
								  <option value="FEMININO">Feminino</option>
							  </c:if>							  
							</select>
						</div>
						<c:if test="${!administrador}">
							<c:if test="${tenista.naoPossuiCpf}">
								<div class="form-group col-sm-4">
									<label for="cpf">CPF *</label>
									<input type="text" id="cpf" name="tenista.cpf" class="form-control" value="" required="required"/>
								</div>
							</c:if>
							<c:if test="${!tenista.naoPossuiCpf}">
								<div class="form-group col-sm-4">
									<label for="cpf">CPF *</label>
									<input type="text" id="cpf" name="tenista.cpf" class="form-control" value="${tenista.cpf}" required="required"/>
								</div>
							</c:if>
						</c:if>
						
						<c:if test="${administrador}">
							<div class="form-group col-sm-4">
								<label for="cpf">CPF *</label>
								<input type="text" id="cpf" name="tenista.cpf" class="form-control" value="${tenista.cpf}" required="required"/>
								<label class="checkbox col-sm-12">
								<c:if test="${tenista.naoPossuiCpf}">
								<input type="checkbox" id="temCpf" name="tenista.possuiCpf" checked="checked" />
									A opção deve estar marcada se o CPF informado não for do tenista.
								</c:if>
								<c:if test="${!tenista.naoPossuiCpf}">
								<input type="checkbox" id="temCpf" name="tenista.possuiCpf" />
									A opção deve estar marcada se o CPF informado não for do tenista. 
								</c:if>
								</label>
							</div>
							
						
						</c:if>
						
						</div>
						
						<div class="row">
						<div class="form-group col-sm-6">
							
						<c:if test="${administrador}">
							<label for="mudarNivelBT">Nível Técnico Beach Tennis * <br/><input type="checkbox" id="mudarNivelBT" name="mudarNivelBT" />
								Marque para mudar o nível técnico.</label>
							<select name="tenista.nivelTecnicoBeachTennis.id" id="nivelTecnicoBT" class="form-control">
							<option value="0">Escolha uma opção</option>
							  <c:forEach items="${niveisTecnicosBT}" var="nivelTecnicoBeachTennis">								
								<c:choose>		
									<c:when test="${nivelTecnicoBeachTennis.id == tenista.nivelTecnicoBeachTennis.id}">
										<option value="${nivelTecnicoBeachTennis.id}" selected="selected">${nivelTecnicoBeachTennis.descricao}</option>
									</c:when>
									
									<c:otherwise>
										<option value="${nivelTecnicoBeachTennis.id}">${nivelTecnicoBeachTennis.descricao}</option>
									</c:otherwise>
								</c:choose>											
								</c:forEach>
							</select>								
						</c:if>
						
						<c:if test="${!administrador}">
						<label for="mudarNivelBT">Nível Técnico Beach Tennis *</label>	
						<select name="tenista.nivelTecnicoBeachTennis.id" id="nivelTecnicoBeachTennis" class="form-control" disabled="disabled">
							<option value="0">Escolha uma opção</option>
							  <c:forEach items="${niveisTecnicosBT}" var="nivelTecnicoBeachTennis">								
								<c:choose>		
									<c:when test="${nivelTecnicoBeachTennis.id == tenista.nivelTecnicoBeachTennis.id}">
										<option value="${nivelTecnicoBeachTennis.id}" selected="selected">${nivelTecnicoBeachTennis.descricao}</option>
									</c:when>									
									<c:otherwise>
										<option value="${nivelTecnicoBeachTennis.id}">${nivelTecnicoBeachTennis.descricao}</option>
									</c:otherwise>
								</c:choose>											
								</c:forEach>
							</select>
							<input type="hidden" name="tenista.nivelTecnicoBeachTennis.id" id="nivelTecnicoBTHidden" value="${tenista.nivelTecnicoBeachTennis.id}" />							
						</c:if>
							
							<small>O seu nível técnico só poderá ser alterado através de solicitação a comissão.</small>
							
						</div>
						
						
						<div class="form-group col-sm-6">
							
						<c:if test="${administrador}">
							<label for="mudarNivel">Nível Técnico Tênis * <br/><input type="checkbox" id="mudarNivel" name="mudarNivel" />
								Marque para mudar o nível técnico.</label>
							<select name="tenista.nivelTecnicoPrincipal.id" id="nivelTecnico" class="form-control">
							<option value="0">Escolha uma opção</option>
							  <c:forEach items="${niveisTecnicos}" var="nivelTecnico">								
								<c:choose>		
									<c:when test="${nivelTecnico.id == tenista.nivelTecnicoPrincipal.id}">
										<option value="${nivelTecnico.id}" selected="selected">${nivelTecnico.descricao}</option>
									</c:when>
									
									<c:otherwise>
										<option value="${nivelTecnico.id}">${nivelTecnico.descricao}</option>
									</c:otherwise>
								</c:choose>											
								</c:forEach>
							</select>								
						</c:if>
						
						<c:if test="${!administrador}">
						<label for="mudarNivel">Nível Técnico *</label>	
						<select name="tenista.nivelTecnicoPrincipal.id" id="nivelTecnico" class="form-control" disabled="disabled">
							<option value="0">Escolha uma opção</option>
							  <c:forEach items="${niveisTecnicos}" var="nivelTecnico">								
								<c:choose>		
									<c:when test="${nivelTecnico.id == tenista.nivelTecnicoPrincipal.id}">
										<option value="${nivelTecnico.id}" selected="selected">${nivelTecnico.descricao}</option>
									</c:when>									
									<c:otherwise>
										<option value="${nivelTecnico.id}">${nivelTecnico.descricao}</option>
									</c:otherwise>
								</c:choose>											
								</c:forEach>
							</select>
							<input type="hidden" name="tenista.nivelTecnicoPrincipal.id" id="nivelTecnicoHidden" value="${tenista.nivelTecnicoPrincipal.id}" />							
						</c:if>
							
							<small>O seu nível técnico só poderá ser alterado através de solicitação a comissão.</small>
							
						</div>
						

					</div>
					
					<div class = "row">
					<c:if test="${administrador}">						
						<div class="form-group col-sm-3">
							<label for="email">E-mail (seu login de acesso) *</label>
							<input type="email" id="email" name="tenista.email" class="form-control" value="${tenista.email}" required="required"/>
						</div>
						
						<div class="form-group col-sm-3">
							<label for="verificacaoEmail">Confime o seu e-mail *</label>
							<input type="email" id="verificacaoEmail" name="verificacaoEmail" class="form-control" value="${tenista.email}" required="required"/>
						</div>
					</c:if>
					<c:if test="${!administrador}">						
						<div class="form-group col-sm-3">
							<label for="email">E-mail (seu login de acesso) *</label>
							<input type="email" id="email" name="tenista.email" class="form-control" value="${tenista.email}" required="required" disabled="disabled"/>
							<input type="hidden" id="emailHidden" name="tenista.email" class="form-control" value="${tenista.email}" required="required"/>
						</div>
						
						<div class="form-group col-sm-3">
							<label for="verificacaoEmail">Confime o seu e-mail *</label>
							<input type="email" id="verificacaoEmail" name="verificacaoEmail" class="form-control" value="${tenista.email}" required="required" disabled="disabled"/>
							<input type="hidden" id="verificacaoEmailHidden" name="verificacaoEmail" class="form-control" value="${tenista.email}" required="required"/>
						</div>
					</c:if>
					
						<div class="form-group col-sm-3">
							<label for="telefonePrincipal">Telefone principal *</label>
							<input type="text" id="telefonePrincipal" name="tenista.telefonePrincipal.numero" class="form-control" value="${tenista.telefonePrincipal.numero}"  required="required" maxlength="11"/>
						</div>
					
						<div class="form-group col-sm-3">
							<label for="telefoneSecundario">Telefone secundário</label>
							<input type="text" id="telefoneSecundario" name="tenista.telefoneSecundario.numero" class="form-control" value="${tenista.telefoneSecundario.numero}" maxlength="11"/>
						</div>
					
						<!--  
						<div class="form-group col-sm-3">
							<label for="senha1">Senha *</label>
							<input type="password" id="senha1" name="senha1" class="form-control" required="required" disabled="disabled"/>
							<input type="hidden" id="senha" name="tenista.senha" class="form-control" value="${tenista.senha}" />							
							<span class="help-block">Mínimo de 7 caracteres</span>
							
						</div>
						
						<div class="form-group col-sm-3">
							<label for="senha2">Confirme a sua senha *</label>
							<input type="password" id="senha2" name="senha2" class="form-control" required="required"  disabled="disabled"/>
							<span class="help-block">Mínimo de 7 caracteres</span>
						</div>
						-->
					</div>

					<!-- 
					<div class = "row">
						
					 
						<div class="form-group col-sm-3">
							<label for="operadoraPrincipal">Operadora principal</label>
							<select name="tenista.telefonePrincipal.operadora" id="operadoraPrincipal" class="form-control" >
								<option value="" selected="selected">Escolha uma operadora</option>
							  <c:if test="${tenista.telefonePrincipal.operadora eq('CLARO')}">
								  <option value="CLARO" selected="selected">Claro</option>
							  </c:if>
							  <c:if test="${tenista.telefonePrincipal.operadora ne('CLARO')}">
								  <option value="CLARO">Claro</option>
							  </c:if>
							  <c:if test="${tenista.telefonePrincipal.operadora eq('OI')}">
								  <option value="OI" selected="selected">Oi</option>
							  </c:if>
							  <c:if test="${tenista.telefonePrincipal.operadora ne('OI')}">
								  <option value="OI">Oi</option>
							  </c:if>
							  <c:if test="${tenista.telefonePrincipal.operadora eq('NET')}">
								  <option value="NET" selected="selected">Net</option>
							  </c:if>
							  <c:if test="${tenista.telefonePrincipal.operadora ne('NET')}">
								  <option value="NET">Net</option>
							  </c:if>
							  <c:if test="${tenista.telefonePrincipal.operadora eq('TIM')}">
								  <option value="TIM" selected="selected">Tim</option>
							  </c:if>
							  <c:if test="${tenista.telefonePrincipal.operadora ne('TIM')}">
								  <option value="TIM">Tim</option>
							  </c:if>
							  <c:if test="${tenista.telefonePrincipal.operadora eq('VIVO')}">
								  <option value="VIVO" selected="selected">Vivo</option>
							  </c:if>
							  <c:if test="${tenista.telefonePrincipal.operadora ne('VIVO')}">
								  <option value="VIVO">Vivo</option>
							  </c:if>
							</select>
						</div>
						-->
						<!--  
						<div class="form-group col-sm-3">
							<label for="operadoraSecundaria">Operadora secundária</label>
							<select name="tenista.telefoneSecundario.operadora" id="operadoraSecundaria" class="form-control">
							<option value="" selected="selected">Escolha uma operadora</option>
							  <c:if test="${tenista.telefoneSecundario.operadora eq('CLARO')}">
								  <option value="CLARO" selected="selected">Claro</option>
							  </c:if>
							  <c:if test="${tenista.telefoneSecundario.operadora ne('CLARO')}">
								  <option value="CLARO">Claro</option>
							  </c:if>
							  <c:if test="${tenista.telefoneSecundario.operadora eq('OI')}">
								  <option value="OI" selected="selected">Oi</option>
							  </c:if>
							  <c:if test="${tenista.telefoneSecundario.operadora ne('OI')}">
								  <option value="OI">Oi</option>
							  </c:if>
							  <c:if test="${tenista.telefoneSecundario.operadora eq('NET')}">
								  <option value="NET" selected="selected">Net</option>
							  </c:if>
							  <c:if test="${tenista.telefoneSecundario.operadora ne('NET')}">
								  <option value="NET">Net</option>
							  </c:if>
							  <c:if test="${tenista.telefoneSecundario.operadora eq('TIM')}">
								  <option value="TIM" selected="selected">Tim</option>
							  </c:if>
							  <c:if test="${tenista.telefoneSecundario.operadora ne('TIM')}">
								  <option value="TIM">Tim</option>
							  </c:if>
							  <c:if test="${tenista.telefoneSecundario.operadora eq('VIVO')}">
								  <option value="VIVO" selected="selected">Vivo</option>
							  </c:if>
							  <c:if test="${tenista.telefoneSecundario.operadora ne('VIVO')}">
								  <option value="VIVO">Vivo</option>
							  </c:if>
							</select>
						</div>
						
					</div>
				-->
				<hr>
	                		<h4>OUTRAS INFORMAÇÕES</h4>
	                		<hr>
					<div class = "row">
						
						<div class="form-group col-sm-3">
							<label for="clube">Clube</label>
							<select name="tenista.clube.id" id="clube" class="form-control">
								<option value="0">Nenhum</option>
								<c:forEach items="${clubes}" var="clube">	
								<c:choose>		
									<c:when test="${clube.id == tenista.clube.id}">
										<option value="${clube.id}" selected="selected">${tenista.clube.nome}</option>
									</c:when>
									
									<c:otherwise>
										<option value="${clube.id}">${clube.nome}</option>
									</c:otherwise>
								</c:choose>											
								</c:forEach>
							</select>
						</div>
						<div class="form-group col-sm-3">
							<label for="professor">Professor</label>
							<select name="tenista.professor.id" id="professor" class="form-control">
							<option value="0">Nenhum</option>
							  <c:forEach items="${professores}" var="professor">
								<c:choose>		
									<c:when test="${professor.id == tenista.professor.id}">
										<option value="${professor.id}" selected="selected">${professor.nome}</option>
									</c:when>									
									<c:otherwise>
										<option value="${professor.id}">${professor.nome}</option>
									</c:otherwise>
								</c:choose>											
							  </c:forEach>
							</select>
						</div>

						<div class="form-group col-sm-3">
							<label for="raquete">Raquete</label>
							<select name="tenista.raquete" id="raquete" class="form-control">
							 <option value="">Escolha uma raquete</option>
							  <c:if test="${tenista.raquete eq('DropShot')}">
								  <option value="Babolat" selected="selected">Drop Shot</option>
							  </c:if>
							  <c:if test="${tenista.raquete ne('DropShot')}">
								  <option value="Babolat">Drop Shot</option>
							  </c:if>
							  <c:if test="${tenista.raquete eq('Mormai')}">
								  <option value="Dunlop" selected="selected">Mormai</option>
							  </c:if>
							  <c:if test="${tenista.raquete ne('Mormai')}">
								  <option value="Dunlop">Mormai</option>
							  </c:if>
							  <c:if test="${tenista.raquete eq('Head')}">
								  <option value="Head" selected="selected">Head</option>
							  </c:if>
							  <c:if test="${tenista.raquete ne('Head')}">
							  	<option value="Head">Head</option>
							  </c:if>
							  <c:if test="${tenista.raquete eq('Prince')}">
								  <option value="Prince" selected="selected">Prince</option>
							  </c:if>
							  <c:if test="${tenista.raquete ne('Prince')}">
							 	 <option value="Prince">Prince</option>
							  </c:if>
							  <c:if test="${tenista.raquete eq('Prokennex')}">
								  <option value="Prokennex" selected="selected">Prokennex</option>
							  </c:if>
 							  <c:if test="${tenista.raquete ne('Prokennex')}">
							  	  <option value="Prokennex">Prokennex</option>
							  </c:if>							  
							  <c:if test="${tenista.raquete eq('Volkl')}">
								  <option value="Volkl" selected="selected">Volkl</option>
							  </c:if>
 							  <c:if test="${tenista.raquete ne('Volkl')}">
								  <option value="Volkl">Volkl</option>
							  </c:if>							  
							  <c:if test="${tenista.raquete eq('Wilson')}">
								  <option value="Wilson" selected="selected">Wilson</option>
							  </c:if>
							  <c:if test="${tenista.raquete ne('Wilson')}">
								  <option value="Wilson">Wilson</option>
							  </c:if>							  
							  <c:if test="${tenista.raquete eq('Yonex')}">
								  <option value="Yonex" selected="selected">Yonex</option>
							  </c:if>
							  <c:if test="${tenista.raquete ne('Yonex')}">
								  <option value="Yonex">Yonex</option>
							  </c:if>
							  <c:if test="${tenista.raquete eq('MaxBeachTennis')}">
								  <option value="Yonex" selected="selected">Max Beach Tennis</option>
							  </c:if>
							  <c:if test="${tenista.raquete ne('MaxBeachTennis')}">
								  <option value="Yonex">Max Beach Tennis</option>
							  </c:if>

 							  <c:if test="${tenista.raquete eq('Outra')}">
								  <option value="Outra" selected="selected">Outra</option>
							  </c:if>							  
 							  <c:if test="${tenista.raquete ne('Outra')}">
								  <option value="Outra">Outra</option>
							  </c:if>							  
							</select>
						</div>
						<div class="form-group col-sm-3">
							<label for="inicioNoTenis">Início no beach tênis</label>
							<input type="text" id="inicioNoTenis" name="tenista.inicioNoTenis"
							 class="form-control" value="<fmt:formatDate pattern="dd/MM/yyyy" value="${tenista.inicioNoTenis.time}"/>" />
							 
						</div>
						
					</div>

				<hr>
				
	                		<h4>ENDEREÇO</h4>
	                		<hr>
					<div class = "row">
						
						<div class="form-group col-sm-2">
							<label for="cep">CEP</label>
							<input type="text" id="cep" name="tenista.endereco.cep" class="form-control" value="${tenista.endereco.cep}" />
						</div>
					
						<div class="form-group col-sm-5">
							<label for="logradouro">Logradouro</label>
							<input type="text" id="logradouro" name="tenista.endereco.logradouro" class="form-control" value="${tenista.endereco.logradouro}" />
						</div>
						<div class="form-group col-sm-2">
							<label for="numero">Número</label>
							<input type="number" id="numero" name="tenista.endereco.numero" class="form-control" value="${tenista.endereco.numero}" />
						</div>
					</div>

					<div class = "row">
						<div class="form-group col-sm-3">
							<label for="bairro">Bairro</label>
							<input type="text" id="bairro" name="tenista.endereco.bairro" class="form-control" value="${tenista.endereco.bairro}" />
						</div>
						<div class="form-group col-sm-3">
							<label for="cidade">Cidade</label>
							<select name="tenista.endereco.cidade.id" id="cidade" class="form-control">
								<c:forEach items="${cidades}" var="cidade">								
								<c:choose>		
									<c:when test="${cidade.id == tenista.endereco.cidade.id}">
										<option value="${cidade.id}" selected="selected">${cidade.nome}</option>
									</c:when>
									
									<c:otherwise>
										<option value="${cidade.id}">${cidade.nome}</option>
									</c:otherwise>
								</c:choose>											
								</c:forEach>
							</select>

						</div>
				
					</div>
					<div class = "row">
						<div class="form-group col-sm-7">
							<label for="complemento">Complemento</label>
							<input type="text" id="complemento" name="tenista.endereco.complemento" class="form-control" value="${tenista.endereco.complemento}" />
						</div>
					</div>
				
				<!--	comentado por enquanto
				<fieldset>
					<legend>Locais de jogo</legend>
						<div class = "row">
							<div class="form-group col-sm-2">
								<label class="checkbox col-sm-1">
								<input type="checkbox" id="1" name="tenista.locais" />Centro Tenístico</label>
							</div>
							<div class="form-group col-sm-2">
								<label class="checkbox col-sm-1">
								<input type="checkbox" id="2" name="tenista.locais" />Cabo Branco</label>
							</div>
							<div class="form-group col-sm-2">
								<label class="checkbox col-sm-1">
								<input type="checkbox" id="3" name="tenista.locais" />Condomínio</label>
							</div>
						</div>
				</fieldset>
				-->
				<c:if test="${administrador}">
						
	                		<h4>Opções especiais</h4>
	                		<hr>
							<div class = "row">
							<div class="form-group col-sm-3">
							<label for="tipoUsuario">Tipo de Usuário</label>
							<select name="tenista.tipo" id="tipo" class="form-control" >
							  <c:if test="${tenista.tipo eq('ADMIN')}">
								  <option value="ADMIN" selected="selected">Administrador</option>
							  </c:if>
							  <c:if test="${tenista.tipo ne('ADMIN')}">
								  <option value="ADMIN">Administrador</option>
							  </c:if>
							  <c:if test="${tenista.tipo eq('PADRAO')}">
								  <option value="PADRAO" selected="selected">Padrão</option>
							  </c:if>
							  <c:if test="${tenista.tipo ne('PADRAO')}">
								  <option value="PADRAO">Padrão</option>
							  </c:if>
							  <c:if test="${tenista.tipo eq('ORGANIZADOR')}">
								  <option value="ORGANIZADOR" selected="selected">Organizador</option>
							  </c:if>
							  <c:if test="${tenista.tipo ne('ORGANIZADOR')}">
								  <option value="ORGANIZADOR">Organizador</option>
							  </c:if>
							</select>
						</div>
						</div>						
						<div class = "row">
							<div class="form-group col-sm-10">
							<c:if test="${tenista.bolsista}">
								<label class="checkbox col-sm-5">
								<input type="checkbox" id="bolsista" name="tenista.bolsista" checked="checked" />Bolsista</label>
							</c:if>
							<c:if test="${!tenista.bolsista}">
								<label class="checkbox col-sm-5">
								<input type="checkbox" id="bolsista" name="tenista.bolsista" />Bolsista</label>
							</c:if>
							</div>
						</div>
						<div class = "row">
							<div class="form-group col-sm-10">
							<c:if test="${tenista.visitante}">
								<label class="checkbox col-sm-5">
								<input type="checkbox" id="visitante" name="tenista.visitante" checked="checked"/>Visitante</label>
							</c:if>
							<c:if test="${!tenista.visitante}">
								<label class="checkbox col-sm-5">
								<input type="checkbox" id="visitante" name="tenista.visitante"/>Visitante</label>
							</c:if>
							</div>
						</div>
						
						<div class = "row">
							<div class="form-group col-sm-10">
							<c:if test="${tenista.ativo}">
								<label class="checkbox col-sm-5">
								<input type="checkbox" id="ativo" name="tenista.ativo" checked="checked" />Ativo</label>
							</c:if>
							<c:if test="${!tenista.ativo}">
								<label class="checkbox col-sm-5">
								<input type="checkbox" id="ativo" name="tenista.ativo" />Ativo</label>
							</c:if>
							</div>
						</div>

						<div class = "row">
							<div class="form-group col-sm-10">
							<c:if test="${tenista.emailConfirmado}">
								<label class="checkbox col-sm-5">
								<input type="checkbox" id="emailConfirmado" name="tenista.emailConfirmado" checked="checked" />E-mail confirmado</label>
							</c:if>
							<c:if test="${!tenista.emailConfirmado}">
								<label class="checkbox col-sm-5">
								<input type="checkbox" id="emailConfirmado" name="tenista.emailConfirmado" />E-mail confirmado</label>
							</c:if>
							</div>
						</div>
						
						<div class = "row">
							<div class="form-group col-sm-10">
							<c:if test="${tenista.participaDeRanking}">
								<label class="checkbox col-sm-5">
								<input type="checkbox" id="participaDeRanking" name="tenista.participaDeRanking" checked="checked"/>Participante do ranking de torneios</label>
							</c:if>
							<c:if test="${!tenista.participaDeRanking}">
								<label class="checkbox col-sm-5">
								<input type="checkbox" id="participaDeRanking" name="tenista.participaDeRanking"/>Participante do ranking de torneios</label>
							</c:if>
							</div>
						</div>
						<div class = "row">
							<div class="form-group col-sm-10">
							<c:if test="${tenista.confirmado}">
								<label class="checkbox col-sm-5">
								<input type="checkbox" id="confirmado" name="tenista.confirmado" checked="checked"/>Confirmado</label>
							</c:if>
							<c:if test="${!tenista.confirmado}">
								<label class="checkbox col-sm-5">
								<input type="checkbox" id="confirmado" name="tenista.confirmado"/>Confirmado</label>
							</c:if>
							</div>
						</div>
						<div class = "row">
							<div class="form-group col-sm-10">
							<c:if test="${tenista.filiado}">
								<label class="checkbox col-sm-5">
								<input type="checkbox" id="filiado" name="tenista.filiado" checked="checked"/>Filiado</label>
							</c:if>
							<c:if test="${!tenista.filiado}">
								<label class="checkbox col-sm-5">
								<input type="checkbox" id="filiado" name="tenista.filiado"/>Filiado</label>
							</c:if>
							</div>
						</div>						
						<div class = "row">
							<div class="form-group col-sm-10">
							<c:if test="${tenista.suspenso}">
								<label class="checkbox col-sm-5">
								<input type="checkbox" id="suspenso" name="tenista.suspenso" checked="checked"/>Suspenso</label>
							</c:if>
							<c:if test="${!tenista.suspenso}">
								<label class="checkbox col-sm-5">
								<input type="checkbox" id="suspenso" name="tenista.suspenso"/>Suspenso</label>
							</c:if>
							</div>
						</div>						
				</c:if>

					<hr>
	                		<h4>NOTIFICAÇÕES</h4>
	                		<hr>
						<div class = "row">
						
							<div class="form-group col-sm-10">
							<c:if test="${tenista.receberNotificacoes}">
								<label class="checkbox col-sm-5">
								<input type="checkbox" id="receberNotificacoes" name="tenista.receberNotificacoes" checked="checked"/>Desejo receber notificações de torneios</label>
							</c:if>
							<c:if test="${!tenista.receberNotificacoes}">
								<label class="checkbox col-sm-5">
								<input type="checkbox" id="receberNotificacoes" name="tenista.receberNotificacoes"/>Desejo receber notificações de torneios</label>
							</c:if>
							</div>
						</div>

						
				<!--  		
				<fieldset>
				<legend>Informe o código</legend>
						<div class = "row">
							<div class="form-group col-sm-2">
							<label>Código:</label>
							<label>${captcha}</label> 
							<input id="captchaInformado" name="captchaInformado" class="form-control" required />
							</div>
						</div>
				</fieldset>
				-->
				<div class="btn-toolbar" align="center">
					<button type="submit" class="form-group col-sm-12 btn btn-primary pull-center">Salvar dados</button>
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