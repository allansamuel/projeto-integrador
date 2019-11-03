package Services;

import java.util.List;

import classes.Usuario;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UsuarioService {

    @POST("usuario")
    Call<Usuario> cadastrar(@Body Usuario usuario);

    @PUT("usuario")
    Call<Usuario> editar(@Body Usuario usuario);

    @DELETE("usuario/{id}")
    Call<Usuario> deletar(@Path("id") long id);

    @GET("usuario")
    Call<List<Usuario>> buscarTodos();

    @GET("usuario/{id}")
    Call<Usuario> buscarPorId(@Path("id") long id);

    @GET("usuario/{email}/{senha}")
    Call<Usuario> buscarPorEmailESenha(@Path("email") String email ,@Path("senha") String senha);

    @GET("usuario/email/{email}/")
    Call<Usuario> buscarPorEmail(@Path("email") String email);

}
