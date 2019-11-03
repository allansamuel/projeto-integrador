package Services;

import java.util.List;

import classes.Postagem;
import classes.TurmaDisciplina;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TurmaDisciplinaService {


    @GET("turma_disciplina/{id}")
    Call<TurmaDisciplina> buscarPorId(@Path("id") long id);


    @GET("turma_disciplina/disciplina/{id}")
    Call<List<TurmaDisciplina>> buscarPorDisciplina(@Path("id") long id);
}
