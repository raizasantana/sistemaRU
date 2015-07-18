<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Editar curso</title>
</head>
<body>
	<form id="form" action="curso" method='post'>
		<table class="TableForm">
			<input type="text" id="id" name="id" value="${curso_id}" style="display:none">
			<tr>
				<td>Nome:</td><td><input type="text" id="nome" name="nome" value="${curso_nome}"></td>
			</tr>
			<tr>
				<td>Sigla:</td><td><input type="text" id="sigla" name="sigla" value="${curso_sigla}"></td>
			</tr>
			<tr><td colspan=2>
			<select id="departamentos" name="departamentos">
				<c:if test="${departamentos!=null}">
					<c:forEach var="departamento" items="${departamentos}">
						<c:choose>
							<c:when test="${curso_id_departamento == departamento.id}">
								<option value=${ departamento.id } selected="selected"> ${ departamento.sigla }</option>
							</c:when>
							<c:otherwise>
								<option value=${ departamento.id }> ${ departamento.sigla }</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</c:if>
			</select>
		</table>
		<input type="submit" name="acao" value="Alterar">
	</form>

	<h1>${message}</h1>
	
</body>
</html>