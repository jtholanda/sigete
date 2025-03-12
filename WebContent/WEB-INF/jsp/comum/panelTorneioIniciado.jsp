<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table class="table table-striped table-condensed table-hover">
		<tr>
			<th>Nome</th> 
			<th>Nível técnico da inscrição</th>
			<th>Colocação e pontuação do ${esportista} no torneio</th>
			
			
			
		</tr>
		
		<c:forEach var="inscricao" items="${inscricoes}">
		 
		 	
				<tr>
				<td><c:if test="${inscricao.tenista.filiado}"><img alt="Filiado" src="/${contexto}/imagens/simbolo_filiado.jpg"  width="30" height="30"/> </c:if>
				${inscricao.tenista.nomeRanking}
				<c:if test="${torneio.duplas }">
					<br><c:if test="${inscricao.tenistaParceiro.filiado}"><img alt="Filiado" src="/${contexto}/imagens/simbolo_filiado.jpg"  width="30" height="30"/> </c:if>
					 ${inscricao.tenistaParceiro.nomeRanking}
				</c:if>
					<c:if test = "${inscricao.horarioEspecial != null}">
						<small><a href="${linkTo[InscricaoController].formEditaInscricao(inscricao.id)}" class="text text-danger">Horário especial</a></small>
					</c:if>
					
					<c:if test="${inscricao.horarioEspecial == null}">
						<small><a href="${linkTo[InscricaoController].formEditaInscricao(inscricao.id)}">Horário especial</a></small>
					</c:if>
				 </td>

				<td>${inscricao.nivel.descricao}</td>			
				
				<td>
				<div class="row" id="div${inscricao.id}">
				<form action="${linkTo[InscricaoController].informaResultadoDoTenista}" method="post" id="formInformaResultado">
				
				<input type="hidden" name="inscricao.id" value="${inscricao.id}"/>
				<input type="hidden" name="inscricao.torneio.id" value="${torneio.id}"/>
				
				<!-- combo com as possiveis colocações -->
				<select name="inscricao.colocacao.id" id="fase" class="form-control">
					<option value="0">Escolha uma opção</option>
					  <c:forEach items="${colocacoes}" var="colocacao">
						<c:choose>		
							<c:when test="${inscricao.colocacao.id == colocacao.id}">
								<option value="${colocacao.id}" selected="selected">${colocacao.descricao}</option>
							</c:when>
							
							<c:otherwise>
								<option value="${colocacao.id}">${colocacao.descricao}</option>
							</c:otherwise>
						</c:choose>				  
					</c:forEach>
				</select>	

				<!-- opção para informa a pontuacao -->
				<label for="fase">Pontuação</label>
					
				<input type="text" name="inscricao.pontuacao" value="${inscricao.pontuacao}"/>
				
				<c:if test="${inscricao.pontuacao == null}">
					<button class="btn btn-success" onclick="
						
					
					$('#div${inscricao.id}').load('${linkTo[InscricaoController].informaResultadoDoTenista}', {inscricao.id:${inscricao.id}, inscricao.torneio.id:${torneio.id}, inscricao.colocacao.id:${colocacao.id}, inscricao.pontuacao:${inscricao.pontuacao}});
					
					">Informar resultado</button>
					<!--  <a href="javascript: enviar()">Informar Resultado</a>-->
				</c:if>
				<c:if test="${inscricao.pontuacao != null}">
					<button class="btn btn-primary" >Editar resultado</button>
				</c:if>
				</form>
				</div>
				
				</td>
			</tr>
		
		</c:forEach>
	</table>