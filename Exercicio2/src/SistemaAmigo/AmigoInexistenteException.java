package SistemaAmigo;

public class AmigoInexistenteException extends Exception {
    public AmigoInexistenteException() {
        super("SistemaAmigo.Amigo não encontrado!");
    }
}
