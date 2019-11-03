package com.example.allan.test;

import android.content.Intent;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import Services.UsuarioService;
import classes.Usuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail;
    private EditText etSenha;
    private Button btCadastro;
    private Button btLogin;
    private TextView tvAviso;
    private Usuario usuario;
    private Retrofit retrofit;
    private UsuarioService usuarioService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inicializaComponentes();


        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                usuario.setEmail(etEmail.getText().toString());
                usuario.setSenha(etSenha.getText().toString());

                if((usuario.getEmail().isEmpty()) ||  usuario.getSenha().isEmpty()){
                    tvAviso.setText(R.string.erro_valores_invalidos);
                    tvAviso.setVisibility(View.VISIBLE);
                }else if(!(usuario.getEmail().contains("@"))){
                    tvAviso.setText(R.string.erro_email_invalido);
                    tvAviso.setVisibility(View.VISIBLE);

                }else {

                    usuarioService.buscarPorEmailESenha(usuario.getEmail(), usuario.getSenha()).enqueue(new Callback<Usuario>() {
                        @Override
                        public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                            if(response.isSuccessful()) {
                                usuario = response.body();
                                Intent intent = new Intent(LoginActivity.this, BottomNavigationActivity.class);

                                intent.putExtra("usuario", usuario);
                                startActivity(intent);
                              Toast.makeText(getApplicationContext(), "Bem vindo(a), " + usuario.getNomeUsuario(), Toast.LENGTH_SHORT).show();
                            }else{
                                tvAviso.setText(R.string.erro_conexao);
                                tvAviso.setVisibility(View.VISIBLE);
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
        });

        btCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, CadastroActivity.class);
                startActivity(intent);
            }
        });


    }
    private void inicializaComponentes(){
        this.etEmail = findViewById(R.id.et_email);
        this.etSenha = findViewById(R.id.et_senha);
        this.btCadastro = findViewById(R.id.bt_cadastro);
        this.btLogin = findViewById(R.id.bt_confirma_cadastro);
        this.tvAviso = findViewById(R.id.tv_aviso);
        this.retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.240.59:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.usuarioService = retrofit.create(UsuarioService.class);
        this.usuario = new Usuario();
    }
}
