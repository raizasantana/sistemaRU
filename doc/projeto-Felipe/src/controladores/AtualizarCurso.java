package controladores;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Curso;
import entidades.Departamento;

@WebServlet("/AtualizarCurso")
public class AtualizarCurso extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String acao = (String) request.getParameter("acaoAtualizar");
		Collection<Departamento> departamentosDisponiveis = ListarDepartamento._listarDepartamentosDisponiveis(request);
		request.setAttribute("departamentosDisponiveis", departamentosDisponiveis);
		
		if (acao == null)
			acao = "";

		switch (acao) {
			case "Cancelar":
			case "Voltar":
				request.getRequestDispatcher("ListarCurso").forward(request,response);
				break;
			case "Atualizar":
				atualizarCursoAntigo(request,response);
				break;
			default:
				Curso cursoAntigo = buscarCursoAntigo(request);
				request.setAttribute("curso antigo",cursoAntigo);
				request.getRequestDispatcher("WEB-INF/AtualizarCurso.jsp").forward(request,response);
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

	private void atualizarCursoAntigo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = (String) request.getParameter("nome");
		String sigla = (String) request.getParameter("sigla");
		
		Departamento departamentoSelecionado = ListarDepartamento._buscarDepartamento(request, new Departamento("",request.getParameter("departamento")));
		Curso curso = new Curso(nome,sigla,departamentoSelecionado);

		if (curso.getNome()=="" || curso.getSigla()=="" || curso.getDepartamento() == null){
			request.setAttribute("erro", "Um curso deve conter um nome, uma sigla e um departamento");
			request.getRequestDispatcher("WEB-INF/AtualizarCurso.jsp").forward(request,response);
		}else{
			Curso cursoAntigo = buscarCursoAntigo(request);
			if (cursoAntigo == null){
				request.getRequestDispatcher("WEB-INF/AtualizarCurso.jsp").forward(request,response);	
			}else{
				ListarCurso._atualizarCurso(request, curso);
				request.getRequestDispatcher("ListarCurso").forward(request,response);
			}			
		}
	}

}