<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
			<div class="row" align="center">
				
				
				<div class="form-group col-sm-12" align="center">
					<c:if test="${dadosDesatualizados}">
					    <a href="${linkTo[TenistaController].formEditaTenista}" class="btn btn-danger">Verifique e atualize seus dados</a>
					</c:if>
					<c:if test="${tenista.naoPossuiCpf}">
						<a href="${linkTo[TenistaController].formEditaTenista}" class="btn btn-danger">CPF não informado, atualize para partipar de sorteios e do ranking.</a>						
					</c:if>
	  			</div>
				<div class="form-group col-sm-12">
				    	    <a href="${linkTo[IndexController].paginaInicial}" class="btn btn-primary">Meu Perfil</a>
				            <a href="${linkTo[PontuacaoController].visualizaMeusPontos}" class="btn btn-primary">Meus pontos</a>
				            <a href="${linkTo[InscricaoController].visualizaMinhasInscricoes}" class="btn btn-primary">Minhas inscrições</a>
				            <a href="${linkTo[TorneioController].visualizaMeusTorneios}" class="btn btn-primary">Meus torneios</a>
				            <a href="${linkTo[JogoDeTorneioController].visualizaMeusJogos(0)}" class="btn btn-primary">Meus jogos</a>
	  			</div>
			</div>
