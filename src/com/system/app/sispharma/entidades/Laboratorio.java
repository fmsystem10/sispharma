package com.system.app.sispharma.entidades;

import com.system.app.sispharma.bd.Conexao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

public class Laboratorio {

    Conexao con = new Conexao();
    private String laboratorio = null;

    public String getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(String laboratorio) {
        this.laboratorio = laboratorio;
    }

    public String inserirLaboratorio(Laboratorio lab) {

        String retorno = "OK";

        try {
            Class.forName(con.getDriver());

            Connection conn = DriverManager.getConnection(con.getStr_conn(), con.getUsuario(), con.getSenha());
            Statement stmt = conn.createStatement();

            stmt.executeUpdate("INSERT INTO laboratorios (nome_lab) "
                    + "VALUES ('" + lab.laboratorio + "')");

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

    public String alterarLaboratorio(Usuario usu) {
        String retorno = "OK";


        return retorno;
    }

    public String excluirLaboratorio(int codigo) {
        String retorno = "OK";

        try {
            Class.forName(con.getDriver());

            Connection conn = DriverManager.getConnection(con.getStr_conn(), con.getUsuario(), con.getSenha());
            Statement stmt = conn.createStatement();

            String sql = "DELETE FROM laboratorios WHERE CHAVE_LAB = '" + codigo + "'";

            stmt.executeUpdate(sql);

            stmt.close();
            return retorno;
        } catch (ClassNotFoundException ex) {
            return "Erro 03: " + ex.getMessage();
        } catch (SQLException ex) {
            return "Erro 04: " + ex.getMessage();
        }
    }

    public Vector retornaLaboratorios() {

        Vector<String> listaLaboratorios = new Vector<String>();

        try {
            Class.forName(con.getDriver());

            Connection conn = DriverManager.getConnection(con.getStr_conn(), con.getUsuario(), con.getSenha());
            Statement stmt = conn.createStatement();

            String sql = "SELECT * FROM laboratorios";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                listaLaboratorios.add(rs.getString("nome_lab"));
            }

            stmt.close();
            return listaLaboratorios;
        } catch (ClassNotFoundException ex) {
            System.out.println("NÃ£o foi possÃ­vel carregar o driver.");
            ex.printStackTrace();
            return null;
        } catch (SQLException ex) {
            System.out.println("Problema com o SQL");
            ex.printStackTrace();
            return null;
        }
    }

    public DefaultTableModel listaLaboratorios() {
        try {
            Class.forName(con.getDriver());
            String[] colunas = new String[2];
            colunas[0] = "Código";
            colunas[1] = "Laboratório";
            DefaultTableModel modeloLaboratorio = new DefaultTableModel(colunas, 0);
            String[] linhas = new String[4];

            Connection conn = DriverManager.getConnection(con.getStr_conn(), con.getUsuario(), con.getSenha());
            Statement stmt = conn.createStatement();

            String sql = "SELECT * FROM laboratorios";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                linhas[0] = rs.getString("chave_lab");
                linhas[1] = rs.getString("nome_lab");
                modeloLaboratorio.addRow(linhas);
            }

            stmt.close();
            return modeloLaboratorio;
        } catch (ClassNotFoundException ex) {
            System.out.println("NÃ£o foi possÃ­vel carregar o driver.");
            ex.printStackTrace();
            return null;
        } catch (SQLException ex) {
            System.out.println("Problema com o SQL");
            ex.printStackTrace();
            return null;
        }
    }
}
