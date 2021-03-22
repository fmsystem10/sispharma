package com.system.app.sispharma.entidades;

import com.system.app.sispharma.bd.Conexao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableModel;
import java.util.Date;

public class Vendas {

    Conexao con = new Conexao();
    private DecimalFormat df = new DecimalFormat("0.00");
    private static int codVenda = 0;

    public static int getCodVenda() {
        return codVenda;
    }

    public static void setCodVenda(int codVenda) {
        Vendas.codVenda = codVenda;
    }

    public String data() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public String inserirVenda(int chave, int usuario, double valor, double desconto, double valorPago, String tipoVenda) {
        String retorno = "OK";

        try {
            Class.forName(con.getDriver());

            Connection conn = DriverManager.getConnection(con.getStr_conn(), con.getUsuario(), con.getSenha());
            Statement stmt = conn.createStatement();

            stmt.executeUpdate("INSERT INTO vendas (chave_vendas, chave_usuarios, data, valor, desconto, valor_pago, tipo_venda)"
                    + "VALUES ('" + chave + "', '" + usuario + "','" + data() + "',"
                    + "'" + valor + "', '" + desconto + "', '" + valorPago + "', '" + tipoVenda + "')");

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

    public String inserirItensVenda(int chaveVenda, int codigo, double qtd_produto) {
        String retorno = "OK";
        double qtd = 0.0;

        try {
            Class.forName(con.getDriver());

            Connection conn = DriverManager.getConnection(con.getStr_conn(), con.getUsuario(), con.getSenha());
            Statement stmt = conn.createStatement();
            String sql1 = "SELECT qtd_produtos FROM produtos WHERE codigo = '" + codigo + "'";
            ResultSet rs = stmt.executeQuery(sql1);

            while (rs.next()) {
                qtd = rs.getDouble("qtd_produtos");
            }

            qtd = qtd - qtd_produto;

            stmt.executeUpdate("INSERT INTO itens_venda (chave_vendas, chave_produtos, qtd_itens)"
                    + "VALUES ('" + chaveVenda + "', '" + codigo + "', '" + qtd_produto + "')");

            String sql = "UPDATE produtos SET qtd_produtos = '" + qtd + "'"
                    + " WHERE codigo = '" + codigo + "'";

            stmt.executeUpdate(sql);

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

    public DefaultTableModel listaVendas(String data) {
        try {
            Class.forName(con.getDriver());

            String[] colunas = new String[7];
            colunas[0] = "Código";
            colunas[1] = "Data";
            colunas[2] = "Valor";
            colunas[3] = "Desconto";
            colunas[4] = "Valor Pago";
            colunas[5] = "Tipo de Venda";
            colunas[6] = "Usuário";
            DefaultTableModel modeloVenda = new DefaultTableModel(colunas, 0);

            String[] linhas = new String[7];

            Connection conn = DriverManager.getConnection(con.getStr_conn(), con.getUsuario(), con.getSenha());
            Statement stmt = conn.createStatement();

            String sql = "SELECT v.*, u.nome FROM vendas v, usuarios u WHERE data = '" + data + "' and v.chave_usuarios = u.chave_usuarios";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                linhas[0] = rs.getString("chave_vendas");
                linhas[1] = rs.getString("data");
                linhas[2] = df.format(Double.parseDouble(rs.getString("valor")));
                linhas[3] = df.format(Double.parseDouble(rs.getString("desconto")));
                linhas[4] = df.format(Double.parseDouble(rs.getString("valor_pago")));
                linhas[5] = rs.getString("tipo_venda");
                linhas[6] = rs.getString("nome").trim();
                modeloVenda.addRow(linhas);
            }

            stmt.close();
            return modeloVenda;
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

    public DefaultTableModel listaItensVendas(int codigo) {
        try {
            Class.forName(con.getDriver());

            String[] colunas = new String[4];
            colunas[0] = "Quantidade";
            colunas[1] = "Produto";
            colunas[2] = "Preço Unitário";
            colunas[3] = "SubTotal";
            DefaultTableModel modeloItensVenda = new DefaultTableModel(colunas, 0);

            String[] linhas = new String[4];

            Connection conn = DriverManager.getConnection(con.getStr_conn(), con.getUsuario(), con.getSenha());
            Statement stmt = conn.createStatement();

            String sql = "SELECT i.*, p.* FROM itens_venda i, produtos p WHERE i.chave_vendas = '" + codigo + "' and i.chave_produtos = p.codigo";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                linhas[0] = rs.getString("qtd_itens");
                linhas[1] = rs.getString("descricao");
                linhas[2] = df.format(Double.parseDouble(rs.getString("valor")));
                linhas[3] = df.format(Double.parseDouble(rs.getString("valor")) * Double.parseDouble(rs.getString("qtd_itens")));
                modeloItensVenda.addRow(linhas);
            }

            stmt.close();
            return modeloItensVenda;
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

    public int retornaNumeroVenda() {
        try {
            int cod = 0;
            Class.forName(con.getDriver());

            Connection conn = DriverManager.getConnection(con.getStr_conn(), con.getUsuario(), con.getSenha());
            Statement stmt = conn.createStatement();

            String sql1 = "SELECT chave_vendas FROM vendas";
            ResultSet rs = stmt.executeQuery(sql1);

            while (rs.next()) {
                cod = rs.getInt("chave_vendas");
            }

            if (cod == 0) {
                stmt.close();
                return 1;
            } else {
                stmt.close();
                return cod + 1;
            }

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
