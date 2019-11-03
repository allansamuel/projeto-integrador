package exemplo.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exemplo.model.Usuario;
import exemplo.persistencia.ConexaoMysql;



public class UsuarioDAO implements DAO {

	private ConexaoMysql conexao;

	public UsuarioDAO() {
		super();
		this.conexao = new ConexaoMysql("localhost", "root", "", "barack_pergunta");
	}

	public Object cadastrar(Object objeto) {

		Usuario usuario = (Usuario) objeto;
		this.conexao.abrirConexao();

		String sqlInsert = "INSERT INTO usuario VALUES(null, ?, ?, ?, ?, ?, ?);";
		try {

			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);

			statement.setString(1, usuario.getNomeUsuario());
			statement.setString(2, usuario.getCursoUsuario());
			statement.setString(3, usuario.getSenha());
			statement.setString(4, usuario.getUrlPerfil());
			statement.setString(5, usuario.getEmail());
			statement.setString(6, usuario.getLogin());


			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if(rs.next()){

				usuario.setIdUsuario(rs.getLong(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(usuario.getIdUsuario());
		} finally {

			this.conexao.fecharConexao();
		}
		return usuario;
	}



	public void editar(Object objeto) {

		Usuario usuario = (Usuario) objeto;

		String sqlEditar = "UPDATE usuario SET nome_usuario=?, login=?, email=?, senha=?, curso_usuario=?, url_perfil=? WHERE id_usuario=?;";
		this.conexao.abrirConexao();
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlEditar);
			statement.setString(1, usuario.getNomeUsuario());
			statement.setString(2, usuario.getLogin());
			statement.setString(3, usuario.getEmail());
			statement.setString(4, usuario.getSenha());
			statement.setString(5, usuario.getCursoUsuario());
			statement.setString(6, usuario.getUrlPerfil());
			statement.setLong(7, usuario.getIdUsuario());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	}

	public void excluir(long id) {
		String sqlExcluir = "DELETE FROM usuario WHERE id_usuario=?";
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

		String sqlSelect = "SELECT * FROM usuario;";
		PreparedStatement statement;
		Usuario usuario = null;
		List<Object> listaUsuarios = new ArrayList<Object>();
		try {
			statement = this.conexao.getConexao().prepareStatement(sqlSelect);
			ResultSet rs = statement.executeQuery();

			while(rs.next()) {

				usuario = new Usuario();
				usuario.setIdUsuario(rs.getLong("id_usuario"));
				usuario.setNomeUsuario(rs.getString("nome_usuario"));
				usuario.setLogin(rs.getString("login"));
				usuario.setEmail(rs.getString("email"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setCursoUsuario(rs.getString("curso_usuario"));
				usuario.setUrlPerfil(rs.getString("url_perfil"));
				listaUsuarios.add(usuario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}

		return listaUsuarios;

	}


	public Object buscarPorId(long id) {

		this.conexao.abrirConexao();

		String sqlInsert = "SELECT * FROM usuario WHERE id_usuario=?;";
		PreparedStatement statement;
		Usuario usuario = new Usuario();
		try {
			statement = this.conexao.getConexao().prepareStatement(sqlInsert);
			statement.setLong(1, id);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {

				usuario.setIdUsuario(rs.getLong("id_usuario"));
				usuario.setNomeUsuario(rs.getString("nome_usuario"));
				usuario.setLogin(rs.getString("login"));
				usuario.setEmail(rs.getString("email"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setCursoUsuario(rs.getString("curso_usuario"));
				usuario.setUrlPerfil(rs.getString("url_perfil"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return usuario;
	}	

	public Usuario buscarPorEmailESenha(String email, String senha){
		this.conexao.abrirConexao();
		String sqlInsert = "SELECT * FROM usuario WHERE email = ? AND senha = ?";
		PreparedStatement statement;
		Usuario usuario = null;
		try {
			statement = (PreparedStatement) this.conexao.getConexao().prepareStatement(sqlInsert);
			statement.setString(1, email);
			statement.setString(2, senha);
			ResultSet rs = statement.executeQuery();
			if (rs.next()){
				usuario = new Usuario();
				usuario.setIdUsuario(rs.getLong("id_usuario"));
				usuario.setNomeUsuario(rs.getString("nome_usuario"));
				usuario.setLogin(rs.getString("login"));
				usuario.setEmail(rs.getString("email"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setCursoUsuario(rs.getString("curso_usuario"));
				usuario.setUrlPerfil(rs.getString("url_perfil"));
			}
		} catch (SQLException e) {				
			e.printStackTrace();
		}finally{
			this.conexao.fecharConexao();
		}

		return usuario;
	}



	public Usuario buscarPorEmail(String email) {
		this.conexao.abrirConexao();

		String sqlInsert = "SELECT * FROM usuario WHERE email=?;";
		PreparedStatement statement;
		Usuario usuario = new Usuario();
		try {
			statement = this.conexao.getConexao().prepareStatement(sqlInsert);
			statement.setString(1, email);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {

				usuario.setIdUsuario(rs.getLong("id_usuario"));
				usuario.setNomeUsuario(rs.getString("nome_usuario"));
				usuario.setLogin(rs.getString("login"));
				usuario.setEmail(rs.getString("email"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setCursoUsuario(rs.getString("curso_usuario"));
				usuario.setUrlPerfil(rs.getString("url_perfil"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return usuario;
	}	

	public List<Object> buscarPorNome(String nome) {

		this.conexao.abrirConexao();

		String sqlInsert = "SELECT * FROM usuario WHERE nome_usuario=?;";
		PreparedStatement statement;
		Usuario usuario = null;
		List<Object> listaUsuarios = new ArrayList<Object>();
		try {
			statement = this.conexao.getConexao().prepareStatement(sqlInsert);
			statement.setString(1, nome);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {

				usuario = new Usuario();

				usuario.setIdUsuario(rs.getLong("id_usuario"));
				usuario.setNomeUsuario(rs.getString("nome_usuario"));
				usuario.setLogin(rs.getString("login"));
				usuario.setEmail(rs.getString("email"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setCursoUsuario(rs.getString("curso_usuario"));
				usuario.setUrlPerfil(rs.getString("url_perfil"));

				listaUsuarios.add(usuario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}

		return listaUsuarios;
	}	


}
