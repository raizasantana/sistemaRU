	$(document).ready(function(){
			
		$("#system_subnav").hide();
		myload('home.html');
		
		
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
		$("#conteudo").slideUp();
		$("#conteudo").load(link);
		$("#conteudo").slideDown();
	}
	
	function elemload(link,table){
		$("#conteudo").slideUp();
		$("#conteudo").load(link);
		$("#conteudo").slideDown();
		
		$("#viewElemento").innerHTML = "";
		var resposta = [[],[]];
		var numCols = 0;
		var numLinhas = 0;
		
		var SelectedId = 0;
		
		var i = 0, j = 0;
		
		// Imprimir os "títulos" das colunas
		$("#viewElemento").innerHTML = "<table><tr><td>Seleção</td>";
		for(i = 1; i < numCols; i++)
			$("#viewElemento").innerHTML += "<td>" + resposta[i][0] + "</td>";
		$("#viewElemento").innerHTML += "</tr>";
		
		
		alert("oi?");
		//imprimir as linhas do banco
		for(i = 1; i < numLinhas; i++){
			$("#viewElemento").innerHTML += "<tr><td><input type='radio' name='id' value='"+resposta[0][i]+"' onclick='function ({SelectedId = this.value});'></td>";
			for(j = 1; i < numCols; i++)
				$("#viewElemento").innerHTML += "<td>" + resposta[i][j] + "</td>";
			$("#viewElemento").innerHTML += "</tr>";
		}
		
		$("#viewElemento").innerHTML += "</table><br /><button id='create' value='Criar Nova Entrada' onclick='request()' /> <button id='edit' value='Editar Entrada Selecionada' /> <button id='delete' value='Deletar Entrada Selecionada' />";
		
		function request(){
			// Criar um formulário para criação do elemento pertinente (DEPARTAMENTO)
		}
		
		function request(type, id){
			$("#loading").slideDown()
			// Editar e Deletar linhas, 0 = Editar 1 = Deletar
			
			// 0 = Reutilizar o formulário de criação de elemento (DEPARTAMENTO) para editar a linha da tabela, essa função recebe a ID da linha.
			
			// 1 - Deletar a linha da tabela.
			
			$.ajax({
				url: "js/editarElemento.jsp",
				data: {type,id,table},
				success: function() {
					$("#loading").slideUp();
					$("#success").slideDown();
					setTimeout($("#success").slideUp(), 3000);
					}
			});
		}
	}