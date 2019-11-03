package exemplo.teamup.model;

import java.util.Date;
import java.util.List;

public class Equipe {
	private long id;
	private String nome;
	private Esporte esporte;
	private String urlImagem;
	private String descricao;
	private double pontuacaoTotal;
	private Date dataCriacao;
	private boolean ativo;
	private List<Usuario> jogadores;
	
	

}
