package com.system.app.sispharma.ui.dialog;

import com.system.app.sispharma.entidades.Laboratorio;
import com.system.app.sispharma.entidades.Produto;
import com.system.app.sispharma.entidades.Usuario;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.util.Vector;
import javax.swing.JOptionPane;

public class JanelaCadastrarProduto extends javax.swing.JDialog {

    Produto prod = new Produto();
    Laboratorio lab = new Laboratorio();
    Usuario usuario = new Usuario();
    Vector<String> laboratorios = new Vector<String>();
    int err = 0;
    int tipo = 0;
    DecimalFormat df = new DecimalFormat("0.00");

    public JanelaCadastrarProduto(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        carregarLaboratorios();
        setLocationRelativeTo(null);
        codjTextField.setText(String.valueOf(prod.retornaCodigo()));
    }

    public int tipoJanela(int tip, String codigo) {
        Produto prod5 = new Produto();
        this.tipo = tip;
        if (tipo == 0) {
            if (String.valueOf(codigo).length() == 13) {
                prod5 = prod5.retornarProdutoCodigoBarra(String.valueOf(codigo));
                if (prod5.getDescricao() == null) {
                    return 999;
                }
            } else {
                prod5 = prod5.retornarProdutoCodigo(Integer.parseInt(codigo));
                if (prod5.getDescricao() == null) {
                    return 999;
                }
            }

            codigoBarrajTextField.transferFocus();
            codjTextField.setText(String.valueOf(prod5.getCodigo()));
            codigoBarrajTextField.setText(prod5.getCodigo_barra());
            descricaojTextField.setText(prod5.getDescricao());
            laboratoriojComboBox.setSelectedIndex(prod5.getLaboratorio());
            unidadejComboBox.setSelectedItem(prod5.getUnidade());
            valorjFormattedTextField.setText(df.format(prod5.getValor()));
            validadejFormattedTextField.setText(prod5.getValidade());
            quantidadeMinimajTextField.setText(String.valueOf(prod5.getQtd_minima()));
            estoqueAtualjLabel.setText(String.valueOf(prod5.getQtd_produto()));
            quantidadejTextField.setEnabled(false);
            return 000;
        } else if (tipo == 1) {
            if (String.valueOf(codigo).length() == 13) {
                prod5 = prod5.retornarProdutoCodigoBarra(String.valueOf(codigo));
                if (prod5.getDescricao() == null) {
                    return 999;
                }
            } else {
                prod5 = prod5.retornarProdutoCodigo(Integer.parseInt(codigo));
                if (prod5.getDescricao() == null) {
                    return 999;
                }
            }

            codigoBarrajTextField.transferFocus();
            codjTextField.setText(String.valueOf(prod5.getCodigo()));
            codigoBarrajTextField.setText(prod5.getCodigo_barra());
            descricaojTextField.setText(prod5.getDescricao());
            laboratoriojComboBox.setSelectedIndex(prod5.getLaboratorio());
            unidadejComboBox.setSelectedItem(prod5.getUnidade());
            valorjFormattedTextField.setText(df.format(prod5.getValor()));
            validadejFormattedTextField.setText(prod5.getValidade());
            quantidadeMinimajTextField.setText(String.valueOf(prod5.getQtd_minima()));
            estoqueAtualjLabel.setText(String.valueOf(prod5.getQtd_produto()));
            return 000;
        } else if (tipo == 2) {
            codjTextField.setText(String.valueOf(prod5.retornaCodigo()));
            return 000;
        }
        return 000;
    }

    private String removeVirgula(String valor) {
        String novoValor = "0.0";
        for (int i = 0; i < valor.length(); i++) {
            novoValor = valor.replace(',', '.');
        }
        return novoValor;
    }

    private void carregarLaboratorios() {
        laboratorios.clear();
        laboratoriojComboBox.removeAllItems();
        laboratorios = lab.retornaLaboratorios();
        int t = 0;
        while (t < laboratorios.size()) {
            laboratoriojComboBox.addItem(laboratorios.elementAt(t));
            t++;
        }
    }

    private boolean capturarDados() {
        try {
            if (descricaojTextField.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Preencha o campo descrição!", "SISPHARMA - Aviso", 2);
                err = 1;
                return false;
            }

            if (valorjFormattedTextField.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Preencha o campo valor!", "SISPHARMA - Aviso", 2);
                err = 1;
                return false;
            }

            if (validadejFormattedTextField.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Preencha o campo validade!", "SISPHARMA - Aviso", 2);
                err = 1;
                return false;
            }
            /*
            prod.setCodigo(Integer.parseInt(codjTextField.getText()));

            if (codigoBarrajTextField.getText().equals("")) {
            prod.setCodigo_barra(codjTextField.getText());
            } else {
            prod.setCodigo_barra(codigoBarrajTextField.getText());
            }

            prod.setDescricao(descricaojTextField.getText().toUpperCase());
            prod.setLaboratorio(laboratoriojComboBox.getSelectedIndex());
            prod.setUnidade(unidadejComboBox.getSelectedItem().toString());
            prod.setValor(Double.parseDouble(valorjFormattedTextField.getText()));
            prod.setValidade(validadejFormattedTextField.getText());
            prod.setQtd_minima(Double.parseDouble(quantidadeMinimajTextField.getText()));
            if (!quantidadejTextField.getText().equals("")) {
            prod.setQtd_produto(Double.parseDouble(quantidadejTextField.getText()));
            } else {
            prod.setQtd_produto(0.0);
            }
             */
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    private void limparCampos() {
        //Produto prod3 = new Produto();
        codjTextField.setText(String.valueOf(prod.retornaCodigo()));
        codigoBarrajTextField.setText("");
        descricaojTextField.setText("");
        laboratoriojComboBox.setSelectedIndex(0);
        unidadejComboBox.setSelectedIndex(0);
        quantidadejTextField.setText("0");
        valorjFormattedTextField.setText("0,00");
        validadejFormattedTextField.setText("");
        quantidadeMinimajTextField.setText("10");
        estoqueAtualjLabel.setText("0");
    }

    private void salvarProduto() {
        String retorno = "OK";
        //Produto prod2 = new Produto();
        if (capturarDados() == true) {
            retorno = prod.inserirProduto(usuario.retornaChaveUsuario(usuario.getSenha()), Integer.parseInt(codjTextField.getText()), codigoBarrajTextField.getText(), descricaojTextField.getText().toUpperCase(), laboratoriojComboBox.getSelectedIndex(), unidadejComboBox.getSelectedItem().toString(), Double.parseDouble(removeVirgula(valorjFormattedTextField.getText())), Double.parseDouble(removeVirgula(quantidadejTextField.getText())), validadejFormattedTextField.getText(), Double.parseDouble(removeVirgula(quantidadeMinimajTextField.getText())));
            if (retorno.equals("OK")) {
                JOptionPane.showMessageDialog(null, "Operação realizada com sucesso", "SISPHARMA - Mensagem", 1);
                limparCampos();
                quantidadeMinimajTextField.transferFocus();
            } else {
                JOptionPane.showMessageDialog(null, retorno, "SISPHARMA - Aviso", 2);
                return;
            }
        } else {
            if (err == 0) {
                JOptionPane.showMessageDialog(null, "Erro ao capturar dados!", "SISPHARMA - Aviso", 2);
                err = 0;
                return;
            }
        }
    }

    private void entradaProduto() {
        String retorno = "OK";
        Produto prod2 = new Produto();

        if (capturarDados() == true) {
            retorno = prod2.entradaProduto(Integer.parseInt(codjTextField.getText()), usuario.retornaChaveUsuario(usuario.getSenha()), codigoBarrajTextField.getText(), descricaojTextField.getText().toUpperCase(), laboratoriojComboBox.getSelectedIndex(), unidadejComboBox.getSelectedItem().toString(), Double.parseDouble(removeVirgula(valorjFormattedTextField.getText())), Double.parseDouble(removeVirgula(quantidadejTextField.getText())), validadejFormattedTextField.getText(), Double.parseDouble(removeVirgula(quantidadeMinimajTextField.getText())));
            if (retorno.equals("OK")) {
                JOptionPane.showMessageDialog(null, "Operação realizada com sucesso", "SISPHARMA - Mensagem", 1);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(null, retorno, "SISPHARMA - Aviso", 2);
                return;
            }
        } else {
            if (err == 0) {
                JOptionPane.showMessageDialog(null, "Erro ao capturar dados!", "SISPHARMA - Aviso", 2);
                err = 0;
                return;
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        informacoesjPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        codigoBarrajTextField = new javax.swing.JTextField();
        descricaojTextField = new javax.swing.JTextField();
        laboratoriojComboBox = new javax.swing.JComboBox();
        unidadejComboBox = new javax.swing.JComboBox();
        novoLabjButton = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        valorjFormattedTextField = new javax.swing.JFormattedTextField();
        jLabel8 = new javax.swing.JLabel();
        validadejFormattedTextField = new javax.swing.JFormattedTextField();
        jLabel9 = new javax.swing.JLabel();
        quantidadeMinimajTextField = new javax.swing.JTextField();
        codjTextField = new javax.swing.JTextField();
        entradajPanel = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        quantidadejTextField = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        estoqueAtualjLabel = new javax.swing.JLabel();
        salvarjButton = new javax.swing.JButton();
        cancelarjButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("SISPHARMA - Cadastrar Produto");
        setResizable(false);

        informacoesjPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Informações", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14))); // NOI18N

        jLabel1.setText("CÓDIGO:");

        jLabel2.setText("CÓDIGO DE BARRA:");

        jLabel3.setText("DESCRIÇÃO:");

        jLabel4.setText("LABORATÓRIO:");

        jLabel5.setText("UNIDADE:");

        codigoBarrajTextField.setFont(new java.awt.Font("Tahoma", 0, 12));
        codigoBarrajTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                codigoBarrajTextFieldKeyPressed(evt);
            }
        });

        descricaojTextField.setFont(new java.awt.Font("Tahoma", 0, 12));
        descricaojTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                descricaojTextFieldKeyPressed(evt);
            }
        });

        laboratoriojComboBox.setFont(new java.awt.Font("Tahoma", 0, 12));
        laboratoriojComboBox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                laboratoriojComboBoxMouseClicked(evt);
            }
        });
        laboratoriojComboBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                laboratoriojComboBoxKeyPressed(evt);
            }
        });

        unidadejComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "UNI", "CX", "ENV", "FT" }));
        unidadejComboBox.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                unidadejComboBoxMouseClicked(evt);
            }
        });
        unidadejComboBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                unidadejComboBoxKeyPressed(evt);
            }
        });

        novoLabjButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/system/app/sispharma/imagens/add.png"))); // NOI18N
        novoLabjButton.setText("NOVO");
        novoLabjButton.setFocusable(false);
        novoLabjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                novoLabjButtonActionPerformed(evt);
            }
        });

        jLabel7.setText("VALOR:");

        jLabel10.setText("R$");

        valorjFormattedTextField.setText("0,00");
        valorjFormattedTextField.setFont(new java.awt.Font("Tahoma", 0, 12));
        valorjFormattedTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                valorjFormattedTextFieldKeyPressed(evt);
            }
        });

        jLabel8.setText("VALIDADE:");

        try {
            validadejFormattedTextField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("##/##/####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        validadejFormattedTextField.setFont(new java.awt.Font("Tahoma", 0, 12));
        validadejFormattedTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                validadejFormattedTextFieldKeyPressed(evt);
            }
        });

        jLabel9.setText("QUANTIDADE MÍNIMA:");

        quantidadeMinimajTextField.setFont(new java.awt.Font("Tahoma", 0, 12));
        quantidadeMinimajTextField.setText("10");
        quantidadeMinimajTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                quantidadeMinimajTextFieldKeyPressed(evt);
            }
        });

        codjTextField.setFont(new java.awt.Font("Tahoma", 0, 12));
        codjTextField.setEnabled(false);

        javax.swing.GroupLayout informacoesjPanelLayout = new javax.swing.GroupLayout(informacoesjPanel);
        informacoesjPanel.setLayout(informacoesjPanelLayout);
        informacoesjPanelLayout.setHorizontalGroup(
            informacoesjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(informacoesjPanelLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(informacoesjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(informacoesjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(informacoesjPanelLayout.createSequentialGroup()
                        .addComponent(codjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(codigoBarrajTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE))
                    .addComponent(descricaojTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 335, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, informacoesjPanelLayout.createSequentialGroup()
                        .addComponent(laboratoriojComboBox, 0, 248, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(novoLabjButton))
                    .addGroup(informacoesjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(unidadejComboBox, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, informacoesjPanelLayout.createSequentialGroup()
                            .addComponent(jLabel10)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(informacoesjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(validadejFormattedTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                                .addComponent(valorjFormattedTextField)
                                .addComponent(quantidadeMinimajTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)))))
                .addGap(34, 34, 34))
        );
        informacoesjPanelLayout.setVerticalGroup(
            informacoesjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(informacoesjPanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(informacoesjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(codigoBarrajTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1)
                    .addComponent(codjTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(informacoesjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(descricaojTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(informacoesjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(laboratoriojComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(novoLabjButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(informacoesjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(unidadejComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(informacoesjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(valorjFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(informacoesjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(validadejFormattedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(informacoesjPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(quantidadeMinimajTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        informacoesjPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {descricaojTextField, laboratoriojComboBox, novoLabjButton, unidadejComboBox, validadejFormattedTextField, valorjFormattedTextField});

        informacoesjPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {codigoBarrajTextField, codjTextField});

        entradajPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Entrada", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Times New Roman", 1, 14))); // NOI18N

        jLabel6.setText("QUANTIDADE:");

        quantidadejTextField.setFont(new java.awt.Font("Tahoma", 0, 12));
        quantidadejTextField.setText("0");
        quantidadejTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                quantidadejTextFieldKeyPressed(evt);
            }
        });

        jLabel11.setText("ESTOQUE ATUAL:");

        estoqueAtualjLabel.setFont(new java.awt.Font("Times New Roman", 1, 36));
        estoqueAtualjLabel.setText("0.0");

        javax.swing.GroupLayout entradajPanelLayout = new javax.swing.GroupLayout(entradajPanel);
        entradajPanel.setLayout(entradajPanelLayout);
        entradajPanelLayout.setHorizontalGroup(
            entradajPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, entradajPanelLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(quantidadejTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 91, Short.MAX_VALUE)
                .addGap(36, 36, 36)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(estoqueAtualjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );
        entradajPanelLayout.setVerticalGroup(
            entradajPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(entradajPanelLayout.createSequentialGroup()
                .addGroup(entradajPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(quantidadejTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(estoqueAtualjLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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

        cancelarjButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/system/app/sispharma/imagens/cancel.png"))); // NOI18N
        cancelarjButton.setText("CANCELAR");
        cancelarjButton.setFocusable(false);
        cancelarjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarjButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(entradajPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(informacoesjPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(141, 141, 141)
                        .addComponent(salvarjButton)
                        .addGap(85, 85, 85)
                        .addComponent(cancelarjButton)))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cancelarjButton, salvarjButton});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(informacoesjPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(entradajPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelarjButton)
                    .addComponent(salvarjButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cancelarjButton, salvarjButton});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void novoLabjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_novoLabjButtonActionPerformed

        JanelaCadastrarLaboratorio dialog = new JanelaCadastrarLaboratorio(new javax.swing.JFrame(), true);
        dialog.setVisible(true);
        carregarLaboratorios();

    }//GEN-LAST:event_novoLabjButtonActionPerformed

    private void cancelarjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarjButtonActionPerformed

        this.dispose();

    }//GEN-LAST:event_cancelarjButtonActionPerformed

    private void salvarjButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salvarjButtonActionPerformed

        if (tipo == 0) {
            salvarProduto();
            salvarjButton.transferFocus();
        } else if (tipo == 1) {
            entradaProduto();
            salvarjButton.transferFocus();
        } else if (tipo == 2) {
            salvarProduto();
            salvarjButton.transferFocus();
        }

    }//GEN-LAST:event_salvarjButtonActionPerformed

    private void codigoBarrajTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_codigoBarrajTextFieldKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (tipo == 2 && !codigoBarrajTextField.getText().equals("")) {
                prod = prod.retornarProdutoCodigoBarra(codigoBarrajTextField.getText());
                if (prod.getDescricao() == null) {
                    codigoBarrajTextField.transferFocus();
                } else {
                    JOptionPane.showMessageDialog(null, "Produto já cadastrado!", "SISPHARMA - Aviso", 2);
                    return;
                }
            } else {
                codigoBarrajTextField.transferFocus();
            }
        }

    }//GEN-LAST:event_codigoBarrajTextFieldKeyPressed

    private void descricaojTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_descricaojTextFieldKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            descricaojTextField.transferFocus();
        }

    }//GEN-LAST:event_descricaojTextFieldKeyPressed

    private void quantidadejTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_quantidadejTextFieldKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            quantidadejTextField.transferFocus();
        }

    }//GEN-LAST:event_quantidadejTextFieldKeyPressed

    private void valorjFormattedTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_valorjFormattedTextFieldKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            valorjFormattedTextField.transferFocus();
        }

    }//GEN-LAST:event_valorjFormattedTextFieldKeyPressed

    private void validadejFormattedTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_validadejFormattedTextFieldKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            validadejFormattedTextField.transferFocus();
        }

    }//GEN-LAST:event_validadejFormattedTextFieldKeyPressed

    private void quantidadeMinimajTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_quantidadeMinimajTextFieldKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            quantidadeMinimajTextField.transferFocus();
        }

    }//GEN-LAST:event_quantidadeMinimajTextFieldKeyPressed

    private void salvarjButtonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_salvarjButtonKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            if (tipo == 0) {
                salvarProduto();
                salvarjButton.transferFocus();
            } else if (tipo == 1) {
                entradaProduto();
                salvarjButton.transferFocus();
            } else if (tipo == 2) {
                salvarProduto();
                salvarjButton.transferFocus();
            }
        }

    }//GEN-LAST:event_salvarjButtonKeyPressed

    private void laboratoriojComboBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_laboratoriojComboBoxKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            laboratoriojComboBox.transferFocus();
        }

    }//GEN-LAST:event_laboratoriojComboBoxKeyPressed

    private void unidadejComboBoxKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_unidadejComboBoxKeyPressed

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            unidadejComboBox.transferFocus();
        }

    }//GEN-LAST:event_unidadejComboBoxKeyPressed

    private void laboratoriojComboBoxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_laboratoriojComboBoxMouseClicked

        laboratoriojComboBox.transferFocus();

    }//GEN-LAST:event_laboratoriojComboBoxMouseClicked

    private void unidadejComboBoxMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_unidadejComboBoxMouseClicked

        unidadejComboBox.transferFocus();

    }//GEN-LAST:event_unidadejComboBoxMouseClicked
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelarjButton;
    private javax.swing.JTextField codigoBarrajTextField;
    private javax.swing.JTextField codjTextField;
    private javax.swing.JTextField descricaojTextField;
    private javax.swing.JPanel entradajPanel;
    private javax.swing.JLabel estoqueAtualjLabel;
    private javax.swing.JPanel informacoesjPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JComboBox laboratoriojComboBox;
    private javax.swing.JButton novoLabjButton;
    private javax.swing.JTextField quantidadeMinimajTextField;
    private javax.swing.JTextField quantidadejTextField;
    private javax.swing.JButton salvarjButton;
    private javax.swing.JComboBox unidadejComboBox;
    private javax.swing.JFormattedTextField validadejFormattedTextField;
    private javax.swing.JFormattedTextField valorjFormattedTextField;
    // End of variables declaration//GEN-END:variables
}
