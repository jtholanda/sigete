<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
					<div class="form-group col-sm-7">
							<label for="nome">Nome:</label>
							<span id="nome">${torneio.nome}</span>
							<br>
							<br>
							<label for="organizador">Organizadores:</label><br>
							<span id="organizador" class="text-default">${torneio.tenistaOrganizador.nome} ${torneio.tenistaOrganizador.telefonePrincipal.numero}</span><br>
							<span id="auxiliar1" class="text-default">${torneio.tenistaAuxiliar1.nome} ${torneio.tenistaAuxiliar1.telefonePrincipal.numero}</span><br>
							<span id="auxiliar2" class="text-default">${torneio.tenistaAuxiliar2.nome} ${torneio.tenistaAuxiliar2.telefonePrincipal.numero}</span><br>

							<br>
							<label for="categoria">Categoria:</label>
							<span id="categoria" class="text-default">${torneio.categoria.nome}</span>
							<br>

							<br>
							<label for="chaveamento">Chaveamento:</label>
							<span id="chaveamento" class="text-default">${torneio.tipoDeChave.nome}</span>
							<br>

						
							<br>
							<label for="dataInicio">Data de início:</label>
							<span  id="dataInicio" class="text-default">							
								<fmt:formatDate value="${torneio.dataInicio.time}" type="both" pattern="dd/MM/yyyy" />
							</span>
							
							<br>
							<br>	
							<label for="dataFim">Finais previstas:</label>
							<span  id="dataFim" class="text-default">							
								<fmt:formatDate value="${torneio.dataFim.time}" type="both" pattern="dd/MM/yyyy" />
							</span>							
							<br>
							<br>
							<label for="horario">Horário:</label>
							<span id="horario" class="text-default">${torneio.horario}</span>
							<br>
							<br>				
							<label for="local">Local:</label>
							<span  id="local" class="text-default">${torneio.local.nome}</span>
							<br>		
							<br>										
							<label for="situacao">Situação:</label>
							<span id="situacao" class="text-default">${torneio.situacao.nome}</span>
							<br>
							<br>
							<label for="pontosRanking">Conta pontos para o circuíto ${empresa}:</label>
							<span id="pontosRanking" class="text-default"> <c:if test="${torneio.contaPontos}">Sim</c:if> <c:if test="${!torneio.contaPontos}">Não</c:if> </span><br>				
	
							<c:if test="${inscricoesAbertas || (torneio.situacao.id == 2)}">
									<c:if test="${tenistaLogado == null }">
										<span class="text-default">Se for ${esportista} com cadastro especial, entre na sua contato para ter o desconto caso não apareça ao logar. 
										Faça a sua inscrição além do pagamento, é necessário os dois processos.</span>
									</c:if> 		 
							</c:if>							
							<!-- 
							<label for="aberto">Aberto:</label>
							<span id="aberto" class="text-default"> <c:if test="${torneio.aberto}">Sim</c:if> <c:if test="${!torneio.aberto}">Não</c:if> </span>
							<br>	
													
							<label for="rankingDeClube">Ranking de Clube:</label>
							<span id="rankingDeClube" class="text-default"> <c:if test="${torneio.rankingDeClube}">Sim</c:if> <c:if test="${!torneio.rankingDeClube}">Não</c:if> </span>
							<br>
							 -->	
							 </div>			
					<div class="form-group col-sm-5" align="center">
				        <img src="${banner}" class="img-responsive img-rounded" alt="${tituloBanner}">
				     </div>
