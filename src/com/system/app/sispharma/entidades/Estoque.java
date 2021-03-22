package com.system.app.sispharma.entidades;

import com.system.app.sispharma.bd.Conexao;
import java.text.DecimalFormat;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Estoque {

    Conexao con = new Conexao();
    private DecimalFormat df = new DecimalFormat("0.00");

    public DefaultTableModel listaEstoqueBsixo() {
        try {
            Class.forName(con.getDriver());

            String[] colunas = new String[4];
            colunas[0] = "Código";
            colunas[1] = "Quantidade";
            colunas[2] = "Descrição";
            colunas[3] = "Valor";

            DefaultTableModel modeloEstoque = new DefaultTableModel(colunas, 0);

            String[] linhas = new String[colunas.length];

            Connection conn = DriverManager.getConnection(con.getStr_conn(), con.getUsuario(), con.getSenha());
            Statement stmt = conn.createStatement();

            String sql = "SELECT * FROM produtos WHERE qtd_produtos < qtd_minima";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                linhas[0] = rs.getString("codigo");
                linhas[1] = rs.getString("qtd_produtos");
                linhas[2] = rs.getString("descricao");
                linhas[3] = df.format(Double.parseDouble(rs.getString("valor")));
                modeloEstoque.addRow(linhas);
            }

            stmt.close();
            return modeloEstoque;
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
