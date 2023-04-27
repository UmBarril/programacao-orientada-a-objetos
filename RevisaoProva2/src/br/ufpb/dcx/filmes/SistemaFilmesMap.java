package br.ufpb.dcx.filmes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SistemaFilmesMap implements SistemaFilmes {

    private Map<String, Filme> filmes;

    public SistemaFilmesMap() {
        this.filmes = new HashMap<String, Filme>();
    }

    @Override
    public void cadastraFilme(Filme f) throws FilmeJaExisteException{
        if (this.filmes.containsKey(f.getCodigo())) {
            throw new FilmeJaExisteException("Já existe filme com este código");
        } else {
            this.filmes.put(f.getCodigo(), f);
        }
    }

    @Override
    public Filme pesquisaFilme(String codigo) throws FilmeNaoExisteException {
        return null;
    }

    @Override
    public List<Filme> pesquisaFilmesDaCategoria(CategoriaFilme categoria) {
        return null;
    }

    @Override
    public int obterQuantidadeFilmesComAtor(String nomeAtor) {
        return 0;
    }

    @Override
    public void incluiAtorEmElencoDeFilme(String codigoFilme, String nomeAtor) throws FilmeNaoExisteException {

    }
    //Demais métodos da classe considerando que implementa a interface e que lança em seus
    //métodos mesma exceções dos métodos da interface.
}
