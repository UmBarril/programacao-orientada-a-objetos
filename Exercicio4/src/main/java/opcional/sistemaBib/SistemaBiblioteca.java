package opcional.sistemaBib;

public interface SistemaBiblioteca {
    public boolean pegarLivro(String matriculaUsuario, String idLivro)
            throws UsuarioInexistenteException, LivroInexistenteException;
    public boolean devolverLivro(String matriculaUsuario, String idLivro)
            throws UsuarioInexistenteException, LivroInexistenteException;
    public boolean cadastrarUsuario(String matricula, String nome, TipoUsuario tipo)
            throws UsuarioJaExisteException;
    public void cadastrarLivro(String id, String titulo, List<String> autores)
            throws LivroJaExisteException;
    public Map<String, Livro> getTodosOsLivros();
    public Collection<Usuario> getUsuarios();
    public Collection<Livro> getLivrosEmprestadosA(String matriculaUsuario);
}
