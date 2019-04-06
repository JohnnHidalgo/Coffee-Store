package com.example.johnnhidalgo.project.modelos;

public class Admin {
    int idAdmin;
    String nombreAdmin;
    String contraseñaAdmin;

    public Admin(int idAdmin, String nombreAdmin, String contraseñaAdmin) {
        this.idAdmin = idAdmin;
        this.nombreAdmin = nombreAdmin;
        this.contraseñaAdmin = contraseñaAdmin;
    }

    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getNombreAdmin() {
        return nombreAdmin;
    }

    public void setNombreAdmin(String nombreAdmin) {
        this.nombreAdmin = nombreAdmin;
    }

    public String getContraseñaAdmin() {
        return contraseñaAdmin;
    }

    public void setContraseñaAdmin(String contraseñaAdmin) {
        this.contraseñaAdmin = contraseñaAdmin;
    }

}
