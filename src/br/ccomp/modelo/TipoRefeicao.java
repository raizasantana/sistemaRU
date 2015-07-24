package br.ccomp.modelo;

public enum TipoRefeicao {

	DESJEJUM,
	ALMOCO,
	JANTAR;
	
	public String getNome(){
		return this.name();
	}
}
