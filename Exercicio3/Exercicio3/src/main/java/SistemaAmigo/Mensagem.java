package SistemaAmigo;

public abstract class Mensagem {
    private String texto;
    private String emailRementente;
    private boolean anonima;

    public Mensagem(String texto, String emailRemetente, boolean anonima) {
        this.texto = texto;
        this.emailRementente = emailRemetente;
        this.anonima = anonima;
    }
    public String getTexto() {
        return this.texto;
    }
    public void setTexto(String texto) {
        this.texto = texto;
    }
    public abstract String getTextoCompletoAExibir();
    public String getEmailRemetente() {
        return emailRementente;
    }
    public void setEmailRementente(String emailRementente) {
        this.emailRementente = emailRementente;
    }
    public boolean ehAnonima() {
        return this.anonima;
    }
}
