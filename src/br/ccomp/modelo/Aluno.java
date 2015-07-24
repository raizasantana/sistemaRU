package br.ccomp.modelo;

public class Aluno extends Consumidor{
	
	public Aluno(){
	}
	
	public Aluno(int id, int matricula){
		super(id, matricula);
	}

	private Curso curso;

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	
	@Override
	public float getValor(Turno t){
		if(t.getNome() == "MANHA")
			return 0.5f;
		else
			return 1.0f;
			
	}
	

}
