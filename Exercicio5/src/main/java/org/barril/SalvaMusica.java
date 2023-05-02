package org.barril;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class SalvaMusica {
    public static void salvar(String enderecoEletronico, String caminho) throws IOException {
        URL url = new URL(enderecoEletronico);
        ReadableByteChannel rbc = Channels.newChannel(url.openStream());
        FileOutputStream fo = new FileOutputStream(caminho);
        fo.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
    }
}
