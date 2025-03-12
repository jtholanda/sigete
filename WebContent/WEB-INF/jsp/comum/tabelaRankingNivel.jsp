<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>



	<table class="table table-hover">

		<tr>
			<th>Colocação</th>
			<th>Nome</th>
			<th>Nível técnico atual do tenista</th>
			<th>Pontuação</th>
			
			 
		</tr>
		<c:forEach var="pontuacao" items="${pontosDosTenistasRankeados}" varStatus="posicao">
		 
			<tr>
			
				<c:if test="${pontuacao.pontos == pontosAnterior}">
					<td>${posicaoRepetida } º</td>
				</c:if>

				<c:if test="${!(pontuacao.pontos == pontosAnterior)}">
					<td>${posicao.count} º</td>
					<c:set var="posicaoRepetida"  value="${posicao.count}"></c:set>
					
				</c:if>
				
				<td><a href="${linkTo[TenistaController].detalhaTenista(pontuacao.tenista.id,1)}">${fn:toUpperCase(pontuacao.tenista.nomeCompleto)}</a>
				
				</td>
				<td>
				<c:if test="${pontuacao.tenista.nivelTecnicoPrincipal.codigo < nivel.codigo && categoria == 1}">
					Subiu para: 
				</c:if>
				
					${pontuacao.tenista.nivelTecnicoPrincipal.descricao}
				
				</td>
				<td>${pontuacao.pontos}</td>
				
				<c:set var="pontosAnterior"  value="${pontuacao.pontos }"></c:set>
				
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