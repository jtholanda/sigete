<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>





							<label for="valorPremiacao">Premia��o em dinheiro(R$):</label>
							<span  id="valorPremiacao" class="text-default"><fmt:formatNumber type="currency" currencySymbol="R$" 
							currencyCode="BRL" value="${torneio.premiacao.valor}"/></span>
							<br>
							<br>
							<label for="trofeusEmedalhas">Trof�us e/ou medalhas?</label>
							<span  id="trofeusEmedalhas" class="text-default">

							     <c:if test="${torneio.premiacao.trofeusEMedalhas}">
							     Sim
							     </c:if>
							     <c:if test="${!torneio.premiacao.trofeusEMedalhas}">
							     N�o
							     </c:if>
							
							</span>
							<br>
							<br>
							<label for="camisaTorneio">Camisa do torneio?</label>
							<span  id="camisaTorneio" class="text-default">

							     <c:if test="${torneio.premiacao.camisaTorneio}">
							     Sim
							     </c:if>
							     <c:if test="${!torneio.premiacao.camisaTorneio}">
							     N�o
							     </c:if>
							
							</span>
							<br>
							<br>
							<label for="sorteioDeBrindes">Sorteio de brindes?</label>
							<span  id="sorteioDeBrindes" class="text-default">

							     <c:if test="${torneio.premiacao.sorteioDeBrindes}">
							     Sim
							     </c:if>
							     <c:if test="${!torneio.premiacao.sorteioDeBrindes}">
							     N�o
							     </c:if>
							
							</span>
							<br>
							<br>
							<label for="premiosEmProdutos">Pr�mios em produtos?</label>
							<span  id="premiosEmProdutos" class="text-default">

							     <c:if test="${torneio.premiacao.premiosEmProdutos}">
							     Sim
							     </c:if>
							     <c:if test="${!torneio.premiacao.premiosEmProdutos}">
							     N�o
							     </c:if>
							
							</span>
							<br>
							<br>
							<label for="premiosParaTodasAsClasses">Pr�mio para todas as classes?</label>
							<span  id="premiosParaTodasAsClasses" class="text-default">

							     <c:if test="${torneio.premiacao.premiosParaTodasAsClasses}">
							     Sim
							     </c:if>
							     <c:if test="${!torneio.premiacao.premiosParaTodasAsClasses}">
							     N�o
							     </c:if>
							
							</span>

							<c:if test="${inscricoesAbertas && torneio.chaveGerada == false && torneio.situacao.id == 1 && torneio.aberto == true && tenistaLogado != null}">
									${botaoPagamento}		 
							</c:if>							
			
