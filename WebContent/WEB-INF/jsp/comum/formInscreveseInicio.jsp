<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<input type="hidden" value="${torneio.id }" name= "inscricao.torneio.id" />
			
			<div class="row">

			<div class="form-group col-sm-6">
			<hr>
			<h4>Meus dados</h4>
			<hr><br/>
				<c:import url="../comum/dadosBasicosTenista.jsp"/>
			</div>
			
			<div class="form-group col-sm-6">
			<hr>
			<h4>Informações do torneio</h4>
			<hr><br/>
			
			<c:import url="../comum/dadosBasicosTorneio.jsp"/>
			</div>
			</div>
			