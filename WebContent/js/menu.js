$(document).ready(function(){
	$("#system_subnav").hide();
	
	var hash = window.location.hash;
	if(hash.substring(1,6) == "redir"){
		myload(hash.substring(7,hash.indexOf("&"))+"?acao=Listar");
		alert(hash.substring(hash.indexOf("&")+9,hash.length));
		window.location.hash = "";
	}
	
	var sysnav = false;
	
	$("#system_nav").mouseenter(function(){
		sysnav = true;
		$("#system_subnav").slideDown();
	});
	
	$("#nav").mouseleave(function(){
		if(sysnav){
			sysnav = false;
			$("#system_subnav").slideUp();
		}
		sysnav = false;
	});
});

function myload(link){
	$("#conteudo").hide("fast");
	$("#conteudo").slideUp();
	$.ajax({
		url: link,
		type: 'GET',
		success: function(result,status,xhr){
			$("#conteudo").html(result);
			$(".button").button().click(function(event) {
				$("#conteudo").hide("fast");
				$("#conteudo").slideUp();
				var target = $($(this).attr('data-target'));
			    target.load($(this).attr('href'),function(){
			    	$("input[type=submit]").button();
			    });
			    $("#conteudo").slideDown();
			    $("#conteudo").show("fast");
				event.preventDefault();
		    });
		}
	});
	$("#conteudo").slideDown();
	$("#conteudo").show("fast");
}