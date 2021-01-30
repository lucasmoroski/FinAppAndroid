package com.example.finapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.finapp.DAO.DBHelper;

public class Login extends AppCompatActivity {

    EditText editUsuario,editSenha;
    Button btnEntrar,btnCadast;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editUsuario = (EditText)findViewById(R.id.editUsuario);
        editSenha = (EditText)findViewById(R.id.editSenha);
        btnEntrar = (Button)findViewById(R.id.btnEntrar);
        btnCadast = (Button)findViewById(R.id.btnCadast);
        db = new DBHelper(this);

        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = editUsuario.getText().toString();
                String sen = editSenha.getText().toString();

                if(user.equals("")||sen.equals("")){
                    Toast.makeText(Login.this,"Verifique se todoas os campos foram preenchidos.", Toast.LENGTH_SHORT).show();
                }else{
                    Boolean checkuser = db.checkusername(user,sen);
                    if(checkuser == true){
                        Toast.makeText(Login.this,"Bem Vindo!!", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                    }else{
                        Toast.makeText(Login.this,"Usuario ou Senha Não existe.", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        btnCadast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getApplicationContext(), CadastroUsuario.class);
                startActivity(in);
            }
        });
    }
}