<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${empresa} | Torneios agendados</title>
<c:import url="../comum/links.jsp"/>
</head>
<body>
<div class="body_wrapper">
  <div class="center">
	<c:import url="../menu/menu.jsp"></c:import>	

	<div class="container-fluid">


    <div class="content_area">


      
        <div class="single_left_coloum_wrapper">
            <h2 class="title">Torneios</h2>
        </div>

	<div class="bs-example bs-example-tabs" role="tabpanel" data-example-id="togglable-tabs">
    
	<label>Escolha o ano das competições anteriores:</label>
	<a href="${linkTo[TorneioController].visualizaTorneios(0,2021)}">2021 </a>|
	<a href="${linkTo[TorneioController].visualizaTorneios(0,2020)}">2020 </a>|
	<a href="${linkTo[TorneioController].visualizaTorneios(0,2019)}">2019 </a>|
	<a href="${linkTo[TorneioController].visualizaTorneios(0,2018)}">2018 </a>|
	<a href="${linkTo[TorneioController].visualizaTorneios(0,2017)}">2017 </a>|
	<a href="${linkTo[TorneioController].visualizaTorneios(0,2016)}">2016 </a>|
	<a href="${linkTo[TorneioController].visualizaTorneios(0,2015)}">2015 </a>
	
    <ul id="myTab" class="nav nav-tabs" role="tablist">
      <li role="presentation" class="active"><a href="#proximasCompeticoes" id="proximasCompeticoes-tab" role="tab" data-toggle="tab" aria-controls="proximasCompeticoes" aria-expanded="true">Próximas competições</a></li>
      <li role="presentation"><a href="#competicoesAnteriores" role="tab" id="competicoesAnteriores-tab" data-toggle="tab" aria-controls="competicoesAnteriores">Competições anteriores ${ano}</a></li>
    </ul>
    
    <div id="myTabContent" class="tab-content">
      
      <!--  próximas competições tab -->
      
      <div role="tabpanel" class="tab-pane fade in active" id="proximasCompeticoes" aria-labelledBy="proximasCompeticoes-tab">
      
       	<div class="container-fluid">
			<c:if test="${numeroDeProximosTorneios > 0}">
				
				
					<c:forEach var="torneio" items="${proximosTorneios}">
					 
					  <div class="col-sm-12">
			                    <div class="panel-heading">
			                        <h4><i class="fa fa-fw fa-check"></i>${torneio.nome}</h4>
				                 	<div class="row">			                      
									 <div class="btn-toolbar col-sm-4">
										<a href="${linkTo[TorneioController].detalhaTorneio(torneio.id)}" class="btn btn-primary">Mais informações</a><br><br>
										
										<!--  inscrições abertas ou ainda não inicializadas -->
				                        <c:if test="${(torneio.dataInicioInscricao.time <= dataDeHoje) && (torneio.dataFimInscricao.time >= dataDeOntem) && torneio.situacao.id == 1  && torneio.chaveGerada == false && torneio.aberto == true}">
									
											<c:if test="${torneio.simples}">
											<a href="${linkTo[InscricaoController].formInscreveSeEmTorneio(torneio.id)}"
											class="btn btn-success right">Inscrever-se</a>
											</c:if>
											
											<c:if test="${torneio.duplas}">
											<a href="${linkTo[InscricaoController].formInscreveSeEmTorneioDeDuplas(torneio.id)}"
											class="btn btn-success right">Inscrever-se</a>
											</c:if>

											<c:if test="${torneio.simplesEDuplas}">
											<a href="${linkTo[InscricaoController].formInscreveSeEmTorneioDeSimplesEDuplas(torneio.id)}"
											class="btn btn-success right">Inscrever-se</a>
											</c:if>
											
									
										</c:if>
									</div>	
									</div>
			                    </div>
			                    
			                    <div class="panel-body">
			                     <div class="col-sm-4 ">
			                        <p>
			                        <label>Início das inscrições:</label><span> <fmt:formatDate pattern="dd/MM/yyyy" value="${torneio.dataInicioInscricao.time}"/></span><br>
			                        <label>Fim das inscrições:</label><span> <fmt:formatDate pattern="dd/MM/yyyy" value="${torneio.dataFimInscricao.time}"/></span><br>
			                        <label>Início do torneio:</label><span> <fmt:formatDate pattern="dd/MM/yyyy" value="${torneio.dataInicio.time}"/></span><br>
			                        <label>Fim do torneio:</label><span> <fmt:formatDate pattern="dd/MM/yyyy" value="${torneio.dataFim.time}"/></span><br>
			                        </p>
			                      </div>

			                     <div class="col-sm-4">
			                        <p>
			                        <label>Valor (primeira inscrição):</label><span style="color:green"> <fmt:formatNumber type="currency" currencySymbol="R$" currencyCode="BRL" value="${torneio.valorInscricao}"/></span><br>
			                        <label>Valor (segunda inscrição):</label> <span style="color:green"> <fmt:formatNumber type="currency" currencySymbol="R$" currencyCode="BRL" value="${torneio.valorSegundaInscricao}"/></span><br>
			                        <label>Valor (terceira inscrição):</label> <span style="color:green"> <fmt:formatNumber type="currency" currencySymbol="R$" currencyCode="BRL" value="${torneio.valorTerceiraInscricao}"/></span><br>
			                        </p>

			                      	<c:if test="${((torneio.dataInicioInscricao.time <= dataDeHoje) && (torneio.dataFimInscricao.time >= dataDeOntem) && torneio.situacao.id == 1) || torneio.situacao.id == 2}">
				                        <c:if test="${tenistaLogado == null}">
											<h5 style="color:green">Para efetuar o pagamento e a inscrição acesse a sua conta.</h5>
										</c:if>
									</c:if>	
			                        
			                      </div>
			                      

			                     <div class="col-sm-4">

			                        <label>Categoria:</label><span> ${torneio.categoria.nome}</span><br>
			                        <label>Premiação:</label><span style="color:green"> <fmt:formatNumber type="currency" currencySymbol="R$" currencyCode="BRL" value="${torneio.premiacao.valor}"/></span><br>
			                        <label>Local:</label><span> ${torneio.local.nome}</span><br>
			                        <label>Situação:</label><span> ${torneio.situacao.nome}</span><br>
			                        
			                        </div>

								
					                        
									
							    </div>
							   </div>					 
					</c:forEach>				
				</c:if>
				
				<c:if test="${numeroDeProximosTorneios == 0}">
					<div align="center">
					<small class="text-info">Ainda não temos próximos torneios registrados no sistema.</small>
					</div>
				</c:if>
				
				
				</div>
	      
     	 </div>
      
       <!--  competições anteriores tab -->
      
	    <div role="tabpanel" class="tab-pane fade" id="competicoesAnteriores" aria-labelledBy="competicoesAnteriores-tab">
		<div class="container-fluid">
		<div class="row">
		<br/>
			<h4>Competições anteriores: ${ano}</h4>
		</div>	
			
		<c:if test="${numeroDeTorneiosAnteriores> 0}">
		
		
			<c:forEach var="torneio" items="${torneiosAnteriores}">
			 
			 <div class="col-md-6">
	                <div class="container">
	                    <div class="panel-heading">
	                        <h5><i class="fa fa-fw fa-check"></i>${torneio.nome}</h5>
	                    </div>
	                    <div class="panel-body">
	                    
	                        <p>
	                        <label>Início das inscrições:</label><span> <fmt:formatDate pattern="dd/MM/yyyy" value="${torneio.dataInicioInscricao.time}"/></span><br>
	                        <label>Local:</label><span> ${torneio.local.nome}</span><br>
	                        <label>Início do torneio:</label><span> <fmt:formatDate pattern="dd/MM/yyyy" value="${torneio.dataInicio.time}"/></span><br>
	                        <label>Fim do torneio:</label><span> <fmt:formatDate pattern="dd/MM/yyyy" value="${torneio.dataFim.time}"/></span><br>
	                        <label>Situação:</label><span> ${torneio.situacao.nome}</span><br>
	                        
	                        </p>
							<a href="${linkTo[TorneioController].detalhaTorneio(torneio.id)}" class="btn btn-primary">Mais informações</a>
					    </div>
	                </div>
	            </div>
				
			</c:forEach>
		
		</c:if>
		
		<c:if test="${numeroDeTorneiosAnteriores == 0 }">
			<div align="center">
			<small class="text-info">Nenhum torneio foi concluído ou cancelado no sistema.</small>
			</div>
		</c:if>
		</div>
		</div>
	      
      
    </div>
  </div><!-- /example -->
</div>

				<c:import url="../menu/rodape.jsp"/>

</div>
</div>
</div>
			    <c:import url="../comum/scripts.jsp"/>
	
	
</body>
</html>