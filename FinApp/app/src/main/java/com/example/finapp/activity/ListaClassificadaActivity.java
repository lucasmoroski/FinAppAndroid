package com.example.finapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.finapp.R;
import com.example.finapp.adapter.ExtratoAdapter;
import com.example.finapp.adapter.ListaAdapter;
import com.example.finapp.helper.ListaDAO;
import com.example.finapp.helper.OperacaoDAO;
import com.example.finapp.model.ItemLista;
import com.example.finapp.model.Operacao;

import java.util.ArrayList;
import java.util.List;

public class ListaClassificadaActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ListaAdapter listaAdapter;
    private List<ItemLista> itemList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_classificada);

        recyclerView = findViewById(R.id.recyclerViewLista);

        ListaDAO listaDAO = new ListaDAO(getApplicationContext());
        itemList = listaDAO.get15Itens();

        listaAdapter = new ListaAdapter(itemList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        recyclerView.setAdapter(listaAdapter);
    }
}