package controladores;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidades.Departamento;

@WebServlet("/VerDepartamento")
public class VerDepartamento extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String acao = (String) request.getParameter("acaoVer");
		if (acao == null)
			acao = "";

		switch (acao) {
			case "Voltar":
				request.getRequestDispatcher("ListarDepartamento").forward(request,response);
				break;
			default:
				Departamento departamentoAntigo = buscarDepartamentoAntigo(request);
				request.setAttribute("departamento antigo",departamentoAntigo);
				request.getRequestDispatcher("WEB-INF/VerDepartamento.jsp").forward(request,response);
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
}