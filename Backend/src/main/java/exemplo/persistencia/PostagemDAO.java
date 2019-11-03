package exemplo.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import exemplo.model.Disciplina;
import exemplo.model.Postagem;
import exemplo.model.Turma;
import exemplo.model.TurmaDisciplina;
import exemplo.model.Usuario;

public class PostagemDAO implements DAO{

	private ConexaoMysql conexao;

	public PostagemDAO() {
		super();
		this.conexao = new ConexaoMysql("localhost", "root", "", "barack_pergunta");
	}

	public Object cadastrar(Object objeto) {

		Postagem postagem = (Postagem) objeto;
		this.conexao.abrirConexao();
		String sqlInsert = "INSERT INTO postagem VALUES(null, ?, ?, ?, ?, ?, ?, ?);";
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			statement.setBoolean(1, postagem.getFavorito());
			statement.setString(2, postagem.getUrlImagem());
			statement.setString(3, postagem.getTextoPostagem());
			statement.setString(4, postagem.getTituloPostagem());
			statement.setBoolean(5, postagem.getAnonimoPostagem());
			statement.setLong(6, postagem.getUsuario().getIdUsuario());
			statement.setLong(7, postagem.getTurmaDisciplina().getIdTurmaDisciplina());

			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if(rs.next()){
				postagem.setIdPostagem(rs.getLong(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			this.conexao.fecharConexao();
		}
		return postagem;
	}



	public void editar(Object objeto) {

		Postagem postagem = (Postagem) objeto;

		String sqlEditar = "UPDATE postagem SET favorito=?, url_imagem=?, texto_postagem=?, titulo_postagem=?, anonimo_postagem=?, id_usuario=?, id_turma_disciplina=? WHERE id_postagem=?;";
		this.conexao.abrirConexao();
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlEditar);
			statement.setBoolean(1, postagem.getFavorito());
			statement.setString(2, postagem.getUrlImagem());
			statement.setString(3, postagem.getTextoPostagem());
			statement.setString(4, postagem.getTituloPostagem());
			statement.setBoolean(5, postagem.getAnonimoPostagem());
			statement.setLong(6, postagem.getUsuario().getIdUsuario());
			statement.setLong(7, postagem.getTurmaDisciplina().getIdTurmaDisciplina());
			statement.setLong(8, postagem.getIdPostagem());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	}

	public void excluir(long id) {
		String sqlExcluir = "DELETE FROM postagem WHERE id_postagem=?";
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
		String sqlSelect = "SELECT * FROM postagem p INNER JOIN usuario u ON p.id_usuario = u.id_usuario INNER JOIN turma_disciplina td ON p.id_turma_disciplina = td.id_turma_disciplina INNER JOIN turma t ON td.id_turma = t.id_turma INNER JOIN disciplina d ON td.id_disciplina = d.id_disciplina;";
		PreparedStatement statement;
		Postagem postagem = null;
		List<Object> listaPostagens = new ArrayList<Object>();
		try {
			statement = this.conexao.getConexao().prepareStatement(sqlSelect);
			ResultSet rs = statement.executeQuery();

			while(rs.next()) {
				postagem = new Postagem();
				postagem.setIdPostagem(rs.getLong("id_postagem"));
				postagem.setFavorito(rs.getBoolean("favorito"));
				postagem.setUrlImagem(rs.getString("url_imagem"));
				postagem.setTextoPostagem(rs.getString("texto_postagem"));

				Usuario usuario = new Usuario();
				usuario.setIdUsuario(rs.getLong("id_usuario"));
				usuario.setNomeUsuario(rs.getString("nome_usuario"));
				usuario.setCursoUsuario(rs.getString("curso_usuario"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setUrlPerfil(rs.getString("url_perfil"));
				usuario.setEmail(rs.getString("email"));
				usuario.setLogin(rs.getString("login"));
				postagem.setUsuario(usuario);

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
				disciplina.setCurso(rs.getString("curso"));
				disciplina.setCor(rs.getString("cor"));
				disciplina.setSerie(rs.getShort("serie"));
				turmaDisciplina.setDisciplina(disciplina);

				postagem.setTurmaDisciplina(turmaDisciplina);

				listaPostagens.add(postagem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	
		return listaPostagens;
	}

	public Object buscarPorId(long id) {
		this.conexao.abrirConexao();
		String sqlInsert = "SELECT * FROM postagem p INNER JOIN usuario u ON p.id_usuario = u.id_usuario INNER JOIN turma_disciplina td ON p.id_turma_disciplina = td.id_turma_disciplina INNER JOIN turma t ON td.id_turma = t.id_turma INNER JOIN disciplina d ON td.id_disciplina = d.id_disciplina WHERE id_postagem=?;";
		PreparedStatement statement;
		Postagem postagem = new Postagem();
		try {
			statement = this.conexao.getConexao().prepareStatement(sqlInsert);
			statement.setLong(1, id);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {

				postagem.setIdPostagem(rs.getLong("id_postagem"));
				postagem.setFavorito(rs.getBoolean("favorito"));
				postagem.setUrlImagem(rs.getString("url_imagem"));
				postagem.setTextoPostagem(rs.getString("texto_postagem"));
				postagem.setAnonimoPostagem(rs.getBoolean("anonimo_postagem"));
				postagem.setTituloPostagem(rs.getString("titulo_postagem"));

				Usuario usuario = new Usuario();
				usuario.setIdUsuario(rs.getLong("id_usuario"));
				usuario.setNomeUsuario(rs.getString("nome_usuario"));
				usuario.setCursoUsuario(rs.getString("curso_usuario"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setUrlPerfil(rs.getString("url_perfil"));
				usuario.setEmail(rs.getString("email"));
				usuario.setLogin(rs.getString("login"));
				postagem.setUsuario(usuario);

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
				disciplina.setCurso(rs.getString("curso"));
				disciplina.setCor(rs.getString("cor"));
				disciplina.setSerie(rs.getShort("serie"));
				turmaDisciplina.setDisciplina(disciplina);

				postagem.setTurmaDisciplina(turmaDisciplina);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return postagem;
	}	
	
	
	public List<Object> buscarPorDisciplina(long id) {
		this.conexao.abrirConexao();
		String sqlInsert = "SELECT * FROM postagem p INNER JOIN usuario u ON p.id_usuario = u.id_usuario INNER JOIN turma_disciplina td ON p.id_turma_disciplina = td.id_turma_disciplina INNER JOIN turma t ON td.id_turma = t.id_turma INNER JOIN disciplina d ON td.id_disciplina = d.id_disciplina WHERE d.id_disciplina=?;";
		PreparedStatement statement;
		Postagem postagem = null;
		List<Object> listaPostagensPorDisciplina = new ArrayList<Object>();
		try {
			statement = this.conexao.getConexao().prepareStatement(sqlInsert);
			statement.setLong(1, id);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				postagem = new Postagem();
				postagem.setIdPostagem(rs.getLong("id_postagem"));
				postagem.setFavorito(rs.getBoolean("favorito"));
				postagem.setUrlImagem(rs.getString("url_imagem"));
				postagem.setTextoPostagem(rs.getString("texto_postagem"));
				postagem.setAnonimoPostagem(rs.getBoolean("anonimo_postagem"));
				postagem.setTituloPostagem(rs.getString("titulo_postagem"));

				Usuario usuario = new Usuario();
				usuario.setIdUsuario(rs.getLong("id_usuario"));
				usuario.setNomeUsuario(rs.getString("nome_usuario"));
				usuario.setCursoUsuario(rs.getString("curso_usuario"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setUrlPerfil(rs.getString("url_perfil"));
				usuario.setEmail(rs.getString("email"));
				usuario.setLogin(rs.getString("login"));
				postagem.setUsuario(usuario);

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

				postagem.setTurmaDisciplina(turmaDisciplina);
				
				listaPostagensPorDisciplina.add(postagem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	
		return listaPostagensPorDisciplina;
	}	
	
	public List<Object> buscarPorUsuario(long id) {
		this.conexao.abrirConexao();
		String sqlInsert = "SELECT * FROM postagem p INNER JOIN usuario u ON p.id_usuario = u.id_usuario INNER JOIN turma_disciplina td ON p.id_turma_disciplina = td.id_turma_disciplina INNER JOIN turma t ON td.id_turma = t.id_turma INNER JOIN disciplina d ON td.id_disciplina = d.id_disciplina WHERE u.id_usuario=?;";
		PreparedStatement statement;
		Postagem postagem = null;
		List<Object> ListaPostagensPorUsuario = new ArrayList<Object>();
		try {
			statement = this.conexao.getConexao().prepareStatement(sqlInsert);
			statement.setLong(1, id);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				postagem = new Postagem();
				postagem.setIdPostagem(rs.getLong("id_postagem"));
				postagem.setFavorito(rs.getBoolean("favorito"));
				postagem.setUrlImagem(rs.getString("url_imagem"));
				postagem.setTextoPostagem(rs.getString("texto_postagem"));
				postagem.setAnonimoPostagem(rs.getBoolean("anonimo_postagem"));
				postagem.setTituloPostagem(rs.getString("titulo_postagem"));

				Usuario usuario = new Usuario();
				usuario.setIdUsuario(rs.getLong("id_usuario"));
				usuario.setNomeUsuario(rs.getString("nome_usuario"));
				usuario.setCursoUsuario(rs.getString("curso_usuario"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setUrlPerfil(rs.getString("url_perfil"));
				usuario.setEmail(rs.getString("email"));
				usuario.setLogin(rs.getString("login"));
				postagem.setUsuario(usuario);

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

				postagem.setTurmaDisciplina(turmaDisciplina);
				
				ListaPostagensPorUsuario.add(postagem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}

		
		return ListaPostagensPorUsuario;
	}	
	
	
	
}