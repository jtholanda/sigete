<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!--  <form action="${linkTo[InscricaoController].reiniciaCabecasDeChave}" method="post">
<div class="row">
<div class="form-group col-sm-7">
<fieldset>
<input type="hidden" name="idTorneio" value="${inscricao.torneio.id}"/>
<legend>Escolha o nível técnico que o tenista deseja se inscrever</legend>
			
			<select name="idNivel" id="niveis" class="form-control">
				  <c:forEach items="${niveis}" var="nivel">								
					  <option value="${nivel.id}">${nivel.descricao}</option>
					</c:forEach>
			</select>
			
</fieldSet>
</div>
<button class="btn btn-primary" >Reiniciar cabeças de chave</button>
</div>
</form>
-->
<table class="table table-hover">
		<tr>
		<c:forEach items="${torneio.niveis}" var="nivel">								
			<a href="${linkTo[InscricaoController].reiniciaChavesDoTorneio(torneio.id, nivel.id)}" class="btn btn-default col-sm-4">Reiniciar ${nivel.descricao}</a>
		</c:forEach>
		</tr>
		
		<tr>
			<th>Nome completo</th>
			<th>Nível técnico</th>
			<th>Confirmada?<br>
				<a href="${linkTo[InscricaoController].confirmaTodasInscricoes(torneio.id)}"	class="btn btn-success">Confirmar todas</a> 
			</th>
			<th>Paga?<br>
				<a href="${linkTo[InscricaoController].confirmaTodosPagamentos(torneio.id)}"	class="btn btn-success">Confirmar todas</a> 
			</th>
			<th>Bolsista?</th>
			<th>Remover?</th>
			<th>Nº do cabeça de chave    /	    Forma de pagamento</th>
		</tr>
		
	
		
		<c:forEach var="inscricao" items="${inscricoes}">
		 
				<tr>
				
				<td><c:if test="${inscricao.tenista.filiado}"><img alt="Filiado" src="/${contexto}/imagens/simbolo_filiado.jpg"  width="30" height="30"/> </c:if>
				<a href="${linkTo[InscricaoController].formEditaInscricao(inscricao.id)}">
				${inscricao.tenista.nomeCompleto}
				</a>
				<c:if test="${torneio.duplas }">
					<br> 
					<a href="${linkTo[InscricaoController].formEditaInscricao(inscricao.id)}">
					<c:if test="${inscricao.tenistaParceiro.filiado}"><img alt="Filiado" src="/${contexto}/imagens/simbolo_filiado.jpg"  width="30" height="30"/> </c:if>
					${inscricao.tenistaParceiro.nomeCompleto}
					</a>
				</c:if>
				<c:if test="${torneio.simplesEDuplas && inscricao.nivel.quantidadeDeTenistas > 1}">
					<br> 
					<a href="${linkTo[InscricaoController].formEditaInscricao(inscricao.id)}">
					<c:if test="${inscricao.tenistaParceiro.filiado}"><img alt="Filiado" src="/${contexto}/imagens/simbolo_filiado.jpg"  width="30" height="30"/> </c:if>
					${inscricao.tenistaParceiro.nomeCompleto}
					</a>
				</c:if>
					<c:if test = "${inscricao.horarioEspecial != null}">
						<small class="text text-danger">Horário especial</small>
					</c:if>
					
					<c:if test="${inscricao.horarioEspecial == null}">
						<small>Horário especial</small>
					</c:if>
				</td>
				
				<td>${inscricao.nivel.descricao}</td>
				
				<c:if test="${inscricao.confirmada}">
					<td class="text text-info">Sim
					<a href="${linkTo[InscricaoController].gerenciaInscricaoAcao(inscricao.id, CANCELA_INSCRICAO)}"  class="btn btn-danger">Cancelar inscrição</a></td>
				</c:if>
				<c:if test="${!inscricao.confirmada}">
					<td class="text text-danger">Não
					 <a href="${linkTo[InscricaoController].gerenciaInscricaoAcao(inscricao.id, CONFIRMA_INSCRICAO)}" class="btn btn-success">Confirmar inscrição</a></td>
				</c:if>
				
				<c:if test="${inscricao.paga}">
					<c:if test="${inscricao.bolsista}">
						<td class="text text-info">Sim/Bolsista
						<a href="${linkTo[InscricaoController].gerenciaInscricaoAcao(inscricao.id, CANCELA_PAGAMENTO_BOLSA)}" class="btn btn-danger">Cancelar $$ e bolsa</a></td>
					</c:if>
					<c:if test="${!inscricao.bolsista}">
						<td class="text text-info">Sim
						<a href="${linkTo[InscricaoController].gerenciaInscricaoAcao(inscricao.id, CANCELA_PAGAMENTO)}" class="btn btn-danger">Cancelar $$</a></td>
					</c:if>
				</c:if>
				<c:if test="${!inscricao.paga}">
					<td class="text text-danger">Não
					 <a href="${linkTo[InscricaoController].gerenciaInscricaoAcao(inscricao.id, CONFIRMA_PAGAMENTO)}" class="btn btn-success">Confirmar $$</a></td>
				</c:if>
		
				
				<td><a href="${linkTo[InscricaoController].gerenciaInscricaoAcao(inscricao.id, ATRIBUI_BOLSA)}"	class="btn btn-primary">Atribuir bolsa</a></td>
				
				<td><a href="${linkTo[InscricaoController].gerenciaInscricaoAcao(inscricao.id, REMOVE_INSCRICAO)}"	class="btn btn-danger">Remover</a></td>
				
				<td>
				<form action="${linkTo[InscricaoController].salvaInscricao}" method="post">
				<input type="hidden" name="idInscricao" value="${inscricao.id}"/>
				<input type="text" name="numeroCabecaDeChave" value="${inscricao.numeroCabecaDeChave}" placeholder="Cabeça de chave"/>
				<input type="text" name="formaDePagamento" value="${inscricao.formaDePagamento}" placeholder="Forma de Pagamento"/>
				<button class="btn btn-primary" >OK</button>
				</form>
				</td>
				
			</tr>
		
		</c:forEach>
	</table>