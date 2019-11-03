package classes;

import java.io.Serializable;

public class UsuarioTurmaDisciplina implements Serializable {

	private long idUsuarioTurmaDisciplina;
	private Usuario usuario;
	private TurmaDisciplina turmaDisciplina;
	
	public UsuarioTurmaDisciplina() {
		super();
	}

	public UsuarioTurmaDisciplina(long idUsuarioTurmaDisciplina, Usuario usuario, TurmaDisciplina turmaDisciplina) {
		super();
		this.idUsuarioTurmaDisciplina = idUsuarioTurmaDisciplina;
		this.usuario = usuario;
		this.turmaDisciplina = turmaDisciplina;
	}

	public long getIdUsuarioTurmaDisciplina() {
		return idUsuarioTurmaDisciplina;
	}

	public void setIdUsuarioTurmaDisciplina(long idUsuarioTurmaDisciplina) {
		this.idUsuarioTurmaDisciplina = idUsuarioTurmaDisciplina;
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
	
	
}
