package com.example.finapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Cadastro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
    }

    public void selecionarCadastro(View view) {
        Intent intent = new Intent(this, OperacaoFinanceira.class);
        startActivity(intent);
    }
}