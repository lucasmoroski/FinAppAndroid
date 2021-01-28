package com.example.finapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class OperacaoFinanceira extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operacao_financeira);
    }

    public void detalheDeb(View view) {
        Intent intent = new Intent(this, DetalheOperacaoDeb.class);
        startActivity(intent);
    }

    public void detalheCre(View view) {
        Intent intent = new Intent(this, DetalheOperacaoCre.class);
        startActivity(intent);
    }
}