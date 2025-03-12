<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${empresa} | Enviar arquivo de imagem</title>
<c:import url="../comum/links.jsp"/>
</head>
<body>
<div class="body_wrapper">
  <div class="center">
	<c:import url="../menu/menu.jsp"></c:import>	

	<div class="container-fluid">


    <div class="content_area">


      
        <div class="single_left_coloum_wrapper">
            <h2 class="title">Nova Imagem</h2>
        </div>

	<c:import url="../comum/errors.jsp"/>
	
			<form action="${linkTo[MidiaTorneioController].enviaMidiaTorneio}" enctype="multipart/form-data" method="POST" >
			<input type="hidden" name="midiaTorneio.torneio.id" value="${torneio.id}">
			
			
			<div class="row">
			<div class="form-group col-sm-12">
			  <label for="titulo">Título:</label> 
			  <input type="text" name="midiaTorneio.titulo" class="form-control" id ="titulo" required="required" maxlength="50"/> 
			 </div>
			
			</div>
			<div class="radio">
			  <label>
			    <input type="radio" name="midiaTorneio.tipoMidiaTorneio" id="imagem" value="IMAGEM" checked>
			    Imagem (Envie arquivos com extensão .jpg, .png ou .jpeg e com até 500KB)
			  </label>
			</div>
			<div class="radio">
			  <label>
			    <input type="radio" name="midiaTorneio.tipoMidiaTorneio" id="video" value="VIDEO">
			    Vídeo (Envie URL do vídeo)
			  </label>
			</div>
			<div class="radio">
			  <label>
			    <input type="radio" name="midiaTorneio.tipoMidiaTorneio" id="regulamento" value="REGULAMENTO">
			    Regulamento (Envie um arquivo em PDF e com até 500KB, com largura próxima a 500px e altura próxima a 700px)
			  </label>
			</div>
			<div class="radio">
			  <label>
			    <input type="radio" name="midiaTorneio.tipoMidiaTorneio" id="banner" value="BANNER">
			    Banner (Envie um arquivo .jpg, .png ou .jpeg e com até 700KB)
			  </label>
			</div>
			<div class="radio">
			  <label>
			    <input type="radio" name="midiaTorneio.tipoMidiaTorneio" id="patrocinio" value="PATROCINIO">
			    Patrocínio (Envie uma imagem com a logomarca da empresa de até 500KB)
			  </label>
			</div>
			<c:if test="${administrador}">
				<div class="radio">
				  <label>
				    <input type="radio" name="midiaTorneio.tipoMidiaTorneio" id="chaveamento" value="CHAVEAMENTO">
				    Chaveamento
				  </label>
				</div><div class="radio">
				  <label>
				    <input type="radio" name="midiaTorneio.tipoMidiaTorneio" id="inscritos" value="INSCRITOS">
				    Inscritos
				  </label>
				</div>
			</c:if>
			
			<div class="row">
			<div class="form-group col-sm-7">
			  <label for="arquivo">Escolha o arquivo:</label> 
			  <input type="file" name="arquivo" class="form-control" id ="arquivo"/> 
			</div>
			<div class="form-group col-sm-5">
			  <label for="video">Se estiver enviando um vídeo informe a url do vídeo na web:</label> 
			  <input type="text" name="midiaTorneio.url" class="form-control" id ="video"/> 
			</div>
			</div>
			
			 <div class="btn btn-tollbar" align="center" >
			 	<button class="form-group btn btn-success col-sm-12 pull-center">Enviar</button>
			 </div>		
			  <a href="${linkTo[TorneioController].detalhaTorneio(torneio.id)}" class="btn btn-primary">Detalhes do torneio</a>
			 	<br><small>${mensagem }</small>
			  
				

				
			</form>
			
		</div>

			<c:import url="../menu/rodape.jsp"/>
		
</div>
</div>
</div>
			<c:import url="../comum/scripts.jsp"/>
			
</body>
</html>