package control;

import java.sql.*;
import javax.swing.JOptionPane;
import model.*;

public class ControladorDBAlunos implements IControlador {
	private static String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
	private static String login = "hr";
	private static String password = "123";
	Connection Conexao = null;

	public void inserirAluno(String id, String nome) {
		conectar();
		Aluno aluno = new Aluno(id, nome);
		String SQL = "INSERT INTO ALUNO (ra,nome) VALUES ('" + aluno.getRa()
				+ "', '" + aluno.getNome() + "')";

		try {
			Statement MeuState = Conexao.createStatement();
			MeuState.executeUpdate(SQL);
			JOptionPane.showMessageDialog(null, "Aluno inserido com sucesso!");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Problema ao incluir um registro. \nErro: " + e, "Erro", 1);
			System.exit(0);
		} finally {
			desconectar();
		}
	}

	public Aluno pesquisarAluno(String id) {
		conectar();
		Aluno aluno;
		String SQL = "SELECT * FROM aluno WHERE ra ='" + id + "'";
		try {
			PreparedStatement stmt = Conexao.prepareStatement(SQL);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				String RA = rs.getString("RA");
				String NOME = rs.getString("NOME");
				aluno = new Aluno(RA, NOME);

				return aluno;
			} else {
				JOptionPane.showMessageDialog(null, "O Aluno não existe", "Mensagem", 1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,
					"Problema ao pesquisar um registro. \nErro: " + e, "Erro", 1);
			System.exit(0);
		} finally {
			desconectar();
		}
		return null;
	}

	public void removerAluno(String id) {
		conectar();
		Aluno aluno = new Aluno();
		aluno.setRa(id);
		String SQL = "DELETE FROM aluno WHERE ra ='" + aluno.getRa() + "'";
		try {
			PreparedStatement stmt = Conexao.prepareStatement(SQL);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
                PreparedStatement stmtd = Conexao.prepareStatement(SQL);
                stmtd.executeUpdate();
                JOptionPane.showMessageDialog(null,
                        "Aluno removido com sucesso!");
			} else {
				JOptionPane.showMessageDialog(null, "O Aluno não existe", "Mensagem", 1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,
					"Problema ao excluir um registro. \nErro: " + e, "Erro", 1);
			System.exit(0);
		} finally {
			desconectar();
		}
	}

	public void conectar() {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Conexao = DriverManager.getConnection(url, login, password);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null,
					"Driver JDBC-ODBC não encontrado ", "Mensagem", 1);
			System.exit(0);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
					"Problemas na conexão com a fonte dos dados \n\n" + e,
					"Mensagem", 1);
			System.exit(0);
		}
	}

	public void desconectar() {
		try {
			Conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
