<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="content_area">


      
        <div class="single_left_coloum_wrapper">
            <h2 class="title">Inscritos no Torneio</h2>
        </div>



					
	
	<h2>${torneio.nome}</h2>
	<c:if test="${numeroDeInscricoes > 0 }">
	
	<c:if test="${!imprimirInscritos}">
	<div align="center">
		<!--  <a href="${linkTo[TorneioController].detalhaTorneio(torneio.id)}"	class="btn btn-primary">Detalhes do torneio</a>-->
	<c:if test="${inscritosPDF !=null}">
		<a href="${inscritosPDF}"	class="btn btn-primary" target="_blank">Download dos inscritos em PDF</a>
	</c:if>
	</div>
	</c:if>
	
	<div class="row">
	<div class="form-group col-sm-6">
	<hr>
	<h5>Visualizar inscrições por nível tecnico</h5>
	<hr>

	<form action="${linkTo[InscricaoController].visualizaInscritosNoTorneioPorNivel}" method="POST" >
	
	
	<input type="hidden" name="inscricao.torneio.id" value="${torneio.id}">

	<select name="inscricao.nivel.id" id="nivelTecnico" class="form-control">
		  <option value="0">Selecionar</option>
	  <c:forEach items="${niveisTecnicosDoTorneio}" var="nivelTecnico">								
		  <option value="${nivelTecnico.id}">${nivelTecnico.descricao}</option>
		</c:forEach>
	</select>
			<br><button class="form-group col-sm-6 btn btn-primary pull-center">Filtrar</button>
	</form>
					
	</div>
	</div>


	<div align="right">
		<label>Total de inscritos: ${numeroDeInscricoes}</label><br>
	</div>
	
	<table class="table table-hover">
		<tr>
			<th>Nome completo</th>
			<th>Nível técnico da inscrição</th>
			<th>Grupo</th>
			<c:if test="${!imprimirInscritos}">
				<th>Inscrição confirmada?</th>
			</c:if>			
			
			<c:if test="${imprimirInscritos}">
				<th>Paga?</th>
				<th>Forma de pagamento</th>
				<th>Telefone</th>
				<th>Restrição de horário</th>
			</c:if>
			
			 
		</tr>
		
		<c:forEach var="inscricao" items="${inscricoes}">
		 
			<tr>
				<td><c:if test="${inscricao.tenista.filiado && !imprimirInscritos}"><img alt="Filiado" src="/${esportista}/imagens/simbolo_filiado.jpg"  width="30" height="30"/> </c:if>
				<c:if test="${inscricao.tenista.filiado && imprimirInscritos}">*</c:if>				
				<c:if test="${!imprimirInscritos}"><a href="${linkTo[TenistaController].detalhaTenista(inscricao.tenista.id,1)}">${inscricao.tenista.nomeCompleto}</a></c:if>
				<c:if test="${imprimirInscritos}">${inscricao.tenista.nomeCompleto}</c:if>
				
				<c:if test="${torneio.duplas || torneio.simplesEDuplas}">
					<c:if test="${inscricao.nivel.quantidadeDeTenistas > 1 }">
						<br>
						/
						<c:if test="${inscricao.tenistaParceiro.filiado && !imprimirInscritos}"><img alt="Filiado" src="/${esportista}/imagens/simbolo_filiado.jpg"  width="30" height="30"/> </c:if>					
						<c:if test="${inscricao.tenistaParceiro.filiado && imprimirInscritos}">*</c:if>				
						<c:if test="${!imprimirInscritos}"><a href="${linkTo[TenistaController].detalhaTenista(inscricao.tenistaParceiro.id,1)}"> ${inscricao.tenistaParceiro.nomeCompleto}</a></c:if>
						<c:if test="${imprimirInscritos}">${inscricao.tenistaParceiro.nomeCompleto}</c:if>
					</c:if>
				</c:if>
				
				</td>
				
				<td>${inscricao.nivel.descricao}</td>
				<td>${inscricao.numeroGrupo}</td>
				
				<c:if test="${!imprimirInscritos}">
					<c:if test="${inscricao.paga}">
						<td>Sim</td>
					</c:if>
					<c:if test="${!inscricao.paga}">
						<td><span style="color:red">Não</span></td>
					</c:if>
					<!-- mostra se está pago para o tenista logado ou para o organizador do torneio -->
					<c:if test="${(administrador || (tenistaLogado != null && (torneio.tenistaOrganizador.id == tenistaLogado.id || torneio.tenistaAuxiliar1.id == tenistaLogado.id || torneio.tenistaAuxiliar2.id == tenistaLogado.id))) || (tenistaLogado != null && tenistaLogado.id == inscricao.tenista.id) }">
						<c:if test="${inscricao.paga}">
							<td><span style="color:green">PAGAMENTO CONFIRMADO</span></td>
						</c:if>
						<c:if test="${!inscricao.paga && inscricao.torneio.chaveGerada == false && inscricao.torneio.situacao.id != 3}">
							<td><span style="color:red">PAGAMENTO PENDENTE</span>
							<a href="${linkTo[InscricaoController].removeInscricaoPessoal(inscricao.id)}"	class="btn btn-danger">Remover</a></td>
						</c:if>
					</c:if>
				</c:if>
				
				
				
				<c:if test="${imprimirInscritos}">
					<c:if test="${inscricao.paga}">
						<td>Sim</td>
					</c:if>
					<c:if test="${!inscricao.paga}">
						<td>Não</td>
					</c:if>
					<td>${inscricao.formaDePagamento}</td>									
					<td>${inscricao.tenista.telefonePrincipal.numero}
					<c:if test="${torneio.duplas }">
						<br> ${inscricao.tenistaParceiro.telefonePrincipal.numero}
					</c:if>
					<c:if test="${torneio.simplesEDuplas && inscricao.nivel.quantidadeDeTenistas > 1 }">
						<br> ${inscricao.tenistaParceiro.telefonePrincipal.numero}
					</c:if>
					</td>
					<td>${inscricao.horarioEspecial}</td>
				</c:if>
			</tr>
		
		</c:forEach>
		
	</table>
	</c:if>
	<c:if test="${numeroDeInscricoes == 0 }">
	<small class="text-info">Total de inscritos: ${numeroDeInscricoes}</small>
	<small class="text-info">Nenhum tenista se inscreveu neste torneio.</small>
	</c:if>

	<c:if test="${!imprimirInscritos }">
		<div align="center">
			<a href="${linkTo[TorneioController].detalhaTorneio(torneio.id)}"	class="btn btn-primary">Detalhes do torneio</a>
		</div>
	</c:if>
	
	
	</div>
