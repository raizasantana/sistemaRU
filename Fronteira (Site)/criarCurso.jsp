<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Criar curso</title>
</head>
<body>
	<p>Criar Curso</p>
	<form action="curso" method='post'>
		<p>Nome: <input type="text" id="nome" name="nome"></p>
		<p>Sigla: <input type="text" id="sigla" name="sigla"></p>
		<p>idDepartamento: <input type="text" id="idDepartamento" name="idDepartamento"></p>
		</br> <input type="submit" name="acao" value="Criar">
	</form>
	
	<c:if mensagem="${not empty message}">
    	<h1>${message}</h1>
	</c:if>
</body>
</html>