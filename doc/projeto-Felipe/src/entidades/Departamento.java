package entidades;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import entidades.value_objects.DepartamentoVO;

public class Departamento implements Serializable {
	
	public static void _adicionarDepartamento (HttpServletRequest request, DepartamentoVO dpto) {
		Set<DepartamentoVO> departamentosDisponiveis = (Set<DepartamentoVO>)request.getSession().getAttribute("departamentosDisponiveis");
		departamentosDisponiveis.add(dpto);
		request.getSession().setAttribute("departamentosDisponiveis",departamentosDisponiveis);
	}

	public static DepartamentoVO _buscarDepartamento(HttpServletRequest request, String sigla) {
		
		
		Set<DepartamentoVO> departamentosDisponiveis = (Set<DepartamentoVO>)request.getSession().getAttribute("departamentosDisponiveis");
		for(DepartamentoVO di : departamentosDisponiveis){
			if (di.getSigla().equals(sigla))
				return di;
		}
		return null;
	}

	public static void _atualizarDepartamento (HttpServletRequest request, DepartamentoVO dpto) throws ServletException, IOException {
		Collection<DepartamentoVO> departamentosDisponiveis = (Collection<DepartamentoVO>)request.getSession().getAttribute("departamentosDisponiveis");
		DepartamentoVO departamentoAntigo = _buscarDepartamento(request,dpto.getSigla());
		departamentoAntigo.setNome(dpto.getNome());
		departamentosDisponiveis.add(departamentoAntigo);
		
		request.getSession().setAttribute("departamentosDisponiveis", departamentosDisponiveis);
	}
	
	public static Collection<DepartamentoVO> _listarDepartamentosDisponiveis(HttpServletRequest request){
		
		HttpSession session = request.getSession();

		Collection<DepartamentoVO> departamentosDisponiveis = (Collection<DepartamentoVO>)session.getAttribute("departamentosDisponiveis");
		if (departamentosDisponiveis == null){
			departamentosDisponiveis = new HashSet<DepartamentoVO>();
		}

		session.setAttribute("departamentosDisponiveis", departamentosDisponiveis);

		return departamentosDisponiveis;
	}

	
	
}
