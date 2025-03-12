<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>



							<label for="dataInicioInscricao">Início das inscrições:</label>
							<span  id="dataInicioInscricao" class="text-default">							
								<fmt:formatDate value="${torneio.dataInicioInscricao.time}" type="both" pattern="dd/MM/yyyy" />
							</span>
							
							<br>
							<br>
							<label for="dataFimInscricao">Término das inscrições:</label>
							<span  id="dataFimInscricao" class="text-default">							
								<fmt:formatDate value="${torneio.dataFimInscricao.time}" type="both" pattern="dd/MM/yyyy" />
							</span>						
							<br>
							<br>
							<label for="valorInscricao">Valor da primeira inscrição</label>
							<span  id="valorInscricao" class="text-default"><fmt:formatNumber type="currency" currencySymbol="R$" 
							currencyCode="BRL" value="${torneio.valorInscricao}"/></span>
							<br>
							<br>

							<label for="valorInscricaoFiliado">Valor da primeira inscrição (Filiado):</label>
							<span  id="valorInscricaoFiliado" class="text-default"><fmt:formatNumber type="currency" currencySymbol="R$" 
							currencyCode="BRL" value="${torneio.valorInscricaoFiliado}"/></span>
							<br>
							<br>

							<label for="valorSegundaInscricao">Valor da segunda inscrição</label>
							<span  id="valorSegundaInscricao" class="text-default"><fmt:formatNumber type="currency" currencySymbol="R$" 
							currencyCode="BRL" value="${torneio.valorSegundaInscricao}"/></span>

							<br>
							<br>
							<label for="valorTerceiraInscricao">Valor da terceira inscrição</label>
							<span  id="valorTerceiraInscricao" class="text-default"><fmt:formatNumber type="currency" currencySymbol="R$" 
							currencyCode="BRL" value="${torneio.valorTerceiraInscricao}"/></span>
							<br>
							<br>

							<label for="informacoesPagamento">Informações de pagamento</label>
							<span  id="informacoesPagamento" class="text-default">${torneio.informacoesPagamento}</span>
							
							<br>
							<br>
							<label for="observacoesAdicionais">Observações adicionais:</label>
							<span  id="observacoesAdicionais" class="text-default">${torneio.observacoesAdicionais}</span>
							
							<c:if test="${inscricoesAbertas && torneio.chaveGerada == false && torneio.situacao.id == 1 && torneio.aberto == true && tenistaLogado != null}">
									${botaoPagamento}		 
							</c:if>							
					
	