<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${empresa } - Inscrições do torneio</title>
  <c:import url="../comum/links.jsp"/>

<cfajaximport>
<script>
function enviar() {
ColdFusion.Ajax.submitForm('formInformaResultado', '${linkTo[InscricaoController].informaResultadoDoTenista}', callback, errorHandler);
}

function callback(text)
{
alert("Callback:" + text);
}

function errorHandler(code, msg)
{
alert("Error! " + code + ":" + msg);
}
</script>
</cfajaximport>
</head>
<body>
<div class="body_wrapper">
  <div class="center">
	<c:import url="../menu/menu.jsp"></c:import>	

	<div class="container-fluid">


    <div class="content_area">


      
        <div class="single_left_coloum_wrapper">
            <h2 class="title">Painel de Gerenciamento</h2>
        </div>

    <h4>Gerenciamento de inscrições</h4>
	<div class="panel-body">
		<div align="center">
		<h3>Torneio: ${torneio.nome} <br>
		Organizador: ${torneio.tenistaOrganizador.nome}</h3>
		<a href="${linkTo[TorneioController].detalhaTorneio(torneio.id)}"	class="btn btn-primary">Detalhes do torneio</a>
		</div>
		
	
	<div class="row">
		<div class="form-group col-sm-12" align="center">
		<small>${mensagemDeSucesso}</small>
		</div>
	</div>

	<c:import url="../comum/panelTorneioIniciado.jsp"></c:import>
	
	

</div>
	
</div>

	<c:import url="../menu/rodape.jsp"/>
	
	
</div>
</div>
</div>

</body>
</html>