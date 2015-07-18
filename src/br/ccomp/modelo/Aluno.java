package br.ccomp.modelo;

public class Aluno extends Consumidor{
	
	public Aluno(){
		
	}

	private Curso curso;

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	
	@Override
	public float getPreco(Turno t)
	{
		if(t.equals(Turno.MANHA.getNome()))
			return (float) 0.5;
		else return (float) 1.0;
	}
	

}
