package controladores;

import java.io.IOException;
import java.util.Collection;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Curso;
import entidades.Departamento;

@WebServlet("/CriarCurso")
public class CriarCurso extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = (String) request.getParameter("acaoCriar");
		Collection<Departamento> departamentosDisponiveis = ListarDepartamento._listarDepartamentosDisponiveis(request);
		request.setAttribute("departamentosDisponiveis", departamentosDisponiveis);
		
		if (acao != null){
			switch (acao) {
				case "Criar":
					criarCurso(request,response);
					break;
				default:
					request.getRequestDispatcher("ListarCurso").forward(request,response);
			}
		}else{
			request.getRequestDispatcher("WEB-INF/CriarCurso.jsp").forward(request,response);	
		}
	}

	private void criarCurso(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = (String) request.getParameter("nome");
		String sigla = (String) request.getParameter("sigla");
		
		Departamento departamentoSelecionado = ListarDepartamento._buscarDepartamento(request, new Departamento("",request.getParameter("departamento")));
		Curso curso = new Curso(nome,sigla,departamentoSelecionado);
		
		if (ListarCurso._buscarCurso(request,curso) == null){
			if (curso.getNome()=="" || curso.getSigla()=="" || curso.getDepartamento() == null){
				request.setAttribute("erro", "Um curso deve conter um nome, uma sigla e um departamento");
				request.getRequestDispatcher("WEB-INF/CriarCurso.jsp").forward(request,response);
			}else{
				ListarCurso._adicionarCurso(request,curso);
				request.setAttribute("message", "Novo curso criado!");
				request.getRequestDispatcher("ListarCurso").forward(request,response);
			}
		}else{
			request.setAttribute("erro", "Sigla '"+sigla+"' já existe!");
			request.getRequestDispatcher("WEB-INF/CriarCurso.jsp").forward(request,response);
		}
	}
	
}