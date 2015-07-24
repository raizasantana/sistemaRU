package br.ccomp.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ccomp.modelo.Consumidor;
import br.ccomp.modelo.Refeicao;
import br.ccomp.modelo.Ticket;
import br.ccomp.transactions.RoteiroAtualizarTicket;
import br.ccomp.transactions.RoteiroBuscaConsumidor;
import br.ccomp.transactions.RoteiroBuscaConsumidorMatricula;
import br.ccomp.transactions.RoteiroBuscaTicket;
import br.ccomp.transactions.RoteiroBuscarRefeicao;
import br.ccomp.transactions.RoteiroCriarTicket;
import br.ccomp.transactions.RoteiroListaRefeicao;
import br.ccomp.transactions.RoteiroListarTicket;

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
			case "Editar":
				editarTicket(request, response);
				break;
			case "Alterar":
				alterarTicket(request, response);
				break;
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
			case "Editar":
				editarTicket(request, response);
				break;
			case "Alterar":
				alterarTicket(request, response);
				break;
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
			
		RoteiroListaRefeicao listaRefeicao = new RoteiroListaRefeicao();
		
		ArrayList<Refeicao> refeicoes = new ArrayList<Refeicao>();
		
		try {
			refeicoes = listaRefeicao.execute();
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
		
		
		RoteiroListarTicket listaTicket = new RoteiroListarTicket();
		
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();
		
		try {
			tickets = listaTicket.execute();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		request.setAttribute("tickets", tickets);
		
		try {
			request.getRequestDispatcher("/listarTicket.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

	private void alterarTicket(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		int pago = Integer.parseInt(request.getParameter("pago"));
		
			
		RoteiroAtualizarTicket atualizaTicket = new RoteiroAtualizarTicket();
		
		boolean pago_b = pago == 1 ? true : false;
		try {
			atualizaTicket.execute(id, pago_b);
			String message = "Alterado com sucesso";
			
			request.setAttribute("response", message);
			
			listarTicket(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void editarTicket(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		RoteiroBuscaTicket buscaTicket = new RoteiroBuscaTicket();
		RoteiroBuscarRefeicao buscaRefeicao = new RoteiroBuscarRefeicao();
		
		Refeicao refeicao = null;
		String message = null;
		int matricula;
		int pago;
		try {
			Ticket tkt = buscaTicket.execute(id);
			int ref_id = tkt.getRefeicao().getId();
			refeicao = buscaRefeicao.execute(ref_id);
			matricula = tkt.getConsumidor().getMatricula();
			pago = tkt.getPago() ? 1 : 0;
			
			request.setAttribute("refeicao", refeicao);
			request.setAttribute("matricula", matricula);
			request.setAttribute("pago", pago);
			request.setAttribute("tkt_id", id);
		} catch (Exception e) {
			message = e.getMessage();
		}
		
		request.setAttribute("response", message);
		
		try {
			request.getRequestDispatcher("/editarTicket.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
		
	}

	private void criarTicket(HttpServletRequest request, HttpServletResponse response) {
		Integer matricula = Integer.parseInt(request.getParameter("matricula"));
		int idRefeicao = Integer.parseInt(request.getParameter("refeicao_selecionada"));
		int pago = Integer.parseInt(request.getParameter("pago"));
		
		String message = null;
		

		RoteiroCriarTicket criaTicket = new RoteiroCriarTicket();
		RoteiroBuscarRefeicao buscaRefeicao = new RoteiroBuscarRefeicao();
		RoteiroBuscaConsumidorMatricula buscaConsMatricula =  new RoteiroBuscaConsumidorMatricula();
		
		try{
			Consumidor consumidor = buscaConsMatricula.execute(matricula);
			Refeicao refeicao = buscaRefeicao.execute(idRefeicao);
			
			criaTicket.execute(consumidor, refeicao, pago);
			
			message = "Ticket criado com sucesso";
		} catch (Exception e) {
			message = e.getMessage();
		}
		
		request.setAttribute("response", message);
		
		listarTicket(request, response);
	}
}
