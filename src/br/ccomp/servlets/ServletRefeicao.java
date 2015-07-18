package br.ccomp.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ccomp.modelo.Refeicao;
import br.ccomp.modelo.TipoRefeicao;
import br.ccomp.modelo.Turno;
import br.ccomp.transactions.TransactionScriptRefeicao;

@WebServlet("/refeicao")
public class ServletRefeicao extends HttpServlet{

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response){
		String acao = (String) request.getParameter("acao");
		
		if (acao != null){
			switch (acao) {
			case "carregarTurno":
				carregarTurno(request, response);
				break;
			case "Criar":
				criarRefeicao(request, response);
				break;
			case "Editar":
				editarRefeicao(request, response);
				break;
			case "Listar":
				listarRefeicao(request, response);
				break;
			case "Salvar":
				salvarRefeicao(request, response);
				break;
			default:
				break;
			}
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response){
		String acao = (String) request.getParameter("acao");
		if (acao != null){
			switch (acao) {
			case "carregarTurno":
				carregarTurno(request, response);
				break;
			case "Criar":
				criarRefeicao(request, response);
				break;
			case "Editar":
				editarRefeicao(request, response);
				break;
			case "Listar":
				listarRefeicao(request, response);
				break;
			case "Salvar":
				salvarRefeicao(request, response);
				break;
			default:
				break;
			}
		}
	}
	
	
	private void criarRefeicao(HttpServletRequest request, HttpServletResponse response) {
		String descricao = (String) request.getParameter("descricao");
		String opcaoVegetariana = (String) request.getParameter("opcao_vegetariana");
		String tipoString = (String) request.getParameter("tipos");
		TipoRefeicao tipo = null;
		if(tipoString.equals(TipoRefeicao.DESJEJUM.getNome()))
			tipo = TipoRefeicao.DESJEJUM;
		if(tipoString.equals(TipoRefeicao.ALMOCO.getNome()))
			tipo = TipoRefeicao.ALMOCO;
		if(tipoString.equals(TipoRefeicao.JANTA.getNome()))
			tipo = TipoRefeicao.JANTA;
		
		Turno turno = null;
		if(request.getParameter("turnos").equals(Turno.MANHA.getNome()))
			turno = Turno.MANHA;
		if(request.getParameter("turnos").equals(Turno.TARDE.getNome()))
			turno = Turno.TARDE;
		if(request.getParameter("turnos").equals(Turno.NOITE.getNome()))
			turno = Turno.NOITE;
		
		String message = null;
		
		TransactionScriptRefeicao transactionScriptRefeicao = new TransactionScriptRefeicao();
		
		if (transactionScriptRefeicao.inserirRefeicao(descricao, opcaoVegetariana, tipo, turno))
			message = "Inserido com sucesso";
		else
			message = "Refeicao n�o pode ser cadastrada";
		
		request.setAttribute("response", message);
		
		listarRefeicao(request, response);
	}
	
	private void editarRefeicao(HttpServletRequest request, HttpServletResponse response){
		int id = Integer.parseInt(request.getParameter("id"));
		
		TransactionScriptRefeicao tsr = new TransactionScriptRefeicao();
		Refeicao refeicao = tsr.recuperarRefeicao(id);
		
		try {
			request.setAttribute("refeicao_id", refeicao.getId());
			request.setAttribute("refeicao_descricao", refeicao.getDescricao());
			request.setAttribute("refeicao_opVegan", refeicao.getOpcaoVegetariana());
			request.setAttribute("refeicao_turno", refeicao.getTurno().getNome());
			request.setAttribute("refeicao_tipo", refeicao.getTipo().getNome());
			request.getRequestDispatcher("/editarRefeicao.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}	
	}

	private void listarRefeicao(HttpServletRequest request, HttpServletResponse response){
		TransactionScriptRefeicao transactionScriptRefeicao = new TransactionScriptRefeicao();
		
		ArrayList<Refeicao> refeicoes = transactionScriptRefeicao.listarRefeicao();
		request.setAttribute("refeicoes", refeicoes);
		
		try {
			request.getRequestDispatcher("/listarRefeicao.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	
	public void carregarTurno(HttpServletRequest request, HttpServletResponse response){
		ArrayList<String> turnos = new ArrayList<String>(); 
		turnos.add(Turno.MANHA.getNome());
		turnos.add(Turno.TARDE.getNome());
		turnos.add(Turno.NOITE.getNome());
		request.setAttribute("turnos", turnos);
		
		ArrayList<String> tipos = new ArrayList<String>(); 
		tipos.add(TipoRefeicao.DESJEJUM.getNome());
		tipos.add(TipoRefeicao.ALMOCO.getNome());
		tipos.add(TipoRefeicao.JANTA.getNome());
		request.setAttribute("tipos", tipos);
	
		
		try {
			request.getRequestDispatcher("/criarRefeicao.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	
	private void salvarRefeicao(HttpServletRequest request, HttpServletResponse response){
		int id = Integer.parseInt(request.getParameter("id"));
		String descricao = (String) request.getParameter("descricao");
		String opVegan = (String) request.getParameter("opVegan");
				
		TransactionScriptRefeicao transactionScriptRefeicao = new TransactionScriptRefeicao();
		
		String message = null;
		
		try{
			transactionScriptRefeicao.alterarRefeicao(id, descricao, opVegan);
			message = "Alterado com sucesso";
		} catch (Exception e) {
			message = e.getMessage();
		}
		
		request.setAttribute("response", message);
		
		listarRefeicao(request, response);
	}
}
