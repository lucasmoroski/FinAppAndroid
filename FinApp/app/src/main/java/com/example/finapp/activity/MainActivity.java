package com.example.finapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.finapp.R;

public class MainActivity extends AppCompatActivity {

    Button btnCadastro,btnPesquisar,btnListar,btnExtrato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnCadastro = (Button)findViewById(R.id.btnCadastro);
        btnExtrato = findViewById(R.id.btnExtrato);
        btnPesquisar = (Button)findViewById(R.id.btnPesquisar);
        btnListar = findViewById(R.id.btnListar);

        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CadastroOperacao.class);
                startActivity(intent);
            }
        });

        btnExtrato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Extrato.class);
                startActivity(intent);
            }
        });

        btnPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Search.class);
                startActivity(intent);
            }
        });

        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ListaClassificada.class);
                startActivity(intent);
            }
        });

    }


    public void sairApp(View view) {
        finish();
    }
}