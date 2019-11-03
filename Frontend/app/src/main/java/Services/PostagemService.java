package Services;

import java.util.ArrayList;
import java.util.List;

import classes.Postagem;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PostagemService {

    @POST("postagem")
    Call<Postagem> cadastrar(@Body Postagem postagem);

    @DELETE("postagem/{id}")
    Call<Postagem> deletar(@Path("id") long id);

    @PUT("postagem")
    Call<Postagem> editar(@Body Postagem postagem);

    @GET("postagem/{id}")
    Call<Postagem> buscarPorId(@Path("id") long id);

    @GET("postagem/buscar_por_usuario/{idUsuario}")
    Call<ArrayList<Postagem>> buscarPorUsuario(@Path("idUsuario") long id);


}
