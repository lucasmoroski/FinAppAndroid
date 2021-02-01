package com.example.finapp.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.finapp.Model.CadastroUser;

import java.util.ArrayList;
import java.util.List;

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

    public List<CadastroUser> listaTodos(){
        List<CadastroUser> usuario = new ArrayList<>();
        Cursor cur = bd.query("Usuario",new String[]{"id","nome","usuario"},null,null,null,null,null);
        while (cur.moveToNext()){
            CadastroUser u = new CadastroUser();
            u.setId(cur.getInt(0));
            u.setNome(cur.getString(1));
            u.setUsuario(cur.getString(2));
            usuario.add(u);
        }
        return usuario;
    }
}
