package org.barril;

import javafx.scene.media.MediaPlayer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GUI {
    private JFrame janela;
    public Tocador tocador;
    private JLabel imagemSuperiorEsquerda = new JLabel();
    private JButton botaoPlay = new JButton();
    public GUI() {
        tocador = new Tocador();

        tocador.setAoFimDaMusica(() -> {
            tocador.proximaMusica();
            tocador.tocarMusica();
        });

        janela = new JFrame();
        janela.setTitle("TOCADOR DE MOOOOSICAS");

        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("src/main/resources/doge.png"));
            ImageIcon icon = new ImageIcon(img);
            janela.setIconImage(icon.getImage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        janela.setLayout(new GridLayout(1, 2));
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setSize(500,500);

        JPanel esquerda = new JPanel();
        esquerda.setLayout(new GridLayout(2,1));
        esquerda.add(criarPainelSuperior());
        esquerda.add(criarPainelInferior());

        JPanel direita = new JPanel();
        direita.setLayout(new GridLayout(2,1));
        direita.add(imagemSuperiorEsquerda);
        direita.add(criarPainelInferiorDireito());

        janela.add(esquerda);
        janela.add(direita);
    }
    public void mudarImagem(String caminho) {
        try {
            BufferedImage img = ImageIO.read(new File(caminho));
            ImageIcon ico = new ImageIcon(img.getScaledInstance(250, 250, Image.SCALE_SMOOTH));
            imagemSuperiorEsquerda.setIcon(ico);
            imagemSuperiorEsquerda.setName(caminho);
        } catch (IOException exp) {
            imagemSuperiorEsquerda.setText("se vc esta vendo isso, deu ruim na imagem.");
            System.out.println("erro: " + exp);
        }
    }
    public void atualizarImagemETexto() {
        if(tocador.getStatusAtual() == MediaPlayer.Status.PLAYING){
            String caminho = "src/main/resources/ovino_moosica.jpg";
            if(imagemSuperiorEsquerda.getName() == null || !imagemSuperiorEsquerda.getName().equals(caminho)) {
                mudarImagem(caminho);
            }
            botaoPlay.setText("Pausar musica");
        } else {
            String caminho = "src/main/resources/not_music.jpg";
            if(imagemSuperiorEsquerda.getName() == null || !imagemSuperiorEsquerda.getName().equals(caminho)) {
                mudarImagem(caminho);
            }
            if(tocador.getStatusAtual() == MediaPlayer.Status.STALLED) {
                botaoPlay.setText("<html>Não há mais músicas na fila.<br> Adicione!<html>");
            } else {
                botaoPlay.setText("Tocar música");
            }
        }
    }
    public JPanel criarPainelInferiorDireito() {
        botaoPlay.addActionListener(e -> {
            if(tocador.getStatusAtual() == MediaPlayer.Status.STALLED){
                JOptionPane.showMessageDialog(null, "Você precisa adicionar músicas na fila primeiro!");
                return;
            } else if(tocador.getStatusAtual() != MediaPlayer.Status.PLAYING){
                if(!tocador.tocarMusica()) {
                    JOptionPane.showMessageDialog(null, "Não há mais músicas na fila.");
                }
            } else {
                try {
                    tocador.pausarMusica();
                } catch (NenhumaMusicaNaFilaException ex) {
                    throw new RuntimeException(ex);
                }
            }
            atualizarImagemETexto();
        });
        botaoPlay.setBackground(Color.DARK_GRAY);
        botaoPlay.setForeground(Color.WHITE);
        atualizarImagemETexto();

        JButton botaoSalvar = new JButton("Salvar musica");
        botaoSalvar.addActionListener(e -> {
            try {
                SalvaMusica.salvar(
                        JOptionPane.showInputDialog(null, "Escreva a URL da música."),
                        JOptionPane.showInputDialog(null, "Escreva o caminho completo para salvar a música (incluido o nome e a extensão do arquivo a ser salvo)")
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        botaoSalvar.setBackground(Color.DARK_GRAY);
        botaoSalvar.setForeground(Color.WHITE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1,2));
        panel.add(botaoPlay);
        panel.add(botaoSalvar);
        return panel;
    }
    public JPanel criarPainelSuperior() {
        JButton addbtn = new JButton("<html><center><b>Adicionar música</b><br>");
        addbtn.addActionListener(e -> {
            String linkDaMusica = JOptionPane.showInputDialog("link da moosica");
            tocador.adicionarMusica(linkDaMusica);
            atualizarImagemETexto();
        });
        addbtn.setBackground(Color.DARK_GRAY);
        addbtn.setForeground(Color.WHITE);

        JButton rmbtn = new JButton("Pular música");
        rmbtn.addActionListener(e -> {
            if(!tocador.proximaMusica()) {
                JOptionPane.showMessageDialog(null, "Não há mais músicas na fila.");
                atualizarImagemETexto();
            }
        });
        rmbtn.setBackground(Color.DARK_GRAY);
        rmbtn.setForeground(Color.WHITE);

        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(1, 2));

        painel.add(addbtn);
        painel.add(rmbtn);

        return painel;
    }
    public JPanel criarPainelInferior() {
        JButton qbtn = new JButton("Mostra fila");
        qbtn.addActionListener(e -> JOptionPane.showMessageDialog(null, tocador.getFilaComoString()));
        qbtn.setBackground(Color.DARK_GRAY);
        qbtn.setForeground(Color.WHITE);

        JButton upbtn = new JButton("Vol +");
        upbtn.addActionListener(e -> tocador.setVolume(tocador.getVolume() + 10));
        upbtn.setBackground(Color.DARK_GRAY);
        upbtn.setForeground(Color.RED);

        JButton mutebtn = new JButton("Mute");
        mutebtn .addActionListener(e -> tocador.mutaDesmuta());
        mutebtn .setBackground(Color.DARK_GRAY);
        mutebtn.setForeground(Color.WHITE);

        JButton downbtn = new JButton("Vol -");
        downbtn .addActionListener(e -> tocador.setVolume(tocador.getVolume() - 10));
        downbtn.setBackground(Color.DARK_GRAY);
        downbtn.setForeground(Color.BLUE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));
        panel.add(qbtn);
        panel.add(upbtn);
        panel.add(mutebtn);
        panel.add(downbtn);
        return panel;
    }
    public void setVisible(boolean b) {
        janela.setVisible(b);
    }
}
