package com.system.app.sispharma.entidades;

import com.system.app.sispharma.bd.Conexao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Usuario {

    Conexao con = new Conexao();
    private static int chave = 0;
    private String nome = null;
    private static int tipo = 0;
    private String login = null;
    private static String senha = null;

    public static int getChave() {
        return chave;
    }

    public static void setChave(int chave) {
        Usuario.chave = chave;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        Usuario.senha = senha;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        Usuario.tipo = tipo;
    }

    public String inserirUsuario(Usuario usu) {

        String retorno = "OK";

        try {
            Class.forName(con.getDriver());

            Connection conn = DriverManager.getConnection(con.getStr_conn(), con.getUsuario(), con.getSenha());
            Statement stmt = conn.createStatement();

            stmt.executeUpdate("INSERT INTO usuarios (nome, tipo, login, senha) "
                    + "VALUES ('" + usu.nome + "', '" + Usuario.tipo + "','" + usu.login + "', '" + Usuario.senha + "')");

            stmt.close();
            return retorno;
        } catch (ClassNotFoundException ex1) {
            retorno = "erro 01:  " + ex1.toString();
            return retorno;
        } catch (SQLException ex2) {
            retorno = "erro 02:  " + ex2.toString();
            return retorno;
        }
    }

    public String alterarUsuario(Usuario usu) {
        String retorno = "OK";


        return retorno;
    }

    public String excluirUsuario(int codigo) {
        String retorno = "OK";


        return retorno;
    }

    public int validarUsuario(String login, String senha) {

        try {
            int tipoUsuario = 999;
            Class.forName(con.getDriver());

            Connection conn = DriverManager.getConnection(con.getStr_conn(), con.getUsuario(), con.getSenha());
            Statement stmt = conn.createStatement();

            String sql = "SELECT tipo FROM usuarios WHERE login = '"+ login +"' and senha = '"+ senha +"'";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                tipoUsuario = rs.getInt("tipo");
            }

            stmt.close();
            return tipoUsuario;
        } catch (ClassNotFoundException ex) {
            System.out.println("NÃ£o foi possÃ­vel carregar o driver.");
            ex.printStackTrace();
            return 999;
        } catch (SQLException ex) {
            System.out.println("Problema com o SQL");
            ex.printStackTrace();
            return 999;
        }
    }

    public int retornaChaveUsuario(String senha) {
        try {
            int chaveUsuario = 0;
            Class.forName(con.getDriver());

            Connection conn = DriverManager.getConnection(con.getStr_conn(), con.getUsuario(), con.getSenha());
            Statement stmt = conn.createStatement();

            String sql1 = "SELECT chave_usuarios FROM usuarios WHERE SENHA = '"+ senha +"'";
            ResultSet rs = stmt.executeQuery(sql1);

            while (rs.next()) {
                chaveUsuario = rs.getInt("chave_usuarios");
            }

            stmt.close();
            return chaveUsuario;
        } catch (ClassNotFoundException ex) {
            System.out.println("Não foi possivel carregar o driver.");
            ex.printStackTrace();
            return 0;
        } catch (SQLException ex) {
            System.out.println("Problema com o SQL");
            ex.printStackTrace();
            return 0;
        }
    }
}
