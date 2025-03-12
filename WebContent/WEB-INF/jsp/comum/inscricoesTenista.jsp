<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h5>Inscrições do tenista</h5><br>

	<table class="table table-hover">
		<tr>
			<th>Torneio</th>
			<th>Nível técnico da inscrição</th>
			<th>Ano</th>
			<th>Pontuação obtida</th>
			<th>Colocação</th>
			<th></th>
			
			
			 
		</tr>
		
		<c:forEach var="inscricao" items="${inscricoes}">
		 
			<tr>
				<td>${inscricao.torneio.nome}</td>
				
				<td>${inscricao.nivel.descricao}</td>
				
				<td><fmt:formatDate pattern="yyyy" value="${inscricao.data.time}"/></td>
				
				<c:if test="${inscricao.pontuacao eq null}">
					<td>Sem pontuação</td>
				</c:if>
				<c:if test="${inscricao.pontuacao ne null}">
					<td>${inscricao.pontuacao}</td>
				</c:if>
				
				<c:if test="${inscricao.colocacao.descricao eq null}">
					<td>Não informado</td>
				</c:if>
				<c:if test="${inscricao.colocacao.descricao ne null}">
					<td>${inscricao.colocacao.descricao}</td>
				</c:if>
				
				<td><c:if test="${inscricao.colocacao.id == 1}">
					<img src="/${contexto}/imagens/trofeu_ouro.png" class="img-responsive" alt="Troféu de 1º lugar " width="15" height="15">
				</c:if>
				<c:if test="${inscricao.colocacao.id == 2}">
					<img src="/${contexto}/imagens/trofeu_prata.png" class="img-responsive" alt="Troféu de 2º lugar" width="15" height="15">
				</c:if>
				</td>
			</tr>
		
		</c:forEach>
		
	</table>
	