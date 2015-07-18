<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Lista de tickets</title>
</head>
<body>
	<form action="ticket" method="post">
		<div id="listarTickets" name="acao" value="Listar">
			<table class='CSSTableGenerator'>
				<tr>
					<td>Matrícula</td>
					<td>Turno</td>
					<td>Refeição</td>
					<td>Valor</td>
				</tr>
				<c:if test="${tickets!=null}">
					<c:forEach var="ticket" items="${tickets}">
						<tr>
							<td> ${ticket.consumidor.matricula} </td>
							<td> ${ticket.refeicao.turno} </td>
							<td> ${ticket.refeicao.descricao} </td>
							<td>R$ ${ticket.valor} </td>
						</tr>
						
					</c:forEach>
				</c:if>
				
			</table>
			<br/>
			<center><a data-target="#conteudo" class="button" href="ticket?acao=carregarCriarTicket">Novo</a><center>
		</div>
	</form>
	
	<c:if test="${response != null}">
		<c:redirect url="index.jsp#redir=ticket&message=${response}"/>
	</c:if>
</body>
</html>