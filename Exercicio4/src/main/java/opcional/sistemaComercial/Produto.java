package opcional.sistemaComercial;

public class Produto {
    private String codigo;
    private String descricao;
    private double precoVenda;
    private int quantidadeEmEstoque;
    private CategoriaProduto categoria;

    public Produto(String codigo, String descricao, double precoVenda, int quantidadeEmEstoque, CategoriaProduto categoria) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.precoVenda = precoVenda;
        this.quantidadeEmEstoque = quantidadeEmEstoque;
        this.categoria = categoria;
    }
    public Produto() {
        this("<PADRAO>", "<PADRAO>", 0.0D, 0, CategoriaProduto.ROUPA);
    }
    public String getCodigo() {
        return codigo;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public double getPrecoVenda() {
        return precoVenda;
    }
    public void setPrecoVenda(double precoVenda) {
        this.precoVenda = precoVenda;
    }
    public int getQuantidadeEmEstoque() {
        return quantidadeEmEstoque;
    }
    public void setQuantidadeEmEstoque(int quantidadeEmEstoque) {
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }
    public CategoriaProduto getCotegoria() {
        return categoria;
    }
    public void setCotegoria(CategoriaProduto categoria) {
        this.categoria = categoria;
    }
}
