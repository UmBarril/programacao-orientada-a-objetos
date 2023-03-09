package SistemaAmigo;

public class AmigoNaoSorteadoException extends Exception {
    public AmigoNaoSorteadoException() {
        super("Não é possível encontrar o amigo pois ele ainda não foi sorteado!");
    }
}
