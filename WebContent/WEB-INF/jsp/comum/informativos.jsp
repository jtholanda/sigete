<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="panel col-sm-12 col-md-12 col-lg-12">
	<c:set var="contador" value="${0}"></c:set>
	<c:forEach items="${informativos}" var="informativo">
	<div class="row">

	<c:set var="contador" property="contador" target="contador" value="${contador+1}"></c:set>

	<div class="col-sm-12">
	  <div class="media-left">
	    <a href="${linkTo[InformativoController].detalhaInformativo(informativo.id)}">
		      <c:if test="${informativo.imagemPrincipal!=null}">
		      <img class="media-object" src="${informativo.imagemPrincipal}" class="img-responsive"  alt="..." width="64px" height="64px">
		    </c:if>
		    <c:if test="${informativo.imagemPrincipal==null}">
		      <img class="media-object" src="/${contexto}/imagens/logomarca.png" class="img-responsive"  alt="..." width="64px" height="64px">
		    </c:if>
		   
	    </a>

	    <div class="media-right">
	    <!--  <small>por ${informativo.tenista.nome} em <fmt:formatDate value="${informativo.data.time}" type="both" pattern="dd/MM/yyyy" /></small><br/>-->
	    <a href="${linkTo[InformativoController].detalhaInformativo(informativo.id)}">
	    <h4 class="media-heading">${informativo.titulo}</h4>
	    </a>${informativo.resumo}
	  	</div>
	    
	  </div>
	  
	 

	</div>
	</div>
	<br><br>
	</c:forEach>
	</div>
<!--  <div class="panel col-sm-12 col-md-12 col-lg-12">
	<c:forEach items="${informativos}" var="informativo">
	<div class="media">
	  <div class="media-left">
	    <a href="${linkTo[InformativoController].detalhaInformativo(informativo.id)}">
		    <c:if test="${informativo.imagemPrincipal!=null}">
		      <img class="media-object" src="${informativo.imagemPrincipal}" width="64px" height="64px" alt="...">
		    </c:if>
		    <c:if test="${informativo.imagemPrincipal==null}">
		      <img class="media-object" src="/sigete/imagens/logomarca.png" width="64px" height="64px" alt="...">
		    </c:if>
	    </a>
	    
	  </div>
	  <div class="media-body">
	    <small>por ${informativo.tenista.nome} em <fmt:formatDate value="${informativo.data.time}" type="both" pattern="dd/MM/yyyy" /></small><br/>
	    <a href="${linkTo[InformativoController].detalhaInformativo(informativo.id)}">
	    <h4 class="media-heading">${informativo.titulo}</h4>
	    </a>${informativo.resumo}
	  </div>
	</div>
	</c:forEach>
	</div>
	-->
	