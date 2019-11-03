package exemplo.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exemplo.model.Disciplina;
import exemplo.model.FavoritoPostagem;
import exemplo.model.Postagem;
import exemplo.model.Turma;
import exemplo.model.TurmaDisciplina;
import exemplo.model.Usuario;

public class FavoritoPostagemDAO {

	private ConexaoMysql conexao;

	public FavoritoPostagemDAO() {
		super();
		this.conexao = new ConexaoMysql("localhost", "root", "", "barack_pergunta");
	}

	public Object favoritar(Object objeto) {

		FavoritoPostagem favoritoPostagem = (FavoritoPostagem) objeto;
		this.conexao.abrirConexao();
		String sqlInsert = "INSERT INTO favorito_postagem VALUES(null, ?, ?);";
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);

			statement.setLong(1, favoritoPostagem.getUsuario().getIdUsuario());
			statement.setLong(2, favoritoPostagem.getPostagem().getIdPostagem());

			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if(rs.next()){
				favoritoPostagem.setIdFavoritoPostagem(rs.getLong(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			this.conexao.fecharConexao();
		}
		return favoritoPostagem;
	}



	public void excluir(long id) {
		String sqlExcluir = "DELETE FROM favorito_postagem WHERE id_favorito_postagem=?";
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
		String sqlSelect = "SELECT * FROM favorito_postagem fp INNER JOIN usuario u ON fp.id_usuario = u.id_usuario INNER JOIN postagem p ON fp.id_postagem = p.id_postagem INNER JOIN turma_disciplina tm ON p.id_turma_disciplina = tm.id_turma_disciplina INNER JOIN turma t ON tm.id_turma = t.id_turma INNER JOIN  disciplina d ON tm.id_disciplina = d.id_disciplina;";
		PreparedStatement statement;
		FavoritoPostagem favoritoPostagem = null;
		List<Object> listaFavoritos = new ArrayList<Object>();
		try {
			statement = this.conexao.getConexao().prepareStatement(sqlSelect);
			ResultSet rs = statement.executeQuery();

			while(rs.next()) {

				favoritoPostagem = new FavoritoPostagem();

				favoritoPostagem.setIdFavoritoPostagem(rs.getLong("id_favorito_postagem"));

				Usuario usuario = new Usuario();

				usuario.setIdUsuario(rs.getLong("id_usuario"));
				usuario.setNomeUsuario(rs.getString("nome_usuario"));
				usuario.setLogin(rs.getString("login"));
				usuario.setEmail(rs.getString("email"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setCursoUsuario(rs.getString("curso_usuario"));
				usuario.setUrlPerfil(rs.getString("url_perfil"));
				favoritoPostagem.setUsuario(usuario);

				Postagem postagem = new Postagem();						
				postagem.setIdPostagem(rs.getLong("id_postagem"));
				postagem.setAnonimoPostagem(rs.getBoolean("anonimo_postagem"));
				postagem.setFavorito(rs.getBoolean("favorito"));
				postagem.setUrlImagem(rs.getString("url_imagem"));
				postagem.setTextoPostagem(rs.getString("texto_postagem"));
				postagem.setTituloPostagem(rs.getString("titulo_postagem"));

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
				disciplina.setEmenta(rs.getString("ementa"));
				disciplina.setNomeDisciplina(rs.getString("nome_disciplina"));

				turmaDisciplina.setTurma(turma);
				turmaDisciplina.setDisciplina(disciplina);
				postagem.setTurmaDisciplina(turmaDisciplina);
				postagem.setUsuario(usuario);

				favoritoPostagem.setPostagem(postagem);

				listaFavoritos.add(favoritoPostagem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		
		return listaFavoritos;
	}

	public List<Object> buscarFavoritosDeUsuario(long id) {
		this.conexao.abrirConexao();
		String sqlInsert = "SELECT * FROM favorito_postagem fp INNER JOIN usuario u ON fp.id_usuario = u.id_usuario INNER JOIN postagem p ON fp.id_postagem = p.id_postagem INNER JOIN turma_disciplina tm ON p.id_turma_disciplina = tm.id_turma_disciplina INNER JOIN turma t ON tm.id_turma = t.id_turma INNER JOIN  disciplina d ON tm.id_disciplina = d.id_disciplina WHERE fp.id_usuario=?";
		PreparedStatement statement;
		List<Object> listaPostagensFavoritas = new ArrayList<Object>();
		try {
			statement = this.conexao.getConexao().prepareStatement(sqlInsert);
			statement.setLong(1, id);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				
				
				Usuario usuario = new Usuario();

				usuario.setIdUsuario(rs.getLong("id_usuario"));
				usuario.setNomeUsuario(rs.getString("nome_usuario"));
				usuario.setLogin(rs.getString("login"));
				usuario.setEmail(rs.getString("email"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setCursoUsuario(rs.getString("curso_usuario"));
				usuario.setUrlPerfil(rs.getString("url_perfil"));
			

				Postagem postagem = new Postagem();						
				postagem.setIdPostagem(rs.getLong("id_postagem"));
				postagem.setAnonimoPostagem(rs.getBoolean("anonimo_postagem"));
				postagem.setFavorito(rs.getBoolean("favorito"));
				postagem.setUrlImagem(rs.getString("url_imagem"));
				postagem.setTextoPostagem(rs.getString("texto_postagem"));
				postagem.setTituloPostagem(rs.getString("titulo_postagem"));

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
				disciplina.setEmenta(rs.getString("ementa"));
				disciplina.setNomeDisciplina(rs.getString("nome_disciplina"));

				turmaDisciplina.setTurma(turma);
				turmaDisciplina.setDisciplina(disciplina);
				postagem.setTurmaDisciplina(turmaDisciplina);
				postagem.setUsuario(usuario);

				
				listaPostagensFavoritas.add(postagem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		for(int i=0;i<listaPostagensFavoritas.size();i++){
			System.out.println(listaPostagensFavoritas.get(i).toString());
		}
		return listaPostagensFavoritas;
	}	
	
	
	public List<Object> buscarFavoritosDePostagem(long id) {
		this.conexao.abrirConexao();
		String sqlInsert = "SELECT * FROM favorito_postagem fp INNER JOIN usuario u ON fp.id_usuario = u.id_usuario INNER JOIN postagem p ON fp.id_postagem = p.id_postagem INNER JOIN turma_disciplina tm ON p.id_turma_disciplina = tm.id_turma_disciplina INNER JOIN turma t ON tm.id_turma = t.id_turma INNER JOIN  disciplina d ON tm.id_disciplina = d.id_disciplina WHERE fp.id_postagem=?";
		PreparedStatement statement;
		List<Object> listaFavoritosDePostagem = new ArrayList<Object>();
		try {
			statement = this.conexao.getConexao().prepareStatement(sqlInsert);
			statement.setLong(1, id);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				
				
				Usuario usuario = new Usuario();

				usuario.setIdUsuario(rs.getLong("id_usuario"));
				usuario.setNomeUsuario(rs.getString("nome_usuario"));
				usuario.setLogin(rs.getString("login"));
				usuario.setEmail(rs.getString("email"));
				usuario.setSenha(rs.getString("senha"));
				usuario.setCursoUsuario(rs.getString("curso_usuario"));
				usuario.setUrlPerfil(rs.getString("url_perfil"));

				
				listaFavoritosDePostagem.add(usuario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	
		return listaFavoritosDePostagem;
	}	
	
	
}