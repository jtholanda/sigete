<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
			<c:if test="${administrador}">
					<a class="btn btn-info" href="${linkTo[TenistaController].formEditaTenista(tenistaDetalhado.id)}">Editar dados</a>
			</c:if><br><br/>
							<label for="codigo" >Código do ${esportista}:</label>
							<span id="codigo" class="text-default">${tenistaDetalhado.id} </span>
							<br><br>


							<label for="nome" >Nome principal:</label>
							<span id="nome" class="text-default">${tenistaDetalhado.nomeCompleto}</span>
							<br><br>

							<label for="sobreNome" >Sobrenome:</label>
							<span id="sobreNome" class="text-default">${tenistaDetalhado.sobreNome}</span>
							<br><br>

							<label for="nivelTecnico">Nível técnico:</label>
							<span id="nivelTecnico" class="text-default">${tenistaDetalhado.nivelTecnicoPrincipal.descricao}</span>
							<br><br>
							<!-- Retirado para teste a posição no ranking do nivel
							-->
							<!--  
							<label for="pontuacao">Pontuação atual em sua classe:</label>
							<span id="pontuacao" class="text-default">${tenistaDetalhado.pontosCategoriaPrincipal} ponto(s)</span>
							<br><br>
							-->

							<label for="nomeRanking">Nome abreviado :</label>
							<span id="nomeRanking" class="text-default">${tenistaDetalhado.nomeRanking}</span>
							<br><br>

							<label for="sexo">Sexo:</label>
							<span id="sexo" class="text-default">${tenistaDetalhado.sexo.nome}</span>
							<br><br>


							<label for="professor" >Professor:</label>
							<span id="professor" class="text-default">${tenistaDetalhado.professor.nome} </span>
							<br><br>

							<label for="clube" >Clube:</label>
							<span id="clube" class="text-default">${tenistaDetalhado.clube.nome} </span>
																				