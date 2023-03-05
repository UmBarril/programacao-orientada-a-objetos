package SistemaTurma;

class Pessoa implements Comparable<Pessoa> {
    private String nome;
    private String cpf;
    private int idade;

    public Pessoa(String nome, String cpf, int idade){
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
    }

    public Pessoa(){
        this("","",0);
    }
    public String getNome(){
        return this.nome;
    }

    public void setNome(String novoNome){
        this.nome = novoNome;
    }

    public int getIdade(){
        return this.idade;
    }

    public void setIdade(int novaIdade){
        this.idade = novaIdade;
    }

    public String getCpf(){
        return this.cpf;
    }

    public void setCpf(String novoCpf){
        this.cpf = novoCpf;
    }

    @Override
    public int compareTo(Pessoa o) {
        throw new RuntimeException("NÃO IMPLEMENTADO");
    }
}
