package org.barril;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class GUI {
    private JFrame janela;
    private Queue<Moosica> moosicas = new LinkedList<>();
    private boolean tocano = true;
    private JLabel lblImg = new JLabel();
    public GUI() {
        janela = new JFrame();
        janela.setTitle("");
        janela.setLayout(new GridLayout(2, 1));
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setSize(500,500);

        JButton addbtn = new JButton("<html><center><b>Adicionar música</b><br>");
        addbtn.addActionListener(e -> {
            String nomeDaMusica = JOptionPane.showInputDialog("nome da moosica");
            String linkDaMusica = JOptionPane.showInputDialog("link da moosica");
            moosicas.add(new Moosica(nomeDaMusica, linkDaMusica));
        });
        addbtn.setBackground(Color.DARK_GRAY);
        addbtn.setForeground(Color.WHITE);

        JButton rmbtn = new JButton("remover música do topo");
        rmbtn.addActionListener(e -> moosicas.poll());
        rmbtn.setBackground(Color.DARK_GRAY);
        rmbtn.setForeground(Color.WHITE);

        JButton playbtn = new JButton("tocar musica");
        playbtn.addActionListener(e -> toggleMoosica());
        playbtn.setBackground(Color.RED);
        playbtn.setForeground(Color.WHITE);

        janela.add(rmbtn);
        janela.add(addbtn);
        janela.add(lblImg);
        janela.add(playbtn);

        toggleMoosica();
    }
    private void toggleMoosica() {
        tocano = !tocano;
        try {
            String img_path = tocano ? "ovino_moosica.jpg" : "not_music.jpg";

            BufferedImage img = ImageIO.read(new File(img_path));
            ImageIcon ico = new ImageIcon(img.getScaledInstance(250, 250, Image.SCALE_SMOOTH));
            lblImg.setIcon(ico);
        } catch (IOException exp) {
            lblImg.setText("se vc esta vendo isso, deu ruim na imagem.");
            System.out.println("erro: " + exp);
        }
    }
    public void setVisible(boolean b) {
        janela.setVisible(b);
    }
}
