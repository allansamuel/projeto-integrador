package exemplo.teamup.model;

import java.util.Date;

public class Partida {

	private long id;
	private TipoPartida tipoPartida;
	private Agendamento agendamento;
	private Date dataIni;
	private Date dataFim;
	private String gritoVisitante;
	private String gritoMandante;
	private Equipe mandante;
	private Equipe visitante;
	private StatusPartida status;
	private int placarVisitante;
	private int placarMandante;
}
