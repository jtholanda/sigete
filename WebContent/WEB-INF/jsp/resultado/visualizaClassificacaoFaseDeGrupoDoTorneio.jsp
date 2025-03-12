<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


	
	<h4>Resultado ${nivel.descricao}</h4><br/>
	<table class="table table-striped">
		<tr>
			<th>Nome do ${esportista}</th>
			<th>Posição</th>
			
			 
		</tr>
		
		<c:forEach var="inscricao" items="${inscricoes}">
		 
			<tr>
				<td>
				<c:if test="${inscricao.tenista.filiado}">
				<c:import url="../comum/iconeFiliado.jsp"></c:import> </c:if>
				${inscricao.tenista.nomeCompleto}
				<c:if test="${nivel.isDuplas() }">
					<br><c:if test="${inscricao.tenistaParceiro.filiado}">
					<c:import url="../comum/iconeFiliado.jsp"></c:import> </c:if>
					 ${inscricao.tenistaParceiro.nomeCompleto}
				</c:if>
				</td>
				<td>${inscricao.numeroCabecaDeChave2Estagio}</td>
			</tr>
		
		</c:forEach>
		
	</table>
	
	<c:if test="${gruposDoNivelNoTorneio != null && gruposDoNivelNoTorneio.size() > 0}">

		<c:import url="../comum/botoesGruposTorneio.jsp"/>
	
	
	</c:if>
	
	
