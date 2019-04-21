package com.example.johnnhidalgo.project.modelos;

public class PedidoMasita {
    int idPedido;
    int idMasita;
    int cantidad;
    int presio;

    public PedidoMasita() {
    }

    public PedidoMasita(int idPedido, int idMasita, int cantidad, int presio) {
        this.idPedido = idPedido;
        this.idMasita = idMasita;
        this.cantidad = cantidad;
        this.presio = presio;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdMasita() {
        return idMasita;
    }

    public void setIdMasita(int idMasita) {
        this.idMasita = idMasita;
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
