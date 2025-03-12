	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
	<div class="row">
	
	<div class="form-group col-sm-3">	
	<fieldset><legend>16º de finais</legend>
	<table class="table table-condensed">
		 

		
		<tr>
			<td colspan="2"><b>Jogo 01</b></td>
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
			<td colspan="2"><b>Jogo 02</b></td>
		</tr>
		<tr>
		<c:if test="${!torneio.duplas}">
				<td><small>${jogos.get(15).tenistaUm.nomeRanking}</small></td>
				<td><small>${jogos.get(15).tenistaDois.nomeRanking}</small></td>
			</c:if>
			<c:if test="${torneio.duplas}">
				<td><small>${jogos.get(15).primeiraDupla}</small></td>
				<td><small>${jogos.get(15).segundaDupla}</small></td>
			</c:if>
	
		</tr>
		
		<tr>
			<td colspan="2"><b>Jogo 03</b></td>
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
			<td colspan="2"><b>Jogo 04</b></td>
		</tr>
		<tr>
		<c:if test="${!torneio.duplas}">
				<td><small>${jogos.get(8).tenistaUm.nomeRanking}</small></td>
				<td><small>${jogos.get(8).tenistaDois.nomeRanking}</small></td>
			</c:if>
			<c:if test="${torneio.duplas}">
				<td><small>${jogos.get(8).primeiraDupla}</small></td>
				<td><small>${jogos.get(8).segundaDupla}</small></td>
			</c:if>
		</tr>
		<tr>
			<td colspan="2"><b>Jogo 05</b></td>
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
			<td colspan="2"><b>Jogo 06</b></td>
		</tr>
		<tr>
		<c:if test="${!torneio.duplas}">
				<td><small>${jogos.get(12).tenistaUm.nomeRanking}</small></td>
				<td><small>${jogos.get(12).tenistaDois.nomeRanking}</small></td>
			</c:if>
			<c:if test="${torneio.duplas}">
				<td><small>${jogos.get(12).primeiraDupla}</small></td>
				<td><small>${jogos.get(12).segundaDupla}</small></td>
			</c:if>
		</tr>
		
		<tr>
			<td colspan="2"><b>Jogo 07</b></td>
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
			<td colspan="2"><b>Jogo 08</b></td>
		</tr>
		<tr>
		<c:if test="${!torneio.duplas}">
				<td><small>${jogos.get(11).tenistaUm.nomeRanking}</small></td>
				<td><small>${jogos.get(11).tenistaDois.nomeRanking}</small></td>
			</c:if>
			<c:if test="${torneio.duplas}">
				<td><small>${jogos.get(11).primeiraDupla}</small></td>
				<td><small>${jogos.get(11).segundaDupla}</small></td>
			</c:if>
		</tr>
		
		<tr>
			<td colspan="2"><b>Jogo 09</b></td>
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
			<td colspan="2"><b>Jogo 10</b></td>
		</tr>
		<tr>
		<c:if test="${!torneio.duplas}">
				<td><small>${jogos.get(14).tenistaUm.nomeRanking}</small></td>
				<td><small>${jogos.get(14).tenistaDois.nomeRanking}</small></td>
			</c:if>
			<c:if test="${torneio.duplas}">
				<td><small>${jogos.get(14).primeiraDupla}</small></td>
				<td><small>${jogos.get(14).segundaDupla}</small></td>
			</c:if>
		
			
		</tr>
		<tr>
			<td colspan="2"><b>Jogo 11</b></td>
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
			<td colspan="2"><b>Jogo 12</b></td>
		</tr>
		<tr>
		<c:if test="${!torneio.duplas}">
				<td><small>${jogos.get(9).tenistaUm.nomeRanking}</small></td>
				<td><small>${jogos.get(9).tenistaDois.nomeRanking}</small></td>
			</c:if>
			<c:if test="${torneio.duplas}">
				<td><small>${jogos.get(9).primeiraDupla}</small></td>
				<td><small>${jogos.get(9).segundaDupla}</small></td>
			</c:if>
		</tr>
		<tr>
			<td colspan="2"><b>Jogo 13</b></td>
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
			<td colspan="2"><b>Jogo 14</b></td>
		</tr>
		<tr>
		<c:if test="${!torneio.duplas}">
				<td><small>${jogos.get(13).tenistaUm.nomeRanking}</small></td>
				<td><small>${jogos.get(13).tenistaDois.nomeRanking}</small></td>
			</c:if>
			<c:if test="${torneio.duplas}">
				<td><small>${jogos.get(13).primeiraDupla}</small></td>
				<td><small>${jogos.get(13).segundaDupla}</small></td>
			</c:if>
		</tr>
		
		<tr>
			<td colspan="2"><b>Jogo 15</b></td>
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

		<tr>
			<td colspan="2"><b>Jogo 16</b></td>
		</tr>
		<tr>
		<c:if test="${!torneio.duplas}">
				<td><small>${jogos.get(10).tenistaUm.nomeRanking}</small></td>
				<td><small>${jogos.get(10).tenistaDois.nomeRanking}</small></td>
			</c:if>
			<c:if test="${torneio.duplas}">
				<td><small>${jogos.get(10).primeiraDupla}</small></td>
				<td><small>${jogos.get(10).segundaDupla}</small></td>
			</c:if>
		</tr>
		

	</table>
	</fieldset>
	
	</div>
	
	
	
	<div class="form-group col-sm-3">	
	<fieldset><legend>Oitavas de finais</legend>
	<table class="table table-condensed ">
		 

		
		<tr>
			<td><b>Jogo 17</b></td>
		</tr>
		<tr>
			<td>Venc. Jogo 01</td>
		</tr>
		<tr>
			<td>Venc. Jogo 02</td>
		</tr>
		
		<tr>
			<td><b>Jogo 18</b></td>
		</tr>
		<tr>
			<td>Ven. Jogo 03</td>
		</tr>
		
		<tr>
			<td>Ven. Jogo 04</td>
		</tr>
		
		<tr>
			<td><b>Jogo 19</b></td>
		</tr>
		<tr>
			<td>Ven. Jogo 05</td>
		</tr>
		
		<tr>
			<td>Ven. Jogo 06</td>
		</tr>

		<tr>
			<td><b>Jogo 20</b></td>
		</tr>
		<tr>
			<td>Ven. Jogo 07</td>
		</tr>
		
		<tr>
			<td>Ven. Jogo 08</td>
		</tr>
		<tr>
			<td><b>Jogo 21</b></td>
		</tr>
		<tr>
			<td>Ven. Jogo 09</td>
		</tr>
				
		
		<tr>
			<td>Ven. Jogo 10</td>
		</tr>
		
		
		<tr>
			<td><b>Jogo 22</b></td>
		</tr>
		<tr>
			<td>Ven. Jogo 11</td>
		</tr>
		
		<tr>
			<td>Ven. Jogo 12</td>
		</tr>
		
		<tr>
			<td><b>Jogo 23</b></td>
		</tr>
		<tr>
			<td>Ven. Jogo 13</td>
		</tr>
		
		<tr>
			<td>Ven. Jogo 14</td>
		</tr>

		<tr>
			<td><b>Jogo 24</b></td>
		</tr>
		<tr>
			<td>Ven. Jogo 15</td>
		</tr>
		
		<tr>
			<td>Ven. Jogo 16</td>
		</tr>

	</table>
	</fieldset>
	
	</div>
	
	<div class="form-group col-sm-2">
	
	<fieldset>
	<legend>Quartas de finais</legend>
	
	<table class="table table-condensed">
		<tr>
			<td><b>Jogo 25</b></td>
		</tr>
		<tr>
			<td>Ven. Jogo 17</td>
		</tr>
		<tr>
			<td>Ven. Jogo 18</td>
		</tr>
		
		<tr>
			<td><b>Jogo 26</b></td>
		</tr>
		<tr>
			<td>Ven. Jogo 19</td>
		</tr>
		<tr>
			<td>Ven. Jogo 20</td>
		</tr>
		
		<tr>
			<td><b>Jogo 27</b></td>
		</tr>
		<tr>
			<td>Ven. Jogo 21</td>
		</tr>
		<tr>
			<td>Ven. Jogo 22</td>
		</tr>
		

		<tr>
			<td><b>Jogo 28</b></td>
		</tr>
		<tr>
			<td>Ven. Jogo 23</td>
		</tr>
		<tr>
			<td>Ven. Jogo 24</td>
		</tr>
		
		</table>
		</fieldset>
		</div>
		
		
		<div class="form-group col-sm-2">
		<fieldset>
		<legend>Semi Finais</legend>
		<table class="table table-condensed">
		
		
		<tr>
			<td><b>Jogo 29</b></td>
		</tr>
		<tr>
			<td>Ven. Jogo 25</td>
		</tr>
		<tr>
			<td>Ven. Jogo 26</td>
		</tr>
		
		
		<tr>
			<td><b>Jogo 30</b></td>
		</tr>
		<tr>
			<td>Ven. Jogo 27</td>
		</tr>
		
		<tr>
			<td>Ven. Jogo 28</td>
		</tr>
		</table>
		</fieldset>
		</div>
		
		<div class="form-group col-sm-2">
		<fieldset>
		<legend>Final</legend>
		<table class="table table-condensed">
		
		<!-- decimo terceiro jogo semi -->
		<tr>
			<td><b>Jogo 30</b></td>
		</tr>
		<tr>
			<td>Ven. Jogo 29</td>
		</tr>
		<tr>
			<td>Ven. Jogo 30</td>
		</tr>
		</table>
		</fieldset>
		</div>
	</div>