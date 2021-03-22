package com.system.app.sispharma.ui.frame;

import com.system.app.sispharma.entidades.Usuario;
import com.system.app.sispharma.ui.dialog.JanelaCadastrarLaboratorio;
import com.system.app.sispharma.ui.dialog.JanelaCadastrarUsuario;
import com.system.app.sispharma.ui.dialog.JanelaLogin;
import com.system.app.sispharma.ui.dialog.JanelaPesquisarProduto;
import com.system.app.sispharma.ui.dialog.JanelaProduto;
import com.system.app.sispharma.ui.dialog.JanelaRelatorio;
import com.system.app.sispharma.ui.dialog.JanelaVenda;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class JanelaPrincipal extends javax.swing.JFrame {

    JFrame f = new JFrame();
    Usuario usuario = new Usuario();

    public JanelaPrincipal() {
        initComponents();
        JanelaLogin dialog = new JanelaLogin(new javax.swing.JFrame(), true);
        dialog.setVisible(true);
        verificarUsuario();
        telaCleia();
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

    private void verificarUsuario() {
        if (usuario.getTipo() == 0) {
            cadastroUsuariojMenuItem.setEnabled(false);
            relatoriojMenu.setEnabled(false);
            configuracaojMenu.setEnabled(false);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        cadastrojMenu = new javax.swing.JMenu();
        cadastroProdutojMenuItem = new javax.swing.JMenuItem();
        cadastroLaboratoriojMenuItem = new javax.swing.JMenuItem();
        cadastroUsuariojMenuItem = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        movimentacaojMenu = new javax.swing.JMenu();
        movimentacaoVendajMenuItem = new javax.swing.JMenuItem();
        movimentacaoEntradajMenuItem = new javax.swing.JMenuItem();
        relatoriojMenu = new javax.swing.JMenu();
        centralRelatoriosjMenuItem = new javax.swing.JMenuItem();
        configuracaojMenu = new javax.swing.JMenu();
        configuracaoUsuariosjMenuItem1 = new javax.swing.JMenuItem();
        ajudajMenu = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SISPHARMA");
        setBackground(new java.awt.Color(255, 255, 255));
        setResizable(false);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/system/app/sispharma/imagens/tela.png"))); // NOI18N
        jLabel1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 2, true));

        cadastrojMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/system/app/sispharma/imagens/add.png"))); // NOI18N
        cadastrojMenu.setText("Cadastro");
        cadastrojMenu.setFont(new java.awt.Font("Tahoma", 0, 14));

        cadastroProdutojMenuItem.setFont(new java.awt.Font("Tahoma", 0, 12));
        cadastroProdutojMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/system/app/sispharma/imagens/brick_add.png"))); // NOI18N
        cadastroProdutojMenuItem.setText("Produto");
        cadastroProdutojMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadastroProdutojMenuItemActionPerformed(evt);
            }
        });
        cadastrojMenu.add(cadastroProdutojMenuItem);

        cadastroLaboratoriojMenuItem.setFont(new java.awt.Font("Tahoma", 0, 12));
        cadastroLaboratoriojMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/system/app/sispharma/imagens/heart_add.png"))); // NOI18N
        cadastroLaboratoriojMenuItem.setText("Laboratório");
        cadastroLaboratoriojMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadastroLaboratoriojMenuItemActionPerformed(evt);
            }
        });
        cadastrojMenu.add(cadastroLaboratoriojMenuItem);

        cadastroUsuariojMenuItem.setFont(new java.awt.Font("Tahoma", 0, 12));
        cadastroUsuariojMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/system/app/sispharma/imagens/user_add.png"))); // NOI18N
        cadastroUsuariojMenuItem.setText("Usuário");
        cadastroUsuariojMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cadastroUsuariojMenuItemActionPerformed(evt);
            }
        });
        cadastrojMenu.add(cadastroUsuariojMenuItem);

        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/system/app/sispharma/imagens/cancel.png"))); // NOI18N
        jMenuItem3.setText("Sair");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        cadastrojMenu.add(jMenuItem3);

        jMenuBar1.add(cadastrojMenu);

        movimentacaojMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/system/app/sispharma/imagens/arrow_refresh.png"))); // NOI18N
        movimentacaojMenu.setText("Movimentação");
        movimentacaojMenu.setFont(new java.awt.Font("Tahoma", 0, 14));

        movimentacaoVendajMenuItem.setFont(new java.awt.Font("Tahoma", 0, 12));
        movimentacaoVendajMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/system/app/sispharma/imagens/cart_add.png"))); // NOI18N
        movimentacaoVendajMenuItem.setText("Venda");
        movimentacaoVendajMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                movimentacaoVendajMenuItemActionPerformed(evt);
            }
        });
        movimentacaojMenu.add(movimentacaoVendajMenuItem);

        movimentacaoEntradajMenuItem.setFont(new java.awt.Font("Tahoma", 0, 12));
        movimentacaoEntradajMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/system/app/sispharma/imagens/arrow_up.png"))); // NOI18N
        movimentacaoEntradajMenuItem.setText("Entrada");
        movimentacaoEntradajMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                movimentacaoEntradajMenuItemActionPerformed(evt);
            }
        });
        movimentacaojMenu.add(movimentacaoEntradajMenuItem);

        jMenuBar1.add(movimentacaojMenu);

        relatoriojMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/system/app/sispharma/imagens/application_view_list.png"))); // NOI18N
        relatoriojMenu.setText("Relatório");
        relatoriojMenu.setFont(new java.awt.Font("Tahoma", 0, 14));

        centralRelatoriosjMenuItem.setFont(new java.awt.Font("Tahoma", 0, 12));
        centralRelatoriosjMenuItem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/system/app/sispharma/imagens/layout.png"))); // NOI18N
        centralRelatoriosjMenuItem.setText("Centro de Relatórios");
        centralRelatoriosjMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                centralRelatoriosjMenuItemActionPerformed(evt);
            }
        });
        relatoriojMenu.add(centralRelatoriosjMenuItem);

        jMenuBar1.add(relatoriojMenu);

        configuracaojMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/system/app/sispharma/imagens/cog.png"))); // NOI18N
        configuracaojMenu.setText("Configuração");
        configuracaojMenu.setFont(new java.awt.Font("Tahoma", 0, 14));

        configuracaoUsuariosjMenuItem1.setFont(new java.awt.Font("Tahoma", 0, 12));
        configuracaoUsuariosjMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/system/app/sispharma/imagens/group.png"))); // NOI18N
        configuracaoUsuariosjMenuItem1.setText("Usuários");
        configuracaoUsuariosjMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                configuracaoUsuariosjMenuItem1ActionPerformed(evt);
            }
        });
        configuracaojMenu.add(configuracaoUsuariosjMenuItem1);

        jMenuBar1.add(configuracaojMenu);

        ajudajMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/system/app/sispharma/imagens/help.png"))); // NOI18N
        ajudajMenu.setText("Ajuda");
        ajudajMenu.setFont(new java.awt.Font("Tahoma", 0, 14));

        jMenuItem1.setFont(new java.awt.Font("Tahoma", 0, 12));
        jMenuItem1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/system/app/sispharma/imagens/text_indent.png"))); // NOI18N
        jMenuItem1.setText("Tutorial SISPHARMA");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        ajudajMenu.add(jMenuItem1);

        jMenuItem2.setFont(new java.awt.Font("Tahoma", 0, 12));
        jMenuItem2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/system/app/sispharma/imagens/wrench.png"))); // NOI18N
        jMenuItem2.setText("Suporte técnico");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        ajudajMenu.add(jMenuItem2);

        jMenuBar1.add(ajudajMenu);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1005, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 724, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void cadastroProdutojMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cadastroProdutojMenuItemActionPerformed

    JanelaProduto dialog = new JanelaProduto(new javax.swing.JFrame(), true);
    dialog.setVisible(true);

}//GEN-LAST:event_cadastroProdutojMenuItemActionPerformed

private void cadastroLaboratoriojMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cadastroLaboratoriojMenuItemActionPerformed

    JanelaCadastrarLaboratorio dialog = new JanelaCadastrarLaboratorio(new javax.swing.JFrame(), true);
    dialog.setVisible(true);

}//GEN-LAST:event_cadastroLaboratoriojMenuItemActionPerformed

private void cadastroUsuariojMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cadastroUsuariojMenuItemActionPerformed

    JanelaCadastrarUsuario dialog = new JanelaCadastrarUsuario(new javax.swing.JFrame(), true);
    dialog.setVisible(true);

}//GEN-LAST:event_cadastroUsuariojMenuItemActionPerformed

private void movimentacaoVendajMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_movimentacaoVendajMenuItemActionPerformed

    JanelaVenda dialog = new JanelaVenda(new javax.swing.JFrame(), true);
    dialog.setVisible(true);

}//GEN-LAST:event_movimentacaoVendajMenuItemActionPerformed

private void movimentacaoEntradajMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_movimentacaoEntradajMenuItemActionPerformed

    JanelaPesquisarProduto dialog = new JanelaPesquisarProduto(new javax.swing.JFrame(), true);
    dialog.tipoJanela(1);
    dialog.setVisible(true);

}//GEN-LAST:event_movimentacaoEntradajMenuItemActionPerformed

private void centralRelatoriosjMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_centralRelatoriosjMenuItemActionPerformed

    JanelaRelatorio dialog = new JanelaRelatorio(new javax.swing.JFrame(), true);
    dialog.setVisible(true);

}//GEN-LAST:event_centralRelatoriosjMenuItemActionPerformed

private void configuracaoUsuariosjMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_configuracaoUsuariosjMenuItem1ActionPerformed

    JOptionPane.showMessageDialog(null, "Janela em construção!", "SISPHARMA - Aviso", 0);

}//GEN-LAST:event_configuracaoUsuariosjMenuItem1ActionPerformed

private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed

    JOptionPane.showMessageDialog(null, "Janela em construção!", "SISPHARMA - Aviso", 0);

}//GEN-LAST:event_jMenuItem1ActionPerformed

private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed

    JOptionPane.showMessageDialog(null, "Janela em construção!", "SISPHARMA - Aviso", 0);

}//GEN-LAST:event_jMenuItem2ActionPerformed

private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed

    System.exit(0);

}//GEN-LAST:event_jMenuItem3ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu ajudajMenu;
    private javax.swing.JMenuItem cadastroLaboratoriojMenuItem;
    private javax.swing.JMenuItem cadastroProdutojMenuItem;
    private javax.swing.JMenuItem cadastroUsuariojMenuItem;
    private javax.swing.JMenu cadastrojMenu;
    private javax.swing.JMenuItem centralRelatoriosjMenuItem;
    private javax.swing.JMenuItem configuracaoUsuariosjMenuItem1;
    private javax.swing.JMenu configuracaojMenu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem movimentacaoEntradajMenuItem;
    private javax.swing.JMenuItem movimentacaoVendajMenuItem;
    private javax.swing.JMenu movimentacaojMenu;
    private javax.swing.JMenu relatoriojMenu;
    // End of variables declaration//GEN-END:variables
}
