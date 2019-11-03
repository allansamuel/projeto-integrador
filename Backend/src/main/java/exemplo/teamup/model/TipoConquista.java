package exemplo.teamup.model;

public enum TipoConquista {

    USUARIO_CONQUISTA(1), EQUIPE_CONQUISTA(2);
    private int tipoConquista;

    TipoConquista(int tipoConquista) {
        this.tipoConquista = tipoConquista;
    }

    public int getTipoConquista() {
        return tipoConquista;
    }
}
