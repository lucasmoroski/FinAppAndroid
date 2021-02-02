package com.example.finapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
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

import com.example.finapp.Adapter.ListaAD;
import com.example.finapp.DAO.CadastroopDAO;
import com.example.finapp.DAO.CateDAO;
import com.example.finapp.DAO.DBHelper;
import com.example.finapp.DAO.RecyclerView;
import com.example.finapp.DAO.UsuarioDAO;
import com.example.finapp.Model.CadastroUser;
import com.example.finapp.Model.Categoria;
import com.example.finapp.Model.Operacao;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.example.finapp.DAO.DBHelper.Table_Categoria;

public class Cadastro extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    private Spinner spCat;
    private ListaAD listaAD;
    private List<Categoria> listCat = new ArrayList<>();

//    Spinner spCat;
    EditText editTextValor;
    TextView viewData, txtCat;
    Categoria categoria;
    private CadastroopDAO opDAO;
    private CadastroUser CadastUs;
    String data;
    Button btn_Criar_Cadastro;
//    CateDAO ctdao;
//    private List<Categoria> listcat;
//    private List<Operacao> operacoes;
//    EditText DataCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        viewData = findViewById(R.id.dataSelecio);
        btn_Criar_Cadastro = (Button) findViewById(R.id.btnCriarCadastro);
        opDAO = new CadastroopDAO(getApplicationContext());

        spCat = findViewById(R.id.spinnerCadastro);

        CateDAO cateDAO = new CateDAO(getApplicationContext());
        listCat = cateDAO.getAllCategorias();
        listaAD = new ListaAD(listCat);

        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase dbc = dbHelper.getReadableDatabase();

        ArrayAdapter adCat = new ArrayAdapter(this,R.layout.todo_cell,listCat);
        spCat.setAdapter(adCat);
        spCat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                 categoria = (Categoria)adapterView.getSelectedItem();
                 txtCat = findViewById(R.id.reViewSpin);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        //data = (String)findViewById(R.id.editDataCadastro);

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
                if(categoria ==null){
                    Toast.makeText(Cadastro.this,"Selecione uma categoria. ",Toast.LENGTH_SHORT).show();
                    return ;
                }else
                    if(editTextValor.getText().toString().length()==0){
                    Toast.makeText(Cadastro.this,"Selecione um valor. ",Toast.LENGTH_SHORT).show();
                    return ;
                }else if(data==null){
                    Toast.makeText(Cadastro.this,"Selecione uma data. ",Toast.LENGTH_SHORT).show();
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
                CadastroUser us = new CadastroUser();
                op.setData(date);
                op.setValor(Double.parseDouble(editTextValor.getText().toString()));
                op.setCate(categoria);
                opDAO.insertOp(op,us);
                Toast.makeText(Cadastro.this,"Operação cadastrada. ",Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void Criar(View v) throws ParseException{

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
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        data = i+"/"+(i1+1)+"/"+i2;
        viewData.setText(data);
    }

}

