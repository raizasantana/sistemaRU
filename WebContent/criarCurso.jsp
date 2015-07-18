<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Criar curso</title>
</head>
<body>
	<form action="curso" method='post'>
		<table>
			<input type="text" id="id" name="id" value="${curso_id}" style="display:none">
			<tr>
				<td>Nome:</td><td><input type="text" id="nome" name="nome"></td>
			</tr>
			<tr>
				<td>Sigla:</td><td><input type="text" id="sigla" name="sigla"></td>
			</tr>
			<tr><td colspan=2>
			<select id="departamentos" name="departamentos">
			<c:if test="${departamentos!=null}">
				<c:forEach var="departamento" items="${departamentos}">
					<option value=${ departamento.id }> ${ departamento.sigla }</option>
				</c:forEach>
			</c:if>
			</select>
			</td></tr>
		</table>
		<input type="submit" name="acao" value="Criar">
	</form>

	<h1>${message}</h1>
	
</body>
</html>