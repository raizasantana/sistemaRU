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
	<p>Criar Refeição</p>
	<form action="Refeicao" method='post'>
		<input type="hidden" name="acao" value="Criar" />
		<p>Descrição: <input type="text" id="descricao" name="descricao"></p>
		<p>Opção Vegetariana: <input type="text" id="opVegan" name="opcao_vegetariana"></p>
		<p>Tipo: 
			<select id="tipo" name="tipos">
				<c:forEach var="tipo" items="${tipos}">
					<option value="${ tipo }"  > ${tipo}</option>
				</c:forEach>
			</select>
		</p>
		<p>Turno: 
			<select id="turnos" name="turnos">
				<c:forEach var="turno" items="${turnos}">
					<option value="${ turno }"  >${turno}</option>
				</c:forEach>
			</select>
		</p>
		<br /> <input type="submit" name="acao" value="Criar">
	</form>
	
    	<h1>${message}</h1>

</body>
</html>