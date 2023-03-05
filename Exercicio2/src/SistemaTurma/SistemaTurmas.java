package SistemaTurma;

import java.util.List;

public class SistemaTurmas {
    private List<Turma> turmas;

    public Pessoa pesquisarAluno(String disciplinaDoAluno, String cpfDoAluno) {
        return this.pesquisarTurma(disciplinaDoAluno).pesquisarAluno(cpfDoAluno);
    }

    // Extra
    public Turma pesquisarTurma(String disciplinaDaTurma) {
        return this.turmas
                .stream()
                .filter(t -> t.getDisciplina().equals(disciplinaDaTurma))
                .findFirst()
                .orElseThrow();
    }

    public static void main(String[] args) {

    }
}
