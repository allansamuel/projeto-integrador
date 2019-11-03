package exemplo.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exemplo.model.Disciplina;
import exemplo.model.Turma;
import exemplo.model.TurmaDisciplina;

public class TurmaDisciplinaDAO implements DAO {

	private ConexaoMysql conexao;

	public TurmaDisciplinaDAO() {
		super();
		this.conexao = new ConexaoMysql("localhost", "root", "", "barack_pergunta");
	}

	public Object cadastrar(Object objeto) {

		TurmaDisciplina turmaDisciplina = (TurmaDisciplina) objeto;
		this.conexao.abrirConexao();
		String sqlInsert = "INSERT INTO turma_disciplina VALUES(null, ?, ?);";
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);

			statement.setLong(1, turmaDisciplina.getDisciplina().getIdDisciplina());
			statement.setLong(2, turmaDisciplina.getTurma().getIdTurma());

			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if(rs.next()){
				turmaDisciplina.setIdTurmaDisciplina(rs.getLong(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			this.conexao.fecharConexao();
		}
		return turmaDisciplina;
	}



	public void editar(Object objeto) {

		TurmaDisciplina turmaDisciplina = (TurmaDisciplina) objeto;

		String sqlEditar = "UPDATE turma_disciplina SET id_disciplina=?, id_turma=? WHERE id_turma_disciplina=?;";
		this.conexao.abrirConexao();
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlEditar);
			statement.setLong(1, turmaDisciplina.getDisciplina().getIdDisciplina());
			statement.setLong(2, turmaDisciplina.getTurma().getIdTurma());
			statement.setLong(3, turmaDisciplina.getIdTurmaDisciplina());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	}

	public void excluir(long id) {

		String sqlExcluir = "DELETE FROM turma_disciplina WHERE id_turma_disciplina=?";
		this.conexao.abrirConexao();
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlExcluir);
			statement.setLong(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	}

	public List<Object> buscarTodos() {
		this.conexao.abrirConexao();
		String sqlSelect = "SELECT * FROM turma_disciplina td INNER JOIN turma t ON td.id_turma = t.id_turma INNER JOIN disciplina d ON td.id_disciplina = d.id_disciplina;";
		PreparedStatement statement;
		TurmaDisciplina turmaDisciplina = null;
		List<Object> listaTurmaDisciplinas = new ArrayList<Object>();
		try {
			statement = this.conexao.getConexao().prepareStatement(sqlSelect);
			ResultSet rs = statement.executeQuery();

			while(rs.next()) {

				turmaDisciplina = new TurmaDisciplina();
				
				turmaDisciplina.setIdTurmaDisciplina(rs.getLong("id_turma_disciplina"));

				Turma turma = new Turma();

				turma.setIdTurma(rs.getLong("id_turma"));
				turma.setNomeTurma(rs.getString("nome_turma"));
				turma.setCursoTurma(rs.getString("curso_turma"));
				turmaDisciplina.setTurma(turma);

				Disciplina disciplina = new Disciplina();

				disciplina.setIdDisciplina(rs.getLong("id_disciplina"));
				disciplina.setBibliografiaBasica(rs.getString("bibliografia_basica"));
				disciplina.setBibliografiaComplementar(rs.getString("bibliografia_complementar"));
				disciplina.setCargaHoraria(rs.getString("carga_horaria"));
				disciplina.setConteudo(rs.getString("conteudo"));
				disciplina.setCor(rs.getString("cor"));
				disciplina.setSerie(rs.getShort("serie"));
				disciplina.setEmenta(rs.getString("ementa"));
				disciplina.setCurso(rs.getString("curso"));
				disciplina.setNomeDisciplina(rs.getString("nome_disciplina"));
				turmaDisciplina.setDisciplina(disciplina);

				listaTurmaDisciplinas.add(turmaDisciplina);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	
		return listaTurmaDisciplinas;
	}

	public Object buscarPorId(long id) {
		this.conexao.abrirConexao();
		String sqlInsert = "SELECT * FROM turma_disciplina td INNER JOIN turma t ON td.id_turma = t.id_turma INNER JOIN disciplina d ON td.id_disciplina = d.id_disciplina;";
		PreparedStatement statement;
		TurmaDisciplina turmaDisciplina = new TurmaDisciplina();
		try {
			statement = this.conexao.getConexao().prepareStatement(sqlInsert);
			statement.setLong(1, id);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {

				turmaDisciplina.setIdTurmaDisciplina(rs.getLong("id_turma_disciplina"));

				Turma turma = new Turma();

				turma.setIdTurma(rs.getLong("id_turma"));
				turma.setNomeTurma(rs.getString("nome_turma"));
				turma.setCursoTurma(rs.getString("curso_turma"));

				turmaDisciplina.setTurma(turma);

				Disciplina disciplina = new Disciplina();

				disciplina.setIdDisciplina(rs.getLong("id_disciplina"));
				disciplina.setBibliografiaBasica(rs.getString("bibliografia_basica"));
				disciplina.setBibliografiaComplementar(rs.getString("bibliografia_complementar"));
				disciplina.setCargaHoraria(rs.getString("carga_horaria"));
				disciplina.setConteudo(rs.getString("conteudo"));
				disciplina.setCor(rs.getString("cor"));
				disciplina.setSerie(rs.getShort("serie"));
				disciplina.setCurso(rs.getString("curso"));
				disciplina.setEmenta(rs.getString("ementa"));
				disciplina.setNomeDisciplina(rs.getString("nome_disciplina"));

				turmaDisciplina.setDisciplina(disciplina);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		
		return turmaDisciplina;
	}	
	
	public List<Object> buscarPorDisciplina(long id) {
		this.conexao.abrirConexao();
		String sqlSelect =	"SELECT * FROM turma_disciplina td INNER JOIN turma t ON td.id_turma = t.id_turma INNER JOIN disciplina d ON td.id_disciplina = d.id_disciplina WHERE td.id_disciplina=?;";

		PreparedStatement statement;
		TurmaDisciplina turmaDisciplina = null;
		List<Object> listaTurmaDisciplinas = new ArrayList<Object>();
		try {
			statement = this.conexao.getConexao().prepareStatement(sqlSelect);
			statement.setLong(1, id);
			ResultSet rs = statement.executeQuery();

			while(rs.next()) {

				turmaDisciplina = new TurmaDisciplina();
				
				turmaDisciplina.setIdTurmaDisciplina(rs.getLong("id_turma_disciplina"));

				Turma turma = new Turma();

				turma.setIdTurma(rs.getLong("id_turma"));
				turma.setNomeTurma(rs.getString("nome_turma"));
				turma.setCursoTurma(rs.getString("curso_turma"));
				turmaDisciplina.setTurma(turma);

				Disciplina disciplina = new Disciplina();

				disciplina.setIdDisciplina(rs.getLong("id_disciplina"));
				disciplina.setBibliografiaBasica(rs.getString("bibliografia_basica"));
				disciplina.setBibliografiaComplementar(rs.getString("bibliografia_complementar"));
				disciplina.setCargaHoraria(rs.getString("carga_horaria"));
				disciplina.setConteudo(rs.getString("conteudo"));
				disciplina.setCor(rs.getString("cor"));
				disciplina.setSerie(rs.getShort("serie"));
				disciplina.setEmenta(rs.getString("ementa"));
				disciplina.setCurso(rs.getString("curso"));
				disciplina.setNomeDisciplina(rs.getString("nome_disciplina"));
				turmaDisciplina.setDisciplina(disciplina);

				listaTurmaDisciplinas.add(turmaDisciplina);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	
		return listaTurmaDisciplinas;
	}
	
	

}
