package br.ccomp.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ccomp.modelo.Curso;
import br.ccomp.modelo.Departamento;
import br.ccomp.transactions.TransactionScriptCurso;
import br.ccomp.transactions.TransactionScriptDepartamento;

@WebServlet("/departamento")
public class ServletDepartamento extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response){
		String acao = (String) request.getParameter("acao");
		
		if (acao != null){
			switch (acao) {
			case "Criar":
				criarDepartamento(request, response);
				break;
			case "Editar":
				editarDepartamento(request, response);
				break;
			case "Listar":
				listarDepartamento(request, response);
				break;
			case "Alterar":
				alterarDepartamento(request, response);
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
			case "Criar":
				criarDepartamento(request, response);
				break;
			case "Editar":
				editarDepartamento(request, response);
				break;
			case "Listar":
				listarDepartamento(request, response);
				break;
			case "Alterar":
				alterarDepartamento(request, response);
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
			
		request.setAttribute("message", message);
		
		try {
			request.getRequestDispatcher("criarDepartamento.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	
	private void alterarDepartamento(HttpServletRequest request, HttpServletResponse response){
		int id = Integer.parseInt(request.getParameter("id"));
		String nome = (String) request.getParameter("nome");
		String sigla = (String) request.getParameter("sigla");
		
		TransactionScriptDepartamento transactionScriptDepartamento = new TransactionScriptDepartamento();
		
		String message = null;
		
		try{
			transactionScriptDepartamento.alterarDepartamento(id, nome, sigla);
			message = "Alterado com sucesso";
		} catch (Exception e) {
			message = e.getMessage();
		}
		
		request.setAttribute("message", message);
		
		try {
			request.getRequestDispatcher("departamento?acao=Listar").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	
	private void editarDepartamento(HttpServletRequest request, HttpServletResponse response){
		int id = Integer.parseInt(request.getParameter("id"));
		
		TransactionScriptDepartamento transactionScriptDepartamento = new TransactionScriptDepartamento();
		
		String message = null;
		
		try{
			Departamento departamento = transactionScriptDepartamento.getDepartamento(id);
			
			request.setAttribute("message", message);
			request.setAttribute("departamento_id", departamento.getId());
			request.setAttribute("departamento_nome", departamento.getNome());
			request.setAttribute("departamento_sigla", departamento.getSigla());
		} catch (Exception e) {
			message = e.getMessage();
		}
		
		try {
			request.getRequestDispatcher("editarDepartamento.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}	
	}

	private void listarDepartamento(HttpServletRequest request, HttpServletResponse response){
		TransactionScriptDepartamento transactionScriptDepartamento = new TransactionScriptDepartamento();
		
		ArrayList<Departamento> departamentos = transactionScriptDepartamento.listarDepartamentos();
		request.setAttribute("departamentos", departamentos);
		
		try {
			request.getRequestDispatcher("listarDepartamento.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
