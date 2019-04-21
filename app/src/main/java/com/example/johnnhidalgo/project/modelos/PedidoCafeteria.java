package com.example.johnnhidalgo.project.modelos;

public class PedidoCafeteria {
    int idPedido;
    int idCafeteria;
    int cantidad;
    int presio;

    public PedidoCafeteria() {
    }
    public PedidoCafeteria(int idCafeteria, int cantidad, int presio) {
        this.idCafeteria = idCafeteria;
        this.cantidad = cantidad;
        this.presio = presio;
    }
    public PedidoCafeteria(int idPedido, int idCafeteria, int cantidad, int presio) {
        this.idPedido = idPedido;
        this.idCafeteria = idCafeteria;
        this.cantidad = cantidad;
        this.presio = presio;
    }

    public int getIdPedido() {
        return idPedido;
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

    public int getPresio() {
        return presio;
    }

    public void setPresio(int presio) {
        this.presio = presio;
    }
}
