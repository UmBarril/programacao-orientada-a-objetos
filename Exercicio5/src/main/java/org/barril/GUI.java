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

        janela.add(criarPainelSuperiorEsquerdo());

        JButton playbtn = new JButton("Tocar música");
        playbtn.addActionListener(e -> {
            tocano = !tocano;
            if(tocano){
                mudarImagem("src/main/resources/ovino_moosica.jpg");
                playbtn.setText("pausar musica");
                playbtn.setBackground(Color.RED);
            } else {
                mudarImagem("src/main/resources/not_music.jpg");
                playbtn.setText("tocar musica");
                playbtn.setBackground(new Color(57, 173, 2, 255));
            }
        });
        playbtn.setForeground(Color.WHITE);
        playbtn.doClick(); // initialize button

        JButton qbtn = new JButton("Mostra fila");
        qbtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, moosicas.toString());
        });

        janela.add(lblImg);
        janela.add(playbtn);
    }
    public void mudarImagem(String caminho) {
        try {
            BufferedImage img = ImageIO.read(new File(caminho));
            ImageIcon ico = new ImageIcon(img.getScaledInstance(250, 250, Image.SCALE_SMOOTH));
            lblImg.setIcon(ico);
        } catch (IOException exp) {
            lblImg.setText("se vc esta vendo isso, deu ruim na imagem.");
            System.out.println("erro: " + exp);
        }
    }
    public JPanel criarPainelSuperiorEsquerdo() {
        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(1, 2));

        JButton addbtn = new JButton("<html><center><b>Adicionar música</b><br>");
        addbtn.addActionListener(e -> {
            String nomeDaMusica = JOptionPane.showInputDialog("nome da moosica");
            String linkDaMusica = JOptionPane.showInputDialog("link da moosica");
            moosicas.add(new Moosica(nomeDaMusica, linkDaMusica));
        });
        addbtn.setBackground(Color.DARK_GRAY);
        addbtn.setForeground(Color.WHITE);

        JButton rmbtn = new JButton("Pular música");
        rmbtn.addActionListener(e -> moosicas.poll());
        rmbtn.setBackground(Color.DARK_GRAY);
        rmbtn.setForeground(Color.WHITE);

        painel.add(addbtn);
        painel.add(rmbtn);

        return painel;
    }
    public void setVisible(boolean b) {
        janela.setVisible(b);
    }
}
