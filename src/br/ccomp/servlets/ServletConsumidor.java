package br.ccomp.servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ccomp.modelo.Consumidor;
import br.ccomp.modelo.Curso;
import br.ccomp.modelo.Departamento;
import br.ccomp.modelo.Titulo;
import br.ccomp.transactions.RoteiroAtualizaConsumidor;
import br.ccomp.transactions.RoteiroBuscaConsumidor;
import br.ccomp.transactions.RoteiroCriarAluno;
import br.ccomp.transactions.RoteiroCriarFuncionario;
import br.ccomp.transactions.RoteiroListaCurso;
import br.ccomp.transactions.RoteiroListaDepartamento;
import br.ccomp.transactions.RoteiroListarConsumidores;
import br.ccomp.transactions.TransactionScriptConsumidor;
import br.ccomp.transactions.TransactionScriptCurso;
import br.ccomp.transactions.TransactionScriptDepartamento;

@WebServlet("/consumidor")

public class ServletConsumidor extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	{
		String acao = (String) request.getParameter("acao");
		
		if (acao != null)
		{
			switch (acao)
			{
				case "Criar":
					criarConsumidor(request, response);
					break;
				case "listarCurso":
					listarCursosDepartamentos(request, response);
					break;
				case "Editar":
					getConsumidor(request, response);
					break;
				case "Alterar":
					atualizarConsumidor(request, response);
					break;
				default:
					break;
			}
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	{
		String acao = (String) request.getParameter("acao");
		
		if (acao != null)
		{
			switch (acao)
			{
				case "Criar":
					criarConsumidor(request, response);
					break;
				case "listarCurso":
					listarCursosDepartamentos(request, response);
					break;
				case "Listar":
					listarConsumidores(request, response);
					break;
				case "Editar":
					getConsumidor(request, response);
					break;
				case "Alterar":
					atualizarConsumidor(request, response);
					break;
				default:
					break;
			}
		}
	}
	
	public void criarConsumidor(HttpServletRequest request, HttpServletResponse response)
	{
		String departamento = (String) request.getParameter("departamentos");
		String curso = (String) request.getParameter("cursos");
		String nome = (String) request.getParameter("nome");
		String cpf = (String) request.getParameter("cpf");
		String titulo = (String) request.getParameter("titulos");
		String matricula = (String) request.getParameter("matricula");
		String anoIngresso = (String) request.getParameter("ano");
		String sexo = (String) request.getParameter("sexo");
		String tipo = (String) request.getParameter("tipo");
		String message = null;
		
		
		
		try {
			if (tipo.equals("aluno")){ //Criar aluno
				RoteiroCriarAluno roteiroAluno = new RoteiroCriarAluno();
				roteiroAluno.execute(Integer.valueOf(curso), nome, cpf,Integer.valueOf(matricula), Integer.valueOf(anoIngresso), sexo);
			}
			else {
				RoteiroCriarFuncionario roteiroFuncionario = new RoteiroCriarFuncionario();
				roteiroFuncionario.execute(Integer.valueOf(departamento), nome, cpf, titulo, Integer.valueOf(matricula), Integer.valueOf(anoIngresso), sexo);
			}
				
			message = "Inserido com sucesso";
		} catch(Exception e){
			message = e.getMessage();
		}
		
		request.setAttribute("response", message);
		
		listarConsumidores(request, response);
	}
	
	public void listarCursosDepartamentos(HttpServletRequest request, HttpServletResponse response)
	{
		
		RoteiroListaCurso listarCurso = new RoteiroListaCurso();
		RoteiroListaDepartamento listarDepartamento = new RoteiroListaDepartamento();
		

		ArrayList<Curso> cursos = listarCurso.execute();
		ArrayList<Departamento> deps = listarDepartamento.execute();
		
		ArrayList<String> titulos = new ArrayList<String>(); 
		titulos.add(Titulo.DOUTORADO.getNome());
		titulos.add(Titulo.ESPECIALIZACAO.getNome());
		titulos.add(Titulo.MESTRADO.getNome());
				
		request.setAttribute("cursos", cursos);
		request.setAttribute("departamentos", deps);
		request.setAttribute("titulos", titulos);
				
		try {
			request.getRequestDispatcher("/criarConsumidor.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void listarConsumidores(HttpServletRequest request, HttpServletResponse response)
	{
	
		RoteiroListarConsumidores listarConsumidores = new RoteiroListarConsumidores();
		ArrayList<Consumidor> consumids = listarConsumidores.execute();
		
		request.setAttribute("consumidores", consumids);
		
		try {
			request.getRequestDispatcher("listarConsumidor.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}

	}
	
	private void getConsumidor(HttpServletRequest request, HttpServletResponse response) {
		
		RoteiroBuscaConsumidor buscaConsumidor = new RoteiroBuscaConsumidor();
		Consumidor c = buscaConsumidor.execute(Integer.valueOf(request.getParameter("id")));
		
		
		request.setAttribute("consumidor", c);
		
		TransactionScriptCurso tsCurso = new TransactionScriptCurso();
		TransactionScriptDepartamento tsDepartamento = new TransactionScriptDepartamento();
		
		ArrayList<Curso> cursos = tsCurso.listarCurso();
		ArrayList<Departamento> deps = tsDepartamento.listarDepartamentos();
		
		ArrayList<String> titulos = new ArrayList<String>(); 
		titulos.add(Titulo.DOUTORADO.getNome());
		titulos.add(Titulo.ESPECIALIZACAO.getNome());
		titulos.add(Titulo.MESTRADO.getNome());
				
		request.setAttribute("cursos", cursos);
		request.setAttribute("departamentos", deps);
		request.setAttribute("titulos", titulos);
		
		try {
			request.getRequestDispatcher("/editarConsumidor.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private void atualizarConsumidor(HttpServletRequest request, HttpServletResponse response) {
		String nome = (String)request.getParameter("nome");
		String sexo = (String)request.getParameter("sexo");
		int id = Integer.valueOf((String) request.getParameter("id"));
		int matricula = Integer.valueOf((String) request.getParameter("matricula"));
		int ano = Integer.valueOf((String) request.getParameter("ano"));
		
		RoteiroAtualizaConsumidor atualizarConsumidor = new RoteiroAtualizaConsumidor();
		
		
		try {
			atualizarConsumidor.execute(id, ano, matricula, nome, sexo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		listarConsumidores(request, response);
		
	}

}
