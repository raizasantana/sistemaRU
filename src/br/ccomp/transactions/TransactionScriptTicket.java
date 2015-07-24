package br.ccomp.transactions;

import java.sql.SQLException;
import java.util.ArrayList;

import br.ccomp.gateway.TicketGateway;
import br.ccomp.modelo.Consumidor;
import br.ccomp.modelo.Refeicao;
import br.ccomp.modelo.Ticket;
import br.ccomp.modelo.Turno;

public class TransactionScriptTicket {
	private TicketGateway ticketGateway;
	
	public TransactionScriptTicket(){
		ticketGateway = new TicketGateway();
	}
	
	public boolean inserirTicket(Consumidor consumidor, Refeicao refeicao, int pago) throws Exception{
		float valor = 0f;
		
		if(consumidor == null)
			throw new Exception("Consumidor n�o encontrado para a matr�cula");
		
		if(refeicao.getTurno() == Turno.MANHA && consumidor.getClass().getName().equals("br.ccomp.modelo.Aluno"))
			valor = 0.5f;
		else if(refeicao.getTurno() == Turno.MANHA && consumidor.getClass().getName().equals("br.ccomp.modelo.Funcionario"))
			valor = 3f;
		else if(consumidor.getClass().getName().equals("br.ccomp.modelo.Aluno"))
			valor = 1f;
		else if(consumidor.getClass().getName().equals("br.ccomp.modelo.Funcionario"))
			valor = 6f;
		
		
		boolean isPago = pago == 1 ? true : false;
		return ticketGateway.insert(consumidor.getId(), valor, refeicao.getId(), isPago);
	}
	
	public boolean alterarTicket(Integer id, Boolean pago) throws SQLException{
		return ticketGateway.update(id, pago);
	}
	
	public Ticket getTicket(int id) throws Exception{
		Ticket ticket = ticketGateway.find(id); 
		if(ticket == null)
			throw new Exception("Ticket n�o encontrado");
		
		return ticket;
	}
	
	public ArrayList<Ticket> listarTickets() throws Exception{
		ArrayList<Ticket> tickets = ticketGateway.findAll();
		
		if(tickets.isEmpty())
			throw new Exception("Nenhum ticket registrado");
		
		return tickets;
	}
	
	public ArrayList<Ticket> listarTickets(int matriculaConsumidor) throws Exception{
		ArrayList<Ticket> tickets = ticketGateway.findAll(matriculaConsumidor);
		
		if(tickets.isEmpty())
			throw new Exception("Nenhum ticket encontrado para o consumidor desejado");
		
		return tickets;
	}
}
