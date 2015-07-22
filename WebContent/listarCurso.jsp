<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Lista de cursos</title>
</head>
<body>
	<div id="listarCursos" name="acao" value="Listar">
		<table class='CSSTableGenerator'>
			<tr>
				<td>Nome</td>
				<td>Sigla</td>
				<td>Departamento</td>
				<td>Sigla do departamento</td>
				<td>Ação</td>
			</tr>
			<c:if test="${cursos!=null}">
				<c:forEach var="curso" items="${cursos}">
					<tr>
						<td> ${curso.nome} </td>
						<td> ${curso.sigla} </td>
						<td> ${curso.departamento.nome} </td>
						<td> ${curso.departamento.sigla} </td>
						<td><center><a data-target="#conteudo" class="button" href="curso?acao=Editar&id=${curso.id}">Editar</a></center></td>
					</tr>
					
				</c:forEach>
			</c:if>
			
		</table>
		<br/>
		<center><a data-target="#conteudo" class="button" href="curso?acao=carregarCriarCurso">Novo</a><center>
	</div>

	<c:if test="${response != null}">
		<c:redirect url="index.jsp#redir=curso&message=${response}"/>
	</c:if>
</body>
</html>