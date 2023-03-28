package sistemaComercial;

import opcional.sistemaComercial.CategoriaProduto;
import opcional.sistemaComercial.Produto;
import opcional.sistemaComercial.SistemaComercialMap;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import static org.junit.jupiter.api.Assertions.*;

public class SistemaComercialMapTest {
    @Test
    public void testaCadastroProdutos() {
        SistemaComercialMap sistema = new SistemaComercialMap();
        Collection<Produto> alimentos = sistema.pesquisaProdutosDaCategoria(CategoriaProduto.ALIMENTO);
        assertTrue(alimentos.size() == 0);

        Produto produto = new Produto("0000", "pudim de feijão com carne de charque", 100.0, 999999999, CategoriaProduto.ALIMENTO);
        sistema.cadastraProduto(produto);
        assertTrue(sistema.existeProduto(produto));
        assertEquals(1, sistema.pesquisaProdutosDaCategoria(CategoriaProduto.ALIMENTO).size());
    }
}