package com.example.finapp.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.finapp.Model.Categoria;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static int version = 8;
    private static String name = "Usuario.db";

    public static String Table_Categoria = "Categoria";
    public static String Table_Operacao = "Operacao";

    public DBHelper(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String strc = "CREATE TABLE IF NOT EXISTS Usuario(" +
                " id integer primary key autoincrement," +
                " nome varchar(50)," +
                " usuario varchar(50)," +
                " senha varchar(50)" +
                ")";

        String str_cat = "CREATE TABLE IF NOT EXISTS " + Table_Categoria + "(" +
                " id_c integer primary key autoincrement, " +
                " descricao varchar(50) NOT NULL, " +
                " cat_id boolean NOT NULL" +
                "); ";

        String str_op = "CREATE TABLE IF NOT EXISTS " + Table_Operacao +"("  +
                            " id_op integer primary key autoincrement, " +
                            " valor double NOT NULL, " +
                            " data int NOT NULL, " +
                            " categoria integer NOT NULL, " +
                            " user_id integer NOT NULL, " +
                            " foreign key (categoria) references Categoria(id_c), " +
                            " foreign key (user_id) references Usuario(id)" +
                            "); ";

        String str_insert_cat = "INSERT INTO Categoria(descricao,cat_id) " +
                                "VALUES ('Educação', 1),('Lazer', 1),('Moradia', 1),('Saúde', 1),('Outros', 1)," +
                                        "('Salário', 0), ('Transferências', 0);";
        String str_insert_op = "INSERT INTO Operacao(valor,data,categoria,user_id) " +
                                "VALUES (250.00, 11122002,1,1);";
        try{
            db.execSQL(strc);
            db.execSQL(str_cat);
            db.execSQL(str_op);
            db.execSQL(str_insert_cat);
//            db.execSQL(str_insert_op);
            Log.i("DB log", "SQL Executada com sucesso!!");
        } catch (Exception e) {
            Log.i("DB log", "Erro ao Executar as SQL" + e.getMessage());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS Usuario;";
        String sql1 = "DROP TABLE IF EXISTS " + Table_Categoria + ";";
        String sql2 = "DROP TABLE IF EXISTS " + Table_Operacao + "; ";
        try {
            db.execSQL(sql);
            db.execSQL(sql1);
            db.execSQL(sql2);
            onCreate(db);
            Log.i("DB log", "SQL Upgrade Executada com sucesso!!");
        } catch (Exception e) {
            Log.i("DB log", "Erro ao Executar as SQL de Upgrade" + e.getMessage());
        }

    }

    public boolean checkusername(String usuario, String senha) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from Usuario where usuario = ? and senha = ?", new String[]{usuario, senha});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }

    }

//    public void insertCategoria(String descricao, Integer cat_id) {
//        SQLiteDatabase dbv = this.getWritableDatabase();
//        dbv.beginTransaction();
//        try {
//            ContentValues v = new ContentValues();
//            v.put("descricao", descricao);
//            v.put("cat_id", cat_id);
//            dbv.insert(Table_Categoria, null, v);
//            dbv.setTransactionSuccessful();
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            dbv.endTransaction();
//            dbv.close();
//        }
//    }

    public List<Categoria> getAllCat() {
        List<Categoria> list = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        db.beginTransaction();
        try {
            String str_select_cat = "Select * FROM " + Table_Categoria;
            Cursor cursor = db.query(Table_Categoria, new String[]{"id","descricao","cat_id"},
                    null, null, null, null,"cat_id, descricao");
//            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    Categoria cat = new Categoria();
                    long idCat = cursor.getLong(cursor.getColumnIndex("id"));
                    String descCat = cursor.getString(cursor.getColumnIndex("descricao"));
                    int cat_id = cursor.getInt(cursor.getColumnIndex("cat_id"));
                    cat.setId(idCat);
                    cat.setDescricao(descCat);
                    cat.setCat_id(cat_id);
                    list.add(cat);
                }
//            }
            db.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
            db.close();
        }

        return list;
    }
}
