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
	<p>Editar Refeição</p>
	<form action="Refeicao" method='post'>
		<input type="hidden" name="acao" value="Salvar" />
		<p style="display: none">
			ID: <input type="text" id="id" name="id" value="${refeicao_id}">
		</p>
		<p>Descrição: <input type="text" id="descricao" name="descricao" value="${refeicao_descricao}"></p>
		<p>Opção Vegetariana: <input type="text" id="opVegan" name="opVegan" value="${refeicao_opVegan}"></p>
		<p>Tipo: <input type="text" id="turno" name="tipo" value="${refeicao_tipo}" disabled>
		</p>
		<p>Turno: <input type="text" id="turno" name="turno" value="${refeicao_turno}" disabled>
			
		</p>
		<br /> <input type="submit" name="acao" value="Alterar">
	</form>
	
    	<h1>${message}</h1>

</body>
</html>