<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- Footer -->
    <footer class="text-center">
        <div class="footer-above">
            <div class="container-fluid">
                <div class="row">
                    <div class="footer-col col-md-12">

							<c:if test="${tenistaLogado != null}"> 
										<div class="form-group col-sm-2">
											<h3>Minha conta</h3>
												<a href="${linkTo[IndexController].paginaInicial}"  >Meu Perfil</a> <br>
												<a href="${linkTo[TenistaController].formEditaTenista}"  >Alterar meus dados</a> <br>
												<a href="${linkTo[TenistaController].formAlteraSenha}"   >Alterar senha</a> <br>
												<a href="${linkTo[MaisController].formSolicitaMudancaNivel}"   >Solicitar mudança de nível técnico</a> <br>
												<a href="${linkTo[TenistaController].logout}" type="button" >Sair</a> 
										</div>
								</c:if>
							
								 	 <c:if test="${tenistaLogado == null}">
										<div class="form-group col-sm-2">
										<h3>Minha conta</h3>
												<a href="${linkTo[TenistaController].login}"  >Acessar minha conta</a> <br>
										</div>
									</c:if>
										<div class="form-group col-sm-2">
										 <h3>Tenistas</h3>
			  						
												 <a href="${linkTo[TenistaController].formCadastro}">Cadastro</a><br>
												 <a href="${linkTo[TenistaController].formProcuraTenistas}" >Procurar tenistas</a><br>
												 <a href="${linkTo[TenistaController].visualizaFiliados}">Filiados</a><br>
										
											 <c:if test="${tenistaLogado != null}">
									             <a href="${linkTo[IndexController].paginaInicial}">Meu perfil</a>
												 <a href="${linkTo[PontuacaoController].visualizaMeusPontos}">Meus pontos</a><br>
												 <a href="${linkTo[InscricaoController].visualizaMinhasInscricoes}">Minhas inscrições</a><br>
												 <a href="${linkTo[TorneioController].visualizaMeusTorneios}">Meus Torneios</a><br>
												 <a href="${linkTo[JogoDeTorneioController].visualizaMeusJogos}">Meus jogos</a><br>
											</c:if>
										</div>
									
										<div class="form-group col-sm-2">
										 <h3>Competições</h3>
										 
											 <a href="${linkTo[TorneioController].visualizaTorneios(0,0)}" >Torneios do ranking paraibano</a><br>
											 <a href="${linkTo[TorneioController].visualizaTorneios(1,0)}" >Etapas de ranking interno de clubes</a><br>
											 <a href="${linkTo[InscricaoController].realizaUmaInscricao}">Realizar minha inscrição</a><br> 
											 <a href="${linkTo[InscricaoController].visualizaUltimosInscritos}" >Últimos inscritos</a><br>
					 			             <a href="${linkTo[MidiaTorneioController].visualizaMidiasDosTorneios}">Mídias</a>
											 
										</div>
									
										<div class="form-group col-sm-2">
										 <h3>Ranking</h3>
										
											 <a href="${linkTo[RankingController].visualizaRankingGeral}"  >Ranking paraibano</a><br>
											 <a href="${linkTo[RankingController].visualizaRankingPorNivelTecnico}"  >Ranking por classes</a><br>
											 <a href="${linkTo[RankingController].visualizaRankingPorIdade}"  >Ranking por idade</a> <br>
											 <a href="${linkTo[RankingController].visualizaRankingFemininoPorNivelTecnico}" >Ranking feminino por classe</a><br>
											 <a href="${linkTo[RankingController].visualizaRankingDeDuplasPorNivelTecnico}" >Ranking de duplas</a><br>
											 <a href="/sigete/regulamento2016.pdf" target="_blank">Regulamento do ranking</a><br>
										 
	
										</div>
										<div class="form-group col-sm-2">
										 <h3>Mais</h3>
									            <a href="${linkTo[MaisController].federacao}">Federação paraibana</a><br/>
									            <a href="${linkTo[MaisController].filiacao}">Filiar-se a FPbT</a><br/>
									            <a href="${linkTo[MaisController].clubes}">Clubes</a><br/>
									            <a href="${linkTo[MaisController].professores}">Professores</a><br/>
									            <a href="${linkTo[MaisController].empresasParceiras}">Empresas parceiras</a><br/>
							  	                <a href="${linkTo[InformativoController].visualizaNoticiasLocais}">Tênis local</a><br/>
									            <a href="${linkTo[InformativoController].visualizaNoticiasInternacionais}">Tênis no mundo</a><br/>
									            <a href="${linkTo[InformativoController].visualizaDicas}">Dicas</a><br/>
									            <a href="${linkTo[MaisController].sobre}">Sobre</a>
										
	
										</div>
										<div class="form-group col-sm-2">
				                        <h3>Contatos</h3>
				                   
				                        
				                                <a href="https://www.facebook.com/portaldotenispb?fref=ts" target="blank" class="btn-social btn-outline"><i class="fa fa-fw fa-facebook"></i></a><br>
				                                <h6>portaldotenispb@gmail.com</h6>
				                                
				                                
				                         
				                  
					                    </div>
									</div>
									   
									
									
                    </div>
                 
                    
                </div>
            </div>
        
        <div class="footer-below">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12">
                        Copyright &copy; www.portaldotenispb.com.br 2015 <br>
                        By jtholanda Tecnologia
                    </div>
                </div>
            </div>
        </div>
        
    </footer>



    
