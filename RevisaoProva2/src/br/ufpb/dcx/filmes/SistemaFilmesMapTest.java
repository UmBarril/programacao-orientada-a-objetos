package br.ufpb.dcx.filmes;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class SistemaFilmesMapTest {
    @Test
    public void testaCadastroEPesquisa() {
        SistemaFilmesMap sistema = new SistemaFilmesMap();
        List<CategoriaFilme> categorias = new ArrayList<>();
        categorias.add(CategoriaFilme.ACAO);
        try {
            sistema.cadastraFilme(new Filme("001", "Matrix", categorias, new ArrayList<>()));
            Filme f = sistema.pesquisaFilme("001");
            assertEquals("Matrix", f.getNome());
            // TODO: Código a adicionar

        } catch(FilmeJaExisteException | FilmeNaoExisteException e) {
            fail("Não deveria lançar exceção ao cadastrar e pesquisar depois");
        }
    }
}
