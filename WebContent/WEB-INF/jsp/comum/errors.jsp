<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
	<c:if test="${errors != null }">
		
    	<div class="alert alert-danger alert-dismissible fade in">
    	<button type="button" class="close" data-dismiss="alert"><span>&times;</span></button>
    
			<c:forEach items="${errors}" var="error">
				<li style="color:black">${error.message}</li>
			</c:forEach>
		</div>
	</c:if>	