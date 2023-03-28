package opcional.sistemaComercial;

public class ClientePJ extends Cliente {
    private String CNPJ;
    public ClientePJ(String nome, String endereco, String email, String CNPJ) {
        super(nome, endereco, email);
        this.CNPJ = CNPJ;
    }
    @Override
    public String getId() {
        return this.CNPJ;
    }
}
