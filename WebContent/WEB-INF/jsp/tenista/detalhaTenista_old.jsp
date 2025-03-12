<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
<meta charset="iso-8859-1">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>${empresa} | Detalhes do ${esportista}</title>
<c:import url="../comum/links.jsp"/>
</head>
<body>

<c:import url="../menu/menu.jsp"></c:import>

<section>
<div class="container-fluid">
	
	<!-- Lateral esquerda -->
   <div class="col-lg-2 ">
  	 <c:import url="../menu/lateral_esquerda.jsp"/>
   </div>
  	<!-- termino da lateral esquerda -->

	<!-- meio da página -->
	<div class="col-lg-8">
          <hr>

	<h3>${tenistaDetalhado.nomeCompleto}

	<c:if test="${trofeus1 > 0 }">
		<c:forEach begin="1" end="${trofeus1}">
			<img src="/sigete/imagens/trofeu_ouro.png" alt="Troféu de 1º lugar " width="30" height="30">
		</c:forEach>
	</c:if>
	<c:if test="${trofeus2 > 0 }">
	<c:forEach begin="1" end="${trofeus2}">
		<img src="/sigete/imagens/trofeu_prata.png"  alt="Troféu de 2º lugar " width="30" height="30">
	</c:forEach>
	</c:if>
	</h3>
	
	
  	<hr>
  	
  	<div class="bs-example bs-example-tabs" role="tabpanel" data-example-id="togglable-tabs">
    
    <ul id="myTab" class="nav nav-tabs" role="tablist">
      <li role="presentation" class="active"><a href="#informacoesTecnicas" id="informacoesTecnicas-tab" role="tab" data-toggle="tab" aria-controls="informacoesTecnicas">Informações técnicas</a></li>
      <li role="presentation"><a href="#pontuacoes" role="tab" id="pontuacoes-tab" data-toggle="tab" aria-controls="pontuacoes">Pontuações</a></li>
      <li role="presentation"><a href="#inscricoesEmTorneios" id="inscricoesEmTorneios-tab" role="tab" data-toggle="tab" aria-controls="inscricoesEmTorneios">Inscrições em torneios</a></li>
      <li role="presentation"><a href="#torneiosOrganizados" role="tab" id="torneiosOrganizados-tab" data-toggle="tab" aria-controls="torneiosOrganizados">Torneios Organizados</a></li>
      <li role="presentation"><a href="#jogos" role="tab" id="jogos-tab" data-toggle="tab" aria-controls="jogos">Jogos</a></li>
    </ul>
    
    <div id="myTabContent" class="tab-content">
	
	
	<input type="hidden" value="${torneio.id }" name= "torneio.id" /><br>
	
	<!-- informações do torneio -->
		
		<div role="tabpanel" class="tab-pane fade in active" id="informacoesTecnicas" aria-labelledBy="informacoesTecnicas-tab">
			<div class="container-fluid">
				<c:import url="../comum/dadosBasicosTenista.jsp"/>
			</div>
		
		</div>
		
		<div role="tabpanel" class="tab-pane fade" id="pontuacoes" aria-labelledBy="pontuacoes-tab">
			<div class="container-fluid">
				<c:import url="../comum/pontuacoesTenista.jsp"/>
			</div>
		</div>

		<div role="tabpanel" class="tab-pane fade" id="inscricoesEmTorneios" aria-labelledBy="inscricoesEmTorneios-tab">
			<div class="container-fluid">
				<c:import url="../comum/inscricoesTenista.jsp"/>
			</div>
		</div>
		
		<div role="tabpanel" class="tab-pane fade" id="torneiosOrganizados" aria-labelledBy="torneiosOrganizados-tab">
			<div class="container-fluid">
				<c:import url="../comum/torneiosTenista.jsp"/>
			</div>
		</div>
		
		<div role="tabpanel" class="tab-pane fade" id="jogos" aria-labelledBy="jogos-tab">
			<div class="container-fluid">
				<c:import url="../comum/jogos.jsp"/>
			</div>
		</div>
	
		</div>
			<div class="btn-toolbar " align="center">
				<a href="${linkTo[TenistaController].formProcuraTenistas}" class="btn btn-primary">Procurar Tenistas</a>
			</div>		
		</div>
		
		</div>
		<!-- Lateral direita-->
		      <div class="col-lg-2">
		        <c:import url="../menu/lateral_direita.jsp"/>
		   	  </div>
    	<!-- termino da lateral direita -->
		</div>
</section>

		<c:import url="../menu/rodape.jsp"/>
		<c:import url="../comum/scripts.jsp"/>
</body>
</html>