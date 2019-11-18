package com.example.menu_tp;

public class Produit {
    @Override
    public String toString() {
        return "Produit{" +
                "id=" + id +
                ", com='" + com + '\'' +
                ", category='" + category + '\'' +
                ", prix='" + prix + '\'' +
                ", nombre=" + nombre +
                '}';
    }

    private int id;
    private String com;
    private String category;
    private String prix;
    private int nombre;

    public Produit(int id, String com, String category, String prix, int nombre) {
        this.id = id;
        this.com = com;
        this.category = category;
        this.prix = prix;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCom() {
        return com;
    }

    public void setCom(String com) {
        this.com = com;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPrix() {
        return prix;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }
}
