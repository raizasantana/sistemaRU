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
import br.ccomp.transactions.RoteiroAtualizaCurso;
import br.ccomp.transactions.RoteiroBuscaCurso;
import br.ccomp.transactions.RoteiroCriaCurso;
import br.ccomp.transactions.RoteiroListaCurso;
import br.ccomp.transactions.RoteiroListaDepartamento;
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
			case "Alterar":
				alterarCurso(request, response);
				break;
			case "Listar":
				listarCursos(request, response);
				break;
			case "carregarCriarCurso":
				carregarCriarCurso(request, response);
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
			case "Alterar":
				alterarCurso(request, response);
				break;
			case "Listar":
				listarCursos(request, response);
				break;
			case "carregarCriarCurso":
				carregarCriarCurso(request, response);
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
		
		RoteiroCriaCurso criarCurso = new RoteiroCriaCurso();
		
		try{
			criarCurso.execute(nome, sigla, idDepartamento);
			message = "Inserido com sucesso";
		} catch (Exception e) {
			message = e.getMessage();
		}
		
		request.setAttribute("response", message);
		
		listarCursos(request,response);
	}
	
	private void editarCurso(HttpServletRequest request, HttpServletResponse response){
		int id = Integer.parseInt(request.getParameter("id"));
		
		RoteiroBuscaCurso buscaCurso = new RoteiroBuscaCurso();
		RoteiroListaDepartamento listaDepartamento = new RoteiroListaDepartamento();
		
		String message = null;
		
		try{
			Curso curso = buscaCurso.execute(id);
			request.setAttribute("response", message);
			request.setAttribute("curso_id", curso.getId());
			request.setAttribute("curso_nome", curso.getNome());
			request.setAttribute("curso_sigla", curso.getSigla());
			request.setAttribute("curso_id_departamento", curso.getDepartamento().getId());
			
			ArrayList<Departamento> departamentos = listaDepartamento.execute();
			request.setAttribute("departamentos", departamentos);
			
		} catch (Exception e){
			message = e.getMessage();
			request.setAttribute("response", message);
		}
		
		
		try {
			request.getRequestDispatcher("/editarCurso.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}	
	}
	
	private void alterarCurso(HttpServletRequest request, HttpServletResponse response){
		int id = Integer.parseInt(request.getParameter("id"));
		String nome = (String) request.getParameter("nome");
		String sigla = (String) request.getParameter("sigla");
		int idDepartamento = Integer.parseInt(request.getParameter("departamentos"));
		
		
		RoteiroAtualizaCurso atualizaCurso = new RoteiroAtualizaCurso();
		
		String message = null;
		
		try{
			atualizaCurso.execute(id, nome, sigla, idDepartamento);
			message = "Alterado com sucesso";
		} catch (Exception e) {
			message = e.getMessage();
		}
		
		request.setAttribute("response", message);
		
		listarCursos(request,response);
	}
	
	private void listarCursos(HttpServletRequest request, HttpServletResponse response){
		
		RoteiroListaCurso listaCurso = new RoteiroListaCurso();
		ArrayList<Curso> cursos = listaCurso.execute();
		request.setAttribute("cursos", cursos);
		
		try {
			request.getRequestDispatcher("/listarCurso.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	
	private void carregarCriarCurso(HttpServletRequest request, HttpServletResponse response){
		TransactionScriptDepartamento transactionScriptDepartamento = new TransactionScriptDepartamento();
		
		ArrayList<Departamento> departamentos = transactionScriptDepartamento.listarDepartamentos();
		request.setAttribute("departamentos", departamentos);
		
		try {
			request.getRequestDispatcher("/criarCurso.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
}
