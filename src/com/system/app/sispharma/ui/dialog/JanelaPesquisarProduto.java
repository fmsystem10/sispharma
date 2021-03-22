package com.system.app.sispharma.ui.dialog;

import com.system.app.sispharma.entidades.Produto;
import java.awt.event.KeyEvent;
import java.util.Vector;

public class JanelaPesquisarProduto extends javax.swing.JDialog {

    Produto prod = new Produto();
    Vector<String> listProd = new Vector<String>();
    int tipo = 0;

    public JanelaPesquisarProduto(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        pesquisarjTextField.setFocusable(true);
        carregaProdutos();
        setLocationRelativeTo(null);
    }

    public void tipoJanela(int tipo) {
        this.tipo = tipo;
    }

    private void carregaProdutos() {
        try {
            produtojTable.setModel(prod.listaProdutos());
            produtojTable.setShowGrid(true);
            produtojTable.getColumnModel().getColumn(0).setPreferredWidth(3);
            produtojTable.getColumnModel().getColumn(1).setPreferredWidth(300);
            produtojTable.getColumnModel().getColumn(2).setPreferredWidth(10);
            produtojTable.getColumnModel().getColumn(3).setPreferredWidth(10);
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        produtojTable = new javax.swing.JTable();
        pesquisarjPanel = new javax.swing.JPanel();
        pesquisarjTextField = new javax.swing.JTextField();
        pesquisarjButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SISPHARMA - Pesquisar Produto");
        setResizable(false);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        produtojTable.setFont(new java.awt.Font("Times New Roman", 0, 18));
        produtojTable.setRowHeight(20);
        produtojTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        produtojTable.getTableHeader().setReorderingAllowed(false);
        produtojTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                produtojTableMouseClicked(evt);
            }
        });
        produtojTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                produtojTableKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(produtojTable);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 698, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 499, Short.MAX_VALUE)
        );

        pesquisarjPanel.setBackground(new java.awt.Color(255, 255, 255));

        pesquisarjTextField.setFont(new java.awt.Font("Times New Roman", 0, 18));
        pesquisarjTextField.setFocusable(false);
        pesquisarjTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pesquisarjTextFieldKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pesquisarjTextFieldKeyReleased(evt);
            }
        });

        pesquisarjButton.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        pesquisarjButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/system/app/sispharma/imagens/arrow_rotate_clockwise.png"))); // NOI18N
        pesquisarjButton.setText("ENTER");
        pesquisarjButton.setFocusable(false);
        pesquisarjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pesquisarjButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pesquisarjPanelLayout = new javax.swing.GroupLayout(pesquisarjPanel);
        pesquisarjPanel.setLayout(pesquisarjPanelLayout);
        pesquisarjPanelLayout.setHorizontalGroup(
            pesquisarjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pesquisarjPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pesquisarjTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pesquisarjButton)
                .addContainerGap())
        );
        pesquisarjPanelLayout.setVerticalGroup(
            pesquisarjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pesquisarjPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pesquisarjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pesquisarjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pesquisarjButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pesquisarjPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {pesquisarjButton, pesquisarjTextField});

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pesquisarjPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pesquisarjPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void pesquisarjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pesquisarjButtonActionPerformed

        pesquisarjTextField.transferFocus();

    }//GEN-LAST:event_pesquisarjButtonActionPerformed

    private void pesquisarjTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pesquisarjTextFieldKeyReleased

        produtojTable.setModel(prod.pesquisaProduto(pesquisarjTextField.getText().toUpperCase()));
        produtojTable.setShowGrid(true);
        produtojTable.getColumnModel().getColumn(0).setPreferredWidth(3);
        produtojTable.getColumnModel().getColumn(1).setPreferredWidth(300);
        produtojTable.getColumnModel().getColumn(2).setPreferredWidth(10);
        produtojTable.getColumnModel().getColumn(3).setPreferredWidth(10);

    }//GEN-LAST:event_pesquisarjTextFieldKeyReleased

    private void pesquisarjTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pesquisarjTextFieldKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            pesquisarjTextField.transferFocus();
        }

    }//GEN-LAST:event_pesquisarjTextFieldKeyPressed

    private void produtojTableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_produtojTableKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER && produtojTable.getSelectedRow() != -1) {
            if (tipo == 0) {
                prod = prod.retornarProdutoCodigo(Integer.parseInt(produtojTable.getValueAt(produtojTable.getSelectedRow(), 0).toString()));
                new JanelaVenda(prod.getCodigo_barra());
                this.dispose();
            } else if (tipo == 1) {
                JanelaCadastrarProduto dialog = new JanelaCadastrarProduto(new javax.swing.JFrame(), true);
                dialog.tipoJanela(1, produtojTable.getValueAt(produtojTable.getSelectedRow(), 0).toString());
                dialog.setVisible(true);
                pesquisarjTextField.setText("");
                carregaProdutos();
                //produtojTable.transferFocus();
            }
        }

    }//GEN-LAST:event_produtojTableKeyPressed

    private void produtojTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_produtojTableMouseClicked

    }//GEN-LAST:event_produtojTableMouseClicked
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton pesquisarjButton;
    private javax.swing.JPanel pesquisarjPanel;
    private javax.swing.JTextField pesquisarjTextField;
    private javax.swing.JTable produtojTable;
    // End of variables declaration//GEN-END:variables
}
