package com.example.johnnhidalgo.project.modelos;

public class User {
    int idUser;
    String userName;
    String userPass;

    public User(int idUser, String userName, String userPass) {
        this.idUser = idUser;
        this.userName = userName;
        this.userPass = userPass;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }
}
