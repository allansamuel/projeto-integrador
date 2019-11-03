package exemplo.teamup.model;

public enum Genero {

	MASCULINO(1), FEMININO(2);
	private int genero;
	
	Genero(int genero) {
		this.genero = genero;
	}
	
	public int getGenero() {
		return this.genero;
	}	
}
