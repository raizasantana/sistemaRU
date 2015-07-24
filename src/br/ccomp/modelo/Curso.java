package br.ccomp.modelo;

public class Curso {

	private Integer id;
	private String nome;
	private String sigla;
	private Departamento departamento;
	
	public Curso(){
		
	}
	
	public Curso(int id, String nome, String sigla, Departamento departamento){
		this.id = id;
		this.nome = nome;
		this.sigla = sigla;
		this.departamento = departamento;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	public Departamento getDepartamento() {
		return departamento;
	}
	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
}
