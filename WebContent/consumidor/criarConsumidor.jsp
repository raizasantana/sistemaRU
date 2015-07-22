<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="jsConsumidor.js"></script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Criar consumidor</title>
</head>
<body onload="carregaCursos();carregaDepartamentos()">
	<form action="consumidor" method="post">
	<input type="hidden" name="acao" value="Criar" id="acao"/>
		<p>Selecione o tipo de Consumidor: 
			<input type="radio" name="tipo"  id="tipoAluno" value="aluno" checked="checked"   onchange="exibeEscondeCampos()"/>Aluno
			<input type="radio" name="tipo" id="tipoFuncionario" value="funcionario"  onchange="exibeEscondeCampos()"/>Funcionario
		</p>
			
		<p>Nome: <input type="text" id="nome" name="nome"></p>
		<p>Matricula: <input type="text" id="matricula" name="matricula"></p>
		<p>Ano de ingresso: <input type="text" id="ano" name="ano"></p>
		<p>Sexo:</p>
			<input type="radio" name="sexo" value="MASCULINO" />Masculino
			<input type="radio" name="sexo" value="FEMININO" checked/>Feminino
		<p>CPF: <input type="text" id="cpf" name="cpf"></p>
		
		<!-- Funcionario -->
		<div id="camposFuncionario" style="display: none">	
		<p>Departamento: 
		<c:if test="${departamentos!=null}">
			<select id="departamentos" name="departamentos">
				<c:forEach var="departamento" items="${departamentos}">
					<option value=${departamento.id }> ${departamento.sigla }</option>
				</c:forEach>
			</c:if>
			</select>
		</p>
		
		<p>Titulo: <select id="titulos" name="titulos">
				<c:forEach var="titulo" items="${titulos}">
					<option value=${titulo }> ${titulo}</option>
				</c:forEach>
		</select></p>			
		</div>
		
		<div id="camposAluno">
			<p>Curso 
			<select id="cursos" name="cursos">
			<c:if test="${cursos!=null}">
				<c:forEach var="curso" items="${cursos}">
					<option value=${ curso.id }> ${ curso.sigla }</option>
				</c:forEach>
			</c:if>
		</select>
			 </p>
		</div>
		<input type="submit"  value="Criar Consumidor">
		
	
	</form>
</body>
</html>