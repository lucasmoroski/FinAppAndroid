package com.example.finapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.finapp.DAO.CadastroopDAO;
import com.example.finapp.DAO.CateDAO;
import com.example.finapp.Model.Categoria;
import com.example.finapp.Model.Operacao;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Cadastro extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{


//    Spinner spinnerCadastro;
    EditText editTextValor;
    TextView viewData, textViewSpinner;
    private Categoria categoria;
    private CadastroopDAO opDAO;
    private CateDAO cateDAO;
    String data;
    Button btn_Criar_Cadastro;
    private List<Categoria> listcat;
    private List<Operacao> operacoes;
//    EditText DataCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        //data = (String)findViewById(R.id.editDataCadastro);

//        spinnerCadastro = findViewById(R.id.spinnerCadastro);
//        editTextValor = (EditText) findViewById(R.id.editValorCadastro);
        viewData = findViewById(R.id.dataSelecio);
//
        btn_Criar_Cadastro = (Button) findViewById(R.id.btnCriarCadastro);

        cateDAO = new CateDAO(getApplicationContext());
        opDAO = new CadastroopDAO(getApplicationContext());
//
//        listcat = cateDAO.getAllCategorias();
        operacoes = opDAO.getAllOperacoes();

//        ArrayAdapter categoriaAdapter = new ArrayAdapter(this,R.layout.activity_select_cate,listcat);
//        spinnerCadastro.setAdapter(categoriaAdapter);
//        spinnerCadastro.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
//
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                categoria = (Categoria) adapterView.getSelectedItem();
//                textViewSpinner = findViewById(R.id.categoria);
//                if(categoria.getCat_id()==1){
//                    textViewSpinner.setTextColor(Color.parseColor("#ff0000"));
//                }else{
//                    textViewSpinner.setTextColor(Color.parseColor("#00ff00"));
//                }
//                Toast.makeText(Cadastro.this,"Selecionado: "+ categoria,Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });


//        btn_data.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                DatePickerDialog datePickerDialog = new DatePickerDialog(null, null, Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
//                datePickerDialog.show();
//            }
//        });

        btn_Criar_Cadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(data==null){
                    Toast.makeText(Cadastro.this,"Selecione uma data. ",Toast.LENGTH_SHORT).show();
                    return ;
                }
                if(editTextValor.getText().toString().length()==0){
                    Toast.makeText(Cadastro.this,"Selecione um valor. ",Toast.LENGTH_SHORT).show();
                    return ;
                }
                if(categoria==null){
                    Toast.makeText(Cadastro.this,"Selecione uma categoria. ",Toast.LENGTH_SHORT).show();
                    return ;
                }
                Date date;
                try{
                    date = Formatacao.stringToDate(data);
                }catch (Exception e){
                    Toast.makeText(Cadastro.this,"Selecione uma data válida. ",Toast.LENGTH_SHORT).show();
                    return ;
                }
                Operacao op = new Operacao();
                op.setData(date);
                op.setValor(Double.parseDouble(editTextValor.getText().toString()));
                op.setCate(categoria);
                opDAO.insertOperacao(op);
                Toast.makeText(Cadastro.this,"Operação cadastrada. ",Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void selecionarCadastro(View view) {
        Intent intent = new Intent(this, OperacaoFinanceira.class);
        startActivity(intent);
    }

//    public void cadastrar(View view) throws ParseException {
//        if(data==null){
//            Toast.makeText(Cadastro.this,"Selecione uma data. ",Toast.LENGTH_SHORT).show();
//            return ;
//        }
//        if(editTextValor.getText().toString().length()==0){
//            Toast.makeText(Cadastro.this,"Selecione um valor. ",Toast.LENGTH_SHORT).show();
//            return ;
//        }
//        if(categoria==null){
//            Toast.makeText(Cadastro.this,"Selecione uma categoria. ",Toast.LENGTH_SHORT).show();
//            return ;
//        }
//        Date date;
//        try{
//            date = Formatacao.stringToDate(data);
//        }catch (Exception e){
//            Toast.makeText(Cadastro.this,"Selecione uma data válida. ",Toast.LENGTH_SHORT).show();
//            return ;
//        }
//        Operacao op = new Operacao();
//        op.setData(date);
//        op.setValor(Double.parseDouble(editTextValor.getText().toString()));
//        op.setCate(categoria);
//        operacaoDAO.insertOperacao(op);
//        Toast.makeText(Cadastro.this,"Operação cadastrada. ",Toast.LENGTH_SHORT).show();
//    }


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
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        data = i+"/"+(i1+1)+"/"+i2;
        viewData.setText(data);
    }

}

