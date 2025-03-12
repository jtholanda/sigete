<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


							<c:forEach items="${niveisTecnicos}" var="nivelTecnico">								
								  <span  class="text-default">${nivelTecnico}</span><br><br>
							</c:forEach>
							
							<c:if test="${inscricoesAbertas && torneio.chaveGerada == false && torneio.situacao.id == 1 && torneio.aberto == true && tenistaLogado != null}">
									${botaoPagamento}		 
							</c:if>							
				
	
	