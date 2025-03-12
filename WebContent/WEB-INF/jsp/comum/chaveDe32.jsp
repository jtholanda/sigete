	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
	<div class="row">
	
	<div class="form-group col-sm-2">	
	<fieldset><legend>32º de finais</legend>
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
				<td><small>${jogos.get(31).tenistaUm.nomeRanking}</small></td>
				<td><small>${jogos.get(31).tenistaDois.nomeRanking}</small></td>
			</c:if>
			<c:if test="${torneio.duplas}">
				<td><small>${jogos.get(31).primeiraDupla}</small></td>
				<td><small>${jogos.get(31).segundaDupla}</small></td>
			</c:if>
	
		</tr>
		
		<tr>
			<td colspan="2"><b>Jogo 03</b></td>
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
			<td colspan="2"><b>Jogo 04</b></td>
		</tr>
		<tr>
		<c:if test="${!torneio.duplas}">
				<td><small>${jogos.get(16).tenistaUm.nomeRanking}</small></td>
				<td><small>${jogos.get(16).tenistaDois.nomeRanking}</small></td>
			</c:if>
			<c:if test="${torneio.duplas}">
				<td><small>${jogos.get(16).primeiraDupla}</small></td>
				<td><small>${jogos.get(16).segundaDupla}</small></td>
			</c:if>
		</tr>
		<tr>
			<td colspan="2"><b>Jogo 05</b></td>
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
			<td colspan="2"><b>Jogo 06</b></td>
		</tr>
		<tr>
		<c:if test="${!torneio.duplas}">
				<td><small>${jogos.get(24).tenistaUm.nomeRanking}</small></td>
				<td><small>${jogos.get(24).tenistaDois.nomeRanking}</small></td>
			</c:if>
			<c:if test="${torneio.duplas}">
				<td><small>${jogos.get(24).primeiraDupla}</small></td>
				<td><small>${jogos.get(24).segundaDupla}</small></td>
			</c:if>
		</tr>
		
		<tr>
			<td colspan="2"><b>Jogo 07</b></td>
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
			<td colspan="2"><b>Jogo 08</b></td>
		</tr>
		<tr>
		<c:if test="${!torneio.duplas}">
				<td><small>${jogos.get(23).tenistaUm.nomeRanking}</small></td>
				<td><small>${jogos.get(23).tenistaDois.nomeRanking}</small></td>
			</c:if>
			<c:if test="${torneio.duplas}">
				<td><small>${jogos.get(23).primeiraDupla}</small></td>
				<td><small>${jogos.get(23).segundaDupla}</small></td>
			</c:if>
		</tr>
		
		<tr>
			<td colspan="2"><b>Jogo 09</b></td>
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
			<td colspan="2"><b>Jogo 10</b></td>
		</tr>
		<tr>
		<c:if test="${!torneio.duplas}">
				<td><small>${jogos.get(28).tenistaUm.nomeRanking}</small></td>
				<td><small>${jogos.get(28).tenistaDois.nomeRanking}</small></td>
			</c:if>
			<c:if test="${torneio.duplas}">
				<td><small>${jogos.get(28).primeiraDupla}</small></td>
				<td><small>${jogos.get(28).segundaDupla}</small></td>
			</c:if>
		
			
		</tr>
		<tr>
			<td colspan="2"><b>Jogo 11</b></td>
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
			<td colspan="2"><b>Jogo 12</b></td>
		</tr>
		<tr>
		<c:if test="${!torneio.duplas}">
				<td><small>${jogos.get(27).tenistaUm.nomeRanking}</small></td>
				<td><small>${jogos.get(27).tenistaDois.nomeRanking}</small></td>
			</c:if>
			<c:if test="${torneio.duplas}">
				<td><small>${jogos.get(27).primeiraDupla}</small></td>
				<td><small>${jogos.get(27).segundaDupla}</small></td>
			</c:if>
		</tr>
		<tr>
			<td colspan="2"><b>Jogo 13</b></td>
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
			<td colspan="2"><b>Jogo 14</b></td>
		</tr>
		<tr>
		<c:if test="${!torneio.duplas}">
				<td><small>${jogos.get(20).tenistaUm.nomeRanking}</small></td>
				<td><small>${jogos.get(20).tenistaDois.nomeRanking}</small></td>
			</c:if>
			<c:if test="${torneio.duplas}">
				<td><small>${jogos.get(20).primeiraDupla}</small></td>
				<td><small>${jogos.get(20).segundaDupla}</small></td>
			</c:if>
		</tr>
		
		<tr>
			<td colspan="2"><b>Jogo 15</b></td>
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
			<td colspan="2"><b>Jogo 16</b></td>
		</tr>
		<tr>
		<c:if test="${!torneio.duplas}">
				<td><small>${jogos.get(19).tenistaUm.nomeRanking}</small></td>
				<td><small>${jogos.get(19).tenistaDois.nomeRanking}</small></td>
			</c:if>
			<c:if test="${torneio.duplas}">
				<td><small>${jogos.get(19).primeiraDupla}</small></td>
				<td><small>${jogos.get(19).segundaDupla}</small></td>
			</c:if>
		</tr>


		<tr>
			<td colspan="2"><b>Jogo 17</b></td>
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
			<td colspan="2"><b>Jogo 18</b></td>
		</tr>
		<tr>
		<c:if test="${!torneio.duplas}">
				<td><small>${jogos.get(30).tenistaUm.nomeRanking}</small></td>
				<td><small>${jogos.get(30).tenistaDois.nomeRanking}</small></td>
			</c:if>
			<c:if test="${torneio.duplas}">
				<td><small>${jogos.get(30).primeiraDupla}</small></td>
				<td><small>${jogos.get(30).segundaDupla}</small></td>
			</c:if>
	
		</tr>
		
		<tr>
			<td colspan="2"><b>Jogo 19</b></td>
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
			<td colspan="2"><b>Jogo 20</b></td>
		</tr>
		<tr>
		<c:if test="${!torneio.duplas}">
				<td><small>${jogos.get(17).tenistaUm.nomeRanking}</small></td>
				<td><small>${jogos.get(17).tenistaDois.nomeRanking}</small></td>
			</c:if>
			<c:if test="${torneio.duplas}">
				<td><small>${jogos.get(17).primeiraDupla}</small></td>
				<td><small>${jogos.get(17).segundaDupla}</small></td>
			</c:if>
		</tr>
		<tr>
			<td colspan="2"><b>Jogo 21</b></td>
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
			<td colspan="2"><b>Jogo 22</b></td>
		</tr>
		<tr>
		<c:if test="${!torneio.duplas}">
				<td><small>${jogos.get(25).tenistaUm.nomeRanking}</small></td>
				<td><small>${jogos.get(25).tenistaDois.nomeRanking}</small></td>
			</c:if>
			<c:if test="${torneio.duplas}">
				<td><small>${jogos.get(25).primeiraDupla}</small></td>
				<td><small>${jogos.get(25).segundaDupla}</small></td>
			</c:if>
		</tr>
		
		<tr>
			<td colspan="2"><b>Jogo 23</b></td>
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
			<td colspan="2"><b>Jogo 24</b></td>
		</tr>
		<tr>
		<c:if test="${!torneio.duplas}">
				<td><small>${jogos.get(22).tenistaUm.nomeRanking}</small></td>
				<td><small>${jogos.get(22).tenistaDois.nomeRanking}</small></td>
			</c:if>
			<c:if test="${torneio.duplas}">
				<td><small>${jogos.get(22).primeiraDupla}</small></td>
				<td><small>${jogos.get(22).segundaDupla}</small></td>
			</c:if>
		</tr>
		
		<tr>
			<td colspan="2"><b>Jogo 25</b></td>
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
			<td colspan="2"><b>Jogo 26</b></td>
		</tr>
		<tr>
		<c:if test="${!torneio.duplas}">
				<td><small>${jogos.get(29).tenistaUm.nomeRanking}</small></td>
				<td><small>${jogos.get(29).tenistaDois.nomeRanking}</small></td>
			</c:if>
			<c:if test="${torneio.duplas}">
				<td><small>${jogos.get(28).primeiraDupla}</small></td>
				<td><small>${jogos.get(28).segundaDupla}</small></td>
			</c:if>
		
			
		</tr>
		<tr>
			<td colspan="2"><b>Jogo 27</b></td>
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
			<td colspan="2"><b>Jogo 28</b></td>
		</tr>
		<tr>
		<c:if test="${!torneio.duplas}">
				<td><small>${jogos.get(18).tenistaUm.nomeRanking}</small></td>
				<td><small>${jogos.get(18).tenistaDois.nomeRanking}</small></td>
			</c:if>
			<c:if test="${torneio.duplas}">
				<td><small>${jogos.get(18).primeiraDupla}</small></td>
				<td><small>${jogos.get(18).segundaDupla}</small></td>
			</c:if>
		</tr>
		<tr>
			<td colspan="2"><b>Jogo 29</b></td>
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
			<td colspan="2"><b>Jogo 30</b></td>
		</tr>
		<tr>
		<c:if test="${!torneio.duplas}">
				<td><small>${jogos.get(26).tenistaUm.nomeRanking}</small></td>
				<td><small>${jogos.get(26).tenistaDois.nomeRanking}</small></td>
			</c:if>
			<c:if test="${torneio.duplas}">
				<td><small>${jogos.get(26).primeiraDupla}</small></td>
				<td><small>${jogos.get(26).segundaDupla}</small></td>
			</c:if>
		</tr>
		
		<tr>
			<td colspan="2"><b>Jogo 31</b></td>
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

		<tr>
			<td colspan="2"><b>Jogo 32</b></td>
		</tr>
		<tr>
		<c:if test="${!torneio.duplas}">
				<td><small>${jogos.get(21).tenistaUm.nomeRanking}</small></td>
				<td><small>${jogos.get(21).tenistaDois.nomeRanking}</small></td>
			</c:if>
			<c:if test="${torneio.duplas}">
				<td><small>${jogos.get(21).primeiraDupla}</small></td>
				<td><small>${jogos.get(21).segundaDupla}</small></td>
			</c:if>
		</tr>
		

	</table>
	</fieldset>
	
	</div>
	
	
	
	<div class="form-group col-sm-2">	
	<fieldset><legend>16º de finais</legend>
	<table class="table table-condensed ">
		 

		
		<tr>
			<td><b>Jogo 33</b></td>
		</tr>
		<tr>
			<td>Venc. Jogo 01</td>
		</tr>
		<tr>
			<td>Venc. Jogo 02</td>
		</tr>
		
		<tr>
			<td><b>Jogo 34</b></td>
		</tr>
		<tr>
			<td>Ven. Jogo 03</td>
		</tr>
		
		<tr>
			<td>Ven. Jogo 04</td>
		</tr>
		
		<tr>
			<td><b>Jogo 35</b></td>
		</tr>
		<tr>
			<td>Ven. Jogo 05</td>
		</tr>
		
		<tr>
			<td>Ven. Jogo 06</td>
		</tr>

		<tr>
			<td><b>Jogo 36</b></td>
		</tr>
		<tr>
			<td>Ven. Jogo 07</td>
		</tr>
		
		<tr>
			<td>Ven. Jogo 08</td>
		</tr>
		<tr>
			<td><b>Jogo 37</b></td>
		</tr>
		<tr>
			<td>Ven. Jogo 09</td>
		</tr>
				
		
		<tr>
			<td>Ven. Jogo 10</td>
		</tr>
		
		
		<tr>
			<td><b>Jogo 38</b></td>
		</tr>
		<tr>
			<td>Ven. Jogo 11</td>
		</tr>
		
		<tr>
			<td>Ven. Jogo 12</td>
		</tr>
		
		<tr>
			<td><b>Jogo 39</b></td>
		</tr>
		<tr>
			<td>Ven. Jogo 13</td>
		</tr>
		
		<tr>
			<td>Ven. Jogo 14</td>
		</tr>

		<tr>
			<td><b>Jogo 40</b></td>
		</tr>
		<tr>
			<td>Ven. Jogo 15</td>
		</tr>
		
		<tr>
			<td>Ven. Jogo 16</td>
		</tr>

		<tr>
			<td><b>Jogo 41</b></td>
		</tr>
		<tr>
			<td>Venc. Jogo 17</td>
		</tr>
		<tr>
			<td>Venc. Jogo 18</td>
		</tr>
		
		<tr>
			<td><b>Jogo 42</b></td>
		</tr>
		<tr>
			<td>Ven. Jogo 19</td>
		</tr>
		
		<tr>
			<td>Ven. Jogo 20</td>
		</tr>
		
		<tr>
			<td><b>Jogo 43</b></td>
		</tr>
		<tr>
			<td>Ven. Jogo 21</td>
		</tr>
		
		<tr>
			<td>Ven. Jogo 22</td>
		</tr>

		<tr>
			<td><b>Jogo 44</b></td>
		</tr>
		<tr>
			<td>Ven. Jogo 23</td>
		</tr>
		
		<tr>
			<td>Ven. Jogo 24</td>
		</tr>
		<tr>
			<td><b>Jogo 45</b></td>
		</tr>
		<tr>
			<td>Ven. Jogo 25</td>
		</tr>
				
		
		<tr>
			<td>Ven. Jogo 26</td>
		</tr>
		
		
		<tr>
			<td><b>Jogo 46</b></td>
		</tr>
		<tr>
			<td>Ven. Jogo 27</td>
		</tr>
		
		<tr>
			<td>Ven. Jogo 28</td>
		</tr>
		
		<tr>
			<td><b>Jogo 47</b></td>
		</tr>
		<tr>
			<td>Ven. Jogo 29</td>
		</tr>
		
		<tr>
			<td>Ven. Jogo 30</td>
		</tr>

		<tr>
			<td><b>Jogo 48</b></td>
		</tr>
		<tr>
			<td>Ven. Jogo 31</td>
		</tr>
		
		<tr>
			<td>Ven. Jogo 32</td>
		</tr>

	</table>
	</fieldset>
	
	</div>
	
	<div class="form-group col-sm-2">
	
	<fieldset>
	<legend>8º de finais</legend>
	
	<table class="table table-condensed">
		<tr>
			<td><b>Jogo 49</b></td>
		</tr>
		<tr>
			<td>Ven. Jogo 33</td>
		</tr>
		<tr>
			<td>Ven. Jogo 34</td>
		</tr>
		
		<tr>
			<td><b>Jogo 50</b></td>
		</tr>
		<tr>
			<td>Ven. Jogo 35</td>
		</tr>
		<tr>
			<td>Ven. Jogo 36</td>
		</tr>
		
		<tr>
			<td><b>Jogo 51</b></td>
		</tr>
		<tr>
			<td>Ven. Jogo 37</td>
		</tr>
		<tr>
			<td>Ven. Jogo 38</td>
		</tr>
		

		<tr>
			<td><b>Jogo 52</b></td>
		</tr>
		<tr>
			<td>Ven. Jogo 39</td>
		</tr>
		<tr>
			<td>Ven. Jogo 40</td>
		</tr>
		<tr>
			<td><b>Jogo 53</b></td>
		</tr>
		<tr>
			<td>Ven. Jogo 41</td>
		</tr>
		<tr>
			<td>Ven. Jogo 42</td>
		</tr>
		
		<tr>
			<td><b>Jogo 54</b></td>
		</tr>
		<tr>
			<td>Ven. Jogo 43</td>
		</tr>
		<tr>
			<td>Ven. Jogo 44</td>
		</tr>
		
		<tr>
			<td><b>Jogo 55</b></td>
		</tr>
		<tr>
			<td>Ven. Jogo 45</td>
		</tr>
		<tr>
			<td>Ven. Jogo 46</td>
		</tr>
		

		<tr>
			<td><b>Jogo 56</b></td>
		</tr>
		<tr>
			<td>Ven. Jogo 47</td>
		</tr>
		<tr>
			<td>Ven. Jogo 48</td>
		</tr>
		
		</table>
		</fieldset>
		</div>
		
		
		<div class="form-group col-sm-2">
		<fieldset>
		<legend>Quartas de Finais</legend>
		<table class="table table-condensed">
		
		
		<tr>
			<td><b>Jogo 57</b></td>
		</tr>
		<tr>
			<td>Ven. Jogo 49</td>
		</tr>
		<tr>
			<td>Ven. Jogo 50</td>
		</tr>
		
		
		<tr>
			<td><b>Jogo 58</b></td>
		</tr>
		<tr>
			<td>Ven. Jogo 51</td>
		</tr>
		
		<tr>
			<td>Ven. Jogo 52</td>
		</tr>
		<tr>
			<td><b>Jogo 59</b></td>
		</tr>
		<tr>
			<td>Ven. Jogo 53</td>
		</tr>
		<tr>
			<td>Ven. Jogo 54</td>
		</tr>
		
		
		<tr>
			<td><b>Jogo 60</b></td>
		</tr>
		<tr>
			<td>Ven. Jogo 55</td>
		</tr>
		
		<tr>
			<td>Ven. Jogo 56</td>
		</tr>
		</table>
		</fieldset>
		</div>
		
		<div class="form-group col-sm-2">
		<fieldset>
		<legend>Semi finais</legend>
		<table class="table table-condensed">
		
		<tr>
			<td><b>Jogo 61</b></td>
		</tr>
		<tr>
			<td>Ven. Jogo 57</td>
		</tr>
		<tr>
			<td>Ven. Jogo 58</td>
		<tr>
			<td><b>Jogo 62</b></td>
		</tr>
		<tr>
			<td>Ven. Jogo 59</td>
		</tr>
		<tr>
			<td>Ven. Jogo 60</td>
		</tr>
		</table>
		</fieldset>
		</div>

		<div class="form-group col-sm-2">
		<fieldset>
		<legend>Final</legend>
		<table class="table table-condensed">
		
		<tr>
			<td><b>Jogo 63</b></td>
		</tr>
		<tr>
			<td>Ven. Jogo 61</td>
		</tr>
		<tr>
			<td>Ven. Jogo 62</td>
		</table>
		</fieldset>
		</div>
	</div>