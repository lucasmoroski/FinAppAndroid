package com.example.finapp.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.finapp.Model.Categoria;

import java.util.ArrayList;
import java.util.List;

import static com.example.finapp.DAO.DBHelper.Table_Categoria;

public class CateDAO {

    private SQLiteDatabase read;

    public CateDAO(Context applicationContext) {
        DBHelper db = new DBHelper(applicationContext);
        read = db.getReadableDatabase();
    }

    public List<Categoria> getAllCategorias(){
        List<Categoria> cateList = new ArrayList<>();
        String str_select_cat = "SELECT * FROM " + Table_Categoria +";";
        Cursor cursor = read.query(DBHelper.Table_Categoria,new String[]{"id","descricao","cat_id"}, null,null,null,null,null);
            while (cursor.moveToNext()) {
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
