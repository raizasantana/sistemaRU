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

import entidades.Departamento;

@WebServlet("/ListarDepartamento")
public class ListarDepartamento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		listarDepartamentos(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = (String) request.getParameter("acaoListar");
		
		if (acao == null) acao = "";
		
		switch (acao) {
			case "Criar":
				criarDepartamento(request,response);
				break;
			case "Atualizar":
				atualizarDepartamento(request,response);
				break;
			case "Ver":
				verDepartamento(request,response);
				break;
			// nos requisitos nao podemos remover departamentos
//			case "Remover":
//				removeDepartamento(request,response);
//				break;
			case "":
			default:
				listarDepartamentos(request,response);				
		}
	}

	private void criarDepartamento(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("CriarDepartamento").forward(request,response);
	}
	
	private void atualizarDepartamento(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("AtualizarDepartamento").forward(request,response);
	}	

	private void verDepartamento(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("VerDepartamento").forward(request,response);
	}

//	private void removeDepartamento(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.getRequestDispatcher("RemoverDepartamento").forward(request,response);
//	}

	private void listarDepartamentos(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("departamentos", Departamento._listarDepartamentosDisponiveis(request));
		request.getRequestDispatcher("WEB-INF/ListarDepartamento.jsp").forward(request,response);
	}

}
