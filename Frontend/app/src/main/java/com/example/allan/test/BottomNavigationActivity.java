package com.example.allan.test;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Services.PostagemService;
import Services.UsuarioService;
import classes.Postagem;
import classes.Usuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BottomNavigationActivity extends AppCompatActivity implements Serializable {

    FragmentManager fragmentManager;
    private BottomNavigationView bottomNavigationView;
    private Bundle bundle;
    private Usuario usuario;
    private Retrofit retrofit;
    private PostagemService postagemService;
    private ArrayList<Postagem> listaPostagensPorUsuario;
    private int listaTamanho;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_navigation);




        inicializaComponentes();

        postagemService.buscarPorUsuario(usuario.getIdUsuario()).enqueue(new Callback<ArrayList<Postagem>>() {
            @Override
            public void onResponse(Call<ArrayList<Postagem>> call, Response<ArrayList<Postagem>> response) {
                ArrayList<Postagem> list = (ArrayList<Postagem>) call;
                if(response.isSuccessful()){

                }else{
                    bundle.putInt("num_postagens", 0);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Postagem>> call, Throwable t) {

            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        PerfilFragment perfilFragment = new PerfilFragment();
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_layout, perfilFragment, perfilFragment.getTag()).commit();
        bundle.putSerializable("usuario", usuario);
        perfilFragment.setArguments(bundle);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    HomeFragment homeFragment = new HomeFragment();
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.content_layout, homeFragment, homeFragment.getTag()).commit();

                    return true;
                case R.id.navigation_calendario:
                    CalendarioFragment calendarioFragment = new CalendarioFragment();
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.content_layout, calendarioFragment, calendarioFragment.getTag()).commit();

                    return true;
                case R.id.navigation_perfil:
                    PerfilFragment perfilFragment = new PerfilFragment();
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.content_layout, perfilFragment, perfilFragment.getTag()).commit();
                    bundle.putSerializable("usuario", usuario);
                    perfilFragment.setArguments(bundle);
                     return true;
                case R.id.navigation_pesquisar:
                    PesquisarFragment pesquisarFragment = new PesquisarFragment();
                    fragmentManager = getSupportFragmentManager();
                    fragmentManager.beginTransaction().replace(R.id.content_layout, pesquisarFragment, pesquisarFragment.getTag()).commit();

                    return true;
            }
            return false;
        }
    };
    private void inicializaComponentes(){
        this.bottomNavigationView = findViewById(R.id.navigationView);
        this.bundle= getIntent().getExtras();
        this.usuario = (Usuario) bundle.getSerializable("usuario");
        this.retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.240.59:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.postagemService = retrofit.create(PostagemService.class);
    }

}
