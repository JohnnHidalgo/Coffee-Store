package com.example.johnnhidalgo.project.modelos;

public class VentaCafeteria {
    int idPedido;
    int idCafeteria;
    int cantidad;

    public VentaCafeteria() {
    }

    public VentaCafeteria(int idPedido, int idCafeteria, int cantidad) {
        this.idPedido = idPedido;
        this.idCafeteria = idCafeteria;
        this.cantidad = cantidad;
    }


    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdCafeteria() {
        return idCafeteria;
    }

    public void setIdCafeteria(int idCafeteria) {
        this.idCafeteria = idCafeteria;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
