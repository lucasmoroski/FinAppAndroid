package com.example.finapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btn_Cadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_Cadastro = (Button) findViewById(R.id.btnCadastro);

    }

    public void abrirCadastro(View view) {
        Intent intent = new Intent(this, Cadastro.class);
        startActivity(intent);
    }

    public void sairApp(View view) {
        finish();
    }
}