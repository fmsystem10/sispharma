package com.system.app.sispharma.entidades;

import com.system.app.sispharma.bd.Conexao;
import java.text.DecimalFormat;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Validade {

    Conexao con = new Conexao();
    private DecimalFormat df = new DecimalFormat("0.00");

    public String data() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public DefaultTableModel listaValidade() {
        try {
            Class.forName(con.getDriver());

            String[] colunas = new String[5];
            colunas[0] = "Código";
            colunas[1] = "Quantidade";
            colunas[2] = "Descrição";
            colunas[3] = "Valor";
            colunas[4] = "Validade";

            DefaultTableModel modeloValidade = new DefaultTableModel(colunas, 0);

            String[] linhas = new String[colunas.length];

            Connection conn = DriverManager.getConnection(con.getStr_conn(), con.getUsuario(), con.getSenha());
            Statement stmt = conn.createStatement();

            String sql = "SELECT * FROM produtos";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                int anoAtual = Integer.parseInt(data().substring(6));
                int mesAtual = Integer.parseInt(data().substring(3, 5));
                int anoBanco = Integer.parseInt(rs.getString("validade").substring(6));
                int mesBanco = Integer.parseInt(rs.getString("validade").substring(3, 5));
                if (anoAtual >= anoBanco) {
                    if (mesAtual >= (mesBanco - 1)) {
                        linhas[0] = rs.getString("codigo");
                        linhas[1] = rs.getString("qtd_produtos");
                        linhas[2] = rs.getString("descricao");
                        linhas[3] = df.format(Double.parseDouble(rs.getString("valor")));
                        linhas[4] = rs.getString("validade");

                        modeloValidade.addRow(linhas);
                    }
                }
            }

            stmt.close();
            return modeloValidade;
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
