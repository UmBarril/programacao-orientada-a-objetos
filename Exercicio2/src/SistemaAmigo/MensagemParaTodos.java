package SistemaAmigo;

class MensagemParaTodos extends Mensagem {
    public MensagemParaTodos(String texto, String emailRemetente, boolean anonima) {
        super(texto, emailRemetente, anonima);
    }

    @Override
    public String getTextoCompletoAExibir() {
        if(this.ehAnonima()) {
            return String.format("SistemaAmigo.Mensagem para todos. Texto: %s", this.getTexto());
        }
        return String.format("SistemaAmigo.Mensagem de %s para todos. Texto: %s", this.getEmailRementente(), this.getTexto());
    }
}
