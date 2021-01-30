package com.example.finapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.finapp.DAO.UsuarioDAO;
import com.example.finapp.Model.CadastroUser;

public class CadastroUsuario extends AppCompatActivity {

    private EditText nome, usuario, senha;
    Button buttonCadast;
    private UsuarioDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_user);

        nome = (EditText)findViewById(R.id.editNome);
        usuario = (EditText)findViewById(R.id.editUser);
        senha = (EditText)findViewById(R.id.editSenha);

        buttonCadast = (Button)findViewById(R.id.buttonCadast);

        dao = new UsuarioDAO(this);

        buttonCadast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CadastroUser c = new CadastroUser();
                c.setNome(nome.getText().toString());
                c.setUsuario(usuario.getText().toString());
                c.setSenha(senha.getText().toString());
                long id = dao.Inserir(c);
                if(id > 0){
                Toast.makeText(CadastroUsuario.this, "Usuario Cadastrado Com Sucesso!! ", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), Login.class);
                startActivity(i);
                } else {
                    Toast.makeText(CadastroUsuario.this, "Usuario Não Cadastrado" , Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
