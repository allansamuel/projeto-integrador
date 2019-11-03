package exemplo.model;

public class Comentario {

	private long idComentario;
	private String textoComentario;
	private int avaliacao;
	private String urlImagem;
	private Boolean anonimoComentario;
	private Usuario usuario;
	private Postagem postagem;
	
	public Comentario() {
		super();
	}

	public Comentario(long idComentario, String textoComentario, int avaliacao,
			String urlImagem, Boolean anonimoComentario, Usuario usuario,
			Postagem postagem) {
		super();
		this.idComentario = idComentario;
		this.textoComentario = textoComentario;
		this.avaliacao = avaliacao;
		this.urlImagem = urlImagem;
		this.anonimoComentario = anonimoComentario;
		this.usuario = usuario;
		this.postagem = postagem;
	}

	public long getIdComentario() {
		return idComentario;
	}

	public void setIdComentario(long idComentario) {
		this.idComentario = idComentario;
	}

	public String getTextoComentario() {
		return textoComentario;
	}

	public void setTextoComentario(String textoComentario) {
		this.textoComentario = textoComentario;
	}

	public int getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(int avaliacao) {
		this.avaliacao = avaliacao;
	}

	public String getUrlImagem() {
		return urlImagem;
	}

	public void setUrlImagem(String urlImagem) {
		this.urlImagem = urlImagem;
	}

	public Boolean getAnonimoComentario() {
		return anonimoComentario;
	}

	public void setAnonimoComentario(Boolean anonimoComentario) {
		this.anonimoComentario = anonimoComentario;
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