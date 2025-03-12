<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${empresa} | Cadastro de filiados</title>
   <c:import url="../comum/links.jsp"/>
</head>
<body>
<div class="body_wrapper">
  <div class="center">
	<c:import url="../menu/menu.jsp"></c:import>	

	<div class="container-fluid">


    <div class="content_area">


      
        <div class="single_left_coloum_wrapper">
            <h2 class="title">Cadastro de Filiados</h2>
        </div>
					
			<c:import url="../comum/errors.jsp"/>
				
			<form action="${linkTo[AdminController].confirmaCadastroFiliado}" method="POST" > 

			<div class="row">
			<div class="form-group col-sm-12">
			<label>Tenista *</label>
						
						<select name="filiado.tenista.id" id="tenistas" class="form-control">
						  <option value="0">Escolha um ${esportista}</option>
							  <c:forEach items="${tenistas}" var="tenista">								
								  <option value="${tenista.id}">${tenista.exibicaoBasica}</option>
								</c:forEach>
						</select>
							
			</div>
			</div>

			<div class="row">
				<div class="form-group col-sm-3">
					<label for="data">Data *</label>
					<input type="text" id="data" name="filiado.data"
					 class="form-control" value="<fmt:formatDate pattern="dd/MM/yyyy" value="${filiado.data.time}"/>"  required="required"/>
				</div>

						
				<div class="form-group col-sm-3">
					<label for="anoReferencia">Ano Referência</label>
					<select name="filiado.anoReferencia" id="anoReferencia" class="form-control" >
					  <option value="2018" selected="selected">2018</option>
					</select>
				</div>
				
				<div class="form-group col-sm-2">
					<label for="valorPago">Valor Pago *</label>
					<span class="input-group-addon">R$</span>
					<input type="text" id="valorPago" name="filiado.valorPago" class="form-control" value="${filiado.valorPago}" required="required"/>
					<span class="input-group-addon">,00</span>
				</div>
				<div class="form-group col-sm-2">
					<label for="valorPago">Valor Líquido *</label>
					<span class="input-group-addon">R$</span>
					<input type="text" id="valorLiquido" name="filiado.valorLiquido" class="form-control" value="${filiado.valorLiquido}" required="required"/>
					<span class="input-group-addon">,00</span>
				</div>
				<div class="form-group col-sm-1">
					<label class="checkbox col-sm-5">
					<input type="checkbox" id="bolsita" name="filiado.bolsista"/>É bolsista?</label>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-sm-12">
						<label for="observacao">Observação</label>
						<textarea id="obs" name="filiado.observacao" class="form-control">
							${filiado.observacao}
						</textarea>
				</div>
			</div>
				
				<div class="btn-toolbar" align="center">
					<button class="form-group col-sm-12 btn btn-success pull-center">Cadastrar Filiado</button>
				</div>
		</form>
			
		</div>
		

		<c:import url="../menu/rodape.jsp"/>
		
</div>
</div>
</div>

		<c:import url="../comum/scripts.jsp"/>



</body>
</html>