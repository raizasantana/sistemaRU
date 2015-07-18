package br.ccomp.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ccomp.modelo.Departamento;
import br.ccomp.transactions.TransactionScriptDepartamento;

@WebServlet("/departamento")
public class ServletDepartamento extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response){
		String acao = (String) request.getParameter("acao");
		
		if (acao != null){
			switch (acao) {
			case "preparaCriar":
				exibirFormDepartamento(request, response);
				break;
			case "Criar":
				criarDepartamento(request, response);
				break;
			case "Editar":
				editarDepartamento(request, response);
				break;
			case "Listar":
				listarDepartamento(request, response);
				break;
			default:
				break;
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response){
		String acao = (String) request.getParameter("acao");
		
		if (acao != null){
			switch (acao) {
			case "preparaCriar":
				exibirFormDepartamento(request, response);
				break;
			case "Criar":
				criarDepartamento(request, response);
				break;
			case "Editar":
				editarDepartamento(request, response);
				break;
			case "Listar":
				listarDepartamento(request, response);
				break;
			default:
				break;
			}
		}
	}
	
	private void criarDepartamento(HttpServletRequest request, HttpServletResponse response) {
		String nome = (String) request.getParameter("nome");
		String sigla = (String) request.getParameter("sigla");
		
		String message = null;
		
		TransactionScriptDepartamento transactionScriptDepartamento = new TransactionScriptDepartamento();
		
		try{
			transactionScriptDepartamento.inserirDepartamento(nome, sigla);
			message = "Inserido com sucesso";
		}catch (Exception e){
			message = e.getMessage();
		}
			
		request.setAttribute("response", message);
		
		listarDepartamento(request,response);
	}
	
	private void editarDepartamento(HttpServletRequest request, HttpServletResponse response){
		try {
			request.getRequestDispatcher("/editarDepartamento.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}	
	}
	
	private void exibirFormDepartamento(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.getRequestDispatcher("/criarDepartamento.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}	
	}

	private void listarDepartamento(HttpServletRequest request, HttpServletResponse response){
		TransactionScriptDepartamento transactionScriptDepartamento = new TransactionScriptDepartamento();
		
		ArrayList<Departamento> departamentos = transactionScriptDepartamento.listarDepartamentos();
		request.setAttribute("departamentos", departamentos);
		
		try {
			request.getRequestDispatcher("/listarDepartamento.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
}
