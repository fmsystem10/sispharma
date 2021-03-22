package com.system.app.sispharma.ui.dialog;

import com.system.app.sispharma.entidades.Produto;
import java.awt.event.KeyEvent;
import java.util.Vector;
import javax.swing.JOptionPane;

public class JanelaProduto extends javax.swing.JDialog {

    Produto prod = new Produto();
    Vector<String> listProd = new Vector<String>();
    int status = 0;

    public JanelaProduto(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        codigoBarrajRadioButton.setSelected(true);
        pesquisarjTextField.setFocusable(true);
        carregaProdutos();
        statusjLabel.setText("Clique no produto para alterar ou excluir");
        setLocationRelativeTo(null);
    }

    public JanelaProduto(int stat) {
        initComponents();
        this.status = stat;
        descricaojRadioButton.setSelected(true);
        pesquisarjTextField.setFocusable(true);
        carregaProdutos();
        setLocationRelativeTo(null);
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

    private void retornaProdutoCodigoBarra(String codBarra) {
        JanelaCadastrarProduto dialog = new JanelaCadastrarProduto(new javax.swing.JFrame(), true);
        int tip = dialog.tipoJanela(0, codBarra);
        if (tip == 999) {
            JOptionPane.showMessageDialog(null, "Produto não encontrado!", "SISPHARMA - Aviso", 2);
        } else {
            dialog.setVisible(true);
            carregaProdutos();
            produtojTable.transferFocus();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        botoesjPanel = new javax.swing.JPanel();
        novojButton = new javax.swing.JButton();
        excluirjButton = new javax.swing.JButton();
        alterarjButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        produtojTable = new javax.swing.JTable();
        pesquisarjPanel = new javax.swing.JPanel();
        codigoBarrajRadioButton = new javax.swing.JRadioButton();
        descricaojRadioButton = new javax.swing.JRadioButton();
        pesquisarjTextField = new javax.swing.JTextField();
        pesquisarjButton = new javax.swing.JButton();
        statusjLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SISPHARMA - Produto");
        setResizable(false);

        botoesjPanel.setBackground(new java.awt.Color(255, 255, 255));

        novojButton.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        novojButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/system/app/sispharma/imagens/tab_add.png"))); // NOI18N
        novojButton.setText("NOVO");
        novojButton.setFocusable(false);
        novojButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                novojButtonActionPerformed(evt);
            }
        });

        excluirjButton.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        excluirjButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/system/app/sispharma/imagens/tab_delete.png"))); // NOI18N
        excluirjButton.setText("EXCLUIR");
        excluirjButton.setFocusable(false);
        excluirjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                excluirjButtonActionPerformed(evt);
            }
        });

        alterarjButton.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        alterarjButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/system/app/sispharma/imagens/tab_edit.png"))); // NOI18N
        alterarjButton.setText("ALTERAR");
        alterarjButton.setFocusable(false);
        alterarjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alterarjButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout botoesjPanelLayout = new javax.swing.GroupLayout(botoesjPanel);
        botoesjPanel.setLayout(botoesjPanelLayout);
        botoesjPanelLayout.setHorizontalGroup(
            botoesjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(botoesjPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(novojButton, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(alterarjButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(excluirjButton)
                .addContainerGap(357, Short.MAX_VALUE))
        );

        botoesjPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {alterarjButton, excluirjButton, novojButton});

        botoesjPanelLayout.setVerticalGroup(
            botoesjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(botoesjPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(botoesjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(novojButton)
                    .addComponent(alterarjButton)
                    .addComponent(excluirjButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        botoesjPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {excluirjButton, novojButton});

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        produtojTable.setFont(new java.awt.Font("Times New Roman", 0, 18));
        produtojTable.setRowHeight(20);
        produtojTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        produtojTable.getTableHeader().setReorderingAllowed(false);
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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 467, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pesquisarjPanel.setBackground(new java.awt.Color(255, 255, 255));
        pesquisarjPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        codigoBarrajRadioButton.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(codigoBarrajRadioButton);
        codigoBarrajRadioButton.setFont(new java.awt.Font("Times New Roman", 1, 14));
        codigoBarrajRadioButton.setText("Código de Barra");
        codigoBarrajRadioButton.setFocusable(false);

        descricaojRadioButton.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(descricaojRadioButton);
        descricaojRadioButton.setFont(new java.awt.Font("Times New Roman", 1, 14));
        descricaojRadioButton.setText("Descrição");
        descricaojRadioButton.setFocusable(false);

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
            .addGroup(pesquisarjPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(codigoBarrajRadioButton)
                .addGap(18, 18, 18)
                .addComponent(descricaojRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pesquisarjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pesquisarjButton, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                .addContainerGap())
        );
        pesquisarjPanelLayout.setVerticalGroup(
            pesquisarjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pesquisarjPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pesquisarjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pesquisarjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pesquisarjButton)
                    .addComponent(codigoBarrajRadioButton)
                    .addComponent(descricaojRadioButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pesquisarjPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {pesquisarjButton, pesquisarjTextField});

        statusjLabel.setFont(new java.awt.Font("Times New Roman", 2, 18));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(statusjLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 730, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(botoesjPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(pesquisarjPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(botoesjPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pesquisarjPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusjLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void novojButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_novojButtonActionPerformed

        JanelaCadastrarProduto dialog = new JanelaCadastrarProduto(new javax.swing.JFrame(), true);
        dialog.tipoJanela(2, "999999");
        dialog.setVisible(true);
        carregaProdutos();

    }//GEN-LAST:event_novojButtonActionPerformed

    private void excluirjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_excluirjButtonActionPerformed

        String ret = "OK";
        int teste = 1;

        if (produtojTable.getSelectedRow() != -1) {
            teste = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir o produto?", "SISPHARMA - Aviso", 2);

            if (teste == 0) {
                ret = prod.excluirProduto(Integer.parseInt(produtojTable.getValueAt(produtojTable.getSelectedRow(), 0).toString()));
                if (ret.equals("OK")) {
                    carregaProdutos();
                } else {
                    JOptionPane.showMessageDialog(null, ret, "SISPHARMA - Aviso", 2);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Escolha um produto para excluir!", "SISPHARMA - Aviso", 2);
        }

    }//GEN-LAST:event_excluirjButtonActionPerformed

    private void pesquisarjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pesquisarjButtonActionPerformed

        if (codigoBarrajRadioButton.isSelected() == true) {
            if (!pesquisarjTextField.getText().equals("")) {
                retornaProdutoCodigoBarra(pesquisarjTextField.getText());
            } else {
                JOptionPane.showMessageDialog(null, "Preencha o campo pesquisa!", "SISPHARMA - Aviso", 2);
            }
        } else {
            pesquisarjTextField.transferFocus();
            produtojTable.setSelectionMode(0);
        }

    }//GEN-LAST:event_pesquisarjButtonActionPerformed

    private void alterarjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alterarjButtonActionPerformed

        if (produtojTable.getSelectedRow() != -1) {
            if (codigoBarrajRadioButton.isSelected() == true && !pesquisarjTextField.getText().equals("")) {
                JanelaCadastrarProduto dialog = new JanelaCadastrarProduto(new javax.swing.JFrame(), true);
                dialog.tipoJanela(0, pesquisarjTextField.getText());
                dialog.setVisible(true);
            } else {
                JanelaCadastrarProduto dialog = new JanelaCadastrarProduto(new javax.swing.JFrame(), true);
                dialog.tipoJanela(0, produtojTable.getValueAt(produtojTable.getSelectedRow(), 0).toString());
                dialog.setVisible(true);
            }
            pesquisarjTextField.setText("");
            carregaProdutos();
        } else
            JOptionPane.showMessageDialog(null, "Escolha um produto para alterar!", "SISPHARMA - Aviso", 2);

    }//GEN-LAST:event_alterarjButtonActionPerformed

    private void pesquisarjTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pesquisarjTextFieldKeyReleased

        if (descricaojRadioButton.isSelected() == true) {
            produtojTable.setModel(prod.pesquisaProduto(pesquisarjTextField.getText().toUpperCase()));
            produtojTable.setShowGrid(true);
            produtojTable.getColumnModel().getColumn(0).setPreferredWidth(3);
            produtojTable.getColumnModel().getColumn(1).setPreferredWidth(300);
            produtojTable.getColumnModel().getColumn(2).setPreferredWidth(10);
            produtojTable.getColumnModel().getColumn(3).setPreferredWidth(10);
        }
        
    }//GEN-LAST:event_pesquisarjTextFieldKeyReleased

    private void pesquisarjTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pesquisarjTextFieldKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (codigoBarrajRadioButton.isSelected() == true) {
                if (!pesquisarjTextField.getText().equals("")) {
                    retornaProdutoCodigoBarra(pesquisarjTextField.getText());
                    pesquisarjTextField.setText("");
                    carregaProdutos();
                } else {
                    JOptionPane.showMessageDialog(null, "Preencha o campo pesquisa!", "SISPHARMA - Aviso", 2);
                }
            } else {
                pesquisarjTextField.transferFocus();
                
            }
        }
        
    }//GEN-LAST:event_pesquisarjTextFieldKeyPressed

    private void produtojTableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_produtojTableKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            JOptionPane.showMessageDialog(null, "Clique no botão alterar ou excluir", "SISPHARMA - Mensagem", 1);
        }

    }//GEN-LAST:event_produtojTableKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton alterarjButton;
    private javax.swing.JPanel botoesjPanel;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton codigoBarrajRadioButton;
    private javax.swing.JRadioButton descricaojRadioButton;
    private javax.swing.JButton excluirjButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton novojButton;
    private javax.swing.JButton pesquisarjButton;
    private javax.swing.JPanel pesquisarjPanel;
    private javax.swing.JTextField pesquisarjTextField;
    private javax.swing.JTable produtojTable;
    private javax.swing.JLabel statusjLabel;
    // End of variables declaration//GEN-END:variables
}
