	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
	<div class="row">
	
	<div class="form-group col-sm-3">	
	<fieldset><legend>8º de finais</legend>
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
				<td><small>${jogos.get(7).tenistaUm.nomeRanking}</small></td>
				<td><small>${jogos.get(7).tenistaDois.nomeRanking}</small></td>
			</c:if>
			<c:if test="${torneio.duplas}">
				<td><small>${jogos.get(7).primeiraDupla}</small></td>
				<td><small>${jogos.get(7).segundaDupla}</small></td>
			</c:if>
		</tr>
		
		<tr>
			<td colspan="2"><small><b>Jogo 03</b></small></td>
		</tr>
		<tr>
			<c:if test="${!torneio.duplas}">
				<td><small>${jogos.get(3).tenistaUm.nomeRanking}</small></td>
				<td><small>${jogos.get(3).tenistaDois.nomeRanking}</small></td>
			</c:if>
			<c:if test="${torneio.duplas}">
				<td><small>${jogos.get(3).primeiraDupla}</small></td>
				<td><small>${jogos.get(3).segundaDupla}</small></td>
			</c:if>
		</tr>

		<tr>
			<td colspan="2"><small><b>Jogo 04</b></small></td>
		</tr>
		<tr>
			<c:if test="${!torneio.duplas}">
				<td><small>${jogos.get(4).tenistaUm.nomeRanking}</small></td>
				<td><small>${jogos.get(4).tenistaDois.nomeRanking}</small></td>
			</c:if>
			<c:if test="${torneio.duplas}">
				<td><small>${jogos.get(4).primeiraDupla}</small></td>
				<td><small>${jogos.get(4).segundaDupla}</small></td>
			</c:if>
		</tr>
		<tr>
			<td colspan="2"><small><b>Jogo 05</b></small></td>
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
		
		
		<tr>
			<td colspan="2"><small><b>Jogo 06</b></small></td>
		</tr>
		<tr>
			<c:if test="${!torneio.duplas}">
				<td><small>${jogos.get(6).tenistaUm.nomeRanking}</small></td>
				<td><small>${jogos.get(6).tenistaDois.nomeRanking}</small></td>
			</c:if>
			<c:if test="${torneio.duplas}">
				<td><small>${jogos.get(6).primeiraDupla}</small></td>
				<td><small>${jogos.get(6).segundaDupla}</small></td>
			</c:if>
		</tr>
		
		<tr>
			<td colspan="2"><small><b>Jogo 07</b></small></td>
		</tr>
		<tr>
			<c:if test="${!torneio.duplas}">
				<td><small>${jogos.get(2).tenistaUm.nomeRanking}</small></td>
				<td><small>${jogos.get(2).tenistaDois.nomeRanking}</small></td>
			</c:if>
			<c:if test="${torneio.duplas}">
				<td><small>${jogos.get(2).primeiraDupla}</small></td>
				<td><small>${jogos.get(2).segundaDupla}</small></td>
			</c:if>
		</tr>

		<tr>
			<td colspan="2"><small><b>Jogo 08</b></small></td>
		</tr>
		<tr>
			<c:if test="${!torneio.duplas}">
				<td><small>${jogos.get(5).tenistaUm.nomeRanking}</small></td>
				<td><small>${jogos.get(5).tenistaDois.nomeRanking}</small></td>
			</c:if>
			<c:if test="${torneio.duplas}">
				<td><small>${jogos.get(5).primeiraDupla}</small></td>
				<td><small>${jogos.get(5).segundaDupla}</small></td>
			</c:if>
		</tr>

	</table>
	</fieldset>
	
	</div>
	
	<div class="form-group col-sm-3">
	
	<fieldset>
	<legend>Quartas de finais</legend>
	
	<table class="table table-hover">
		<tr>
			<td><small><b>Jogo 09</b></small></td>
		</tr>
		<tr>
			<td><small>Ven. Jogo 01</small></td>
		</tr>
		<tr>
			<td><small>Ven. Jogo 02</small></td>
		</tr>
		
		<!-- decimo jogo quartas -->	
		<tr>
			<td><small><b>Jogo 10</b></small></td>
		</tr>
		<tr>
			<td><small>Ven. Jogo 03</small></td>
		</tr>
		<tr>
			<td><small>Ven. Jogo 04</small></td>
		</tr>
		
		<!-- decimo primeiro jogo quartas -->
		<tr>
			<td><small><b>Jogo 11</b></small></td>
		</tr>
		<tr>
			<td><small>Ven. Jogo 05</small></td>
		</tr>
		<tr>
			<td><small>Ven. Jogo 06</small></td>
		</tr>
		

		<!-- decimo segundo jogo quartas -->
		<tr>
			<td><small><b>Jogo 12</b></small></td>
		</tr>
		<tr>
			<td><small>Ven. Jogo 07</small></td>
		</tr>
		<tr>
			<td><small>Ven. Jogo 08</small></td>
		</tr>
		
		</table>
		</fieldset>
		</div>
		
		
		<div class="form-group col-sm-3">
		<fieldset>
		<legend>Semi Finais</legend>
		<table class="table table-hover">
		
		
		<!-- decimo terceiro jogo semi -->
		<tr>
			<td><small><b>Jogo 13</b></small></td>
		</tr>
		<tr>
			<td><small>Ven. Jogo 09</small></td>
		</tr>
		<tr>
			<td><small>Ven. Jogo 10</small></td>
		</tr>
		
		
		<!-- decimo quarto jogo semi -->
		<tr>
			<td><small><b>Jogo 14</b></small></td>
		</tr>
		<tr>
			<td><small>Ven. Jogo 11</small></td>
		</tr>
		
		<tr>
			<td><small>Ven. Jogo 12</small></td>
		</tr>
		</table>
		</fieldset>
		</div>
		
		<div class="form-group col-sm-3">
		<fieldset>
		<legend>Final</legend>
		<table class="table table-hover">
		
		<!-- decimo terceiro jogo semi -->
		<tr>
			<td><small><b>Jogo 15</b></small></td>
		</tr>
		<tr>
			<td><small>Ven. Jogo 13</small></td>
		</tr>
		<tr>
			<td><small>Ven. Jogo 14</small></td>
		</tr>
		</table>
		</fieldset>
		</div>
	</div>