package com.system.app.sispharma.ui.dialog;

import com.system.app.sispharma.entidades.Entradas;
import com.system.app.sispharma.entidades.Estoque;
import com.system.app.sispharma.entidades.Validade;
import com.system.app.sispharma.entidades.Vendas;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;

public class JanelaRelatorio extends javax.swing.JDialog {

    Vendas vendas = new Vendas();
    Entradas entradas = new Entradas();
    Estoque estoque = new Estoque();
    Validade validade = new Validade();
    DecimalFormat df = new DecimalFormat("0.00");

    public JanelaRelatorio(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        dataVendasjFormattedTextField.setText(vendas.data());
        setLocationRelativeTo(null);
    }

    private String removeVirgula(String valor) {
        String novoValor = "0.0";
        for (int i = 0; i < valor.length(); i++) {
            novoValor = valor.replace(',', '.');
        }
        return novoValor;
    }

    private void carregaVendas() {
        try {
            vendasjTable.setModel(vendas.listaVendas(dataVendasjFormattedTextField.getText()));
            vendasjTable.updateUI();
            vendasjTable.setShowGrid(true);
            vendasjTable.getColumnModel().getColumn(0).setPreferredWidth(3);
            vendasjTable.getColumnModel().getColumn(1).setPreferredWidth(30);
            vendasjTable.getColumnModel().getColumn(2).setPreferredWidth(30);
            vendasjTable.getColumnModel().getColumn(3).setPreferredWidth(30);
            vendasjTable.getColumnModel().getColumn(4).setPreferredWidth(30);
            vendasjTable.getColumnModel().getColumn(5).setPreferredWidth(30);
            vendasjTable.getColumnModel().getColumn(6).setPreferredWidth(150);
            if (vendasjTable.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Nenhuma VENDA nessa data", "SISPHARMA - Mensagem", 1);
            }
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    private void calculaTotalGeralVendas() {
        int qtdVendas = 0;
        double valorDinheiro = 0.0;
        double totalDinheiro = 0.0;
        double valorCartao = 0.0;
        double totalCartao = 0.0;

        qtdVendas = vendasjTable.getRowCount();
        for (int i = 0; i < qtdVendas; i++) {
            if (vendasjTable.getValueAt(i, 5).toString().equals("DINHEIRO")) {
                valorDinheiro = Double.parseDouble(removeVirgula(vendasjTable.getValueAt(i, 4).toString()));
                totalDinheiro = totalDinheiro + valorDinheiro;
            }
            if (vendasjTable.getValueAt(i, 5).toString().equals("CARTÃO")) {
                valorCartao = Double.parseDouble(removeVirgula(vendasjTable.getValueAt(i, 4).toString()));
                totalCartao = totalCartao + valorCartao;
            }
        }

        totalDinheirojLabel.setText("Dinheiro: R$ " + df.format(totalDinheiro));
        totalCartaojLabel.setText("Cartão de crédito: R$ " + df.format(totalCartao));
        statusjLabel.setText("Clique na venda para visualizar os itens");
    }

    private void carregaEntradas() {
        try {
            vendasjTable.setModel(entradas.listaEntradas(dataVendasjFormattedTextField.getText()));
            vendasjTable.updateUI();
            vendasjTable.setShowGrid(true);
            vendasjTable.getColumnModel().getColumn(0).setPreferredWidth(3);
            vendasjTable.getColumnModel().getColumn(1).setPreferredWidth(35);
            vendasjTable.getColumnModel().getColumn(2).setPreferredWidth(300);
            vendasjTable.getColumnModel().getColumn(3).setPreferredWidth(20);
            vendasjTable.getColumnModel().getColumn(4).setPreferredWidth(40);
            vendasjTable.getColumnModel().getColumn(5).setPreferredWidth(50);
            if (vendasjTable.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Nenhuma ENTRADA nessa data", "SISPHARMA - Mensagem", 1);
            }
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    private void calculaTotalGeralEntradas() {
        int qtdEntradas = 0;
        double total = 0.0;

        qtdEntradas = vendasjTable.getRowCount();
        for (int i = 0; i < qtdEntradas; i++) {
            total = total + Double.parseDouble(removeVirgula(vendasjTable.getValueAt(i, 4).toString()));
        }

        totalDinheirojLabel.setText(String.valueOf(qtdEntradas) + " item(s)");
        totalCartaojLabel.setText("Total: R$ " + df.format(total));
    }

    private void carregaEstoqueBaixo() {
        try {
            vendasjTable.setModel(estoque.listaEstoqueBsixo());
            vendasjTable.updateUI();
            vendasjTable.setShowGrid(true);
            vendasjTable.getColumnModel().getColumn(0).setPreferredWidth(3);
            vendasjTable.getColumnModel().getColumn(1).setPreferredWidth(30);
            vendasjTable.getColumnModel().getColumn(2).setPreferredWidth(300);
            vendasjTable.getColumnModel().getColumn(3).setPreferredWidth(30);
            totalDinheirojLabel.setText(vendasjTable.getRowCount() + " item(s)");
            totalCartaojLabel.setText("");
            statusjLabel.setText("");
            if (vendasjTable.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Nenhum PRODUTO com o estoque baixo", "SISPHARMA - Mensagem", 1);
            }
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    private void carregavalidade() {
        try {
            vendasjTable.setModel(validade.listaValidade());
            vendasjTable.updateUI();
            vendasjTable.setShowGrid(true);
            vendasjTable.getColumnModel().getColumn(0).setPreferredWidth(3);
            vendasjTable.getColumnModel().getColumn(1).setPreferredWidth(30);
            vendasjTable.getColumnModel().getColumn(2).setPreferredWidth(260);
            vendasjTable.getColumnModel().getColumn(3).setPreferredWidth(30);
            vendasjTable.getColumnModel().getColumn(4).setPreferredWidth(30);
            totalDinheirojLabel.setText(vendasjTable.getRowCount() + " item(s)");
            totalCartaojLabel.setText("");
            statusjLabel.setText("");
            if (vendasjTable.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "Nenhum PRODUTO com a validade próxima", "SISPHARMA - Mensagem", 1);
            }
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        totalCartaojLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        vendasjTable = new javax.swing.JTable();
        totalDinheirojLabel = new javax.swing.JLabel();
        statusjLabel = new javax.swing.JLabel();
        vendasjButton = new javax.swing.JButton();
        entradasjButton = new javax.swing.JButton();
        estoquejButton = new javax.swing.JButton();
        validadejButton = new javax.swing.JButton();
        dataVendasjFormattedTextField = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        titulojLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SISPHARMA - Relatório");
        setResizable(false);

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        totalCartaojLabel.setFont(new java.awt.Font("Times New Roman", 0, 28));

        vendasjTable.setFont(new java.awt.Font("Times New Roman", 0, 14));
        vendasjTable.setRowHeight(20);
        vendasjTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        vendasjTable.getTableHeader().setResizingAllowed(false);
        vendasjTable.getTableHeader().setReorderingAllowed(false);
        vendasjTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                vendasjTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(vendasjTable);

        totalDinheirojLabel.setFont(new java.awt.Font("Times New Roman", 0, 28));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(totalDinheirojLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(totalCartaojLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
                .addContainerGap())
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 651, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(totalDinheirojLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(totalCartaojLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        statusjLabel.setFont(new java.awt.Font("Times New Roman", 2, 14));

        vendasjButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/system/app/sispharma/imagens/money.png"))); // NOI18N
        vendasjButton.setText("VENDAS");
        vendasjButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        vendasjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vendasjButtonActionPerformed(evt);
            }
        });

        entradasjButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/system/app/sispharma/imagens/arrow_up.png"))); // NOI18N
        entradasjButton.setText("ENTRADAS");
        entradasjButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        entradasjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                entradasjButtonActionPerformed(evt);
            }
        });

        estoquejButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/system/app/sispharma/imagens/exclamation.png"))); // NOI18N
        estoquejButton.setText("ESTOQUE BAIXO");
        estoquejButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        estoquejButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                estoquejButtonActionPerformed(evt);
            }
        });

        validadejButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/system/app/sispharma/imagens/calendar.png"))); // NOI18N
        validadejButton.setText("VALIDADE");
        validadejButton.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        validadejButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                validadejButtonActionPerformed(evt);
            }
        });

        try {
            dataVendasjFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        dataVendasjFormattedTextField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        dataVendasjFormattedTextField.setFont(new java.awt.Font("Times New Roman", 0, 24));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 14));
        jLabel1.setText("Data:");
        jLabel1.setFocusable(false);

        titulojLabel.setFont(new java.awt.Font("Times New Roman", 1, 18));
        titulojLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulojLabel.setText("ESCOLHA UM DOS RELATÓRIOS AO LADO");
        titulojLabel.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));
        titulojLabel.setFocusable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(titulojLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 797, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dataVendasjFormattedTextField, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(validadejButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(estoquejButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(entradasjButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(vendasjButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(11, 11, 11)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(statusjLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 797, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titulojLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dataVendasjFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(vendasjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(entradasjButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(estoquejButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(validadejButton)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusjLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {entradasjButton, estoquejButton, validadejButton, vendasjButton});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void vendasjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vendasjButtonActionPerformed

        carregaVendas();
        calculaTotalGeralVendas();
        titulojLabel.setText("RELATÓRIO DE VENDAS");
        dataVendasjFormattedTextField.transferFocus();

    }//GEN-LAST:event_vendasjButtonActionPerformed

    private void vendasjTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vendasjTableMouseClicked

        JanelaItensVenda dialog = new JanelaItensVenda(new javax.swing.JFrame(), true);
        dialog.carregaItensVenda(Integer.parseInt(vendasjTable.getValueAt(vendasjTable.getSelectedRow(), 0).toString()));
        dialog.setVisible(true);

    }//GEN-LAST:event_vendasjTableMouseClicked

    private void entradasjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_entradasjButtonActionPerformed

        carregaEntradas();
        calculaTotalGeralEntradas();
        titulojLabel.setText("RELATÓRIO DE ENTRADAS");
        dataVendasjFormattedTextField.transferFocus();

    }//GEN-LAST:event_entradasjButtonActionPerformed

    private void estoquejButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_estoquejButtonActionPerformed

        carregaEstoqueBaixo();
        titulojLabel.setText("RELATÓRIO DE ESTOQUE BAIXO");
        dataVendasjFormattedTextField.transferFocus();

    }//GEN-LAST:event_estoquejButtonActionPerformed

    private void validadejButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_validadejButtonActionPerformed

        carregavalidade();
        titulojLabel.setText("RELATÓRIO DE PRODUTOS COM VALIDADE PRÓXIMA");
        dataVendasjFormattedTextField.transferFocus();

    }//GEN-LAST:event_validadejButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField dataVendasjFormattedTextField;
    private javax.swing.JButton entradasjButton;
    private javax.swing.JButton estoquejButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel statusjLabel;
    private javax.swing.JLabel titulojLabel;
    private javax.swing.JLabel totalCartaojLabel;
    private javax.swing.JLabel totalDinheirojLabel;
    private javax.swing.JButton validadejButton;
    private javax.swing.JButton vendasjButton;
    private javax.swing.JTable vendasjTable;
    // End of variables declaration//GEN-END:variables
}
