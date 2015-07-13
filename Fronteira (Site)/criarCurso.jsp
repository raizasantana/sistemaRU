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
	<p>Criar Curso</p>
	<form action="curso" method='post'>
		<p>
			Nome: <input type="text" id="nome" name="nome">
		</p>
		<p>
			Sigla: <input type="text" id="sigla" name="sigla">
		</p>
		
		<select id="departamentos" name="departamentos">
			<c:if test="${departamentos!=null}">
				<c:forEach var="departamento" items="${departamentos}">
					<option value=${ departamento.id }> ${ departamento.sigla }</option>
				</c:forEach>
			</c:if>
		</select>
			
		</div>
		</br> <input type="submit" name="acao" value="Criar">
	</form>

	<h1>${message}</h1>
	
</body>
</html>