package SistemaTurma;

import java.util.ArrayList;
import java.util.List;

public class Turma {
    private String disciplina;
    private List<Pessoa> alunos;

    public Turma(String disciplina) {
        this.alunos = new ArrayList<>();
        this.disciplina = disciplina;
    }

    public String getDisciplina() {
        return this.disciplina;
    }

    public void cadastrarAluno(Pessoa aluno) {
        this.alunos.add(aluno);
    }

    public Pessoa pesquisarAluno(String cpfDoAluno) {
        return this.alunos
                .stream()
                .filter(aluno -> aluno.getCpf().equals(cpfDoAluno))
                .findFirst()
                .orElse(null);
    }
}
