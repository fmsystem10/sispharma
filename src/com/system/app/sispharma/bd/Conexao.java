package com.system.app.sispharma.bd;

public class Conexao {

    String driver = "com.mysql.jdbc.Driver";
    String str_conn = "jdbc:mysql://localhost:3306/sispharma";
    String usuario = "root";
    String senha = "";

    public String getDriver() {
        return driver;
    }

    public String getSenha() {
        return senha;
    }

    public String getStr_conn() {
        return str_conn;
    }

    public String getUsuario() {
        return usuario;
    }
}
