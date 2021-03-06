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

@WebServlet("/curso")
public class ServletCurso extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response){
		String acao = (String) request.getParameter("acao");
		
		if (acao != null){
			switch (acao) {
			case "Criar":
				criarCurso(request, response);
				break;
			case "Editar":
				editarCurso(request, response);
				break;
			case "Listar":
				listarCurso(request, response);
				break;
			case "carregar_departamentos":
				carregarDepartamentos(request, response);
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
				criarCurso(request, response);
				break;
			case "Editar":
				editarCurso(request, response);
				break;
			case "Listar":
				listarCurso(request, response);
				break;
			case "carregar_departamentos":
				carregarDepartamentos(request, response);
				break;
			default:
				break;
			}
		}
	}
	
	private void criarCurso(HttpServletRequest request, HttpServletResponse response) {
		String nome = (String) request.getParameter("nome");
		String sigla = (String) request.getParameter("sigla");
		int idDepartamento = Integer.parseInt(request.getParameter("departamentos"));
		
		String message = null;
		
		TransactionScriptCurso transactionScriptCurso = new TransactionScriptCurso();
		
		if (transactionScriptCurso.inserirCurso(nome, sigla, idDepartamento))
			message = "Inserido com sucesso";
		else
			message = "Curso de mesma sigla j� existe";
		
		request.setAttribute("message", message);
		
		try {
			request.getRequestDispatcher("/curso?acao=carregar_departamentos").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	
	private void editarCurso(HttpServletRequest request, HttpServletResponse response){
		
		try {
			request.getRequestDispatcher("/editarCurso.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}	
	}

	private void listarCurso(HttpServletRequest request, HttpServletResponse response){
		TransactionScriptCurso transactionScriptCurso = new TransactionScriptCurso();
		
		ArrayList<Curso> cursos = transactionScriptCurso.listarCurso();
		request.setAttribute("cursos", cursos);
		
		try {
			request.getRequestDispatcher("/listarCurso.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void carregarDepartamentos(HttpServletRequest request, HttpServletResponse response){
		TransactionScriptDepartamento transactionScriptDepartamento = new TransactionScriptDepartamento();
		
		ArrayList<Departamento> departamentos = transactionScriptDepartamento.listarDepartamentos();
		request.setAttribute("departamentos", departamentos);
		
		try {
			request.getRequestDispatcher("/criarCurso.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
