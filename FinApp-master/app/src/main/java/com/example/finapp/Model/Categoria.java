package com.example.finapp.Model;

import java.io.Serializable;

public class Categoria implements Serializable {
    long id;
    String descricao;
    int cat_id;

    public int getCat_id() {
        return cat_id;
    }

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "descricao='" + descricao + '\'' +
                '}';
    }

}
