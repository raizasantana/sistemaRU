package br.ccomp.modelo;

public enum Titulo {

	ESPECIALIZACAO,
	MESTRADO,
	DOUTORADO;
	
	public String getNome(){
		return this.name();
	}
}
