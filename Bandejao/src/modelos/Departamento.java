package modelos;

public class Departamento {
	
	private String nome;
	private String sigla;
	
	public Departamento(String n, String s){
		this.nome = n;
		this.sigla = s;
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
	
	
	

}
