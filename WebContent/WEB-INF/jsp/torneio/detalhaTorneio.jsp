<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
<meta charset="iso-8859-1">
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<title>${empresa} | Detalhes do torneio</title>
<c:import url="../comum/links.jsp"/>
</head>
<body>
<div class="body_wrapper">
  <div class="center">
	<c:import url="../menu/menu.jsp"></c:import>	

	<div class="container-fluid">


    <div class="content_area">


      
        <div class="single_left_coloum_wrapper">
            <h2 class="title">Torneio</h2>
        </div>

	<h4>${torneio.nome}</h4>
  	<hr>
  	
  	<div class="bs-example bs-example-tabs" role="tabpanel" data-example-id="togglable-tabs">
    
    <ul id="myTab" class="nav nav-tabs" role="tablist">
      <li role="presentation" class="active"><a href="#informacoesbasicas" id="informacoesbasicas-tab" role="tab" data-toggle="tab" aria-controls="informacoesbasicas" aria-expanded="true">Informações básicas</a></li>
      <li role="presentation"><a href="#informacoesDasInscricoes" role="tab" id="informacoesDasInscricoes-tab" data-toggle="tab" aria-controls="informacoesDasInscricoes">Informações das inscrições</a></li>
      <li role="presentation"><a href="#niveisDoTorneio" id="niveisDoTorneio-tab" role="tab" data-toggle="tab" aria-controls="niveisDoTorneio" aria-expanded="true">Níveis técnicos para inscrições</a></li>
      <li role="presentation"><a href="#premiacao" role="tab" id="premiacao-tab" data-toggle="tab" aria-controls="premiacao">Premiação</a></li>
    </ul>
    
    <div id="myTabContent" class="tab-content">
	
	
	<input type="hidden" value="${torneio.id}" name= "torneio.id" /><br>
	<!-- informações do torneio -->
		
		<div role="tabpanel" class="tab-pane fade in active" id="informacoesbasicas" aria-labelledBy="informacoesbasicas-tab">
			<div class="container-fluid">
				<c:import url="../comum/dadosBasicosTorneio.jsp"/>	
			</div>		
		</div>
		
		<div role="tabpanel" class="tab-pane fade" id="informacoesDasInscricoes" aria-labelledBy="informacoesDasInscricoes-tab">
			<div class="container-fluid">
				<c:import url="../comum/dadosInscricoesTorneio.jsp"/>
			</div>
		</div>
		
		<div role="tabpanel" class="tab-pane fade" id="niveisDoTorneio" aria-labelledBy="niveisDoTorneio-tab">
			<div class="container-fluid">
				<c:import url="../comum/dadosNiveisTorneio.jsp"/>
			</div>
		</div>
		
		<div role="tabpanel" class="tab-pane fade" id="premiacao" aria-labelledBy="premiacao-tab">
			<div class="container-fluid">
				<c:import url="../comum/dadosPremiacaoTorneio.jsp"/>
			</div>
		</div>
		<br/>
	
	<!-- apresenta mensagem para o usuario se as inscrições estão pendentes ou pagas -->	
	<c:if test="${tenistaLogado != null}">
		<c:if test="${torneio.situacao.id == 1 || torneio.situacao.id == 2 }">
			<c:if test="${numeroDePendencias > 0 }">
				<h3>ATENÇÃO!</h3><br/>
				<c:if test="${numeroDePendencias == 1 }">			
					<h5 style="color:red">Você tem ${numeroDePendencias} inscrição não paga neste torneio.<br><br> Se o pagamento foi efetuado direto ao organizador, solicite a confirmação por e-mail com comprovante para ${email} ou espere o organizador confirmar.</h5><br>
				</c:if>
				<c:if test="${numeroDePendencias > 1 }">			
					<h5 style="color:red">Você tem ${numeroDePendencias} inscrições não pagas neste torneio.<br><br> Se o pagamento foi efetuado direto ao organizador, solicite a confirmação por e-mail com comprovante para ${email} ou espere o organizador confirmar.</h5><br>
				</c:if>
			</c:if>
			<c:if test="${numeroDePendencias == 0 }">
				<h5 style="color:green">A(s) sua(s) inscrição(ões) está(ão) paga(s)!</h5><br>
			</c:if>
		</c:if>
	</c:if>
	<div class="form-group col-sm-12">
		<c:forEach var="inscricao" items="${inscricoesDoTenista}">
		<c:if test="${!inscricao.paga && inscricao.torneio.chaveGerada == false && inscricao.torneio.situacao.id != 3}">
			<a href="${linkTo[InscricaoController].removeInscricaoPessoal(inscricao.id)}"	class="btn btn-danger">Remover minha Inscrição na ${inscricao.nivel.descricao}</a>
		</c:if>	
		</c:forEach>
	</div>
	<br>
	<br>
	<div class="form-group col-sm-12">
	<c:if test="${quantidadeInscricoesTenista == 1 }">
		<c:if test="${!inscricao.tenista.isFiliado }">
			${torneio.botaoPagamento}
		</c:if>
		<c:if test="${inscricao.tenista.isFiliado }">
			${torneio.botaoPagamentoFiliado}
		</c:if>
	</c:if>
	<c:if test="${quantidadeInscricoesTenista == 2 }">
		${torneio.botaoSegundoPagamento}
	</c:if>
	<c:if test="${quantidadeInscricoesTenista == 3 }">
		${torneio.botaoTerceiroPagamento}
	</c:if>
	</div>
	
	<br/><br/>
	
	<div class="form-group col-sm-12">
	<!-- apresenta mensagem se o tenista deseja cancelar a sua inscrição de duplas -->
	<c:if test="${torneio.duplas}"><small style="color:red">Se desejar cancelar a sua dupla envie e-mail para ${email_empresa}</small><br/><br/></c:if>
	<!-- botões de gerenciamento básico -->
	
	<div class="btn-toolbar " align="center">				
		<c:if test="${inscricoesAbertas && torneio.chaveGerada == false && torneio.situacao.id == 1 && torneio.aberto == true }">
			<c:if test="${torneio.simples}">
				<a href= "${linkTo[InscricaoController].formInscreveSeEmTorneio(torneio.id)}"
				 class="btn btn-success col-sm-2" >Inscrever-se</a>
			</c:if>
			<c:if test="${torneio.duplas}">
				<a href= "${linkTo[InscricaoController].formInscreveSeEmTorneioDeDuplas(torneio.id)}"
				 class="btn btn-success col-sm-2" >Inscrever-se</a>
			</c:if>
			<c:if test="${torneio.simplesEDuplas}">
				<a href= "${linkTo[InscricaoController].formInscreveSeEmTorneioDeSimplesEDuplas(torneio.id)}"
				 class="btn btn-success col-sm-2" >Inscrever-se</a>
			</c:if>

		</c:if>
		
		<c:if test="${chaveamentoPDF !=null}">
			<a href="${chaveamentoPDF}"	class="btn btn-primary col-sm-2" target="_blank">Download do chaveamento</a>
		</c:if>
		
		<c:if test="${torneio.chaveGerada && torneio.situacao.id <= 3 }">
			<a href= "${linkTo[TorneioController].geraChavesDoTorneio(torneio.id)}"
			 class="btn btn-primary col-sm-2" >Chaveamento</a>
		</c:if>
		
		<c:if test="${torneio.situacao.id <= 3 }">
			<a href= "${linkTo[JogoDeTorneioController].visualizaJogosDoTorneio(torneio.id,false)}" class="btn btn-primary col-sm-2" >Jogos</a>
		</c:if>
			 
			<a href= "${linkTo[InscricaoController].visualizaInscritosNoTorneio(torneio.id, false)}" class="btn btn-primary col-sm-2" >Inscritos</a>

			<a href= "${linkTo[ResultadoController].panelResultadoDoTorneio(torneio.id)}" class="btn btn-primary col-sm-2" >Resultados</a>

			<c:if test="${torneio.isMisto2Estagios() && torneio.situacao.id <= 3 }">
				<a href= "${linkTo[ResultadoController].panelClassificadosFaseDeGrupo(torneio.id)}" class="btn btn-primary col-sm-2" >Classificados</a>
			</c:if>

			
			<a href= "${linkTo[MidiaTorneioController].visualizaMidiasDoTorneio(torneio.id)}" class="btn btn-primary col-sm-1">Fotos</a>
			<c:if test="${regulamento!=null}">
				<a href= "${regulamento}" class="btn btn-primary" target="_blank">Ver regulamento</a>
			</c:if>
	</div>
	
	</div>
	
	<div class="form-group col-sm-12">
	
	<!-- botões de gerenciamento do administrador do torneio a iniciar -->
			<c:if test="${(administrador || (tenistaLogado != null && (torneio.tenistaOrganizador.id == tenistaLogado.id || torneio.tenistaAuxiliar1.id == tenistaLogado.id || torneio.tenistaAuxiliar2.id == tenistaLogado.id))) && torneio.situacao.id == 1}">
			<br>
			
			<div class="panel panel-default">
			<div class="panel-heading">Painel administrativo do torneio</div><br>
			<div class="btn-toolbar " align="center">
				
				<c:if test="${torneio.simples}">
					<a href="${linkTo[InscricaoController].formInscreveTenistaEmTorneio(torneio.id)}" class="btn btn-default col-sm-2" type="sub">Inscrever tenista</a>
				</c:if>
				<c:if test="${torneio.duplas }">
					<a href="${linkTo[InscricaoController].formInscreveTenistasEmTorneioDeDuplas(torneio.id)}" class="btn btn-default col-sm-2" type="sub">Inscrever tenista</a>
				</c:if>									 
				<c:if test="${torneio.simplesEDuplas }">
					<a href="${linkTo[InscricaoController].formInscreveTenistasEmTorneioDeSimplesEDuplas(torneio.id)}" class="btn btn-default col-sm-2" type="sub">Inscrever tenista</a>
				</c:if>									 
					
					<a href= "${linkTo[TorneioController].ajustaSituacaoDoTorneio(torneio.id, torneio.nome, INICIA_TORNEIO)}" class="btn btn-default col-sm-2" >Iniciar torneio</a>
					
					<a href= "${linkTo[TorneioController].formEditaDadosDoTorneio(torneio.id)}" class="btn btn-default col-sm-2" >Alterar dados</a>
					
					<a href= "${linkTo[InscricaoController].panelGerenciaInscricoes(torneio.id)}" class="btn btn-default col-sm-2" >Gerenciar inscrições</a>
					
					<a href= "${linkTo[InscricaoController].visualizaInscritosNoTorneio(torneio.id, true)}" class="btn btn-default col-sm-2" >Imprimir inscritos</a>

					<a href= "${linkTo[MidiaTorneioController].formEnviaMidiaTorneio(torneio.id)}" class="btn btn-default col-sm-2">Enviar mídias</a>
					
					<a href= "${linkTo[TorneioController].ajustaSituacaoDoTorneio(torneio.id, torneio.nome, CANCELA_TORNEIO)}" class="btn btn-default col-sm-2" >Cancelar torneio</a>
					
					
			</div>
			<br>
			</div>
			</c:if>
	
	<!-- botões de gerenciamento do administrador do torneio iniciado -->
			<c:if test="${(administrador || (tenistaLogado != null && torneio.tenistaOrganizador.id == tenistaLogado.id)) && torneio.situacao.id == 2}">
			<br>
			<div class="panel panel-default">
			<div class="panel-heading">Painel administrativo do torneio</div><br>
			<div class="btn-toolbar " align="center">
			

			
					<c:if test="${torneio.simples}">					
						<a href="${linkTo[JogoDeTorneioController].formGerenciaJogo(torneio.id)}" class="btn btn-default col-sm-2" type="sub">Gerenciar jogos</a>
					</c:if>
					<c:if test="${torneio.duplas}">					
						<a href="${linkTo[JogoDeTorneioController].formGerenciaJogoDeDuplas(torneio.id)}" class="btn btn-default col-sm-2" type="sub">Gerenciar jogos</a>
					</c:if>
					<c:if test="${torneio.simplesEDuplas}">					
						<a href="${linkTo[JogoDeTorneioController].formGerenciaJogoDeDuplas(torneio.id)}" class="btn btn-default col-sm-2" type="sub">Gerenciar jogos</a>
					</c:if>
					
					<a href= "${linkTo[InscricaoController].panelGerenciaInscricoesConfirmadas(torneio.id)}" class="btn btn-default col-sm-2" >Gerenciar resultados</a>
					
					
					<a href= "${linkTo[TorneioController].formEditaDadosDoTorneio(torneio.id)}" class="btn btn-default col-sm-2" >Alterar dados</a>
					
					
					<c:if test="${torneio.chaveGerada}">
						<a href= "${linkTo[TorneioController].geraChavesDoTorneio(torneio.id)}"
					 		class="btn btn-default col-sm-2" >Ver chaves</a>
					</c:if>
					<c:if test="${administrador}">					
						<a href= "${linkTo[InscricaoController].panelGerenciaInscricoes(torneio.id)}" class="btn btn-default col-sm-2" >Gerenciar inscrições</a>
					</c:if>
					
					<c:if test="${!torneio.chaveGerada}">
						<a href= "${linkTo[TorneioController].geraChavesDoTorneio(torneio.id)}" class="btn btn-default col-sm-2" >Gerar chave</a>
					</c:if>
					
					
					<a href="${linkTo[TorneioController].ajustaSituacaoDoTorneio(torneio.id, torneio.nome, REINICIA_TORNEIO)}" class="btn btn-default col-sm-2" type="sub">Reiniciar torneio</a>

					<a href= "${linkTo[MidiaTorneioController].formEnviaMidiaTorneio(torneio.id)}" class="btn btn-default col-sm-2">Enviar mídias</a>

					<c:if test="${torneio.misto2Estagios}">
						<a href= "${linkTo[TorneioController].iniciaSegundoEstagioDoTorneio(torneio.id)}"
					 		class="btn btn-default col-sm-3" >Iniciar segundo estágio</a>
					</c:if>
					<c:if test="${torneio.misto2Estagios && !torneio.iniciouSegundoEstagio}">
						<a href= "${linkTo[TorneioController].liberaChavesFinais(torneio.id)}"
					 		class="btn btn-default col-sm-3" >Liberar chaves eliminatórias</a>
					</c:if>
	
		
					<a href= "${linkTo[InscricaoController].visualizaInscritosNoTorneio(torneio.id, true)}" class="btn btn-default col-sm-2" >Imprimir inscritos</a>
					<a href= "${linkTo[TorneioController].ajustaSituacaoDoTorneio(torneio.id, torneio.nome, CONCLUI_TORNEIO)}" class="btn btn-default col-sm-2" >Concluir torneio</a>
					<a href= "${linkTo[TorneioController].ajustaSituacaoDoTorneio(torneio.id, torneio.nome, CANCELA_TORNEIO)}" class="btn btn-default col-sm-2" >Cancelar torneio</a>
				
			</div>
			<br>
			
			</div>
			</c:if>
	<!-- botões de gerenciamento do administrador geral do torneio concluido -->
			<c:if test="${(administrador || (tenistaLogado != null && torneio.tenistaOrganizador.id == tenistaLogado.id)) && torneio.situacao.id == 3}">
			<br>
			<div class="panel panel-default">
			<div class="panel-heading">Painel administrativo do torneio</div><br>
			<div class="btn-toolbar">
			

			
					<c:if test="${torneio.simples}">					
						<a href="${linkTo[JogoDeTorneioController].formGerenciaJogo(torneio.id)}" class="btn btn-default col-sm-2">Gerenciar jogos</a>
					</c:if>
					<c:if test="${torneio.duplas }">					
						<a href="${linkTo[JogoDeTorneioController].formGerenciaJogoDeDuplas(torneio.id)}" class="btn btn-default col-sm-2">Gerenciar jogos</a>
					</c:if>
					<c:if test="${torneio.simplesEDuplas }">					
						<a href="${linkTo[JogoDeTorneioController].formGerenciaJogoDeDuplas(torneio.id)}" class="btn btn-default col-sm-2">Gerenciar jogos</a>
					</c:if>
					
					<c:if test="${administrador}">
						<a href= "${linkTo[InscricaoController].panelGerenciaInscricoesConfirmadas(torneio.id)}" class="btn btn-default col-sm-2" >Gerenciar resultados</a>
					</c:if>
					
					
					<c:if test="${administrador}">
						<a href= "${linkTo[TorneioController].formEditaDadosDoTorneio(torneio.id)}" class="btn btn-default col-sm-2" >Alterar dados</a>
					</c:if>
										
					<c:if test="${administrador}">
					<c:if test="${torneio.chaveGerada}">
						<a href= "${linkTo[TorneioController].geraChavesDoTorneio(torneio.id)}"
					 		class="btn btn-default" >Ver chaves</a>
					</c:if>
					
					<c:if test="${!torneio.chaveGerada}">
						<a href= "${linkTo[TorneioController].geraChavesDoTorneio(torneio.id)}" class="btn btn-default col-sm-2" >Gerar chave</a>
					</c:if>
					</c:if>					
					
					<a href="${linkTo[TorneioController].ajustaSituacaoDoTorneio(torneio.id, torneio.nome, REINICIA_TORNEIO)}" class="btn btn-default col-sm-2" type="sub">Reiniciar torneio</a>

					<a href= "${linkTo[MidiaTorneioController].formEnviaMidiaTorneio(torneio.id)}" class="btn btn-default col-sm-2">Enviar mídias</a>

					<a href= "${linkTo[InscricaoController].visualizaInscritosNoTorneio(torneio.id, true)}" class="btn btn-default col-sm-2" >Imprimir inscritos</a>
						
					<c:if test="${administrador}">
					<a href= "${linkTo[TorneioController].ajustaSituacaoDoTorneio(torneio.id, torneio.nome, CANCELA_TORNEIO)}" class="btn btn-default col-sm-2" >Cancelar torneio</a>
					</c:if>
				
			</div>
			<br>
			
			</div>
			</c:if>
		</div>
		
		<div class="container">
				
		<div class="form-group col-sm-12">
		<h4>Apoio:</h4>

		<c:forEach var="patrocinio" items="${patrocinios}" varStatus="loop">
				<div class="col-sm-6"><img src="${patrocinio}" class="img-responsive" alt="Patrocínio" height="250" width="250"></div>
				<c:if test="${loop.count >=8}"> <br></c:if>
		</c:forEach>

		</div>
		</div>	
		</div>
		</div>
		
		
		</div>
				<c:import url="../menu/rodape.jsp"/>
		
		
</div>
</div>
</div>
		<c:import url="../comum/scripts.jsp"/>
</body>
</html>