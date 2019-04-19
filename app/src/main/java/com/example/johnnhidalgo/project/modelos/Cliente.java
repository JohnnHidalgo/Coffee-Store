package com.example.johnnhidalgo.project.modelos;

public class Cliente {
    int idCliente;
    String clienteName;
    String clientePass;

    public Cliente(){

    }

    public Cliente(int idCliente, String clienteName, String clientePass) {
        this.idCliente = idCliente;
        this.clienteName = clienteName;
        this.clientePass = clientePass;
    }

    public Cliente(String clienteName, String clientePass) {
        this.clienteName = clienteName;
        this.clientePass = clientePass;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getClienteName() {
        return clienteName;
    }

    public void setClienteName(String clienteName) {
        this.clienteName = clienteName;
    }

    public String getClientePass() {
        return clientePass;
    }

    public void setClientePass(String clientePass) {
        this.clientePass = clientePass;
    }
}
