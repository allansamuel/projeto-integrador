package com.example.allan.test;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import Services.UsuarioService;
import classes.Usuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EditarExcluirUsuarioActivity extends AppCompatActivity {

    private Bundle bundle;
    private Usuario usuario;
    private EditText etNomeUsuario;
    private EditText etEmail;
    private EditText etSenha;
    private EditText etConfirmaSenha;
    private FloatingActionButton btPerfil;
    private TextView tvAviso;
    private Button btEditar;
    private Button btExcluir;
    private Button btSair;
    private Retrofit retrofit;
    private UsuarioService usuarioService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_excluir_usuario);

        inicializaComponentes();

        etNomeUsuario.setText(usuario.getNomeUsuario());
        etEmail.setText(usuario.getEmail());

        btPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvAviso.setText(R.string.erro_em_breve);
                tvAviso.setVisibility(View.VISIBLE);
            }
        });

        btSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(EditarExcluirUsuarioActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuarioService.deletar(usuario.getIdUsuario()).enqueue(new Callback<Usuario>() {
                    @Override
                    public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                        if(response.isSuccessful()){
                            Intent intent = new Intent(EditarExcluirUsuarioActivity.this, LoginActivity.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<Usuario> call, Throwable t) {
                        Intent intent = new Intent(EditarExcluirUsuarioActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                });
            }
        });

        btEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Usuario usuarioEditado = new Usuario();
                usuarioEditado.setSenha(etSenha.getText().toString());
                usuarioEditado.setNomeUsuario(etNomeUsuario.getText().toString());
                usuarioEditado.setEmail(etEmail.getText().toString());
                if (!(etSenha.getText().toString().equals(etConfirmaSenha.getText().toString()))) {
                    tvAviso.setText(R.string.erro_senhas_diferentes);
                    tvAviso.setVisibility(View.VISIBLE);
                }else {
                    usuarioService.editar(usuarioEditado).enqueue(new Callback<Usuario>() {
                        @Override
                        public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                            if(response.isSuccessful()){
                                Intent intent = new Intent(EditarExcluirUsuarioActivity.this, BottomNavigationActivity.class);
                                intent.putExtra("usuario", response.body());
                                startActivity(intent);
                            }
                        }

                        @Override
                        public void onFailure(Call<Usuario> call, Throwable t) {

                        }
                    });
                }
            }
        });
    }

    private void inicializaComponentes(){
        this.bundle= getIntent().getExtras();
        this.usuario = (Usuario) bundle.getSerializable("usuario");
        this.etNomeUsuario = findViewById(R.id.et_nome);
        this.etEmail = findViewById(R.id.et_email);
        this.etSenha = findViewById(R.id.et_senha);
        this.etConfirmaSenha = findViewById(R.id.et_confirma_senha);
        this.btPerfil = findViewById(R.id.bt_perfil);
        this.tvAviso = findViewById(R.id.tv_aviso);
        this.retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.240.59:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.usuarioService = retrofit.create(UsuarioService.class);
        this.btEditar = findViewById(R.id.bt_editar);
        this.btSair  = findViewById(R.id.bt_logoff);
        this.btExcluir = findViewById(R.id.bt_excluir);
    }
}
