package exemplo.teamup.model;

import java.util.Date;
import java.util.List;

public class Usuario {
	private long id;
	private String nome;
	private String email;
	private String senha;
	private Genero genero;
	private String urlImagem;
	private double pontuacaoTotal;
	private long socialId;
	private Date dataCadastro;
	private Date ultimoAcesso;
	private List<Usuario> usuariosFavoritos;
	private List<Arena> arenasFavoritas;
	private List<Equipe> equipesFavoritas;
	

}
