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
  

    <title>${empresa}</title>

   <c:import url="../comum/links.jsp"/>
    

</head>

<body>

<c:import url="../menu/menu.jsp"/>

<div class="container-fluid"> 

    <!-- Portfolio Grid Section -->
    <section>
    <div class="container-fluid">
      
     <!-- Lateral esquerda -->
      <div class="col-lg-2">
        <c:import url="../menu/lateral_esquerda.jsp"/>
   	  </div>
    	<!-- termino da lateral esquerda -->
    
    	<!-- Parte do conteúdo-->
       <div class="col-lg-8 ">
          <hr>
    
        
           <div class="col-lg-12 text-center">
                    
            <!--  publicidade central -->
            <div class="row" align="center">
           
                <div class="col-sm-1 portfolio-item">
                	<br>
                	<img src="imagens/logomarca_bolinha.png" class="img-responsive" alt="">
                  </div>
                  <div class="col-sm-1 portfolio-item">
                	<br>
                	<img src="imagens/logomarca_bolinha.png" class="img-responsive" alt="">
                  </div>
                  <div class="col-sm-1 portfolio-item">
                	<br>
                	<img src="imagens/logomarca_bolinha.png" class="img-responsive" alt="">
                  </div>
                  
                  <div class="col-sm-6 portfolio-item">
                	<br>
                	<img src="imagens/anuncie_aqui_banner_horizontal_grande.jpg" class="img-responsive" alt="">
                  </div>
                  <div class="col-sm-1 portfolio-item">
                	<br>
                	<img src="imagens/logomarca_bolinha.png" class="img-responsive" alt="">
                  </div>
                   <div class="col-sm-1 portfolio-item">
                	<br>
                	<img src="imagens/logomarca_bolinha.png" class="img-responsive" alt="">
                  </div>
                  
                  <div class="col-sm-1 portfolio-item">
                	<br>
                	<img src="imagens/logomarca_bolinha.png" class="img-responsive" alt="">
                  </div>
                  
               </div>
            
            <!-- estrelas de destaques -->
               <div class="row" align="center">
                 <div class="col-sm-4 portfolio-item">
                     <hr class="star-primary">
                  </div>
                   <div class="col-sm-4 portfolio-item">
                              <hr class="star-primary">
                  </div>
           		<div class="col-sm-4 portfolio-item">
                              <hr class="star-primary">
               </div>
            </div>
    		
    		<!-- banners centrais -->        
            <div class="row">
                <div class="col-sm-4 portfolio-item">
                    <a href="torneio/detalhes-do-torneio/34" class="portfolio-link" data-toggle="modal">
                      
                        <img src="imagens/banner_01.jpg" class="img-responsive" alt="" height="500" width="700">
                    </a>
                </div>
                <div class="col-sm-4 portfolio-item">
                    <a href="torneio/detalhes-do-torneio/35" class="portfolio-link" data-toggle="modal">
                        
                        <img src="imagens/banner_02.jpg" class="img-responsive" alt="" height="500" width="700">
                    </a>
                    
                    
                </div>
                <div class="col-sm-4 portfolio-item">
                    <a href="http://www.cbtenis.com.br/site.aspx/Torneios" class="portfolio-link" data-toggle="modal" target="blank">
                        
                        <img src="imagens/banner_03.jpg" class="img-responsive" alt="" height="500" width="700">
                    </a>
                </div>
              </div>
               <br>
        </div>
		</div>
		<!--  termino da parte do meio -->

		<!-- lateral direita -->		
		<div class="col-lg-2 ">
        	        <c:import url="../menu/lateral_direita.jsp"/>
        	
		</div>
		<!--  termino do container -->

 <!-- Marketing Icons Section -->
        <div class="row">
            <div class="col-md-4">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4><i class="fa fa-fw fa-check"></i>Cadastro</h4>
                    </div>
                    <div class="panel-body">
                        <p>Realize o seu cadastro no portal do tênis paraibano e fique por dentro de todas as competições de tênis que ocorrem no estado, além de participar do ranking paraibano de tenistas.</p>
                        <a href="${linkTo[TenistaController].formCadastro}" class="btn btn-default">Cadastre-se</a>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4><i class="fa fa-fw fa-list"></i>Inscrições abertas</h4>
                    </div>
                    <div class="panel-body">
                        <p>Verifique as competições de tênis no estado que estão com as inscrições abertas, faça a sua inscrição pelo site e entre em contato com a organização para realizar o pagamento.</p>
                        <a href="${linkTo[InscricaoController].realizaUmaInscricao}" class="btn btn-default">Inscrever-me</a>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4><i class="fa fa-fw fa-search"></i>Tenistas</h4>
                    </div>
                    <div class="panel-body">
                        <p>Pesquise os tenista cadastrados e analise os dados técnicos de cada um. Assim você também poderá buscar mais informações do seu adversário e conhecê-lo melhor.</p>
                        <a href="${linkTo[IndexController].mensagem}"class="btn btn-default">Pesquisar</a>
                    </div>
                </div>
            </div>
        </div>
        <!-- /.row -->
		<!--  publicidade simples -->
		<div class="row" align="center">
				<div class="col-sm-4"></div>
		
				<div class="col-sm-1"><a href="http://www.fpbtenis.com.br/index.php" target="blank"><img src="imagens/empresasParceiras/logo_fpt.jpg" class="img-responsive" alt="" height="70" width="70"></a></div>
				<div class="col-sm-1"><a href="http://www.pbtenis.com.br/" target="blank"><img src="imagens/empresasParceiras/logo_pbtenis.png" class="img-responsive" alt="" height="100" width="100"></a></div>
				<div class="col-sm-1"><a href="https://www.facebook.com/pages/Pet-Shop-Hobby-Bichos/181027502003475" target="blank"><img src="imagens/empresasParceiras/logo_hb.jpg" class="img-responsive" alt="" height="100" width="100"></a></div>
				<div class="col-sm-1"><a href="https://www.facebook.com/EraUmaVezBabyKids?fref=ts" target="blank"><img src="imagens/empresasParceiras/logo_euv.jpg" class="img-responsive" alt="" height="200" width="200"></a></div>
				<div class="col-sm-1"><a href="https://www.facebook.com/CanoAcai?fref=ts" target="blank"><img src="imagens/empresasParceiras/logo_canoaacai.jpg" class="img-responsive" alt="" height="100" width="100"></a></div>
				
				<div class="col-sm-3"></div>


		</div>		
</div>
</section>

   
    
    <c:import url="../menu/rodape.jsp"></c:import>

      <c:import url="../comum/scripts.jsp"/>
   

    
 </div> 
</body>

</html>
