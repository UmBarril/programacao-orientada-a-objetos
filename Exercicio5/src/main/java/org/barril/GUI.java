package org.barril;

import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class GUI {
    private final JFrame janela = new JFrame();
    private final Tocador tocador = new Tocador();
    private final JLabel imagemLbl = new JLabel();
    private final JButton botaoPlay = new JButton();
    private final JButton botaoSalvar = new JButton("<html>Salvar musica<br> a partir de uma URL</html>");
    private final JButton pularBtn = new JButton("Pular música");
    private final JButton mostraFilaBtn = new JButton("Mostrar fila");
    private final JButton volumeMaisBtn = new JButton("Vol +");
    private final JButton muteBtn = new JButton("Mutar");
    private final JButton volumeMenosBtn = new JButton("Vol -");
    private final JButton adicionarMusicaLocalBtn = new JButton("<html><center><b>Adicionar música localmente</b><br>");
    private final JButton adicionarMusicaURLBtn = new JButton("<html><center><b>Adicionar música da internet</b><br>");
    private final JButton carregarPlaylistBtn = new JButton("<html><center><b>Carregar playlist</b><br>");
    public URL getResource(String nome) {
        return getClass().getClassLoader().getResource(nome);
    }
    public GUI() {
        tocador.setAoFimDaMusica(() -> {
            tocador.proximaMusica();
            if(!tocador.tocarMusica()) {
                atualizarImagemETexto(MediaPlayer.Status.STALLED);
            }
        });
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
    public void atualizarImagemETexto(MediaPlayer.Status status) {
        if(status == MediaPlayer.Status.PLAYING) {
            imagemLbl.setIcon(new ImageIcon(getResource("rsz_ovino_moosica.jpg")));
            imagemLbl.setIcon(new ImageIcon(getResource("rsz-da-rock.gif")));
            botaoPlay.setText("Pausar musica");
        } else {
            imagemLbl.setIcon(new ImageIcon(getResource("rsz_not_music.jpg")));
            if(status == MediaPlayer.Status.STALLED) {
                botaoPlay.setText("<html>Não há mais músicas na fila.<br> Adicione!<html>");
            } else {
                botaoPlay.setText("Tocar música");
            }
        }
    }
    public JPanel criarPainelInferiorDireito() {
        botaoPlay.addActionListener(e -> {
            if(tocador.getStatus() == MediaPlayer.Status.STALLED) {
                JOptionPane.showMessageDialog(janela, "Você precisa adicionar músicas na fila primeiro!");
            } else if(tocador.getStatus() != MediaPlayer.Status.PLAYING) {
                if (!tocador.tocarMusica()) {
                    JOptionPane.showMessageDialog(janela, "Não há mais músicas na fila.");
                    return;
                }
                atualizarImagemETexto(MediaPlayer.Status.PLAYING);
            } else {
                try {
                    tocador.pausarMusica();
                } catch (NenhumaMusicaNaFilaException ex) {
                    throw new RuntimeException(ex);
                }
                atualizarImagemETexto(MediaPlayer.Status.PAUSED);
            }
        });
        botaoPlay.setBackground(Color.DARK_GRAY);
        botaoPlay.setForeground(Color.WHITE);
        atualizarImagemETexto(MediaPlayer.Status.STALLED);

        botaoSalvar.addActionListener(e -> {
            try {
                String url = JOptionPane.showInputDialog(janela, "Escreva a URL da música.");
                if (url == null) return;

                JFileChooser fc = new JFileChooser();
                fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                fc.setDialogTitle("Especifique a pasta onde salvar a música");

                if (JFileChooser.APPROVE_OPTION == fc.showSaveDialog(janela)) {
                    Tocador.salvarMusica(url.trim(), fc.getSelectedFile().toPath());
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        botaoSalvar.setBackground(Color.DARK_GRAY);
        botaoSalvar.setForeground(Color.WHITE);

        pularBtn.addActionListener(e -> {
            if(!tocador.proximaMusica()) {
                JOptionPane.showMessageDialog(janela, "Não há mais músicas na fila.");
                atualizarImagemETexto(MediaPlayer.Status.STALLED);
            }
        });
        JPanel meio = new JPanel();
        meio.setLayout(new GridLayout(2, 1));
        meio.add(pularBtn);
        meio.add(botaoSalvar);
        
        pularBtn.setBackground(Color.DARK_GRAY);
        pularBtn.setForeground(Color.WHITE);
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1,2));
        panel.add(meio);
        panel.add(botaoPlay);
        return panel;
    }
    public JPanel criarPainelSuperior() {
        adicionarMusicaURLBtn.addActionListener(e -> {
            String linkDaMusica = JOptionPane.showInputDialog("Link da moosica");
            if(linkDaMusica == null) return;
            try {
                tocador.adicionarMusica(linkDaMusica.trim());
            } catch (MediaException ex) {
                JOptionPane.showMessageDialog(janela, "Não foi possível encontrar a música. Erro: " + ex.getMessage());
            }
            atualizarImagemETexto(tocador.getStatus());
        });
        adicionarMusicaURLBtn.setBackground(Color.DARK_GRAY);
        adicionarMusicaURLBtn.setForeground(Color.WHITE);

        adicionarMusicaLocalBtn.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            // tipos suportados pelo JavaFX https://docs.oracle.com/javafx/2/api/javafx/scene/media/package-summary.html#SupportedMediaTypes
            fc.setFileFilter(new FileNameExtensionFilter("Arquivo de música (*.mp3, *.aac, *.wav)", "mp3", "aac", "wav"));
            if(JFileChooser.APPROVE_OPTION == fc.showOpenDialog(janela)) {
                String caminho = fc.getSelectedFile().toURI().toString();
                try {
                    tocador.adicionarMusica(caminho);
                    atualizarImagemETexto(tocador.getStatus());
                } catch (MediaException ex) {
                    JOptionPane.showMessageDialog(adicionarMusicaURLBtn, "Não foi possível encontrar a música. Erro: " + ex.getMessage());
                }
            }
        });
        adicionarMusicaLocalBtn.setBackground(Color.DARK_GRAY);
        adicionarMusicaLocalBtn.setForeground(Color.WHITE);

        JButton salvarPlaylistBtn = new JButton("<html><center><b>Salvar playlist atual</b><br>");
        salvarPlaylistBtn.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            fc.setDialogTitle("Especifique uma playlist para salvar");
            fc.setSelectedFile(new File(System.getProperty("user.home"), "playlist.txt"));
            if(JFileChooser.APPROVE_OPTION == fc.showSaveDialog(janela)) {
                try {
                    tocador.salvarPlaylist(fc.getSelectedFile().toPath());
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(salvarPlaylistBtn, "Erro ao salvar playlist: " + ex.getMessage());
                }
            }
        });
        salvarPlaylistBtn.setBackground(Color.DARK_GRAY);
        salvarPlaylistBtn.setForeground(Color.WHITE);
        
        carregarPlaylistBtn.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            fc.setFileFilter(new FileNameExtensionFilter("Arquivo de playlist", "txt"));
            if(JFileChooser.APPROVE_OPTION == fc.showOpenDialog(janela)) {
                try {
                    tocador.resetarECarregarPlaylist(fc.getSelectedFile().toPath());
                    atualizarImagemETexto(MediaPlayer.Status.READY);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(carregarPlaylistBtn, "Erro ao carregar playlist: " + ex.getMessage());
                }
            }
        });
        carregarPlaylistBtn.setBackground(Color.DARK_GRAY);
        carregarPlaylistBtn.setForeground(Color.WHITE);

        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(2, 2));
        painel.add(adicionarMusicaURLBtn);
        painel.add(carregarPlaylistBtn);
        painel.add(adicionarMusicaLocalBtn);
        painel.add(salvarPlaylistBtn);
        return painel;
    }
    public JPanel criarPainelInferior() {
        mostraFilaBtn.addActionListener(e -> JOptionPane.showMessageDialog(null, tocador.getFilaComoString()));
        mostraFilaBtn.setBackground(Color.DARK_GRAY);
        mostraFilaBtn.setForeground(Color.WHITE);

        volumeMaisBtn.addActionListener(e -> {
            double volumeAtual = tocador.getVolume();
            tocador.setVolume(volumeAtual <= 1000 ? volumeAtual + 10 : volumeAtual);
        });
        volumeMaisBtn.setBackground(Color.DARK_GRAY);
        volumeMaisBtn.setForeground(Color.RED);

        muteBtn.addActionListener(e -> {
            boolean mute = tocador.getMute();
            if(!mute) {
                muteBtn.setText("Desmutar");
            } else {
                muteBtn.setText("Mutar");
            }
            tocador.setMute(!mute);
        });
        muteBtn.setBackground(Color.DARK_GRAY);
        muteBtn.setForeground(Color.WHITE);

        volumeMenosBtn.addActionListener(e -> {
            double volumeAtual = tocador.getVolume();
            tocador.setVolume(volumeAtual >= 0 ? volumeAtual - 10 : volumeAtual);
        });
        volumeMenosBtn.setBackground(Color.DARK_GRAY);
        volumeMenosBtn.setForeground(Color.BLUE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));
        panel.add(mostraFilaBtn);
        panel.add(volumeMaisBtn);
        panel.add(muteBtn);
        panel.add(volumeMenosBtn);
        return panel;
    }
    public void setVisible(boolean b) {
        janela.setVisible(b);
    }
}
