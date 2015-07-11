package br.ccomp.modelo;

public class Turno {

	enum TipoTurno{
		MANHA,
		TARDE,
		NOITE;
	}
	
	private Integer id;
	private TipoTurno tipoTurno;
	private double precoAluno;
	private double precoFuncionario;
	
	public TipoTurno getTipoTurno() {
		return tipoTurno;
	}
	public void setTipoTurno(TipoTurno tipoTurno) {
		this.tipoTurno = tipoTurno;
	}
	public double getPrecoAluno() {
		return precoAluno;
	}
	public void setPrecoAluno(double precoAluno) {
		this.precoAluno = precoAluno;
	}
	public double getPrecoFuncionario() {
		return precoFuncionario;
	}
	public void setPrecoFuncionario(double precoFuncionario) {
		this.precoFuncionario = precoFuncionario;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}
