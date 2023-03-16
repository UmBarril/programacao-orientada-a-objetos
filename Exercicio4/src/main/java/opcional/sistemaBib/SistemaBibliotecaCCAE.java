package opcional.sistemaBib;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SistemaBibliotecaCCAE {
    private Map<String, Livro> livros = new HashMap<String, Livro>();
    private Map<String, Usuario> usuarios = new HashMap<String, Usuario>();

    public boolean pegarLivro(String matricula, String idLivro) {

    }
    public boolean devolverLivro(String matricula, String idLivro) {

    }
    public boolean cadastrarUsuario(String matricula, String nome, TipoUsuario tipo) {

    }
    public void casdastrarLivro(String id, String titulo, List<String> auutores) {

    }
    public Map<String, Livro> getTodosOsLivros() {
        Map<String, Livro> livroMap = new HashMap(this.livros);
        return livroMap;
    }
    public Map<String, Usuario> getUsuarios() {
        return usuarios;
    }
    public Collection<Livro> getLivrosEmprestados(String matriculaUsuario) {
        Collection<Livro> livrosEmprestados = new ArrayList<>();
        for(Livro livro : this.livros.values()) {
            // TODO
        }
        return  livrosEmprestados;
    }
}
