package control;

import model.Aluno;

public interface IControlador {

	public void inserirAluno(String id, String nome);

	public Aluno pesquisarAluno(String id);

	public void removerAluno(String id);

	public void conectar();

	public void desconectar();

}
