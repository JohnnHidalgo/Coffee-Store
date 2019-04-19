package com.example.johnnhidalgo.project.modelos;

public class Cafeteria {
    private int id;
    private String name;
    private String descripcion;
    private int precio;
    private byte[] image;

    public Cafeteria() {
    }

    public Cafeteria(String name, String descripcion, int precio, byte[] image) {
        this.name = name;
        this.descripcion = descripcion;
        this.precio = precio;
        this.image = image;
    }

    public Cafeteria(int id, String name, String descripcion, int precio, byte[] image) {
        this.id = id;
        this.name = name;
        this.descripcion = descripcion;
        this.precio = precio;
        this.image = image;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
