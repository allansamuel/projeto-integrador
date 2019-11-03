package exemplo.model;

import java.util.List;

public class Turma {

	private long idTurma;
	private String nomeTurma;
	private String cursoTurma;
	private List<TurmaDisciplina> listaTurmaDisciplina;
	
	public Turma() {
		super();
	}

	public Turma(long idTurma, String nomeTurma, String cursoTurma, List<TurmaDisciplina> listaTurmaDisciplina) {
		super();
		this.idTurma = idTurma;
		this.nomeTurma = nomeTurma;
		this.cursoTurma = cursoTurma;
		this.listaTurmaDisciplina = listaTurmaDisciplina;
	}

	public long getIdTurma() {
		return idTurma;
	}

	public void setIdTurma(long idTurma) {
		this.idTurma = idTurma;
	}

	public String getNomeTurma() {
		return nomeTurma;
	}

	public void setNomeTurma(String nomeTurma) {
		this.nomeTurma = nomeTurma;
	}

	public String getCursoTurma() {
		return cursoTurma;
	}

	public void setCursoTurma(String cursoTurma) {
		this.cursoTurma = cursoTurma;
	}

	public List<TurmaDisciplina> getListaTurmaDisciplina() {
		return listaTurmaDisciplina;
	}

	public void setListaTurmaDisciplina(List<TurmaDisciplina> listaTurmaDisciplina) {
		this.listaTurmaDisciplina = listaTurmaDisciplina;
	}

	
}

