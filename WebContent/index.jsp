<html>
	<head>
		<title>Grupo 6 - Sistema para Restaurante Universitário</title>
		
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		
		<link rel="stylesheet" type="text/css" href="css/stylesheet.css">
		<link rel="stylesheet" type="text/css" href="css/jquery-ui.css">
		<link rel="stylesheet" type="text/css" href="css/tableStyleGenerator.css">
		<link rel="stylesheet" type="text/css" href="css/TableForm.css">
		
		<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
		<script type="text/javascript" src="js/jquery.js"></script>
		<script type="text/javascript" src="js/jquery-ui.js"></script>
		<script type="text/javascript" src="js/menu.js"></script>
	</head>
	<body>
		<div id="site" class="center">
			<div id="header" style="position: relative; top: 0px;"><img src="img/Header.png"/></div>
				<div id="nav">
					<hr width="100%" /><center>
						<ul class="topnav">
							<li><a href="#" onclick="myload('ticket?acao=Listar')">Criar Ticket</a></li> || 
							<li id="system_nav"><a href="#">Visualizar Banco<img src="img/arrow.png"/></a></li> || 
							<li><a href="#" onclick="myload('creditos.html')">Créditos</a></li>
						</ul>
						<ul id="system_subnav">  
							<li><a href="#" onclick="myload('departamento?acao=Listar')">Departamento</a></li> ||
							<li><a href="#" onclick="myload('consumidor?acao=Listar')">Consumidor</a></li> || 
							<li><a href="#" onclick="myload('curso?acao=Listar')">Curso</a></li> || 
							<li><a href="#" onclick="myload('refeicao?acao=Listar')">Refeição</a></li>
						</ul>
					</center><hr width="100%" />
				</div>
				
				<div id="conteudo" style="position: relative;">
					
				</div>
				
				
				<hr width="100%" />
				
			<div id="footer" style="position: relative; bottom: 0px;"><img src="img/Footer.png"/></div>
		</div>
	</body>
</html>