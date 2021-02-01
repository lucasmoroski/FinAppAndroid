package com.example.finapp.Adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finapp.Formatacao;
import com.example.finapp.Model.Operacao;
import com.example.finapp.R;

import java.io.Serializable;
import java.util.List;

public class ExtratoAD implements Serializable {
    private List<Operacao> list;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView categ, valor, data;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            categ = itemView.findViewById(R.id.textViewCategoriaExtrato);
            data = itemView.findViewById(R.id.textViewDataExtrato);
            valor = itemView.findViewById(R.id.textViewValorExtrato);
        }
    }

    public ExtratoAD(List<Operacao> list){this.list = list; }

//    @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View operacaoItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_extrato_list, parent, false);
//        return new MyViewHolder(operacaoItem);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        Operacao operacao = list.get(position);
//        holder.categoria.setText(operacao.getCate().getDescricao());
//        holder.data.setText(Formatacao.dateToString(operacao.getData()));
//        if(operacao.getCate().getCat_id()==1){
//            holder.valor.setText("- " + Formatacao.formatValor(operacao.getValor()));
//            holder.valor.setTextColor(Color.parseColor("#ff0000"));
//        }else{
//            holder.valor.setText("+ " + Formatacao.formatValor(operacao.getValor()));
//            holder.valor.setTextColor(Color.parseColor("#00ff00"));
//        }
//    }
//

    public int getItemCount() {
        return this.list.size();
    }
}
