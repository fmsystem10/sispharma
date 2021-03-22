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
import java.util.Vector;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

public class Produto {

    Conexao con = new Conexao();
    private int codigo = 0;
    private String codigo_barra = null;
    private String descricao = null;
    private int laboratorio = 0;
    private String unidade = null;
    private double qtd_produto = 0.0;
    private double valor = 0.0;
    private String validade = null;
    private double qtd_minima = 0.0;
    private DecimalFormat df = new DecimalFormat("0.00");

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getCodigo_barra() {
        return codigo_barra;
    }

    public void setCodigo_barra(String codigo_barra) {
        this.codigo_barra = codigo_barra;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getLaboratorio() {
        return laboratorio;
    }

    public void setLaboratorio(int laboratorio) {
        this.laboratorio = laboratorio;
    }

    public double getQtd_minima() {
        return qtd_minima;
    }

    public void setQtd_minima(double qtd_minima) {
        this.qtd_minima = qtd_minima;
    }

    public double getQtd_produto() {
        return qtd_produto;
    }

    public void setQtd_produto(double qtd_produto) {
        this.qtd_produto = qtd_produto;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getData() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }

    public String inserirProduto(int usuario, int codigo, String codigo_barra, String descricao, int laboratorio, String unidade, double valor, double qtd_produto, String validade, double qtd_minima) {

        String retorno = "OK";
        if (codigo_barra.equals("")) {
            codigo_barra = String.valueOf(codigo);
        }

        try {
            Class.forName(con.getDriver());

            Connection conn = DriverManager.getConnection(con.getStr_conn(), con.getUsuario(), con.getSenha());
            Statement stmt = conn.createStatement();

            String sql = "SELECT * FROM produtos WHERE codigo = '" + codigo + "'";
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.first() == true) {
                retorno = alterarProduto(codigo, codigo_barra, descricao, laboratorio, unidade, valor, qtd_produto, validade, qtd_minima);
                return retorno;
            } else {
                stmt.executeUpdate("INSERT INTO produtos (codigo, codigo_barra, descricao, laboratorio,"
                        + "unidade, qtd_produtos, valor, validade, qtd_minima)"
                        + "VALUES ('" + codigo + "', '" + codigo_barra + "','" + descricao + "',"
                        + "'" + laboratorio + "', '" + unidade + "', '" + qtd_produto + "',"
                        + "'" + valor + "', '" + validade + "', '" + qtd_minima + "')");

                stmt.executeUpdate("INSERT INTO entradas (chave_produtos, chave_usuarios, data, qtd_entrada)"
                        + "VALUES ('" + codigo + "', '" + usuario + "','" + getData() + "',"
                        + "'" + qtd_produto + "')");
            }

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

    public String alterarProduto(int codigo, String codigo_barra, String descricao, int laboratorio, String unidade, double valor, double qtd_produto, String validade, double qtd_minima) {
        String retorno = "OK";

        try {
            Class.forName(con.getDriver());

            Connection conn = DriverManager.getConnection(con.getStr_conn(), con.getUsuario(), con.getSenha());
            Statement stmt = conn.createStatement();

            String sql = "UPDATE produtos SET codigo_barra = '" + codigo_barra + "', descricao = '" + descricao + "', laboratorio = '" + laboratorio + "',"
                    + " unidade = '" + unidade + "', valor = '" + valor + "',"
                    + " validade = '" + validade + "', qtd_minima = '" + qtd_minima + "' "
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

    public String excluirProduto(int codigo) {
        String retorno = "OK";

        try {
            Class.forName(con.getDriver());

            Connection conn = DriverManager.getConnection(con.getStr_conn(), con.getUsuario(), con.getSenha());
            Statement stmt = conn.createStatement();

            String sql = "DELETE FROM produtos WHERE CODIGO = '" + codigo + "'";

            stmt.executeUpdate(sql);

            stmt.close();
            return retorno;
        } catch (ClassNotFoundException ex) {
            retorno = "Erro 01: " + ex.getMessage();
            return retorno;
        } catch (SQLException ex) {
            retorno = "Erro 02: " + ex.getMessage();
            return retorno;
        }
    }

    public Produto retornarProdutoCodigo(int codigo) {
        try {
            Class.forName(con.getDriver());

            Connection conn = DriverManager.getConnection(con.getStr_conn(), con.getUsuario(), con.getSenha());
            Statement stmt = conn.createStatement();

            String sql = "SELECT * FROM produtos WHERE codigo = '" + codigo + "'";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                this.codigo = rs.getInt("codigo");
                this.codigo_barra = rs.getString("codigo_barra");
                this.descricao = rs.getString("descricao");
                this.laboratorio = rs.getInt("laboratorio");
                this.unidade = rs.getString("unidade");
                this.qtd_produto = rs.getDouble("qtd_produtos");
                this.valor = rs.getDouble("valor");
                this.validade = rs.getString("validade");
                this.qtd_minima = rs.getDouble("qtd_minima");
            }

            stmt.close();
            return this;
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

    public Produto retornarProdutoCodigoBarra(String codigoBarra) {
        try {
            Class.forName(con.getDriver());

            Connection conn = DriverManager.getConnection(con.getStr_conn(), con.getUsuario(), con.getSenha());
            Statement stmt = conn.createStatement();

            String sql = "SELECT * FROM produtos WHERE codigo_barra = '" + codigoBarra + "'";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                this.codigo = rs.getInt("codigo");
                this.codigo_barra = rs.getString("codigo_barra");
                this.descricao = rs.getString("descricao");
                this.laboratorio = rs.getInt("laboratorio");
                this.unidade = rs.getString("unidade");
                this.qtd_produto = rs.getDouble("qtd_produtos");
                this.valor = rs.getDouble("valor");
                this.validade = rs.getString("validade");
                this.qtd_minima = rs.getDouble("qtd_minima");
            }

            stmt.close();
            return this;
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

    public DefaultTableModel pesquisaProduto(String nome) {
        try {
            Class.forName(con.getDriver());
            String[] colunas = new String[4];
            colunas[0] = "Código";
            colunas[1] = "Descrição";
            colunas[2] = "Quantidade";
            colunas[3] = "Preço";
            DefaultTableModel modeloProduto = new DefaultTableModel(colunas, 0);
            String[] linhas = new String[4];

            Connection conn = DriverManager.getConnection(con.getStr_conn(), con.getUsuario(), con.getSenha());
            Statement stmt = conn.createStatement();

            String descr = nome + "%";
            String sql = "SELECT * FROM produtos WHERE descricao LIKE '" + descr + "'";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                linhas[0] = rs.getString("codigo");
                linhas[1] = rs.getString("descricao");
                linhas[2] = rs.getString("qtd_produtos");
                linhas[3] = df.format(Double.parseDouble(rs.getString("valor")));
                modeloProduto.addRow(linhas);
            }

            stmt.close();
            return modeloProduto;
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

    public Vector listaProduto() {
        try {
            Vector<String> prod = new Vector<String>();
            Class.forName(con.getDriver());

            Connection conn = DriverManager.getConnection(con.getStr_conn(), con.getUsuario(), con.getSenha());
            Statement stmt = conn.createStatement();

            String sql = "SELECT * FROM produtos";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                prod.add(rs.getString("codigo") + " >   " + rs.getString("descricao") + "     ::R$ " + String.valueOf(rs.getDouble("valor") + "\n"));
            }

            stmt.close();
            return prod;
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

    public int retornaCodigo() {
        try {
            int cod = 0;
            Class.forName(con.getDriver());

            Connection conn = DriverManager.getConnection(con.getStr_conn(), con.getUsuario(), con.getSenha());
            Statement stmt = conn.createStatement();

            String sql1 = "SELECT codigo FROM produtos";
            ResultSet rs = stmt.executeQuery(sql1);

            while (rs.next()) {
                cod = rs.getInt("codigo");
            }

            if (cod == 0) {
                stmt.close();
                return 101;
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

    public String entradaProduto(int codigo, int usuario, String codigo_barra, String descricao, int laboratorio, String unidade, double valor, double qtd_produto, String validade, double qtd_minima) {
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

            qtd = qtd + qtd_produto;

            stmt.executeUpdate("INSERT INTO entradas (chave_produtos, chave_usuarios, data, qtd_entrada)"
                    + "VALUES ('" + codigo + "', '" + usuario + "','" + getData() + "',"
                    + "'" + qtd_produto + "')");

            String sql = "UPDATE produtos SET codigo_barra = '" + codigo_barra + "', descricao = '" + descricao + "', laboratorio = '" + laboratorio + "',"
                    + " unidade = '" + unidade + "',  valor = '" + valor + "', qtd_produtos = '" + qtd + "',"
                    + " validade = '" + validade + "', qtd_minima = '" + qtd_minima + "' "
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

    public DefaultTableModel listaProdutos() {
        try {
            Class.forName(con.getDriver());
            String[] colunas = new String[4];
            colunas[0] = "Código";
            colunas[1] = "Descrição";
            colunas[2] = "Quantidade";
            colunas[3] = "Preço";
            DefaultTableModel modeloProduto = new DefaultTableModel(colunas, 0);
            String[] linhas = new String[4];

            Connection conn = DriverManager.getConnection(con.getStr_conn(), con.getUsuario(), con.getSenha());
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM produtos order by descricao";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                linhas[0] = rs.getString("codigo");
                linhas[1] = rs.getString("descricao");
                linhas[2] = rs.getString("qtd_produtos");
                linhas[3] = df.format(Double.parseDouble(rs.getString("valor")));
                modeloProduto.addRow(linhas);
            }

            stmt.close();
            return modeloProduto;
        } catch (ClassNotFoundException ex) {
            System.out.println("Nao foi possivel carregar o driver.");
            ex.printStackTrace();
            return null;
        } catch (SQLException ex) {
            System.out.println("Problema com o SQL");
            ex.printStackTrace();
            return null;
        }
    }
}
