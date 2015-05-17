package entidades;

import java.util.Collection;

public class Aluno extends Consumidor{
	private Curso curso;

	public Aluno(String nome, int matricula, int anoIngresso, Curso curso) {
		super(nome, matricula, anoIngresso);
		this.curso = curso;
	}
	
	public Aluno(String nome, int matricula, int anoIngresso, Sexo sexo, Titulo titulo, CPF cpf, Curso curso) {
		super(nome, matricula, anoIngresso, sexo, titulo, cpf);
		this.curso = curso;
	}
	
	public Curso getCurso() {
		return curso;
	}
}
