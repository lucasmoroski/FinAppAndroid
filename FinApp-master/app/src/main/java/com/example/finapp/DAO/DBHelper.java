package com.example.finapp.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static int  version = 1;
    private static String  name = "Usuario.db";

    public DBHelper(@Nullable Context context) {
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

        String str_cat = "CREATE TABLE IF NOT EXISTS Categoria(" +
                            " id integer primary key autoincrement, " +
                            " descricao varchar(50) , " +
                            " cat_id boolean " +
                            "); ";

        String str_op = "CREATE TABLE IF NOT EXISTS Operacao("  +
                            " id integer primary key autoincrement, " +
                            " valor double , " +
                            " data int, " +
                            " categoria integer, " +
                            " foreign key (categoria) references Categoria(id)" +
                            "); ";

        String str_insert_cat = "INSERT INTO Categoria(descricao,cat_id) " +
                                "VALUES ('Educação', 1),('Lazer', 1),('Moradia', 1),('Saúde', 1),('Outros', 1)," +
                                        "('Salário', 0), ('Transferências', 0);";

        try{
            db.execSQL(strc);
            db.execSQL(str_cat);
            db.execSQL(str_op);
            db.execSQL(str_insert_cat);
            Log.i("DB log", "SQL Executada com sucesso!!");
        }catch (Exception e){
            Log.i("DB log","Erro ao Executar as SQL" + e.getMessage());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS  Categoria; ";
        String sql2 = "DROP TABLE IF EXISTS Operacao; ";
        try{
            db.execSQL(sql);
            db.execSQL(sql2);
            onCreate(db);
            Log.i("DB log","SQL Upgrade Executada com sucesso!!");
        }catch (Exception e){
            Log.i("DB log","Erro ao Executar as SQL de Upgrade" + e.getMessage());
        }

    }

    public  boolean checkusername(String usuario, String senha){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from Usuario where usuario = ? and senha = ?", new String[]{usuario,senha});
        if(cursor.getCount() > 0){
            return true;
        }else{
            return false;
        }

    }
}
