package exemplo.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exemplo.model.Turma;

public class TurmaDAO implements DAO {

	private ConexaoMysql conexao;

	public TurmaDAO() {
		super();
		this.conexao = new ConexaoMysql("localhost", "root", "", "barack_pergunta");
	}

	public Object cadastrar(Object objeto) {

		Turma turma = (Turma) objeto;
		this.conexao.abrirConexao();

		String sqlInsert = "INSERT INTO turma VALUES(null, ?, ?);";
		try {
			
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);

			statement.setString(1, turma.getNomeTurma());
			statement.setString(2, turma.getCursoTurma());


			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if(rs.next()){
				
				turma.setIdTurma(rs.getLong(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			this.conexao.fecharConexao();
		}
		return turma;
	}




	public void editar(Object objeto) {
		String sqlEditar = "UPDATE turma SET nome_turma=?, curso_turma=? WHERE id_turma=?";

		Turma turma = (Turma) objeto;
		this.conexao.abrirConexao();

		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlEditar);
			statement.setString(1, turma.getNomeTurma());
			statement.setString(2, turma.getCursoTurma());
			statement.setLong(3, turma.getIdTurma());

			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	}


	public void excluir(long id) {
		String sqlExcluir = "DELETE FROM turma WHERE id_turma=?";


		this.conexao.abrirConexao();
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlExcluir);
			statement.setObject(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	}


	public List<Object> buscarTodos() {

		this.conexao.abrirConexao();
		
		String sqlSelect = "SELECT * FROM turma;";
		PreparedStatement statement;
		Turma turma = null;
		List<Object> listaTurmas = new ArrayList<Object>();
		try {
			statement = this.conexao.getConexao().prepareStatement(sqlSelect);
			ResultSet rs = statement.executeQuery();

			while(rs.next()) {

				turma = new Turma();
				turma.setIdTurma(rs.getLong("id_turma"));
				turma.setNomeTurma(rs.getString("nome_turma"));
				turma.setCursoTurma(rs.getString("curso_turma"));
				listaTurmas.add(turma);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}

		return listaTurmas;

	}





	public Object buscarPorId(long id) {
		
		this.conexao.abrirConexao();

		String sqlInsert = "SELECT * FROM turma WHERE id_turma=?;";
		PreparedStatement statement;
		Turma turma = new Turma();
		try {
			statement = this.conexao.getConexao().prepareStatement(sqlInsert);
			statement.setLong(1, id);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {

				turma.setIdTurma(rs.getLong("id_turma"));
				turma.setNomeTurma(rs.getString("nome_turma"));
				turma.setCursoTurma(rs.getString("curso_turma"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return turma;
	}	

}
