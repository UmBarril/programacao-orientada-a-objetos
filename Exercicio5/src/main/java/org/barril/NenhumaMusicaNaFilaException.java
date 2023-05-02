package org.barril;

public class NenhumaMusicaNaFilaException extends Exception {
    public NenhumaMusicaNaFilaException() {
        super("Não há nenhuma música na fila atual.");
    }
}
