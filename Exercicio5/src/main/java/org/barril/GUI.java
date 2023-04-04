package org.barril;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class GUI extends JFrame {
    public GUI() {
        setTitle("");
        setLayout(new GridLayout(2, 2));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,500);

        Button addbtn = new Button("adicionar musica");
        addbtn.setBackground(Color.RED);

        Button rmbtn = new Button("remover musica");
        rmbtn.setBackground(Color.RED);

        JLabel lblImg = new JLabel();
        try {
            BufferedImage img = ImageIO.read(new File("ovino_moosica.jpg"));
            ImageIcon ico = new ImageIcon(img.getScaledInstance(250, 250, Image.SCALE_SMOOTH));
            lblImg.setIcon(ico);
        } catch (IOException e) {
            lblImg.setText("se vc esta vendo isso, deu ruim na imagem.");
        }

        add(rmbtn);
        add(addbtn);
        add(lblImg);
    }
}
