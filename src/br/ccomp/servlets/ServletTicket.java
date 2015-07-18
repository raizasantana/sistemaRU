package br.ccomp.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ccomp.modelo.Aluno;
import br.ccomp.modelo.Consumidor;
import br.ccomp.modelo.Funcionario;
import br.ccomp.modelo.Refeicao;
import br.ccomp.modelo.Ticket;
import br.ccomp.transactions.TransactionScriptConsumidor;
import br.ccomp.transactions.TransactionScriptRefeicao;
import br.ccomp.transactions.TransactionScriptTicket;

@WebServlet("/ticket")
public class ServletTicket extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response){
		String acao = (String) request.getParameter("acao");
		
		if (acao != null){
			switch (acao) {
			case "Criar":
				criarTicket(request, response);
				break;
			/*
			case "Editar":
				editarTicket(request, response);
				break;
			case "Alterar":
				alterarTicket(request, response);
				break;
			*/
			case "Listar":
				listarTicket(request, response);
				break;
			case "carregarCriarTicket":
				carregarCriarTicket(request, response);
				break;
			default:
				break;
			}
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response){
		String acao = (String) request.getParameter("acao");
		
		if (acao != null){
			switch (acao) {
			case "Criar":
				criarTicket(request, response);
				break;
			/*
			case "Editar":
				editarTicket(request, response);
				break;
			case "Alterar":
				alterarTicket(request, response);
				break;
			*/
			case "Listar":
				listarTicket(request, response);
				break;
			case "carregarCriarTicket":
				carregarCriarTicket(request, response);
				break;
			default:
				break;
			}
		}
	}
	
	private void carregarCriarTicket(HttpServletRequest request, HttpServletResponse response) {
		TransactionScriptRefeicao transactionScriptRefeicao = new TransactionScriptRefeicao();
		
		ArrayList<Refeicao> refeicoes = new ArrayList<Refeicao>();
		
		try {
			refeicoes = transactionScriptRefeicao.listarRefeicao();
		} catch (Exception e) {
		}
		
		request.setAttribute("refeicoes", refeicoes);
		
		try {
			request.getRequestDispatcher("/criarTicket.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
		
	}

	private void listarTicket(HttpServletRequest request, HttpServletResponse response) {
		TransactionScriptTicket transactionScriptTicket = new TransactionScriptTicket();
		
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();
		
		String message = null;
		
		try {
			tickets = transactionScriptTicket.listarTickets();
		} catch (Exception e) {
			message = e.getMessage();
		}
		
		request.setAttribute("tickets", tickets);
		request.setAttribute("message", message);
		
		try {
			request.getRequestDispatcher("/listarTicket.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}
	/*
	private void alterarTicket(HttpServletRequest request, HttpServletResponse response) {
		TransactionScriptTicket transactionScriptTicket = new TransactionScriptTicket();
		
	}

	private void editarTicket(HttpServletRequest request, HttpServletResponse response) {
		TransactionScriptTicket transactionScriptTicket = new TransactionScriptTicket();
	}
	*/
	
	private void criarTicket(HttpServletRequest request, HttpServletResponse response) {
		// String valor = request.getParameter("valor");
		Integer matricula = Integer.parseInt(request.getParameter("matricula"));
		int idRefeicao = Integer.parseInt(request.getParameter("refeicao_selecionada"));
		int pago = Integer.parseInt(request.getParameter("pago"));
		TransactionScriptRefeicao ts = new TransactionScriptRefeicao();
		Refeicao r = ts.recuperarRefeicao(idRefeicao);
		
		
		String message = null;
		
		TransactionScriptTicket transactionScriptTicket = new TransactionScriptTicket();
		TransactionScriptConsumidor transactionScriptConsumidor = new TransactionScriptConsumidor();
		
		try{
			Consumidor consumidor = transactionScriptConsumidor.getConsumidorMatricula(matricula);
			
			if(consumidor.getTitulo() == null){
				Aluno aluno = new Aluno();
				transactionScriptTicket.inserirTicket(consumidor,aluno.getPreco(r.getTurno()) , idRefeicao, pago);
			}
			else{
				Funcionario func = new Funcionario();
				transactionScriptTicket.inserirTicket(consumidor, func.getPreco(r.getTurno()) , idRefeicao, pago);
			}
			
			message = "Ticket criado com sucesso";
		} catch (Exception e) {
			message = e.getMessage();
		}
		
		request.setAttribute("response", message);
		
		listarTicket(request, response);
	}
}
