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
	
	

}
