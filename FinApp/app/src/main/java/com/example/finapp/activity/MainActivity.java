package com.example.finapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.finapp.R;

public class MainActivity extends AppCompatActivity {

    ImageView imgCadastrar, imgExtrato, imgPesquisar, imgCategoria;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgCadastrar = findViewById(R.id.imageViewCadastrar);
        imgExtrato = findViewById(R.id.imageViewExtrato);
        imgPesquisar = findViewById(R.id.imageViewPesquisar);
        imgCategoria = findViewById(R.id.imageViewCategorias);

        imgCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cadastrar(view);
            }
        });

        imgExtrato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                extrato(view);
            }
        });

        imgPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pesquisar(view);
            }
        });

        imgCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lista(view);
            }
        });

    }

    public void cadastrar(View view){
        Intent intent = new Intent(MainActivity.this, CadastroActivity.class);
        startActivity(intent);
    }

    public void extrato(View view){
        Intent intent = new Intent(MainActivity.this, ExtratoActivity.class);
        startActivity(intent);
    }

    public void pesquisar(View view){
        Intent intent = new Intent(MainActivity.this, PesquisarActivity.class);
        startActivity(intent);
    }

    public void lista(View view){
        Intent intent = new Intent(MainActivity.this, ListaClassificadaActivity.class);
        startActivity(intent);
    }
}