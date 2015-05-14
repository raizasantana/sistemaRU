package controladores;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Departamento;

@WebServlet("/AtualizarDepartamento")
public class AtualizarDepartamento extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String acao = (String) request.getParameter("acaoAtualizar");
		if (acao == null)
			acao = "";

		switch (acao) {
			case "Cancelar":
			case "Voltar":
				request.getRequestDispatcher("ListarDepartamento").forward(request,response);
				break;
			case "Atualizar":
				atualizarDepartamentoAntigo(request,response);
				break;
			default:
				Departamento departamentoAntigo = buscarDepartamentoAntigo(request);
				request.setAttribute("departamento antigo",departamentoAntigo);
				request.getRequestDispatcher("WEB-INF/AtualizarDepartamento.jsp").forward(request,response);
		}
	}
	
	
	private Departamento buscarDepartamentoAntigo(HttpServletRequest request) throws ServletException, IOException{
		Departamento departamentoAntigo = new Departamento("", request.getParameter("sigla"));
		try {
			departamentoAntigo = ListarDepartamento._buscarDepartamento(request, departamentoAntigo);
		} catch (NullPointerException e) {
			request.setAttribute("erro", "Departamento não existe!");
			departamentoAntigo = null;
		}
		if (departamentoAntigo == null){
			request.setAttribute("erro", "Departamento não existe!");
			departamentoAntigo = null;
		}
		
		return departamentoAntigo;
	}

	private void atualizarDepartamentoAntigo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = (String) request.getParameter("nome");
		String sigla = (String) request.getParameter("sigla");
		Departamento dpto = new Departamento(nome,sigla);
		
		if (dpto.getNome()=="" || dpto.getSigla()==""){
			request.setAttribute("erro", "Um departamento deve conter um nome e uma sigla");
			request.getRequestDispatcher("WEB-INF/CriarDepartamento.jsp").forward(request,response);
		}else{
			Departamento departamentoAntigo = buscarDepartamentoAntigo(request);
			if (departamentoAntigo == null){
				request.getRequestDispatcher("WEB-INF/CriarDepartamento.jsp").forward(request,response);	
			}else{
				ListarDepartamento._atualizarDepartamento(request, dpto);
				request.getRequestDispatcher("ListarDepartamento").forward(request,response);
			}
		}
	}

}