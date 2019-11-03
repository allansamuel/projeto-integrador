package Services;

import classes.UsuarioTurmaDisciplina;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UsuarioTurmaDisciplinaService {
    @POST("usuario_turma_disciplina")
    Call<UsuarioTurmaDisciplina> cadastrar(@Body UsuarioTurmaDisciplina usuarioTurmaDisciplina);

    @PUT("usuario_turma_disciplina")
    Call<UsuarioTurmaDisciplina> editar(@Body UsuarioTurmaDisciplina usuarioTurmaDisciplina);

    @DELETE("usuario_turma_disciplina/{id}")
    Call<UsuarioTurmaDisciplina> deletar(@Path("id") long id);

    @GET("usuario_turma_disciplina/{id}")
    Call<UsuarioTurmaDisciplina> buscarPorId(@Path("id") long id);
}
