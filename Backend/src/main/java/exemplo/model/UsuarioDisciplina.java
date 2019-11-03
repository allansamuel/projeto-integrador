package exemplo.model;

public class UsuarioDisciplina {

	private long idUsuarioDisciplina;
	private Usuario usuario;
	private Disciplina disciplina;
	
	public UsuarioDisciplina() {
		super();
	}

	public UsuarioDisciplina(long idUsuarioDisciplina, Usuario usuario, Disciplina disciplina) {
		super();
		this.idUsuarioDisciplina = idUsuarioDisciplina;
		this.usuario = usuario;
		this.disciplina = disciplina;
	}

	public long getIdUsuarioDisciplina() {
		return idUsuarioDisciplina;
	}

	public void setIdUsuarioDisciplina(long idUsuarioDisciplina) {
		this.idUsuarioDisciplina = idUsuarioDisciplina;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}
	
	
	
}
