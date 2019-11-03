package classes;

import java.io.Serializable;
import java.util.List;

public class TurmaDisciplina implements Serializable {

	private long idTurmaDisciplina;
	private Disciplina disciplina;
	private Turma turma;
	private List<Postagem> listaPostagens;
	private List<Prova> listaProvas;
	private List<UsuarioTurmaDisciplina> listaUsuarioTurmaDisciplinas;
	
	public TurmaDisciplina() {
		super();
	}

	public TurmaDisciplina(long idTurmaDisciplina, Disciplina disciplina, Turma turma, List<Postagem> listaPostagens,
			List<Prova> listaProvas, List<UsuarioTurmaDisciplina> listaUsuarioTurmaDisciplinas) {
		super();
		this.idTurmaDisciplina = idTurmaDisciplina;
		this.disciplina = disciplina;
		this.turma = turma;
		this.listaPostagens = listaPostagens;
		this.listaProvas = listaProvas;
		this.listaUsuarioTurmaDisciplinas = listaUsuarioTurmaDisciplinas;
	}

	public long getIdTurmaDisciplina() {
		return idTurmaDisciplina;
	}

	public void setIdTurmaDisciplina(long idTurmaDisciplina) {
		this.idTurmaDisciplina = idTurmaDisciplina;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public List<Postagem> getListaPostagens() {
		return listaPostagens;
	}

	public void setListaPostagens(List<Postagem> listaPostagens) {
		this.listaPostagens = listaPostagens;
	}

	public List<Prova> getListaProvas() {
		return listaProvas;
	}

	public void setListaProvas(List<Prova> listaProvas) {
		this.listaProvas = listaProvas;
	}

	public List<UsuarioTurmaDisciplina> getListaUsuarioTurmaDisciplinas() {
		return listaUsuarioTurmaDisciplinas;
	}

	public void setListaUsuarioTurmaDisciplinas(List<UsuarioTurmaDisciplina> listaUsuarioTurmaDisciplinas) {
		this.listaUsuarioTurmaDisciplinas = listaUsuarioTurmaDisciplinas;
	}
	
	
}
