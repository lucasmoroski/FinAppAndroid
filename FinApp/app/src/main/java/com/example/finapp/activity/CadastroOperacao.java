package com.example.finapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finapp.R;
import com.example.finapp.Formatador;
import com.example.finapp.helper.CategoriaDAO;
import com.example.finapp.helper.OperacaoDAO;
import com.example.finapp.model.Categoria;
import com.example.finapp.model.Operacao;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CadastroOperacao extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    Spinner spinnerCategorias;
    EditText editTextValor;
    TextView textViewData, textViewSpinner;
    Categoria categoria;
    OperacaoDAO operacaoDAO;
    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        spinnerCategorias = findViewById(R.id.spinnerCategorias);
        editTextValor = findViewById(R.id.editTextValor);
        textViewData = findViewById(R.id.textViewData);

        CategoriaDAO categoriaDAO = new CategoriaDAO(getApplicationContext());
        operacaoDAO = new OperacaoDAO(getApplicationContext());

        List<Categoria> categorias = categoriaDAO.getAllCategorias();
//        List<Operacao> operacoes = operacaoDAO.getAllOperacoes();

        ArrayAdapter categoriaAdapter = new ArrayAdapter(this,R.layout.spinner,categorias);
        spinnerCategorias.setAdapter(categoriaAdapter);
        spinnerCategorias.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                categoria = (Categoria) adapterView.getSelectedItem();
                textViewSpinner = findViewById(R.id.textViewSpinner);
                Toast.makeText(CadastroOperacao.this,"Selecionado: "+categoria,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void cadastrar(View view) throws ParseException {
        if(data == null){
            Toast.makeText(CadastroOperacao.this,"Insira uma data. ",Toast.LENGTH_SHORT).show();
            return ;
        }else if(editTextValor.getText().toString().length() == 0){
            Toast.makeText(CadastroOperacao.this,"Insira um valor. ",Toast.LENGTH_SHORT).show();
            return ;
        }else if(categoria == null){
            Toast.makeText(CadastroOperacao.this,"Selecione uma categoria. ",Toast.LENGTH_SHORT).show();
            return ;
        }
        Date date;
        try{
            date = Formatador.stringToDate(data);
        }catch (Exception e){
            Toast.makeText(CadastroOperacao.this,"Insira uma data válida. ",Toast.LENGTH_SHORT).show();
            return ;
        }
        Operacao op = new Operacao();
        op.setData(date);
        op.setValor(Double.parseDouble(editTextValor.getText().toString()));
        op.setCategoria(categoria);
        boolean teste = operacaoDAO.insertOperacao(op);
        Toast.makeText(CadastroOperacao.this,"Operação cadastrada. ",Toast.LENGTH_SHORT).show();
        if(teste == true) {
            Intent intent = new Intent(CadastroOperacao.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void datePicker(View view){
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }


    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        data = day+"/"+(month+1)+"/"+year;
        textViewData.setText(data);
    }
}