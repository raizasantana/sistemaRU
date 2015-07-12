package br.ccomp.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ccomp.modelo.Curso;
import br.ccomp.transactions.TransactionScriptCurso;

@WebServlet("/curso")
public class ServletCurso extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
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
			default:
				break;
			}
		}
	}
	
	private void criarCurso(HttpServletRequest request, HttpServletResponse response) {
		String nome = (String) request.getParameter("nome");
		String sigla = (String) request.getParameter("sigla");
		int idDepartamento = Integer.parseInt(request.getParameter("idDepartamento"));
		
		String message = null;
		
		TransactionScriptCurso transactionScriptCurso = new TransactionScriptCurso();
		
		if (transactionScriptCurso.inserirCurso(nome, sigla, idDepartamento))
			message = "Inserido com sucesso";
		else
			message = "Curso de mesma sigla já existe";
		
		request.setAttribute("message", message);
		
//		try {
//			request.getRequestDispatcher("/criarCurso.jsp").forward(request, response);
//		} catch (ServletException | IOException e) {
//			e.printStackTrace();
//		}
	}
	
	private void editarCurso(HttpServletRequest request, HttpServletResponse response){
		
		try {
			request.getRequestDispatcher("/listarCurso.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
		
	}

}
