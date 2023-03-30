package org.barril;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Main {
    public static void main(String[] args) {
        Telinha telinha = new Telinha();
        telinha.setVisible(true);
        telinha.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);

        WindowListener wl = new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int retorno = JOptionPane.showConfirmDialog(null, "quer fechar mermo??????", "ceertezas", JOptionPane.YES_NO_OPTION);
                if(retorno == 0) {
//                    telinha.dispose();
                    System.exit(0);
                }
            }
        };
        telinha.addWindowListener(wl);
    }
}