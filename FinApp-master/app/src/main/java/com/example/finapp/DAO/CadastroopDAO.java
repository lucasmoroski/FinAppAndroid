package com.example.finapp.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.finapp.Formatacao;
import com.example.finapp.Model.CadastroUser;
import com.example.finapp.Model.Categoria;
import com.example.finapp.Model.Operacao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.finapp.DAO.DBHelper.Table_Operacao;

public class CadastroopDAO {

    private DBHelper dbHelper;
    private SQLiteDatabase bdr,bdw;

    public CadastroopDAO(Context context) {
        dbHelper = new DBHelper(context);
        bdr = dbHelper.getWritableDatabase();
        bdw = dbHelper.getWritableDatabase();
    }

    public List<Operacao> getAllOperacoes() {
        List<Operacao> operacaoList = new ArrayList<>();
        try{
            String sql = "SELECT o.id, o.valor, o.data, o.categoria, c.id, c.descricao, c.cat_id "+
                         "FROM Operacao o " +
                         "JOIN Categoria c " +
                         "ON(o.categoria = c.id) ";
            Cursor cursor = bdr.rawQuery(sql,null);
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
            Cursor cursor = bdr.rawQuery(sql,null);
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
            Cursor cursor = bdr.rawQuery(sql,new String[]{String.valueOf(md1),String.valueOf(md2)});
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

    public long insertOp(Operacao op, CadastroUser us){
        ContentValues v = new ContentValues();
        v.put("data",Formatacao.dateToMili(op.getData()));
        v.put("valor",op.getValor());
        v.put("categoria",op.getCate().getId());
        v.put("user_id",us.getId());
        return bdw.insert(Table_Operacao,null,v);

    }

}
