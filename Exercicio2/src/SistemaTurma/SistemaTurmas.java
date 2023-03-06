package SistemaTurma;

import java.util.ArrayList;
import java.util.List;

public class SistemaTurmas {
    private List<Turma> turmas;

    public SistemaTurmas() {
        this.turmas = new ArrayList<>();
    }

    public Pessoa pesquisarAlunoEmTurma(String cpfDoAluno, String disciplinaDoAluno) {
        return this.pesquisarTurma(disciplinaDoAluno).pesquisarAluno(cpfDoAluno);
    }

    public Turma pesquisarTurma(String disciplinaDaTurma) {
        return this.turmas
                .stream()
                .filter(t -> t.getDisciplina().equalsIgnoreCase(disciplinaDaTurma))
                .findFirst()
                .orElse(null);
    }

    // Cadastra um aluno em uma turma existente ou cria uma nova caso não exista ainda.
    public void cadastrarAlunoEmTurma(Pessoa aluno, String disciplinaDaTurma) {
        if(aluno == null) {
            return;
        }
        Turma turma = this.pesquisarTurma(disciplinaDaTurma);
        if(turma == null) {
            turma = new Turma(disciplinaDaTurma);
            turmas.add(turma);
        }
        turma.cadastrarAluno(aluno);
    }

    public static void main(String[] args) {
        SistemaTurmas sistemaTurmas = new SistemaTurmas();
        sistemaTurmas.cadastrarAlunoEmTurma(new Pessoa("Jorge", "222111333", 15), "Matemática");
        System.out.println("Teste: " + sistemaTurmas.pesquisarAlunoEmTurma("222111333", "matemática").getNome());
    }
}
