package exemplo.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exemplo.model.Disciplina;
import exemplo.model.Usuario;
import exemplo.model.UsuarioDisciplina;

public class UsuarioDisciplinaDAO implements DAO{

	private ConexaoMysql conexao;

	public UsuarioDisciplinaDAO() {
		super();
		this.conexao = new ConexaoMysql("localhost", "root", "", "barack_pergunta");
	}

	public Object cadastrar(Object objeto) {

		UsuarioDisciplina usuarioDisciplina = (UsuarioDisciplina) objeto;
		this.conexao.abrirConexao();
		
		String sqlInsert = "INSERT INTO usuario_disciplina VALUES(null, ?, ?);";
		try {

			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);


			statement.setLong(1, usuarioDisciplina.getDisciplina().getIdDisciplina());
			statement.setLong(2, usuarioDisciplina.getUsuario().getIdUsuario());


			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if(rs.next()){

				usuarioDisciplina.setIdUsuarioDisciplina(rs.getLong(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			this.conexao.fecharConexao();
		}
		return usuarioDisciplina;
	}



	public void editar(Object objeto) {

		UsuarioDisciplina usuarioDisciplina = (UsuarioDisciplina) objeto;

		String sqlEditar = "UPDATE usuario_disciplina SET id_disciplina=?, id_usuario=? WHERE id_usuario_disciplina=?;";
		this.conexao.abrirConexao();
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlEditar);
			statement.setLong(1, usuarioDisciplina.getDisciplina().getIdDisciplina());
			statement.setLong(2, usuarioDisciplina.getUsuario().getIdUsuario());
			statement.setLong(3, usuarioDisciplina.getIdUsuarioDisciplina());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	}

	public void excluir(long id) {
		String sqlExcluir = "DELETE FROM usuario_disciplina WHERE id_usuario_disciplina=?";
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

		String sqlSelect = "SELECT * FROM usuario_disciplina ud INNER JOIN usuario u ON ud.id_usuario = u.id_usuario INNER JOIN disciplina d ON ud.id_disciplina = d.id_disciplina;";
		PreparedStatement statement;
		UsuarioDisciplina usuarioDisciplina = null;
		List<Object> listaUsuarioDisciplinas = new ArrayList<Object>();
		try {
			statement = this.conexao.getConexao().prepareStatement(sqlSelect);
			ResultSet rs = statement.executeQuery();

			while(rs.next()) {

				usuarioDisciplina = new UsuarioDisciplina();

				usuarioDisciplina.setIdUsuarioDisciplina(rs.getLong("id_usuario_disciplina"));

				Usuario usuario = new Usuario();

				usuario.setIdUsuario(rs.getLong("id_usuario"));
				usuario.setNomeUsuario(rs.getString("nome_usuario"));
				usuario.setLogin(rs.getString("login"));
				usuario.setEmail(rs.getString("email"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setCursoUsuario(rs.getString("curso_usuario"));
				usuario.setUrlPerfil(rs.getString("url_perfil"));
				usuarioDisciplina.setUsuario(usuario);

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
				usuarioDisciplina.setDisciplina(disciplina);

				listaUsuarioDisciplinas.add(usuarioDisciplina);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}

		return listaUsuarioDisciplinas;
	}


	public Object buscarPorId(long id) {

		this.conexao.abrirConexao();

		String sqlInsert = "SELECT * FROM usuario_disciplina INNER JOIN usuario ON usuario_disciplina.id_usuario = usuario.id_usuario INNER JOIN disciplina ON usuario_disciplina.id_disciplina = disciplina.id_disciplina WHERE id_usuario_disciplina=?;";
		PreparedStatement statement;
		UsuarioDisciplina usuarioDisciplina = new UsuarioDisciplina();
		try {
			statement = this.conexao.getConexao().prepareStatement(sqlInsert);
			statement.setLong(1, id);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {

				usuarioDisciplina.setIdUsuarioDisciplina(rs.getLong("id_usuario_disciplina"));

				Usuario usuario = new Usuario();

				usuario.setIdUsuario(rs.getLong("id_usuario"));
				usuario.setNomeUsuario(rs.getString("nome_usuario"));
				usuario.setLogin(rs.getString("login"));
				usuario.setEmail(rs.getString("email"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setCursoUsuario(rs.getString("curso_usuario"));
				usuario.setUrlPerfil(rs.getString("url_perfil"));
				usuarioDisciplina.setUsuario(usuario);

				Disciplina disciplina = new Disciplina();

				disciplina.setIdDisciplina(rs.getLong("id_disciplina"));
				disciplina.setBibliografiaBasica(rs.getString("bibliografia_basica"));
				disciplina.setBibliografiaComplementar(rs.getString("bibliografia_complementar"));
				disciplina.setCargaHoraria(rs.getString("carga_horaria"));
				disciplina.setConteudo(rs.getString("conteudo"));
				disciplina.setCor(rs.getString("cor"));
				disciplina.setCurso(rs.getString("curso"));
				disciplina.setSerie(rs.getShort("serie"));
				disciplina.setEmenta(rs.getString("ementa"));
				disciplina.setNomeDisciplina(rs.getString("nome_disciplina"));

				usuarioDisciplina.setDisciplina(disciplina);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		
		return usuarioDisciplina;
	}	



	public List<Object> buscarDisciplinasPorUsuario(long id) {

		this.conexao.abrirConexao();
		
		String sqlInsert = "SELECT * FROM usuario_disciplina INNER JOIN usuario ON usuario_disciplina.id_usuario = usuario.id_usuario INNER JOIN disciplina ON usuario_disciplina.id_disciplina = disciplina.id_disciplina WHERE usuario_disciplina.id_usuario=?;";
		PreparedStatement statement;
		Disciplina disciplina = null;
		List<Object> listaDisciplinasPorUsuario = new ArrayList<Object>();
		try {
			statement = this.conexao.getConexao().prepareStatement(sqlInsert);
			statement.setLong(1, id);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {

				disciplina = new Disciplina();
				disciplina.setIdDisciplina(rs.getLong("id_disciplina"));
				disciplina.setBibliografiaBasica(rs.getString("bibliografia_basica"));
				disciplina.setBibliografiaComplementar(rs.getString("bibliografia_complementar"));
				disciplina.setCargaHoraria(rs.getString("carga_horaria"));
				disciplina.setConteudo(rs.getString("conteudo"));
				disciplina.setCor(rs.getString("cor"));
				disciplina.setCurso(rs.getString("curso"));
				disciplina.setSerie(rs.getShort("serie"));
				disciplina.setEmenta(rs.getString("ementa"));
				disciplina.setNomeDisciplina(rs.getString("nome_disciplina"));

				listaDisciplinasPorUsuario.add(disciplina);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		
		return listaDisciplinasPorUsuario;
	}	
}