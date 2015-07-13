<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/menu.js"></script>
<title>Lista de cursos</title>
</head>
<body>
	<form action="curso" method="post">
		<input type="submit" name="acao" value="Listar"></br>
		<div id="listarCursos" name="acao" value="Listar">
			<table border="0">
			
				<c:if test="${cursos!=null}">
					<c:forEach var="curso" items="${cursos}">
						<tr>
							<td rowspan="2"> ${curso.id} </td>
							<td height="20" valign="top"> ${curso.nome} </td>
							<td height="20" valign="top"> ${curso.sigla} </td>
							<td height="20" valign="top"> ${curso.departamento.id} </td>
							<td height="20" valign="top"> ${curso.departamento.sigla} </td>
						</tr>
						
						<tr height="18">
							<td colspan="2"><hr size="1" width="100%" color="#191970"></td>
						</tr>
					</c:forEach>
				</c:if>
				
			</table>
		</div>
	</form>
</body>
</html>