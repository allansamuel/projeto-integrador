package classes;

import java.io.Serializable;
import java.util.ArrayList;

public class Disciplina implements Serializable {

	private long idDisciplina;
	private String bibliografiaComplementar;
	private String bibliografiaBasica;
	private String nomeDisciplina;
	private String cargaHoraria;
	private String conteudo;
	private String ementa;
	private String cor;
	private int serie;
	private String curso;
	private ArrayList<TurmaDisciplina> listaTurmaDisciplinas;
	private ArrayList<UsuarioDisciplina> listaUsuarioDisciplina;
	
	public Disciplina() {
		super();
	}

	public Disciplina(long idDisciplina, String bibliografiaComplementar, String bibliografiaBasica, String nomeDisciplina, String cargaHoraria, String conteudo, String ementa, String cor, int serie, String curso, ArrayList<TurmaDisciplina> listaTurmaDisciplinas, ArrayList<UsuarioDisciplina> listaUsuarioDisciplina) {
		this.idDisciplina = idDisciplina;
		this.bibliografiaComplementar = bibliografiaComplementar;
		this.bibliografiaBasica = bibliografiaBasica;
		this.nomeDisciplina = nomeDisciplina;
		this.cargaHoraria = cargaHoraria;
		this.conteudo = conteudo;
		this.ementa = ementa;
		this.cor = cor;
		this.serie = serie;
		this.curso = curso;
		this.listaTurmaDisciplinas = listaTurmaDisciplinas;
		this.listaUsuarioDisciplina = listaUsuarioDisciplina;
	}

	public long getIdDisciplina() {
		return idDisciplina;
	}

	public void setIdDisciplina(long idDisciplina) {
		this.idDisciplina = idDisciplina;
	}

	public String getBibliografiaComplementar() {
		return bibliografiaComplementar;
	}

	public void setBibliografiaComplementar(String bibliografiaComplementar) {
		this.bibliografiaComplementar = bibliografiaComplementar;
	}

	public String getBibliografiaBasica() {
		return bibliografiaBasica;
	}

	public void setBibliografiaBasica(String bibliografiaBasica) {
		this.bibliografiaBasica = bibliografiaBasica;
	}

	public String getNomeDisciplina() {
		return nomeDisciplina;
	}

	public void setNomeDisciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
	}

	public String getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(String cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	public String getEmenta() {
		return ementa;
	}

	public void setEmenta(String ementa) {
		this.ementa = ementa;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public int getSerie() {
		return serie;
	}

	public void setSerie(int serie) {
		this.serie = serie;
	}

	public ArrayList<TurmaDisciplina> getListaTurmaDisciplinas() {
		return listaTurmaDisciplinas;
	}

	public void setListaTurmaDisciplinas(ArrayList<TurmaDisciplina> listaTurmaDisciplinas) {
		this.listaTurmaDisciplinas = listaTurmaDisciplinas;
	}

	public ArrayList<UsuarioDisciplina> getListaUsuarioDisciplina() {
		return listaUsuarioDisciplina;
	}

	public void setListaUsuarioDisciplina(ArrayList<UsuarioDisciplina> listaUsuarioDisciplina) {
		this.listaUsuarioDisciplina = listaUsuarioDisciplina;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	@Override
	public String toString() {
		return getNomeDisciplina();
	}
}
