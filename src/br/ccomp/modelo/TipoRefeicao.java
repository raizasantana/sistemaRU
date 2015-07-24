package br.ccomp.modelo;

public enum TipoRefeicao {

	DESJEJUM,
	ALMOCO,
	JANTA;
	
	public String getNome(){
		return this.name();
	}
}
