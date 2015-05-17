package controladores;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Curso;

@WebServlet("/VerCurso")
public class VerCurso extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String acao = (String) request.getParameter("acaoVer");
		if (acao == null)
			acao = "";

		switch (acao) {
			case "Voltar":
				request.getRequestDispatcher("ListarCurso").forward(request,response);
				break;
			default:
				Curso cursoAntigo = buscarCursoAntigo(request);
				request.setAttribute("curso antigo",cursoAntigo);
				request.getRequestDispatcher("WEB-INF/VerCurso.jsp").forward(request,response);
		}
	}
	
	
	private Curso buscarCursoAntigo(HttpServletRequest request) throws ServletException, IOException{
		Curso cursoAntigo = new Curso("", request.getParameter("sigla"),null);
		try {
			cursoAntigo = ListarCurso._buscarCurso(request, cursoAntigo);
		} catch (NullPointerException e) {
			request.setAttribute("erro", "Curso não existe!");
			cursoAntigo = null;
		}
		if (cursoAntigo == null){
			request.setAttribute("erro", "Curso não existe!");
			cursoAntigo = null;
		}
		
		return cursoAntigo;
	}

}