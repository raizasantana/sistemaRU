<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Criar Refeicao</title>
</head>
<body>
	<form action="refeicao" method='post'>
		<table class="TableForm">
			<tr>
				<td>Descrição:</td><td><input type="text" id="descricao" name="descricao"></td>
			</tr><tr>
				<td>Opção Vegetariana:</td><td><input type="text" id="opVegan" name="opcao_vegetariana"></td>
			</tr><tr>
			<td>Tipo:</td> 
				<td><select id="tipo" name="tipos">
					<c:forEach var="tipo" items="${tipos}">
						<option value="${ tipo }"  > ${tipo}</option>
					</c:forEach>
				</select></td>
			</tr><tr>
			<td>Turno:</td>
				<td><select id="turnos" name="turnos">
					<c:forEach var="turno" items="${turnos}">
						<option value="${ turno }"  >${turno}</option>
					</c:forEach>
				</select></td>
			</tr>
		</table>
		<input type="submit" name="acao" value="Criar">
	</form>
	
    	<h1>${message}</h1>

</body>
</html>