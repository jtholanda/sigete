<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h5>Inscri��es do tenista</h5><br>

	<table class="table table-hover">
		<tr>
			<th>Torneio</th>
			<th>N�vel t�cnico da inscri��o</th>
			<th>Ano</th>
			<th>Pontua��o obtida</th>
			<th>Coloca��o</th>
			<th></th>
			
			
			 
		</tr>
		
		<c:forEach var="inscricao" items="${inscricoes}">
		 
			<tr>
				<td>${inscricao.torneio.nome}</td>
				
				<td>${inscricao.nivel.descricao}</td>
				
				<td><fmt:formatDate pattern="yyyy" value="${inscricao.data.time}"/></td>
				
				<c:if test="${inscricao.pontuacao eq null}">
					<td>Sem pontua��o</td>
				</c:if>
				<c:if test="${inscricao.pontuacao ne null}">
					<td>${inscricao.pontuacao}</td>
				</c:if>
				
				<c:if test="${inscricao.colocacao.descricao eq null}">
					<td>N�o informado</td>
				</c:if>
				<c:if test="${inscricao.colocacao.descricao ne null}">
					<td>${inscricao.colocacao.descricao}</td>
				</c:if>
				
				<td><c:if test="${inscricao.colocacao.id == 1}">
					<img src="/${contexto}/imagens/trofeu_ouro.png" class="img-responsive" alt="Trof�u de 1� lugar " width="15" height="15">
				</c:if>
				<c:if test="${inscricao.colocacao.id == 2}">
					<img src="/${contexto}/imagens/trofeu_prata.png" class="img-responsive" alt="Trof�u de 2� lugar" width="15" height="15">
				</c:if>
				</td>
			</tr>
		
		</c:forEach>
		
	</table>
	