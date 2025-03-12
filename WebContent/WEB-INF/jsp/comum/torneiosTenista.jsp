<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<h5>Torneios organizados</h5><br>
	
		<c:forEach var="torneio" items="${torneiosOrganizados}">
		 
		  <div class="col-md-6">
                    <div class="panel-heading">
                        <h4><i class="fa fa-fw fa-check"></i>${torneio.nome}</h4>
                    </div>
                    <div class="panel-body">
                        <p>
                        <span>Inícios das inscrições: <fmt:formatDate pattern="dd/MM/yyyy" value="${torneio.dataInicioInscricao.time}"/></span><br>
                        <span>Início do torneio: <fmt:formatDate pattern="dd/MM/yyyy" value="${torneio.dataInicio.time}"/></span><br>
                        <span>Local: ${torneio.local.nome}</span><br>
                        <span>Valor: <fmt:formatNumber type="currency" currencySymbol="R$" currencyCode="BRL" value="${torneio.valorInscricao}"/></span><br>
                        
                        </p>
						<a href="${linkTo[TorneioController].detalhaTorneio(torneio.id)}" class="btn btn-success">Mais informações</a>
				    </div>
            </div>
		 
		
		</c:forEach>
				
