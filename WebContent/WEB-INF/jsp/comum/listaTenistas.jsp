<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

	<div class="row" align="center">
	<div class="col-lg-6" align="right">	
	<c:if test="${anterior > 0}">
	    <form action="${linkTo[TenistaController].buscaTenistas}" method="POST">
	    	<input type="hidden" value="${nomeTenista}" name="nome">
	    	<input type="hidden" value="${nivelTenista}" name="idNivel">
	    	<input type="hidden" value="${anterior}" name="numero">
	    	<button class="btn btn-primary previous">&laquo; Página Anterior</button>
	    </form>
	</c:if>
	</div>
	<div class="col-lg-6" align="left">	
	<c:if test="${numeroDeTenistas >= numeroElementosMaximo}">
	   <form action="${linkTo[TenistaController].buscaTenistas}" method="POST">
	    	<input type="hidden" value="${nomeTenista}" name="nome">
	    	<input type="hidden" value="${nivelTenista}" name="idNivel">
	    	<input type="hidden" value="${proximo}" name="numero">
	    	<button class="btn btn-primary next">Próxima Página &raquo;</button>
	    </form>	
	   </c:if>
   </div>
	<div class="col-lg-3">
	<label>${numeroDeTenistas} Tenista(s) na página - Máximo por página: ${numeroElementosMaximo}</label>
	</div>	
	</div>
	<br/>
	
	<table class="table table-hover">

		<tr>
			<th>Nome</th>
			<th>Nível técnico atual do tenista</th>
			<th>Pontuação no nível técnico principal</th>
			<th>Nível técnico atual no beach tennis</th>
			<th>Pontuação no beach tennis</th>
			<c:if test="${administrador}"><th>Editar</th></c:if>
			
			 
		</tr>
		<c:forEach var="tenista" items="${tenistasEncontrados}">
			<tr>
				<td><a href="${linkTo[TenistaController].detalhaTenista(tenista.id,1)}">${tenista.nomeCompleto}</a>
						<c:if test="${tenista.bolsista}"><img alt="Boleiro" src="/${esportista}/imagens/bolinha.png"/> </c:if>
						<c:if test="${tenista.filiado}"> <c:import url="../comum/iconeFiliado.jsp"></c:import></c:if>
				</td>
				<td>${tenista.nivelTecnicoPrincipal.descricao}</td>
				<td>${tenista.pontosCategoriaPrincipal}</td>
				<td>${tenista.nivelTecnicoBeachTennis.descricao}</td>
				<td>${tenista.pontosCategoriaBeachTennis}</td>
				<c:if test="${administrador}">
				<td><a class="btn btn-info" href="${linkTo[TenistaController].formEditaTenista(tenista.id)}">Editar dados</a></td>
				</c:if>				
			</tr>
		</c:forEach>
		
	</table>
			
		

		<div class="btn-toolbar" align="center">
		<c:if test="${tenistaLogado !=null }">
			<a class="btn btn-primary"  href="${linkTo[IndexController].paginaInicial}">Menu principal</a>
		</c:if>
			<input class="btn btn-primary " type="button"
							onClick="javascript:history.back(1)" value="Voltar" />
		</div>