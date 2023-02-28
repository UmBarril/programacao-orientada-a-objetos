import java.time.LocalDate;

public class Pessoa {
    public String nome;
    public LocalDate dataNascimento;
    public boolean sabeDirigir;

    public Pessoa() {
        this("placeholder", LocalDate.now(), false);
    }
    public Pessoa(String nome, LocalDate dataNascimento, boolean sabeDirigir) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.sabeDirigir = sabeDirigir;
    }
    public void tirarCarteira() {
        this.sabeDirigir = true;
    }
    public void mudarNome(String novoNome) {
        this.nome = novoNome;
    }
}
