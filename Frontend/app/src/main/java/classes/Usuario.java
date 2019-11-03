package classes;

import java.io.Serializable;
import java.util.ArrayList;

public class Usuario implements Serializable {
	private long idUsuario;
	private String nomeUsuario;
	private String login;
	private String email;
	private String senha;
	private String cursoUsuario;
	private String urlPerfil;
	private ArrayList<Postagem> listaPostagem;
	private ArrayList<Prova> listaProva;
	private ArrayList<Comentario> listaComentario;
	private ArrayList<UsuarioDisciplina> listaUsuarioDisciplina;
	private ArrayList<UsuarioTurmaDisciplina> listaUsuarioturmaDisciplinas;
	
	public Usuario() {
		super();
	}

	public Usuario(long idUsuario, String nomeUsuario, String login,
			String email, String senha, String cursoUsuario, String urlPerfil,
			ArrayList<Postagem> listaPostagem, ArrayList<Prova> listaProva,
			ArrayList<Comentario> listaComentario,
			ArrayList<UsuarioDisciplina> listaUsuarioDisciplina,
			ArrayList<UsuarioTurmaDisciplina> listaUsuarioturmaDisciplinas) {
		super();
		this.idUsuario = idUsuario;
		this.nomeUsuario = nomeUsuario;
		this.login = login;
		this.email = email;
		this.senha = senha;
		this.cursoUsuario = cursoUsuario;
		this.urlPerfil = urlPerfil;
		this.listaPostagem = listaPostagem;
		this.listaProva = listaProva;
		this.listaComentario = listaComentario;
		this.listaUsuarioDisciplina = listaUsuarioDisciplina;
		this.listaUsuarioturmaDisciplinas = listaUsuarioturmaDisciplinas;
	}

	public long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCursoUsuario() {
		return cursoUsuario;
	}

	public void setCursoUsuario(String cursoUsuario) {
		this.cursoUsuario = cursoUsuario;
	}

	public String getUrlPerfil() {
		return urlPerfil;
	}

	public void setUrlPerfil(String urlPerfil) {
		this.urlPerfil = urlPerfil;
	}

	public ArrayList<Postagem> getListaPostagem() {
		return listaPostagem;
	}

	public void setListaPostagem(ArrayList<Postagem> listaPostagem) {
		this.listaPostagem = listaPostagem;
	}

	public ArrayList<Prova> getListaProva() {
		return listaProva;
	}

	public void setListaProva(ArrayList<Prova> listaProva) {
		this.listaProva = listaProva;
	}

	public ArrayList<Comentario> getListaComentario() {
		return listaComentario;
	}

	public void setListaComentario(ArrayList<Comentario> listaComentario) {
		this.listaComentario = listaComentario;
	}

	public ArrayList<UsuarioDisciplina> getListaUsuarioDisciplina() {
		return listaUsuarioDisciplina;
	}

	public void setListaUsuarioDisciplina(
			ArrayList<UsuarioDisciplina> listaUsuarioDisciplina) {
		this.listaUsuarioDisciplina = listaUsuarioDisciplina;
	}

	public ArrayList<UsuarioTurmaDisciplina> getListaUsuarioturmaDisciplinas() {
		return listaUsuarioturmaDisciplinas;
	}

	public void setListaUsuarioturmaDisciplinas(
			ArrayList<UsuarioTurmaDisciplina> listaUsuarioturmaDisciplinas) {
		this.listaUsuarioturmaDisciplinas = listaUsuarioturmaDisciplinas;
	}

	@Override
	public String toString() {
		return nomeUsuario.toString() + " " + email.toString() + " " + senha.toString() + " " + urlPerfil.toString() + " " + cursoUsuario.toString() + " " + idUsuario + " " +  login.toString();
	}
}