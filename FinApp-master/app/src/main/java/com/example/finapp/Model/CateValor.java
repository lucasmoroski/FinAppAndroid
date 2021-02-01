package com.example.finapp.Model;

import java.io.Serializable;

public class CateValor implements Serializable {
    long id;
    double valor;
    Categoria categ;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Categoria getCateg() {
        return categ;
    }

    public void setCateg(Categoria categ) {
        this.categ = categ;
    }
}
