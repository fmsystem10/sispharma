package com.system.app.sispharma.ui.dialog;

import com.system.app.sispharma.entidades.Usuario;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

public class JanelaCadastrarUsuario extends javax.swing.JDialog {

    Usuario usu = new Usuario();

    public JanelaCadastrarUsuario(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        loginjFormattedTextField.setText("func");
    }

    private boolean capturarDados() {
        try {
            if (camposObrigatorios() == true) {
                usu.setNome(nomejFormattedTextField.getText().toUpperCase());
                usu.setTipo(tipoUsuariojComboBox.getSelectedIndex());
                usu.setLogin(loginjFormattedTextField.getText());
                usu.setSenha(senhajPasswordField.getText());
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    private boolean conferirSenha() {
        if (senhajPasswordField.getText().equals(repetirSenhajPasswordField.getText())) {
            return true;
        } else {
            return false;
        }
    }

    private void limparCampos() {
        nomejFormattedTextField.setText("");
        tipoUsuariojComboBox.setSelectedIndex(0);
        loginjFormattedTextField.setText("");
        senhajPasswordField.setText("");
        repetirSenhajPasswordField.setText("");
    }

    private boolean camposObrigatorios() {
        boolean ret = true;

        if (nomejFormattedTextField.getText().equals("")) {
            ret = false;
        } else if (senhajPasswordField.getText().equals("")) {
            ret = false;
        }
        return ret;
    }

    private void salvarUsuario() {
        String retorno = "OK";

        if (conferirSenha() == true) {
            if (capturarDados() == true) {
                retorno = usu.inserirUsuario(usu);
                if (retorno.equals("OK")) {
                    JOptionPane.showMessageDialog(null, "Operação realizada com sucesso", "SISPHARMA - Mensagem", 1);
                    limparCampos();
                } else {
                    JOptionPane.showMessageDialog(null, retorno, "SISPHARMA - Aviso", 2);
                    return;
                }
            } else {
                JOptionPane.showMessageDialog(null, "Erro ao capturar dados!", "SISPHARMA - Aviso", 2);
                return;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Senha inválida!", "SISPHARMA - Aviso", 0);
            return;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tipoUsuariojComboBox = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        loginjFormattedTextField = new javax.swing.JFormattedTextField();
        nomejFormattedTextField = new javax.swing.JFormattedTextField();
        senhajPasswordField = new javax.swing.JPasswordField();
        repetirSenhajPasswordField = new javax.swing.JPasswordField();
        salvarjButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SISPHARMA - Cadastrar Usuário");
        setResizable(false);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setText("NOME:");

        jLabel2.setText("TIPO:");

        jLabel3.setText("SENHA:");

        jLabel4.setText("REPETIR SENHA:");

        tipoUsuariojComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Funcionário", "Administrador" }));
        tipoUsuariojComboBox.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tipoUsuariojComboBoxItemStateChanged(evt);
            }
        });
        tipoUsuariojComboBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tipoUsuariojComboBoxKeyPressed(evt);
            }
        });

        jLabel5.setText("LOGIN:");

        loginjFormattedTextField.setEditable(false);

        try {
            nomejFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("****************************************")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        nomejFormattedTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nomejFormattedTextFieldKeyPressed(evt);
            }
        });

        senhajPasswordField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                senhajPasswordFieldKeyPressed(evt);
            }
        });

        repetirSenhajPasswordField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                repetirSenhajPasswordFieldKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nomejFormattedTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 272, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(repetirSenhajPasswordField, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(senhajPasswordField, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(tipoUsuariojComboBox, javax.swing.GroupLayout.Alignment.LEADING, 0, 135, Short.MAX_VALUE)
                        .addComponent(loginjFormattedTextField, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nomejFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tipoUsuariojComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(loginjFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(senhajPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(repetirSenhajPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {loginjFormattedTextField, nomejFormattedTextField, repetirSenhajPasswordField, senhajPasswordField, tipoUsuariojComboBox});

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(125, 125, 125)
                        .addComponent(salvarjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(salvarjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tipoUsuariojComboBoxItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tipoUsuariojComboBoxItemStateChanged

        if (tipoUsuariojComboBox.getSelectedIndex() == 0) {
            loginjFormattedTextField.setText("func");
        } else {
            loginjFormattedTextField.setText("admin");
        }

    }//GEN-LAST:event_tipoUsuariojComboBoxItemStateChanged

    private void salvarjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salvarjButtonActionPerformed

        salvarUsuario();

    }//GEN-LAST:event_salvarjButtonActionPerformed

    private void nomejFormattedTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nomejFormattedTextFieldKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            nomejFormattedTextField.transferFocus();
        }

    }//GEN-LAST:event_nomejFormattedTextFieldKeyPressed

    private void tipoUsuariojComboBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tipoUsuariojComboBoxKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            tipoUsuariojComboBox.transferFocus();
            loginjFormattedTextField.transferFocus();
        }

    }//GEN-LAST:event_tipoUsuariojComboBoxKeyPressed

    private void senhajPasswordFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_senhajPasswordFieldKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            senhajPasswordField.transferFocus();
        }

    }//GEN-LAST:event_senhajPasswordFieldKeyPressed

    private void repetirSenhajPasswordFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_repetirSenhajPasswordFieldKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            repetirSenhajPasswordField.transferFocus();
        }

    }//GEN-LAST:event_repetirSenhajPasswordFieldKeyPressed

    private void salvarjButtonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_salvarjButtonKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            salvarUsuario();
            salvarjButton.transferFocus();
        }

    }//GEN-LAST:event_salvarjButtonKeyPressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JFormattedTextField loginjFormattedTextField;
    private javax.swing.JFormattedTextField nomejFormattedTextField;
    private javax.swing.JPasswordField repetirSenhajPasswordField;
    private javax.swing.JButton salvarjButton;
    private javax.swing.JPasswordField senhajPasswordField;
    private javax.swing.JComboBox tipoUsuariojComboBox;
    // End of variables declaration//GEN-END:variables
}
