<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <title>${empresa} | Cadastro de Atletas</title>
    <c:import url="../comum/links.jsp"/>
</head>

<body> 
<div class="body_wrapper">
  <div class="center">
	<c:import url="../menu/menu.jsp"></c:import>	

	<div class="container-fluid">


    <div class="content_area">


      
        <div class="single_left_coloum_wrapper">
            <h2 class="title">Cadastro de ${esportista}s</h2> 
            <div align="center"><small> Se você já for cadastrado não realize um novo cadastro, pois você poderá receber punições na pontuação do circuito. <br> </small>
            <small>* Dados obrigatórios</small></div>
        </div>
    
    		<c:import url="../comum/errors.jsp"/>

			<form action="${linkTo[TenistaController].solicitaCadastroTenista}" method="POST" name = "solicitaCadastroTenista">
				
					
					<div class="row">
						<div class="col-lg-12">
	                		<h4>Dados pessoais</h4>
	                		<hr>
						</div>
						<div class="form-group col-sm-6">
						<label for="nome">Nome *</label>
						<input type="text" id="nome" name="tenista.nome" class="form-control" value="${tenista.nome}" required="required"/>
						</div>

						<div class="form-group col-sm-3">
						<label for="sobrenome">Sobrenome *</label>
						<input type="text" id="sobrenome" name="tenista.sobreNome" class="form-control" value="${tenista.sobreNome}" required="required"/>
						</div>
						
						<div class="form-group col-sm-3">
							<label for="nomeRanking">Nome no Ranking (Abreviado) *</label>
							<input type="text" id="nomeRanking" name="tenista.nomeRanking" class="form-control" value="${tenista.nomeRanking}" required="required"/> 
						</div>
						
					</div>
					
					<div class="row">

						<div class="form-group col-sm-3">
							<label for="dataNascimento">Data de Nascimento</label>
							<input type="text" id="dataNascimento" name="tenista.dataNascimento"
							 class="form-control" value="<fmt:formatDate pattern="dd/MM/yyyy" value="${tenista.dataNascimento.time}"/>" />
						</div>

						<div class="form-group col-sm-3">
							<label for="sexo">Sexo</label>
							<select name="tenista.sexo" id="sexo" class="form-control" >
							  <option value="MASCULINO">Masculino</option>
							  <option value="FEMININO">Feminino</option>
							</select>
						</div>

						<div class="form-group col-sm-3">
							<label for="cpf">CPF *</label>
							<input type="text" id="cpf" name="tenista.cpf" class="form-control" value="${tenista.cpf}" required="required"/>
						</div>

						<c:if test="${administrador}">

						<div class="checkbox col-sm-3">
							<label>
							<input type="checkbox" id="temCpf" name="tenista.naoPossuiCpf" />
								Se não possui CPF e está informando o CPF de um responsável, marque esta opção.
							</label>
						</div>
						
						</c:if>

					</div>
					
					<div class = "row">
						
						<div class="form-group col-sm-3">
							<label for="email">E-mail (seu login de acesso) *</label>
							<input type="email" id="email" name="tenista.email" class="form-control" value="${tenista.email}" required="required"/>
						</div>
						
						<div class="form-group col-sm-3">
							<label for="verificacaoEmail">Confime o seu e-mail *</label>
							<input type="email" id="verificacaoEmail" name="verificacaoEmail" class="form-control" value="${verificacaoEmail}" required="required"/>
						</div>

						<div class="form-group col-sm-3">
							<label for="senha1">Senha *</label>
							<input type="password" id="senha1" name="senha1" class="form-control" required="required" />
							<span class="help-block">Mínimo de 7 caracteres</span>
							
						</div>
						
						<div class="form-group col-sm-3">
							<label for="senha2">Confirme a sua senha *</label>
							<input type="password" id="senha2" name="senha2" class="form-control" required="required" />
							<span class="help-block">Mínimo de 7 caracteres</span>
						</div>
					</div>

				
					<div class = "row">
						<div class="form-group col-sm-3">
							<label for="telefonePrincipal">Telefone principal *</label>
							<input type="text" id="telefonePrincipal" name="tenista.telefonePrincipal.numero" class="form-control telefone" value="${tenista.telefonePrincipal.numero}"  required="required"/>
						</div>
						
						<!--  
						<div class="form-group col-sm-3">
							<label for="operadoraPrincipal">Operadora principal</label>
							<select name="tenista.telefonePrincipal.operadora" id="operadoraPrincipal" class="form-control" >
							  <option value="">Escolha uma operadora</option>
							  <option value="CLARO">Claro</option>
							  <option value="OI">Oi</option>
							  <option value="NET">Net</option>  
							  <option value="TIM">Tim</option>
							  <option value="VIVO">Vivo</option>
							</select>
						</div>
						
						-->
						<div class="form-group col-sm-3">
							<label for="telefoneSecundario">Telefone secundário</label>
							<input type="text" id="telefoneSecundario" name="tenista.telefoneSecundario.numero" class="form-control telefone" value="${tenista.telefoneSecundario.numero}" />
						</div>
						
						<div class="form-group col-sm-3">
							<label for="nivelTecnico">Nível Técnico (Tênis) *</label>
							<select name="tenista.nivelTecnicoPrincipal.id" id="nivelTecnico" class="form-control">
							<option value="0">Escolha uma opção</option>
							  <c:forEach items="${niveisTecnicos}" var="nivelTecnico">								
								  <option value="${nivelTecnico.id}">${nivelTecnico.descricao}</option>
								</c:forEach>
							</select>
							<small>O seu nível técnico só poderá ser alterado através de solicitação a comissão do Portal do Tênis Paraibano.</small>
							
						</div>
						
						<div class="form-group col-sm-3">
							<label for="nivelTecnicoBeachTennis">Nível Técnico (Beach Tennis) *</label>
							<select name="tenista.nivelTecnicoBeachTennis.id" id="nivelTecnicoBeachTennis" class="form-control">
							<option value="0">Escolha uma opção</option>
							  <c:forEach items="${niveisTecnicosBeachTennis}" var="nivelTecnicoBeachTennis">								
								  <option value="${nivelTecnicoBeachTennis.id}">${nivelTecnicoBeachTennis.descricao}</option>
								</c:forEach>
							</select>
							<small>O seu nível técnico só poderá ser alterado através de solicitação a comissão da Portal do Tênis Paraibano.</small>
							
						</div>
						
						<!--  
						<div class="form-group col-sm-3">
							<label for="operadoraSecundaria">Operadora secundária</label>
							<select name="tenista.telefoneSecundario.operadora" id="operadoraSecundaria" class="form-control">
							  <option value="">Escolha uma operadora</option>
							  <option value="CLARO">Claro</option>
							  <option value="OI">Oi</option>
							  <option value="NET">Net</option>  
							  <option value="TIM">Tim</option>
							  <option value="VIVO">Vivo</option>
							</select>
						</div>
						
						-->
					</div>
				
				<!-- 
				
					<div class = "row">
						<div class="col-lg-12">
	                		<h4>Informações técnicas</h4>
	                		<hr>
						</div>
						<div class="form-group col-sm-3">
							<label for="clube">Clube</label>
							<select name="tenista.clube.id" id="clube" class="form-control">
								<option value="0">Nenhum</option>
								<c:forEach items="${clubes}" var="clube">								
								  <option value="${clube.id}">${clube.nome}</option>
								</c:forEach>
							</select>
						</div>
						<div class="form-group col-sm-3">
							<label for="professor">Professor</label>
							<select name="tenista.professor.id" id="professor" class="form-control">
							<option value="0">Nenhum</option>
							  <c:forEach items="${professores}" var="professor">
							  		<option value="${professor.id}">${professor.nome}</option>
							  </c:forEach>
							</select>
						</div>

						<div class="form-group col-sm-3">
							<label for="raquete">Raquete</label>
							<select name="tenista.raquete" id="raquete" class="form-control">
							  <option value="">Escolha a marca da sua</option>
							  <option value="Babolat">Babolat</option>
							  <option value="Dunlop">Dunlop</option>
							  <option value="Head">Head</option>
							  <option value="Prince">Prince</option>
							  <option value="Prokennex">Prokennex</option>
							  <option value="Volkl">Volkl</option>
							  <option value="Wilson">Wilson</option>
							  <option value="Yonex">Yonex</option>
							  
							  <option value="Outra">Outra</option>
							</select>
						</div>
						
					</div>

					<div class = "row">
						<div class="form-group col-sm-3">
							<label for="nivelTecnico">Nível Técnico *</label>
							<select name="tenista.nivelTecnicoPrincipal.id" id="nivelTecnico" class="form-control">
							<option value="0">Escolha uma opção</option>
							  <c:forEach items="${niveisTecnicos}" var="nivelTecnico">								
								  <option value="${nivelTecnico.id}">${nivelTecnico.descricao}</option>
								</c:forEach>
							</select>
							<small>O seu nível técnico só poderá ser alterado através de solicitação a comissão da FPT.</small>
							
						</div>
						
						<div class="form-group col-sm-3">
							<label for="inicioNoTenis">Início no tênis</label>
							<input type="text" id="inicioNoTenis" name="tenista.inicioNoTenis"
							 class="form-control" value="<fmt:formatDate pattern="dd/MM/yyyy" value="${tenista.inicioNoTenis.time}"/>" />
							 
						</div>
						
					</div>
				
				
					<div class = "row">
						<div class="col-lg-12">
	                		<h4>Endereço (opcional)</h4>
	                		<hr>
						</div>
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
							  		<option value="${cidade.id}">${cidade.nome}</option>
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
				 -->
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
				
						<div class = "row">
						<div class="col-lg-12">
	                		<h4>Opções especiais</h4>
	                		<hr>
						</div>
							<div class="form-group col-sm-10">
								<label class="checkbox col-sm-5">
								<input type="checkbox" id="bolsista" name="tenista.bolsista" />Bolsista</label>
							</div>
						</div>

						<div class = "row">
							<div class="form-group col-sm-10">
								<label class="checkbox col-sm-5">
								<input type="checkbox" id="visitante" name="tenista.visitante"/>Visitante</label>
							</div>
						</div>
						
						<div class = "row">
							<div class="form-group col-sm-10">
								<label class="checkbox col-sm-5">
								<input type="checkbox" id="ativo" name="tenista.ativo" checked="checked"/>Ativo</label>
							</div>
						</div>
						
						<div class = "row">
							<div class="form-group col-sm-10">
								<label class="checkbox col-sm-5">
								<input type="checkbox" id="participaDeRanking" name="tenista.participaDeRanking" checked="checked"/>Participante do ranking de torneios</label>
							</div>
						</div>
						
				</c:if>

				
						<div class = "row">
						<div class="col-lg-12">
	                		<h4>Notificações</h4>
	                		<hr>
						</div>
							<div class="form-group col-sm-10">
								<label class="checkbox col-sm-5">
								<input type="checkbox" id="receberNotificacoes" name="tenista.receberNotificacoes" checked="checked"/>Desejo receber notificações de torneios</label>
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
					<button type="submit" class="form-group col-sm-12 btn btn-default pull-center">Avançar</button>
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