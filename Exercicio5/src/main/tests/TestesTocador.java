import org.barril.NenhumaMusicaNaFilaException;
import org.barril.Tocador;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestesTocador {
    @Test
    public void testaVolume() {
        Tocador tocador = new Tocador();
        tocador.setVolume(1000);
        assertEquals(1000, tocador.getVolume());
    }
    @Test
    public void testaDesmuta() {
        Tocador tocador = new Tocador();
        tocador.setMute(!tocador.getMute());
        tocador.setMute(!tocador.getMute());
        assertFalse(tocador.estaMutado());
    }

    @Test
    public void testAdicionarMusica() {
        Tocador tocador = new Tocador();
        tocador.adicionarMusica("https://cdn.discordapp.com/attachments/650518741250998273/1102802849785729086/scrabdackleOST_-_Junk_Heap.mp3");
        assertTrue(tocador.temMusica());
    }

     @Test
     public void testProximaMusica() {
         Tocador tocador = new Tocador();
         tocador.adicionarMusica("https://cdn.discordapp.com/attachments/650518741250998273/1102802849785729086/scrabdackleOST_-_Junk_Heap.mp3");
         tocador.adicionarMusica("https://www.myinstants.com/media/sounds/among.mp3");
         assertTrue(tocador.proximaMusica());
         tocador.adicionarMusica("https://www.myinstants.com/media/sounds/among.mp3");
         assertTrue(tocador.proximaMusica());
         assertFalse(tocador.proximaMusica());
     }

     @Test
     public void testPausarMusicaSemMusica() {
        Tocador tocador = new Tocador();
        assertThrows(NenhumaMusicaNaFilaException.class, () -> tocador.pausarMusica());
    }
}