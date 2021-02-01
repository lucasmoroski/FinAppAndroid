package com.example.finapp.Adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finapp.Model.CateValor;
import com.example.finapp.R;

import java.io.Serializable;
import java.util.List;

public class ListaAD implements Serializable {
    private List<CateValor> list;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView categ, valor;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
//            categ = itemView.findViewById(R.id.textViewCategoriaLista);
//            valor = itemView.findViewById(R.id.textViewValorLista);
        }
    }
//
//    public ListaAdapter(List<ItemLista> list){this.list = list; System.out.println(list.size()); }
//
//    @NonNull
//    @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View listaItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_cell, parent, false);
//        return new MyViewHolder(listaItem);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        ItemLista itemLista = list.get(position);
//        holder.categoria.setText(itemLista.getCategoria().getDescricao());
//        if(itemLista.getCategoria().isDebito()==1){
//            holder.valor.setText("- " + Utils.formatValor(itemLista.getValor()));
//            holder.valor.setTextColor(Color.parseColor("#ff0000"));
//        }else{
//            holder.valor.setText("+ " + Utils.formatValor(itemLista.getValor()));
//            holder.valor.setTextColor(Color.parseColor("#00ff00"));
//        }
//    }
//
//    @Override
//    public int getItemCount() {
//        return this.list.size();
//    }
}
