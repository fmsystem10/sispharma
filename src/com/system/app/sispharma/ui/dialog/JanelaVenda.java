package com.system.app.sispharma.ui.dialog;

import com.system.app.sispharma.entidades.Produto;
import com.system.app.sispharma.entidades.Usuario;
import com.system.app.sispharma.entidades.Vendas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class JanelaVenda extends javax.swing.JDialog {

    private static Produto prod = new Produto();
    private static Vendas venda = new Vendas();
    private static Usuario usuario = new Usuario();
    private static String codigoDeBarra = null;
    private static int codigo = 0;
    private static String descricao = null;
    private static double total = 0.0;
    private static double val = 0.0;
    private static double qtd = 0.0;
    private static int mult = 0;
    private JFrame f = new JFrame();
    private DecimalFormat df = new DecimalFormat("0.00");
    private String[] colunas = new String[4];
    private String[] linhas = new String[4];
    private DefaultTableModel modeloVenda;

    public JanelaVenda(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        total = 0.0;
        nomeProdjLabel.setForeground(Color.red);
        pagamentojPanel.setVisible(false);
        proximaVendajButton.setVisible(false);
        numeroVendajLabel.setText("Venda Nº: " + String.valueOf(venda.retornaNumeroVenda()));
        iniciaModeloVenda();
        telaCleia();
    }

    public JanelaVenda(String cBarra) {
        JanelaVenda.codigoDeBarra = cBarra;
    }

    private void telaCleia() {
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension d = tk.getScreenSize();
        this.setSize(d);
        Window w = new Window(f.getOwner());
        w.setBackground(Color.BLACK);
        w.setSize(d);
        w.setVisible(true);
    }

    private String removeVirgula(String valor) {
        String novoValor = "0.0";
        for (int i = 0; i < valor.length(); i++) {
            novoValor = valor.replace(',', '.');
        }
        return novoValor;
    }

    private int calculaTroco() {
        double desconto = 0.0;
        double tot = Double.parseDouble(removeVirgula(totaljTextField.getText()));
        if (!removeVirgula(descontojTextField.getText()).equals("")) {
            desconto = Double.parseDouble(removeVirgula(descontojTextField.getText()));
        }
        if ((Double.parseDouble(removeVirgula(totaljTextField.getText())) - desconto) > Double.parseDouble(removeVirgula(valorPagojTextField.getText()))) {
            JOptionPane.showMessageDialog(null, "Valor insuficiente para o pagamento", "SISPHARMA - Aviso", 2);
            return 1;
        }
        double valorPago = Double.parseDouble(removeVirgula(valorPagojTextField.getText()));
        double troco = valorPago - (tot - desconto);

        if (String.valueOf(troco).length() > 5) {
            trocojTextField.setText(df.format(troco));
            return 0;
        }
        trocojTextField.setText(df.format(troco));
        return 0;
    }

    private void limparCampos() {
        nomeProdjLabel.setForeground(Color.red);
        nomeProdjLabel.setText("PDV LIVRE");
        subtotaljTextField.setText("");
        totaljTextField.setText("");
        tipoPagamentojComboBox.setSelectedIndex(0);
        descontojTextField.setText("");
        valorPagojTextField.setText("");
        trocojTextField.setText("");
        modeloVenda.setNumRows(0);
        vendajTable.setModel(modeloVenda);
        zerarValores();
    }

    private String recebeProduto(String codigoBarra) {
        Produto prod1 = new Produto();
        if (verificaQtd() == false) {
            prod1 = prod1.retornarProdutoCodigoBarra(codigoBarra);
            //if (prod1.getQtd_produto() == 0) {
            //    JOptionPane.showMessageDialog(null, "Não existe produto no estoque", "Aviso", 2);
            //    codigoBarrajTextField.setText("");
            //    return null;
            //} else {
            codigo = prod1.getCodigo();
            descricao = prod1.getDescricao();
            val = prod1.getValor();
            qtd = 1.0;
            //}
        } else {
            prod1 = prod1.retornarProdutoCodigoBarra(codigoBarra.substring(mult + 1));
            //if (prod1.getQtd_produto() == 0) {
            //    JOptionPane.showMessageDialog(null, "Não existe produto no estoque", "Aviso", 2);
            //    codigoBarrajTextField.setText("");
            //    return null;
            //} else {
            codigo = prod1.getCodigo();
            descricao = prod1.getDescricao();
            val = prod1.getValor() * qtd;
            //}
        }
        adicionaLinha(String.valueOf(codigo), String.valueOf(qtd), descricao, String.valueOf(df.format(prod1.getValor())));
        return "ok";
    }

    private void calculaTotal() {
        subtotaljTextField.setText(df.format(val));
        total = total + val;
        if (String.valueOf(total).length() > 5) {
            totaljTextField.setText(df.format(total));
        } else {
            totaljTextField.setText(df.format(total));
        }
    }

    private boolean verificaQtd() {
        if (codigoBarrajTextField.getText().equals("")) {
            return false;
        }
        for (int i = 0; i < codigoBarrajTextField.getText().length(); i++) {
            if (codigoBarrajTextField.getText().charAt(i) == '*') {
                mult = i;
                try {
                    qtd = Double.parseDouble(removeVirgula(codigoBarrajTextField.getText().substring(0, mult)));
                    return true;
                } catch (Exception e) {
                    codigoBarrajTextField.setText("");
                }
            }
        }
        return false;
    }

    private void zerarValores() {
        total = 0.0;
        val = 0.0;
        qtd = 0.0;
    }

    private void venda() {
        double valorPago = Double.parseDouble(removeVirgula(valorPagojTextField.getText()));
        double troco = Double.parseDouble(removeVirgula(trocojTextField.getText()));

        venda.inserirVenda(Integer.parseInt(numeroVendajLabel.getText().substring(10)), usuario.retornaChaveUsuario(usuario.getSenha()), Double.parseDouble(removeVirgula(totaljTextField.getText())), Double.parseDouble(removeVirgula(descontojTextField.getText())), valorPago - troco, tipoPagamentojComboBox.getSelectedItem().toString());

        for (int i = 0; i < vendajTable.getRowCount(); i++) {
            venda.inserirItensVenda(Integer.parseInt(numeroVendajLabel.getText().substring(10)), Integer.parseInt(vendajTable.getValueAt(i, 0).toString()), Double.parseDouble(vendajTable.getValueAt(i, 1).toString()));
        }
    }

    public void inserirProdutoVenda(String codBarra) {
        String p = recebeProduto(codBarra);
        if (!p.equals(null)) {
            nomeProdjLabel.setForeground(Color.black);
            nomeProdjLabel.setText(descricao);
            codigoBarrajTextField.setText("");
            calculaTotal();
        }
    }

    private void iniciaModeloVenda() {
        colunas[0] = "Código";
        colunas[1] = "Quantidade";
        colunas[2] = "Descrição";
        colunas[3] = "Preço Unitário";

        modeloVenda = new DefaultTableModel(colunas, 0);
        vendajTable.setModel(modeloVenda);
        vendajTable.getColumnModel().getColumn(0).setPreferredWidth(3);
        vendajTable.getColumnModel().getColumn(1).setPreferredWidth(5);
        vendajTable.getColumnModel().getColumn(2).setPreferredWidth(320);
        vendajTable.getColumnModel().getColumn(3).setPreferredWidth(10);

        vendajTable.setShowGrid(true);
    }

    private void adicionaLinha(String cod, String qtd, String desc, String val) {
        try {
            linhas[0] = cod;
            linhas[1] = qtd;
            linhas[2] = desc;
            linhas[3] = val;

            modeloVenda.addRow(linhas);

        } catch (Exception e) {
            System.out.print(e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        vendajTable = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        codigoBarrajTextField = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        nomeProdjLabel = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        totaljTextField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        subtotaljTextField = new javax.swing.JTextField();
        fecharVendajButton = new javax.swing.JButton();
        pagamentojPanel = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        trocojTextField = new javax.swing.JTextField();
        tipoPagamentojComboBox = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        descontojTextField = new javax.swing.JTextField();
        valorPagojTextField = new javax.swing.JTextField();
        proximaVendajButton = new javax.swing.JButton();
        numeroVendajLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SISPHARMA - Venda");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        vendajTable.setFont(new java.awt.Font("Times New Roman", 0, 24));
        vendajTable.setFocusable(false);
        vendajTable.setRowHeight(25);
        vendajTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        vendajTable.getTableHeader().setResizingAllowed(false);
        vendajTable.getTableHeader().setReorderingAllowed(false);
        vendajTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                vendajTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(vendajTable);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 644, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 527, Short.MAX_VALUE)
        );

        jPanel4.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        codigoBarrajTextField.setFont(new java.awt.Font("Times New Roman", 0, 30));
        codigoBarrajTextField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        codigoBarrajTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                codigoBarrajTextFieldKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(codigoBarrajTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 642, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(codigoBarrajTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE)
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        nomeProdjLabel.setFont(new java.awt.Font("Times New Roman", 3, 52));
        nomeProdjLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        nomeProdjLabel.setText("PDV LIVRE");
        nomeProdjLabel.setFocusable(false);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nomeProdjLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 993, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(nomeProdjLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
        );

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jPanel7.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel4.setFont(new java.awt.Font("Times New Roman", 1, 48));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("TOTAL");

        totaljTextField.setBackground(new java.awt.Color(102, 255, 102));
        totaljTextField.setEditable(false);
        totaljTextField.setFont(new java.awt.Font("Times New Roman", 1, 60));
        totaljTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        totaljTextField.setText("0,00");
        totaljTextField.setFocusable(false);

        jLabel6.setFont(new java.awt.Font("Times New Roman", 1, 36));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel6.setText("R$");

        jLabel15.setFont(new java.awt.Font("Times New Roman", 0, 24));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("SUBTOTAL");

        jLabel16.setFont(new java.awt.Font("Times New Roman", 0, 24));
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel16.setText("R$");

        subtotaljTextField.setEditable(false);
        subtotaljTextField.setFont(new java.awt.Font("Times New Roman", 0, 36));
        subtotaljTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        subtotaljTextField.setText("0,00");
        subtotaljTextField.setFocusable(false);

        fecharVendajButton.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        fecharVendajButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/system/app/sispharma/imagens/money.png"))); // NOI18N
        fecharVendajButton.setText("EFETUAR PAGAMENTO - F9");
        fecharVendajButton.setFocusable(false);
        fecharVendajButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fecharVendajButtonMouseClicked(evt);
            }
        });
        fecharVendajButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fecharVendajButtonActionPerformed(evt);
            }
        });
        fecharVendajButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                fecharVendajButtonKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addComponent(jLabel16))
                            .addGroup(jPanel7Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel6)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(totaljTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                            .addComponent(subtotaljTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(fecharVendajButton, javax.swing.GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(subtotaljTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(totaljTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fecharVendajButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        pagamentojPanel.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pagamentojPanel.setFocusable(false);

        jLabel9.setFont(new java.awt.Font("Times New Roman", 0, 24));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel9.setText("R$");

        jLabel10.setFont(new java.awt.Font("Times New Roman", 0, 18));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("VALOR PAGO");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 1, 24));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("R$");

        trocojTextField.setBackground(new java.awt.Color(255, 153, 153));
        trocojTextField.setEditable(false);
        trocojTextField.setFont(new java.awt.Font("Times New Roman", 1, 30));
        trocojTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        trocojTextField.setText("0,00");
        trocojTextField.setFocusable(false);

        tipoPagamentojComboBox.setFont(new java.awt.Font("Times New Roman", 1, 14));
        tipoPagamentojComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "DINHEIRO", "CARTÃO" }));
        tipoPagamentojComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tipoPagamentojComboBoxItemStateChanged(evt);
            }
        });
        tipoPagamentojComboBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tipoPagamentojComboBoxKeyPressed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 18));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel12.setText("TROCO");

        jLabel13.setFont(new java.awt.Font("Times New Roman", 0, 24));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel13.setText("R$");

        jLabel14.setFont(new java.awt.Font("Times New Roman", 0, 18));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("DESCONTO");

        descontojTextField.setFont(new java.awt.Font("Times New Roman", 1, 30));
        descontojTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        descontojTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                descontojTextFieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                descontojTextFieldKeyReleased(evt);
            }
        });

        valorPagojTextField.setFont(new java.awt.Font("Times New Roman", 1, 30));
        valorPagojTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        valorPagojTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                valorPagojTextFieldKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout pagamentojPanelLayout = new javax.swing.GroupLayout(pagamentojPanel);
        pagamentojPanel.setLayout(pagamentojPanelLayout);
        pagamentojPanelLayout.setHorizontalGroup(
            pagamentojPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pagamentojPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pagamentojPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tipoPagamentojComboBox, 0, 307, Short.MAX_VALUE)
                    .addGroup(pagamentojPanelLayout.createSequentialGroup()
                        .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(descontojTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pagamentojPanelLayout.createSequentialGroup()
                        .addGroup(pagamentojPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pagamentojPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pagamentojPanelLayout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(valorPagojTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pagamentojPanelLayout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(trocojTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        pagamentojPanelLayout.setVerticalGroup(
            pagamentojPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pagamentojPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tipoPagamentojComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pagamentojPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(descontojTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pagamentojPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(valorPagojTextField, 0, 0, Short.MAX_VALUE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10))
                .addGap(34, 34, 34)
                .addGroup(pagamentojPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(trocojTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addContainerGap())
        );

        pagamentojPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {descontojTextField, trocojTextField, valorPagojTextField});

        proximaVendajButton.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        proximaVendajButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/system/app/sispharma/imagens/tick.png"))); // NOI18N
        proximaVendajButton.setText("ENCERRAR VENDA");
        proximaVendajButton.setFocusCycleRoot(true);
        proximaVendajButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                proximaVendajButtonMouseClicked(evt);
            }
        });
        proximaVendajButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                proximaVendajButtonActionPerformed(evt);
            }
        });
        proximaVendajButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                proximaVendajButtonKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(proximaVendajButton, javax.swing.GroupLayout.DEFAULT_SIZE, 333, Short.MAX_VALUE)
                    .addComponent(pagamentojPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pagamentojPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(proximaVendajButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        numeroVendajLabel.setFont(new java.awt.Font("Times New Roman", 2, 18));
        numeroVendajLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        numeroVendajLabel.setText("n");

        jLabel1.setFont(new java.awt.Font("Times New Roman", 2, 18));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("F2 procurar produto / F9 efetuar pagamento / Clique no produto para excluir");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(numeroVendajLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 791, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(numeroVendajLabel))
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void fecharVendajButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fecharVendajButtonActionPerformed

        if (vendajTable.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Informe o produto!", "SISPHARMA - Aviso", 2);
            return;
        }
        pagamentojPanel.setVisible(true);
        proximaVendajButton.setVisible(true);
        fecharVendajButton.setVisible(false);
        //vendajTable.setEnabled(false);
        codigoBarrajTextField.setEnabled(false);
        proximaVendajButton.setEnabled(false);
        codigoBarrajTextField.transferFocus();

    }//GEN-LAST:event_fecharVendajButtonActionPerformed

    private void tipoPagamentojComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tipoPagamentojComboBoxItemStateChanged

        descontojTextField.setFocusable(true);

    }//GEN-LAST:event_tipoPagamentojComboBoxItemStateChanged

    private void fecharVendajButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fecharVendajButtonMouseClicked

        if (vendajTable.getRowCount() == 0) {
            JOptionPane.showMessageDialog(null, "Informe o produto!", "SISPHARMA - Aviso", 2);
            return;
        }

    }//GEN-LAST:event_fecharVendajButtonMouseClicked

    private void proximaVendajButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_proximaVendajButtonMouseClicked

        pagamentojPanel.setVisible(false);
        proximaVendajButton.setVisible(false);
        fecharVendajButton.setVisible(true);
        vendajTable.setEnabled(true);
        codigoBarrajTextField.setEnabled(true);
        proximaVendajButton.transferFocus();
        vendajTable.transferFocus();
        venda();
        limparCampos();
        numeroVendajLabel.setText("Venda Nº: " + String.valueOf(venda.retornaNumeroVenda()));

    }//GEN-LAST:event_proximaVendajButtonMouseClicked

    private void proximaVendajButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_proximaVendajButtonActionPerformed

        pagamentojPanel.setVisible(false);
        proximaVendajButton.setVisible(false);
        fecharVendajButton.setVisible(true);
        vendajTable.setEnabled(true);
        codigoBarrajTextField.setEnabled(true);
        proximaVendajButton.transferFocus();
        vendajTable.transferFocus();
        venda();
        limparCampos();
        numeroVendajLabel.setText("Venda Nº: " + String.valueOf(venda.retornaNumeroVenda()));

    }//GEN-LAST:event_proximaVendajButtonActionPerformed

    private void codigoBarrajTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_codigoBarrajTextFieldKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_F9) {
            if (vendajTable.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Informe o produto!", "SISPHARMA - Aviso", 2);
                return;
            }
            pagamentojPanel.setVisible(true);
            proximaVendajButton.setVisible(true);
            fecharVendajButton.setVisible(false);
            //vendajTable.setEnabled(false);
            //codigoBarrajTextField.setEnabled(false);
            proximaVendajButton.setEnabled(false);
            codigoBarrajTextField.transferFocus();
        }

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (codigoBarrajTextField.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Informe o produto!", "SISPHARMA - Aviso", 2);
                return;
            }
            inserirProdutoVenda(codigoBarrajTextField.getText());
        }

        if (evt.getKeyCode() == KeyEvent.VK_F2) {
            JanelaPesquisarProduto dialog = new JanelaPesquisarProduto(new javax.swing.JFrame(), true);
            dialog.tipoJanela(0);
            dialog.setVisible(true);
            codigoBarrajTextField.setText(codigoBarrajTextField.getText() + codigoDeBarra);
            if (codigoBarrajTextField.getText().equals("null") || codigoBarrajTextField.getText().equals("")) {
                codigoBarrajTextField.setText("");
                return;
            }
            codigoDeBarra = "";
            inserirProdutoVenda(codigoBarrajTextField.getText());
        }

    }//GEN-LAST:event_codigoBarrajTextFieldKeyPressed

    private void descontojTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_descontojTextFieldKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (!descontojTextField.getText().equals("")) {
                if (Double.parseDouble(removeVirgula(descontojTextField.getText())) > Double.parseDouble(removeVirgula(totaljTextField.getText()))) {
                    JOptionPane.showMessageDialog(null, "Desconto acima do valor da compra!", "SISPHARMA - Aviso", 2);
                    return;
                }
            }
            if (!valorPagojTextField.getText().equals("")) {
                calculaTroco();
            }
            descontojTextField.transferFocus();
        }

    }//GEN-LAST:event_descontojTextFieldKeyPressed

    private void valorPagojTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_valorPagojTextFieldKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (valorPagojTextField.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Informe o valor pago!", "SISPHARMA - Aviso", 2);
                return;
            }

            if (calculaTroco() == 0) {
                proximaVendajButton.setEnabled(true);
                valorPagojTextField.transferFocus();
            }
        }

    }//GEN-LAST:event_valorPagojTextFieldKeyPressed

    private void proximaVendajButtonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_proximaVendajButtonKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            pagamentojPanel.setVisible(false);
            proximaVendajButton.setVisible(false);
            fecharVendajButton.setVisible(true);
            vendajTable.setEnabled(true);
            codigoBarrajTextField.setEnabled(true);
            proximaVendajButton.transferFocus();
            vendajTable.transferFocus();
            venda();
            limparCampos();
            numeroVendajLabel.setText("Venda Nº: " + String.valueOf(venda.retornaNumeroVenda()));
        }

    }//GEN-LAST:event_proximaVendajButtonKeyPressed

    private void tipoPagamentojComboBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tipoPagamentojComboBoxKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            tipoPagamentojComboBox.transferFocus();
        }

    }//GEN-LAST:event_tipoPagamentojComboBoxKeyPressed

    private void fecharVendajButtonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_fecharVendajButtonKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            pagamentojPanel.setVisible(true);
            proximaVendajButton.setVisible(true);
            fecharVendajButton.setVisible(false);
            //vendajTable.setEnabled(false);
            codigoBarrajTextField.setEnabled(false);
            proximaVendajButton.setEnabled(false);
            codigoBarrajTextField.transferFocus();
            fecharVendajButton.transferFocus();
        }

    }//GEN-LAST:event_fecharVendajButtonKeyPressed

    private void descontojTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_descontojTextFieldKeyReleased
    }//GEN-LAST:event_descontojTextFieldKeyReleased

    private void vendajTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vendajTableMouseClicked

        int res = JOptionPane.showConfirmDialog(null, "Deseja excluir produto da venda?", "SISPHARMA - Aviso", 2);
        if (res == 0) {
            subtotaljTextField.setText("0,00");
            double valor = Double.parseDouble(removeVirgula(vendajTable.getValueAt(vendajTable.getSelectedRow(), 3).toString()));
            double subValor = (Double.parseDouble(vendajTable.getValueAt(vendajTable.getSelectedRow(), 1).toString()) * valor);
            double totalMod = Double.parseDouble(removeVirgula(totaljTextField.getText()));
            totaljTextField.setText(df.format(totalMod - subValor));
            total = totalMod - subValor;
            modeloVenda.removeRow(vendajTable.getSelectedRow());
        }

    }//GEN-LAST:event_vendajTableMouseClicked
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField codigoBarrajTextField;
    private javax.swing.JTextField descontojTextField;
    private javax.swing.JButton fecharVendajButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel nomeProdjLabel;
    private javax.swing.JLabel numeroVendajLabel;
    private javax.swing.JPanel pagamentojPanel;
    private javax.swing.JButton proximaVendajButton;
    private javax.swing.JTextField subtotaljTextField;
    private javax.swing.JComboBox tipoPagamentojComboBox;
    private javax.swing.JTextField totaljTextField;
    private javax.swing.JTextField trocojTextField;
    private javax.swing.JTextField valorPagojTextField;
    private javax.swing.JTable vendajTable;
    // End of variables declaration//GEN-END:variables
}
