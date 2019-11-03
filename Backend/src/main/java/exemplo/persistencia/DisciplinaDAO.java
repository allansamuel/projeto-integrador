package exemplo.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exemplo.model.Disciplina;

public class DisciplinaDAO implements DAO {


	private ConexaoMysql conexao;

	public DisciplinaDAO() {
		super();
		this.conexao = new ConexaoMysql("localhost", "root", "", "barack_pergunta");
	}

	public Object cadastrar(Object objeto) {

		Disciplina disciplina = (Disciplina) objeto;
		this.conexao.abrirConexao();

		String sqlInsert = "INSERT INTO disciplina VALUES(null, ?, ?, ?, ?, ?, ?, ?, ?);";
		try {

			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);

			statement.setString(1, disciplina.getNomeDisciplina());
			statement.setString(2, disciplina.getEmenta());
			statement.setString(3, disciplina.getBibliografiaBasica());
			statement.setString(4, disciplina.getCargaHoraria());
			statement.setString(5, disciplina.getConteudo());
			statement.setString(6, disciplina.getBibliografiaComplementar());
			statement.setString(7, disciplina.getCor());
			statement.setInt(8, disciplina.getSerie());
			statement.setString(9, disciplina.getCurso());


			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if(rs.next()){

				disciplina.setIdDisciplina(rs.getLong(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
		
			this.conexao.fecharConexao();
		}
		return disciplina;
	}




	public void editar(Object objeto) {
		String sqlEditar = "UPDATE disciplina SET nome_disciplina=?, ementa=?, bibliografia_basica=?, carga_horaria=?, conteudo=?, bibliografia_complementar=?, cor=?, serie=?, curso=? WHERE id_disciplina=?";

		Disciplina disciplina = (Disciplina) objeto;
		this.conexao.abrirConexao();

		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlEditar);
			statement.setString(1, disciplina.getNomeDisciplina());
			statement.setString(2, disciplina.getEmenta());
			statement.setString(3, disciplina.getBibliografiaBasica());
			statement.setString(4, disciplina.getCargaHoraria());
			statement.setString(5, disciplina.getConteudo());
			statement.setString(6, disciplina.getBibliografiaComplementar());
			statement.setString(7, disciplina.getCor());
			statement.setInt(8, disciplina.getSerie());
			statement.setString(9, disciplina.getCurso());
			statement.setLong(10, disciplina.getIdDisciplina());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	}


	public void excluir(long id) {
		String sqlExcluir = "DELETE FROM disciplina WHERE id_disciplina=?";


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
		
		String sqlSelect = "SELECT * FROM disciplina;";
		PreparedStatement statement;
		Disciplina disciplina = null;
		List<Object> listaDisciplinas = new ArrayList<Object>();
		try {
			statement = this.conexao.getConexao().prepareStatement(sqlSelect);
			ResultSet rs = statement.executeQuery();

			while(rs.next()) {
				disciplina = new Disciplina();
				disciplina.setIdDisciplina(rs.getLong("id_disciplina"));
				disciplina.setNomeDisciplina(rs.getString("nome_disciplina"));
				disciplina.setEmenta(rs.getString("ementa"));
				disciplina.setBibliografiaBasica(rs.getString("bibliografia_basica"));
				disciplina.setCargaHoraria(rs.getString("carga_horaria"));
				disciplina.setConteudo(rs.getString("conteudo"));
				disciplina.setBibliografiaComplementar(rs.getString("bibliografia_complementar"));
				disciplina.setCor(rs.getString("cor"));
				disciplina.setSerie(rs.getShort("serie"));
				disciplina.setCurso(rs.getString("curso"));
				listaDisciplinas.add(disciplina);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		
		return listaDisciplinas;
	}



	public Object buscarPorId(long id) {
		
		this.conexao.abrirConexao();
	
		String sqlInsert = "SELECT * FROM disciplina WHERE id_disciplina=?;";
		PreparedStatement statement;
		Disciplina disciplina = new Disciplina();
		try {
			statement = this.conexao.getConexao().prepareStatement(sqlInsert);
			statement.setLong(1, id);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {

				disciplina.setIdDisciplina(rs.getLong("id_disciplina"));
				disciplina.setNomeDisciplina(rs.getString("nome_disciplina"));
				disciplina.setEmenta(rs.getString("ementa"));
				disciplina.setBibliografiaBasica(rs.getString("bibliografia_basica"));
				disciplina.setCargaHoraria(rs.getString("carga_horaria"));
				disciplina.setConteudo(rs.getString("conteudo"));
				disciplina.setBibliografiaComplementar(rs.getString("bibliografia_complementar"));
				disciplina.setCor(rs.getString("cor"));
				disciplina.setSerie(rs.getShort("serie"));
				disciplina.setCurso(rs.getString("curso"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}


		return disciplina;
	}	
	
		public ArrayList<Disciplina> buscarPorCursoESerie(String curso, int serie ) {
		
		this.conexao.abrirConexao();
	
		String sqlInsert = "SELECT * FROM disciplina  where (curso=? or curso='') and serie=?;";
		PreparedStatement statement;
		ArrayList<Disciplina> listaDisciplinas = new ArrayList<Disciplina>();
		Disciplina disciplina = null;
		try {
			statement = this.conexao.getConexao().prepareStatement(sqlInsert);
			statement.setString(1, curso);
			statement.setInt(2,serie);

			ResultSet rs = statement.executeQuery();
			while(rs.next()) {

				disciplina = new Disciplina();
				
				disciplina.setIdDisciplina(rs.getLong("id_disciplina"));
				disciplina.setNomeDisciplina(rs.getString("nome_disciplina"));
				disciplina.setEmenta(rs.getString("ementa"));
				disciplina.setBibliografiaBasica(rs.getString("bibliografia_basica"));
				disciplina.setCargaHoraria(rs.getString("carga_horaria"));
				disciplina.setConteudo(rs.getString("conteudo"));
				disciplina.setBibliografiaComplementar(rs.getString("bibliografia_complementar"));
				disciplina.setCor(rs.getString("cor"));
				disciplina.setSerie(rs.getShort("serie"));
				disciplina.setCurso(rs.getString("curso"));
				listaDisciplinas.add(disciplina);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}


		return listaDisciplinas;
	}	
			
	
}
