package exemplo.teamup.model;

public enum TipoPartida {
	FUTSAL(1), CAMPO(2), FUT7(3), AREIA(4);
	private int tipoPartida;
	
	private TipoPartida(int tipoPartida) {
		this.tipoPartida = tipoPartida;
	}
}
