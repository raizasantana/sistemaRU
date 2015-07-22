/**
 * 
 */

function exibeEscondeCampos() 
{
		if(document.getElementById('tipoAluno').checked) 
		{
			document.getElementById("camposAluno").style.display = "block";
			document.getElementById("camposFuncionario").style.display = "none";
		}
		
		else if(document.getElementById('tipoFuncionario').checked) 
		{
			document.getElementById("camposAluno").style.display = "none";
			document.getElementById("camposFuncionario").style.display = "block";
		}
				
}

function carregaCursos()
{
}

function carregaDepartamentos()
{
}