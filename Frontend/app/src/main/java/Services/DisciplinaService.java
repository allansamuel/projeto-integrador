package Services;

import java.util.ArrayList;
import java.util.List;

import classes.Disciplina;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface DisciplinaService {

    @GET("disciplina/{id}")
    Call<Disciplina> buscarPorId(@Path("id") long id);

    @GET("disciplina/{curso}/{serie}")
    Call<ArrayList<Disciplina>> buscarPorCursoESerie(@Path("curso") String curso, @Path("serie") int serie);


}
