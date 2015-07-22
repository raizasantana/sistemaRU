<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Criar consumidor</title>
</head>
<body onload="carregaCursos();carregaDepartamentos()">
	<form action="consumidor" method="post">
		<table class="TableForm">
			<tr>
				<td colspan=2>Selecione o tipo de Consumidor:</td>
			</tr>
			<tr>
				<td><input type="radio" name="tipo"  id="tipoAluno" value="aluno" checked="checked" onclick="$('#camposAluno').show(); $('#camposFuncionario').hide()"/>Aluno</td>
				<td><input type="radio" name="tipo" id="tipoFuncionario" value="funcionario" onclick="$('#camposAluno').hide(); $('#camposFuncionario').show()"/>Funcionario</td>
			</tr>
			<tr>	
			<td>Nome:</td><td><input type="text" id="nome" name="nome"></td>
			</tr>
			<tr>
			<td>Matricula:</td><td><input type="text" id="matricula" name="matricula"></td>
			</tr>
			<tr>
			<td>Ano de ingresso:</td><td><input type="text" id="ano" name="ano"></td>
			</tr>
			<tr>
			<td>CPF:</td><td><input type="text" id="cpf" name="cpf"></td>
			</tr>
			<tr>
			<td colspan=2>Sexo:</td>
			</tr>
			<tr>
				<td><input type="radio" name="sexo" value="MASCULINO" />Masculino</td>
				<td><input type="radio" name="sexo" value="FEMININO" />Feminino</td>
			</tr>
			</table>
			<!-- Funcionario -->
			<table id="camposFuncionario" style="display: none">	
			<tr>
			<td colspan=2>Departamento:</td>
			</tr><tr>
			<td colspan=2><c:if test="${departamentos!=null}">
				<select id="departamentos" name="departamentos">
					<c:forEach var="departamento" items="${departamentos}">
						<option value=${departamento.id }> ${departamento.sigla }</option>
					</c:forEach>
				</c:if>
				</select></td>
			</tr><tr>
			<td colspan=2>Titulo:</td>
			</tr><tr>
			<td colspan=2><select id="titulos" name="titulos">
					<c:forEach var="titulo" items="${titulos}">
						<option value=${titulo }> ${titulo}</option>
					</c:forEach>
			</select></td>	
			</tr>		
			</table>
			
			<table id="camposAluno">
				<tr>
				<td colspan=2>Curso</td>
				</tr><tr>
				<td colspan=2><select id="cursos" name="cursos">
				<c:if test="${cursos!=null}">
					<c:forEach var="curso" items="${cursos}">
						<option value=${ curso.id }> ${ curso.sigla }</option>
					</c:forEach>
				</c:if>
				</select></td>
				</tr>
			</table>
		<input type="submit" name="acao" value="Criar">
	</form>
</body>
</html>