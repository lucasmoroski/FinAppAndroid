package com.example.finapp.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finapp.R;
import com.example.finapp.Formatador;
import com.example.finapp.model.Operacao;

import java.util.List;

public class ExtratoAdapter extends RecyclerView.Adapter<ExtratoAdapter.MyViewHolder> {
    private List<Operacao> list;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView categoria, data, valor;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            categoria = itemView.findViewById(R.id.textViewCategoriaExtrato);
            data = itemView.findViewById(R.id.textViewDataExtrato);
            valor = itemView.findViewById(R.id.textViewValorExtrato);
        }
    }

    public ExtratoAdapter(List<Operacao> list){this.list = list; }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View operacaoItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.extrato_cell, parent, false);
        return new MyViewHolder(operacaoItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Operacao operacao = list.get(position);
        holder.categoria.setText(operacao.getCategoria().getDescricao());
        holder.data.setText(Formatador.dateToString(operacao.getData()));
        if(operacao.getCategoria().isDebito()==1){
            holder.valor.setText("- " + Formatador.formatValor(operacao.getValor()));
            holder.valor.setTextColor(Color.parseColor("#f15757"));
        }else{
            holder.valor.setText("+ " + Formatador.formatValor(operacao.getValor()));
            holder.valor.setTextColor(Color.parseColor("#52bf52"));
        }
    }

    @Override
    public int getItemCount() {
        return this.list.size();
    }
}
