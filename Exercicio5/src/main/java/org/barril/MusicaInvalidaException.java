package org.barril;

public class MusicaInvalidaException extends RuntimeException {
    public MusicaInvalidaException(Exception e) {
        super(e);
    }
}
