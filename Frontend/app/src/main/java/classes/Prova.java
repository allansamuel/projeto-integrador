package classes;


import java.io.Serializable;

public class Prova implements Serializable {

	private long idProva;
	private String tituloProva;
	private String dataProva;
	private String resumo;
	private TurmaDisciplina turmaDisciplina;
	private Usuario usuario;
	
	public Prova() {
		super();
	}

	public Prova(long idProva, String tituloProva, String dataProva,
			String resumo, TurmaDisciplina turmaDisciplina, Usuario usuario) {
		super();
		this.idProva = idProva;
		this.tituloProva = tituloProva;
		this.dataProva = dataProva;
		this.resumo = resumo;
		this.turmaDisciplina = turmaDisciplina;
		this.usuario = usuario;
	}

	public long getIdProva() {
		return idProva;
	}

	public void setIdProva(long idProva) {
		this.idProva = idProva;
	}

	public String getTituloProva() {
		return tituloProva;
	}

	public void setTituloProva(String tituloProva) {
		this.tituloProva = tituloProva;
	}

	public String getDataProva() {
		return dataProva;
	}

	public void setDataProva(String dataProva) {
		this.dataProva = dataProva;
	}

	public String getResumo() {
		return resumo;
	}

	public void setResumo(String resumo) {
		this.resumo = resumo;
	}

	public TurmaDisciplina getTurmaDisciplina() {
		return turmaDisciplina;
	}

	public void setTurmaDisciplina(TurmaDisciplina turmaDisciplina) {
		this.turmaDisciplina = turmaDisciplina;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	
	
}