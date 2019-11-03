package exemplo.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exemplo.model.Disciplina;
import exemplo.model.Prova;
import exemplo.model.Turma;
import exemplo.model.TurmaDisciplina;
import exemplo.model.Usuario;

public class ProvaDAO implements DAO {
	private ConexaoMysql conexao;

	public ProvaDAO() {
		super();
		this.conexao = new ConexaoMysql("localhost", "root", "", "barack_pergunta");
	}

	public Object cadastrar(Object objeto) {

		Prova prova = (Prova) objeto;
		this.conexao.abrirConexao();
		String sqlInsert = "INSERT INTO prova VALUES(null, ?, ?, ?, ?, ?);";
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, prova.getTituloProva());
			statement.setString(2, prova.getDataProva());
			statement.setString(3, prova.getResumo());
			statement.setLong(4, prova.getUsuario().getIdUsuario());
			statement.setLong(5, prova.getTurmaDisciplina().getIdTurmaDisciplina());

			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if(rs.next()){
				prova.setIdProva(rs.getLong(1));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return prova;
	}



	public void editar(Object objeto) {

		Prova prova = (Prova) objeto;
		String sqlEditar = "UPDATE prova SET titulo_prova=?, data_prova=?, resumo=?, id_usuario=?, id_turma_disciplina=? WHERE id_prova=?;";
		this.conexao.abrirConexao();
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlEditar);
			statement.setString(1, prova.getTituloProva());
			statement.setString(2, prova.getDataProva());
			statement.setString(3, prova.getResumo());
			statement.setLong(4, prova.getUsuario().getIdUsuario());
			statement.setLong(5, prova.getTurmaDisciplina().getIdTurmaDisciplina());
			statement.setLong(6, prova.getIdProva());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	}

	public void excluir(long id) {
		String sqlExcluir = "DELETE FROM prova WHERE id_prova=?";
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
		String sqlSelect = "SELECT * FROM prova p INNER JOIN usuario u ON p.id_usuario = u.id_usuario INNER JOIN turma_disciplina td ON p.id_turma_disciplina = td.id_turma_disciplina INNER JOIN turma t ON td.id_turma = t.id_turma INNER JOIN disciplina d ON td.id_disciplina = d.id_disciplina;";
		PreparedStatement statement;
		Prova prova = null;
		List<Object> listaProvas = new ArrayList<Object>();

		try {
			statement = this.conexao.getConexao().prepareStatement(sqlSelect);

			ResultSet rs = statement.executeQuery();

			while(rs.next()) {
				prova = new Prova();
				prova.setIdProva(rs.getLong("id_prova"));
				prova.setTituloProva(rs.getString("titulo_prova"));
				prova.setDataProva(rs.getString("data_prova"));
				prova.setResumo(rs.getString("resumo"));

				Usuario usuario = new Usuario();
				usuario.setIdUsuario(rs.getLong("id_usuario"));
				usuario.setNomeUsuario(rs.getString("nome_usuario"));
				usuario.setCursoUsuario(rs.getString("curso_usuario"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setUrlPerfil(rs.getString("url_perfil"));
				usuario.setEmail(rs.getString("email"));
				usuario.setLogin(rs.getString("login"));
				prova.setUsuario(usuario);

				TurmaDisciplina turmaDisciplina = new TurmaDisciplina();
				turmaDisciplina.setIdTurmaDisciplina(rs.getLong("id_turma_disciplina"));

				Turma turma = new Turma();
				turma.setIdTurma(rs.getLong("id_turma"));
				turma.setNomeTurma(rs.getString("nome_turma"));
				turma.setCursoTurma(rs.getString("curso_turma"));
				turmaDisciplina.setTurma(turma);

				Disciplina disciplina= new Disciplina();
				disciplina.setIdDisciplina(rs.getLong("id_disciplina"));
				disciplina.setBibliografiaComplementar(rs.getString("bibliografia_complementar"));
				disciplina.setBibliografiaBasica(rs.getString("bibliografia_basica"));
				disciplina.setNomeDisciplina(rs.getString("nome_disciplina"));
				disciplina.setCargaHoraria(rs.getString("carga_horaria"));
				disciplina.setConteudo(rs.getString("conteudo"));
				disciplina.setEmenta(rs.getString("ementa"));
				disciplina.setCor(rs.getString("cor"));
				disciplina.setCurso(rs.getString("curso"));
				disciplina.setSerie(rs.getShort("serie"));
				turmaDisciplina.setDisciplina(disciplina);

				prova.setTurmaDisciplina(turmaDisciplina);

				listaProvas.add(prova);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	
		return listaProvas;
	}

	public Object buscarPorId(long id) {
		this.conexao.abrirConexao();
		String sqlInsert = "SELECT * FROM prova p INNER JOIN usuario u ON p.id_usuario = u.id_usuario INNER JOIN turma_disciplina td ON p.id_turma_disciplina = td.id_turma_disciplina INNER JOIN turma t ON td.id_turma = t.id_turma INNER JOIN disciplina d ON td.id_disciplina = d.id_disciplina WHERE id_prova=?;";
		PreparedStatement statement;
		Prova prova = new Prova();
		try {
			statement = this.conexao.getConexao().prepareStatement(sqlInsert);
			statement.setLong(1, id);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {

				prova.setIdProva(rs.getLong("id_prova"));
				prova.setTituloProva(rs.getString("titulo_prova"));
				prova.setDataProva(rs.getString("data_prova"));
				prova.setResumo(rs.getString("resumo"));

				Usuario usuario = new Usuario();
				usuario.setIdUsuario(rs.getLong("id_usuario"));
				usuario.setNomeUsuario(rs.getString("nome_usuario"));
				usuario.setCursoUsuario(rs.getString("curso_usuario"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setUrlPerfil(rs.getString("url_perfil"));
				usuario.setEmail(rs.getString("email"));
				usuario.setLogin(rs.getString("login"));
				prova.setUsuario(usuario);

				TurmaDisciplina turmaDisciplina = new TurmaDisciplina();
				turmaDisciplina.setIdTurmaDisciplina(rs.getLong("id_turma_disciplina"));

				Turma turma = new Turma();
				turma.setIdTurma(rs.getLong("id_turma"));
				turma.setNomeTurma(rs.getString("nome_turma"));
				turma.setCursoTurma(rs.getString("curso_turma"));
				turmaDisciplina.setTurma(turma);

				Disciplina disciplina= new Disciplina();
				disciplina.setIdDisciplina(rs.getLong("id_disciplina"));
				disciplina.setBibliografiaComplementar(rs.getString("bibliografia_complementar"));
				disciplina.setBibliografiaBasica(rs.getString("bibliografia_basica"));
				disciplina.setNomeDisciplina(rs.getString("nome_disciplina"));
				disciplina.setCargaHoraria(rs.getString("carga_horaria"));
				disciplina.setConteudo(rs.getString("conteudo"));
				disciplina.setEmenta(rs.getString("ementa"));
				disciplina.setCor(rs.getString("cor"));
				disciplina.setCurso(rs.getString("curso"));
				disciplina.setSerie(rs.getShort("serie"));
				turmaDisciplina.setDisciplina(disciplina);

				prova.setTurmaDisciplina(turmaDisciplina);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}

		return prova;
	}	

}
