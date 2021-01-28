package com.example.finapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class UsuarioDAO {

    private DBHelper dbHelper;
    private SQLiteDatabase bd;


    public UsuarioDAO (Context context){
        dbHelper = new DBHelper(context);
        bd = dbHelper.getWritableDatabase();
    }

    public long Inserir(CadastroUser cadastroUser){
        ContentValues v = new ContentValues();
        v.put("nome",cadastroUser.getNome());
        v.put("usuario",cadastroUser.getUsuario());
        v.put("senha",cadastroUser.getSenha());
        return bd.insert("Usuario", null, v);
    }
}
