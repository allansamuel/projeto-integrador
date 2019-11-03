package exemplo.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exemplo.model.Comentario;
import exemplo.model.Disciplina;
import exemplo.model.Postagem;
import exemplo.model.Turma;
import exemplo.model.TurmaDisciplina;
import exemplo.model.Usuario;


public class ComentarioDAO implements DAO{

	private ConexaoMysql conexao;

	public ComentarioDAO() {
		super();
		this.conexao = new ConexaoMysql("localhost", "root", "", "barack_pergunta");
	}

	public Object cadastrar(Object objeto) {

		Comentario comentario = (Comentario) objeto;
		this.conexao.abrirConexao();
		String sqlInsert = "INSERT INTO comentario VALUES(null, ?, ?, ?, ?, ?, ?);";
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			statement.setLong(1, comentario.getAvaliacao());
			statement.setString(2, comentario.getUrlImagem());
			statement.setString(3, comentario.getTextoComentario());
			statement.setBoolean(4, comentario.getAnonimoComentario());
			statement.setLong(5, comentario.getPostagem().getIdPostagem());
			statement.setLong(6, comentario.getUsuario().getIdUsuario());

			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if(rs.next()){
				comentario.setIdComentario(rs.getLong(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
		} finally {
			this.conexao.fecharConexao();
		}
		return comentario;
	}



	public void editar(Object objeto) {

		Comentario comentario = (Comentario) objeto;

		String sqlEditar = "UPDATE comentario SET avaliacao=?, url_imagem=?, texto_comentario=?, anonimo_comentario=?, id_postagem=?, id_usuario=? WHERE id_comentario=?;";
		this.conexao.abrirConexao();
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlEditar);
			statement.setLong(1, comentario.getAvaliacao());
			statement.setString(2, comentario.getUrlImagem());
			statement.setString(3, comentario.getTextoComentario());
			statement.setBoolean(4, comentario.getAnonimoComentario());
			statement.setLong(5,comentario.getPostagem().getIdPostagem());
			statement.setLong(6, comentario.getUsuario().getIdUsuario());
			statement.setLong(7, comentario.getIdComentario());


			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	}

	public void excluir(long id) {
		
		String sqlExcluir = "DELETE FROM comentario WHERE id_comentario=?";
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
		String sqlSelect = "SELECT * FROM comentario c INNER JOIN usuario u ON c.id_usuario = u.id_usuario INNER JOIN postagem p ON c.id_postagem = p.id_postagem INNER JOIN turma_disciplina td ON p.id_turma_disciplina = td.id_turma_disciplina INNER JOIN turma t ON td.id_turma = t.id_turma INNER JOIN disciplina d ON td.id_disciplina = d.id_disciplina;";
		PreparedStatement statement;
		Comentario comentario = new Comentario();
		List<Object> listaComentarios = new ArrayList<Object>();
		try {
			statement = this.conexao.getConexao().prepareStatement(sqlSelect);
			ResultSet rs = statement.executeQuery();

			while(rs.next()) {
				
				comentario = new Comentario();
				
				comentario.setIdComentario(rs.getLong("id_comentario"));
				comentario.setAvaliacao(rs.getInt("avaliacao"));
				comentario.setAnonimoComentario(rs.getBoolean("anonimo_comentario"));
				comentario.setTextoComentario(rs.getString("texto_comentario"));
				comentario.setUrlImagem(rs.getString("url_imagem"));

				Usuario usuario = new Usuario();

				usuario.setIdUsuario(rs.getLong("id_usuario"));
				usuario.setNomeUsuario(rs.getString("nome_usuario"));
				usuario.setLogin(rs.getString("login"));
				usuario.setEmail(rs.getString("email"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setCursoUsuario(rs.getString("curso_usuario"));
				usuario.setUrlPerfil(rs.getString("url_perfil"));
				comentario.setUsuario(usuario);

				Postagem postagem = new Postagem();

				postagem.setIdPostagem(rs.getLong("id_postagem"));
				postagem.setFavorito(rs.getBoolean("favorito"));
				postagem.setUrlImagem(rs.getString("url_imagem"));
				postagem.setTextoPostagem(rs.getString("texto_postagem"));
				
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
				disciplina.setCurso(rs.getString("curso"));
				disciplina.setSerie(rs.getShort("serie"));
				disciplina.setEmenta(rs.getString("ementa"));
				disciplina.setNomeDisciplina(rs.getString("nome_disciplina"));

				turmaDisciplina.setTurma(turma);
				turmaDisciplina.setDisciplina(disciplina);
				postagem.setTurmaDisciplina(turmaDisciplina);

				comentario.setPostagem(postagem);


				listaComentarios.add(comentario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		
		return listaComentarios;
	}

	public Object buscarPorId(long id) {
		this.conexao.abrirConexao();
		String sqlInsert = "SELECT * FROM comentario c INNER JOIN usuario u ON c.id_usuario = u.id_usuario INNER JOIN postagem p ON c.id_postagem = p.id_postagem INNER JOIN turma_disciplina td ON p.id_turma_disciplina = td.id_turma_disciplina INNER JOIN turma t ON td.id_turma = t.id_turma INNER JOIN disciplina d ON td.id_disciplina = d.id_disciplina WHERE id_comentario=?;";
		PreparedStatement statement;
		Comentario comentario = new Comentario();;
		try {
			statement = this.conexao.getConexao().prepareStatement(sqlInsert);
			statement.setLong(1, id);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				
				comentario.setIdComentario(rs.getLong("id_comentario"));
				comentario.setAvaliacao(rs.getInt("avaliacao"));
				comentario.setAnonimoComentario(rs.getBoolean("anonimo_comentario"));
				comentario.setTextoComentario(rs.getString("texto_comentario"));
				comentario.setUrlImagem(rs.getString("url_imagem"));
				

				Usuario usuario = new Usuario();

				usuario.setIdUsuario(rs.getLong("id_usuario"));
				usuario.setNomeUsuario(rs.getString("nome_usuario"));
				usuario.setLogin(rs.getString("login"));
				usuario.setEmail(rs.getString("email"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setCursoUsuario(rs.getString("curso_usuario"));
				usuario.setUrlPerfil(rs.getString("url_perfil"));
				comentario.setUsuario(usuario);

				Postagem postagem = new Postagem();

				postagem.setIdPostagem(rs.getLong("id_postagem"));
				postagem.setFavorito(rs.getBoolean("favorito"));
				postagem.setUrlImagem(rs.getString("url_imagem"));
				postagem.setTextoPostagem(rs.getString("texto_postagem"));
				
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
				disciplina.setCurso(rs.getString("curso"));
				disciplina.setSerie(rs.getShort("serie"));
				disciplina.setEmenta(rs.getString("ementa"));
				disciplina.setNomeDisciplina(rs.getString("nome_disciplina"));

				turmaDisciplina.setTurma(turma);
				turmaDisciplina.setDisciplina(disciplina);
				postagem.setTurmaDisciplina(turmaDisciplina);

				comentario.setPostagem(postagem);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return comentario;
	}	
}
