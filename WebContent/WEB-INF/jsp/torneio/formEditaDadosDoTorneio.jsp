<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${empresa} | Edição de torneios</title>
<c:import url="../comum/links.jsp"/>
</head>
<body>
<div class="body_wrapper">
  <div class="center">
	<c:import url="../menu/menu.jsp"></c:import>	

	<div class="container-fluid">


    <div class="content_area">


      
        <div class="single_left_coloum_wrapper">
            <h2 class="title">Editar Torneio</h2>
        </div>

	<c:import url="../comum/errors.jsp"/>

			<form action="${linkTo[TorneioController].editaDadosDoTorneio}" method="POST" >
			
			<input type="hidden" name="torneio.id" value="${torneio.id}">
			<input type="hidden" name="torneio.premiacao.id" value="${torneio.premiacao.id}">
			
			<hr>
			<h4>Informações básicas</h4>
			<hr>
				<div class="row">
					<div class="form-group col-sm-12">
						<label for="nome">Nome do torneio *</label>
						<input type="text" id="nome" name="torneio.nome" class="form-control" value="${torneio.nome}" required="required"/>
					</div>
				</div>
				<div class="row">
					<div class="form-group col-sm-4">
						<label for="organizador">Organizador *</label>
						<select id="organizador" name="torneio.tenistaOrganizador.id" class="form-control">
							
							<c:forEach items="${organizadores}" var="organizador">
								<c:choose>		
									<c:when test="${torneio.tenistaOrganizador.id == organizador.id}">
										<option value="${organizador.id}" selected="selected">${organizador.nomeRanking}</option>
									</c:when>
									
									<c:otherwise>
										<option value="${organizador.id}">${organizador.nomeRanking}</option>
									</c:otherwise>
								</c:choose>
															
							</c:forEach>
						</select> 
					</div>
					<div class="form-group col-sm-4">
						<label for="organizador">Auxiliar 1</label>
						<select id="organizador" name="torneio.tenistaAuxiliar1.id" class="form-control">
	
							<option value="0" selected="selected">Escolha uma opção</option>
							
							<c:forEach items="${organizadores}" var="auxiliar">
								<c:choose>		
									<c:when test="${torneio.tenistaAuxiliar1.id == auxiliar.id}">
										<option value="${auxiliar.id}" selected="selected">${auxiliar.nomeRanking}</option>
									</c:when>
									
									<c:otherwise>
										<option value="${auxiliar.id}">${auxiliar.nomeRanking}</option>
									</c:otherwise>
								</c:choose>
															
							</c:forEach>
							
	
	
							
						</select> 
					</div>
					<div class="form-group col-sm-4">
					<label for="organizador">Auxiliar 2</label>
					<select id="organizador" name="torneio.tenistaAuxiliar2.id" class="form-control">
			
					<option value="0" selected="selected">Escolha uma opção</option>
						
						<c:forEach items="${organizadores}" var="auxiliar">
							<c:choose>		
								<c:when test="${torneio.tenistaAuxiliar2.id == auxiliar.id}">
									<option value="${auxiliar.id}" selected="selected">${auxiliar.nomeRanking}</option>
								</c:when>
								
								<c:otherwise>
									<option value="${auxiliar.id}">${auxiliar.nomeRanking}</option>
								</c:otherwise>
							</c:choose>
														
						</c:forEach>
					</select> 
				</div>
						
					
				</div>

					<div class="row">
				
						<div class="form-group col-sm-5">
							<label for="dataInicio">Data de início do torneio *</label>
							<input type="text" id="dataInicio" name="torneio.dataInicio" class="form-control" required="required"
							 value="<fmt:formatDate pattern="dd/MM/yyyy" value="${torneio.dataInicio.time}"/>" />
						</div>
							
						<div class="form-group col-sm-5">
							<label for="dataFim">Data de fim do torneio *</label>
							<input type="text" id="dataFim" name="torneio.dataFim" class="form-control" required="required"
							 value="<fmt:formatDate pattern="dd/MM/yyyy" value="${torneio.dataFim.time}"/>" />
						</div>
						<div class="form-group col-sm-2">
							<label for="nome">Horário *</label>
							<input type="text" id="horario" name="torneio.horario" class="form-control" value="${torneio.horario}" required="required"/>
						</div>
					</div>
					
					<div class="row">
					
					<div class="form-group col-sm-3">
						<label for="local">Local principal *</label>
						<select id="local" name="torneio.local.id" class="form-control">
							
							<c:forEach items="${locais}" var="local">
								<c:choose>		
									<c:when test="${torneio.local.id == local.id}">
										<option value="${local.id}" selected="selected">${local.nome}</option>
									</c:when>
									
									<c:otherwise>
										<option value="${local.id}">${local.nome}</option>
									</c:otherwise>
								</c:choose>
															
							</c:forEach>
						</select> 
					</div>
					
					<div class="form-group col-sm-3">
						<label for="categoria">Categoria *</label>
						<select id="categoria" name="torneio.categoria" class="form-control">
							
							<c:forEach items="${categorias}" var="categoria">
								<c:choose>		
									<c:when test="${torneio.categoria.nome == categoria.nome}">
										<option value="${categoria}" selected="selected">${categoria.nome}</option>
									</c:when>
									
									<c:otherwise>
										<option value="${categoria}">${categoria.nome}</option>
									</c:otherwise>
								</c:choose>
															
							</c:forEach>
						</select> 
					</div>
					
					<div class="form-group col-sm-2">
						<label for="tipoDeChave">Tipo de Chave *</label>
						<select id="tipoDeChave" name="torneio.tipoDeChave" class="form-control">
							
							<c:forEach items="${tiposDeChaves}" var="tipoDeChave">
								<c:choose>		
									<c:when test="${torneio.tipoDeChave.id == tipoDeChave.id}">
										<option value="${tipoDeChave}" selected="selected">${tipoDeChave.nome}</option>
									</c:when>
									
									<c:otherwise>
										<option value="${tipoDeChave}">${tipoDeChave.nome}</option>
									</c:otherwise>
								</c:choose>
															
							</c:forEach>
						</select> 
					</div>

					<div class="form-group col-sm-2">
						<label for="numeroDeEstagios">Estágios</label>
						<select id="numeroDeEstagios" name="torneio.numeroDeEstagios" class="form-control">
						<c:if test="${torneio.numeroDeEstagios<=1 || torneio.numeroDeEstagios == null}">
								<option value="1" selected="selected">1</option>
								<option value="2">2</option>
						</c:if>
						<c:if test="${torneio.numeroDeEstagios >1}">
								<option value="1">1</option>
								<option value="2" selected="selected">2</option>
						</c:if>
						</select> 
					</div>

					<div class="form-group col-sm-2">
						<label for="tenistasPorGrupo">Tenistas por grupos *</label>
						<input type="number" id="tenistasPorGrupo" name="torneio.tenistasPorGrupo" class="form-control" value="${torneio.tenistasPorGrupo}" required="required"/>
					</div>


					
					</div>
				
				<div class="row">

						<div class="form-group col-sm-12">
							<label for="observacoesAdicionais">Observações adicionais do torneio</label> 
							<textarea id="observacoesAdicionais" name="torneio.observacoesAdicionais" 
								class="form-control" rows="3" maxlength="255">${torneio.observacoesAdicionais}</textarea>
						</div>					
					
						
				</div>

			<hr>
			<h4>Informações das inscrições</h4>
			<hr>
				<div class="row">
				
					<div class="form-group col-sm-5">
						<label for="dataInicioInscricao">Início das inscrições *</label>
						<input type="text" id="dataInicioInscricao" name="torneio.dataInicioInscricao" class="form-control" required="required"
						 value="<fmt:formatDate pattern="dd/MM/yyyy" value="${torneio.dataInicioInscricao.time}"/>" />
						 <small>A data de início da inscrição só pode ser alterada pelo administrador do portal.</small>
						 
					</div>
						
					<div class="form-group col-sm-5">
						<label for="dataFimInscricao">Fim das inscrições *</label>
						<input type="text" id="dataFimInscricao" name="torneio.dataFimInscricao" class="form-control" required="required"
						 value="<fmt:formatDate pattern="dd/MM/yyyy" value="${torneio.dataFimInscricao.time}"/>" />
						 <small>A data de fim da inscrição só pode ser alterada pelo administrador do portal.</small>
					</div>

					<div class="form-group col-sm-2">
						<label for="limiteDeInscritos">Limite de inscritos</label>
						<input type="number" id="limiteDeInscritos" name="torneio.limiteDeInscritos" class="form-control" 
						value="${torneio.limiteDeInscritos}" required="required" maxlength="255"/>
						<small>Deixe vazio ou insira 0 se não tiver limite de inscritos no torneio.</small>
					</div>				
					
				</div>
				
				<div class="row">

					<div class="form-group col-sm-3">
						<label for="valorInscricao">Valor da inscrição *</label>
						<fmt:setLocale value="pt-BR" />  
						<span class="input-group-addon">R$</span>
						<input type="text" id="valorInscricao" name="torneio.valorInscricao" class="form-control" value="<fmt:formatNumber value="${torneio.valorInscricao}" minFractionDigits="2" />"required="required"/>
						  
					</div>
					<div class="form-group col-sm-3">
						<label for="valorInscricaoFiliado">Valor da inscrição para filiados *</label>
						<fmt:setLocale value="pt-BR" />  
						<span class="input-group-addon">R$</span>
						<input type="text" id="valorInscricaoFiliado" name="torneio.valorInscricaoFiliado" class="form-control" value="<fmt:formatNumber value="${torneio.valorInscricaoFiliado}" minFractionDigits="2" />"/>						  
					</div>

					<div class="form-group col-sm-3">
						<label for="valorSegundaInscricao">Valor da segunda inscrição *</label>
						<fmt:setLocale value="pt-BR" />  
						<span class="input-group-addon">R$</span>
						<input type="text" id="valorSegundaInscricao" name="torneio.valorSegundaInscricao" class="form-control" value="<fmt:formatNumber value="${torneio.valorSegundaInscricao}" minFractionDigits="2" />"required="required"/>
						  
					</div>

					<div class="form-group col-sm-3">
						<label for="valorTerceiraInscricao">Valor da terceira inscrição *</label>
						<fmt:setLocale value="pt-BR" />  
						<span class="input-group-addon">R$</span>
						<input type="text" id="valorTerceiraInscricao" name="torneio.valorTerceiraInscricao" class="form-control" value="<fmt:formatNumber value="${torneio.valorTerceiraInscricao}" minFractionDigits="2" />"required="required"/>
						  
					</div>

				</div>
				<div class="row">
					
					<div class="form-group col-sm-3">	
						<label for="botaoPagamento">Botão de Pagamento do pagseguro para 1 inscrição</label> 
						<textarea id="botaoPagamento" name="torneio.botaoPagamento" 
							class="form-control" rows="3">${torneio.botaoPagamento}</textarea>
					</div>				

					<div class="form-group col-sm-3">	
						<label for="botaoPagamentoFiliado">Botão Pagamento do pagseguro para o Filiado</label> 
						<textarea id="botaoPagamentoFiliado" name="torneio.botaoPagamentoFiliado" 
							class="form-control" rows="3">${torneio.botaoPagamentoFiliado}</textarea>
					</div>				

					<div class="form-group col-sm-3">	
						<label for="botaoSegundoPagamento">Botão de Pagamento do pagseguro para 2 inscrição</label> 
						<textarea id="botaoSegundoPagamento" name="torneio.botaoSegundoPagamento" 
							class="form-control" rows="3">${torneio.botaoSegundoPagamento}</textarea>
					</div>				

					<div class="form-group col-sm-3">	
						<label for="botaoTerceiroPagamento">Botão de Pagamento do pagseguro para 3 inscrição</label> 
						<textarea id="botaoTerceiroPagamento" name="torneio.botaoTerceiroPagamento" 
							class="form-control" rows="3">${torneio.botaoTerceiroPagamento}</textarea>
					</div>				

				
				</div>				
				
				<div class="row">
				
					<div class="form-group col-sm-12">
	
						<label for="informacoesPagamento">Informações de pagamento *</label> 
						<textarea id="informacoesPagamento" name="torneio.informacoesPagamento" 
							class="form-control" rows="3" required="required" maxlength="255">${torneio.informacoesPagamento}</textarea>
					</div>

					
				
										
				</div>
			<hr>
			<h4>Níveis disponibilizados para inscrição no Beach Tennis  (segure a tecla Ctrl e selecione)</h4>
			<hr>
			<div class="row">
			
				<div class="form-group col-sm-6">
						<label for="niveisTecnicosMasculinos">Escolha os níveis técnicos simples masculino</label>
						<select id="niveisTecnicosMasculinos" name="torneio.niveis.id" class="form-control" multiple="multiple" size="5">
							<c:forEach items="${niveisTecnicosMasculinoBT}" var="nivelTecnicoMasculino">								

									<c:set var="isNivelSelecionadoNoTorneio" value="${false}" />  

									<!-- Percorre todos os níveis do torneio e verifica se é igual ao nível atual que será mostrado -->
								    <c:forEach items="${torneio.niveis}" var ="nivelDoTorneio">

										<c:if test="${nivelDoTorneio.id == nivelTecnicoMasculino.id}">
											<c:set var="isNivelSelecionadoNoTorneio" value="${true}" />  
										</c:if>
										
								    </c:forEach>
								    
									<!-- Se o nível atual estiver disponibilizado no torneio ele mostra como selecionado -->
									<c:if test="${isNivelSelecionadoNoTorneio}">
										<option value="${nivelTecnicoMasculino.id}" selected="selected">${nivelTecnicoMasculino.descricao}</option>
									</c:if>
	
	
									<c:if test="${!isNivelSelecionadoNoTorneio}">
										<option value="${nivelTecnicoMasculino.id}">${nivelTecnicoMasculino.descricao}</option>
									</c:if>
							</c:forEach>
						</select> 
				</div>
			

				<div class="form-group col-sm-6">
						<label for="niveisFeminino">Escolha os níveis técnicos simples feminino</label>
						<select id="niveisFeminino" name="torneio.niveis.id" class="form-control" multiple="multiple" size="5">
							<!-- Percorre cada nível por idade cadastrado no portal -->
							<c:forEach items="${niveisTecnicosFemininoBT}" var="nivelTecnicoFeminino">								
									
									<c:set var="isNivelSelecionadoNoTorneio" value="${false}" />  
								
									<!-- Percorre todos os níveis do torneio e verifica se é igual ao nível atual que será mostrado -->
								    <c:forEach items="${torneio.niveis}" var ="nivelDoTorneio">

										<c:if test="${nivelDoTorneio.id == nivelTecnicoFeminino.id}">
											<c:set var="isNivelSelecionadoNoTorneio" value="${true}" />  
										</c:if>
										
								    </c:forEach>
								    
								    	<!-- Se o nível atual estiver disponibilizado no torneio ele mostra como selecionado -->
										<c:if test = "${isNivelSelecionadoNoTorneio}" >
											<option value="${nivelTecnicoFeminino.id}" selected="selected">${nivelTecnicoFeminino.descricao}</option>
										</c:if>
										
	
										<c:if test = "${!isNivelSelecionadoNoTorneio}">
											<option value="${nivelTecnicoFeminino.id}">${nivelTecnicoFeminino.descricao}</option>
										</c:if>
								    
							</c:forEach>
						</select> 
				</div>
				</div>
	
				<div class="row">			
				<div class="form-group col-sm-6">
						<label for="niveisDupla">Escolha os níveis por duplas</label>
						<select id="niveisDupla" name="torneio.niveis.id" class="form-control" multiple="multiple" size="5">
							<!-- Percorre cada nível por idade cadastrado no portal -->
							<c:forEach items="${niveisPorDuplasBT}" var="nivelDupla">								
									
									<c:set var="isNivelSelecionadoNoTorneio" value="${false}" />  
								
									<!-- Percorre todos os níveis do torneio e verifica se é igual ao nível atual que será mostrado -->
								    <c:forEach items="${torneio.niveis}" var ="nivelDoTorneio">

										<c:if test="${nivelDoTorneio.id == nivelDupla.id}">
											<c:set var="isNivelSelecionadoNoTorneio" value="${true}" />  
										</c:if>
										
								    </c:forEach>
								    
								    	<!-- Se o nível atual estiver disponibilizado no torneio ele mostra como selecionado -->
										<c:if test = "${isNivelSelecionadoNoTorneio}" >
											<option value="${nivelDupla.id}" selected="selected">${nivelDupla.descricao}</option>
										</c:if>
										
	
										<c:if test = "${!isNivelSelecionadoNoTorneio}">
											<option value="${nivelDupla.id}">${nivelDupla.descricao}</option>
										</c:if>
								    
							</c:forEach>
						</select> 
				</div>

				<div class="form-group col-sm-6">
						<label for="niveisPorIdade">Escolha os níveis por idade</label>
						<select id="niveisPorIdade" name="torneio.niveis.id" class="form-control" multiple="multiple" size="5">
							<!-- Percorre cada nível por idade cadastrado no portal -->
							<c:forEach items="${niveisPorIdadeBT}" var="nivelIdade">								
									
									<c:set var="isNivelSelecionadoNoTorneio" value="${false}" />  
								
									<!-- Percorre todos os níveis do torneio e verifica se é igual ao nível atual que será mostrado -->
								    <c:forEach items="${torneio.niveis}" var ="nivelDoTorneio">

										<c:if test="${nivelDoTorneio.id == nivelIdade.id}">
											<c:set var="isNivelSelecionadoNoTorneio" value="${true}" />  
										</c:if>
										
								    </c:forEach>
								    
								    	<!-- Se o nível atual estiver disponibilizado no torneio ele mostra como selecionado -->
										<c:if test = "${isNivelSelecionadoNoTorneio}" >
											<option value="${nivelIdade.id}" selected="selected">${nivelIdade.descricao}</option>
										</c:if>
										
	
										<c:if test = "${!isNivelSelecionadoNoTorneio}">
											<option value="${nivelIdade.id}">${nivelIdade.descricao}</option>
										</c:if>
								    
							</c:forEach>
						</select> 
				</div>
				</div>
				
				<div class="row">				
				<div class="form-group col-sm-6">
						<label for="niveisGrupo">Escolha os níveis por grupo</label>
						<select id="niveisGrupo" name="torneio.niveis.id" class="form-control" multiple="multiple" size="5">
							<!-- Percorre cada nível por idade cadastrado no portal -->
							<c:forEach items="${niveisPorGrupoBT}" var="nivelGrupo">								
									
									<c:set var="isNivelSelecionadoNoTorneio" value="${false}" />  
								
									<!-- Percorre todos os níveis do torneio e verifica se é igual ao nível atual que será mostrado -->
								    <c:forEach items="${torneio.niveis}" var ="nivelDoTorneio">

										<c:if test="${nivelDoTorneio.id == nivelGrupo.id}">
											<c:set var="isNivelSelecionadoNoTorneio" value="${true}" />  
										</c:if>
										
								    </c:forEach>
								    
								    	<!-- Se o nível atual estiver disponibilizado no torneio ele mostra como selecionado -->
										<c:if test = "${isNivelSelecionadoNoTorneio}" >
											<option value="${nivelGrupo.id}" selected="selected">${nivelGrupo.descricao}</option>
										</c:if>
										
	
										<c:if test = "${!isNivelSelecionadoNoTorneio}">
											<option value="${nivelGrupo.id}">${nivelGrupo.descricao}</option>
										</c:if>
								    
							</c:forEach>
						</select> 
				</div>
				

				<div class="form-group col-sm-6">
						<label for="niveisTecnicosBT">Escolha os níveis técnicos</label>
						<select id="niveisTecnicosBT" name="torneio.niveis.id" class="form-control" multiple="multiple" size="5">
							
							<!-- Percorre cada nível cadastrado no portal -->
							<c:forEach items="${niveisTecnicosBT}" var="nivelTecnico">								
									
									<c:set var="isNivelSelecionadoNoTorneio" value="${false}" />  
								
									<!-- Percorre todos os níveis técnicos do torneio e verifica se é igual ao nível atual que será mostrado -->
								    <c:forEach items="${torneio.niveis}" var ="nivelDoTorneio">

										<c:if test="${nivelDoTorneio.id == nivelTecnico.id}">
											<c:set var="isNivelSelecionadoNoTorneio" value="${true}" />  
										</c:if>
										
								    </c:forEach>
								    
								    	<!-- Se o nível atual estiver disponibilizado no torneio ele mostra como selecionado -->
										<c:if test = "${isNivelSelecionadoNoTorneio}" >
											<option value="${nivelTecnico.id}" selected="selected">${nivelTecnico.descricao}</option>
										</c:if>
										
	
										<c:if test = "${!isNivelSelecionadoNoTorneio}">
											<option value="${nivelTecnico.id}">${nivelTecnico.descricao}</option>
										</c:if>
								    
							</c:forEach>
						</select> 
				</div>
			</div>	

			<hr>
			<h4>Níveis disponibilizados para inscrição no Tênis  (segure a tecla Ctrl e selecione)</h4>
			<hr>
			<div class="row">
			
				<div class="form-group col-sm-6">
						<label for="niveisTecnicosMasculinos">Escolha os níveis técnicos simples masculino</label>
						<select id="niveisTecnicosMasculinos" name="torneio.niveis.id" class="form-control" multiple="multiple" size="5">
							<c:forEach items="${niveisTecnicosMasculino}" var="nivelTecnicoMasculino">								

									<c:set var="isNivelSelecionadoNoTorneio" value="${false}" />  

									<!-- Percorre todos os níveis do torneio e verifica se é igual ao nível atual que será mostrado -->
								    <c:forEach items="${torneio.niveis}" var ="nivelDoTorneio">

										<c:if test="${nivelDoTorneio.id == nivelTecnicoMasculino.id}">
											<c:set var="isNivelSelecionadoNoTorneio" value="${true}" />  
										</c:if>
										
								    </c:forEach>
								    
									<!-- Se o nível atual estiver disponibilizado no torneio ele mostra como selecionado -->
									<c:if test="${isNivelSelecionadoNoTorneio}">
										<option value="${nivelTecnicoMasculino.id}" selected="selected">${nivelTecnicoMasculino.descricao}</option>
									</c:if>
	
	
									<c:if test="${!isNivelSelecionadoNoTorneio}">
										<option value="${nivelTecnicoMasculino.id}">${nivelTecnicoMasculino.descricao}</option>
									</c:if>
							</c:forEach>
						</select> 
				</div>
			

				<div class="form-group col-sm-6">
						<label for="niveisFeminino">Escolha os níveis técnicos simples feminino</label>
						<select id="niveisFeminino" name="torneio.niveis.id" class="form-control" multiple="multiple" size="5">
							<!-- Percorre cada nível por idade cadastrado no portal -->
							<c:forEach items="${niveisTecnicosFeminino}" var="nivelTecnicoFeminino">								
									
									<c:set var="isNivelSelecionadoNoTorneio" value="${false}" />  
								
									<!-- Percorre todos os níveis do torneio e verifica se é igual ao nível atual que será mostrado -->
								    <c:forEach items="${torneio.niveis}" var ="nivelDoTorneio">

										<c:if test="${nivelDoTorneio.id == nivelTecnicoFeminino.id}">
											<c:set var="isNivelSelecionadoNoTorneio" value="${true}" />  
										</c:if>
										
								    </c:forEach>
								    
								    	<!-- Se o nível atual estiver disponibilizado no torneio ele mostra como selecionado -->
										<c:if test = "${isNivelSelecionadoNoTorneio}" >
											<option value="${nivelTecnicoFeminino.id}" selected="selected">${nivelTecnicoFeminino.descricao}</option>
										</c:if>
										
	
										<c:if test = "${!isNivelSelecionadoNoTorneio}">
											<option value="${nivelTecnicoFeminino.id}">${nivelTecnicoFeminino.descricao}</option>
										</c:if>
								    
							</c:forEach>
						</select> 
				</div>
				</div>
	
				<div class="row">			
				<div class="form-group col-sm-6">
						<label for="niveisDupla">Escolha os níveis por duplas</label>
						<select id="niveisDupla" name="torneio.niveis.id" class="form-control" multiple="multiple" size="5">
							<!-- Percorre cada nível por idade cadastrado no portal -->
							<c:forEach items="${niveisPorDuplas}" var="nivelDupla">								
									
									<c:set var="isNivelSelecionadoNoTorneio" value="${false}" />  
								
									<!-- Percorre todos os níveis do torneio e verifica se é igual ao nível atual que será mostrado -->
								    <c:forEach items="${torneio.niveis}" var ="nivelDoTorneio">

										<c:if test="${nivelDoTorneio.id == nivelDupla.id}">
											<c:set var="isNivelSelecionadoNoTorneio" value="${true}" />  
										</c:if>
										
								    </c:forEach>
								    
								    	<!-- Se o nível atual estiver disponibilizado no torneio ele mostra como selecionado -->
										<c:if test = "${isNivelSelecionadoNoTorneio}" >
											<option value="${nivelDupla.id}" selected="selected">${nivelDupla.descricao}</option>
										</c:if>
										
	
										<c:if test = "${!isNivelSelecionadoNoTorneio}">
											<option value="${nivelDupla.id}">${nivelDupla.descricao}</option>
										</c:if>
								    
							</c:forEach>
						</select> 
				</div>

				<div class="form-group col-sm-6">
						<label for="niveisPorIdade">Escolha os níveis por idade</label>
						<select id="niveisPorIdade" name="torneio.niveis.id" class="form-control" multiple="multiple" size="5">
							<!-- Percorre cada nível por idade cadastrado no portal -->
							<c:forEach items="${niveisPorIdade}" var="nivelIdade">								
									
									<c:set var="isNivelSelecionadoNoTorneio" value="${false}" />  
								
									<!-- Percorre todos os níveis do torneio e verifica se é igual ao nível atual que será mostrado -->
								    <c:forEach items="${torneio.niveis}" var ="nivelDoTorneio">

										<c:if test="${nivelDoTorneio.id == nivelIdade.id}">
											<c:set var="isNivelSelecionadoNoTorneio" value="${true}" />  
										</c:if>
										
								    </c:forEach>
								    
								    	<!-- Se o nível atual estiver disponibilizado no torneio ele mostra como selecionado -->
										<c:if test = "${isNivelSelecionadoNoTorneio}" >
											<option value="${nivelIdade.id}" selected="selected">${nivelIdade.descricao}</option>
										</c:if>
										
	
										<c:if test = "${!isNivelSelecionadoNoTorneio}">
											<option value="${nivelIdade.id}">${nivelIdade.descricao}</option>
										</c:if>
								    
							</c:forEach>
						</select> 
				</div>
				</div>
				
				<div class="row">				
				<div class="form-group col-sm-6">
						<label for="niveisGrupo">Escolha os níveis por grupo</label>
						<select id="niveisGrupo" name="torneio.niveis.id" class="form-control" multiple="multiple" size="5">
							<!-- Percorre cada nível por idade cadastrado no portal -->
							<c:forEach items="${niveisPorGrupo}" var="nivelGrupo">								
									
									<c:set var="isNivelSelecionadoNoTorneio" value="${false}" />  
								
									<!-- Percorre todos os níveis do torneio e verifica se é igual ao nível atual que será mostrado -->
								    <c:forEach items="${torneio.niveis}" var ="nivelDoTorneio">

										<c:if test="${nivelDoTorneio.id == nivelGrupo.id}">
											<c:set var="isNivelSelecionadoNoTorneio" value="${true}" />  
										</c:if>
										
								    </c:forEach>
								    
								    	<!-- Se o nível atual estiver disponibilizado no torneio ele mostra como selecionado -->
										<c:if test = "${isNivelSelecionadoNoTorneio}" >
											<option value="${nivelGrupo.id}" selected="selected">${nivelGrupo.descricao}</option>
										</c:if>
										
	
										<c:if test = "${!isNivelSelecionadoNoTorneio}">
											<option value="${nivelGrupo.id}">${nivelGrupo.descricao}</option>
										</c:if>
								    
							</c:forEach>
						</select> 
				</div>
				

				<div class="form-group col-sm-6">
						<label for="niveisTecnicos">Escolha os níveis técnicos</label>
						<select id="niveisTecnicos" name="torneio.niveis.id" class="form-control" multiple="multiple" size="5">
							
							<!-- Percorre cada nível cadastrado no portal -->
							<c:forEach items="${niveisTecnicos}" var="nivelTecnico">								
									
									<c:set var="isNivelSelecionadoNoTorneio" value="${false}" />  
								
									<!-- Percorre todos os níveis técnicos do torneio e verifica se é igual ao nível atual que será mostrado -->
								    <c:forEach items="${torneio.niveis}" var ="nivelDoTorneio">

										<c:if test="${nivelDoTorneio.id == nivelTecnico.id}">
											<c:set var="isNivelSelecionadoNoTorneio" value="${true}" />  
										</c:if>
										
								    </c:forEach>
								    
								    	<!-- Se o nível atual estiver disponibilizado no torneio ele mostra como selecionado -->
										<c:if test = "${isNivelSelecionadoNoTorneio}" >
											<option value="${nivelTecnico.id}" selected="selected">${nivelTecnico.descricao}</option>
										</c:if>
										
	
										<c:if test = "${!isNivelSelecionadoNoTorneio}">
											<option value="${nivelTecnico.id}">${nivelTecnico.descricao}</option>
										</c:if>
								    
							</c:forEach>
						</select> 
				</div>
			</div>							
			<hr>
			<h4>Premiação</h4>
			<hr>
			
			<div class="row">
				
				<div class="form-group col-sm-3">
					<label for="valorPremiacao">Premiação</label>
					<input type="text" id="valorPremiacao" name="torneio.premiacao.valor" class="form-control" value="<fmt:formatNumber value="${torneio.premiacao.valor}" minFractionDigits="2" />"/>
				</div>
			</div>

			<div class="row">
	
				<div class="checkbox col-sm-12">
						
						<label  class="checkbox inline">
							<input type="checkbox" id="trofeusEMedalhas" 
								name="torneio.premiacao.trofeusEMedalhas"  
								<c:if test="${torneio.premiacao.trofeusEMedalhas}"> checked="checked"</c:if> />Troféus e/ou medalhas
						</label><br><br>
				
						<label  class="checkbox inline">
							<input type="checkbox" id="camisaTorneio" 
								name="torneio.premiacao.camisaTorneio"
								<c:if test="${torneio.premiacao.camisaTorneio}"> checked="checked"</c:if> />Camisa do torneio
						</label><br><br>
						
						<label class="checkbox inline">
							<input type="checkbox" id="sorteioDeBrindes" 
								name="torneio.premiacao.sorteioDeBrindes" 
								<c:if test="${torneio.premiacao.sorteioDeBrindes}"> checked="checked"</c:if>/>Sorteio de brindes
						</label><br><br>
						
						<label class="checkbox inline">
							<input type="checkbox" id="premiosEmProdutos" 
								name="torneio.premiacao.premiosEmProdutos" 
								<c:if test="${torneio.premiacao.premiosEmProdutos}"> checked="checked"</c:if>/>Prêmios em produtos
						</label><br><br>
						
						<label class="checkbox inline">
							<input type="checkbox" id="premiosParaTodasAsClasses" 
								name="torneio.premiacao.premiosParaTodasAsClasses"
								<c:if test="${torneio.premiacao.premiosParaTodasAsClasses}"> checked="checked"</c:if> />Prêmios para todas as classes
						</label><br>
				</div>
				
				
			</div>
			
			
			<hr>
			<h4>Configurações Adicionais</h4>
			<hr>
			<div class="row">
			<div class="checkbox col-sm-12">
						<label  class="checkbox inline">
							<input type="checkbox" id="aberto" 
								name="torneio.aberto" 
								<c:if test="${torneio.aberto}"> checked="checked" </c:if> />Aberto
						</label><br><br>
						
						<label class="checkbox inline">
							<input type="checkbox" id="divulgarTorneio" 
							name="torneio.divulgarTorneio" 
							<c:if test="${torneio.divulgarTorneio}"> checked="checked"</c:if> />Divulgar torneio entre os cadastrados</label><br><br>
						
						<label class="checkbox inline">
							<input type="checkbox" id="rankingDeClube" 
							name="torneio.rankingDeClube" 
							<c:if test="${torneio.rankingDeClube}"> checked="checked"</c:if> />Ranking de Clube</label><br><br>
						
						<label class="checkbox inline">
							<input type="checkbox" id="confraternizacao" 
							name="torneio.confraternizacao" 
							<c:if test="${torneio.confraternizacao}"> checked="checked"</c:if> />Confraternização</label><br><br>
						
						
				</div>
			</div>
			<div class="btn-toolbar " align="center" >
				
				<a href="${linkTo[TorneioController].detalhaTorneio(torneio.id)}"	class="btn col-sm-2 btn btn-default pull-center" >Detalhes do torneio</a> <br>
				
			</div>
			<br>
				<button class="form-group col-sm-12 btn btn-primary pull-center">Alterar dados do torneio</button>
			</form>
			</div>
			
		<c:import url="../menu/rodape.jsp"/>
</div>
</div>
</div>
			<c:import url="../comum/scripts.jsp"/>
			
</body>
</html>