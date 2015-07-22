<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="../css/tableStyleGenerator.css">
<title>Consumidores</title>
</head>
<body>
	<c:if test="${consumidores!=null}">
		
		<p>
			Alunos
			<table class='CSSTableGenerator'>
				<tr>
					<td>Nome</td>
					<td>Matricula</td>
					<td>Ano de ingresso</td>
					<td>CPF</td>
					<td>Curso</td>
				</tr>
	
				<c:forEach var="cons" items="${consumidores}">
					<c:choose>
					<c:when test="${ cons.getClass().name == 'br.ccomp.modelo.Aluno' }">
						<tr>
							<td>${cons.nome }</td>
							<td>${cons.matricula }</td>
							<td>${cons.anoIngresso }</td>
							<td>${cons.cpf}</td>
							<td>${cons.curso.sigla}</td>
						</tr>
					</c:when>
					</c:choose>
				</c:forEach>
			</table>
		</p>
		
		<p>
			Funcionários
			<table class='CSSTableGenerator'>
				<tr>
					<td>Nome</td>
					<td>Matricula</td>
					<td>Ano de ingresso</td>
					<td>CPF</td>
					<td>Departamento</td>
				</tr>
	
				<c:forEach var="cons" items="${consumidores}">
					<c:choose>
					<c:when test="${ cons.getClass().name == 'br.ccomp.modelo.Funcionario' }">
						<tr>
							<td>${cons.nome }</td>
							<td>${cons.matricula }</td>
							<td>${cons.anoIngresso }</td>
							<td>${cons.cpf}</td>
							<td>${cons.departamento.sigla}</td>
						</tr>
					</c:when>
					</c:choose>
				</c:forEach>
			</table>
		</p>
	</c:if>


</body>
</html>