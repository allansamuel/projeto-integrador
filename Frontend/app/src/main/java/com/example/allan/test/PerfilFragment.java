package com.example.allan.test;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import classes.Usuario;


/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragment extends Fragment {

    private ListView listView ;
    private TextView tvNomeUsuario;
    private TextView tvEmail;
    private TextView tvCurso;
    private TextView tvNumeroPostagens;
    private Bundle bundle;
    private Usuario usuario;
    private String nomeTxt;
    private String emailTxt;
    private String cursoTxt;
    private String numPostagensTxt;
    private View rootView;
    private int numPostagens;
    private FloatingActionButton btConfig;


    public PerfilFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_perfil, container, false);
        usuario = (Usuario) getArguments().getSerializable("usuario");
        numPostagens = getArguments().getInt("num_postagens");
        tvNomeUsuario = rootView.findViewById(R.id.tv_nome_usuario);
        tvEmail = rootView.findViewById(R.id.tv_email);
        tvCurso = rootView.findViewById(R.id.tv_curso);
        tvNumeroPostagens = rootView.findViewById(R.id.tv_numero_postagens);
        nomeTxt = usuario.getNomeUsuario();
        emailTxt = usuario.getEmail();
        cursoTxt = usuario.getCursoUsuario();
        numPostagensTxt = "Publicou " + numPostagens + " Perguntas.";

        tvNomeUsuario.setText(nomeTxt);
        tvEmail.setText(emailTxt);
        tvCurso.setText(cursoTxt);
        tvNumeroPostagens.setText(numPostagensTxt);

        btConfig = rootView.findViewById(R.id.bt_config);

        btConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), EditarExcluirUsuarioActivity.class);
                intent.putExtra("usuario", usuario);
                startActivity(intent);
            }
        });
        return rootView;

    }

}
