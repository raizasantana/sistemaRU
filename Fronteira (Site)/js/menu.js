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

		$("#conteudo").load(link, function () {
			$("#conteudo").slideDown();
			$("#listTitle").html("Lista "+table.charAt(0).toUpperCase() + table.slice(1));
			$("#viewElemento").html("");
			// var resposta = [[],[]];
			var resposta = [["id","1","2"],["Nome","Departamento de Ciencia da Computação","Departamento de Tecnologias e Linguagens"],["Sigla","DCC","DTL"]];
			var numCols = 3;
			var numLinhas = 3;
			
			/*
			$.ajax({
					url: "Consulta.jsp",
					type: "POST",
					data: "table="+table,
					success: function(dados) {
					
						// Tratar o retorno da JSP aqui
						// Precisamos recuperar as linhas da tabela, sendo que a primeira linha da matriz deve conter o nome de cada uma das colunas (eg. Nome e Sigla pra departamento)
						// (ex: resposta[0][0] = Nome | resposta [1][0] = Sigla)
						// Precisamos também recuperar o número de colunas e linhas
						// (ex: numCols = 2 | NumLinhas = numero de departamentos no banco)
						
					}
			});
			*/
			
			var SelectedId = 0;
			var tabela = "";
			
			var i = 0, j = 0;
			// Imprimir os "títulos" das colunas
			tabela = "<table class='CSSTableGenerator'><tr><td>Seleção</td>";
			for(i = 1; i < numCols; i++)
				tabela += "<td>" + resposta[i][0] + "</td>";
			tabela += "</tr>";
			
			
			//imprimir as linhas do banco
			for(i = 1; i < numLinhas; i++){
				tabela += "<tr><td><input type='radio' name='id' value='"+resposta[0][i]+"' onclick='{SelectedId = this.value; alert(SelectedId);}'></td>";
				for(j = 1; j < numCols; j++)
					tabela += "<td>" + resposta[j][i] + "</td>";
				tabela += "</tr>";
			}
			
			tabela += "</table><br />";
			tabela += "<button id='create'>Nova</button>";
			tabela += "<button id='edit'>Editar</button>";
			tabela += "<button id='delete'>Deletar</button>";
			
			$("#viewElemento").html(tabela);
			
			function request(){
				// Criar um formulário para criação do elemento pertinente (DEPARTAMENTO)
			}
			
			function request(tipo, id){
				$("#loading").slideDown()
				// Editar e Deletar linhas, 0 = Editar 1 = Deletar
				
				// 0 = Reutilizar o formulário de criação de elemento (DEPARTAMENTO) para editar a linha da tabela, essa função recebe a ID da linha.
				
				// 1 - Deletar a linha da tabela.
				
				$.ajax({
					url: "Consulta.jsp",
					type: "POST",
					data: "tipo="+tipo+"id="+id+"table="+table,
					success: function() {
						$("#loading").slideUp();
						$("#success").slideDown();
						setTimeout($("#success").slideUp(), 3000);
					}
				});
			}
		});
	}