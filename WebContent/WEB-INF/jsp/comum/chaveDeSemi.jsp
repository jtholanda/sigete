	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
	<div class="row">
	
	<div class="form-group col-sm-6">
		<fieldset>
		<legend>Semi Finais</legend>
		<table class="table table-hover">
		
		
		<tr>
			<td colspan="2"><small><b>Jogo 01</b></small></td>
		</tr>
		<tr>
			<c:if test="${!torneio.duplas}">
				<td><small>${jogos.get(0).tenistaUm.nomeRanking}</small></td>
				<td><small>${jogos.get(0).tenistaDois.nomeRanking}</small></td>
			</c:if>
			<c:if test="${torneio.duplas}">
				<td><small>${jogos.get(0).primeiraDupla}</small></td>
				<td><small>${jogos.get(0).segundaDupla}</small></td>
			</c:if>		
		</tr>
		<tr>
			<td colspan="2"><small><b>Jogo 02</b></small></td>
		</tr>
		<tr>
			<c:if test="${!torneio.duplas}">
				<td><small>${jogos.get(1).tenistaUm.nomeRanking}</small></td>
				<td><small>${jogos.get(1).tenistaDois.nomeRanking}</small></td>
			</c:if>
			<c:if test="${torneio.duplas}">
				<td><small>${jogos.get(1).primeiraDupla}</small></td>
				<td><small>${jogos.get(1).segundaDupla}</small></td>
			</c:if>				
		</tr>
		

		</table>
		</fieldset>
		</div>
		
		<div class="form-group col-sm-6">
		<fieldset>
		<legend>Final</legend>
		<table class="table table-hover">
		
		<tr>
			<td><small><b>Jogo 03</b></small></td>
		</tr>
		<tr>
			<td><small>Ven. Jogo 01</small></td>
		</tr>
		<tr>
			<td><small>Ven. Jogo 02</small></td>
		</tr>
		</table>
		</fieldset>
		</div>
	</div>