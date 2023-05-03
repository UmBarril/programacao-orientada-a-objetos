package org.barril;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.*;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Stream;

public class Tocador {
    private double volume = 50;
    private boolean mute = false;
    private Queue<Media> musicas = new LinkedList<>();
    private MediaPlayer atual;
    private Runnable aoFimDaMedia;

    // Retorna true se tiver uma música para tocar na fila e false caso o contrário.
    public boolean tocarMusica() {
        if(atual == null) {
            return false;
        }
        if(atual.getStatus() == MediaPlayer.Status.PLAYING) {
            proximaMusica();
        }
        atual.setOnEndOfMedia(aoFimDaMedia);
        atual.setMute(mute);
        atual.setVolume(volume);
        atual.play();
        return true;
    }
    public void setAoFimDaMusica(Runnable funcao) {
        aoFimDaMedia = funcao;
    }
    public void pausarMusica() throws NenhumaMusicaNaFilaException {
        if(atual == null) {
            throw new NenhumaMusicaNaFilaException();
        }
        atual.pause();
    }
    public void adicionarMusica(String url) {
        Media media = new Media(url);
        musicas.add(media);
        if(atual == null) {
            atual = new MediaPlayer(media);
        }
    }
    // retorna verdadeiro se tinha uma música seguinte / falso se não tiver
    public boolean proximaMusica() {
        if(atual == null) {
            return false;
        } else if(atual.getMedia() != null) {
            atual.dispose();
            atual = null;
        }
        musicas.poll(); // tirar a musica que está tocando agora
        Media proximo = musicas.peek(); // pegar a música seguinte
        if(proximo == null) {
            return false;
        }
        atual = new MediaPlayer(proximo);
        return true;
    }
    public MediaPlayer.Status getStatus() {
        if(atual == null) {
            return MediaPlayer.Status.STALLED;
        }
        return atual.getStatus();
//        return MediaPlayer.Status.PLAYING;
    }
    public boolean temMusica() {
        return !musicas.isEmpty();
    }
    public String getFilaComoString() {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        sb.append("<html>");
        for(Media m : musicas) {
            sb.append(i).append(": ").append(m.getSource()).append("<br>");
            i++;
        }
        sb.append("</html>");
        return sb.toString();
    }
    public void setVolume(double volume) {
        this.volume = volume;
        if(atual != null) {
            atual.setVolume(volume);
        }
    }
    public double getVolume() {
        return volume;
    }
    public void setMute(boolean mute) {
        this.mute = mute;
        if(atual != null) {
            this.atual.setMute(mute);
        }
    }
    public boolean getMute() {
        return mute;
    }
    public boolean estaMutado() {
        return mute;
    }
    public static void salvarMusica(String enderecoEletronico, Path caminho) throws IOException {
        URL url = new URL(enderecoEletronico);
        try (
            ReadableByteChannel rbc = Channels.newChannel(url.openStream());
            FileOutputStream fo = new FileOutputStream(caminho.toFile())
        ) {
            fo.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        }
    }
    public void resetarECarregarPlaylist(Path caminho) throws IOException {
        proximaMusica(); // parar musica atual se estiver tocando
        musicas = new LinkedList<>(); // resetar playlist
        try(Stream<String> lines = Files.lines(caminho)) { // carregar playlist
            lines.forEach(this::adicionarMusica);
        }
    }
    public void salvarPlaylist(Path caminho) throws IOException {
        try(FileWriter fw = new FileWriter(caminho.toFile())) {
            for(Media media : musicas) {
                fw.write(media.getSource());
            }
        }
    }
}
