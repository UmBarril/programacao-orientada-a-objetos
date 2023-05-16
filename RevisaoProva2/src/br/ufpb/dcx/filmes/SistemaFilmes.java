package br.ufpb.dcx.filmes;

import java.util.List;
public interface SistemaFilmes {
    void cadastraFilme(Filme f) throws FilmeJaExisteException;
    Filme pesquisaFilme(String codigo) throws FilmeNaoExisteException;
    List<Filme> pesquisaFilmesDaCategoria(CategoriaFilme categoria);
    int obterQuantidadeFilmesComAtor(String nomeAtor);
    void incluiAtorEmElencoDeFilme(String codigoFilme, String nomeAtor)
            throws FilmeNaoExisteException;
}

