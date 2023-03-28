package SistemaAmigo;

public class AmigoJaExisteException extends Exception {
    public AmigoJaExisteException() { super("Este amigo já está cadastrado no sistema!"); }
}
