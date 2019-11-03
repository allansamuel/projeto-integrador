package exemplo.persistencia;


import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exemplo.model.Disciplina;
import exemplo.model.Turma;
import exemplo.model.TurmaDisciplina;
import exemplo.model.Usuario;
import exemplo.model.UsuarioTurmaDisciplina;

public class UsuarioTurmaDisciplinaDAO implements DAO{

	private ConexaoMysql conexao;

	public UsuarioTurmaDisciplinaDAO() {
		super();
		this.conexao = new ConexaoMysql("localhost", "root", "", "barack_pergunta");
	}

	public Object cadastrar(Object objeto) {

		UsuarioTurmaDisciplina usuarioTurmaDisciplina = (UsuarioTurmaDisciplina) objeto;
		this.conexao.abrirConexao();
		String sqlInsert = "INSERT INTO usuario_turma_disciplina VALUES(null, ?, ?);";
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);

			statement.setLong(1, usuarioTurmaDisciplina.getTurmaDisciplina().getIdTurmaDisciplina());
			statement.setLong(2, usuarioTurmaDisciplina.getUsuario().getIdUsuario());

			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if(rs.next()){
				usuarioTurmaDisciplina.setIdUsuarioTurmaDisciplina(rs.getLong(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			this.conexao.fecharConexao();
		}
		return usuarioTurmaDisciplina;
	}



	public void editar(Object objeto) {

		UsuarioTurmaDisciplina usuarioTurmaDisciplina = (UsuarioTurmaDisciplina) objeto;

		String sqlEditar = "UPDATE usuario_turma_disciplina SET id_turma_disciplina=?, id_usuario=? WHERE id_usuario_turma_disciplina=?;";
		this.conexao.abrirConexao();
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlEditar);
			statement.setLong(1, usuarioTurmaDisciplina.getTurmaDisciplina().getIdTurmaDisciplina());
			statement.setLong(2, usuarioTurmaDisciplina.getUsuario().getIdUsuario());
			statement.setLong(3, usuarioTurmaDisciplina.getIdUsuarioTurmaDisciplina());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	}

	public void excluir(long id) {

		String sqlExcluir = "DELETE FROM usuario_turma_disciplina WHERE id_usuario_turma_disciplina=?";
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
		String sqlSelect = "SELECT * FROM usuario_turma_disciplina utd INNER JOIN usuario u ON utd.id_usuario = u.id_usuario INNER JOIN turma_disciplina td ON utd.id_turma_disciplina = td.id_turma_disciplina INNER JOIN disciplina d ON td.id_disciplina = d.id_disciplina INNER JOIN turma t ON td.id_turma = t.id_turma;";
		PreparedStatement statement;
		UsuarioTurmaDisciplina usuarioTurmaDisciplina = null;
		List<Object> listaUsuarioTurmaDisciplinas = new ArrayList<Object>();
		try {
			statement = this.conexao.getConexao().prepareStatement(sqlSelect);
			ResultSet rs = statement.executeQuery();

			while(rs.next()) {

				usuarioTurmaDisciplina = new UsuarioTurmaDisciplina();
				usuarioTurmaDisciplina.setIdUsuarioTurmaDisciplina(rs.getLong("id_usuario_turma_disciplina"));

				Usuario usuario = new Usuario();

				usuario.setIdUsuario(rs.getLong("id_usuario"));
				usuario.setNomeUsuario(rs.getString("nome_usuario"));
				usuario.setLogin(rs.getString("login"));
				usuario.setEmail(rs.getString("email"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setCursoUsuario(rs.getString("curso_usuario"));
				usuario.setUrlPerfil(rs.getString("url_perfil"));

				usuarioTurmaDisciplina.setUsuario(usuario);

				TurmaDisciplina turmaDisciplina = new TurmaDisciplina();

				turmaDisciplina.setIdTurmaDisciplina(rs.getLong("id_turma_disciplina"));

				Turma turma = new Turma();

				turma.setIdTurma(rs.getLong("id_turma"));
				turma.setNomeTurma(rs.getString("nome_turma"));
				turma.setCursoTurma(rs.getString("curso_turma"));

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

				turmaDisciplina.setTurma(turma);
				turmaDisciplina.setDisciplina(disciplina);
				usuarioTurmaDisciplina.setTurmaDisciplina(turmaDisciplina);

				listaUsuarioTurmaDisciplinas.add(usuarioTurmaDisciplina);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}

		return listaUsuarioTurmaDisciplinas;
	}

	public Object buscarPorId(long id) {
		this.conexao.abrirConexao();
		String sqlInsert = "SELECT * FROM usuario_turma_disciplina utd INNER JOIN usuario u ON utd.id_usuario = u.id_usuario INNER JOIN turma_disciplina td ON utd.id_turma_disciplina = td.id_turma_disciplina INNER JOIN disciplina d ON td.id_disciplina = d.id_disciplina INNER JOIN turma t ON td.id_turma = t.id_turma WHERE utd.id_usuario_turma_disciplina=?;";
		PreparedStatement statement;
		UsuarioTurmaDisciplina usuarioTurmaDisciplina = new UsuarioTurmaDisciplina();
		try {
			statement = this.conexao.getConexao().prepareStatement(sqlInsert);
			statement.setLong(1, id);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {

				usuarioTurmaDisciplina.setIdUsuarioTurmaDisciplina(rs.getLong("id_usuario_turma_disciplina"));

				Usuario usuario = new Usuario();

				usuario.setIdUsuario(rs.getLong("id_usuario"));
				usuario.setNomeUsuario(rs.getString("nome_usuario"));
				usuario.setLogin(rs.getString("login"));
				usuario.setEmail(rs.getString("email"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setCursoUsuario(rs.getString("curso_usuario"));
				usuario.setUrlPerfil(rs.getString("url_perfil"));
				usuarioTurmaDisciplina.setUsuario(usuario);

				TurmaDisciplina turmaDisciplina = new TurmaDisciplina();

				turmaDisciplina.setIdTurmaDisciplina(rs.getLong("id_turma_disciplina"));

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

				Turma turma = new Turma();

				turma.setIdTurma(rs.getLong("id_turma"));
				turma.setNomeTurma(rs.getString("nome_turma"));
				turma.setCursoTurma(rs.getString("curso_turma"));
				turmaDisciplina.setTurma(turma);

				usuarioTurmaDisciplina.setTurmaDisciplina(turmaDisciplina);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		
		return usuarioTurmaDisciplina;
	}	

	public List<Object> buscarTurmasEDisciplinasPorUsuario(long id) {

		this.conexao.abrirConexao();

		String sqlInsert = "SELECT * FROM usuario_turma_disciplina utd INNER JOIN usuario u ON utd.id_usuario = u.id_usuario INNER JOIN turma_disciplina td ON utd.id_turma_disciplina = td.id_turma_disciplina INNER JOIN disciplina d ON td.id_disciplina = d.id_disciplina INNER JOIN turma t ON td.id_turma = t.id_turma WHERE u.id_usuario=?;";
		PreparedStatement statement;
		TurmaDisciplina turmaDisciplina = null;
		List<Object> listaTurmasEDisciplinasPorUsuario = new ArrayList<Object>();
		try {
			statement = this.conexao.getConexao().prepareStatement(sqlInsert);
			statement.setLong(1, id);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {

				turmaDisciplina = new TurmaDisciplina();

				turmaDisciplina.setIdTurmaDisciplina(rs.getLong("id_turma_disciplina"));

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

				Turma turma = new Turma();

				turma.setIdTurma(rs.getLong("id_turma"));
				turma.setNomeTurma(rs.getString("nome_turma"));
				turma.setCursoTurma(rs.getString("curso_turma"));
				turmaDisciplina.setTurma(turma);

				listaTurmasEDisciplinasPorUsuario.add(turmaDisciplina);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}


		return listaTurmasEDisciplinasPorUsuario;
	}	

}
