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
<div class="body_wrapper">
  <div class="center">

<c:import url="../menu/menu.jsp"></c:import>

<div class="container-fluid">
    <div class="content_area">
	

	
    <div class="single_left_coloum_wrapper">
            <h2 class="title">O ${esportista}</h2>
        </div>
	<h4><c:if test="${filiado}">
			<img src="/sigete/imagens/simbolo_filiado.jpg" alt="Tenista Filiado" width="30" height="30">
	</c:if>${tenistaDetalhado.nomeCompleto}

	
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
	</h4>
  	<hr>
  	<div class="btn-toolbar " align="center">
			<c:import url="../comum/botoesDetalhaTenista.jsp"/>
		</div>		
		<c:if test="${acao == 1}">
			<c:import url="../comum/dadosBasicosTenista.jsp"/>
		</c:if>
		
		<c:if test="${acao == 2}">
			<c:import url="../comum/pontuacoesTenista.jsp"/>
		</c:if>	
		
		<c:if test="${acao == 3}">
			<c:import url="../comum/inscricoesTenista.jsp"/>
		</c:if>	
		
		<c:if test="${acao == 4}">
			<c:import url="../comum/torneiosTenista.jsp"/>
		</c:if>	
		
		<c:if test="${acao == 5}">
			<c:import url="../comum/jogos.jsp"/>
		</c:if>


		
	</div>
		

	</div>

		<c:import url="../menu/rodape.jsp"/>
</div>
</div>
		<c:import url="../comum/scripts.jsp"/>
</body>
</html>