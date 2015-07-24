<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Editar Departamento</title>
</head>
<body>
	<form action="departamento" method='post'>
	<input type="text" id="id" name="id" value="${departamento_id}" style="display:none">
	<table class="TableForm">
		<tr>	
			<td>Nome:</td>
			<td><input type="text" id="nome" name="nome" value="${departamento_nome}"></td>
		</tr>
		<tr>
			<td>Sigla:</td>
			<td><input type="text" id="sigla" name="sigla" maxlength="5" value="${departamento_sigla}"></td>
		</tr>
	</table>
	<input class="button" type="submit" name="acao" value="Alterar">
	</form>
    
</body>
</html>