<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Lista de departamentos</title>
</head>
<body>
	<form action="departamento" method="post">
		<div id="listarDepartamentos" name="acao" value="Listar">
			<table class='CSSTableGenerator'>
				<tr>
					<td>Nome</td>
					<td>Sigla</td>
					<td>Ação</td>
				</tr>
				<c:if test="${departamentos!=null}">
					<c:forEach var="departamento" items="${departamentos}">
						<tr>
							<td> ${departamento.nome} </td>
							<td> ${departamento.sigla} </td>
							<td><center><a data-target="#conteudo" class="button" href="departamento?acao=Editar&id=${departamento.id}">Editar</a><center></center></td>
						</tr>
						
					</c:forEach>
				</c:if>
				
			</table>
			<br/>
			<center><a data-target="#conteudo" class="button" href="departamento?acao=preparaCriar">Novo</a><center>
		</div>
	</form>
	
	<c:if test="${response != null}">
		<c:redirect url="index.jsp#redir=departamento&message=${response}"/>
	</c:if>
</body>
</html>