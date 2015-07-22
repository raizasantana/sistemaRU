<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Consumidores</title>
</head>
<body>
	<c:if test="${consumidores!=null}">
			<h3>Alunos</h3>
			<table class='CSSTableGenerator'>
				<tr>
					<td>Nome</td>
					<td>Matricula</td>
					<td>Ano de ingresso</td>
					<td>CPF</td>
					<td>Curso</td>
					<td>Ação</td>
				</tr>
	
				<c:forEach var="cons" items="${consumidores}">
					<c:choose>
					<c:when test="${ cons.getClass().name == 'br.ccomp.modelo.Aluno' }">
						<tr>
							<td style="width: 30%">${cons.nome }</td>
							<td style="width: 10%">${cons.matricula }</td>
							<td style="width: 20%">${cons.anoIngresso }</td>
							<td style="width: 15%">${cons.cpf}</td>
							<td style="width: 10%">${cons.curso.sigla}</td>
							<td style="width: 15%"><center><a data-target="#conteudo" class="button" href="consumidor?acao=Editar&id=${cons.id}">Editar</a></center></td>
						</tr>
					</c:when>
					</c:choose>
				</c:forEach>
			</table>
			
			<h3>Funcionários</h3>
			<table class='CSSTableGenerator'>
				<tr>
					<td>Nome</td>
					<td>Matricula</td>
					<td>Ano de ingresso</td>
					<td>CPF</td>
					<td>Departamento</td>
					<td>Ação</td>
				</tr>
	
				<c:forEach var="cons" items="${consumidores}">
					<c:choose>
					<c:when test="${ cons.getClass().name == 'br.ccomp.modelo.Funcionario' }">
						<tr>
							<td style="width: 30%">${cons.nome }</td>
							<td style="width: 10%">${cons.matricula }</td>
							<td style="width: 20%">${cons.anoIngresso }</td>
							<td style="width: 15%">${cons.cpf}</td>
							<td style="width: 10%">${cons.departamento.sigla}</td>
							<td style="width: 15%"><center><a data-target="#conteudo" class="button" href="consumidor?acao=Editar&id=${cons.id}">Editar</a></center></td>
						</tr>
					</c:when>
					</c:choose>
				</c:forEach>
			</table>
		<br/>
		<center><a data-target="#conteudo" class="button" href="consumidor?acao=listarCurso">Novo</a><center>
	</c:if>
	
	<c:if test="${response != null}">
		<c:redirect url="index.jsp#redir=consumidor&message=${response}"/>
	</c:if>

</body>
</html>