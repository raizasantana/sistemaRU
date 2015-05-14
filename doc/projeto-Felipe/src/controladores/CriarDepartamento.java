package controladores;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Departamento;
import entidades.value_objects.DepartamentoVO;

@WebServlet("/CriarDepartamento")
public class CriarDepartamento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = (String) request.getParameter("acaoCriar");
		
		if (acao != null){
			switch (acao) {
				case "Criar":
					criarDepartamento(request,response);
					break;
				default:
					request.getRequestDispatcher("ListarDepartamento").forward(request,response);
			}
		}else{
			request.getRequestDispatcher("WEB-INF/CriarDepartamento.jsp").forward(request,response);	
		}
	}
	private void criarDepartamento(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = (String) request.getParameter("nome");
		String sigla = (String) request.getParameter("sigla");
		
//		Departamento dpto = new Departamento(nome,sigla);
		
		if (Departamento._buscarDepartamento(request,sigla) == null){
			if (nome=="" || sigla==""){
				request.setAttribute("erro", "Um departamento deve conter um nome e uma sigla");
				request.getRequestDispatcher("WEB-INF/CriarDepartamento.jsp").forward(request,response);
			}else{
				DepartamentoVO dpto = new DepartamentoVO(nome,sigla);
				Departamento._adicionarDepartamento (request,dpto);
				request.setAttribute("message", "Novo departamento criado!");
				request.getRequestDispatcher("ListarDepartamento").forward(request,response);
			}
		}else{
			request.setAttribute("erro", "Sigla '"+sigla+"' já existe!");
			request.getRequestDispatcher("WEB-INF/CriarDepartamento.jsp").forward(request,response);
		}
	}

}