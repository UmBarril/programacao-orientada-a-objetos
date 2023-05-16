package br.ufpb.dcx.filmes;

public class FilmeJaExisteException extends Exception {
    public FilmeJaExisteException(String msg) {
        super(msg);
    }
}
