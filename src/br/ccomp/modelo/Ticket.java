package br.ccomp.modelo;

public class Ticket {
	
	private Integer id;
	private float valor;
	private Refeicao refeicao;
	private Consumidor consumidor;
	private boolean pago;
	
	public Ticket() {
		
	}
	
	public Ticket(int id, float valor, Refeicao refeicao, Consumidor consumidor, boolean pago) {
		this.id = id;
		this.valor = valor;
		this.refeicao = refeicao;
		this.consumidor = consumidor;
		this.pago = pago;
	}
	
	public Refeicao getRefeicao() {
		return refeicao;
	}
	public void setRefeicao(Refeicao refeicao) {
		this.refeicao = refeicao;
	}
	public Consumidor getConsumidor() {
		return consumidor;
	}
	public void setConsumidor(Consumidor consumidor) {
		this.consumidor = consumidor;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	public boolean isPago() {
		return pago;
	}
	public void setPago(boolean pago) {
		this.pago = pago;
	}
	
	public boolean getPago(){
		return pago;
	}

}
