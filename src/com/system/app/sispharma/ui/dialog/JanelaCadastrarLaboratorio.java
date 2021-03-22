package com.system.app.sispharma.ui.dialog;

import com.system.app.sispharma.entidades.Laboratorio;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

public class JanelaCadastrarLaboratorio extends javax.swing.JDialog {

    Laboratorio lab = new Laboratorio();

    public JanelaCadastrarLaboratorio(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        carregarLaboratorios();
        setLocationRelativeTo(null);
    }

    private boolean capturarDados() {
        try {
            lab.setLaboratorio(laboratoriojTextField.getText().toUpperCase());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void salvarLaboratorio() {
        String retorno = "OK";

        if (!laboratoriojTextField.getText().equals("")) {
            if (capturarDados() == true) {
                retorno = lab.inserirLaboratorio(lab);
                if (retorno.equals("OK")) {
                    JOptionPane.showMessageDialog(null, "Operação realizada com sucesso", "SISPHARMA - Mensagem", 1);
                    laboratoriojTextField.setText("");
                } else {
                    JOptionPane.showMessageDialog(null, retorno, "SISPHARMA - Aviso", 2);
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao capturar dados!", "SISPHARMA - Aviso", 2);
                return;
            }
        } else
            JOptionPane.showMessageDialog(null, "Preencha o campo laboratório!", "SISPHARMA - Aviso", 2);
    }

    private void carregarLaboratorios() {
        try {
            laboratoriojTable.setModel(lab.listaLaboratorios());
            laboratoriojTable.setShowGrid(true);
            laboratoriojTable.getColumnModel().getColumn(0).setPreferredWidth(3);
            laboratoriojTable.getColumnModel().getColumn(1).setPreferredWidth(450);
        } catch (Exception e) {
            System.out.print(e);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        laboratoriojTextField = new javax.swing.JTextField();
        salvarjButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        laboratoriojTable = new javax.swing.JTable();

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SISPHARMA - Cadastar Laboratório");
        setResizable(false);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setText("LABORATÓRIO:");

        laboratoriojTextField.setFont(new java.awt.Font("Tahoma", 0, 14));
        laboratoriojTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                laboratoriojTextFieldKeyPressed(evt);
            }
        });

        salvarjButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/system/app/sispharma/imagens/disk.png"))); // NOI18N
        salvarjButton.setText("SALVAR");
        salvarjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salvarjButtonActionPerformed(evt);
            }
        });
        salvarjButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                salvarjButtonKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(laboratoriojTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 326, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(salvarjButton)
                .addGap(22, 22, 22))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(laboratoriojTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(salvarjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        laboratoriojTable.setFont(new java.awt.Font("Times New Roman", 0, 18));
        laboratoriojTable.setRowHeight(20);
        laboratoriojTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        laboratoriojTable.getTableHeader().setReorderingAllowed(false);
        laboratoriojTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                laboratoriojTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(laboratoriojTable);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
                .addContainerGap())
        );

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

    private void salvarjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salvarjButtonActionPerformed

        salvarLaboratorio();
        carregarLaboratorios();
        salvarjButton.transferFocus();
        laboratoriojTable.transferFocus();

    }//GEN-LAST:event_salvarjButtonActionPerformed

    private void laboratoriojTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_laboratoriojTextFieldKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            laboratoriojTextField.transferFocus();
        }

    }//GEN-LAST:event_laboratoriojTextFieldKeyPressed

    private void salvarjButtonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_salvarjButtonKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            salvarLaboratorio();
            carregarLaboratorios();
            salvarjButton.transferFocus();
            laboratoriojTable.transferFocus();
        }

    }//GEN-LAST:event_salvarjButtonKeyPressed

    private void laboratoriojTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_laboratoriojTableMouseClicked
/*
        String ret = "OK";
        int teste = 1;

        if (laboratoriojTable.getSelectedRow() != -1) {
            teste = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir o laboratório?", "SISPHARMA - Aviso", 2);

            if (teste == 0) {
                ret = lab.excluirLaboratorio(Integer.parseInt(laboratoriojTable.getValueAt(laboratoriojTable.getSelectedRow(), 0).toString()));
                if (ret.equals("OK")) {
                    carregarLaboratorios();
                    laboratoriojTable.transferFocus();
                } else {
                    JOptionPane.showMessageDialog(null, ret, "SISPHARMA - Aviso", 2);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Escolha um laboratório para excluir!", "SISPHARMA - Aviso", 2);
        }
*/
    }//GEN-LAST:event_laboratoriojTableMouseClicked
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable laboratoriojTable;
    private javax.swing.JTextField laboratoriojTextField;
    private javax.swing.JButton salvarjButton;
    // End of variables declaration//GEN-END:variables
}
