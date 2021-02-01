package com.example.finapp.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.finapp.Formatacao;
import com.example.finapp.Model.Categoria;
import com.example.finapp.Model.Operacao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CadastroopDAO {

    private SQLiteDatabase Ler;
    private SQLiteDatabase mostrar;

    public CadastroopDAO(Context applicationContext) {
        DBHelper db = new DBHelper(applicationContext);
        Ler = db.getReadableDatabase();
        mostrar = db.getWritableDatabase();
    }

    public List<Operacao> getAllOperacoes() {
        List<Operacao> operacaoList = new ArrayList<>();
        try{
            String sql = "SELECT o.id, o.valor, o.data, o.categoria, c.id, c.descricao, c.cat_id "+
                         "FROM Operacao o " +
                         "JOIN Categoria c " +
                         "ON(o.categoria = c.id) ";
            Cursor cursor = Ler.rawQuery(sql,null);
            while(cursor.moveToNext()){
                Operacao op = new Operacao();
                Long id = cursor.getLong(0);
                Double valor = cursor.getDouble(1);
                op.setId(id);
                op.setValor(valor);
                op.setData(Formatacao.miliToDate(cursor.getLong(2)));

                Categoria categoria = new Categoria();
                Long idCat = cursor.getLong(4);
                String descricao = cursor.getString(5);
                int cat_id = cursor.getInt(6);
                categoria.setId(idCat);
                categoria.setDescricao(descricao);
                categoria.setCat_id(cat_id);
                op.setCate(categoria);
                operacaoList.add(op);
            }
            return operacaoList;
        }catch (Exception e){
            return null;
        }
    }

    public List<Operacao> get15Operacoes() {
        List<Operacao> operacaoList = new ArrayList<>();
        try{
            String sql = "SELECT o.id, o.valor, o.data, o.categoria, c.id, c.descricao, c.cat_id "+
                         "FROM Operacao o " +
                         "JOIN Categoria c " +
                         "ON(o.categoria=c.id) " +
                         "ORDER BY o.data DESC LIMIT 15 ";
            Cursor cursor = Ler.rawQuery(sql,null);
            while(cursor.moveToNext()){
                Operacao op = new Operacao();
                Long id = cursor.getLong(0);
                Double valor = cursor.getDouble(1);
                op.setId(id);
                op.setValor(valor);
                op.setData(Formatacao.miliToDate(cursor.getLong(2)));

                Categoria categoria = new Categoria();
                Long idCat = cursor.getLong(4);
                String descricao = cursor.getString(5);
                int debito = cursor.getInt(6);
                categoria.setId(idCat);
                categoria.setDescricao(descricao);
                categoria.setCat_id(debito);
                op.setCate(categoria);
                operacaoList.add(op);
            }
            return operacaoList;
        }catch (Exception e){
            return null;
        }
    }

    public List<Operacao> pesquisar(Date d1, Date d2, String categoria) {
        List<Operacao> operacaoList = new ArrayList<>();
        try{
            String sql = "SELECT o.id, o.valor, o.data, o.categoria, c.id, c.descricao, c.cat_id "+
                         "FROM Operacao o " +
                         "JOIN Categoria c " +
                         "ON(o.categoria=c.id) " +
                         "WHERE o.data >= ? AND o.data <= ? ";
            if(categoria.equals("Débito")){
                sql += "AND c.cat_id = 1 ";
            }else if(categoria.equals("Crédito")){
                sql += " AND c.cat_id = 0 ";
            }
            sql += " ORDER BY o.data DESC ";
            long md1 = Formatacao.dateToMili(d1);
            long md2 = Formatacao.dateToMili(d2);
            Cursor cursor = Ler.rawQuery(sql,new String[]{String.valueOf(md1),String.valueOf(md2)});
            while(cursor.moveToNext()){
                Operacao op = new Operacao();
                Long id = cursor.getLong(0);
                Double valor = cursor.getDouble(1);
                op.setId(id);
                op.setValor(valor);
                op.setData(Formatacao.miliToDate(cursor.getLong(2)));

                Categoria cat = new Categoria();
                Long idCat = cursor.getLong(4);
                String descricao = cursor.getString(5);
                int debito = cursor.getInt(6);
                cat.setId(idCat);
                cat.setDescricao(descricao);
                cat.setCat_id(debito);
                op.setCate(cat);
                operacaoList.add(op);
            }
            return operacaoList;
        }catch (Exception e){
            return null;
        }
    }

    public boolean insertOperacao(Operacao operacao){
        ContentValues values = new ContentValues();
        values.put("data",Formatacao.dateToMili(operacao.getData()));
        values.put("valor",operacao.getValor());
        values.put("categoria",operacao.getCate().getId());
        try{
            mostrar.insert("Operacao",null,values);
            Log.i("INFO", "Tarefa salva.");
        }catch (Exception e){
            Log.e("INFO","Erro ao salvar" + e.getMessage());
            return false;
        }
        return true;
    }

}
