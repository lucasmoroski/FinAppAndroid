package com.example.finapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListarCadastroUser extends AppCompatActivity {

    private ListView listaUser;
    private UsuarioDAO dao;
    private List<CadastroUser> userlista;
    private List<CadastroUser> userfiltro = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_cadastro_user);

        listaUser = findViewById(R.id.lista_usuario);
        dao = new UsuarioDAO(this);
        userlista = dao.listaTodos();
        userfiltro.addAll(userfiltro);
        ArrayAdapter<CadastroUser> adapterList = new ArrayAdapter<CadastroUser>(this, android.R.layout.simple_list_item_1, userfiltro);
        listaUser.setAdapter(adapterList);
    }
}