package br.ccomp.modelo;

public class Departamento {

	private Integer id;
	private String nome;
	private String sigla;
	
	public Departamento(){
		
	}
	
	public Departamento(int id){
		this.id = id;
	}
	
	public Departamento(int id, String nome, String sigla){
		this.id = id;
		this.nome = nome;
		this.sigla = sigla;
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
