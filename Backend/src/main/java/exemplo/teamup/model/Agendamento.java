package exemplo.teamup.model;

import java.util.Date;
import java.util.List;

public class Agendamento {

	private long id;
    private Date dataIni;
    private Date dataFim;
    private Arena arena;
	private Partida partida;
    private String nomeReservante;
    private String telefoneReservante;
    private boolean pago;
    private double preco;
	
}
