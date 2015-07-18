<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Criar Refeicao</title>
</head>
<body>
	<form action="Refeicao" method='post'>
		<table>
			<input type="text" id="id" name="id" value="${refeicao_id}" style="display:none">
			<tr>
			<td>Descrição:</td><td><input type="text" id="descricao" name="descricao" value="${refeicao_descricao}"></td>
			</tr>
			<tr>
			<td>Opção Vegetariana:</td><td><input type="text" id="opVegan" name="opVegan" value="${refeicao_opVegan}"></td>
			</tr>
			<tr>
			<td>Tipo:</td><td><input type="text" id="turno" name="tipo" value="${refeicao_tipo}" disabled></td>
			</tr>
			<tr>
			<td>Turno:</td><td><input type="text" id="turno" name="turno" value="${refeicao_turno}" disabled></td>
			</tr>
		</table>
		<input type="submit" name="acao" value="Alterar">
	</form>
	
    	<h1>${message}</h1>

</body>
</html>