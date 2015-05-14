package controladores;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Curso;

@WebServlet("/ListarCurso")
public class ListarCurso extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		listarCursos(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = (String) request.getParameter("acaoListar");
		
		if (acao == null) acao = "";
		
		switch (acao) {
			case "Criar":
				criarCurso(request,response);
				break;
			case "Atualizar":
				atualizarCurso(request,response);
				break;
			case "Ver":
				verCurso(request,response);
				break;
			// nos requisitos nao podemos remover cursos
//			case "Remover":
//				removeCurso(request,response);
//				break;
			case "":
			default:
				listarCursos(request,response);				
		}
	}

	private void criarCurso(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("CriarCurso").forward(request,response);
	}
	
	private void atualizarCurso(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("AtualizarCurso").forward(request,response);
	}	

	private void verCurso(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("VerCurso").forward(request,response);
	}

//	private void removeCurso(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.getRequestDispatcher("RemoverCurso").forward(request,response);
//	}

	private void listarCursos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("cursos", _listarCursosDisponiveis(request));
		request.getRequestDispatcher("WEB-INF/ListarCurso.jsp").forward(request,response);
	}

	// metodos de persistencia de Curso

	public static Collection<Curso> _listarCursosDisponiveis(HttpServletRequest request){
		
		HttpSession session = request.getSession();

		Collection<Curso> cursosDisponiveis = (Collection<Curso>)session.getAttribute("cursosDisponiveis");
		if (cursosDisponiveis == null){
			cursosDisponiveis = new HashSet<Curso>();
		}

		session.setAttribute("cursosDisponiveis", cursosDisponiveis);

		return cursosDisponiveis;
	}
	
	public static void _adicionarCurso(HttpServletRequest request, Curso curso) {
		Set<Curso> cursosDisponiveis = (Set<Curso>)request.getSession().getAttribute("cursosDisponiveis");
		cursosDisponiveis.add(curso);
		request.getSession().setAttribute("cursosDisponiveis",cursosDisponiveis);
	}

	public static Curso _buscarCurso(HttpServletRequest request, Curso curso) {
		Set<Curso> cursosDisponiveis = (Set<Curso>)request.getSession().getAttribute("cursosDisponiveis");
		for(Curso di : cursosDisponiveis){
			if (curso.equals(di))
				return di;
		}
		return null;
	}

	public static void _atualizarCurso (HttpServletRequest request, Curso curso) throws ServletException, IOException {
		Collection<Curso> cursosDisponiveis = (Collection<Curso>)request.getSession().getAttribute("cursosDisponiveis");
		Curso cursoAntigo = _buscarCurso(request,curso);
		cursoAntigo.setNome(curso.getNome());
		cursoAntigo.setDepartamento(curso.getDepartamento());
		cursosDisponiveis.add(cursoAntigo);
		
		request.getSession().setAttribute("cursosDisponiveis", cursosDisponiveis);
	}

}
