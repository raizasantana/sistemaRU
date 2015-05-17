package entidades.value_objects;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public class DepartamentoVO implements Serializable {
	private String nome;
	private String sigla;
	
	public DepartamentoVO(){
		
	}

	public DepartamentoVO(String nome, String sigla) {
		this.nome = nome;
		this.sigla = sigla;
	}
	
	public String getNome() {
		return nome;
	}
	
	public String getSigla() {
		return sigla;
	}
	@Override
	public boolean equals(Object obj) {
		DepartamentoVO outroObjeto = (DepartamentoVO) obj;
		return sigla.equals(outroObjeto.getSigla());
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
