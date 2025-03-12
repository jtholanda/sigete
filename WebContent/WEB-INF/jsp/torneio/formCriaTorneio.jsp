<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${empresa} | Criação de torneios</title>
<c:import url="../comum/links.jsp"/>
</head>
<body>
<div class="body_wrapper">
  <div class="center">
	<c:import url="../menu/menu.jsp"></c:import>	

	<div class="container-fluid">


    <div class="content_area">


      
        <div class="single_left_coloum_wrapper">
            <h2 class="title">Novo Torneio</h2>
        </div>

	<c:import url="../comum/errors.jsp"/>
		
			<form action="${linkTo[TorneioController].criaTorneio}" method="POST" >
			
			<hr>
			<h4>Informações básicas</h4>
			<hr>	
				<div class="row">
					<div class="form-group col-sm-6">
						<label for="nome">Nome do torneio *</label>
						<input type="text" id="nome" name="torneio.nome" class="form-control" value="${torneio.nome}" required="required"/>
					</div>
				</div>
				<div class="row">
					<div class="form-group col-sm-6">
					<label for="organizador">Auxiliar 1 *</label>
					<select id="organizador" name="torneio.tenistaAuxiliar1.id" class="form-control">
			
						<option value="0">Escolha uma opção</option>
						
						<c:forEach items="${organizadores}" var="auxiliar">
								<option value="${auxiliar.id}">${auxiliar.nomeRanking}</option>
						</c:forEach>
					</select> 
				</div>
					<div class="form-group col-sm-6">
					<label for="organizador">Auxiliar 2 *</label>
					<select id="organizador" name="torneio.tenistaAuxiliar2.id" class="form-control">

						<option value="0">Escolha uma opção</option>
						
						<c:forEach items="${organizadores}" var="auxiliar">
								<option value="${auxiliar.id}">${auxiliar.nomeRanking}</option>
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
						<label for="horario">Horário *</label>
						<input type="text" id="horario" name="torneio.horario" class="form-control" value="${torneio.horario}" required="required"/>
					</div>
					
				</div>
				
				<div class="row">
					<div class="form-group col-sm-3">
						<label for="local">Local principal *</label>
						<select id="local" name="torneio.local.id" class="form-control">
							<c:forEach items="${locais}" var="local">								
								<option value="${local.id}">${local.nome}</option>
							</c:forEach>
						</select> 
					</div>
					
					<div class="form-group col-sm-3">
						<label for="categoria">Categoria *</label>
						<select id="categoria" name="torneio.categoria" class="form-control">
		  					<c:forEach items="${categorias}" var="categoria">								
								<option value="${categoria}">${categoria.nome}</option>
							</c:forEach>
						</select> 
					</div>								
					<div class="form-group col-sm-2">
						<label for="tipoDeChave">Tipo de Chave *</label>
						<select id="tipoDeChave" name="torneio.tipoDeChave" class="form-control">
		  					<c:forEach items="${tiposDeChaves}" var="tipoDeChave">								
								<option value="${tipoDeChave}">${tipoDeChave.nome}</option>
							</c:forEach>
						</select> 
					</div>
					<div class="form-group col-sm-2">
						<label for="numeroDeEstagios">Estágios</label>
						<select id="numeroDeEstagios" name="torneio.numeroDeEstagios" class="form-control">
								<option value="1" selected="selected">1</option>
								<option value="2">2</option>
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
					</div>
						
					<div class="form-group col-sm-5">
						<label for="dataFimInscricao">Fim das inscrições *</label>
						<input type="text" id="dataFimInscricao" name="torneio.dataFimInscricao" class="form-control" required="required"
						 value="<fmt:formatDate pattern="dd/MM/yyyy" value="${torneio.dataFimInscricao.time}"/>" />
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
						<label for="valorInscricao">Valor da primeira inscrição *</label>
						<span class="input-group-addon">R$</span>
						<input type="text" id="valorInscricao" name="torneio.valorInscricao" class="form-control" value="${torneio.valorInscricao}" required="required"/>
						<!--  <span class="input-group-addon">,00</span>-->
					</div>
					
					<div class="form-group col-sm-3">
						<label for="valorInscricaoFiliado">Valor da inscrição do Filiado *</label>
						<span class="input-group-addon">R$</span>
						<input type="text" id="valorInscricaoFiliado" name="torneio.valorInscricaoFiliado" class="form-control" value="${torneio.valorInscricaoFiliado}" required="required"/>
						<!--  <span class="input-group-addon">,00</span>-->
					</div>

					<div class="form-group col-sm-3">
						<label for="valorSegundaInscricao">Valor da segunda inscrição *</label>
						<span class="input-group-addon">R$</span>
						<input type="text" id="valorSegundaInscricao" name="torneio.valorSegundaInscricao" class="form-control" value="${torneio.valorSegundaInscricao}" required="required"/>
						<!--  <span class="input-group-addon">,00</span>-->
					</div>

					<div class="form-group col-sm-3">
						<label for="valorTerceiraInscricao">Valor da terceira inscrição *</label>
						<span class="input-group-addon">R$</span>
						<input type="text" id="valorTerceiraInscricao" name="torneio.valorTerceiraInscricao" class="form-control" value="${torneio.valorTerceiraInscricao}"/>
						<!--  <span class="input-group-addon">,00</span>-->
					</div>

				</div>

				<div class="row">

					<div class="form-group col-sm-3">	
						<label for="botaoPagamento">Botão de pagamento do pagseguro para 1 inscrição</label> 
						<textarea id="botaoPagamento" name="torneio.botaoPagamento" 
							class="form-control" rows="3">${torneio.botaoPagamento}</textarea>
					</div>				

					<div class="form-group col-sm-3">	
						<label for="botaoPagamentoFiliado">Botão de pagamento do pagseguro para filiado</label> 
						<textarea id="botaoPagamentoFiliado" name="torneio.botaoPagamentoFiliado" 
							class="form-control" rows="3">${torneio.botaoPagamentoFiliado}</textarea>
					</div>				
				
					<div class="form-group col-sm-3">	
						<label for="botaoSegundoPagamento">Botão de pagamento do pagseguro para 2 inscrições</label> 
						<textarea id="botaoSegundoPagamento" name="torneio.botaoSegundoPagamento" 
							class="form-control" rows="3">${torneio.botaoSegundoPagamento}</textarea>
					</div>				

					<div class="form-group col-sm-3">	
						<label for="botaoTerceiroPagamento">Botão de pagamento do pagseguro para 3 inscrições</label> 
						<textarea id="botaoTerceiroPagamento" name="torneio.botaoTerceiroPagamento" 
							class="form-control" rows="3">${torneio.botaoTerceiroPagamento}</textarea>
					</div>				

						
				</div>
				
				
				<div class="row">				
					<div class="form-group col-sm-12">	
						<label for="informacoesPagamento">Informações de pagamento *</label> 
						<textarea id="informacoesPagamento" name="torneio.informacoesPagamento" 
							class="form-control" rows="3" required="required">${torneio.informacoesPagamento}</textarea>
					</div>				
				</div>
			
			<h4>Níveis disponibilizados para inscrição - Beach Tennis (segure a tecla Ctrl e selecione)</h4>
			<hr>
			<div class="row">


				<div class="form-group col-sm-6">
						<label for="niveisTecnicosMasculinos">Escolha os níveis técnicos masculinos</label>
						<select id="niveisTecnicosMasculinos" name="torneio.niveis.id" class="form-control" multiple="multiple" size="5">
							<c:forEach items="${niveisTecnicosMasculinoBT}" var="nivelTecnicoMasculino">								
								<option value="${nivelTecnicoMasculino.id}">${nivelTecnicoMasculino.descricao}</option>
							</c:forEach>
						</select> 
				</div>

				<div class="form-group col-sm-6">
						<label for="niveisTecnicosFemininos">Escolha os níveis técnicos femininos</label>
						<select id="niveisTecnicosFemininos" name="torneio.niveis.id" class="form-control" multiple="multiple" size="5">
							<c:forEach items="${niveisTecnicosFemininoBT}" var="nivelTecnicoFeminino">								
								<option value="${nivelTecnicoFeminino.id}">${nivelTecnicoFeminino.descricao}</option>
							</c:forEach>
						</select> 
				</div>
				
				<div class="form-group col-sm-6">
						<label for="niveisPorIdade">Escolha os níveis por idade</label>
						<select id="niveisPorIdade" name="torneio.niveis.id" class="form-control" multiple="multiple" size="5">
							<c:forEach items="${niveisPorIdadeBT}" var="nivelIdade">								
								<option value="${nivelIdade.id}">${nivelIdade.descricao}</option>
							</c:forEach>
						</select> 
				</div>

				<div class="form-group col-sm-6">
						<label for="niveisPorGrupo">Escolha os níveis por grupo</label>
						<select id="niveisPorGrupo" name="torneio.niveis.id" class="form-control" multiple="multiple" size="5">
							<c:forEach items="${niveisPorGrupoBT}" var="nivelGrupo">								
								<option value="${nivelGrupo.id}">${nivelGrupo.descricao}</option>
							</c:forEach>
						</select> 
				</div>

				<div class="form-group col-sm-6">
						<label for="niveisPorDuplas">Escolha os níveis de duplas</label>
						<select id="niveisPorDuplas" name="torneio.niveis.id" class="form-control" multiple="multiple" size="5">
							<c:forEach items="${niveisPorDuplasBT}" var="nivelDupla">								
								<option value="${nivelDupla.id}">${nivelDupla.descricao}</option>
							</c:forEach>
						</select> 
				</div>
				
				<div class="form-group col-sm-6">
						<label for="niveisTecnicos">Escolha os níveis técnicos gerais</label>
						<select id="niveisTecnicos" name="torneio.niveis.id" class="form-control" multiple="multiple" size="5">
							<c:forEach items="${niveisTecnicosBT}" var="nivelTecnico">								
								<option value="${nivelTecnico.id}">${nivelTecnico.descricao}</option>
							</c:forEach>
						</select> 
				</div>
				
			</div>
			
			
			
			<hr>
			<h4>Níveis técnicos disponibilizados para inscrição - Tênis  (segure a tecla Ctrl e selecione)</h4>
			<hr>
			<div class="row">

				<div class="form-group col-sm-6">
						<label for="niveisTecnicosMasculinos">Escolha os níveis técnicos simples masculino</label>
						<select id="niveisTecnicosMasculinos" name="torneio.niveis.id" class="form-control" multiple="multiple" size="5">
							<c:forEach items="${niveisTecnicosMasculinoTenis}" var="nivelTecnico">								
								<option value="${nivelTecnico.id}">${nivelTecnico.descricao}</option>
							</c:forEach>
						</select> 
				</div>



				<div class="form-group col-sm-6">
						<label for="niveisTecnicosFemininos">Escolha os níveis técnicos simples feminino</label>
						<select id="niveisTecnicosFemininos" name="torneio.niveis.id" class="form-control" multiple="multiple" size="5">
							<c:forEach items="${niveisTecnicosFemininoTenis}" var="nivelTecnicoFeminino">								
								<option value="${nivelTecnicoFeminino.id}">${nivelTecnicoFeminino.descricao}</option>
							</c:forEach>
						</select> 
				</div>
				
			</div>
				
			<div class="row">

				<div class="form-group col-sm-6">
						<label for="niveisPorDuplas">Escolha os níveis de duplas</label>
						<select id="niveisPorDuplas" name="torneio.niveis.id" class="form-control" multiple="multiple" size="5">
							<c:forEach items="${niveisPorDuplasTenis}" var="nivelDupla">								
								<option value="${nivelDupla.id}">${nivelDupla.descricao}</option>
							</c:forEach>
						</select> 
				</div>
				
				<div class="form-group col-sm-6">
						<label for="niveisPorIdade">Escolha os níveis por idade</label>
						<select id="niveisPorIdade" name="torneio.niveis.id" class="form-control" multiple="multiple" size="5">
							<c:forEach items="${niveisPorIdadeTenis}" var="nivelIdade">								
								<option value="${nivelIdade.id}">${nivelIdade.descricao}</option>
							</c:forEach>
						</select> 
				</div>
			</div>

			<div class="row">

				<div class="form-group col-sm-6">
						<label for="niveisPorGrupo">Escolha os níveis por grupo</label>
						<select id="niveisPorGrupo" name="torneio.niveis.id" class="form-control" multiple="multiple" size="5">
							<c:forEach items="${niveisPorGrupoTenis}" var="nivelGrupo">								
								<option value="${nivelGrupo.id}">${nivelGrupo.descricao}</option>
							</c:forEach>
						</select> 
				</div>


				<div class="form-group col-sm-6">
						<label for="niveisTecnicos">Escolha os níveis técnicos gerais</label>
						<select id="niveisTecnicos" name="torneio.niveis.id" class="form-control" multiple="multiple" size="5">
							<c:forEach items="${niveisTecnicosTenis}" var="nivelTecnico">								
								<option value="${nivelTecnico.id}">${nivelTecnico.descricao}</option>
							</c:forEach>
						</select> 
				</div>
			</div>
				
			
			<hr>
			<h4>Premiação</h4>
			<hr>
			<div class="row">
				
				<div class="form-group col-sm-3">
					<label for="valorPremiacao">Valor em R$</label>
					<input type="text" id="valorPremiacao" name="torneio.premiacao.valor" class="form-control" value="${torneio.premiacao.valor}"/>
				</div>
			</div>
			<div class="row">

				<div class="checkbox col-sm-12">
						<label  class="checkbox inline">
							<input type="checkbox" id="trofeusEMedalhas" 
								name="torneio.premiacao.trofeusEMedalhas" />Troféus e/ou medalhas
						</label> <br><br>
						<label  class="checkbox inline">
							<input type="checkbox" id="camisaTorneio" 
								name="torneio.premiacao.camisaTorneio" />Camisa do torneio
						</label><br><br>
						<label class="checkbox inline">
							<input type="checkbox" id="sorteioDeBrindes" 
								name="torneio.premiacao.sorteioDeBrindes" />Sorteio de brindes
						</label><br><br>
						<label class="checkbox inline">
							<input type="checkbox" id="premiosEmProdutos" 
								name="torneio.premiacao.premiosEmProdutos" />Prêmios em produtos
						</label><br><br>
						<label class="checkbox inline">
							<input type="checkbox" id="premiosParaTodasAsClasses" 
								name="torneio.premiacao.premiosParaTodasAsClasses" />Prêmios para todas as classes
						</label><br><br>
				</div>
				
				
			</div>
			
			<hr>
			<h4>Opções Adicionais</h4>
			<hr>
			<div class="row">
			<div class="checkbox col-sm-12">
						<label  class="checkbox inline">
							<input type="checkbox" id="aberto" 
								name="torneio.aberto" checked="checked" />Aberto
						</label><br><br>
						
						<label class="checkbox inline">
							<input type="checkbox" id="divulgarTorneio" 
							name="torneio.divulgarTorneio" checked="checked"/>Divulgar torneio entre os cadastrados</label> <br><br>
					
						<label class="checkbox inline">
							<input type="checkbox" id="rankingDeClube" 
							name="torneio.divulgarTorneio"/>Ranking de Clube</label><br><br>
						<label class="checkbox inline">
							<input type="checkbox" id="confraternizacao" 
							name="torneio.confraternizacao"/>Confraternização</label><br><br>							
			</div>
			</div>
			
			<div class="btn-toolbar" align="center">
					<button class="form-group col-sm-12 btn btn-primary pull-center">Criar torneio</button>
			</div>
			<small>* O tipo do torneio e a pontuação podem ser atribuídas posteriormente assim que as inscrições estiverem fechadas.</small>
			
			</form>
			
		</div>
			<c:import url="../menu/rodape.jsp"/>

</div>
</div>
</div>

			<c:import url="../comum/scripts.jsp"/>
			
</body>
</html>