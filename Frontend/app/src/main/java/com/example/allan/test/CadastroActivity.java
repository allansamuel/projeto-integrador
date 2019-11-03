package com.example.allan.test;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

import Services.UsuarioService;
import classes.Usuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CadastroActivity extends AppCompatActivity implements Serializable {

    private EditText etNome;
    private EditText etEmail;
    private EditText etSenha;
    private EditText etConfirmaSenha;
    private Spinner spCurso;
    private Spinner spSerie;
    private FloatingActionButton btProximo;
    private FloatingActionButton btPerfil;
    private String[] listaCursos = {"Selecione seu Curso", "Informática", "Eventos", "Plásticos"};
    private String[] listaSeries = {"Selecione seu ano", "1° Ano", "2° Ano", "3° Ano", "4° Ano"};
    private ArrayAdapter<String> adapterSeries;
    private ArrayAdapter<String> adapterCursos;
    private ImageView ivDogPerfil;
    private Usuario usuario;
    private TextView tvAviso;
    private Retrofit retrofit;
    private UsuarioService usuarioService;
    private Boolean bool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        inicializaComponentes();

        spSerie.setAdapter(adapterSeries);
        spCurso.setAdapter(adapterCursos);


        btProximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                usuario = new Usuario();

                usuario.setNomeUsuario(etNome.getText().toString());
                usuario.setEmail(etEmail.getText().toString());
                usuario.setSenha(etSenha.getText().toString());
                usuario.setLogin("");
                usuario.setUrlPerfil("picture");
                usuario.setCursoUsuario(spCurso.getSelectedItem().toString());



                if(!(etNome.getText().toString().isEmpty()) && !(etEmail.getText().toString().isEmpty()) && !(spCurso.getSelectedItem().toString().equals("Selecione seu Curso")) && !(spSerie.getSelectedItem().toString().equals("Selecione sua série"))) {
                    usuarioService.buscarPorEmail(etEmail.getText().toString()).enqueue(new Callback<Usuario>() {
                        @Override
                        public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                           for (int i = 0; i < etNome.getText().toString().length(); i++) {

                                if ((etNome.getText().toString().charAt(i) == ' ')  && (((etNome.getText().toString().charAt(i) >= 'A') && (etNome.getText().toString().charAt(i) <= 'Z')) || ((etNome.getText().toString().charAt(i) >= 'a') && (etNome.getText().toString().charAt(i) <= 'z')))) {
                                    bool = false;

                                } else if((((etNome.getText().toString().charAt(i) >= 'A') && (etNome.getText().toString().charAt(i) <= 'Z')) || ((etNome.getText().toString().charAt(i) >= 'a') && (etNome.getText().toString().charAt(i) <= 'z')))) {
                                    bool = false;
                                }else{
                                    bool = true;
                                }
                                if (bool) {
                                    break;
                                }
                            }
                            if (response.isSuccessful()) {
                                tvAviso.setText(R.string.erro_email_ja_existe);
                                tvAviso.setVisibility(View.VISIBLE);

                            } else if (!(etSenha.getText().toString().equals(etConfirmaSenha.getText().toString()))) {
                                    tvAviso.setText(R.string.erro_senhas_diferentes);
                                    tvAviso.setVisibility(View.VISIBLE);
                                } else if (bool) {
                                    tvAviso.setText(R.string.erro_nome_invalido);
                                    tvAviso.setVisibility(View.VISIBLE);
                                } else if (!(usuario.getEmail().contains("@")) || usuario.getEmail().contains(" ")) {
                                    tvAviso.setText(R.string.erro_email_invalido);
                                    tvAviso.setVisibility(View.VISIBLE);
                                } else if (usuario.getSenha().length() < 4) {
                                    tvAviso.setText(R.string.erro_senha_pequena);
                                    tvAviso.setVisibility(View.VISIBLE);
                                } else {
                                    usuarioService.cadastrar(usuario).enqueue(new Callback<Usuario>() {
                                        @Override
                                        public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                                            if (response.isSuccessful()) {
                                                Intent intent = new Intent(CadastroActivity.this, CadastroDisciplinasActivity.class);
                                                intent.putExtra("usuario", usuario);
                                                intent.putExtra("serie", spSerie.getSelectedItem().toString());
                                                startActivity(intent);
                                                //Toast.makeText(getApplicationContext(), "Por favor, selecione suas disciplinas, para concluir o cadastro.", Toast.LENGTH_SHORT).show();
                                                finish();
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<Usuario> call, Throwable t) {
                                            tvAviso.setText(R.string.erro_conexao);
                                            tvAviso.setVisibility(View.VISIBLE);
                                        }
                                    });
                                }


                        }

                        @Override
                        public void onFailure(Call<Usuario> call, Throwable t) {


                        }
                    });
                }else{
                    tvAviso.setText(R.string.erro_valores_invalidos);
                    tvAviso.setVisibility(View.VISIBLE);
                }

            }
        });

        btPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvAviso.setText(R.string.erro_em_breve);
                tvAviso.setVisibility(View.VISIBLE);
            }
        });
    }

    private void inicializaComponentes(){
        this.etNome = findViewById(R.id.et_nome);
        this.etEmail = findViewById(R.id.et_email);
        this.etSenha = findViewById(R.id.et_senha);
        this.etConfirmaSenha = findViewById(R.id.et_confirma_senha);
        this.spCurso = findViewById(R.id.sp_curso);
        this.spSerie = findViewById(R.id.sp_serie);
        this.btProximo = findViewById(R.id.bt_next);
        this.btPerfil = findViewById(R.id.bt_perfil);
        this.adapterSeries = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaSeries);
        this.adapterCursos = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaCursos);
        this.ivDogPerfil = findViewById(R.id.iv_dog_perfil);
        this.tvAviso = findViewById(R.id.tv_aviso);
        this.retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.240.59:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.usuarioService = retrofit.create(UsuarioService.class);
        this.bool = false;

    }
}
