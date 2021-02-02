package com.example.finapp.Adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finapp.Formatacao;
import com.example.finapp.Model.CateValor;
import com.example.finapp.Model.Categoria;
import com.example.finapp.R;

import java.util.List;

public class ListaAD extends RecyclerView.Adapter<ListaAD.MyViewHolder> {
    private List<Categoria> list;

    public class MyViewHolder extends RecyclerView.ViewHolder{

//        RecyclerView categ;
        TextView view, categ;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            categ = itemView.findViewById(R.id.reViewSpin);
            view = itemView.findViewById(R.id.textTodocell);
        }
    }

    public ListaAD(List<Categoria> list){
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View Item = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_cell, parent, false);
        return new MyViewHolder(Item);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Categoria listIt = list.get(position);
        holder.view.setText(listIt.getDescricao());
        if(listIt.getCat_id()==1){
            holder.view.setText("- " + Formatacao.formatValor(listIt.getId()));
            holder.view.setTextColor(Color.parseColor("#ff0000"));
        }else{
            holder.view.setText("+ " + Formatacao.formatValor(listIt.getId()));
            holder.view.setTextColor(Color.parseColor("#00ff00"));
        }
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }

}
