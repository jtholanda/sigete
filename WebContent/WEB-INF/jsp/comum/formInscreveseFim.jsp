<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

			<div class="row">
				<div class="form-group col-sm-12">
				<hr>
				<h4>Escolha o nível técnico</h4>
				<hr>
							
								<select name="inscricao.nivel.id" id="nivelTecnico" class="form-control">
								  <c:forEach items="${niveisTecnicosDoTorneio}" var="nivelTecnico">								
									  <option value="${nivelTecnico.id}">${nivelTecnico.descricao}</option>
									</c:forEach>
								</select>
								<small>Você só poderá se inscrever em um nível igual ou tecnicamente superior ao seu.<br>
								 Inscrições por idade considerarão o ano que você nasceu.</small>
								
				</div>
			</div>

			<div class="row">
				
				<div class="form-group col-sm-12">
				<hr>
				<h4>Indique alguém que te indicou para esse torneio (a pessoa poderá ser beneficiada):</h4><hr>
				<select name="inscricao.indicacaoInscricao.id" id="indicacaoInscricao" class="form-control">
				<option value="0">Sem indicações</option>
				  <c:forEach items="${indicacoesInscricoes}" var="indicacaoInscricao">								
					  <option value="${indicacaoInscricao.id}">${indicacaoInscricao.descricao}</option>
					</c:forEach>
				</select>
			
				
				</div>
			</div>

			<div class="row">
				<div class="form-group col-sm-12">
				<hr>
				<h4>Como você ficou sabendo do torneio?</h4><hr>
								<select name="inscricao.conhecimentoTorneio" id="conhecimentoTorneio" class="form-control">
								  <option value="NAO_INFORMADO">Selecionar</option>
								  <option value="COM_PROFESSOR">Com professor</option>
								  <option value="NO_CLUBE">No Clube</option>
								  <option value="COM_AMIGO">Com amigos</option>
								  <option value="OUTROS">Outros</option>
								  <option value="POR_EMAIL">E-mail</option>
								</select>
							
							
				</div>
			</div>
				
				<br>
				
			  
			<div class="row">
				<div class="form-group col-sm-12">
				<h4>Horário especial de jogos (evite usar, nem sempre será possível adaptar seus jogos)</h4><br/>
				<textarea class="form-control" name="inscricao.horarioEspecial" maxlength="255"></textarea>
				</div>
			</div>
				
					
				
					<a href="${linkTo[TorneioController].detalhaTorneio(torneio.id)}" class="btn btn-default col-sm-12">Detalhes do torneio</a>
				<br/>
			
				