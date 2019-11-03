package exemplo.model;

import java.util.ArrayList;

public class Postagem {

	private long idPostagem;
	private Boolean favorito;
	private String urlImagem;
	private String textoPostagem;
	private String tituloPostagem;
	private Boolean anonimoPostagem;
	private Usuario usuario;
	private TurmaDisciplina turmaDisciplina;
	private ArrayList<Comentario> listaComentario;

	
	public Postagem() {
		super();
	}

	public Postagem(long idPostagem, Boolean favorito, String urlImagem,
			String textoPostagem, String tituloPostagem,
			Boolean anonimoPostagem, Usuario usuario,
			TurmaDisciplina turmaDisciplina,
			ArrayList<Comentario> listaComentario) {
		super();
		this.idPostagem = idPostagem;
		this.favorito = favorito;
		this.urlImagem = urlImagem;
		this.textoPostagem = textoPostagem;
		this.tituloPostagem = tituloPostagem;
		this.anonimoPostagem = anonimoPostagem;
		this.usuario = usuario;
		this.turmaDisciplina = turmaDisciplina;
		this.listaComentario = listaComentario;
	}


	public long getIdPostagem() {
		return idPostagem;
	}


	public void setIdPostagem(long idPostagem) {
		this.idPostagem = idPostagem;
	}


	public Boolean getFavorito() {
		return favorito;
	}


	public void setFavorito(Boolean favorito) {
		this.favorito = favorito;
	}


	public String getUrlImagem() {
		return urlImagem;
	}


	public void setUrlImagem(String urlImagem) {
		this.urlImagem = urlImagem;
	}


	public String getTextoPostagem() {
		return textoPostagem;
	}


	public void setTextoPostagem(String textoPostagem) {
		this.textoPostagem = textoPostagem;
	}


	public String getTituloPostagem() {
		return tituloPostagem;
	}


	public void setTituloPostagem(String tituloPostagem) {
		this.tituloPostagem = tituloPostagem;
	}


	public Boolean getAnonimoPostagem() {
		return anonimoPostagem;
	}


	public void setAnonimoPostagem(Boolean anonimoPostagem) {
		this.anonimoPostagem = anonimoPostagem;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public TurmaDisciplina getTurmaDisciplina() {
		return turmaDisciplina;
	}


	public void setTurmaDisciplina(TurmaDisciplina turmaDisciplina) {
		this.turmaDisciplina = turmaDisciplina;
	}


	public ArrayList<Comentario> getListaComentario() {
		return listaComentario;
	}


	public void setListaComentario(ArrayList<Comentario> listaComentario) {
		this.listaComentario = listaComentario;
	}


	
	
}
