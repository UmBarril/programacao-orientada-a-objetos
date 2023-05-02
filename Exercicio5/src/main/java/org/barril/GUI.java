package org.barril;

import javafx.scene.media.MediaPlayer;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class GUI {
    private JFrame janela;
    public Tocador tocador;
    private JLabel imagemLbl = new JLabel();
    private JButton botaoPlay = new JButton();
    public GUI() {
        tocador = new Tocador();

        tocador.setAoFimDaMusica(() -> {
            tocador.proximaMusica();
            if(!tocador.tocarMusica()) {
                atualizarImagemETexto(false);
            };
        });

        janela = new JFrame();
        janela.setTitle("TOCADOR DE MOOOOSICAS");

        janela.setIconImage(new ImageIcon(getResource("doge.png")).getImage());
        janela.setLayout(new GridLayout(1, 2));
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setSize(500,500);

        JPanel esquerda = new JPanel();
        esquerda.setLayout(new GridLayout(2,1));
        esquerda.add(criarPainelSuperior());
        esquerda.add(criarPainelInferior());

        JPanel direita = new JPanel();
        direita.setLayout(new GridLayout(2,1));
        direita.add(imagemLbl);
        direita.add(criarPainelInferiorDireito());

        janela.add(esquerda);
        janela.add(direita);
    }
    public URL getResource(String nome) {
        return getClass().getClassLoader().getResource(nome);
    }
    public void atualizarImagemETexto(boolean tocando) {
        if(tocando) {
            imagemLbl.setIcon(new ImageIcon(getResource("rsz_ovino_moosica.jpg")));
            imagemLbl.setIcon(new ImageIcon(getResource("rsz-da-rock.gif")));
            botaoPlay.setText("Pausar musica");
        } else {
            imagemLbl.setIcon(new ImageIcon(getResource("rsz_not_music.jpg")));
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
            } else if(tocador.getStatusAtual() != MediaPlayer.Status.PLAYING){
                if(!tocador.tocarMusica()) {
                    JOptionPane.showMessageDialog(null, "Não há mais músicas na fila.");
                }
                atualizarImagemETexto(true);
            } else {
                try {
                    tocador.pausarMusica();
                } catch (NenhumaMusicaNaFilaException ex) {
                    throw new RuntimeException(ex);
                }
                atualizarImagemETexto(false);
            }
        });
        botaoPlay.setBackground(Color.DARK_GRAY);
        botaoPlay.setForeground(Color.WHITE);
        atualizarImagemETexto(false);

        JButton botaoSalvar = new JButton("Salvar musica");
        botaoSalvar.addActionListener(e -> {
            try {
                Tocador.salvarMusica(
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
        JButton addbtn = new JButton("<html><center><b>Adicionar música da internet</b><br>");
        addbtn.addActionListener(e -> {
            String linkDaMusica = JOptionPane.showInputDialog("Link da moosica");
            tocador.adicionarMusica(linkDaMusica);
            atualizarImagemETexto(false);
        });
        addbtn.setBackground(Color.DARK_GRAY);
        addbtn.setForeground(Color.WHITE);

        JButton addCaminhoBtn = new JButton("<html><center><b>Adicionar música localmente</b><br>");
        addCaminhoBtn .addActionListener(e -> {
            String linkDaMusica = "file://" + JOptionPane.showInputDialog("Caminho da moosica").replace('\\', '/');
            tocador.adicionarMusica(linkDaMusica);
            atualizarImagemETexto(false);
        });
        addCaminhoBtn.setBackground(Color.DARK_GRAY);
        addCaminhoBtn.setForeground(Color.WHITE);

        JPanel painelEsquerda = new JPanel();
        painelEsquerda.setLayout(new GridLayout(2, 1));
        painelEsquerda.add(addbtn);
        painelEsquerda.add(addCaminhoBtn);

        JButton rmbtn = new JButton("Pular música");
        rmbtn.addActionListener(e -> {
            if(!tocador.proximaMusica()) {
                JOptionPane.showMessageDialog(null, "Não há mais músicas na fila.");
                atualizarImagemETexto(false);
            }
        });
        rmbtn.setBackground(Color.DARK_GRAY);
        rmbtn.setForeground(Color.WHITE);

        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(1, 2));
        painel.add(painelEsquerda);
        painel.add(rmbtn);

        return painel;
    }
    public JPanel criarPainelInferior() {
        JButton qbtn = new JButton("Mostrar fila");
        qbtn.addActionListener(e -> JOptionPane.showMessageDialog(null, tocador.getFilaComoString()));
        qbtn.setBackground(Color.DARK_GRAY);
        qbtn.setForeground(Color.WHITE);

        JButton upbtn = new JButton("Vol +");
        upbtn.addActionListener(e -> {
            double volumeAtual = tocador.getVolume();
            tocador.setVolume(volumeAtual <= 100 ? volumeAtual + 10 : volumeAtual);
        });
        upbtn.setBackground(Color.DARK_GRAY);
        upbtn.setForeground(Color.RED);

        JButton mutebtn = new JButton("Mute");
        mutebtn.addActionListener(e -> tocador.mutaDesmuta());
        mutebtn.setBackground(Color.DARK_GRAY);
        mutebtn.setForeground(Color.WHITE);

        JButton downbtn = new JButton("Vol -");
        downbtn .addActionListener(e -> {
            double volumeAtual = tocador.getVolume();
            tocador.setVolume(volumeAtual >= 0 ? volumeAtual - 10 : volumeAtual);
        });
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
