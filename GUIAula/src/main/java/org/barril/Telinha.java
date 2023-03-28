package org.barril;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Telinha extends JFrame {
    public Telinha() {
        setTitle("TELINHA :)");

        setSize(720, 720);
        setResizable(true);
        getContentPane().setBackground(Color.DARK_GRAY);

        JLabel lbl = new JLabel("olá :)", JLabel.CENTER);
        lbl.setFont(new Font("Arial", Font.BOLD, 100));
        lbl.setForeground(Color.WHITE);

        ImageIcon daRockIcon = new ImageIcon("./da-rock-shitpost.gif");
        JLabel daRock = new JLabel(daRockIcon);

        JButton btn = new JButton("cliqueeeeee");
        btn.addActionListener(new ActionListener() {
            boolean ativado = true;
            @Override
            public void actionPerformed(ActionEvent e) {
                  if(ativado) {
                      lbl.setText("tchau :(");
                  } else {
                      lbl.setText("olá :)");
                  }
                  ativado = !ativado;
                }
            }
        );
        setLayout(new FlowLayout());
        add(lbl);
        add(daRock);
        add(btn);
    }
}
