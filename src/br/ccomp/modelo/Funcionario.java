package br.ccomp.modelo;

public class Funcionario extends Consumidor{

	public Funcionario() {
	}
	
	public Funcionario(int id, int matricula){
		super(id, matricula);
	}

	private Departamento departamento;

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	
	
}
