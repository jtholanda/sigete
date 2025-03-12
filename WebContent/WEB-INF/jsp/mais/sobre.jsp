<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="pt_br">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Tudo do tênis paraibano em um só lugar. Competições, tenistas, torneios, inscrições, ranking. Tênis da Paraíba.  ">
    <meta name="author" content="José Thiago Holanda">
  

    <title>${empresa} | Sobre</title>

   <c:import url="../comum/links.jsp"/>
    

</head>

<body>
<div class="body_wrapper">
  <div class="center">
	<c:import url="../menu/menu.jsp"></c:import>	

	<div class="container-fluid">


    <div class="content_area">


      
        <div class="single_left_coloum_wrapper">
            <h2 class="title">Sobre</h2>
        </div>
        <div class="col-lg-12 text-center">
 
 		<div class="row">
	 		<div class="col-sm-5">
 				<img src="../imagens/logomarca.png" class="img-responsive" alt="Portal do Tênis Paraibano" class="img-responsive" width="500" height="500">
			</div>
 			
 			<div class="col-sm-7"><br><br><br><br><br><br>
	 		<p>
			O SIGETE - ${empresa} é um sistema de competições esportivas que está sendo desenvolvido por José Thiago Holanda de Alcântara Cabral. O sistema gerencia tenistas, torneios, rankings, pontuações, jogos, resultados, inscrições etc.<br>
 			<br>
			O ${atleta} ao se cadastrar no sistema estará apto a se inscrever em torneios, participar do ranking, solicitar ser um organizador de torneios, acompanhar seu histórico de jogos, de resultados e pontuações em cada torneio e nível que participar.
			</p> 			
 			
 			</div>
 			
        </div>
        
        
		</div>
		<!--  termino da parte do meio -->

		
		
</div>

    <c:import url="../menu/rodape.jsp"></c:import>

	</div>
	</div>
	</div>

   
    

      <c:import url="../comum/scripts.jsp"/>
   

    
  
</body>

</html>
