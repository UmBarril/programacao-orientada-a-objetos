package br.ufpb.dcx.filmes;

import java.util.ArrayList;
import java.util.List;

public class Filme {
    private String codigo;
    private String nome;
    private List<CategoriaFilme> categorias;
    private List<String> nomesAtores;

    public Filme(String codigo) {
        this(codigo,"", new ArrayList<>(), new ArrayList<>());
    }

    public Filme(String codigo,String nome, List<CategoriaFilme> categorias,
                 List<String> nomesAtores) {
        this.codigo = codigo;
        this.nome = nome;
        this.categorias = categorias;
        this.nomesAtores = nomesAtores;
    }

    public boolean ehDaCategoria(CategoriaFilme categoria) {
        for (CategoriaFilme cat: this.categorias) {
            if (cat ==categoria) {
                return true;
            }
        }
        return false;
    } //Demais métodos da classe por aqui …

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
