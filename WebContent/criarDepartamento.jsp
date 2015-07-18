<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Criar Departamento</title>
</head>
<body>
	<form action="departamento" method='post'>
	<table class="TableForm">
		<tr>
			<td>Nome:</td><td><input type="text" id="nome" name="nome"></td>
		</tr>
		<tr>
			<td>Sigla:</td><td><input type="text" id="sigla" name="sigla"></td>
		</tr>
	</table>
	<input class="button" type="submit" name="acao" value="Criar">
	</form>
	
    <h1>${message}</h1>
</body>
</html>