package classes;

import java.io.Serializable;

public class FavoritoPostagem implements Serializable {

	private long idFavoritoPostagem;
	private Usuario usuario;
	private Postagem postagem;
	
	public FavoritoPostagem() {
		super();
	}

	public FavoritoPostagem(long idFavoritoPostagem,
			Usuario usuario, Postagem postagem) {
		super();
		this.idFavoritoPostagem = idFavoritoPostagem;
		this.usuario = usuario;
		this.postagem = postagem;
	}

	public long getIdFavoritoPostagem() {
		return idFavoritoPostagem;
	}

	public void setIdFavoritoPostagem(long idFavoritoPostagem) {
		this.idFavoritoPostagem = idFavoritoPostagem;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Postagem getPostagem() {
		return postagem;
	}

	public void setPostagem(Postagem postagem) {
		this.postagem = postagem;
	}
	
	
}
