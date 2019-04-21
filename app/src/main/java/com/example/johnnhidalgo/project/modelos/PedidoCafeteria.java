package com.example.johnnhidalgo.project.modelos;

public class PedidoCafeteria {
    int idPedido;
    int idCafeteria;
    int cantidad;

    public PedidoCafeteria() {
    }
    public PedidoCafeteria(int idCafeteria, int cantidad ) {
        this.idCafeteria = idCafeteria;
        this.cantidad = cantidad;
    }
    public PedidoCafeteria(int idPedido, int idCafeteria, int cantidad ) {
        this.idPedido = idPedido;
        this.idCafeteria = idCafeteria;
        this.cantidad = cantidad;
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

}
