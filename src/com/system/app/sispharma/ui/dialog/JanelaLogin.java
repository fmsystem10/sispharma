package com.system.app.sispharma.ui.dialog;

import com.system.app.sispharma.entidades.Usuario;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;

public class JanelaLogin extends javax.swing.JDialog {

    Usuario usuario = new Usuario();
    private int tipo = 999;

    public JanelaLogin(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
    }

    private boolean validarUsuario(String login, String senha) {

        this.tipo = usuario.validarUsuario(login, senha);

        if (this.tipo == 999) {
            return false;
        } else {
            return true;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        loginjTextField = new javax.swing.JTextField();
        senhajPasswordField = new javax.swing.JPasswordField();
        entrarjButton = new javax.swing.JButton();
        logojLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SISPHARMA - Login");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBorder(new javax.swing.border.MatteBorder(null));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel1.setText("Login:");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jLabel2.setText("Senha:");

        loginjTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                loginjTextFieldKeyPressed(evt);
            }
        });

        senhajPasswordField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                senhajPasswordFieldKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(loginjTextField)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(senhajPasswordField))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(loginjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(senhajPasswordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {loginjTextField, senhajPasswordField});

        entrarjButton.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        entrarjButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/system/app/sispharma/imagens/accept.png"))); // NOI18N
        entrarjButton.setText("entrar");
        entrarjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                entrarjButtonActionPerformed(evt);
            }
        });
        entrarjButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                entrarjButtonKeyPressed(evt);
            }
        });

        logojLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        logojLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/system/app/sispharma/imagens/LogoSispharma.png"))); // NOI18N

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/system/app/sispharma/imagens/rodape.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(logojLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(entrarjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(logojLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(entrarjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void entrarjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_entrarjButtonActionPerformed

        if (validarUsuario(loginjTextField.getText(), senhajPasswordField.getText()) == true) {
            usuario.setTipo(this.tipo);
            usuario.setSenha(senhajPasswordField.getText());
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Login e/ou senha incorreto(s)", "SISPHARMA - Aviso", 0);
            entrarjButton.transferFocus();
        }

    }//GEN-LAST:event_entrarjButtonActionPerformed

    private void loginjTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_loginjTextFieldKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            loginjTextField.transferFocus();
        }

    }//GEN-LAST:event_loginjTextFieldKeyPressed

    private void senhajPasswordFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_senhajPasswordFieldKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            senhajPasswordField.transferFocus();
        }

    }//GEN-LAST:event_senhajPasswordFieldKeyPressed

    private void entrarjButtonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_entrarjButtonKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (validarUsuario(loginjTextField.getText(), senhajPasswordField.getText()) == true) {
                usuario.setTipo(this.tipo);
                usuario.setSenha(senhajPasswordField.getText());
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Login e/ou senha incorreto(s)", "SISPHARMA - Aviso", 0);
                entrarjButton.transferFocus();
            }
        }

    }//GEN-LAST:event_entrarjButtonKeyPressed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing

        System.exit(0);

    }//GEN-LAST:event_formWindowClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton entrarjButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField loginjTextField;
    private javax.swing.JLabel logojLabel;
    private javax.swing.JPasswordField senhajPasswordField;
    // End of variables declaration//GEN-END:variables
}
