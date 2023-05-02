package org.barril;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.LinkedList;
import java.util.Queue;

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
        Media proximo = musicas.poll();
        if(proximo == null) {
            return false;
        }
        atual = new MediaPlayer(proximo);
        return true;
    }
    public MediaPlayer.Status getStatusAtual() {
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
            sb.append(i + ": " + m.getSource() + "<br>");
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
    public void mutaDesmuta() {
        mute = !mute;
        if(atual != null) {
            atual.setMute(mute);
        }
    }
    public boolean estaMutado() {
        return mute;
    }
    public String getMusicaAtual() {
        return musicas.peek().getSource();
    }

    public static void salvarMusica(String enderecoEletronico, String caminho) throws IOException {
        URL url = new URL(enderecoEletronico);
        ReadableByteChannel rbc = Channels.newChannel(url.openStream());
        FileOutputStream fo = new FileOutputStream(caminho);
        fo.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
    }
}
