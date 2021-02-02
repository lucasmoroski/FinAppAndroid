package com.example.finapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finapp.R;
import com.example.finapp.Formatador;
import com.example.finapp.helper.CategoriaDAO;
import com.example.finapp.helper.OperacaoDAO;
import com.example.finapp.model.Operacao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Search extends AppCompatActivity {

    Spinner spinner;
    TextView textViewData1, textViewData2, textViewSpinner;
    Button btnDtInicio, btnDtFim;
    OperacaoDAO operacaoDAO;
    String dtInicio, dtFim, categoria;
    public static List<Operacao> operacoes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        spinner = findViewById(R.id.spinner);
        textViewData1 = findViewById(R.id.textView10);
        textViewData2 = findViewById(R.id.textView9);
        btnDtInicio = findViewById(R.id.button2);
        btnDtFim = findViewById(R.id.button4);

        CategoriaDAO categoriaDAO = new CategoriaDAO(getApplicationContext());
        operacaoDAO = new OperacaoDAO(getApplicationContext());

        List<String> categorias = new ArrayList<>();
        categorias.add("Todos");
        categorias.add("Débito");
        categorias.add("Crédito");

        List<Operacao> operacoes = operacaoDAO.getAllOperacoes();

        ArrayAdapter categoriaAdapter = new ArrayAdapter(this,R.layout.spinner,categorias);
        spinner.setAdapter(categoriaAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                categoria = (String) adapterView.getSelectedItem();
                textViewSpinner = findViewById(R.id.textViewSpinner);
                Toast.makeText(Search.this, "Selecionado: " + categoria, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        View.OnClickListener showDatePicker = new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                final View vv = view;

                DatePickerDialog datePickerDialog = new DatePickerDialog(Search.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        if(vv.getId() == R.id.button2){
                            dtInicio = day+"/"+(month+1)+"/"+year;
                            textViewData1.setText(dtInicio);
                        }else{
                            dtFim = day+"/"+(month+1)+"/"+year;
                            textViewData2.setText(dtFim);
                        }
                    }
                },Calendar.getInstance().get(Calendar.YEAR),
                        Calendar.getInstance().get(Calendar.MONTH),
                        Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        };

        btnDtInicio.setOnClickListener(showDatePicker);
        btnDtFim.setOnClickListener(showDatePicker);
    }
    public void BuscaCredandDebito(View view) throws ParseException {
        if(dtInicio == null){
            Toast.makeText(Search.this,"Data de início Não inserida. ",Toast.LENGTH_SHORT).show();
            return ;
        }else
        if(dtFim == null){
            Toast.makeText(Search.this,"Data de fim Não inserida. ",Toast.LENGTH_SHORT).show();
            return ;
        }else
        if(categoria == null){
            Toast.makeText(Search.this,"Categoria Não selecionada. ",Toast.LENGTH_SHORT).show();
            return ;
        }
        Date dtIni, dtF;
        try{
            dtIni = Formatador.stringToDate(dtInicio);
            dtF = Formatador.stringToDate(dtFim);
            if(dtF.getTime() < dtIni.getTime()) {
                Toast.makeText(Search.this,"Selecione A data Final maior que a de Inicio. ",Toast.LENGTH_SHORT).show();
            }else{
                operacoes = operacaoDAO.pesquisar(dtIni, dtF, categoria);
                Intent i = new Intent(this, ResultSearch.class);
                startActivity(i);
                finish();
            }

        }catch (Exception e){
            Toast.makeText(Search.this,"Erro ao Pesquisar. ",Toast.LENGTH_SHORT).show();
            return ;
        }

    }


}