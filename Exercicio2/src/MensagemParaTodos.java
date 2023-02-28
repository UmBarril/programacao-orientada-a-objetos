class MensagemParaTodos extends Mensagem {
    public MensagemParaTodos(String texto, String emailRemetente, boolean anonima) {
        super(texto, emailRemetente, anonima);
    }
    public String getTextoCompletoAExibir() {
        if(this.ehAnonima()) {
            return String.format("Mensagem para todos. Texto: %s", this.getTexto());
        }
        return String.format("Mensagem de %s para todos. Texto: %s", this.getEmailRementente(), this.getTexto());
    }
}
