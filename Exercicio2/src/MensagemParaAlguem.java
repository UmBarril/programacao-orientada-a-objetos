class MensagemParaAlguem extends Mensagem {
    private String emailDestinatario;

    public MensagemParaAlguem(String texto, String emailRemetente, String emailDestinatario, boolean anonima) {
        super(texto, emailRemetente, anonima);

        this.emailDestinatario = emailDestinatario;
    }

    public String getEmailDestinatario() {
        return this.emailDestinatario;
    }

    public void setEmailDestinatario(String emailDestinatario) {
        this.emailDestinatario = emailDestinatario;
    }

    @Override
    public String getTextoCompletoAExibir() {
        if(this.ehAnonima()) {
            return String.format("Mensagem para %s. Texto:  %s", this.getEmailDestinatario(), this.getTexto());
        }
        return String.format("Mensagem de: %s para %s. Texto: %s", this.getEmailRementente(), this.getEmailDestinatario(), this.getTexto());
    }
}
