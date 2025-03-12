$(function(){

//Tenistas
	$("#cpf").mask("999.999.999-99");	
	$("#dataNascimento").mask("99/99/9999");
	$("#inicioNoTenis").mask("99/99/9999");
	$(".telefone").mask("(99) 9999-9999");
	$("#cep").mask("99999-999");
//Torneios
	$("#dataInicio").mask("99/99/9999");
	$("#dataFim").mask("99/99/9999");
	$("#dataInicioInscricao").mask("99/99/9999");
	$("#dataFimInscricao").mask("99/99/9999");
	//$("#valorInscricao").maskMoney({symbol:'R$ ', showSymbol:true, thousands:'.', decimal:',', symbolStay: true});
	//$("#valorPremiacao").maskMoney({symbol:'R$ ', showSymbol:true, thousands:'.', decimal:',', symbolStay: true});
//Jogos
	$("#dataDoJogo").mask("99/99/9999");
	$("#horaDoJogo").mask("99:99");
//Filiados
	$("#data").mask("99/99/9999");

	
});