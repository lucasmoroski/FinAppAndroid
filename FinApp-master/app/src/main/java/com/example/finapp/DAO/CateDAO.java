package com.example.finapp.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.finapp.Model.Categoria;

import java.util.ArrayList;
import java.util.List;

public class CateDAO {
    private SQLiteDatabase leitura;

    public CateDAO(Context applicationContext) {
        DBHelper db = new DBHelper(applicationContext);
        leitura = db.getReadableDatabase();
    }

    public List<Categoria> getAllCategorias(){
        List<Categoria> cateList = new ArrayList<>();
        Cursor cursor = leitura.query("Categoria", new String[]{"id","descricao","cat_id"},
                null,null,null,null,"cat_id, descricao");
        while(cursor.moveToNext()){
            Categoria categoria = new Categoria();
            Long id = cursor.getLong(cursor.getColumnIndex("id"));
            String descricao = cursor.getString(cursor.getColumnIndex("descricao"));
            int cat_id = cursor.getInt(cursor.getColumnIndex("cat_id"));

            categoria.setId(id);
            categoria.setDescricao(descricao);
            categoria.setCat_id(cat_id);
            cateList.add(categoria);
        }
        return cateList;
    }
}
