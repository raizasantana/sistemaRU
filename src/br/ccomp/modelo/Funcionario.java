package br.ccomp.modelo;

public class Funcionario extends Consumidor{

	public Funcionario() {
		// TODO Auto-generated constructor stub
	}

	private Departamento departamento;

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	
	@Override
	public float getPreco(Turno t)
	{
		if(t.equals(Turno.MANHA.getNome()))
			return (float) 3.0;
		else return (float) 6.0;
	}
	
}
