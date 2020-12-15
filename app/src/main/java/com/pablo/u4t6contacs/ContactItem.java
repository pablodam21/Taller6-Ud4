package com.pablo.u4t6contacs;

import android.widget.ImageView;

public class ContactItem {
    private int id;

    private String nombre;

    private String numero;

    private String foto;

    public ContactItem(int id, String nombre, String numero, String foto) {
        this.id = id;
        this.nombre = nombre;
        this.numero = numero;
        this.foto = foto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        return "ContactItem{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", numero='" + numero + '\'' +
                ", foto=" + foto +
                '}';
    }
}
