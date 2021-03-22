package com.system.app.sispharma.main;

//import ch.randelshofer.quaqua.QuaquaLookAndFeel;
import com.system.app.sispharma.ui.frame.JanelaPrincipal;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {

    public static void main(String[] args) {
        //setLookAndFeelQuaqua();
        new JanelaPrincipal().setVisible(true);
    }
/*
    private static void setLookAndFeelQuaqua() {
        try {
            UIManager.setLookAndFeel(new QuaquaLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }
 */
}
