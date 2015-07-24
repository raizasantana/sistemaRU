<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Lista de refeicoes</title>
</head>
<body>
	<form action="Refeicao" method="post">
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
							<td><center><a data-target="#conteudo" class="button" href="refeicao?acao=Editar&id=${refeicao.id}">Editar</a><center></td>
						</tr>
					</c:forEach>
				</c:if>
			</table>
			<br/>
			<center><a data-target="#conteudo" class="button" href="refeicao?acao=carregarTurno">Nova</a><center>
			
			<c:if test="${response != null}">
				<c:redirect url="index.jsp#redir=refeicao&message=${response}"/>
			</c:if>
		</div>
	</form>
</body>
</html>