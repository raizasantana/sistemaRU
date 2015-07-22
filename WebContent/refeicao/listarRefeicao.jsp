<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="../css/tableStyleGenerator.css">
<title>Lista de refeicoes</title>
</head>
<body>
	<form action="Refeicao" method="post">
		<input type="submit" name="acao" value="Listar"><br />
		<div id="listarRefeicao">
			<table class='CSSTableGenerator'>
				<tr>
					<td>Descrição</td>
					<td>Opção Vegetariana</td>
					<td>Tipo</td>
					<td>Turno</td>
					<td>Ação</td>
				</tr>
				<c:if test="${refeicoes!=null}">
					<c:forEach var="refeicao" items="${refeicoes}">
						<tr>
							<td> ${refeicao.descricao} </td>
							<td> ${refeicao.opcaoVegetariana} </td>
							<td> ${refeicao.tipo} </td>
							<td> ${refeicao.turno} </td>
							<td>  <a href="Refeicao?acao=Editar&id=${refeicao.id}">Editar</a></td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
		</div>
	</form>
</body>
</html>