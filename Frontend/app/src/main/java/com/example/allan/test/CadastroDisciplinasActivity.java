package com.example.allan.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Services.DisciplinaService;
import Services.TurmaDisciplinaService;
import Services.UsuarioTurmaDisciplinaService;
import classes.Disciplina;
import classes.ExpandableListAdapter;
import classes.Turma;
import classes.TurmaDisciplina;
import classes.Usuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CadastroDisciplinasActivity extends AppCompatActivity {

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    private Retrofit retrofit;
    private UsuarioTurmaDisciplinaService usuarioTurmaDisciplinaService;
    private DisciplinaService disciplinaService;
    private TurmaDisciplinaService turmaDisciplinaService;
    private Usuario usuario;
    private String serieTexto;
    private Bundle bundle;
    private String cursoUsuario;
    private List<TurmaDisciplina> listaTurmaDisciplina;
    private List<Disciplina> listaDisciplinas;
    private Disciplina disciplina;
    private TextView tvLoading;
    private TurmaDisciplina turmaDisciplina;
    private Turma turma;
    private ArrayList<Turma> listaTurmas;
    private String nomeTurma;
    private List<String> listaNomesTurmas;
    private int i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_disciplinas);
        expListView = findViewById(R.id.lvExp);


        prepareListData1();

            listAdapter = new ExpandableListAdapter(this, listDataHeader, listDataChild);

            tvLoading.setVisibility(View.INVISIBLE);
            expListView.setAdapter(listAdapter);

    }

    private void prepareListData1() {

        inicializaComponentes();
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();


        if(serieTexto.equals("1° Ano") || serieTexto.equals("2° Ano") || serieTexto.equals("3° Ano") || serieTexto.equals("4° Ano")) {
            disciplinaService.buscarPorCursoESerie(cursoUsuario, 1).enqueue(new Callback<ArrayList<Disciplina>>() {
                @Override
                public void onResponse(Call<ArrayList<Disciplina>> call, Response<ArrayList<Disciplina>> response) {


                    if (response.isSuccessful()) {

                        tvLoading.setText(R.string.carregando);
                        tvLoading.setVisibility(View.VISIBLE);

                        listaDisciplinas = response.body();
                        for (i = 0; i < listaDisciplinas.size(); i++) {

                            disciplina = response.body().get(i);

                            listDataHeader.add(response.body().get(i).getNomeDisciplina());

                            turmaDisciplinaService.buscarPorDisciplina(disciplina.getIdDisciplina()).enqueue(new Callback<List<TurmaDisciplina>>() {
                                @Override
                                public void onResponse(Call<List<TurmaDisciplina>> call, Response<List<TurmaDisciplina>> response) {
                                    if(response.isSuccessful()){
                                        listaTurmaDisciplina = response.body();

                                        for(int y=0;y<listaTurmaDisciplina.size();y++){
                                            turmaDisciplina = response.body().get(y);
                                            turma = turmaDisciplina.getTurma();
                                            listaTurmas = new ArrayList<Turma>();
                                            listaTurmas.add(turma);
                                            Toast.makeText(getApplicationContext(), turma.getNomeTurma(), Toast.LENGTH_SHORT).show();
                                        }

                                        for(int x=0;x<listaTurmaDisciplina.size();x++){
                                            turma = listaTurmas.get(x);
                                            nomeTurma = turma.getNomeTurma();
                                            listaNomesTurmas = new ArrayList<>();
                                            listaNomesTurmas.add(nomeTurma);
                                        }

                                    }
                                }

                                @Override
                                public void onFailure(Call<List<TurmaDisciplina>> call, Throwable t) {

                                }
                            });
                            listDataChild.put(listDataHeader.get(i),listaNomesTurmas);//


                        }



                    }
                }
                    @Override
                    public void onFailure (Call < ArrayList < Disciplina >> call, Throwable t){


                }
            });

        }
    }

    private void inicializaComponentes() {
        this.bundle = getIntent().getExtras();
        this.serieTexto = (String) bundle.getSerializable("serie");
        this.usuario = (Usuario) bundle.getSerializable("usuario");
        this.cursoUsuario = usuario.getCursoUsuario();

        this.retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.240.59:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.usuarioTurmaDisciplinaService = retrofit.create(UsuarioTurmaDisciplinaService.class);
        this.disciplinaService = retrofit.create(DisciplinaService.class);
        this.turmaDisciplinaService = retrofit.create(TurmaDisciplinaService.class);
        this.listaDisciplinas = new ArrayList<>();
        this.listaTurmaDisciplina = new ArrayList<>();
        this.tvLoading = findViewById(R.id.tv_loading);

    }
}