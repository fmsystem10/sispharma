package com.system.app.sispharma.entidades;

import com.system.app.sispharma.bd.Conexao;
import java.text.DecimalFormat;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Entradas {

    Conexao con = new Conexao();
    private DecimalFormat df = new DecimalFormat("0.00");

    public DefaultTableModel listaEntradas(String data) {
        try {
            Class.forName(con.getDriver());

            String[] colunas = new String[6];
            colunas[0] = "Código";
            colunas[1] = "Quantidade";
            colunas[2] = "Descrição";
            colunas[3] = "Valor";
            colunas[4] = "Sub Total";
            colunas[5] = "Usuário";
            DefaultTableModel modeloEntrada = new DefaultTableModel(colunas, 0);

            String[] linhas = new String[colunas.length];

            Connection conn = DriverManager.getConnection(con.getStr_conn(), con.getUsuario(), con.getSenha());
            Statement stmt = conn.createStatement();

            String sql = "SELECT p.*, e.*, u.nome FROM produtos p, entradas e, usuarios u WHERE data = '" + data + "' and e.chave_produtos = p.codigo and e.chave_usuarios = u.chave_usuarios";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                linhas[0] = rs.getString("codigo");
                linhas[1] = rs.getString("qtd_entrada");
                linhas[2] = rs.getString("descricao");
                linhas[3] = df.format(Double.parseDouble(rs.getString("valor")));
                linhas[4] = df.format(Double.parseDouble(rs.getString("qtd_entrada")) * Double.parseDouble(rs.getString("valor")));
                linhas[5] = rs.getString("nome").trim();
                modeloEntrada.addRow(linhas);
            }

            stmt.close();
            return modeloEntrada;
        } catch (ClassNotFoundException ex) {
            System.out.println("Não foi possivel carregar o driver.");
            ex.printStackTrace();
            return null;
        } catch (SQLException ex) {
            System.out.println("Problema com o SQL");
            ex.printStackTrace();
            return null;
        }
    }
}
