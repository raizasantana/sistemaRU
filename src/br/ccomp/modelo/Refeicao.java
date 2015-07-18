package br.ccomp.modelo;

public class Refeicao {
	
	private Integer id;
	private TipoRefeicao tipo;
	private Turno turno;
	private String descricao;
	private String opcaoVegetariana;
	
	public Refeicao() {
	
	}
	
	public Refeicao(Turno turno, String descricao){
		this.turno = turno;
		this.descricao = descricao;
	}
	
	public Refeicao(int id, String descricao, String opcaoVegetariana,
			TipoRefeicao tipo, Turno turno) {
		this.id = id;
		this.tipo = tipo;
		this.turno = turno;
		this.descricao = descricao;
		this.opcaoVegetariana =  opcaoVegetariana;
	}

	public TipoRefeicao getTipo() {
		return tipo;
	}
	public void setTipo(TipoRefeicao tipo) {
		this.tipo = tipo;
	}
	public Turno getTurno() {
		return turno;
	}
	public void setTurno(Turno turno) {
		this.turno = turno;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descrcicao) {
		this.descricao = descrcicao;
	}
	public String getOpcaoVegetariana() {
		return opcaoVegetariana;
	}
	public void setOpcaoVegetariana(String opcaoVegetariana) {
		this.opcaoVegetariana = opcaoVegetariana;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}
