<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Editar consumidor</title>
</head>
<body onload="carregaCursos();carregaDepartamentos()">
	<form action="consumidor" method="post">
		<input type="text" id="id" name="id" value="${consumidor.id}" style="display:none">
		<table class="TableForm">
			<tr>
				<td colspan=2>Tipo de Consumidor:</td>
			</tr><tr>
			<c:choose>
				<c:when test="${ consumidor.getClass().name == 'br.ccomp.modelo.Aluno' }">
						<td colspan=2><input type="radio" name="tipo"  id="tipoAluno" value="aluno" checked />Aluno</td>
					</c:when>
					<c:otherwise>
						<td colspan=2><input type="radio" name="tipo" id="tipoFuncionario" value="funcionario" checked />Funcionario</td>
					</c:otherwise>
				</c:choose>
			</tr>
			<tr>	
				<td>Nome:</td><td><input type="text" id="nome" name="nome" value="${consumidor.nome }"></td>
				</tr>
				<tr>
				<td>Matricula:</td><td><input type="text" id="matricula" name="matricula" value="${consumidor.matricula }"></td>
				</tr>
				<tr>
				<td>Ano de ingresso:</td><td><input type="text" id="ano" name="ano" value="${consumidor.anoIngresso}"></td>
				</tr>
				<tr>
				<td>CPF:</td><td><input type="text" id="cpf" name="cpf" value="${consumidor.cpf }"></td>
				</tr>
				<tr>
				<td colspan=2>Sexo:</td>
				</tr>
				<tr>
				<c:choose>
					<c:when test="${consumidor.sexo == 'FEMININO'}">
						<td><input type="radio" name="sexo" value="MASCULINO" />Masculino</td>
						<td><input type="radio" name="sexo" value="FEMININO" checked />Feminino</td>
					</c:when>
					<c:otherwise>
						<td><input type="radio" name="sexo" value="MASCULINO" checked />Masculino</td>
						<td><input type="radio" name="sexo" value="FEMININO" />Feminino</td>
				</c:otherwise>
				</c:choose>
				</tr>
			</table>
			<c:choose>
			<c:when test="${ consumidor.getClass().name == 'br.ccomp.modelo.Aluno' }">
				<table id="camposAluno">
					<tr>
						<td colspan=2>Curso</td>
					</tr><tr>
						<td><input type="text" id="nome" name="nome" value="${consumidor.curso.sigla }" disable></td>
					</tr>
				</table>
				
			</c:when>
			<c:otherwise>
				<table id="camposFuncionario">
					<tr>
					<td colspan=2>Departamento:</td>
					</tr><tr>
					<td><input type="text" id="nome" name="nome" value="${consumidor.departamento.sigla }" disable></td>
					</tr><tr>
					<td colspan=2>Titulo:</td>
					</tr><tr>
					<td><input type="text" id="nome" name="nome" value="${consumidor.titulo}" disable></td>
					</tr>
				</table>
			</c:otherwise>
			</c:choose>
			
			<input type="submit" name="acao" value="Alterar">
	</form>
</body>
</html>