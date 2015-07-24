<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Criar Ticket</title>
</head>
<body>
	<form action="ticket" method='post'>
		<input type="text" id="id" name="id" value="${tkt_id}" style="display: none">
		<table class="TableForm">
			<tr>
				<td>Matrícula do consumidor:</td><td><input type="text" id="matricula" name="matricula" value="${matricula}" disabled="disabled"></td>
			</tr><tr>
				<td>Pago:</td>
				<td><select id="pago" name="pago">
					<c:choose>
						<c:when test="${pago == 0}">
							<option value="0" selected="selected"> Não </option>
							<option value="1"> Sim </option>
						</c:when>
						<c:otherwise>
							<option value="0"> Não </option>
							<option value="1" selected="selected"> Sim </option>	
						</c:otherwise>
					</c:choose>
				</select></td>
			</tr>
		
		</table>
		<br/>
		<table class='CSSTableGenerator'>
				<tr>
					<td>Selecionar</td>
					<td>Turno</td>
					<td>Refeicao</td>
					<td>Opcao Vegetariana</td>
					<td>Tipo</td>
				</tr>
				<tr>
					<td> <input type="radio" name="refeicao_selecionada" value="${refeicao.id}" checked="checked" disabled="disabled"> </td>
					<td> ${refeicao.turno} </td>
					<td> ${refeicao.descricao} </td>
					<td> ${refeicao.opcaoVegetariana} </td>
					<td> ${refeicao.tipo} </td>
				</tr>
		</table>
		<br/>
		<input type="submit" name="acao" value="Alterar">
	</form>

	<c:if test="${message != null}">
		<c:redirect url="index.jsp#redir=ticket&message=${message}"/>
	</c:if>
	
</body>
</html>