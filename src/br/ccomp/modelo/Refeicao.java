package br.ccomp.modelo;

public class Refeicao {

	private TipoRefeicao tipo;
	private Turno turno;
	private String descrcicao;
	private String opcaoVegetariana;
	
	public TipoRefeicao getTipo() {
		return tipo;
	}
	public void setTipo(TipoRefeicao tipo) {
		this.tipo = tipo;
	}
	public Turno getTurno() {
		return turno;
	}
	public void setTurno(Turno turno) {
		this.turno = turno;
	}
	public String getDescrcicao() {
		return descrcicao;
	}
	public void setDescrcicao(String descrcicao) {
		this.descrcicao = descrcicao;
	}
	public String getOpcaoVegetariana() {
		return opcaoVegetariana;
	}
	public void setOpcaoVegetariana(String opcaoVegetariana) {
		this.opcaoVegetariana = opcaoVegetariana;
	}
	
	
}
